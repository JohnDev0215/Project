package defpackage;

import java.util.Map;

/* JADX INFO: renamed from: k1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1027k1 extends AbstractC1578u1 {
    public final /* synthetic */ C1083l1 d;

    public C1027k1(C1083l1 c1083l1) {
        this.d = c1083l1;
    }

    @Override // defpackage.AbstractC1578u1
    public void a() {
        this.d.clear();
    }

    @Override // defpackage.AbstractC1578u1
    public Object b(int i, int i2) {
        return this.d.t[i];
    }

    @Override // defpackage.AbstractC1578u1
    public Map c() {
        throw new UnsupportedOperationException("not a map");
    }

    @Override // defpackage.AbstractC1578u1
    public int d() {
        return this.d.u;
    }

    @Override // defpackage.AbstractC1578u1
    public int e(Object obj) {
        return this.d.indexOf(obj);
    }

    @Override // defpackage.AbstractC1578u1
    public int f(Object obj) {
        return this.d.indexOf(obj);
    }

    @Override // defpackage.AbstractC1578u1
    public void g(Object obj, Object obj2) {
        this.d.add(obj);
    }

    @Override // defpackage.AbstractC1578u1
    public void h(int i) {
        this.d.i(i);
    }

    @Override // defpackage.AbstractC1578u1
    public Object i(int i, Object obj) {
        throw new UnsupportedOperationException("not a map");
    }
}
