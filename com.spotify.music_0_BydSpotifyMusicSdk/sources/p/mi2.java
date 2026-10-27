package p;

import android.app.job.JobParameters;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public abstract class mi2 {
    public static String[] a(JobParameters jobParameters) {
        return jobParameters.getTriggeredContentAuthorities();
    }

    public static Uri[] b(JobParameters jobParameters) {
        return jobParameters.getTriggeredContentUris();
    }
}
