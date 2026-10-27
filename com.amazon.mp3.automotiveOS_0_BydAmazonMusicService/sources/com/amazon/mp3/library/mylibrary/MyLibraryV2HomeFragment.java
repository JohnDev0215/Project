package com.amazon.mp3.library.mylibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.capability.Capabilities;
import com.amazon.mp3.curate.provider.CurateEndpointSettingsProvider;
import com.amazon.mp3.curate.provider.CurateFeatureFlagProvider;
import com.amazon.mp3.curate.provider.CurateFragmentsStackProvider;
import com.amazon.mp3.curate.provider.CurateLibraryNavigationProvider;
import com.amazon.mp3.curate.provider.CurateNetworkProvider;
import com.amazon.mp3.curate.provider.CuratePlaybackProvider;
import com.amazon.mp3.curate.provider.CuratePlaylistProvider;
import com.amazon.mp3.curate.provider.CurateStyleSheetProvider;
import com.amazon.mp3.curate.provider.CurateSubscriptionProvider;
import com.amazon.mp3.detailpages.playlist.PlaylistsPrivacyEnforcementRibbonExperience;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.navigation.LibraryPlaylistsDestinationHandler;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.PlaylistsImportRecurrenceExperience;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.view.BauhausRibbon;
import com.amazon.music.curate.skyfire.Curate;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.curate.skyfire.local.SyncScheduler;
import com.amazon.music.curate.skyfire.views.TemplateFragment;
import com.amazon.music.destination.LibraryPlaylistsDestination;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: MyLibraryV2HomeFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 22\u00020\u0001:\u00012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0002J&\u0010\u001c\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u0012H\u0016J\u0006\u0010$\u001a\u00020\u0012J\b\u0010%\u001a\u00020\u0012H\u0016J\b\u0010&\u001a\u00020\u0012H\u0016J\u001a\u0010'\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0006\u0010(\u001a\u00020\u0012J\u0018\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020\u0010H\u0002J\u0010\u0010/\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u00100\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0006\u00101\u001a\u00020\u0012R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/amazon/mp3/library/mylibrary/MyLibraryV2HomeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "featureBanner", "Landroid/widget/RelativeLayout;", "mCurate", "Lcom/amazon/music/curate/skyfire/Curate;", "mCurateBootstrapProviders", "Lcom/amazon/music/curate/skyfire/bootstrap/CurateBootstrapProviders;", "mRecurrenceExperience", "Lcom/amazon/mp3/util/PlaylistsImportRecurrenceExperience;", "styleSheetSubscription", "Lrx/Subscription;", "swipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "syncLibraryInitiated", "", "deInitStyling", "", "hideBannerToImportPlaylists", "view", "Landroid/view/View;", "hidePlaylistsPrivacyEnforcementRibbon", "initPrivacyRibbonStyling", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "initStyling", "loadLibrary", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onLibraryHomeFragmentShownOnBackPressed", "onPause", "onResume", "onViewCreated", "refreshLibraryIfStale", "sendUiClickMetric", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "shouldShowBannerToImportPlaylists", "showBannerToImportPlaylists", "showPlaylistsPrivacyEnforcementRibbon", "stopLoadingLibrary", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MyLibraryV2HomeFragment extends Fragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOG;
    private static final String TAG;
    private RelativeLayout featureBanner;
    private Curate mCurate;
    private CurateBootstrapProviders mCurateBootstrapProviders;
    private PlaylistsImportRecurrenceExperience mRecurrenceExperience;
    private Subscription styleSheetSubscription;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean syncLibraryInitiated;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(PageType.LIBRARY_LANDING_PAGE.getMetricsValue()).withPageSubType(PageSubType.LIBRARY_ONLINE_MODE.getMetricsValue()).withViewType(ScreenUtil.getScreenViewType(getContext())).build());
        final View view = inflater.inflate(R.layout.my_library_v2_front_page, container, false);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        this.swipeRefreshLayout = swipeRefreshLayout;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setColorSchemeResources(R.color.white);
        }
        SwipeRefreshLayout swipeRefreshLayout2 = this.swipeRefreshLayout;
        if (swipeRefreshLayout2 != null) {
            swipeRefreshLayout2.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.glass_2));
        }
        SwipeRefreshLayout swipeRefreshLayout3 = this.swipeRefreshLayout;
        if (swipeRefreshLayout3 != null) {
            swipeRefreshLayout3.setVisibility(0);
        }
        stopLoadingLibrary();
        SwipeRefreshLayout swipeRefreshLayout4 = this.swipeRefreshLayout;
        if (swipeRefreshLayout4 != null) {
            swipeRefreshLayout4.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment$$ExternalSyntheticLambda3
                @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
                public final void onRefresh() {
                    MyLibraryV2HomeFragment.m1329onCreateView$lambda0(this.f$0);
                }
            });
        }
        this.styleSheetSubscription = StyleSheetProvider.getStyleSheetObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MyLibraryV2HomeFragment.m1330onCreateView$lambda1(this.f$0, view, (StyleSheet) obj);
            }
        });
        if (AmazonApplication.getCapabilities().isSonicRushEnabled()) {
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.feature_banner);
            this.featureBanner = relativeLayout;
            if (relativeLayout != null) {
                relativeLayout.setBackgroundResource(R.drawable.ads_banner_sonic_rush);
            }
        }
        if (shouldShowBannerToImportPlaylists()) {
            Intrinsics.checkNotNullExpressionValue(view, "view");
            showBannerToImportPlaylists(view);
        } else {
            Intrinsics.checkNotNullExpressionValue(view, "view");
            hideBannerToImportPlaylists(view);
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m1329onCreateView$lambda0(MyLibraryV2HomeFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Curate.requestLibrarySync();
        this$0.loadLibrary();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-1, reason: not valid java name */
    public static final void m1330onCreateView$lambda1(MyLibraryV2HomeFragment this$0, View view, StyleSheet it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(view, "view");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.initStyling(view, it);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        loadLibrary();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        if (swipeRefreshLayout == null) {
            return;
        }
        swipeRefreshLayout.setEnabled(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        deInitStyling();
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setOnRefreshListener(null);
        }
        this.swipeRefreshLayout = null;
        stopLoadingLibrary();
        this.featureBanner = null;
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        Intrinsics.checkNotNullExpressionValue(fragments, "childFragmentManager.fragments");
        for (Fragment fragment : fragments) {
            if (fragment instanceof TemplateFragment) {
                FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
                Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "childFragmentManager.beginTransaction()");
                fragmentTransactionBeginTransaction.remove(fragment);
                fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            }
        }
        Curate.releaseStaticProviders();
        this.mCurate = null;
        this.mCurateBootstrapProviders = null;
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setEnabled(true);
        }
        if (BottomNavigationBarContainer.getInstance().isEnabled()) {
            BottomNavigationBarContainer.getInstance().setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.LIBRARY);
        }
        refreshLibraryIfStale();
    }

    public final void onLibraryHomeFragmentShownOnBackPressed() {
        if (isAdded()) {
            refreshLibraryIfStale();
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment$initStyling$1, reason: invalid class name */
    /* JADX INFO: compiled from: MyLibraryV2HomeFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment$initStyling$1", f = "MyLibraryV2HomeFragment.kt", i = {}, l = {ContentCatalogStatus.HAWKFIRE_OR_NOT_BOUNDARY}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ StyleSheet $styleSheet;
        final /* synthetic */ View $view;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(View view, StyleSheet styleSheet, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$view = view;
            this.$styleSheet = styleSheet;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MyLibraryV2HomeFragment.this.new AnonymousClass1(this.$view, this.$styleSheet, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0042  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0053  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r4.label
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L18
                if (r1 != r3) goto L10
                kotlin.ResultKt.throwOnFailure(r5)
                goto L37
            L10:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L18:
                kotlin.ResultKt.throwOnFailure(r5)
                com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment r5 = com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment.this
                android.content.Context r5 = r5.getContext()
                if (r5 != 0) goto L25
                r5 = 0
                goto L2b
            L25:
                com.amazon.mp3.detailpages.playlist.PlaylistsPrivacyEnforcementRibbonExperience r1 = new com.amazon.mp3.detailpages.playlist.PlaylistsPrivacyEnforcementRibbonExperience
                r1.<init>(r5)
                r5 = r1
            L2b:
                if (r5 != 0) goto L2e
                goto L40
            L2e:
                r4.label = r3
                java.lang.Object r5 = r5.shouldPresentRibbon(r4)
                if (r5 != r0) goto L37
                return r0
            L37:
                java.lang.Boolean r5 = (java.lang.Boolean) r5
                boolean r5 = r5.booleanValue()
                if (r5 != r3) goto L40
                r2 = r3
            L40:
                if (r2 == 0) goto L53
                com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment r5 = com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment.this
                android.view.View r0 = r4.$view
                com.amazon.music.views.library.styles.StyleSheet r1 = r4.$styleSheet
                com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment.access$initPrivacyRibbonStyling(r5, r0, r1)
                com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment r5 = com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment.this
                android.view.View r0 = r4.$view
                com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment.access$showPlaylistsPrivacyEnforcementRibbon(r5, r0)
                goto L5a
            L53:
                com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment r5 = com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment.this
                android.view.View r0 = r4.$view
                com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment.access$hidePlaylistsPrivacyEnforcementRibbon(r5, r0)
            L5a:
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final void initStyling(View view, StyleSheet styleSheet) {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass1(view, styleSheet, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initPrivacyRibbonStyling(View view, StyleSheet styleSheet) {
        BauhausRibbon bauhausRibbon = (BauhausRibbon) view.findViewById(R.id.playlist_privacy_enforcement_ribbon);
        bauhausRibbon.initViewStyling(styleSheet);
        bauhausRibbon.setRibbonTextAlignment(2);
        bauhausRibbon.setRibbonTextStyle(styleSheet, FontStyleKey.SECONDARY);
        bauhausRibbon.setRoundedBackground();
    }

    private final void deInitStyling() {
        Subscription subscription = this.styleSheetSubscription;
        if (subscription == null) {
            return;
        }
        subscription.unsubscribe();
    }

    public final void refreshLibraryIfStale() {
        if (SyncScheduler.shouldSyncContextMappingTable()) {
            loadLibrary();
            LOG.debug("refreshing library");
        }
    }

    private final void loadLibrary() {
        if (!isAdded()) {
            LOG.debug("Fragment no longer attached, returning...");
            return;
        }
        if (getContext() != null && getActivity() != null) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            if (!activity.isFinishing()) {
                FragmentActivity activity2 = getActivity();
                Intrinsics.checkNotNull(activity2);
                if (!activity2.isDestroyed()) {
                    LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.REQUEST, PageLoadLatencyCode.LIBRARY));
                    CurateFragmentsStackProvider curateFragmentsStackProvider = CurateFragmentsStackProvider.INSTANCE;
                    curateFragmentsStackProvider.setFragmentId(R.id.curate_my_library_content);
                    FragmentManager childFragmentManager = getChildFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
                    curateFragmentsStackProvider.setFragmentManager(childFragmentManager);
                    this.mCurateBootstrapProviders = new CurateBootstrapProviders(getActivity(), new CurateEndpointSettingsProvider(), curateFragmentsStackProvider, new CurateLibraryNavigationProvider(new WeakReference(getActivity())), new CurateNetworkProvider(), new CurateStyleSheetProvider(), new CuratePlaybackProvider(), new CurateSubscriptionProvider(), new CuratePlaylistProvider(), new CurateFeatureFlagProvider());
                    Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment$$ExternalSyntheticLambda0
                        @Override // rx.functions.Action1
                        public final void call(Object obj) {
                            MyLibraryV2HomeFragment.m1326loadLibrary$lambda2(this.f$0, (Subscriber) obj);
                        }
                    }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment$$ExternalSyntheticLambda1
                        @Override // rx.functions.Action1
                        public final void call(Object obj) {
                            MyLibraryV2HomeFragment.m1327loadLibrary$lambda3(this.f$0, (Void) obj);
                        }
                    }, new Action1() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment$$ExternalSyntheticLambda2
                        @Override // rx.functions.Action1
                        public final void call(Object obj) {
                            MyLibraryV2HomeFragment.m1328loadLibrary$lambda4(this.f$0, (Throwable) obj);
                        }
                    });
                    return;
                }
            }
        }
        LOG.debug("Null context or activity, returning...");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadLibrary$lambda-2, reason: not valid java name */
    public static final void m1326loadLibrary$lambda2(MyLibraryV2HomeFragment this$0, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(subscriber, "subscriber");
        if (!this$0.syncLibraryInitiated) {
            this$0.syncLibraryInitiated = true;
            this$0.mCurate = Curate.create(this$0.mCurateBootstrapProviders);
            CurateBootstrapProviders curateBootstrapProviders = this$0.mCurateBootstrapProviders;
            Curate.setStyleSheetStaticProvider(curateBootstrapProviders == null ? null : curateBootstrapProviders.getStyleSheetProvider());
            CurateBootstrapProviders curateBootstrapProviders2 = this$0.mCurateBootstrapProviders;
            Curate.setPlaybackStaticProvider(curateBootstrapProviders2 == null ? null : curateBootstrapProviders2.getPlaybackProvider());
            CurateBootstrapProviders curateBootstrapProviders3 = this$0.mCurateBootstrapProviders;
            Curate.setSubscriptionStaticProvider(curateBootstrapProviders3 == null ? null : curateBootstrapProviders3.getSubscriptionProvider());
            CurateBootstrapProviders curateBootstrapProviders4 = this$0.mCurateBootstrapProviders;
            Curate.setNetworkStaticProvider(curateBootstrapProviders4 == null ? null : curateBootstrapProviders4.getNetworkProvider());
            this$0.stopLoadingLibrary();
            Curate curate = this$0.mCurate;
            if (curate != null) {
                curate.library();
            }
        }
        subscriber.onNext(null);
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadLibrary$lambda-3, reason: not valid java name */
    public static final void m1327loadLibrary$lambda3(MyLibraryV2HomeFragment this$0, Void r3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.syncLibraryInitiated = false;
        SwipeRefreshLayout swipeRefreshLayout = this$0.swipeRefreshLayout;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
        LOG.debug("Library loaded...");
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.LOAD_COMPLETE, PageLoadLatencyCode.LIBRARY));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadLibrary$lambda-4, reason: not valid java name */
    public static final void m1328loadLibrary$lambda4(MyLibraryV2HomeFragment this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.syncLibraryInitiated = false;
        SwipeRefreshLayout swipeRefreshLayout = this$0.swipeRefreshLayout;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.LOAD_COMPLETE, PageLoadLatencyCode.LIBRARY));
        LOG.error("Failed to load library", th);
    }

    public final void stopLoadingLibrary() {
        Curate curate = this.mCurate;
        if (curate == null) {
            return;
        }
        curate.stopLoadingLibrary();
    }

    /* JADX INFO: compiled from: MyLibraryV2HomeFragment.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/library/mylibrary/MyLibraryV2HomeFragment$Companion;", "", "()V", "LOG", "Lorg/slf4j/Logger;", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "newInstance", "Lcom/amazon/mp3/library/mylibrary/MyLibraryV2HomeFragment;", "args", "Landroid/os/Bundle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return MyLibraryV2HomeFragment.TAG;
        }

        public final MyLibraryV2HomeFragment newInstance(Bundle args) {
            MyLibraryV2HomeFragment myLibraryV2HomeFragment = new MyLibraryV2HomeFragment();
            myLibraryV2HomeFragment.setArguments(args);
            return myLibraryV2HomeFragment;
        }
    }

    static {
        String simpleName = MyLibraryV2HomeFragment.class.getSimpleName();
        TAG = simpleName;
        Logger logger = LoggerFactory.getLogger(simpleName);
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(TAG)");
        LOG = logger;
    }

    private final void sendUiClickMetric(ActionType actionType, PageType pageType) {
        UiClickEvent uiClickEventBuild = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withPageType(pageType).withEventTime(System.currentTimeMillis()).build();
        if (uiClickEventBuild != null) {
            MetricsHolder.getManager().handleEvent(uiClickEventBuild);
        }
    }

    private final boolean shouldShowBannerToImportPlaylists() {
        Context context = getContext();
        this.mRecurrenceExperience = context == null ? null : PlaylistsImportRecurrenceExperience.INSTANCE.get(context);
        if (Capabilities.shouldShowBannerToImportPlaylists()) {
            PlaylistsImportRecurrenceExperience playlistsImportRecurrenceExperience = this.mRecurrenceExperience;
            if (playlistsImportRecurrenceExperience != null && playlistsImportRecurrenceExperience.isEligibleToExperience()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPlaylistsPrivacyEnforcementRibbon(View view) {
        MusicHomeActivity musicHomeActivity = (MusicHomeActivity) getActivity();
        if (musicHomeActivity != null) {
            musicHomeActivity.dismissDynMsgRibbon();
        }
        final BauhausRibbon bauhausRibbon = (BauhausRibbon) view.findViewById(R.id.playlist_privacy_enforcement_ribbon);
        String string = getString(R.string.dmusic_playlist_privacy_ribbon_text);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusi…list_privacy_ribbon_text)");
        bauhausRibbon.setRibbonText(string);
        bauhausRibbon.setVisibility(0);
        bauhausRibbon.setNavigationButtonClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MyLibraryV2HomeFragment.m1334showPlaylistsPrivacyEnforcementRibbon$lambda7(this.f$0, view2);
            }
        });
        bauhausRibbon.setRibbonClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MyLibraryV2HomeFragment.m1335showPlaylistsPrivacyEnforcementRibbon$lambda8(this.f$0, view2);
            }
        });
        bauhausRibbon.setDismissButtonClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MyLibraryV2HomeFragment.m1336showPlaylistsPrivacyEnforcementRibbon$lambda9(this.f$0, bauhausRibbon, view2);
            }
        });
        MetricsLogger.emitUIContentViewMetric(ContentName.PLAYLISTS_PRIVACY_ENFORCEMENT_FOLLOWED_RIBBON, ContainerType.RIBBON);
    }

    private static final void showPlaylistsPrivacyEnforcementRibbon$recordPlaylistsPrivacyRibbonExperience(MyLibraryV2HomeFragment myLibraryV2HomeFragment) {
        Context context = myLibraryV2HomeFragment.getContext();
        PlaylistsPrivacyEnforcementRibbonExperience playlistsPrivacyEnforcementRibbonExperience = context == null ? null : new PlaylistsPrivacyEnforcementRibbonExperience(context);
        if (playlistsPrivacyEnforcementRibbonExperience == null) {
            return;
        }
        playlistsPrivacyEnforcementRibbonExperience.disableExperiencePresentation();
    }

    private static final void showPlaylistsPrivacyEnforcementRibbon$navigateToLibraryPlaylists(MyLibraryV2HomeFragment myLibraryV2HomeFragment) {
        showPlaylistsPrivacyEnforcementRibbon$recordPlaylistsPrivacyRibbonExperience(myLibraryV2HomeFragment);
        myLibraryV2HomeFragment.sendUiClickMetric(ActionType.OPEN_PLAYLISTS_PRIVACY_ENFORCEMENT_FOLLOWED_RIBBON, PageType.LIBRARY_LANDING_PAGE);
        new LibraryPlaylistsDestinationHandler().navigateTo(myLibraryV2HomeFragment.getContext(), new LibraryPlaylistsDestination("ribbon_ref", "ribbon_tag"));
    }

    private static final void showPlaylistsPrivacyEnforcementRibbon$dismissPlaylistsPrivacyRibbon(MyLibraryV2HomeFragment myLibraryV2HomeFragment, BauhausRibbon bauhausRibbon) {
        showPlaylistsPrivacyEnforcementRibbon$recordPlaylistsPrivacyRibbonExperience(myLibraryV2HomeFragment);
        myLibraryV2HomeFragment.sendUiClickMetric(ActionType.CLOSE_PLAYLISTS_PRIVACY_ENFORCEMENT_FOLLOWED_RIBBON, PageType.LIBRARY_LANDING_PAGE);
        bauhausRibbon.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showPlaylistsPrivacyEnforcementRibbon$lambda-7, reason: not valid java name */
    public static final void m1334showPlaylistsPrivacyEnforcementRibbon$lambda7(MyLibraryV2HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        showPlaylistsPrivacyEnforcementRibbon$navigateToLibraryPlaylists(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showPlaylistsPrivacyEnforcementRibbon$lambda-8, reason: not valid java name */
    public static final void m1335showPlaylistsPrivacyEnforcementRibbon$lambda8(MyLibraryV2HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        showPlaylistsPrivacyEnforcementRibbon$navigateToLibraryPlaylists(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showPlaylistsPrivacyEnforcementRibbon$lambda-9, reason: not valid java name */
    public static final void m1336showPlaylistsPrivacyEnforcementRibbon$lambda9(MyLibraryV2HomeFragment this$0, BauhausRibbon bauhausRibbon, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        showPlaylistsPrivacyEnforcementRibbon$dismissPlaylistsPrivacyRibbon(this$0, bauhausRibbon);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hidePlaylistsPrivacyEnforcementRibbon(View view) {
        ((BauhausRibbon) view.findViewById(R.id.playlist_privacy_enforcement_ribbon)).setVisibility(8);
    }

    private final void showBannerToImportPlaylists(View view) {
        MusicHomeActivity musicHomeActivity = (MusicHomeActivity) getActivity();
        if (musicHomeActivity != null) {
            musicHomeActivity.dismissDynMsgRibbon();
        }
        View viewFindViewById = view.findViewById(R.id.import_playlists_banner);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.import_playlists_banner)");
        final LinearLayout linearLayout = (LinearLayout) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.import_playlists_link_area);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.import_playlists_link_area)");
        View viewFindViewById3 = view.findViewById(R.id.import_playlists_close_area);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "view.findViewById(R.id.i…ort_playlists_close_area)");
        View viewFindViewById4 = view.findViewById(R.id.import_playlists_close_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "view.findViewById(R.id.i…t_playlists_close_button)");
        linearLayout.setVisibility(0);
        MetricsLogger.emitUIContentViewMetric(ContentName.IMPORT_PLAYLISTS_BANNER, ContainerType.IMPORT_PLAYLISTS_DIALOG);
        ((RelativeLayout) viewFindViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MyLibraryV2HomeFragment.m1331showBannerToImportPlaylists$lambda11(linearLayout, this, view2);
            }
        });
        ((RelativeLayout) viewFindViewById3).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MyLibraryV2HomeFragment.m1332showBannerToImportPlaylists$lambda12(linearLayout, this, view2);
            }
        });
        ((ImageButton) viewFindViewById4).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MyLibraryV2HomeFragment.m1333showBannerToImportPlaylists$lambda13(linearLayout, this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showBannerToImportPlaylists$lambda-11, reason: not valid java name */
    public static final void m1331showBannerToImportPlaylists$lambda11(LinearLayout importPlaylistsBanner, MyLibraryV2HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(importPlaylistsBanner, "$importPlaylistsBanner");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(EndpointsProvider.get().getPlaylistImportEndpoint()));
        importPlaylistsBanner.setVisibility(8);
        PlaylistsImportRecurrenceExperience playlistsImportRecurrenceExperience = this$0.mRecurrenceExperience;
        if (playlistsImportRecurrenceExperience != null) {
            playlistsImportRecurrenceExperience.bannerClosed();
        }
        this$0.sendUiClickMetric(ActionType.OPEN_IMPORT_PLAYLISTS_LINK, PageType.LIBRARY_LANDING_PAGE);
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showBannerToImportPlaylists$lambda-12, reason: not valid java name */
    public static final void m1332showBannerToImportPlaylists$lambda12(LinearLayout importPlaylistsBanner, MyLibraryV2HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(importPlaylistsBanner, "$importPlaylistsBanner");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        importPlaylistsBanner.setVisibility(8);
        PlaylistsImportRecurrenceExperience playlistsImportRecurrenceExperience = this$0.mRecurrenceExperience;
        if (playlistsImportRecurrenceExperience != null) {
            playlistsImportRecurrenceExperience.bannerClosed();
        }
        this$0.sendUiClickMetric(ActionType.CLOSE_IMPORT_PLAYLISTS_BANNER, PageType.LIBRARY_LANDING_PAGE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showBannerToImportPlaylists$lambda-13, reason: not valid java name */
    public static final void m1333showBannerToImportPlaylists$lambda13(LinearLayout importPlaylistsBanner, MyLibraryV2HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(importPlaylistsBanner, "$importPlaylistsBanner");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        importPlaylistsBanner.setVisibility(8);
        PlaylistsImportRecurrenceExperience playlistsImportRecurrenceExperience = this$0.mRecurrenceExperience;
        if (playlistsImportRecurrenceExperience != null) {
            playlistsImportRecurrenceExperience.bannerClosed();
        }
        this$0.sendUiClickMetric(ActionType.CLOSE_IMPORT_PLAYLISTS_BANNER, PageType.LIBRARY_LANDING_PAGE);
    }

    private final void hideBannerToImportPlaylists(View view) {
        View viewFindViewById = view.findViewById(R.id.import_playlists_banner);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.import_playlists_banner)");
        ((LinearLayout) viewFindViewById).setVisibility(8);
    }
}
