package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class W {
    public final View a;
    public J0 d;
    public J0 e;
    public J0 f;
    public int c = -1;
    public final C0636d0 b = C0636d0.g();

    public W(View view) {
        this.a = view;
    }

    public void a() {
        Drawable background = this.a.getBackground();
        if (background != null) {
            int i = Build.VERSION.SDK_INT;
            boolean z = true;
            if (i <= 21 ? i == 21 : this.d != null) {
                if (this.f == null) {
                    this.f = new J0();
                }
                J0 j0 = this.f;
                j0.a = null;
                j0.d = false;
                j0.b = null;
                j0.c = false;
                ColorStateList colorStateListE = AbstractC1251o3.e(this.a);
                if (colorStateListE != null) {
                    j0.d = true;
                    j0.a = colorStateListE;
                }
                PorterDuff.Mode backgroundTintMode = this.a.getBackgroundTintMode();
                if (backgroundTintMode != null) {
                    j0.c = true;
                    j0.b = backgroundTintMode;
                }
                if (j0.d || j0.c) {
                    C0636d0.p(background, j0, this.a.getDrawableState());
                } else {
                    z = false;
                }
                if (z) {
                    return;
                }
            }
            J0 j02 = this.e;
            if (j02 != null) {
                C0636d0.p(background, j02, this.a.getDrawableState());
                return;
            }
            J0 j03 = this.d;
            if (j03 != null) {
                C0636d0.p(background, j03, this.a.getDrawableState());
            }
        }
    }

    public void b(AttributeSet attributeSet, int i) {
        L0 l0L = L0.l(this.a.getContext(), attributeSet, AbstractC0678dn.J0, i, 0);
        try {
            if (l0L.k(2)) {
                this.c = l0L.i(2, -1);
                ColorStateList colorStateListL = this.b.l(this.a.getContext(), this.c);
                if (colorStateListL != null) {
                    e(colorStateListL);
                }
            }
            if (l0L.k(0)) {
                AbstractC1251o3.D(this.a, l0L.c(0));
            }
            if (l0L.k(1)) {
                AbstractC1251o3.E(this.a, AbstractC1248o0.c(l0L.h(1, -1), null));
            }
        } finally {
            l0L.b.recycle();
        }
    }

    public void c() {
        this.c = -1;
        e(null);
        a();
    }

    public void d(int i) {
        this.c = i;
        C0636d0 c0636d0 = this.b;
        e(c0636d0 != null ? c0636d0.l(this.a.getContext(), i) : null);
        a();
    }

    public void e(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new J0();
            }
            J0 j0 = this.d;
            j0.a = colorStateList;
            j0.d = true;
        } else {
            this.d = null;
        }
        a();
    }
}
