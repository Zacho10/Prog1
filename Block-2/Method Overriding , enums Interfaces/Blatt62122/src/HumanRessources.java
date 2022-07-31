import java.util.HashMap;

public class HumanRessources {

    static int nextID = 0;
    static HashMap<Person,Integer> lookup = new HashMap();

    static int getID(Person p){
        return  lookup.get(p);
    }

    static int employ(Person p){
        lookup.put(p, nextID);
        return nextID++;
    }

    static void fire(Person p){
        lookup.remove(p);
    }

}
