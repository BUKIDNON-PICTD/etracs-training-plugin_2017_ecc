import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.reports.*;

class ReportPermitteeCumulativeQuantity extends ReportController {
    
    @Service("ReportPermitteeCumulativeQuantityService")
    def svc;

    def title = "Total Quantity";
    final String REPORT_PATH = 'etracs/training/plugin/report/';
    String reportName = REPORT_PATH + 'cumulativequantity.jasper';
    def data
    
    def getReportData() {
        //entity._schemaname = 'hrmis_appointmentjoborder'
        data = svc.getPermitteeCumulativeQuantity(entity);
        return data.reportdata
    } 

//    void buildReportData(entity, asyncHandler){
//        svc.getSIByIPCRId(entity, asyncHandler)
//    }

    Map getParameters(){
        return data.parameters;
    }
    
    //SubReport[] getSubReports() {
   //     return [ 
      //     new SubReport("AppointmentItemList", REPORT_PATH + "appointmentjoborderitems.jasper"),           
      //     new SubReport("SignatoryGroup", REPORT_PATH + "jobordersignatorygroupitems.jasper"),

    //    ] as SubReport[];    
   // }
} 