package com.amazon.mp3.prime;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider;
import com.amazon.mp3.library.adapter.AlbumTileAdapter;
import com.amazon.mp3.library.adapter.DefaultAlbumStateProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.pager.BrowseAlbumPagerProvider;
import com.amazon.mp3.prime.browse.pager.PagerProvider;
import com.amazon.mp3.prime.browse.pager.PaginatedRankType;
import com.amazon.mp3.prime.browse.pager.RecommendedAlbumPagerProvider;
import com.amazon.mp3.prime.browse.pager.SimilarRecommendedAlbumPagerProvider;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.search.view.SearchFragmentUtils;
import com.amazon.mp3.util.ArtworkSizeUtil;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class AlbumPaginatedFragment extends PaginatedFragment<PrimeAlbum, AlbumTileAdapter, StateProvider<PrimeAlbum>> {
    private static final String TAG = AlbumPaginatedFragment.class.getSimpleName();
    private PrimeAlbumContextMenuProvider mContextMenuProvider;
    private PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener mProviderListener = new PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener() { // from class: com.amazon.mp3.prime.AlbumPaginatedFragment.1
        @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
        public void removeDownloadAlbumMenuItem(ContextMenu menu) {
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
        public void updateAlbumStatusForPlaylist(PrimeAlbum album) {
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
        public StateProvider<PrimeAlbum> getAlbumStateProvider() {
            return AlbumPaginatedFragment.this.mStateProvider;
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
        public void updateAlbumStatus(PrimeAlbum album) {
            ((AlbumTileAdapter) AlbumPaginatedFragment.this.mAdapter).updateAlbumStatus(album);
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
        public void startAlbumPlayback(final PrimeAlbum album) {
            PrimeCollectionTask.createPlaybackTask(AlbumPaginatedFragment.this.getActivity(), album.getAsin(), PlaybackPageType.PRIME_BROWSE_ALBUMS_LIST, new QueryAlbumByAsin(AlbumPaginatedFragment.this.getActivity())).execute();
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
        public void addAlbumForPlaylist(final PrimeAlbum album) {
            PrimeCollectionTask.createAddToPlaylistTask(AlbumPaginatedFragment.this.getActivity(), album.getAsin(), new QueryAlbumByAsin(AlbumPaginatedFragment.this.getActivity())).execute();
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
        public ContextMenuPageType getContextMenuPageType() {
            return ContextMenuPageType.LONG_PRESS;
        }
    };

    @Override // com.amazon.mp3.prime.PaginatedFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    public static AlbumPaginatedFragment newInstance(final PaginatedRankType rankType, final String refinementId, final String headerText) {
        return newInstance(rankType, refinementId, headerText, null);
    }

    public static AlbumPaginatedFragment newInstance(final PaginatedRankType rankType, final String refinementId, final String headerText, final String asin) {
        return newInstance(rankType, refinementId, headerText, asin, null);
    }

    public static AlbumPaginatedFragment newInstance(final PaginatedRankType rankType, final String refinementId, final String headerText, final String asin, final String widgetId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("RANK_TYPE_KEY", rankType);
        bundle.putString("REFINEMENT_KEY", refinementId);
        bundle.putString("HEADER_TEXT_KEY", headerText);
        bundle.putString("PAGINATED_FRAGMENT_ASIN_KEY", asin);
        bundle.putString("PAGINATED_FRAGMENT_WIDGET_ID_KEY", widgetId);
        AlbumPaginatedFragment albumPaginatedFragment = new AlbumPaginatedFragment();
        albumPaginatedFragment.setArguments(bundle);
        return albumPaginatedFragment;
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected PagerProvider createPagerProvider() {
        PaginatedRankType paginatedRankType = this.mRankType;
        if (paginatedRankType == PaginatedRankType.RECOMMENDED) {
            return new RecommendedAlbumPagerProvider(this.mWidgetId);
        }
        if (paginatedRankType == PaginatedRankType.SIMILAR) {
            return new SimilarRecommendedAlbumPagerProvider(this.mAsin);
        }
        return new BrowseAlbumPagerProvider(paginatedRankType, this.mRefinementId);
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected void createContextMenuProvider() {
        this.mContextMenuProvider = new PrimeAlbumContextMenuProvider((BaseActivity) getActivity(), this.mProviderListener);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        if (adapterContextMenuInfo == null) {
            return false;
        }
        return this.mContextMenuProvider.onContextMenuItemSelected(getActivity(), menuItem, ((AlbumTileAdapter.ViewHolder) adapterContextMenuInfo.targetView.getTag()).mPrimeAlbum, "cirrus");
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected View.OnCreateContextMenuListener getOnCreateContextMenuListener() {
        return new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.prime.AlbumPaginatedFragment.2
            @Override // android.view.View.OnCreateContextMenuListener
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                if (menuInfo instanceof AdapterView.AdapterContextMenuInfo) {
                    AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                    AlbumPaginatedFragment.this.mContextMenuProvider.onCreateContextMenu(AlbumPaginatedFragment.this.getActivity(), menu, ((AlbumTileAdapter.ViewHolder) adapterContextMenuInfo.targetView.getTag()).mPrimeAlbum, adapterContextMenuInfo.position, "cirrus");
                }
            }
        };
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected StateProvider<PrimeAlbum> createStateProvider() {
        return new DefaultAlbumStateProvider(getContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.prime.PaginatedFragment
    public AlbumTileAdapter createAdapter() {
        boolean z = this.mPagerProvider.getRankType() == PaginatedRankType.POPULAR;
        return new AlbumTileAdapter(getContext(), this.mOverflowListener, ArtworkSizeUtil.getGridArtworkSize(getActivity()), this.mStateProvider, !z, z, this.mPagerProvider.getRankType() == PaginatedRankType.RECOMMENDED, Branding.shouldShowPrimeBranding());
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected List<PrimeAlbum> transformItemsFromResponse(List items) {
        PaginatedRankType paginatedRankType = this.mRankType;
        if (paginatedRankType == PaginatedRankType.RECOMMENDED || paginatedRankType == PaginatedRankType.SIMILAR) {
            return PrimeItemsTransformationUtil.toPrimeAlbumListFromRecommendations(items);
        }
        return PrimeItemsTransformationUtil.toPrimeAlbumList(items);
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected void updateAdapterOnUiThread(final List<PrimeAlbum> items) {
        getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.prime.AlbumPaginatedFragment.3
            @Override // java.lang.Runnable
            public void run() {
                ((AlbumTileAdapter) AlbumPaginatedFragment.this.mAdapter).addAll(items);
            }
        });
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected int getLayoutResourceId() {
        return this.mPagerProvider.getRankType() == PaginatedRankType.POPULAR ? R.layout.list_paginated_fragment : R.layout.grid_paginated_fragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.prime.PaginatedFragment
    public void handleOnClick(final PrimeAlbum album, final int position) {
        Fragment albumDetailsFragment = SearchFragmentUtils.getAlbumDetailsFragment(getActivity(), album);
        sendUiClickMetric("selectAlbum", album.getAsin(), EntityIdType.ASIN, "BROWSE_RECOMMENDED", position);
        ((MusicHomeActivity) getActivity()).changeScreenFragment(albumDetailsFragment, true, false, true);
    }
}
