package 二叉树.二叉平衡树;

import java.util.LinkedList;
import java.util.Queue;

public class test {
    /**
     * test代码
     * @param args
     */
    public static void main(String[] args) {
        test test = new test();
        int[] arr={37, 21, 45, 99, 38, 42, 25, 63, 70, 79, 94, 9};
        for (int value : arr) {
            test.add(value);
        }
        test.remove(2);
        System.out.println("遍历结构位");
        test.Level_OrderTraversal();

        test.change_lift_right(test.root);
        System.out.println("\n遍历结构位\n");
        test.Level_OrderTraversal();




    }




    private A_Node root;

    //对外

    //交换左右节点的值的函数
    public void change_lift_right(A_Node node){
        if(node==null || node.left ==null && node.right ==null)return;
        if(node.left!=null && node.right!=null){
            int temp = node.left.element;
            node.left.element = node.right.element;
            node.right.element = temp;
        }else {
            if(node.left!=null){
                node.right=node.left;
                node.left=null;
            }else {
                node.left=node.right;
                node.right=null;
            }
        }
        change_lift_right(node.left);
        change_lift_right(node.right);

    }


    public void Middle_list(A_Node node){
        if(node.left!=null){
            Middle_list(node.left);
        }
        System.out.print(node.element+"\t");
        if(node.right != null){
            Middle_list(node.right);
        }
    }

    //找前驱节点的方法
    private A_Node find_preNode(int element){
        A_Node target = find_By_element(element);
        if(target == null){
            throw new NullPointerException("无法找到该节点");
        }
        if(target.left != null){
            A_Node left_Node = target.left;
            while (left_Node.right != null){
                left_Node = left_Node.right;
            }
            return left_Node;
        }else if(target.parent!=null){
            while (target.parent.right != target){
                target = target.parent;
            }
            return target.parent;
        }
        else {     //没有父节点
            return null;
        }
    }



