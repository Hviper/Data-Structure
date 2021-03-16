package 二叉树.二叉平衡树;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AVL_Tree {
    public static void main(String[] args) {
        AVL_Tree avl_tree = new AVL_Tree();
        int[] arr = {63, 88, 91, 85, 62, 79, 93, 49};
        for (int value : arr) {
            AVL_Node avl_node = new AVL_Node(value);
            avl_tree.add(avl_node);
        }
        avl_tree.printPath(avl_tree.root);
        System.out.println(avl_tree.find_target(avl_tree.root, 49));
        System.out.println("--------------------------->");
        System.out.println(avl_tree.BFS(avl_tree.root));
        System.out.println("--------------------------->");
        avl_tree.Level_OrderTraversal(avl_tree.root);
        System.out.println("最小高度为"+avl_tree.DFS(avl_tree.root));
    }
    int minDepth= Integer.MAX_VALUE;
    public int DFS(AVL_Node root){
        if(root==null){
            return 0;
        }

        dfs(root,1);
        return  minDepth;
    }

    private void dfs(AVL_Node root, int depth) {
        if(root.left==null && root.right==null){
            minDepth = Math.min(depth,minDepth);
        }
        if(root.left!=null){
            dfs(root.left, depth+1);
        }
        if(root.right!=null){
            dfs(root.right,depth+1);
        }
    }

    //深搜
    public int BFS(AVL_Node root){
        if(root==null)return 0;
        Queue<AVL_Node> queue = new LinkedList<>();
        queue.offer(root);
        int size=0;
        int depth = 1;
        while(!queue.isEmpty()){
            size = queue.size();
            for(int i=0;i<size;i++){            //for循环遍历每一层的节点
                AVL_Node poll = queue.poll();         //每一层去poll()
                assert poll != null;
                System.out.println(poll.toString());
                if(poll.left!=null){
                    queue.offer(poll.left);
                }
                if(poll.right!=null){
                    queue.offer(poll.right);
                }
            }
            depth++;          //类似高度
        }
        return depth;
    }


    public void Level_OrderTraversal(AVL_Node root) {
        Queue<AVL_Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            AVL_Node poll = queue.poll();
            System.out.println(poll.element + "\t");
//            if(poll.has_twoChild()){
//                System.out.println(poll.element+"的父节点为："+poll.parent+"\t"+"高度为:"+poll.height+"  =-------->满节点");
//            }else if(poll.isLeaf()){
//                System.out.println(poll.element+"的父节点为："+poll.parent+"\t"+"高度为:"+poll.height+"  =-------->叶子节点");
//            }else {
//                System.out.println(poll.element+"的父节点为："+poll.parent+"\t"+"高度为:"+poll.height+"  =-------->单叶子节点");
//            }
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
    }




    public AVL_Node find_target(AVL_Node root,int target){
        if(root==null || root.element == target)return root;
        AVL_Node left = find_target(root.left, target);
        AVL_Node right = find_target(root.right, target);
        if(left!=null && left.element==target)return left;
        if(right!=null && right.element==target)return right;
        return null;
    }


    Stack<Integer> path = new Stack<>();
    public void printPath(AVL_Node root){
        if(root == null)return;
        path.push(root.element);           //回溯法直接先将root加入到结果集中。再以root为根节点进行选择
        printpath(root);
    }
    private void printpath(AVL_Node root){
        if(root.left == null && root.right == null){
            System.out.println(path.toString());
            return;
        }
        if(root.left!=null){
            path.push(root.left.element);
            printpath(root.left);       //回溯的思路是等它找到返回的时候就再进行pop()处理
            path.pop();
        }
        if(root.right!=null){
            path.push(root.right.element);
            printpath(root.right);
            path.pop();
        }
//        if(root == null)return;
//        path.push(root.element);
//        if(root.left == null && root.right == null){      //前序遍历才能处理这样的问题
//            System.out.println(path.toString());
//            path.pop();          //叶子之后亥需要回溯
//            return;
//        }else {
//            printpath(root.left);
//            printpath(root.right);
//        }
//        path.pop();
    }



    int counts;
    Stack<AVL_Node> stack = new Stack<>();

    public void print_left(AVL_Node root){
        if(root == null){
            System.out.println(stack.toString());
            return;
        }
        stack.push(root);
        print_left(root.left);
        stack.pop();
        stack.push(root);
        print_left(root.right);
        stack.pop();
    }

    //从下往上数数
    public int get_high(AVL_Node root){
        if(root==null)return 0;
        return Math.max(get_high(root.left),get_high(root.right))+1;
    }
    /**
     * 求叶子节点的总数量
     * @param  root:根节点
     * @return： 返回叶子节点的数量
     */
    public int Leaf_number(AVL_Node root){
        if(root==null)return 0;
        if(root.isLeaf())return 1;
        return Leaf_number(root.left)+Leaf_number(root.right);
    }

    /**
     * 计算总节点数量
     */
    int number = 0;
    public void number(AVL_Node root){
        if(root == null)return;
        number ++ ;
        System.out.println(root);
        number(root.left);
        number(root.right);
    }


    public int height(AVL_Node node){
        if(node == null) return 0;
        return Math.max(height(node.right),height(node.left))+1;
    }

    public boolean Balance(AVL_Node root){
        if(root == null)return true;
        int left = height(root.left);
        int right = height(root.right);
        if(Math.abs(left-right)>1)return false;
        return Balance(root.left) && Balance(root.right);
    }
    /**
     * Leedcode测试代码：
     */


    public boolean isBalance(AVL_Node root) {
        if (root == null) return true;
        int left = get_length(root.left);
        int right = get_length(root.right);
        if (Math.abs(left - right) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int get_length(AVL_Node node) {
        int left, right;
        if (node == null) {
            return 0;
        }
        left = get_length(node.left);
        right = get_length(node.right);
        return left > right ? left + 1 : right + 1;
    }


    /**
     * Avl平衡二叉树的代码--------------------------------------------------------------------->
     */


    public AVL_Node root;
    private int size;


    public void pre(AVL_Node root){
        if(root==null)return;
        System.out.print(root.element+"\t");
        pre(root.left);
        pre(root.right);
    }
    //非递归的前序遍历
    public void preList() {
        if (root == null) return;
        Stack<AVL_Node> stack = new Stack<>();
        AVL_Node temp = root;
        stack.push(temp);
        while(!stack.isEmpty()){
            AVL_Node pop = stack.pop();
            System.out.print(pop.element+"\t");
            if(pop.right!=null){
                stack.push(pop.right);    //先进右节点
            }
            if(pop.left!=null){
                stack.push(pop.left);
            }
        }
    }

    public void preList2(){
        if(root == null)return;
        AVL_Node temp = root;
        Stack<AVL_Node> stack = new Stack<>();
        while(true){
            /**
             * if 和  else if 和 else中执行一个
             */
            if(temp!=null){
                System.out.print(temp.element+"\t");
                if(temp.right!=null){
                    stack.push(temp.right);
                }
                temp = temp.left;        //一路往左走
            }
            //temp为空遍历到了最左边，但是stack中有元素
            else if(!stack.isEmpty()){
                //处理右边
                temp = stack.pop();          //到左边的尽头后马上更新temp的指向继续循环
            }
            //stack为空
            else {
                return;
            }
        }
    }

    //中序遍历
    public void inorder(){
        if(root == null)return;
        AVL_Node temp = root;
        Stack<AVL_Node> stack = new Stack<>();
        while (true){
            if(temp!=null){
                stack.push(temp);      //因为将所有left入栈，当访问该节点时，也可以实现访问该节点的right！
                temp = temp.left;      //一路往左走
            }
            else if(!stack.isEmpty()){
                //处理右边
                temp = stack.pop();
                System.out.print(temp.element+"\t");
                temp = temp.right;              //因为
            }else {
                return;
            }
        }
    }
    public void inorder(AVL_Node node){
        if(node == null)return;
        inorder(node.left);
        System.out.print(node.element+"\t");
        inorder(node.right);
    }
    public void postList(){
        if(root == null)return;
        AVL_Node temp = root;
        Stack<AVL_Node> stack = new Stack<>();
        while(true){
            if(temp != null){
                stack.push(temp);
                if(temp.right!=null){
                    stack.push(temp.right);
                }
                if(temp.left!=null){
                    stack.push(temp.left);
                }
                temp = temp.left;
            }
            if(stack.peek().left == null){
                while (!stack.isEmpty()){
                    temp = stack.pop();
                    System.out.print(temp.element+"\t");
                }
                break;
            }
        }
    }

    public void postList(AVL_Node root){
        if(root == null)return;
        postList(root.left);
        postList(root.right);
        System.out.print(root.element+"\t");
    }


    public boolean isEmpty() {
        return size == 0;
    }





    public void add(AVL_Node Node) {
        if (root == null) {
            root = Node;
            root.parent = null;
            addAfter(root);       //平衡操作
            return;
        }

        AVL_Node parent=root;
        AVL_Node temp = root;
        while (temp != null) {
            parent = temp;
            if (temp.element > Node.element) {
                temp = temp.left;
            } else if (temp.element < Node.element) {
                temp = temp.right;
            } else return;      //相等，即不做处理，直接返回
        }
        if (parent.element > Node.element) {
            parent.left = Node;
        } else {
            parent.right = Node;
        }
        Node.parent = parent;
        addAfter(Node);      //平衡操作
    }

    public void addAfter(AVL_Node node) {
        /**该addAfter()函数作用：=----> 对新添加的节点的父节点进行判断，判断是否平衡/是否不平衡
         * 为什么需要遍历新添加的节点的所有父节点：=-----> 起初while循环是为了找到第一个不平衡节点的，让其平衡，【最终发现可以利用while来进行更新高度的操作】
         */
        while ((node = node.parent) != null) { //迭代求法：不需要递归求高度，just在迭代里面求得高度即可
            if (isBalanced(node)) {
                update_height(node);    //不断向上找父节点更新高度
            } else {
                release_balance(node);       //传进的是不平衡的 “节点（父节点）”
                break;
            }
        }
    }

    public void release_balance(AVL_Node grand) {
        /**
         * 传进的是最低的不平衡节点
         * 找到LL/LR/RR/RL
         * parent 和 child 都一定存在！
         */
        AVL_Node parent = grand.tallerChild();
        AVL_Node child = parent.tallerChild();
        if (parent.isParent_Left()) {
            if (child.isParent_Left()) {       //LL
                while_right(grand);
            } else {
                while_left(parent);
                while_right(grand);
            }
        } else if (parent.isParent_Right()) {
            if (child.isParent_Right()) {        //RR
                while_left(grand);
            } else {
                while_right(parent);
                while_left(grand);
            }
        }
    }

    //左旋转（RR）
    public void while_left(AVL_Node grand) {    //grand节点    【三者都需要更新父节点】
        AVL_Node parent = grand.right;       //parent节点
        AVL_Node child = parent.left;       //child节点
        //交换两个指针指向
        grand.right = child;
        parent.left = grand;

        //维护父节点1
        parent.parent = grand.parent;
        if (grand.isParent_Right()) {
            grand.parent.right = parent;           //可能grand.parent为null
        } else if (grand.isParent_Left()) {
            grand.parent.left = parent;
        } else {     //处理grand.parent为null情况，即grand为root
            root = parent;
            root.parent = null;
        }
        if (child != null) {
            child.parent = grand;
        }      //维护父节点2
        grand.parent = parent;                      //维护父节点3
        update_height(grand);
        update_height(parent);
    }

    //右旋转(LL)
    public void while_right(AVL_Node grand) {
        AVL_Node parent = grand.left;
        AVL_Node child = parent.right;          //可能为空null
        grand.left = child;
        parent.right = grand;

        parent.parent = grand.parent;
        if (grand.isParent_Left()) {
            grand.parent.left = parent;
        } else if (grand.isParent_Right()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }
        grand.parent = parent;
        if (child != null) {
            child.parent = grand;
        }
        /**
         * 先 “update” 更新低的节点的高度，再更新高节点的高度
         */
        update_height(grand);
        update_height(parent);
    }


    //判断平衡
    public boolean isBalanced(AVL_Node node) {
        return Math.abs(node.balanceFactor()) <= 1;
    }

    //更新高度
    public void update_height(AVL_Node node) {
        node.Update_height();
    }

}

class AVL_Node {
    public AVL_Node left;
    public AVL_Node right;
    //增加一个父节点用来记录
    public AVL_Node parent;
    public int element;
    public int height = 1;

    public AVL_Node(int element) {
        this.element = element;
    }

    public boolean has_twoChild() {
        return this.left != null && this.right != null;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    //更新该节点的高度  【每添加一个节点更新一个该节点的高度】
    public void Update_height() {
        int left_height = left == null ? 0 : left.height;
        int right_height = right == null ? 0 : right.height;
        height = 1 + Math.max(left_height, right_height);
    }

    //平衡因子 【用来维持绝对平衡】
    public int balanceFactor() {
        int left_height = left == null ? 0 : left.height;
        int right_height = right == null ? 0 : right.height;
        return left_height - right_height;
    }

    //返回子节点中最高的那个节点
    public AVL_Node tallerChild() {
        int left_height = left == null ? 0 : left.height;
        int right_height = right == null ? 0 : right.height;
        if (left_height > right_height) return left;
        if (left_height < right_height) return right;
        //左右节点高度一样高的情况的处理//同方向返回
        return this.isParent_Left() ? left : right;
    }


    //判断自己当前节点是父节点的那个子节点
    public boolean isParent_Left() {
        return this.parent != null && this.parent.left == this;
    }

    //判断自己当前节点是父节点的那个子节点
    public boolean isParent_Right() {
        return this.parent != null && this.parent.right == this;
    }

    @Override
    public String toString() {
        return "Node{" +
                "element=" + element +
                '}';
    }
}