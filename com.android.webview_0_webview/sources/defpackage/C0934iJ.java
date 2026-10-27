package defpackage;

/* JADX INFO: renamed from: iJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0934iJ implements ZI {
    public final VJ s;

    public C0934iJ(VJ vj) {
        this.s = vj;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.s.close();
    }

    @Override // defpackage.ZI
    /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
    public VJ e() {
        return this.s.v();
    }
}
