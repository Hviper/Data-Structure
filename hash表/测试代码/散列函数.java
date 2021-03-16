package hash表.测试代码;

public class 散列函数 {
    public static void main(String[] args) {
        String string = "jack";
        char[] chars = string.toCharArray();
        int hashcode=0;
        int hashcode2=0;
        for (char aChar : chars) {
            System.out.println(aChar);
            //二进制的数左移变大
            hashcode=hashcode*31+aChar;
            hashcode2 = (hashcode2<<5)-hashcode2+aChar;
        }
        System.out.println(hashcode);
        System.out.println(hashcode2);
        int i = Float.floatToIntBits(8848480.18412f);
        System.out.println("------------->"+i);
        System.out.println(Integer.toBinaryString(i).length());
        System.out.println(Integer.toBinaryString(i));
        int i1 = Float.hashCode(10.12f);
        System.out.println("--------->"+i1);
        System.out.println();
    }
}
