package com.amazon.mp3.find.dagger;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.music.find.model.search.PlaylistSearchItem;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.find.providers.CacheImageProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FindCacheImageProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/find/dagger/FindCacheImageProvider;", "Lcom/amazon/music/find/providers/CacheImageProvider;", "()V", "generatePrimePlaylistImageUri", "Landroid/net/Uri;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/find/model/search/PlaylistSearchItem;", "generateUserPlaylistImageUri", "getCacheImage", "Landroid/graphics/drawable/Drawable;", "searchItem", "Lcom/amazon/music/find/model/search/SearchItem;", "artworkSize", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindCacheImageProvider implements CacheImageProvider {
    @Override // com.amazon.music.find.providers.CacheImageProvider
    public Drawable getCacheImage(SearchItem searchItem, int artworkSize) {
        Intrinsics.checkNotNullParameter(searchItem, "searchItem");
        CacheManager cacheManager = CacheManager.getInstance();
        ImageLoaderFactory.ItemType artworkType = SearchItemUtils.getArtworkType(searchItem);
        if (artworkSize <= 0) {
            artworkSize = CacheManager.MAX_UNSCALED_SIZE;
        }
        Drawable onCurrentThread = cacheManager.getOnCurrentThread(artworkType, "cirrus", artworkSize, String.valueOf(searchItem.getArtworkId()), true, true);
        Intrinsics.checkNotNullExpressionValue(onCurrentThread, "getInstance().getOnCurre…d.toString(), true, true)");
        return onCurrentThread;
    }

    @Override // com.amazon.music.find.providers.CacheImageProvider
    public Uri generatePrimePlaylistImageUri(PlaylistSearchItem playlist) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Long id = playlist.getId();
        if (id == null) {
            return null;
        }
        return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.intValue(), id.longValue());
    }

    @Override // com.amazon.music.find.providers.CacheImageProvider
    public Uri generateUserPlaylistImageUri(PlaylistSearchItem playlist) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Long id = playlist.getId();
        if (id == null) {
            return null;
        }
        return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PLAYLIST_UDO_THUMBNAIL.intValue(), id.longValue());
    }
}
