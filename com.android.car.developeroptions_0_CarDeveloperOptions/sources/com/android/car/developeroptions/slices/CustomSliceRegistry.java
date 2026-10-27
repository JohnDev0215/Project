package com.android.car.developeroptions.slices;

import android.net.Uri;
import android.util.ArrayMap;
import com.android.car.developeroptions.flashlight.FlashlightSlice;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPreferenceController;
import com.android.car.developeroptions.homepage.contextualcards.deviceinfo.DataUsageSlice;
import com.android.car.developeroptions.homepage.contextualcards.deviceinfo.DeviceInfoSlice;
import com.android.car.developeroptions.homepage.contextualcards.deviceinfo.EmergencyInfoSlice;
import com.android.car.developeroptions.homepage.contextualcards.deviceinfo.StorageSlice;
import com.android.car.developeroptions.homepage.contextualcards.slices.BatteryFixSlice;
import com.android.car.developeroptions.homepage.contextualcards.slices.BluetoothDevicesSlice;
import com.android.car.developeroptions.homepage.contextualcards.slices.ContextualNotificationChannelSlice;
import com.android.car.developeroptions.homepage.contextualcards.slices.LowStorageSlice;
import com.android.car.developeroptions.homepage.contextualcards.slices.NotificationChannelSlice;
import com.android.car.developeroptions.location.LocationSlice;
import com.android.car.developeroptions.media.MediaOutputIndicatorSlice;
import com.android.car.developeroptions.media.MediaOutputSlice;
import com.android.car.developeroptions.network.telephony.MobileDataSlice;
import com.android.car.developeroptions.nfc.NfcPreferenceController;
import com.android.car.developeroptions.notification.ZenModeSoundSettingsPreferenceController;
import com.android.car.developeroptions.wifi.slice.ContextualWifiSlice;
import com.android.car.developeroptions.wifi.slice.WifiSlice;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CustomSliceRegistry {
    public static final Uri AIRPLANE_URI = new Uri.Builder().scheme("content").authority("android.settings.slices").appendPath("action").appendPath("airplane_mode").build();
    public static final Uri BATTERY_FIX_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendEncodedPath("intent").appendPath(BatteryTipPreferenceController.PREF_NAME).build();
    public static final Uri BLUETOOTH_DEVICES_SLICE_URI;
    public static final Uri CONTEXTUAL_NOTIFICATION_CHANNEL_SLICE_URI;
    public static final Uri CONTEXTUAL_WIFI_SLICE_URI;
    public static final Uri DATA_USAGE_SLICE_URI;
    public static final Uri DEVICE_INFO_SLICE_URI;
    public static final Uri EMERGENCY_INFO_SLICE_URI;
    public static final Uri ENHANCED_4G_SLICE_URI;
    public static final Uri FLASHLIGHT_SLICE_URI;
    public static final Uri LOCATION_SLICE_URI;
    public static final Uri LOW_STORAGE_SLICE_URI;
    public static Uri MEDIA_OUTPUT_INDICATOR_SLICE_URI;
    public static Uri MEDIA_OUTPUT_SLICE_URI;
    public static final Uri MOBILE_DATA_SLICE_URI;
    public static final Uri NFC_SLICE_URI;
    public static final Uri NOTIFICATION_CHANNEL_SLICE_URI;
    public static final Uri STORAGE_SLICE_URI;
    public static final Uri VOLUME_ALARM_URI;
    public static final Uri VOLUME_CALL_URI;
    public static final Uri VOLUME_MEDIA_URI;
    public static final Uri VOLUME_REMOTE_MEDIA_URI;
    public static final Uri VOLUME_RINGER_URI;
    public static final Uri WIFI_CALLING_PREFERENCE_URI;
    public static final Uri WIFI_CALLING_URI;
    public static final Uri WIFI_SLICE_URI;
    static final Map<Uri, Class<? extends CustomSliceable>> sUriToSlice;

    static {
        new Uri.Builder().scheme("content").authority("android.settings.slices").appendPath("action").appendPath("bluetooth").build();
        BLUETOOTH_DEVICES_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath("bluetooth_devices").build();
        CONTEXTUAL_NOTIFICATION_CHANNEL_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath("contextual_notification_channel").build();
        CONTEXTUAL_WIFI_SLICE_URI = new Uri.Builder().scheme("content").authority("android.settings.slices").appendPath("action").appendPath("contextual_wifi").build();
        DATA_USAGE_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath("data_usage_card").build();
        DEVICE_INFO_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("intent").appendPath("device_info_card").build();
        EMERGENCY_INFO_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("intent").appendPath("emergency_info_card").build();
        ENHANCED_4G_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath("enhanced_4g_lte").build();
        FLASHLIGHT_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath("flashlight").build();
        LOCATION_SLICE_URI = new Uri.Builder().scheme("content").authority("android.settings.slices").appendPath("action").appendPath("location").build();
        LOW_STORAGE_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendEncodedPath("intent").appendPath("low_storage").build();
        NFC_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath(NfcPreferenceController.KEY_TOGGLE_NFC).build();
        MOBILE_DATA_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendEncodedPath("action").appendPath("mobile_data").build();
        NOTIFICATION_CHANNEL_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath("notification_channel").build();
        STORAGE_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("intent").appendPath("storage_card").build();
        VOLUME_ALARM_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath("alarm_volume").build();
        VOLUME_CALL_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath("call_volume").build();
        VOLUME_MEDIA_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath("media_volume").build();
        VOLUME_REMOTE_MEDIA_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath("remote_volume").build();
        VOLUME_RINGER_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath("ring_volume").build();
        WIFI_CALLING_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("intent").appendPath("wifi_calling").build();
        WIFI_CALLING_PREFERENCE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath("wifi_calling_preference").build();
        WIFI_SLICE_URI = new Uri.Builder().scheme("content").authority("android.settings.slices").appendPath("action").appendPath("wifi").build();
        new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath(ZenModeSoundSettingsPreferenceController.ZEN_MODE_KEY).build();
        MEDIA_OUTPUT_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("action").appendPath("media_output").build();
        MEDIA_OUTPUT_INDICATOR_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.slices").appendPath("intent").appendPath("media_output_indicator").build();
        ArrayMap arrayMap = new ArrayMap();
        sUriToSlice = arrayMap;
        arrayMap.put(BATTERY_FIX_SLICE_URI, BatteryFixSlice.class);
        sUriToSlice.put(BLUETOOTH_DEVICES_SLICE_URI, BluetoothDevicesSlice.class);
        sUriToSlice.put(CONTEXTUAL_NOTIFICATION_CHANNEL_SLICE_URI, ContextualNotificationChannelSlice.class);
        sUriToSlice.put(CONTEXTUAL_WIFI_SLICE_URI, ContextualWifiSlice.class);
        sUriToSlice.put(DATA_USAGE_SLICE_URI, DataUsageSlice.class);
        sUriToSlice.put(DEVICE_INFO_SLICE_URI, DeviceInfoSlice.class);
        sUriToSlice.put(EMERGENCY_INFO_SLICE_URI, EmergencyInfoSlice.class);
        sUriToSlice.put(FLASHLIGHT_SLICE_URI, FlashlightSlice.class);
        sUriToSlice.put(LOCATION_SLICE_URI, LocationSlice.class);
        sUriToSlice.put(LOW_STORAGE_SLICE_URI, LowStorageSlice.class);
        sUriToSlice.put(MOBILE_DATA_SLICE_URI, MobileDataSlice.class);
        sUriToSlice.put(NOTIFICATION_CHANNEL_SLICE_URI, NotificationChannelSlice.class);
        sUriToSlice.put(STORAGE_SLICE_URI, StorageSlice.class);
        sUriToSlice.put(WIFI_SLICE_URI, WifiSlice.class);
        sUriToSlice.put(MEDIA_OUTPUT_SLICE_URI, MediaOutputSlice.class);
        sUriToSlice.put(MEDIA_OUTPUT_INDICATOR_SLICE_URI, MediaOutputIndicatorSlice.class);
    }

    public static Class<? extends CustomSliceable> getSliceClassByUri(Uri uri) {
        return sUriToSlice.get(removeParameterFromUri(uri));
    }

    public static Uri removeParameterFromUri(Uri uri) {
        if (uri != null) {
            return uri.buildUpon().clearQuery().build();
        }
        return null;
    }

    public static boolean isValidUri(Uri uri) {
        return sUriToSlice.containsKey(removeParameterFromUri(uri));
    }

    public static boolean isValidAction(String str) {
        return isValidUri(Uri.parse(str));
    }
}
