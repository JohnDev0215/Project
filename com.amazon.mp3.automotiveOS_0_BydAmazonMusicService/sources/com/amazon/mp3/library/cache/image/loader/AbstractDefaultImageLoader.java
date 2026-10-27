package com.amazon.mp3.library.cache.image.loader;

import com.amazon.mp3.library.cache.image.ImageMetadata;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.task.JobContext;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractDefaultImageLoader extends AbstractImageLoader implements ImageLoaderFactory.DefaultImageLoader {
    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.DefaultImageLoader, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public abstract /* synthetic */ int getMaxImageSize();

    public AbstractDefaultImageLoader(ImageLoaderFactory.ItemType itemType) {
        super(itemType);
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public JobContext<ImageMetadata> getImage(JobContext<ImageMetadata> taskContext) {
        return getImage(taskContext, false);
    }
}
