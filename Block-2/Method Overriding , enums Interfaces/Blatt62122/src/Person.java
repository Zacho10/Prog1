import java.util.Arrays;
import java.util.Objects;

public class Person {
    String lastName;

    String firstName;

    String highestScientificQualification;

    Integer timeOfBirthInUnixTime;

    String placeOfBirth;

    Degree[] degrees = new Degree[0];

    Diploma[] diplomas = new Diploma[0];

    public Person(String name, String highestScientificQualification, Degree[] degrees, Diploma[] diplomas) {
        this.lastName = name;
        this.highestScientificQualification = highestScientificQualification;
        this.degrees = degrees;
        this.diplomas = diplomas;
    }

    public Person(String lastName, String firstName, String highestScientificQualification, Integer timeOfBirthInUnixTime, String placeOfBirth, Degree[] degrees, Diploma[] diplomas) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.highestScientificQualification = highestScientificQualification;
        this.timeOfBirthInUnixTime = timeOfBirthInUnixTime;
        this.placeOfBirth = placeOfBirth;
        this.degrees = degrees;
        this.diplomas = diplomas;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", highestScientificQualification='" + highestScientificQualification + '\'' +
                ", timeOfBirthInUnixTime=" + timeOfBirthInUnixTime +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", degrees=" + Arrays.toString(degrees) +
                ", diplomas=" + Arrays.toString(diplomas) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(lastName, person.lastName) && Objects.equals(firstName, person.firstName) && Objects.equals(highestScientificQualification, person.highestScientificQualification) && Objects.equals(timeOfBirthInUnixTime, person.timeOfBirthInUnixTime) && Objects.equals(placeOfBirth, person.placeOfBirth) && Arrays.equals(degrees, person.degrees) && Arrays.equals(diplomas, person.diplomas);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(lastName, firstName, highestScientificQualification, timeOfBirthInUnixTime, placeOfBirth);
        result = 31 * result + Arrays.hashCode(degrees);
        result = 31 * result + Arrays.hashCode(diplomas);
        return result;
    }

    public static void main(String[] args) {
        Person p = new Person("Strong", "Andrea", "None", 1638534741, "Waialua", new Degree[0], new Diploma[0]);
        Person q = new Person("Strong", "Andrea", "None", 1638534741, "Waialua", new Degree[0], new Diploma[0]);

        System.out.println(p);

        System.out.println("It is "+p.equals(q)+" that "+p+" equals " + q + ".");


    }
}

class Degree {
    String name;
}
class Diploma {
    String name;
}



class Employee extends Person implements Employed{

    public Employee(String name, String highestScientificQualification, Degree[] degrees, Diploma[] diplomas) {
        super(name, highestScientificQualification, degrees, diplomas);

        HumanRessources.employ(this);
    }


}

class Academic extends Employee {
    String group;

    public Academic(String name, String highestScientificQualification, Degree[] degrees, Diploma[] diplomas) {
        super(name, highestScientificQualification, degrees, diplomas);
    }
}

class Tutor extends Academic implements Studying {
    public Tutor(String name, String highestScientificQualification, Degree[] degrees, Diploma[] diplomas) {
        super(name, highestScientificQualification, degrees, diplomas);
    }
}



class AdministrativeStaff extends Employee{
    String section;

    public AdministrativeStaff(String name, String highestScientificQualification, Degree[] degrees, Diploma[] diplomas) {
        super(name, highestScientificQualification, degrees, diplomas);
    }
}





