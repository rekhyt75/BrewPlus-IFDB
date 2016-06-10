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
package jmash;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import jmash.tableModel.PickerTableModel;

/**
 *
 * @author Alessandro
 */
public class Picker extends ModalInternalFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 5761863200328720728L;
	private PickerTableModel pickerTableModel;
	public static javax.swing.ImageIcon okIcon = new javax.swing.ImageIcon(
			Msg.class.getResource("/jmash/images/button_ok.png"));
	public static javax.swing.ImageIcon koIcon = new javax.swing.ImageIcon(
			Msg.class.getResource("/jmash/images/button_cancel.png"));
	TableSorter sorter;

	/** Creates new form Picker */
	public Picker(PickerTableModel model) {
		this.setPickerTableModel(model);
		this.sorter = new TableSorter(this.getPickerTableModel());
		initComponents();
		this.sorter.setTableHeader(this.jTable1.getTableHeader());
		pnlFiltri.setVisible(false);
		// jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		jTable1.getColumnModel().getColumn(1).setCellRenderer(Main.multiLineCellRenderer);

	}

	private LinkedList<JRadioButton> radioButtons = new LinkedList<JRadioButton>();
	private ButtonGroup buttonGroup = new ButtonGroup();

	public void setFilters(String[] str, ActionListener listener) {
		pnlFiltri.removeAll();
		radioButtons.clear();
		for (int i = 0; i < str.length; i++) {
			JRadioButton radio = new JRadioButton(str[i]);
			pnlFiltri.add(radio);
			radioButtons.add(radio);
			radio.addActionListener(listener);
			if (i == 0) {
				radio.setSelected(true);
			}
			buttonGroup.add(radio);
		}
		pnlFiltri.setVisible(true);
	}

	Icon titleIcon = null;
	Object selection = null;

	Object getSelection() {
		return this.selection;
	}

	public void setIcon(Icon icon) {
		this.jLabel1.setIcon(icon);
	}

	public JTable getTable() {
		return jTable1;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane(this.jTable1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jTable1 = new javax.swing.JTable();
		jPanel3 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		btnCancel = new javax.swing.JButton();
		btnOk = new javax.swing.JButton();
		pnlFiltri = new javax.swing.JPanel();

		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				formKeyTyped(evt);
			}
		});

		jPanel1.add(jLabel1);

		getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

		jTable1.setModel(sorter);
		jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				jTable1KeyTyped(evt);
			}
		});
		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable1MouseClicked(evt);
			}
		});

		jScrollPane1.setViewportView(jTable1);

		getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

		jPanel3.setLayout(new java.awt.BorderLayout());

		btnCancel.setIcon(koIcon);
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelActionPerformed(evt);
			}
		});
		jPanel2.add(btnCancel);

		btnOk.setIcon(okIcon);
		btnOk.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnOkActionPerformed(evt);
			}
		});
		jPanel2.add(btnOk);

		jPanel3.add(jPanel2, java.awt.BorderLayout.SOUTH);
		jPanel3.add(pnlFiltri, java.awt.BorderLayout.NORTH);

		getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		// setBounds((screenSize.width-573)/2, (screenSize.height-300)/2, 573,
		// 300);
		setBounds((screenSize.width - 673) / 2, (screenSize.height - 300) / 2, 673, 300);
	}// </editor-fold>//GEN-END:initComponents

	private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTable1KeyTyped
		int c = this.jTable1.getSelectedColumn();
		if (c < 0) {
			c = 0;
		}
		int i = this.getPickerTableModel().keyTyped(evt.getKeyChar(), new Date(), c, this.sorter);
		if (i >= 0) {
			// jTable1.getSelectionModel().setSelectionInterval(i,i);
			// jTable1.setRowSelectionInterval(i,i);
			this.jTable1.changeSelection(i, c, false, false);
			this.jTable1.setRowSelectionInterval(i, i);
			Rectangle rect = this.jTable1.getCellRect(i, 0, true);
			this.jTable1.scrollRectToVisible(rect);
			this.jTable1.setRowSelectionInterval(i, i);
		}
	}// GEN-LAST:event_jTable1KeyTyped

	private void formKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_formKeyTyped
		jTable1KeyTyped(evt);
	}// GEN-LAST:event_formKeyTyped

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jTable1MouseClicked
		if (evt.getClickCount() == 2) {
			doSelection();
		}
	}// GEN-LAST:event_jTable1MouseClicked

	private void doSelection() {
		if (this.jTable1.getSelectedRow() >= 0) {
			this.selection = this.getPickerTableModel().getRows()
					.get(this.sorter.modelIndex(this.jTable1.getSelectedRow()));
			stopModal();
		}
	}

	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelActionPerformed
		this.selection = null;
		stopModal();
		setVisible(false);
	}// GEN-LAST:event_btnCancelActionPerformed

	private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnOkActionPerformed
		doSelection();
	}// GEN-LAST:event_btnOkActionPerformed

	@Override
	public void doDefaultCloseAction() {
		this.selection = null;
		stopModal();
		setVisible(false);
	}

	public PickerTableModel getPickerTableModel() {
		return pickerTableModel;
	}

	public JTable geTable() {
		return jTable1;
	}

	public void setPickerTableModel(PickerTableModel pickerTableModel) {
		this.pickerTableModel = pickerTableModel;
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCancel;
	public javax.swing.JButton btnOk;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JPanel pnlFiltri;
	// End of variables declaration//GEN-END:variables
}
