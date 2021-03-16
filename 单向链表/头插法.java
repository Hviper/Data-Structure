package 单向链表;

public class 头插法 {
    public static void main(String[] args) {
        Body body1=new Body("kiwi");
        Body body2=new Body("java");
        Body body3=new Body("Tomcat");
        deal_Body deal_body = new deal_Body();
        deal_body.add_head(body1);
        deal_body.add_head(body2);
        deal_body.add_head(body3);
//        deal_body.add_foot(body1);
//        deal_body.add_foot(body2);
//        deal_body.add_foot(body3);
        deal_body.show();
    }
}



class deal_Body{
    //用于遍历找到该链表在内存中的位置！！务必
    Body head;
    //用于尾插法
    Body foot;


    //尾插法，定义一个头【找到该链表】，定义一个尾【始终保持为尾部】
    public void add_foot(Body body){
        if(head==null){
            foot=head=body;
        }else {
            foot.next=body;
            foot=foot.next;
        }
    }


    public void add_head(Body body){
        //1.head头节点变量指向为空
        if(head==null){
            head=body;
        }else {
            body.next=head;
            head=body;
        }
    }

    public void show(){
        Body temp=head;
        while (temp!=null){
            System.out.println(temp);
            temp=temp.next;
        }
    }

}

class Body{
    public String name;
    public Body next;
    public Body(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "单向链表.Body{" +
                "name='" + name + '\'' +
                '}';
    }
}
