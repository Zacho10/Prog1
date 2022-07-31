public class Person {
    String name;

    String highestScientificQualification;


    Degree[] degrees = new Degree[0];

    Diploma[] diplomas = new Diploma[0];

    public Person(String name, String highestScientificQualification, Degree[] degrees, Diploma[] diplomas) {
        this.name = name;
        this.highestScientificQualification = highestScientificQualification;
        this.degrees = degrees;
        this.diplomas = diplomas;
    }



    public static void main(String[] args) {
        Person p = new Person("M.M.", "None", new Degree[0], new Diploma[0]);
        Student s = new Student("N.A.", "None", new Degree[0], new Diploma[0]);
        Person q = s.exmatriculate();
        s = null;

    }
}

class Degree {
    String name;
}
class Diploma {
    String name;
}

class Student extends Person{
    static int nextStudentNumber = 0;

    int studentNumber = nextStudentNumber++;

    public Student(String name, String highestScientificQualification, Degree[] degrees, Diploma[] diplomas) {
        super(name, highestScientificQualification, degrees, diplomas);
    }

    void obtainDegree(Degree d){
        Degree[] newDegrees = new Degree[degrees.length+1];
        newDegrees[degrees.length] = d;
        for(int i=0; i<degrees.length; i++){
            newDegrees[i] = degrees[i];
        }
    }

    Person exmatriculate(){
        return new Person(name, highestScientificQualification, degrees, diplomas);
    }
}

class BachelorStudent extends Student{
    public BachelorStudent(String name, String highestScientificQualification, Degree[] degrees, Diploma[] diplomas) {
        super(name, highestScientificQualification, degrees, diplomas);
    }
}

class MasterStudent extends Student{
    public MasterStudent(String name, String highestScientificQualification, Degree[] degrees, Diploma[] diplomas) {
        super(name, highestScientificQualification, degrees, diplomas);
    }
}

class Guest extends Person{
    public Guest(String name, String highestScientificQualification, Degree[] degrees, Diploma[] diplomas) {
        super(name, highestScientificQualification, degrees, diplomas);
    }
}

class Employee extends Person{
    static int nextID = 0;

    int employeeNumber = nextID++;

    public Employee(String name, String highestScientificQualification, Degree[] degrees, Diploma[] diplomas) {
        super(name, highestScientificQualification, degrees, diplomas);
    }
}

class Scholar extends Employee {
    String faculty;

    public Scholar(String name, String highestScientificQualification, Degree[] degrees, Diploma[] diplomas) {
        super(name, highestScientificQualification, degrees, diplomas);
    }
}

class AdministrativeStaff extends Employee{
    String section;

    public AdministrativeStaff(String name, String highestScientificQualification, Degree[] degrees, Diploma[] diplomas) {
        super(name, highestScientificQualification, degrees, diplomas);
    }
}





