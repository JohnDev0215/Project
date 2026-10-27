package com.amazon.mp3.view.refinements;

import android.view.View;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.providers.RefinementCallback;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DetailPageRefinementManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0016¢\u0006\u0004\b4\u00105J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u001a\u0010\u000b\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0005H\u0016J\u0016\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0013H\u0016R\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010!\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R$\u0010'\u001a\u0004\u0018\u00010\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R$\u0010.\u001a\u0004\u0018\u00010-8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103¨\u00066"}, d2 = {"Lcom/amazon/mp3/view/refinements/DetailPageRefinementManager;", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Lcom/amazon/music/views/library/models/FilterModel;", "filterModel", "", "onChildFilterClick", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "model", "Landroid/view/View;", "view", "onClick", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "onFiltersReset", "Lcom/amazon/mp3/catalog/fragment/AbstractBaseViewsFragment;", "fragment", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "initializeOverflowManager", "Landroidx/lifecycle/LifecycleOwner;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "onDestroy", "Lkotlin/Function0;", "onSelectedRefinementListUpdatedListener", "Lkotlin/jvm/functions/Function0;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "refinementsManager", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "getRefinementsManager", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "setRefinementsManager", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;)V", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "actionBarManager", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "getActionBarManager", "()Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "setActionBarManager", "(Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;)V", "additionalCallbackHandler", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "getAdditionalCallbackHandler", "()Lcom/amazon/music/views/library/providers/RefinementCallback;", "setAdditionalCallbackHandler", "(Lcom/amazon/music/views/library/providers/RefinementCallback;)V", "Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;", "refinementOverflowManager", "Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;", "getRefinementOverflowManager", "()Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;", "setRefinementOverflowManager", "(Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;)V", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DetailPageRefinementManager implements RefinementCallback, DefaultLifecycleObserver {
    private BaseViewsFilterActionBarManager actionBarManager;
    private RefinementCallback additionalCallbackHandler;
    private Function0<Unit> onSelectedRefinementListUpdatedListener;
    private BaseViewsRefinementOverflowManager refinementOverflowManager;
    private BaseViewsRefinementManager refinementsManager;

    public DetailPageRefinementManager(Function0<Unit> onSelectedRefinementListUpdatedListener) {
        Intrinsics.checkNotNullParameter(onSelectedRefinementListUpdatedListener, "onSelectedRefinementListUpdatedListener");
        this.onSelectedRefinementListUpdatedListener = onSelectedRefinementListUpdatedListener;
    }

    public final BaseViewsRefinementManager getRefinementsManager() {
        return this.refinementsManager;
    }

    public final void setRefinementsManager(BaseViewsRefinementManager baseViewsRefinementManager) {
        this.refinementsManager = baseViewsRefinementManager;
    }

    public final BaseViewsFilterActionBarManager getActionBarManager() {
        return this.actionBarManager;
    }

    public final void setActionBarManager(BaseViewsFilterActionBarManager baseViewsFilterActionBarManager) {
        this.actionBarManager = baseViewsFilterActionBarManager;
    }

    public final RefinementCallback getAdditionalCallbackHandler() {
        return this.additionalCallbackHandler;
    }

    public final void setAdditionalCallbackHandler(RefinementCallback refinementCallback) {
        this.additionalCallbackHandler = refinementCallback;
    }

    public final BaseViewsRefinementOverflowManager getRefinementOverflowManager() {
        return this.refinementOverflowManager;
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onChildFilterClick(FilterModel filterModel) {
        Intrinsics.checkNotNullParameter(filterModel, "filterModel");
        RefinementCallback refinementCallback = this.additionalCallbackHandler;
        if (refinementCallback == null) {
            return;
        }
        refinementCallback.onChildFilterClick(filterModel);
    }

    @Override // com.amazon.music.views.library.providers.RefinementClickListenerProvider
    public void onClick(final RefinementPillModel model, View view) {
        OnFilterChangedListener changedListener;
        BaseViewsRefinementOverflowManager refinementOverflowManager;
        Intrinsics.checkNotNullParameter(model, "model");
        FilterModel content = model.getContent();
        if (content == null) {
            return;
        }
        if (content.isCollection()) {
            final FilterCollectionModel collectionModel = content.getCollectionModel();
            if (collectionModel == null) {
                return;
            }
            String title = collectionModel.getTitle();
            RefinementFilterType.Companion companion = RefinementFilterType.INSTANCE;
            if ((Intrinsics.areEqual(title, companion.getDisplayName(RefinementFilterType.SORT)) || Intrinsics.areEqual(collectionModel.getTitle(), companion.getDisplayName(RefinementFilterType.AUDIO_QUALITY))) && (refinementOverflowManager = getRefinementOverflowManager()) != null) {
                refinementOverflowManager.showRefinementOverflow(collectionModel, view, new SingleSelectRefinementOverflowSession(collectionModel), new View.OnClickListener() { // from class: com.amazon.mp3.view.refinements.DetailPageRefinementManager$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        DetailPageRefinementManager.m1733onClick$lambda2$lambda1(collectionModel, this, model, view2);
                    }
                });
            }
        } else {
            FilterItemModel itemModel = content.getItemModel();
            if (itemModel == null) {
                return;
            }
            BaseViewsRefinementManager refinementsManager = getRefinementsManager();
            boolean z = false;
            if (refinementsManager != null && (changedListener = refinementsManager.getChangedListener()) != null && changedListener.shouldOverrideSelectionChange(itemModel.getTitle(), !itemModel.getSelected())) {
                z = true;
            }
            if (z) {
                return;
            }
            itemModel.setSelected(!itemModel.getSelected());
            model.updateFilterModel(itemModel);
            BaseViewsRefinementManager refinementsManager2 = getRefinementsManager();
            if (refinementsManager2 != null) {
                refinementsManager2.updateSelectedFilterSet(itemModel);
            }
            this.onSelectedRefinementListUpdatedListener.invoke();
        }
        RefinementCallback additionalCallbackHandler = getAdditionalCallbackHandler();
        if (additionalCallbackHandler == null) {
            return;
        }
        additionalCallbackHandler.onClick(model, view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onClick$lambda-2$lambda-1, reason: not valid java name */
    public static final void m1733onClick$lambda2$lambda1(FilterCollectionModel collectionFilter, DetailPageRefinementManager this$0, RefinementPillModel model, View view) {
        Intrinsics.checkNotNullParameter(collectionFilter, "$collectionFilter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        for (FilterItemModel filterItemModel : CollectionsKt___CollectionsKt.filterNotNull(collectionFilter.getFilters())) {
            BaseViewsRefinementManager refinementsManager = this$0.getRefinementsManager();
            if (refinementsManager != null) {
                refinementsManager.updateSelectedFilterSet(filterItemModel);
            }
        }
        this$0.onSelectedRefinementListUpdatedListener.invoke();
        model.updateFilterModel(collectionFilter);
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onFiltersReset(FilterCollectionModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        RefinementCallback refinementCallback = this.additionalCallbackHandler;
        if (refinementCallback == null) {
            return;
        }
        refinementCallback.onFiltersReset(model);
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onFiltersReset() {
        OnFilterChangedListener changedListener;
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
        if ((baseViewsRefinementManager == null || (changedListener = baseViewsRefinementManager.getChangedListener()) == null || !changedListener.shouldOverrideResetFilters()) ? false : true) {
            return;
        }
        BaseViewsRefinementManager baseViewsRefinementManager2 = this.refinementsManager;
        if (baseViewsRefinementManager2 != null) {
            baseViewsRefinementManager2.resetRefinementFilters();
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager != null) {
            baseViewsFilterActionBarManager.refreshActionBarFilters();
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager2 = this.actionBarManager;
        if (baseViewsFilterActionBarManager2 != null) {
            BaseViewsFilterActionBarManager.shouldShowExposedRefinementList$default(baseViewsFilterActionBarManager2, false, 1, null);
        }
        RefinementCallback refinementCallback = this.additionalCallbackHandler;
        if (refinementCallback != null) {
            refinementCallback.onFiltersReset();
        }
        this.onSelectedRefinementListUpdatedListener.invoke();
    }

    public final void initializeOverflowManager(AbstractBaseViewsFragment fragment, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.refinementOverflowManager = new BaseViewsRefinementOverflowManager(fragment, styleSheet, this);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onDestroy(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        super.onDestroy(owner);
        this.refinementsManager = null;
        this.actionBarManager = null;
        this.additionalCallbackHandler = null;
        this.refinementOverflowManager = null;
        this.onSelectedRefinementListUpdatedListener = new Function0<Unit>() { // from class: com.amazon.mp3.view.refinements.DetailPageRefinementManager.onDestroy.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        };
    }
}
