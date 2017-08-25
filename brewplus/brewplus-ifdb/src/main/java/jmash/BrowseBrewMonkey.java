/*
 * BrowseBrewMonkey.java
 *
 * Created on 30 ottobre 2008, 19.04
 */

package jmash;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import jmash.utils.BeerXMLReader;

/**
 *
 * @author Alessandro
 */
public class BrowseBrewMonkey extends javax.swing.JInternalFrame {

	private List<StyleTreeNode> styleNodes = new ArrayList<StyleTreeNode>();
	private String html = "";
	GridLayout gl = new GridLayout();
	private static final int COLUMNS = 3;

	public static void main(String[] av) {
		new BrowseBrewMonkey();
	}

	public BrowseBrewMonkey() {
		initComponents();
		setBorder(Utils.getDefaultBorder());
		pnl.setBackground(Color.white);
		try {
			lblTitle.setIcon(new ImageIcon(new URL("http://www.brew-monkey.com/images/b-m-typist500.gif")));
			html = Utils.download("http://www.brew-monkey.com/recipes/recipes.php").toLowerCase();
		} catch (Exception ex) {
			Utils.showException(ex, "", this);
		}
		int i = html.indexOf("href=\"recipesbystyle.php?id=".toLowerCase());
		html = html.replaceAll("\n", "");
		int j = html.indexOf("href=\"recipesbystyle.php?id=", i);
		i = j;
		int k = html.indexOf("</a><br>", i);
		while (j >= 0 && k >= 0) {
			j = html.indexOf("<a href=\"recipesbystyle.php?id=", i);
			if (j >= 0) {
				String id = html.substring(html.indexOf("id=", j) + 3, html.indexOf("\">", j));
				j = html.indexOf(">", j) + ">".length();
				k = html.indexOf("</a><br>", j);
				String S = html.substring(j, k);

				// System.out.println("stile: "+S+", id = "+ id);

				styleNodes.add(new StyleTreeNode(id, S));
			}

			i = k;

		}

		gl.setColumns(COLUMNS);
		gl.setRows(styleNodes.size() / COLUMNS + styleNodes.size() % COLUMNS);
		gl.setVgap(2);
		this.pnl.setLayout(gl);
		for (StyleTreeNode node : styleNodes) {
			this.pnl.add(node);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		pnl = new javax.swing.JPanel();
		lblTitle = new javax.swing.JLabel();

		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setTitle("BrewMonkey.com");
		setFont(getFont());

		org.jdesktop.layout.GroupLayout pnlLayout = new org.jdesktop.layout.GroupLayout(pnl);
		pnl.setLayout(pnlLayout);
		pnlLayout.setHorizontalGroup(
				pnlLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(0, 593, Short.MAX_VALUE));
		pnlLayout.setVerticalGroup(
				pnlLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(0, 375, Short.MAX_VALUE));

		jScrollPane1.setViewportView(pnl);

		getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
		getContentPane().add(lblTitle, java.awt.BorderLayout.NORTH);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblTitle;
	private javax.swing.JPanel pnl;

	// End of variables declaration//GEN-END:variables
	public class StyleTreeNode extends JButton {
		/**
		 *
		 */
		private static final long serialVersionUID = 9142284801436841375L;
		private String idStile;
		private String stile;
		private String des;
		private JButton jb = new JButton("Torna a Tutti gli stili",
				new ImageIcon(BrowseHobbyBirra2.class.getResource("/jmash/images/up.png")));

		public StyleTreeNode(String stile, String des) {

			super(des, new ImageIcon(BrowseHobbyBirra2.class.getResource("/jmash/images/folder.png")));
			this.stile = stile;
			idStile = "" + stile;
			this.des = des;
			setAlignmentX(Component.LEFT_ALIGNMENT);
			setMaximumSize(BTN_DIM);
			setSize(BTN_DIM);
			setPreferredSize(BTN_DIM);
			setHorizontalAlignment(SwingConstants.LEFT);
			this.jb.setMaximumSize(BTN_DIM);
			this.jb.setSize(BTN_DIM);
			this.jb.setPreferredSize(BTN_DIM);
			this.setBorderPainted(false);
			this.setBackground(pnl.getBackground());
			this.jb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					pnl.removeAll();
					gl.setColumns(COLUMNS);
					gl.setRows(styleNodes.size() / COLUMNS + styleNodes.size() % COLUMNS);

					gl.setVgap(2);
					for (StyleTreeNode node : styleNodes) {
						pnl.add(node);
					}
					pnl.updateUI();
				}
			});

			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					Thread th = new Thread() {
						@Override
						public void run() {
							setEnabled(false);
							show();
							setEnabled(true);
						}
					};
					th.start();
				}
			});
		}

		public String getIdStile() {
			return this.idStile;
		}

		@Override
		public void show() {
			pnl.removeAll();
			Thread th = new Thread() {
				@Override
				public void run() {
					readRecipesForStyle(stile, jb);
				}
			};
			th.start();
			// pnl.add(jb,newConstraint(0));
			pnl.updateUI();
			updateUI();
		}

		public String getDes() {
			return this.des;
		}

		public void setDes(String des) {
			this.des = des;
			setText(des);
		}
	}

	private Dimension BTN_DIM = new Dimension(240, 32);

	public GridBagConstraints newConstraint(int i) {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new java.awt.Insets(2, 2, 2, 2);
		c.gridx = i % 4;
		c.gridy = i / 4;
		return c;
	}

	public void readRecipesForStyle(String style, JButton jb) {
		try {
			List<RecipeTreeNode> nodes = new ArrayList<RecipeTreeNode>();
			String url = "http://www.brew-monkey.com/recipes/recipesbystyle.php?id=" + style;

			html = Utils.download(url).toLowerCase();
			int i = html.indexOf("<!-- BEGIN MAIN CONTENT TABLE -->".toLowerCase());

			while (i >= 0) {
				String R = html.substring(i, html.indexOf("</table>", i));
				// System.out.println(R);
				String NM = R.substring(R.indexOf("target=\"_blank\">") + "target=\"_blank\">".length(),
						R.indexOf("</a></td>", R.indexOf("target=\"_blank\">")));
				String h = R.substring(R.indexOf("http://www.brew-monkey.com/recipes/beerxml/"),
						R.indexOf(".xml\">") + 4);
				RecipeTreeNode N = new RecipeTreeNode(new Integer(i), NM, h);
				nodes.add(N);
				i = html.indexOf("<!-- BEGIN MAIN CONTENT TABLE -->".toLowerCase(), i + 1);
			}

			gl.setColumns(1);
			gl.setRows(nodes.size() + 1);
			gl.setVgap(2);
			this.pnl.add(jb);
			for (RecipeTreeNode node : nodes) {
				this.pnl.add(node);
			}
			pnl.updateUI();
			updateUI();

		} catch (Exception ex) {
			Utils.showException(ex, "", this);
		}
	}

	public class RecipeTreeNode extends JButton implements Comparable<RecipeTreeNode> {
		private int id;
		private String des;
		private String file;

		public RecipeTreeNode(int id, String des, String file2) {

			super(des, new ImageIcon(BrowseHobbyBirra2.class.getResource("/jmash/images/ingredients.jpg")));
			this.id = id;
			this.file = file2;
			this.des = des;
			setAlignmentX(Component.LEFT_ALIGNMENT);
			setMaximumSize(BTN_DIM);
			setSize(BTN_DIM);
			setPreferredSize(BTN_DIM);
			setHorizontalAlignment(SwingConstants.LEFT);

			this.id = id;

			setHorizontalAlignment(SwingConstants.LEFT);
			this.setBorderPainted(false);
			this.setBackground(pnl.getBackground());
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					BeerXMLReader.parseBeerXMLRecipes(Utils.readFileAsXml(file).getRootElement());
				}
			});
		}

		@Override
		public int compareTo(RecipeTreeNode e) {
			return des.compareTo(e.getDes());
		}

		public String getDes() {
			return this.des;
		}

		public void setDes(String des) {
			this.des = des;
			setText(des);
		}
	}

}
