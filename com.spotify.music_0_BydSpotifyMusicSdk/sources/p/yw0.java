package p;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class yw0 extends bk0 implements qp1, fq1, zp1, aq1, mu2, op1, x4, x62, rx0, ui1 {
    public final Activity q;
    public final Context r;
    public final Handler s;
    public final ox0 t;
    public final /* synthetic */ zw0 u;

    public yw0(t7 t7Var) {
        this.u = t7Var;
        Handler handler = new Handler();
        this.t = new ox0();
        this.q = t7Var;
        this.r = t7Var;
        this.s = handler;
    }

    @Override // p.bk0
    public final View O(int i) {
        return this.u.findViewById(i);
    }

    @Override // p.bk0
    public final boolean P() {
        Window window = this.u.getWindow();
        return (window == null || window.peekDecorView() == null) ? false : true;
    }

    public final void X(hx0 hx0Var) {
        kk0 kk0Var = this.u.k;
        ((CopyOnWriteArrayList) kk0Var.k).add(hx0Var);
        ((Runnable) kk0Var.e).run();
    }

    public final void Y(cz czVar) {
        this.u.s.add(czVar);
    }

    public final void Z(ex0 ex0Var) {
        this.u.v.add(ex0Var);
    }

    @Override // p.x62
    public final v62 a() {
        return this.u.m.b;
    }

    public final void a0(ex0 ex0Var) {
        this.u.w.add(ex0Var);
    }

    @Override // p.rx0
    public final void b() {
        this.u.getClass();
    }

    public final void b0(ex0 ex0Var) {
        this.u.t.add(ex0Var);
    }

    public final void c0(hx0 hx0Var) {
        this.u.o(hx0Var);
    }

    public final void d0(ex0 ex0Var) {
        this.u.p(ex0Var);
    }

    public final void e0(ex0 ex0Var) {
        this.u.q(ex0Var);
    }

    public final void f0(ex0 ex0Var) {
        this.u.r(ex0Var);
    }

    public final void g0(ex0 ex0Var) {
        this.u.s(ex0Var);
    }

    @Override // p.mu2
    public final lu2 j() {
        return this.u.j();
    }

    @Override // p.s91
    public final l91 l() {
        return this.u.A;
    }
}
