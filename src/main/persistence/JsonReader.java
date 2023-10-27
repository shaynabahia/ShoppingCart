package persistence;

import model.ShoppingCart;
import model.Item;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.WishList;
import org.json.*;

public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ShoppingCart from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ShoppingCart read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShoppingCart(jsonObject);
    }

    // EFFECTS: reads WishList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WishList readWl() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWishList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses shopping cart from JSON object and returns it
    private ShoppingCart parseShoppingCart(JSONObject jsonObject) {
       // String name = jsonObject.getString("name");
        ShoppingCart sc = new ShoppingCart();
        addItems(sc, jsonObject);
        return sc;
    }

    // EFFECTS: parses wish list from JSON object and returns it
    private WishList parseWishList(JSONObject jsonObject) {
        WishList wl = new WishList();
        addItemsWl(wl, jsonObject);
        return wl;
    }

    // MODIFIES: wl
    // EFFECTS: parses list items from JSON object and adds them to wishlist
    private void addItemsWl(WishList wl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("wishlist");
        for (Object json : jsonArray) {
            JSONObject nextItemWl = (JSONObject) json;
            addItemToWishList(wl, nextItemWl);
        }
    }

    // MODIFIES: wl
    // EFFECTS: parses item from JSON object and adds it to wishlist
    private void addItemToWishList(WishList wl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int price = jsonObject.getInt("price");
        String colour = jsonObject.getString("colour");
        String size = jsonObject.getString("size");
        //boolean inStock = jsonObject.getBoolean("inStock");
        Item item = new Item(name, colour, size, price, false);
        wl.addItemToWishList(item);
    }


    // MODIFIES: sc
    // EFFECTS: parses items from JSON object and adds them to shopping cart
    private void addItems(ShoppingCart sc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItemToCart(sc, nextItem);
        }
    }

    // MODIFIES: sc
    // EFFECTS: parses item from JSON object and adds it to shopping cart
    private void addItemToCart(ShoppingCart sc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int price = jsonObject.getInt("price");
        String colour = jsonObject.getString("colour");
        String size = jsonObject.getString("size");
        //boolean inStock = jsonObject.getBoolean("inStock");
        Item item = new Item(name, colour, size, price, true);
        sc.addItemToCart(item);
    }

}


