package com.byd.overseaappstore.ui.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import com.bumptech.glide.a;
import com.byd.overseaappstore.databinding.ItemInstalledAppBinding;
import com.byd.overseaappstore.module.ability.R$drawable;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareInstalled;
import com.byd.overseaappstore.ui.view.base.BaseRvAdapter;
import com.byd.overseaappstore.ui.view.base.ContentViewHolder;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import spi.vd;
import spi.vf;
import spi.vm;
import spi.xa;
import spi.yd;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/byd/overseaappstore/ui/view/adapter/InstalledAdapter;", "Lcom/byd/overseaappstore/ui/view/base/BaseRvAdapter;", "Lcom/byd/overseaappstore/module/ability/data/upper/SoftwareInstalled;", "Lcom/byd/overseaappstore/databinding/ItemInstalledAppBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class InstalledAdapter extends BaseRvAdapter<SoftwareInstalled, ItemInstalledAppBinding> {
    public InstalledAdapter() {
        super(new vf());
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final ViewBinding b(Context context, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemInstalledAppBinding itemInstalledAppBindingA = ItemInstalledAppBinding.a(LayoutInflater.from(context), parent);
        Intrinsics.checkNotNullExpressionValue(itemInstalledAppBindingA, "inflate(\n            Lay…          false\n        )");
        return itemInstalledAppBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void c(ContentViewHolder contentViewHolder, SoftwareInstalled softwareInstalled, int i) {
        SoftwareInstalled itemData = softwareInstalled;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        ((ItemInstalledAppBinding) contentViewHolder.a).d.setText(itemData.getName());
        ((ItemInstalledAppBinding) contentViewHolder.a).e.setText(itemData.getSizeTip());
        ((ItemInstalledAppBinding) contentViewHolder.a).f.setText(itemData.getClassifyName());
        vd vdVar = vd.a;
        Context context = ((ItemInstalledAppBinding) contentViewHolder.a).a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "contentViewHolder.binding.root.context");
        Drawable iconDrawable = itemData.getIconDrawable();
        ImageView intoImageView = ((ItemInstalledAppBinding) contentViewHolder.a).b;
        Intrinsics.checkNotNullExpressionValue(intoImageView, "contentViewHolder.binding.ivUninstallAppIcon");
        Objects.requireNonNull(vdVar);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intoImageView, "intoImageView");
        Intrinsics.checkNotNullParameter(InstalledAdapter.class, "clazz");
        a.e(context).n(iconDrawable).h(vm.a.a(context, R$drawable.bg_app_detial_default_bg)).A(new yd(InstalledAdapter.class)).G(intoImageView);
        ((ItemInstalledAppBinding) contentViewHolder.a).c.setOnClickListener(new xa(this, itemData, i, 3));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void e(ContentViewHolder contentViewHolder, SoftwareInstalled softwareInstalled, int i, List payloads) {
        SoftwareInstalled itemData = softwareInstalled;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
    }
}
