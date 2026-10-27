package defpackage;

import android.app.job.JobParameters;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Wx implements Rx {
    public final /* synthetic */ JobParameters a;
    public final /* synthetic */ Xx b;

    public Wx(Xx xx, JobParameters jobParameters) {
        this.b = xx;
        this.a = jobParameters;
    }

    public void a(boolean z) {
        if (z) {
            Ts.d("MinidumpJobService", "Some minidumps remain un-uploaded; rescheduling.", new Object[0]);
        }
        synchronized (this.b.t) {
            this.b.u = false;
        }
        this.b.jobFinished(this.a, z);
    }
}
