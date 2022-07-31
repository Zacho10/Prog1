import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class BTreeNode<T extends Number> {
    /** value of this node */
    public T value;
    /** the left child node */
    public BTreeNode<T> left;
    /** the right child node */
    public BTreeNode<T> right;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BTreeNode that = (BTreeNode) o;
        if (left == null && that.left != null || left != null && that.left == null ||
                right == null && that.right != null || right != null && that.right == null
        ) {
            return false;
        } else {
            return ( left == null ? true : left.equals(that.left)) &&
                    ( right == null ? true : right.equals(that.right));
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(value, Objects.hash(left), Objects.hash(right));
    }

    public int height(){
        int l = (left == null)? -1 : left.height();
        int r = (right == null)? -1 : right.height();
        return 1 + ((l > r) ? l : r);
    }

    @Override
    public String toString() {
        return "«"+ this.value + "»";
    }

    private List toStrinbufferList(){
        List res = new LinkedList();
        int h = this.height();
        if (h == 0){

        }
        return null;
    }

    private static int widthOfHeight(int i){
        switch (i){
            case -1:
                return 0;
            case 0:
                return 1;
            default:
                return 2*widthOfHeight(i-1) + 1;
        }
    }


}
