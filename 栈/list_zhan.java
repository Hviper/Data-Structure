package 栈;

public class list_zhan {
    public static void main(String[] args) {
        Node n1 = new Node("kiwi");
        Node n2 = new Node("apache");
        Node n3 = new Node("linux");
        Node n4 = new Node("Python");
        deal_Node deal_node = new deal_Node();
        deal_node.push(n1);
        deal_node.push(n2);
        deal_node.push(n3);
        deal_node.push(n4);
        deal_node.show();
    }
}


class deal_Node{
    public Node head;
    public Node foot;

    //入栈
    public void push(Node node){
        if(head==null){
            head=node;

            foot=head;
        }else {
            foot.next=node;
            foot=node;
        }
    }
    //出栈


    public void show(){
        Node temp=head;
        System.out.println(head);
        while (temp.next!=null){
            System.out.println(temp.next);
            temp=temp.next;
        }
    }
}
class Node{
    public Node next;
    public String name;
    public Node(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }
}
