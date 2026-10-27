package com.android.bluetooth.btservice;

import android.bluetooth.IBluetoothManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.provider.Settings;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import com.android.bluetooth.R;
import com.android.bluetooth.a2dp.A2dpService;
import com.android.bluetooth.a2dpsink.A2dpSinkService;
import com.android.bluetooth.avrcp.AvrcpTargetService;
import com.android.bluetooth.avrcpcontroller.AvrcpControllerService;
import com.android.bluetooth.gatt.GattService;
import com.android.bluetooth.hearingaid.HearingAidService;
import com.android.bluetooth.hfp.HeadsetService;
import com.android.bluetooth.hfpclient.HeadsetClientService;
import com.android.bluetooth.hid.HidDeviceService;
import com.android.bluetooth.hid.HidHostService;
import com.android.bluetooth.map.BluetoothMapService;
import com.android.bluetooth.mapclient.MapClientService;
import com.android.bluetooth.opp.BluetoothOppService;
import com.android.bluetooth.pan.PanService;
import com.android.bluetooth.pbap.BluetoothPbapService;
import com.android.bluetooth.pbapclient.PbapClientService;
import com.android.bluetooth.powermanager.BluetoothPowerManager;
import com.android.bluetooth.sap.SapService;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Config {
    private static final String TAG = "AdapterServiceConfig";
    private static final String IS_BLE_SUPPORTED_PROPERTY = "persist.vendor.bt.is_ble_supported";
    private static boolean mIsBleSupported = SystemProperties.getBoolean(IS_BLE_SUPPORTED_PROPERTY, true);
    private static final ProfileConfig[] PROFILE_SERVICES_AND_FLAGS = {new ProfileConfig(HeadsetService.class, R.bool.profile_supported_hs_hfp, 2), new ProfileConfig(A2dpService.class, R.bool.profile_supported_a2dp, 4), new ProfileConfig(A2dpSinkService.class, R.bool.profile_supported_a2dp_sink, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH), new ProfileConfig(HidHostService.class, R.bool.profile_supported_hid_host, 16), new ProfileConfig(PanService.class, R.bool.profile_supported_pan, 32), new ProfileConfig(GattService.class, R.bool.profile_supported_gatt, 128), new ProfileConfig(BluetoothMapService.class, R.bool.profile_supported_map, 512), new ProfileConfig(HeadsetClientService.class, R.bool.profile_supported_hfpclient, PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH), new ProfileConfig(AvrcpTargetService.class, R.bool.profile_supported_avrcp_target, PlaybackStateCompat.ACTION_PLAY_FROM_URI), new ProfileConfig(AvrcpControllerService.class, R.bool.profile_supported_avrcp_controller, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM), new ProfileConfig(SapService.class, R.bool.profile_supported_sap, PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID), new ProfileConfig(PbapClientService.class, R.bool.profile_supported_pbapclient, PlaybackStateCompat.ACTION_PREPARE_FROM_URI), new ProfileConfig(MapClientService.class, R.bool.profile_supported_mapmce, PlaybackStateCompat.ACTION_SET_REPEAT_MODE), new ProfileConfig(HidDeviceService.class, R.bool.profile_supported_hid_device, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED), new ProfileConfig(BluetoothOppService.class, R.bool.profile_supported_opp, PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED), new ProfileConfig(BluetoothPbapService.class, R.bool.profile_supported_pbap, 64), new ProfileConfig(HearingAidService.class, android.R.bool.config_cecQuerySadAtracEnabled_allowed, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE)};
    private static Class[] sSupportedProfiles = new Class[0];

    private static class ProfileConfig {
        Class mClass;
        long mMask;
        int mSupported;

        ProfileConfig(Class theClass, int supportedFlag, long mask) {
            this.mClass = theClass;
            this.mSupported = supportedFlag;
            this.mMask = mask;
        }
    }

    static void init(Context ctx) {
        Resources resources;
        if (ctx == null || (resources = ctx.getResources()) == null) {
            return;
        }
        List<String> enabledProfiles = getSystemConfigEnabledProfilesForPackage(ctx.getPackageName());
        ArrayList<Class> profiles = new ArrayList<>(PROFILE_SERVICES_AND_FLAGS.length);
        for (ProfileConfig config : PROFILE_SERVICES_AND_FLAGS) {
            boolean supported = resources.getBoolean(config.mSupported);
            if (mIsBleSupported || config.mClass != GattService.class) {
                if (!supported && config.mClass == HearingAidService.class && isHearingAidSettingsEnabled(ctx)) {
                    Log.v(TAG, "Feature Flag enables support for HearingAidService");
                    supported = true;
                }
                if (enabledProfiles != null && enabledProfiles.contains(config.mClass.getName())) {
                    supported = true;
                    Log.v(TAG, config.mClass.getSimpleName() + " Feature Flag set to true by components configuration");
                }
                if (supported && !isProfileDisabled(ctx, config.mMask)) {
                    Log.v(TAG, "Adding " + config.mClass.getSimpleName());
                    profiles.add(config.mClass);
                }
            }
        }
        sSupportedProfiles = (Class[]) profiles.toArray(new Class[profiles.size()]);
        addBluetoothPowerManager(ctx);
    }

    static Class[] getSupportedProfiles() {
        return sSupportedProfiles;
    }

    private static long getProfileMask(Class profile) {
        for (ProfileConfig config : PROFILE_SERVICES_AND_FLAGS) {
            if (config.mClass == profile) {
                return config.mMask;
            }
        }
        Log.w(TAG, "Could not find profile bit mask for " + profile.getSimpleName());
        return 0L;
    }

    static long getSupportedProfilesBitMask() {
        long mask = 0;
        for (Class profileClass : getSupportedProfiles()) {
            mask |= getProfileMask(profileClass);
        }
        return mask;
    }

    private static boolean isProfileDisabled(Context context, long profileMask) {
        ContentResolver resolver = context.getContentResolver();
        long disabledProfilesBitMask = Settings.Global.getLong(resolver, "bluetooth_disabled_profiles", 0L);
        return (disabledProfilesBitMask & profileMask) != 0;
    }

    private static boolean isHearingAidSettingsEnabled(Context context) {
        if (context != null) {
            String value = Settings.Global.getString(context.getContentResolver(), "settings_bluetooth_hearing_aid");
            if (!TextUtils.isEmpty(value)) {
                return Boolean.parseBoolean(value);
            }
        }
        String value2 = SystemProperties.get("sys.fflag.override.settings_bluetooth_hearing_aid");
        if (!TextUtils.isEmpty(value2)) {
            return Boolean.parseBoolean(value2);
        }
        return false;
    }

    private static List<String> getSystemConfigEnabledProfilesForPackage(String packageName) {
        IBinder b = ServiceManager.getService("bluetooth_manager");
        if (b == null) {
            Log.e(TAG, "Bluetooth binder is null");
        }
        IBluetoothManager managerService = IBluetoothManager.Stub.asInterface(b);
        try {
            return managerService.getSystemConfigEnabledProfilesForPackage(packageName);
        } catch (RemoteException e) {
            return null;
        }
    }

    private static void addBluetoothPowerManager(Context context) {
        Resources resources = context.getResources();
        PackageManager pm = context.getPackageManager();
        if (resources != null && pm != null && pm.hasSystemFeature("android.hardware.type.automotive")) {
            boolean enablePowerManager = resources.getBoolean(R.bool.enable_power_manager);
            Log.i(TAG, "enablePowerManager " + enablePowerManager);
            if (enablePowerManager) {
                BluetoothPowerManager.createInstance(context);
            }
        }
    }
}
