package g0;

import E0.AbstractC0156f;
import E0.InterfaceC0163m;
import E0.d0;
import E0.g0;
import S8.AbstractC0727y;
import S8.C0722t;
import S8.InterfaceC0725w;
import S8.W;
import S8.Y;
import T.Q;
import y.C2688I;

/* JADX INFO: loaded from: classes.dex */
public abstract class n implements InterfaceC0163m {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public X8.c f14685m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14686n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public n f14687p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public n f14688q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public g0 f14689r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public d0 f14690s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f14691t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f14692u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f14693v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f14694w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f14695x;
    public n l = this;
    public int o = -1;

    public void A0() {
    }

    public void B0() {
    }

    public void C0() {
        if (this.f14695x) {
            B0();
        } else {
            l9.d.h0("reset() called on an unattached node");
            throw null;
        }
    }

    public void D0() {
        if (!this.f14695x) {
            l9.d.h0("Must run markAsAttached() prior to runAttachLifecycle");
            throw null;
        }
        if (!this.f14693v) {
            l9.d.h0("Must run runAttachLifecycle() only once after markAsAttached()");
            throw null;
        }
        this.f14693v = false;
        z0();
        this.f14694w = true;
    }

    public void E0() {
        if (!this.f14695x) {
            l9.d.h0("node detached multiple times");
            throw null;
        }
        if (this.f14690s == null) {
            l9.d.h0("detach invoked on a node without a coordinator");
            throw null;
        }
        if (!this.f14694w) {
            l9.d.h0("Must run runDetachLifecycle() once after runAttachLifecycle() and before markAsDetached()");
            throw null;
        }
        this.f14694w = false;
        A0();
    }

    public void F0(n nVar) {
        this.l = nVar;
    }

    public void G0(d0 d0Var) {
        this.f14690s = d0Var;
    }

    public final InterfaceC0725w v0() {
        X8.c cVar = this.f14685m;
        if (cVar != null) {
            return cVar;
        }
        X8.c cVarA = AbstractC0727y.a(AbstractC0156f.u(this).getCoroutineContext().v(new Y((W) AbstractC0156f.u(this).getCoroutineContext().F(C0722t.f8137m))));
        this.f14685m = cVarA;
        return cVarA;
    }

    public boolean w0() {
        return !(this instanceof C2688I);
    }

    public void x0() {
        if (this.f14695x) {
            l9.d.h0("node attached multiple times");
            throw null;
        }
        if (this.f14690s == null) {
            l9.d.h0("attach invoked on a node without a coordinator");
            throw null;
        }
        this.f14695x = true;
        this.f14693v = true;
    }

    public void y0() {
        if (!this.f14695x) {
            l9.d.h0("Cannot detach a node that is not attached");
            throw null;
        }
        if (this.f14693v) {
            l9.d.h0("Must run runAttachLifecycle() before markAsDetached()");
            throw null;
        }
        if (this.f14694w) {
            l9.d.h0("Must run runDetachLifecycle() before markAsDetached()");
            throw null;
        }
        this.f14695x = false;
        X8.c cVar = this.f14685m;
        if (cVar != null) {
            AbstractC0727y.d(cVar, new Q("The Modifier.Node was detached", 1));
            this.f14685m = null;
        }
    }

    public void z0() {
    }
}
