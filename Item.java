
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item {
    private String name;
    //Q22 20
    private String description;

    public Item(String name) {
        this.name = name;
        this.description = "This is a " + name + ".";
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}