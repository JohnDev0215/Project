package com.amazon.music.curate.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.curate.R;
import com.amazon.music.curate.data.PlaylistRecsRepository;
import com.amazon.music.curate.data.PlaylistService;
import com.amazon.music.curate.data.UserPlaylistRepository;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.data.database.RecsCache;
import com.amazon.music.curate.provider.Configuration;
import com.amazon.music.curate.provider.DownloadProvider;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.provider.InternalProvider;
import com.amazon.music.curate.provider.OverflowMenuProvider;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.provider.SubscriptionProvider;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import dagger.android.support.AndroidSupportInjection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: PlaylistRecsSeeMoreFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 A2\u00020\u0001:\u0002@AB\u0005¢\u0006\u0002\u0010\u0002J\n\u00103\u001a\u0004\u0018\u000104H\u0002J\u0012\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000108H\u0016J&\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>2\b\u00107\u001a\u0004\u0018\u000108H\u0016J\b\u0010?\u001a\u000206H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010%\u001a\u00020&8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001e\u0010+\u001a\u00020,8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u000e\u00101\u001a\u000202X\u0082.¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/amazon/music/curate/view/PlaylistRecsSeeMoreFragment;", "Lcom/amazon/music/curate/view/BaseFragment;", "()V", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "getDeviceInfoProvider", "()Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "setDeviceInfoProvider", "(Lcom/amazon/music/platform/providers/DeviceInfoProvider;)V", "downloadProvider", "Lcom/amazon/music/curate/provider/DownloadProvider;", "getDownloadProvider", "()Lcom/amazon/music/curate/provider/DownloadProvider;", "setDownloadProvider", "(Lcom/amazon/music/curate/provider/DownloadProvider;)V", "featureFlagProvider", "Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "getFeatureFlagProvider", "()Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "setFeatureFlagProvider", "(Lcom/amazon/music/curate/provider/FeatureFlagProvider;)V", "overflowMenuProvider", "Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "getOverflowMenuProvider", "()Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "setOverflowMenuProvider", "(Lcom/amazon/music/curate/provider/OverflowMenuProvider;)V", "playbackProvider", "Lcom/amazon/music/curate/provider/PlaybackProvider;", "getPlaybackProvider", "()Lcom/amazon/music/curate/provider/PlaybackProvider;", "setPlaybackProvider", "(Lcom/amazon/music/curate/provider/PlaybackProvider;)V", "playlistId", "", "playlistRecsViewModel", "Lcom/amazon/music/curate/view/PlaylistRecsViewModel;", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "getRequestInterceptor", "()Lcom/amazon/hermes/RequestInterceptor;", "setRequestInterceptor", "(Lcom/amazon/hermes/RequestInterceptor;)V", "subscriptionProvider", "Lcom/amazon/music/curate/provider/SubscriptionProvider;", "getSubscriptionProvider", "()Lcom/amazon/music/curate/provider/SubscriptionProvider;", "setSubscriptionProvider", "(Lcom/amazon/music/curate/provider/SubscriptionProvider;)V", "visualRowItemListAdapter", "Lcom/amazon/music/curate/view/VisualRowItemListAdapter;", "getActionBar", "Landroidx/appcompat/app/ActionBar;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onResume", "Builder", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistRecsSeeMoreFragment extends BaseFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOG;
    public static final String PLAYLIST_ID_KEY = "PLAYLIST_ID_KEY";
    public DeviceInfoProvider deviceInfoProvider;
    public DownloadProvider downloadProvider;
    public FeatureFlagProvider featureFlagProvider;
    public OverflowMenuProvider overflowMenuProvider;
    public PlaybackProvider playbackProvider;
    private String playlistId;
    private PlaylistRecsViewModel playlistRecsViewModel;
    public RequestInterceptor requestInterceptor;
    public SubscriptionProvider subscriptionProvider;
    private VisualRowItemListAdapter visualRowItemListAdapter;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-1, reason: not valid java name */
    public static final void m1947onCreateView$lambda1() {
    }

    /* JADX INFO: compiled from: PlaylistRecsSeeMoreFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/music/curate/view/PlaylistRecsSeeMoreFragment$Companion;", "", "()V", "LOG", "Lorg/slf4j/Logger;", PlaylistRecsSeeMoreFragment.PLAYLIST_ID_KEY, "", "builder", "Lcom/amazon/music/curate/view/PlaylistRecsSeeMoreFragment$Builder;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Builder builder() {
            return new Builder();
        }
    }

    static {
        Logger logger = LoggerFactory.getLogger(PlaylistRecsSeeMoreFragment.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(PlaylistRecsSe…t::class.java.simpleName)");
        LOG = logger;
    }

    /* JADX INFO: compiled from: PlaylistRecsSeeMoreFragment.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/music/curate/view/PlaylistRecsSeeMoreFragment$Builder;", "", "()V", "playlistId", "", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/curate/view/PlaylistRecsSeeMoreFragment;", "withPlaylistId", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder {
        private String playlistId;

        public final Builder withPlaylistId(String playlistId) {
            Intrinsics.checkNotNullParameter(playlistId, "playlistId");
            this.playlistId = playlistId;
            return this;
        }

        public final PlaylistRecsSeeMoreFragment build() {
            PlaylistRecsSeeMoreFragment playlistRecsSeeMoreFragment = new PlaylistRecsSeeMoreFragment();
            Bundle bundle = new Bundle();
            bundle.putString(PlaylistRecsSeeMoreFragment.PLAYLIST_ID_KEY, this.playlistId);
            playlistRecsSeeMoreFragment.setArguments(bundle);
            return playlistRecsSeeMoreFragment;
        }
    }

    public final RequestInterceptor getRequestInterceptor() {
        RequestInterceptor requestInterceptor = this.requestInterceptor;
        if (requestInterceptor != null) {
            return requestInterceptor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("requestInterceptor");
        return null;
    }

    public final void setRequestInterceptor(RequestInterceptor requestInterceptor) {
        Intrinsics.checkNotNullParameter(requestInterceptor, "<set-?>");
        this.requestInterceptor = requestInterceptor;
    }

    public final DownloadProvider getDownloadProvider() {
        DownloadProvider downloadProvider = this.downloadProvider;
        if (downloadProvider != null) {
            return downloadProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("downloadProvider");
        return null;
    }

    public final void setDownloadProvider(DownloadProvider downloadProvider) {
        Intrinsics.checkNotNullParameter(downloadProvider, "<set-?>");
        this.downloadProvider = downloadProvider;
    }

    public final PlaybackProvider getPlaybackProvider() {
        PlaybackProvider playbackProvider = this.playbackProvider;
        if (playbackProvider != null) {
            return playbackProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playbackProvider");
        return null;
    }

    public final void setPlaybackProvider(PlaybackProvider playbackProvider) {
        Intrinsics.checkNotNullParameter(playbackProvider, "<set-?>");
        this.playbackProvider = playbackProvider;
    }

    public final OverflowMenuProvider getOverflowMenuProvider() {
        OverflowMenuProvider overflowMenuProvider = this.overflowMenuProvider;
        if (overflowMenuProvider != null) {
            return overflowMenuProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("overflowMenuProvider");
        return null;
    }

    public final void setOverflowMenuProvider(OverflowMenuProvider overflowMenuProvider) {
        Intrinsics.checkNotNullParameter(overflowMenuProvider, "<set-?>");
        this.overflowMenuProvider = overflowMenuProvider;
    }

    public final FeatureFlagProvider getFeatureFlagProvider() {
        FeatureFlagProvider featureFlagProvider = this.featureFlagProvider;
        if (featureFlagProvider != null) {
            return featureFlagProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("featureFlagProvider");
        return null;
    }

    public final void setFeatureFlagProvider(FeatureFlagProvider featureFlagProvider) {
        Intrinsics.checkNotNullParameter(featureFlagProvider, "<set-?>");
        this.featureFlagProvider = featureFlagProvider;
    }

    public final SubscriptionProvider getSubscriptionProvider() {
        SubscriptionProvider subscriptionProvider = this.subscriptionProvider;
        if (subscriptionProvider != null) {
            return subscriptionProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("subscriptionProvider");
        return null;
    }

    public final void setSubscriptionProvider(SubscriptionProvider subscriptionProvider) {
        Intrinsics.checkNotNullParameter(subscriptionProvider, "<set-?>");
        this.subscriptionProvider = subscriptionProvider;
    }

    public final DeviceInfoProvider getDeviceInfoProvider() {
        DeviceInfoProvider deviceInfoProvider = this.deviceInfoProvider;
        if (deviceInfoProvider != null) {
            return deviceInfoProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("deviceInfoProvider");
        return null;
    }

    public final void setDeviceInfoProvider(DeviceInfoProvider deviceInfoProvider) {
        Intrinsics.checkNotNullParameter(deviceInfoProvider, "<set-?>");
        this.deviceInfoProvider = deviceInfoProvider;
    }

    @Override // com.amazon.music.curate.view.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        String str;
        Bundle arguments = getArguments();
        String str2 = null;
        String string = arguments == null ? null : arguments.getString(PLAYLIST_ID_KEY);
        Intrinsics.checkNotNull(string);
        Intrinsics.checkNotNullExpressionValue(string, "arguments?.getString(PLAYLIST_ID_KEY)!!");
        this.playlistId = string;
        FragmentCallbacks[] fragmentCallbacksArr = new FragmentCallbacks[1];
        String metricsValue = PageType.DETAIL_USER_PLAYLIST_SEE_MORE_RECS.getMetricsValue();
        Intrinsics.checkNotNullExpressionValue(metricsValue, "DETAIL_USER_PLAYLIST_SEE…ORE_RECS.getMetricValue()");
        String str3 = this.playlistId;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistId");
            str3 = null;
        }
        String metricsValue2 = EntityIdType.PLAYLIST_ID.getMetricsValue();
        Intrinsics.checkNotNullExpressionValue(metricsValue2, "PLAYLIST_ID.metricValue");
        fragmentCallbacksArr[0] = new UiContentViewFragmentCallbacks(this, metricsValue, str3, metricsValue2);
        addFragmentCallbacks(fragmentCallbacksArr);
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        InternalProvider internalProvider = InternalProvider.Singleton.INSTANCE.get();
        RequestInterceptor requestInterceptor = getRequestInterceptor();
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNullExpressionValue(context, "context!!");
        PlaylistService playlistServiceProvideService = internalProvider.provideService(requestInterceptor, context, getFeatureFlagProvider());
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        Intrinsics.checkNotNullExpressionValue(context2, "context!!");
        PlaylistDB playlistDBProvideCache = internalProvider.provideCache(context2, playlistServiceProvideService);
        RecsCache recsCacheProvideRecsCache = internalProvider.provideRecsCache(playlistServiceProvideService);
        Configuration configuration = new Configuration(getOverflowMenuProvider(), getFeatureFlagProvider(), getSubscriptionProvider(), getDeviceInfoProvider(), playlistDBProvideCache, recsCacheProvideRecsCache, playlistServiceProvideService, internalProvider.provideRecentsService(getRequestInterceptor()));
        Context context3 = getContext();
        Intrinsics.checkNotNull(context3);
        Intrinsics.checkNotNullExpressionValue(context3, "context!!");
        UserPlaylistRepository userPlaylistRepository = new UserPlaylistRepository(configuration, context3);
        PlaylistRecsRepository playlistRecsRepository = new PlaylistRecsRepository(playlistDBProvideCache, recsCacheProvideRecsCache, playlistServiceProvideService, getSubscriptionProvider());
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
        String str4 = this.playlistId;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistId");
            str = null;
        } else {
            str = str4;
        }
        ViewModelProvider viewModelProvider = new ViewModelProvider(activity, new UserPlaylistViewModelFactory(str, userPlaylistRepository, null, 4, null));
        String str5 = this.playlistId;
        if (str5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistId");
            str5 = null;
        }
        UserPlaylistViewModel userPlaylistViewModel = (UserPlaylistViewModel) viewModelProvider.get(str5, UserPlaylistViewModel.class);
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        Intrinsics.checkNotNullExpressionValue(activity2, "activity!!");
        String str6 = this.playlistId;
        if (str6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistId");
            str6 = null;
        }
        ViewModelProvider viewModelProvider2 = new ViewModelProvider(activity2, new PlaylistRecsViewModelFactory(str6, userPlaylistViewModel, playlistRecsRepository));
        String str7 = this.playlistId;
        if (str7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistId");
        } else {
            str2 = str7;
        }
        this.playlistRecsViewModel = (PlaylistRecsViewModel) viewModelProvider2.get(str2, PlaylistRecsViewModel.class);
    }

    @Override // com.amazon.music.curate.view.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        View viewInflate = inflater.inflate(R.layout.detail_page, container, false);
        View viewFindViewById = viewInflate.findViewById(R.id.visual_item_list);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        }
        RecyclerView recyclerView = (RecyclerView) viewFindViewById;
        View viewFindViewById2 = viewInflate.findViewById(R.id.swipe_refresh);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.swipe_refresh)");
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) viewFindViewById2;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.visualRowItemListAdapter = new VisualRowItemListAdapter(this, getPlaybackProvider(), getDownloadProvider());
        PlaylistRecsViewModel playlistRecsViewModel = this.playlistRecsViewModel;
        VisualRowItemListAdapter visualRowItemListAdapter = null;
        if (playlistRecsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistRecsViewModel");
            playlistRecsViewModel = null;
        }
        playlistRecsViewModel.getPlaylistRecsLiveData().observe(this, new Observer() { // from class: com.amazon.music.curate.view.PlaylistRecsSeeMoreFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                PlaylistRecsSeeMoreFragment.m1946onCreateView$lambda0(this.f$0, swipeRefreshLayout, (PagedList) obj);
            }
        });
        VisualRowItemListAdapter visualRowItemListAdapter2 = this.visualRowItemListAdapter;
        if (visualRowItemListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("visualRowItemListAdapter");
        } else {
            visualRowItemListAdapter = visualRowItemListAdapter2;
        }
        recyclerView.setAdapter(visualRowItemListAdapter);
        registerForContextMenu(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.music.curate.view.PlaylistRecsSeeMoreFragment$$ExternalSyntheticLambda1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                PlaylistRecsSeeMoreFragment.m1947onCreateView$lambda1();
            }
        });
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m1946onCreateView$lambda0(PlaylistRecsSeeMoreFragment this$0, SwipeRefreshLayout swipeRefresh, PagedList pagedList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(swipeRefresh, "$swipeRefresh");
        VisualRowItemListAdapter visualRowItemListAdapter = this$0.visualRowItemListAdapter;
        if (visualRowItemListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("visualRowItemListAdapter");
            visualRowItemListAdapter = null;
        }
        Intrinsics.checkNotNull(pagedList);
        visualRowItemListAdapter.submitList(pagedList);
        swipeRefresh.setRefreshing(false);
        LOG.debug("Got " + pagedList.size() + " tracks");
    }

    @Override // com.amazon.music.curate.view.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ActionBar actionBar = getActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        actionBar.setTitle(context.getResources().getString(R.string.similar_songs_page_title));
    }

    private final ActionBar getActionBar() {
        FragmentActivity activity = getActivity();
        AppCompatActivity appCompatActivity = activity instanceof AppCompatActivity ? (AppCompatActivity) activity : null;
        if (appCompatActivity == null) {
            return null;
        }
        return appCompatActivity.getSupportActionBar();
    }
}
