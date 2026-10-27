package com.amazon.music.views.library.recyclerview.adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.binders.BaseStateChangeBinder;
import com.amazon.music.views.library.binders.CarModeButtonNavigatorBinder;
import com.amazon.music.views.library.binders.CastingListItemHorizontalTileBinder;
import com.amazon.music.views.library.binders.FollowTileBinder;
import com.amazon.music.views.library.binders.GroupBinder;
import com.amazon.music.views.library.binders.HorizontalTileBinder;
import com.amazon.music.views.library.binders.PodcastHorizontalTileBinder;
import com.amazon.music.views.library.binders.PodcastTileBinder;
import com.amazon.music.views.library.binders.SimpleHorizontalTileBinder;
import com.amazon.music.views.library.binders.SimpleShovelerBinder;
import com.amazon.music.views.library.binders.SimpleVerticalTileBinder;
import com.amazon.music.views.library.binders.ThumbnailCellBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.binders.VerticalTileBinder;
import com.amazon.music.views.library.models.CarModeButtonNavigatorModel;
import com.amazon.music.views.library.models.CastingListItemHorizontalTileModel;
import com.amazon.music.views.library.models.FollowTileModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.PodcastHorizontalTileModel;
import com.amazon.music.views.library.models.PodcastTileModel;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.models.SimpleShovelerModel;
import com.amazon.music.views.library.models.SimpleVerticalTileModel;
import com.amazon.music.views.library.models.ThumbnailCellModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.OnListItemRenderedListener;
import com.amazon.music.views.library.recyclerview.DraggableItemView;
import com.amazon.music.views.library.recyclerview.SimpleItemTouchHelperAdapter;
import com.amazon.music.views.library.views.CarModeButtonNavigatorView;
import com.amazon.music.views.library.views.CastingListItemHorizontalTileView;
import com.amazon.music.views.library.views.FollowTileView;
import com.amazon.music.views.library.views.HorizontalTileView;
import com.amazon.music.views.library.views.PodcastHorizontalTileView;
import com.amazon.music.views.library.views.PodcastTileView;
import com.amazon.music.views.library.views.ScopedView;
import com.amazon.music.views.library.views.SimpleHorizontalTileView;
import com.amazon.music.views.library.views.SimpleSectionWidget;
import com.amazon.music.views.library.views.SimpleVerticalTileView;
import com.amazon.music.views.library.views.ThumbnailCellView;
import com.amazon.music.views.library.views.VerticalTileView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: UniversalAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001uB+\u0012\"\u0010\u0011\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000e0r\"\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000e¢\u0006\u0004\bs\u0010tJ,\u0010\u000b\u001a\u00020\n2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0002H\u0002J\u001a\u0010\u000f\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002J\u001e\u0010\u0012\u001a\u00020\n2\u0014\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000e0\u0010H\u0002J\b\u0010\u0013\u001a\u00020\nH\u0002J\u001a\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0002J\u001c\u0010\u001a\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\fH\u0002J\u0016\u0010\u001c\u001a\u00020\n2\f\b\u0002\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0002J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u0015H\u0002J4\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00102\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00102\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 2\b\b\u0002\u0010#\u001a\u00020\"J0\u0010'\u001a\u00020\n2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 J\u001a\u0010,\u001a\u00020\n2\b\u0010)\u001a\u0004\u0018\u00010(2\b\u0010+\u001a\u0004\u0018\u00010*J\u0014\u0010-\u001a\u00020\n2\f\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0010J\u0016\u00100\u001a\u00020\"2\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\fJ\u0006\u00101\u001a\u00020\nJ\u000e\u00101\u001a\u00020\n2\u0006\u00102\u001a\u00020\"J\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010J\u0006\u00104\u001a\u00020\nJ\u000e\u00106\u001a\u00020\n2\u0006\u00105\u001a\u00020\"J\u0016\u00109\u001a\u00020\n2\u000e\u00108\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u001507J\u000e\u0010;\u001a\u00020\n2\u0006\u0010:\u001a\u00020\"J\u000e\u0010<\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020\u0015J\u0010\u0010=\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0016J\u0018\u0010A\u001a\u00020\u00022\u0006\u0010?\u001a\u00020>2\u0006\u0010@\u001a\u00020\fH\u0016J&\u0010D\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\f2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00150BH\u0016J\u0018\u0010D\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\fH\u0016J\u0010\u0010E\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\b\u0010F\u001a\u00020\fH\u0016J\u0018\u0010G\u001a\u00020\"2\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\fH\u0016J\u0018\u0010H\u001a\u00020\n2\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\fH\u0016J\u001f\u0010K\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\fH\u0000¢\u0006\u0004\bI\u0010JJ\u000e\u0010M\u001a\u00020\n2\u0006\u0010L\u001a\u00020\fR$\u0010O\u001a\u0004\u0018\u00010N8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR$\u0010V\u001a\u0004\u0018\u00010U8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bV\u0010W\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\u0014\u0010]\u001a\u00020\\8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b]\u0010^R,\u0010a\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030`\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000e0_8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010bR\u001e\u0010d\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030`0c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010eR\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00150c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010eR \u0010g\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\f0f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bg\u0010hR\u001a\u0010j\u001a\b\u0012\u0004\u0012\u00020i0c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bj\u0010eR\u0016\u00106\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u0010kR\u0016\u0010;\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010kR \u0010l\u001a\f\u0012\u0006\b\u0000\u0012\u00020\u0015\u0018\u0001078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bl\u0010mR\u0018\u0010o\u001a\u0004\u0018\u00010n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bo\u0010pR\u0016\u0010L\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bL\u0010q¨\u0006v"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalViewHolder;", "Lcom/amazon/music/views/library/recyclerview/SimpleItemTouchHelperAdapter;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Landroid/view/View;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "binder", "model", "holder", "", "handlePayloads", "", "itemViewType", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getBinderForItemViewType", "", "binders", "buildBinders", "notifyDataSetChangedOnUiThread", "position", "", "payload", "notifyItemChangedOnUiThread", "from", "to", "updateItemsIndexMap", ContextMappingConstants.ITEMS, "removeItemsInItemsIndexMap", "item", "getItemIndex", "newItems", "Landroidx/recyclerview/widget/DiffUtil$DiffResult;", "diffResult", "", "filterDupes", "addItems", "startIndex", "changedCount", "updateItemsInRange", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "contentViewedListener", "", "containerViewId", "initContentViewReporting", "removeItems", "fromPosition", "toPosition", "moveItem", "clear", "shouldNotifyDataChanged", "getItems", "sortItems", "value", "allowRebind", "Ljava/util/Comparator;", "comparator", "setComparator", "sort", "sortOnAdd", "isItemTypeSupported", "getItemViewType", "Landroid/view/ViewGroup;", "parent", "viewType", "onCreateViewHolder", "", "payloads", "onBindViewHolder", "onViewRecycled", "getItemCount", "onItemMove", "onDroppedWithItemMoved", "notifyModelChanged$DMMViewLibrary_release", "(Ljava/lang/Object;I)V", "notifyModelChanged", "tileViewWidth", "setTileViewWidth", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter$ItemMovedListener;", "itemMovedListener", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter$ItemMovedListener;", "getItemMovedListener", "()Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter$ItemMovedListener;", "setItemMovedListener", "(Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter$ItemMovedListener;)V", "Landroidx/recyclerview/widget/ItemTouchHelper;", "itemTouchHelper", "Landroidx/recyclerview/widget/ItemTouchHelper;", "getItemTouchHelper", "()Landroidx/recyclerview/widget/ItemTouchHelper;", "setItemTouchHelper", "(Landroidx/recyclerview/widget/ItemTouchHelper;)V", "Landroid/os/Handler;", "handler", "Landroid/os/Handler;", "Ljava/util/HashMap;", "Lkotlin/reflect/KClass;", "BINDERS", "Ljava/util/HashMap;", "Ljava/util/ArrayList;", "modelClasses", "Ljava/util/ArrayList;", "", "itemsIndexMap", "Ljava/util/Map;", "Lcom/amazon/music/views/library/recyclerview/adapters/OnFocusChangedListener;", "focusListeners", "Z", "itemComparator", "Ljava/util/Comparator;", "Lcom/amazon/music/views/library/providers/OnListItemRenderedListener;", "onListItemRenderedListener", "Lcom/amazon/music/views/library/providers/OnListItemRenderedListener;", "I", "", "<init>", "([Lcom/amazon/music/views/library/binders/UniversalBinder;)V", "ItemMovedListener", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public class UniversalAdapter extends RecyclerView.Adapter<UniversalViewHolder> implements SimpleItemTouchHelperAdapter {
    private final HashMap<KClass<?>, UniversalBinder<?, ?>> BINDERS;
    private boolean allowRebind;
    private final ArrayList<OnFocusChangedListener> focusListeners;
    private final Handler handler;
    private Comparator<? super Object> itemComparator;
    private ItemMovedListener itemMovedListener;
    private ItemTouchHelper itemTouchHelper;
    private final ArrayList<Object> items;
    private final Map<Object, Integer> itemsIndexMap;
    private final ArrayList<KClass<?>> modelClasses;
    private OnListItemRenderedListener onListItemRenderedListener;
    private boolean sortOnAdd;
    private int tileViewWidth;

    /* JADX INFO: compiled from: UniversalAdapter.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H&¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter$ItemMovedListener;", "", "onItemMoved", "", "fromIndex", "", "toIndex", "shouldMoveItem", "", "model", "targetModel", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface ItemMovedListener {
        void onItemMoved(int fromIndex, int toIndex);

        boolean shouldMoveItem(Object model, Object targetModel);
    }

    public UniversalAdapter(UniversalBinder<?, ?>... binders) {
        Intrinsics.checkNotNullParameter(binders, "binders");
        this.handler = new Handler(Looper.getMainLooper());
        this.BINDERS = new HashMap<>();
        this.modelClasses = new ArrayList<>();
        this.items = new ArrayList<>();
        this.itemsIndexMap = new LinkedHashMap();
        this.focusListeners = new ArrayList<>();
        this.tileViewWidth = -1;
        List<? extends UniversalBinder<?, ?>> listAsList = Arrays.asList(Arrays.copyOf(binders, binders.length));
        Intrinsics.checkNotNullExpressionValue(listAsList, "asList<UniversalBinder<*, *>>(*binders)");
        buildBinders(listAsList);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((UniversalViewHolder) viewHolder, i, (List<Object>) list);
    }

    public final void setItemMovedListener(ItemMovedListener itemMovedListener) {
        this.itemMovedListener = itemMovedListener;
    }

    public final ItemTouchHelper getItemTouchHelper() {
        return this.itemTouchHelper;
    }

    public final void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    public static /* synthetic */ List addItems$default(UniversalAdapter universalAdapter, List list, DiffUtil.DiffResult diffResult, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addItems");
        }
        if ((i & 2) != 0) {
            diffResult = null;
        }
        if ((i & 4) != 0) {
            z = true;
        }
        return universalAdapter.addItems(list, diffResult, z);
    }

    public static /* synthetic */ void updateItemsInRange$default(UniversalAdapter universalAdapter, List list, int i, int i2, DiffUtil.DiffResult diffResult, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateItemsInRange");
        }
        if ((i3 & 8) != 0) {
            diffResult = null;
        }
        universalAdapter.updateItemsInRange(list, i, i2, diffResult);
    }

    public final void updateItemsInRange(List<? extends Object> newItems, int startIndex, int changedCount, DiffUtil.DiffResult diffResult) {
        Unit unit;
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        this.items.addAll(newItems);
        if (this.sortOnAdd) {
            sortItems();
        }
        if (diffResult == null) {
            unit = null;
        } else {
            diffResult.dispatchUpdatesTo(this);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            notifyItemRangeChanged(startIndex, changedCount);
        }
        if (this.sortOnAdd) {
            return;
        }
        updateItemsIndexMap(startIndex, changedCount);
    }

    public final void initContentViewReporting(ContentViewedListener contentViewedListener, String containerViewId) {
        if (contentViewedListener == null || containerViewId == null) {
            return;
        }
        this.onListItemRenderedListener = new ContentViewedProvider(contentViewedListener, containerViewId);
    }

    public final void removeItems(List<?> items) {
        if (items == null || !(!items.isEmpty())) {
            return;
        }
        this.items.removeAll(items);
        removeItemsInItemsIndexMap(items);
        updateItemsIndexMap$default(this, 0, 0, 3, null);
        notifyDataSetChangedOnUiThread();
    }

    public final boolean moveItem(final int fromPosition, final int toPosition) {
        if (fromPosition >= 0 && fromPosition < this.items.size()) {
            if (toPosition >= 0 && toPosition < this.items.size()) {
                if (toPosition > fromPosition) {
                    int i = fromPosition;
                    while (i < toPosition) {
                        int i2 = i + 1;
                        Collections.swap(this.items, i, i2);
                        i = i2;
                    }
                    updateItemsIndexMap(fromPosition, toPosition + 1);
                } else {
                    int i3 = toPosition + 1;
                    if (i3 <= fromPosition) {
                        int i4 = fromPosition;
                        while (true) {
                            int i5 = i4 - 1;
                            Collections.swap(this.items, i4, i4 - 1);
                            if (i4 == i3) {
                                break;
                            }
                            i4 = i5;
                        }
                    }
                    updateItemsIndexMap(toPosition, fromPosition + 1);
                }
                this.handler.post(new Runnable() { // from class: com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        UniversalAdapter.m2382moveItem$lambda5(this.f$0, fromPosition, toPosition);
                    }
                });
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: moveItem$lambda-5, reason: not valid java name */
    public static final void m2382moveItem$lambda5(UniversalAdapter this$0, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.notifyItemMoved(i, i2);
    }

    public final void clear() {
        clear(true);
    }

    public final void clear(boolean shouldNotifyDataChanged) {
        removeItemsInItemsIndexMap$default(this, null, 1, null);
        this.items.clear();
        if (shouldNotifyDataChanged) {
            notifyDataSetChanged();
        }
    }

    public final List<Object> getItems() {
        return this.items;
    }

    public final void sortItems() {
        Comparator<? super Object> comparator = this.itemComparator;
        if (comparator != null) {
            Collections.sort(this.items, comparator);
            updateItemsIndexMap$default(this, 0, 0, 3, null);
            return;
        }
        throw new NullPointerException("Comparator cannot be null - call setComparator first!");
    }

    public final void allowRebind(boolean value) {
        this.allowRebind = value;
    }

    public final void setComparator(Comparator<? super Object> comparator) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        this.itemComparator = comparator;
    }

    public final void sortOnAdd(boolean sort) {
        this.sortOnAdd = sort;
    }

    public final boolean isItemTypeSupported(Object item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (this.BINDERS.containsKey(Reflection.getOrCreateKotlinClass(item.getClass()))) {
            return true;
        }
        Collection<UniversalBinder<?, ?>> collectionValues = this.BINDERS.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "BINDERS.values");
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            UniversalBinder universalBinder = (UniversalBinder) it.next();
            if ((universalBinder instanceof GroupBinder) && CollectionsKt___CollectionsKt.contains(((GroupBinder) universalBinder).getModelClassList(), Reflection.getOrCreateKotlinClass(item.getClass()))) {
                return true;
            }
        }
        StringBuilder sb = new StringBuilder("Supported binders");
        Collection<UniversalBinder<?, ?>> collectionValues2 = this.BINDERS.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues2, "BINDERS.values");
        Iterator<T> it2 = collectionValues2.iterator();
        while (it2.hasNext()) {
            sb.append(Intrinsics.stringPlus(" - ", ((UniversalBinder) it2.next()).getClass().getSimpleName()));
        }
        LoggerFactory.getLogger((Class<?>) UniversalAdapter.class).error("Model type " + ((Object) item.getClass().getSimpleName()) + " is not supported. " + ((Object) sb));
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        Object obj = this.items.get(position);
        Intrinsics.checkNotNullExpressionValue(obj, "items[position]");
        int size = this.modelClasses.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            if (Intrinsics.areEqual(this.modelClasses.get(i), Reflection.getOrCreateKotlinClass(obj.getClass()))) {
                return i;
            }
            UniversalBinder<?, ?> universalBinder = this.BINDERS.get(this.modelClasses.get(i));
            if ((universalBinder instanceof GroupBinder) && CollectionsKt___CollectionsKt.contains(((GroupBinder) universalBinder).getModelClassList(), Reflection.getOrCreateKotlinClass(obj.getClass()))) {
                return i;
            }
            i = i2;
        }
        return -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public UniversalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        UniversalBinder<?, ?> binderForItemViewType = getBinderForItemViewType(viewType);
        if (binderForItemViewType != null) {
            Context context = parent.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "parent.context");
            return new UniversalViewHolder(binderForItemViewType.createView(context));
        }
        throw new RuntimeException("Adapter doesn't know how to inflate and bind " + this.modelClasses.get(viewType) + ". Register a UniversalBinder.");
    }

    public void onBindViewHolder(UniversalViewHolder holder, int position, List<Object> payloads) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        Object obj = this.items.get(position);
        Intrinsics.checkNotNullExpressionValue(obj, "items[position]");
        UniversalBinder<?, ?> binderForItemViewType = getBinderForItemViewType(getItemViewType(position));
        if (binderForItemViewType == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.binders.UniversalBinder<*, *>");
        }
        BaseViewModel baseViewModel = (BaseViewModel) obj;
        holder.setModel$DMMViewLibrary_release(baseViewModel);
        BaseStateChangeBinder<View, BaseViewModel> baseStateChangeBinder = binderForItemViewType instanceof BaseStateChangeBinder ? (BaseStateChangeBinder) binderForItemViewType : null;
        if (baseStateChangeBinder == null || !(!payloads.isEmpty())) {
            super.onBindViewHolder(holder, position, payloads);
            return;
        }
        for (Object obj2 : payloads) {
            if (Intrinsics.areEqual(obj2, (Object) (-1))) {
                handlePayloads(baseStateChangeBinder, baseViewModel, holder);
            } else {
                Set<Integer> payloads$DMMViewLibrary_release = baseViewModel.getPayloads$DMMViewLibrary_release();
                Intrinsics.checkNotNullExpressionValue(payloads$DMMViewLibrary_release, "model.payloads");
                payloads$DMMViewLibrary_release.remove(obj2);
                View view = holder.itemView;
                Intrinsics.checkNotNullExpressionValue(view, "holder.itemView");
                baseStateChangeBinder.handleStateChange(view, baseViewModel, obj2);
            }
        }
    }

    private final void handlePayloads(BaseStateChangeBinder<View, BaseViewModel> binder, BaseViewModel model, UniversalViewHolder holder) {
        Set<Integer> payloads$DMMViewLibrary_release = model.getPayloads$DMMViewLibrary_release();
        Intrinsics.checkNotNullExpressionValue(payloads$DMMViewLibrary_release, "model.payloads");
        List<Integer> list = CollectionsKt___CollectionsKt.toList(payloads$DMMViewLibrary_release);
        model.getPayloads$DMMViewLibrary_release().removeAll(list);
        for (Integer it : list) {
            View view = holder.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "holder.itemView");
            Intrinsics.checkNotNullExpressionValue(it, "it");
            binder.handleStateChange(view, model, it);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final UniversalViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final Object obj = this.items.get(position);
        Intrinsics.checkNotNullExpressionValue(obj, "items[position]");
        UniversalBinder<?, ?> binderForItemViewType = getBinderForItemViewType(getItemViewType(position));
        if (binderForItemViewType == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.binders.UniversalBinder<*, *>");
        }
        if (this.allowRebind || obj != holder.getModel()) {
            KeyEvent.Callback callback = holder.itemView;
            ScopedView scopedView = callback instanceof ScopedView ? (ScopedView) callback : null;
            if (scopedView != null) {
                scopedView.clear();
            }
            BaseViewModel baseViewModel = (BaseViewModel) obj;
            holder.setModel$DMMViewLibrary_release(baseViewModel);
            holder.itemView.setTag(holder);
            if (binderForItemViewType instanceof FollowTileBinder) {
                ((FollowTileBinder) binderForItemViewType).bind((FollowTileView) holder.itemView, (FollowTileModel) obj, this.tileViewWidth);
            } else if (binderForItemViewType instanceof VerticalTileBinder) {
                ((VerticalTileBinder) binderForItemViewType).bind((VerticalTileView) holder.itemView, (VerticalTileModel) obj, this.tileViewWidth);
            } else if (binderForItemViewType instanceof PodcastTileBinder) {
                ((PodcastTileBinder) binderForItemViewType).bind((PodcastTileView) holder.itemView, (PodcastTileModel) obj);
            } else if (binderForItemViewType instanceof PodcastHorizontalTileBinder) {
                ((PodcastHorizontalTileBinder) binderForItemViewType).bind((PodcastHorizontalTileView) holder.itemView, (PodcastHorizontalTileModel) obj, this.tileViewWidth);
            } else if (binderForItemViewType instanceof HorizontalTileBinder) {
                ((HorizontalTileBinder) binderForItemViewType).bind((HorizontalTileView) holder.itemView, (HorizontalTileModel) obj, this.tileViewWidth);
            } else if (binderForItemViewType instanceof ThumbnailCellBinder) {
                ((ThumbnailCellBinder) binderForItemViewType).bind((ThumbnailCellView) holder.itemView, (ThumbnailCellModel) obj, this.tileViewWidth);
            } else if (binderForItemViewType instanceof SimpleHorizontalTileBinder) {
                ((SimpleHorizontalTileBinder) binderForItemViewType).bind((SimpleHorizontalTileView) holder.itemView, (SimpleHorizontalTileModel) obj, position);
            } else if (binderForItemViewType instanceof SimpleVerticalTileBinder) {
                ((SimpleVerticalTileBinder) binderForItemViewType).bind((SimpleVerticalTileView) holder.itemView, (SimpleVerticalTileModel) obj, this.tileViewWidth);
            } else if (binderForItemViewType instanceof CastingListItemHorizontalTileBinder) {
                ((CastingListItemHorizontalTileBinder) binderForItemViewType).bind((CastingListItemHorizontalTileView) holder.itemView, (CastingListItemHorizontalTileModel) obj, this.tileViewWidth);
            } else if (binderForItemViewType instanceof SimpleShovelerBinder) {
                ((SimpleShovelerBinder) binderForItemViewType).bind((SimpleSectionWidget) holder.itemView, (SimpleShovelerModel) obj);
            } else if (binderForItemViewType instanceof CarModeButtonNavigatorBinder) {
                ((CarModeButtonNavigatorBinder) binderForItemViewType).bind((CarModeButtonNavigatorView) holder.itemView, (CarModeButtonNavigatorModel) obj);
            } else {
                View view = holder.itemView;
                Intrinsics.checkNotNullExpressionValue(view, "holder.itemView");
                binderForItemViewType.bind(view, baseViewModel);
            }
            holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter$$ExternalSyntheticLambda2
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    UniversalAdapter.m2385onBindViewHolder$lambda11(this.f$0, obj, view2, z);
                }
            });
            OnListItemRenderedListener onListItemRenderedListener = this.onListItemRenderedListener;
            if (onListItemRenderedListener != null) {
                onListItemRenderedListener.onListItemRendered(position);
            }
            KeyEvent.Callback callback2 = holder.itemView;
            if (callback2 instanceof DraggableItemView) {
                DraggableItemView draggableItemView = (DraggableItemView) callback2;
                if (draggableItemView.isDraggingEnabled()) {
                    draggableItemView.setOnDraggedListener(new Function0<Unit>() { // from class: com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter$onBindViewHolder$3$1
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
                            ItemTouchHelper itemTouchHelper = this.this$0.getItemTouchHelper();
                            if (itemTouchHelper == null) {
                                return;
                            }
                            itemTouchHelper.startDrag(holder);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindViewHolder$lambda-11, reason: not valid java name */
    public static final void m2385onBindViewHolder$lambda11(UniversalAdapter this$0, Object model, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        for (OnFocusChangedListener onFocusChangedListener : this$0.focusListeners) {
            Intrinsics.checkNotNullExpressionValue(view, "view");
            onFocusChangedListener.onFocusChanged(view, (BaseViewModel) model, z);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(UniversalViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        super.onViewRecycled(holder);
        KeyEvent.Callback callback = holder.itemView;
        ScopedView scopedView = callback instanceof ScopedView ? (ScopedView) callback : null;
        if (scopedView == null) {
            return;
        }
        scopedView.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // com.amazon.music.views.library.recyclerview.SimpleItemTouchHelperAdapter
    public boolean onItemMove(int fromPosition, int toPosition) {
        Object orNull = CollectionsKt___CollectionsKt.getOrNull(this.items, fromPosition);
        Object orNull2 = CollectionsKt___CollectionsKt.getOrNull(this.items, toPosition);
        if (orNull == null || orNull2 == null) {
            return false;
        }
        ItemMovedListener itemMovedListener = this.itemMovedListener;
        return !(itemMovedListener != null && !itemMovedListener.shouldMoveItem(orNull, orNull2)) && moveItem(fromPosition, toPosition);
    }

    @Override // com.amazon.music.views.library.recyclerview.SimpleItemTouchHelperAdapter
    public void onDroppedWithItemMoved(int fromPosition, int toPosition) {
        ItemMovedListener itemMovedListener = this.itemMovedListener;
        if (itemMovedListener == null) {
            return;
        }
        itemMovedListener.onItemMoved(fromPosition, toPosition);
    }

    private final UniversalBinder<?, ?> getBinderForItemViewType(int itemViewType) {
        KClass<?> kClass = this.modelClasses.get(itemViewType);
        Intrinsics.checkNotNullExpressionValue(kClass, "modelClasses[itemViewType]");
        return this.BINDERS.get(kClass);
    }

    private final void buildBinders(List<? extends UniversalBinder<?, ?>> binders) {
        for (UniversalBinder<?, ?> universalBinder : binders) {
            this.modelClasses.add((KClass<?>) universalBinder.getModelClass());
            this.BINDERS.put((KClass<?>) universalBinder.getModelClass(), universalBinder);
        }
    }

    private final void notifyDataSetChangedOnUiThread() {
        this.handler.post(new Runnable() { // from class: com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                UniversalAdapter.m2383notifyDataSetChangedOnUiThread$lambda16(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: notifyDataSetChangedOnUiThread$lambda-16, reason: not valid java name */
    public static final void m2383notifyDataSetChangedOnUiThread$lambda16(UniversalAdapter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.notifyDataSetChanged();
    }

    public final void notifyModelChanged$DMMViewLibrary_release(Object item, int payload) {
        Intrinsics.checkNotNullParameter(item, "item");
        int itemIndex = item instanceof VisualRowItemModel ? getItemIndex(item) : this.items.indexOf(item);
        if (itemIndex >= 0) {
            notifyItemChangedOnUiThread(itemIndex, Integer.valueOf(payload));
        }
    }

    private final void notifyItemChangedOnUiThread(final int position, final Object payload) {
        this.handler.post(new Runnable() { // from class: com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                UniversalAdapter.m2384notifyItemChangedOnUiThread$lambda18(this.f$0, position, payload);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: notifyItemChangedOnUiThread$lambda-18, reason: not valid java name */
    public static final void m2384notifyItemChangedOnUiThread$lambda18(UniversalAdapter this$0, int i, Object obj) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.notifyItemChanged(i, obj);
    }

    public final void setTileViewWidth(int tileViewWidth) {
        this.tileViewWidth = tileViewWidth;
    }

    static /* synthetic */ void updateItemsIndexMap$default(UniversalAdapter universalAdapter, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateItemsIndexMap");
        }
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = universalAdapter.items.size();
        }
        universalAdapter.updateItemsIndexMap(i, i2);
    }

    private final synchronized void updateItemsIndexMap(int from, int to) {
        if (from > to) {
            removeItemsInItemsIndexMap$default(this, null, 1, null);
            updateItemsIndexMap$default(this, 0, 0, 3, null);
            return;
        }
        int iMax = Math.max(0, from);
        int iMin = Math.min(this.items.size(), to);
        while (iMax < iMin) {
            int i = iMax + 1;
            Map<Object, Integer> map = this.itemsIndexMap;
            Object obj = this.items.get(iMax);
            Intrinsics.checkNotNullExpressionValue(obj, "items[i]");
            map.put(obj, Integer.valueOf(iMax));
            iMax = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void removeItemsInItemsIndexMap$default(UniversalAdapter universalAdapter, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeItemsInItemsIndexMap");
        }
        if ((i & 1) != 0) {
            list = universalAdapter.items;
        }
        universalAdapter.removeItemsInItemsIndexMap(list);
    }

    private final synchronized void removeItemsInItemsIndexMap(List<?> items) {
        Iterator<?> it = items.iterator();
        while (it.hasNext()) {
            this.itemsIndexMap.remove(it.next());
        }
    }

    private final int getItemIndex(Object item) {
        Map<Object, Integer> map = this.itemsIndexMap;
        Integer numValueOf = map.get(item);
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(this.items.indexOf(item));
            map.put(item, numValueOf);
        }
        return numValueOf.intValue();
    }

    public final List<Object> addItems(List<? extends Object> newItems, DiffUtil.DiffResult diffResult, boolean filterDupes) {
        if (newItems == null) {
            return null;
        }
        if (filterDupes) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : newItems) {
                if (!this.items.contains(obj) && isItemTypeSupported(obj)) {
                    arrayList.add(obj);
                }
            }
            newItems = arrayList;
        }
        if (!(!newItems.isEmpty())) {
            return null;
        }
        updateItemsInRange(newItems, this.items.size(), newItems.size(), diffResult);
        return newItems;
    }
}
