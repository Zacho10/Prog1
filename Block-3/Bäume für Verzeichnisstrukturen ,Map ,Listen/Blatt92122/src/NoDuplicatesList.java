
import java.util.HashMap;


public class NoDuplicatesList<T> {
    T head;
    HashMap<T,T> next = new HashMap<>();

    public boolean contains(T element){
        if (head == null) {
            return false;
        } else {
            if (element.equals(head)){
                return true;
            } else {
                return (next.values()).contains(element);
            }
        }
    }

    public T get(int index){
        T tmp = head;
        for(int i = 0; i < index && tmp != null; i++){
            tmp = next.get(tmp);
        }
        return tmp;
    }

    public void insertAtHead(T x){
        if (head != null) {
            next.put(x, head);
        }
        head = x;
    }

    public void insertAtPosition(T x, int pos){
        if (this.contains(x))
            return;
        if (pos == 0) {
            if (head != null) {
                next.put(x, head);
            }
            head = x;
            return;
        }

        T tmp = head;
        for(int i = 0; i < pos-1 && tmp != null; i++){
            tmp = next.get(tmp);
        }
        if (tmp == null ){
            return;
        } else {
            T nextEl = next.get(tmp);
            next.put(tmp, x);
            if (nextEl != null) {
                next.put(x, nextEl);
            }
        }
    }


    public static void main(String[] args) {
        NoDuplicatesList<Integer> list = new NoDuplicatesList<>();
        for(int i = 0; i < 10; i++){
            list.insertAtHead(i);
        }
        for(int i = 10; i < 20; i++){
            list.insertAtPosition(i, 14-i);
        }
        for(int i = 0; i < 20; i++){
            System.out.println(list.get(i));
        }
    }
}
