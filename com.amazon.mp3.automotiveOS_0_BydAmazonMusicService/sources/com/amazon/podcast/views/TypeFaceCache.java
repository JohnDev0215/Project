package com.amazon.podcast.views;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
enum TypeFaceCache {
    INSTANCE;

    private final Map<String, Typeface> lookup = new HashMap();

    TypeFaceCache() {
    }

    public Typeface getTypeFace(final String fontPath, final Context context) {
        Typeface typeface = this.lookup.get(fontPath);
        if (typeface != null) {
            return typeface;
        }
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(context.getAssets(), fontPath);
        this.lookup.put(fontPath, typefaceCreateFromAsset);
        return typefaceCreateFromAsset;
    }
}
