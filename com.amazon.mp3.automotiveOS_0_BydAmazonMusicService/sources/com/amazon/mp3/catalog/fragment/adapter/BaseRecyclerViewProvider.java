package com.amazon.mp3.catalog.fragment.adapter;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: BaseRecyclerViewProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\b\u0010\u0007\u001a\u00020\u0003H&J0\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J4\u0010\u0010\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0014\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00120\u0005H&J(\u0010\u0013\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017H&J&\u0010\u0018\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u000bH&¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/adapter/BaseRecyclerViewProvider;", "", "addItemsToAdapter", "", "models", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "cleanupAdapterProvider", "createRootView", "baseViewModels", "tileType", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerViewContainer", "Landroid/view/ViewGroup;", "setAdapter", "binders", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "updateAdapter", "diffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "detectMoves", "", "updateAdapterForRange", "positionStart", "itemCount", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface BaseRecyclerViewProvider {
    void addItemsToAdapter(List<? extends BaseViewModel> models);

    void cleanupAdapterProvider();

    void createRootView(List<? extends BaseViewModel> baseViewModels, int tileType, RecyclerView recyclerView, ViewGroup recyclerViewContainer);

    void setAdapter(RecyclerView recyclerView, List<? extends BaseViewModel> models, List<? extends UniversalBinder<?, ?>> binders);

    void updateAdapter(List<? extends BaseViewModel> models, BaseViewsDiffUtilFactory diffUtilFactory, boolean detectMoves);

    void updateAdapterForRange(List<? extends BaseViewModel> models, int positionStart, int itemCount);
}
