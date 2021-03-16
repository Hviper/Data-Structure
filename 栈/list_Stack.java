package 栈;

public class list_Stack {
    public static void main(String[] args) {
        list_Node l1 = new list_Node("kiwi");
        list_Node l2 = new list_Node("apache");
        list_Node l3 = new list_Node("Tomcat");
        list_Node l4 = new list_Node("selves");
        deal_list_Node deal_list_node = new deal_list_Node();
        deal_list_node.push(l1);
        deal_list_node.push(l2);
        deal_list_node.push(l3);
        deal_list_node.push(l4);
        System.out.println(deal_list_node.pop());
        System.out.println(deal_list_node.pop());
        System.out.println(deal_list_node.pop());
        System.out.println(deal_list_node.pop());
    }
}


class deal_list_Node{
    //压栈
    private list_Node head=new list_Node("");
    private list_Node foot;
    //
    private int count;

    public void push(list_Node node){
        if(head.next==null){
            head.next=node;
            foot=node;
            count++;
        }
        else {
            foot.next=node;
            foot=node;
            count++;
        }
    }
    //出栈：pop，从栈底pop出！！！，
    // 用count栈针记录需要遍历多少次才到栈针，每pop出一个数据，count--；每push一个数据，count++
    public list_Node pop(){
        list_Node temp=head;
        //用count计数，来控制遍历栈中的个数
        for (int i = 0; i < count; i++) {
            temp=temp.next;
        }
        count--;
        return temp;
    }
}

class list_Node{
    public list_Node next;
    public list_Node pre;
    public String name;
    public list_Node(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "list_Node{" +
                "name='" + name + '\'' +
                '}';
    }
}