package etracs.training.plugin;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.util.*;
import com.rameses.osiris2.reports.*;
import etracs.training.plugin.report.SGAssessmentReportAdminWidItemsController


public class SGAssessmentController extends SGAssessmentReportAdminWidItemsController
{
    //NEW NEW NEW NEW
    @Service("BUKPayOrderService")
    def svc;
    
    @Service("BUKPermitteeCRUDService")
    def psvc;
    
    @Binding
    def binding;
    
    
    boolean allowApprove = false;
    boolean allowEdit = false;
    boolean allowDelete = false;
    def entity;
    def items;
    def sginfo;
    
    def mode;
    
    def officeOrigin = ['Admin', 'BENRO'];
    
    def MODE_READ = 'read';
    def MODE_CREATE = 'create';
    def MODE_EDIT = 'edit';
    def MODE_ASSESS = 'assess';
    def MODE_INIT = 'init';
    
   
    
    void init(){
     entity = [:];
     entity.items =[];
     mode = MODE_INIT;
    }
    
    void assess(){
        def assessment = svc.generateAssessment(entity);
        sginfo = TemplateProvider.instance.getResult( "etracs/training/plugin/templates/sgassessment.gtpl", [entity:assessment] );
        mode = MODE_ASSESS;
    }
    
    def save(){
        
        def pos = svc.submitAssessmentForPayment(entity);
        MsgBox.alert("Order Submitted to PTO");
        mode = MODE_READ;
        return preview(pos);
//        def handle = findReportOpener(pos);
//        handle.report.viewReport();
    }
//    def findReportOpener(def reportData) {
//        //check fist if form handler exists.
//        def o = InvokerUtil.lookupOpener( "churvaness:report", [reportData:reportData] );
//        if(!o)
//            throw new Exception("Handler not found");
//        return o.handle;
//    }

    void cancel(){          
         mode = MODE_READ;
    }
    //override afterOpen to load trainings
    //public void afterOpen(Object payorder){
     //   payorder.putAll(service.open(payorder));
   // }
    
    void entityChanged(){
        sgassessmentHandler.reload();
    }
    
    void afterCreate(payorder){
        entityChanged();
    }
    
    
    
    /*================== Items Support ===========*/
   
    def selectedSGAssessmentitem;
   
    def sgassessmentHandler = [
       getRows : {entity.items.size() + 1 },
       fetchList: { entity.items },
       
       createItem : {
           return[
               //payorderid : entity.objid,
               isnew : true
           ]
       },
       
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                service.deleteItem(it)               
                entity.items.remove(it)
                entityChanged()
                return true;
            }
            return false;
        },
        
        onAddItem : {
            //MsgBox.alert (qtybalance())
            //def balance =  (it.eccallowed - it.totalqty) - 1;
            if(it.qty > (qtybalance()) )throw new Exception("ECC Limit Exceeded. Please enter a lesser quantity");             
                entity.items << it; /* add to list syntax */
     },
     
        
        validate:{li->
            def item=li.item;
        }
        
  ] as EditorListModel; 
  
    
   def qtybalance(){
       return psvc.permitteeTotalQuantity(entity.permobjid);
   }
  
    
    /* ========== Lookup Payer ========= */
    def getLookupPayer(){
        return Inv.lookupOpener('permittee:lookup',[
                onselect :{
                    entity.payer = it;
                    entity.paidby = it.name;
                    entity.paidbyaddress = it.location;
                    entity.permobjid = it.permobjid;
                    binding.refresh('entity.paidby.*')
                },
                
                onempty: {
                    //
                }
        ])
    }
     /* ========== Lookup Itemaccounts ========= */
    def getLookupSGKind(){
        return Inv.lookupOpener('sgkind:lookup',[
                entity:entity,
                onselect :{   
                    selectedSGAssessmentitem.objid = it.objid;
                    selectedSGAssessmentitem.name = it.name;
                    selectedSGAssessmentitem.code = it.code;
                    selectedSGAssessmentitem.eccallowed = it.eccallowed;
                    selectedSGAssessmentitem.totalqty = it.totalqty;
                    selectedSGAssessmentitem.qty = it.qty;
                                   
                        
                     
                    
                    binding.refresh('selectedSGAssessmentitem.name.*')
                    //MsgBox.alert (entity.items)
                    //binding.refresh('entity.paidby.*')
                },
                
                onempty: {
                    //
                }
        ])
    }
    
   
}