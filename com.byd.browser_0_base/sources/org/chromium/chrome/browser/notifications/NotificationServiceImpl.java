package org.chromium.chrome.browser.notifications;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;
import defpackage.qm0;
import defpackage.rh3;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.base.SplitCompatIntentService;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationServiceImpl extends SplitCompatIntentService.a {
    public static final /* synthetic */ int b = 0;

    public static class Receiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            int i = NotificationServiceImpl.b;
            Log.i("NotificationServiceImpl", "Received a notification intent in the NotificationService's receiver.");
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("notification_id", intent.getStringExtra("notification_id"));
            persistableBundle.putInt("notification_type", intent.getIntExtra("notification_type", 0));
            persistableBundle.putString("notification_info_origin", intent.getStringExtra("notification_info_origin"));
            persistableBundle.putString("notification_info_scope", intent.getStringExtra("notification_info_scope"));
            persistableBundle.putString("notification_info_profile_id", intent.getStringExtra("notification_info_profile_id"));
            persistableBundle.putBoolean("notification_info_profile_incognito", intent.getBooleanExtra("notification_info_profile_incognito", false));
            persistableBundle.putInt("notification_info_action_index", intent.getIntExtra("notification_info_action_index", -1));
            persistableBundle.putString("notification_info_webapk_package", intent.getStringExtra("notification_info_webapk_package"));
            persistableBundle.putString("notification_action", intent.getAction());
            persistableBundle.putString("notification_reply", NotificationPlatformBridge.b(intent));
            persistableBundle.putLong("notification_job_scheduled_time_ms", SystemClock.elapsedRealtime());
            jobScheduler.schedule(new JobInfo.Builder(21, new ComponentName(context, (Class<?>) NotificationJobService.class)).setExtras(persistableBundle).setOverrideDeadline(0L).build());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c(android.content.Intent r15) {
        /*
            Method dump skipped, instruction units count: 241
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.notifications.NotificationServiceImpl.c(android.content.Intent):void");
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatIntentService.a
    public final void a(Intent intent) {
        if (intent.hasExtra("notification_id") && intent.hasExtra("notification_info_origin")) {
            PostTask.d(rh3.a, new qm0(11, intent));
        }
    }
}
