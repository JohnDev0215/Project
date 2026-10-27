package r;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;

/* JADX INFO: loaded from: classes.dex */
public final class A0 implements InterfaceC2224L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Toolbar f21408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f21409b;
    public View c;
    public Drawable d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f21410e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f21411f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f21412g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CharSequence f21413h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f21414i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f21415j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Window.Callback f21416k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Drawable f21417m;

    public final void a(int i7) {
        View view;
        int i10 = this.f21409b ^ i7;
        this.f21409b = i7;
        if (i10 != 0) {
            if ((i10 & 4) != 0) {
                if ((i7 & 4) != 0) {
                    b();
                }
                int i11 = this.f21409b & 4;
                Toolbar toolbar = this.f21408a;
                if (i11 != 0) {
                    Drawable drawable = this.f21411f;
                    if (drawable == null) {
                        drawable = this.f21417m;
                    }
                    toolbar.setNavigationIcon(drawable);
                } else {
                    toolbar.setNavigationIcon((Drawable) null);
                }
            }
            if ((i10 & 3) != 0) {
                c();
            }
            int i12 = i10 & 8;
            Toolbar toolbar2 = this.f21408a;
            if (i12 != 0) {
                if ((i7 & 8) != 0) {
                    toolbar2.setTitle(this.f21413h);
                    toolbar2.setSubtitle(this.f21414i);
                } else {
                    toolbar2.setTitle((CharSequence) null);
                    toolbar2.setSubtitle((CharSequence) null);
                }
            }
            if ((i10 & 16) == 0 || (view = this.c) == null) {
                return;
            }
            if ((i7 & 16) != 0) {
                toolbar2.addView(view);
            } else {
                toolbar2.removeView(view);
            }
        }
    }

    public final void b() {
        if ((this.f21409b & 4) != 0) {
            boolean zIsEmpty = TextUtils.isEmpty(this.f21415j);
            Toolbar toolbar = this.f21408a;
            if (zIsEmpty) {
                toolbar.setNavigationContentDescription(this.l);
            } else {
                toolbar.setNavigationContentDescription(this.f21415j);
            }
        }
    }

    public final void c() {
        Drawable drawable;
        int i7 = this.f21409b;
        if ((i7 & 2) == 0) {
            drawable = null;
        } else if ((i7 & 1) == 0 || (drawable = this.f21410e) == null) {
            drawable = this.d;
        }
        this.f21408a.setLogo(drawable);
    }
}
