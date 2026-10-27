package com.android.launcher3.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.android.launcher3.MainThreadExecutor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
public class ConfigMonitor extends BroadcastReceiver implements DisplayManager.DisplayListener {
    private static final String TAG = "ConfigMonitor";
    private Consumer<Context> mCallback;
    private final Context mContext;
    private final int mDensity;
    private final int mDisplayId;
    private final float mFontScale;
    private final Point mLargestSize;
    private final Point mRealSize;
    private final Point mSmallestSize;
    private final Point mTmpPoint1 = new Point();
    private final Point mTmpPoint2 = new Point();

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int displayId) {
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int displayId) {
    }

    public ConfigMonitor(Context context, Consumer<Context> callback) {
        this.mContext = context;
        Configuration configuration = context.getResources().getConfiguration();
        this.mFontScale = configuration.fontScale;
        this.mDensity = configuration.densityDpi;
        Display defaultDisplay = getDefaultDisplay(context);
        this.mDisplayId = defaultDisplay.getDisplayId();
        Point point = new Point();
        this.mRealSize = point;
        defaultDisplay.getRealSize(point);
        Point point2 = new Point();
        this.mSmallestSize = point2;
        Point point3 = new Point();
        this.mLargestSize = point3;
        defaultDisplay.getCurrentSizeRange(point2, point3);
        this.mCallback = callback;
        context.registerReceiver(this, new IntentFilter(Intent.ACTION_CONFIGURATION_CHANGED));
        ((DisplayManager) context.getSystemService(DisplayManager.class)).registerDisplayListener(this, new Handler(UiThreadHelper.getBackgroundLooper()));
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Configuration configuration = context.getResources().getConfiguration();
        if (this.mFontScale == configuration.fontScale && this.mDensity == configuration.densityDpi) {
            return;
        }
        Log.d(TAG, "Configuration changed.");
        notifyChange();
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int displayId) {
        if (displayId != this.mDisplayId) {
            return;
        }
        Display defaultDisplay = getDefaultDisplay(this.mContext);
        defaultDisplay.getRealSize(this.mTmpPoint1);
        if (!this.mRealSize.equals(this.mTmpPoint1) && !this.mRealSize.equals(this.mTmpPoint1.y, this.mTmpPoint1.x)) {
            Log.d(TAG, String.format("Display size changed from %s to %s", this.mRealSize, this.mTmpPoint1));
            notifyChange();
            return;
        }
        defaultDisplay.getCurrentSizeRange(this.mTmpPoint1, this.mTmpPoint2);
        if (this.mSmallestSize.equals(this.mTmpPoint1) && this.mLargestSize.equals(this.mTmpPoint2)) {
            return;
        }
        Log.d(TAG, String.format("Available size changed from [%s, %s] to [%s, %s]", this.mSmallestSize, this.mLargestSize, this.mTmpPoint1, this.mTmpPoint2));
        notifyChange();
    }

    private synchronized void notifyChange() {
        final Consumer<Context> consumer = this.mCallback;
        if (consumer != null) {
            this.mCallback = null;
            new MainThreadExecutor().execute(new Runnable() { // from class: com.android.launcher3.util.-$$Lambda$ConfigMonitor$NjUPuJWgs2qhLFf36k7aw9RDtPc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$notifyChange$0$ConfigMonitor(consumer);
                }
            });
        }
    }

    public /* synthetic */ void lambda$notifyChange$0$ConfigMonitor(Consumer consumer) {
        consumer.accept(this.mContext);
    }

    private Display getDefaultDisplay(Context context) {
        return ((WindowManager) context.getSystemService(WindowManager.class)).getDefaultDisplay();
    }

    public void unregister() {
        try {
            this.mContext.unregisterReceiver(this);
            ((DisplayManager) this.mContext.getSystemService(DisplayManager.class)).unregisterDisplayListener(this);
        } catch (Exception e) {
            Log.e(TAG, "Failed to unregister config monitor", e);
        }
    }
}
