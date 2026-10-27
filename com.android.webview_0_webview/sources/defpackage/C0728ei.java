package defpackage;

/* JADX INFO: renamed from: ei, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0728ei extends AbstractC0438Yh {
    public final transient AbstractC0402Wh u;
    public final transient AbstractC0366Uh v;

    public C0728ei(AbstractC0402Wh abstractC0402Wh, AbstractC0366Uh abstractC0366Uh) {
        this.u = abstractC0402Wh;
        this.v = abstractC0366Uh;
    }

    @Override // defpackage.AbstractC0330Sh, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.u.get(obj) != null;
    }

    @Override // defpackage.AbstractC0330Sh
    public final int e(Object[] objArr, int i) {
        return this.v.e(objArr, i);
    }

    @Override // defpackage.AbstractC0438Yh
    public final AbstractC0366Uh i() {
        return this.v;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final AbstractC0840gi iterator() {
        return (AbstractC0840gi) this.v.iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.u.size();
    }
}
