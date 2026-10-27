package com.android.permissioncontroller.incident;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IncidentManager;
import android.util.ArraySet;
import android.util.Log;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.incident.PendingList;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes8.dex */
class PendingList {
    static final int FLAG_FROM_NOTIFICATION = 1;
    private static final String SHARED_PREFS_KEY_NOTIFICATIONS = "notifications";
    private static final String SHARED_PREFS_NAME = "com.android.packageinstaller.incident.PendingList";
    private static final String TAG = "PermissionController.incident";
    private static final PendingList sInstance = new PendingList();
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /* JADX INFO: Access modifiers changed from: private */
    static class Rec {
        public final String label;
        public final IncidentManager.PendingReport report;

        Rec(IncidentManager.PendingReport r, String l) {
            this.report = r;
            this.label = l;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class Updater {
        private Collator mCollator;
        private final Context mContext;
        private final int mFlags;
        private final Formatting mFormatting;
        private final NotificationManager mNm;

        Updater(Context context, int flags) {
            this.mContext = context;
            this.mFlags = flags;
            this.mNm = (NotificationManager) context.getSystemService(NotificationManager.class);
            this.mFormatting = new Formatting(context);
            this.mCollator = Collator.getInstance(context.getResources().getConfiguration().getLocales().get(0));
        }

        void updateState() {
            IncidentManager incidentManager = (IncidentManager) this.mContext.getSystemService(IncidentManager.class);
            List<IncidentManager.PendingReport> reports = incidentManager.getPendingReports();
            SharedPreferences prefs = this.mContext.getSharedPreferences(PendingList.SHARED_PREFS_NAME, 0);
            Set<String> prevNotifications = prefs.getStringSet(PendingList.SHARED_PREFS_KEY_NOTIFICATIONS, null);
            ArraySet<String> remainingNotifications = new ArraySet<>();
            if (prevNotifications != null) {
                for (String s : prevNotifications) {
                    remainingNotifications.add(s);
                }
            }
            ArraySet<String> currentNotifications = new ArraySet<>();
            List<Rec> recs = new ArrayList<>();
            int recCount = reports.size();
            for (int i = 0; i < recCount; i++) {
                IncidentManager.PendingReport report = reports.get(i);
                String label = this.mFormatting.getAppLabel(report.getRequestingPackage());
                if (label == null) {
                    Log.w(PendingList.TAG, "Application (or its label) could not be found. Summarily  denying report: " + report.getRequestingPackage());
                    incidentManager.denyReport(report.getUri());
                } else {
                    recs.add(new Rec(report, label));
                }
            }
            recs.sort(new Comparator() { // from class: com.android.permissioncontroller.incident.-$$Lambda$PendingList$Updater$UlV1qXO8KYBczIyUdSyVcDJUNBU
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return this.f$0.lambda$updateState$0$PendingList$Updater((PendingList.Rec) obj, (PendingList.Rec) obj2);
                }
            });
            Rec firstDialog = null;
            List<Rec> notificationRecs = new ArrayList<>();
            int notificationCount = recs.size();
            int i2 = 0;
            while (i2 < notificationCount) {
                Rec rec = recs.get(i2);
                notificationRecs.add(rec);
                String uri = rec.report.getUri().toString();
                remainingNotifications.remove(uri);
                currentNotifications.add(uri);
                IncidentManager incidentManager2 = incidentManager;
                if ((rec.report.getFlags() & 1) != 0 && firstDialog == null) {
                    firstDialog = rec;
                }
                i2++;
                incidentManager = incidentManager2;
            }
            showNotifications(notificationRecs);
            int i3 = 0;
            for (int remainingCount = remainingNotifications.size(); i3 < remainingCount; remainingCount = remainingCount) {
                this.mNm.cancel(remainingNotifications.valueAt(i3), Constants.INCIDENT_NOTIFICATION_ID);
                i3++;
            }
            if (firstDialog != null) {
                if (!firstDialog.report.getUri().equals(ConfirmationActivity.getCurrentUri()) && (this.mFlags & 1) == 0) {
                    this.mContext.startActivity(newDialogIntent(firstDialog));
                }
            } else {
                ConfirmationActivity.finishCurrent();
            }
            SharedPreferences.Editor editor = prefs.edit();
            editor.putStringSet(PendingList.SHARED_PREFS_KEY_NOTIFICATIONS, currentNotifications);
            editor.apply();
        }

        public /* synthetic */ int lambda$updateState$0$PendingList$Updater(Rec a, Rec b) {
            long val = a.report.getTimestamp() - b.report.getTimestamp();
            if (val == 0) {
                return this.mCollator.compare(a.label, b.label);
            }
            return val < 0 ? -1 : 1;
        }

        private void showNotifications(List<Rec> recs) {
            createNotificationChannel();
            int recCount = recs.size();
            for (int i = 0; i < recCount; i++) {
                Rec rec = recs.get(i);
                PendingIntent dialog = PendingIntent.getActivity(this.mContext, 0, newDialogIntent(rec), 0);
                PendingIntent deny = PendingIntent.getBroadcast(this.mContext, 0, new Intent(ApprovalReceiver.ACTION_DENY, rec.report.getUri(), this.mContext, ApprovalReceiver.class), 0);
                Notification notification = new Notification.Builder(this.mContext).setStyle(new Notification.BigTextStyle()).setContentTitle(this.mContext.getString(R.string.incident_report_notification_title)).setContentText(this.mContext.getString(R.string.incident_report_notification_text, rec.label)).setSmallIcon(R.drawable.ic_bug_report_black_24dp).setWhen(rec.report.getTimestamp()).setGroup(Constants.INCIDENT_NOTIFICATION_GROUP_KEY).setChannelId(Constants.INCIDENT_NOTIFICATION_CHANNEL_ID).setSortKey(getSortKey(rec.report.getTimestamp())).setContentIntent(dialog).setDeleteIntent(deny).setColor(this.mContext.getColor(android.R.color.system_notification_accent_color)).extend(new Notification.TvExtender()).build();
                this.mNm.notify(rec.report.getUri().toString(), Constants.INCIDENT_NOTIFICATION_ID, notification);
            }
        }

        private void createNotificationChannel() {
            NotificationChannel channel = new NotificationChannel(Constants.INCIDENT_NOTIFICATION_CHANNEL_ID, this.mContext.getString(R.string.incident_report_channel_name), 3);
            this.mNm.createNotificationChannel(channel);
        }

        private String getSortKey(long timestamp) {
            return PendingList.sDateFormatter.format(new Date(timestamp));
        }

        private Intent newDialogIntent(Rec rec) {
            Intent result = new Intent("android.intent.action.MAIN", rec.report.getUri(), this.mContext, ConfirmationActivity.class);
            result.setFlags(268468224);
            return result;
        }
    }

    public static PendingList getInstance() {
        return sInstance;
    }

    private PendingList() {
    }

    public void updateState(Context context, int flags) {
        new Updater(context, flags).updateState();
    }
}
