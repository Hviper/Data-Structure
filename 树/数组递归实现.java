package 树;

public class 数组递归实现 {
    public static void main(String[] args) {
        Student s1 = new Student("kiwi");
        Student s2 = new Student("Linux");
        Student s3 = new Student("Java");
        Student s4 = new Student("Tomcat");
        Student s5 = new Student("Apache");
        Student s0 = new Student("新节点");
        Deal_Student deal_student = new Deal_Student();
        Student head = deal_student.getHead();
        deal_student.add_Node(head,s1);
        deal_student.add_Node(head,s2);
        deal_student.add_Node(head,s3);
        deal_student.add_Node(head,s4);
        deal_student.add_Node(head,s5);
        deal_student.show_List(head);
        System.out.println("--------------------------");
        deal_student.reserve(head,s0);
        deal_student.show_List(s0);


    }
}


class Deal_Student{
    private final Student head=new Student("head头节点");

    public Student getHead(){
        return head;
    }

    public void add_Node(Student head,Student node){
        if(head!=null){
            head.add_next(node);
        }

    }
    public void remove_last(Student head){
        Student temp=head;
        while (true){
            if(temp.next!=null){
                if(temp.next.next==null){
                    break;
                }
                else temp=temp.next;
            }
        }
        temp.next=null;
    }

    public void reserve(Student head,Student new_head){
        Student temp=head;
        while (temp.next!=null){
            temp=temp.next;
        }
        add_Node(new_head,temp);
        remove_last(head);
        if(head.next!=null){
            reserve(head,new_head);    //将删去最后一节节点的head再此递归调用reserve函数！！
        }
    }

    public void show_List(Student head){
        head.return_Next();
    }

}

class Student{
    public Student next;
    public String name;
    public Student (String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }


    //能通过当前节点来实现链表的遍历，查询等操作，就不用管理链表的类来操作！！！
    //欲实现：通过当前节点。实现接上新的节点,而对于管理Student的节点类而言，需要头节点来遍历整个链表，尽管可以,但是灵活度不够，这里希望通过单个节点来遍历后面的全部节点
    public void add_next(Student node){
        if(this.next==null){
            this.next=node;
            return;
        }
        Student temp=this;
        //因为连接节点功能一定需要当当前节点指向的next为空才能将新的节点接上该节点的next域
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next=node;
    }

    //通过本身节点，遍历后面的全部节点
    public void return_Next(){
        System.out.println(this);
        if(this.next!=null){
            this.next.return_Next();
        }
    }
}
