package Tire数据结构;


import java.util.HashMap;

public class text {
    public static void main(String[] args) {


        Tire<Integer> integerTire = new Tire<Integer>();
        integerTire.add("kiwi",1);
        integerTire.add("good",2);
        integerTire.add("apache",3);
        integerTire.add("java",4);
        integerTire.add("god",5);
        Integer god1 = integerTire.add("good", 100);
        System.out.println("被替换的字符对应的value为："+god1);
        int size = integerTire.size();
        System.out.println("总个数为：--->   "+size);
        Integer god = integerTire.get("kiwi");
        System.out.println(god);

    }
}
