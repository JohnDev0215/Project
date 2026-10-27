package com.amazon.mp3.catalog.fragment.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultRecyclerViewProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 &2\u00020\u0001:\u0001&B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0016\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0016J0\u0010\u0013\u001a\u00020\u000e2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J4\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0014\u0010\u001c\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001d0\u0010H\u0016J&\u0010\u001e\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J&\u0010#\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u0016H\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/adapter/DefaultRecyclerViewProvider;", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseRecyclerViewProvider;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "itemTouchHelper", "Landroidx/recyclerview/widget/ItemTouchHelper;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;Landroidx/recyclerview/widget/ItemTouchHelper;)V", "adapter", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "addItemsToAdapter", "", "models", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "cleanupAdapterProvider", "createRootView", "baseViewModels", "tileType", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerViewContainer", "Landroid/view/ViewGroup;", "setAdapter", "binders", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "updateAdapter", "diffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "detectMoves", "", "updateAdapterForRange", "positionStart", "itemCount", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultRecyclerViewProvider implements BaseRecyclerViewProvider {
    private UniversalAdapter adapter;
    private final BaseViewModelAdapterProvider adapterProvider;
    private final Context context;
    private ItemTouchHelper itemTouchHelper;
    private final StyleSheet styleSheet;

    public DefaultRecyclerViewProvider(Context context, StyleSheet styleSheet, BaseViewModelAdapterProvider adapterProvider, ItemTouchHelper itemTouchHelper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        this.context = context;
        this.styleSheet = styleSheet;
        this.adapterProvider = adapterProvider;
        this.itemTouchHelper = itemTouchHelper;
    }

    @Override // com.amazon.mp3.catalog.fragment.adapter.BaseRecyclerViewProvider
    public void createRootView(List<? extends BaseViewModel> baseViewModels, int tileType, RecyclerView recyclerView, ViewGroup recyclerViewContainer) {
        GridSize gridSize;
        Intrinsics.checkNotNullParameter(baseViewModels, "baseViewModels");
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        GridLayoutUtils.setLayoutManager$default(new GridLayoutUtils(this.context, this.styleSheet), recyclerView, tileType, null, false, true, null, null, 108, null);
        if ((recyclerView.getLayoutManager() instanceof GridLayoutManager) && (gridSize = this.styleSheet.getGridSize()) != null) {
            LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
            if (recyclerViewContainer == null) {
                ViewParent parent = recyclerView.getParent();
                if (parent == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
                }
                recyclerViewContainer = (ViewGroup) parent;
            }
            layoutParamUtils.setLayoutMargins(recyclerViewContainer, -1, -1, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : Integer.valueOf(gridSize.getMarginSpace() + (gridSize.getColumnSpace() / 2)), (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : Integer.valueOf(gridSize.getMarginSpace() + (gridSize.getColumnSpace() / 2)), (240 & 128) != 0 ? null : null);
        }
        ItemTouchHelper itemTouchHelper = this.itemTouchHelper;
        if (itemTouchHelper == null) {
            return;
        }
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override // com.amazon.mp3.catalog.fragment.adapter.BaseRecyclerViewProvider
    public void setAdapter(RecyclerView recyclerView, List<? extends BaseViewModel> models, List<? extends UniversalBinder<?, ?>> binders) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(models, "models");
        Intrinsics.checkNotNullParameter(binders, "binders");
        BaseViewModelAdapterProvider baseViewModelAdapterProvider = this.adapterProvider;
        Object[] array = binders.toArray(new UniversalBinder[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        UniversalBinder[] universalBinderArr = (UniversalBinder[]) array;
        UniversalAdapter adapter = baseViewModelAdapterProvider.getAdapter(models, (UniversalBinder[]) Arrays.copyOf(universalBinderArr, universalBinderArr.length));
        this.adapter = adapter;
        if (adapter != null) {
            adapter.setItemTouchHelper(this.itemTouchHelper);
        }
        recyclerView.setAdapter(this.adapter);
    }

    @Override // com.amazon.mp3.catalog.fragment.adapter.BaseRecyclerViewProvider
    public void addItemsToAdapter(List<? extends BaseViewModel> models) {
        Intrinsics.checkNotNullParameter(models, "models");
        UniversalAdapter universalAdapter = this.adapter;
        if (universalAdapter == null) {
            return;
        }
        BaseViewModelAdapterProvider.addItems$default(this.adapterProvider, universalAdapter, models, null, 4, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.adapter.BaseRecyclerViewProvider
    public void updateAdapter(List<? extends BaseViewModel> models, BaseViewsDiffUtilFactory diffUtilFactory, boolean detectMoves) {
        Intrinsics.checkNotNullParameter(models, "models");
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
        UniversalAdapter universalAdapter = this.adapter;
        if (universalAdapter == null) {
            return;
        }
        List<Object> items = universalAdapter.getItems();
        ArrayList arrayList = new ArrayList();
        for (Object obj : items) {
            if (obj instanceof BaseViewModel) {
                arrayList.add(obj);
            }
        }
        this.adapterProvider.updateItems(universalAdapter, models, diffUtilFactory.getBaseViewsDiffUtil(arrayList, models), detectMoves);
    }

    @Override // com.amazon.mp3.catalog.fragment.adapter.BaseRecyclerViewProvider
    public void updateAdapterForRange(List<? extends BaseViewModel> models, int positionStart, int itemCount) {
        Intrinsics.checkNotNullParameter(models, "models");
        UniversalAdapter universalAdapter = this.adapter;
        if (universalAdapter == null) {
            return;
        }
        this.adapterProvider.updateItemsInRange(universalAdapter, models, positionStart, itemCount);
    }

    @Override // com.amazon.mp3.catalog.fragment.adapter.BaseRecyclerViewProvider
    public void cleanupAdapterProvider() {
        this.adapterProvider.onCleanup();
    }
}
