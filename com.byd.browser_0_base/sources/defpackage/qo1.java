package defpackage;

import android.app.job.JobParameters;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.base.SplitCompatJobService;
import org.chromium.chrome.browser.notifications.NotificationServiceImpl;

/* JADX INFO: compiled from: NotificationJobServiceImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class qo1 extends SplitCompatJobService.a {
    @Override // org.chromium.chrome.browser.base.SplitCompatJobService.a
    public final void a(JobParameters jobParameters) {
        PersistableBundle extras = jobParameters.getExtras();
        extras.putLong("notification_job_started_time_ms", SystemClock.elapsedRealtime());
        if (extras.containsKey("notification_id") && extras.containsKey("notification_info_origin")) {
            Intent intent = new Intent(extras.getString("notification_action"));
            intent.putExtras(new Bundle(extras));
            Object obj = ThreadUtils.a;
            NotificationServiceImpl.c(intent);
        }
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatJobService.a
    public final void b() {
    }
}
