package com.amazon.mp3.catalog.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.voice.hints.guicontext.GUIMetadataNotReadyException;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.explore.containers.FeaturedContainer;
import com.amazon.music.explore.events.SingleLiveEvent;
import com.amazon.music.explore.managers.ExploreLiveStreamManager;
import com.amazon.music.explore.managers.ExploreManagerFactory;
import com.amazon.music.explore.managers.ExploreRootsAndBranchesManager;
import com.amazon.music.explore.managers.ExploreSpotlightManager;
import com.amazon.music.explore.widgets.models.ExploreModel;
import com.amazon.music.explore.widgets.models.FeaturedShovelerModel;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.skyfire.ui.container.TemplateContainer;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.models.ArtistDetailHeaderModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.ShovelerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import dagger.android.support.AndroidSupportInjection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsKt;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: compiled from: ArtistDetailBrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 A2\u00020\u0001:\u0001AB\u0007¢\u0006\u0004\b?\u0010@J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\b\u0010\b\u001a\u00020\u0004H\u0002J\b\u0010\t\u001a\u00020\u0004H\u0002J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002J,\u0010\u0010\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u00022\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u0012\u0010\u0016\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0017\u001a\u00020\u0004H\u0016J\u001a\u0010\u0018\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u0002H\u0014J\b\u0010\u0019\u001a\u00020\u0004H\u0014J\b\u0010\u001a\u001a\u00020\u0002H\u0004J\n\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001e\u001a\u00020\u001dH\u0014J\u0010\u0010 \u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u001bH\u0014J\b\u0010!\u001a\u00020\u0004H\u0016J\b\u0010\"\u001a\u00020\u0004H\u0016J\u0006\u0010#\u001a\u00020\u001bR\"\u0010%\u001a\u00020$8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\"\u0010,\u001a\u00020+8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0018\u00103\u001a\u0004\u0018\u0001028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0018\u00106\u001a\u0004\u0018\u0001058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0018\u00109\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u0018\u0010<\u001a\u0004\u0018\u00010;8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b<\u0010=R\u0018\u0010>\u001a\u0004\u0018\u00010;8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010=¨\u0006B"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/ArtistDetailBrushFragment;", "Lcom/amazon/mp3/catalog/fragment/DetailBrushFragment;", "", "isEligibleForArtistUpsell", "", "postUserInitialization", "createRootsAndBranchesManager", "createSpotlightManager", "initializeFeaturedContainer", "createLiveStreamManager", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageGridViewModel", "mergeLiveStream", "isInitialPage", "Lkotlin/Function0;", "onModelsNotReady", "onBlockingExploreModelsReady", "Landroid/content/Context;", "context", "onAttach", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "onDestroy", "updatePageModel", "setupBrushViews", "isArtistWithUnlimitedContent", "", "getBackgroundImageUrl", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "identifier", "sendUiPageViewMetric", "onStart", "onStop", "getArtistName", "Lcom/amazon/music/explore/managers/ExploreManagerFactory;", "exploreManagerFactory", "Lcom/amazon/music/explore/managers/ExploreManagerFactory;", "getExploreManagerFactory", "()Lcom/amazon/music/explore/managers/ExploreManagerFactory;", "setExploreManagerFactory", "(Lcom/amazon/music/explore/managers/ExploreManagerFactory;)V", "Lcom/amazon/music/explore/containers/FeaturedContainer;", "featuredContainer", "Lcom/amazon/music/explore/containers/FeaturedContainer;", "getFeaturedContainer", "()Lcom/amazon/music/explore/containers/FeaturedContainer;", "setFeaturedContainer", "(Lcom/amazon/music/explore/containers/FeaturedContainer;)V", "Lcom/amazon/music/explore/managers/ExploreRootsAndBranchesManager;", "rootsBranchesManager", "Lcom/amazon/music/explore/managers/ExploreRootsAndBranchesManager;", "Lcom/amazon/music/explore/managers/ExploreSpotlightManager;", "spotlightManager", "Lcom/amazon/music/explore/managers/ExploreSpotlightManager;", "Lcom/amazon/music/explore/managers/ExploreLiveStreamManager;", "liveStreamManager", "Lcom/amazon/music/explore/managers/ExploreLiveStreamManager;", "Lrx/Subscription;", "userObserver", "Lrx/Subscription;", "featureFlagObserver", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ArtistDetailBrushFragment extends DetailBrushFragment {
    private static final String TAG = ArtistDetailBrushFragment.class.getSimpleName();
    public ExploreManagerFactory exploreManagerFactory;
    private Subscription featureFlagObserver;
    public FeaturedContainer featuredContainer;
    private ExploreLiveStreamManager liveStreamManager;
    private ExploreRootsAndBranchesManager rootsBranchesManager;
    private ExploreSpotlightManager spotlightManager;
    private Subscription userObserver;

    public final ExploreManagerFactory getExploreManagerFactory() {
        ExploreManagerFactory exploreManagerFactory = this.exploreManagerFactory;
        if (exploreManagerFactory != null) {
            return exploreManagerFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("exploreManagerFactory");
        return null;
    }

    public final FeaturedContainer getFeaturedContainer() {
        FeaturedContainer featuredContainer = this.featuredContainer;
        if (featuredContainer != null) {
            return featuredContainer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("featuredContainer");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        if (isEligibleForArtistUpsell()) {
            setIsArtistUpsell(true);
        }
        super.onCreate(savedInstanceState);
        postUserInitialization();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        Subscription subscription = this.userObserver;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Subscription subscription2 = this.featureFlagObserver;
        if (subscription2 != null) {
            subscription2.unsubscribe();
        }
        super.onDestroy();
    }

    @Override // com.amazon.mp3.catalog.fragment.DetailBrushFragment, com.amazon.mp3.catalog.fragment.BrushFragment
    protected void updatePageModel(final PageGridViewModel pageGridViewModel, boolean isInitialPage) {
        Bundle arguments = getArguments();
        boolean z = false;
        if (arguments != null && arguments.getBoolean("see_more")) {
            z = true;
        }
        if (z || pageGridViewModel == null) {
            super.updatePageModel(pageGridViewModel, isInitialPage);
            return;
        }
        mergeLiveStream(pageGridViewModel);
        getFeaturedContainer().merge(pageGridViewModel);
        ExploreSpotlightManager exploreSpotlightManager = this.spotlightManager;
        if (exploreSpotlightManager != null) {
            exploreSpotlightManager.mergeModels(pageGridViewModel);
        }
        ExploreRootsAndBranchesManager exploreRootsAndBranchesManager = this.rootsBranchesManager;
        if (exploreRootsAndBranchesManager != null) {
            exploreRootsAndBranchesManager.mergeModels(pageGridViewModel);
        }
        onBlockingExploreModelsReady(pageGridViewModel, isInitialPage, new Function0<Unit>() { // from class: com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment.updatePageModel.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ArtistDetailBrushFragment.this.mPageModel = pageGridViewModel;
            }
        });
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void setupBrushViews() {
        super.setupBrushViews();
        this.brushViews.setComparator(new Comparator() { // from class: com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ArtistDetailBrushFragment.m466setupBrushViews$lambda0(this.f$0, obj, obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupBrushViews$lambda-0, reason: not valid java name */
    public static final int m466setupBrushViews$lambda0(ArtistDetailBrushFragment this$0, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return CollectionsKt___CollectionsKt.indexOf((List<? extends Object>) this$0.mPageModel.getModels(), obj) - CollectionsKt___CollectionsKt.indexOf((List<? extends Object>) this$0.mPageModel.getModels(), obj2);
    }

    protected final boolean isArtistWithUnlimitedContent() {
        String title;
        for (BaseViewModel baseViewModel : this.mPageModel.getModels()) {
            if (baseViewModel instanceof SectionGridViewModel) {
                title = ((SectionGridViewModel) baseViewModel).getTitle();
            } else {
                title = baseViewModel instanceof ShovelerModel ? ((ShovelerModel) baseViewModel).getTitle() : null;
            }
            if (title != null && StringsKt__StringsKt.contains$default((CharSequence) title, (CharSequence) "Unlimited", false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.mp3.catalog.fragment.DetailBrushFragment
    protected String getBackgroundImageUrl() {
        Object next;
        List<BaseViewModel> models = this.mPageModel.getModels();
        BrushViews brushViews = this.brushViews;
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        int tileType = this.mPageModel.getTileType();
        RecyclerView mRecyclerView = this.mRecyclerView;
        Intrinsics.checkNotNullExpressionValue(mRecyclerView, "mRecyclerView");
        brushViews.createRootView(userSubscription, models, tileType, mRecyclerView);
        Iterator<T> it = models.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((BaseViewModel) next) instanceof ArtistDetailHeaderModel) {
                break;
            }
        }
        ArtistDetailHeaderModel artistDetailHeaderModel = (ArtistDetailHeaderModel) next;
        if (artistDetailHeaderModel == null) {
            return null;
        }
        return artistDetailHeaderModel.getHeroImageUrl();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected PageType getPageType() {
        return PageType.BROWSE_ARTIST_DETAIL;
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void sendUiPageViewMetric(String identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString("brush_asin_extra");
        String str = string != null ? "ASIN" : null;
        Boolean isArtistUpsell = this.isArtistUpsell;
        Intrinsics.checkNotNullExpressionValue(isArtistUpsell, "isArtistUpsell");
        if (isArtistUpsell.booleanValue()) {
            NavigationAppEvent.builder(!isArtistWithUnlimitedContent() ? "detail-browseArtistWithNoUnlimitedContent" : "detail-browseArtistWithUnlimitedContent").useIdentifierAsPageType().withItemIdentifier(string).withItemIdentifierType(str).withRefMarker(this.refMarker).withAssociateTag(this.associateTag).withOrientation(ScreenUtil.getScreenOrientation(getContext())).publish();
        } else {
            NavigationAppEvent.builder(identifier).useIdentifierAsPageType().withItemIdentifier(string).withItemIdentifierType(str).withRefMarker(this.refMarker).withAssociateTag(this.associateTag).withOrientation(ScreenUtil.getScreenOrientation(getContext())).publish();
        }
    }

    private final boolean isEligibleForArtistUpsell() {
        try {
            AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(FeatureGateProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            if (!(interfaceProvider instanceof FeatureGateProvider)) {
                interfaceProvider = null;
            }
            FeatureGateProvider featureGateProvider = (FeatureGateProvider) interfaceProvider;
            if (featureGateProvider == null) {
                return false;
            }
            return featureGateProvider.isFeatureAvailable(Feature.artist_upsell);
        } catch (Exception e) {
            Log.warning(TAG, "Failed to get account information with error: ", e);
            return false;
        }
    }

    private final void postUserInitialization() {
        if (AmazonApplication.getCapabilities().isLiveEventEnabled()) {
            getFeaturedContainer().attach(this);
        }
        this.userObserver = BootstrapSingletonTask.get().registerUserObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ArtistDetailBrushFragment.m464postUserInitialization$lambda2(this.f$0, (Void) obj);
            }
        });
        this.featureFlagObserver = BootstrapSingletonTask.get().registerFeatureFlagObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ArtistDetailBrushFragment.m465postUserInitialization$lambda3(this.f$0, (Void) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: postUserInitialization$lambda-2, reason: not valid java name */
    public static final void m464postUserInitialization$lambda2(ArtistDetailBrushFragment this$0, Void r1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AmazonApplication.getCapabilities().isLiveEventEnabled()) {
            this$0.initializeFeaturedContainer();
        } else {
            this$0.createSpotlightManager();
        }
        this$0.createRootsAndBranchesManager();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: postUserInitialization$lambda-3, reason: not valid java name */
    public static final void m465postUserInitialization$lambda3(ArtistDetailBrushFragment this$0, Void r1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.createLiveStreamManager();
    }

    private final void createRootsAndBranchesManager() {
        SingleLiveEvent<List<ExploreModel>> models;
        ExploreManagerFactory exploreManagerFactory = getExploreManagerFactory();
        Bundle arguments = getArguments();
        ExploreRootsAndBranchesManager exploreRootsAndBranchesManagerRequestRootsAndBranches = exploreManagerFactory.requestRootsAndBranches(this, this, arguments == null ? null : arguments.getString("brush_asin_extra"));
        this.rootsBranchesManager = exploreRootsAndBranchesManagerRequestRootsAndBranches;
        if (exploreRootsAndBranchesManagerRequestRootsAndBranches == null || (models = exploreRootsAndBranchesManagerRequestRootsAndBranches.getModels()) == null) {
            return;
        }
        models.observe(this, new Observer() { // from class: com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ArtistDetailBrushFragment.m459createRootsAndBranchesManager$lambda4(this.f$0, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createRootsAndBranchesManager$lambda-4, reason: not valid java name */
    public static final void m459createRootsAndBranchesManager$lambda4(ArtistDetailBrushFragment this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ExploreRootsAndBranchesManager exploreRootsAndBranchesManager = this$0.rootsBranchesManager;
        if (exploreRootsAndBranchesManager != null) {
            exploreRootsAndBranchesManager.mergeModels(this$0.mPageModel);
        }
        onBlockingExploreModelsReady$default(this$0, this$0.mPageModel, false, null, 4, null);
    }

    private final void createSpotlightManager() {
        SingleLiveEvent<Boolean> singleLiveEventOnModelsReady;
        SingleLiveEvent<List<FeaturedShovelerModel>> models;
        ExploreSpotlightManager exploreSpotlightManagerCreateSpotlightManager = getExploreManagerFactory().createSpotlightManager(this);
        this.spotlightManager = exploreSpotlightManagerCreateSpotlightManager;
        if (exploreSpotlightManagerCreateSpotlightManager != null) {
            Bundle arguments = getArguments();
            exploreSpotlightManagerCreateSpotlightManager.requestSpotlight(arguments == null ? null : arguments.getString("brush_asin_extra"));
        }
        ExploreSpotlightManager exploreSpotlightManager = this.spotlightManager;
        if (exploreSpotlightManager != null && (models = exploreSpotlightManager.getModels()) != null) {
            models.observe(this, new Observer() { // from class: com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment$$ExternalSyntheticLambda6
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ArtistDetailBrushFragment.m460createSpotlightManager$lambda5(this.f$0, (List) obj);
                }
            });
        }
        ExploreSpotlightManager exploreSpotlightManager2 = this.spotlightManager;
        if (exploreSpotlightManager2 == null || (singleLiveEventOnModelsReady = exploreSpotlightManager2.onModelsReady()) == null) {
            return;
        }
        singleLiveEventOnModelsReady.observe(this, new Observer() { // from class: com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ArtistDetailBrushFragment.m461createSpotlightManager$lambda7(this.f$0, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createSpotlightManager$lambda-5, reason: not valid java name */
    public static final void m460createSpotlightManager$lambda5(ArtistDetailBrushFragment this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ExploreSpotlightManager exploreSpotlightManager = this$0.spotlightManager;
        if (exploreSpotlightManager == null) {
            return;
        }
        exploreSpotlightManager.mergeModels(this$0.mPageModel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createSpotlightManager$lambda-7, reason: not valid java name */
    public static final void m461createSpotlightManager$lambda7(ArtistDetailBrushFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PageGridViewModel pageGridViewModel = this$0.mPageModel;
        if (pageGridViewModel == null) {
            return;
        }
        super.updatePageModel(pageGridViewModel, true);
    }

    private final void initializeFeaturedContainer() {
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString("brush_asin_extra");
        if (string == null) {
            return;
        }
        getFeaturedContainer().getStatus().observe(this, new Observer() { // from class: com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ArtistDetailBrushFragment.m462initializeFeaturedContainer$lambda8(this.f$0, (Integer) obj);
            }
        });
        TemplateContainer.requestTemplate$default(getFeaturedContainer(), Intrinsics.stringPlus("/showArtistFeaturedWidget/", string), null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeFeaturedContainer$lambda-8, reason: not valid java name */
    public static final void m462initializeFeaturedContainer$lambda8(ArtistDetailBrushFragment this$0, Integer num) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PageGridViewModel pageGridViewModel = this$0.mPageModel;
        if (pageGridViewModel == null) {
            return;
        }
        this$0.getFeaturedContainer().merge(pageGridViewModel);
    }

    private final void createLiveStreamManager() {
        if (AmazonApplication.getCapabilities().isWoodstockEnabled()) {
            ExploreLiveStreamManager exploreLiveStreamManagerCreateLiveStreamManager = getExploreManagerFactory().createLiveStreamManager(this);
            this.liveStreamManager = exploreLiveStreamManagerCreateLiveStreamManager;
            if (exploreLiveStreamManagerCreateLiveStreamManager == null) {
                return;
            }
            Bundle arguments = getArguments();
            exploreLiveStreamManagerCreateLiveStreamManager.requestArtistLiveStream(arguments == null ? null : arguments.getString("brush_asin_extra"));
        }
    }

    private final void mergeLiveStream(final PageGridViewModel pageGridViewModel) {
        SingleLiveEvent<SectionGridViewModel> models;
        ExploreLiveStreamManager exploreLiveStreamManager;
        ExploreLiveStreamManager exploreLiveStreamManager2 = this.liveStreamManager;
        boolean z = false;
        if (exploreLiveStreamManager2 != null && !exploreLiveStreamManager2.isInitialRequest()) {
            z = true;
        }
        if (z && (exploreLiveStreamManager = this.liveStreamManager) != null) {
            Bundle arguments = getArguments();
            exploreLiveStreamManager.requestArtistLiveStream(arguments == null ? null : arguments.getString("brush_asin_extra"));
        }
        ExploreLiveStreamManager exploreLiveStreamManager3 = this.liveStreamManager;
        if (exploreLiveStreamManager3 == null || (models = exploreLiveStreamManager3.getModels()) == null) {
            return;
        }
        models.observe(this, new Observer() { // from class: com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ArtistDetailBrushFragment.m463mergeLiveStream$lambda9(this.f$0, pageGridViewModel, (SectionGridViewModel) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mergeLiveStream$lambda-9, reason: not valid java name */
    public static final void m463mergeLiveStream$lambda9(ArtistDetailBrushFragment this$0, PageGridViewModel pageGridViewModel, SectionGridViewModel sectionGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ExploreLiveStreamManager exploreLiveStreamManager = this$0.liveStreamManager;
        if (exploreLiveStreamManager != null) {
            exploreLiveStreamManager.mergeModels(pageGridViewModel);
        }
        onBlockingExploreModelsReady$default(this$0, pageGridViewModel, false, null, 4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void onBlockingExploreModelsReady$default(ArtistDetailBrushFragment artistDetailBrushFragment, PageGridViewModel pageGridViewModel, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            function0 = null;
        }
        artistDetailBrushFragment.onBlockingExploreModelsReady(pageGridViewModel, z, function0);
    }

    private final void onBlockingExploreModelsReady(PageGridViewModel pageGridViewModel, boolean isInitialPage, Function0<Unit> onModelsNotReady) {
        ExploreSpotlightManager exploreSpotlightManager = this.spotlightManager;
        boolean z = false;
        if (exploreSpotlightManager != null && exploreSpotlightManager.areModelsReady()) {
            z = true;
        }
        if (!z) {
            Integer value = getFeaturedContainer().getStatus().getValue();
            int status_complete = TemplateContainer.INSTANCE.getSTATUS_COMPLETE();
            if (value == null || value.intValue() != status_complete) {
                if (onModelsNotReady != null) {
                    onModelsNotReady.invoke();
                    return;
                }
                return;
            }
        }
        if (pageGridViewModel == null) {
            return;
        }
        super.updatePageModel(pageGridViewModel, isInitialPage);
    }

    @Override // com.amazon.mp3.catalog.fragment.DetailBrushFragment, com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        DynamicBackgroundImagePublisher.INSTANCE.getInstance().pauseDynamicBackgroundImagePublisher();
    }

    @Override // com.amazon.mp3.catalog.fragment.DetailBrushFragment, com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        DynamicBackgroundImagePublisher.INSTANCE.getInstance().resumeDynamicBackgroundImagePublisher();
    }

    public final String getArtistName() throws GUIMetadataNotReadyException {
        List<BaseViewModel> models;
        PageGridViewModel pageGridViewModel = this.mPageModel;
        BaseViewModel baseViewModel = (pageGridViewModel == null || (models = pageGridViewModel.getModels()) == null) ? null : models.get(0);
        ArtistDetailHeaderModel artistDetailHeaderModel = baseViewModel instanceof ArtistDetailHeaderModel ? (ArtistDetailHeaderModel) baseViewModel : null;
        String name = artistDetailHeaderModel != null ? artistDetailHeaderModel.getName() : null;
        if (name != null) {
            return name;
        }
        throw new GUIMetadataNotReadyException("Artist name not ready in ArtistDetail brush fragment");
    }
}
