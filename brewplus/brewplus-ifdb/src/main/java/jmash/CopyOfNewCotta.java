
package jmash;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 *
 * @author Azatoth
 */
public class CopyOfNewCotta extends javax.swing.JInternalFrame {

	private static Logger LOGGER = Logger.getLogger(CopyOfNewCotta.class);

	private Ricetta ricettaOriginale = new Ricetta();
	private Ricetta ricettaReale = new Ricetta();
	PanelFermentationLog logF;
	PanelMashLog mashLog;
	PanelDegustazione degu = new PanelDegustazione();
	PanelMashStep mashDesign;
	WaterNeeded waterNeeded = new WaterNeeded();
	private File file;

	/** Creates new form NewCotta */
	public CopyOfNewCotta() {
		initComponents();
		mashDesign = new PanelMashStep(null, this);
		Component c = ricettaOriginale.getComponent(0);
		JPanel pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		pnl.add(c, java.awt.BorderLayout.CENTER);
		// pnl.add(jPanel1, java.awt.BorderLayout.WEST);
		tabPane.add(pnl, "Dati previsti da ricetta originale");
		setBorder(Utils.getDefaultBorder());
		((JRootPane) c).setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 100, 50), 3, true));
		ricettaOriginale.setEnabled(false);
		ricettaOriginale.setCotta(true);
		c = ricettaReale.getComponent(0);
		ricettaReale.setCotta(true);
		tabPane.add(c, "Dati registrati nella cotta");
		((JRootPane) c).setBorder(Utils.getDefaultBorder());
		// ricettaReale.hideMashDesign();
		mashLog = new PanelMashLog(ricettaReale, this);

		ricettaReale.mashDesign.setLinkedPanel(mashDesign);
		mashDesign.addPHAxis();
		mashDesign.setReadOnly();
		mashLog.setMainPanel(mashDesign);
		tabPane.add(mashLog, "Log del mash");
		tabPane.add(waterNeeded.getComponent(0), "Acqua usata");
		logF = new PanelFermentationLog(ricettaReale, this);
		tabPane.add(logF, "Log della fermentazione");

		tabPane.add(degu, "Scheda degustazione");
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jToolBar1 = new javax.swing.JToolBar();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		tabPane = new javax.swing.JTabbedPane();
		jPanel2 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		fldBrewer = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		fldNoteA = new javax.swing.JEditorPane();
		jLabel3 = new javax.swing.JLabel();
		jScrollPane4 = new javax.swing.JScrollPane();
		fldNoteB = new javax.swing.JEditorPane();
		jLabel4 = new javax.swing.JLabel();
		fldDate = new com.toedter.calendar.JDateChooser();
		jLabel5 = new javax.swing.JLabel();

		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setTitle("Registrazione di una cotta");
		setFont(getFont());
		getContentPane().setLayout(new java.awt.GridBagLayout());

		jToolBar1.setFloatable(false);
		jToolBar1.setRollover(true);

		jButton1.setIcon(new ImageIcon(CopyOfNewCotta.class.getResource("/jmash/images/new.png"))); // NOI18N
		jButton1.setToolTipText("Nuova cotta");
		jButton1.setFocusable(false);
		jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jToolBar1.add(jButton1);

		jButton2.setIcon(new ImageIcon(CopyOfNewCotta.class.getResource("/jmash/images/open.png"))); // NOI18N
		jButton2.setToolTipText("Apri cotta");
		jButton2.setFocusable(false);
		jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});
		jToolBar1.add(jButton2);

		jButton3.setIcon(new ImageIcon(CopyOfNewCotta.class.getResource("/jmash/images/save.png"))); // NOI18N
		jButton3.setToolTipText("Salva cotta");
		jButton3.setFocusable(false);
		jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});
		jToolBar1.add(jButton3);

		jButton4.setIcon(new ImageIcon(CopyOfNewCotta.class.getResource("/jmash/images/saveas.png"))); // NOI18N
		jButton4.setToolTipText("Salva come...");
		jButton4.setFocusable(false);
		jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});
		jToolBar1.add(jButton4);

		jButton5.setIcon(new ImageIcon(CopyOfNewCotta.class.getResource("/jmash/images/filefind.png"))); // NOI18N
		jButton5.setToolTipText("Importa ricetta di partenza");
		jButton5.setFocusable(false);
		jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});
		jToolBar1.add(jButton5);

		jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/upload.png"))); // NOI18N
		jButton6.setToolTipText("Importa ricetta di partenza da www.hobbybirra.it");
		jButton6.setFocusable(false);
		jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});
		// modifica IXTLANAS nascosto importazione ricetta
		// jToolBar1.add(jButton6);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
		gridBagConstraints.weightx = 0.1;
		getContentPane().add(jToolBar1, gridBagConstraints);

		jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		jPanel2.setLayout(new java.awt.GridBagLayout());

		jLabel1.setFont(jLabel1.getFont());
		jLabel1.setText("Birrai");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
		jPanel2.add(jLabel1, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
		jPanel2.add(fldBrewer, gridBagConstraints);

		jLabel2.setText("Note cotta");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
		jPanel2.add(jLabel2, gridBagConstraints);

		jScrollPane3.setPreferredSize(new java.awt.Dimension(550, 120));
		jScrollPane3.setViewportView(fldNoteA);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
		jPanel2.add(jScrollPane3, gridBagConstraints);

		jLabel3.setText("Note fermentazione");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
		jPanel2.add(jLabel3, gridBagConstraints);

		jScrollPane4.setPreferredSize(new java.awt.Dimension(550, 120));
		jScrollPane4.setViewportView(fldNoteB);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		jPanel2.add(jScrollPane4, gridBagConstraints);

		jLabel4.setText("Data della cotta");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
		jPanel2.add(jLabel4, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
		jPanel2.add(fldDate, gridBagConstraints);

		jLabel5.setIcon(new ImageIcon(CopyOfNewCotta.class.getResource("/jmash/images/kettles.png"))); // NOI18N
		jLabel5.setMaximumSize(new java.awt.Dimension(140, 353));
		jLabel5.setMinimumSize(new java.awt.Dimension(140, 353));
		jLabel5.setPreferredSize(new java.awt.Dimension(140, 353));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 21;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		jPanel2.add(jLabel5, gridBagConstraints);

		tabPane.addTab("Dati generali", jPanel2);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 1.0;
		getContentPane().add(tabPane, gridBagConstraints);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		read();
	}// GEN-LAST:event_jButton2ActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		clear();
	}// GEN-LAST:event_jButton1ActionPerformed

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
		save();
	}// GEN-LAST:event_jButton3ActionPerformed

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
		saveAs();
	}// GEN-LAST:event_jButton4ActionPerformed

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton5ActionPerformed
		File file1 = Utils.pickFileToLoad(new JInternalFrame(),
				(String) Main.getFromCache("recipe.dir", Main.recipeDir));
		if (file1 != null) {
			ricettaOriginale.read(file1);
			if (Ask.doAsk(this, "Si vuole usare questa ricetta come base per la cotta reale?")) {
				ricettaReale.read(file1);
			}
		}
	}// GEN-LAST:event_jButton5ActionPerformed

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton6ActionPerformed
		GetRicettaFromHobbybirra frame = new GetRicettaFromHobbybirra();
		frame.startModal(this);
		Document doc = frame.getRicettaSelezionata();

		ricettaOriginale.read(doc);
		if (Ask.doAsk(this, "Si vuole usare questa ricetta come base per la cotta reale?")) {
			ricettaReale.read(doc);
		}
	}// GEN-LAST:event_jButton6ActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JTextField fldBrewer;
	private com.toedter.calendar.JDateChooser fldDate;
	private javax.swing.JEditorPane fldNoteA;
	private javax.swing.JEditorPane fldNoteB;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JToolBar jToolBar1;
	private javax.swing.JTabbedPane tabPane;
	// End of variables declaration//GEN-END:variables

	public Document toXml() {
		Element RO = ricettaOriginale.toRecipeData().getXmlRoot();
		Element RR = ricettaReale.toRecipeData().getXmlRoot();
		RO.setAttribute("tipo", "prevista");
		RR.setAttribute("tipo", "reale");
		Document doc = new Document();
		Element root = new Element("cotta");
		root.setAttribute("brewer", fldBrewer.getText());
		if (fldDate.getDate() != null)
			root.setAttribute("date", new SimpleDateFormat("dd/MM/yyyy").format(fldDate.getDate()));
		root.setAttribute("noteA", fldNoteA.getText());
		root.setAttribute("noteB", fldNoteB.getText());
		root.addContent(RO);
		root.addContent(RR);
		root.addContent(logF.toXml());
		root.addContent(mashLog.toXml());
		root.addContent(degu.toXml());
		root.addContent(waterNeeded.toXml());
		doc.setRootElement(root);
		return doc;
	}

	public void save() {
		if (this.file == null) {
			this.file = Utils.pickFileToSave(this, (String) Main.getFromCache("batch.dir", Main.batchDir));
		}
		if (this.file == null)
			return;
		Utils.saveXmlAsFile(toXml(), this.file, this);
		Main.putIntoCache("batch.dir", file.getAbsolutePath());
		setTitle(this.file.getName());
	}

	public void saveAs() {
		File file1 = Utils.pickFileToSave(this, (String) Main.getFromCache("batch.dir", Main.batchDir));
		if (file1 == null)
			return;
		Utils.saveXmlAsFile(toXml(), file1, this);
		this.file = file1;
		Main.putIntoCache("batch.dir", file.getAbsolutePath());
		setTitle(this.file.getName());
	}

	public void read() {
		File file1 = Utils.pickFileToLoad(new JInternalFrame(), (String) Main.getFromCache("batch.dir", Main.batchDir));

		if (file1 != null) {
			this.file = file1;
			setTitle(this.file.getName());
			Document doc = Utils.readFileAsXml(this.file.toString());
			Element root = doc.getRootElement();
			if (root.getName().compareToIgnoreCase("cotta") == 0) {
				fldBrewer.setText(root.getAttribute("brewer").getValue());
				if (root.getAttribute("date") != null) {
					try {
						fldDate.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(root.getAttribute("date").getValue()));
					} catch (ParseException ex) {
						LOGGER.error(ex.getMessage(), ex);
					}
				}
				fldNoteA.setText(root.getAttribute("noteA").getValue());
				fldNoteB.setText(root.getAttribute("noteB").getValue());
				Iterator iterator = root.getChildren().iterator();
				while (iterator.hasNext()) {
					Element elem = (Element) iterator.next();
					if (elem.getName().compareToIgnoreCase(XmlTags.RECIPE) == 0) {
						String tipo = elem.getAttribute("tipo").getValue();
						RecipeData data = new RecipeData();
						data.read(elem);
						if (tipo.equalsIgnoreCase("prevista"))
							ricettaOriginale.fromRecipeData(data);
						if (tipo.equalsIgnoreCase("reale"))
							ricettaReale.fromRecipeData(data);
					} else if (elem.getName().compareToIgnoreCase("fermentazione") == 0) {
						Iterator it = elem.getChildren().iterator();
						while (it.hasNext()) {
							Element L = (Element) it.next();
							SGLog e = SGLog.fromXml(L);
							logF.add(e);
						}
					} else if (elem.getName().compareToIgnoreCase("mashLog") == 0) {
						Iterator it = elem.getChildren().iterator();
						while (it.hasNext()) {
							Element L = (Element) it.next();
							TLog e = TLog.fromXml(L);
							mashLog.add(e);
						}
					} else if (elem.getName().compareToIgnoreCase("degu") == 0) {
						degu.fromXml(elem);
					} else if (elem.getName().compareToIgnoreCase("water") == 0) {
						waterNeeded.fromXml(elem);
					}
				}
				logF.logModificato();
				mashLog.logModificato();
				degu.calc();
				ricettaReale.ricettaModificata();
				ricettaReale.mashDesign.mashModificato();
				ricettaOriginale.ricettaModificata();
				ricettaOriginale.mashDesign.mashModificato();
			}
		}
	}

	public void clear() {

	}
}
