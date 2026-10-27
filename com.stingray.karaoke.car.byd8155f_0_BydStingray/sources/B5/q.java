package B5;

import B5.C0087k;
import android.content.SharedPreferences;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ExecutorService f730a = Executors.newFixedThreadPool(2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0087k f731b;
    public final SharedPreferences c;
    public final CountDownLatch d;

    public q(C0087k c0087k, SharedPreferences sharedPreferences, CountDownLatch countDownLatch) {
        this.f731b = c0087k;
        this.c = sharedPreferences;
        this.d = countDownLatch;
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b10 : bArr) {
            sb.append(String.format("%02x", Byte.valueOf(b10)));
        }
        return sb.toString();
    }

    public final void b(String str) {
        synchronized (this.f731b) {
            try {
                if (!this.f731b.l.containsKey("device")) {
                    this.f731b.f(new C0087k.a(), "device");
                }
                ((C0087k.a) this.f731b.l.get("device")).f(str, "id");
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
