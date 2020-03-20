package ca.georgebrown.comp3074.assigment_part2;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyItems extends Application {
    public static final List<Element> ITEMS = new ArrayList<Element>();

    public static final Map<String, Element> ITEM_MAP = new HashMap<String, Element>();

    public static class Element implements Serializable {

        public  String id;
        public String content;

        public Element(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
