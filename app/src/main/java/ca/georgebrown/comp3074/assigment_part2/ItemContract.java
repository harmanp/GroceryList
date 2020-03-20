package ca.georgebrown.comp3074.assigment_part2;

import android.provider.BaseColumns;

public class ItemContract {
    private ItemContract(){}
    public static  class ItemEntry
            implements BaseColumns
    {
        public static final String TABLE_NAME = "items_list";
        public static final String NAME = "name";
    }
}
