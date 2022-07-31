public class ObjectRingBuffer extends AbstractRingBuffer{

    protected Object[] buffer;
    public ObjectRingBuffer(int g){
        super(g);
        buffer = new Object [g];
    }
    @Override
    protected void writeToIndex(Object o, int index) {
        buffer[index] = o;
    }

    @Override
    protected Object readFromIndex(int index) {
        return buffer[index];
    }

    public static void main(String[] args) {
        final int SIZE = 6;
        final String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id dui non nisl blandit feugiat. Quisque vitae dapibus justo. Fusce interdum, enim vitae euismod hendrerit, velit metus molestie mauris, a finibus tellus ante sed ipsum. Donec lobortis eget nunc consequat laoreet. Mauris porta convallis mi, ut eleifend augue suscipit eget. Sed blandit, tellus at finibus egestas, lacus ipsum molestie elit, eget pulvinar turpis erat auctor felis. Mauris eget ultricies mi, lobortis ullamcorper ex. Donec congue accumsan metus vitae finibus. Pellentesque aliquet tellus in imperdiet laoreet. Maecenas vel nunc et odio vehicula tempus. ";
        ObjectRingBuffer b = new ObjectRingBuffer(SIZE);

        for (int j=0;j<20;j++) {
            for (int i = 0; i < SIZE; i++) {
                b.put(loremIpsum.charAt(i+j*SIZE));
            }

            for (int i = 0; i < SIZE; i++) {
                System.out.print(b.remove());
            }
        }

    }
}
