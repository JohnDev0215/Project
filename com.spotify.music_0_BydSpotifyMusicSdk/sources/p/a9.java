package p;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public final class a9 {
    public final ImageView a;
    public xl2 b;
    public int c = 0;

    public a9(ImageView imageView) {
        this.a = imageView;
    }

    public final void a() {
        xl2 xl2Var;
        ImageView imageView = this.a;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            cb0.a(drawable);
        }
        if (drawable == null || (xl2Var = this.b) == null) {
            return;
        }
        u8.d(drawable, xl2Var, imageView.getDrawableState());
    }

    public final void b(AttributeSet attributeSet, int i) {
        int iY;
        ImageView imageView = this.a;
        Context context = imageView.getContext();
        int[] iArr = yz1.f;
        v4 v4VarJ = v4.J(context, attributeSet, iArr, i);
        zt2.j(imageView, imageView.getContext(), iArr, attributeSet, (TypedArray) v4VarJ.k, i);
        try {
            Drawable drawable = imageView.getDrawable();
            if (drawable == null && (iY = v4VarJ.y(1, -1)) != -1 && (drawable = l13.f(imageView.getContext(), iY)) != null) {
                imageView.setImageDrawable(drawable);
            }
            if (drawable != null) {
                cb0.a(drawable);
            }
            if (v4VarJ.G(2)) {
                t31.c(imageView, v4VarJ.q(2));
            }
            if (v4VarJ.G(3)) {
                t31.d(imageView, cb0.b(v4VarJ.w(3, -1), null));
            }
            v4VarJ.L();
        } catch (Throwable th) {
            v4VarJ.L();
            throw th;
        }
    }
}
