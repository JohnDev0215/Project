package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Base64;
import p.fr2;
import p.gx1;
import p.ho2;
import p.jr2;
import p.ua;
import p.v4;
import p.wi;

/* JADX INFO: loaded from: classes.dex */
public class JobInfoSchedulerService extends JobService {
    public static final /* synthetic */ int c = 0;

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        String string = jobParameters.getExtras().getString("backendName");
        String string2 = jobParameters.getExtras().getString("extras");
        int i = jobParameters.getExtras().getInt("priority");
        int i2 = jobParameters.getExtras().getInt("attemptNumber");
        ho2.b(getApplicationContext());
        v4 v4VarA = wi.a();
        v4VarA.M(string);
        v4VarA.N(gx1.b(i));
        if (string2 != null) {
            v4VarA.k = Base64.decode(string2, 0);
        }
        jr2 jr2Var = ho2.a().d;
        wi wiVarE = v4VarA.e();
        ua uaVar = new ua(this, 13, jobParameters);
        jr2Var.getClass();
        jr2Var.e.execute(new fr2(jr2Var, wiVarE, i2, uaVar));
        return true;
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
