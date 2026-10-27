package defpackage;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import skin.support.R$styleable;

/* JADX INFO: compiled from: SkinCompatImageHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mo2 extends vd1 {
    public final ImageView k;
    public int l = 0;
    public int m = 0;
    public int n = 0;

    public mo2(ImageView imageView) {
        this.k = imageView;
    }

    public final void J() {
        Drawable drawableA;
        int iB = vd1.b(this.m);
        this.m = iB;
        ImageView imageView = this.k;
        if (iB != 0) {
            Drawable drawableA2 = xo2.a(imageView.getContext(), this.m);
            if (drawableA2 != null) {
                imageView.setImageDrawable(drawableA2);
            }
        } else {
            int iB2 = vd1.b(this.l);
            this.l = iB2;
            if (iB2 != 0 && (drawableA = xo2.a(imageView.getContext(), this.l)) != null) {
                imageView.setImageDrawable(drawableA);
            }
        }
        int iB3 = vd1.b(this.n);
        this.n = iB3;
        if (iB3 != 0) {
            imageView.setImageTintList(po2.b(imageView.getContext(), this.n));
        }
    }

    public final void K(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = null;
        try {
            typedArrayObtainStyledAttributes = this.k.getContext().obtainStyledAttributes(attributeSet, R$styleable.SkinCompatImageView, i, 0);
            this.l = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SkinCompatImageView_android_src, 0);
            this.m = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SkinCompatImageView_srcCompat, 0);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SkinCompatImageView_tint, 0);
            this.n = resourceId;
            if (resourceId == 0) {
                this.n = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SkinCompatImageView_android_tint, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
            J();
        } catch (Throwable th) {
            if (typedArrayObtainStyledAttributes != null) {
                typedArrayObtainStyledAttributes.recycle();
            }
            throw th;
        }
    }
}
