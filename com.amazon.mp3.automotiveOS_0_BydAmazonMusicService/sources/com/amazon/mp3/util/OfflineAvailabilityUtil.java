package com.amazon.mp3.util;

import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OfflineAvailabilityUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H\u0007J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H\u0007J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H\u0007¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/util/OfflineAvailabilityUtil;", "", "()V", "getDownloadState", "", "catalogContent", "Lcom/amazon/mp3/library/item/CatalogContent;", "(Lcom/amazon/mp3/library/item/CatalogContent;)Ljava/lang/Integer;", "isAssetDownloadInProgess", "", "content", "isCorruptedAsset", "isPreWidevineAsset", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OfflineAvailabilityUtil {
    public static final OfflineAvailabilityUtil INSTANCE = new OfflineAvailabilityUtil();

    private OfflineAvailabilityUtil() {
    }

    @JvmStatic
    public static final boolean isPreWidevineAsset(CatalogContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        Integer downloadState = INSTANCE.getDownloadState(content);
        return downloadState != null && downloadState.intValue() == 6;
    }

    @JvmStatic
    public static final boolean isCorruptedAsset(CatalogContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        Integer downloadState = INSTANCE.getDownloadState(content);
        return downloadState != null && downloadState.intValue() == 7;
    }

    @JvmStatic
    public static final boolean isAssetDownloadInProgess(CatalogContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        Integer downloadState = INSTANCE.getDownloadState(content);
        if (downloadState != null && downloadState.intValue() == 1) {
            return true;
        }
        if (downloadState != null && downloadState.intValue() == -2) {
            return true;
        }
        if (downloadState != null && downloadState.intValue() == 4) {
            return true;
        }
        if (downloadState != null && downloadState.intValue() == 3) {
            return true;
        }
        return downloadState != null && downloadState.intValue() == 2;
    }

    private final Integer getDownloadState(CatalogContent catalogContent) {
        if (catalogContent instanceof MusicTrack) {
            return Integer.valueOf(((MusicTrack) catalogContent).getDownloadState());
        }
        if (catalogContent instanceof PrimeTrack) {
            return Integer.valueOf(((PrimeTrack) catalogContent).getDownloadState());
        }
        if (catalogContent instanceof PrimeAlbum) {
            return Integer.valueOf(((PrimeAlbum) catalogContent).getDownloadState());
        }
        return null;
    }
}
