package com.android.systemui.util.leak;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Debug;
import android.os.Parcelable;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.google.android.collect.Lists;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class LeakReporter {
    public static final String FILEPROVIDER_AUTHORITY = "com.android.systemui.fileprovider";
    static final String LEAK_DIR = "leak";
    static final String LEAK_DUMP = "leak.dump";
    static final String LEAK_HPROF = "leak.hprof";
    static final String TAG = "LeakReporter";
    private final Context mContext;
    private final LeakDetector mLeakDetector;
    private final String mLeakReportEmail;

    public LeakReporter(Context context, LeakDetector leakDetector, String leakReportEmail) {
        this.mContext = context;
        this.mLeakDetector = leakDetector;
        this.mLeakReportEmail = leakReportEmail;
    }

    public void dumpLeak(int garbageCount) {
        try {
            File file = new File(this.mContext.getCacheDir(), LEAK_DIR);
            file.mkdir();
            File file2 = new File(file, LEAK_HPROF);
            Debug.dumpHprofData(file2.getAbsolutePath());
            File file3 = new File(file, LEAK_DUMP);
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            try {
                PrintWriter printWriter = new PrintWriter(fileOutputStream);
                printWriter.print("Build: ");
                printWriter.println(SystemProperties.get("ro.build.description"));
                printWriter.println();
                printWriter.flush();
                this.mLeakDetector.dump(fileOutputStream.getFD(), printWriter, new String[0]);
                printWriter.close();
                fileOutputStream.close();
                NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService(NotificationManager.class);
                NotificationChannel notificationChannel = new NotificationChannel(LEAK_DIR, "Leak Alerts", 4);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
                notificationManager.notify(TAG, 0, new Notification.Builder(this.mContext, notificationChannel.getId()).setAutoCancel(true).setShowWhen(true).setContentTitle("Memory Leak Detected").setContentText(String.format("SystemUI has detected %d leaked objects. Tap to send", Integer.valueOf(garbageCount))).setSmallIcon(R.drawable.pointer_wait_39).setContentIntent(PendingIntent.getActivityAsUser(this.mContext, 0, getIntent(file2, file3), 134217728, null, UserHandle.CURRENT)).build());
            } finally {
            }
        } catch (IOException e) {
            Log.e(TAG, "Couldn't dump heap for leak", e);
        }
    }

    private Intent getIntent(File hprofFile, File dumpFile) {
        Uri uriForFile = FileProvider.getUriForFile(this.mContext, FILEPROVIDER_AUTHORITY, dumpFile);
        Uri uriForFile2 = FileProvider.getUriForFile(this.mContext, FILEPROVIDER_AUTHORITY, hprofFile);
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        intent.addFlags(1);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("application/vnd.android.leakreport");
        intent.putExtra("android.intent.extra.SUBJECT", "SystemUI leak report");
        intent.putExtra("android.intent.extra.TEXT", "Build info: " + SystemProperties.get("ro.build.description"));
        ClipData clipData = new ClipData(null, new String[]{"application/vnd.android.leakreport"}, new ClipData.Item(null, null, null, uriForFile));
        ArrayList<? extends Parcelable> arrayListNewArrayList = Lists.newArrayList(new Uri[]{uriForFile});
        clipData.addItem(new ClipData.Item(null, null, null, uriForFile2));
        arrayListNewArrayList.add(uriForFile2);
        intent.setClipData(clipData);
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayListNewArrayList);
        String str = this.mLeakReportEmail;
        if (str != null) {
            intent.putExtra("android.intent.extra.EMAIL", new String[]{str});
        }
        return intent;
    }
}
