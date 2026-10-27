package com.android.car.apps.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.car.ui.R$styleable;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class CropAlignedImageView extends ImageView {
    public int a;
    public float b;
    public int c;
    public int d;

    public CropAlignedImageView(Context context) {
        this(context, null);
    }

    public final void a() {
        int i;
        if (getDrawable() != null) {
            float intrinsicWidth = getDrawable().getIntrinsicWidth();
            float intrinsicHeight = getDrawable().getIntrinsicHeight();
            float fMax = Math.max(this.c / intrinsicWidth, this.d / intrinsicHeight);
            boolean z = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
            float f = intrinsicWidth * fMax;
            Matrix matrix = new Matrix();
            matrix.setTranslate((-intrinsicWidth) / 2.0f, (-intrinsicHeight) / 2.0f);
            float f2 = this.b;
            matrix.postScale(fMax * f2, fMax * f2);
            float f3 = 0.0f;
            int i2 = this.a;
            if (i2 == 0) {
                f3 = this.c / 2.0f;
            } else if (i2 == 1) {
                if (z) {
                    i = this.c;
                    f3 = i - (f / 2.0f);
                }
                f3 = f / 2.0f;
            } else if (i2 == 2) {
                if (!z) {
                    i = this.c;
                    f3 = i - (f / 2.0f);
                }
                f3 = f / 2.0f;
            }
            matrix.postTranslate(f3, this.d / 2.0f);
            setImageMatrix(matrix);
        }
    }

    @Override // android.widget.ImageView
    public final boolean setFrame(int i, int i2, int i3, int i4) {
        this.c = i3 - i;
        this.d = i4 - i2;
        a();
        return super.setFrame(i, i2, i3, i4);
    }

    public void setImageAdditionalScale(float f) {
        this.b = f;
        a();
    }

    public CropAlignedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CropAlignedImageView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public CropAlignedImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.b = 1.0f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CrossfadeImageView, i, i2);
        this.a = typedArrayObtainStyledAttributes.getInt(R$styleable.CrossfadeImageView_align_horizontal, 0);
        typedArrayObtainStyledAttributes.recycle();
        setScaleType(ImageView.ScaleType.MATRIX);
    }
}
