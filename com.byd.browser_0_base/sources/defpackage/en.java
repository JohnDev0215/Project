package defpackage;

import defpackage.qn;

/* JADX INFO: compiled from: ChildConnectionAllocator.java */
/* JADX INFO: loaded from: classes.dex */
public final class en implements qn.e {
    public final /* synthetic */ qn.e a;
    public final /* synthetic */ gn b;

    /* JADX INFO: compiled from: ChildConnectionAllocator.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            en.this.a.c();
        }
    }

    /* JADX INFO: compiled from: ChildConnectionAllocator.java */
    public class b implements Runnable {
        public final /* synthetic */ qn e;

        public b(qn qnVar) {
            this.e = qnVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            en.this.a.b(this.e);
        }
    }

    /* JADX INFO: compiled from: ChildConnectionAllocator.java */
    public class c implements Runnable {
        public final /* synthetic */ qn e;

        public c(qn qnVar) {
            this.e = qnVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            en.this.a.a(this.e);
        }
    }

    public en(gn gnVar, qn.e eVar) {
        this.b = gnVar;
        this.a = eVar;
    }

    @Override // qn.e
    public final void a(qn qnVar) {
        qn.e eVar = this.a;
        gn gnVar = this.b;
        if (eVar != null) {
            gnVar.c.post(new c(qnVar));
        }
        gnVar.c.postDelayed(new fn(this, qnVar), 1L);
    }

    @Override // qn.e
    public final void b(qn qnVar) {
        qn.e eVar = this.a;
        gn gnVar = this.b;
        if (eVar != null) {
            gnVar.c.post(new b(qnVar));
        }
        gnVar.c.postDelayed(new fn(this, qnVar), 1L);
    }

    @Override // qn.e
    public final void c() {
        if (this.a != null) {
            this.b.c.post(new a());
        }
    }
}
