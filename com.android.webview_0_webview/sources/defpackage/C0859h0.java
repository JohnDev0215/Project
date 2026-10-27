package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: renamed from: h0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0859h0 {
    public final ImageView a;
    public J0 b;
    public J0 c;

    public C0859h0(ImageView imageView) {
        this.a = imageView;
    }

    public void a() {
        Drawable drawable = this.a.getDrawable();
        if (drawable != null) {
            AbstractC1248o0.b(drawable);
        }
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            boolean z = true;
            if (i <= 21 && i == 21) {
                if (this.c == null) {
                    this.c = new J0();
                }
                J0 j0 = this.c;
                j0.a = null;
                j0.d = false;
                j0.b = null;
                j0.c = false;
                ColorStateList imageTintList = this.a.getImageTintList();
                if (imageTintList != null) {
                    j0.d = true;
                    j0.a = imageTintList;
                }
                PorterDuff.Mode imageTintMode = this.a.getImageTintMode();
                if (imageTintMode != null) {
                    j0.c = true;
                    j0.b = imageTintMode;
                }
                if (j0.d || j0.c) {
                    C0636d0.p(drawable, j0, this.a.getDrawableState());
                } else {
                    z = false;
                }
                if (z) {
                    return;
                }
            }
            J0 j02 = this.b;
            if (j02 != null) {
                C0636d0.p(drawable, j02, this.a.getDrawableState());
            }
        }
    }

    public void b(AttributeSet attributeSet, int i) {
        int i2;
        int i3 = Build.VERSION.SDK_INT;
        boolean z = false;
        L0 l0L = L0.l(this.a.getContext(), attributeSet, AbstractC0678dn.n, i, 0);
        try {
            Drawable drawable = this.a.getDrawable();
            if (drawable == null && (i2 = l0L.i(0, -1)) != -1 && (drawable = AbstractC1631v.b(this.a.getContext(), i2)) != null) {
                this.a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                AbstractC1248o0.b(drawable);
            }
            if (l0L.k(1)) {
                ImageView imageView = this.a;
                imageView.setImageTintList(l0L.c(1));
                if (i3 == 21) {
                    Drawable drawable2 = imageView.getDrawable();
                    boolean z2 = (imageView.getImageTintList() == null || imageView.getImageTintMode() == null) ? false : true;
                    if (drawable2 != null && z2) {
                        if (drawable2.isStateful()) {
                            drawable2.setState(imageView.getDrawableState());
                        }
                        imageView.setImageDrawable(drawable2);
                    }
                }
            }
            if (l0L.k(2)) {
                ImageView imageView2 = this.a;
                imageView2.setImageTintMode(AbstractC1248o0.c(l0L.h(2, -1), null));
                if (i3 == 21) {
                    Drawable drawable3 = imageView2.getDrawable();
                    if (imageView2.getImageTintList() != null && imageView2.getImageTintMode() != null) {
                        z = true;
                    }
                    if (drawable3 != null && z) {
                        if (drawable3.isStateful()) {
                            drawable3.setState(imageView2.getDrawableState());
                        }
                        imageView2.setImageDrawable(drawable3);
                    }
                }
            }
            l0L.b.recycle();
        } catch (Throwable th) {
            l0L.b.recycle();
            throw th;
        }
    }

    public void c(int i) {
        if (i != 0) {
            Drawable drawableB = AbstractC1631v.b(this.a.getContext(), i);
            if (drawableB != null) {
                AbstractC1248o0.b(drawableB);
            }
            this.a.setImageDrawable(drawableB);
        } else {
            this.a.setImageDrawable(null);
        }
        a();
    }
}
