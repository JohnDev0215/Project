package com.amazon.mp3.find.view;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import Touch.GalleryTemplateInterface.v1_0.GalleryTemplate;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.DisplayGenericErrorMethod;
import Touch.WidgetsInterface.v1_0.MessageElement;
import Touch.WidgetsInterface.v1_0.NavigationBarElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.BrushFragment;
import com.amazon.mp3.feed.FeedButton;
import com.amazon.mp3.find.skyfire.FindSkyfireFactory;
import com.amazon.mp3.find.util.AlexaUtil;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.find.featuregating.FindFeatureGating;
import com.amazon.music.find.skyfire.Constants;
import com.amazon.music.find.skyfire.Contexts;
import com.amazon.music.find.skyfire.Methods;
import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.skyfire.ui.fragment.TemplateFragment;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.utils.FadingBackdropHelper;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: FindLandingFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\n\u0018\u0000 V2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003VWXB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020$H\u0002J\b\u0010&\u001a\u00020$H\u0002J\b\u0010'\u001a\u00020$H\u0002J\b\u0010(\u001a\u00020\u0005H\u0002J\b\u0010)\u001a\u00020$H\u0002J\b\u0010*\u001a\u00020+H\u0002J\u001c\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u00010\u0002H\u0002J\u0010\u00101\u001a\u00020$2\u0006\u00102\u001a\u000203H\u0002J\u0012\u00104\u001a\u00020$2\b\u00105\u001a\u0004\u0018\u00010-H\u0002J\u0012\u00106\u001a\u00020$2\b\u00107\u001a\u0004\u0018\u00010-H\u0002J\b\u00108\u001a\u00020$H\u0002J\b\u00109\u001a\u00020$H\u0002J\b\u0010:\u001a\u00020\u0011H\u0002J\b\u0010;\u001a\u00020\u0011H\u0002J\b\u0010<\u001a\u00020\u0011H\u0002J\u0012\u0010=\u001a\u00020$2\b\u0010>\u001a\u0004\u0018\u00010/H\u0016J&\u0010?\u001a\u0004\u0018\u00010\u000b2\u0006\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010C2\b\u0010>\u001a\u0004\u0018\u00010/H\u0016J\b\u0010D\u001a\u00020$H\u0016J\u0010\u0010E\u001a\u00020$2\u0006\u0010F\u001a\u00020GH\u0016J\b\u0010H\u001a\u00020$H\u0016J\b\u0010I\u001a\u00020$H\u0016J\b\u0010J\u001a\u00020$H\u0016J\b\u0010K\u001a\u00020$H\u0016J$\u0010L\u001a\u00020$2\u0006\u0010M\u001a\u00020-2\u0012\u0010N\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020-0OH\u0016J\b\u0010P\u001a\u00020$H\u0002J\b\u0010Q\u001a\u00020$H\u0002J\b\u0010R\u001a\u00020$H\u0002J\b\u0010S\u001a\u00020$H\u0002J\b\u0010T\u001a\u00020$H\u0002J\b\u0010U\u001a\u00020$H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00078\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u000b8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001b\u001a\u00020\u001c8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u00020\u001e8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u001f\u001a\u00020 8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010!\u001a\u00020\"8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000¨\u0006Y"}, d2 = {"Lcom/amazon/mp3/find/view/FindLandingFragment;", "Lcom/amazon/music/skyfire/ui/fragment/TemplateFragment;", "LTouch/GalleryTemplateInterface/v1_0/GalleryTemplate;", "()V", "actionBarView", "Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "backdrop", "Landroid/widget/ImageView;", "contentAdapter", "Lcom/amazon/mp3/find/view/FindLandingFragment$ContentAdapter;", "errorView", "Landroid/view/View;", "fadingBackdropHelper", "Lcom/amazon/music/skyfire/ui/utils/FadingBackdropHelper;", "findSearchBarView", "Lcom/amazon/music/find/view/FindSearchBarView;", "inErrorState", "", "inOfflineState", "mAccountStateChangeListener", "Lcom/amazon/music/account/AccountStateChangeListener;", "mAlexaTooltipExperience", "Lcom/amazon/mp3/find/view/AlexaTooltipExperience;", "mFeedButton", "Lcom/amazon/mp3/feed/FeedButton;", "mFindLandingATCSkipTooltipExperience", "Lcom/amazon/mp3/find/view/FindLandingATCSkipTooltipExperience;", "mainContent", "Landroidx/recyclerview/widget/RecyclerView;", "messageContainer", "Landroid/widget/FrameLayout;", "spinner", "Landroid/widget/ProgressBar;", "swipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "configureActionBar", "", "configureFindLandingActionBar", "configureFindSeeMoreActionBar", "connectActionBarView", "createActionBarView", "disconnectActionBarView", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getSkillPath", "", "arguments", "Landroid/os/Bundle;", "template", "goSearch", "mode", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "handleDeepLink", "link", "handleUri", "uri", "initActionBarView", "initSwipeToRefresh", "isCustomerEligibleForFindUpsell", "isDynMsgRibbonVisible", "isUpsellButtonLayoutVisible", "onActivityCreated", "savedInstanceState", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onHandleMethod", "method", "LCoreInterface/v1_0/Method;", "onResume", "onStart", "onStop", "onTemplateChanged", "onTriggerExternalAction", "name", "args", "", "rebind", "refreshFindPage", "styleActionBar", "styleSearchBarViewMargins", "updateFindUpsell", "updateQueryHint", "Companion", "ContentAdapter", "ContentViewHolder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindLandingFragment extends TemplateFragment<GalleryTemplate> {
    private static final Logger logger = LoggerFactory.getLogger(FindLandingFragment.class.getName());
    private BauhausActionBarView actionBarView;

    @ViewFinder.AView
    private ImageView backdrop;

    @ViewFinder.AView
    private View errorView;
    private FadingBackdropHelper fadingBackdropHelper;
    private FindSearchBarView findSearchBarView;
    private boolean inErrorState;
    private boolean inOfflineState;
    private AlexaTooltipExperience mAlexaTooltipExperience;
    private FeedButton mFeedButton;
    private FindLandingATCSkipTooltipExperience mFindLandingATCSkipTooltipExperience;

    @ViewFinder.AView
    private RecyclerView mainContent;

    @ViewFinder.AView
    private FrameLayout messageContainer;

    @ViewFinder.AView
    private ProgressBar spinner;

    @ViewFinder.AView
    private SwipeRefreshLayout swipeRefreshLayout;
    private final ContentAdapter contentAdapter = new ContentAdapter(this);
    private AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.find.view.FindLandingFragment$$ExternalSyntheticLambda0
        @Override // com.amazon.music.account.AccountStateChangeListener
        public final void onAccountStateChange(Set set) {
            FindLandingFragment.m1038mAccountStateChangeListener$lambda0(this.f$0, set);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mAccountStateChangeListener$lambda-0, reason: not valid java name */
    public static final void m1038mAccountStateChangeListener$lambda0(FindLandingFragment this$0, Set set) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getLogger().debug(Intrinsics.stringPlus("SPL::mAccountStateChangeListener. set: ", set));
        if (set.contains(AccountStateField.USER_BENEFITS) || set.contains(AccountStateField.USER_TERMS_OF_USE_MARKETPLACE) || set.contains(AccountStateField.USER_HAS_ACCEPTED_TERMS_OF_USE) || set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE)) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new FindLandingFragment$mAccountStateChangeListener$1$1(this$0, null), 3, null);
        }
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment
    public void onTemplateChanged() {
        this.inErrorState = false;
        rebind();
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment, com.amazon.music.skyfire.ui.skyfire.TemplateMethodListener
    public void onHandleMethod(Method method) throws IllegalAccessException, NoSuchFieldException {
        Intrinsics.checkNotNullParameter(method, "method");
        if (method instanceof DisplayGenericErrorMethod) {
            getLogger().error(method.toString());
            this.inErrorState = true;
            rebind();
            return;
        }
        super.onHandleMethod(method);
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment
    public void onTriggerExternalAction(String name, Map<String, String> args) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(args, "args");
        int iHashCode = name.hashCode();
        if (iHashCode != -2020843247) {
            if (iHashCode != -137257358) {
                if (iHashCode == 454393126 && name.equals("OPEN_TARGET")) {
                    String str = args.get("TARGET");
                    if (str != null && StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) FindSearchBarView.URI_PREFIX, false, 2, (Object) null)) {
                        getLogger().debug("SPL::openTarget using URI handler.");
                        handleUri(str);
                        return;
                    } else {
                        try {
                            getLogger().debug("SPL::openTarget using DeepLink handler.");
                            handleDeepLink(str);
                            return;
                        } catch (Exception unused) {
                            getLogger().error(Intrinsics.stringPlus("SPL::openTarget failed to handle deeplink: ", str));
                            return;
                        }
                    }
                }
            } else if (name.equals(Methods.openFindSeeMore)) {
                String str2 = args.get("TITLE");
                if (str2 == null) {
                    str2 = "";
                }
                String str3 = args.get(Contexts.OpenFindSeeMore.contentId);
                String str4 = str3 != null ? str3 : "";
                Map<String, String> mapMapOf = StringsKt__StringsJVMKt.isBlank(str4) ? null : MapsKt__MapsJVMKt.mapOf(TuplesKt.to("contentId", str4));
                Bundle bundle = new Bundle();
                bundle.putString("TOOLBAR_TITLE_KEY", str2);
                setArguments(FindSkyfireFactory.INSTANCE.createSkyfireSkill(Constants.SHOW_FIND_CONTENT_SKILL, mapMapOf, bundle));
                resetTemplate();
                fetchInitialMethods();
                configureFindSeeMoreActionBar();
                return;
            }
        } else if (name.equals(Methods.handleError)) {
            if (ConnectivityUtil.hasAnyInternetConnection(getContext())) {
                this.inErrorState = true;
            } else {
                this.inOfflineState = true;
            }
            rebind();
            return;
        }
        super.onTriggerExternalAction(name, args);
    }

    private final void rebind() {
        GalleryTemplate value = getTemplate().getValue();
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        ViewGroup viewGroup = null;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setRefreshing(false);
        ImageView imageView = this.backdrop;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backdrop");
            imageView = null;
        }
        ViewKt.hideIf(imageView, this.inErrorState);
        ProgressBar progressBar = this.spinner;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinner");
            progressBar = null;
        }
        ViewKt.collapseIf(progressBar, value != null || this.inErrorState || this.inOfflineState);
        View view = this.errorView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorView");
            view = null;
        }
        ViewKt.collapseIf(view, !this.inErrorState);
        RecyclerView recyclerView = this.mainContent;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
            recyclerView = null;
        }
        ViewKt.hideIf(recyclerView, value == null || value.messageElement() != null || this.inErrorState);
        FrameLayout frameLayout = this.messageContainer;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("messageContainer");
            frameLayout = null;
        }
        ViewKt.collapseIf(frameLayout, (value == null ? null : value.messageElement()) == null);
        if (value == null) {
            initSwipeToRefresh();
            return;
        }
        MessageElement messageElement = value.messageElement();
        if (messageElement != null) {
            FrameLayout frameLayout2 = this.messageContainer;
            if (frameLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageContainer");
                frameLayout2 = null;
            }
            frameLayout2.removeAllViews();
            ElementBindingProvider elementBindingProvider = getElementBindingProvider();
            FrameLayout frameLayout3 = this.messageContainer;
            if (frameLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageContainer");
            } else {
                viewGroup = frameLayout3;
            }
            elementBindingProvider.attachView(messageElement, viewGroup);
            return;
        }
        String strBackgroundImage = value.backgroundImage();
        if (strBackgroundImage != null) {
            FadingBackdropHelper fadingBackdropHelper = this.fadingBackdropHelper;
            if (fadingBackdropHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fadingBackdropHelper");
                fadingBackdropHelper = null;
            }
            fadingBackdropHelper.setBackdropURL(strBackgroundImage);
        }
        this.contentAdapter.notifyDataSetChanged();
        if (value.canSwipeToRefresh()) {
            initSwipeToRefresh();
            return;
        }
        SwipeRefreshLayout swipeRefreshLayout2 = this.swipeRefreshLayout;
        if (swipeRefreshLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
        } else {
            viewGroup = swipeRefreshLayout2;
        }
        viewGroup.setEnabled(false);
    }

    private final void initSwipeToRefresh() {
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.mp3.find.view.FindLandingFragment$$ExternalSyntheticLambda1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                FindLandingFragment.m1037initSwipeToRefresh$lambda3(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initSwipeToRefresh$lambda-3, reason: not valid java name */
    public static final void m1037initSwipeToRefresh$lambda3(FindLandingFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.fetchInitialMethods();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshFindPage() {
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.INITIALIZING, PageLoadLatencyCode.FIND_V2));
        setArguments(FindSkyfireFactory.INSTANCE.createSkyfireSkill(Constants.SHOW_FIND_SKILL, null, getArguments()));
        resetTemplate();
        fetchInitialMethods();
        if (isCustomerEligibleForFindUpsell()) {
            styleSearchBarViewMargins();
        }
        configureFindLandingActionBar();
    }

    private final PageType getPageType() {
        return PageType.BROWSE_FIND;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate$default;
        StyleSheet styleSheet;
        Context context;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.INITIALIZING, PageLoadLatencyCode.FIND_V2));
        RecyclerView recyclerView = null;
        View viewFindViews = (container == null || (viewInflate$default = ViewGroupKt.inflate$default(container, R.layout.fragment_gallery, false, 2, null)) == null) ? null : ViewFinderKt.findViews(viewInflate$default, this);
        if (viewFindViews == null || (styleSheet = getStyleSheet()) == null || (context = getContext()) == null) {
            return null;
        }
        ImageView imageView = this.backdrop;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backdrop");
            imageView = null;
        }
        RecyclerView recyclerView2 = this.mainContent;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
            recyclerView2 = null;
        }
        this.fadingBackdropHelper = new FadingBackdropHelper(imageView, recyclerView2, styleSheet);
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setColorSchemeResources(android.R.color.white);
        SwipeRefreshLayout swipeRefreshLayout2 = this.swipeRefreshLayout;
        if (swipeRefreshLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout2 = null;
        }
        swipeRefreshLayout2.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(context, R.color.glass_2));
        RecyclerView recyclerView3 = this.mainContent;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
            recyclerView3 = null;
        }
        recyclerView3.setLayoutManager(new LinearLayoutManager(context, 1, false));
        RecyclerView recyclerView4 = this.mainContent;
        if (recyclerView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
        } else {
            recyclerView = recyclerView4;
        }
        recyclerView.setAdapter(this.contentAdapter);
        rebind();
        return viewFindViews;
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLogger().debug("SPL::onActivityCreated");
        initActionBarView();
        connectActionBarView();
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment, androidx.fragment.app.Fragment
    public void onStop() {
        AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getLogger().debug("SPL::onResume");
        SearchTabFragmentHost.setBottomBarTab();
        styleActionBar();
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        getLogger().debug("SPL::onDestroyView");
        disconnectActionBarView();
        FeedButton feedButton = this.mFeedButton;
        if (feedButton != null) {
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
            feedButton.removeObservers(fragmentActivityRequireActivity);
        }
        this.mFeedButton = null;
        AlexaTooltipExperience alexaTooltipExperience = this.mAlexaTooltipExperience;
        if (alexaTooltipExperience != null) {
            alexaTooltipExperience.onDestroy();
        }
        FindLandingATCSkipTooltipExperience findLandingATCSkipTooltipExperience = this.mFindLandingATCSkipTooltipExperience;
        if (findLandingATCSkipTooltipExperience != null) {
            findLandingATCSkipTooltipExperience.onDestroy();
        }
        super.onDestroyView();
    }

    private final void initActionBarView() {
        FragmentActivity activity;
        getLogger().debug("SPL::initActionBarView");
        if (this.actionBarView == null && (activity = getActivity()) != null) {
            FindSearchBarView findSearchBarView = new FindSearchBarView(activity);
            this.findSearchBarView = findSearchBarView;
            findSearchBarView.disableSearchViewKeyboard();
            BauhausActionBarView bauhausActionBarViewCreateActionBarView = createActionBarView();
            this.actionBarView = bauhausActionBarViewCreateActionBarView;
            if (bauhausActionBarViewCreateActionBarView != null) {
                this.mFeedButton = new FeedButton(bauhausActionBarViewCreateActionBarView, activity);
                updateQueryHint();
                updateFindUpsell();
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.view.View");
            }
        }
        BauhausActionBarView bauhausActionBarView = this.actionBarView;
        if (bauhausActionBarView != null) {
            MusicHomeActivity musicHomeActivity = (MusicHomeActivity) getActivity();
            if (musicHomeActivity != null) {
                musicHomeActivity.setHeaderView(bauhausActionBarView);
                musicHomeActivity.restoreHomeButton();
                if (isCustomerEligibleForFindUpsell()) {
                    musicHomeActivity.dismissDynMsgRibbon();
                    AlexaTooltipExperience alexaTooltipExperience = this.mAlexaTooltipExperience;
                    if (alexaTooltipExperience != null) {
                        alexaTooltipExperience.hideToolTip();
                    }
                }
            }
            bauhausActionBarView.setBackButtonVisible(false);
            bauhausActionBarView.addTopMargin();
            bauhausActionBarView.setLogoVisible(true);
            if (!PlatformUtil.isFireOS()) {
                bauhausActionBarView.setFeedButtonVisible(true);
            }
        }
        BauhausActionBarView bauhausActionBarView2 = this.actionBarView;
        if (bauhausActionBarView2 != null) {
            bauhausActionBarView2.setBackButtonVisible(false);
        }
        BauhausActionBarView bauhausActionBarView3 = this.actionBarView;
        if (bauhausActionBarView3 != null) {
            bauhausActionBarView3.setFeedButtonVisible(!PlatformUtil.isFireOS());
        }
        BauhausActionBarView bauhausActionBarView4 = this.actionBarView;
        if (bauhausActionBarView4 == null) {
            return;
        }
        bauhausActionBarView4.setLayoutTransition(null);
    }

    private final BauhausActionBarView createActionBarView() {
        return new BauhausActionBarView(getActivity(), null, Boolean.valueOf(AmazonApplication.getCapabilities().isCustomerProfileSupported()), Boolean.TRUE, this.findSearchBarView);
    }

    private final void updateQueryHint() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        int i = !ConnectivityUtil.hasAnyInternetConnection(context) ? R.string.dmusic_prime_search_library_offline_query_hint : PodcastFeatureGating.PODCAST.isEnabled() && !ChildUserUtil.INSTANCE.isChildUser(context) ? R.string.dmusic_prime_search_music_and_podcasts_query_hint : R.string.dmusic_prime_search_prime_query_hint;
        FindSearchBarView findSearchBarView = this.findSearchBarView;
        if (findSearchBarView == null) {
            return;
        }
        String string = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "it.getString(hintId)");
        findSearchBarView.setQueryHint(string);
    }

    private final void updateFindUpsell() {
        StyleSheet styleSheet;
        BauhausActionBarView bauhausActionBarView = this.actionBarView;
        if (bauhausActionBarView == null) {
            return;
        }
        if (isCustomerEligibleForFindUpsell()) {
            if (!bauhausActionBarView.isFindUpsellButtonLayoutVisible() && (styleSheet = getStyleSheet()) != null) {
                bauhausActionBarView.setFindUpsellButton(styleSheet, new View.OnClickListener() { // from class: com.amazon.mp3.find.view.FindLandingFragment$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FindLandingFragment.m1039updateFindUpsell$lambda10$lambda9$lambda8(this.f$0, view);
                    }
                });
            }
            bauhausActionBarView.showFindUpsellButton();
            return;
        }
        bauhausActionBarView.hideFindUpsellButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateFindUpsell$lambda-10$lambda-9$lambda-8, reason: not valid java name */
    public static final void m1039updateFindUpsell$lambda10$lambda9$lambda8(FindLandingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UpsellUtil.showBlockingUpsell(this$0.getActivity(), null, UpsellReason.PERSISTENT_UPSELL, UpsellSourceEntity.PERSISTENT_UPSELL_INGRESS);
    }

    private final void connectActionBarView() {
        View.OnKeyListener onKeyListener = new View.OnKeyListener() { // from class: com.amazon.mp3.find.view.FindLandingFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return FindLandingFragment.m1032connectActionBarView$lambda11(this.f$0, view, i, keyEvent);
            }
        };
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.find.view.FindLandingFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FindLandingFragment.m1033connectActionBarView$lambda12(this.f$0, view);
            }
        };
        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.amazon.mp3.find.view.FindLandingFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                FindLandingFragment.m1034connectActionBarView$lambda13(this.f$0, view, z);
            }
        };
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: com.amazon.mp3.find.view.FindLandingFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FindLandingFragment.m1035connectActionBarView$lambda14(this.f$0, view);
            }
        };
        View.OnClickListener onClickListener3 = new View.OnClickListener() { // from class: com.amazon.mp3.find.view.FindLandingFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FindLandingFragment.m1036connectActionBarView$lambda15(this.f$0, view);
            }
        };
        FindSearchBarView findSearchBarView = this.findSearchBarView;
        if (findSearchBarView != null) {
            findSearchBarView.setSearchViewOnKeyListener(onKeyListener);
            findSearchBarView.setSearchViewOnClickListener(onClickListener);
            findSearchBarView.setSearchViewFocusChangeListener(onFocusChangeListener);
            findSearchBarView.setBackButtonListener(onClickListener2);
            findSearchBarView.setAlexaButtonListener(onClickListener3);
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            this.mAlexaTooltipExperience = new AlexaTooltipExperience(contextRequireContext);
            Context contextRequireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
            this.mFindLandingATCSkipTooltipExperience = new FindLandingATCSkipTooltipExperience(contextRequireContext2);
        }
        configureActionBar();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: connectActionBarView$lambda-11, reason: not valid java name */
    public static final boolean m1032connectActionBarView$lambda11(FindLandingFragment this$0, View view, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.goSearch(SearchExperienceMode.SEARCH_HISTORY);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: connectActionBarView$lambda-12, reason: not valid java name */
    public static final void m1033connectActionBarView$lambda12(FindLandingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.goSearch(SearchExperienceMode.SEARCH_HISTORY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: connectActionBarView$lambda-13, reason: not valid java name */
    public static final void m1034connectActionBarView$lambda13(FindLandingFragment this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.goSearch(SearchExperienceMode.SEARCH_HISTORY);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: connectActionBarView$lambda-14, reason: not valid java name */
    public static final void m1035connectActionBarView$lambda14(FindLandingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.refreshFindPage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: connectActionBarView$lambda-15, reason: not valid java name */
    public static final void m1036connectActionBarView$lambda15(FindLandingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AlexaUtil.INSTANCE.triggerAlexa(this$0.getPageType());
    }

    private final void configureActionBar() {
        Bundle arguments;
        GalleryTemplate value = getTemplate().getValue();
        String skillPath = getSkillPath(getArguments(), value);
        if (value != null) {
            NavigationBarElement navigationBarElement = value.navigationBarElement();
            if (!((navigationBarElement == null ? null : navigationBarElement.searchBarElement()) != null) && (arguments = getArguments()) != null) {
                NavigationBarElement navigationBarElement2 = value.navigationBarElement();
                arguments.putString("TOOLBAR_TITLE_KEY", navigationBarElement2 != null ? navigationBarElement2.primaryHeader() : null);
            }
        }
        if (Intrinsics.areEqual(skillPath, Constants.SHOW_FIND_SKILL)) {
            configureFindLandingActionBar();
        } else if (Intrinsics.areEqual(skillPath, Constants.SHOW_FIND_CONTENT_SKILL)) {
            configureFindSeeMoreActionBar();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final String getSkillPath(Bundle arguments, GalleryTemplate template) {
        String string;
        if (template != null) {
            NavigationBarElement navigationBarElement = template.navigationBarElement();
            obj = !((navigationBarElement != null ? navigationBarElement.searchBarElement() : null) != null) ? Constants.SHOW_FIND_CONTENT_SKILL : Constants.SHOW_FIND_SKILL;
        }
        return obj == null ? (arguments == null || (string = arguments.getString(Constants.SKILL_PATH_ID)) == null) ? Constants.SHOW_FIND_SKILL : string : obj;
    }

    private final void disconnectActionBarView() {
        FindSearchBarView findSearchBarView = this.findSearchBarView;
        if (findSearchBarView != null) {
            findSearchBarView.clearFocus();
        }
        FindSearchBarView findSearchBarView2 = this.findSearchBarView;
        if (findSearchBarView2 == null) {
            return;
        }
        findSearchBarView2.clearAllListeners();
    }

    private final boolean isDynMsgRibbonVisible() {
        FragmentActivity activity = getActivity();
        MusicHomeActivity musicHomeActivity = activity instanceof MusicHomeActivity ? (MusicHomeActivity) activity : null;
        return musicHomeActivity != null && musicHomeActivity.isDynMsgRibbonVisible();
    }

    private final boolean isUpsellButtonLayoutVisible() {
        BauhausActionBarView bauhausActionBarView = this.actionBarView;
        return bauhausActionBarView != null && bauhausActionBarView.isFindUpsellButtonLayoutVisible();
    }

    private final void styleActionBar() {
        FindSearchBarView findSearchBarView;
        FindLandingATCSkipTooltipExperience findLandingATCSkipTooltipExperience;
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet != null && (findSearchBarView = this.findSearchBarView) != null) {
            findSearchBarView.styleFindSearchBarView(styleSheet);
            if (!isUpsellButtonLayoutVisible() && !isDynMsgRibbonVisible() && AlexaUtil.INSTANCE.shouldShowAlexaButton()) {
                AlexaTooltipExperience alexaTooltipExperience = this.mAlexaTooltipExperience;
                if (alexaTooltipExperience != null) {
                    alexaTooltipExperience.showTooltip(findSearchBarView, styleSheet);
                }
            } else {
                AlexaTooltipExperience alexaTooltipExperience2 = this.mAlexaTooltipExperience;
                if (alexaTooltipExperience2 != null) {
                    alexaTooltipExperience2.hideToolTip();
                }
            }
            if (FTUSettingsUtil.customerSkippedATC(getContext()) && (findLandingATCSkipTooltipExperience = this.mFindLandingATCSkipTooltipExperience) != null) {
                findLandingATCSkipTooltipExperience.showTooltip(findSearchBarView, styleSheet);
            }
        }
        styleSearchBarViewMargins();
        updateQueryHint();
        updateFindUpsell();
    }

    private final void styleSearchBarViewMargins() {
        BauhausActionBarView bauhausActionBarView;
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet == null) {
            return;
        }
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        Bundle arguments = getArguments();
        Object obj = arguments == null ? null : arguments.get(Constants.SKILL_PATH_ID);
        if (Intrinsics.areEqual(obj, Constants.SHOW_FIND_SKILL)) {
            BauhausActionBarView bauhausActionBarView2 = this.actionBarView;
            if (bauhausActionBarView2 != null) {
                bauhausActionBarView2.setSearchBarMargins(spacerInPixels, spacerInPixels, spacerInPixels, spacerInPixels);
            }
        } else if (Intrinsics.areEqual(obj, Constants.SHOW_FIND_CONTENT_SKILL) && (bauhausActionBarView = this.actionBarView) != null) {
            bauhausActionBarView.setSearchBarMargins(0, 0, 0, 0);
        }
        BauhausActionBarView bauhausActionBarView3 = this.actionBarView;
        if (bauhausActionBarView3 == null) {
            return;
        }
        bauhausActionBarView3.addTopMargin();
    }

    private final void configureFindLandingActionBar() {
        BauhausActionBarView bauhausActionBarView = this.actionBarView;
        if (bauhausActionBarView != null) {
            bauhausActionBarView.setActionBarVisible(true);
        }
        FindSearchBarView findSearchBarView = this.findSearchBarView;
        if (findSearchBarView != null) {
            findSearchBarView.showBackButton(false);
        }
        FindSearchBarView findSearchBarView2 = this.findSearchBarView;
        if (findSearchBarView2 != null) {
            findSearchBarView2.showSearchBox();
        }
        FindSearchBarView findSearchBarView3 = this.findSearchBarView;
        if (findSearchBarView3 != null) {
            findSearchBarView3.showAlexaButton(AlexaUtil.INSTANCE.shouldShowAlexaButton());
        }
        updateFindUpsell();
    }

    private final void configureFindSeeMoreActionBar() {
        String string;
        Bundle arguments = getArguments();
        String str = "";
        if (arguments != null && (string = arguments.getString("TOOLBAR_TITLE_KEY")) != null) {
            str = string;
        }
        BauhausActionBarView bauhausActionBarView = this.actionBarView;
        if (bauhausActionBarView != null) {
            bauhausActionBarView.setActionBarVisible(false);
        }
        FindSearchBarView findSearchBarView = this.findSearchBarView;
        if (findSearchBarView != null) {
            findSearchBarView.showTitle(str);
        }
        FindSearchBarView findSearchBarView2 = this.findSearchBarView;
        if (findSearchBarView2 != null) {
            findSearchBarView2.showBackButton(true);
        }
        FindSearchBarView findSearchBarView3 = this.findSearchBarView;
        if (findSearchBarView3 != null) {
            findSearchBarView3.showAlexaButton(false);
        }
        updateFindUpsell();
    }

    private final void goSearch(SearchExperienceMode mode) {
        FragmentActivity activity = getActivity();
        SearchTabFragmentHost.selectSearchTab(activity instanceof MusicHomeActivity ? (MusicHomeActivity) activity : null, mode, null, false, true);
    }

    private final void handleDeepLink(String link) throws Exception {
        DeeplinksManager.get(AmazonApplication.getContext()).handle(Uri.parse(link));
    }

    private final void handleUri(String uri) {
        BrushFragment brushFragmentCreateFragment = uri != null ? new BrushFragmentNavigation.Builder(uri).withToken(null).withBundle(getArguments()).createFragment() : null;
        FragmentActivity activity = getActivity();
        if (activity instanceof NowPlayingFragmentActivity) {
            FragmentActivity activity2 = getActivity();
            if (activity2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.playback.activity.NowPlayingFragmentActivity");
            }
            ((NowPlayingFragmentActivity) activity2).changeScreenFragment(brushFragmentCreateFragment, true, true, true);
            return;
        }
        if (!(activity instanceof MusicHomeActivity)) {
            getLogger().error("SPL::handleUri unknown activity");
            return;
        }
        FragmentActivity activity3 = getActivity();
        if (activity3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.MusicHomeActivity");
        }
        ((MusicHomeActivity) activity3).changeScreenFragment(brushFragmentCreateFragment, true, true, true);
    }

    /* JADX INFO: compiled from: FindLandingFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/find/view/FindLandingFragment$ContentViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class ContentViewHolder extends RecyclerView.ViewHolder {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ContentViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
        }
    }

    /* JADX INFO: compiled from: FindLandingFragment.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0016¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/find/view/FindLandingFragment$ContentAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/mp3/find/view/FindLandingFragment$ContentViewHolder;", "(Lcom/amazon/mp3/find/view/FindLandingFragment;)V", "getElement", "LCoreInterface/v1_0/Element;", "position", "", "getItemCount", "getItemViewType", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class ContentAdapter extends RecyclerView.Adapter<ContentViewHolder> {
        final /* synthetic */ FindLandingFragment this$0;

        public ContentAdapter(FindLandingFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        private final Element getElement(int position) {
            List<WidgetElement> listWidgetElements;
            GalleryTemplate galleryTemplate = (GalleryTemplate) this.this$0.getTemplate().getValue();
            if (galleryTemplate == null || (listWidgetElements = galleryTemplate.widgetElements()) == null) {
                return null;
            }
            return (WidgetElement) CollectionsKt___CollectionsKt.getOrNull(listWidgetElements, position);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            View viewCreateView = this.this$0.getElementBindingProvider().createView(viewType, parent);
            viewCreateView.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ContentViewHolder(viewCreateView);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<WidgetElement> listWidgetElements;
            GalleryTemplate galleryTemplate = (GalleryTemplate) this.this$0.getTemplate().getValue();
            if (galleryTemplate == null || (listWidgetElements = galleryTemplate.widgetElements()) == null) {
                return 0;
            }
            return listWidgetElements.size();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int position) {
            Element element = getElement(position);
            if (element == null) {
                return -1;
            }
            return this.this$0.getElementBindingProvider().getViewType(element.getClass());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ContentViewHolder holder, int position) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Element element = getElement(position);
            if (element == null) {
                return;
            }
            ElementBindingProvider elementBindingProvider = this.this$0.getElementBindingProvider();
            View view = holder.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "holder.itemView");
            elementBindingProvider.bind(element, view);
            if (position == 0) {
                LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.LOAD_COMPLETE, PageLoadLatencyCode.FIND_V2));
            }
        }
    }

    private final boolean isCustomerEligibleForFindUpsell() {
        if (!ConnectivityUtil.hasAnyInternetConnection(getContext()) || getSkillPath(getArguments(), getTemplate().getValue()).equals(Constants.SHOW_FIND_CONTENT_SKILL)) {
            return false;
        }
        if (this.mAlexaTooltipExperience == null) {
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            this.mAlexaTooltipExperience = new AlexaTooltipExperience(contextRequireContext);
        }
        AlexaTooltipExperience alexaTooltipExperience = this.mAlexaTooltipExperience;
        if ((alexaTooltipExperience == null || alexaTooltipExperience.canPresentTooltip()) ? false : true) {
            return FindFeatureGating.UPSELL_ON_FIND_PAGE.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
        }
        return false;
    }
}
