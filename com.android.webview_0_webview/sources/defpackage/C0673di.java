package defpackage;

/* JADX INFO: renamed from: di, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0673di extends AbstractC0366Uh {
    public final transient Object[] u;
    public final transient int v;
    public final transient int w;

    public C0673di(Object[] objArr, int i, int i2) {
        this.u = objArr;
        this.v = i;
        this.w = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        AbstractC0151Ih.a(i, this.w);
        return this.u[(i * 2) + this.v];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.w;
    }
}
