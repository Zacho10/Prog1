public interface Employed {
    default int getID(){
        return HumanRessources.getID((Person) this);
    };
    default void quit(){
        HumanRessources.fire((Person)this);
    };
    // ...

}
