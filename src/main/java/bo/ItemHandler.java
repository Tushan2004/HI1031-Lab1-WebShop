package bo;

import db.ItemDB;
import ui.ItemInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Handles processing and retrieval of Item objects from the database.
 * Converts database Item objects to UI-friendly ItemInfo objects.
 */
public class ItemHandler {

    /**
     * Retrieves items from the database for a given group/category and
     * converts them into a collection of ItemInfo objects for display.
     *
     * @param s the item group or category to search for
     * @return a collection of ItemInfo objects representing the items in the group
     */
    public static Collection<ItemInfo> getItemsWithGroup(String s) {
        Collection<Item> c = ItemDB.searchItems(s);
        ArrayList<ItemInfo> items = new ArrayList<>();

        for (Iterator<Item> it = c.iterator(); it.hasNext(); ) {
            Item item = it.next();
            items.add(new ItemInfo(item.getName(), item.getDescr()));
        }

        return items;
    }
}
