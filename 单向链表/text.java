package 单向链表;

public class text {
    public static void main(String[] args) {
        Phone p1 = new Phone(1);
        Phone p2 = new Phone(2);
        Phone p3 = new Phone(3);
        Phone p4 = new Phone(4);
        Phone p5 = new Phone(5);
        Phone p6 = new Phone(6);
        p1.adds(p2);
        p1.adds(p3);
        p1.adds(p4);
        p1.adds(p5);

        p1.adds(p6);
        p5.next=p1;

        Deal_phone deal_phone = new Deal_phone();
        boolean b = deal_phone.ensure_annulus(p1);
        System.out.println(b);
    }
}


class Deal_phone{
    public boolean ensure_annulus(Phone head){
        if(head==null||head.next==null)return false;
        Phone slow=head;
        Phone fast=head.next;
        while (fast.next!=null && fast!=null){
            slow=slow.next;
            //这里要确保fast.next不为空指针和fast，不然无法进行循环，会报空指针异常！！！
            fast=fast.next.next;
            if(slow==fast)return true;
        }
        return false;
    }

    public Phone reserve(Phone head){
        if(head==null || head.next==null)return head;
        Phone new_head=reserve(head.next);
        head.next.next=head;
        head.next=null;
        return new_head;
    }
}


class Phone{

    public Phone next;
    public int id;
    public Phone(int id){
        this.id=id;
    }
    public void adds(Phone phone){
        Phone temp=this;
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next=phone;
    }
    public void list(){
        if(this.next==null){
            System.out.println(this);
            return;
        }
        this.next.list();
        System.out.println(this);
    }


    @Override
    public String toString() {
        return "单向链表.Phone" +
                "id='" + id ;
    }
}
