import java.util.List;

public class TestRead {
    public static void main(String args[]) {
        XMLReader read = new XMLReader();
        List<Course> readConfig = read.readConfig("courses2.xml");
        for (Course course : readConfig) {
            System.out.println(course);
        }
    }
}
