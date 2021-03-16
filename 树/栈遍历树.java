package 树;

import java.util.Stack;

public class 栈遍历树 {
    public static void main(String[] args) {
        Tree_eachNode t1 = new Tree_eachNode("kiwi");
        Tree_eachNode t2 = new Tree_eachNode("Apache");
        Tree_eachNode t3 = new Tree_eachNode("banana");
        Tree_eachNode t4 = new Tree_eachNode("Tomcat");
        Tree_eachNode t5 = new Tree_eachNode("JAVA");
        Tree_eachNode t6 = new Tree_eachNode("Centos");
        Tree_eachNode t7 = new Tree_eachNode("Python");
        Tree_eachNode t8 = new Tree_eachNode("PHP");
        Tree_eachNode t9 = new Tree_eachNode("Linux");
        t1.left=t2;
        t1.mid=t3;
        t1.right=t4;
        t2.mid=t5;
        t2.right=t6;
        t6.left=t7;
        t7.left=t8;
        t3.mid=t9;
        t1.Show_By_stack();
        System.out.println("-------------------------------");
        t1.Show_listBy_r();
        System.out.println("-------------------------------");
        int[] arr={1,2,3,4,5,6,7,8,9,10};
        栈遍历树 s = new 栈遍历树();
        s.shows(arr);
        System.out.println("-----------------------");
        Deal_Tree deal_tree = new Deal_Tree();
        deal_tree.show_list(t1);
    }

    public void shows(int[] arr){
        Stack<Integer> stack = new Stack<>();
        int index=0;
        stack.push(arr[index]);
        while (!stack.empty()){
            Integer pop = stack.pop();
            System.out.println(pop);
            if(index<arr.length){
                stack.push(arr[index++]);
            }
        }
    }
}


class Deal_Tree{
    Tree_eachNode root;
    public void setRoot(Tree_eachNode root){
        this.root=root;
    }
    public void show_list(Tree_eachNode root){
        System.out.println(root);
        if(root.right!=null){
            show_list(root.right);
        }
        if(root.mid!=null){
            show_list(root.mid);
        }
        if(root.left!=null){
            show_list(root.left);
        }
    }

}

class Tree_eachNode{
    public Tree_eachNode left;
    public Tree_eachNode mid;
    public Tree_eachNode right;
    private String date;

    public Tree_eachNode(String date){
        this.date=date;
    }

    //递归遍历三叉树
    public void Show_listBy_r(){
        System.out.println(this);
        if(this.mid!=null){
            this.mid.Show_listBy_r();
        }
        if(this.left!=null){
            this.left.Show_listBy_r();
        }
        if(this.right!=null){
            this.right.Show_listBy_r();
        }
    }

    //栈遍历三叉树
    public void Show_By_stack(){
        Stack<Tree_eachNode> stack = new Stack<>();
        stack.push(this);
        //相当于对一个一个节点分析，分析该节点的左中右节点
        while (!stack.empty()){
            Tree_eachNode pop = stack.pop();    //每次都不一样，实现重复入栈和出栈
            System.out.println(pop);
            if(pop.left!=null){
                stack.push(pop.left);
            }
            if(pop.mid!=null){
                stack.push(pop.mid);
            }
            if(pop.right!=null){
                stack.push(pop.right);
            }
        }
    }

    @Override
    public String toString() {
        return "Tree_eachNode{" +
                "date='" + date + '\'' +
                '}';
    }
}
