package com.amazon.mp3.prime.browse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.DownloadListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeTabFragmentHost extends AbstractPrimeTabFragmentHost<PrimeMusicPagerAdapter> {

    @VisibleForTesting
    public static final PrimeMusicTab DEFAULT_TAB = PrimeMusicTab.BROWSE_HOME;
    private static final String TAG = PrimeTabFragmentHost.class.getSimpleName();
    private List<PrimeMusicTab> mTabList = new ArrayList();
    private final BroadcastReceiver mNewDownloadReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.prime.browse.PrimeTabFragmentHost.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            PrimeTabFragmentHost.this.showDownloadInProgressRibbonIfThereAreQueuedDownloads();
        }
    };
    private final DownloadListener mDownloadListener = new DownloadListener(new HashSet()) { // from class: com.amazon.mp3.prime.browse.PrimeTabFragmentHost.2
        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String id, final Group group, final float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String id, final Item item, final float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(final DownloadState state, final String id, final Group group) {
            if (state == DownloadState.DOWNLOADED || state == DownloadState.CANCELED) {
                PrimeTabFragmentHost.this.showDownloadInProgressRibbonIfThereAreQueuedDownloads();
            }
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(final DownloadState state, final String id, final Item item) {
            if (state == DownloadState.DOWNLOADED || state == DownloadState.CANCELED) {
                PrimeTabFragmentHost.this.showDownloadInProgressRibbonIfThereAreQueuedDownloads();
            }
        }
    };

    @Override // com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        startAccountJob(true);
        if (this.mPagerAdapter != 0) {
            Fragment fragmentAt = ((PrimeMusicPagerAdapter) this.mPagerAdapter).getFragmentAt(getLastViewedTab(getActivity()));
            if (fragmentAt != null) {
                fragmentAt.getArguments().putAll(getArguments());
            }
        }
        LocalBroadcastManager.getInstance(getApplication().getApplicationContext()).registerReceiver(this.mNewDownloadReceiver, new IntentFilter("com.amazon.music.download.NEW_DOWNLOAD_REQUEST"));
        MusicDownloader.getInstance(getApplication().getApplicationContext()).registerDownloadListener(this.mDownloadListener);
    }

    @Override // com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getApplication().getApplicationContext()).unregisterReceiver(this.mNewDownloadReceiver);
        MusicDownloader.getInstance(getApplication().getApplicationContext()).unregisterDownloadListener(this.mDownloadListener);
    }

    private void updateTabs() {
        this.mTabList.clear();
        this.mTabList.add(PrimeMusicTab.BROWSE_HOME);
    }

    public int getPrimeTabPosition(final PrimeMusicTab tab) {
        if (this.mTabList.isEmpty()) {
            updateTabs();
        }
        int iIndexOf = this.mTabList.indexOf(tab);
        if (iIndexOf >= 0) {
            return iIndexOf;
        }
        Log.error(TAG, tab.name() + " position requested but doesn't exist in adapter. Returning the default");
        return this.mTabList.indexOf(DEFAULT_TAB);
    }

    public static PrimeTabFragmentHost newInstance(Bundle extras) {
        PrimeTabFragmentHost primeTabFragmentHost = new PrimeTabFragmentHost();
        if (extras == null) {
            primeTabFragmentHost.setArguments(new Bundle());
        } else {
            primeTabFragmentHost.setArguments(extras);
        }
        return primeTabFragmentHost;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost
    public PrimeMusicPagerAdapter createPagerAdapter() {
        return new PrimeMusicPagerAdapter(getArguments(), getActivity(), getChildFragmentManager(), this.mTabList);
    }

    @Override // com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost
    protected View createHeaderView(Bundle savedInstance) {
        if (AmazonApplication.getCapabilities().isBauhausTopAppBarEnabled()) {
            return new BauhausActionBarView(getActivity(), null, Boolean.FALSE, Boolean.valueOf(true ^ AmazonApplication.getCapabilities().isFacetedNavigationEnabled()));
        }
        ActionBarView actionBarView = new ActionBarView(getActivity(), null, false, true);
        actionBarView.setLogoVisible(true);
        return actionBarView;
    }

    @Override // com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost
    protected int getLastViewedTab(Context context) {
        if (this.mPagerAdapter != 0) {
            return getSharedPreference(context, "com.amazon.mp3_LastViewedPrimeTab").getInt("lastViewedTab", ((PrimeMusicPagerAdapter) this.mPagerAdapter).getDefaultTabPosition());
        }
        return 0;
    }

    @Override // com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost
    protected void setLastViewedTab(Context context, int lastTab) {
        SharedPreferences.Editor editorEdit = getSharedPreference(context, "com.amazon.mp3_LastViewedPrimeTab").edit();
        editorEdit.putInt("lastViewedTab", lastTab);
        editorEdit.apply();
    }

    @Override // com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost, androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost
    protected void initializeViews() {
        Fragment fragmentAt;
        updateTabs();
        super.initializeViews();
        int lastViewedTab = getLastViewedTab(getActivity());
        T t = this.mPagerAdapter;
        if (t == 0 || (fragmentAt = ((PrimeMusicPagerAdapter) t).getFragmentAt(lastViewedTab)) == null) {
            return;
        }
        fragmentAt.getArguments().putAll(getArguments());
    }
}
