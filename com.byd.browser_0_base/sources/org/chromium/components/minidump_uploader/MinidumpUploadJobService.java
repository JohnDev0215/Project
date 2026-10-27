package org.chromium.components.minidump_uploader;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;
import defpackage.d73;
import defpackage.mq;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.components.minidump_uploader.a;
import org.chromium.components.minidump_uploader.b.a;

/* JADX INFO: loaded from: classes3.dex */
public abstract class MinidumpUploadJobService extends JobService implements a.InterfaceC0133a {
    public static final /* synthetic */ int j = 0;
    public final Object e = new Object();
    public a f;
    public JobParameters g;
    public long h;
    public boolean i;

    public abstract a a(PersistableBundle persistableBundle);

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        synchronized (this.e) {
            boolean z = this.f != null;
            this.i = z;
            if (z) {
                return false;
            }
            this.f = a(jobParameters.getExtras());
            this.g = jobParameters;
            this.h = SystemClock.uptimeMillis();
            b bVar = (b) this.f;
            bVar.getClass();
            Object obj = ThreadUtils.a;
            bVar.b = false;
            ((mq) bVar.a).getClass();
            Object obj2 = ThreadUtils.a;
            PostTask.c(d73.g, bVar.new a(this));
            return true;
        }
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        boolean z;
        Log.i("cr_MinidumpJobService", "Canceling pending uploads due to change in networking status.");
        synchronized (this.e) {
            a aVar = this.f;
            z = true;
            if (aVar != null) {
                ((b) aVar).b = true;
            } else if (!this.i) {
                z = false;
            }
        }
        return z;
    }

    public void b(long j2) {
    }
}
