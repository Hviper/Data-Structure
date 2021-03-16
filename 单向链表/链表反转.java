package 单向链表;

public class 链表反转 {
    public static void main(String[] args) {
        Node n1 = new Node("kiwi");
        Node n2 = new Node("apple");
        Node n3 = new Node("Tomcat");
        Node n4 = new Node("Apache");
        Deal_Node deal_node = new Deal_Node();
        deal_node.add(n1);
        deal_node.add(n2);
        deal_node.add(n3);
        deal_node.add(n4);
        deal_node.show();
        System.out.println("---------------反转后----------------");
        Node new_head=deal_node.R_reserve(n1);      //返回新的节点
        deal_node.printf(new_head);


        System.out.println("-----------------------");

    }
}


class Deal_Node{
    private  Node head=new Node("");
    public int size;

    //普通反转
    private Node Reverses(Node head){
        // 递归到最后一个节点，返回新的新的头结点
        if (head.next == null) {
            return head;
        }
        Node newHead = Reverses(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    //递归反转
    public Node R_reserve(Node head){
        if(head==null||head.next==null){
            return head;
        }
        Node new_head=R_reserve(head.next);
        head.next.next=head;
        head.next=null;
        return new_head;
    }

    //递归打印
    public void printf(Node head){
        if(head==null){return;}

        printf(head.next);
        System.out.println(head);
    }

    public void add(Node node){
        if(head.next==null){
            head.next=node;
        }else {
            Node temp=head;
            while (temp.next!=null){
                temp=temp.next;
            }
            temp.next=node;
        }
        size++;
    }

    public void remove(Node node){
        Node temp=head;
        while (true){
            if(temp.next.name.equals(node.name)){
                break;
            }else {
                temp=temp.next;
            }
        }
        temp.next=temp.next.next;
    }

    public void show(){
        if(head.next==null){
            return;
        }
        Node temp=head;
        while (temp.next!=null){
            System.out.println(temp.next);
            temp=temp.next;
        }
    }






    public int getSize(){
        return size;
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