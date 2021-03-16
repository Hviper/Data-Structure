package 双向链表;

public class Double_Link {
    public static void main(String[] args) {
        Double_Node d1 = new Double_Node(1);
        Double_Node d2 = new Double_Node(2);
        Double_Node d3 = new Double_Node(3);
        Double_Node d4 = new Double_Node(4);
        Double_Node d5 = new Double_Node(5);
        Double_Node d6 = new Double_Node(6);
        Double_Node d7 = new Double_Node(7);
        Double_Node d8 = new Double_Node(8);
        Deal_DoubleNode deal = new Deal_DoubleNode();
        deal.add_node(d1);
        deal.add_node(d2);
        deal.add_node(d3);
        deal.add_node(d4);
        deal.add_node(d5);

        int size = deal.getSize();
        System.out.println(
                "size:"+size
        );


        Double_Node double_node2 = deal.get(5);
        System.out.println(double_node2);
        System.out.println("-----------------------");
        deal.insert(5,d6);
        deal.insert(6,d7);
        deal.insert(7,d8);
//        deal.insert(7,d6);
        deal.list(d1);
        int size2 = deal.getSize();
        System.out.println(
                "size:"+size2
        );

    }
}


//规定index为1开始
class Deal_DoubleNode{
    int size=0;
    Double_Node head=new Double_Node(0);
    Double_Node foot=new Double_Node(-1);

    public int getSize(){
        return size;
    }
    public void add_node(Double_Node node){
        if(head.next ==null){
            head.next=node;
            node.pre=null;
            node.next=null;
            foot.next=node;
            size++;
        }
        else {
            Double_Node temp=head;
            while (temp.next!=null){
                temp=temp.next;
            }
            temp.next=node;
            node.pre=temp;
            node.next=null;
            foot.next=node;
            size++;
        }
    }
    public void insert(int index,Double_Node node){
        Double_Node current_Node=get(index);
        if(index==size){
            current_Node.next=node;
            node.pre=current_Node;
            node.next=null;
            foot.next=node;
        }
        else {
            current_Node.pre.next=node;
            node.pre=current_Node.pre;
            node.next=current_Node;

            if(index==1){
                head.next=node;
            }
            //这里会出现空指针异常！因为假如current_Node为空指针，空指针.pre会报错！！【空指针没有方法】
            else current_Node.pre=node;
        }
        size++;
    }
    public void list(Double_Node head){
        if(head.next==null){
            System.out.println(head);
            return;}
        list(head.next);
        System.out.println(head);
    }

    public Double_Node get(int index){
        if(index<=0){
            throw new IndexOutOfBoundsException("索引请从一开始");}
        ensure_RangeCheck(index);
        if(index<=(size>>1)){
            Double_Node temp=head;
            for (int i = 0; i < index; i++) {
                temp=temp.next;
            }
            return temp;
        }
        else {
            Double_Node temp=foot.next;
            for(int i=size;i>index;i--){
                temp=temp.pre;
            }
            return temp;
        }
    }
    private void ensure_RangeCheck(int index){
        if(index<0 || index>size){
            throw new IndexOutOfBoundsException("链表越界，，，，【index="+index+"size="+size+"】");
        }
    }
}

class Double_Node{
    public Double_Node pre;
    public Double_Node next;
    public int id;
    public Double_Node(int id){
        this.id=id;
    }

    @Override
    public String toString() {
        return "Double_Node{" +
                "id=" + id +
                '}';
    }
}
