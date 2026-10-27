package c6;

import java.io.Closeable;
import java.io.InputStream;

/* JADX INFO: compiled from: ResponseBody.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class c0 implements Closeable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f3675e = new a(null);

    /* JADX INFO: compiled from: ResponseBody.kt */
    public static final class a {

        /* JADX INFO: renamed from: c6.c0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ResponseBody.kt */
        public static final class C0040a extends c0 {

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ p6.g f3676g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            final /* synthetic */ w f3677h;

            /* JADX INFO: renamed from: i, reason: collision with root package name */
            final /* synthetic */ long f3678i;

            C0040a(p6.g gVar, w wVar, long j7) {
                this.f3676g = gVar;
                this.f3677h = wVar;
                this.f3678i = j7;
            }

            @Override // c6.c0
            public p6.g A() {
                return this.f3676g;
            }

            @Override // c6.c0
            public long z() {
                return this.f3678i;
            }
        }

        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public static /* synthetic */ c0 c(a aVar, byte[] bArr, w wVar, int i7, Object obj) {
            if ((i7 & 1) != 0) {
                wVar = null;
            }
            return aVar.b(bArr, wVar);
        }

        public final c0 a(p6.g asResponseBody, w wVar, long j7) {
            kotlin.jvm.internal.l.e(asResponseBody, "$this$asResponseBody");
            return new C0040a(asResponseBody, wVar, j7);
        }

        public final c0 b(byte[] toResponseBody, w wVar) {
            kotlin.jvm.internal.l.e(toResponseBody, "$this$toResponseBody");
            return a(new p6.e().d(toResponseBody), wVar, toResponseBody.length);
        }
    }

    public abstract p6.g A();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        d6.b.i(A());
    }

    public final InputStream m() {
        return A().X();
    }

    public abstract long z();
}
