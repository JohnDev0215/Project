package q;

import android.view.View;
import android.view.ViewConfiguration;
import androidx.appcompat.view.menu.ActionMenuItemView;
import r.C2247f;
import r.C2248g;
import r.C2249h;
import r.RunnableC2233V;

/* JADX INFO: renamed from: q.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnTouchListenerC2144a implements View.OnTouchListener, View.OnAttachStateChangeListener {
    public final float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f21009m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f21010n;
    public final View o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public RunnableC2233V f21011p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public RunnableC2233V f21012q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f21013r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f21014s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int[] f21015t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f21016u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ View f21017v;

    public ViewOnTouchListenerC2144a(View view) {
        this.f21015t = new int[2];
        this.o = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.l = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f21009m = tapTimeout;
        this.f21010n = (ViewConfiguration.getLongPressTimeout() + tapTimeout) / 2;
    }

    public final void a() {
        RunnableC2233V runnableC2233V = this.f21012q;
        View view = this.o;
        if (runnableC2233V != null) {
            view.removeCallbacks(runnableC2233V);
        }
        RunnableC2233V runnableC2233V2 = this.f21011p;
        if (runnableC2233V2 != null) {
            view.removeCallbacks(runnableC2233V2);
        }
    }

    public final AbstractC2153j b() {
        C2247f c2247f;
        switch (this.f21016u) {
            case 0:
                AbstractC2145b abstractC2145b = ((ActionMenuItemView) this.f21017v).f11272w;
                if (abstractC2145b == null || (c2247f = ((C2248g) abstractC2145b).f21476a.f21479D) == null) {
                    return null;
                }
                return c2247f.a();
            default:
                C2247f c2247f2 = ((C2249h) this.f21017v).o.f21478C;
                if (c2247f2 == null) {
                    return null;
                }
                return c2247f2.a();
        }
    }

    public final boolean c() {
        AbstractC2153j abstractC2153jB;
        switch (this.f21016u) {
            case 0:
                ActionMenuItemView actionMenuItemView = (ActionMenuItemView) this.f21017v;
                InterfaceC2150g interfaceC2150g = actionMenuItemView.f11270u;
                return interfaceC2150g != null && interfaceC2150g.a(actionMenuItemView.f11267r) && (abstractC2153jB = b()) != null && abstractC2153jB.j();
            default:
                ((C2249h) this.f21017v).o.j();
                return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0124  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouch(android.view.View r13, android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: q.ViewOnTouchListenerC2144a.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.f21013r = false;
        this.f21014s = -1;
        RunnableC2233V runnableC2233V = this.f21011p;
        if (runnableC2233V != null) {
            this.o.removeCallbacks(runnableC2233V);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ViewOnTouchListenerC2144a(ActionMenuItemView actionMenuItemView) {
        this((View) actionMenuItemView);
        this.f21016u = 0;
        this.f21017v = actionMenuItemView;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ViewOnTouchListenerC2144a(C2249h c2249h, C2249h c2249h2) {
        this(c2249h2);
        this.f21016u = 1;
        this.f21017v = c2249h;
    }
}
