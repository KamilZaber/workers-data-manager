public class Worker {
    private int ID;
    private String name;
    private String surname;
    private String job;
    private float salary;

    public Worker(int id) {
        this.ID = id;
    }

    public Worker(int id, String name, String surname, String job, float salary) {
        this.ID = id;
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.salary = salary;
    }

    public void displayData() {
        System.out.print("(" + ID + ") " + name + " " + surname + "; " + job + "; " + salary + "zl\n");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getID() {
        return ID;
    }
}
