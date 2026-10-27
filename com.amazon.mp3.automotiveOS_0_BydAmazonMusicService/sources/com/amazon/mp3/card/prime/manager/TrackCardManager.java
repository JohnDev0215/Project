package com.amazon.mp3.card.prime.manager;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.card.prime.AbstractPrimeItemCardManager;
import com.amazon.mp3.card.prime.ContextMenuHandler;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.card.prime.PrimeCard;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.TrackStateProvider;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.search.view.PrimeTracksLoadedCallbacks;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class TrackCardManager extends AbstractPrimeItemCardManager<PrimeTrack> implements StateProvider.Listener<PrimeTrack>, ContextMenuHandler {
    private final PrimeCard<PrimeTrack> mCard;
    private PrimeTrack mLastOpenedItem;
    private final Fragment mParentFragment;
    private final List<PrimeTrack> mPrimeTracks;
    private final PrimeTrackContextMenuProvider mTrackContextMenuProvider;
    private PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener mTrackProviderListener;
    private PrimeCollectionTask.Task playBackMode;

    @Override // com.amazon.mp3.card.prime.AbstractPrimeItemCardManager
    protected boolean canViewItemDetail() {
        return false;
    }

    public TrackCardManager(Fragment fragment, NetworkErrorDialog.INetworkErrorDialogHandler handler, PrimeCard<PrimeTrack> card, List<PrimeTrack> primeTracks, final List<Map<String, String>> contentInfo, final String pageType) {
        super((BaseActivity) fragment.getActivity(), handler, contentInfo, pageType);
        PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener primeTrackContextMenuProviderListener = new PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener() { // from class: com.amazon.mp3.card.prime.manager.TrackCardManager.1
            @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
            public OnItemFinishedAddingListener getOnAddedListener() {
                return null;
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
            public TrackStateProvider getTrackStateProvider() {
                return (TrackStateProvider) TrackCardManager.this.mCard.getAdapter().getStateProvider();
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
            public void updateTrackStatus(PrimeTrack track) {
                TrackCardManager.this.mCard.getAdapter().updateItemStatus(track);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
            public void updateTrackStatusForPlaylist(PrimeTrack track) {
                TrackCardManager.this.mCard.getAdapter().updateItemStatus(track, TrackCardManager.this);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
            public void setAdapterStatus(PrimeTrack track, boolean adding, boolean downloading) {
                if (adding) {
                    TrackCardManager.this.mCard.getAdapter().updateItemStatus(track);
                }
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
            public ContextMenuPageType getContextMenupageType() {
                return ContextMenuPageType.LONG_PRESS;
            }
        };
        this.mTrackProviderListener = primeTrackContextMenuProviderListener;
        this.mParentFragment = fragment;
        this.mCard = card;
        this.mTrackContextMenuProvider = new PrimeTrackContextMenuProvider(this.mActivity, primeTrackContextMenuProviderListener);
        this.mPrimeTracks = primeTracks;
    }

    @Override // com.amazon.mp3.card.prime.AbstractPrimeItemCardManager
    public void handleOnClick(final PrimeTrack item) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.mActivity, 0, this.mPrimeTracks);
        PrimeTracksLoadedCallbacks primeTracksLoadedCallbacks = new PrimeTracksLoadedCallbacks(this.mParentFragment.getActivity(), arrayAdapter, arrayAdapter.getPosition(item), PlaybackPageType.PRIME_BROWSE_SONGS_LIST, Clock.now(), false, new EligibilityUtil().getPlayMode(item) == PlayModeEnum.SIMILAR);
        PrimeCollectionTask.Task task = this.playBackMode;
        if (task != null) {
            primeTracksLoadedCallbacks.setPlaybackmode(task);
        }
        this.mParentFragment.getActivity().getSupportLoaderManager().initLoader(0, null, primeTracksLoadedCallbacks);
        sendUiClickMetric("playSong", item.getAsin(), EntityIdType.ASIN, getPageType());
    }

    @Override // com.amazon.mp3.card.Card.OnItemClickListener
    public void onLongItemClick(View view, PrimeTrack item) {
        this.mLastOpenedItem = item;
        if (ChildUserUtil.INSTANCE.isChildUser(view.getContext()) && item.getIsExplicit()) {
            return;
        }
        ((ContextMenuSupportingFragment) this.mParentFragment).openContextMenuForView(this, view);
    }

    @Override // com.amazon.mp3.library.provider.StateProvider.Listener
    public void onStateChanged(final PrimeTrack item, final int ownershipState, final int downloadState) {
        this.mTrackContextMenuProvider.addToPlaylist(this.mActivity, item);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public boolean onContextMenuItemSelected(final MenuItem menuItem, final String sourceId) {
        return this.mTrackContextMenuProvider.onContextMenuItemSelected((FragmentActivity) this.mActivity, menuItem, this.mLastOpenedItem, sourceId);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public void onCreateContextMenu(final ContextMenu menu, final String sourceId) {
        this.mCard.getAdapter().updateItemStatus(this.mLastOpenedItem);
        this.mTrackContextMenuProvider.onCreateContextMenu(this.mActivity, menu, this.mLastOpenedItem, 0, sourceId);
    }
}
