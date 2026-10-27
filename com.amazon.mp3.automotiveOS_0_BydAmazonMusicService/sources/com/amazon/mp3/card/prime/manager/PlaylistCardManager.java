package com.amazon.mp3.card.prime.manager;

import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.card.prime.AbstractPrimeItemCardManager;
import com.amazon.mp3.card.prime.ContextMenuHandler;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.card.prime.PrimeCard;
import com.amazon.mp3.detailpages.playlist.PlaylistDetailFactory;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistCardManager extends AbstractPrimeItemCardManager<PrimePlaylist> implements StateProvider.Listener<PrimePlaylist>, ContextMenuHandler {
    private final PrimeCard<PrimePlaylist> mCard;
    private final Fragment mHostFragment;
    private PrimePlaylist mLastOpenedPlaylist;
    private final NavigationProvider mNavigationProvider;
    private final PrimePlaylistContextMenuProvider mPlaylistContextMenuProvider;
    private PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener mPlaylistContextMenuProviderListener;

    @Override // com.amazon.mp3.card.prime.AbstractPrimeItemCardManager
    protected boolean canViewItemDetail() {
        return true;
    }

    @Override // com.amazon.mp3.library.provider.StateProvider.Listener
    public void onStateChanged(PrimePlaylist item, int ownershipState, int downloadState) {
    }

    public PlaylistCardManager(Fragment fragment, NavigationProvider navigationProvider, NetworkErrorDialog.INetworkErrorDialogHandler handler, PrimeCard<PrimePlaylist> card) {
        this(fragment, navigationProvider, handler, card, null, null);
    }

    public PlaylistCardManager(Fragment fragment, NavigationProvider navigationProvider, NetworkErrorDialog.INetworkErrorDialogHandler handler, PrimeCard<PrimePlaylist> card, final List<Map<String, String>> contentInfo, final String pageType) {
        super((BaseActivity) fragment.getActivity(), handler, contentInfo, pageType);
        PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener primePlaylistContextMenuProviderListener = new PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener() { // from class: com.amazon.mp3.card.prime.manager.PlaylistCardManager.1
            @Override // com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener
            public StateProvider<PrimePlaylist> getPlaylistStateProvider() {
                return PlaylistCardManager.this.mCard.getAdapter().getStateProvider();
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener
            public void updatePlaylistStatus(PrimePlaylist playlist) {
                PlaylistCardManager.this.mCard.getAdapter().updateItemStatus(playlist);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener
            public void startPlaylistPlayback(PrimePlaylist playlist) {
                GetPrimePlaylistTracksTask.createPlaybackTask(((AbstractPrimeItemCardManager) PlaylistCardManager.this).mActivity, playlist.getAsin(), PlaybackPageType.PRIME_BROWSE_PLAYLISTS_LIST, false).execute(new Void[0]);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener
            public PlaybackPageType getPlaybackPageType() {
                return PlaybackPageType.PRIME_BROWSE_PLAYLISTS_LIST;
            }
        };
        this.mPlaylistContextMenuProviderListener = primePlaylistContextMenuProviderListener;
        this.mHostFragment = fragment;
        this.mNavigationProvider = navigationProvider;
        this.mCard = card;
        this.mPlaylistContextMenuProvider = new PrimePlaylistContextMenuProvider(this.mActivity, primePlaylistContextMenuProviderListener, ContextMenuPageType.LONG_PRESS);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public boolean onContextMenuItemSelected(MenuItem menuItem, String sourceId) {
        return this.mPlaylistContextMenuProvider.onContextMenuItemSelected((FragmentActivity) this.mActivity, menuItem, this.mLastOpenedPlaylist, sourceId);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public void onCreateContextMenu(ContextMenu menu, String sourceId) {
        this.mCard.getAdapter().updateItemStatus(this.mLastOpenedPlaylist);
        this.mPlaylistContextMenuProvider.onCreateContextMenu(this.mActivity, menu, this.mLastOpenedPlaylist, 0, sourceId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.card.prime.AbstractPrimeItemCardManager
    public void handleOnClick(PrimePlaylist playlist) {
        String asin = playlist.getAsin();
        Uri contentUri = MediaProvider.PrimePlaylists.getContentUri("cirrus", asin);
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus");
        bundle.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        bundle.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        bundle.putBoolean("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
        this.mNavigationProvider.changeScreenFragment(PlaylistDetailFactory.createFragment(asin, playlist.getTitle(), false, bundle), true, false, true);
        sendUiClickMetric("selectPlaylist", playlist.getAsin(), EntityIdType.ASIN, getPageType());
    }

    @Override // com.amazon.mp3.card.Card.OnItemClickListener
    public void onLongItemClick(View view, PrimePlaylist item) {
        this.mLastOpenedPlaylist = item;
        ((ContextMenuSupportingFragment) this.mHostFragment).openContextMenuForView(this, view);
    }
}
