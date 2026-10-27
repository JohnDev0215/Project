package com.amazon.mp3.library.cache.image.loader;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.ImageMetadata;
import com.amazon.mp3.library.cache.image.loader.AbstractImageLoader;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.task.JobContext;
import com.amazon.mp3.util.CompositeImageGenerator;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class GenreImageLoader extends AbstractImageLoader implements ImageLoaderFactory.ImageLoader {
    private static final int GENRE_IMAGE_UNSCALED_SIZE = CacheManager.MAX_UNSCALED_SIZE;
    private static final CompositeImageGenerator sImageGenerator = new CompositeImageGenerator(0.5f, 1.0f, 46.0f, 30.0f, 46.0f, 84.0f, 70, -2);

    public GenreImageLoader(ImageLoaderFactory.ItemType itemType) {
        super(itemType);
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public JobContext<ImageMetadata> getImage(JobContext<ImageMetadata> imageLoadContext) {
        Bitmap compositeBitmap;
        ImageMetadata imageMetadata = (ImageMetadata) imageLoadContext.getMetadata();
        Uri contentUri = MediaProvider.Genres.getContentUri(imageMetadata.getSource(), Long.parseLong(imageMetadata.getId()));
        String cacheFileName = getCacheFileName(imageMetadata.getType(), imageMetadata.getSource(), imageMetadata.getSize(), imageMetadata.getId());
        if (cacheFileName != null && new File(cacheFileName).exists()) {
            compositeBitmap = loadAndScaleFromFile(cacheFileName, imageMetadata.getSize());
            imageMetadata.addFlags(2);
            imageMetadata.setUri(cacheFileName);
        } else {
            ImageLoaderFactory.ItemType type = imageMetadata.getType();
            int i = GENRE_IMAGE_UNSCALED_SIZE;
            String cacheFileName2 = getCacheFileName(type, null, i, imageMetadata.getId());
            if (cacheFileName2 == null || !new File(cacheFileName2).exists()) {
                compositeBitmap = sImageGenerator.getCompositeBitmap(i, contentUri);
                cacheFileName2 = saveToFile(compositeBitmap, imageMetadata.getSource(), imageMetadata.getType(), imageMetadata.getId());
            } else {
                compositeBitmap = null;
            }
            if (cacheFileName2 != null) {
                compositeBitmap = new AbstractImageLoader.ScaledImageLoader(cacheFileName2).loadAndScale(imageMetadata.getSize(), true);
                imageMetadata.setUri(saveToFile(compositeBitmap, imageMetadata.getSource(), imageMetadata.getType(), imageMetadata.getId()));
            }
        }
        if (compositeBitmap == null) {
            ImageLoaderFactory.getInstance().getDefaultImageLoader(this.mItemType).getImage(imageLoadContext, true);
            imageMetadata.setImage(new BitmapDrawable(AmazonApplication.getContext().getResources(), ((BitmapDrawable) imageMetadata.getImage()).getBitmap()));
            imageMetadata.setUri(null);
        } else {
            imageMetadata.setImage(new BitmapDrawable(AmazonApplication.getContext().getResources(), compositeBitmap));
        }
        imageLoadContext.setStatus(JobContext.Status.FINISHED);
        return imageLoadContext;
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public int getMaxImageSize() {
        return GENRE_IMAGE_UNSCALED_SIZE;
    }
}
