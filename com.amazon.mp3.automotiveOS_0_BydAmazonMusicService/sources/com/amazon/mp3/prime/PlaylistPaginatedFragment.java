package com.amazon.mp3.prime;

import android.net.Uri;
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
import com.amazon.mp3.detailpages.playlist.PlaylistDetailFactory;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider;
import com.amazon.mp3.library.adapter.DefaultPlaylistStateProvider;
import com.amazon.mp3.library.adapter.PlaylistTileAdapter;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.pager.BrowsePlaylistPagerProvider;
import com.amazon.mp3.prime.browse.pager.PagerProvider;
import com.amazon.mp3.prime.browse.pager.PaginatedRankType;
import com.amazon.mp3.prime.browse.pager.RecommendedPlaylistPagerProvider;
import com.amazon.mp3.prime.browse.pager.SimilarRecommendedPlaylistPagerProvider;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class PlaylistPaginatedFragment extends PaginatedFragment<PrimePlaylist, PlaylistTileAdapter, StateProvider<PrimePlaylist>> {
    protected static final String TAG = PlaylistPaginatedFragment.class.getSimpleName();
    private PrimePlaylistContextMenuProvider mContextMenuProvider;
    private PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener mProviderListener = new PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener() { // from class: com.amazon.mp3.prime.PlaylistPaginatedFragment.1
        @Override // com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener
        public StateProvider<PrimePlaylist> getPlaylistStateProvider() {
            return PlaylistPaginatedFragment.this.mStateProvider;
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener
        public void updatePlaylistStatus(PrimePlaylist playlist) {
            ((PlaylistTileAdapter) PlaylistPaginatedFragment.this.mAdapter).updatePlaylistStatus(playlist);
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener
        public void startPlaylistPlayback(PrimePlaylist playlist) {
            GetPrimePlaylistTracksTask.createPlaybackTask(PlaylistPaginatedFragment.this.getActivity(), playlist.getAsin(), PlaybackPageType.PRIME_BROWSE_PLAYLISTS_LIST, false).execute(new Void[0]);
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener
        public PlaybackPageType getPlaybackPageType() {
            return PlaybackPageType.PRIME_BROWSE_PLAYLISTS_LIST;
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

    public static PlaylistPaginatedFragment newInstance(final PaginatedRankType rankType, final String refinementId, final String headerText) {
        return newInstance(rankType, refinementId, headerText, null);
    }

    public static PlaylistPaginatedFragment newInstance(final PaginatedRankType rankType, final String refinementId, final String headerText, final String asin) {
        return newInstance(rankType, refinementId, headerText, asin, null);
    }

    public static PlaylistPaginatedFragment newInstance(final PaginatedRankType rankType, final String refinementId, final String headerText, final String asin, final String widgetId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("RANK_TYPE_KEY", rankType);
        bundle.putString("REFINEMENT_KEY", refinementId);
        bundle.putString("HEADER_TEXT_KEY", headerText);
        bundle.putString("PAGINATED_FRAGMENT_ASIN_KEY", asin);
        bundle.putString("PAGINATED_FRAGMENT_WIDGET_ID_KEY", widgetId);
        PlaylistPaginatedFragment playlistPaginatedFragment = new PlaylistPaginatedFragment();
        playlistPaginatedFragment.setArguments(bundle);
        return playlistPaginatedFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        if (adapterContextMenuInfo == null) {
            return false;
        }
        return this.mContextMenuProvider.onContextMenuItemSelected(getActivity(), menuItem, ((PlaylistTileAdapter.ViewHolder) adapterContextMenuInfo.targetView.getTag()).mPrimePlaylist, "cirrus");
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected PagerProvider createPagerProvider() {
        PaginatedRankType paginatedRankType = this.mRankType;
        if (paginatedRankType == PaginatedRankType.RECOMMENDED) {
            return new RecommendedPlaylistPagerProvider(this.mWidgetId);
        }
        if (paginatedRankType == PaginatedRankType.SIMILAR) {
            return new SimilarRecommendedPlaylistPagerProvider(this.mAsin);
        }
        return new BrowsePlaylistPagerProvider(paginatedRankType, this.mRefinementId);
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected void createContextMenuProvider() {
        this.mContextMenuProvider = new PrimePlaylistContextMenuProvider((BaseActivity) getActivity(), this.mProviderListener, ContextMenuPageType.LONG_PRESS);
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected View.OnCreateContextMenuListener getOnCreateContextMenuListener() {
        return new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.prime.PlaylistPaginatedFragment.2
            @Override // android.view.View.OnCreateContextMenuListener
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                if (menuInfo instanceof AdapterView.AdapterContextMenuInfo) {
                    AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                    PlaylistPaginatedFragment.this.mContextMenuProvider.onCreateContextMenu(PlaylistPaginatedFragment.this.getActivity(), menu, ((PlaylistTileAdapter.ViewHolder) adapterContextMenuInfo.targetView.getTag()).mPrimePlaylist, adapterContextMenuInfo.position, "cirrus");
                }
            }
        };
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected StateProvider<PrimePlaylist> createStateProvider() {
        return new DefaultPlaylistStateProvider(getContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.prime.PaginatedFragment
    public PlaylistTileAdapter createAdapter() {
        return new PlaylistTileAdapter(getContext(), this.mOverflowListener, getResources().getDimensionPixelSize(R.dimen.playlist_tile_art_size), this.mStateProvider, this.mPagerProvider.getRankType() == PaginatedRankType.POPULAR);
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected List<PrimePlaylist> transformItemsFromResponse(List items) {
        PaginatedRankType paginatedRankType = this.mRankType;
        if (paginatedRankType == PaginatedRankType.RECOMMENDED || paginatedRankType == PaginatedRankType.SIMILAR) {
            return PrimeItemsTransformationUtil.toPrimePlaylistListFromRecommendations(items);
        }
        return PrimeItemsTransformationUtil.toPrimePlaylistList(items);
    }

    @Override // com.amazon.mp3.prime.PaginatedFragment
    protected void updateAdapterOnUiThread(final List<PrimePlaylist> items) {
        getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.prime.PlaylistPaginatedFragment.3
            @Override // java.lang.Runnable
            public void run() {
                ((PlaylistTileAdapter) PlaylistPaginatedFragment.this.mAdapter).addAll(items);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.prime.PaginatedFragment
    public void handleOnClick(final PrimePlaylist playlist, final int position) {
        String asin = playlist.getAsin();
        Uri contentUri = MediaProvider.PrimePlaylists.getContentUri("cirrus", asin);
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus");
        bundle.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        bundle.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        bundle.putBoolean("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
        Fragment fragmentCreateFragment = PlaylistDetailFactory.createFragment(asin, playlist.getTitle(), false, bundle);
        sendUiClickMetric("selectPlaylist", asin, EntityIdType.ASIN, "BROWSE_RECOMMENDED", position);
        ((MusicHomeActivity) getActivity()).changeScreenFragment(fragmentCreateFragment, true, false, true);
    }
}
