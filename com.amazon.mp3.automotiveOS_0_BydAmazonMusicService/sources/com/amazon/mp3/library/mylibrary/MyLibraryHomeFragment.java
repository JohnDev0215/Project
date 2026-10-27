package com.amazon.mp3.library.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarSourceToggleView;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.util.AccountJobController;
import com.amazon.mp3.auto.carmode.CarModeUserPresetHandlerImpl;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment;
import com.amazon.mp3.catalog.fragment.LibraryArtistsFragment;
import com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment;
import com.amazon.mp3.catalog.fragment.LibraryTracksFragment;
import com.amazon.mp3.feed.FeedButton;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.fragment.LibraryBaseFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.MyLibraryPodcastsToggleHelper;
import com.amazon.mp3.recents.RecentsFragment;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.CompositeOnSourceChangedListener;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.curate.view.MadeForYouFragment;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.providers.FeatureGateProviderImpl;
import com.amazon.podcast.ActionBarInfo;
import com.amazon.podcast.AdvertisingPreferencesProviderImpl;
import com.amazon.podcast.DownloadSettings;
import com.amazon.podcast.FragmentInfo;
import com.amazon.podcast.NetworkSettings;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.PodcastDynamicMessaging;
import com.amazon.podcast.PodcastSharingProvider;
import com.amazon.podcast.UserInfo;
import com.amazon.podcast.bootstrap.Bootstrap;
import com.amazon.podcast.featuregating.PodcastFeatureGating;

/* JADX INFO: loaded from: classes3.dex */
public final class MyLibraryHomeFragment extends LibraryBaseFragment implements ActionBarSourceToggleView.SourceChangedListener {
    private static final String TAG = MyLibraryHomeFragment.class.getSimpleName();
    private AccountJobController mAccountJobs;
    private LinearLayout mCategoriesView;
    private FeedButton mFeedButton;
    private CompositeOnSourceChangedListener mListener;
    private MadeForYouFragment mMadeForYouFragment;
    private RecentsFragment mRecentsFragment;
    private View rootView;

    enum Category {
        PLAYLISTS(R.string.dmusic_library_playlists_tab),
        ARTISTS(R.string.dmusic_library_artists_tab),
        ALBUMS(R.string.dmusic_library_albums_tab),
        SONGS(R.string.dmusic_library_songs_tab);


        @LayoutRes
        private int labelResId;

        Category(final int labelResId) {
            this.labelResId = labelResId;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @LayoutRes
        public int getLabelResId() {
            return this.labelResId;
        }
    }

