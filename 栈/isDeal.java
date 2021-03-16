package 栈;



import java.util.Stack;

public class isDeal {

    public static void main(String[] args) {

        isDeal isDeal = new isDeal();
        boolean valid = isDeal.isValid("[][][]()[()");
        System.out.println(valid);
    }


    public boolean isValid(String s){
        while (s.contains("()") || s.contains("{}") || s.contains("[]")){
            s=s.replace("()","");
            s=s.replace("[]","");
            s=s.replace("{}","");
        }
        return s.isEmpty();
    }

    public boolean is_String(String s){
        Double_Stack<Character> stack = new Double_Stack<>();
//        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '(' || aChar == '{' || aChar == '[') {
                stack.push(aChar);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    Character pop = stack.pop();
                    if (pop == '(') {
                        if (aChar == ')') {
                            continue;
                        }
                        else return false;
                    }
                    if (pop == '{') {
                        if (aChar == '}') {
                            continue;
                        }
                        else return false;
                    }
                    if (pop == '[') {
                        if (aChar == ']') {
                            continue;
                        }
                        else return false;
                    }
                    else return false;
                }
            }
        }
        return true;
    }
}

class Double_Stack<E>{
    private int size=0;
    private E[] elements;

    public Double_Stack(){
        elements= (E[])new Object[10];
    }
    public void push(E element){
        add(element);
    }
    private void add(E element){
        ensure_Capacity();
        elements[size++]=element;
    }
    private void ensure_Capacity(){
        if(size>(elements.length>>1)){
            E[] new_elements=(E[])new Object[elements.length+(elements.length>>1)];
            for (int i = 0; i < elements.length; i++) {
                new_elements[i]=elements[i];
            }
            elements=new_elements;
        }
    }
    public E pop(){
        if(size>0){
            size--;
            return elements[size];
        }
        throw new NullPointerException("指针一擦");
    }

    public E peek(){
        return elements[size];
    }
    public boolean isEmpty(){
        return size==0;
    }

}
