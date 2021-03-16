package 树;

public class test {
    public static void main(String[] args) {
        test test = new test();
        String[] array={"kiwi","apache","java","Tomcat","Python","PHP","Servlet"};
        int[] arr={1,2,3,4,5,6,7};
        //数字
        Node root=test.create_NodeByArray(arr,0);
        root.printTree();




    }
    public void adjustHeap(int[] array,int i){
        int temp=array[i];
        //依次遍历所有左子节点和右子节点
        for(int index=0;index<array.length;index=index*2+1){
            if(index+1<array.length && array[index]<array[index+1]){
                index++;
            }
            if(array[index]>temp){
                array[i]=array[index];
                i=index;
            }
        }
    }

    //直接通过数组来创建--------->完全二叉树
    public Node create_NodeByArray(int[] array,int index){
        Node node=null;
        //node=new Node(array[index]);     //这里的话可能2*index+1已经越界了
        if(index<array.length){
            node=new Node(array[index]);
            node.left=create_NodeByArray(array,2*index+1);
            node.right=create_NodeByArray(array,2*index+2);
        }
        return node;
    }

    public Node create_BinaryTree(String[] arr,int index){
        Node node = new Node(arr[index]);
        if(2*index+1<arr.length){
            node.left=create_BinaryTree(arr,index*2+1);
        }
        if(2*index+2<arr.length){
            node.right=create_BinaryTree(arr,index*2+2);
        }
        return node;
    }
}

class deal_Node {
    public Node pre=null;



}

class Node{
    public int leftID=0;
    public int rightID=0;
    public Node left;
    public Node right;
    public String date;
    public int id;
    public Node(String date){
        this.date=date;
    }
    public Node(int id){this.id=id;}
    public void printTree(){
        System.out.println(this);
        if(this.left!=null){
            this.left.printTree();
        }
        if(this.right!=null){
            this.right.printTree();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }
}
