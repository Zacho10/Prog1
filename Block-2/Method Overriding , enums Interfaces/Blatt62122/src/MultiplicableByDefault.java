public interface MultiplicableByDefault extends Multiplicable{
    public void setA(Integer a);
    public Integer getA();
    default public void defaultMultiplyWith(Integer a){
        setA(getA() * a);
    }
}
