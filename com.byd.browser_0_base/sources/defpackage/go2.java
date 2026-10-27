package defpackage;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import defpackage.om3;
import java.util.WeakHashMap;
import skin.support.R$styleable;

/* JADX INFO: compiled from: SkinCompatBackgroundHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class go2 extends vd1 {
    public final View k;
    public int l = 0;

    public go2(View view) {
        this.k = view;
    }

    public final void J() {
        int iB = vd1.b(this.l);
        this.l = iB;
        if (iB == 0) {
            return;
        }
        View view = this.k;
        Drawable drawableA = xo2.a(view.getContext(), this.l);
        if (drawableA != null) {
            int paddingLeft = view.getPaddingLeft();
            int paddingTop = view.getPaddingTop();
            int paddingRight = view.getPaddingRight();
            int paddingBottom = view.getPaddingBottom();
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.n(view, drawableA);
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
    }

    public final void K(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = this.k.getContext().obtainStyledAttributes(attributeSet, R$styleable.SkinBackgroundHelper, i, 0);
        try {
            int i2 = R$styleable.SkinBackgroundHelper_android_background;
            if (typedArrayObtainStyledAttributes.hasValue(i2)) {
                this.l = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
            J();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void L(int i) {
        this.l = i;
        J();
    }
}
