
public class Inventory {

    String[] Items;
    final int MAX_ITEMS = 10;

    public Inventory() {
        Items = new String[MAX_ITEMS];
        for (int i = 0; i < MAX_ITEMS; i++) {
            Items[i] = null;
        }
    }

    public void addItem(String item) {
// adds an item to an array if there is space
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (Items[i]==null) {
                Items[i] = item;
                break;
            }
        }
    }

    public int hasItem(String item) {
        // returns the position of an item if it is in the array 
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (item.equals(Items[i])) {
                return i;
            }
        }
        return -1;
    }

    public void removeItem(String item) {
        String s;
        // removes a specified item while ensuring there is no empty items in the array 
        for (int i = 0;i< MAX_ITEMS;i++) {
            s = Items[i];
            if (s == null ? item == null : s.equals(item)) {
                Items[i] = null; // removes an item from the iventory 
                for (int x = i+1; x < MAX_ITEMS - 1 /* makes sure loop doesn't go beyound bounds of array*/; x++) {
                    if (!(Items[x]==null)) { //checks if there is a string at that index 
                        // stores the next item in the array 
                        Items[x - 1] = Items[x]; // assigns the next item to the current index 
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public String displayInventory() {
        String toolkit = new String();
        for (String I : Items) {
            if (!(I == null))
            {
            toolkit += I + "";
            }
        }
        return toolkit;
    }
}
