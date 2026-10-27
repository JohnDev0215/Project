package defpackage;

import java.util.Map;

/* JADX INFO: renamed from: i1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0916i1 extends AbstractC1578u1 {
    public final /* synthetic */ C0971j1 d;

    public C0916i1(C0971j1 c0971j1) {
        this.d = c0971j1;
    }

    @Override // defpackage.AbstractC1578u1
    public void a() {
        this.d.clear();
    }

    @Override // defpackage.AbstractC1578u1
    public Object b(int i, int i2) {
        return this.d.t[(i << 1) + i2];
    }

    @Override // defpackage.AbstractC1578u1
    public Map c() {
        return this.d;
    }

    @Override // defpackage.AbstractC1578u1
    public int d() {
        return this.d.u;
    }

    @Override // defpackage.AbstractC1578u1
    public int e(Object obj) {
        return this.d.e(obj);
    }

    @Override // defpackage.AbstractC1578u1
    public int f(Object obj) {
        return this.d.g(obj);
    }

    @Override // defpackage.AbstractC1578u1
    public void g(Object obj, Object obj2) {
        this.d.put(obj, obj2);
    }

    @Override // defpackage.AbstractC1578u1
    public void h(int i) {
        this.d.i(i);
    }

    @Override // defpackage.AbstractC1578u1
    public Object i(int i, Object obj) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.d.t;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        return obj2;
    }
}
