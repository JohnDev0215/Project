package t5;

import java.io.Serializable;
import kotlin.jvm.internal.g;

/* JADX INFO: compiled from: Random.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f9560e = new a(null);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final c f9561g = m5.b.f8405a.b();

    /* JADX INFO: compiled from: Random.kt */
    public static final class a extends c implements Serializable {

        /* JADX INFO: renamed from: t5.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Random.kt */
        private static final class C0159a implements Serializable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public static final C0159a f9562e = new C0159a();
            private static final long serialVersionUID = 0;

            private C0159a() {
            }

            private final Object readResolve() {
                return c.f9560e;
            }
        }

        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        private final Object writeReplace() {
            return C0159a.f9562e;
        }

        @Override // t5.c
        public int b() {
            return c.f9561g.b();
        }
    }

    public abstract int b();
}
