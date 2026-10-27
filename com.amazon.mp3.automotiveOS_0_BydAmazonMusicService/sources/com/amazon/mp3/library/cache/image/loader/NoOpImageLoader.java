package com.amazon.mp3.library.cache.image.loader;

import com.amazon.mp3.library.cache.image.ImageMetadata;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.task.JobContext;

/* JADX INFO: loaded from: classes3.dex */
public class NoOpImageLoader extends AbstractImageLoader implements ImageLoaderFactory.DefaultImageLoader {
    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.DefaultImageLoader
    public void generateImages() {
    }

    @Override // com.amazon.mp3.library.cache.image.loader.AbstractImageLoader, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public String getCacheFileName(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        return null;
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public JobContext<ImageMetadata> getImage(JobContext<ImageMetadata> taskContext) {
        return taskContext;
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.DefaultImageLoader
    public JobContext<ImageMetadata> getImage(JobContext<ImageMetadata> jobContext, boolean forceLoad) {
        return null;
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.DefaultImageLoader, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public int getMaxImageSize() {
        return 0;
    }

    NoOpImageLoader(ImageLoaderFactory.ItemType itemType) {
        super(itemType);
    }
}
