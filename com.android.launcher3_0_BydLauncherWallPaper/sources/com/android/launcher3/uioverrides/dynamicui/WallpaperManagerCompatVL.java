package com.android.launcher3.uioverrides.dynamicui;

import android.app.WallpaperManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.telephony.SmsManager;
import android.util.Log;
import android.util.Pair;
import com.android.launcher3.Utilities;
import com.android.launcher3.icons.ColorExtractor;
import com.android.launcher3.uioverrides.dynamicui.WallpaperManagerCompat;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class WallpaperManagerCompatVL extends WallpaperManagerCompat {
    private static final String ACTION_EXTRACTION_COMPLETE = "com.android.launcher3.uioverrides.dynamicui.WallpaperManagerCompatVL.EXTRACTION_COMPLETE";
    private static final String KEY_COLORS = "wallpaper_parsed_colors";
    private static final String TAG = "WMCompatVL";
    private static final String VERSION_PREFIX = "1,";
    public static final int WALLPAPER_COMPAT_JOB_ID = 1;
    private WallpaperColorsCompat mColorsCompat;
    private final Context mContext;
    private final ArrayList<WallpaperManagerCompat.OnColorsChangedListenerCompat> mListeners = new ArrayList<>();

    WallpaperManagerCompatVL(Context context) {
        int iIntValue;
        this.mContext = context;
        String string = Utilities.getDevicePrefs(context).getString(KEY_COLORS, "");
        if (string.startsWith(VERSION_PREFIX)) {
            Pair<Integer, WallpaperColorsCompat> value = parseValue(string);
            iIntValue = value.first.intValue();
            this.mColorsCompat = value.second;
        } else {
            iIntValue = -1;
        }
        if (iIntValue == -1 || iIntValue != getWallpaperId(context)) {
            reloadColors();
        }
        context.registerReceiver(new BroadcastReceiver() { // from class: com.android.launcher3.uioverrides.dynamicui.WallpaperManagerCompatVL.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                WallpaperManagerCompatVL.this.reloadColors();
            }
        }, new IntentFilter(Intent.ACTION_WALLPAPER_CHANGED));
        String str = null;
        try {
            for (PermissionInfo permissionInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).permissions) {
                if ((permissionInfo.protectionLevel & 2) != 0) {
                    str = permissionInfo.name;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "Unable to get permission info", e);
        }
        this.mContext.registerReceiver(new BroadcastReceiver() { // from class: com.android.launcher3.uioverrides.dynamicui.WallpaperManagerCompatVL.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                WallpaperManagerCompatVL.this.handleResult(intent.getStringExtra(WallpaperManagerCompatVL.KEY_COLORS));
            }
        }, new IntentFilter(ACTION_EXTRACTION_COMPLETE), str, new Handler());
    }

    @Override // com.android.launcher3.uioverrides.dynamicui.WallpaperManagerCompat
    public WallpaperColorsCompat getWallpaperColors(int which) {
        if (which == 1) {
            return this.mColorsCompat;
        }
        return null;
    }

    @Override // com.android.launcher3.uioverrides.dynamicui.WallpaperManagerCompat
    public void addOnColorsChangedListener(WallpaperManagerCompat.OnColorsChangedListenerCompat listener) {
        this.mListeners.add(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reloadColors() {
        ((JobScheduler) this.mContext.getSystemService(Context.JOB_SCHEDULER_SERVICE)).schedule(new JobInfo.Builder(1, new ComponentName(this.mContext, (Class<?>) ColorExtractionService.class)).setMinimumLatency(0L).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(String result) {
        Utilities.getDevicePrefs(this.mContext).edit().putString(KEY_COLORS, result).apply();
        this.mColorsCompat = parseValue(result).second;
        Iterator<WallpaperManagerCompat.OnColorsChangedListenerCompat> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onColorsChanged(this.mColorsCompat, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getWallpaperId(Context context) {
        return ((WallpaperManager) context.getSystemService(WallpaperManager.class)).getWallpaperId(1);
    }

    private static Pair<Integer, WallpaperColorsCompat> parseValue(String value) {
        String[] strArrSplit = value.split(SmsManager.REGEX_PREFIX_DELIMITER);
        Integer numValueOf = Integer.valueOf(Integer.parseInt(strArrSplit[1]));
        if (strArrSplit.length == 2) {
            return Pair.create(numValueOf, null);
        }
        return Pair.create(numValueOf, new WallpaperColorsCompat(strArrSplit.length > 2 ? Integer.parseInt(strArrSplit[2]) : 0, strArrSplit.length > 3 ? Integer.parseInt(strArrSplit[3]) : 0, strArrSplit.length > 4 ? Integer.parseInt(strArrSplit[4]) : 0, 0));
    }

    public static class ColorExtractionService extends JobService implements Runnable {
        private static final int MAX_WALLPAPER_EXTRACTION_AREA = 12544;
        private ColorExtractor mColorExtractor;
        private Handler mWorkerHandler;
        private HandlerThread mWorkerThread;

        @Override // android.app.Service
        public void onCreate() {
            super.onCreate();
            HandlerThread handlerThread = new HandlerThread("ColorExtractionService");
            this.mWorkerThread = handlerThread;
            handlerThread.start();
            this.mWorkerHandler = new Handler(this.mWorkerThread.getLooper());
            this.mColorExtractor = new ColorExtractor();
        }

        @Override // android.app.Service
        public void onDestroy() {
            super.onDestroy();
            this.mWorkerThread.quit();
        }

        @Override // android.app.job.JobService
        public boolean onStartJob(final JobParameters jobParameters) {
            this.mWorkerHandler.post(this);
            return true;
        }

        @Override // android.app.job.JobService
        public boolean onStopJob(JobParameters jobParameters) {
            this.mWorkerHandler.removeCallbacksAndMessages(null);
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x0096  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x009c  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00eb  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 290
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.uioverrides.dynamicui.WallpaperManagerCompatVL.ColorExtractionService.run():void");
        }
    }
}
