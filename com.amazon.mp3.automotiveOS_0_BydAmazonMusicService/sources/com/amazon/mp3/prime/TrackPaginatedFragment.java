package com.amazon.mp3.prime;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider;
import com.amazon.mp3.library.adapter.DefaultTrackStateProvider;
import com.amazon.mp3.library.adapter.SongTileAdapter;
import com.amazon.mp3.library.adapter.TileAdapter;
import com.amazon.mp3.library.fragment.AddAndDownloadTrackTask;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.TrackStateProvider;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.browse.pager.BrowseTrackPagerProvider;
import com.amazon.mp3.prime.browse.pager.PagerProvider;
import com.amazon.mp3.prime.browse.pager.PaginatedRankType;
import com.amazon.mp3.prime.browse.pager.RecommendedTrackPagerProvider;
import com.amazon.mp3.prime.browse.pager.SimilarRecommendedTrackPagerProvider;
import com.amazon.mp3.search.view.PrimeTracksLoadedCallbacks;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.LinkType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class TrackPaginatedFragment extends PaginatedFragment<PrimeTrack, SongTileAdapter, TrackStateProvider> {
    private static final String TAG = TrackPaginatedFragment.class.getSimpleName();
    private PrimeTrackContextMenuProvider mContextMenuProvider;
    private PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener mProviderListener = new PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener() { // from class: com.amazon.mp3.prime.TrackPaginatedFragment.1
        @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
        public OnItemFinishedAddingListener getOnAddedListener() {
            return null;
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
        public TrackStateProvider getTrackStateProvider() {
            return (TrackStateProvider) TrackPaginatedFragment.this.mStateProvider;
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
        public void updateTrackStatus(PrimeTrack track) {
            ((SongTileAdapter) TrackPaginatedFragment.this.mAdapter).updateTrackStatus(track);
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
        public void updateTrackStatusForPlaylist(PrimeTrack track) {
            ((SongTileAdapter) TrackPaginatedFragment.this.mAdapter).updateTrackStatus(track);
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
        public void setAdapterStatus(PrimeTrack track, boolean adding, boolean downloading) {
            if (adding) {
                ((SongTileAdapter) TrackPaginatedFragment.this.mAdapter).setAdded(track);
            }
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
        public ContextMenuPageType getContextMenupageType() {
            return ContextMenuPageType.LONG_PRESS;
        }
    };
    private final TileAdapter.ItemButtonListener mAddListener = new TileAdapter.ItemButtonListener() { // from class: com.amazon.mp3.prime.TrackPaginatedFragment.2
        @Override // com.amazon.mp3.library.adapter.TileAdapter.ItemButtonListener
        public void onClick(View v, int position) {
            PrimeTrack primeTrack = (PrimeTrack) ((SongTileAdapter) TrackPaginatedFragment.this.mAdapter).getItem(position);
            if (ContentAccessUtil.canContinueCatalogAction(TrackPaginatedFragment.this.getActivity(), primeTrack, ContentAccessUtil.ContentAccessOperation.INTERACT) && ConnectivityUtil.checkConnectivityAndShowDialog(TrackPaginatedFragment.this.getActivity())) {
                ((SongTileAdapter) TrackPaginatedFragment.this.mAdapter).setAdded(primeTrack);
                ((TrackStateProvider) TrackPaginatedFragment.this.mStateProvider).setAddingToLibrary(primeTrack, true);
                new AddAndDownloadTrackTask((Context) TrackPaginatedFragment.this.getActivity(), primeTrack.getAsin(), false, true, (OnItemFinishedAddingListener) null).execute(new Void[0]);
                MetricsLogger.clickedAddPrimeContent(LinkType.TRACK, primeTrack.getAsin(), SelectionSourceType.SONGS, null);
            }
        }
    };

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected int getLayoutResourceId() {
        return R.layout.list_paginated_fragment;
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    public static final TrackPaginatedFragment newInstance(final PaginatedRankType rankType, final String refinementId, final String headerText, final MediaProvider.PrimeBrowseTracksCollection.ContentSource contentSource) {
        return newInstance(rankType, refinementId, headerText, contentSource, null);
    }

    public static final TrackPaginatedFragment newInstance(final PaginatedRankType rankType, final String refinementId, final String headerText, final MediaProvider.PrimeBrowseTracksCollection.ContentSource contentSource, final String asin) {
        return newInstance(rankType, refinementId, headerText, contentSource, asin, null);
    }

    public static final TrackPaginatedFragment newInstance(final PaginatedRankType rankType, final String refinementId, final String headerText, final MediaProvider.PrimeBrowseTracksCollection.ContentSource contentSource, final String asin, final String widgetId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("RANK_TYPE_KEY", rankType);
        bundle.putString("REFINEMENT_KEY", refinementId);
        bundle.putString("HEADER_TEXT_KEY", headerText);
        bundle.putString("CONTENT_SOURCE", contentSource.toString());
        bundle.putString("PAGINATED_FRAGMENT_ASIN_KEY", asin);
        bundle.putString("PAGINATED_FRAGMENT_WIDGET_ID_KEY", widgetId);
        TrackPaginatedFragment trackPaginatedFragment = new TrackPaginatedFragment();
        trackPaginatedFragment.setArguments(bundle);
        return trackPaginatedFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        if (adapterContextMenuInfo == null) {
            return false;
        }
        return this.mContextMenuProvider.onContextMenuItemSelected(getActivity(), menuItem, ((SongTileAdapter.ViewHolder) adapterContextMenuInfo.targetView.getTag()).mPrimeTrack, "cirrus");
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected PagerProvider createPagerProvider() {
        PaginatedRankType paginatedRankType = this.mRankType;
        if (paginatedRankType == PaginatedRankType.RECOMMENDED) {
            return new RecommendedTrackPagerProvider(this.mWidgetId);
        }
        if (paginatedRankType == PaginatedRankType.SIMILAR) {
            return new SimilarRecommendedTrackPagerProvider(this.mAsin);
        }
        return new BrowseTrackPagerProvider(paginatedRankType, this.mRefinementId);
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected void createContextMenuProvider() {
        this.mContextMenuProvider = new PrimeTrackContextMenuProvider((BaseActivity) getActivity(), this.mProviderListener);
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected View.OnCreateContextMenuListener getOnCreateContextMenuListener() {
        return new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.prime.TrackPaginatedFragment.3
            @Override // android.view.View.OnCreateContextMenuListener
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                if (menuInfo instanceof AdapterView.AdapterContextMenuInfo) {
                    AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                    SongTileAdapter.ViewHolder viewHolder = (SongTileAdapter.ViewHolder) adapterContextMenuInfo.targetView.getTag();
                    PrimeTrack primeTrack = viewHolder.mPrimeTrack;
                    if (ChildUserUtil.INSTANCE.isChildUser(TrackPaginatedFragment.this.getContext()) && viewHolder.mPrimeTrack.getIsExplicit()) {
                        return;
                    }
                    TrackPaginatedFragment.this.mContextMenuProvider.onCreateContextMenu(TrackPaginatedFragment.this.getActivity(), menu, primeTrack, adapterContextMenuInfo.position, "cirrus");
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.prime.PaginatedFragment
    public TrackStateProvider createStateProvider() {
        return new DefaultTrackStateProvider(getContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.prime.PaginatedFragment
    public SongTileAdapter createAdapter() {
        return new SongTileAdapter(getContext(), (TrackStateProvider) this.mStateProvider, this.mAddListener, this.mOverflowListener, getResources().getDimensionPixelSize(R.dimen.song_tile_art_size), this.mPagerProvider.getRankType() == PaginatedRankType.POPULAR, this.mPagerProvider.getRankType() == PaginatedRankType.RECOMMENDED, Branding.shouldShowPrimeBranding());
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected List<PrimeTrack> transformItemsFromResponse(List items) {
        PaginatedRankType paginatedRankType = this.mRankType;
        if (paginatedRankType == PaginatedRankType.RECOMMENDED || paginatedRankType == PaginatedRankType.SIMILAR) {
            return PrimeItemsTransformationUtil.toPrimeTrackListFromRecommendations(items);
        }
        return PrimeItemsTransformationUtil.toPrimeTrackList(items);
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected void updateAdapterOnUiThread(final List<PrimeTrack> items) {
        getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.prime.TrackPaginatedFragment.4
            @Override // java.lang.Runnable
            public void run() {
                ((SongTileAdapter) TrackPaginatedFragment.this.mAdapter).addAll(items);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.prime.PaginatedFragment
    public void handleOnClick(final PrimeTrack track, final int position) {
        MediaProvider.PrimeBrowseTracksCollection.ContentSource.fromString(getArguments().getString("CONTENT_SOURCE"));
        sendUiClickMetric("playSong", track.getAsin(), EntityIdType.ASIN, "BROWSE_RECOMMENDED", position);
        FragmentActivity activity = getActivity();
        T t = this.mAdapter;
        getActivity().getSupportLoaderManager().initLoader(0, null, new PrimeTracksLoadedCallbacks(activity, (ArrayAdapter<PrimeTrack>) t, ((SongTileAdapter) t).getPosition(track), PlaybackPageType.PRIME_BROWSE_SONGS_LIST, Clock.now()));
    }
}
