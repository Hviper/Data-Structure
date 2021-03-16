package 单向环形链表;

public class text01 {
    public static void main(String[] args) {
        Child c1 = new Child("kiwi", 18);
        Child c2 = new Child("tomcat", 18);
        Child c3 = new Child("apple", 18);
        Deal_Child deal_child = new Deal_Child();
        deal_child.add(c1);
        deal_child.add(c2);
        deal_child.add(c3);
        deal_child.show();
        System.out.println("--------------------------------");
        System.out.println(c1.next.next);
        System.out.println(c3.next);
        System.out.println(c3.next.next.next);
    }
}

class Deal_Child{
    public Child head=new Child();
    public Child foot=new Child();
    public void add(Child child){
        Child temp=head;
        if(head.next==null){
            foot.next=head;
            head.next=foot;
            child.next=head.next;
            head.next=child;
        }
        else {
            while (true){
                if(temp.next==foot){break;}
                else {
                    temp=temp.next;
                }
            }
            child.next=foot;
            temp.next=child;
        }

    }
    public void show(){
        Child temp=head;
        while (temp.next!=foot){
            System.out.println(temp.next);
            temp=temp.next;
        }
    }
}
class Child{
    public Child next;
    public String name;
    public int id;

    public Child(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public Child(){}
    @Override
    public String toString() {
        return "Child{ name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
