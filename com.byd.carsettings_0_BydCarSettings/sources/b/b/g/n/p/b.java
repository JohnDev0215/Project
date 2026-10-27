package b.b.g.n.p;

import b.b.g.p.h;
import b.b.m.e.g;
import java.util.List;

/* JADX INFO: compiled from: AppListPagerPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends g<b.b.g.q.f.c.a> {

    /* JADX INFO: compiled from: AppListPagerPresenter.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1238a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1239b;

        public a(int i, int i2) {
            this.f1238a = i;
            this.f1239b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<b.b.g.m.d.g> listG = h.g(((b.b.g.q.f.c.a) b.this.getV()).getContext(), false);
            if (b.this.getV() != null) {
                ((b.b.g.q.f.c.a) b.this.getV()).sendMessage(this.f1238a, this.f1239b, listG);
            }
        }
    }

    /* JADX INFO: renamed from: b.b.g.n.p.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppListPagerPresenter.java */
    public class RunnableC0057b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f1241a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ b.b.g.m.d.c f1242b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f1243c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f1244d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f1245e;

        public RunnableC0057b(String str, b.b.g.m.d.c cVar, boolean z, int i, int i2) {
            this.f1241a = str;
            this.f1242b = cVar;
            this.f1243c = z;
            this.f1244d = i;
            this.f1245e = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            h.v(((b.b.g.q.f.c.a) b.this.getV()).getContext(), this.f1241a, this.f1242b.e(), this.f1242b.c(), this.f1243c);
            if (b.this.getV() != null) {
                ((b.b.g.q.f.c.a) b.this.getV()).sendMessageDelay(this.f1244d, this.f1245e, 0L, null);
            }
        }
    }

    public void i(int i, int i2) {
        b.b.a.f.h.a().e(new a(i, i2), 200L);
    }

    public void j(String str, b.b.g.m.d.c cVar, boolean z, int i, int i2) {
        b.b.m.g.b.a(new RunnableC0057b(str, cVar, z, i, i2));
    }
}
