package com.amazon.music.curate.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.curate.R;
import com.amazon.music.curate.data.MySoundtrackRepository;
import com.amazon.music.curate.model.station;
import com.amazon.music.curate.provider.DownloadProvider;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.provider.NavigationProvider;
import com.amazon.music.curate.provider.OverflowMenuProvider;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.provider.SubscriptionProvider;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import dagger.android.support.AndroidSupportInjection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: MySoundTrackFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 G2\u00020\u0001:\u0001GB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J&\u0010?\u001a\u0004\u0018\u00010@2\u0006\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010D2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\b\u0010E\u001a\u00020<H\u0016J\u000e\u0010F\u001a\u00020<2\u0006\u0010\u0003\u001a\u00020\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001e\u0010!\u001a\u00020\"8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001e\u0010'\u001a\u00020(8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001e\u0010-\u001a\u00020.8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001e\u00103\u001a\u0002048\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0010\u00109\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lcom/amazon/music/curate/view/MySoundtrackFragment;", "Landroidx/fragment/app/Fragment;", "()V", "callback", "Lcom/amazon/music/curate/view/MadeForYouCallback;", "getCallback", "()Lcom/amazon/music/curate/view/MadeForYouCallback;", "setCallback", "(Lcom/amazon/music/curate/view/MadeForYouCallback;)V", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "getDeviceInfoProvider", "()Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "setDeviceInfoProvider", "(Lcom/amazon/music/platform/providers/DeviceInfoProvider;)V", "downloadProvider", "Lcom/amazon/music/curate/provider/DownloadProvider;", "getDownloadProvider", "()Lcom/amazon/music/curate/provider/DownloadProvider;", "setDownloadProvider", "(Lcom/amazon/music/curate/provider/DownloadProvider;)V", "featureFlagProvider", "Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "getFeatureFlagProvider", "()Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "setFeatureFlagProvider", "(Lcom/amazon/music/curate/provider/FeatureFlagProvider;)V", "navigationProvider", "Lcom/amazon/music/curate/provider/NavigationProvider;", "getNavigationProvider", "()Lcom/amazon/music/curate/provider/NavigationProvider;", "setNavigationProvider", "(Lcom/amazon/music/curate/provider/NavigationProvider;)V", "overflowMenuProvider", "Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "getOverflowMenuProvider", "()Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "setOverflowMenuProvider", "(Lcom/amazon/music/curate/provider/OverflowMenuProvider;)V", "playbackProvider", "Lcom/amazon/music/curate/provider/PlaybackProvider;", "getPlaybackProvider", "()Lcom/amazon/music/curate/provider/PlaybackProvider;", "setPlaybackProvider", "(Lcom/amazon/music/curate/provider/PlaybackProvider;)V", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "getRequestInterceptor", "()Lcom/amazon/hermes/RequestInterceptor;", "setRequestInterceptor", "(Lcom/amazon/hermes/RequestInterceptor;)V", "subscriptionProvider", "Lcom/amazon/music/curate/provider/SubscriptionProvider;", "getSubscriptionProvider", "()Lcom/amazon/music/curate/provider/SubscriptionProvider;", "setSubscriptionProvider", "(Lcom/amazon/music/curate/provider/SubscriptionProvider;)V", "visualRowItemListAdapter", "Lcom/amazon/music/curate/view/VisualRowItemListAdapter;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "registerCallback", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MySoundtrackFragment extends Fragment {
    private static final Logger LOG;
    private MadeForYouCallback callback;
    public DeviceInfoProvider deviceInfoProvider;
    public DownloadProvider downloadProvider;
    public FeatureFlagProvider featureFlagProvider;
    public NavigationProvider navigationProvider;
    public OverflowMenuProvider overflowMenuProvider;
    public PlaybackProvider playbackProvider;
    public RequestInterceptor requestInterceptor;
    public SubscriptionProvider subscriptionProvider;
    private VisualRowItemListAdapter visualRowItemListAdapter;

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

    public final NavigationProvider getNavigationProvider() {
        NavigationProvider navigationProvider = this.navigationProvider;
        if (navigationProvider != null) {
            return navigationProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("navigationProvider");
        return null;
    }

    public final void setNavigationProvider(NavigationProvider navigationProvider) {
        Intrinsics.checkNotNullParameter(navigationProvider, "<set-?>");
        this.navigationProvider = navigationProvider;
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

    static {
        Logger logger = LoggerFactory.getLogger(MySoundtrackFragment.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(MySoundtrackFr…t::class.java.simpleName)");
        LOG = logger;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = View.inflate(getActivity(), R.layout.my_soundtrack, null);
        this.visualRowItemListAdapter = new VisualRowItemListAdapter(this, getPlaybackProvider(), getDownloadProvider());
        View viewFindViewById = viewInflate.findViewById(R.id.my_soundtrack_list);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        }
        RecyclerView recyclerView = (RecyclerView) viewFindViewById;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        recyclerView.setAdapter(this.visualRowItemListAdapter);
        MySoundtrackRepository mySoundtrackRepository = new MySoundtrackRepository(getOverflowMenuProvider());
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNullExpressionValue(context, "context!!");
        MySoundtrackViewModel mySoundtrackViewModel = (MySoundtrackViewModel) new ViewModelProvider(this, new MySoundtrackViewModelFactory(context, mySoundtrackRepository)).get(MySoundtrackViewModel.class);
        mySoundtrackViewModel.setOnItemClick(new Function1<station, Unit>() { // from class: com.amazon.music.curate.view.MySoundtrackFragment.onCreateView.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(station stationVar) {
                invoke2(stationVar);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(station it) {
                Intrinsics.checkNotNullParameter(it, "it");
                PlaybackProvider playbackProvider = MySoundtrackFragment.this.getPlaybackProvider();
                FragmentActivity activity = MySoundtrackFragment.this.getActivity();
                Intrinsics.checkNotNull(activity);
                playbackProvider.playStation(it, activity);
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SELECT_MADE_FOR_YOU).withPageType(PageType.MY_MUSIC_CLOUD_FRONT_PAGE).withEntityId(it.getStationKey()).withEntityIdType(EntityIdType.STATION_KEY).withInteractionType(InteractionType.TAP).withEntityPos(-1).build());
            }
        });
        mySoundtrackViewModel.setOnMoreClick(new Function1<View, Unit>() { // from class: com.amazon.music.curate.view.MySoundtrackFragment.onCreateView.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                FragmentActivity activity = MySoundtrackFragment.this.getActivity();
                Intrinsics.checkNotNull(activity);
                activity.openContextMenu(it);
            }
        });
        mySoundtrackViewModel.getMySoundtrack().observe(this, new Observer() { // from class: com.amazon.music.curate.view.MySoundtrackFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MySoundtrackFragment.m1943onCreateView$lambda0(this.f$0, (PagedList) obj);
            }
        });
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m1943onCreateView$lambda0(MySoundtrackFragment this$0, PagedList pagedList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VisualRowItemListAdapter visualRowItemListAdapter = this$0.visualRowItemListAdapter;
        if (visualRowItemListAdapter != null) {
            Intrinsics.checkNotNull(pagedList);
            visualRowItemListAdapter.submitList(pagedList);
        }
        MadeForYouCallback madeForYouCallback = this$0.callback;
        if (madeForYouCallback == null) {
            return;
        }
        madeForYouCallback.onMySoundtrackLoaded(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.callback = null;
    }

    public final MadeForYouCallback getCallback() {
        return this.callback;
    }

    public final void setCallback(MadeForYouCallback madeForYouCallback) {
        this.callback = madeForYouCallback;
    }

    public final void registerCallback(MadeForYouCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
    }
}
