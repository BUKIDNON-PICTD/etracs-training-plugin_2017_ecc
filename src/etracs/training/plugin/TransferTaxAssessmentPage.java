
package etracs.training.plugin;

import com.rameses.osiris2.themes.FormPage;
import com.rameses.rcp.ui.annotations.StyleSheet;
import com.rameses.rcp.ui.annotations.Template;


@StyleSheet
public class TransferTaxAssessmentPage extends javax.swing.JPanel {

    /**
     * Creates new form PayOrderPage
     */
    public TransferTaxAssessmentPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        xFormPanel2 = new com.rameses.rcp.control.XFormPanel();
        xLookupField2 = new com.rameses.rcp.control.XLookupField();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xTextField4 = new com.rameses.rcp.control.XTextField();
        xActionBar1 = new com.rameses.rcp.control.XActionBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        xHtmlView1 = new com.rameses.rcp.control.XHtmlView();
        xFormPanel3 = new com.rameses.rcp.control.XFormPanel();
        xDecimalField1 = new com.rameses.rcp.control.XDecimalField();
        xIntegerField1 = new com.rameses.rcp.control.XIntegerField();
        xCheckBox1 = new com.rameses.rcp.control.XCheckBox();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Transfer Tax Assessment");
        setBorder(xTitledBorder1);
        setName(""); // NOI18N

        xFormPanel2.setName(""); // NOI18N

        xLookupField2.setCaption("Payer");
        xLookupField2.setExpression("#{entity.payer.name}");
        xLookupField2.setHandler("lookupPayer");
        xLookupField2.setName("entity.payer"); // NOI18N
        xLookupField2.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel2.add(xLookupField2);

        xTextField3.setCaption("Paid By");
        xTextField3.setCaptionFontStyle("");
        xTextField3.setDepends(new String[] {"entity"});
        xTextField3.setName("entity.paidby"); // NOI18N
        xTextField3.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField3.setRequired(true);
        xFormPanel2.add(xTextField3);

        xTextField4.setCaption("Address");
        xTextField4.setDepends(new String[] {"entity"});
        xTextField4.setName("entity.paidbyaddress"); // NOI18N
        xTextField4.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField4.setRequired(true);
        xFormPanel2.add(xTextField4);

        xActionBar1.setFormName("sgassessment");
        xActionBar1.setName("formActions"); // NOI18N

        xHtmlView1.setName("ttinfo"); // NOI18N
        jScrollPane1.setViewportView(xHtmlView1);

        xFormPanel3.setName(""); // NOI18N

        xDecimalField1.setCaption("Amount Considered");
        xDecimalField1.setCaptionWidth(120);
        xDecimalField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        xDecimalField1.setName("entity.amountconsidered"); // NOI18N
        xDecimalField1.setPreferredSize(new java.awt.Dimension(200, 20));
        xDecimalField1.setRequired(true);
        xFormPanel3.add(xDecimalField1);

        xIntegerField1.setCaption("# of Months");
        xIntegerField1.setCaptionWidth(120);
        xIntegerField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        xIntegerField1.setName("entity.nummonth"); // NOI18N
        xIntegerField1.setPreferredSize(new java.awt.Dimension(200, 20));
        xIntegerField1.setRequired(true);
        xFormPanel3.add(xIntegerField1);

        xCheckBox1.setCaption("Check if Surcharge");
        xCheckBox1.setCaptionWidth(120);
        xCheckBox1.setName("entity.ifsurcharge"); // NOI18N
        xFormPanel3.add(xCheckBox1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(xFormPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                            .addComponent(xActionBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 39, Short.MAX_VALUE))
                    .addComponent(xFormPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(xActionBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(xFormPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xFormPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private com.rameses.rcp.control.XActionBar xActionBar1;
    private com.rameses.rcp.control.XCheckBox xCheckBox1;
    private com.rameses.rcp.control.XDecimalField xDecimalField1;
    private com.rameses.rcp.control.XFormPanel xFormPanel2;
    private com.rameses.rcp.control.XFormPanel xFormPanel3;
    private com.rameses.rcp.control.XHtmlView xHtmlView1;
    private com.rameses.rcp.control.XIntegerField xIntegerField1;
    private com.rameses.rcp.control.XLookupField xLookupField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    private com.rameses.rcp.control.XTextField xTextField4;
    // End of variables declaration//GEN-END:variables
}
