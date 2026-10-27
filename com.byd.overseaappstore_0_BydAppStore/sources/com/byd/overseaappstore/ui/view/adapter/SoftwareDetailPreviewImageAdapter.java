package com.byd.overseaappstore.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import com.bumptech.glide.a;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.ItemSoftwareDetailPreviewImgBinding;
import com.byd.overseaappstore.module.ability.R$drawable;
import com.byd.overseaappstore.module.ability.data.upper.ImagePath;
import com.byd.overseaappstore.ui.view.base.BaseRvAdapter;
import com.byd.overseaappstore.ui.view.base.ContentViewHolder;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import spi.gq;
import spi.i6;
import spi.qn;
import spi.tm;
import spi.ud;
import spi.vd;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/byd/overseaappstore/ui/view/adapter/SoftwareDetailPreviewImageAdapter;", "Lcom/byd/overseaappstore/ui/view/base/BaseRvAdapter;", "Lcom/byd/overseaappstore/module/ability/data/upper/ImagePath;", "Lcom/byd/overseaappstore/databinding/ItemSoftwareDetailPreviewImgBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SoftwareDetailPreviewImageAdapter extends BaseRvAdapter<ImagePath, ItemSoftwareDetailPreviewImgBinding> {
    public SoftwareDetailPreviewImageAdapter() {
        super(new gq());
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final ViewBinding b(Context context, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemSoftwareDetailPreviewImgBinding itemSoftwareDetailPreviewImgBindingA = ItemSoftwareDetailPreviewImgBinding.a(LayoutInflater.from(context), parent);
        Intrinsics.checkNotNullExpressionValue(itemSoftwareDetailPreviewImgBindingA, "inflate(\n            Lay…          false\n        )");
        return itemSoftwareDetailPreviewImgBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void c(ContentViewHolder contentViewHolder, ImagePath imagePath, int i) {
        ImagePath itemData = imagePath;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        f(contentViewHolder, itemData);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void e(ContentViewHolder contentViewHolder, ImagePath imagePath, int i, List payloads) {
        ImagePath itemData = imagePath;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        f(contentViewHolder, itemData);
    }

    public final void f(ContentViewHolder<ItemSoftwareDetailPreviewImgBinding> contentViewHolder, ImagePath imagePath) {
        ItemSoftwareDetailPreviewImgBinding itemSoftwareDetailPreviewImgBinding = (ItemSoftwareDetailPreviewImgBinding) contentViewHolder.a;
        vd vdVar = vd.a;
        itemSoftwareDetailPreviewImgBinding.a.getResources().getDimensionPixelSize(R.dimen.dp_10);
        vm vmVar = vm.a;
        Context context = itemSoftwareDetailPreviewImgBinding.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "root.context");
        int iA = vmVar.a(context, R.drawable.bg_app_detial_default_bg);
        Objects.requireNonNull(vdVar);
        tm tmVarP = new tm().p(iA);
        Intrinsics.checkNotNullExpressionValue(tmVarP, "RequestOptions()\n//     …laceholder(placeholderId)");
        tm options = tmVarP;
        Context context2 = itemSoftwareDetailPreviewImgBinding.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "root.context");
        String path = imagePath.getPath();
        ImageView intoImageView = itemSoftwareDetailPreviewImgBinding.b;
        Intrinsics.checkNotNullExpressionValue(intoImageView, "ivSoftwarePreviewImg");
        int dimensionPixelSize = itemSoftwareDetailPreviewImgBinding.a.getResources().getDimensionPixelSize(R.dimen.dp_10);
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(intoImageView, "intoImageView");
        Intrinsics.checkNotNullParameter(SoftwareDetailPreviewImageAdapter.class, "clazz");
        Intrinsics.checkNotNullParameter(options, "options");
        a.e(context2).o(path).h(vmVar.a(context2, R$drawable.bg_app_detial_default_bg)).f(i6.c).a(new tm().x(new qn(dimensionPixelSize), true)).a(options).A(new ud(SoftwareDetailPreviewImageAdapter.class)).G(intoImageView);
    }
}
