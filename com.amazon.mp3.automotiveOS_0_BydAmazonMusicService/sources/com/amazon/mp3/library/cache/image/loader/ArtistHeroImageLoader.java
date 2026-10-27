package com.amazon.mp3.library.cache.image.loader;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.ImageMetadata;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.data.ContributorManager;
import com.amazon.mp3.library.data.impl.ContributorAccessObject;
import com.amazon.mp3.library.data.impl.ContributorManagerImpl;
import com.amazon.mp3.library.item.ItemWrapper$IdType;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.net.BitmapHttpClient;
import com.amazon.mp3.task.JobContext;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ArtistHeroImageLoader extends AbstractImageLoader implements ImageLoaderFactory.ImageLoader {
    private static final int ARTIST_HERO_IMAGE_DEFAULT_HEIGHT;
    private static final int ARTIST_HERO_IMAGE_DEFAULT_SIZE = CacheManager.DEFAULT_HERO_IMAGE_SIZE;
    private ContributorManager mService;

    static {
        ARTIST_HERO_IMAGE_DEFAULT_HEIGHT = Math.round((r0 * 9) / 16.0f);
    }

    ArtistHeroImageLoader(ImageLoaderFactory.ItemType itemType) {
        super(itemType);
        this.mService = ContributorManagerImpl.INSTANCE;
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public JobContext<ImageMetadata> getImage(JobContext<ImageMetadata> imageLoadContext) {
        ContributorAccessObject.Contributor contributor;
        ImageMetadata imageMetadata = (ImageMetadata) imageLoadContext.getMetadata();
        List<ContributorAccessObject.Contributor> contributors = this.mService.getContributors(ItemWrapper$IdType.MERGED_ID.getUri(ItemWrapper$ItemType.ARTIST, imageMetadata.getId()).toString());
        if (contributors.isEmpty()) {
            contributor = null;
        } else {
            contributor = contributors.get(0);
            imageMetadata.setExtraData(contributor.getAsin());
        }
        Bitmap bitmapLoad16x9HeroImageFromContributor = contributor != null ? load16x9HeroImageFromContributor(contributor, imageMetadata) : null;
        if (bitmapLoad16x9HeroImageFromContributor == null) {
            imageMetadata.setImage(null);
        } else {
            imageMetadata.setImage(new BitmapDrawable(AmazonApplication.getContext().getResources(), bitmapLoad16x9HeroImageFromContributor));
            imageLoadContext.setStatus(JobContext.Status.FINISHED);
        }
        return imageLoadContext;
    }

    private Bitmap load16x9HeroImageFromContributor(final ContributorAccessObject.Contributor contributor, final ImageMetadata metadata) {
        Bitmap bitmapBitmapFromUrl;
        int size = metadata.getSize();
        int iRound = Math.round((size * 9) / 16.0f);
        ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.ARTIST_HERO;
        String cacheFileName = super.getCacheFileName(itemType, metadata.getSource(), size, contributor.getAsin());
        String cacheFileName2 = super.getCacheFileName(itemType, metadata.getSource(), getMaxImageSize(), contributor.getAsin());
        File file = new File(cacheFileName);
        File file2 = new File(cacheFileName2);
        if (file.exists()) {
            Bitmap bitmapLoadAndScaleFromFile = loadAndScaleFromFile(cacheFileName, size, iRound);
            metadata.addFlags(2);
            return bitmapLoadAndScaleFromFile;
        }
        if (file2.exists() && size <= getMaxImageSize()) {
            return loadAndScaleFromFile(cacheFileName2, size, iRound);
        }
        if (contributor.get16x9HeroImageURL() == null || (bitmapBitmapFromUrl = BitmapHttpClient.bitmapFromUrl(contributor.get16x9HeroImageURL())) == null) {
            return null;
        }
        Bitmap bitmapResample = bitmapBitmapFromUrl.getWidth() != getMaxImageSize() ? resample(bitmapBitmapFromUrl, getMaxImageSize(), getMaxImageHeight()) : bitmapBitmapFromUrl;
        if (bitmapResample != null) {
            saveToFile(bitmapResample, itemType, contributor.getAsin());
        }
        Bitmap bitmapResample2 = resample(bitmapBitmapFromUrl, size, iRound);
        if (bitmapResample2 == null) {
            return bitmapResample2;
        }
        saveToFile(bitmapResample2, itemType, contributor.getAsin());
        return bitmapResample2;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.cache.image.loader.ArtistHeroImageLoader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType;

        static {
            int[] iArr = new int[ImageLoaderFactory.ItemType.values().length];
            $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType = iArr;
            try {
                iArr[ImageLoaderFactory.ItemType.ARTIST_HERO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ImageLoaderFactory.ItemType.ARTIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.amazon.mp3.library.cache.image.loader.AbstractImageLoader, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public String getCacheFileName(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        if (id == null || type == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[type.ordinal()];
        if (i == 1) {
            return super.getCacheFileName(type, source, size, id);
        }
        if (i == 2) {
            List<ContributorAccessObject.Contributor> contributors = this.mService.getContributors(ItemWrapper$IdType.MERGED_ID.getUri(ItemWrapper$ItemType.ARTIST, id).toString());
            if (contributors != null && !contributors.isEmpty()) {
                return super.getCacheFileName(type, source, size, contributors.get(0).getAsin());
            }
            return super.getCacheFileName(type, source, size, id);
        }
        return getCacheManager().getCacheFileName(type, source, size, id);
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public int getMaxImageSize() {
        return ARTIST_HERO_IMAGE_DEFAULT_SIZE;
    }

    public int getMaxImageHeight() {
        return ARTIST_HERO_IMAGE_DEFAULT_HEIGHT;
    }

    public boolean has16x9HeroImage(final String artistId) {
        List<ContributorAccessObject.Contributor> contributors = this.mService.getContributors(ItemWrapper$IdType.MERGED_ID.getUri(ItemWrapper$ItemType.ARTIST, artistId).toString());
        ContributorAccessObject.Contributor contributor = !contributors.isEmpty() ? contributors.get(0) : null;
        return (contributor == null || contributor.get16x9HeroImageURL() == null) ? false : true;
    }
}
