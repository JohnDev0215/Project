package com.byd.overseaappstore.ui.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.a;
import com.byd.overseaappstore.module.ability.R$drawable;
import com.byd.overseaappstore.module.ability.data.upper.Banner;
import com.byd.overseaappstore.ui.view.adapter.BannerAdapter;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;
import spi.e;
import spi.i6;
import spi.qn;
import spi.td;
import spi.tm;
import spi.vd;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/byd/overseaappstore/ui/view/adapter/BannerAdapter;", "Landroidx/viewpager/widget/PagerAdapter;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class BannerAdapter extends PagerAdapter {

    @Nullable
    public Function2<? super Integer, ? super Banner, Unit> a;

    @Nullable
    public Function1<? super List<Banner>, Unit> b;

    @NotNull
    public List<Banner> c = CollectionsKt.mutableListOf(new Banner(0, ""));
    public qn d;
    public tm e;

    @Override // androidx.viewpager.widget.PagerAdapter
    public final void destroyItem(@NotNull ViewGroup container, int i, @NotNull Object destroyObject) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(destroyObject, "destroyObject");
        container.removeView((View) destroyObject);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final int getCount() {
        int size;
        synchronized (this.c) {
            size = this.c.size();
        }
        return size <= 0 ? 0 : 840100;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final int getItemPosition(@NotNull Object item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NotNull
    public final Object instantiateItem(@NotNull ViewGroup container, final int i) {
        Intrinsics.checkNotNullParameter(container, "container");
        final int size = i % this.c.size();
        ct.a(e.c("instantiateItem position:", i), new Object[0]);
        ImageView intoImageView = new ImageView(container.getContext());
        intoImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        intoImageView.setOnClickListener(new View.OnClickListener() { // from class: spi.a1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BannerAdapter this$0 = this.a;
                int i2 = i;
                int i3 = size;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Function2<? super Integer, ? super Banner, Unit> function2 = this$0.a;
                if (function2 != null) {
                    function2.mo6invoke(Integer.valueOf(i2), this$0.c.get(i3));
                }
            }
        });
        vd vdVar = vd.a;
        Context context = container.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "container.context");
        String avatar = this.c.get(size).getAvatar();
        tm options = this.e;
        if (options == null) {
            Intrinsics.throwUninitializedPropertyAccessException("options");
            options = null;
        }
        Objects.requireNonNull(vdVar);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intoImageView, "intoImageView");
        Intrinsics.checkNotNullParameter(BannerAdapter.class, "clazz");
        Intrinsics.checkNotNullParameter(options, "options");
        a.e(context).o(avatar).h(vm.a.a(context, R$drawable.bg_app_detial_default_bg)).f(i6.c).a(options).A(new td(BannerAdapter.class)).G(intoImageView);
        container.addView(intoImageView);
        return intoImageView;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final boolean isViewFromObject(@NotNull View view, @NotNull Object viewFromObject) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(viewFromObject, "viewFromObject");
        return Intrinsics.areEqual(view, viewFromObject);
    }
}
