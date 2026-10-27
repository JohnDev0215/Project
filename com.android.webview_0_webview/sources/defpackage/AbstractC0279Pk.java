package defpackage;

import java.io.PrintStream;

/* JADX INFO: renamed from: Pk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0279Pk {
    public static final AbstractC0172Jk a;

    static {
        AbstractC0172Jk c0243Nk;
        Integer num = null;
        try {
            try {
                num = (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
            } catch (Exception e) {
                System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
                e.printStackTrace(System.err);
            }
            c0243Nk = (num == null || num.intValue() < 19) ? Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ^ true ? new C0225Mk() : new C0243Nk() : new C0261Ok();
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            StringBuilder sbF = Z7.f("An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy ");
            sbF.append(C0243Nk.class.getName());
            sbF.append("will be used. The error is: ");
            printStream.println(sbF.toString());
            th.printStackTrace(System.err);
            c0243Nk = new C0243Nk();
        }
        a = c0243Nk;
        if (num == null) {
            return;
        }
        num.intValue();
    }
}
