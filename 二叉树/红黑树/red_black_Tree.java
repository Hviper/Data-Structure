package 二叉树.红黑树;



import java.util.LinkedList;
import java.util.Queue;

public class red_black_Tree {

    public static void main(String[] args) {
        red_black_Tree red_black_tree = new red_black_Tree();
        int[] arr={72, 50, 38, 14, 2, 85, 68, 91, 11, 8, 24, 27, 77, 69, 28, 19, 64, 43, 32, 49};
        for (int i : arr) {
            red_black_tree.add(i);

        }
        red_black_tree.list();
        System.out.println("\n高度为:"+red_black_tree.getHeight());
        System.out.println("根节点为"+red_black_tree.root);
        red_black_tree.remove(72);
        red_black_tree.list();

    }




    private static final boolean RED=false;
    private static final boolean BLACK=true;
    private Red_Node root;
    private int height;

    private Red_Node create_Node(int element,Red_Node parent){
        return new Red_Node(element, parent);
    }


    /**
     * 红黑树add之后处理 （新添加的元素默认为：RED）
     * @param node
     * 一：父节点为黑色的情况【最简单的四种】  直接 return
     * 二：判断条件：uncle 不是 RED  【其中四种】
     *         1.parent 染成BLACK，grand 染成RED
     *         2.grand 进行单旋操作   【LL:右旋转 ， RR：左旋转，
     *         /2.双旋操作：----> RL:parent右旋转，grand左旋转；  LR:parent左旋转，grand右旋转】
     * 三：【最后4种】
     *
     *
     * 红黑树是通过颜色的约束来维持二叉树的平衡！！
     */

    public void  afterAdd(Red_Node node){
        Red_Node parent = node.parent;
        //添加的是根节点，将根节点染成黑色 【 或者上溢到达根节点 】
        if(parent==null){
            black(node);
        }
        //四种情况中的父节点为黑色的情况！，不做处理直接返回
        if(isBlack(parent))return;       //isBlack()函数中，假如传进的是空节点null，则返回黑色

        //uncle 节点
        Red_Node uncle = parent.sibling();
        Red_Node grand = parent.parent;

        //叔父节点是红色且parent也是红色的情况！
        if(isRed(uncle)){
            black(parent);
            black(uncle);        //【两个染成黑色是为了实现：假如一个节点为红色，则它的两个节点都是黑色】
            afterAdd(red(grand));       //祖父节点当成新添加的节点上溢，【上溢之前染红色】
            return;               //叔父节点的逻辑写完了，因此return
        }

        //叔父节点不是红色但是parent是红色的情况
        if(parent.isParentLeft_Child()){
            if(node.isParentLeft_Child()){       //LL
                black(parent);
                red(grand);
            }else {                                //LR
                black(node);
                red(grand);
                while_left(parent);
            }
            while_right(grand);
        }
        else {                      //R
            if(node.isParentLeft_Child()){       //RL
                black(node);
                red(grand);
                while_right(parent);

            }else {                                //RR
                black(parent);
                red(grand);
            }
            while_left(grand);
        }

    }

    /**
     * 旋转
     * @param element
     */
    //左旋转（RR）
    public void while_left(Red_Node grand){    //grand节点    【三者都需要更新父节点】
        Red_Node parent = grand.right;       //parent节点
        Red_Node child = parent.left;       //child节点
        //交换两个指针指向
        grand.right=child;
        parent.left=grand;

        //维护父节点1
        parent.parent=grand.parent;
        if(grand.isParentRight_Child()){
            grand.parent.right=parent;           //可能grand.parent为null
        }
        else if(grand.isParentLeft_Child()){
            grand.parent.left=parent;
        }else {     //处理grand.parent为null情况，即grand为root
            root=parent;
            root.parent=null;
        }
        if(child!=null){child.parent=grand;}      //维护父节点2
        grand.parent=parent;                      //维护父节点3

    }
    //右旋转(LL)：指针交换属于常数级别 O（1）复杂度
    public void while_right(Red_Node grand){
        Red_Node parent = grand.left;
        Red_Node child = parent.right;          //可能为空null
        grand.left=child;
        parent.right=grand;

        parent.parent=grand.parent;
        if(grand.isParentLeft_Child()){
            grand.parent.left=parent;
        }else if(grand.isParentRight_Child()){
            grand.parent.right=parent;
        //没有parent的情况。即为root节点的parent == null
        }else {
            root=parent;
        }
        grand.parent=parent;
        if(child!=null){
            child.parent=grand;
        }

    }

