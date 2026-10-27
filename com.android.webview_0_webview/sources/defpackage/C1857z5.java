package defpackage;

/* JADX INFO: renamed from: z5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1857z5 {
    public H5 a;
    public int b;
    public int c;
    public boolean d;
    public boolean e;

    public C1857z5() {
        a();
    }

    public void a() {
        this.b = -1;
        this.c = Integer.MIN_VALUE;
        this.d = false;
        this.e = false;
    }

    public String toString() {
        StringBuilder sbF = Z7.f("AnchorInfo{mPosition=");
        sbF.append(this.b);
        sbF.append(", mCoordinate=");
        sbF.append(this.c);
        sbF.append(", mLayoutFromEnd=");
        sbF.append(this.d);
        sbF.append(", mValid=");
        sbF.append(this.e);
        sbF.append('}');
        return sbF.toString();
    }
}
