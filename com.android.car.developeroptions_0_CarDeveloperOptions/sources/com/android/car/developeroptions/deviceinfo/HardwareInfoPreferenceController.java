package com.android.car.developeroptions.deviceinfo;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.DeviceInfoUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes.dex */
public class HardwareInfoPreferenceController extends BasePreferenceController {
    private static final String TAG = "DeviceModelPrefCtrl";

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public HardwareInfoPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(R.bool.config_show_device_model) ? 1 : 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return this.mContext.getResources().getString(R.string.model_summary, getDeviceModel());
    }

    public static String getDeviceModel() {
        FutureTask futureTask = new FutureTask(new Callable() { // from class: com.android.car.developeroptions.deviceinfo.-$$Lambda$HardwareInfoPreferenceController$t6WEj_0PGzTfnmD1_dwGLm5O6EU
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DeviceInfoUtils.getMsvSuffix();
            }
        });
        futureTask.run();
        try {
            return Build.MODEL + ((String) futureTask.get());
        } catch (InterruptedException unused) {
            Log.e(TAG, "Interruption error, so we only show model name");
            return Build.MODEL;
        } catch (ExecutionException unused2) {
            Log.e(TAG, "Execution error, so we only show model name");
            return Build.MODEL;
        }
    }
}
