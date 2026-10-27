package com.amazon.mp3.catalog.fragment.adapter;

import androidx.recyclerview.widget.DiffUtil;
import com.amazon.music.views.library.models.EmptyStateModel;
import com.amazon.music.views.library.models.HighlightableMessageModel;
import com.amazon.music.views.library.models.PageHeaderModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: BaseViewsDiffUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\f\b\u0016\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u0011\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u0011¢\u0006\u0004\b\u001b\u0010\u001cJ\u0006\u0010\u0003\u001a\u00020\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0018\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016J\u0018\u0010\t\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0014J$\u0010\t\u001a\u00020\u00022\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\rH\u0014J\u0018\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016J\u0018\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0014J$\u0010\u0010\u001a\u00020\u00022\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\rH\u0014R(\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u00118\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R(\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00118\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtil;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "", "areListsTheSame", "", "getOldListSize", "getNewListSize", "oldItemPosition", "newItemPosition", "areContentsTheSame", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "oldModel", "newModel", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "oldContainerModel", "newContainerModel", "areItemsTheSame", "", "oldList", "Ljava/util/List;", "getOldList", "()Ljava/util/List;", "setOldList", "(Ljava/util/List;)V", "newList", "getNewList", "setNewList", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BaseViewsDiffUtil extends DiffUtil.Callback {
    private List<? extends BaseViewModel> newList;
    private List<? extends BaseViewModel> oldList;

    public BaseViewsDiffUtil(List<? extends BaseViewModel> oldList, List<? extends BaseViewModel> newList) {
        Intrinsics.checkNotNullParameter(oldList, "oldList");
        Intrinsics.checkNotNullParameter(newList, "newList");
        this.oldList = oldList;
        this.newList = newList;
    }

    protected final List<BaseViewModel> getNewList() {
        return this.newList;
    }

    protected final List<BaseViewModel> getOldList() {
        return this.oldList;
    }

    public final boolean areListsTheSame() {
        if (this.oldList.size() != this.newList.size()) {
            return false;
        }
        int size = this.newList.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            if (!areItemsTheSame(i, i)) {
                return false;
            }
            i = i2;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getOldListSize() {
        return this.oldList.size();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getNewListSize() {
        return this.newList.size();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return areContentsTheSame(this.oldList.get(oldItemPosition), this.newList.get(newItemPosition));
    }

    protected boolean areContentsTheSame(BaseViewModel oldModel, BaseViewModel newModel) {
        Intrinsics.checkNotNullParameter(oldModel, "oldModel");
        Intrinsics.checkNotNullParameter(newModel, "newModel");
        if (!Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(oldModel.getClass()), Reflection.getOrCreateKotlinClass(newModel.getClass()))) {
            return false;
        }
        if (oldModel instanceof BaseViewContainerModel) {
            return areContentsTheSame((BaseViewContainerModel<BaseViewModel>) oldModel, (BaseViewContainerModel<BaseViewModel>) newModel);
        }
        if (oldModel instanceof BaseViewContentModel ? true : oldModel instanceof PageHeaderModel ? true : oldModel instanceof EmptyStateModel ? true : oldModel instanceof HighlightableMessageModel) {
            return Intrinsics.areEqual(oldModel, newModel);
        }
        return true;
    }

    protected boolean areContentsTheSame(BaseViewContainerModel<BaseViewModel> oldContainerModel, BaseViewContainerModel<BaseViewModel> newContainerModel) {
        Intrinsics.checkNotNullParameter(oldContainerModel, "oldContainerModel");
        Intrinsics.checkNotNullParameter(newContainerModel, "newContainerModel");
        List<T> models = oldContainerModel.getModels();
        List<T> models2 = newContainerModel.getModels();
        if (models.size() != models2.size()) {
            return false;
        }
        int size = models2.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            if (!areContentsTheSame((BaseViewModel) models.get(i), (BaseViewModel) models2.get(i))) {
                return false;
            }
            i = i2;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        if (oldItemPosition >= 0 && oldItemPosition < this.oldList.size()) {
            if (newItemPosition >= 0 && newItemPosition < this.newList.size()) {
                return areItemsTheSame(this.oldList.get(oldItemPosition), this.newList.get(newItemPosition));
            }
        }
        return false;
    }

    protected boolean areItemsTheSame(BaseViewModel oldModel, BaseViewModel newModel) {
        Intrinsics.checkNotNullParameter(oldModel, "oldModel");
        Intrinsics.checkNotNullParameter(newModel, "newModel");
        if (!Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(oldModel.getClass()), Reflection.getOrCreateKotlinClass(newModel.getClass()))) {
            return false;
        }
        if (oldModel instanceof BaseViewContainerModel) {
            return areItemsTheSame((BaseViewContainerModel<BaseViewModel>) oldModel, (BaseViewContainerModel<BaseViewModel>) newModel);
        }
        if (oldModel instanceof BaseViewContentModel ? true : oldModel instanceof PageHeaderModel ? true : oldModel instanceof EmptyStateModel) {
            return oldModel.areItemsTheSame(newModel);
        }
        return true;
    }

    protected boolean areItemsTheSame(BaseViewContainerModel<BaseViewModel> oldContainerModel, BaseViewContainerModel<BaseViewModel> newContainerModel) {
        Intrinsics.checkNotNullParameter(oldContainerModel, "oldContainerModel");
        Intrinsics.checkNotNullParameter(newContainerModel, "newContainerModel");
        List<T> models = oldContainerModel.getModels();
        List<T> models2 = newContainerModel.getModels();
        if (models.size() != models2.size()) {
            return false;
        }
        int size = models2.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            if (!areItemsTheSame((BaseViewModel) models.get(i), (BaseViewModel) models2.get(i))) {
                return false;
            }
            i = i2;
        }
        return true;
    }
}
