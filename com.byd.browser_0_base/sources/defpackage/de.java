package defpackage;

/* JADX INFO: compiled from: BatchListModel.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class de<T> extends p81<T> {
    public final ee g = new ee(new a());

    /* JADX INFO: compiled from: BatchListModel.java */
    public class a implements z81 {
        public a() {
        }

        @Override // defpackage.z81
        public final void a(int i, int i2) {
            de.C(de.this, i, i2);
        }

        @Override // defpackage.z81
        public final void b(int i, int i2) {
            de.D(de.this, i, i2);
        }

        @Override // defpackage.z81
        public final void c(int i, int i2, Object obj) {
            de.B(de.this, i, i2);
        }
    }

    public static void B(de deVar, int i, int i2) {
        super.q(i, i2, null);
    }

    public static void C(de deVar, int i, int i2) {
        super.r(i, i2);
    }

    public static void D(de deVar, int i, int i2) {
        super.s(i, i2);
    }

    @Override // org.chromium.ui.modelutil.e
    public final void q(int i, int i2, Object obj) {
        this.g.c(i, i2, null);
    }

    @Override // org.chromium.ui.modelutil.e
    public final void r(int i, int i2) {
        this.g.a(i, i2);
    }

    @Override // org.chromium.ui.modelutil.e
    public final void s(int i, int i2) {
        this.g.b(i, i2);
    }
}
