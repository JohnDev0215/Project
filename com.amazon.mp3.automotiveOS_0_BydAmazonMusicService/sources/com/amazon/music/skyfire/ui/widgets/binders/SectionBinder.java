package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.SectionElement;
import android.content.Context;
import android.view.ViewGroup;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.views.SectionView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.ktx.TextViewKt;
import com.amazon.ui.foundations.styles.GridSize;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SectionBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/SectionBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/SectionElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/SectionView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SectionBinder extends ElementBinder<SectionElement, SectionView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public SectionView createView(ViewGroup parent) {
        return new SectionView(getContext());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(SectionElement element, SectionView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        StyleSheet styleSheet = getStyleSheet();
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        if (gridSize != null) {
            int marginSpace = gridSize.getMarginSpace() + gridSize.getColumnSpace();
            view.setPadding(marginSpace, view.getPaddingTop(), marginSpace, view.getPaddingBottom());
        }
        ViewKt.applyFontStyle(view.getPrimaryHeader(), getStyleSheet(), FontStyleKey.HEADLINE_4);
        ViewKt.applyFontStyle(view.getSecondaryHeader(), getStyleSheet(), FontStyleKey.INDEX);
        view.getPrimaryHeader().setText(element.primaryHeader());
        TextViewKt.collapseIfEmpty(view.getPrimaryHeader());
        view.getSecondaryHeader().setText(element.secondaryHeader());
        TextViewKt.collapseIfEmpty(view.getSecondaryHeader());
        view.getItemContainer().removeAllViews();
        List<Element> listItems = element.items();
        Intrinsics.checkNotNullExpressionValue(listItems, "element.items()");
        for (Element e : listItems) {
            ElementBindingProvider bindingProvider = getBindingProvider();
            Intrinsics.checkNotNullExpressionValue(e, "e");
            bindingProvider.attachView(e, view.getItemContainer());
        }
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }
}
