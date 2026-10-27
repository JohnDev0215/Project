package defpackage;

import java.io.PrintStream;

/* JADX INFO: renamed from: ji, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1007ji {
    public static final AbstractC1119li a;

    static {
        AbstractC1119li c0951ii;
        Integer num = null;
        try {
            try {
                num = (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
            } catch (Exception e) {
                System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
                AbstractC0279Pk.a.c(e, System.err);
            }
            c0951ii = (num == null || num.intValue() < 19) ? Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ^ true ? new C1174mi() : new C0951ii() : new C1284oi();
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = C0951ii.class.getName();
            StringBuilder sb = new StringBuilder(name.length() + 133);
            sb.append("An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy ");
            sb.append(name);
            sb.append("will be used. The error is: ");
            printStream.println(sb.toString());
            AbstractC0279Pk.a.c(th, System.err);
            c0951ii = new C0951ii();
        }
        a = c0951ii;
        if (num == null) {
            return;
        }
        num.intValue();
    }
}
