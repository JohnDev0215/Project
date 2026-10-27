package org.chromium.content.browser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.R;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class TracingControllerAndroid {
    private static final String ACTION_LIST_CATEGORIES = "GPU_PROFILER_LIST_CATEGORIES";
    private static final String ACTION_START = "GPU_PROFILER_START";
    private static final String ACTION_STOP = "GPU_PROFILER_STOP";
    private static final String CATEGORIES_EXTRA = "categories";
    private static final String DEFAULT_CHROME_CATEGORIES_PLACE_HOLDER = "_DEFAULT_CHROME_CATEGORIES";
    private static final String FILE_EXTRA = "file";
    private static final String PROFILER_FINISHED_FMT = "Profiler finished. Results are in %s.";
    private static final String PROFILER_STARTED_FMT = "Profiler started: %s";
    private static final String RECORD_CONTINUOUSLY_EXTRA = "continuous";
    private static final String TAG = "cr.TracingController";
    private final Context mContext;
    private String mFilename;
    private final TracingIntentFilter mIntentFilter;
    private boolean mIsTracing;
    private long mNativeTracingControllerAndroid;
    private boolean mShowToasts = true;
    private final TracingBroadcastReceiver mBroadcastReceiver = new TracingBroadcastReceiver();

    private native void nativeDestroy(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native String nativeGetDefaultCategories();

    private native boolean nativeGetKnownCategoryGroupsAsync(long j);

    private native long nativeInit();

    private native boolean nativeStartTracing(long j, String str, String str2);

    private native void nativeStopTracing(long j, String str);

    public TracingControllerAndroid(Context context) {
        this.mContext = context;
        this.mIntentFilter = new TracingIntentFilter(context);
    }

    public BroadcastReceiver getBroadcastReceiver() {
        return this.mBroadcastReceiver;
    }

    public IntentFilter getIntentFilter() {
        return this.mIntentFilter;
    }

    public void registerReceiver(Context context) {
        context.registerReceiver(getBroadcastReceiver(), getIntentFilter());
    }

    public void unregisterReceiver(Context context) {
        context.unregisterReceiver(getBroadcastReceiver());
    }

    public boolean isTracing() {
        return this.mIsTracing;
    }

    public String getOutputPath() {
        return this.mFilename;
    }

    private static String generateTracingFilePath() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "chrome-profile-results-" + simpleDateFormat.format(new Date())).getPath();
    }

    public boolean startTracing(boolean z, String str, String str2) {
        this.mShowToasts = z;
        String strGenerateTracingFilePath = generateTracingFilePath();
        if (strGenerateTracingFilePath == null) {
            logAndToastError(this.mContext.getString(R.string.profiler_no_storage_toast));
        }
        return startTracing(strGenerateTracingFilePath, z, str, str2);
    }

    private void initializeNativeControllerIfNeeded() {
        if (this.mNativeTracingControllerAndroid == 0) {
            this.mNativeTracingControllerAndroid = nativeInit();
        }
    }

    public boolean startTracing(String str, boolean z, String str2, String str3) {
        this.mShowToasts = z;
        if (isTracing()) {
            Log.e(TAG, "Received startTracing, but we're already tracing", new Object[0]);
            return false;
        }
        initializeNativeControllerIfNeeded();
        if (!nativeStartTracing(this.mNativeTracingControllerAndroid, str2, str3.toString())) {
            logAndToastError(this.mContext.getString(R.string.profiler_error_toast));
            return false;
        }
        logForProfiler(String.format(PROFILER_STARTED_FMT, str2));
        showToast(this.mContext.getString(R.string.profiler_started_toast) + ": " + str2);
        this.mFilename = str;
        this.mIsTracing = true;
        return true;
    }

    public void stopTracing() {
        if (isTracing()) {
            nativeStopTracing(this.mNativeTracingControllerAndroid, this.mFilename);
        }
    }

    protected void onTracingStopped() {
        if (!isTracing()) {
            Log.e(TAG, "Received onTracingStopped, but we aren't tracing", new Object[0]);
            return;
        }
        logForProfiler(String.format(PROFILER_FINISHED_FMT, this.mFilename));
        showToast(this.mContext.getString(R.string.profiler_stopped_toast, this.mFilename));
        this.mIsTracing = false;
        this.mFilename = null;
    }

    public void getCategoryGroups() {
        initializeNativeControllerIfNeeded();
        if (nativeGetKnownCategoryGroupsAsync(this.mNativeTracingControllerAndroid)) {
            return;
        }
        Log.e(TAG, "Unable to fetch tracing record groups list.", new Object[0]);
    }

    public void destroy() {
        long j = this.mNativeTracingControllerAndroid;
        if (j != 0) {
            nativeDestroy(j);
            this.mNativeTracingControllerAndroid = 0L;
        }
    }

    private void logAndToastError(String str) {
        Log.e(TAG, str, new Object[0]);
        if (this.mShowToasts) {
            Toast.makeText(this.mContext, str, 0).show();
        }
    }

    private void logForProfiler(String str) {
        Log.i(TAG, str, new Object[0]);
    }

    private void showToast(String str) {
        if (this.mShowToasts) {
            Toast.makeText(this.mContext, str, 0).show();
        }
    }

    private static class TracingIntentFilter extends IntentFilter {
        TracingIntentFilter(Context context) {
            addAction(context.getPackageName() + ".GPU_PROFILER_START");
            addAction(context.getPackageName() + ".GPU_PROFILER_STOP");
            addAction(context.getPackageName() + ".GPU_PROFILER_LIST_CATEGORIES");
        }
    }

    class TracingBroadcastReceiver extends BroadcastReceiver {
        TracingBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().endsWith(TracingControllerAndroid.ACTION_START)) {
                String stringExtra = intent.getStringExtra(TracingControllerAndroid.CATEGORIES_EXTRA);
                String strNativeGetDefaultCategories = TextUtils.isEmpty(stringExtra) ? TracingControllerAndroid.this.nativeGetDefaultCategories() : stringExtra.replaceFirst(TracingControllerAndroid.DEFAULT_CHROME_CATEGORIES_PLACE_HOLDER, TracingControllerAndroid.this.nativeGetDefaultCategories());
                String str = intent.getStringExtra(TracingControllerAndroid.RECORD_CONTINUOUSLY_EXTRA) == null ? "record-until-full" : "record-continuously";
                String stringExtra2 = intent.getStringExtra(TracingControllerAndroid.FILE_EXTRA);
                if (stringExtra2 != null) {
                    TracingControllerAndroid.this.startTracing(stringExtra2, true, strNativeGetDefaultCategories, str);
                    return;
                } else {
                    TracingControllerAndroid.this.startTracing(true, strNativeGetDefaultCategories, str);
                    return;
                }
            }
            if (intent.getAction().endsWith(TracingControllerAndroid.ACTION_STOP)) {
                TracingControllerAndroid.this.stopTracing();
            } else if (intent.getAction().endsWith(TracingControllerAndroid.ACTION_LIST_CATEGORIES)) {
                TracingControllerAndroid.this.getCategoryGroups();
            } else {
                Log.e(TracingControllerAndroid.TAG, "Unexpected intent: %s", intent);
            }
        }
    }
}
