package com.android.car.ui.uxr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import java.util.Objects;
import spi.gb;
import spi.m3;
import spi.n3;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"AppCompatCustomView"})
public class DrawableStateImageView extends ImageView implements n3 {
    public m3 a;

    public DrawableStateImageView(Context context) {
        super(context);
    }

    public static /* synthetic */ int[] a(DrawableStateImageView drawableStateImageView, Integer num) {
        Objects.requireNonNull(drawableStateImageView);
        return super.onCreateDrawableState(num.intValue());
    }

    @Override // spi.n3
    public final void d(@Nullable int[] iArr, @Nullable int[] iArr2) {
        if (this.a == null) {
            this.a = new m3(this);
        }
        this.a.d(iArr, iArr2);
    }

    @Override // android.widget.ImageView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        if (this.a == null) {
            this.a = new m3(this);
        }
        return this.a.a(i, new gb(this, 4));
    }

    public DrawableStateImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DrawableStateImageView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
