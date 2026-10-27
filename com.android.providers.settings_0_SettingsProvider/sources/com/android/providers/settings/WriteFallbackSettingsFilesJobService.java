package com.android.providers.settings;

import android.app.job.JobParameters;
import android.app.job.JobService;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class WriteFallbackSettingsFilesJobService extends JobService {
    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        if (jobParameters.getJobId() != 1) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(jobParameters.getExtras().getString("global", ""));
        arrayList.add(jobParameters.getExtras().getString("system", ""));
        arrayList.add(jobParameters.getExtras().getString("secure", ""));
        arrayList.add(jobParameters.getExtras().getString("ssaid", ""));
        arrayList.add(jobParameters.getExtras().getString("config", ""));
        SettingsProvider.writeFallBackSettingsFiles(arrayList);
        return false;
    }
}
