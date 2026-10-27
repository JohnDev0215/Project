package c6;

import java.util.List;

/* JADX INFO: compiled from: CookieJar.kt */
/* JADX INFO: loaded from: classes.dex */
public interface n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f3823b = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n f3822a = new a.C0041a();

    /* JADX INFO: compiled from: CookieJar.kt */
    public static final class a {

        /* JADX INFO: renamed from: c6.n$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CookieJar.kt */
        private static final class C0041a implements n {
            @Override // c6.n
            public void a(u url, List<m> cookies) {
                kotlin.jvm.internal.l.e(url, "url");
                kotlin.jvm.internal.l.e(cookies, "cookies");
            }

            @Override // c6.n
            public List<m> b(u url) {
                kotlin.jvm.internal.l.e(url, "url");
                return h5.p.h();
            }
        }

        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    void a(u uVar, List<m> list);

    List<m> b(u uVar);
}
