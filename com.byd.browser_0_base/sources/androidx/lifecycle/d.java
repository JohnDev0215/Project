package androidx.lifecycle;

import defpackage.a71;
import defpackage.i21;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: Lifecycle.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: compiled from: Lifecycle.kt */
    public enum a {
        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
        ON_ANY;

        public static final C0011a Companion = new C0011a();

        /* JADX INFO: renamed from: androidx.lifecycle.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Lifecycle.kt */
        public static final class C0011a {
            public static a a(b bVar) {
                i21.e(bVar, "state");
                int iOrdinal = bVar.ordinal();
                if (iOrdinal == 1) {
                    return a.ON_CREATE;
                }
                if (iOrdinal == 2) {
                    return a.ON_START;
                }
                if (iOrdinal != 3) {
                    return null;
                }
                return a.ON_RESUME;
            }
        }

        /* JADX INFO: compiled from: Lifecycle.kt */
        public /* synthetic */ class b {
            public static final /* synthetic */ int[] a;

            static {
                int[] iArr = new int[a.values().length];
                try {
                    iArr[a.ON_CREATE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[a.ON_STOP.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[a.ON_START.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[a.ON_PAUSE.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[a.ON_RESUME.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[a.ON_DESTROY.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[a.ON_ANY.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                a = iArr;
            }
        }

        public final b a() {
            switch (b.a[ordinal()]) {
                case 1:
                case 2:
                    return b.CREATED;
                case 3:
                case 4:
                    return b.STARTED;
                case 5:
                    return b.RESUMED;
                case 6:
                    return b.DESTROYED;
                default:
                    throw new IllegalArgumentException(this + " has no target state");
            }
        }
    }

    /* JADX INFO: compiled from: Lifecycle.kt */
    public enum b {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED
    }

    public d() {
        new AtomicReference();
    }

    public abstract void a(a71 a71Var);

    public abstract void b(a71 a71Var);
}
