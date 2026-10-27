package com.amazon.mp3.detailpages.playlist.actions;

import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.api.PlaylistFactory;
import com.amazon.mp3.sharing.ShareProviderActionHandler;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.music.share.PlaylistShareProvider;
import com.amazon.music.share.ShareProvider;
import com.amazon.music.share.UserPlaylistShareProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistShareAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistShareAction;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "playlistUri", "Landroid/net/Uri;", "isCatalog", "", "(Landroidx/fragment/app/FragmentActivity;Landroid/net/Uri;Z)V", "buildPlaylistShareProvider", "Lcom/amazon/music/share/ShareProvider;", "sharePlaylist", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistShareAction {
    private final FragmentActivity activity;
    private final boolean isCatalog;
    private final Uri playlistUri;

    public PlaylistShareAction(FragmentActivity activity, Uri playlistUri, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        this.activity = activity;
        this.playlistUri = playlistUri;
        this.isCatalog = z;
    }

    public final void sharePlaylist() {
        ShareProvider shareProviderBuildPlaylistShareProvider = buildPlaylistShareProvider();
        if (shareProviderBuildPlaylistShareProvider == null) {
            return;
        }
        shareProviderBuildPlaylistShareProvider.startShare();
    }

    private final ShareProvider buildPlaylistShareProvider() {
        FragmentActivity fragmentActivity = this.activity;
        if (!ConnectivityUtil.checkConnectivityAndShowDialog(fragmentActivity)) {
            return null;
        }
        String musicDomain = EndpointsProvider.get().getMusicDomain();
        LibraryItemFactory libraryItemFactory = AmazonApplication.getLibraryItemFactory();
        if (this.isCatalog) {
            CatalogPlaylist catalogPlaylist = libraryItemFactory.getCatalogPlaylist(this.playlistUri);
            Intrinsics.checkNotNullExpressionValue(catalogPlaylist, "itemFactory.getCatalogPlaylist(playlistUri)");
            return new PlaylistShareProvider(fragmentActivity, musicDomain, catalogPlaylist.getAsin(), catalogPlaylist.getTitle(), catalogPlaylist.getBannerArtUrl());
        }
        Playlist playlist = libraryItemFactory.getPlaylist(this.playlistUri);
        Intrinsics.checkNotNullExpressionValue(playlist, "itemFactory.getPlaylist(playlistUri)");
        return new UserPlaylistShareProvider(fragmentActivity, musicDomain, playlist.getLuid(), playlist.getName(), playlist.getVersion(), PlaylistFactory.createPlaylistLibrary(fragmentActivity), ShareProviderActionHandler.newInstance(), null);
    }
}
