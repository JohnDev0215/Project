package com.amazon.mp3.library.cache.image.loader;

import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;

/* JADX INFO: loaded from: classes3.dex */
public class AlexaRecentsImageLoader extends DefaultGenericImageLoader {
    @Override // com.amazon.mp3.library.cache.image.loader.DefaultGenericImageLoader
    protected int getDefaultImageId() {
        return R.drawable.placeholder_alexa_recents;
    }

    AlexaRecentsImageLoader(ImageLoaderFactory.ItemType itemType) {
        super(itemType);
    }
}
