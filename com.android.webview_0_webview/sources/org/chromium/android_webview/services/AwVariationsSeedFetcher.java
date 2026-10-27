package org.chromium.android_webview.services;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import defpackage.AbstractC0626cr;
import defpackage.AbstractC1077kw;
import defpackage.C0570br;
import defpackage.C0961is;
import defpackage.C1240nt;
import defpackage.Es;
import defpackage.Is;
import defpackage.Su;
import defpackage.Ts;
import java.io.File;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.chromium.base.PathUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwVariationsSeedFetcher extends JobService {
    public static final long t = TimeUnit.HOURS.toMillis(12);
    public C0961is s;

    public static long a() {
        return System.currentTimeMillis();
    }

    public static void b() {
        JobInfo jobInfoB;
        JobScheduler jobScheduler = (JobScheduler) Is.a.getSystemService("jobscheduler");
        if (jobScheduler == null) {
            return;
        }
        if (!Es.e().h("finch-seed-ignore-pending-download")) {
            if (Build.VERSION.SDK_INT < 24) {
                Iterator<JobInfo> it = jobScheduler.getAllPendingJobs().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        jobInfoB = null;
                        break;
                    } else {
                        jobInfoB = it.next();
                        if (jobInfoB.getId() == 83) {
                            break;
                        }
                    }
                }
            } else {
                jobInfoB = C1240nt.b(jobScheduler, 83);
            }
            if (jobInfoB != null) {
                AbstractC0626cr.b("Seed download job already scheduled");
                return;
            }
        }
        long jLastModified = new File(PathUtils.getDataDirectory(), "variations_stamp").lastModified();
        if (jLastModified != 0 && System.currentTimeMillis() < AbstractC0626cr.c("finch-seed-min-download-period", t) + jLastModified) {
            AbstractC0626cr.b("Throttling seed download job");
            return;
        }
        AbstractC0626cr.b("Scheduling seed download job");
        Context context = Is.a;
        if (jobScheduler.schedule(new JobInfo.Builder(83, new ComponentName(context, (Class<?>) AwVariationsSeedFetcher.class)).setRequiredNetworkType(1).setRequiresCharging(true).build()) != 1) {
            Ts.a("AwVariationsSeedFet-", "Failed to schedule job", new Object[0]);
            return;
        }
        C0570br c0570brA = C0570br.a(context);
        c0570brA.a.putLong("last_enqueue_time", System.currentTimeMillis());
        if (c0570brA.b(context)) {
            return;
        }
        Ts.a("AwVariationsSeedFet-", "Failed to write variations SharedPreferences to disk", new Object[0]);
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

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        C0961is c0961is = new C0961is(this, jobParameters);
        this.s = c0961is;
        c0961is.d(Su.f);
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        C0961is c0961is = this.s;
        if (c0961is == null) {
            return false;
        }
        c0961is.b(true);
        this.s = null;
        return false;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        AbstractC1077kw.c();
        super.setTheme(i);
    }
}
