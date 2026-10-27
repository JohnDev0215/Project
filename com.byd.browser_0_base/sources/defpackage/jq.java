package defpackage;

import android.os.Handler;
import defpackage.bi1;
import defpackage.kj;
import defpackage.l61;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.messages.MessageContainer;

/* JADX INFO: compiled from: ChromeMessageQueueMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jq implements eh1, vj3 {
    public lb1 e;
    public sg1 f;
    public fj g;
    public int h = -1;
    public final d i;
    public l61 j;
    public z2 k;
    public bi1 l;
    public q2 m;
    public final ll n;
    public int o;
    public Handler p;
    public final a q;
    public final b r;
    public final c s;

    /* JADX INFO: compiled from: ChromeMessageQueueMediator.java */
    public class a implements l61.a {
        public int e = -1;

        public a() {
        }

        @Override // l61.a
        public final void g(int i, boolean z) {
            if (this.e != -1 || i == 1) {
                return;
            }
            this.e = jq.this.f();
        }

        @Override // l61.a
        public final void h(int i) {
            int i2 = this.e;
            if (i2 == -1 || i != 1) {
                return;
            }
            ((org.chromium.components.messages.a) jq.this.e).e.a.c(i2);
            this.e = -1;
        }
    }

    /* JADX INFO: compiled from: ChromeMessageQueueMediator.java */
    public class b implements bi1.a {
        public int e = -1;

        public b() {
        }

        @Override // bi1.a
        public final void d() {
            int i = this.e;
            if (i != -1) {
                ((org.chromium.components.messages.a) jq.this.e).e.a.c(i);
                this.e = -1;
            }
        }

        @Override // bi1.a
        public final void e() {
            if (this.e == -1) {
                this.e = jq.this.f();
            }
        }
    }

    /* JADX INFO: compiled from: ChromeMessageQueueMediator.java */
    public class c implements hy1 {
        public int e = -1;

        public c() {
        }

        @Override // defpackage.hy1
        public final void f() {
            int i = this.e;
            if (i != -1) {
                ((org.chromium.components.messages.a) jq.this.e).e.a.c(i);
                this.e = -1;
            }
        }

        @Override // defpackage.hy1
        public final void h() {
            if (this.e == -1) {
                this.e = jq.this.f();
            }
        }
    }

    /* JADX INFO: compiled from: ChromeMessageQueueMediator.java */
    public class d implements kj.a {
        public Runnable e;

        public d() {
        }

        @Override // kj.a
        public final void c(boolean z, int i, int i2, int i3) {
            if (this.e == null || !a8.g(jq.this.g)) {
                return;
            }
            this.e.run();
            this.e = null;
        }
    }

    public jq(fj fjVar, sg1 sg1Var, z2 z2Var, qs1 qs1Var, zp1 zp1Var, q2 q2Var, org.chromium.components.messages.a aVar) {
        ll llVar = new ll();
        this.n = llVar;
        this.o = -1;
        this.q = new a();
        this.r = new b();
        c cVar = new c();
        this.s = cVar;
        this.g = fjVar;
        this.f = sg1Var;
        this.e = aVar;
        this.k = z2Var;
        d dVar = new d();
        this.i = dVar;
        fjVar.o(dVar);
        qs1Var.h(llVar.b(new dm0(14, this)));
        zp1Var.p(new td2(4, this));
        this.m = q2Var;
        q2Var.f(cVar);
        this.p = new Handler();
    }

    public final void a() {
        MessageContainer messageContainer;
        sg1 sg1Var = this.f;
        if (sg1Var == null || (messageContainer = sg1Var.e) == null) {
            return;
        }
        ao3.f(messageContainer, true);
    }

    public final void b() {
        MessageContainer messageContainer;
        sg1 sg1Var = this.f;
        if (sg1Var == null || (messageContainer = sg1Var.e) == null) {
            return;
        }
        ao3.f(messageContainer, false);
    }

    public final void c() {
        fj fjVar = this.g;
        if (fjVar == null) {
            return;
        }
        fjVar.f.u(this.h);
        this.h = -1;
        sg1 sg1Var = this.f;
        sg1Var.e.setVisibility(8);
        Iterator<tg1> it = sg1Var.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                this.i.e = null;
                return;
            }
            ((tg1) aVar.next()).a();
        }
    }

    public final void d(Runnable runnable) {
        fj fjVar = this.g;
        if (fjVar == null) {
            return;
        }
        this.h = fjVar.f.v();
        sg1 sg1Var = this.f;
        sg1Var.e.setVisibility(0);
        sg1Var.b();
        Iterator<tg1> it = sg1Var.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            } else {
                ((tg1) aVar.next()).b();
            }
        }
        Tab tab = (Tab) this.k.f;
        boolean z = ((tab == null || py2.a(tab) == null) ? 3 : py2.a(tab).b()) == 2 || a8.g(this.g);
        d dVar = this.i;
        if (!z) {
            dVar.e = runnable;
        } else {
            dVar.e = null;
            runnable.run();
        }
    }

    public final void e(l61 l61Var) {
        l61 l61Var2 = this.j;
        a aVar = this.q;
        if (l61Var2 != null) {
            l61Var2.f(aVar);
        }
        this.j = l61Var;
        if (l61Var == null) {
            return;
        }
        if (this.e == null) {
            throw new IllegalStateException("setLayoutStateProvider() is called after destroy()");
        }
        l61Var.j(aVar);
    }

    public final int f() {
        return ((org.chromium.components.messages.a) this.e).e.a.a();
    }

    @Override // defpackage.vj3
    public final void k(boolean z) {
        if (!z) {
            this.p.postDelayed(new ql0(14, this), 1000L);
            return;
        }
        if (this.o == -1) {
            this.o = f();
        }
        this.p.removeCallbacksAndMessages(null);
    }
}
