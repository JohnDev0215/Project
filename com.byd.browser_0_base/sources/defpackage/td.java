package defpackage;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$styleable;

/* JADX INFO: compiled from: BaseProgressIndicatorSpec.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class td {
    public int a;
    public int b;
    public int[] c;
    public int d;
    public int e;
    public int f;

    public td(Context context, AttributeSet attributeSet, int i, int i2) {
        this.c = new int[0];
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.mtrl_progress_track_thickness);
        TypedArray typedArrayD = r83.d(context, attributeSet, R$styleable.BaseProgressIndicator, i, i2, new int[0]);
        this.a = rc1.c(context, typedArrayD, R$styleable.BaseProgressIndicator_trackThickness, dimensionPixelSize);
        this.b = Math.min(rc1.c(context, typedArrayD, R$styleable.BaseProgressIndicator_trackCornerRadius, 0), this.a / 2);
        this.e = typedArrayD.getInt(R$styleable.BaseProgressIndicator_showAnimationBehavior, 0);
        this.f = typedArrayD.getInt(R$styleable.BaseProgressIndicator_hideAnimationBehavior, 0);
        int i3 = R$styleable.BaseProgressIndicator_indicatorColor;
        if (!typedArrayD.hasValue(i3)) {
            this.c = new int[]{oj2.o(context, R$attr.colorPrimary, -1)};
        } else if (typedArrayD.peekValue(i3).type != 1) {
            this.c = new int[]{typedArrayD.getColor(i3, -1)};
        } else {
            int[] intArray = context.getResources().getIntArray(typedArrayD.getResourceId(i3, -1));
            this.c = intArray;
            if (intArray.length == 0) {
                throw new IllegalArgumentException("indicatorColors cannot be empty when indicatorColor is not used.");
            }
        }
        int i4 = R$styleable.BaseProgressIndicator_trackColor;
        if (typedArrayD.hasValue(i4)) {
            this.d = typedArrayD.getColor(i4, -1);
        } else {
            this.d = this.c[0];
            TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.disabledAlpha});
            float f = typedArrayObtainStyledAttributes.getFloat(0, 0.2f);
            typedArrayObtainStyledAttributes.recycle();
            this.d = oj2.e(this.d, (int) (f * 255.0f));
        }
        typedArrayD.recycle();
    }

    public abstract void a();
}
