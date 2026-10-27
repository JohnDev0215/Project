package com.amazon.mp3.catalog.fragment.contextmenu;

import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider;
import com.amazon.mp3.library.adapter.DefaultPlaylistStateProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;

/* JADX INFO: loaded from: classes3.dex */
public class PrimePlaylistContextMenuListener extends PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener {
    private final FragmentActivity activity;
    private final DefaultPlaylistStateProvider defaultPlaylistStateProvider;

    public PrimePlaylistContextMenuListener(FragmentActivity activity) {
        this.activity = activity;
        this.defaultPlaylistStateProvider = new DefaultPlaylistStateProvider(activity);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener
    public StateProvider<PrimePlaylist> getPlaylistStateProvider() {
        return this.defaultPlaylistStateProvider;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener
    public void updatePlaylistStatus(PrimePlaylist playlist) {
        if ("".equals(playlist.getAsin())) {
            return;
        }
        this.defaultPlaylistStateProvider.requestState(playlist, (StateProvider.Listener<PrimePlaylist>) null);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener
    public void startPlaylistPlayback(PrimePlaylist playlist) {
        GetPrimePlaylistTracksTask.createPlaybackTask(this.activity, playlist.getAsin(), getPlaybackPageType(), false).execute(new Void[0]);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener
    public PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.PRIME_BROWSE_PLAYLISTS_LIST;
    }
}
