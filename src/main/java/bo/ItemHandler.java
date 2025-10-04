package bo;

import db.ItemDB;
import ui.ItemInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ItemHandler {
    public static Collection<ItemInfo> getItemsWithGroup(String s){
        Collection<Item> c = ItemDB.searchItems(s);
        ArrayList<ItemInfo> items = new ArrayList<>();
        for (Iterator<Item> it = c.iterator(); it.hasNext();){
            Item item = it.next();
            items.add(new ItemInfo(item.getName(), item.getDescr()));
        }
        return items;
    }
}
