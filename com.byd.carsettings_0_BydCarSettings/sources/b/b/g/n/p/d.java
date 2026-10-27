package b.b.g.n.p;

import b.b.g.m.d.i;
import b.b.g.p.h;
import b.b.m.e.g;
import java.util.List;

/* JADX INFO: compiled from: PermissionPagerPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends g<b.b.g.q.f.c.b> {

    /* JADX INFO: compiled from: PermissionPagerPresenter.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1248a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1249b;

        public a(int i, int i2) {
            this.f1248a = i;
            this.f1249b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<b.b.g.m.d.d<i>> listN = h.n(((b.b.g.q.f.c.b) d.this.getV()).getContext(), false);
            if (d.this.getV() != null) {
                ((b.b.g.q.f.c.b) d.this.getV()).sendMessage(this.f1248a, this.f1249b, listN);
            }
        }
    }

    /* JADX INFO: compiled from: PermissionPagerPresenter.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i f1251a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f1252b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f1253c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f1254d;

        public b(i iVar, boolean z, int i, int i2) {
            this.f1251a = iVar;
            this.f1252b = z;
            this.f1253c = i;
            this.f1254d = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            h.v(((b.b.g.q.f.c.b) d.this.getV()).getContext(), this.f1251a.d(), this.f1251a.e(), this.f1251a.c(), this.f1252b);
            if (d.this.getV() != null) {
                ((b.b.g.q.f.c.b) d.this.getV()).sendMessageDelay(this.f1253c, this.f1254d, 0L, null);
            }
        }
    }

    public void i(int i, int i2) {
        b.b.a.f.h.a().j(new a(i, i2));
    }

    public void j(i iVar, boolean z, int i, int i2) {
        b.b.m.g.b.a(new b(iVar, z, i, i2));
    }
}
