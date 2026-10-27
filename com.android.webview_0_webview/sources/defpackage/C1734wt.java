package defpackage;

/* JADX INFO: renamed from: wt, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1734wt extends RuntimeException {
    public C1734wt(int i) {
        super(Z7.a("errorCode=", i));
    }

    public C1734wt(int i, Throwable th) {
        super(Z7.a("errorCode=", i), th);
    }
}
