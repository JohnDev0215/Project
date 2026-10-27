package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.PopupWindow;
import java.util.Objects;

/* JADX INFO: renamed from: nP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewOnTouchListenerC1218nP implements View.OnTouchListener, InterfaceC1273oP {
    public final Context c;
    public final Handler d;
    public final View e;
    public final PopupWindow f;
    public final AbstractC1328pP g;
    public boolean j;
    public InterfaceC1163mP l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public final Rect a = new Rect();
    public final Rect b = new Rect();
    public final Runnable h = new RunnableC1051kP(this);
    public final PopupWindow.OnDismissListener i = new C1107lP(this);
    public Ys k = new Ys();
    public int r = 0;
    public int s = 0;

    public ViewOnTouchListenerC1218nP(Context context, View view, Drawable drawable, View view2, AbstractC1328pP abstractC1328pP) {
        this.c = context;
        this.e = view.getRootView();
        C1657vP c1657vPA = C1657vP.a();
        Context context2 = this.c;
        Objects.requireNonNull(c1657vPA);
        this.f = new PopupWindow(context2);
        this.d = new Handler();
        this.g = abstractC1328pP;
        this.f.setWidth(-2);
        this.f.setHeight(-2);
        this.f.setBackgroundDrawable(drawable);
        this.f.setContentView(view2);
        this.f.setTouchInterceptor(this);
        this.f.setOnDismissListener(this.i);
    }

    public void a() {
        if (this.f.isShowing()) {
            return;
        }
        ViewTreeObserverOnGlobalLayoutListenerC1712wP viewTreeObserverOnGlobalLayoutListenerC1712wP = (ViewTreeObserverOnGlobalLayoutListenerC1712wP) this.g;
        viewTreeObserverOnGlobalLayoutListenerC1712wP.w.addOnAttachStateChangeListener(viewTreeObserverOnGlobalLayoutListenerC1712wP);
        ViewTreeObserver viewTreeObserver = viewTreeObserverOnGlobalLayoutListenerC1712wP.w.getViewTreeObserver();
        viewTreeObserverOnGlobalLayoutListenerC1712wP.x = viewTreeObserver;
        viewTreeObserver.addOnGlobalLayoutListener(viewTreeObserverOnGlobalLayoutListenerC1712wP);
        viewTreeObserverOnGlobalLayoutListenerC1712wP.x.addOnPreDrawListener(viewTreeObserverOnGlobalLayoutListenerC1712wP);
        viewTreeObserverOnGlobalLayoutListenerC1712wP.b();
        viewTreeObserverOnGlobalLayoutListenerC1712wP.t = this;
        b();
        try {
            this.f.showAtLocation(this.e, 8388659, this.m, this.n);
        } catch (WindowManager.BadTokenException unused) {
        }
    }

    public final void b() {
        int iWidth;
        int i;
        boolean z = this.u;
        boolean z2 = this.v;
        boolean z3 = this.f.isShowing() && !this.y;
        this.f.getBackground().getPadding(this.a);
        Rect rect = this.a;
        int i2 = rect.left + rect.right;
        int i3 = rect.top + rect.bottom;
        int i4 = this.q;
        int width = this.e.getWidth() - 0;
        if (i4 == 0 || i4 >= width) {
            i4 = width;
        }
        int i5 = i4 > i2 ? i4 - i2 : 0;
        View contentView = this.f.getContentView();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE);
        contentView.measure(iMakeMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredHeight = contentView.getMeasuredHeight();
        int measuredWidth = contentView.getMeasuredWidth();
        this.e.getWindowVisibleDisplayFrame(this.b);
        int[] iArr = new int[2];
        this.e.getLocationOnScreen(iArr);
        this.b.offset(-iArr[0], -iArr[1]);
        Rect rect2 = this.g.s;
        int i6 = this.w ? rect2.bottom : rect2.top;
        Rect rect3 = this.b;
        int i7 = ((i6 - rect3.top) - i3) - 0;
        int i8 = ((rect3.bottom - (this.w ? rect2.top : rect2.bottom)) - i3) - 0;
        boolean z4 = measuredHeight <= i8;
        boolean z5 = measuredHeight <= i7;
        boolean z6 = (z4 && i8 >= i7) || !z5;
        this.u = z6;
        if (z3 && z != z6) {
            if (z && z4) {
                this.u = true;
            }
            if (!z && z5) {
                this.u = false;
            }
        }
        if (this.s == 0) {
            int i9 = (this.x ? rect2.right : rect2.left) - this.b.left;
            int i10 = this.b.right - (this.x ? rect2.left : rect2.right);
            int i11 = measuredWidth + i3 + 0;
            boolean z7 = i9 >= i10;
            if (z3 && z7 != z2) {
                if (z2 && i11 <= i9) {
                    z7 = true;
                }
                if (!z2 && i11 <= i10) {
                    z7 = false;
                }
            }
            this.v = z7;
        }
        if (this.u) {
            i7 = i8;
        }
        contentView.measure(iMakeMeasureSpec, View.MeasureSpec.makeMeasureSpec(i7, Integer.MIN_VALUE));
        this.o = contentView.getMeasuredWidth() + i2;
        this.p = contentView.getMeasuredHeight() + i3;
        Rect rect4 = this.b;
        int i12 = this.o;
        boolean z8 = this.x;
        int i13 = this.s;
        boolean z9 = this.v;
        if (i13 == 1) {
            iWidth = ((rect2.width() - i12) / 2) + rect2.left + 0;
        } else if (z9) {
            iWidth = (z8 ? rect2.right : rect2.left) - i12;
        } else {
            iWidth = z8 ? rect2.left : rect2.right;
        }
        int i14 = (rect4.right - i12) + 0;
        int i15 = i14 < 0 ? i14 : 0;
        if (i14 < 0) {
            i14 = 0;
        }
        if (iWidth < i15) {
            iWidth = i15;
        } else if (iWidth > i14) {
            iWidth = i14;
        }
        this.m = iWidth;
        int i16 = this.p;
        boolean z10 = this.w;
        if (this.u) {
            i = z10 ? rect2.top : rect2.bottom;
        } else {
            i = (z10 ? rect2.bottom : rect2.top) - i16;
        }
        this.n = i;
        InterfaceC1163mP interfaceC1163mP = this.l;
        if (interfaceC1163mP != null) {
            C1600uN c1600uN = (C1600uN) interfaceC1163mP;
            c1600uN.l.setBounds(rect2);
            c1600uN.g.f.setBackgroundDrawable(AbstractC1623us.c(c1600uN.a.getResources(), AbstractC0317Rm.Z1));
        }
        if (this.f.isShowing() && this.u != z) {
            try {
                this.t = true;
                this.f.dismiss();
                try {
                    this.f.showAtLocation(this.e, 8388659, this.m, this.n);
                } catch (WindowManager.BadTokenException unused) {
                }
            } finally {
                this.t = false;
            }
        }
        this.f.update(this.m, this.n, this.o, this.p);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.j) {
            this.f.dismiss();
        }
        return false;
    }
}
