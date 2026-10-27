package com.amazon.mp3.library.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.detailpages.playlist.PlaylistDetailFactory;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.fragment.VisibleListView;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedFragment;
import com.amazon.mp3.recents.RecentsFragment;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class RecentActivityPagerAdapter extends ScrollableFragmentPagerAdapter {
    private static final String TAG = "RecentActivityPagerAdapter";
    protected Activity mActivity;
    protected FragmentManager mFragmentManager;
    protected int mTabPositionOnStart;

    public static int getRecentlyAddedPosition() {
        return 1;
    }

    public static int getRecentlyDownloadedPosition() {
        return 2;
    }

    public static int getRecentlyPlayedPosition() {
        return 0;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return 3;
    }

    public int getDefaultTabPosition() {
        return 0;
    }

    public RecentActivityPagerAdapter(Activity activity, FragmentManager fm) {
        super(fm);
        this.mActivity = activity;
        this.mFragmentManager = fm;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        if (position == 0) {
            return getRecentsFragment();
        }
        if (position == 1) {
            return getRecentlyAddedFragment();
        }
        if (position == 2) {
            return getRecentlyDownloadedFragment();
        }
        Log.debug(TAG, "Adapter is trying to access a fragment outside the bounds of the tab count");
        return null;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return this.mActivity.getResources().getString(R.string.dmusic_library_recently_played_tab);
        }
        if (position == 1) {
            return this.mActivity.getResources().getString(R.string.dmusic_library_recently_added_tab);
        }
        if (position == 2) {
            return this.mActivity.getResources().getString(R.string.dmusic_library_recently_downloaded_tab);
        }
        Log.debug(TAG, "Adapter is trying to access a title outside the bounds of the tab count");
        return null;
    }

    protected Fragment getRecentsFragment() {
        Fragment fragmentNewInstance;
        if (AmazonApplication.getCapabilities().isRecentTracksSyncSupported()) {
            fragmentNewInstance = RecentsFragment.newInstance();
        } else {
            fragmentNewInstance = RecentlyPlayedFragment.newInstance();
        }
        fragmentNewInstance.getArguments().putBoolean("com.amazon.mp3.library.EXTRA_FRAGMENT_IN_TAB_HOST", true);
        return fragmentNewInstance;
    }

    protected Fragment getRecentlyAddedFragment() {
        return getTabFragment(MediaProvider.SmartPlaylists.getContentUri("cirrus", 1L), "PLAYLIST_RECENTLY_ADDED_TAB_NAME");
    }

    protected Fragment getRecentlyDownloadedFragment() {
        return getTabFragment(MediaProvider.SmartPlaylists.getContentUri("cirrus-local", 2L), "PLAYLIST_RECENTLY_DOWNLOADED_TAB_NAME");
    }

    protected Fragment getTabFragment(Uri contentUri, String tabName) {
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(this.mActivity, contentUri);
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_FRAGMENT_IN_TAB_HOST", true);
        intentForContentUri.putExtra("com.amazon.mp3.EXTRA_PLAYLIST_DETAIL_TAB_NAME", tabName);
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_FRAGMENT_TAB_HOST_NAME", this.mActivity.getResources().getString(R.string.dmusic_recent_activity_tab));
        return PlaylistDetailFactory.createFragment(intentForContentUri, false, (String) null, false);
    }

    public void setTabPositionOnStart(int position) {
        this.mTabPositionOnStart = position;
    }

    @Override // com.amazon.mp3.library.adapter.ScrollableFragmentPagerAdapter
    public void scrollFragmentToTop(int position) {
        LifecycleOwner fragmentAt = getFragmentAt(position);
        if (fragmentAt instanceof VisibleListView) {
            ((VisibleListView) fragmentAt).scrollToTop();
        }
    }

    public void onActivityResult(int position, int requestCode, int resultCode, Intent data) {
        Fragment fragmentAt = getFragmentAt(position);
        if (fragmentAt != null) {
            fragmentAt.onActivityResult(requestCode, resultCode, data);
        }
    }

    public Fragment getFragmentAtPosition(int position) {
        return getFragmentAt(position);
    }
}
