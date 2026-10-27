package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.chromium.android_webview.AwBrowserProcess;

/* JADX INFO: renamed from: tq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1566tq implements Runnable {
    public boolean s;
    public boolean t;
    public long v;
    public final /* synthetic */ C1731wq y;
    public long u = Long.MIN_VALUE;
    public int w = 0;
    public FutureTask x = new FutureTask(new Callable(this) { // from class: sq
        public final RunnableC1566tq a;

        {
            this.a = this;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x004d  */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object call() throws java.lang.Throwable {
            /*
                r11 = this;
                tq r0 = r11.a
                java.util.Objects.requireNonNull(r0)
                java.io.File r1 = defpackage.AbstractC0626cr.d()
                java.io.File r2 = defpackage.AbstractC0626cr.e()
                ty r3 = defpackage.AbstractC0626cr.f(r1)
                r4 = 1
                if (r3 == 0) goto L1d
                r0.s = r4
                long r1 = r1.lastModified()
                r0.v = r1
                goto L29
            L1d:
                ty r3 = defpackage.AbstractC0626cr.f(r2)
                if (r3 == 0) goto L29
                long r1 = r2.lastModified()
                r0.v = r1
            L29:
                r1 = 0
                if (r3 == 0) goto L4d
                wq r2 = r0.y
                long r5 = r0.v
                java.util.Objects.requireNonNull(r2)
                long r7 = defpackage.C1731wq.c
                java.lang.String r9 = "finch-seed-expiration-age"
                long r7 = defpackage.AbstractC0626cr.c(r9, r7)
                long r9 = r2.b()
                long r5 = r5 + r7
                int r2 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
                if (r2 <= 0) goto L46
                r2 = 1
                goto L47
            L46:
                r2 = 0
            L47:
                if (r2 == 0) goto L4a
                goto L4d
            L4a:
                r0.w = r4
                goto L84
            L4d:
                r0.t = r4
                r2 = 2
                r0.w = r2
                wq r2 = r0.y
                long r5 = r2.b()
                java.io.File r2 = new java.io.File
                java.lang.String r7 = org.chromium.base.PathUtils.getDataDirectory()
                java.lang.String r8 = "variations_stamp"
                r2.<init>(r7, r8)
                long r7 = r2.lastModified()
                r9 = 0
                int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
                if (r2 != 0) goto L6e
                goto L7c
            L6e:
                long r9 = defpackage.C1731wq.d
                java.lang.String r2 = "finch-seed-min-update-period"
                long r9 = defpackage.AbstractC0626cr.c(r2, r9)
                long r9 = r9 + r7
                int r2 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
                if (r2 >= 0) goto L7c
                goto L7d
            L7c:
                r4 = 0
            L7d:
                if (r4 == 0) goto L84
                r0.t = r1
                r1 = 3
                r0.w = r1
            L84:
                if (r3 == 0) goto L8a
                long r1 = r3.c
                r0.u = r1
            L8a:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: defpackage.CallableC1511sq.call():java.lang.Object");
        }
    });

    public RunnableC1566tq(C1731wq c1731wq, AbstractC1456rq abstractC1456rq) {
        this.y = c1731wq;
    }

    public C1574ty a(long j, TimeUnit timeUnit) {
        C1574ty c1574ty = (C1574ty) this.x.get(j, timeUnit);
        It.d("Variations.AppSeedRequestState", this.w, 4);
        if (this.v != 0) {
            It.b("Variations.AppSeedFreshness", (int) TimeUnit.MILLISECONDS.toMinutes(this.y.b() - this.v), 1, (int) TimeUnit.DAYS.toMinutes(30L), 50);
        }
        return c1574ty;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.x.run();
        if (this.s) {
            AbstractC0626cr.g();
        }
        if (this.t) {
            C1731wq c1731wq = this.y;
            long j = this.u;
            Objects.requireNonNull(c1731wq);
            File fileD = AbstractC0626cr.d();
            try {
                fileD.createNewFile();
                try {
                    ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(fileD, 536870912);
                    AbstractC0626cr.b("Requesting new seed from IVariationsSeedServer");
                    ServiceConnectionC1676vq serviceConnectionC1676vq = new ServiceConnectionC1676vq(c1731wq, parcelFileDescriptorOpen, j);
                    try {
                        Context context = Is.a;
                        Objects.requireNonNull(serviceConnectionC1676vq.c);
                        Intent intent = new Intent();
                        intent.setClassName(AwBrowserProcess.d(), "org.chromium.android_webview.services.VariationsSeedServer");
                        if (!context.bindService(intent, serviceConnectionC1676vq, 1)) {
                            Ts.a("VariationsSeedLoader", "Failed to bind to WebView service", new Object[0]);
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        StringBuilder sbF = Z7.f("WebView provider \"");
                        sbF.append(AwBrowserProcess.d());
                        sbF.append("\" not found!");
                        Ts.a("VariationsSeedLoader", sbF.toString(), new Object[0]);
                    }
                } catch (FileNotFoundException unused2) {
                    Ts.a("VariationsSeedLoader", "Failed to open seed file " + fileD, new Object[0]);
                }
            } catch (IOException unused3) {
                Ts.a("VariationsSeedLoader", "Failed to create seed file " + fileD, new Object[0]);
            }
            AbstractC0626cr.h();
        }
        Objects.requireNonNull(this.y);
    }
}
