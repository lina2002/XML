
public class TestWrite {

    public static void main(String[] args) {
        XMLWriter configFile = new XMLWriter();
        configFile.setFile("courses.xml");
        try {
            configFile.saveConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}