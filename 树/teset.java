package 树;

public class teset {
    small_Node pre=null;
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        teset t = new teset();
        small_Node tree = t.create_Tree(arr, 0);
        tree.show();
        System.out.println(tree);
        t.Import(tree);
        System.out.println(tree.left.left.right);
    }

    public small_Node create_Tree(int[] arr,int index){
        small_Node node=null;
        if(index<arr.length){
            node=new small_Node(arr[index]);
            node.left=create_Tree(arr,index*2+1);
            node.right=create_Tree(arr,index*2+2);
        }
        return node;
    }

    public void Import(small_Node node){

        Thread_Tree(node);
    }
    public void Thread_Tree(small_Node node){
        if(node==null){
            return;
        }
        Thread_Tree(node.left);
        if(node.left==null){
            node.left=pre;
            node.left_id=1;
        }
        if(pre!=null&&pre.right==null){
            pre.right=node;
            pre.right_id=1;
        }
        pre=node;
        Thread_Tree(node.right);
    }

}
class small_Node{
    public int left_id;
    public int right_id;
    public small_Node left;
    public small_Node right;
    private int id;
    public small_Node(int id){
        this.id=id;
    }
    public void show(){
        System.out.println(this);
        if(this.left!=null){
            this.left.show();
        }
        if(this.right!=null){
            this.right.show();
        }
    }

    @Override
    public String toString() {
        return "small_Node{" +
                "id=" + id +
                '}';
    }
}
