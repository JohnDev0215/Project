package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Q0 implements InterfaceC1193n0 {
    public Toolbar a;
    public int b;
    public View c;
    public Drawable d;
    public Drawable e;
    public Drawable f;
    public CharSequence g;
    public CharSequence h;
    public CharSequence i;
    public int j;
    public Drawable k;

    public Q0(Toolbar toolbar, boolean z) {
        Drawable drawable;
        int i = AbstractC0566bn.b;
        this.j = 0;
        this.a = toolbar;
        this.g = toolbar.f16J;
        this.h = toolbar.K;
        this.f = toolbar.l();
        L0 l0L = L0.l(toolbar.getContext(), null, AbstractC0678dn.a, AbstractC0245Nm.f, 0);
        int i2 = 15;
        this.k = l0L.f(15);
        if (z) {
            CharSequence charSequenceJ = l0L.j(27);
            if (!TextUtils.isEmpty(charSequenceJ)) {
                this.g = charSequenceJ;
                if ((this.b & 8) != 0) {
                    this.a.x(charSequenceJ);
                }
            }
            CharSequence charSequenceJ2 = l0L.j(25);
            if (!TextUtils.isEmpty(charSequenceJ2)) {
                this.h = charSequenceJ2;
                if ((this.b & 8) != 0) {
                    this.a.w(charSequenceJ2);
                }
            }
            Drawable drawableF = l0L.f(20);
            if (drawableF != null) {
                this.e = drawableF;
                d();
            }
            Drawable drawableF2 = l0L.f(17);
            if (drawableF2 != null) {
                this.d = drawableF2;
                d();
            }
            if (this.f == null && (drawable = this.k) != null) {
                this.f = drawable;
                c();
            }
            a(l0L.h(10, 0));
            int i3 = l0L.i(9, 0);
            if (i3 != 0) {
                View viewInflate = LayoutInflater.from(this.a.getContext()).inflate(i3, (ViewGroup) this.a, false);
                View view = this.c;
                if (view != null && (this.b & 16) != 0) {
                    this.a.removeView(view);
                }
                this.c = viewInflate;
                if (viewInflate != null && (this.b & 16) != 0) {
                    this.a.addView(viewInflate);
                }
                a(this.b | 16);
            }
            int layoutDimension = l0L.b.getLayoutDimension(13, 0);
            if (layoutDimension > 0) {
                ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
                layoutParams.height = layoutDimension;
                this.a.setLayoutParams(layoutParams);
            }
            int iD = l0L.d(7, -1);
            int iD2 = l0L.d(3, -1);
            if (iD >= 0 || iD2 >= 0) {
                Toolbar toolbar2 = this.a;
                int iMax = Math.max(iD, 0);
                int iMax2 = Math.max(iD2, 0);
                toolbar2.c();
                toolbar2.F.a(iMax, iMax2);
            }
            int i4 = l0L.i(28, 0);
            if (i4 != 0) {
                Toolbar toolbar3 = this.a;
                Context context = toolbar3.getContext();
                toolbar3.x = i4;
                TextView textView = toolbar3.s;
                if (textView != null) {
                    textView.setTextAppearance(context, i4);
                }
            }
            int i5 = l0L.i(26, 0);
            if (i5 != 0) {
                Toolbar toolbar4 = this.a;
                Context context2 = toolbar4.getContext();
                toolbar4.y = i5;
                TextView textView2 = toolbar4.t;
                if (textView2 != null) {
                    textView2.setTextAppearance(context2, i5);
                }
            }
            int i6 = l0L.i(22, 0);
            if (i6 != 0) {
                this.a.v(i6);
            }
        } else {
            if (this.a.l() != null) {
                this.k = this.a.l();
            } else {
                i2 = 11;
            }
            this.b = i2;
        }
        l0L.b.recycle();
        if (i != this.j) {
            this.j = i;
            ImageButton imageButton = this.a.u;
            if (TextUtils.isEmpty(imageButton != null ? imageButton.getContentDescription() : null)) {
                int i7 = this.j;
                this.i = i7 == 0 ? null : this.a.getContext().getString(i7);
                b();
            }
        }
        ImageButton imageButton2 = this.a.u;
        this.i = imageButton2 != null ? imageButton2.getContentDescription() : null;
        Toolbar toolbar5 = this.a;
        P0 p0 = new P0(this);
        toolbar5.d();
        toolbar5.u.setOnClickListener(p0);
    }

    public void a(int i) {
        View view;
        int i2 = this.b ^ i;
        this.b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    b();
                }
                c();
            }
            if ((i2 & 3) != 0) {
                d();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.a.x(this.g);
                    this.a.w(this.h);
                } else {
                    this.a.x(null);
                    this.a.w(null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.c) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.a.addView(view);
            } else {
                this.a.removeView(view);
            }
        }
    }

    public final void b() {
        if ((this.b & 4) != 0) {
            if (!TextUtils.isEmpty(this.i)) {
                this.a.t(this.i);
                return;
            }
            Toolbar toolbar = this.a;
            int i = this.j;
            toolbar.t(i != 0 ? toolbar.getContext().getText(i) : null);
        }
    }

    public final void c() {
        if ((this.b & 4) == 0) {
            this.a.u(null);
            return;
        }
        Toolbar toolbar = this.a;
        Drawable drawable = this.f;
        if (drawable == null) {
            drawable = this.k;
        }
        toolbar.u(drawable);
    }

    public final void d() {
        Drawable drawable;
        int i = this.b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.e) == null) {
            drawable = this.d;
        }
        this.a.s(drawable);
    }
}
