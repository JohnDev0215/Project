package com.amazon.mp3.library.cache.image.loader;

import android.graphics.drawable.BitmapDrawable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.image.ImageMetadata;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.task.JobContext;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultPlaylistImageLoader extends DefaultGenericImageLoader {
    @Override // com.amazon.mp3.library.cache.image.loader.DefaultGenericImageLoader
    protected int getDefaultImageId() {
        return R.drawable.playlist_placeholder_detail;
    }

    DefaultPlaylistImageLoader(ImageLoaderFactory.ItemType itemType) {
        super(itemType);
    }

    @Override // com.amazon.mp3.library.cache.image.loader.DefaultGenericImageLoader, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.DefaultImageLoader
    public JobContext<ImageMetadata> getImage(JobContext<ImageMetadata> taskContext, boolean forceLoad) {
        ((ImageMetadata) taskContext.getMetadata()).setImage((BitmapDrawable) AmazonApplication.getContext().getResources().getDrawable(getDefaultImageId()));
        return taskContext;
    }
}
