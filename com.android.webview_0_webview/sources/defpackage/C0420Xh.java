package defpackage;

/* JADX INFO: renamed from: Xh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0420Xh extends AbstractC0366Uh {
    public final transient int u;
    public final transient int v;
    public final /* synthetic */ AbstractC0366Uh w;

    public C0420Xh(AbstractC0366Uh abstractC0366Uh, int i, int i2) {
        this.w = abstractC0366Uh;
        this.u = i;
        this.v = i2;
    }

    @Override // defpackage.AbstractC0330Sh
    public final Object[] f() {
        return this.w.f();
    }

    @Override // defpackage.AbstractC0330Sh
    public final int g() {
        return this.w.g() + this.u;
    }

    @Override // java.util.List
    public final Object get(int i) {
        AbstractC0151Ih.a(i, this.v);
        return this.w.get(i + this.u);
    }

    @Override // defpackage.AbstractC0330Sh
    public final int h() {
        return this.w.g() + this.u + this.v;
    }

    @Override // defpackage.AbstractC0366Uh, java.util.List
    /* JADX INFO: renamed from: i */
    public final AbstractC0366Uh subList(int i, int i2) {
        AbstractC0151Ih.d(i, i2, this.v);
        AbstractC0366Uh abstractC0366Uh = this.w;
        int i3 = this.u;
        return (AbstractC0366Uh) abstractC0366Uh.subList(i + i3, i2 + i3);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.v;
    }
}
