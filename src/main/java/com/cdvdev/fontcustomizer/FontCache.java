package com.cdvdev.fontcustomizer;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for Caching Custom Fonts
 *
 * @author Dmitriy V. Chernysh (dmitriy.chernysh@gmail.com)
 *
 */
public class FontCache {

    private static String TAG = "FontCache";
    private static final String FONT_DIR = "fonts";

    private static FontCache sInstance;

    private Map<String, Typeface> mCache;
    private Map<String, String> mFontMapping;
    private AssetManager mAssetManager;

    private FontCache(Context appContext) {
        mFontMapping = new HashMap<>();
        mCache = new HashMap<>();
        mAssetManager = appContext.getResources().getAssets();

        String fileList[];
        try {
            fileList = mAssetManager.list(FONT_DIR);
        } catch (IOException e) {
            Log.e(TAG, "FontCache(): Error loading fonts from assets/fonts - " + e.getMessage(), e);
            return;
        }

        for (String filename : fileList) {
            String alias = filename.substring(0, filename.lastIndexOf('.'));
            mFontMapping.put(alias, filename);
            mFontMapping.put(alias.toLowerCase(), filename);
        }
    }

    public static FontCache getInstance(Context appContext) {
        if (sInstance == null) {
            sInstance = new FontCache(appContext);
        }
        return sInstance;
    }

    public void addFont(String name, String fontFilename) {
        mFontMapping.put(name, fontFilename);
    }

    public Typeface get(String fontName) {
        String fontFilename = mFontMapping.get(fontName);
        if (fontFilename == null) {
            Log.e(TAG, "Couldn't find font " + fontName + ". You need to call addFont() or addFontsFromAssets() first?");
            return null;
        }
        try{
           if (mCache.containsKey(fontFilename)) {
               return mCache.get(fontFilename);
           } else {
               Typeface typeface = Typeface.createFromAsset(mAssetManager, FONT_DIR + "/" + fontFilename);
               mCache.put(fontFilename, typeface);
               return typeface;
           }
        } catch (Exception e) {
           Log.e(TAG, "FontCache.get(): Could not get typeface - " + e.getMessage(), e);
        }
        
        return null;
    }
}
