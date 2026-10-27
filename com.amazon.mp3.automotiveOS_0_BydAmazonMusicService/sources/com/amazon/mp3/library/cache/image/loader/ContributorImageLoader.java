package com.amazon.mp3.library.cache.image.loader;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.ImageMetadata;
import com.amazon.mp3.library.cache.image.loader.AbstractImageLoader;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.data.ContributorManager;
import com.amazon.mp3.library.data.impl.ContributorAccessObject;
import com.amazon.mp3.library.data.impl.ContributorManagerImpl;
import com.amazon.mp3.net.BitmapHttpClient;
import com.amazon.mp3.task.JobContext;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class ContributorImageLoader extends AbstractImageLoader implements ImageLoaderFactory.ImageLoader {
    private static final int ARTIST_IMAGE_UNSCALED_SIZE = CacheManager.GRID_VIEW_IMAGE_SIZE;
    private ContributorManager mService;

    ContributorImageLoader(ImageLoaderFactory.ItemType itemType) {
        super(itemType);
        this.mService = ContributorManagerImpl.INSTANCE;
    }

    protected String downloadUnscaledImage(ContributorAccessObject.Contributor contributor, ImageMetadata metadata) {
        String imageURL;
        ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.CONTRIBUTOR;
        int i = ARTIST_IMAGE_UNSCALED_SIZE;
        String cacheFileName = getCacheFileName(itemType, null, i, contributor.getAsin());
        if (!new File(cacheFileName).exists() && (imageURL = contributor.getImageURL()) != null) {
            Bitmap bitmapBitmapFromUrl = BitmapHttpClient.bitmapFromUrl(imageURL);
            if (bitmapBitmapFromUrl != null && bitmapBitmapFromUrl.getWidth() != i) {
                bitmapBitmapFromUrl = resample(bitmapBitmapFromUrl, i);
            }
            saveToFile(bitmapBitmapFromUrl, itemType, contributor.getAsin());
            ImageLoaderFactory.ItemType type = metadata.getType();
            ImageLoaderFactory.ItemType itemType2 = ImageLoaderFactory.ItemType.ARTIST;
            if (type == itemType2) {
                saveToFile(bitmapBitmapFromUrl, itemType2, metadata.getId());
            }
        }
        return cacheFileName;
    }

    public JobContext<ImageMetadata> getImage(JobContext<ImageMetadata> imageLoadContext) {
        Bitmap bitmapLoadAndScaleFromFile;
        ImageMetadata imageMetadata = (ImageMetadata) imageLoadContext.getMetadata();
        String id = imageMetadata.getId();
        ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.CONTRIBUTOR;
        String cacheFileName = getCacheFileName(itemType, null, imageMetadata.getSize(), id);
        if (new File(cacheFileName).exists()) {
            bitmapLoadAndScaleFromFile = loadAndScaleFromFile(cacheFileName, imageMetadata.getSize());
            imageMetadata.addFlags(2);
        } else {
            ContributorAccessObject.Contributor contributor = this.mService.getContributor(id);
            if (contributor != null) {
                Bitmap bitmapLoadAndScale = new AbstractImageLoader.ScaledImageLoader(downloadUnscaledImage(contributor, imageMetadata)).loadAndScale(imageMetadata.getSize(), true);
                if (bitmapLoadAndScale != null) {
                    saveToFile(bitmapLoadAndScale, itemType, id);
                }
                bitmapLoadAndScaleFromFile = bitmapLoadAndScale;
            } else {
                bitmapLoadAndScaleFromFile = null;
            }
        }
        if (bitmapLoadAndScaleFromFile == null) {
            imageMetadata.setImage(null);
        } else {
            imageMetadata.setImage(new BitmapDrawable(AmazonApplication.getContext().getResources(), bitmapLoadAndScaleFromFile));
            imageLoadContext.setStatus(JobContext.Status.FINISHED);
        }
        return imageLoadContext;
    }

    @Override // com.amazon.mp3.library.cache.image.loader.AbstractImageLoader, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public String getCacheFileName(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        return super.getCacheFileName(ImageLoaderFactory.ItemType.CONTRIBUTOR, source, size, id);
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public int getMaxImageSize() {
        return ARTIST_IMAGE_UNSCALED_SIZE;
    }
}
