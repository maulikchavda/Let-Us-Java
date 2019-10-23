import java.util.Scanner;

/*class Node{
	Node left;
	Node right;
	int value;
	public Node() {
		left=right=null;
	}
	public Node(int x) {
		left=right=null;
		value=x;
	}
}*/

class AVL{
public  Node root=null;

int max(int a,int b) {
	return a>b?a:b; 
}//end of max

public int Height(Node Root) {
	if(Root==null)
			return -1;
	else if(Root.right==null&&Root.left==null)
			return 0;
	else 
		return 1+max(Height(Root.left),Height(Root.right));
}//end of height

int BalanceFactor(Node ROOT) {
	return Height(ROOT.left)-Height(ROOT.right);
}//end of balance factor

Node LeftRotation(Node ROOT) {
	Node temp=ROOT.right;
	ROOT.right=temp.left;
	temp.left=ROOT;
	return temp;
}//end of Left Rotation

Node RightRotation(Node ROOT) {
	Node temp=ROOT.left;
	ROOT.left=temp.right;
	temp.right=ROOT;
	return temp;
}//end of right rotation

public void insert(int val) {
	root=insert(root,val);
}//end of insert

public  Node insert(Node Root,int val) {
	Node newnode=new Node(val);
	if(Root==null) {
		Root=newnode;
	}
	else if(val<Root.value) {
			Root.left=insert(Root.left,val);
			if(BalanceFactor(Root)==2) {
				if(val>Root.left.value) {
					Root.left=LeftRotation(Root.left);
				}
				Root=RightRotation(Root);
			}
		}
		else {
			Root.right=insert(Root.right,val);
			if(BalanceFactor(Root)==-2) {
				if(val<Root.right.value) {
					Root.right=RightRotation(Root.right);
				}
				Root=LeftRotation(Root);
			}
			}
	return Root;
	}//end of insert function

public void delete(int val) {
	root=delete(root,val);
}

int TotalNodes(Node ROOT) {
	if(ROOT==null) {
		return 0;
	}
	else {
		return 1+TotalNodes(ROOT.left)+TotalNodes(ROOT.right);
	}
}

public Node delete(Node Root,int val) {
	int i=0;
	Scanner sc=new Scanner(System.in);
	if(Root==null) {
		System.out.println("node of value "+val+" is not present");
		return Root;
	}
	else if(val>Root.value) {
		Root.right=delete(Root.right,val);
		if(BalanceFactor(Root)==2)
	       {
	         if(BalanceFactor(Root.left)==-1)
	         {
	           Root.left=LeftRotation(Root.left);
	         }
	         Root=RightRotation(Root);
	       }
	}
	else if(val<Root.value) {
		Root.left=delete(Root.left,val);
		 if(BalanceFactor(Root)==-2)
	       {
	         if(BalanceFactor(Root.right)==1)
	         {
	           Root.right=RightRotation(Root.right);
	         }
	         Root=LeftRotation(Root);
	       }
		
	}
	else {	//Root is the node to be deleted										
		if(Root.left==null&&Root.right==null) {
			Root=null;
		}
		else if(Root.right==null) {//only left child is present
			Root=Root.left;	
		}
		else if(Root.left==null) {//only right child is present
			Root=Root.right;
		}
		else {//both child are present than we will find maxleft and minright
			System.out.println("which value u want to put at root "+maxleft(Root).value+" or "+minright(Root).value);
			i=sc.nextInt();
			if(i==minright(Root).value) {
				Root.value=i;
				Root.right=delete(Root.right,i);
				if(BalanceFactor(Root)==2)
			       {
			         if(BalanceFactor(Root.left)==-1)
			         {
			           Root.left=LeftRotation(Root.left);
			         }
			         Root=RightRotation(Root);
			       }
			}
			else {
				Root.value=i;
				Root.left=delete(Root.left,i);
				if(BalanceFactor(Root)==-2)
			       {
			         if(BalanceFactor(Root.right)==1)
			         {
			           Root.right=RightRotation(Root.right);
			         }
			         Root=LeftRotation(Root);
			       }
			}
		}
	}
	return Root;
}//end of delete function

int TotalNodes(Node ROOT) {
	if(ROOT==null) {
		return 0;
	}
	else {
		return 1+TotalNodes(ROOT.left)+TotalNodes(ROOT.right);
	}
}

public static Node maxleft(Node ROOT) {
	Node temp=ROOT.left;
	while(temp.right!=null) {
		temp=temp.right;
	}//end of while
	return temp;//reference of maxleft
}//end of maxleft

public static Node minright(Node ROOT) {
	Node temp=ROOT.right;
	while(temp.left!=null) {
		temp=temp.left;
	}//end of while
	return temp;//reference of minright
}//end of minright

public void InOrder() {
	InOrder(root);
}

public void InOrder(Node Root){
		if(Root==null) {return;}
		else {
			InOrder(Root.left);
			System.out.println(Root.value);
			InOrder(Root.right);
	}
}//end of InOrder function

public void PreOrder() {
	PreOrder(root);
}

public void PreOrder(Node Root){
		if(Root==null) {return;}
		else {
			System.out.println(Root.value);
			PreOrder(Root.left);
			PreOrder(Root.right);
	}
}//end of PreOrder function

public void PostOrder() {
	PostOrder(root);
}

public void PostOrder(Node Root){
		if(Root==null) {return;}
		else {
			PostOrder(Root.left);
			PostOrder(Root.right);
			System.out.println(Root.value);
	}
}//end of InOrder function



void LevelElements(Node ROOT,int level) {
	if(level==0) {
		if(ROOT==null)
			return;
		else
			System.out.print(ROOT.value+"     ");
	}
	else {
	if(ROOT.left!=null)
		{LevelElements(ROOT.left,level-1);}
	if(ROOT.right!=null)
		{LevelElements(ROOT.right,level-1);}
	}
}//end of level element

void LevelOrder() {
	for(int i=0;i<=Height(root);i++) {
		LevelElements(root,i);
		System.out.println("");
	}
}//end of level order

void Height() {
	System.out.println("height of the tree is "+Height(root));
}

}//end of Avl tree

public class AvlTree {

	public static void main(String[] args) {
		AVL Tree=new AVL();
		int i;
		Scanner sc=new Scanner(System.in);
		do {
		System.out.println("-------------------------------------");
		System.out.println("enter 1 for insert a node");
		System.out.println("enter 2 for delete a node");
		System.out.println("enter 3 for display the tree");
		System.out.println("enter 4 to determine the height of tree");
		System.out.println("enter 5 to determine the number of nodes in tree");
		System.out.println("enter 6 for exit");
		System.out.println("-------------------------------------");
		System.out.println("enter choice:");
		i=sc.nextInt();
		switch (i) {
		case 1:
			System.out.println("enter num which u want to insert:");
			Tree.insert(sc.nextInt());
	
		break;
		case 2:
			System.out.println("enter num which u want to insert:");
			Tree.delete(sc.nextInt());
			break;
		case 3:
			System.out.println("in order form");
			Tree.InOrder();
			System.out.println("pre order form");
			Tree.PreOrder();
			System.out.println("post order form");
			Tree.PostOrder();
			System.out.println("level order form");
			Tree.LevelOrder();
			break;
		case 4:
			Tree.Height();
			break;
		case 5:
			System.out.println("total nodes are "+Tree.TotalNodes(Tree.root));
			break;
		case 6:
			System.out.println("thanks for using this");
			break;
		default :
			System.out.println("enter valid choice");
		}
		}while(i!=6);//end of while
	}//end of main

}//end of AvlTree
