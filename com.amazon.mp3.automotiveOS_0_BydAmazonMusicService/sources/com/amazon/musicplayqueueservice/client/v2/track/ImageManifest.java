package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class ImageManifest extends ImageMetadataBase {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.ImageManifest");
    private Map<String, String> urls;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.v2.track.ImageMetadataBase, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ImageMetadataBase imageMetadataBase) {
        if (imageMetadataBase == null) {
            return -1;
        }
        if (imageMetadataBase == this) {
            return 0;
        }
        if (!(imageMetadataBase instanceof ImageManifest)) {
            return 1;
        }
        Map<String, String> urls = getUrls();
        Map<String, String> urls2 = ((ImageManifest) imageMetadataBase).getUrls();
        if (urls != urls2) {
            if (urls == null) {
                return -1;
            }
            if (urls2 == null) {
                return 1;
            }
            if (urls instanceof Comparable) {
                int iCompareTo = ((Comparable) urls).compareTo(urls2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!urls.equals(urls2)) {
                int iHashCode = urls.hashCode();
                int iHashCode2 = urls2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(imageMetadataBase);
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.ImageMetadataBase
    public boolean equals(Object obj) {
        if (obj instanceof ImageManifest) {
            return super.equals(obj) && internalEqualityCheck(getUrls(), ((ImageManifest) obj).getUrls());
        }
        return false;
    }

    public Map<String, String> getUrls() {
        return this.urls;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.ImageMetadataBase
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getUrls());
    }

    public void setUrls(Map<String, String> map) {
        this.urls = map;
    }
}
