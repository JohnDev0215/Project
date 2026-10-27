package kotlinx.coroutines.channels;

/* JADX INFO: compiled from: Channel.kt */
/* JADX INFO: loaded from: classes.dex */
public interface l<E> extends g0<E>, c0<E> {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f7574f = a.f7576b;

    /* JADX INFO: compiled from: Channel.kt */
    public static final class a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ a f7576b = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final int f7575a = kotlinx.coroutines.internal.d0.b("kotlinx.coroutines.channels.defaultBuffer", 64, 1, 2147483646);

        private a() {
        }

        public final int a() {
            return f7575a;
        }
    }
}
