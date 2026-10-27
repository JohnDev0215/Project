package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class GD {
    public static void a(String str, String str2, String str3, String str4, boolean z, int i, boolean z2, boolean z3) {
        My.a = str;
        if (str2 == null) {
            str2 = "org.chromium.content.app.PrivilegedProcessService";
        }
        My.g = str2;
        My.b = str3;
        if (str4 == null) {
            str4 = "org.chromium.content.app.SandboxedProcessService";
        }
        My.h = str4;
        My.c = z;
        My.d = i;
        My.e = z2;
        My.f = z3;
        My.i = true;
    }
}
