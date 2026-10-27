package defpackage;

/* JADX INFO: renamed from: y5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1802y5 {
    public int b;
    public int c;
    public int d;
    public int e;
    public boolean a = true;
    public int f = 0;
    public int g = 0;

    public String toString() {
        StringBuilder sbF = Z7.f("LayoutState{mAvailable=");
        sbF.append(this.b);
        sbF.append(", mCurrentPosition=");
        sbF.append(this.c);
        sbF.append(", mItemDirection=");
        sbF.append(this.d);
        sbF.append(", mLayoutDirection=");
        sbF.append(this.e);
        sbF.append(", mStartLine=");
        sbF.append(this.f);
        sbF.append(", mEndLine=");
        sbF.append(this.g);
        sbF.append('}');
        return sbF.toString();
    }
}
