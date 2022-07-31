import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class DirectoryTree {
    Directory root = new Directory(null,SEPARATOR);

    Directory workingDirectory = root;

    static final String SEPARATOR = "/";

    public boolean chooseSubDirectory(String name){

        int index = workingDirectory.contents.indexOf(new Node(workingDirectory, name));
        if (index == -1){
            return false;
        } else {
            workingDirectory = (Directory) (workingDirectory.contents).get(index);
            return true;
        }
    }

    public void createFile(String name){
        workingDirectory.contents.add(new File(workingDirectory,name));
    }

    public void makeDirectory(String name){
        workingDirectory.contents.add(new Directory(workingDirectory,name));
    }

    @Override
    public String toString() {
        String res = SEPARATOR + "\n";
        for(Node n : root.contents){
            res = res + n.toString(1);
        }
        return res;
    }

    public void goUp(){
        if (workingDirectory.parent != null){
            workingDirectory = (Directory) workingDirectory.parent;
        }

    }

    public static void main(String[] args) {
        DirectoryTree x = new DirectoryTree();
        x.createFile("a");
        x.makeDirectory("dirOne");
        x.chooseSubDirectory("dirOne");
        x.createFile("b");
        x.goUp();
        x.createFile("c");
        System.out.println(x);
    }

}

class Node {
    Node parent;
    String name;

    Node(){
        super();
    }

    Node(Node p, String n){
        super();
        parent = p;
        name = n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ! (o instanceof Node))
            return false;
        Node node = (Node) o;
        return Objects.equals(parent, node.parent) && Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, name);
    }

    String toString(int depth){
        if (depth < 1){
            return "ERROR!";
        }
        String res = "|";
        for (int i = 1; i < depth; i++){
            res = res + " |";
        }
        res = res + "-" + name + "\n";
        return res;
    }

}

class File extends Node{
    StringBuffer contents = new StringBuffer();

    public File(Directory workingDirectory, String name) {
        super(workingDirectory,name);
        contents = new StringBuffer();
    }


}

class Directory extends Node{
    List<Node> contents = new LinkedList<>();

    public Directory(Directory workingDirectory, String name) {
        super(workingDirectory,name);
        contents = new LinkedList<>();
    }

    String toString(int depth){
        String res = super.toString(depth);
        for (Node n : contents){
            res = res + n.toString(depth+1);
        }
        return res;
    }
}

