package com.amazon.mp3.catalog.fragment;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.actionbar.view.BauhausFilterActionBarView;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.catalog.fragment.adapter.FilteredPageCallback;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager;
import com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.providers.DefaultContextMenuProvider;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.refinements.BaseViewsRefinementOverflowManager;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.binders.RefinementPillBinder;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.BaseDetailHeaderModel;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.EndlessScrollDataProvider;
import com.amazon.music.views.library.providers.RefinementCallback;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: NewReleasesDetailBrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 K2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0001KB\u0007¢\u0006\u0004\bI\u0010JJ\b\u0010\u0007\u001a\u00020\u0006H\u0014J\u0012\u0010\n\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\fH\u0014J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0014J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u001a\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u0006H\u0016J\u0010\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u001cH\u0016J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00130\u001dH\u0016J\u0012\u0010!\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020\"H\u0016J\n\u0010&\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010'\u001a\u00020\u0006H\u0014J\b\u0010(\u001a\u00020\u0006H\u0014J\u0011\u0010)\u001a\u0004\u0018\u00010\"H\u0016¢\u0006\u0004\b)\u0010*J\n\u0010,\u001a\u0004\u0018\u00010+H\u0016J\n\u0010.\u001a\u0004\u0018\u00010-H\u0016J\b\u0010/\u001a\u00020$H\u0016J\b\u00100\u001a\u00020$H\u0016J\b\u00101\u001a\u00020\u0006H\u0016J\u0010\u00103\u001a\u00020\u00062\u0006\u00102\u001a\u00020$H\u0016J\b\u00104\u001a\u00020\u0006H\u0002J\b\u00105\u001a\u00020\u0006H\u0002J\u0011\u00106\u001a\u0004\u0018\u00010\"H\u0002¢\u0006\u0004\b6\u0010*J\n\u00107\u001a\u0004\u0018\u00010-H\u0002J\u0014\u00109\u001a\u0004\u0018\u00010\f2\b\u00108\u001a\u0004\u0018\u00010\fH\u0002R\u0018\u0010:\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u0010;R\u0018\u0010=\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u0018\u0010@\u001a\u0004\u0018\u00010?8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010BR-\u0010H\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001d0C8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G¨\u0006L"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/NewReleasesDetailBrushFragment;", "Lcom/amazon/mp3/catalog/fragment/DetailBrushFragment;", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "Lcom/amazon/mp3/fragment/headerbar/OverflowMenuReceiver;", "Lcom/amazon/mp3/catalog/fragment/adapter/FilteredPageCallback;", "", "initActionBarView", "Landroid/os/Bundle;", "savedInstanceState", "onActivityCreated", "onDestroy", "", "getBackgroundImageUrl", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "Lcom/amazon/music/views/library/models/FilterModel;", "filterModel", "onChildFilterClick", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "model", "Landroid/view/View;", "view", "onClick", "onFiltersReset", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "", "getRefinementFilterModels", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "provider", "setActionBarProvider", "", "actionBarHeight", "", "shouldDisplayPageComponentsInTopAppBar", "getArtworkUrl", "setupBrushViews", "createBrushRootView", "getFilterButtonClickDestination", "()Ljava/lang/Integer;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getContentMetadata", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "getRefinementListModel", "shouldDisplayFilterAndContextButtons", "shouldDisplayTitle", "onFilterRequestApplied", "successfulRequest", "onFilterResponse", "initializeRefinementOverflowManager", "initializeActionBarManager", "findPositionOfRefinementList", "retrieveRefinementListModel", "uri", "removeUriPrefix", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "actionBarManager", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;", "refinementOverflowManager", "Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;", "I", "Ljava/util/HashMap;", "refinementLists$delegate", "Lkotlin/Lazy;", "getRefinementLists", "()Ljava/util/HashMap;", "refinementLists", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class NewReleasesDetailBrushFragment extends DetailBrushFragment implements BaseViewsFilterActionBarManager.InfoProvider, RefinementCallback, OverflowMenuReceiver, FilteredPageCallback {
    private static final String TAG = NewReleasesDetailBrushFragment.class.getSimpleName();
    private int actionBarHeight;
    private BaseViewsFilterActionBarManager actionBarManager;
    private ActionBarProvider actionBarProvider;

    /* JADX INFO: renamed from: refinementLists$delegate, reason: from kotlin metadata */
    private final Lazy refinementLists = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, List<? extends String>>>() { // from class: com.amazon.mp3.catalog.fragment.NewReleasesDetailBrushFragment$refinementLists$2
        @Override // kotlin.jvm.functions.Function0
        public final HashMap<String, List<? extends String>> invoke() {
            return new HashMap<>();
        }
    });
    private BaseViewsRefinementOverflowManager refinementOverflowManager;

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public String getArtworkUrl() {
        return null;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public ContentMetadata getContentMetadata() {
        return null;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterAndContextButtons() {
        return false;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    /* JADX INFO: renamed from: shouldDisplayTitle */
    public boolean getDisplayTitle() {
        return false;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean doesHaveRefinement() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.doesHaveRefinement(this);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    /* JADX INFO: renamed from: getTitle */
    public String getTitleText() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.getTitle(this);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayContextMenuOverflowButton() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldDisplayContextMenuOverflowButton(this);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterButton(RecyclerView recyclerView, int i) {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldDisplayFilterButton(this, recyclerView, i);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldHideRefinementsOnLoad() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldHideRefinementsOnLoad(this);
    }

    @Override // com.amazon.mp3.catalog.fragment.DetailBrushFragment, com.amazon.mp3.catalog.fragment.BrushFragment
    protected void initActionBarView() {
        if (this.mActionBarView == null) {
            FragmentActivity activity = getActivity();
            this.mActionBarView = activity == null ? null : new ActionBarView(activity, null, null, false, false, null, 62, null);
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeActionBarManager();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager != null) {
            getLifecycle().addObserver(baseViewsFilterActionBarManager);
        }
        initializeRefinementOverflowManager();
        BauhausActionBarView.Companion companion = BauhausActionBarView.INSTANCE;
        Resources resources = getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "resources");
        this.actionBarHeight = companion.getActionBarHeight(resources);
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager == null) {
            return;
        }
        getLifecycle().removeObserver(baseViewsFilterActionBarManager);
    }

    @Override // com.amazon.mp3.catalog.fragment.DetailBrushFragment
    protected String getBackgroundImageUrl() {
        Object next;
        Iterator<T> it = this.mPageModel.getModels().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((BaseViewModel) next) instanceof BaseDetailHeaderModel) {
                break;
            }
        }
        BaseDetailHeaderModel baseDetailHeaderModel = (BaseDetailHeaderModel) next;
        if (baseDetailHeaderModel == null) {
            return null;
        }
        return baseDetailHeaderModel.getImageUrl();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        BauhausActionBarView.Companion companion = BauhausActionBarView.INSTANCE;
        Resources resources = getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "resources");
        this.actionBarHeight = companion.getActionBarHeight(resources);
    }

    private final void initializeRefinementOverflowManager() {
        if (getContext() == null) {
            return;
        }
        StyleSheetProvider.getStyleSheet().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.catalog.fragment.NewReleasesDetailBrushFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NewReleasesDetailBrushFragment.m518initializeRefinementOverflowManager$lambda5$lambda4(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeRefinementOverflowManager$lambda-5$lambda-4, reason: not valid java name */
    public static final void m518initializeRefinementOverflowManager$lambda5$lambda4(NewReleasesDetailBrushFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        this$0.refinementOverflowManager = new BaseViewsRefinementOverflowManager(this$0, styleSheet, this$0);
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected PageType getPageType() {
        return PageType.BROWSE_NEW_TO_PRIME;
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onChildFilterClick(FilterModel filterModel) {
        String browseId;
        List<FilterItemModel> filters;
        Intrinsics.checkNotNullParameter(filterModel, "filterModel");
        if (filterModel.isCollection()) {
            FilterCollectionModel collectionModel = filterModel.getCollectionModel();
            if (collectionModel == null) {
                return;
            }
            BaseViewsRefinementOverflowManager baseViewsRefinementOverflowManager = this.refinementOverflowManager;
            if (baseViewsRefinementOverflowManager != null) {
                baseViewsRefinementOverflowManager.showRefinementOverflow(collectionModel, getView());
            }
            Log.debug(TAG, "onChildFilterClick " + collectionModel.getTitle() + " has been clicked");
            return;
        }
        final FilterItemModel itemModel = filterModel.getItemModel();
        if (itemModel == null) {
            return;
        }
        this.overlayLoadingView.setVisibility(0);
        String collectionId = itemModel.getCollectionId();
        FilterCollectionModel parentCollection = itemModel.getParentCollection();
        final Integer numValueOf = null;
        if (parentCollection != null && (filters = parentCollection.getFilters()) != null) {
            numValueOf = Integer.valueOf(filters.indexOf(itemModel));
        }
        HashMap<String, String> map = new HashMap<String, String>(numValueOf) { // from class: com.amazon.mp3.catalog.fragment.NewReleasesDetailBrushFragment$onChildFilterClick$contentInfo$1
            final /* synthetic */ Integer $refinementIndex;

            {
                this.$refinementIndex = numValueOf;
                put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
                put("contentName", this.$filter.getTitle());
                put("containerIndex", String.valueOf(numValueOf));
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ boolean containsKey(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return containsKey((String) obj);
                }
                return false;
            }

            public /* bridge */ boolean containsKey(String str) {
                return super.containsKey((Object) str);
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ boolean containsValue(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return containsValue((String) obj);
                }
                return false;
            }

            public /* bridge */ boolean containsValue(String str) {
                return super.containsValue((Object) str);
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Set<Map.Entry<String, String>> entrySet() {
                return getEntries();
            }

            public /* bridge */ Set<Map.Entry<String, String>> getEntries() {
                return super.entrySet();
            }

            public /* bridge */ Set<String> getKeys() {
                return super.keySet();
            }

            public /* bridge */ int getSize() {
                return super.size();
            }

            public /* bridge */ Collection<String> getValues() {
                return super.values();
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Set<String> keySet() {
                return getKeys();
            }

            @Override // java.util.HashMap, java.util.Map
            public final /* bridge */ boolean remove(Object obj, Object obj2) {
                if (!(obj == null ? true : obj instanceof String)) {
                    return false;
                }
                if (obj2 != null ? obj2 instanceof String : true) {
                    return remove((String) obj, (String) obj2);
                }
                return false;
            }

            public /* bridge */ boolean remove(String str, String str2) {
                return super.remove((Object) str, (Object) str2);
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ int size() {
                return getSize();
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Collection<String> values() {
                return getValues();
            }
        };
        if (collectionId != null && (browseId = itemModel.getBrowseId()) != null) {
            this.refinementLists.put(collectionId, CollectionsKt__CollectionsJVMKt.listOf(browseId));
        }
        sendUiClickMetric(itemModel.getBlockRef(), ActionType.SELECT_REFINEMENT, InteractionType.TAP, map, itemModel.getBrowseId(), EntityIdType.REFINEMENT_ID, EntityType.REFINEMENT);
        this.mPageDataModel.fetchFilteredPageDataBrushV3(this, this.refinementLists);
        Log.debug(TAG, "onChildFilterClick " + itemModel.getTitle() + " has been clicked");
    }

    @Override // com.amazon.music.views.library.providers.RefinementClickListenerProvider
    public void onClick(RefinementPillModel model, View view) {
        List<FilterItemModel> filters;
        BaseViewsRefinementOverflowManager baseViewsRefinementOverflowManager;
        Intrinsics.checkNotNullParameter(model, "model");
        FilterModel content = model.getContent();
        if (content == null) {
            return;
        }
        if (content.isCollection()) {
            FilterCollectionModel collectionModel = content.getCollectionModel();
            if (collectionModel == null || (baseViewsRefinementOverflowManager = this.refinementOverflowManager) == null) {
                return;
            }
            baseViewsRefinementOverflowManager.showRefinementOverflow(collectionModel, view);
            return;
        }
        final FilterItemModel itemModel = content.getItemModel();
        if (itemModel == null) {
            return;
        }
        this.overlayLoadingView.setVisibility(0);
        FilterCollectionModel parentCollection = itemModel.getParentCollection();
        final Integer numValueOf = (parentCollection == null || (filters = parentCollection.getFilters()) == null) ? null : Integer.valueOf(filters.indexOf(itemModel));
        sendUiClickMetric(itemModel.getBlockRef(), ActionType.SELECT_REFINEMENT, InteractionType.TAP, new HashMap<String, String>(numValueOf) { // from class: com.amazon.mp3.catalog.fragment.NewReleasesDetailBrushFragment$onClick$1$contentInfo$1
            final /* synthetic */ Integer $refinementIndex;

            {
                this.$refinementIndex = numValueOf;
                put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
                put("contentName", this.$filter.getTitle());
                put("containerIndex", String.valueOf(numValueOf));
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ boolean containsKey(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return containsKey((String) obj);
                }
                return false;
            }

            public /* bridge */ boolean containsKey(String str) {
                return super.containsKey((Object) str);
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ boolean containsValue(Object obj) {
                if (obj == null ? true : obj instanceof String) {
                    return containsValue((String) obj);
                }
                return false;
            }

            public /* bridge */ boolean containsValue(String str) {
                return super.containsValue((Object) str);
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Set<Map.Entry<String, String>> entrySet() {
                return getEntries();
            }

            public /* bridge */ Set<Map.Entry<String, String>> getEntries() {
                return super.entrySet();
            }

            public /* bridge */ Set<String> getKeys() {
                return super.keySet();
            }

            public /* bridge */ int getSize() {
                return super.size();
            }

            public /* bridge */ Collection<String> getValues() {
                return super.values();
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Set<String> keySet() {
                return getKeys();
            }

            @Override // java.util.HashMap, java.util.Map
            public final /* bridge */ boolean remove(Object obj, Object obj2) {
                if (!(obj == null ? true : obj instanceof String)) {
                    return false;
                }
                if (obj2 != null ? obj2 instanceof String : true) {
                    return remove((String) obj, (String) obj2);
                }
                return false;
            }

            public /* bridge */ boolean remove(String str, String str2) {
                return super.remove((Object) str, (Object) str2);
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ int size() {
                return getSize();
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public final /* bridge */ Collection<String> values() {
                return getValues();
            }
        }, itemModel.getBrowseId(), EntityIdType.REFINEMENT_ID, EntityType.REFINEMENT);
        BrushViewModel mPageDataModel = this.mPageDataModel;
        Intrinsics.checkNotNullExpressionValue(mPageDataModel, "mPageDataModel");
        BrushViewModel.fetchFilteredPageDataBrushV3$default(mPageDataModel, this, null, 2, null);
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onFiltersReset() {
        this.overlayLoadingView.setVisibility(0);
        this.refinementLists.clear();
        BrushViewModel mPageDataModel = this.mPageDataModel;
        Intrinsics.checkNotNullExpressionValue(mPageDataModel, "mPageDataModel");
        BrushViewModel.fetchFilteredPageDataBrushV3$default(mPageDataModel, this, null, 2, null);
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onFiltersReset(FilterCollectionModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        FilterItemModel filterItemModel = model.getFilters().get(0);
        String collectionId = filterItemModel == null ? null : filterItemModel.getCollectionId();
        if (this.refinementLists.containsKey(collectionId)) {
            this.refinementLists.remove(collectionId);
        }
        this.overlayLoadingView.setVisibility(0);
        this.mPageDataModel.fetchFilteredPageDataBrushV3(this, this.refinementLists);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public List<FilterModel> getRefinementFilterModels() {
        ExposedRefinementListModel exposedRefinementListModelRetrieveRefinementListModel = retrieveRefinementListModel();
        if (exposedRefinementListModelRetrieveRefinementListModel != null) {
            return exposedRefinementListModelRetrieveRefinementListModel.getFilterModels();
        }
        Log.warning(TAG, "refinement list model is null, returning empty list");
        return new ArrayList();
    }

    private final void initializeActionBarManager() {
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = new BaseViewsFilterActionBarManager(this, this, this.mRecyclerView, this.actionBarProvider);
        this.actionBarManager = baseViewsFilterActionBarManager;
        baseViewsFilterActionBarManager.shouldShowFilterButton();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager2 = this.actionBarManager;
        if (baseViewsFilterActionBarManager2 != null) {
            BaseViewsFilterActionBarManager.shouldShowExposedRefinementList$default(baseViewsFilterActionBarManager2, false, 1, null);
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager3 = this.actionBarManager;
        if (baseViewsFilterActionBarManager3 != null) {
            baseViewsFilterActionBarManager3.shouldShowPageComponentInTopAppBar();
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager4 = this.actionBarManager;
        if (baseViewsFilterActionBarManager4 == null) {
            return;
        }
        baseViewsFilterActionBarManager4.hideFilterAndContextButtons();
    }

    @Override // com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver
    public void setActionBarProvider(ActionBarProvider provider) {
        this.actionBarProvider = provider;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayPageComponentsInTopAppBar(int actionBarHeight) {
        BauhausFilterActionBarView actionBarView;
        RecyclerView.LayoutManager layoutManager;
        View viewFindViewByPosition;
        Integer numFindPositionOfRefinementList = findPositionOfRefinementList();
        if (numFindPositionOfRefinementList == null) {
            return false;
        }
        int iIntValue = numFindPositionOfRefinementList.intValue();
        RecyclerView recyclerView = this.mRecyclerView;
        TextView title = null;
        RecyclerView.LayoutManager layoutManager2 = recyclerView == null ? null : recyclerView.getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager2 instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager2 : null;
        boolean zIsTargetViewUnderActionBarView = linearLayoutManager == null ? false : BaseViewsFilterActionBarManager.INSTANCE.isTargetViewUnderActionBarView(iIntValue, actionBarHeight, linearLayoutManager);
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != null && (layoutManager = recyclerView2.getLayoutManager()) != null && (viewFindViewByPosition = layoutManager.findViewByPosition(iIntValue)) != null) {
            viewFindViewByPosition.setVisibility(zIsTargetViewUnderActionBarView ? 4 : 0);
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager != null && (actionBarView = baseViewsFilterActionBarManager.getActionBarView()) != null) {
            title = actionBarView.getTitleView();
        }
        if (title != null) {
            title.setVisibility(zIsTargetViewUnderActionBarView ? 0 : 8);
        }
        return zIsTargetViewUnderActionBarView;
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void setupBrushViews() {
        ExposedRefinementListModel exposedRefinementListModelRetrieveRefinementListModel;
        BauhausFilterActionBarView actionBarView;
        PageGridViewModel pageGridViewModel;
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        LibraryStateProvider mLibraryStateProvider = this.mLibraryStateProvider;
        Intrinsics.checkNotNullExpressionValue(mLibraryStateProvider, "mLibraryStateProvider");
        this.mContextMenuProvider = new DefaultContextMenuProvider(fragmentActivityRequireActivity, this, mLibraryStateProvider, getPageType(), this.mPageDataModel.getPageStateManager());
        this.brushViews = BrowseViewsFactory.createBrowseViewsV2(this, this.styleSheet, this.mRecyclerView, this.mContextMenuProvider, this.mSeeMoreProvider, this.mUriClickProvider, null, null, new EndlessScrollDataProvider() { // from class: com.amazon.mp3.catalog.fragment.NewReleasesDetailBrushFragment$setupBrushViews$endlessScrollDataProvider$1
            @Override // com.amazon.music.views.library.providers.EndlessScrollDataProvider
            public void loadMore(int page, int totalItemsCount) {
                this.this$0.mPageDataModel.fetchData(page);
            }
        }, this.mLibraryStateProvider, this.mContentViewManager, getPageType(), this.mPageDataModel.getFollowStateUseCase(), this);
        this.mLoadingView.setVisibility(8);
        createBrushRootView();
        StyleSheet styleSheet = this.styleSheet;
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        RefinementPillBinder refinementPillBinder = new RefinementPillBinder(styleSheet, this);
        BaseViewModelAdapterProvider baseViewModelAdapterProvider = new BaseViewModelAdapterProvider(requireActivity(), null, LifecycleOwnerKt.getLifecycleScope(requireActivity()), Feature.use_optimized_observable_browse_views_factory.isEnabled());
        BaseViewsRefinementManager refinementsManager = this.mPageDataModel.getRefinementManager();
        List<FilterModel> filterModels = (refinementsManager == null || (exposedRefinementListModelRetrieveRefinementListModel = refinementsManager.retrieveRefinementListModel()) == null) ? null : exposedRefinementListModelRetrieveRefinementListModel.getFilterModels();
        if (filterModels == null) {
            filterModels = new ArrayList<>();
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager != null) {
            baseViewsFilterActionBarManager.setAdapterProviderForActionBarView(baseViewModelAdapterProvider, refinementPillBinder, filterModels);
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager2 = this.actionBarManager;
        if (baseViewsFilterActionBarManager2 == null || (actionBarView = baseViewsFilterActionBarManager2.getActionBarView()) == null) {
            return;
        }
        actionBarView.setTitles((isGridListHeaderViewPresent() || (pageGridViewModel = this.mPageModel) == null) ? null : pageGridViewModel.getTitle(), null);
    }

    @Override // com.amazon.mp3.catalog.fragment.DetailBrushFragment, com.amazon.mp3.catalog.fragment.BrushFragment
    protected void createBrushRootView() {
        Set<Integer> of = isGridListHeaderViewPresent() ? SetsKt__SetsJVMKt.setOf(0) : null;
        BrushViews brushViews = this.brushViews;
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        List<BaseViewModel> models = this.mPageModel.getModels();
        int tileType = this.mPageModel.getTileType();
        RecyclerView mRecyclerView = this.mRecyclerView;
        Intrinsics.checkNotNullExpressionValue(mRecyclerView, "mRecyclerView");
        brushViews.createRootView(userSubscription, models, tileType, mRecyclerView, this.swipeRefreshLayout, of);
        setBrushViewsTopOffsetIfNeed();
        setupLayoutAndScrollPosition();
        if (this.mPageModel.getNextPageToken() == null) {
            bindBackgroundImageToFirstItem();
        }
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public Integer getFilterButtonClickDestination() {
        List<Object> items;
        RecyclerView recyclerView = this.mRecyclerView;
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        UniversalAdapter universalAdapter = adapter instanceof UniversalAdapter ? (UniversalAdapter) adapter : null;
        if (universalAdapter == null || (items = universalAdapter.getItems()) == null) {
            return null;
        }
        int i = 0;
        Iterator<Object> it = items.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next() instanceof ExposedRefinementListModel) {
                break;
            }
            i++;
        }
        return Integer.valueOf(i);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public ExposedRefinementListModel getRefinementListModel() {
        List<Object> items;
        Integer numFindPositionOfRefinementList = findPositionOfRefinementList();
        if (numFindPositionOfRefinementList == null) {
            return null;
        }
        int iIntValue = numFindPositionOfRefinementList.intValue();
        RecyclerView recyclerView = this.mRecyclerView;
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        UniversalAdapter universalAdapter = adapter instanceof UniversalAdapter ? (UniversalAdapter) adapter : null;
        Object obj = (universalAdapter == null || (items = universalAdapter.getItems()) == null) ? null : items.get(iIntValue);
        if (obj instanceof ExposedRefinementListModel) {
            return (ExposedRefinementListModel) obj;
        }
        return null;
    }

    private final Integer findPositionOfRefinementList() {
        UniversalAdapter universalAdapter;
        List<Object> items;
        Integer numValueOf;
        RecyclerView recyclerView = this.mRecyclerView;
        if ((recyclerView == null ? null : recyclerView.getAdapter()) instanceof UniversalAdapter) {
            RecyclerView recyclerView2 = this.mRecyclerView;
            RecyclerView.Adapter adapter = recyclerView2 == null ? null : recyclerView2.getAdapter();
            if (adapter == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter");
            }
            universalAdapter = (UniversalAdapter) adapter;
        } else {
            universalAdapter = null;
        }
        if (universalAdapter == null || (items = universalAdapter.getItems()) == null) {
            numValueOf = null;
        } else {
            int i = 0;
            Iterator<Object> it = items.iterator();
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                if (it.next() instanceof ExposedRefinementListModel) {
                    break;
                }
                i++;
            }
            numValueOf = Integer.valueOf(i);
        }
        if (numValueOf != null && numValueOf.intValue() == -1) {
            return null;
        }
        return numValueOf;
    }

    private final ExposedRefinementListModel retrieveRefinementListModel() {
        PageGridViewModel pageGridViewModel = this.mPageModel;
        if (pageGridViewModel != null) {
            for (BaseViewModel baseViewModel : pageGridViewModel.getModels()) {
                if (baseViewModel instanceof ExposedRefinementListModel) {
                    return (ExposedRefinementListModel) baseViewModel;
                }
            }
        }
        Log.warning(TAG, "Unable to find ExposedRefinementListModel in page model");
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.adapter.FilteredPageCallback
    public void onFilterRequestApplied() {
        BaseViewsRefinementOverflowManager baseViewsRefinementOverflowManager = this.refinementOverflowManager;
        if (baseViewsRefinementOverflowManager == null) {
            return;
        }
        baseViewsRefinementOverflowManager.dismissActionSheet();
    }

    @Override // com.amazon.mp3.catalog.fragment.adapter.FilteredPageCallback
    public void onFilterResponse(boolean successfulRequest) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.catalog.fragment.NewReleasesDetailBrushFragment$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    NewReleasesDetailBrushFragment.m519onFilterResponse$lambda17(this.f$0);
                }
            });
        }
        if (successfulRequest) {
            sendUiPageViewMetric(removeUriPrefix(getIdentifier()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onFilterResponse$lambda-17, reason: not valid java name */
    public static final void m519onFilterResponse$lambda17(NewReleasesDetailBrushFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.overlayLoadingView.setVisibility(8);
    }

    private final String removeUriPrefix(String uri) {
        return uri != null ? StringsKt__StringsJVMKt.replace$default(uri, FindSearchBarView.URI_PREFIX, "", false, 4, (Object) null) : uri;
    }
}
