package com.byd.overseaappstore.ui.view.custom.transformer;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/byd/overseaappstore/ui/view/custom/transformer/CoverModeTransformer;", "Landroidx/viewpager/widget/ViewPager$PageTransformer;", "Landroidx/viewpager/widget/ViewPager;", "pager", "", "dimenId", "<init>", "(Landroidx/viewpager/widget/ViewPager;I)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class CoverModeTransformer implements ViewPager.PageTransformer {
    public float a;
    public float b;
    public float c;
    public final int d;

    @NotNull
    public final ViewPager e;

    public CoverModeTransformer(@NotNull ViewPager pager, int i) {
        Intrinsics.checkNotNullParameter(pager, "pager");
        this.e = pager;
        this.d = i;
    }

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public final void transformPage(@NotNull View view, float f) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (this.c == 0.0f) {
            float paddingLeft = this.e.getPaddingLeft();
            this.c = paddingLeft / ((this.e.getMeasuredWidth() - paddingLeft) - this.e.getPaddingRight());
        }
        float f2 = f - this.c;
        if (this.b == 0.0f) {
            float width = view.getWidth();
            this.b = width;
            this.a = (width * 0.19999999f) / 2.0f;
        }
        if (f2 <= -1.0f) {
            view.setTranslationX(this.a + this.d);
            view.setScaleX(0.8f);
            view.setScaleY(0.8f);
            return;
        }
        if (f2 > 1.0f) {
            view.setScaleX(0.8f);
            view.setScaleY(0.8f);
            view.setTranslationX((-this.a) - this.d);
            return;
        }
        float fAbs = Math.abs(1.0f - Math.abs(f2)) * 0.19999999f;
        float fAbs2 = (-this.a) * f2;
        if (f2 <= -0.5f) {
            view.setTranslationX(((Math.abs(Math.abs(f2) - 0.5f) * this.d) / 0.5f) + fAbs2);
        } else {
            if (f2 > 0.0f && f2 >= 0.5f) {
                fAbs2 -= (Math.abs(Math.abs(f2) - 0.5f) * this.d) / 0.5f;
            }
            view.setTranslationX(fAbs2);
        }
        float f3 = fAbs + 0.8f;
        view.setScaleX(f3);
        view.setScaleY(f3);
    }
}
