import java.util.*;

public class BTree<T extends Number> {
    /**
     * root element of the tree
     */
    private BTreeNode<T> root;

    public void insert(T value) {
        if (root == null) {
            root = new BTreeNode();
            root.value = value;
        } else
            insert(value, root);
    }

    private void insert(T value, BTreeNode currentNode) {
        if (value.doubleValue() < currentNode.value.doubleValue()) {
            if (currentNode.left == null) {
                currentNode.left = new BTreeNode();
                currentNode.left.value = value;
            } else {
                insert(value, currentNode.left);
            }
        } else if (value.doubleValue() > currentNode.value.doubleValue()) {
            if (currentNode.right == null) {
                currentNode.right = new BTreeNode();
                currentNode.right.value = value;
            } else {
                insert(value, currentNode.right);
            }
        }
    }

    private int height(){
        return (root == null) ? -1 : root.height();
    }



    public BTreeNode findNode(int value) {
        BTreeNode res = root;

        return res;
    }

    public Set<List<BTreeNode<T>>> allPaths(){
        return allPaths(root);
    }

    private Set<List<BTreeNode<T>>> allPaths(BTreeNode anchor){
        Set<List<BTreeNode<T>>> res = new HashSet<>();
        if (anchor == null){
            res.add(new LinkedList<>());
            return res;
        }
        assert(anchor!=null);
        if (anchor.left == null && anchor.right==null){
            // we have arrived at a leaf
            // there is a single path from a leaf to a leaf
            LinkedList<BTreeNode<T>> z = new LinkedList<>();
            z.add(0,anchor);
            res.add(z);
            return res;
        }
        assert(anchor.left != null || anchor.right!=null);

        if (anchor.left != null){
            Set<List<BTreeNode<T>>> l = allPaths(anchor.left);
            for (List<BTreeNode<T>> x : l) {
                x.add(0, anchor);
                res.add(x);
            }

        }
        if (anchor.right!= null){
            Set<List<BTreeNode<T>>> r = allPaths(anchor.right);
            for (List<BTreeNode<T>> x : r) {
                x.add(0, anchor);
                res.add(x);
            }

        }

        return res;


    }

    @Override
    public String toString() {
        return this.toString(root);
    }

    private String toString(BTreeNode<T> anchor){
        if (anchor == null ){
            return "";
        } else {
            return "[" + this.toString(anchor.left) + "(" + anchor.value + ")" + this.toString(anchor.right) + "]";
        }
    }

//    private int computeWidth(List<StringBuffer> lst){
//        int max = 0;
//        for(StringBuffer s : lst){
//            max = (s.length()>max)? s.length() : max;
//        }
//        return max;
//    }
//    private List<StringBuffer> fancyToString(BTreeNode<T> anchor){
//        List<StringBuffer> res = new LinkedList<>();
//        if (anchor == null) {
//            StringBuffer empty = new StringBuffer("");
//            return res;
//        }
//        if (anchor != null) {
//            int len = (anchor.toString()).length();
//
//            List<StringBuffer> l = fancyToString(anchor.left);
//            List<StringBuffer> r = fancyToString(anchor.right);
//
//            int lw = computeWidth(l);
//            int rw = computeWidth(r);
//
//            if (l.size() > r.size()){
//
//            }
//        }
//    }

    public static void main(String[] args) {
        BTree<Integer> t = new BTree<>();
        BTree<Double> td = new BTree<>();
        BTree<Float> tf = new BTree<>();
        for (int i = 0; i < 12; i++) {
            t.insert((int) Math.round(100 * Math.random()));
            td.insert(100 * Math.random());
            tf.insert((float) (100 * Math.random()));
        }

        System.out.println("The tree is: "+ t);
        Set<List<BTreeNode<Integer>>> test = t.allPaths();
        System.out.print("Pfade"+test);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BTree binTree = (BTree) o;
        if (root == null && binTree.root==null){
            return true;
        } else {
            if (root != null && binTree.root!= null){
                return root.equals(binTree.root);
            } else {
                return false;
            }
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

}