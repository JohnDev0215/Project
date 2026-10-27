package com.amazon.mp3.prime.activity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.fragment.contextmenu.StationContextMenuProvider;
import com.amazon.mp3.library.adapter.StationsAdapter;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.prime.PaginatedFragment;
import com.amazon.mp3.prime.browse.pager.PagerProvider;
import com.amazon.mp3.prime.browse.pager.PaginatedRankType;
import com.amazon.mp3.prime.browse.pager.RecommendedStationsPagerProvider;
import com.amazon.mp3.prime.browse.pager.SimilarRecommendedStationsPagerProvider;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.util.ArtworkSizeUtil;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class StationPaginatedFragment extends PaginatedFragment<Station, StationsAdapter, StateProvider<Station>> {
    private StationContextMenuProvider mContextMenuProvider;

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected StateProvider<Station> createStateProvider() {
        return null;
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected int getLayoutResourceId() {
        return R.layout.grid_paginated_fragment;
    }

    public static StationPaginatedFragment newInstance(final PaginatedRankType rankType, final String refinementId, final String headerText) {
        return newInstance(rankType, refinementId, headerText, null);
    }

    public static StationPaginatedFragment newInstance(final PaginatedRankType rankType, final String refinementId, final String headerText, final String asin) {
        return newInstance(rankType, refinementId, headerText, asin, null);
    }

    public static StationPaginatedFragment newInstance(final PaginatedRankType rankType, final String refinementId, final String headerText, final String asin, final String widgetId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("RANK_TYPE_KEY", rankType);
        bundle.putString("REFINEMENT_KEY", refinementId);
        bundle.putString("HEADER_TEXT_KEY", headerText);
        bundle.putString("PAGINATED_FRAGMENT_ASIN_KEY", asin);
        bundle.putString("PAGINATED_FRAGMENT_WIDGET_ID_KEY", widgetId);
        StationPaginatedFragment stationPaginatedFragment = new StationPaginatedFragment();
        stationPaginatedFragment.setArguments(bundle);
        return stationPaginatedFragment;
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected View.OnCreateContextMenuListener getOnCreateContextMenuListener() {
        return new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.prime.activity.StationPaginatedFragment.1
            @Override // android.view.View.OnCreateContextMenuListener
            public void onCreateContextMenu(final ContextMenu contextMenu, final View view, final ContextMenu.ContextMenuInfo contextMenuInfo) {
                if (contextMenuInfo instanceof AdapterView.AdapterContextMenuInfo) {
                    AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) contextMenuInfo;
                    StationPaginatedFragment.this.mContextMenuProvider.onCreateContextMenu(StationPaginatedFragment.this.getActivity(), contextMenu, ((StationsAdapter.ViewHolder) adapterContextMenuInfo.targetView.getTag()).mStation, adapterContextMenuInfo.position, "cirrus");
                }
            }
        };
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected PagerProvider createPagerProvider() {
        PaginatedRankType paginatedRankType = this.mRankType;
        if (paginatedRankType == PaginatedRankType.RECOMMENDED) {
            return new RecommendedStationsPagerProvider(this.mWidgetId);
        }
        if (paginatedRankType == PaginatedRankType.SIMILAR) {
            return new SimilarRecommendedStationsPagerProvider(this.mAsin);
        }
        throw new IllegalArgumentException("Invalid RankType used to create StationPaginatedFragment");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.prime.PaginatedFragment
    public StationsAdapter createAdapter() {
        return new StationsAdapter(getActivity(), ArtworkSizeUtil.getGridArtworkSize(getActivity()), this.mPagerProvider.getRankType() == PaginatedRankType.RECOMMENDED, this.mOverflowListener);
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected void createContextMenuProvider() {
        this.mContextMenuProvider = new StationContextMenuProvider((BaseActivity) getActivity());
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        if (adapterContextMenuInfo == null) {
            return false;
        }
        return this.mContextMenuProvider.onContextMenuItemSelected(getActivity(), menuItem, ((StationsAdapter.ViewHolder) adapterContextMenuInfo.targetView.getTag()).mStation, "cirrus");
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected List<Station> transformItemsFromResponse(List items) {
        return PrimeItemsTransformationUtil.toStationListFromRecommendations(items);
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected void updateAdapterOnUiThread(final List<Station> items) {
        getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.prime.activity.StationPaginatedFragment.2
            @Override // java.lang.Runnable
            public void run() {
                ((StationsAdapter) ((PaginatedFragment) StationPaginatedFragment.this).mAdapter).addAll(items);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.prime.PaginatedFragment
    public void handleOnClick(final Station station, final int position) {
        sendUiClickMetric("playStation", station.getKey(), EntityIdType.STATION_KEY, "BROWSE_RECOMMENDED", position);
        StationUtils.playStation(getActivity(), station, PlaybackPageType.PRIME_BROWSE_STATIONS_LIST);
    }
}
