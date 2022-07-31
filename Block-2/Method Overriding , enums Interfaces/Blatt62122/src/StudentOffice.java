import java.util.HashMap;

public class StudentOffice {
    static int nextID = 0;
    static HashMap<Person,Integer> lookup = new HashMap();
    static HashMap<Person,String> degrees = new HashMap();

    static int getStudentNumber(Person p){
        return  lookup.get(p);
    }

    static int immatriculate(Person p){
        lookup.put(p, nextID);
        return nextID++;
    }

    static void exmatriculate(Person p){
        lookup.remove(p);
    }

    // ...
}
