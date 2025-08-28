

public class Room {

    private String Name = null;
    private String Description = null;
    private char Symbol;
    private Position Xy = null;
    private String feature = null;
    private String item = null;

    public Room(String name, String description, char symbol, Position position) {
        this.Name = name;
        this.Description = description;
        this.Symbol = symbol;
        this.Xy = position;
        feature = "";
    }

// getters 
    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public char getSymbol() {
        return Symbol;
    }

    public Position getPosition() {
        return Xy;
    }

    public String getFeature() {
        return feature;
    }

    public String getItem() {
        return item;
    }

    public void setFeature(String feat) {
        feature = feat;
    }

    public void setItem(String i) {
        item = i;
    }

}
