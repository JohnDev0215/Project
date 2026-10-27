package com.amazon.mp3.platform.providers;

import android.util.LruCache;
import com.amazon.music.platform.data.image.ImageUrlCache;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageURLCacheImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/platform/providers/ImageUrlCacheImpl;", "Lcom/amazon/music/platform/data/image/ImageUrlCache;", "()V", "getUrl", "", "id", "putUrl", "", ImagesContract.URL, "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ImageUrlCacheImpl implements ImageUrlCache {
    private static final LruCache<String, String> cache = new LruCache<>(50);

    @Override // com.amazon.music.platform.data.image.ImageUrlCache
    public String getUrl(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return cache.get(id);
    }

    @Override // com.amazon.music.platform.data.image.ImageUrlCache
    public void putUrl(String id, String url) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(url, "url");
        cache.put(id, url);
    }
}
