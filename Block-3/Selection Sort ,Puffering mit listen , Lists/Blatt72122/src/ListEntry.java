import java.util.List;

public class ListEntry {

    /**value of this list entry*/
    public Object value;

    /**reference to the successor of this list entry*/
    public ListEntry next;
    // 	add "ListEntry prev;" for doubly linked list

    /**
     *
     * @param anchor
     * @return The last Element of the list to be constructed
     */
    // we can think of the iterated list structures as trees where all leaves are left children
    // and only leaves carry values
    // if the value is proper, then the left child is a leaf with a certain value
    // the right child cannot be a leaf (by the structural condition) and thus is an inner node.
    // for the recursion, we will always take the list of the left tree (which might be a single item)
    // so, for inner nodes, we first construct the list for the left node (or leaf),
    // then the list for the right descendent
    // and finally "stitch together" the two,
    // redirecting the last element of the "left" list---the anchor
    // pointing it to the head of the right list
    ListEntry flatten(ListEntry anchor){
        if (this.value == null) {
            // nothing here
            anchor.next = null;
            return anchor;
        }
        System.out.println("node with val"+this.value+"is"+this.value.getClass()+"is not ? ListEntry"+(this.value.getClass() != ListEntry.class));
        if (this.value.getClass() != ListEntry.class){
            // put current "proper" value
            anchor.next = this;
            // continue with the sequel (next element might branch already)
            return this;
        } else {
            // this is a branching point
            ListEntry endOfLeftList = ((ListEntry) this.value).flatten(anchor);
            if (this.next != null){
                return next.flatten(endOfLeftList);
            } else {
                return this;
            }
        }
    }



}
