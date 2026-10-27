package defpackage;

import android.os.SystemClock;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.chromium.android_webview.AwVariationsSeedBridge;

/* JADX INFO: renamed from: wq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1731wq {
    public static final long c = TimeUnit.HOURS.toMillis(6);
    public static final long d = TimeUnit.HOURS.toMillis(1);
    public RunnableC1566tq a;
    public BinderC1621uq b = new BinderC1621uq(this, null);

    public static void c(int i) {
        It.d("Variations.SeedLoadResult", i, 10);
    }

    public void a() {
        C1574ty c1574tyA;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        try {
        } catch (InterruptedException unused) {
            c(8);
            Ts.a("VariationsSeedLoader", "Failed loading variations seed. Variations disabled.", new Object[0]);
            c1574tyA = null;
        } catch (ExecutionException unused2) {
            c(9);
            Ts.a("VariationsSeedLoader", "Failed loading variations seed. Variations disabled.", new Object[0]);
            c1574tyA = null;
        } catch (TimeoutException unused3) {
            c(7);
            Ts.a("VariationsSeedLoader", "Failed loading variations seed. Variations disabled.", new Object[0]);
            c1574tyA = null;
        }
        try {
            c1574tyA = this.a.a(20L, TimeUnit.MILLISECONDS);
            It.e("Variations.SeedLoadBlockingTime", SystemClock.elapsedRealtime() - jElapsedRealtime);
            if (c1574tyA != null) {
                AwVariationsSeedBridge.a = c1574tyA;
                AwVariationsSeedBridge.b = this.a.w == 1;
                AbstractC0626cr.b("Loaded seed with age " + TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - c1574tyA.c) + "s");
            }
        } catch (Throwable th) {
            It.e("Variations.SeedLoadBlockingTime", SystemClock.elapsedRealtime() - jElapsedRealtime);
            throw th;
        }
    }

    public long b() {
        return new Date().getTime();
    }
}
