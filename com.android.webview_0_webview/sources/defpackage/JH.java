package defpackage;

import java.util.Comparator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JH implements Comparator {
    public final /* synthetic */ int a;

    public JH(int i) {
        this.a = i;
    }

    public int a(KH kh) {
        int i = kh.a;
        int i2 = i < 8000 ? i * 1 : ((i - 8000) * 4) + 8000;
        int iAbs = Math.abs(this.a - kh.b);
        return i2 + (iAbs < 5000 ? iAbs * 1 : ((iAbs - 5000) * 3) + 5000);
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return a((KH) obj) - a((KH) obj2);
    }
}
