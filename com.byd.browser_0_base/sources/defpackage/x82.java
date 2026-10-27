package defpackage;

import java.io.Serializable;

/* JADX INFO: compiled from: Random.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class x82 {
    public static final a e = new a(0);
    public static final wj0 f;

    /* JADX INFO: compiled from: Random.kt */
    public static final class a extends x82 implements Serializable {

        /* JADX INFO: renamed from: x82$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Random.kt */
        public static final class C0170a implements Serializable {
            public static final C0170a e = new C0170a();
            private static final long serialVersionUID = 0;

            private final Object readResolve() {
                return x82.e;
            }
        }

        public a(int i) {
        }

        private final Object writeReplace() {
            return C0170a.e;
        }

        public final int a() {
            return x82.f.a();
        }
    }

    static {
        p12.a.getClass();
        f = new wj0();
    }
}
