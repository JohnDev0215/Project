package com.android.car.developeroptions.slices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.car.developeroptions.bluetooth.BluetoothSliceBuilder;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.core.SliderPreferenceController;
import com.android.car.developeroptions.core.TogglePreferenceController;
import com.android.car.developeroptions.notification.ZenModeSliceBuilder;
import com.android.car.developeroptions.overlay.FeatureFactory;

/* JADX INFO: loaded from: classes.dex */
public class SliceBroadcastReceiver extends BroadcastReceiver {
    private static String TAG = "SettSliceBroadcastRec";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra("com.android.car.developeroptions.slice.extra.key");
        boolean booleanExtra = intent.getBooleanExtra("com.android.car.developeroptions.slice.extra.platform", false);
        if (CustomSliceRegistry.isValidAction(action)) {
            CustomSliceable.createInstance(context, CustomSliceRegistry.getSliceClassByUri(Uri.parse(action))).onNotifyChange(intent);
        }
        switch (action) {
            case "com.android.car.developeroptions.slice.action.TOGGLE_CHANGED":
                handleToggleAction(context, stringExtra, intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", false), booleanExtra);
                break;
            case "com.android.car.developeroptions.slice.action.SLIDER_CHANGED":
                handleSliderAction(context, stringExtra, intent.getIntExtra("android.app.slice.extra.RANGE_VALUE", -1), booleanExtra);
                break;
            case "com.android.car.developeroptions.bluetooth.action.BLUETOOTH_MODE_CHANGED":
                BluetoothSliceBuilder.handleUriChange(context, intent);
                break;
            case "com.android.car.developeroptions.wifi.calling.action.WIFI_CALLING_CHANGED":
                FeatureFactory.getFactory(context).getSlicesFeatureProvider().getNewWifiCallingSliceHelper(context).handleWifiCallingChanged(intent);
                break;
            case "com.android.car.developeroptions.notification.ZEN_MODE_CHANGED":
                ZenModeSliceBuilder.handleUriChange(context, intent);
                break;
            case "com.android.car.developeroptions.mobilenetwork.action.ENHANCED_4G_LTE_CHANGED":
                FeatureFactory.getFactory(context).getSlicesFeatureProvider().getNewEnhanced4gLteSliceHelper(context).handleEnhanced4gLteChanged(intent);
                break;
            case "com.android.car.developeroptions.slice.action.WIFI_CALLING_PREFERENCE_WIFI_ONLY":
            case "com.android.car.developeroptions.slice.action.WIFI_CALLING_PREFERENCE_WIFI_PREFERRED":
            case "com.android.car.developeroptions.slice.action.WIFI_CALLING_PREFERENCE_CELLULAR_PREFERRED":
                FeatureFactory.getFactory(context).getSlicesFeatureProvider().getNewWifiCallingSliceHelper(context).handleWifiCallingPreferenceChanged(intent);
                break;
            case "com.android.car.developeroptions.slice.action.COPY":
                handleCopyAction(context, stringExtra, booleanExtra);
                break;
        }
    }

    private void handleToggleAction(Context context, String str, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalStateException("No key passed to Intent for toggle controller");
        }
        BasePreferenceController preferenceController = getPreferenceController(context, str);
        if (!(preferenceController instanceof TogglePreferenceController)) {
            throw new IllegalStateException("Toggle action passed for a non-toggle key: " + str);
        }
        if (!preferenceController.isAvailable()) {
            Log.w(TAG, "Can't update " + str + " since the setting is unavailable");
            if (preferenceController.hasAsyncUpdate()) {
                return;
            }
            updateUri(context, str, z2);
            return;
        }
        ((TogglePreferenceController) preferenceController).setChecked(z);
        logSliceValueChange(context, str, z ? 1 : 0);
        if (preferenceController.hasAsyncUpdate()) {
            return;
        }
        updateUri(context, str, z2);
    }

    private void handleSliderAction(Context context, String str, int i, boolean z) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No key passed to Intent for slider controller. Use extra: com.android.car.developeroptions.slice.extra.key");
        }
        if (i == -1) {
            throw new IllegalArgumentException("Invalid position passed to Slider controller");
        }
        BasePreferenceController preferenceController = getPreferenceController(context, str);
        if (!(preferenceController instanceof SliderPreferenceController)) {
            throw new IllegalArgumentException("Slider action passed for a non-slider key: " + str);
        }
        if (!preferenceController.isAvailable()) {
            Log.w(TAG, "Can't update " + str + " since the setting is unavailable");
            updateUri(context, str, z);
            return;
        }
        SliderPreferenceController sliderPreferenceController = (SliderPreferenceController) preferenceController;
        int maxSteps = sliderPreferenceController.getMaxSteps();
        if (i < 0 || i > maxSteps) {
            throw new IllegalArgumentException("Invalid position passed to Slider controller. Expected between 0 and " + maxSteps + " but found " + i);
        }
        sliderPreferenceController.setSliderPosition(i);
        logSliceValueChange(context, str, i);
        updateUri(context, str, z);
    }

    private void handleCopyAction(Context context, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No key passed to Intent for controller");
        }
        BasePreferenceController preferenceController = getPreferenceController(context, str);
        if (!(preferenceController instanceof Sliceable)) {
            throw new IllegalArgumentException("Copyable action passed for a non-copyable key:" + str);
        }
        if (!preferenceController.isAvailable()) {
            Log.w(TAG, "Can't update " + str + " since the setting is unavailable");
            if (preferenceController.hasAsyncUpdate()) {
                return;
            }
            updateUri(context, str, z);
            return;
        }
        preferenceController.copy();
    }

    private void logSliceValueChange(Context context, String str, int i) {
        FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(0, 1372, 0, str, i);
    }

    private BasePreferenceController getPreferenceController(Context context, String str) {
        return SliceBuilderUtils.getPreferenceController(context, new SlicesDatabaseAccessor(context).getSliceDataFromKey(str));
    }

    private void updateUri(Context context, String str, boolean z) {
        context.getContentResolver().notifyChange(new Uri.Builder().scheme("content").authority(z ? "android.settings.slices" : "com.android.car.developeroptions.slices").appendPath("action").appendPath(str).build(), null);
    }
}
