package defpackage;

/* JADX INFO: renamed from: c3, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0583c3 extends C0527b3 {
    public final Object c;

    public C0583c3(int i) {
        super(i);
        this.c = new Object();
    }

    @Override // defpackage.C0527b3, defpackage.InterfaceC0471a3
    public boolean a(Object obj) {
        boolean zA;
        synchronized (this.c) {
            zA = super.a(obj);
        }
        return zA;
    }

    @Override // defpackage.C0527b3, defpackage.InterfaceC0471a3
    public Object b() {
        Object objB;
        synchronized (this.c) {
            objB = super.b();
        }
        return objB;
    }
}
