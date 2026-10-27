package com.amazon.mp3.library.cache.image;

import android.graphics.drawable.Drawable;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;

/* JADX INFO: loaded from: classes3.dex */
public interface IArtCache {

    public interface IArtLoadedListener {
        void onDrawableLoaded(Drawable d, String source, long id);

        void onDrawableLoaded(Drawable d, String source, String id);
    }

    Drawable getDrawable(ImageLoaderFactory.ItemType itemType, String source, long id);

    Drawable getDrawable(ImageLoaderFactory.ItemType itemType, String source, String id);
}
