package defpackage;

import java.util.AbstractMap;

/* JADX INFO: renamed from: bi, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0561bi extends AbstractC0366Uh {
    public final /* synthetic */ C0617ci u;

    public C0561bi(C0617ci c0617ci) {
        this.u = c0617ci;
    }

    @Override // java.util.List
    public final /* synthetic */ Object get(int i) {
        AbstractC0151Ih.a(i, this.u.w);
        Object[] objArr = this.u.v;
        int i2 = i * 2;
        return new AbstractMap.SimpleImmutableEntry(objArr[i2], objArr[i2 + 1]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.u.w;
    }
}
