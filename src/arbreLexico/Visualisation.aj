package arbreLexico;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;

public privileged aspect Visualisation {

		declare parents : ArbreLexicographique implements TreeModel;
		private DefaultTreeModel ArbreLexicographique.treeModel;
		private JTree ArbreLexicographique.vue;

		declare parents : NoeudAbstrait implements TreeNode;
		private DefaultMutableTreeNode NoeudAbstrait.treeNode;
		

		/* 
		 * Implémentation de TreeModel
		 * */
		
		public void ArbreLexicographique.setVue(JTree jt){
			this.vue = jt;
		}
		
		public void ArbreLexicographique.addTreeModelListener(TreeModelListener l){
			this.treeModel.addTreeModelListener(l);
		}
		
		public Object ArbreLexicographique.getChild(Object parent, int index){
			return this.treeModel.getChild(parent, index);
		}
		public int ArbreLexicographique.getChildCount(Object parent){
			return this.treeModel.getChildCount(parent);
		}
		
		public int	ArbreLexicographique.getIndexOfChild(Object parent, Object child){
			return this.treeModel.getIndexOfChild(parent, child);
		}
		public Object ArbreLexicographique.getRoot(){
			return this.treeModel.getRoot();
		}
		
		public boolean	ArbreLexicographique.isLeaf(Object node){
			return this.treeModel.isLeaf(node);
		}
		public void ArbreLexicographique.removeTreeModelListener(TreeModelListener l){
			this.treeModel.removeTreeModelListener(l);
		}
		
		public void ArbreLexicographique.valueForPathChanged(TreePath path, Object newValue){
			this.treeModel.valueForPathChanged(path, newValue);
		}
		
		/* 
		 * Implémentation de TreeNode
		 * */
		public Enumeration<TreeNode> NoeudAbstrait.children(){
		return this.treeNode.children();
		}
		
		public boolean NoeudAbstrait.getAllowsChildren(){
		return this.treeNode.getAllowsChildren();
		}

		public TreeNode NoeudAbstrait.getChildAt(int childIndex){
		return this.treeNode.getChildAt(childIndex);
		}

		public int NoeudAbstrait.getChildCount(){
		return treeNode.getChildCount();
		}

		public int NoeudAbstrait.getIndex(TreeNode node){
		return treeNode.getIndex(node);
		}

		public TreeNode NoeudAbstrait.getParent(){
		return treeNode.getParent();
		}

		public boolean NoeudAbstrait.isLeaf(){
		return treeNode.isLeaf();
		}
		
		/* 
		 * Pointcuts 
		 * */
		
		
		pointcut initArbre(ArbreLexicographique a) : target(a) && execution(ArbreLexicographique.new());
		after (ArbreLexicographique a) : initArbre(a){
			a.treeModel = new DefaultTreeModel(new DefaultMutableTreeNode());
		}
		
		pointcut modifR(ArbreLexicographique a) : target(a) && set(NoeudAbstrait ArbreLexicographique.entree);
		
		pointcut modifAjout(ArbreLexicographique a) : target(a) && modifR(ArbreLexicographique) && withincode(boolean ArbreLexicographique.ajout(String));
		after(ArbreLexicographique a) : modifAjout(a){
			if(a.entree.treeNode != null){
			((DefaultMutableTreeNode) a.treeModel.getRoot()).insert(a.entree.treeNode, 0);
			((DefaultTreeModel)a.treeModel).reload(); 
			}
		}
		
		pointcut modifSuppr(ArbreLexicographique a) : target(a) && modifR(ArbreLexicographique) && withincode(boolean ArbreLexicographique.suppr(String));
		before(ArbreLexicographique a) : modifSuppr(a){
			((DefaultMutableTreeNode)a.getRoot()).remove(a.entree.treeNode);
		}
		after(ArbreLexicographique a) : modifSuppr(a){
			if(a.entree != new NoeudVide()){
				((DefaultMutableTreeNode) a.treeModel.getRoot()).insert(a.entree.treeNode, 0);
			}
			((DefaultTreeModel)a.treeModel).reload();
		}
		
		pointcut initNoeudAbstrait(NoeudAbstrait n) : target(n) && execution(NoeudAbstrait.new(NoeudAbstrait));
		after(NoeudAbstrait n) : initNoeudAbstrait(n){
			n.treeNode = new DefaultMutableTreeNode();
		}
		
		pointcut initNoeud(Noeud n, NoeudAbstrait frere, NoeudAbstrait fils, char val) : target(n) && args(frere, fils, val) && execution(Noeud.new(NoeudAbstrait, NoeudAbstrait, char));
		after(Noeud n, NoeudAbstrait frere, NoeudAbstrait fils, char val) : initNoeud(n, frere,  fils,  val){
			n.treeNode = new DefaultMutableTreeNode(Character.toString(val));
			n.treeNode.add(fils.treeNode);
		}
		
		pointcut modifFrere(NoeudAbstrait n) : target(n) && set(NoeudAbstrait NoeudAbstrait.frere);
	
		pointcut modifFils(Noeud n, NoeudAbstrait n1) : this(n) && target(n1) && set(NoeudAbstrait Noeud.fils);
			
		pointcut modifFilsajout(Noeud n) : target(n) && modifFils(Noeud, NoeudAbstrait) && withincode(NoeudAbstrait NoeudAbstrait.ajout(String));
		after(Noeud n) : modifFilsajout(n){
			((DefaultMutableTreeNode) n.treeNode).insert(n.fils.treeNode, 0);
		}
		
		pointcut modifFrereajout(NoeudAbstrait n, NoeudAbstrait nouveauFrere) :
		target(n) && set(NoeudAbstrait NoeudAbstrait.frere) && args(nouveauFrere) && withincode(NoeudAbstrait NoeudAbstrait+.ajout(String));
		after(NoeudAbstrait n, NoeudAbstrait nf) : modifFrereajout(n, nf){
			if(n.getParent() != null){
				((DefaultMutableTreeNode) n.treeNode.getParent()).insert(nf.treeNode,0);
			}
		}
			
		pointcut modifFilsSuppr(Noeud n) : target(n) &&  modifFils(Noeud, NoeudAbstrait) && withincode(NoeudAbstrait NoeudAbstrait+.suppr(String));
		before(Noeud n) : modifFilsSuppr(n){
			if(n.fils.treeNode.getParent() != null) n.treeNode.remove(n.fils.treeNode);
			}
			after(Noeud n) : modifFilsSuppr(n){
				if(n.fils != new NoeudVide()) n.treeNode.insert(n.fils.treeNode, 0);
			}
			
		pointcut modifFrereSuppr(NoeudAbstrait n) : target(n) && modifFrere(NoeudAbstrait) && withincode(NoeudAbstrait NoeudAbstrait+.suppr(String));
		before(NoeudAbstrait n) : modifFrereSuppr(n){
				((DefaultMutableTreeNode)n.treeNode.getParent()).remove(n.frere.treeNode);
			}
		after(NoeudAbstrait n) : modifFrereSuppr(n){
			if(n.frere != new NoeudVide())
				((DefaultMutableTreeNode) n.treeNode.getParent()).insert(n.frere.treeNode, 0);
			}

}