    /**
     * add() 添加方法
     * @param  element !!
     */

    public void add(int element){
        if(root == null){
            root = create_Node(element,null);
            afterAdd(root);
                     //根节点为黑色
            return;
        }
        Red_Node temp = root;
        Red_Node parent = root;
        while (temp !=null){
            parent = temp;
            if(temp.element > element){
                temp = temp.left;
            }
            else if(temp.element < element){
                temp = temp.right;
            }
            else {return;}
        }
        Red_Node node=create_Node(element,parent);
        if(parent.element > element){
            parent.left = node;
        }else {
            parent.right = node;
        }
        afterAdd(node);

    }




    /**
     * 删除
     * @param element
     */

    public void removeAfter(Red_Node node,Red_Node replacement){
        if(isRed(node)){return;}       //红色叶子节点（一种）
        if(isRed(replacement)){        //度为一的节点（两种）
            black(replacement);
            return;
        }


        /***            “ 删除黑色的情况 ”
         * 最后一种情况：度为 " 一 " 且 被删除的节点颜色为黑色的情况
         * 看被删除的节点的兄弟节点的颜色判断进行是否可以借用或者进行下溢
         * sibling : 兄弟节点
         * 当黑色的节点存在时，必定在同阶的高度中有节点，即：----> 黑节点一定存在兄弟节点
         */
        Red_Node parent = node.parent;
        if(parent == null){
            return;         //删除的是根节点【度为一且为黑】,也是递归出口！！！
        }
        boolean left = parent.left ==null;
        Red_Node sibling = left ? parent.right:parent.left;
        /**
         * 因为旋转的方向不同，分为被删除的节点的位置不一样，在右边和左边的情况
         */
        if(left){        //被删除的节点在左边，兄弟节点在右边

            if(isRed(sibling)){
                red(parent);
                black(sibling);
                while_left(parent);
                sibling = parent.right;
            }
            //兄弟节点必定是黑色的

            if(isBlack(sibling.left) && isBlack(sibling.right)){   //兄弟节点的左右节点都是黑色！且当子节点是null时默认为黑色
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack){
                    removeAfter(parent,null);
                }
            }else {          //兄弟节点中至少有一个子节点为红色【即可以被node节点借！！】
                if(isBlack(sibling.right)){
                    while_right(sibling);
                    sibling = parent.right;
                }
                color(sibling,colorOf(parent));
                black(sibling.right);
                black(parent);
                while_left(parent);
            }

        }else {        //被删除的节点在右边，兄弟节点在左边
            /**
             * 先处理兄弟是红色的情况 --- > 转为黑色情况
             * 后面直接处理黑色情况即可
             */
            if(isRed(sibling)){
                red(parent);
                black(sibling);
                while_right(parent);
                sibling = parent.left;
            }
            //兄弟节点必定是黑色的

            if(isBlack(sibling.left) && isBlack(sibling.right)){   //兄弟节点的左右节点都是黑色！且当子节点是null时默认为黑色
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack){
                    removeAfter(parent,null);
                }
            }else {          //兄弟节点中至少有一个子节点为红色【即可以被node节点借！！】
                if(isBlack(sibling.left)){
                    while_left(sibling);
                    sibling = parent.left;
                }
                color(sibling,colorOf(parent));
                black(sibling.left);
                black(parent);
                while_right(parent);
            }

        }
    }

    public void remove(int element){
        Red_Node target = find_By_element(element);
        if(target == null){
            throw new NullPointerException("没有找到对应的元素！！");
        }
        if(target.two_Child()){
            Red_Node preNode = find_PreNode(element);
            if(preNode != null){
                target.element = preNode.element;
                target = preNode;
            }else {
                root = null;
            }
        }
        if(target.isLeaf()){
            if(target.parent!=null && target.isParentRight_Child()){
                target.parent.right = null;
            }
            else if(target.parent!=null && target.isParentLeft_Child()){
                target.parent.left = null;
            }else {
                root = null;
            }
            removeAfter(target,null);
        }
        else {
            Red_Node replacement = target.left == null ? target.right : target.left;
            if(target.parent!=null && target.isParentLeft_Child()){
                target.parent.left = replacement;
                replacement.parent = target.parent;

            }else if(target.parent!=null && target.isParentRight_Child()){
                target.parent.right = replacement;
                replacement.parent = target.parent;

            }else {
                root=replacement;
                replacement.parent=null;
            }
            removeAfter(target,replacement);
        }

    }

    //对于中序遍历的找前驱节点而言
    public Red_Node find_PreNode(int element){
        Red_Node target = find_By_element(element);
        if(target == null){
            return null;
        }
        if(target.left !=null){
            target = target.left;
            while (target.right!=null){
                target = target.right;
            }
            return target;
        }
        else if(target.parent !=null){
            while (target.parent.right != target){
                target = target.parent;
            }
            return target.parent;
        }else {
            return null;
        }
    }

    public Red_Node find_By_element(int element){
        Red_Node temp = root;
        while (temp !=null){
            if(temp.element==element){
                return temp;
            }
            else if(temp.element>element){
                temp = temp.left;
            }
            else {
                temp = temp.right;
            }
        }
        return null;
    }


    /**
     * 遍历 ---->   【中序遍历】   {递归法}
     */

    public void Middle_list(Red_Node root){
        if(root.left != null){
            Middle_list(root.left);
        }
        System.out.print(root.element+"\t");
        if(root.right !=null){
            Middle_list(root.right);
        }
    }

    /**
     * 遍历 ---> 【层序遍历】     {非递归法}
     */
    public void list(){
        Inner_list(root);
    }
    private void Inner_list(Red_Node root){
        Queue<Red_Node> queue = new LinkedList<>();
        queue.offer(root);
        int size = 1;
        while (!queue.isEmpty()){
            Red_Node poll = queue.poll();
            size--;
            /**
             * 判断节点颜色
             */
            String i ="";
            if(poll.color){
                i="黑色";
            }else {i="红色";}
            System.out.println(poll.element+"\t"+"颜色为"+i);
            if(poll.left != null){
                queue.offer(poll.left);
            }
            if(poll.right !=null){
                queue.offer(poll.right);
            }
            if(size == 0 ){
                size = queue.size();
                height++;
            }
        }
    }

    public int getHeight() {
        return height;
    }




    /**
     *红黑树的基本操作
     * 染色返回该节点的颜色
     */
    private Red_Node color(Red_Node node,boolean color){
        if(node == null)return null;        //默认为黑色，null也为黑色 return node/null
        node.color=color;
        return node;
    }

    //染色
    public Red_Node red(Red_Node node){
        return color(node,RED);
    }
    public Red_Node black(Red_Node node){
        return color(node,BLACK);
    }

    //判断该节点的颜色
    private boolean colorOf(Red_Node node){
        return node == null ? BLACK : node.color;    //空节点默认为黑色
    }
    public boolean isRed(Red_Node node){
        return !colorOf(node);
    }
    public boolean isBlack(Red_Node node){
        return colorOf(node);
    }





    /**--------------------------------------------------------------------------------------------------------------------->
     * 节点类
     */
    static class Red_Node{
        private Red_Node left;
        private Red_Node right;
        private int element;
        private Red_Node parent;
        public boolean color=RED;

        public Red_Node(int element,Red_Node parent){
            this.element=element;
            this.parent=parent;
        }



        public boolean isLeaf(){
            return this.left ==null && this.right ==null;
        }

        public boolean two_Child(){
            return this.left !=null && this.right!=null;
        }

        public boolean isParentLeft_Child(){
            return this.parent!=null && this.parent.left==this;
        }

        public boolean isParentRight_Child(){
            return this.parent!=null && this.parent.right==this;
        }

        public Red_Node sibling(){
            if(isParentLeft_Child()){
                return parent.right;
            }
            if(isParentRight_Child()){
                return parent.left;
            }
            return null;       //没有兄弟节点，假如要拿叔父节点即只需要 node.parent.sibling()即可
        }

        @Override
        public String toString() {

            return "Red_Node{" +
                    "element=" + element +
                    ", parent=" + parent +
                    ", color=" + color +
                    '}';
        }
    }
}

