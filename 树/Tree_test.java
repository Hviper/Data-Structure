package 树;

public class Tree_test {
    public static void main(String[] args) {
        Tree_Node node1 = new Tree_Node("kiwi", 1);
        Tree_Node node2 = new Tree_Node("java", 2);
        Tree_Node node3 = new Tree_Node("Apache", 3);
        Tree_Node node4 = new Tree_Node("Tomcat", 4);
        Tree_Node node5 = new Tree_Node("第三个输出！！！", 5);

        node1.left=node2;
        node2.right=node5;
        node1.right=node3;
        node3.right=node4;
        Binary_tree binary_tree = new Binary_tree();
        binary_tree.setRoot(node1);
        System.out.println("删除------------------");
        binary_tree.mid_Node_search();



        System.out.println("删除后----------------");
        binary_tree.remove(node4);
        binary_tree.mid_Node_search();
    }
}

class Binary_tree{
    private Tree_Node root;

    public Tree_Node search(int id){
        return root.searchBy_id(id);
    }


    public void remove(Tree_Node node){
        root.remove_Node(node);
    }

    public void mid_Node_search(){
        if(root!=null){
            root.mid_list();
        }
        else {
            System.out.println("当前root节点为空！");
        }
    }


    public void setRoot(Tree_Node root) {
        this.root = root;
    }
}

class Tree_Node{
    //可以通过当前节点来找到左右两个节点的数据,
    // 因为让deal_Node来处理每一个节点的right和left节点会很麻烦，节点之间的关系可以在节点类中定义相关函数来连接
    public Tree_Node left;
    public Tree_Node right;
    public String date;
    public int id;

    public Tree_Node(String date,int id){
        this.date=date;
        this.id=id;
    }


    public Tree_Node searchBy_id(int id){
        if(this.id==id){
            return this;
        }
        Tree_Node temp=null;
        if(this.right!=null){
            temp=this.right.searchBy_id(id);
        }
        if(temp!=null){
            return temp;
        }
        if(this.left!=null){
            temp=this.left.searchBy_id(id);
        }
        return temp;
    }

    //中序遍历
    public void mid_list(){
        if(this.left!=null){
            this.left.mid_list();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.mid_list();
        }
    }

    public void remove_Node(Tree_Node node){
        if(this.left!=null&&this.left.id==node.id){
            this.left=null;
            return;
        }
        if(this.right!=null&&this.right.id==node.id){
            this.right=null;
            return;
        }
        if(this.left!=null){
            this.left.remove_Node(node);
        }
        if(this.right!=null){      //假如左left节点依然无法找到该节点将其删除，就启用右节点
            this.right.remove_Node(node);
        }

    }

    @Override
    public String toString() {
        return "Tree_Node{" +
                "date='" + date + '\'' +
                ", id=" + id +
                '}';
    }
}
