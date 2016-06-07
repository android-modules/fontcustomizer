package com.cdvdev.fontcustomizer;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for cache Custom Fonts
 *
 * @author Dmitriy V. Chernysh (dmitriy.chernysh@gmail.com)
 *
 */
public class FontCache {

    private static String TAG = "FontCache";
    private static final String FONT_DIR = "fonts";
    private static Map<String, Typeface> cache = new HashMap<>();
    private static Map<String, String> fontMapping = new HashMap<>();
    private static FontCache sInstance;
    private static Context sAppContext;

    public static FontCache getInstance(Context appContext) {
        if (sInstance == null) {
            sAppContext = appContext;
            sInstance = new FontCache();
        }
        return sInstance;
    }

    public void addFont(String name, String fontFilename) {
        fontMapping.put(name, fontFilename);
    }

    private FontCache() {
        AssetManager am = sAppContext.getResources().getAssets();
        String fileList[];
        try {
            fileList = am.list(FONT_DIR);
        } catch (IOException e) {
            Log.e(TAG, "Error loading fonts from assets/fonts.");
            return;
        }

        for (String filename : fileList) {
            String alias = filename.substring(0, filename.lastIndexOf('.'));
            fontMapping.put(alias, filename);
            fontMapping.put(alias.toLowerCase(), filename);
        }
    }

    public Typeface get(String fontName) {
        String fontFilename = fontMapping.get(fontName);
        if (fontFilename == null) {
            Log.e(TAG, "Couldn't find font " + fontName + ". Maybe you need to call addFont() first?");
            return null;
        }
        if (cache.containsKey(fontFilename)) {
            return cache.get(fontFilename);
        } else {
            Typeface typeface = Typeface.createFromAsset(sAppContext.getResources().getAssets(), FONT_DIR + "/" + fontFilename);
            cache.put(fontFilename, typeface);
            return typeface;
        }
    }
}
