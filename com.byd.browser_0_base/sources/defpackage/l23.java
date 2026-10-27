package defpackage;

import java.util.Iterator;
import org.chromium.base.b;

/* JADX INFO: compiled from: TabObscuringHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l23 {
    public final org.chromium.base.b<a> c = new org.chromium.base.b<>();
    public final mb3 a = new mb3(new d92(6, this));
    public final mb3 b = new mb3(new zx1(15, this));

    /* JADX INFO: compiled from: TabObscuringHandler.java */
    public interface a {
        void r(boolean z, boolean z2);
    }

    /* JADX INFO: compiled from: TabObscuringHandler.java */
    public static final class b {
        public final int a;
        public final int b;

        public b(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public static void a(l23 l23Var) {
        Iterator<a> it = l23Var.c.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).r(l23Var.c(), l23Var.b.b());
            }
        }
    }

    public final void b(a aVar) {
        this.c.a(aVar);
    }

    public final boolean c() {
        return this.a.b() || this.b.b();
    }

    public final b d(int i) {
        return new b(i, i != 1 ? i != 2 ? -1 : this.b.a() : this.a.a());
    }

    public final void e(a aVar) {
        this.c.c(aVar);
    }

    public final void f(b bVar) {
        int i = bVar.a;
        int i2 = bVar.b;
        if (i == 1) {
            this.a.c(i2);
        } else {
            if (i != 2) {
                return;
            }
            this.b.c(i2);
        }
    }
}
