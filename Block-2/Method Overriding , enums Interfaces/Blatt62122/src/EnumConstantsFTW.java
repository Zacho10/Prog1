public enum EnumConstantsFTW {
    A,B,C;
    static Object[] test(Class c){
        return c.getEnumConstants();
    }
    public static void main(String[] args) {
        System.out.println(A.getClass() + " hat als Superklasse die Klasse "
                + A.getClass().getSuperclass());

        System.out.print(A.getDeclaringClass() + " hat folgende Konstanten: ");
        for(Object e : test(EnumConstantsFTW.class)){
            System.out.print(e +", ");
        }
        System.out.println("\b\b.");
        EnumConstantsFTW.class.getEnumConstants();
    }
}
