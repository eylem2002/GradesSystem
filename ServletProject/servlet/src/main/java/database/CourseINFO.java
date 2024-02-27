
package database;

public class CourseINFO {
    private String name;
    private Double mark;

    public CourseINFO(String name, Double mark) {
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
        return "Mark in " + name + " = " + mark;
    }
}
