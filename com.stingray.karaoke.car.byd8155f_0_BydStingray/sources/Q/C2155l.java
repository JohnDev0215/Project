package q;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import com.stingray.drivemodedetector.R;

/* JADX INFO: renamed from: q.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2155l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f21089a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MenuC2151h f21090b;
    public final boolean c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f21091e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f21093g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public InterfaceC2156m f21094h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public AbstractC2153j f21095i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public C2154k f21096j;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f21092f = 8388611;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C2154k f21097k = new C2154k(this);

    public C2155l(int i7, Context context, View view, MenuC2151h menuC2151h, boolean z6) {
        this.f21089a = context;
        this.f21090b = menuC2151h;
        this.f21091e = view;
        this.c = z6;
        this.d = i7;
    }

    public final AbstractC2153j a() {
        AbstractC2153j viewOnKeyListenerC2160q;
        if (this.f21095i == null) {
            Context context = this.f21089a;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            if (Math.min(point.x, point.y) >= context.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width)) {
                viewOnKeyListenerC2160q = new ViewOnKeyListenerC2148e(context, this.f21091e, this.d, this.c);
            } else {
                View view = this.f21091e;
                Context context2 = this.f21089a;
                boolean z6 = this.c;
                viewOnKeyListenerC2160q = new ViewOnKeyListenerC2160q(this.d, context2, view, this.f21090b, z6);
            }
            viewOnKeyListenerC2160q.l(this.f21090b);
            viewOnKeyListenerC2160q.r(this.f21097k);
            viewOnKeyListenerC2160q.n(this.f21091e);
            viewOnKeyListenerC2160q.i(this.f21094h);
            viewOnKeyListenerC2160q.o(this.f21093g);
            viewOnKeyListenerC2160q.p(this.f21092f);
            this.f21095i = viewOnKeyListenerC2160q;
        }
        return this.f21095i;
    }

    public final boolean b() {
        AbstractC2153j abstractC2153j = this.f21095i;
        return abstractC2153j != null && abstractC2153j.j();
    }

    public void c() {
        this.f21095i = null;
        C2154k c2154k = this.f21096j;
        if (c2154k != null) {
            c2154k.onDismiss();
        }
    }

    public final void d(int i7, int i10, boolean z6, boolean z10) {
        AbstractC2153j abstractC2153jA = a();
        abstractC2153jA.s(z10);
        if (z6) {
            if ((Gravity.getAbsoluteGravity(this.f21092f, this.f21091e.getLayoutDirection()) & 7) == 5) {
                i7 -= this.f21091e.getWidth();
            }
            abstractC2153jA.q(i7);
            abstractC2153jA.t(i10);
            int i11 = (int) ((this.f21089a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            abstractC2153jA.l = new Rect(i7 - i11, i10 - i11, i7 + i11, i10 + i11);
        }
        abstractC2153jA.c();
    }
}
