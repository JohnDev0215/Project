package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.SeeMoreItemElement;
import Touch.WidgetsInterface.v1_0.ThumbnailNavigatorItemElement;
import Touch.WidgetsInterface.v1_0.ThumbnailNavigatorWidgetElement;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.adapters.ThumbnailNavigatorItemAdapter;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.views.SectionWidget;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ThumbnailNavigatorBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0012\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0003H\u0002J\u0018\u0010\u0017\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/ThumbnailNavigatorBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/ThumbnailNavigatorWidgetElement;", "Lcom/amazon/music/views/library/views/SectionWidget;", "()V", "gridLayoutUtils", "Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "getGridLayoutUtils", "()Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "gridLayoutUtils$delegate", "Lkotlin/Lazy;", "bind", "", "element", "view", "bindChildren", "bindStyle", "sectionTitle", "", "createView", "parent", "Landroid/view/ViewGroup;", "reset", "showView", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ThumbnailNavigatorBinder extends ElementBinder<ThumbnailNavigatorWidgetElement, SectionWidget> {

    /* JADX INFO: renamed from: gridLayoutUtils$delegate, reason: from kotlin metadata */
    private final Lazy gridLayoutUtils = LazyKt__LazyJVMKt.lazy(new Function0<GridLayoutUtils>() { // from class: com.amazon.music.skyfire.ui.widgets.binders.ThumbnailNavigatorBinder$gridLayoutUtils$2
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
    public void bind(ThumbnailNavigatorWidgetElement element, SectionWidget view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        reset(view);
        bindChildren(view, element);
        String strHeader = element.header();
        if (strHeader == null) {
            strHeader = "";
        }
        view.setTitle(strHeader);
        view.setSubtitle("");
        final SeeMoreItemElement seeMoreItemElementSeeMore = element.seeMore();
        if (seeMoreItemElementSeeMore != null) {
            SectionWidget.setMoreButtonText$default(view, seeMoreItemElementSeeMore.text(), false, 2, null);
            view.setMoreButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.ThumbnailNavigatorBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ThumbnailNavigatorBinder.m2243bind$lambda1$lambda0(this.f$0, seeMoreItemElementSeeMore, view2);
                }
            });
        }
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
        showView(view, element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-1$lambda-0, reason: not valid java name */
    public static final void m2243bind$lambda1$lambda0(ThumbnailNavigatorBinder this$0, SeeMoreItemElement it, View view) {
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

    private final void showView(SectionWidget view, ThumbnailNavigatorWidgetElement element) {
        int size = element.items().size();
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView != null) {
            GridLayoutUtils.setLayoutManager$default(getGridLayoutUtils(), recyclerView, 15, Integer.valueOf(size), false, false, null, null, 96, null);
        }
        String strHeader = element.header();
        if (strHeader == null) {
            strHeader = "";
        }
        bindStyle(view, strHeader);
    }

    private final void bindStyle(SectionWidget view, String sectionTitle) {
        SectionWidget.setMargins$default(view, null, sectionTitle.length() == 0 ? 0 : null, null, null, null, 29, null);
    }

    private final void reset(SectionWidget view) {
        view.setTitle("");
        view.setSubtitle("");
        SectionWidget.setMoreButtonText$default(view, "", false, 2, null);
        view.setMoreButtonOnClickListener(null);
        RecyclerView recyclerView = view.getRecyclerView();
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        ThumbnailNavigatorItemAdapter thumbnailNavigatorItemAdapter = adapter instanceof ThumbnailNavigatorItemAdapter ? (ThumbnailNavigatorItemAdapter) adapter : null;
        if (thumbnailNavigatorItemAdapter != null) {
            thumbnailNavigatorItemAdapter.clear();
        }
        view.setInitialMargins();
        RecyclerView recyclerView2 = view.getRecyclerView();
        if (recyclerView2 == null) {
            return;
        }
        recyclerView2.setPadding(0, 0, 0, 0);
    }

    private final void bindChildren(SectionWidget view, ThumbnailNavigatorWidgetElement element) {
        ThumbnailNavigatorItemAdapter thumbnailNavigatorItemAdapter = new ThumbnailNavigatorItemAdapter(getOwnerId(), getMethodsDispatcher(), getBindingProvider());
        List<ThumbnailNavigatorItemElement> listItems = element.items();
        Intrinsics.checkNotNullExpressionValue(listItems, "element.items()");
        thumbnailNavigatorItemAdapter.addItems(listItems);
        int size = element.items().size();
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView == null) {
            return;
        }
        GridLayoutUtils.setLayoutManager$default(getGridLayoutUtils(), recyclerView, 15, Integer.valueOf(size), true, false, null, null, 96, null);
        recyclerView.setAdapter(thumbnailNavigatorItemAdapter);
    }
}
