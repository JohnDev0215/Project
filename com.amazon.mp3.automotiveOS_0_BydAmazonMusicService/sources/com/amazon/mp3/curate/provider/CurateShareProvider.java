package com.amazon.mp3.curate.provider;

import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.playlist.api.PlaylistFactory;
import com.amazon.mp3.sharing.ShareProviderActionHandler;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.provider.ShareProvider;
import com.amazon.music.share.UserPlaylistShareProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CurateShareProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateShareProvider;", "Lcom/amazon/music/curate/provider/ShareProvider;", "()V", "sharePlaylist", "", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CurateShareProvider implements ShareProvider {
    @Override // com.amazon.music.curate.provider.ShareProvider
    public void sharePlaylist(Playlist playlist, FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(activity, "activity");
        new UserPlaylistShareProvider(activity, EndpointsProvider.get().getMusicDomain(), playlist.getId(), playlist.getTitle(), playlist.getVersion(), PlaylistFactory.createPlaylistLibrary(activity), ShareProviderActionHandler.newInstance(), playlist.getArtworkUrl()).startShare();
    }
}
