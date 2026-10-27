package com.amazon.music.platform.data.image;

import com.amazon.music.platform.providers.InterfaceProvider;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;

/* JADX INFO: compiled from: ImageUrlCache.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/platform/data/image/ImageUrlCache;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "getUrl", "", "id", "putUrl", "", ImagesContract.URL, "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface ImageUrlCache extends InterfaceProvider {
    String getUrl(String id);

    void putUrl(String id, String url);
}
