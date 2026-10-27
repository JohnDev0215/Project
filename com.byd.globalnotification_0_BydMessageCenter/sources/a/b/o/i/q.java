package a.b.o.i;

import a.b.o.i.m;
import a.b.p.f0;
import a.b.p.m0;
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class q extends k implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, m, View.OnKeyListener {
    public static final int v = a.b.g.abc_popup_menu_item_layout;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f211b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g f212c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f f213d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f214e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f215f;
    public final int g;
    public final int h;
    public final m0 i;
    public PopupWindow.OnDismissListener l;
    public View m;
    public View n;
    public m.a o;
    public ViewTreeObserver p;
    public boolean q;
    public boolean r;
    public int s;
    public boolean u;
    public final ViewTreeObserver.OnGlobalLayoutListener j = new a();
    public final View.OnAttachStateChangeListener k = new b();
    public int t = 0;

    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (q.this.a()) {
                q qVar = q.this;
                if (qVar.i.A) {
                    return;
                }
                View view = qVar.n;
                if (view == null || !view.isShown()) {
                    q.this.dismiss();
                } else {
                    q.this.i.i();
                }
            }
        }
    }

    public class b implements View.OnAttachStateChangeListener {
        public b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = q.this.p;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    q.this.p = view.getViewTreeObserver();
                }
                q qVar = q.this;
                qVar.p.removeGlobalOnLayoutListener(qVar.j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public q(Context context, g gVar, View view, int i, int i2, boolean z) {
        this.f211b = context;
        this.f212c = gVar;
        this.f214e = z;
        this.f213d = new f(gVar, LayoutInflater.from(context), this.f214e, v);
        this.g = i;
        this.h = i2;
        Resources resources = context.getResources();
        this.f215f = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(a.b.d.abc_config_prefDialogWidth));
        this.m = view;
        this.i = new m0(this.f211b, null, this.g, this.h);
        gVar.b(this, context);
    }

    @Override // a.b.o.i.p
    public boolean a() {
        return !this.q && this.i.a();
    }

    @Override // a.b.o.i.m
    public void b(g gVar, boolean z) {
        if (gVar != this.f212c) {
            return;
        }
        dismiss();
        m.a aVar = this.o;
        if (aVar != null) {
            aVar.b(gVar, z);
        }
    }

    @Override // a.b.o.i.m
    public void d(m.a aVar) {
        this.o = aVar;
    }

    @Override // a.b.o.i.p
    public void dismiss() {
        if (a()) {
            this.i.dismiss();
        }
    }

    @Override // a.b.o.i.p
    public ListView e() {
        return this.i.f305c;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006e  */
    @Override // a.b.o.i.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean f(a.b.o.i.r r10) {
        /*
            r9 = this;
            boolean r0 = r10.hasVisibleItems()
            r1 = 0
            if (r0 == 0) goto L76
            a.b.o.i.l r0 = new a.b.o.i.l
            android.content.Context r3 = r9.f211b
            android.view.View r5 = r9.n
            boolean r6 = r9.f214e
            int r7 = r9.g
            int r8 = r9.h
            r2 = r0
            r4 = r10
            r2.<init>(r3, r4, r5, r6, r7, r8)
            a.b.o.i.m$a r2 = r9.o
            r0.d(r2)
            boolean r2 = a.b.o.i.k.v(r10)
            r0.h = r2
            a.b.o.i.k r3 = r0.j
            if (r3 == 0) goto L2a
            r3.p(r2)
        L2a:
            android.widget.PopupWindow$OnDismissListener r2 = r9.l
            r0.k = r2
            r2 = 0
            r9.l = r2
            a.b.o.i.g r2 = r9.f212c
            r2.c(r1)
            a.b.p.m0 r2 = r9.i
            int r3 = r2.f308f
            boolean r4 = r2.i
            if (r4 != 0) goto L40
            r2 = r1
            goto L42
        L40:
            int r2 = r2.g
        L42:
            int r4 = r9.t
            android.view.View r5 = r9.m
            int r5 = a.h.k.q.p(r5)
            int r4 = android.view.Gravity.getAbsoluteGravity(r4, r5)
            r4 = r4 & 7
            r5 = 5
            if (r4 != r5) goto L5a
            android.view.View r4 = r9.m
            int r4 = r4.getWidth()
            int r3 = r3 + r4
        L5a:
            boolean r4 = r0.b()
            r5 = 1
            if (r4 == 0) goto L62
            goto L6b
        L62:
            android.view.View r4 = r0.f208f
            if (r4 != 0) goto L68
            r0 = r1
            goto L6c
        L68:
            r0.e(r3, r2, r5, r5)
        L6b:
            r0 = r5
        L6c:
            if (r0 == 0) goto L76
            a.b.o.i.m$a r9 = r9.o
            if (r9 == 0) goto L75
            r9.c(r10)
        L75:
            return r5
        L76:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.o.i.q.f(a.b.o.i.r):boolean");
    }

    @Override // a.b.o.i.m
    public boolean g() {
        return false;
    }

    @Override // a.b.o.i.m
    public void h(boolean z) {
        this.r = false;
        f fVar = this.f213d;
        if (fVar != null) {
            fVar.notifyDataSetChanged();
        }
    }

    @Override // a.b.o.i.p
    public void i() {
        View view;
        boolean z = true;
        if (!a()) {
            if (this.q || (view = this.m) == null) {
                z = false;
            } else {
                this.n = view;
                this.i.B.setOnDismissListener(this);
                m0 m0Var = this.i;
                m0Var.s = this;
                m0Var.s(true);
                View view2 = this.n;
                boolean z2 = this.p == null;
                ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
                this.p = viewTreeObserver;
                if (z2) {
                    viewTreeObserver.addOnGlobalLayoutListener(this.j);
                }
                view2.addOnAttachStateChangeListener(this.k);
                m0 m0Var2 = this.i;
                m0Var2.r = view2;
                m0Var2.l = this.t;
                if (!this.r) {
                    this.s = k.n(this.f213d, null, this.f211b, this.f215f);
                    this.r = true;
                }
                this.i.r(this.s);
                this.i.B.setInputMethodMode(2);
                m0 m0Var3 = this.i;
                Rect rect = this.f202a;
                if (m0Var3 == null) {
                    throw null;
                }
                m0Var3.z = rect != null ? new Rect(rect) : null;
                this.i.i();
                f0 f0Var = this.i.f305c;
                f0Var.setOnKeyListener(this);
                if (this.u && this.f212c.m != null) {
                    FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f211b).inflate(a.b.g.abc_popup_menu_header_item_layout, (ViewGroup) f0Var, false);
                    TextView textView = (TextView) frameLayout.findViewById(R.id.title);
                    if (textView != null) {
                        textView.setText(this.f212c.m);
                    }
                    frameLayout.setEnabled(false);
                    f0Var.addHeaderView(frameLayout, null, false);
                }
                this.i.o(this.f213d);
                this.i.i();
            }
        }
        if (!z) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // a.b.o.i.k
    public void l(g gVar) {
    }

    @Override // a.b.o.i.k
    public void o(View view) {
        this.m = view;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.q = true;
        this.f212c.c(true);
        ViewTreeObserver viewTreeObserver = this.p;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.p = this.n.getViewTreeObserver();
            }
            this.p.removeGlobalOnLayoutListener(this.j);
            this.p = null;
        }
        this.n.removeOnAttachStateChangeListener(this.k);
        PopupWindow.OnDismissListener onDismissListener = this.l;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // a.b.o.i.k
    public void p(boolean z) {
        this.f213d.f172c = z;
    }

    @Override // a.b.o.i.k
    public void q(int i) {
        this.t = i;
    }

    @Override // a.b.o.i.k
    public void r(int i) {
        this.i.f308f = i;
    }

    @Override // a.b.o.i.k
    public void s(PopupWindow.OnDismissListener onDismissListener) {
        this.l = onDismissListener;
    }

    @Override // a.b.o.i.k
    public void t(boolean z) {
        this.u = z;
    }

    @Override // a.b.o.i.k
    public void u(int i) {
        m0 m0Var = this.i;
        m0Var.g = i;
        m0Var.i = true;
    }
}
