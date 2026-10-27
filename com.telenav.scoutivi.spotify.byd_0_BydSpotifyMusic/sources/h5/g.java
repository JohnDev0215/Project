package h5;

/* JADX INFO: compiled from: ArrayDeque.kt */
/* JADX INFO: loaded from: classes.dex */
public final class g<E> extends d<E> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f6652e = new a(null);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final Object[] f6653g = new Object[0];

    /* JADX INFO: compiled from: ArrayDeque.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final int a(int i7, int i8) {
            int i9 = i7 + (i7 >> 1);
            if (i9 - i8 < 0) {
                i9 = i8;
            }
            return i9 - 2147483639 > 0 ? i8 > 2147483639 ? Integer.MAX_VALUE : 2147483639 : i9;
        }
    }
}
