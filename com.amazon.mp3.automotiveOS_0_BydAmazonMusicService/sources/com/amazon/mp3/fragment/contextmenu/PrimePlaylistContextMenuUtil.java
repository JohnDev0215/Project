package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import kotlin.Metadata;

/* JADX INFO: compiled from: PrimePlaylistContextMenuUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JI\u0010\u0003\u001a\u00020\u0004\"\u0010\b\u0000\u0010\u0005*\u0004\u0018\u00010\u0006*\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u0002H\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0010\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000e¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/PrimePlaylistContextMenuUtil;", "", "()V", "addTracksToQueue", "", "D", "Lcom/amazon/music/find/model/search/SearchItem;", "Lcom/amazon/mp3/library/item/CatalogContent;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", Environment.PLAYLIST_PATH, "playNext", "", "playlistStateProvider", "Lcom/amazon/mp3/library/provider/StateProvider;", "Lcom/amazon/mp3/prime/browse/metadata/PrimePlaylist;", "(Landroid/app/Activity;Lcom/amazon/music/find/model/search/SearchItem;ZLcom/amazon/mp3/library/provider/StateProvider;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PrimePlaylistContextMenuUtil {
    public static final PrimePlaylistContextMenuUtil INSTANCE = new PrimePlaylistContextMenuUtil();

    private PrimePlaylistContextMenuUtil() {
    }

    public final <D extends SearchItem & CatalogContent> void addTracksToQueue(Activity activity, D playlist, boolean playNext, StateProvider<PrimePlaylist> playlistStateProvider) {
        if (playlist == null) {
            return;
        }
        if ((playlistStateProvider == null || playlistStateProvider.isInLibrary((PrimePlaylist) playlist)) ? false : true) {
            GetPrimePlaylistTracksTask.createPlaybackTask(activity, playlist.getAsin(), (String) null, PlaybackPageType.PRIME_BROWSE_PLAYLISTS_LIST, playNext ? GetPrimePlaylistTracksTask.Task.PLAY_NEXT : GetPrimePlaylistTracksTask.Task.ADD_TO_PLAYQUEUE).execute(new Void[0]);
        } else {
            NowPlayingQueueUtil.getInstance().addToPlayQueue(MediaProvider.PrimePlaylists.getContentUri("cirrus", playlist.getAsin()), playNext);
        }
    }
}