    public static MyLibraryHomeFragment newInstance(final Bundle args) {
        MyLibraryHomeFragment myLibraryHomeFragment = new MyLibraryHomeFragment();
        myLibraryHomeFragment.setArguments(args);
        return myLibraryHomeFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PageType pageType;
        this.rootView = inflater.inflate(R.layout.my_music_landing_page, container, false);
        if ("cirrus".equals(LastViewedScreenUtil.getLastViewedSource(getContext(), LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource())) {
            pageType = PageType.MY_MUSIC_CLOUD_FRONT_PAGE;
        } else {
            pageType = PageType.MY_MUSIC_OFFLINE_FRONT_PAGE;
        }
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(pageType.getMetricsValue()).withViewType(ScreenUtil.getScreenViewType(getContext())).build());
        return this.rootView;
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CompositeOnSourceChangedListener compositeOnSourceChangedListener = new CompositeOnSourceChangedListener();
        this.mListener = compositeOnSourceChangedListener;
        compositeOnSourceChangedListener.addSourceChangedListener(this);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean z = LastViewedScreenUtil.getLastViewedSource(getActivity()) == LastViewedScreenUtil.LastViewedSource.DEVICE;
        if (z) {
            removeMadeForYou();
        }
        this.mCategoriesView = (LinearLayout) view.findViewById(R.id.my_music_categories);
        initCategoryTabs();
        if (!z) {
            initMadeForYou();
        }
        initRecents();
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

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        FeedButton feedButton = this.mFeedButton;
        if (feedButton != null) {
            feedButton.removeObservers(getActivity());
            this.mFeedButton = null;
        }
        CompositeOnSourceChangedListener compositeOnSourceChangedListener = this.mListener;
        if (compositeOnSourceChangedListener != null) {
            compositeOnSourceChangedListener.removeOnSourceChangedListener(this);
        }
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.mAccountJobs.onActivityResult(getSession(), requestCode, resultCode, data);
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        this.mAccountJobs.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.actionbar.view.ActionBarSourceToggleView.SourceChangedListener
    public void onSourceToggled(ActionBarSourceToggleView.Source source) {
        ActionBarSourceToggleView.Source source2 = ActionBarSourceToggleView.Source.CLOUD;
        LastViewedScreenUtil.LastViewedSource lastViewedSourceFromCirrusMediaSource = LastViewedScreenUtil.LastViewedSource.fromCirrusMediaSource(source == source2 ? "cirrus" : "cirrus-local");
        if (shouldSaveLastViewedSource()) {
            LastViewedScreenUtil.setLastViewed(getApplication(), LastViewedScreenUtil.LastViewedScreen.LIBRARY, lastViewedSourceFromCirrusMediaSource);
        }
        if (source == source2) {
            this.mAccountJobs.checkImmediate(getSession(), null);
            initMadeForYou();
        } else {
            removeMadeForYou();
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        PageType pageType;
        super.onResume();
        if ("cirrus".equals(LastViewedScreenUtil.getLastViewedSource(getContext(), LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource())) {
            pageType = PageType.MY_MUSIC_CLOUD_FRONT_PAGE;
        } else {
            pageType = PageType.MY_MUSIC_OFFLINE_FRONT_PAGE;
        }
        UiPageViewEvent.builder(pageType.getMetricsValue()).withViewType(ScreenUtil.getScreenViewType(getContext())).build();
        this.mAccountJobs.check(getSession());
        if (BottomNavigationBarContainer.getInstance().isEnabled()) {
            BottomNavigationBarContainer.getInstance().setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.LIBRARY);
        }
    }

    private void initCategoryTabs() {
        this.mCategoriesView.removeAllViews();
        if (PodcastFeatureGating.PODCAST.isEnabled() && !MyLibraryPodcastsToggleHelper.INSTANCE.isPodcastsToggleSupported() && !ChildUserUtil.INSTANCE.isChildUser(requireContext())) {
            addPodcast();
        }
        for (Category category : Category.values()) {
            View viewInflate = getLayoutInflater().inflate(R.layout.my_music_category_tab, (ViewGroup) null);
            if (!Category.ARTISTS.equals(category) || !AmazonApplication.getCapabilities().shouldHideArtistLink()) {
                addView(category, viewInflate);
            }
        }
    }

    private void addPodcast() {
        View viewInflate = getLayoutInflater().inflate(R.layout.my_music_category_tab, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.my_music_category_row_text)).setText(getResources().getString(R.string.dmusic_library_podcasts_tab));
        viewInflate.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryHomeFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Podcast.create(MyLibraryHomeFragment.this.getPodcastBootstrap()).startLibrary("", false);
                MyLibraryHomeFragment.this.sendUiClickMetrics(ActionType.GO_PODCASTS_LIBRARY);
            }
        });
        this.mCategoriesView.addView(viewInflate);
    }

    private void addView(final Category category, final View view) {
        ((TextView) view.findViewById(R.id.my_music_category_row_text)).setText(category.getLabelResId());
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryHomeFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Uri filterContentUri;
                Intent intentForContentUri;
                ActionType actionType;
                Uri filterContentUri2;
                Uri filterContentUri3;
                Uri filterContentUri4;
                Context context = MyLibraryHomeFragment.this.getContext();
                String cirrusMediaSource = LastViewedScreenUtil.getLastViewedSource(context, LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource();
                int i = AnonymousClass3.$SwitchMap$com$amazon$mp3$library$mylibrary$MyLibraryHomeFragment$Category[category.ordinal()];
                if (i == 1) {
                    if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
                        filterContentUri = LibraryPlaylistsFragment.getUri();
                    } else {
                        filterContentUri = MediaProvider.Playlists.getFilterContentUri(cirrusMediaSource, "");
                    }
                    intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, filterContentUri);
                    actionType = ActionType.GO_LIBRARY_PLAYLISTS;
                } else if (i == 2) {
                    if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
                        filterContentUri2 = LibraryArtistsFragment.getUri();
                    } else {
                        filterContentUri2 = MediaProvider.Artists.getFilterContentUri(cirrusMediaSource, "");
                    }
                    intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, filterContentUri2);
                    actionType = ActionType.GO_LIBRARY_ARTISTS;
                } else if (i == 3) {
                    if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
                        filterContentUri3 = LibraryAlbumsFragment.getUri();
                    } else {
                        filterContentUri3 = MediaProvider.Albums.getFilterContentUri(cirrusMediaSource, "");
                    }
                    intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, filterContentUri3);
                    actionType = ActionType.GO_LIBRARY_ALBUMS;
                } else if (i == 4) {
                    if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
                        filterContentUri4 = LibraryTracksFragment.getUri();
                    } else {
                        filterContentUri4 = MediaProvider.Tracks.getFilterContentUri(cirrusMediaSource, "");
                    }
                    intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, filterContentUri4);
                    actionType = ActionType.GO_LIBRARY_SONGS;
                } else {
                    intentForContentUri = LibraryActivityFactory.getLibraryHomeStartIntent(context);
                    actionType = null;
                }
                intentForContentUri.putExtra("com.amazon.mp3.STANDALONE_FRAGMENT_KEY", true);
                context.startActivity(intentForContentUri);
                if (actionType != null) {
                    MyLibraryHomeFragment.this.sendUiClickMetrics(actionType);
                }
            }
        });
        this.mCategoriesView.addView(view);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.mylibrary.MyLibraryHomeFragment$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$mylibrary$MyLibraryHomeFragment$Category;

        static {
            int[] iArr = new int[Category.values().length];
            $SwitchMap$com$amazon$mp3$library$mylibrary$MyLibraryHomeFragment$Category = iArr;
            try {
                iArr[Category.PLAYLISTS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$mylibrary$MyLibraryHomeFragment$Category[Category.ARTISTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$mylibrary$MyLibraryHomeFragment$Category[Category.ALBUMS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$mylibrary$MyLibraryHomeFragment$Category[Category.SONGS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bootstrap getPodcastBootstrap() {
        return new Bootstrap(null, getApplication().getApplicationContext(), new UserInfo(), ActionBarInfo.INSTANCE, FragmentInfo.INSTANCE, new NetworkSettings(), new DownloadSettings(getActivity()), new PodcastSharingProvider(), new FeatureGateProviderImpl(), false, new PodcastDynamicMessaging(getActivity()), new CarModeUserPresetHandlerImpl(), new AdvertisingPreferencesProviderImpl());
    }

    private void initMadeForYou() {
        FragmentActivity activity;
        if (this.rootView == null || (activity = getActivity()) == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        if (this.mMadeForYouFragment == null) {
            this.mMadeForYouFragment = MadeForYouFragment.newInstance();
        }
        childFragmentManager.beginTransaction().replace(R.id.my_music_made_for_you, this.mMadeForYouFragment).commit();
    }

    private void removeMadeForYou() {
        FragmentActivity activity;
        FragmentManager childFragmentManager;
        Fragment fragmentFindFragmentById;
        if (this.rootView == null || (activity = getActivity()) == null || activity.isFinishing() || activity.isDestroyed() || (fragmentFindFragmentById = (childFragmentManager = getChildFragmentManager()).findFragmentById(R.id.my_music_made_for_you)) == null) {
            return;
        }
        childFragmentManager.beginTransaction().remove(fragmentFindFragmentById).commit();
    }

    private void initRecents() {
        FragmentActivity activity;
        if (this.rootView == null || (activity = getActivity()) == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        if (this.mRecentsFragment == null) {
            this.mRecentsFragment = RecentsFragment.newInstance();
        }
        childFragmentManager.beginTransaction().replace(R.id.my_music_recents, this.mRecentsFragment).commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUiClickMetrics(final ActionType actionType) {
        PageType pageType;
        if ("cirrus".equals(LastViewedScreenUtil.getLastViewedSource(getActivity(), LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource())) {
            pageType = PageType.MY_MUSIC_CLOUD_FRONT_PAGE;
        } else {
            pageType = PageType.MY_MUSIC_OFFLINE_FRONT_PAGE;
        }
        if (actionType != null) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withPageType(pageType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
        }
    }
}
