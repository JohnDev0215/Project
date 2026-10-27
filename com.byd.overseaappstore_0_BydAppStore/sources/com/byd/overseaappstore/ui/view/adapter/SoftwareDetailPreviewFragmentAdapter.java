package com.byd.overseaappstore.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.databinding.ItemSoftwareDetailPreviewFragmentBinding;
import com.byd.overseaappstore.module.ability.data.upper.ImagePath;
import com.byd.overseaappstore.ui.view.base.BaseRvAdapter;
import com.byd.overseaappstore.ui.view.base.ContentViewHolder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import spi.vd;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/byd/overseaappstore/ui/view/adapter/SoftwareDetailPreviewFragmentAdapter;", "Lcom/byd/overseaappstore/ui/view/base/BaseRvAdapter;", "Lcom/byd/overseaappstore/module/ability/data/upper/ImagePath;", "Lcom/byd/overseaappstore/databinding/ItemSoftwareDetailPreviewFragmentBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SoftwareDetailPreviewFragmentAdapter extends BaseRvAdapter<ImagePath, ItemSoftwareDetailPreviewFragmentBinding> {
    public SoftwareDetailPreviewFragmentAdapter() {
        super(new DiffUtil.ItemCallback<ImagePath>() { // from class: com.byd.overseaappstore.ui.view.adapter.SoftwareDetailPreviewFragmentAdapter.1
            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public final boolean areContentsTheSame(ImagePath imagePath, ImagePath imagePath2) {
                ImagePath oldItem = imagePath;
                ImagePath newItem = imagePath2;
                Intrinsics.checkNotNullParameter(oldItem, "oldItem");
                Intrinsics.checkNotNullParameter(newItem, "newItem");
                return Intrinsics.areEqual(oldItem, newItem);
            }

            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public final boolean areItemsTheSame(ImagePath imagePath, ImagePath imagePath2) {
                ImagePath oldItem = imagePath;
                ImagePath newItem = imagePath2;
                Intrinsics.checkNotNullParameter(oldItem, "oldItem");
                Intrinsics.checkNotNullParameter(newItem, "newItem");
                return Intrinsics.areEqual(oldItem, newItem);
            }
        });
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final ViewBinding b(Context context, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemSoftwareDetailPreviewFragmentBinding itemSoftwareDetailPreviewFragmentBindingA = ItemSoftwareDetailPreviewFragmentBinding.a(LayoutInflater.from(context), parent);
        Intrinsics.checkNotNullExpressionValue(itemSoftwareDetailPreviewFragmentBindingA, "inflate(\n            Lay…          false\n        )");
        return itemSoftwareDetailPreviewFragmentBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void c(ContentViewHolder contentViewHolder, ImagePath imagePath, int i) {
        ImagePath itemData = imagePath;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        ItemSoftwareDetailPreviewFragmentBinding itemSoftwareDetailPreviewFragmentBinding = (ItemSoftwareDetailPreviewFragmentBinding) contentViewHolder.a;
        vd vdVar = vd.a;
        Context context = itemSoftwareDetailPreviewFragmentBinding.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "root.context");
        String path = itemData.getPath();
        ImageView ivSoftwarePreviewFragment = itemSoftwareDetailPreviewFragmentBinding.b;
        Intrinsics.checkNotNullExpressionValue(ivSoftwarePreviewFragment, "ivSoftwarePreviewFragment");
        vdVar.a(context, path, ivSoftwarePreviewFragment, SoftwareDetailPreviewFragmentAdapter.class);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void e(ContentViewHolder contentViewHolder, ImagePath imagePath, int i, List payloads) {
        ImagePath itemData = imagePath;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
    }
}
