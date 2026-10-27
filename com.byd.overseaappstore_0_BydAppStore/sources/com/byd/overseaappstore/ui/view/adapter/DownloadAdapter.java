package com.byd.overseaappstore.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.ItemDownloadAppBinding;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.view.adapter.DownloadAdapter;
import com.byd.overseaappstore.ui.view.base.BaseRvAdapter;
import com.byd.overseaappstore.ui.view.base.ContentViewHolder;
import com.byd.overseaappstore.ui.view.custom.DownloadProgressButtonView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import spi.a7;
import spi.ct;
import spi.o5;
import spi.vd;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/byd/overseaappstore/ui/view/adapter/DownloadAdapter;", "Lcom/byd/overseaappstore/ui/view/base/BaseRvAdapter;", "Lcom/byd/overseaappstore/module/ability/data/upper/Software;", "Lcom/byd/overseaappstore/databinding/ItemDownloadAppBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class DownloadAdapter extends BaseRvAdapter<Software, ItemDownloadAppBinding> {
    public DownloadAdapter() {
        super(new a7());
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final ViewBinding b(Context context, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemDownloadAppBinding itemDownloadAppBindingA = ItemDownloadAppBinding.a(LayoutInflater.from(context), parent);
        Intrinsics.checkNotNullExpressionValue(itemDownloadAppBindingA, "inflate(\n            Lay…          false\n        )");
        return itemDownloadAppBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void c(final ContentViewHolder contentViewHolder, Software software, int i) {
        final Software itemData = software;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        ((ItemDownloadAppBinding) contentViewHolder.a).j.setText(itemData.getName());
        ((ItemDownloadAppBinding) contentViewHolder.a).k.setText(itemData.getSizeTip());
        ((ItemDownloadAppBinding) contentViewHolder.a).g.setProgress(itemData.getPercentCount());
        ((ItemDownloadAppBinding) contentViewHolder.a).i.setText(itemData.getClassifyName());
        int state = itemData.getState();
        if (state != 1) {
            if (state == 3 || state == 4) {
                ((ItemDownloadAppBinding) contentViewHolder.a).h.d(itemData.getState(), itemData.getPercentCount(), false);
                ((ItemDownloadAppBinding) contentViewHolder.a).c.setVisibility(8);
                ((ItemDownloadAppBinding) contentViewHolder.a).d.setVisibility(0);
                ItemDownloadAppBinding itemDownloadAppBinding = (ItemDownloadAppBinding) contentViewHolder.a;
                itemDownloadAppBinding.i.setText(itemDownloadAppBinding.a.getContext().getResources().getString(R.string.download_manager_current_size, o5.a.a(Long.valueOf((long) (itemData.getTotalSize() * ((long) itemData.getPercentCount()) * 0.01d))), itemData.getSizeTip()));
            } else if (state == 10 || state == 11) {
                ((ItemDownloadAppBinding) contentViewHolder.a).h.d(itemData.getState(), itemData.getPercentCount(), false);
            } else {
                DownloadProgressButtonView downloadProgressButtonView = ((ItemDownloadAppBinding) contentViewHolder.a).h;
                Intrinsics.checkNotNullExpressionValue(downloadProgressButtonView, "contentViewHolder.binding.pbManagerDownloadButton");
                int state2 = itemData.getState();
                int percentCount = itemData.getPercentCount();
                int i2 = DownloadProgressButtonView.d;
                downloadProgressButtonView.d(state2, percentCount, true);
                ((ItemDownloadAppBinding) contentViewHolder.a).c.setVisibility(0);
                ((ItemDownloadAppBinding) contentViewHolder.a).d.setVisibility(8);
            }
            vd vdVar = vd.a;
            Context context = ((ItemDownloadAppBinding) contentViewHolder.a).a.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "contentViewHolder.binding.root.context");
            String icon = itemData.getIcon();
            ImageView imageView = ((ItemDownloadAppBinding) contentViewHolder.a).f;
            Intrinsics.checkNotNullExpressionValue(imageView, "contentViewHolder.binding.ivDownloadIcon");
            vdVar.a(context, icon, imageView, DownloadAdapter.class);
            ((ItemDownloadAppBinding) contentViewHolder.a).e.setOnClickListener(new View.OnClickListener() { // from class: spi.v6
                /* JADX WARN: Type inference failed for: r0v1, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, kotlin.jvm.functions.Function3<Data, java.lang.Integer, java.lang.Integer, kotlin.Unit>>] */
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DownloadAdapter this$0 = this.a;
                    Software itemData2 = itemData;
                    ContentViewHolder contentViewHolder2 = contentViewHolder;
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    Intrinsics.checkNotNullParameter(itemData2, "$itemData");
                    Intrinsics.checkNotNullParameter(contentViewHolder2, "$contentViewHolder");
                    Function3 function3 = (Function3) this$0.b.get(Integer.valueOf(view.getId()));
                    if (function3 != null) {
                        function3.invoke(itemData2, Integer.valueOf(view.getId()), Integer.valueOf(contentViewHolder2.getAdapterPosition()));
                    }
                }
            });
            ((ItemDownloadAppBinding) contentViewHolder.a).h.setOnClickListener(new View.OnClickListener() { // from class: spi.w6
                /* JADX WARN: Type inference failed for: r0v1, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, kotlin.jvm.functions.Function3<Data, java.lang.Integer, java.lang.Integer, kotlin.Unit>>] */
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DownloadAdapter this$0 = this.a;
                    Software itemData2 = itemData;
                    ContentViewHolder contentViewHolder2 = contentViewHolder;
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    Intrinsics.checkNotNullParameter(itemData2, "$itemData");
                    Intrinsics.checkNotNullParameter(contentViewHolder2, "$contentViewHolder");
                    Function3 function3 = (Function3) this$0.b.get(Integer.valueOf(view.getId()));
                    if (function3 != null) {
                        function3.invoke(itemData2, Integer.valueOf(view.getId()), Integer.valueOf(contentViewHolder2.getAdapterPosition()));
                    }
                }
            });
        }
        DownloadProgressButtonView downloadProgressButtonView2 = ((ItemDownloadAppBinding) contentViewHolder.a).h;
        Intrinsics.checkNotNullExpressionValue(downloadProgressButtonView2, "contentViewHolder.binding.pbManagerDownloadButton");
        int state3 = itemData.getState();
        int percentCount2 = itemData.getPercentCount();
        int i3 = DownloadProgressButtonView.d;
        downloadProgressButtonView2.d(state3, percentCount2, true);
        ((ItemDownloadAppBinding) contentViewHolder.a).c.setVisibility(0);
        ((ItemDownloadAppBinding) contentViewHolder.a).d.setVisibility(0);
        ((ItemDownloadAppBinding) contentViewHolder.a).b.setVisibility(8);
        ((ItemDownloadAppBinding) contentViewHolder.a).i.setText(itemData.getClassifyName());
        vd vdVar2 = vd.a;
        Context context2 = ((ItemDownloadAppBinding) contentViewHolder.a).a.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "contentViewHolder.binding.root.context");
        String icon2 = itemData.getIcon();
        ImageView imageView2 = ((ItemDownloadAppBinding) contentViewHolder.a).f;
        Intrinsics.checkNotNullExpressionValue(imageView2, "contentViewHolder.binding.ivDownloadIcon");
        vdVar2.a(context2, icon2, imageView2, DownloadAdapter.class);
        ((ItemDownloadAppBinding) contentViewHolder.a).e.setOnClickListener(new View.OnClickListener() { // from class: spi.v6
            /* JADX WARN: Type inference failed for: r0v1, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, kotlin.jvm.functions.Function3<Data, java.lang.Integer, java.lang.Integer, kotlin.Unit>>] */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadAdapter this$0 = this.a;
                Software itemData2 = itemData;
                ContentViewHolder contentViewHolder2 = contentViewHolder;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Intrinsics.checkNotNullParameter(itemData2, "$itemData");
                Intrinsics.checkNotNullParameter(contentViewHolder2, "$contentViewHolder");
                Function3 function3 = (Function3) this$0.b.get(Integer.valueOf(view.getId()));
                if (function3 != null) {
                    function3.invoke(itemData2, Integer.valueOf(view.getId()), Integer.valueOf(contentViewHolder2.getAdapterPosition()));
                }
            }
        });
        ((ItemDownloadAppBinding) contentViewHolder.a).h.setOnClickListener(new View.OnClickListener() { // from class: spi.w6
            /* JADX WARN: Type inference failed for: r0v1, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, kotlin.jvm.functions.Function3<Data, java.lang.Integer, java.lang.Integer, kotlin.Unit>>] */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadAdapter this$0 = this.a;
                Software itemData2 = itemData;
                ContentViewHolder contentViewHolder2 = contentViewHolder;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Intrinsics.checkNotNullParameter(itemData2, "$itemData");
                Intrinsics.checkNotNullParameter(contentViewHolder2, "$contentViewHolder");
                Function3 function3 = (Function3) this$0.b.get(Integer.valueOf(view.getId()));
                if (function3 != null) {
                    function3.invoke(itemData2, Integer.valueOf(view.getId()), Integer.valueOf(contentViewHolder2.getAdapterPosition()));
                }
            }
        });
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
            Software software2 = (Software) obj;
            ((ItemDownloadAppBinding) contentViewHolder.a).g.setProgress(software2.getPercentCount());
            int state = software2.getState();
            if (state == 3 || state == 4) {
                ((ItemDownloadAppBinding) contentViewHolder.a).h.d(software2.getState(), software2.getPercentCount(), false);
                ((ItemDownloadAppBinding) contentViewHolder.a).c.setVisibility(8);
                ((ItemDownloadAppBinding) contentViewHolder.a).d.setVisibility(0);
                ItemDownloadAppBinding itemDownloadAppBinding = (ItemDownloadAppBinding) contentViewHolder.a;
                itemDownloadAppBinding.i.setText(itemDownloadAppBinding.a.getContext().getResources().getString(R.string.download_manager_current_size, o5.a.a(Long.valueOf((long) (itemData.getTotalSize() * ((long) itemData.getPercentCount()) * 0.01d))), itemData.getSizeTip()));
                return;
            }
            if (state == 10 || state == 11) {
                ((ItemDownloadAppBinding) contentViewHolder.a).h.d(software2.getState(), software2.getPercentCount(), false);
                return;
            }
            DownloadProgressButtonView downloadProgressButtonView = ((ItemDownloadAppBinding) contentViewHolder.a).h;
            Intrinsics.checkNotNullExpressionValue(downloadProgressButtonView, "contentViewHolder.binding.pbManagerDownloadButton");
            int state2 = software2.getState();
            int percentCount = software2.getPercentCount();
            int i2 = DownloadProgressButtonView.d;
            downloadProgressButtonView.d(state2, percentCount, true);
            ((ItemDownloadAppBinding) contentViewHolder.a).c.setVisibility(0);
            ((ItemDownloadAppBinding) contentViewHolder.a).d.setVisibility(8);
            ((ItemDownloadAppBinding) contentViewHolder.a).i.setText(itemData.getClassifyName());
        }
    }
}
