package com.example.zerostore;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class FavoritesManager {

    private static final String PREF_NAME = "ZeroStoreFavorites";
    private static final String KEY_FAVORITES = "favorite_ids";

    private SharedPreferences sharedPreferences;
    private static FavoritesManager instance;

    private FavoritesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized FavoritesManager getInstance(Context context) {
        if (instance == null) {
            instance = new FavoritesManager(context.getApplicationContext());
        }
        return instance;
    }

    public void addFavorite(int productId) {
        Set<String> favorites = getFavoritesSet();
        favorites.add(String.valueOf(productId));
        saveFavorites(favorites);
    }

    public void removeFavorite(int productId) {
        Set<String> favorites = getFavoritesSet();
        favorites.remove(String.valueOf(productId));
        saveFavorites(favorites);
    }

    public boolean isFavorite(int productId) {
        Set<String> favorites = getFavoritesSet();
        return favorites.contains(String.valueOf(productId));
    }

    public Set<String> getFavoritesSet() {
        return sharedPreferences.getStringSet(KEY_FAVORITES, new HashSet<>());
    }

    private void saveFavorites(Set<String> favorites) {
        sharedPreferences.edit().putStringSet(KEY_FAVORITES, favorites).apply();
    }
}
