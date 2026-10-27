package com.byd.overseaappstore.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.collection.ArraySet;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.databinding.ItemFeatureCenterBinding;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.view.base.BaseRvAdapter;
import com.byd.overseaappstore.ui.view.base.ContentViewHolder;
import com.byd.overseaappstore.ui.view.custom.DownloadProgressButtonView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.gb;
import spi.hb;
import spi.ib;
import spi.jb;
import spi.vd;
import spi.xa;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/byd/overseaappstore/ui/view/adapter/FeatureCenterAdapter;", "Lcom/byd/overseaappstore/ui/view/base/BaseRvAdapter;", "Lcom/byd/overseaappstore/module/ability/data/upper/Software;", "Lcom/byd/overseaappstore/databinding/ItemFeatureCenterBinding;", "Landroidx/collection/ArraySet;", "", "picErrorCollect", "<init>", "(Landroidx/collection/ArraySet;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class FeatureCenterAdapter extends BaseRvAdapter<Software, ItemFeatureCenterBinding> {

    @NotNull
    public final ArraySet<String> c;

    public FeatureCenterAdapter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeatureCenterAdapter(@NotNull ArraySet<String> picErrorCollect) {
        super(new jb(picErrorCollect));
        Intrinsics.checkNotNullParameter(picErrorCollect, "picErrorCollect");
        this.c = picErrorCollect;
    }

    public /* synthetic */ FeatureCenterAdapter(ArraySet arraySet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArraySet() : arraySet);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final ViewBinding b(Context context, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemFeatureCenterBinding itemFeatureCenterBindingA = ItemFeatureCenterBinding.a(LayoutInflater.from(context), parent);
        Intrinsics.checkNotNullExpressionValue(itemFeatureCenterBindingA, "inflate(LayoutInflater.f…(context), parent, false)");
        return itemFeatureCenterBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void c(ContentViewHolder contentViewHolder, Software software, int i) {
        Software itemData = software;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        ItemFeatureCenterBinding itemFeatureCenterBinding = (ItemFeatureCenterBinding) contentViewHolder.a;
        itemFeatureCenterBinding.d.setText(itemData.getName());
        itemFeatureCenterBinding.e.setText(itemData.getSizeTip());
        this.c.remove(itemData.getPackageName());
        vd vdVar = vd.a;
        Context context = itemFeatureCenterBinding.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "root.context");
        String icon = itemData.getIcon();
        ImageView ivIcon = itemFeatureCenterBinding.c;
        Intrinsics.checkNotNullExpressionValue(ivIcon, "ivIcon");
        vdVar.b(context, icon, ivIcon, FeatureCenterAdapter.class, new ib(this, itemData));
        DownloadProgressButtonView downloadProgressButtonView = ((ItemFeatureCenterBinding) contentViewHolder.a).b;
        Intrinsics.checkNotNullExpressionValue(downloadProgressButtonView, "contentViewHolder.binding.ivDownLoad");
        int state = itemData.getState();
        int percentCount = itemData.getPercentCount();
        int i2 = DownloadProgressButtonView.d;
        downloadProgressButtonView.d(state, percentCount, true);
        itemFeatureCenterBinding.f.setOnClickListener(new hb(this, itemData, i, 0));
        itemFeatureCenterBinding.b.setOnClickListener(new gb(this, itemData, i, 1));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void e(ContentViewHolder contentViewHolder, Software software, int i, List payloads) {
        Software itemData = software;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        Object obj = payloads.get(0);
        if (obj instanceof Software) {
            DownloadProgressButtonView downloadProgressButtonView = ((ItemFeatureCenterBinding) contentViewHolder.a).b;
            Intrinsics.checkNotNullExpressionValue(downloadProgressButtonView, "contentViewHolder.binding.ivDownLoad");
            Software software2 = (Software) obj;
            int state = software2.getState();
            int percentCount = software2.getPercentCount();
            int i2 = DownloadProgressButtonView.d;
            downloadProgressButtonView.d(state, percentCount, true);
            ((ItemFeatureCenterBinding) contentViewHolder.a).e.setText(itemData.getSizeTip());
            ((ItemFeatureCenterBinding) contentViewHolder.a).f.setOnClickListener(new gb(this, itemData, i, 0));
            ((ItemFeatureCenterBinding) contentViewHolder.a).b.setOnClickListener(new xa(this, itemData, i, 2));
        }
    }
}
