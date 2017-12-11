package etracs.training.plugin.report;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;
import com.rameses.gov.etracs.rpt.common.*;
import com.rameses.etracs.shared.*;


public class PayOrderReportAdmin extends ReportController
{
   @Service('BUKPayOrderService')
   def svc;
   
   @Service("ReportParameterService")
   def paramSvc;
   
   def query = [:];
   //def clientContext = com.rameses.rcp.framework.ClientContext.currentContext;
   
   String title = 'SG Assessment';
   String reportPath = 'etracs/training/plugin/report/';
   String reportName = reportPath + 'PayOrderReportAdmin.jasper';
   
   public def getReportData(){
       //entity is personnel info akin to the platform
//       MsgBox.alert(svc.getReportData(entity))
       return svc.getReportData(entity);
       
   }
   Map getParameters (){
        def params = paramSvc.getStandardParameter()
       
        params.signature = ReportUtil.getImageAsStream("cajessignature.jpg")
        return params 
   }
           

   SubReport[] getSubReports(){
       return[
           new SubReport('SGPAYORDERITEMS', reportPath+'PayOrderReportAdminItems.jasper'), 
       ]
   }
}

