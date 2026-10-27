package com.amazon.music.views.library.recyclerview;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DiffUtil;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.Hideable;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HideableBaseViewModelAdapterProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B1\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ(\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J.\u0010\u0015\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/HideableBaseViewModelAdapterProvider;", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "itemMovedListener", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter$ItemMovedListener;", "lifecycleScope", "Lkotlinx/coroutines/CoroutineScope;", "useOptimizedObservable", "", "(Landroidx/lifecycle/LifecycleOwner;Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter$ItemMovedListener;Lkotlinx/coroutines/CoroutineScope;Z)V", "addItems", "", "adapter", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "models", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "diffResult", "Landroidx/recyclerview/widget/DiffUtil$DiffResult;", "getVisibleModels", "updateItemsInRange", "positionStart", "", "itemCount", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HideableBaseViewModelAdapterProvider extends BaseViewModelAdapterProvider {
    private UniversalAdapter.ItemMovedListener itemMovedListener;
    private LifecycleOwner lifecycleOwner;
    private final CoroutineScope lifecycleScope;
    private final boolean useOptimizedObservable;

    public /* synthetic */ HideableBaseViewModelAdapterProvider(LifecycleOwner lifecycleOwner, UniversalAdapter.ItemMovedListener itemMovedListener, CoroutineScope coroutineScope, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lifecycleOwner, (i & 2) != 0 ? null : itemMovedListener, (i & 4) != 0 ? null : coroutineScope, (i & 8) != 0 ? false : z);
    }

    public HideableBaseViewModelAdapterProvider(LifecycleOwner lifecycleOwner, UniversalAdapter.ItemMovedListener itemMovedListener, CoroutineScope coroutineScope, boolean z) {
        super(lifecycleOwner, itemMovedListener, coroutineScope, z);
        this.lifecycleOwner = lifecycleOwner;
        this.itemMovedListener = itemMovedListener;
        this.lifecycleScope = coroutineScope;
        this.useOptimizedObservable = z;
    }

    @Override // com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider
    public synchronized void addItems(UniversalAdapter adapter, List<? extends BaseViewModel> models, DiffUtil.DiffResult diffResult) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(models, "models");
        super.addItems(adapter, getVisibleModels(models), diffResult);
    }

    @Override // com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider
    public synchronized void updateItemsInRange(UniversalAdapter adapter, List<? extends BaseViewModel> models, int positionStart, int itemCount) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(models, "models");
        super.updateItemsInRange(adapter, getVisibleModels(models), positionStart, itemCount);
    }

    private final List<BaseViewModel> getVisibleModels(List<? extends BaseViewModel> models) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : models) {
            Object obj2 = (BaseViewModel) obj;
            Hideable hideable = obj2 instanceof Hideable ? (Hideable) obj2 : null;
            if (hideable != null ? true ^ hideable.getHidden() : true) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
