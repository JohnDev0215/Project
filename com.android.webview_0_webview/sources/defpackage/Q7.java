package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Q7 extends P7 {
    public C1305p2[] a;
    public String b;
    public int c;

    public Q7() {
        super(null);
        this.a = null;
    }

    public boolean c() {
        return false;
    }

    public C1305p2[] getPathData() {
        return this.a;
    }

    public String getPathName() {
        return this.b;
    }

    public void setPathData(C1305p2[] c1305p2Arr) {
        if (!AbstractC1360q2.a(this.a, c1305p2Arr)) {
            this.a = AbstractC1360q2.e(c1305p2Arr);
            return;
        }
        C1305p2[] c1305p2Arr2 = this.a;
        for (int i = 0; i < c1305p2Arr.length; i++) {
            c1305p2Arr2[i].a = c1305p2Arr[i].a;
            for (int i2 = 0; i2 < c1305p2Arr[i].b.length; i2++) {
                c1305p2Arr2[i].b[i2] = c1305p2Arr[i].b[i2];
            }
        }
    }

    public Q7(Q7 q7) {
        super(null);
        this.a = null;
        this.b = q7.b;
        this.c = q7.c;
        this.a = AbstractC1360q2.e(q7.a);
    }
}
