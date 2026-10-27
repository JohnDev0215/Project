package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.ApplicationLifetime;
import org.chromium.chrome.browser.BrowserRestartActivity;

/* JADX INFO: compiled from: ChromeLifetimeController.java */
/* JADX INFO: loaded from: classes.dex */
public final class fq implements ApplicationLifetime.a, ApplicationStatus.c {
    public static fq j;
    public final Handler e = new Handler(Looper.getMainLooper());
    public final a f = new a();
    public boolean g;
    public boolean h;
    public int i;

    /* JADX INFO: compiled from: ChromeLifetimeController.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            fq.this.b();
        }
    }

    @Override // org.chromium.chrome.browser.ApplicationLifetime.a
    public final void a(boolean z) {
        this.h = z;
        for (Activity activity : ApplicationStatus.b()) {
            ApplicationStatus.f(this, activity);
            this.i++;
            activity.finish();
        }
        this.e.postDelayed(this.f, 1000L);
    }

    public final void b() {
        Object obj = ThreadUtils.a;
        if (this.g) {
            return;
        }
        this.g = true;
        this.e.removeCallbacks(this.f);
        Context context = xz.a;
        boolean z = this.h;
        int i = BrowserRestartActivity.e;
        Intent intent = new Intent();
        intent.setClassName(context.getPackageName(), BrowserRestartActivity.class.getName());
        intent.setFlags(268435456);
        intent.putExtra("org.chromium.chrome.browser.BrowserRestartActivity.main_pid", Process.myPid());
        intent.putExtra("org.chromium.chrome.browser.BrowserRestartActivity.restart", z);
        context.startActivity(intent);
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        if (i == 6) {
            int i2 = this.i - 1;
            this.i = i2;
            if (i2 == 0) {
                b();
            }
        }
    }
}
