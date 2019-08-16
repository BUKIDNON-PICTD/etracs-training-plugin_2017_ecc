package etracs.training.plugin;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.util.*;
import com.rameses.osiris2.reports.*;
import etracs.training.plugin.report.TransferTaxAssessmentPOReportController


public class TransferTaxAssessmentController extends TransferTaxAssessmentPOReportController
{
    //NEW NEW NEW NEW
    @Service("TransferTaxPayOrderService")
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
    def ttinfo;
    
    def mode;
    
    //def officeOrigin = ['Admin', 'BENRO'];
    //def officeOrigin = 'Assessor';
    
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
    
    void compute(){
        def assessment = svc.generateAssessment(entity);
        ttinfo = TemplateProvider.instance.getResult( "etracs/training/plugin/templates/ttassessment.gtpl", [entity:assessment] );
        mode = MODE_ASSESS;
    }
    
    def save(){
        
        def pos = svc.submitAssessmentForPayment(entity);
        //MsgBox.alert("Order Submitted to PTO");
        MsgBox.alert "Payment Order Number : " + pos.ordernum
        mode = MODE_READ;
        
        return preview(pos);
        
        //return pos;
        //MsgBox.alert "Payment Order Number : " + x.ordernum

    }

    void cancel(){          
         mode = MODE_READ;
    }
    
//    void entityChanged(){
//        ttassessmentHandler.reload();
//    }
//    
//    void afterCreate(payorder){
//        entityChanged();
//    }
    
    
    
   

    /* ========== Lookup Payer ========= */
    def getLookupPayer(){
        return Inv.lookupOpener('entity:lookup',[
                onselect :{
                    entity.payer = it;
                    entity.paidby = it.name;
                    entity.paidbyaddress = it.address.text;
                    binding.refresh('entity.paidby.*')
                },
                
                onempty: {
                    //
                }
        ])
    }
    
   
}