package org.chromium.chrome.browser.base;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import org.chromium.base.BundleUtils;

/* JADX INFO: loaded from: classes.dex */
public class SplitCompatJobService extends JobService {
    public final String e = "qo1";
    public a f;

    public static abstract class a {
        public abstract void a(JobParameters jobParameters);

        public abstract void b();
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        Context contextB = SplitCompatApplication.b(context);
        a aVar = (a) BundleUtils.e(contextB, this.e);
        this.f = aVar;
        aVar.getClass();
        super.attachBaseContext(contextB);
    }

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        this.f.a(jobParameters);
        return false;
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        this.f.b();
        return false;
    }
}
