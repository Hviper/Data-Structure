package 单向链表;

public class text02 {
    public static void main(String[] args) {
        db db1=new db("kiwi");
        db db2=new db("show_database");
        db db3=new db("apple");
        deal_db deal_db = new deal_db();
//        单向链表.deal_db.add(db1);
//        单向链表.deal_db.add(db2);
//        单向链表.deal_db.add(db3);
//        单向链表.deal_db.show();
//        System.out.println("================");



//        单向链表.deal_db.new_add(db1);
//        单向链表.deal_db.new_add(db2);
//        单向链表.deal_db.new_add(db3);

        deal_db.new_addAll(db1);
        deal_db.new_addAll(db2);
        deal_db.new_addAll(db3);
        deal_db.new_showAll();
    }
}

//定义最后的节点foot，只需要将需要插入的元素和foot进行交换和指向即可
class deal_db{
//也是属于变量！！！ 【全局变量，地址指向会不断发生改变，因此可以在函数中使用局部变量克隆该全局变量达到利用该局部变量来实现遍历而不改变全局变量head的值】
    private db head=new db(); //保证头节点指向地址唯一！！
    private db foot;


    public void new_addAll(db d){
        if(head.next==null){
            head.next=d;
            foot=head;
        }else {
            d.next=null;
            foot.next=d;
            foot=d;
        }
    }
    public void new_showAll(){
        while (true){
            if(head.next==null){
                break;
            }else {
                System.out.println(head.next);
                head=head.next;
            }
        }
    }




    public void add(db s){
        if(head==null){
            head=s;
            head.next=null;
            foot=head;
        }else {
            s.next=null;
            foot.next=s;
            foot=s;
        }
    }


    public void new_add(db d){
        final db l = foot;
        d.next=null;
        foot = d;    //修改全局foot【必须要改变，不然链表不会在尾部添加】，直接使得插入的数据变成链表尾部foot【通过该函数已经修改全局变量】

        //核心代码
        if (l == null)
            head = d;
        else
            l.next = d;
    }

    public void show(){
        System.out.println("这是头："+head);
        db temp=head;
        while (temp.next!=null){
            System.out.println(temp.next);
            temp=temp.next;
        }
    }

}
class db{
    public String name;
    public db next;

    public db(String name) {
        this.name = name;
    }

    public db() {
    }

    @Override
    public String toString() {
        return "单向链表.db{" +
                "name='" + name + '\'';
    }
}
