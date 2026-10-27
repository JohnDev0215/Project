package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Cz {
    public static final Cz c = new Cz();
    public int[] a = new int[4];
    public boolean b = false;

    public static int a(boolean z, boolean z2, boolean z3) {
        if (z) {
            return -1;
        }
        return z2 ? z3 ? 3 : 1 : z3 ? 2 : 0;
    }

    public void b(int i) {
        if (i < 0) {
            return;
        }
        if (this.b) {
            It.d("Servicification.Startup2", i, 4);
        } else {
            int[] iArr = this.a;
            iArr[i] = iArr[i] + 1;
        }
    }
}
