package com.amazon.mp3.library.data;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.data.impl.ContributorAccessObject;
import com.amazon.mp3.library.provider.MediaProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ContributorManager {
    private CacheManager mCacheManager = CacheManager.getInstance();

    public abstract void update(List<ContributorAccessObject.ContributorData> contributorData);

    public ContributorAccessObject.Contributor getContributor(String asin) {
        return ContributorAccessObject.fetchContributor(asin);
    }

    public List<ContributorAccessObject.Contributor> getContributors(String itemUri) {
        return ContributorAccessObject.fetchContributorsForItem(itemUri);
    }

    public ContributorAccessObject.Contributor getDefaultContributor(String itemUri) {
        return ContributorAccessObject.fetchDefaultContributorForItem(itemUri);
    }

    public Collection<String> getArtistIdsForContributor(String contributorAsin) {
        HashSet hashSet = new HashSet();
        Iterator<ContributorAccessObject.ItemContributor> it = ContributorAccessObject.fetchItemsForContributor(contributorAsin).iterator();
        while (it.hasNext()) {
            Uri uri = Uri.parse(it.next().getItemUri());
            if (MediaProvider.Artists.isArtist(uri)) {
                hashSet.add(Long.toString(MediaProvider.Artists.getArtistId(uri)));
            }
        }
        return hashSet;
    }

    public String getHeroImageServerCommand(int imageWidth, int imageHeight, int requestedSize) {
        int i;
        int i2;
        int i3 = imageWidth > imageHeight ? imageHeight : imageWidth;
        int i4 = (imageWidth > imageHeight ? imageWidth : imageHeight) - i3;
        if (imageWidth > imageHeight) {
            i2 = i4 / 2;
            i = 0;
        } else {
            i = i4 / 2;
            i2 = 0;
        }
        return String.format(Locale.US, "._CR%s,%s,%s,%s_SX%s", Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(i3), Integer.valueOf(requestedSize));
    }

    public String getRegImageServerCommand(int imageWidth, int imageHeight, int requestedSize) {
        String str;
        int i;
        int i2;
        int i3 = imageWidth > imageHeight ? imageHeight : imageWidth;
        double d = requestedSize;
        int i4 = (int) (1.3d * d);
        if (i3 >= i4) {
            int i5 = (int) (0.1d * d);
            int i6 = (int) (0.07d * d);
            if (imageHeight < imageWidth) {
                str = "Y";
                i6 = i5;
                i5 = (int) (d * 0.25d);
            } else {
                str = "X";
            }
            return String.format(Locale.US, "._S%s%s_CR%s,%s,%s,%s_", str, Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(requestedSize), Integer.valueOf(requestedSize));
        }
        int i7 = (int) (((double) i3) * 0.9d);
        int i8 = ((int) (((double) (imageWidth > imageHeight ? imageWidth : imageHeight)) * 0.9d)) - i7;
        if (imageWidth > imageHeight) {
            i = i8 / 2;
            i2 = (int) (((double) i7) * 0.1d);
        } else {
            int i9 = (int) (((double) i8) * 0.7d);
            i = (int) (((double) i7) * 0.1d);
            i2 = i9;
        }
        return String.format(Locale.US, "._CR%s,%s,%s,%s_", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i7));
    }

    public void prefetchImages(Collection<String> contributorAsins, Collection<String> artistIds) {
        ArrayList arrayList = new ArrayList(contributorAsins);
        ArrayList arrayList2 = new ArrayList(artistIds);
        this.mCacheManager.clearFromDiskCache(ImageLoaderFactory.ItemType.CONTRIBUTOR, arrayList, true);
        this.mCacheManager.clearFromDiskCache(ImageLoaderFactory.ItemType.ARTIST, artistIds, true);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.mCacheManager.prefetch(ImageLoaderFactory.ItemType.CONTRIBUTOR, AmazonApplication.getCapabilities().defaultPrefetchImageSize(), (String) it.next(), true);
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            this.mCacheManager.prefetch(ImageLoaderFactory.ItemType.ARTIST, AmazonApplication.getCapabilities().defaultPrefetchImageSize(), (String) it2.next(), true);
        }
    }
}
