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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import org.jdom.DataConversionException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class BrowseHobbyBirra2 extends javax.swing.JInternalFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 6795432869963837389L;
    private List<StyleTreeNode> styleNodes = new ArrayList<StyleTreeNode>();
    GridLayout gl = new GridLayout();
    public Document ricettaSelezionata = null;
    private int mode = -1;
    private ModalInternalFrame parent = null;
    private static final int COLUMNS = 3;    
    /** Creates new form BrowseHobbyBirra */
    public BrowseHobbyBirra2(ModalInternalFrame frame) {
	this();
	this.mode = 1;
	parent = frame;
    }
    
    public BrowseHobbyBirra2() {
	initComponents();
	setBorder(Utils.getDefaultBorder());
	Document doc = Utils.readFileAsXml("http://" + Main.config.getRemoteServer() + "/ricette_stile_xml.asp");
	if (doc == null) {
	    return;
	}
	
	
	Element root = doc.getRootElement();
	@SuppressWarnings("unchecked")
	Iterator it = root.getChildren().iterator();
	while (it.hasNext()) {
	    Element el = (Element) it.next();
	    StyleTreeNode styleNode = new StyleTreeNode(el.getAttribute("nome").getValue(), "-1");
	    @SuppressWarnings("unchecked")
	    Iterator it2 = el.getChildren().iterator();
	    List<SubStyleTreeNode> subNodes = new ArrayList<SubStyleTreeNode>();
	    int count = 0;
	    while (it2.hasNext()) {
		Element el2 = (Element) it2.next();
		try {
		    count += el2.getAttribute("count").getIntValue();
		} catch ( DataConversionException ex) {
		    ex.printStackTrace();
		}
		SubStyleTreeNode subNode = new SubStyleTreeNode(el2.getAttribute("nome").getValue() + " (" + el2.getAttribute("count").getValue() + ")",
			el2.getAttribute("id_stile").getValue(), styleNode);
		
		subNodes.add(subNode);
	    }
	    styleNode.setNodes(subNodes);
	    styleNode.setDes(styleNode.getDes() + " (" + count + ")");
	    this.styleNodes.add(styleNode);
	}
	
	gl.setColumns(COLUMNS);
	gl.setRows(styleNodes.size()/COLUMNS);
	gl.setVgap(2);
	this.panelConfirmed.setLayout(gl);
	
	FlowLayout fl = new FlowLayout();
	fl.setAlignment(FlowLayout.LEFT);
	this.panelUnconfirmed.setLayout(fl);
	Dimension dim = new Dimension(720, 1000);
	this.panelUnconfirmed.setMaximumSize(dim);
	this.panelUnconfirmed.setSize(dim);
	this.panelUnconfirmed.setPreferredSize(dim);
	for ( StyleTreeNode node : this.styleNodes) {
	    this.panelConfirmed.add(node);
	}
	gl.setColumns(COLUMNS);
	gl.setRows(styleNodes.size()/COLUMNS+styleNodes.size()%COLUMNS);
	this.jScrollPane1.setViewportView(this.panelConfirmed);
	
	doc = Utils.readFileAsXml("http://" + Main.config.getRemoteServer() + "/ricette_unconfirmed_xml.asp");
	if (doc == null) {
	    return;
	}
	
	List<RecipeTreeNode> unconfirmedRecipes = new ArrayList<RecipeTreeNode>();
	root = doc.getRootElement();
	it = root.getChildren().iterator();
	while (it.hasNext()) {
	    Element el = (Element) it.next();
	    
	    RecipeTreeNode newNode = new RecipeTreeNode(
		    "(" + el.getAttribute("id_ricetta_HB").getValue() + ") - " + el.getAttribute("nome").getValue(),
		    el.getAttribute("id_ricetta_HB").getValue());
	    unconfirmedRecipes.add(newNode);
	}
	
	this.jScrollPane2.setViewportView(this.panelUnconfirmed);
	for ( RecipeTreeNode node : unconfirmedRecipes) {
	    this.panelUnconfirmed.add(node);
	}
	
    }
    
    /**
     * This method is called from within the constructor to
     *
     * initialize the form.
     *
     * WARNING: Do NOT modify this code. The content of this method is
     *
     * always regenerated by the Form Editor.
     *
     */
    private void initComponents() {// GEN-BEGIN:initComponents
	this.jTabbedPane1 = new javax.swing.JTabbedPane();
	this.jScrollPane1 = new javax.swing.JScrollPane();
	this.jScrollPane2 = new javax.swing.JScrollPane();
	this.panelConfirmed = new javax.swing.JPanel();
	this.panelUnconfirmed = new javax.swing.JPanel();
	setClosable(true);
	setIconifiable(true);
	setMaximizable(true);
	setResizable(true);
	
	this.panelConfirmed.setBackground(Color.white);
	this.panelUnconfirmed.setBackground(Color.white);
	
	this.jTabbedPane1.setMaximumSize(new java.awt.Dimension(100, 100));
	this.jTabbedPane1.setMinimumSize(new java.awt.Dimension(100, 100));
	
	this.jTabbedPane1.addTab("Ricette approvate", this.jScrollPane1);
	this.jTabbedPane1.addTab("Ricette In attesa di approvazione", this.jScrollPane2);
	
	getContentPane().add(this.jTabbedPane1, java.awt.BorderLayout.CENTER);
	
	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	setBounds((screenSize.width - 800) / 2, (screenSize.height - 500) / 2,
		800, 500);
    }// GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelConfirmed;
    private javax.swing.JPanel panelUnconfirmed;

    // End of variables declaration//GEN-END:variables
    public class StyleTreeNode extends JButton {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 9142284801436841375L;
	private String idStile;
	private String des;
	private List<SubStyleTreeNode> nodes;
	private JButton jb = new JButton("Torna a Tutti gli stili", new ImageIcon(BrowseHobbyBirra2.class.getResource("/jmash/images/up.png")));
	
	public StyleTreeNode(String des, String idStile) {
	    super(des, folderIcon);
	    this.idStile = idStile;
	    this.des = des;
	    this.setBorderPainted(false);
	    this.setBackground(panelConfirmed.getBackground());
	    setAlignmentX(Component.LEFT_ALIGNMENT);
	    setMaximumSize(new Dimension(240, 32));
	    setSize(new Dimension(240, 32));
	    setPreferredSize(new Dimension(240, 32));
	    setHorizontalAlignment(SwingConstants.LEFT);
	    this.jb.setBorderPainted(false);
	    this.jb.setAlignmentX(Component.LEFT_ALIGNMENT);
	    this.jb.setHorizontalAlignment(SwingConstants.LEFT);
	    this.jb.setBackground(panelConfirmed.getBackground());
	    this.jb.setMaximumSize(new Dimension(240, 32));
	    this.jb.setSize(new Dimension(240, 32));
	    this.jb.setPreferredSize(new Dimension(240, 32));
	    this.jb.addActionListener(new ActionListener() {
		
                @Override
		public void actionPerformed(ActionEvent e) {
		    BrowseHobbyBirra2.this.panelConfirmed.removeAll();
		    for (StyleTreeNode node : BrowseHobbyBirra2.this.styleNodes) {
			BrowseHobbyBirra2.this.panelConfirmed.add(node);
		    }
		    gl.setRows(styleNodes.size());
		    gl.setColumns(COLUMNS);
		    gl.setRows(styleNodes.size()/COLUMNS+styleNodes.size()%COLUMNS);
		    BrowseHobbyBirra2.this.panelConfirmed.updateUI();
		}
	    });
	    
	    
	    addActionListener(new ActionListener() {
		
                @Override
		public void actionPerformed(ActionEvent e) {
		    show();
		}
	    });
	}
	
	public String getIdStile() {
	    return this.idStile;
	}
	
	public List<SubStyleTreeNode> getNodes() {
	    return this.nodes;
	}
	
	public void setNodes(List<SubStyleTreeNode> nodes) {
	    this.nodes = nodes;
	}
	
	@Override
	public void show() {
	    BrowseHobbyBirra2.this.panelConfirmed.removeAll();
	    BrowseHobbyBirra2.this.panelConfirmed.add(this.jb);
	    for (SubStyleTreeNode node : this.nodes) {
		BrowseHobbyBirra2.this.panelConfirmed.add(node);
	    }
	    gl.setColumns(1);
	    gl.setRows(nodes.size() + 1);
	    BrowseHobbyBirra2.this.panelConfirmed.updateUI();
	}
	
	public String getDes() {
	    return this.des;
	}
	
	public void setDes(String des) {
	    this.des = des;
	    setText(des);
	}
    }
    
    public class SubStyleTreeNode extends JButton {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 2703624374017241784L;
	private String idStile;
	private StyleTreeNode superStyle;
	private List<RecipeTreeNode> nodes = new ArrayList<RecipeTreeNode>();
	private JButton jb = new JButton("Up", new ImageIcon(BrowseHobbyBirra2.class.getResource("/jmash/images/up.png")));
	
	public SubStyleTreeNode(String des, String idStile, StyleTreeNode _superStyle) {
	    super(des, folderIcon);
	    this.idStile = idStile;
	    this.superStyle = _superStyle;
	    this.jb.setText("Torna a " + _superStyle.getDes());
	    this.jb.setBorderPainted(false);
	    this.jb.setBackground(panelConfirmed.getBackground());
	    setHorizontalAlignment(SwingConstants.LEFT);
	    setAlignmentX(Component.LEFT_ALIGNMENT);
	    this.setBorderPainted(false);
	    this.setBackground(panelConfirmed.getBackground());
	    setMaximumSize(new Dimension(240, 32));
	    setSize(new Dimension(240, 32));
	    setPreferredSize(new Dimension(240, 32));
	    this.jb.setAlignmentX(Component.LEFT_ALIGNMENT);
	    this.jb.setHorizontalAlignment(SwingConstants.LEFT);
	    this.jb.setMaximumSize(new Dimension(320, 32));
	    this.jb.setSize(new Dimension(320, 32));
	    this.jb.setPreferredSize(new Dimension(320, 32));
	    this.jb.addActionListener(new ActionListener() {
		
                @Override
		public void actionPerformed(ActionEvent e) {
		    SubStyleTreeNode.this.superStyle.show();
		}
	    });
	    addActionListener(new ActionListener() {
		
                @Override
		public void actionPerformed(ActionEvent e) {
		    
		    if (SubStyleTreeNode.this.nodes.isEmpty()) {
			Document doc = Utils.readFileAsXml("http://" + Main.config.getRemoteServer() + "/ricette_xml.asp?id_stile=" + getIdStile());
			if (doc == null) {
			    return;
			}
			
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			Iterator it = root.getChildren().iterator();
			while (it.hasNext()) {
			    Element el = (Element) it.next();
			    String concorso = "";
			    if (el.getAttribute("finalista") != null) {
				concorso = " - finalista a '" + el.getAttribute("concorso").getValue() + "'";
			    }
			    RecipeTreeNode newNode = new RecipeTreeNode(
				    "(" + el.getAttribute("id_ricetta_HB").getValue() + ") - " + el.getAttribute("nome").getValue() + concorso,
				    el.getAttribute("id_ricetta_HB").getValue());
			    SubStyleTreeNode.this.nodes.add(newNode);
			}
		    }
		    
		    BrowseHobbyBirra2.this.panelConfirmed.removeAll();
		    BrowseHobbyBirra2.this.panelConfirmed.add(SubStyleTreeNode.this.jb);
		    for ( RecipeTreeNode node : SubStyleTreeNode.this.nodes) {
			BrowseHobbyBirra2.this.panelConfirmed.add(node);
		    }
		    gl.setColumns(1);
		    gl.setRows(nodes.size() + 1);
		    BrowseHobbyBirra2.this.panelConfirmed.updateUI();
		}
	    });
	}
	
	public String getIdStile() {
	    return this.idStile;
	}
	
	public List<RecipeTreeNode> getNodes() {
	    return this.nodes;
	}
	
	public void setNodes(List<RecipeTreeNode> nodes) {
	    this.nodes = nodes;
	}
    }
    private static final ImageIcon folderIcon = new ImageIcon(BrowseHobbyBirra2.class.getResource("/jmash/images/folder.png"));
    private static final ImageIcon recipeIcon = new ImageIcon(BrowseHobbyBirra2.class.getResource("/jmash/images/ingredients.jpg"));
    
    public class RecipeTreeNode extends JButton {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -5731799309594130608L;
	private String id;
	
	public RecipeTreeNode(String des, String id) {
	    super(des, recipeIcon);
	    this.id = id;
	    
	    setHorizontalAlignment(SwingConstants.LEFT);
	    this.setBorderPainted(false);
	    this.setBackground(panelConfirmed.getBackground());
	    setMaximumSize(new Dimension(320, 32));
	    setSize(new Dimension(320, 32));
	    setPreferredSize(new Dimension(320, 32));
	    
	    addActionListener(new ActionListener() {
		
                @Override
		public void actionPerformed(ActionEvent e) {
		    Document doc = Utils.readFileAsXml("http://" + Main.config.getRemoteServer() + "/view_ricetta_xml.asp?id_ricetta_HB=" + getId());
		    if (doc == null) {
			return;
		    }
		    XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		    String xml = outputter.outputString(doc);
		    System.out.println(xml);
		    if (mode == -1) {
			Main.gui.nuovaRicetta(new Ricetta(doc, getId()));
		    } else {
			ricettaSelezionata = doc;
			BrowseHobbyBirra2.this.parent.stopModal();
		    }
		}
	    });
	}
	
	public String getId() {
	    return this.id;
	}
    }
}