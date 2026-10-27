package com.android.systemui.util.leak;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.util.LongSparseArray;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SystemUI;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.util.SystemUIException;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class GarbageMonitor {
    private static final int DO_GARBAGE_INSPECTION = 1000;
    private static final int DO_HEAP_TRACK = 3000;
    private static final boolean ENABLE_AM_HEAP_LIMIT = true;
    private static final String FORCE_ENABLE_LEAK_REPORTING = "sysui_force_enable_leak_reporting";
    private static final int GARBAGE_ALLOWANCE = 5;
    private static final long GARBAGE_INSPECTION_INTERVAL = 900000;
    private static final boolean HEAP_TRACKING_ENABLED;
    private static final long HEAP_TRACK_INTERVAL = 60000;
    private static final boolean LEAK_REPORTING_ENABLED;
    private static final String TAG = "GarbageMonitor";
    private final ActivityManager mAm;
    private final Context mContext;
    private DumpTruck mDumpTruck;
    private final Handler mHandler;
    private long mHeapLimit;
    private final LeakReporter mLeakReporter;
    private final TrackedGarbage mTrackedGarbage;
    private final LongSparseArray<ProcessMemInfo> mData = new LongSparseArray<>();
    private final ArrayList<Long> mPids = new ArrayList<>();
    private int[] mPidsArray = new int[1];

    static {
        boolean z = false;
        if (Build.IS_DEBUGGABLE && SystemProperties.getBoolean("debug.enable_leak_reporting", false)) {
            z = true;
        }
        LEAK_REPORTING_ENABLED = z;
        HEAP_TRACKING_ENABLED = Build.IS_DEBUGGABLE;
    }

    public GarbageMonitor(Context context, Looper bgLooper, LeakDetector leakDetector, LeakReporter leakReporter) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mAm = (ActivityManager) context.getSystemService(Constants.PackageInfo.ACTIVITY);
        this.mHandler = new BackgroundHeapCheckHandler(bgLooper);
        this.mTrackedGarbage = leakDetector.getTrackedGarbage();
        this.mLeakReporter = leakReporter;
        this.mDumpTruck = new DumpTruck(applicationContext);
        this.mHeapLimit = applicationContext.getResources().getInteger(R.integer.watch_heap_limit);
    }

    public void startLeakMonitor() {
        if (this.mTrackedGarbage == null) {
            return;
        }
        this.mHandler.sendEmptyMessage(1000);
    }

    public void startHeapTracking() {
        startTrackingProcess(Process.myPid(), this.mContext.getPackageName(), System.currentTimeMillis());
        this.mHandler.sendEmptyMessage(3000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean gcAndCheckGarbage() {
        if (this.mTrackedGarbage.countOldGarbage() <= 5) {
            return false;
        }
        Runtime.getRuntime().gc();
        return true;
    }

    void reinspectGarbageAfterGc() {
        int iCountOldGarbage = this.mTrackedGarbage.countOldGarbage();
        if (iCountOldGarbage > 5) {
            this.mLeakReporter.dumpLeak(iCountOldGarbage);
        }
    }

    public ProcessMemInfo getMemInfo(int pid) {
        return this.mData.get(pid);
    }

    public int[] getTrackedProcesses() {
        return this.mPidsArray;
    }

    public void startTrackingProcess(long pid, String name, long start) {
        synchronized (this.mPids) {
            if (this.mPids.contains(Long.valueOf(pid))) {
                return;
            }
            this.mPids.add(Long.valueOf(pid));
            updatePidsArrayL();
            this.mData.put(pid, new ProcessMemInfo(pid, name, start));
        }
    }

    private void updatePidsArrayL() {
        int size = this.mPids.size();
        this.mPidsArray = new int[size];
        StringBuffer stringBuffer = new StringBuffer("Now tracking processes: ");
        for (int i = 0; i < size; i++) {
            int iIntValue = this.mPids.get(i).intValue();
            this.mPidsArray[i] = iIntValue;
            stringBuffer.append(iIntValue);
            stringBuffer.append(" ");
        }
        Log.v(TAG, stringBuffer.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        synchronized (this.mPids) {
            Debug.MemoryInfo[] processMemoryInfo = this.mAm.getProcessMemoryInfo(this.mPidsArray);
            int i = 0;
            while (true) {
                if (i >= processMemoryInfo.length) {
                    break;
                }
                Debug.MemoryInfo memoryInfo = processMemoryInfo[i];
                if (i > this.mPids.size()) {
                    Log.e(TAG, "update: unknown process info received: " + memoryInfo);
                    break;
                }
                long jIntValue = this.mPids.get(i).intValue();
                ProcessMemInfo processMemInfo = this.mData.get(jIntValue);
                processMemInfo.head = (processMemInfo.head + 1) % processMemInfo.pss.length;
                long[] jArr = processMemInfo.pss;
                int i2 = processMemInfo.head;
                long totalPss = memoryInfo.getTotalPss();
                processMemInfo.currentPss = totalPss;
                jArr[i2] = totalPss;
                long[] jArr2 = processMemInfo.uss;
                int i3 = processMemInfo.head;
                long totalPrivateDirty = memoryInfo.getTotalPrivateDirty();
                processMemInfo.currentUss = totalPrivateDirty;
                jArr2[i3] = totalPrivateDirty;
                if (processMemInfo.currentPss > processMemInfo.max) {
                    processMemInfo.max = processMemInfo.currentPss;
                }
                if (processMemInfo.currentUss > processMemInfo.max) {
                    processMemInfo.max = processMemInfo.currentUss;
                }
                if (processMemInfo.currentPss == 0) {
                    Log.v(TAG, "update: pid " + jIntValue + " has pss=0, it probably died");
                    this.mData.remove(jIntValue);
                }
                i++;
            }
            for (int size = this.mPids.size() - 1; size >= 0; size--) {
                if (this.mData.get(this.mPids.get(size).intValue()) == null) {
                    this.mPids.remove(size);
                    updatePidsArrayL();
                }
            }
        }
    }

    private static String formatBytes(long b) {
        String[] strArr = {"B", "K", "M", "G", ExifInterface.GPS_DIRECTION_TRUE};
        int i = 0;
        while (i < 5 && b >= 1024) {
            b /= 1024;
            i++;
        }
        return b + strArr[i];
    }

    private void dumpHprofAndShare() {
        this.mContext.startActivity(this.mDumpTruck.captureHeaps(getTrackedProcesses()).createShareIntent());
    }

    private static class MemoryIconDrawable extends Drawable {
        final Drawable baseIcon;
        final float dp;
        long limit;
        final Paint paint;
        long pss;

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        MemoryIconDrawable(Context context) {
            Paint paint = new Paint();
            this.paint = paint;
            this.baseIcon = context.getDrawable(R.drawable.ic_selector_home).mutate();
            this.dp = context.getResources().getDisplayMetrics().density;
            paint.setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }

        public void setPss(long pss) {
            if (pss != this.pss) {
                this.pss = pss;
                invalidateSelf();
            }
        }

        public void setLimit(long limit) {
            if (limit != this.limit) {
                this.limit = limit;
                invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            this.baseIcon.draw(canvas);
            long j = this.limit;
            if (j > 0) {
                long j2 = this.pss;
                if (j2 > 0) {
                    float fMin = Math.min(1.0f, j2 / j);
                    Rect bounds = getBounds();
                    canvas.translate(bounds.left + (this.dp * 8.0f), bounds.top + (this.dp * 5.0f));
                    float f = this.dp;
                    canvas.drawRect(0.0f, f * 14.0f * (1.0f - fMin), (8.0f * f) + 1.0f, (f * 14.0f) + 1.0f, this.paint);
                }
            }
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(int left, int top, int right, int bottom) {
            super.setBounds(left, top, right, bottom);
            this.baseIcon.setBounds(left, top, right, bottom);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.baseIcon.getIntrinsicHeight();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.baseIcon.getIntrinsicWidth();
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.baseIcon.setAlpha(i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.baseIcon.setColorFilter(colorFilter);
            this.paint.setColorFilter(colorFilter);
        }

        @Override // android.graphics.drawable.Drawable
        public void setTint(int tint) {
            super.setTint(tint);
            this.baseIcon.setTint(tint);
        }

        @Override // android.graphics.drawable.Drawable
        public void setTintList(ColorStateList tint) {
            super.setTintList(tint);
            this.baseIcon.setTintList(tint);
        }

        @Override // android.graphics.drawable.Drawable
        public void setTintMode(PorterDuff.Mode tintMode) {
            super.setTintMode(tintMode);
            this.baseIcon.setTintMode(tintMode);
        }
    }

    private static class MemoryGraphIcon extends QSTile.Icon {
        long limit;
        long pss;

        private MemoryGraphIcon() {
        }

        public void setPss(long pss) {
            this.pss = pss;
        }

        public void setHeapLimit(long limit) {
            this.limit = limit;
        }

        @Override // com.android.systemui.plugins.qs.QSTile.Icon
        public Drawable getDrawable(Context context) {
            MemoryIconDrawable memoryIconDrawable = new MemoryIconDrawable(context);
            memoryIconDrawable.setPss(this.pss);
            memoryIconDrawable.setLimit(this.limit);
            return memoryIconDrawable;
        }
    }

    public static class ProcessMemInfo {
        public long currentPss;
        public long currentUss;
        public String name;
        public long pid;
        public long startTime;
        public long[] pss = new long[256];
        public long[] uss = new long[256];
        public long max = 1;
        public int head = 0;

        public ProcessMemInfo(long pid, String name, long start) {
            this.pid = pid;
            this.name = name;
            this.startTime = start;
        }

        public long getUptime() {
            return System.currentTimeMillis() - this.startTime;
        }
    }

    public static class Service extends SystemUI {
        private GarbageMonitor mGarbageMonitor;

        @Override // com.android.systemui.SystemUI
        public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        }

        @Override // com.android.systemui.SystemUI
        protected void onBootCompleted() {
        }

        @Override // com.android.systemui.SystemUI
        protected void onConfigurationChanged(Configuration newConfig) {
        }

        @Override // com.android.systemui.SystemUI
        public void start() {
            boolean z = Settings.Secure.getInt(this.mContext.getContentResolver(), GarbageMonitor.FORCE_ENABLE_LEAK_REPORTING, 0) != 0;
            this.mGarbageMonitor = (GarbageMonitor) Dependency.get(GarbageMonitor.class);
            if (GarbageMonitor.LEAK_REPORTING_ENABLED || z) {
                this.mGarbageMonitor.startLeakMonitor();
            }
            if (GarbageMonitor.HEAP_TRACKING_ENABLED || z) {
                this.mGarbageMonitor.startHeapTracking();
            }
        }
    }

    private class BackgroundHeapCheckHandler extends Handler {
        BackgroundHeapCheckHandler(Looper onLooper) {
            super(onLooper);
            if (Looper.getMainLooper().equals(onLooper)) {
                throw new SystemUIException("BackgroundHeapCheckHandler may not run on the ui thread");
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message m) {
            int i = m.what;
            if (i != 1000) {
                if (i != 3000) {
                    return;
                }
                GarbageMonitor.this.update();
                removeMessages(3000);
                sendEmptyMessageDelayed(3000, GarbageMonitor.HEAP_TRACK_INTERVAL);
                return;
            }
            if (GarbageMonitor.this.gcAndCheckGarbage()) {
                final GarbageMonitor garbageMonitor = GarbageMonitor.this;
                postDelayed(new Runnable() { // from class: com.android.systemui.util.leak.-$$Lambda$9GgzpVy9pZOESciMWGtFKQSt1Sk
                    @Override // java.lang.Runnable
                    public final void run() {
                        garbageMonitor.reinspectGarbageAfterGc();
                    }
                }, 100L);
            }
            removeMessages(1000);
            sendEmptyMessageDelayed(1000, GarbageMonitor.GARBAGE_INSPECTION_INTERVAL);
        }
    }
}
