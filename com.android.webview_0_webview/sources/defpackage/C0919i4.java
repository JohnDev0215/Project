package defpackage;

/* JADX INFO: renamed from: i4, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0919i4 implements J3 {
    public final boolean a;
    public final C3 b;
    public int c;

    public C0919i4(C3 c3, boolean z) {
        this.a = z;
        this.b = c3;
    }

    public void a() {
        boolean z = this.c > 0;
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.b.a;
        int size = layoutInflaterFactory2C0974j4.v.size();
        for (int i = 0; i < size; i++) {
            K3 k3 = (K3) layoutInflaterFactory2C0974j4.v.get(i);
            k3.e0(null);
            if (z) {
                H3 h3 = k3.d0;
            }
        }
        C3 c3 = this.b;
        c3.a.g(c3, this.a, !z, true);
    }
}
