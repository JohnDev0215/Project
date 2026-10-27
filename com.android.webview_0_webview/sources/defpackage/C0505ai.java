package defpackage;

/* JADX INFO: renamed from: ai, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0505ai extends AbstractC0366Uh {
    public static final AbstractC0366Uh w = new C0505ai(new Object[0], 0);
    public final transient Object[] u;
    public final transient int v;

    public C0505ai(Object[] objArr, int i) {
        this.u = objArr;
        this.v = i;
    }

    @Override // defpackage.AbstractC0366Uh, defpackage.AbstractC0330Sh
    public final int e(Object[] objArr, int i) {
        System.arraycopy(this.u, 0, objArr, i, this.v);
        return i + this.v;
    }

    @Override // defpackage.AbstractC0330Sh
    public final Object[] f() {
        return this.u;
    }

    @Override // defpackage.AbstractC0330Sh
    public final int g() {
        return 0;
    }

    @Override // java.util.List
    public final Object get(int i) {
        AbstractC0151Ih.a(i, this.v);
        return this.u[i];
    }

    @Override // defpackage.AbstractC0330Sh
    public final int h() {
        return this.v;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.v;
    }
}
