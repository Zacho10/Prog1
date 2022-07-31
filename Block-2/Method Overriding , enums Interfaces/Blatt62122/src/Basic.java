
public class Basic {
    public Integer a;

    @Override
    public String toString() {
        return "Basic{" +
                "a=" + a +
                '}';
    }
}
class MultiplyAndSum extends Basic {
    public void multiplyWith(Integer b){
        a = a * b;
    }
    public void sumyWith(Integer b){
        a = a + b;
    }

    public static void main(String[] args) {
        MultiplyAndSum x = new MultiplyAndSum();
        x.a = 1;
        x.multiplyWith(2);
        MultiplyAndSum y = new MultiplyAndSum();
        y.a = 1;
        y.sumyWith(3);
        System.out.println(x);
        System.out.println(y);
    }
}

class TestIt extends MultiplyAndSum implements MultiplicableByDefault{
    @Override
    public void setA(Integer a) {
        this.a = a;
        System.out.print("Meep ");
    }

    @Override
    public Integer getA() {
        return this.a;
    }



    public static void main(String[] args) {
        TestIt z = (new TestIt());
        z.setA(100);
        z.defaultMultiplyWith(10);
    }
}
