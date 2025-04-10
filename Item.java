
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
    //31
    private int weight;

    public Item(String name, String description, int weight) {
        this.name = name;
        this.description = description;
        //Q31
        this.weight = weight; 
    }


    public String getName() {
         return name;
     }

    public String getDescription() {
        return description;
    }
    //Q31
    public int getWeight() { 
        return weight;
    }
}