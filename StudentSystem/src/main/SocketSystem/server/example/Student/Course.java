package main.SocketSystem.server.example.Student;

public class Course implements java.io.Serializable {
    private String name;
    private Double mark;

    public Course(String name, Double mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "\nMark in " + name + " --> " + mark+"\n";
    }
}
