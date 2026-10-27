package com.amazon.mp3.library.adapter;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment;
import com.amazon.mp3.catalog.fragment.LibraryArtistsFragment;
import com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment;
import com.amazon.mp3.catalog.fragment.LibraryTracksFragment;
import com.amazon.mp3.library.fragment.AbstractListFragment;
import com.amazon.mp3.library.fragment.LibraryFragmentFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.recents.RecentsFragment;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryPagerAdapter extends ScrollableFragmentPagerAdapter {
    public static final LastViewedScreenUtil.LastViewedSource DEFAULT_LIBRARY_SOURCE = LastViewedScreenUtil.LastViewedSource.CLOUD;
    private static final String TAG = "LibraryPagerAdapter";
    private Activity mActivity;
    private int mTabPositionOnStart;

    public interface OnListViewCreatedListener {
        void onListViewCreated(ViewGroup listView);
    }

    public static int getDefaultTabPosition() {
        return 0;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        int pagerPosition = getPagerPosition(position);
        if (pagerPosition == 0) {
            return getRecentsFragment();
        }
        if (pagerPosition == 1) {
            return getPlaylistListFragment();
        }
        if (pagerPosition == 2) {
            return getArtistListFragment();
        }
        if (pagerPosition == 3) {
            return getAlbumListFragment();
        }
        if (pagerPosition == 4) {
            return getSongListFragment();
        }
        if (pagerPosition == 5) {
            return getGenreListFragment();
        }
        Log.debug(TAG, "Adapter is trying to access a fragment outside the bounds of the tab count");
        return null;
    }

    public String getSourceId() {
        return LastViewedScreenUtil.getLastViewedSource(this.mActivity, DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return shouldHideArtistTab() ? 5 : 6;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        int pagerPosition = getPagerPosition(position);
        if (pagerPosition == 0) {
            return this.mActivity.getResources().getString(R.string.dmusic_recent_activity_tab);
        }
        if (pagerPosition == 1) {
            return this.mActivity.getResources().getString(R.string.dmusic_library_playlists_tab);
        }
        if (pagerPosition == 2) {
            return this.mActivity.getResources().getString(R.string.dmusic_library_artists_tab);
        }
        if (pagerPosition == 3) {
            return this.mActivity.getResources().getString(R.string.dmusic_library_albums_tab);
        }
        if (pagerPosition == 4) {
            return this.mActivity.getResources().getString(R.string.dmusic_library_songs_tab);
        }
        if (pagerPosition != 5) {
            return null;
        }
        return this.mActivity.getResources().getString(R.string.dmusic_library_genres_tab);
    }

    private int getPagerPosition(final int position) {
        return (!shouldHideArtistTab() || position < 2) ? position : position + 1;
    }

    private boolean shouldHideArtistTab() {
        return AmazonApplication.getCapabilities().shouldHideArtistLink();
    }

    private Fragment getRecentsFragment() {
        RecentsFragment recentsFragmentNewInstance = RecentsFragment.newInstance();
        recentsFragmentNewInstance.getArguments().putBoolean("com.amazon.mp3.library.EXTRA_FRAGMENT_IN_TAB_HOST", true);
        return recentsFragmentNewInstance;
    }

    private Fragment getPlaylistListFragment() {
        Uri filterContentUri;
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            filterContentUri = LibraryPlaylistsFragment.getUri();
        } else {
            filterContentUri = MediaProvider.Playlists.getFilterContentUri(getSourceId(), "");
        }
        Fragment listFragment = getListFragment(filterContentUri, 1);
        listFragment.getArguments().putBoolean("com.amazon.mp3.library.EXTRA_USE_GRIDVIEW", false);
        return listFragment;
    }

    private Fragment getArtistListFragment() {
        Uri filterContentUri;
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            filterContentUri = LibraryArtistsFragment.getUri();
        } else {
            filterContentUri = MediaProvider.Artists.getFilterContentUri(getSourceId(), "");
        }
        return getListFragment(filterContentUri, 2);
    }

    private Fragment getAlbumListFragment() {
        Uri filterContentUri;
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            filterContentUri = LibraryAlbumsFragment.getUri();
        } else {
            filterContentUri = MediaProvider.Albums.getFilterContentUri(getSourceId(), "");
        }
        return getListFragment(filterContentUri, 3);
    }

    private Fragment getSongListFragment() {
        Uri filterContentUri;
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            filterContentUri = LibraryTracksFragment.getUri();
        } else {
            filterContentUri = MediaProvider.Tracks.getFilterContentUri(getSourceId(), "");
        }
        Fragment fragment = LibraryFragmentFactory.getFragment(this.mActivity, filterContentUri);
        Bundle arguments = fragment.getArguments();
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            arguments.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", filterContentUri);
            arguments.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", getSourceId());
        } else {
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_SHUFFLE", true);
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_USE_GRIDVIEW", false);
            arguments.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", filterContentUri);
            arguments.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", getSourceId());
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_FRAGMENT_IN_TAB_HOST", true);
        }
        return fragment;
    }

    private Fragment getGenreListFragment() {
        return getListFragment(MediaProvider.Genres.getFilterContentUri(getSourceId(), ""), 5);
    }

    private Fragment getListFragment(Uri uri, int position) {
        Fragment fragment = LibraryFragmentFactory.getFragment(this.mActivity, uri);
        Bundle arguments = fragment.getArguments();
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            arguments.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", uri);
            arguments.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", getSourceId());
        } else {
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
            arguments.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", uri);
            arguments.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", getSourceId());
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_USE_GRIDVIEW", !(AmazonApplication.getCapabilities().shouldUseGridView() ? SettingsUtil.getBooleanPref(R.string.setting_key_listview_selected_library) : true));
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_FRAGMENT_IN_TAB_HOST", true);
        }
        return fragment;
    }

    @Override // com.amazon.mp3.library.adapter.ScrollableFragmentPagerAdapter
    public void scrollFragmentToTop(int position) {
        AbstractListFragment abstractListFragment;
        if ((getFragmentAt(position) instanceof RecentsFragment) || (abstractListFragment = (AbstractListFragment) getFragmentAt(position)) == null) {
            return;
        }
        abstractListFragment.getListView().smoothScrollToPositionFromTop(0, 0);
    }

    public void setTabPositionOnStart(int position) {
        this.mTabPositionOnStart = position;
    }
}
