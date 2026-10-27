package com.amazon.mp3.library.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.activity.util.AccountJobController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.adapter.RecentActivityPagerAdapter;
import com.amazon.mp3.library.provider.InactivityProvider;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.view.SlidingTabLayout;
import com.amazon.music.events.NavigationAppEvent;

/* JADX INFO: loaded from: classes3.dex */
public class RecentActivityTabFragmentHost extends LibraryBaseFragment {
    private static final String TAG = RecentActivityTabFragmentHost.class.getSimpleName();
    private AccountJobController mAccountJobs;
    private ActionBarProvider mActionBarProvider;
    private View mDefaultActionBarView;
    private RecentActivityPagerAdapter mRecentPagerAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    public static RecentActivityTabFragmentHost newInstance() {
        RecentActivityTabFragmentHost recentActivityTabFragmentHost = new RecentActivityTabFragmentHost();
        recentActivityTabFragmentHost.setArguments(new Bundle());
        return recentActivityTabFragmentHost;
    }

    public static int getRecentActivityPlayedPosition() {
        return RecentActivityPagerAdapter.getRecentlyPlayedPosition();
    }

    public static int getRecentActivityAddedPosition() {
        return RecentActivityPagerAdapter.getRecentlyAddedPosition();
    }

    public static int getRecentActivityDownloadedPosition() {
        return RecentActivityPagerAdapter.getRecentlyDownloadedPosition();
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mAccountJobs = new AccountJobController(this, activity);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mAccountJobs.destroy();
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        this.mAccountJobs.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    public void setTabToOpen(Context context, int position) {
        setLastViewedTab(context, position);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mRecentPagerAdapter = new RecentActivityPagerAdapter(getActivity(), getChildFragmentManager());
        if (InactivityProvider.isInactive(getActivity())) {
            setLastViewedTab(getActivity(), this.mRecentPagerAdapter.getDefaultTabPosition());
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mDefaultActionBarView = new ActionBarView(getActivity(), getApplication().getString(R.string.dmusic_recent_activity_tab), AmazonApplication.getCapabilities().isCustomerProfileSupported(), true);
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mViewPager.setAdapter(null);
        removeHeaderView();
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setHeaderView(this.mDefaultActionBarView);
        int lastViewedTab = getLastViewedTab(getActivity());
        if (lastViewedTab == 0 || lastViewedTab == -1) {
            NavigationAppEvent.builder("recentPlayed").withOrientation(ScreenUtil.getScreenOrientation(getContext())).publish();
        }
        this.mViewPager.setCurrentItem(lastViewedTab);
        this.mSlidingTabLayout.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.RecentActivityTabFragmentHost.1
            @Override // java.lang.Runnable
            public void run() {
                RecentActivityTabFragmentHost.this.mSlidingTabLayout.scrollToTab(RecentActivityTabFragmentHost.this.mViewPager.getCurrentItem(), 0);
            }
        });
        this.mAccountJobs.check(getSession());
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver
    public void setActionBarProvider(final ActionBarProvider actionBarProvider) {
        super.setActionBarProvider(actionBarProvider);
        this.mActionBarProvider = actionBarProvider;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.library_prime_browse_tabs_host, container, false);
    }

    public void sendUiPageViewMetric(final int position) {
        if (position == 0 || position == -1) {
            NavigationAppEvent.builder("recentPlayed").withOrientation(ScreenUtil.getScreenOrientation(getContext())).publish();
        } else if (position == 1) {
            NavigationAppEvent.builder("recentAdded").withOrientation(ScreenUtil.getScreenOrientation(getContext())).publish();
        } else if (position == 2) {
            NavigationAppEvent.builder("recentDownloaded").withOrientation(ScreenUtil.getScreenOrientation(getContext())).publish();
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.feature_banner_container).setVisibility(8);
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        this.mSlidingTabLayout = slidingTabLayout;
        slidingTabLayout.setVisibility(0);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        this.mViewPager = viewPager;
        viewPager.setAdapter(this.mRecentPagerAdapter);
        this.mSlidingTabLayout.setViewPager(this.mViewPager);
        this.mSlidingTabLayout.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: com.amazon.mp3.library.fragment.RecentActivityTabFragmentHost.2
            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                RecentActivityTabFragmentHost.this.sendUiPageViewMetric(position);
                if (RecentActivityTabFragmentHost.this.mActionBarProvider != null) {
                    Fragment fragmentAtPosition = RecentActivityTabFragmentHost.this.mRecentPagerAdapter.getFragmentAtPosition(position);
                    if (!(fragmentAtPosition instanceof PlaylistDetailFragment)) {
                        RecentActivityTabFragmentHost.this.mActionBarProvider.setHeaderView(RecentActivityTabFragmentHost.this.mDefaultActionBarView);
                    } else {
                        RecentActivityTabFragmentHost.this.mActionBarProvider.setHeaderView(((PlaylistDetailFragment) fragmentAtPosition).getActionBarView());
                    }
                }
            }
        });
        this.mSlidingTabLayout.highlightSelectedTabText(this.mViewPager.getCurrentItem());
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        setLastViewedTab(getActivity(), this.mViewPager.getCurrentItem());
    }

    private int getLastViewedTab(Context context) {
        return getSharedPreference(context).getInt("lastViewedTab", this.mRecentPagerAdapter.getDefaultTabPosition());
    }

    private void setLastViewedTab(Context context, int lastTab) {
        SharedPreferences.Editor editorEdit = getSharedPreference(context).edit();
        editorEdit.putInt("lastViewedTab", lastTab);
        editorEdit.apply();
    }

    private SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences("com.amazon.mp3_LastViewedRecentActivityTab", 4);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.mAccountJobs.onActivityResult(getSession(), requestCode, resultCode, data);
        this.mRecentPagerAdapter.onActivityResult(this.mViewPager.getCurrentItem(), requestCode, resultCode, data);
    }
}
