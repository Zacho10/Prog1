public class IntegerByEnum {

    Integer[] array = null;
    Class enumClass;

    IntegerByEnum(){
        array=null;
    }

    public IntegerByEnum(Class c){
        enumClass = c;
        Enum[] e = (Enum[])c.getEnumConstants();
        array = new Integer[e.length];
    }

    public Integer get(Enum k){
        return array[k.ordinal()];
    }

    void put(Enum k, Integer i){
        array[k.ordinal()] = i;
    }

    public String toString(){
        String res = "";
        for(Enum e: (Enum[])enumClass.getEnumConstants()){
            res = res + e + ":" + get(e) + "\n";
        }
        return res;
    }
    IntegerByEnum(Enum[] vals){
        for( Enum o : vals){
            System.out.println(o.ordinal());
        }
    }

    public static void main(String[] args) {
        IntegerByEnum x = new IntegerByEnum(BeliebigeEnum.class);

        for (Enum e : BeliebigeEnum.class.getEnumConstants()){
            x.put(e,e.ordinal());
        }

        for (Enum e : BeliebigeEnum.class.getEnumConstants()){
            System.out.println(e + ":" + x.get(e));
        }

    }

}

class TestTest extends IntegerByEnum {
    int foo = 42;

    void printSuperclas(){
        System.out.println("Superclass of "+getClass() + " is " + super.getClass().getSuperclass());
    }
}

enum BeliebigeEnum {
    KEINS,
    DREI,
    BADMINTON;

    void print(){
        System.out.println(getClass().getSuperclass());
    }
}