    public void Level_OrderTraversal(){
        inner_Level_OrderTraversal(root);
    }
    private void inner_Level_OrderTraversal(A_Node root){
        Queue<A_Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            A_Node poll = queue.poll();
            System.out.print(poll.element+"\t");
//            if(poll.has_twoChild()){
//                System.out.println(poll.element+"的父节点为："+poll.parent+"\t"+"高度为:"+poll.height+"  =-------->满节点");
//            }else if(poll.isLeaf()){
//                System.out.println(poll.element+"的父节点为："+poll.parent+"\t"+"高度为:"+poll.height+"  =-------->叶子节点");
//            }else {
//                System.out.println(poll.element+"的父节点为："+poll.parent+"\t"+"高度为:"+poll.height+"  =-------->单叶子节点");
//            }
            if(poll.left!=null){
                queue.offer(poll.left);
            }if(poll.right!=null){
                queue.offer(poll.right);
            }
        }
    }


    public void remove(int element){
        A_Node target = find_By_element(element);
        if(target == null) return;
        if(target.has_twoChild()){
            A_Node preNode = find_preNode(element);      //找到前驱节点
            if(preNode !=null){
                target.element = preNode.element;        //找到前驱节点的值去替代需要删除的值
                target = preNode;            //删除target即可
            }else {
                root = null;
            }
        }
        //下面代码尽管删除 target 即可
        if(target.isLeaf()){     //处理叶子节点
            if(target.parent !=null && target.parent.left == target){
                target.parent.left = null;
                After_remove(target);               //调整1
            }else if(target.parent !=null && target.parent.right == target){
                target.parent.right = null;
                After_remove(target);               //调整2
            }
            else {
                root = null;
            }
        }
        else {         //处理单叶子节点
            A_Node replacement = target.left == null ? target.right:target.left;
            if(target.isParent_left()){
                target.parent.left = replacement;
                replacement.parent = target.parent;
                After_remove(target);             //调整3
            }else if(target.isParent_right()){
                target.parent.right = replacement;
                replacement.parent = target.parent;
                After_remove(target);              //调整4
            }
            else {
                root = replacement;
            }
        }
    }

    public void After_remove(A_Node node){
        while ((node=node.parent)!=null){
            if(node.is_Balance()){
                node.update_high();
            }
            else {
                release_balance(node);       //该node一定有三个属性
            }
        }
    }



    private A_Node find_By_element(int element){
        A_Node temp = root;
        while (temp!=null){
            if(temp.element == element){
                return temp;
            }
            if(temp.element>element){
                temp = temp.left;
            }else {
                temp = temp.right;
            }
        }
        return null;
    }

    private A_Node create_A_Node(int element,A_Node parent){
        return new A_Node(element, parent);
    }

    public void add(int element){
        if(root == null){
            root=create_A_Node(element,null);   //root父节点为空
            return;
        }
        A_Node temp = root;
        A_Node parent = root;
        while (temp!=null){
            parent = temp;
            if(temp.element == element){
                return;
            }
            if(temp.element>element){
                temp=temp.left;
            }
            else {
                temp=temp.right;
            }
        }
        A_Node node;
        if(parent.element>element){
            node=create_A_Node(element,parent);
            parent.left=node;
        }else {
            node = create_A_Node(element,parent);
            parent.right=node;
        }
        addAfter(node);
    }


    //一定有子，因为传进的一定是parent属性



    /**
     * 查找是否 “存在” 不平衡节点，没找到就更新高度，【因为新添加的节点高度必定为1，根据1+max(left,right).height更新高度】
     * @param node
     */
    public void addAfter(A_Node node){
        while ((node=node.parent)!=null){
            if(node.is_Balance()){
                node.update_high();
            }
            else {
                release_balance(node);       //该node一定有三个属性
                break;
            }
        }
    }

    /**不平衡节点一定有三代
     * 传进的都是不平衡的节点！！  【 且 ，至少也是 parent 级别，即有子节点的和子孙节点！！【三剑客】】
     * child 和 parent 都不为空的！
     * @param node  :不平衡的节点
     */
    private void release_balance(A_Node grand){
        A_Node parent = grand.best_high_Child();
        A_Node child = parent.best_high_Child();      //一定不为空！！因为能进这里的说明该节点为不平衡节点 =-----> 至少也有三层

        /**
         * 证明该parent和child和grand一定不为空
         */
//        System.out.println("祖父节点"+grand.element);
//        System.out.println("夫节点"+parent.element);
//        System.out.println("夫节点"+child.element);


        if(grand.left == parent){        //L
            if(child.isParent_left()){    //LL
                adjust_right(grand);
            }else {                     //LR
                adjust_left(parent);
                adjust_right(grand);
            }
        }
        else {
            if(child.isParent_left()){   //RL
                adjust_right(parent);
                adjust_left(grand);
            }
            else {                     //RR
                adjust_left(grand);
            }
        }
    }

    public void test_left(A_Node grand){
        A_Node parent = grand.right;
        A_Node child = parent.left;
    }

    //左旋转
    private void adjust_left(A_Node grand){
        A_Node parent = grand.right;
        A_Node child = parent.left;         //child可能为空null
        grand.right=child;
        parent.left=grand;

        parent.parent = grand.parent;         //如果为null也行
        if(grand.isParent_left()){
            grand.parent.left=parent;
        }else if(grand.isParent_right()){
            grand.parent.right=parent;
        }else {
            root=parent;
        }
        grand.parent=parent;
        if(child!=null){
            child.parent=grand;
        }
        grand.update_high();        //因为grand已经调整至下面，且可以知道grand左右节点高度一定是已知！
        parent.update_high();
    }
    //右旋转
    private void adjust_right(A_Node grand){
        A_Node parent = grand.left;
        A_Node child = parent.right;
        grand.left=child;
        parent.right=grand;

        parent.parent = grand.parent;
        if(grand.isParent_right()){
            grand.parent.right=parent;
        }else if(grand.isParent_left()){
            grand.parent.left=parent;
        }else {
            root = parent;
        }
        grand.parent=parent;
        if(child!=null){
            child.parent=grand;
        }
        grand.update_high();
        parent.update_high();

    }

}
class A_Node{
    public A_Node left;
    public A_Node right;
    public A_Node parent;
    public int element;
    public int height = 1;
    public A_Node(int element,A_Node parent){
        this.element=element;
        this.parent=parent;
    }
    public boolean has_twoChild(){
        return this.left!=null && this.right!=null;
    }
    public boolean isLeaf(){
        return this.left==null && this.right==null;
    }

    //判断自己和父节点的关系
    public boolean isParent_left(){
        return this.parent!=null && this==this.parent.left;
    }

    public boolean isParent_right(){
        return this.parent!=null && this==this.parent.right;
    }

    public boolean is_Balance(){
        int left_high = this.left == null ? 0: this.left.height;
        int right_high = this.right == null ? 0: this.right.height;
        return Math.abs((left_high-right_high)) <= 1;
    }

    public void  update_high(){
        int left_high = this.left == null ? 0: this.left.height;
        int right_high = this.right == null ? 0: this.right.height;
        this.height = 1+Math.max(left_high,right_high);
    }

    //返回最高子节点
    public A_Node best_high_Child(){
        int left_high = this.left == null ? 0: this.left.height;
        int right_high = this.right == null ? 0: this.right.height;
        if(left_high > right_high)return left;
        if(left_high < right_high)return right;
        return this.isParent_left() ? left:right;
    }

    @Override
    public String toString() {
        return "A_Node{" +
                "parent=" + parent +
                ", element=" + element +
                '}';
    }
}
