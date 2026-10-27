package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.binders.RefinementFilterRowItemBinder;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.RefinementFilterListRowItemModel;
import com.amazon.music.views.library.models.RefinementFilterRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.SimpleClickListenerProvider;
import com.amazon.music.views.library.providers.SortOrderChangeProvider;
import com.amazon.music.views.library.providers.StateChangeProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.DividerStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IndicatorSizeKey;
import com.amazon.music.views.library.styles.keys.IndicatorStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.DividerStyle;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.firebase.perf.util.Constants;
import defpackage.RefinementFilterListRowItemBinder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementOverflowView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010P\u001a\u00020O\u0012\u0006\u0010R\u001a\u00020Q¢\u0006\u0004\bS\u0010TJ\u001e\u0010\b\u001a\u00020\u00072\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0005J\u001a\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000bJ\"\u0010\u000f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000bJ&\u0010\u0013\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0010J\u0010\u0010\u0016\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014J\"\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00192\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001bJ\u0018\u0010!\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0014J\u0010\u0010\"\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\u001e\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\u00022\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u0002J\u0010\u0010%\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\u0010\u0010&\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\u0010\u0010'\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\u0010\u0010(\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\u0010\u0010)\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\u0010\u0010*\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0002R\u001b\u00100\u001a\u00020+8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u001b\u00105\u001a\u0002018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u0010-\u001a\u0004\b3\u00104R\u001b\u00108\u001a\u0002018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010-\u001a\u0004\b7\u00104R\u001b\u0010;\u001a\u0002018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b9\u0010-\u001a\u0004\b:\u00104R\u001b\u0010@\u001a\u00020<8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b=\u0010-\u001a\u0004\b>\u0010?R\u001b\u0010C\u001a\u00020<8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bA\u0010-\u001a\u0004\bB\u0010?R\u0018\u0010E\u001a\u0004\u0018\u00010D8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u0017\u0010G\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bI\u0010JR\u0017\u0010K\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\bK\u0010H\u001a\u0004\bL\u0010JR\u0017\u0010M\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\bM\u0010H\u001a\u0004\bN\u0010J¨\u0006U"}, d2 = {"Lcom/amazon/music/views/library/views/RefinementOverflowView;", "Landroid/widget/LinearLayout;", "", "Lcom/amazon/music/views/library/models/FilterModel;", "models", "", "title", "", "display", "", "isShown", "Landroid/view/View$OnClickListener;", "onClickListener", "showDismissButton", Constants.ENABLE_DISABLE, "showApplyButton", "Lkotlin/Function1;", "Lcom/amazon/music/views/library/providers/SortOrderChangeProvider$SortOrder;", "onClick", "showSortButton", "Lcom/amazon/music/views/library/providers/StateChangeProvider;", "stateChangeProvider", "setResetButtonClickListener", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "adapterProvider", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/providers/SimpleClickListenerProvider;", "itemClickListener", "init", "", "widthMeasureSpec", "heightMeasureSpec", "onMeasure", "addDividersForOverflowList", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convertToBaseViewModels", "initStyleSheet", "initApplyButton", "initDismissButton", "initSortButton", "initResetButton", "initTitleView", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView$delegate", "Lkotlin/Lazy;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "Lcom/amazon/ui/foundations/views/BaseButton;", "applyButton$delegate", "getApplyButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "applyButton", "resetButton$delegate", "getResetButton", "resetButton", "sortButton$delegate", "getSortButton", "sortButton", "Landroid/widget/TextView;", "dismissView$delegate", "getDismissView", "()Landroid/widget/TextView;", "dismissView", "titleTextView$delegate", "getTitleTextView", "titleTextView", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "adapter", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "applyText", "Ljava/lang/String;", "getApplyText", "()Ljava/lang/String;", "dismissText", "getDismissText", "resetText", "getResetText", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class RefinementOverflowView extends LinearLayout {
    private UniversalAdapter adapter;

    /* JADX INFO: renamed from: applyButton$delegate, reason: from kotlin metadata */
    private final Lazy applyButton;
    private final String applyText;
    private final String dismissText;

    /* JADX INFO: renamed from: dismissView$delegate, reason: from kotlin metadata */
    private final Lazy dismissView;

    /* JADX INFO: renamed from: recyclerView$delegate, reason: from kotlin metadata */
    private final Lazy recyclerView;

    /* JADX INFO: renamed from: resetButton$delegate, reason: from kotlin metadata */
    private final Lazy resetButton;
    private final String resetText;

    /* JADX INFO: renamed from: sortButton$delegate, reason: from kotlin metadata */
    private final Lazy sortButton;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RefinementOverflowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.recyclerView = LazyKt__LazyJVMKt.lazy(new Function0<RecyclerView>() { // from class: com.amazon.music.views.library.views.RefinementOverflowView$recyclerView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RecyclerView invoke() {
                return (RecyclerView) this.this$0.findViewById(R$id.recycler_view);
            }
        });
        this.applyButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.RefinementOverflowView$applyButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.apply_button);
            }
        });
        this.resetButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.RefinementOverflowView$resetButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.reset_button);
            }
        });
        this.sortButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.RefinementOverflowView$sortButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.sort_button);
            }
        });
        this.dismissView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.RefinementOverflowView$dismissView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.dismiss_view);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.RefinementOverflowView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.refinement_title);
            }
        });
        String string = getResources().getString(R$string.dmusic_refinement_apply);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st….dmusic_refinement_apply)");
        this.applyText = string;
        String string2 = getResources().getString(R$string.dmusic_refinement_dismiss);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…music_refinement_dismiss)");
        this.dismissText = string2;
        String string3 = getResources().getString(R$string.dmusic_refinement_reset);
        Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.st….dmusic_refinement_reset)");
        this.resetText = string3;
        LayoutInflater.from(getContext()).inflate(R$layout.dmmviewlibrary_refinement_overflow, this);
        setOrientation(1);
    }

    private final RecyclerView getRecyclerView() {
        Object value = this.recyclerView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-recyclerView>(...)");
        return (RecyclerView) value;
    }

    private final BaseButton getApplyButton() {
        Object value = this.applyButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-applyButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getResetButton() {
        Object value = this.resetButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-resetButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getSortButton() {
        Object value = this.sortButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-sortButton>(...)");
        return (BaseButton) value;
    }

    private final TextView getDismissView() {
        Object value = this.dismissView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-dismissView>(...)");
        return (TextView) value;
    }

    private final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    public final String getApplyText() {
        return this.applyText;
    }

    public final String getDismissText() {
        return this.dismissText;
    }

    public final String getResetText() {
        return this.resetText;
    }

    public final void display(List<? extends FilterModel> models, String title) {
        Intrinsics.checkNotNullParameter(models, "models");
        Intrinsics.checkNotNullParameter(title, "title");
        UniversalAdapter universalAdapter = this.adapter;
        if (universalAdapter != null) {
            universalAdapter.clear();
        }
        UniversalAdapter universalAdapter2 = this.adapter;
        if (universalAdapter2 != null) {
            UniversalAdapter.addItems$default(universalAdapter2, convertToBaseViewModels(models), null, false, 6, null);
        }
        getTitleTextView().setText(title);
    }

    public final void showDismissButton(boolean isShown, View.OnClickListener onClickListener) {
        getDismissView().setVisibility(isShown ? 0 : 8);
        getDismissView().setOnClickListener(onClickListener);
    }

    public final void showApplyButton(boolean isShown, boolean isEnabled, View.OnClickListener onClickListener) {
        getApplyButton().setVisibility(isShown ? 0 : 8);
        getApplyButton().setEnabled(isEnabled);
        getApplyButton().setOnClickListener(onClickListener);
    }

    public final void showSortButton(boolean isShown, final Function1<? super SortOrderChangeProvider.SortOrder, Unit> onClick) {
        getSortButton().setVisibility(isShown ? 0 : 8);
        if (isShown) {
            getSortButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.RefinementOverflowView$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RefinementOverflowView.m2431showSortButton$lambda0(this.f$0, onClick, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showSortButton$lambda-0, reason: not valid java name */
    public static final void m2431showSortButton$lambda0(RefinementOverflowView this$0, Function1 function1, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getSortButton().setActive(!this$0.getSortButton().getIsActive());
        SortOrderChangeProvider.SortOrder sortOrder = this$0.getSortButton().getIsActive() ? SortOrderChangeProvider.SortOrder.ASCENDING : SortOrderChangeProvider.SortOrder.DESCENDING;
        if (function1 == null) {
            return;
        }
        function1.invoke(sortOrder);
    }

    public final void setResetButtonClickListener(final StateChangeProvider stateChangeProvider) {
        getResetButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.RefinementOverflowView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RefinementOverflowView.m2430setResetButtonClickListener$lambda1(stateChangeProvider, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setResetButtonClickListener$lambda-1, reason: not valid java name */
    public static final void m2430setResetButtonClickListener$lambda1(StateChangeProvider stateChangeProvider, RefinementOverflowView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (stateChangeProvider != null) {
            stateChangeProvider.onResetState(null);
        }
        UniversalAdapter universalAdapter = this$0.adapter;
        if (universalAdapter == null) {
            return;
        }
        universalAdapter.getItems();
    }

    public final void init(BaseViewModelAdapterProvider adapterProvider, StyleSheet styleSheet, SimpleClickListenerProvider itemClickListener) {
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        initStyleSheet(styleSheet);
        getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = adapterProvider.getAdapter(CollectionsKt__CollectionsKt.emptyList(), new RefinementFilterRowItemBinder(styleSheet, itemClickListener), new RefinementFilterListRowItemBinder(styleSheet, itemClickListener));
        getRecyclerView().setAdapter(this.adapter);
        addDividersForOverflowList(styleSheet);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), Math.min(View.MeasureSpec.getSize(heightMeasureSpec), getMeasuredHeight()));
    }

    private final void addDividersForOverflowList(StyleSheet styleSheet) {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.grid_divider);
        DividerStyle dividerStyle = styleSheet.getDividerStyle(DividerStyleKey.DEFAULT);
        if (dividerStyle != null) {
            int color = dividerStyle.getColor();
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_OVER));
            }
        }
        getRecyclerView().addItemDecoration(new RefinementOverflowItemDividerDecoration(drawable));
    }

    private final List<BaseViewModel> convertToBaseViewModels(List<? extends FilterModel> models) {
        FilterItemModel itemModel;
        ArrayList arrayList = new ArrayList();
        Iterator<? extends FilterModel> it = models.iterator();
        while (it.hasNext()) {
            FilterModel next = it.next();
            if (next != null && next.isCollection()) {
                FilterCollectionModel collectionModel = next.getCollectionModel();
                if (collectionModel != null) {
                    arrayList.add(new RefinementFilterListRowItemModel(null, collectionModel));
                }
            } else {
                if (((next == null || next.isCollection()) ? false : true) && (itemModel = next.getItemModel()) != null) {
                    arrayList.add(new RefinementFilterRowItemModel(null, itemModel));
                }
            }
        }
        return arrayList;
    }

    private final void initStyleSheet(StyleSheet styleSheet) {
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        GridSize gridSize = styleSheet.getGridSize();
        if (gridSize != null) {
            int marginSpace = gridSize.getMarginSpace() + gridSize.getColumnSpace();
            setPadding(marginSpace, spacerInPixels == null ? getPaddingTop() : spacerInPixels.intValue(), marginSpace, getPaddingBottom());
        }
        initApplyButton(styleSheet);
        initSortButton(styleSheet);
        initResetButton(styleSheet);
        initTitleView(styleSheet);
        initDismissButton(styleSheet);
    }

    private final void initApplyButton(StyleSheet styleSheet) {
        BaseButton.StyleBuilder styleBuilderWithText;
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID);
        if (buttonBuilder != null && (styleBuilderWithText = buttonBuilder.withText(this.applyText)) != null) {
            styleBuilderWithText.applyStyle(getApplyButton());
        }
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        if (spacerInPixels == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        ViewGroup.LayoutParams layoutParams = getApplyButton().getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        }
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
        layoutParams2.topMargin = iIntValue;
        layoutParams2.bottomMargin = iIntValue;
        getApplyButton().requestLayout();
    }

    private final void initDismissButton(StyleSheet styleSheet) {
        getDismissView().setText(this.dismissText);
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getDismissView(), fontStyle);
        }
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.BASE);
        if (spacerInPixels == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        ViewGroup.LayoutParams layoutParams = getDismissView().getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        }
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
        layoutParams2.topMargin = iIntValue;
        layoutParams2.bottomMargin = iIntValue;
        getDismissView().requestLayout();
    }

    private final void initSortButton(StyleSheet styleSheet) {
        BaseButton.StyleBuilder indicatorBuilder = styleSheet.getIndicatorBuilder(IndicatorSizeKey.SMALL, IndicatorStyleKey.PLAYBACK);
        ButtonStyle buttonStyle = styleSheet.getButtonStyle(ButtonStyleKey.GLASS);
        if (buttonStyle != null && indicatorBuilder != null) {
            indicatorBuilder.withStyle(buttonStyle);
        }
        ButtonStyle buttonStyle2 = styleSheet.getButtonStyle(ButtonStyleKey.SOLID);
        if (buttonStyle2 != null && indicatorBuilder != null) {
            indicatorBuilder.withActiveStyle(buttonStyle2);
        }
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_other_sort2);
        if (drawable != null && indicatorBuilder != null) {
            indicatorBuilder.withIcon(drawable);
        }
        Drawable drawable2 = AppCompatResources.getDrawable(getContext(), R$drawable.ic_other_sort3);
        if (drawable2 != null && indicatorBuilder != null) {
            indicatorBuilder.withActiveIcon(drawable2);
        }
        if (indicatorBuilder != null) {
            indicatorBuilder.applyStyle(getSortButton());
        }
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MINI);
        if (spacerInPixels == null) {
            return;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(getSortButton(), -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : Integer.valueOf(spacerInPixels.intValue()), (240 & 128) != 0 ? null : null);
    }

    private final void initResetButton(StyleSheet styleSheet) {
        BaseButton.StyleBuilder styleBuilderWithText;
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.SMALL, ButtonStyleKey.GLASS);
        if (buttonBuilder == null || (styleBuilderWithText = buttonBuilder.withText(this.resetText)) == null) {
            return;
        }
        styleBuilderWithText.applyStyle(getResetButton());
    }

    private final void initTitleView(StyleSheet styleSheet) {
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle);
        }
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MINI);
        if (spacerInPixels == null) {
            return;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(getTitleTextView(), 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : Integer.valueOf(spacerInPixels.intValue()), (240 & 128) != 0 ? null : null);
    }
}
