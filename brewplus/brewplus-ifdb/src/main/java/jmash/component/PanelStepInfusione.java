/*

 *
 *  This file is part of BrewPlus.
 *
 *  BrewPlus is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  BrewPlus is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with BrewPlus; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package jmash.component;

import org.jfree.data.xy.XYSeries;

/**
 *
 * @author Alessandro
 */
public class PanelStepInfusione extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3368567547772139356L;

	/** Creates new form PanelStepInfusione */
	public PanelStepInfusione() {
		initComponents();
		setBackground(getBackground().brighter());

		getSpinLen().setModelFormat(15, 1, 360 * 24, 1, "0.0", "PanelStepInfusione.Len");
		getSpinRampa().setModelFormat(2, 1, 360 * 24, 1, "0.0", "PanelStepInfusione.Rampa");
		getSpinStart().setModelFormat(0, 0, 360 * 24, 1, "0.0", "PanelStepInfusione.Start");
		getSpinTFin().setModelFormat(50, 1, 100, 1, "0.0", "PanelStepInfusione.Tfin");
		getSpinTIni().setModelFormat(50, 1, 100, 1, "0.0", "PanelStepInfusione.Tini");
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {// GEN-BEGIN:initComponents
		java.awt.GridBagConstraints gridBagConstraints;

		this.jLabel1 = new javax.swing.JLabel();
		this.jLabel2 = new javax.swing.JLabel();
		this.spinTIni = new jmash.component.JMashSpinner();
		this.spinTFin = new jmash.component.JMashSpinner();
		this.spinStart = new jmash.component.JMashSpinner();
		this.jLabel3 = new javax.swing.JLabel();
		this.jLabel4 = new javax.swing.JLabel();
		this.spinLen = new jmash.component.JMashSpinner();
		this.jLabel5 = new javax.swing.JLabel();
		this.spinRampa = new jmash.component.JMashSpinner();

		setLayout(new java.awt.GridBagLayout());

		setBorder(javax.swing.BorderFactory.createTitledBorder("Infusione"));
		this.jLabel1.setText("T. iniz.");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
		add(this.jLabel1, gridBagConstraints);

		this.jLabel2.setText("T. fin.");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
		add(this.jLabel2, gridBagConstraints);

		this.spinTIni.setPreferredSize(new java.awt.Dimension(40, 20));
		this.spinTIni.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinTIniStateChanged(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		add(this.spinTIni, gridBagConstraints);

		this.spinTFin.setPreferredSize(new java.awt.Dimension(40, 20));
		this.spinTFin.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinTFinStateChanged(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		add(this.spinTFin, gridBagConstraints);

		this.spinStart.setPreferredSize(new java.awt.Dimension(40, 20));
		this.spinStart.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinStartStateChanged(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		add(this.spinStart, gridBagConstraints);

		this.jLabel3.setText("Durata");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
		add(this.jLabel3, gridBagConstraints);

		this.jLabel4.setText("Inizio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
		add(this.jLabel4, gridBagConstraints);

		this.spinLen.setPreferredSize(new java.awt.Dimension(40, 20));
		this.spinLen.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinLenStateChanged(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 1;
		add(this.spinLen, gridBagConstraints);

		this.jLabel5.setText("Rampa");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
		add(this.jLabel5, gridBagConstraints);

		this.spinRampa.setPreferredSize(new java.awt.Dimension(40, 20));
		this.spinRampa.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinRampaStateChanged(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 5;
		gridBagConstraints.gridy = 1;
		add(this.spinRampa, gridBagConstraints);

	}// GEN-END:initComponents

	private void spinRampaStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinRampaStateChanged
		// parent.mashModificato();
	}// GEN-LAST:event_spinRampaStateChanged

	private void spinLenStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinLenStateChanged
		// parent.mashModificato();
	}// GEN-LAST:event_spinLenStateChanged

	private void spinStartStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinStartStateChanged
		// parent.mashModificato();
	}// GEN-LAST:event_spinStartStateChanged

	private void spinTFinStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinTFinStateChanged
		// parent.mashModificato();
	}// GEN-LAST:event_spinTFinStateChanged

	private void spinTIniStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinTIniStateChanged
		// parent.mashModificato();
	}// GEN-LAST:event_spinTIniStateChanged

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private jmash.component.JMashSpinner spinLen;
	private jmash.component.JMashSpinner spinRampa;
	private jmash.component.JMashSpinner spinStart;
	private jmash.component.JMashSpinner spinTFin;
	private jmash.component.JMashSpinner spinTIni;
	// End of variables declaration//GEN-END:variables

	// private Integer tIni;
	// private Integer tFin;
	// private Integer ramp;
	// private Integer start;
	// private Integer len;
	private XYSeries serie;

	public Integer getTIni() {
		return getSpinTIni().getIntegerValue();
	}

	public void setTIni(Integer tIni) {
		// this.tIni = tIni;
		getSpinTIni().setValue(tIni);
	}

	public Integer getTFin() {
		return getSpinTFin().getIntegerValue();
	}

	public void setTFin(Integer tFin) {
		// this.tFin = tFin;
		getSpinTFin().setValue(tFin);
	}

	public Integer getRamp() {
		return getSpinRampa().getIntegerValue();
	}

	public void setRamp(Integer ramp) {
		// this.ramp = ramp;
		getSpinRampa().setValue(ramp);
	}

	public Integer getStart() {
		return getSpinStart().getIntegerValue();
	}

	public void setStart(Integer start) {
		// this.start = start;
		getSpinStart().setValue(start);
	}

	public Integer getLen() {
		return getSpinLen().getIntegerValue();
	}

	public void setLen(Integer len) {
		// this.len = len;
		getSpinLen().setValue(len);
	}

	public jmash.component.JMashSpinner getSpinLen() {
		return this.spinLen;
	}

	public void setSpinLen(jmash.component.JMashSpinner spinLen) {
		this.spinLen = spinLen;
	}

	public jmash.component.JMashSpinner getSpinRampa() {
		return this.spinRampa;
	}

	public void setSpinRampa(jmash.component.JMashSpinner spinRampa) {
		this.spinRampa = spinRampa;
	}

	public jmash.component.JMashSpinner getSpinStart() {
		return this.spinStart;
	}

	public void setSpinStart(jmash.component.JMashSpinner spinStart) {
		this.spinStart = spinStart;
	}

	public jmash.component.JMashSpinner getSpinTFin() {
		return this.spinTFin;
	}

	public void setSpinTFin(jmash.component.JMashSpinner spinTFin) {
		this.spinTFin = spinTFin;
	}

	public jmash.component.JMashSpinner getSpinTIni() {
		return this.spinTIni;
	}

	public void setSpinTIni(jmash.component.JMashSpinner spinTIni) {
		this.spinTIni = spinTIni;
	}

	public XYSeries getSerie() {
		if (this.serie == null) {
			this.serie = new XYSeries("");

		}
		this.serie.clear();
		this.serie.add(getStart(), getTIni());
		this.serie.add(getStart() + getRamp(), getTFin());
		this.serie.add(getStart() + getLen() + getRamp(), getTFin());

		return this.serie;
	}

	public void setSerie(XYSeries serie) {
		this.serie = serie;
	}
}
