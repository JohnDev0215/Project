package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElement;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
import Touch.WidgetsInterface.v1_0.SeeMoreItemElement;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.adapters.HorizontalItemAdapter;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.SectionWidget;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DescriptiveShowcaseBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001b2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u001e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J\u0018\u0010\u0013\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0012\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0003H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/DescriptiveShowcaseBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/DescriptiveShowcaseElement;", "Lcom/amazon/music/views/library/views/SectionWidget;", "()V", "gridLayoutUtils", "Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "getGridLayoutUtils", "()Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "gridLayoutUtils$delegate", "Lkotlin/Lazy;", "bind", "", "element", "view", "bindChildren", ContextMappingConstants.ITEMS, "", "LTouch/WidgetsInterface/v1_0/HorizontalItemElement;", "bindStyle", "createView", "parent", "Landroid/view/ViewGroup;", "displayingHeaderText", "", "displayingMoreButton", "reset", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DescriptiveShowcaseBinder extends ElementBinder<DescriptiveShowcaseElement, SectionWidget> {
    private static final int tileLayoutType = 0;

    /* JADX INFO: renamed from: gridLayoutUtils$delegate, reason: from kotlin metadata */
    private final Lazy gridLayoutUtils = LazyKt__LazyJVMKt.lazy(new Function0<GridLayoutUtils>() { // from class: com.amazon.music.skyfire.ui.widgets.binders.DescriptiveShowcaseBinder$gridLayoutUtils$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final GridLayoutUtils invoke() {
            return new GridLayoutUtils(this.this$0.getContext(), this.this$0.getStyleSheet());
        }
    });

    private final GridLayoutUtils getGridLayoutUtils() {
        return (GridLayoutUtils) this.gridLayoutUtils.getValue();
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public SectionWidget createView(ViewGroup parent) {
        SectionWidget sectionWidget = new SectionWidget(getContext(), getStyleSheet());
        sectionWidget.setContentView(new RecyclerView(getContext()));
        return sectionWidget;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(DescriptiveShowcaseElement element, SectionWidget view) {
        final SeeMoreItemElement seeMoreItemElementSeeMore;
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        reset(view);
        GridLayoutUtils gridLayoutUtils = getGridLayoutUtils();
        RecyclerView recyclerView = view.getRecyclerView();
        Intrinsics.checkNotNull(recyclerView);
        GridLayoutUtils.setLayoutManager$default(gridLayoutUtils, recyclerView, 0, Integer.valueOf(element.items().size()), true, false, null, null, 96, null);
        int iMin = Math.min(getGridLayoutUtils().getMaxItems(0), element.items().size());
        List<HorizontalItemElement> listItems = element.items();
        Intrinsics.checkNotNullExpressionValue(listItems, "element.items()");
        List<? extends HorizontalItemElement> listTake = CollectionsKt___CollectionsKt.take(listItems, iMin);
        bindStyle(view, element);
        bindChildren(view, listTake);
        String strPrimaryHeader = element.primaryHeader();
        Intrinsics.checkNotNullExpressionValue(strPrimaryHeader, "element.primaryHeader()");
        view.setTitle(strPrimaryHeader);
        String strSecondaryHeader = element.secondaryHeader();
        if (strSecondaryHeader != null) {
            view.setSubtitle(strSecondaryHeader);
        }
        if (element.items().size() > iMin && (seeMoreItemElementSeeMore = element.seeMore()) != null) {
            SectionWidget.setMoreButtonText$default(view, seeMoreItemElementSeeMore.text(), false, 2, null);
            view.setMoreButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.DescriptiveShowcaseBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    DescriptiveShowcaseBinder.m2214bind$lambda2$lambda1(this.f$0, seeMoreItemElementSeeMore, view2);
                }
            });
        }
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-2$lambda-1, reason: not valid java name */
    public static final void m2214bind$lambda2$lambda1(DescriptiveShowcaseBinder this$0, SeeMoreItemElement it, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        DispatcherContext dispatcherContext = this$0.getDispatcherContext();
        if (dispatcherContext == null) {
            return;
        }
        List<Method> listOnItemSelected = it.onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "it.onItemSelected()");
        SkyFireKt.dispatch$default(dispatcherContext, listOnItemSelected, (String) null, 2, (Object) null);
    }

    private final void reset(SectionWidget view) {
        view.setMoreButtonOnClickListener(null);
        view.setTitle("");
        view.setSubtitle("");
        SectionWidget.setMoreButtonText$default(view, "", false, 2, null);
        view.setMoreButtonOnClickListener(null);
        RecyclerView recyclerView = view.getRecyclerView();
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        HorizontalItemAdapter horizontalItemAdapter = adapter instanceof HorizontalItemAdapter ? (HorizontalItemAdapter) adapter : null;
        if (horizontalItemAdapter != null) {
            horizontalItemAdapter.clear();
        }
        view.setInitialMargins();
    }

    private final void bindChildren(SectionWidget view, List<? extends HorizontalItemElement> items) {
        HorizontalItemAdapter horizontalItemAdapter = new HorizontalItemAdapter(getBindingProvider());
        horizontalItemAdapter.addItems(items);
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView == null) {
            return;
        }
        recyclerView.setAdapter(horizontalItemAdapter);
    }

    private final void bindStyle(SectionWidget view, DescriptiveShowcaseElement element) {
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        SectionWidget.setTextMargins$default(view, null, null, null, Integer.valueOf(displayingHeaderText(element) ? iIntValue : 0), 7, null);
        SectionWidget.setMoreButtonMargins$default(view, null, null, null, Integer.valueOf(displayingMoreButton(element) ? iIntValue : 0), 7, null);
    }

    private final boolean displayingHeaderText(DescriptiveShowcaseElement element) {
        return (TextUtils.isEmpty(element.primaryHeader()) && TextUtils.isEmpty(element.secondaryHeader())) ? false : true;
    }

    private final boolean displayingMoreButton(DescriptiveShowcaseElement element) {
        return !TextUtils.isEmpty(element.seeMore() == null ? null : r1.text());
    }
}
