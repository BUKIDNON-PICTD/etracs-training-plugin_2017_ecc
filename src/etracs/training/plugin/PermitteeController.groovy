package etracs.training.plugin;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

public class PermitteeController extends CRUDController
{
    //NEW NEW NEW NEW NEW NEW
    
    @Binding
    def binding;
    
    String serviceName = 'BUKPermitteeCRUDService';
    String entityName = 'permittee';
    boolean allowApprove = false;
    def aftersave;
    
    //def genders = ["Male", "Female"];
    //def nationalities = ["FILIPINO", "AMERICAN", "ESPANOL"];
    //def civilservice = ["passed", "failed", "nottaken"]
    
    
    //override create entity to initialize trainings field   
    public Map createEntity(){
        return [ permits:[] ];
    }
    
    //override afterOpen to load trainings
    public void afterOpen(Object entity){
        entity.putAll(service.open(entity));
    }
    
    void entityChanged(){
        commodityHandler.reload();
    }
    
    void afterCreate(permittee){
        entityChanged();
    }
    
    void afterSave(permittee){
        
    }
    
    /*================== Permit Info Support ===========*/
    
    //def commoditytypes = ['Sand & Gravel', 'Limestone', 'Mt. Quarry', 'Boulders', 'Phyllites'];
    def status = ['New', 'Renewal', 'For Approval', 'Suspended', 'Gov Approval Only'];
    def selectedPermit;
   
    def permitHandler = [
       getRows : {entity.permits.size() + 1 },
       fetchList: { entity.permits },
       
       createItem : {
           return[
               objid : 'PER' + new java.rmi.server.UID(),
               permobjid : entity.objid,
               isnew : true,
               commoditys:[]
           ]
       },
       
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                service.deleteCommodity(it)               
                entity.permits.remove(it)
                entityChanged()
                return true;
            }
            return false;
        },
        onColumnUpdate:{item,colName ->
            entity.permits.each{ y ->
                if(item.objid == y.objid){
                    //y.commodityname = item.commodityname;
                    y.permitno = item.permitno;
                    y.status = item.status;
                    y.startdate = item.startdate;
                    y.enddate = item.enddate;
                    y.eccallowed = item.eccallowed;
                }
            }
        },
        onAddItem : {
            entity.permits << it; /* add to list syntax */
        },
             
    ] as EditorListModel;
    
     /*================== Commodity Info Support ===========*/
    
    //def commoditytypes = ['Sand & Gravel', 'Limestone', 'Mt. Quarry', 'Boulders', 'Phyllites'];
    //def status = ['New', 'Renewal', 'For Approval', 'Suspended', 'Gov Approval Only'];
    def selectedCommodity;
   
    def commodityHandler = [
      // getRows : {entity.permits.commoditys.size() + 1 },
       fetchList: { return selectedPermit?.commoditys },
       
      // createItem : {
       //    return[
               //objid : 'PC' + new java.rmi.server.UID(),
               //permobjid : entity.objid,
               //isnew : true
       //    ]
      // },
       
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                service.deleteCommodity(it)               
                selectedPermit.commoditys.remove(it)
                entityChanged()
                return true;
            }
            return false;
        },
        onColumnUpdate:{item,colName ->
            selectedPermit.commoditys.each{ y ->
                if(item.objid == y.objid){
                    y.commodityname = item.commodityname;
                    //y.status = item.status;
                    //y.startdate = item.startdate;
                    //y.enddate = item.enddate;
                    //y.eccallowed = item.eccallowed;
                }
            }
        },
        onAddItem : {
           // MsgBox.alert(entity.permits.commoditys)
            selectedPermit.commoditys << it; /* add to list syntax */
        },
             
    ] as EditorListModel;
    
   
   /* ========== Lookup Payer ========= */
    def getLookupPayer(){
        return Inv.lookupOpener('entity:lookup',[
                onselect :{
                    entity.entobjid = it.objid;
                    entity.payer = it;
                    entity.permitteename = it.name;
                    //entity.paidbyaddress = it.address.text;
                    binding.refresh('entity.permitteename.*')
                },
                
                onempty: {
                    //
                }
        ])
    }
    
    def getLookupCommodity(){       
        return Inv.lookupOpener('commodity:lookup',[
                onselect :{
                    selectedCommodity.commodityid = it.objid;
                    selectedCommodity.commodityname = it.commodityname;
                    selectedCommodity.commoditycode = it.commoditycode;
                    //selectedCommodity.name = it.name;
                    //selectedCommodity.code = it.code;  
                    //selectedCommodity.qty = it.qty;  
                  
                    binding.refresh('selectedCommodity.commodityname.*')
                    //binding.refresh('entity.paidby.*')
                },
                
                onempty: {
                    //
                }
        ])
    }
    
    def print() {
        def op = Inv.lookupOpener( "test:permittee", [entity: entity] );
        op.target = 'self';
        return op;
    }
    
}