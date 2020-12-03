package arbreLexico;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class Interface extends ArbreLexicographique {
	private JFrame frame;
	private JTextField textField;
	private ArbreLexicographique arbre;
	private JTree tree;
	JLabel lblStatut = new JLabel();
	JLabel lblNbrWords = new JLabel("Number of word in tree");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Interface window = new Interface();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	public Interface() {
		arbre = new ArbreLexicographique();
        initialize();
        //arbre.setVue(tree);
        //tree.setModel(arbre);
        lblStatut.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatut.setVerticalAlignment(SwingConstants.CENTER);
        Font font = new Font("Arial",Font.CENTER_BASELINE,20);
        lblStatut.setFont(font);
        frame.getContentPane().add(lblStatut, BorderLayout.SOUTH);
    }
	
	public void initialize() {
		
		TextArea textArea = new TextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		
		frame = new JFrame("Arbre Lexicographique");
		frame.setBounds(100, 100, 700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel BarMenu = new JPanel();
		frame.getContentPane().add(BarMenu, BorderLayout.NORTH);
		BarMenu.setLayout(new GridLayout(2, 0, 0, 0));
		
		
		JPanel Menu = new JPanel();
		BarMenu.add(Menu);
		Menu.setLayout(new GridLayout(1, 0, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		Menu.add(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenuItem mntmCharger = new JMenuItem("Charger");
		mntmCharger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Select the file to import");
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setAcceptAllFileFilterUsed(false);
				
				if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					textArea.setText(arbre.toString());
					lblNbrWords.setText("Number of words : " + arbre.nbMots());
					lblStatut.setText("the file has been loaded ");
				}
				for (int i = 0; i < tree.getRowCount(); i++) {
				    tree.expandRow(i);
				}
			}
			});
		
		mnFichier.add(mntmCharger);
		
		JMenuItem mntmSauvegarder = new JMenuItem("Enregistrer");
		mntmSauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Select a path to save the tree");
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					lblNbrWords.setText("Number of words : " + arbre.nbMots());
					lblStatut.setText("File saved ");
				}
			}
		});
		
		mnFichier.add(mntmSauvegarder);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFichier.add(mntmQuitter);
		
		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);
		
		JPanel Boutons = new JPanel();
		BarMenu.add(Boutons);
		Boutons.setLayout(new GridLayout(1, 6, 0, 0));
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arbre.contient(textField.getText())){
					JOptionPane.showMessageDialog(frame, "This word is already inside the tree","Error",JOptionPane.ERROR_MESSAGE);
				}
				if(textField.getText().isEmpty()){JOptionPane.showMessageDialog(frame,"Enter a word to add", "Error",JOptionPane.ERROR_MESSAGE);
				}
				else{
				arbre.ajout(textField.getText().toLowerCase());
				textField.setText("");
				textArea.setText(arbre.toString());
				lblNbrWords.setText("Number of words : " + arbre.nbMots());
				lblStatut.setText("the word has been added ");
				}
				for (int i = 0; i < tree.getRowCount(); i++) {
				    tree.expandRow(i);
				}
			}
		});
		Boutons.add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()){
					JOptionPane.showMessageDialog(frame,"Enter a word to delete","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(arbre.contient(textField.getText())){
					arbre.suppr(textField.getText());
					textField.setText("");
					textArea.setText(arbre.toString());
					lblNbrWords.setText("Number of words : " + arbre.nbMots());
					lblStatut.setText("the word has been cancelled ");
					}
					else{
						JOptionPane.showMessageDialog(frame,"the word is not in the tree","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				for (int i = 0; i < tree.getRowCount(); i++) {
				    tree.expandRow(i);
				}
			}
		});
		Boutons.add(btnSupprimer);
		
		JButton btnChercher = new JButton("Chercher");
		btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()){
					JOptionPane.showMessageDialog(frame,"Enter a word","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if (arbre.contient(textField.getText())){
					lblStatut.setText("The word " + textField.getText() + " is inside the tree ");
				} else {
					lblStatut.setText("The word " + textField.getText() + " is not inside the tree ");
				}
				
			}
		});
		Boutons.add(btnChercher);
		
		JButton btnPrefixe = new JButton("Prefixe");
		btnPrefixe.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(textField.getText().isEmpty()){
				JOptionPane.showMessageDialog(frame,"Enter a word","Error",JOptionPane.ERROR_MESSAGE);
			}
			else if (arbre.prefixe(textField.getText())){
				lblStatut.setText("The word "+textField.getText()+" is prefix ");
			} else {
				lblStatut.setText("The word "+textField.getText()+" is not prefix ");
			}
			textField.setText("");
			textArea.setText(arbre.toString());
		}
	});

		Boutons.add(btnPrefixe);
		
		JLabel lblAddWord = new JLabel("Renseigner un mot :");
		lblAddWord.setHorizontalAlignment(SwingConstants.CENTER);
		Boutons.add(lblAddWord);
		
		textField = new JTextField();
		Boutons.add(textField);
		textField.setColumns(10);
		
		JPanel MainWindows = new JPanel();
		frame.getContentPane().add(MainWindows, BorderLayout.CENTER);
		MainWindows.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.TOP);
		MainWindows.add(tabPanel, BorderLayout.CENTER);
		
		JScrollPane PanelArbre = new JScrollPane();
		tabPanel.addTab("Arbre", null, PanelArbre, null);
		
		tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("JTree") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("mots");
						node_1.add(new DefaultMutableTreeNode("debutant"));
						node_1.add(new DefaultMutableTreeNode("eclipse"));
						node_1.add(new DefaultMutableTreeNode("elipse"));
						node_1.add(new DefaultMutableTreeNode("sac"));
						node_1.add(new DefaultMutableTreeNode("sel"));
						node_1.add(new DefaultMutableTreeNode("sodium"));
					add(node_1);
				}
			}
		));
		PanelArbre.setColumnHeaderView(tree);
		
		JPanel PanelListe = new JPanel();
		tabPanel.addTab("List", null, PanelListe, null);
		textArea.setText(arbre.toString());
		PanelListe.add(textArea);
		
		
		MainWindows.add(lblNbrWords, BorderLayout.SOUTH);
	
	}

}


