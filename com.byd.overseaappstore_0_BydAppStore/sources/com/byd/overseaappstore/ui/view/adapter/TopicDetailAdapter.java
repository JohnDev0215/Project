package com.byd.overseaappstore.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.databinding.ItemTopicDetailBinding;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.view.base.BaseRvAdapter;
import com.byd.overseaappstore.ui.view.base.ContentViewHolder;
import com.byd.overseaappstore.ui.view.custom.DownloadProgressButtonView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.hb;
import spi.vd;
import spi.xa;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/byd/overseaappstore/ui/view/adapter/TopicDetailAdapter;", "Lcom/byd/overseaappstore/ui/view/base/BaseRvAdapter;", "Lcom/byd/overseaappstore/module/ability/data/upper/Software;", "Lcom/byd/overseaappstore/databinding/ItemTopicDetailBinding;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "differ", "<init>", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class TopicDetailAdapter extends BaseRvAdapter<Software, ItemTopicDetailBinding> {
    public TopicDetailAdapter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TopicDetailAdapter(@NotNull DiffUtil.ItemCallback<Software> differ) {
        super(differ);
        Intrinsics.checkNotNullParameter(differ, "differ");
    }

    public /* synthetic */ TopicDetailAdapter(DiffUtil.ItemCallback itemCallback, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new DiffUtil.ItemCallback<Software>() { // from class: com.byd.overseaappstore.ui.view.adapter.TopicDetailAdapter.1
            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public final boolean areContentsTheSame(Software software, Software software2) {
                Software oldItem = software;
                Software newItem = software2;
                Intrinsics.checkNotNullParameter(oldItem, "oldItem");
                Intrinsics.checkNotNullParameter(newItem, "newItem");
                return oldItem.getState() == newItem.getState() && oldItem.getPercentCount() == newItem.getPercentCount();
            }

            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public final boolean areItemsTheSame(Software software, Software software2) {
                Software oldItem = software;
                Software newItem = software2;
                Intrinsics.checkNotNullParameter(oldItem, "oldItem");
                Intrinsics.checkNotNullParameter(newItem, "newItem");
                return Intrinsics.areEqual(oldItem, newItem);
            }

            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public final Object getChangePayload(Software software, Software software2) {
                Software oldItem = software;
                Software newItem = software2;
                Intrinsics.checkNotNullParameter(oldItem, "oldItem");
                Intrinsics.checkNotNullParameter(newItem, "newItem");
                return newItem;
            }
        } : itemCallback);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final ViewBinding b(Context context, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemTopicDetailBinding itemTopicDetailBindingA = ItemTopicDetailBinding.a(LayoutInflater.from(context), parent);
        Intrinsics.checkNotNullExpressionValue(itemTopicDetailBindingA, "inflate(LayoutInflater.f…(context), parent, false)");
        return itemTopicDetailBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void c(ContentViewHolder contentViewHolder, Software software, int i) {
        Software itemData = software;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        ItemTopicDetailBinding itemTopicDetailBinding = (ItemTopicDetailBinding) contentViewHolder.a;
        itemTopicDetailBinding.d.setText(itemData.getName());
        vd vdVar = vd.a;
        Context context = itemTopicDetailBinding.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "root.context");
        String icon = itemData.getIcon();
        ImageView ivTopicDetailAppIcon = itemTopicDetailBinding.b;
        Intrinsics.checkNotNullExpressionValue(ivTopicDetailAppIcon, "ivTopicDetailAppIcon");
        vdVar.a(context, icon, ivTopicDetailAppIcon, TopicDetailAdapter.class);
        itemTopicDetailBinding.f.setText(itemData.getClassifyName());
        itemTopicDetailBinding.e.setText(itemData.getSizeTip());
        DownloadProgressButtonView pbTopicDetailAppButton = itemTopicDetailBinding.c;
        Intrinsics.checkNotNullExpressionValue(pbTopicDetailAppButton, "pbTopicDetailAppButton");
        int state = itemData.getState();
        int percentCount = itemData.getPercentCount();
        int i2 = DownloadProgressButtonView.d;
        pbTopicDetailAppButton.d(state, percentCount, true);
        itemTopicDetailBinding.c.setOnClickListener(new xa(itemData, this, i));
        itemTopicDetailBinding.g.setOnClickListener(new hb(this, itemData, i, 1));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void e(ContentViewHolder contentViewHolder, Software software, int i, List payloads) {
        Software itemData = software;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        DownloadProgressButtonView pbTopicDetailAppButton = ((ItemTopicDetailBinding) contentViewHolder.a).c;
        int percentCount = itemData.getPercentCount();
        int state = itemData.getState();
        Intrinsics.checkNotNullExpressionValue(pbTopicDetailAppButton, "pbTopicDetailAppButton");
        int i2 = DownloadProgressButtonView.d;
        pbTopicDetailAppButton.d(state, percentCount, true);
    }
}
