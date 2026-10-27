package com.amazon.mp3.navigation.deeplink;

import android.content.Context;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.music.share.AlbumShareProvider;
import com.amazon.music.share.PlaylistShareProvider;
import com.amazon.music.share.TrackShareProvider;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: ShareDeeplinkActionUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u001a\u0010\u0010\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u001a\u0010\u0013\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015R#\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/navigation/deeplink/ShareDeeplinkActionUtil;", "", "()V", "domainName", "", "kotlin.jvm.PlatformType", "getDomainName", "()Ljava/lang/String;", "domainName$delegate", "Lkotlin/Lazy;", "shareAlbumItem", "", "context", "Landroid/content/Context;", "album", "Lcom/amazon/mp3/library/item/Album;", "sharePlaylistItem", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "shareTrackItem", "track", "Lcom/amazon/mp3/library/item/MusicTrack;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ShareDeeplinkActionUtil {
    public static final ShareDeeplinkActionUtil INSTANCE = new ShareDeeplinkActionUtil();

    /* JADX INFO: renamed from: domainName$delegate, reason: from kotlin metadata */
    private static final Lazy domainName = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.navigation.deeplink.ShareDeeplinkActionUtil$domainName$2
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return EndpointsProvider.get().getMusicDomain();
        }
    });

    private ShareDeeplinkActionUtil() {
    }

    private final String getDomainName() {
        return (String) domainName.getValue();
    }

    public final boolean shareTrackItem(Context context, MusicTrack track) {
        if (context == null || track == null) {
            return false;
        }
        new TrackShareProvider(context, getDomainName(), track.getAlbumAsin(), track.getAsin(), track.getTitle(), track.getArtistName(), new CTAPrimeCache(context).getArtworkUrlForAsin(track.getAlbumAsin())).startShare();
        return true;
    }

    public final boolean shareAlbumItem(Context context, Album album) {
        if (context == null || album == null) {
            return false;
        }
        new AlbumShareProvider(context, getDomainName(), album.getAsin(), album.getTitle(), album.getArtistName(), new CTAPrimeCache(context).getArtworkUrlForAsin(album.getAsin())).startShare();
        return true;
    }

    public final boolean sharePlaylistItem(Context context, CatalogPlaylist playlist) {
        if (context == null || playlist == null) {
            return false;
        }
        new PlaylistShareProvider(context, getDomainName(), playlist.getAsin(), playlist.getTitle(), playlist.getThumbnailArtUrl()).startShare();
        return true;
    }
}
