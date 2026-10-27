package org.chromium.components.minidump_uploader;

import android.app.job.JobParameters;
import android.os.SystemClock;
import android.util.Log;
import defpackage.d10;
import defpackage.fa1;
import defpackage.lq;
import defpackage.mq;
import defpackage.vh1;
import defpackage.xh1;
import java.io.File;
import org.chromium.chrome.browser.crash.MinidumpUploadServiceImpl;
import org.chromium.components.minidump_uploader.a;

/* JADX INFO: compiled from: MinidumpUploadJobImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b implements org.chromium.components.minidump_uploader.a {
    public final xh1 a;
    public volatile boolean b;

    /* JADX INFO: compiled from: MinidumpUploadJobImpl.java */
    public class a implements Runnable {
        public final a.InterfaceC0133a e;

        public a(a.InterfaceC0133a interfaceC0133a) {
            this.e = interfaceC0133a;
        }

        public final void a(boolean z) {
            JobParameters jobParameters;
            long j;
            boolean z2;
            b.this.getClass();
            MinidumpUploadJobService minidumpUploadJobService = (MinidumpUploadJobService) this.e;
            if (z) {
                minidumpUploadJobService.getClass();
                Log.i("cr_MinidumpJobService", "Some minidumps remain un-uploaded; rescheduling.");
            }
            synchronized (minidumpUploadJobService.e) {
                jobParameters = minidumpUploadJobService.g;
                j = minidumpUploadJobService.h;
                z2 = z || minidumpUploadJobService.i;
                minidumpUploadJobService.f = null;
                minidumpUploadJobService.g = null;
            }
            minidumpUploadJobService.jobFinished(jobParameters, z2);
            minidumpUploadJobService.b(SystemClock.uptimeMillis() - j);
        }

        @Override // java.lang.Runnable
        public final void run() {
            File cacheDir = ((mq) b.this.a).a.getCacheDir();
            if (!cacheDir.isDirectory()) {
                Log.e("cr_MDUploadJobImpl", "Parent crash directory doesn't exist!");
                a(false);
                return;
            }
            b.this.getClass();
            d10 d10Var = new d10(cacheDir);
            if (!d10Var.d().isDirectory()) {
                Log.e("cr_MDUploadJobImpl", "Crash directory doesn't exist!");
                a(false);
                return;
            }
            File[] fileArrE = d10Var.e();
            fa1.g("MDUploadJobImpl", "Attempting to upload %d minidumps.", Integer.valueOf(fileArrE.length));
            for (File file : fileArrE) {
                Log.i("cr_MDUploadJobImpl", "Attempting to upload " + file.getName());
                b bVar = b.this;
                File file2 = new File(d10Var.d(), "uploads.log");
                mq mqVar = (mq) bVar.a;
                mqVar.getClass();
                int iIntValue = new vh1(file, file2, new lq(mqVar)).call().intValue();
                if (iIntValue == 0) {
                    ((mq) b.this.a).getClass();
                    MinidumpUploadServiceImpl.e(file.getAbsolutePath());
                } else if (iIntValue == 1) {
                    int iH = d10.h(file.getName());
                    if (iH < 0) {
                        iH = 0;
                    }
                    if (iH + 1 == 3) {
                        ((mq) b.this.a).getClass();
                        MinidumpUploadServiceImpl.d(file.getAbsolutePath());
                    }
                }
                if (b.this.b) {
                    b.this.getClass();
                    return;
                }
                if (iIntValue == 1 && d10.j(file) == null) {
                    Log.w("cr_MDUploadJobImpl", "Failed to increment attempt number of " + file);
                }
            }
            d10Var.a();
            a(d10Var.e().length > 0);
        }
    }

    public b(mq mqVar) {
        this.a = mqVar;
    }
}
