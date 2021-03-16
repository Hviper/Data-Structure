package 树;

public class 线索化二叉树 {
    public static void main(String[] args) {
        Thread_Node t1 = new Thread_Node("kiwi");
        Thread_Node t2 = new Thread_Node("Apache");
        Thread_Node t3 = new Thread_Node("Tomcat");
        Thread_Node t4 = new Thread_Node("Java");
        Thread_Node t5 = new Thread_Node("Python");
        Thread_Node t6 = new Thread_Node("PHP");
        t1.left=t2;
        t2.left=t3;
        t1.right=t4;
        t2.right=t5;
        t3.right=t6;
        Thread_Tree thread_tree = new Thread_Tree();
        thread_tree.setRoot(t1);

        System.out.println("----------------------------");
        System.out.println(t3.left);
        System.out.println(t3.right);
        System.out.println("--------------------------");
        thread_tree.ThreadNodes(t1);
        System.out.println("----------------------------");
        System.out.println(t3.left);
        System.out.println(t3.right);
        System.out.println(t6.right);
        System.out.println("--------------------------");
    }

    //通过字符串数组（用于初始化Thread_Node的各个date域）直接递归创建完全二叉树
    public Thread_Node Binary_ByArray(String[] arr,int index){
        Thread_Node node=null;
        if(index<arr.length){
            node=new Thread_Node(arr[index]);
            node.left=Binary_ByArray(arr,index*2+1);
            node.right=Binary_ByArray(arr,index*2+2);
        }

        return node;

    }
}


class Thread_Tree{
    Thread_Node root=null;
    //临时变量pre来记录当前节点的（前驱）前一个节点
    Thread_Node pre=null;
    public void setRoot(Thread_Node root){
        this.root=root;
    }

    //封装接口供外界通过设置root直接遍历二叉树
    public void show_AllNode(){
        if(root==null){
            return;
        }
        root.show_each_Node();
    }

    //编写二叉树线索化二叉树的方法 【中序线索化】
    //该node就是当前需要线索化的节点
    public void ThreadNodes(Thread_Node node){
        if(root==null || node==null){
            return;
        }
        //正式代码执行区域
                                       //由于参数不同
        ThreadNodes(node.left);      //在此之前栈中会不断创建出独立的函数栈空间！！直到node.left指向为null，即找到指向最左边上的节点


        //中序代码实现，开始对单个最后节点处理
        if(node.left==null){
            node.left=pre;
            node.Thread_left=1;
        }
            //通过修改pre的指向来修改当前节点的right指向
        if(pre!=null && pre.right==null){
            //让前驱节点的右指针指向当前节点
            pre.right=node;
            pre.Thread_right=1;
        }
        //！！！每处理一个节点后，让当前是下一个节点的前驱节点  【node跟着走的时候需要保存上一次的node的值】
        pre=node;      //pre临时保留当前节点的地址引用【这样才能修改当前节点的right “指向” 递归回去的前一个节点】

        ThreadNodes(node.right);

    }

}

class Thread_Node{
    public String date;
    public Thread_Node left;
    public Thread_Node right;
    public int Thread_left;
    public int Thread_right;

    public Thread_Node(String date){
        this.date=date;
    }

    public void show_each_Node(){
        System.out.println(this);
        if(this.left!=null){
            this.left.show_each_Node();
        }
        if(this.right!=null){
            this.right.show_each_Node();
        }
    }

    @Override
    public String toString() {
        return "Thread_Node{" +
                "date='" + date + '\'' +
                '}';
    }
}
