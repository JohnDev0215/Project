package defpackage;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Xx extends JobService {
    public Sx s;
    public final Object t = new Object();
    public boolean u;

    public static void a(JobInfo.Builder builder) {
        Ts.d("MinidumpJobService", "Scheduling upload of all pending minidumps.", new Object[0]);
        ((JobScheduler) Is.a.getSystemService("jobscheduler")).schedule(builder.setRequiredNetworkType(2).setBackoffCriteria(1800000L, 1).build());
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createConfigurationContext(Configuration configuration) {
        AbstractC1077kw.c();
        return super.createConfigurationContext(configuration);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        AbstractC1077kw.c();
        return super.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        AbstractC1077kw.c();
        return super.getResources();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        AbstractC1077kw.c();
        return super.getTheme();
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.s = null;
        super.onDestroy();
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        synchronized (this.t) {
            this.u = true;
        }
        jobParameters.getExtras();
        Vx vx = new Vx(new C0850gs());
        this.s = vx;
        Wx wx = new Wx(this, jobParameters);
        Vx vx2 = vx;
        Objects.requireNonNull(vx2);
        ThreadUtils.b();
        if (vx2.c != null) {
            throw new RuntimeException("A given minidump upload job instance should never be launched more than once.");
        }
        vx2.c = new Thread(new Ux(vx2, wx), "MinidumpUploadJob-WorkerThread");
        vx2.b = false;
        InterfaceC0521ay interfaceC0521ay = vx2.a;
        Tx tx = new Tx(vx2);
        C0850gs c0850gs = (C0850gs) interfaceC0521ay;
        Objects.requireNonNull(c0850gs);
        AbstractC0178Jq.a();
        ThreadUtils.b();
        ThreadUtils.b();
        c0850gs.b = false;
        tx.run();
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        Ts.d("MinidumpJobService", "Canceling pending uploads due to change in networking status.", new Object[0]);
        ((Vx) this.s).b = true;
        synchronized (this.t) {
            this.u = false;
        }
        return true;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        AbstractC1077kw.c();
        super.setTheme(i);
    }
}
