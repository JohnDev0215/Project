package com.ts.appservice.settings.impl.version;

import android.content.Context;
import android.os.SystemProperties;
import com.android.common.speech.LoggingEvents;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.version.IVersionSettingsService;

/* JADX INFO: loaded from: classes.dex */
public class IVersionServiceBinder extends IVersionSettingsService.Stub {
    private static final String HW_VERSION_NAME = "persist.vendor.bosch.hw.ver";
    private static final String INSTRUMENT_HW_VERSION_NAME = "persist.vendor.bosch.cluster.ver";
    private static final String INSTRUMENT_SW_VERSION_NAME = "persist.vendor.bosch.cluster.swver";
    private static final String MCU_BOOT_VERSION_NAME = "persist.vendor.bosch.mcuboot.ver";
    private static final String MCU_VERSION_NAME = "persist.vendor.bosch.mcu.ver";
    private static final String SYSTEM_VERSION_NAME = "ro.product.version.soc.name";
    private static final String TAG = IVersionServiceBinder.class.getSimpleName();
    private static final String VEHICLE_SN_VERSION_NAME = "persist.vendor.bosch.cfg.diag.sn";
    private static final String VERSION_DEFAULT_NAME = "51.1.1.2209270.1";
    private Context mContext;
    private String mSystemVersionMessage = VERSION_DEFAULT_NAME;
    private String mAudioBoxVersionMessager = LoggingEvents.EXTRA_CALLING_APP_NAME;
    private String mHwVersionMessage = LoggingEvents.EXTRA_CALLING_APP_NAME;
    private String mInstrumentHwVersionMessage = LoggingEvents.EXTRA_CALLING_APP_NAME;
    private String mInstrumentSwVersionMessage = LoggingEvents.EXTRA_CALLING_APP_NAME;
    private String mVehicleSnVersionMessage = LoggingEvents.EXTRA_CALLING_APP_NAME;
    private String[] mMcuVersionMessage = {LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME};
    private String mDspVersionMessage = LoggingEvents.EXTRA_CALLING_APP_NAME;

    public IVersionServiceBinder(Context context) {
        this.mContext = context;
    }

    public String getBydSystemVersion() {
        this.mSystemVersionMessage = SystemProperties.get(SYSTEM_VERSION_NAME, VERSION_DEFAULT_NAME);
        LogUtil.info(TAG, "getBydSystemVersion: " + this.mSystemVersionMessage);
        return this.mSystemVersionMessage;
    }

    public String getHwVersion() {
        this.mHwVersionMessage = SystemProperties.get(HW_VERSION_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
        LogUtil.info(TAG, "getHwVersion: " + this.mHwVersionMessage);
        return this.mHwVersionMessage;
    }

    public String getInstrumentHwVersion() {
        this.mInstrumentHwVersionMessage = SystemProperties.get(INSTRUMENT_HW_VERSION_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
        LogUtil.info(TAG, "getInstrumentHwVersion: " + this.mInstrumentHwVersionMessage);
        return this.mInstrumentHwVersionMessage;
    }

    public String getInstrumentSwVersion() {
        this.mInstrumentSwVersionMessage = SystemProperties.get(INSTRUMENT_SW_VERSION_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
        LogUtil.info(TAG, "getInstrumentSwVersion: " + this.mInstrumentSwVersionMessage);
        return this.mInstrumentSwVersionMessage;
    }

    public String getVehicleSN() {
        this.mVehicleSnVersionMessage = SystemProperties.get(VEHICLE_SN_VERSION_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
        LogUtil.info(TAG, "getVehicleSN: " + this.mVehicleSnVersionMessage);
        return this.mVehicleSnVersionMessage;
    }

    public String[] getMcuVersion() {
        String mcuBootVersion = SystemProperties.get(MCU_BOOT_VERSION_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
        String mcuAppVersion = SystemProperties.get(MCU_VERSION_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
        LogUtil.debug(TAG, "mcuBootVersion: " + mcuBootVersion + " mcuAppVersion: " + mcuAppVersion);
        this.mMcuVersionMessage = new String[]{mcuBootVersion, mcuAppVersion};
        LogUtil.info(TAG, "getMcuVersion: {" + this.mMcuVersionMessage[0] + "," + this.mMcuVersionMessage[1] + "}");
        return this.mMcuVersionMessage;
    }
}
