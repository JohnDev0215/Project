package com.amazon.mp3.detailpages.playlist;

import android.net.Uri;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.EntityValidatorUtil;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: PlaylistDetailDependencies.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailDependencyFactory;", "", "()V", "buildDependencies", "Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailDependencies;", "isCatalog", "", "contentUri", "Landroid/net/Uri;", "asin", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistDetailDependencyFactory {
    public static final PlaylistDetailDependencyFactory INSTANCE = new PlaylistDetailDependencyFactory();

    private PlaylistDetailDependencyFactory() {
    }

    public static /* synthetic */ PlaylistDetailDependencies buildDependencies$default(boolean z, Uri uri, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        return buildDependencies(z, uri, str);
    }

    @JvmStatic
    public static final PlaylistDetailDependencies buildDependencies(boolean isCatalog, Uri contentUri, String asin) {
        if (isCatalog) {
            if (EntityValidatorUtil.isPersonalizedPlaylistId(asin)) {
                return new PersonalizedPlaylistDetailDependencies();
            }
            return new CatalogPlaylistDetailDependencies();
        }
        if (MediaProvider.SmartPlaylists.isSmartPlaylist(contentUri)) {
            return new AutoPlaylistDetailDependencies();
        }
        return new UserPlaylistDetailDependencies();
    }
}
