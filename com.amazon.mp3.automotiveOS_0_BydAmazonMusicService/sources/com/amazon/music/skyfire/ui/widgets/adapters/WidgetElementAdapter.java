package com.amazon.music.skyfire.ui.widgets.adapters;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import Touch.WidgetsInterface.v1_0.VisualListingElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.ktx.LayoutManagerKt;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.widgets.binders.ElementBinder;
import com.amazon.music.skyfire.ui.widgets.binders.TextButtonBinder;
import com.amazon.music.skyfire.ui.widgets.elements.VisualListingRowElement;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.GridStyleKey;
import com.amazon.ui.foundations.styles.GridStyle;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: WidgetElementAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003:\u00017B\u0005¢\u0006\u0002\u0010\u0005J\u001c\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015J\u0014\u0010!\u001a\u00020\"2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015J\u0006\u0010&\u001a\u00020\"J\b\u0010'\u001a\u00020$H\u0016J\u0010\u0010(\u001a\u00020$2\u0006\u0010#\u001a\u00020$H\u0016J\u001c\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0002J\u0018\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020\u00042\u0006\u0010#\u001a\u00020$H\u0016J\u0018\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020$H\u0016J\u000e\u00100\u001a\u00020\"2\u0006\u00101\u001a\u000202J\u000e\u00103\u001a\u00020\"2\u0006\u00101\u001a\u000202J\u0010\u00104\u001a\u00020\"2\u0006\u0010+\u001a\u00020\u0004H\u0016J\u0010\u00105\u001a\u00020\"2\u0006\u0010+\u001a\u00020\u0004H\u0016J\u0014\u00106\u001a\u00020\"2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u00068"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/WidgetElementAdapter;", "T", "LTouch/WidgetsInterface/v1_0/WidgetElement;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/skyfire/ui/widgets/adapters/WidgetElementAdapter$WidgetElementViewHolder;", "()V", "dispatcherContext", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "getDispatcherContext", "()Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "setDispatcherContext", "(Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;)V", "elementBindingProvider", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "getElementBindingProvider", "()Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "setElementBindingProvider", "(Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;)V", ContextMappingConstants.ITEMS, "", "onEndOfList", "", "LCoreInterface/v1_0/Method;", "getOnEndOfList", "()Ljava/util/List;", "setOnEndOfList", "(Ljava/util/List;)V", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setStyleSheet", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "addItems", "", "position", "", "newItems", "clear", "getItemCount", "getItemViewType", "mapItems", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onPause", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onResume", "onViewAttachedToWindow", "onViewDetachedFromWindow", "resetAndNotify", "WidgetElementViewHolder", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WidgetElementAdapter<T extends WidgetElement> extends RecyclerView.Adapter<WidgetElementViewHolder> {
    private DispatcherContext dispatcherContext;
    public ElementBindingProvider elementBindingProvider;
    private final List<T> items = new ArrayList();
    private List<? extends Method> onEndOfList;
    public StyleSheet styleSheet;

    public final ElementBindingProvider getElementBindingProvider() {
        ElementBindingProvider elementBindingProvider = this.elementBindingProvider;
        if (elementBindingProvider != null) {
            return elementBindingProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("elementBindingProvider");
        return null;
    }

    public final void setElementBindingProvider(ElementBindingProvider elementBindingProvider) {
        Intrinsics.checkNotNullParameter(elementBindingProvider, "<set-?>");
        this.elementBindingProvider = elementBindingProvider;
    }

    public final StyleSheet getStyleSheet() {
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet != null) {
            return styleSheet;
        }
        Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
        return null;
    }

    public final void setStyleSheet(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "<set-?>");
        this.styleSheet = styleSheet;
    }

    public final DispatcherContext getDispatcherContext() {
        return this.dispatcherContext;
    }

    public final void setDispatcherContext(DispatcherContext dispatcherContext) {
        this.dispatcherContext = dispatcherContext;
    }

    public final List<Method> getOnEndOfList() {
        return this.onEndOfList;
    }

    public final void setOnEndOfList(List<? extends Method> list) {
        this.onEndOfList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public WidgetElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewCreateView = getElementBindingProvider().createView(viewType, parent);
        viewCreateView.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        return new WidgetElementViewHolder(viewCreateView);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return getElementBindingProvider().getViewType(this.items.get(position).getClass());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(WidgetElementViewHolder holder, int position) {
        DispatcherContext dispatcherContext;
        Intrinsics.checkNotNullParameter(holder, "holder");
        ElementBindingProvider elementBindingProvider = getElementBindingProvider();
        T t = this.items.get(position);
        View view = holder.itemView;
        Intrinsics.checkNotNullExpressionValue(view, "holder.itemView");
        elementBindingProvider.bind(t, view);
        if (this.items.get(position) instanceof TextButtonElement) {
            View view2 = holder.itemView;
            if (view2 instanceof BaseButton) {
                TextButtonBinder.Companion companion = TextButtonBinder.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(view2, "holder.itemView");
                companion.styleTextButtonAsWidget((BaseButton) view2, getStyleSheet());
            }
        }
        if (position == CollectionsKt__CollectionsKt.getLastIndex(this.items)) {
            List<? extends Method> list = this.onEndOfList;
            if (list != null && (dispatcherContext = getDispatcherContext()) != null) {
                SkyFireKt.dispatch$default(dispatcherContext, list, (String) null, 2, (Object) null);
            }
            this.onEndOfList = null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public final void addItems(List<? extends T> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        CollectionsKt__MutableCollectionsKt.addAll(this.items, mapItems(newItems));
    }

    public final void addItems(int position, List<? extends T> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        this.items.addAll(position, mapItems(newItems));
    }

    public final void clear() {
        this.items.clear();
    }

    public final void resetAndNotify(List<? extends T> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        clear();
        addItems(newItems);
        notifyDataSetChanged();
    }

    public final void onPause(RecyclerView recyclerView) {
        IntRange intRangeVisibleItemRange;
        ElementBinder<Element, View> binder;
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager == null || (intRangeVisibleItemRange = LayoutManagerKt.visibleItemRange(linearLayoutManager)) == null) {
            return;
        }
        Iterator<Integer> it = intRangeVisibleItemRange.iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(iNextInt);
            View view = viewHolderFindViewHolderForAdapterPosition == null ? null : viewHolderFindViewHolderForAdapterPosition.itemView;
            if (view != null && (binder = getElementBindingProvider().getBinder(this.items.get(iNextInt))) != null) {
                binder.onDetached(this.items.get(iNextInt), view);
            }
        }
    }

    public final void onResume(RecyclerView recyclerView) {
        IntRange intRangeVisibleItemRange;
        ElementBinder<Element, View> binder;
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager == null || (intRangeVisibleItemRange = LayoutManagerKt.visibleItemRange(linearLayoutManager)) == null) {
            return;
        }
        Iterator<Integer> it = intRangeVisibleItemRange.iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(iNextInt);
            View view = viewHolderFindViewHolderForAdapterPosition == null ? null : viewHolderFindViewHolderForAdapterPosition.itemView;
            if (view != null && (binder = getElementBindingProvider().getBinder(this.items.get(iNextInt))) != null) {
                binder.onAttached(this.items.get(iNextInt), view);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(WidgetElementViewHolder holder) {
        ElementBinder<Element, View> binder;
        Intrinsics.checkNotNullParameter(holder, "holder");
        super.onViewDetachedFromWindow(holder);
        WidgetElement widgetElement = (WidgetElement) CollectionsKt___CollectionsKt.getOrNull(this.items, holder.getAdapterPosition());
        if (widgetElement == null || (binder = getElementBindingProvider().getBinder(widgetElement)) == null) {
            return;
        }
        View view = holder.itemView;
        Intrinsics.checkNotNullExpressionValue(view, "holder.itemView");
        binder.onDetached(widgetElement, view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(WidgetElementViewHolder holder) {
        ElementBinder<Element, View> binder;
        Intrinsics.checkNotNullParameter(holder, "holder");
        super.onViewAttachedToWindow(holder);
        WidgetElement widgetElement = (WidgetElement) CollectionsKt___CollectionsKt.getOrNull(this.items, holder.getAdapterPosition());
        if (widgetElement == null || (binder = getElementBindingProvider().getBinder(widgetElement)) == null) {
            return;
        }
        View view = holder.itemView;
        Intrinsics.checkNotNullExpressionValue(view, "holder.itemView");
        binder.onAttached(widgetElement, view);
    }

    private final List<T> mapItems(List<? extends T> items) {
        Context context = getElementBindingProvider().getContext();
        if (context == null) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        GridStyle gridStyle = getStyleSheet().getGridStyle(GridStyleKey.VERTICAL_ITEM_GRID, context);
        int columnCount = gridStyle == null ? 0 : gridStyle.getColumnCount();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = items.iterator();
        while (it.hasNext()) {
            WidgetElement widgetElement = (WidgetElement) it.next();
            if (widgetElement instanceof VisualListingElement) {
                List<VerticalItemElement> listItems = ((VisualListingElement) widgetElement).items();
                Intrinsics.checkNotNullExpressionValue(listItems, "item.items()");
                List listChunked = CollectionsKt___CollectionsKt.chunked(listItems, columnCount);
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listChunked, 10));
                Iterator it2 = listChunked.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new VisualListingRowElement((List) it2.next()));
                }
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, arrayList2);
            } else {
                arrayList.add(widgetElement);
            }
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: WidgetElementAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/WidgetElementAdapter$WidgetElementViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class WidgetElementViewHolder extends RecyclerView.ViewHolder {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WidgetElementViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
        }
    }
}
