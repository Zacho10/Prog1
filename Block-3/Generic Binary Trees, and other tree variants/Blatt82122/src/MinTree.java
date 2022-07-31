public class MinTree<T extends Number> {
    BTreeNode<T> root;

    public MinTree(){
        root = null;
    }

    /**
     * inserts the value such that the min-Heap property is satisfied
     * @param v
     */
    public void insert(T v){
        BTreeNode<T> newNode = new BTreeNode<T>();
        newNode.value = v;
        if (root == null){
            root = newNode;
        } else {
            insert(newNode,root);
        }
    }

    /**
     * inserts the value such that the min-Heap property is satisfied, below a non-null node
     * @param newNode the new node to add
     * @param anchor the root of the sub-tree
     */
    public void insert(BTreeNode<T> newNode, BTreeNode<T> anchor){
        if (newNode.value.doubleValue() >= anchor.value.doubleValue()){
            // I can further descend
            if (anchor.left == null){
                // links ist platz
                anchor.left = newNode;
            } else {
                if (anchor.right == null){
                    // rechts ist platz
                    anchor.right = newNode;
                } else {
                    // Wir müssen absteigen
                    assert(anchor.left != null && anchor.right != null);
                    // zufällig links oder rechts
                    if(0==(int)Math.round(2*Math.random())){
                        // rekursiv links
                        insert(newNode,anchor.left);
                    } else {
                        // rekursiv rechts
                        insert(newNode,anchor.right);
                    }
                }
            }
        } else {
            assert (newNode.value.doubleValue() < anchor.value.doubleValue());
            // we are low enough (and we only have used the value )

            // swap value of new node and exisiting node
            T anchorVal = anchor.value;
            anchor.value = newNode.value;
            newNode.value = anchorVal;
            // push down the (new) node with the old value
            insert(newNode, anchor);
        }
    }

    T takeMin(){ //
        if (root == null)
            return null;
        assert (root!=null);
        // Rückgabe ist einfach
        T res = root.value;
        // Nun aber noch aus dem Rest des Baumes einen "Ast" nach oben ziehen
        // und dann den root-knoten mit dem neuen Root-Knoten überschreiben.
        root = pullUpTo(root);
        return res;
    }

    private  boolean leq(BTreeNode<T> x, BTreeNode<T> y){
        if (y == null ){
            return (x != null && x.value != null);
        } else {
            return (x != null && x.value!= null &&
                    (x.value.doubleValue() <=
                            ((y==null || y.value == null)?x.value.doubleValue():y.value.doubleValue())));
        }
    }

    /**
     * pull up a branch of the non-empty tree at the node and
     * return the "root" of the branch with left and right refereces to
     * the unchanged subtree and the (new) root of the subtree
     * @param anchor
     * @return
     */
    private BTreeNode<T> pullUpTo(BTreeNode<T> anchor) {
        //
        if (anchor.left == null && anchor.right == null) {
            // nothing to do if the tree was only a single node, which thus disappears
            return null;
        } else {
            // NB leq = less or equal der Werte und Berücksichtigung der 'null'-Werte
            if (leq(anchor.left , anchor.right)){
                // linken Wert nach oben ziehen
                anchor.value = anchor.left.value;
                // und das linke Kind updaten, entsprechend dem Rekursiven Aufruf
                anchor.left = pullUpTo(anchor.left);
            } else {
                // sonst, den rechten Wert nach oben ziehen
                anchor.value = anchor.right.value;
                // und das rechte Kind updaten, entsprechend dem Rekursiven Aufruf
                anchor.right = pullUpTo(anchor.right);
            }
            return anchor;
        }
    }

    public static void main(String[] args) {
        MinTree<Integer> t = new MinTree<>();
        final int MAX = 15;

        System.out.print("Die Liste der Zahlen ist: ");
        for (int i = 0; i < MAX; i++) {
            int tmp = (int) Math.round(2*MAX * Math.random());
            System.out.print(tmp + (i <MAX-1 ? ", " : ".\n"));
            t.insert(tmp);
        }

        System.out.print("Die Sortierung ergibt:   ");
        for(int j = 0; j < MAX; j++){
            System.out.print(t.takeMin() + (j <MAX-1 ? ", " : ".\n"));
        }

    }
}
