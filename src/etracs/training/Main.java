package etracs.training;

import com.rameses.osiris2.test.OsirisTestPlatform;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            Map env = new HashMap();
            env.put("app.host", "localhost:8072");
            env.put("app.context", "etracs25");
            env.put("app.cluster", "osiris3");
        
            Map roles = new HashMap();
            roles.put("PO.SPONEW", null);
            roles.put("PO.SPOLIST", null);
            roles.put("PO.SPOADMIN", null);
            roles.put("PO.SPOLISTREPORT", null);
            roles.put("PO.SPOADMINLISTREPORT", null);
            roles.put("PO.SPOSGASSESSOR", null);
            roles.put("PO.SPOSGMASTER", null);
            roles.put("PO.SPOPERMITTEE", null);
            roles.put("PO.SPOPERMITTEELIST", null);
            roles.put("PO.ORESPONEW", null);
            roles.put("PO.SPOHOUSINGREPORT", null);
            roles.put("PO.SPOOFFLINE", null);
            roles.put("PO.TRANSFERTAX", null);
            
            Map profile = new HashMap();
            profile.put("CLIENTTYPE", "desktop");
            profile.put("USERID", "ADMIN");
            profile.put("USERNAME", "ADMIN");
            profile.put("FULLNAME", "ADMINISTRATOR");
            profile.put("ORGID", "059");
            profile.put("ORGCODE", "059");
            profile.put("ORGNAME", "BUKIDNON");
            profile.put("ORGCLASS", "PROVINCE");
            OsirisTestPlatform.runTest(env, roles, profile);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
       
        
    }
}
