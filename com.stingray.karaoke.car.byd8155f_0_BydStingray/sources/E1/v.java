package e1;

import F0.X0;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.C;
import androidx.lifecycle.EnumC0980s;
import androidx.lifecycle.a0;
import com.stingray.drivemodedetector.R;
import java.util.UUID;
import r.C2257p;
import x.AbstractC2641j;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class v extends Dialog implements androidx.lifecycle.A, h.y, t3.e {
    public C l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final t4.e f13811m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final h.x f13812n;
    public InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public t f13813p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final View f13814q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final s f13815r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f13816s;

    /* JADX WARN: Illegal instructions before constructor call */
    public v(InterfaceC2772a interfaceC2772a, t tVar, View view, a1.k kVar, a1.b bVar, UUID uuid) {
        Context context = view.getContext();
        int i7 = Build.VERSION.SDK_INT;
        if (i7 < 31) {
            tVar.getClass();
        }
        super(new ContextThemeWrapper(context, R.style.DialogWindowTheme), 0);
        this.f13811m = new t4.e(this);
        this.f13812n = new h.x(new A2.l(this, 13));
        this.o = interfaceC2772a;
        this.f13813p = tVar;
        this.f13814q = view;
        float f10 = 8;
        Window window = getWindow();
        if (window == null) {
            throw new IllegalStateException("Dialog has no window");
        }
        this.f13816s = window.getAttributes().softInputMode & 240;
        window.requestFeature(1);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        this.f13813p.getClass();
        if (i7 >= 35) {
            B1.a.e(window);
        } else if (i7 >= 30) {
            B1.a.d(window);
        } else {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (-1793));
        }
        s sVar = new s(getContext(), window);
        sVar.setTag(R.id.compose_view_saveable_id_tag, "Dialog:" + uuid);
        sVar.setClipChildren(false);
        sVar.setElevation(bVar.y(f10));
        sVar.setOutlineProvider(new X0(1));
        this.f13815r = sVar;
        View decorView2 = window.getDecorView();
        ViewGroup viewGroup = decorView2 instanceof ViewGroup ? (ViewGroup) decorView2 : null;
        if (viewGroup != null) {
            d(viewGroup);
        }
        setContentView(sVar);
        a0.j(sVar, a0.f(view));
        a0.k(sVar, a0.g(view));
        h6.q.z(sVar, h6.q.s(view));
        h(this.o, this.f13813p, kVar);
        com.bumptech.glide.d.q(this.f13812n, this, new C1189b(this, 1));
    }

    public static void c(v vVar) {
        A7.m.f(vVar, "this$0");
        super.onBackPressed();
    }

    public static final void d(ViewGroup viewGroup) {
        viewGroup.setClipChildren(false);
        if (viewGroup instanceof s) {
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = viewGroup.getChildAt(i7);
            ViewGroup viewGroup2 = childAt instanceof ViewGroup ? (ViewGroup) childAt : null;
            if (viewGroup2 != null) {
                d(viewGroup2);
            }
        }
    }

    @Override // h.y
    public final h.x a() {
        return this.f13812n;
    }

    @Override // android.app.Dialog
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        A7.m.f(view, "view");
        f();
        super.addContentView(view, layoutParams);
    }

    @Override // t3.e
    public final C2257p b() {
        return (C2257p) this.f13811m.o;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void cancel() {
    }

    public final C e() {
        C c = this.l;
        if (c != null) {
            return c;
        }
        C c10 = new C(this);
        this.l = c10;
        return c10;
    }

    public final void f() {
        Window window = getWindow();
        A7.m.c(window);
        View decorView = window.getDecorView();
        A7.m.e(decorView, "window!!.decorView");
        a0.j(decorView, this);
        Window window2 = getWindow();
        A7.m.c(window2);
        View decorView2 = window2.getDecorView();
        A7.m.e(decorView2, "window!!.decorView");
        decorView2.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, this);
        Window window3 = getWindow();
        A7.m.c(window3);
        View decorView3 = window3.getDecorView();
        A7.m.e(decorView3, "window!!.decorView");
        h6.q.z(decorView3, this);
    }

    @Override // androidx.lifecycle.A
    public final A9.b g() {
        return e();
    }

    public final void h(InterfaceC2772a interfaceC2772a, t tVar, a1.k kVar) {
        Window window;
        Window window2;
        this.o = interfaceC2772a;
        this.f13813p = tVar;
        tVar.getClass();
        boolean zC = m.c(this.f13814q);
        int i7 = 1;
        int iC = AbstractC2641j.c(1);
        if (iC != 0) {
            if (iC == 1) {
                zC = true;
            } else {
                if (iC != 2) {
                    throw new A9.k();
                }
                zC = false;
            }
        }
        Window window3 = getWindow();
        A7.m.c(window3);
        window3.setFlags(zC ? 8192 : -8193, 8192);
        int iOrdinal = kVar.ordinal();
        if (iOrdinal == 0) {
            i7 = 0;
        } else if (iOrdinal != 1) {
            throw new A9.k();
        }
        s sVar = this.f13815r;
        sVar.setLayoutDirection(i7);
        boolean z6 = tVar.c;
        if (z6 && !sVar.f13807v && (window2 = getWindow()) != null) {
            window2.setLayout(-2, -2);
        }
        sVar.f13807v = z6;
        if (Build.VERSION.SDK_INT >= 31 || (window = getWindow()) == null) {
            return;
        }
        window.setSoftInputMode(this.f13816s);
    }

    @Override // android.app.Dialog
    public final void onBackPressed() {
        this.f13812n.c();
    }

    @Override // android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher onBackInvokedDispatcher = getOnBackInvokedDispatcher();
            A7.m.e(onBackInvokedDispatcher, "onBackInvokedDispatcher");
            h.x xVar = this.f13812n;
            xVar.getClass();
            xVar.f15146e = onBackInvokedDispatcher;
            xVar.d(xVar.f15148g);
        }
        this.f13811m.l(bundle);
        e().c1(EnumC0980s.ON_CREATE);
    }

    @Override // android.app.Dialog
    public final Bundle onSaveInstanceState() {
        Bundle bundleOnSaveInstanceState = super.onSaveInstanceState();
        A7.m.e(bundleOnSaveInstanceState, "super.onSaveInstanceState()");
        this.f13811m.m(bundleOnSaveInstanceState);
        return bundleOnSaveInstanceState;
    }

    @Override // android.app.Dialog
    public final void onStart() {
        super.onStart();
        e().c1(EnumC0980s.ON_RESUME);
    }

    @Override // android.app.Dialog
    public final void onStop() {
        e().c1(EnumC0980s.ON_DESTROY);
        this.l = null;
        super.onStop();
    }

    @Override // android.app.Dialog
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
        if (zOnTouchEvent && this.f13813p.f13810b) {
            this.o.a();
        }
        return zOnTouchEvent;
    }

    @Override // android.app.Dialog
    public final void setContentView(int i7) {
        f();
        super.setContentView(i7);
    }

    @Override // android.app.Dialog
    public final void setContentView(View view) {
        A7.m.f(view, "view");
        f();
        super.setContentView(view);
    }

    @Override // android.app.Dialog
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        A7.m.f(view, "view");
        f();
        super.setContentView(view, layoutParams);
    }
}
