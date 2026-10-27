package defpackage;

import android.content.Context;

/* JADX INFO: renamed from: ng, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1227ng implements InterfaceC0894hg {
    @Override // defpackage.InterfaceC0894hg
    public final C0838gg a(Context context, String str, InterfaceC0782fg interfaceC0782fg) {
        C0838gg c0838gg = new C0838gg();
        c0838gg.a = interfaceC0782fg.a(context, str);
        int iB = interfaceC0782fg.b(context, str, true);
        c0838gg.b = iB;
        if (c0838gg.a == 0 && iB == 0) {
            c0838gg.c = 0;
        } else if (c0838gg.b >= c0838gg.a) {
            c0838gg.c = 1;
        } else {
            c0838gg.c = -1;
        }
        return c0838gg;
    }
}
