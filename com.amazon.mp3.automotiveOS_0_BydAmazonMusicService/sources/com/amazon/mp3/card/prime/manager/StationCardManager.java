package com.amazon.mp3.card.prime.manager;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.card.prime.AbstractPrimeItemCardManager;
import com.amazon.mp3.card.prime.ContextMenuHandler;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.fragment.contextmenu.StationContextMenuProvider;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.search.model.Station;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class StationCardManager extends AbstractPrimeItemCardManager<Station> implements ContextMenuHandler {
    private Station mLastOpenedItem;
    private final Fragment mParentFragment;
    private final PlaybackPageType mPlaybackPageType;
    private final StationContextMenuProvider mStationContextMenuProvider;

    @Override // com.amazon.mp3.card.prime.AbstractPrimeItemCardManager
    protected boolean canViewItemDetail() {
        return false;
    }

    public StationCardManager(final Fragment fragment, final NetworkErrorDialog.INetworkErrorDialogHandler handler, final PlaybackPageType playbackPageType) {
        this(fragment, handler, playbackPageType, null, null);
    }

    public StationCardManager(final Fragment fragment, final NetworkErrorDialog.INetworkErrorDialogHandler handler, final PlaybackPageType playbackPageType, final List<Map<String, String>> contentInfo, final String pageType) {
        super((BaseActivity) fragment.getActivity(), handler, contentInfo, pageType);
        this.mParentFragment = fragment;
        this.mStationContextMenuProvider = new StationContextMenuProvider(this.mActivity);
        this.mPlaybackPageType = playbackPageType;
    }

    @Override // com.amazon.mp3.card.prime.AbstractPrimeItemCardManager
    public void handleOnClick(final Station station) {
        StationUtils.playStation(this.mActivity, station, this.mPlaybackPageType);
        sendUiClickMetric("playStation", station.getKey(), EntityIdType.STATION_KEY, getPageType());
    }

    @Override // com.amazon.mp3.card.Card.OnItemClickListener
    public void onLongItemClick(View view, Station item) {
        this.mLastOpenedItem = item;
        ((ContextMenuSupportingFragment) this.mParentFragment).openContextMenuForView(this, view);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public void onCreateContextMenu(ContextMenu menu, String sourceId) {
        this.mStationContextMenuProvider.onCreateContextMenu(this.mActivity, menu, this.mLastOpenedItem, 0, sourceId);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public boolean onContextMenuItemSelected(final MenuItem menuItem, final String sourceId) {
        return this.mStationContextMenuProvider.onContextMenuItemSelected((FragmentActivity) this.mActivity, menuItem, this.mLastOpenedItem, sourceId);
    }
}
