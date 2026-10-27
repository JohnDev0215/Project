package defpackage;

import defpackage.n60;
import java.util.ArrayList;

/* JADX INFO: compiled from: ListMutationController.java */
/* JADX INFO: loaded from: classes.dex */
public final class r81 {
    public final g81 a;
    public final n60 b;
    public final v60 c;
    public final d60 d;
    public final n60.a e;
    public final x60 f;
    public final b81 g;
    public final n60.a h;
    public final h81 i;
    public final gj3 j;
    public int k = 0;
    public final a l = new a();

    /* JADX INFO: compiled from: ListMutationController.java */
    public class a implements b81 {
        public a() {
        }

        @Override // defpackage.b81
        public final b81 a(b81 b81Var) {
            return null;
        }

        @Override // defpackage.b81
        public final void b(ArrayList arrayList) {
            r81 r81Var = r81.this;
            r81Var.a.A(arrayList);
            r81Var.a.g.d();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [n60$a] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [n60$a] */
    /* JADX WARN: Type inference failed for: r1v6 */
    public r81(wc0 wc0Var, um0 um0Var, n60 n60Var, g81 g81Var) {
        this.b = n60Var;
        this.a = g81Var;
        b81 bVar = new b();
        boolean z = wc0Var.f;
        this.e = z ? new e60() : bVar;
        this.h = z ? new r32() : bVar;
        gj3 gj3Var = new gj3(2);
        this.j = gj3Var;
        this.c = new v60(um0Var);
        this.d = new d60(um0Var);
        this.f = new x60();
        this.g = wc0Var.e ? new jt0(gj3Var) : bVar;
        this.i = new h81(wc0Var);
        a();
        n60Var.a();
    }

    public final void a() {
        boolean z = this.k == 7;
        b81 b81Var = z ? this.f : this.c;
        b81 b81Var2 = z ? this.g : this.d;
        n60.a aVar = z ? this.h : this.e;
        this.b.g = b81Var;
        b81Var.a(b81Var2).a(aVar).a(this.i).a(this.l);
    }

    /* JADX INFO: compiled from: ListMutationController.java */
    public static class b implements b81, n60.a {
        public b81 a;

        @Override // defpackage.b81
        public final b81 a(b81 b81Var) {
            this.a = b81Var;
            return b81Var;
        }

        @Override // defpackage.b81
        public final void b(ArrayList arrayList) {
            b81 b81Var = this.a;
            if (b81Var == null) {
                return;
            }
            b81Var.b(arrayList);
        }

        @Override // n60.a
        public final void c() {
        }

        @Override // n60.a
        public final void reset() {
        }
    }
}
