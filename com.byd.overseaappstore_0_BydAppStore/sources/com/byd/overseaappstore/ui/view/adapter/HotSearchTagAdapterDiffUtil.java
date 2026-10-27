package com.byd.overseaappstore.ui.view.adapter;

import androidx.recyclerview.widget.DiffUtil;
import com.byd.overseaappstore.module.ability.data.upper.HotSearchItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/adapter/HotSearchTagAdapterDiffUtil;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/byd/overseaappstore/module/ability/data/upper/HotSearchItem;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class HotSearchTagAdapterDiffUtil extends DiffUtil.ItemCallback<HotSearchItem> {
    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final boolean areContentsTheSame(HotSearchItem hotSearchItem, HotSearchItem hotSearchItem2) {
        HotSearchItem oldItem = hotSearchItem;
        HotSearchItem newItem = hotSearchItem2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return Intrinsics.areEqual(oldItem, newItem);
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final boolean areItemsTheSame(HotSearchItem hotSearchItem, HotSearchItem hotSearchItem2) {
        HotSearchItem oldItem = hotSearchItem;
        HotSearchItem newItem = hotSearchItem2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return oldItem.getId() == newItem.getId();
    }
}
