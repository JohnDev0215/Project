package defpackage;

import android.content.Context;

/* JADX INFO: renamed from: mg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1172mg implements InterfaceC0894hg {
    @Override // defpackage.InterfaceC0894hg
    public final C0838gg a(Context context, String str, InterfaceC0782fg interfaceC0782fg) {
        C0838gg c0838gg = new C0838gg();
        int iB = interfaceC0782fg.b(context, str, true);
        c0838gg.b = iB;
        if (iB != 0) {
            c0838gg.c = 1;
        } else {
            int iA = interfaceC0782fg.a(context, str);
            c0838gg.a = iA;
            if (iA != 0) {
                c0838gg.c = -1;
            }
        }
        return c0838gg;
    }
}
