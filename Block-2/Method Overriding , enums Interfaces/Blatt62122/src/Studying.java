public interface Studying {
    default int getStudentNumber(){
        return StudentOffice.getStudentNumber((Person)this);
    };

    default void  exmatriculate(){
        StudentOffice.exmatriculate((Person) this);
    };

    // ...
}
