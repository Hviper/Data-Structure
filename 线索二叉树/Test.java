package 线索二叉树;



public class Test {
    private HeroNode root;
    public static void main(String[] args) {
        HeroNode t1 = new HeroNode(1);
        HeroNode t2 = new HeroNode(2);
        HeroNode t3 = new HeroNode(3);
        HeroNode t4 = new HeroNode(4);
        HeroNode t5 = new HeroNode(7);
        HeroNode t6 = new HeroNode(5);
        HeroNode t7 = new HeroNode(8);
        HeroNode t8 = new HeroNode(42);
        HeroNode t9 = new HeroNode(421);

        t1.left=t2;
        t1.right=t3;

        t2.left=t5;
        t2.right=t6;
        t5.left=t7;
        t7.left=t8;
        t3.right=t9;
        t9.right=t4;
        Test test = new Test();
        test.root=t1;
        HeroNode by_element = test.find_By_element(t1,42);
        System.out.println(by_element.date);
    }
    public HeroNode find_By_element(HeroNode node,int date){
        //前面本身节点寻找,或查到最左边直到为null了，还没找到 【终止条件】
        if(node==null)return null;
        if(node.date == date)return node;

        //本身找不到，左右节点寻找
        if(node.left!=null){
            HeroNode by_element = find_By_element(node.left, date);
            if(by_element!=null)return by_element;
        }

        return find_By_element(node.right,date);
    }
//    public HeroNode remove(HeroNode root){
//        if(root==null)return null;
//        if()
//    }
}
class HeroNode{
    HeroNode left;
    HeroNode right;
    int date;
    /**
     * 规定 0表示正常，1：表示指向前驱节点
     */
    int leftType;
    int rightType;
    public HeroNode(int date){
        this.date=date;
    }
}
