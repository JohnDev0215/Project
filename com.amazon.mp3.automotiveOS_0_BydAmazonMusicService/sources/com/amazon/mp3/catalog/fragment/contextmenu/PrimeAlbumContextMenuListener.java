package com.amazon.mp3.catalog.fragment.contextmenu;

import android.text.TextUtils;
import android.view.ContextMenu;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider;
import com.amazon.mp3.library.adapter.DefaultAlbumStateProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;

/* JADX INFO: loaded from: classes3.dex */
public class PrimeAlbumContextMenuListener extends PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener {
    private final FragmentActivity activity;
    private final DefaultAlbumStateProvider defaultAlbumStateProvider;

    @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
    public void removeDownloadAlbumMenuItem(ContextMenu menu) {
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
    public void updateAlbumStatusForPlaylist(PrimeAlbum album) {
    }

    public PrimeAlbumContextMenuListener(FragmentActivity activity) {
        this.activity = activity;
        this.defaultAlbumStateProvider = new DefaultAlbumStateProvider(activity);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
    public StateProvider<PrimeAlbum> getAlbumStateProvider() {
        return this.defaultAlbumStateProvider;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
    public void updateAlbumStatus(PrimeAlbum album) {
        if (TextUtils.isEmpty(album.getAsin())) {
            return;
        }
        this.defaultAlbumStateProvider.requestState(album, (StateProvider.Listener<PrimeAlbum>) null);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
    public void startAlbumPlayback(final PrimeAlbum album) {
        PrimeCollectionTask.createPlaybackTask(this.activity, album.getAsin(), getPlaybackPageType(), new QueryAlbumByAsin(this.activity)).execute();
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
    public void addAlbumForPlaylist(final PrimeAlbum album) {
        PrimeCollectionTask.createAddToPlaylistTask(this.activity, album.getAsin(), new QueryAlbumByAsin(this.activity)).execute();
    }

    public PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.PRIME_BROWSE_ALBUMS_LIST;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
    public ContextMenuPageType getContextMenuPageType() {
        return ContextMenuPageType.LONG_PRESS;
    }
}
