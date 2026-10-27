package defpackage;

import android.annotation.TargetApi;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import skin.support.R$styleable;

/* JADX INFO: compiled from: SkinCompatTextHelperV17.java */
/* JADX INFO: loaded from: classes3.dex */
@TargetApi(17)
public final class to2 extends so2 {
    public int r;
    public int s;

    public to2(TextView textView) {
        super(textView);
        this.r = 0;
        this.s = 0;
    }

    @Override // defpackage.so2
    public final void J() {
        int iB = vd1.b(this.o);
        this.o = iB;
        TextView textView = this.k;
        Drawable drawableA = iB != 0 ? xo2.a(textView.getContext(), this.o) : null;
        int iB2 = vd1.b(this.q);
        this.q = iB2;
        Drawable drawableA2 = iB2 != 0 ? xo2.a(textView.getContext(), this.q) : null;
        int iB3 = vd1.b(this.p);
        this.p = iB3;
        Drawable drawableA3 = iB3 != 0 ? xo2.a(textView.getContext(), this.p) : null;
        int iB4 = vd1.b(this.n);
        this.n = iB4;
        Drawable drawableA4 = iB4 != 0 ? xo2.a(textView.getContext(), this.n) : null;
        Drawable drawableA5 = this.r != 0 ? xo2.a(textView.getContext(), this.r) : null;
        if (drawableA5 != null) {
            drawableA = drawableA5;
        }
        Drawable drawableA6 = this.s != 0 ? xo2.a(textView.getContext(), this.s) : null;
        if (drawableA6 != null) {
            drawableA3 = drawableA6;
        }
        if (this.o == 0 && this.q == 0 && this.p == 0 && this.n == 0 && this.r == 0 && this.s == 0) {
            return;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(drawableA, drawableA2, drawableA3, drawableA4);
    }

    @Override // defpackage.so2
    public final void M(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = this.k.getContext().obtainStyledAttributes(attributeSet, R$styleable.SkinCompatTextHelper, i, 0);
        int i2 = R$styleable.SkinCompatTextHelper_android_drawableStart;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
            this.r = resourceId;
            this.r = vd1.b(resourceId);
        }
        int i3 = R$styleable.SkinCompatTextHelper_android_drawableEnd;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(i3, 0);
            this.s = resourceId2;
            this.s = vd1.b(resourceId2);
        }
        typedArrayObtainStyledAttributes.recycle();
        super.M(attributeSet, i);
    }

    public final void P(int i, int i2, int i3, int i4) {
        this.r = i;
        this.q = i2;
        this.s = i3;
        this.n = i4;
        J();
    }
}
