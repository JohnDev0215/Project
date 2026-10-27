package com.amazon.mp3.detailpages.playlist.playlistSongSearch;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.mp3.connect.providers.DefaultToastProvider;
import com.amazon.mp3.dialog.NoNetworkEmptyPageDialog;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.reactnative.ReactExperienceNavigationRouteHelper;
import com.amazon.mp3.util.ConnectivityUtil;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistSongSearchHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/playlistSongSearch/PlaylistSongSearchHandler;", "", "hostActivity", "Landroidx/fragment/app/FragmentActivity;", "playlistId", "", "playlistName", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;Ljava/lang/String;)V", "PLAYLIST_ID", "PLAYLIST_NAME", "playlistData", "Landroid/os/Bundle;", "playlistSongSearchInitializer", "Lcom/amazon/mp3/detailpages/playlist/playlistSongSearch/PlaylistSongSearchInitializer;", "toastProvider", "Lcom/amazon/mp3/connect/providers/DefaultToastProvider;", "startPlaylistSongSearchActivty", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistSongSearchHandler {
    private final String PLAYLIST_ID;
    private final String PLAYLIST_NAME;
    private final FragmentActivity hostActivity;
    private Bundle playlistData;
    private final String playlistId;
    private final String playlistName;
    private PlaylistSongSearchInitializer playlistSongSearchInitializer;
    private final DefaultToastProvider toastProvider;

    public PlaylistSongSearchHandler(FragmentActivity hostActivity, String playlistId, String playlistName) {
        Intrinsics.checkNotNullParameter(hostActivity, "hostActivity");
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Intrinsics.checkNotNullParameter(playlistName, "playlistName");
        this.hostActivity = hostActivity;
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        DefaultToastProvider defaultToastProvider = new DefaultToastProvider(hostActivity);
        this.toastProvider = defaultToastProvider;
        this.playlistSongSearchInitializer = new PlaylistSongSearchInitializer(defaultToastProvider);
        this.PLAYLIST_ID = "playlistId";
        this.PLAYLIST_NAME = "playlistName";
        this.playlistData = BundleKt.bundleOf(TuplesKt.to("playlistId", playlistId), TuplesKt.to("playlistName", playlistName));
    }

    public final void startPlaylistSongSearchActivty() {
        if (!ConnectivityUtil.hasAnyInternetConnection(this.hostActivity.getApplicationContext())) {
            NoNetworkEmptyPageDialog noNetworkEmptyPageDialog = new NoNetworkEmptyPageDialog(null, 1, null);
            FragmentManager supportFragmentManager = this.hostActivity.getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "hostActivity.supportFragmentManager");
            noNetworkEmptyPageDialog.show(supportFragmentManager, noNetworkEmptyPageDialog.getTag());
            return;
        }
        if (Feature.dragonfly_enabled.isEnabled() || Feature.dragonfly_fireos_enabled.isEnabled()) {
            ReactExperienceNavigationRouteHelper.INSTANCE.navigateToSongSearchRoute(this.playlistData.getString(this.PLAYLIST_ID, ""), this.hostActivity);
        } else {
            this.playlistSongSearchInitializer.setPlaylistData(this.playlistData);
            this.playlistSongSearchInitializer.startSongSearch(this.hostActivity);
        }
    }
}
