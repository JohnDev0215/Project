package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class KN implements Runnable {
    public final /* synthetic */ String[] s;
    public final /* synthetic */ RN t;
    public final /* synthetic */ LN u;

    public KN(LN ln, String[] strArr, RN rn) {
        this.u = ln;
        this.s = strArr;
        this.t = rn;
    }

    @Override // java.lang.Runnable
    public void run() {
        int[] iArr = new int[this.s.length];
        int i = 0;
        while (true) {
            String[] strArr = this.s;
            if (i >= strArr.length) {
                this.t.a(strArr, iArr);
                return;
            } else {
                iArr[i] = this.u.hasPermission(strArr[i]) ? 0 : -1;
                i++;
            }
        }
    }
}
