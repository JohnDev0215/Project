package j6;

import c6.t;
import kotlin.jvm.internal.l;
import p6.g;

/* JADX INFO: compiled from: HeadersReader.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final C0092a f7152c = new C0092a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f7153a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g f7154b;

    /* JADX INFO: renamed from: j6.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HeadersReader.kt */
    public static final class C0092a {
        private C0092a() {
        }

        public /* synthetic */ C0092a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    public a(g source) {
        l.e(source, "source");
        this.f7154b = source;
        this.f7153a = 262144;
    }

    public final t a() {
        t.a aVar = new t.a();
        while (true) {
            String strB = b();
            if (strB.length() == 0) {
                return aVar.d();
            }
            aVar.b(strB);
        }
    }

    public final String b() {
        String strP = this.f7154b.p(this.f7153a);
        this.f7153a -= (long) strP.length();
        return strP;
    }
}
