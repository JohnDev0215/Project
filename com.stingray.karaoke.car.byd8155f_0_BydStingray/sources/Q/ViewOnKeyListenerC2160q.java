package q;

import F0.C;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.stingray.drivemodedetector.R;
import r.i0;
import r.j0;

/* JADX INFO: renamed from: q.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnKeyListenerC2160q extends AbstractC2153j implements PopupWindow.OnDismissListener, View.OnKeyListener {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f21098A;
    public boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f21099C;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f21101E;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Context f21102m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final MenuC2151h f21103n;
    public final C2149f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f21104p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f21105q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f21106r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final j0 f21107s;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public C2154k f21110v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public View f21111w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public View f21112x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public InterfaceC2156m f21113y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public ViewTreeObserver f21114z;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final ViewTreeObserverOnGlobalLayoutListenerC2146c f21108t = new ViewTreeObserverOnGlobalLayoutListenerC2146c(this, 1);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C f21109u = new C(this, 4);

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public int f21100D = 0;

    public ViewOnKeyListenerC2160q(int i7, Context context, View view, MenuC2151h menuC2151h, boolean z6) {
        this.f21102m = context;
        this.f21103n = menuC2151h;
        this.f21104p = z6;
        this.o = new C2149f(menuC2151h, LayoutInflater.from(context), z6, R.layout.abc_popup_menu_item_layout);
        this.f21106r = i7;
        Resources resources = context.getResources();
        this.f21105q = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.f21111w = view;
        this.f21107s = new j0(context, i7);
        menuC2151h.b(this, context);
    }

    @Override // q.InterfaceC2157n
    public final void a(MenuC2151h menuC2151h, boolean z6) {
        if (menuC2151h != this.f21103n) {
            return;
        }
        dismiss();
        InterfaceC2156m interfaceC2156m = this.f21113y;
        if (interfaceC2156m != null) {
            interfaceC2156m.a(menuC2151h, z6);
        }
    }

    @Override // q.InterfaceC2157n
    public final boolean b(SubMenuC2161r subMenuC2161r) {
        if (subMenuC2161r.hasVisibleItems()) {
            C2155l c2155l = new C2155l(this.f21106r, this.f21102m, this.f21112x, subMenuC2161r, this.f21104p);
            InterfaceC2156m interfaceC2156m = this.f21113y;
            c2155l.f21094h = interfaceC2156m;
            AbstractC2153j abstractC2153j = c2155l.f21095i;
            if (abstractC2153j != null) {
                abstractC2153j.i(interfaceC2156m);
            }
            boolean zU = AbstractC2153j.u(subMenuC2161r);
            c2155l.f21093g = zU;
            AbstractC2153j abstractC2153j2 = c2155l.f21095i;
            if (abstractC2153j2 != null) {
                abstractC2153j2.o(zU);
            }
            c2155l.f21096j = this.f21110v;
            this.f21110v = null;
            this.f21103n.c(false);
            j0 j0Var = this.f21107s;
            int width = j0Var.f21509p;
            int i7 = !j0Var.f21511r ? 0 : j0Var.f21510q;
            if ((Gravity.getAbsoluteGravity(this.f21100D, this.f21111w.getLayoutDirection()) & 7) == 5) {
                width += this.f21111w.getWidth();
            }
            if (!c2155l.b()) {
                if (c2155l.f21091e != null) {
                    c2155l.d(width, i7, true, true);
                }
            }
            InterfaceC2156m interfaceC2156m2 = this.f21113y;
            if (interfaceC2156m2 != null) {
                interfaceC2156m2.v(subMenuC2161r);
            }
            return true;
        }
        return false;
    }

    @Override // q.InterfaceC2159p
    public final void c() {
        View view;
        if (j()) {
            return;
        }
        if (this.f21098A || (view = this.f21111w) == null) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
        this.f21112x = view;
        j0 j0Var = this.f21107s;
        j0Var.f21505G.setOnDismissListener(this);
        j0Var.f21517x = this;
        j0Var.f21504F = true;
        j0Var.f21505G.setFocusable(true);
        View view2 = this.f21112x;
        boolean z6 = this.f21114z == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.f21114z = viewTreeObserver;
        if (z6) {
            viewTreeObserver.addOnGlobalLayoutListener(this.f21108t);
        }
        view2.addOnAttachStateChangeListener(this.f21109u);
        j0Var.f21516w = view2;
        j0Var.f21514u = this.f21100D;
        boolean z10 = this.B;
        Context context = this.f21102m;
        C2149f c2149f = this.o;
        if (!z10) {
            this.f21099C = AbstractC2153j.m(c2149f, context, this.f21105q);
            this.B = true;
        }
        int i7 = this.f21099C;
        Drawable background = j0Var.f21505G.getBackground();
        if (background != null) {
            Rect rect = j0Var.f21502D;
            background.getPadding(rect);
            j0Var.o = rect.left + rect.right + i7;
        } else {
            j0Var.o = i7;
        }
        j0Var.f21505G.setInputMethodMode(2);
        Rect rect2 = this.l;
        j0Var.f21503E = rect2 != null ? new Rect(rect2) : null;
        j0Var.c();
        i0 i0Var = j0Var.f21508n;
        i0Var.setOnKeyListener(this);
        if (this.f21101E) {
            MenuC2151h menuC2151h = this.f21103n;
            if (menuC2151h.l != null) {
                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) i0Var, false);
                TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
                if (textView != null) {
                    textView.setText(menuC2151h.l);
                }
                frameLayout.setEnabled(false);
                i0Var.addHeaderView(frameLayout, null, false);
            }
        }
        j0Var.a(c2149f);
        j0Var.c();
    }

    @Override // q.InterfaceC2157n
    public final void d() {
        this.B = false;
        C2149f c2149f = this.o;
        if (c2149f != null) {
            c2149f.notifyDataSetChanged();
        }
    }

    @Override // q.InterfaceC2159p
    public final void dismiss() {
        if (j()) {
            this.f21107s.dismiss();
        }
    }

    @Override // q.InterfaceC2159p
    public final ListView e() {
        return this.f21107s.f21508n;
    }

    @Override // q.InterfaceC2157n
    public final boolean g() {
        return false;
    }

    @Override // q.InterfaceC2157n
    public final void i(InterfaceC2156m interfaceC2156m) {
        this.f21113y = interfaceC2156m;
    }

    @Override // q.InterfaceC2159p
    public final boolean j() {
        return !this.f21098A && this.f21107s.f21505G.isShowing();
    }

    @Override // q.AbstractC2153j
    public final void l(MenuC2151h menuC2151h) {
    }

    @Override // q.AbstractC2153j
    public final void n(View view) {
        this.f21111w = view;
    }

    @Override // q.AbstractC2153j
    public final void o(boolean z6) {
        this.o.c = z6;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.f21098A = true;
        this.f21103n.c(true);
        ViewTreeObserver viewTreeObserver = this.f21114z;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f21114z = this.f21112x.getViewTreeObserver();
            }
            this.f21114z.removeGlobalOnLayoutListener(this.f21108t);
            this.f21114z = null;
        }
        this.f21112x.removeOnAttachStateChangeListener(this.f21109u);
        C2154k c2154k = this.f21110v;
        if (c2154k != null) {
            c2154k.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i7, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i7 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // q.AbstractC2153j
    public final void p(int i7) {
        this.f21100D = i7;
    }

    @Override // q.AbstractC2153j
    public final void q(int i7) {
        this.f21107s.f21509p = i7;
    }

    @Override // q.AbstractC2153j
    public final void r(PopupWindow.OnDismissListener onDismissListener) {
        this.f21110v = (C2154k) onDismissListener;
    }

    @Override // q.AbstractC2153j
    public final void s(boolean z6) {
        this.f21101E = z6;
    }

    @Override // q.AbstractC2153j
    public final void t(int i7) {
        j0 j0Var = this.f21107s;
        j0Var.f21510q = i7;
        j0Var.f21511r = true;
    }
}
