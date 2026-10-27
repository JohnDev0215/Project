package c6;

/* JADX INFO: compiled from: RequestBody.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f3640a = new a(null);

    /* JADX INFO: compiled from: RequestBody.kt */
    public static final class a {

        /* JADX INFO: renamed from: c6.a0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: RequestBody.kt */
        public static final class C0038a extends a0 {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ byte[] f3641b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ w f3642c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ int f3643d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ int f3644e;

            C0038a(byte[] bArr, w wVar, int i7, int i8) {
                this.f3641b = bArr;
                this.f3642c = wVar;
                this.f3643d = i7;
                this.f3644e = i8;
            }

            @Override // c6.a0
            public long a() {
                return this.f3643d;
            }

            @Override // c6.a0
            public w b() {
                return this.f3642c;
            }

            @Override // c6.a0
            public void e(p6.f sink) {
                kotlin.jvm.internal.l.e(sink, "sink");
                sink.e(this.f3641b, this.f3644e, this.f3643d);
            }
        }

        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public static /* synthetic */ a0 b(a aVar, byte[] bArr, w wVar, int i7, int i8, int i9, Object obj) {
            if ((i9 & 1) != 0) {
                wVar = null;
            }
            if ((i9 & 2) != 0) {
                i7 = 0;
            }
            if ((i9 & 4) != 0) {
                i8 = bArr.length;
            }
            return aVar.a(bArr, wVar, i7, i8);
        }

        public final a0 a(byte[] toRequestBody, w wVar, int i7, int i8) {
            kotlin.jvm.internal.l.e(toRequestBody, "$this$toRequestBody");
            d6.b.h(toRequestBody.length, i7, i8);
            return new C0038a(toRequestBody, wVar, i8, i7);
        }
    }

    public abstract long a();

    public abstract w b();

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public abstract void e(p6.f fVar);
}
