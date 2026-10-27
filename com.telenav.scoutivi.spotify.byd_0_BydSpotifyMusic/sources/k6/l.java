package k6;

import java.util.List;

/* JADX INFO: compiled from: PushObserver.kt */
/* JADX INFO: loaded from: classes.dex */
public interface l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f7479b = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l f7478a = new a.C0103a();

    /* JADX INFO: compiled from: PushObserver.kt */
    public static final class a {

        /* JADX INFO: renamed from: k6.l$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PushObserver.kt */
        private static final class C0103a implements l {
            @Override // k6.l
            public boolean a(int i7, List<c> requestHeaders) {
                kotlin.jvm.internal.l.e(requestHeaders, "requestHeaders");
                return true;
            }

            @Override // k6.l
            public boolean b(int i7, List<c> responseHeaders, boolean z6) {
                kotlin.jvm.internal.l.e(responseHeaders, "responseHeaders");
                return true;
            }

            @Override // k6.l
            public void c(int i7, b errorCode) {
                kotlin.jvm.internal.l.e(errorCode, "errorCode");
            }

            @Override // k6.l
            public boolean d(int i7, p6.g source, int i8, boolean z6) {
                kotlin.jvm.internal.l.e(source, "source");
                source.a(i8);
                return true;
            }
        }

        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    boolean a(int i7, List<c> list);

    boolean b(int i7, List<c> list, boolean z6);

    void c(int i7, b bVar);

    boolean d(int i7, p6.g gVar, int i8, boolean z6);
}
