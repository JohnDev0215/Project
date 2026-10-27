package com.android.shell;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.FileUtils;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class HeapDumpReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.d("HeapDumpReceiver", "onReceive(): " + intent);
        String action = intent.getAction();
        if (action == null) {
            Log.e("HeapDumpReceiver", "null action received");
            return;
        }
        byte b = -1;
        int iHashCode = action.hashCode();
        if (iHashCode != 66110433) {
            if (iHashCode != 599113813) {
                if (iHashCode == 798292259 && action.equals("android.intent.action.BOOT_COMPLETED")) {
                    b = 0;
                }
            } else if (action.equals("com.android.internal.intent.action.HEAP_DUMP_FINISHED")) {
                b = 2;
            }
        } else if (action.equals("com.android.shell.action.DELETE_HEAP_DUMP")) {
            b = 1;
        }
        if (b == 0) {
            cleanupOldFiles(context);
        } else if (b == 1) {
            deleteHeapDump(context, intent.getStringExtra("uri"));
        } else {
            if (b != 2) {
                return;
            }
            showDumpNotification(context, intent);
        }
    }

    private void cleanupOldFiles(final Context context) {
        final BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
        new AsyncTask<Void, Void, Void>(this) { // from class: com.android.shell.HeapDumpReceiver.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                try {
                    Log.d("HeapDumpReceiver", "Deleting from " + new File(context.getFilesDir(), "heapdumps"));
                    FileUtils.deleteOlderFiles(new File(context.getFilesDir(), "heapdumps"), 0, 604800000L);
                } catch (RuntimeException e) {
                    Log.e("HeapDumpReceiver", "Couldn't delete old files", e);
                }
                pendingResultGoAsync.finish();
                return null;
            }
        }.execute(new Void[0]);
    }

    private void deleteHeapDump(final Context context, final String str) {
        if (str == null) {
            Log.e("HeapDumpReceiver", "null URI for delete heap dump intent");
        } else {
            final BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
            new AsyncTask<Void, Void, Void>(this) { // from class: com.android.shell.HeapDumpReceiver.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(Void... voidArr) {
                    context.getContentResolver().delete(Uri.parse(str), null, null);
                    pendingResultGoAsync.finish();
                    return null;
                }
            }.execute(new Void[0]);
        }
    }

    private void showDumpNotification(Context context, Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("com.android.internal.extra.heap_dump.IS_USER_INITIATED", false);
        String stringExtra = intent.getStringExtra("com.android.internal.extra.heap_dump.PROCESS_NAME");
        int intExtra = intent.getIntExtra("android.intent.extra.UID", 0);
        String stringExtra2 = intent.getStringExtra("com.android.internal.extra.heap_dump.REPORT_PACKAGE");
        long longExtra = intent.getLongExtra("com.android.internal.extra.heap_dump.SIZE_BYTES", 0L);
        if (stringExtra == null) {
            Log.e("HeapDumpReceiver", "No process name sent over");
            return;
        }
        NotificationManager.from(context).createNotificationChannel(new NotificationChannel("heapdumps", "Heap dumps", 3));
        String string = context.getString(booleanExtra ? android.R.string.config_satellite_sim_spn_identifier : android.R.string.config_satellite_service_package, intExtra == 1000 ? context.getString(android.R.string.accessibility_system_action_back_label) : stringExtra);
        Intent intent2 = new Intent();
        intent2.setClassName(context, HeapDumpActivity.class.getName());
        intent2.putExtra("com.android.internal.extra.heap_dump.PROCESS_NAME", stringExtra);
        intent2.putExtra("com.android.internal.extra.heap_dump.SIZE_BYTES", longExtra);
        intent2.putExtra("com.android.internal.extra.heap_dump.IS_USER_INITIATED", booleanExtra);
        intent2.putExtra("android.intent.extra.UID", intExtra);
        if (stringExtra2 != null) {
            intent2.putExtra("com.android.internal.extra.heap_dump.REPORT_PACKAGE", stringExtra2);
        }
        Notification.Builder contentIntent = new Notification.Builder(context, "heapdumps").setSmallIcon(BugreportProgressService.isTv(context) ? R.drawable.ic_bug_report_black_24dp : android.R.drawable.pointer_wait_39).setLocalOnly(true).setColor(context.getColor(android.R.color.system_notification_accent_color)).setContentTitle(string).setTicker(string).setAutoCancel(true).setContentText(context.getText(android.R.string.config_satellite_sim_plmn_identifier)).setContentIntent(PendingIntent.getActivity(context, 2, intent2, 134217728));
        Log.v("HeapDumpReceiver", "Creating share heap dump notification");
        NotificationManager.from(context).notify(2019, contentIntent.build());
    }
}
