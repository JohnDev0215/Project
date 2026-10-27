package defpackage;

import android.view.View;

/* JADX INFO: renamed from: s6, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1473s6 {
    public final InterfaceC1418r6 a;
    public C1364q6 b = new C1364q6();

    public C1473s6(InterfaceC1418r6 interfaceC1418r6) {
        this.a = interfaceC1418r6;
    }

    public View a(int i, int i2, int i3, int i4) {
        int iC = this.a.c();
        int iB = this.a.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewA = this.a.a(i);
            int iE = this.a.e(viewA);
            int iD = this.a.d(viewA);
            C1364q6 c1364q6 = this.b;
            c1364q6.b = iC;
            c1364q6.c = iB;
            c1364q6.d = iE;
            c1364q6.e = iD;
            if (i3 != 0) {
                c1364q6.a = 0;
                c1364q6.a = i3 | 0;
                if (c1364q6.a()) {
                    return viewA;
                }
            }
            if (i4 != 0) {
                C1364q6 c1364q62 = this.b;
                c1364q62.a = 0;
                c1364q62.a = i4 | 0;
                if (c1364q62.a()) {
                    view = viewA;
                }
            }
            i += i5;
        }
        return view;
    }
}
