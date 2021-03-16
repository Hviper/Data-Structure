package 二叉树.红黑树;

public class test_red_black_Tree {
    public static void main(String[] args) {
        test_red_black_Tree test = new test_red_black_Tree();
        int[] arr={51, 97, 79, 60, 66, 68, 85, 1, 38, 36, 25, 28};
        for (int i : arr) {
            test.add(i);
        }
        test.middle_list(test.root);
    }


    private static final boolean RED=false;
    private static final boolean BLACK=true;
    private Node root;

    public Node createNode(int date,Node parent){
        return new Node(date,parent);
    }


    public void after_add(Node node){
        Node parent = node.parent;
        if(parent == null){
            black(node);
        }
        /**
         * 因为当父节点为black不做任何处理！所以一定存在parent和grand和node三个节点的存在
         * uncle不一定存在，
         *      1.uncle存在：有黑色和红色两种情况
         *      2.uncle不存在，即默认为黑色
         *
         */
        if(isBlack(parent)){
            return;
        }
        Node uncle = parent.brother();
        Node grand = parent.parent;
        if(isBlack(uncle)){
            if(parent.isParent_liftChild()){
                if(node.isParent_liftChild()){        //LL

                }else {                              //LR

                }
            }
            else {
                if(node.isParent_rightChild()){       //RR

                }else {             //RL

                }
            }
        }
    }




    public void add(int date){
        if(root ==null){
            root=createNode(date,null);

            return;
        }
        Node temp =root;
        Node parent=root;
        while (temp!=null){
            parent = temp;
            if(temp.date > date){
                temp=temp.left;
            }
            else if(temp.date < date){
                temp = temp.right;
            }else {
                return;
            }
        }
        Node new_node = createNode(date, parent);
        if(parent.date > date){
            parent.left = new_node;
        }else {
            parent.right = new_node;
        }

    }




    public void middle_list(Node root){
        if(root.left !=null){
            middle_list(root.left);
        }
        System.out.print(root.date+"\t");
        if(root.right!=null){
            middle_list(root.right);
        }
    }



    /**
     * 红黑树基本操作
     * @param node
     * @return
     */
    private Node color(Node node,boolean color){
        if(node==null)return null;
        node.color=color;
        return node;
    }


    //染色
    public Node red(Node node){
        return color(node,RED);
    }
    public Node black(Node node){
        return color(node,BLACK);
    }


    public boolean colorOf(Node node){
        return node==null?BLACK:node.color;              //uncle为空的时候默认为黑色
    }
    public boolean isRed(Node node){
        return colorOf(node)==RED;
    }
    public boolean isBlack(Node node){
        return colorOf(node)==BLACK;
    }




    /**
     * 节点类
     */
    static class Node{
        private Node left;
        private Node right;
        private int date;
        private Node parent;
        private boolean color =RED;
        public Node(int date,Node parent){
            this.date=date;
            this.parent=parent;
        }

        public Node brother(){
            return isParent_liftChild()?this.parent.right:this.parent.left;  //包含空
        }

        public boolean isParent_liftChild(){
            return this.parent.left == this;
        }
        public boolean isParent_rightChild(){
            return this.parent.right == this;
        }



    }
}

