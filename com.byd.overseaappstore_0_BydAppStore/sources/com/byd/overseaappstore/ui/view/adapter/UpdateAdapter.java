package com.byd.overseaappstore.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.ItemUpdateAppBinding;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.view.base.BaseRvAdapter;
import com.byd.overseaappstore.ui.view.base.ContentViewHolder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import spi.ct;
import spi.eu;
import spi.o5;
import spi.vd;
import spi.xa;
import spi.ya;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/byd/overseaappstore/ui/view/adapter/UpdateAdapter;", "Lcom/byd/overseaappstore/ui/view/base/BaseRvAdapter;", "Lcom/byd/overseaappstore/module/ability/data/upper/Software;", "Lcom/byd/overseaappstore/databinding/ItemUpdateAppBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class UpdateAdapter extends BaseRvAdapter<Software, ItemUpdateAppBinding> {
    public UpdateAdapter() {
        super(new eu());
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final ViewBinding b(Context context, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemUpdateAppBinding itemUpdateAppBindingA = ItemUpdateAppBinding.a(LayoutInflater.from(context), parent);
        Intrinsics.checkNotNullExpressionValue(itemUpdateAppBindingA, "inflate(\n            Lay…          false\n        )");
        return itemUpdateAppBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void c(ContentViewHolder contentViewHolder, Software software, int i) {
        Software itemData = software;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        ((ItemUpdateAppBinding) contentViewHolder.a).h.setText(itemData.getName());
        ((ItemUpdateAppBinding) contentViewHolder.a).i.setText(itemData.getSizeTip());
        ((ItemUpdateAppBinding) contentViewHolder.a).e.setProgress(itemData.getPercentCount());
        ((ItemUpdateAppBinding) contentViewHolder.a).g.setText(itemData.getClassifyName());
        ((ItemUpdateAppBinding) contentViewHolder.a).f.d(itemData.getState(), itemData.getPercentCount(), false);
        int state = itemData.getState();
        if (state == 8) {
            ((ItemUpdateAppBinding) contentViewHolder.a).b.setVisibility(0);
            ((ItemUpdateAppBinding) contentViewHolder.a).e.setVisibility(8);
            ((ItemUpdateAppBinding) contentViewHolder.a).c.setVisibility(0);
        } else {
            if (state == 10 || state == 11) {
                ((ItemUpdateAppBinding) contentViewHolder.a).b.setVisibility(8);
                ((ItemUpdateAppBinding) contentViewHolder.a).e.setVisibility(0);
                ((ItemUpdateAppBinding) contentViewHolder.a).c.setVisibility(0);
                ItemUpdateAppBinding itemUpdateAppBinding = (ItemUpdateAppBinding) contentViewHolder.a;
                itemUpdateAppBinding.g.setText(itemUpdateAppBinding.a.getContext().getResources().getString(R.string.download_manager_current_size, o5.a.a(Long.valueOf((long) (itemData.getTotalSize() * ((long) itemData.getPercentCount()) * 0.01d))), itemData.getSizeTip()));
                vd vdVar = vd.a;
                Context context = ((ItemUpdateAppBinding) contentViewHolder.a).a.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "contentViewHolder.binding.root.context");
                String icon = itemData.getIcon();
                ImageView imageView = ((ItemUpdateAppBinding) contentViewHolder.a).d;
                Intrinsics.checkNotNullExpressionValue(imageView, "contentViewHolder.binding.ivUpdateIcon");
                vdVar.a(context, icon, imageView, UpdateAdapter.class);
                ((ItemUpdateAppBinding) contentViewHolder.a).f.setOnClickListener(new ya(this, itemData, i, 2));
                ((ItemUpdateAppBinding) contentViewHolder.a).c.setOnClickListener(new xa(this, itemData, i, 5));
            }
            ((ItemUpdateAppBinding) contentViewHolder.a).b.setVisibility(0);
            ((ItemUpdateAppBinding) contentViewHolder.a).e.setVisibility(8);
            ((ItemUpdateAppBinding) contentViewHolder.a).c.setVisibility(8);
        }
        ((ItemUpdateAppBinding) contentViewHolder.a).g.setText(itemData.getClassifyName());
        vd vdVar2 = vd.a;
        Context context2 = ((ItemUpdateAppBinding) contentViewHolder.a).a.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "contentViewHolder.binding.root.context");
        String icon2 = itemData.getIcon();
        ImageView imageView2 = ((ItemUpdateAppBinding) contentViewHolder.a).d;
        Intrinsics.checkNotNullExpressionValue(imageView2, "contentViewHolder.binding.ivUpdateIcon");
        vdVar2.a(context2, icon2, imageView2, UpdateAdapter.class);
        ((ItemUpdateAppBinding) contentViewHolder.a).f.setOnClickListener(new ya(this, itemData, i, 2));
        ((ItemUpdateAppBinding) contentViewHolder.a).c.setOnClickListener(new xa(this, itemData, i, 5));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void e(ContentViewHolder contentViewHolder, Software software, int i, List payloads) {
        Software itemData = software;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        Object obj = payloads.get(0);
        ct.a("onBindViewHolder  " + payloads.isEmpty() + " ", new Object[0]);
        if (obj instanceof Software) {
            ((ItemUpdateAppBinding) contentViewHolder.a).e.setProgress(itemData.getPercentCount());
            ((ItemUpdateAppBinding) contentViewHolder.a).f.d(itemData.getState(), itemData.getPercentCount(), false);
            int state = itemData.getState();
            if (state == 8) {
                ((ItemUpdateAppBinding) contentViewHolder.a).b.setVisibility(0);
                ((ItemUpdateAppBinding) contentViewHolder.a).e.setVisibility(8);
                ((ItemUpdateAppBinding) contentViewHolder.a).c.setVisibility(0);
            } else {
                if (state == 10 || state == 11) {
                    ((ItemUpdateAppBinding) contentViewHolder.a).b.setVisibility(8);
                    ((ItemUpdateAppBinding) contentViewHolder.a).e.setVisibility(0);
                    ((ItemUpdateAppBinding) contentViewHolder.a).c.setVisibility(0);
                    ItemUpdateAppBinding itemUpdateAppBinding = (ItemUpdateAppBinding) contentViewHolder.a;
                    itemUpdateAppBinding.g.setText(itemUpdateAppBinding.a.getContext().getResources().getString(R.string.download_manager_current_size, o5.a.a(Long.valueOf((long) (itemData.getTotalSize() * ((long) itemData.getPercentCount()) * 0.01d))), itemData.getSizeTip()));
                    return;
                }
                ((ItemUpdateAppBinding) contentViewHolder.a).b.setVisibility(0);
                ((ItemUpdateAppBinding) contentViewHolder.a).e.setVisibility(8);
                ((ItemUpdateAppBinding) contentViewHolder.a).c.setVisibility(8);
            }
            ((ItemUpdateAppBinding) contentViewHolder.a).g.setText(itemData.getClassifyName());
        }
    }
}
