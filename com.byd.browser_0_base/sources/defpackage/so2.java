package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import skin.support.R$styleable;

/* JADX INFO: compiled from: SkinCompatTextHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public class so2 extends vd1 {
    public final TextView k;
    public int l = 0;
    public int m = 0;
    public int n = 0;
    public int o = 0;
    public int p = 0;
    public int q = 0;

    public so2(TextView textView) {
        this.k = textView;
    }

    public void J() {
        throw null;
    }

    public final void K() {
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
        if (this.o == 0 && this.q == 0 && this.p == 0 && this.n == 0) {
            return;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(drawableA, drawableA2, drawableA3, drawableA4);
    }

    public final void L() {
        J();
        int iB = vd1.b(this.l);
        this.l = iB;
        TextView textView = this.k;
        if (iB != 0) {
            try {
                textView.setTextColor(po2.b(textView.getContext(), this.l));
            } catch (Exception unused) {
            }
        }
        int iB2 = vd1.b(this.m);
        this.m = iB2;
        if (iB2 != 0) {
            try {
                textView.setHintTextColor(po2.b(textView.getContext(), this.m));
            } catch (Exception unused2) {
            }
        }
    }

    public void M(AttributeSet attributeSet, int i) {
        Context context = this.k.getContext();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SkinCompatTextHelper, i, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SkinCompatTextHelper_android_textAppearance, 0);
        int i2 = R$styleable.SkinCompatTextHelper_android_drawableLeft;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            this.o = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
        }
        int i3 = R$styleable.SkinCompatTextHelper_android_drawableTop;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            this.q = typedArrayObtainStyledAttributes.getResourceId(i3, 0);
        }
        int i4 = R$styleable.SkinCompatTextHelper_android_drawableRight;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            this.p = typedArrayObtainStyledAttributes.getResourceId(i4, 0);
        }
        int i5 = R$styleable.SkinCompatTextHelper_android_drawableBottom;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            this.n = typedArrayObtainStyledAttributes.getResourceId(i5, 0);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, R$styleable.SkinTextAppearance);
            int i6 = R$styleable.SkinTextAppearance_android_textColor;
            if (typedArrayObtainStyledAttributes2.hasValue(i6)) {
                this.l = typedArrayObtainStyledAttributes2.getResourceId(i6, 0);
            }
            int i7 = R$styleable.SkinTextAppearance_android_textColorHint;
            if (typedArrayObtainStyledAttributes2.hasValue(i7)) {
                this.m = typedArrayObtainStyledAttributes2.getResourceId(i7, 0);
            }
            typedArrayObtainStyledAttributes2.recycle();
        }
        TypedArray typedArrayObtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, R$styleable.SkinTextAppearance, i, 0);
        int i8 = R$styleable.SkinTextAppearance_android_textColor;
        if (typedArrayObtainStyledAttributes3.hasValue(i8)) {
            this.l = typedArrayObtainStyledAttributes3.getResourceId(i8, 0);
        }
        int i9 = R$styleable.SkinTextAppearance_android_textColorHint;
        if (typedArrayObtainStyledAttributes3.hasValue(i9)) {
            this.m = typedArrayObtainStyledAttributes3.getResourceId(i9, 0);
        }
        typedArrayObtainStyledAttributes3.recycle();
        L();
    }

    public final void N(int i, int i2, int i3, int i4) {
        this.o = i;
        this.q = i2;
        this.p = i3;
        this.n = i4;
        K();
    }

    public final void O(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.SkinTextAppearance);
        int i2 = R$styleable.SkinTextAppearance_android_textColor;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            this.l = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
        }
        int i3 = R$styleable.SkinTextAppearance_android_textColorHint;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            this.m = typedArrayObtainStyledAttributes.getResourceId(i3, 0);
        }
        typedArrayObtainStyledAttributes.recycle();
        int iB = vd1.b(this.l);
        this.l = iB;
        TextView textView = this.k;
        if (iB != 0) {
            try {
                textView.setTextColor(po2.b(textView.getContext(), this.l));
            } catch (Exception unused) {
            }
        }
        int iB2 = vd1.b(this.m);
        this.m = iB2;
        if (iB2 != 0) {
            try {
                textView.setHintTextColor(po2.b(textView.getContext(), this.m));
            } catch (Exception unused2) {
            }
        }
    }
}
