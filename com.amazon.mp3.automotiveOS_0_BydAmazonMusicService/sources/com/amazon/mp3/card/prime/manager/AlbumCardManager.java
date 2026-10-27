package com.amazon.mp3.card.prime.manager;

import android.content.Context;
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
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.navigation.AlbumDestinationHandler;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.music.destination.AlbumDestination;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumCardManager extends AbstractPrimeItemCardManager<PrimeAlbum> implements StateProvider.Listener<PrimeAlbum>, ContextMenuHandler {
    private final PrimeAlbumContextMenuProvider mAlbumContextMenuProvider;
    private PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener mAlbumProviderListener;
    private final PrimeCard<PrimeAlbum> mCard;
    private boolean mForceReplace;
    private PrimeAlbum mLastOpenedItem;
    private final Fragment mParentFragment;

    @Override // com.amazon.mp3.card.prime.AbstractPrimeItemCardManager
    protected boolean canViewItemDetail() {
        return true;
    }

    public AlbumCardManager(Fragment fragment, NetworkErrorDialog.INetworkErrorDialogHandler handler, PrimeCard<PrimeAlbum> card, final List<Map<String, String>> contentInfo, final String pageType) {
        this(fragment, handler, card, null, null, false);
    }

    public AlbumCardManager(Fragment fragment, NetworkErrorDialog.INetworkErrorDialogHandler handler, PrimeCard<PrimeAlbum> card, final List<Map<String, String>> contentInfo, final String pageType, final boolean forceReplace) {
        super((BaseActivity) fragment.getActivity(), handler, contentInfo, pageType);
        PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener primeAlbumContextMenuProviderListener = new PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener() { // from class: com.amazon.mp3.card.prime.manager.AlbumCardManager.1
            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public void removeDownloadAlbumMenuItem(ContextMenu menu) {
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public StateProvider<PrimeAlbum> getAlbumStateProvider() {
                return AlbumCardManager.this.mCard.getAdapter().getStateProvider();
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public void updateAlbumStatus(PrimeAlbum album) {
                AlbumCardManager.this.mCard.getAdapter().updateItemStatus(album);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public void updateAlbumStatusForPlaylist(PrimeAlbum album) {
                AlbumCardManager.this.mCard.getAdapter().updateItemStatus(album, AlbumCardManager.this);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public void startAlbumPlayback(final PrimeAlbum album) {
                PrimeCollectionTask.createPlaybackTask(((AbstractPrimeItemCardManager) AlbumCardManager.this).mActivity, album.getAsin(), PlaybackPageType.PRIME_BROWSE_ALBUMS_LIST, new QueryAlbumByAsin(((AbstractPrimeItemCardManager) AlbumCardManager.this).mActivity)).execute();
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public void addAlbumForPlaylist(final PrimeAlbum album) {
                PrimeCollectionTask.createAddToPlaylistTask(((AbstractPrimeItemCardManager) AlbumCardManager.this).mActivity, album.getAsin(), new QueryAlbumByAsin(((AbstractPrimeItemCardManager) AlbumCardManager.this).mActivity)).execute();
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public ContextMenuPageType getContextMenuPageType() {
                return ContextMenuPageType.LONG_PRESS;
            }
        };
        this.mAlbumProviderListener = primeAlbumContextMenuProviderListener;
        this.mParentFragment = fragment;
        this.mCard = card;
        this.mAlbumContextMenuProvider = new PrimeAlbumContextMenuProvider(this.mActivity, primeAlbumContextMenuProviderListener);
        this.mForceReplace = forceReplace;
    }

    @Override // com.amazon.mp3.card.prime.AbstractPrimeItemCardManager
    public void handleOnClick(final PrimeAlbum album) {
        new AlbumDestinationHandler().navigateTo((Context) this.mActivity, new AlbumDestination(album.getAsin(), null, null, null, null));
        sendUiClickMetric("selectAlbum", album.getAsin(), EntityIdType.ASIN, getPageType());
    }

    @Override // com.amazon.mp3.card.Card.OnItemClickListener
    public void onLongItemClick(View view, PrimeAlbum item) {
        this.mLastOpenedItem = item;
        ((ContextMenuSupportingFragment) this.mParentFragment).openContextMenuForView(this, view);
    }

    @Override // com.amazon.mp3.library.provider.StateProvider.Listener
    public void onStateChanged(final PrimeAlbum item, final int ownershipState, final int downloadState) {
        this.mAlbumContextMenuProvider.addToPlaylist(this.mActivity, item);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public boolean onContextMenuItemSelected(final MenuItem menuItem, final String sourceId) {
        return this.mAlbumContextMenuProvider.onContextMenuItemSelected((FragmentActivity) this.mActivity, menuItem, this.mLastOpenedItem, sourceId);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public void onCreateContextMenu(final ContextMenu menu, final String sourceId) {
        this.mCard.getAdapter().updateItemStatus(this.mLastOpenedItem);
        this.mAlbumContextMenuProvider.onCreateContextMenu(this.mActivity, menu, this.mLastOpenedItem, 0, sourceId);
    }
}
