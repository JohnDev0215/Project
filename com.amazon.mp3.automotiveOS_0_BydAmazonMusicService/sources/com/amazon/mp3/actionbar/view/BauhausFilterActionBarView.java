package com.amazon.mp3.actionbar.view;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.find.model.filter.FilterMetadata;
import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.models.ScrollStateListener;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.ExposedRefinementListView;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: compiled from: BauhausFilterActionBarView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B;\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\b\u0010*\u001a\u00020+H\u0016J\u0019\u0010,\u001a\u0004\u0018\u00010\"2\b\u0010-\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u001bH\u0002J\u0006\u00102\u001a\u00020+J\u0010\u00103\u001a\u00020+2\u0006\u00104\u001a\u000205H\u0002J\u0010\u00106\u001a\u00020+2\u0006\u00104\u001a\u000205H\u0002J\u0006\u00107\u001a\u00020\bJ\b\u00108\u001a\u00020+H\u0014J\u0010\u00109\u001a\u00020+2\u0006\u0010:\u001a\u00020\"H\u0016J,\u0010\u0011\u001a\u00020+2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010;\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030<2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aJ\u0010\u0010>\u001a\u00020+2\b\u0010?\u001a\u0004\u0018\u00010@J\u000e\u0010A\u001a\u00020+2\u0006\u0010B\u001a\u00020CJ\u0010\u0010D\u001a\u00020+2\u0006\u00104\u001a\u000205H\u0002J\u001f\u0010E\u001a\u00020+2\u0006\u0010F\u001a\u00020\b2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\"¢\u0006\u0002\u0010HJ\u000e\u0010I\u001a\u00020+2\u0006\u0010F\u001a\u00020\bR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R4\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001e\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/amazon/mp3/actionbar/view/BauhausFilterActionBarView;", "Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "Lcom/amazon/music/views/library/models/ScrollStateListener;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "title", "", "showProfileButton", "", "showOverflowButton", "findSearchBarView", "Lcom/amazon/music/find/view/FindSearchBarView;", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;ZZLcom/amazon/music/find/view/FindSearchBarView;)V", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "getAdapterProvider", "()Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "setAdapterProvider", "(Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;)V", "filterView", "Lcom/amazon/ui/foundations/views/BaseButton;", "getFilterView", "()Lcom/amazon/ui/foundations/views/BaseButton;", "filterView$delegate", "Lkotlin/Lazy;", "value", "", "Lcom/amazon/music/views/library/models/FilterModel;", "filters", "getFilters", "()Ljava/util/List;", "setFilters", "(Ljava/util/List;)V", "<set-?>", "", "refinementListScrollOffset", "getRefinementListScrollOffset", "()I", "refinementsListView", "Lcom/amazon/music/views/library/views/ExposedRefinementListView;", "subscription", "Lrx/Subscription;", "clearSubscriptions", "", "getPillViewIdForBrowseId", "browseId", "(Ljava/lang/String;)Ljava/lang/Integer;", "getRefinementPillModel", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "filterModel", "hideFilterAndContextButtons", "initExposedRefinements", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "initStyleSheet", "isExposedRefinementsShown", "onDetachedFromWindow", "onScrollStateUpdated", "newScrollOffset", "refinementBinder", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "filterModels", "setFocusedState", "model", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "setOnFilterButtonClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setSpacers", "showExposedRefinements", "isShown", "scrollOffset", "(ZLjava/lang/Integer;)V", "showFilterButton", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BauhausFilterActionBarView extends BauhausActionBarView implements ScrollStateListener {
    private BaseViewModelAdapterProvider adapterProvider;

    /* JADX INFO: renamed from: filterView$delegate, reason: from kotlin metadata */
    private final Lazy filterView;
    private List<? extends FilterModel> filters;
    private int refinementListScrollOffset;
    private ExposedRefinementListView refinementsListView;
    private Subscription subscription;

    public /* synthetic */ BauhausFilterActionBarView(FragmentActivity fragmentActivity, String str, boolean z, boolean z2, FindSearchBarView findSearchBarView, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragmentActivity, (i & 2) != 0 ? null : str, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? null : findSearchBarView);
    }

    public BauhausFilterActionBarView(FragmentActivity fragmentActivity, String str, boolean z, boolean z2, FindSearchBarView findSearchBarView) {
        super(fragmentActivity, str, Boolean.valueOf(z), Boolean.valueOf(z2), findSearchBarView);
        this.filterView = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.actionbar.view.BauhausFilterActionBarView$filterView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.filter_view);
            }
        });
        this.refinementsListView = fragmentActivity == null ? null : new ExposedRefinementListView(fragmentActivity);
        Subscription subscription = this.subscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        if (fragmentActivity == null) {
            return;
        }
        this.subscription = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.actionbar.view.BauhausFilterActionBarView$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BauhausFilterActionBarView.m136lambda3$lambda2(this.f$0, (StyleSheet) obj);
            }
        });
    }

    public final BaseViewModelAdapterProvider getAdapterProvider() {
        return this.adapterProvider;
    }

    public final void setAdapterProvider(BaseViewModelAdapterProvider baseViewModelAdapterProvider) {
        this.adapterProvider = baseViewModelAdapterProvider;
    }

    public final int getRefinementListScrollOffset() {
        return this.refinementListScrollOffset;
    }

    public final List<FilterModel> getFilters() {
        return this.filters;
    }

    public final void setFilters(List<? extends FilterModel> list) {
        UniversalAdapter adapter;
        UniversalAdapter adapter2;
        BaseViewModelAdapterProvider adapterProvider;
        UniversalAdapter adapter3;
        this.filters = list;
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList2.add(getRefinementPillModel((FilterModel) it.next()));
            }
        }
        ExposedRefinementListView exposedRefinementListView = this.refinementsListView;
        if (exposedRefinementListView != null && (adapter3 = exposedRefinementListView.getAdapter()) != null) {
            adapter3.clear();
        }
        ExposedRefinementListView exposedRefinementListView2 = this.refinementsListView;
        if (exposedRefinementListView2 != null && (adapter2 = exposedRefinementListView2.getAdapter()) != null && (adapterProvider = getAdapterProvider()) != null) {
            BaseViewModelAdapterProvider.addItems$default(adapterProvider, adapter2, arrayList, null, 4, null);
        }
        ExposedRefinementListView exposedRefinementListView3 = this.refinementsListView;
        if (exposedRefinementListView3 == null || (adapter = exposedRefinementListView3.getAdapter()) == null) {
            return;
        }
        adapter.notifyDataSetChanged();
    }

    private final BaseButton getFilterView() {
        Object value = this.filterView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-filterView>(...)");
        return (BaseButton) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda-3$lambda-2, reason: not valid java name */
    public static final void m136lambda3$lambda2(BauhausFilterActionBarView this$0, StyleSheet it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.initStyleSheet(it);
        this$0.initExposedRefinements(it);
        this$0.setSpacers(it);
    }

    private final void initStyleSheet(StyleSheet styleSheet) {
        BaseButton.StyleBuilder buttonBuilder;
        BaseButton.StyleBuilder styleBuilderWithCornerRadius;
        showFilterButton(true);
        if (Feature.sonic_rush.isEnabled() && Feature.SR_page_headers.isEnabled()) {
            BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.TINY, IconStyleKey.GLASS);
            if (iconBuilder == null) {
                return;
            }
            Drawable drawable = getContext().getResources().getDrawable(R.drawable.ic_search_filter_btn);
            Intrinsics.checkNotNullExpressionValue(drawable, "context.resources.getDra…ble.ic_search_filter_btn)");
            BaseButton.StyleBuilder styleBuilderWithIcon = iconBuilder.withIcon(drawable);
            if (styleBuilderWithIcon == null) {
                return;
            }
            styleBuilderWithIcon.applyStyle(getFilterView());
            return;
        }
        Integer cornerSize = styleSheet.getCornerSize(CornerSizeKey.MEDIUM);
        if (cornerSize == null || (buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM_WITH_ICON, ButtonStyleKey.GLASS)) == null) {
            return;
        }
        String string = getContext().getString(R.string.dmusic_prime_search_filter_title);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…rime_search_filter_title)");
        BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
        if (styleBuilderWithText == null) {
            return;
        }
        Drawable drawable2 = getContext().getResources().getDrawable(R.drawable.ic_search_filter_btn);
        Intrinsics.checkNotNullExpressionValue(drawable2, "context.resources.getDra…ble.ic_search_filter_btn)");
        BaseButton.StyleBuilder styleBuilderWithIcon2 = styleBuilderWithText.withIcon(drawable2);
        if (styleBuilderWithIcon2 == null || (styleBuilderWithCornerRadius = styleBuilderWithIcon2.withCornerRadius(cornerSize.intValue())) == null) {
            return;
        }
        styleBuilderWithCornerRadius.applyStyle(getFilterView());
    }

    private final void setSpacers(StyleSheet styleSheet) {
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.BASE);
        int paddingTop = spacerInPixels == null ? getPaddingTop() : spacerInPixels.intValue();
        Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        int paddingBottom = spacerInPixels2 == null ? getPaddingBottom() : spacerInPixels2.intValue();
        ExposedRefinementListView exposedRefinementListView = this.refinementsListView;
        if (exposedRefinementListView == null) {
            return;
        }
        exposedRefinementListView.setPadding(getPaddingLeft(), paddingTop, getPaddingRight(), paddingBottom);
    }

    public final void setOnFilterButtonClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getFilterView().setOnClickListener(onClickListener);
    }

    public static /* synthetic */ void showExposedRefinements$default(BauhausFilterActionBarView bauhausFilterActionBarView, boolean z, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        bauhausFilterActionBarView.showExposedRefinements(z, num);
    }

    public final void showExposedRefinements(boolean isShown, Integer scrollOffset) {
        ExposedRefinementListView exposedRefinementListView;
        if (isExposedRefinementsShown() != isShown) {
            if (scrollOffset != null && (exposedRefinementListView = this.refinementsListView) != null) {
                exposedRefinementListView.setScrollState(scrollOffset.intValue());
            }
            ExposedRefinementListView exposedRefinementListView2 = this.refinementsListView;
            if (exposedRefinementListView2 != null) {
                exposedRefinementListView2.setScrollStateListener(isShown ? this : null);
            }
            ExposedRefinementListView exposedRefinementListView3 = this.refinementsListView;
            if (exposedRefinementListView3 != null) {
                exposedRefinementListView3.setVisibility(isShown ? 0 : 8);
            }
            ExposedRefinementListView exposedRefinementListView4 = this.refinementsListView;
            if (exposedRefinementListView4 != null) {
                exposedRefinementListView4.requestLayout();
            }
            if (isShown) {
                setBackgroundOpacity(0.85f);
            }
        }
    }

    public final boolean isExposedRefinementsShown() {
        ExposedRefinementListView exposedRefinementListView = this.refinementsListView;
        return exposedRefinementListView != null && exposedRefinementListView.getVisibility() == 0;
    }

    public final void showFilterButton(boolean isShown) {
        getFilterView().setVisibility(isShown ? 0 : 4);
        showContextMenuOverflowButton(!isShown);
    }

    public final void hideFilterAndContextButtons() {
        getFilterView().setVisibility(4);
        showContextMenuOverflowButton(false);
    }

    public final void setFocusedState(FilterCollectionModel model) {
        if (model != null) {
            ExposedRefinementListView exposedRefinementListView = this.refinementsListView;
            if (exposedRefinementListView == null) {
                return;
            }
            exposedRefinementListView.setFocusedState(model);
            return;
        }
        ExposedRefinementListView exposedRefinementListView2 = this.refinementsListView;
        if (exposedRefinementListView2 == null) {
            return;
        }
        exposedRefinementListView2.clearFocusedState();
    }

    @Override // com.amazon.music.views.library.models.ScrollStateListener
    public void onScrollStateUpdated(int newScrollOffset) {
        this.refinementListScrollOffset = newScrollOffset;
    }

    private final void initExposedRefinements(StyleSheet styleSheet) {
        ArrayList arrayList;
        ExposedRefinementListView exposedRefinementListView = this.refinementsListView;
        if (exposedRefinementListView != null) {
            exposedRefinementListView.init(styleSheet);
            addViewBelowActionBar(exposedRefinementListView);
            List<FilterModel> filters = getFilters();
            if (filters == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(filters, 10));
                Iterator<T> it = filters.iterator();
                while (it.hasNext()) {
                    arrayList.add(getRefinementPillModel((FilterModel) it.next()));
                }
            }
            exposedRefinementListView.setRefinements(arrayList);
        }
        showExposedRefinements$default(this, false, null, 2, null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final Integer getPillViewIdForBrowseId(String browseId) {
        if (browseId != null) {
            switch (browseId.hashCode()) {
                case -2072154266:
                    if (browseId.equals(FilterMetadata.UHD_FILTER_ID)) {
                        return Integer.valueOf(RefinementFilterType.ULTRA_HD.viewId());
                    }
                    break;
                case -1738187099:
                    if (browseId.equals(FilterMetadata.MY_MUSIC_FILTER_ID)) {
                        return Integer.valueOf(RefinementFilterType.MUSIC_LIBRARY.viewId());
                    }
                    break;
                case -143223838:
                    if (browseId.equals(FilterMetadata.SONY_360_FILTER_ID)) {
                        return Integer.valueOf(RefinementFilterType.RA360.viewId());
                    }
                    break;
                case 455969952:
                    if (browseId.equals(FilterMetadata.DOLBY_ATMOS_FILTER_ID)) {
                        return Integer.valueOf(RefinementFilterType.ATMOS.viewId());
                    }
                    break;
            }
        }
        return null;
    }

    private final RefinementPillModel getRefinementPillModel(FilterModel filterModel) {
        Integer num;
        FilterItemModel itemModel = filterModel.getItemModel();
        String browseId = itemModel == null ? null : itemModel.getBrowseId();
        FilterItemModel itemModel2 = filterModel.getItemModel();
        Integer viewId = itemModel2 == null ? null : itemModel2.getViewId();
        if (viewId != null) {
            num = viewId;
        } else {
            FilterCollectionModel collectionModel = filterModel.getCollectionModel();
            Integer viewId2 = collectionModel != null ? collectionModel.getViewId() : null;
            if (viewId2 == null) {
                viewId = getPillViewIdForBrowseId(browseId);
                num = viewId;
            } else {
                num = viewId2;
            }
        }
        return new RefinementPillModel(browseId, filterModel, false, num, null, 20, null);
    }

    public final void setAdapterProvider(BaseViewModelAdapterProvider adapterProvider, UniversalBinder<?, ?> refinementBinder, List<? extends FilterModel> filterModels) {
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        Intrinsics.checkNotNullParameter(refinementBinder, "refinementBinder");
        Intrinsics.checkNotNullParameter(filterModels, "filterModels");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(filterModels, 10));
        Iterator<T> it = filterModels.iterator();
        while (it.hasNext()) {
            arrayList2.add(getRefinementPillModel((FilterModel) it.next()));
        }
        CollectionsKt___CollectionsKt.toCollection(arrayList2, arrayList);
        this.adapterProvider = adapterProvider;
        ExposedRefinementListView exposedRefinementListView = this.refinementsListView;
        if (exposedRefinementListView == null) {
            return;
        }
        exposedRefinementListView.setAdapter(adapterProvider.getAdapter(arrayList, refinementBinder));
    }

    @Override // com.amazon.mp3.actionbar.view.BauhausActionBarView
    public void clearSubscriptions() {
        super.clearSubscriptions();
        Subscription subscription = this.subscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        subscription.unsubscribe();
    }

    @Override // com.amazon.mp3.actionbar.view.BauhausActionBarView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearSubscriptions();
        this.adapterProvider = null;
    }
}
