package com.byd.overseaappstore.ui.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.collection.ArraySet;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.bumptech.glide.a;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.ItemFeatureCategoryHotAppBinding;
import com.byd.overseaappstore.databinding.ItemFeatureCategorySoftwareThemeBinding;
import com.byd.overseaappstore.module.ability.R$drawable;
import com.byd.overseaappstore.module.ability.data.upper.FeatureThemTopicGroup;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.view.base.BaseRvAdapter;
import com.byd.overseaappstore.ui.view.base.ContentViewHolder;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.bb;
import spi.cb;
import spi.db;
import spi.eb;
import spi.fb;
import spi.tm;
import spi.vd;
import spi.vm;
import spi.xa;
import spi.xd;
import spi.ya;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/byd/overseaappstore/ui/view/adapter/FeatureCategoryThemeAdapter;", "Lcom/byd/overseaappstore/ui/view/base/BaseRvAdapter;", "Lcom/byd/overseaappstore/module/ability/data/upper/FeatureThemTopicGroup;", "Lcom/byd/overseaappstore/databinding/ItemFeatureCategorySoftwareThemeBinding;", "Landroidx/collection/ArraySet;", "", "picErrorCollect", "<init>", "(Landroidx/collection/ArraySet;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class FeatureCategoryThemeAdapter extends BaseRvAdapter<FeatureThemTopicGroup, ItemFeatureCategorySoftwareThemeBinding> {

    @NotNull
    public final ArraySet<Integer> c;

    @Nullable
    public Function3<? super Software, ? super ItemFeatureCategoryHotAppBinding, ? super Integer, Unit> d;

    @Nullable
    public Function3<? super Software, ? super Integer, ? super Integer, Unit> e;

    public FeatureCategoryThemeAdapter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeatureCategoryThemeAdapter(@NotNull ArraySet<Integer> picErrorCollect) {
        super(new fb(picErrorCollect));
        Intrinsics.checkNotNullParameter(picErrorCollect, "picErrorCollect");
        this.c = picErrorCollect;
    }

    public /* synthetic */ FeatureCategoryThemeAdapter(ArraySet arraySet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArraySet() : arraySet);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final ViewBinding b(Context context, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemFeatureCategorySoftwareThemeBinding itemFeatureCategorySoftwareThemeBindingA = ItemFeatureCategorySoftwareThemeBinding.a(LayoutInflater.from(context), parent);
        Intrinsics.checkNotNullExpressionValue(itemFeatureCategorySoftwareThemeBindingA, "inflate(\n            Lay…          false\n        )");
        return itemFeatureCategorySoftwareThemeBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void c(ContentViewHolder contentViewHolder, FeatureThemTopicGroup featureThemTopicGroup, int i) {
        FeatureThemTopicGroup itemData = featureThemTopicGroup;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        FeatureCategoryHotAppAdapter featureCategoryHotAppAdapter = new FeatureCategoryHotAppAdapter(null, 1, null);
        db block = new db(this);
        Intrinsics.checkNotNullParameter(block, "block");
        featureCategoryHotAppAdapter.a = block;
        featureCategoryHotAppAdapter.a(R.id.dpb_hot_app_button, new eb(this));
        ItemFeatureCategorySoftwareThemeBinding itemFeatureCategorySoftwareThemeBinding = (ItemFeatureCategorySoftwareThemeBinding) contentViewHolder.a;
        itemFeatureCategorySoftwareThemeBinding.d.setText(itemData.getTitle());
        this.c.remove(Integer.valueOf(itemData.getId()));
        if (itemData.getPicUrl().length() == 0) {
            itemFeatureCategorySoftwareThemeBinding.b.setVisibility(8);
        } else {
            vd vdVar = vd.a;
            itemFeatureCategorySoftwareThemeBinding.a.getResources().getDimensionPixelSize(R.dimen.dp_15);
            vm vmVar = vm.a;
            Context context = itemFeatureCategorySoftwareThemeBinding.a.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "root.context");
            int iA = vmVar.a(context, R.drawable.bg_featrue_app_theme_pic_default_bg);
            Objects.requireNonNull(vdVar);
            tm tmVarP = new tm().p(iA);
            Intrinsics.checkNotNullExpressionValue(tmVarP, "RequestOptions()\n//     …laceholder(placeholderId)");
            tm option = tmVarP;
            Context context2 = itemFeatureCategorySoftwareThemeBinding.a.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "root.context");
            String picUrl = itemData.getPicUrl();
            ImageView intoImageView = itemFeatureCategorySoftwareThemeBinding.b;
            Intrinsics.checkNotNullExpressionValue(intoImageView, "ivFeatureDetailImg");
            cb block2 = new cb(this, itemData);
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(intoImageView, "intoImageView");
            Intrinsics.checkNotNullParameter(FeatureCategoryThemeAdapter.class, "clazz");
            Intrinsics.checkNotNullParameter(block2, "block");
            Intrinsics.checkNotNullParameter(option, "option");
            a.e(context2).o(picUrl).h(vmVar.a(context2, R$drawable.bg_featrue_app_theme_pic_default_bg)).a(option).A(new xd(block2, FeatureCategoryThemeAdapter.class)).G(intoImageView);
        }
        itemFeatureCategorySoftwareThemeBinding.c.setLayoutManager(new LinearLayoutManager(itemFeatureCategorySoftwareThemeBinding.a.getContext(), 1, false));
        itemFeatureCategorySoftwareThemeBinding.c.setAdapter(featureCategoryHotAppAdapter);
        RecyclerView recyclerView = itemFeatureCategorySoftwareThemeBinding.c;
        Context context3 = itemFeatureCategorySoftwareThemeBinding.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "root.context");
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context3, 1);
        Drawable drawable = ResourcesCompat.getDrawable(context3.getResources(), R.drawable.line_recyclerview_horizontal_boundary, null);
        if (drawable != null) {
            dividerItemDecoration.setDrawable(drawable);
        }
        recyclerView.addItemDecoration(dividerItemDecoration);
        featureCategoryHotAppAdapter.submitList(itemData.getSoftwareList());
        itemFeatureCategorySoftwareThemeBinding.e.setOnClickListener(new xa(this, itemData, i, 1));
        itemFeatureCategorySoftwareThemeBinding.b.setOnClickListener(new bb(this, itemData, i, 1));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void e(ContentViewHolder contentViewHolder, FeatureThemTopicGroup featureThemTopicGroup, int i, List payloads) {
        FeatureThemTopicGroup itemData = featureThemTopicGroup;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        RecyclerView.Adapter adapter = ((ItemFeatureCategorySoftwareThemeBinding) contentViewHolder.a).c.getAdapter();
        Object obj = payloads.get(0);
        if ((adapter instanceof FeatureCategoryHotAppAdapter) && (obj instanceof FeatureThemTopicGroup)) {
            ((ItemFeatureCategorySoftwareThemeBinding) contentViewHolder.a).d.setText(itemData.getTitle());
            ((FeatureCategoryHotAppAdapter) adapter).submitList(((FeatureThemTopicGroup) obj).getSoftwareList());
            ((ItemFeatureCategorySoftwareThemeBinding) contentViewHolder.a).e.setOnClickListener(new bb(this, itemData, i, 0));
            ((ItemFeatureCategorySoftwareThemeBinding) contentViewHolder.a).b.setOnClickListener(new ya(this, itemData, i, 1));
        }
    }
}
