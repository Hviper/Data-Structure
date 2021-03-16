package 二叉树.设计层面test;



public class 设计访问的元素的外界来实现 {
    public static void main(String[] args) {
        deal_Node list = new deal_Node();
        for (int i = 0; i < 10; i++) {
            list.add(new Node(i));
        }

        /**【设计模式相关】
         * 因为节点对外不可见，需要通过接口来实现外界对内部节点的访问【类似数据都是通过接口进行传输的原则】
         * 这里需要实现该 show_list（）方法,需要实现该接口的visit方法，
         * 因为内部会通过接口将数据传给外界供外界进行访问和实现对节点的处理
         */
        list.show_list(new deal_Node.Visit() {
            @Override
            public void visit(Node node) {
                System.out.println(node.hashCode());
            }
        });
    }
}
class deal_Node{
    private Node root;
    public void add(Node node){
        if(root ==null){
            root = node;
            return;
        }
        Node temp =root;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }
    public void show_list(Visit v){
        Node temp =root;

        /**
         *代码执行顺序的问题，导致使得内部传入的参数先于 VisitL(int element) 代码逻辑
         * =数据=先于=处理逻辑代码的时间，执行处理逻辑时，数据已经传入到执行代码的参数中供外界调用
         */
        while (temp !=null ){
            v.visit(temp);
            temp = temp.next;
        }
    }

    /**
     * 设计 Visit 接口实现外部访问节点，内部通过传参传给外界，让外界来实现节点的处理
     */
    public interface Visit{
        void visit(Node node);
    }
}
class Node{
    public Node next;
    public int element;
    public Node(int element){
        this.element = element;
    }

}
