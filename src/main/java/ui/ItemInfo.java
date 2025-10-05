package ui;

import java.io.Serializable;

/**
 * A simple data class that holds information about an item
 * to be displayed in the UI. This is typically used for views
 * where only the name and description are needed.
 */
public class ItemInfo implements Serializable {

    private String name;
    private String description;

    /**
     * Constructs an ItemInfo object with the given name and description.
     *
     * @param name        the name of the item
     * @param description the description of the item
     */
    public ItemInfo(String name, String description){
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the description of the item.
     *
     * @return the item's description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Sets a new description for the item.
     *
     * @param description the new description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Returns the name of the item.
     *
     * @return the item's name
     */
    public String getName(){
        return name;
    }

    /**
     * Sets a new name for the item.
     *
     * @param name the new name
     */
    public void setName(String name){
        this.name = name;
    }
}
