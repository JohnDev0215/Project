package q3;

import android.annotation.SuppressLint;
import h3.e;
import h3.g;
import h3.i;
import h3.k;

/* JADX INFO: compiled from: VividLogger.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f9159a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private g f9160b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private g f9161c;

    /* JADX INFO: compiled from: VividLogger.java */
    class a extends h3.c {
        a(e eVar) {
            super(eVar);
        }

        @Override // h3.f
        public boolean b(int i7, String str) {
            return d.this.f9159a;
        }
    }

    /* JADX INFO: compiled from: VividLogger.java */
    class b extends h3.a {
        b(e eVar) {
            super(eVar);
        }

        @Override // h3.f
        public boolean b(int i7, String str) {
            return d.this.f9159a;
        }
    }

    /* JADX INFO: compiled from: VividLogger.java */
    private static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @SuppressLint({"StaticFieldLeak"})
        private static final d f9164a = new d(null);
    }

    /* synthetic */ d(a aVar) {
        this();
    }

    public static d b() {
        return c.f9164a;
    }

    private void d(int i7) {
        this.f9161c = new q3.b(i7);
        i.a(new b(k.k().e(false).c(0).d(0).b(this.f9161c).f("").a()));
    }

    private void e(int i7, String str) {
        this.f9160b = new q3.c(i7, str);
        i.a(new a(h3.b.c().b(this.f9160b).a()));
    }

    public void c(q3.a aVar) {
        if (aVar.c()) {
            d(aVar.b());
        }
        if (aVar.d()) {
            e(aVar.b(), aVar.a());
        }
    }

    private d() {
        this.f9159a = true;
    }
}
