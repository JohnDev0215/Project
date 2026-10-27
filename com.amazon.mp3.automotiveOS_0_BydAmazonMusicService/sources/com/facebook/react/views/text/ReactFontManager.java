package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class ReactFontManager {
    private static final String[] EXTENSIONS = {"", "_bold", "_italic", "_bold_italic"};
    private static final String[] FILE_EXTENSIONS = {".ttf", ".otf"};
    private static ReactFontManager sReactFontManagerInstance;
    private final Map<String, AssetFontFamily> mFontCache = new HashMap();
    private final Map<String, Typeface> mCustomTypefaceCache = new HashMap();

    private ReactFontManager() {
    }

    public static ReactFontManager getInstance() {
        if (sReactFontManagerInstance == null) {
            sReactFontManagerInstance = new ReactFontManager();
        }
        return sReactFontManagerInstance;
    }

    public Typeface getTypeface(String str, int i, AssetManager assetManager) {
        return getTypeface(str, new TypefaceStyle(i), assetManager);
    }

    public Typeface getTypeface(String str, TypefaceStyle typefaceStyle, AssetManager assetManager) {
        if (this.mCustomTypefaceCache.containsKey(str)) {
            return typefaceStyle.apply(this.mCustomTypefaceCache.get(str));
        }
        AssetFontFamily assetFontFamily = this.mFontCache.get(str);
        if (assetFontFamily == null) {
            assetFontFamily = new AssetFontFamily();
            this.mFontCache.put(str, assetFontFamily);
        }
        int nearestStyle = typefaceStyle.getNearestStyle();
        Typeface typefaceForStyle = assetFontFamily.getTypefaceForStyle(nearestStyle);
        if (typefaceForStyle != null) {
            return typefaceForStyle;
        }
        Typeface typefaceCreateAssetTypeface = createAssetTypeface(str, nearestStyle, assetManager);
        assetFontFamily.setTypefaceForStyle(nearestStyle, typefaceCreateAssetTypeface);
        return typefaceCreateAssetTypeface;
    }

    private static Typeface createAssetTypeface(String str, int i, AssetManager assetManager) {
        String str2 = EXTENSIONS[i];
        for (String str3 : FILE_EXTENSIONS) {
            try {
                return Typeface.createFromAsset(assetManager, "fonts/" + str + str2 + str3);
            } catch (RuntimeException unused) {
            }
        }
        return Typeface.create(str, i);
    }

    private static class AssetFontFamily {
        private SparseArray<Typeface> mTypefaceSparseArray;

        private AssetFontFamily() {
            this.mTypefaceSparseArray = new SparseArray<>(4);
        }

        @Nullable
        public Typeface getTypefaceForStyle(int i) {
            return this.mTypefaceSparseArray.get(i);
        }

        public void setTypefaceForStyle(int i, Typeface typeface) {
            this.mTypefaceSparseArray.put(i, typeface);
        }
    }
}
