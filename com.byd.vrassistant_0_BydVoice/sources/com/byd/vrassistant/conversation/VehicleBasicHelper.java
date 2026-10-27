package com.byd.vrassistant.conversation;

import android.app.UiStyleConfig;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.SystemProperties;
import android.util.Log;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.vehiclecontrol.VehicleControlRequestsEnum;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.CheckCapabilityParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlChildLockParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlEngineSoundParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlWindowLockParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlWingMirrorHeatingParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetSunroofStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetWindowLockStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetTrunkStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetBlindsStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetSunroofStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetTowingStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetTrunkStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetVehicleInfoResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetWindowLockStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.IsParkingGearOnResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.IsRainingResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.QueryCurrentSpeedResult;
import cerence.ark.assistant.controller.enumeration.DriverSeatTypeEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.enumeration.SpeedUnitsEnum;
import cerence.ark.assistant.controller.enumeration.VCTowingStatusEnum;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import cerence.ark.assistant.controller.enumeration.VehicleFunctionEnum;
import cerence.ark.assistant.controller.enumeration.YNUEnum;
import cerence.ark.assistant.controller.struct.TrunkStateInfo;
import cerence.ark.assistant.controller.struct.VehicleInfo;
import cerence.ark.assistant.controller.struct.WindowLockStateInfo;
import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SystemPropertiesUtils;
import com.byd.vrassistant.utils.TagUtil;
import com.byd.vrassistant.utils.TtsUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntPredicate;

/* JADX INFO: loaded from: classes.dex */
public class VehicleBasicHelper {
    private BYDAutoAcDevice acDevice;
    public String adasType;
    BYDAutoAudioDevice audioDevice;
    private BYDAutoStudy autoStudy;
    private BYDAutoBodyworkDevice bodyworkDevice;
    private BYDAutoGearboxDevice gearboxDevice;
    private BYDAutoInstrumentDevice instrumentDevice;
    private BYDAutoSettingDevice settingDevice;
    private BYDAutoSpeedDevice speedDevice;
    private UiStyleConfig uiStyleConfig;
    private BYDAutoVehicleHealthDevice vehicleHealthDevice;
    private static final String TAG = TagUtil.getTagName(VehicleBasicHelper.class);
    public static final String[] BLOCK_COUNTRY_CODES = {"7", "20", "27", "30", "31", "32", "33", "34", "36", "39", "40", "41", "43", "44", "45", "46", "47", "48", "49", "90", "212", "216", "230", "233", "238", "244", "260", "261", "262", "263", "351", "352", "353", "354", "355", "356", "357", "358", "359", "370", "371", "372", "373", "374", "375", "377", "379", "380", "381", "382", "385", "386", "387", "389", "420", "421", "423", "961", "962", "965", "966", "968", "971", "972", "973", "974", "992", "994", "996", "997"};
    private static final String[] CAR_LOCK_POSITION = {VehicleAreaEnum.SEAT_ROW_2_LEFT, VehicleAreaEnum.SEAT_ROW_2_RIGHT, VehicleAreaEnum.SEAT_ALL_LEFT, VehicleAreaEnum.SEAT_ALL_RIGHT, VehicleAreaEnum.SEAT_ROW_REAR, VehicleAreaEnum.SEAT_ALL};
    public static final int[] ENGINE_ANALOG_SOUND_SUPPORT = {505, 966, 971, 965, 968, 974, 212, 973, 216, 961, 962, 977, 61, 66, 81, 92, 91, 60, 62, 95, 856, 855, 63, 689, 687, 64, 84, 976, 975, 673, 853, 598, 57, 1890, 506, 51, 593, 595, 55, 591, 1242, 507, 599, 52, 502, 1876};

    static /* synthetic */ boolean lambda$contains$0(int i, int i2) {
        return i2 == i;
    }

    private int percentChange(int i) {
        if (i > 90) {
            return 100;
        }
        if (i >= 40 && i <= 60) {
            return 50;
        }
        if (i >= 5 && i <= 20) {
            return 5;
        }
        if (i < 5) {
            return 0;
        }
        return i;
    }

    private static final class SingletonHolder {
        private static final VehicleBasicHelper INSTANCE = new VehicleBasicHelper();

        private SingletonHolder() {
        }
    }

    public static VehicleBasicHelper getInstance() {
        RLog.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private VehicleBasicHelper() {
        this.adasType = "";
        try {
            RLog.i(TAG, "VehicleBasicHelper()");
            this.bodyworkDevice = BYDAutoBodyworkDevice.getInstance(BydVoiceApplication.getContext());
            this.settingDevice = BYDAutoSettingDevice.getInstance(BydVoiceApplication.getContext());
            this.speedDevice = BYDAutoSpeedDevice.getInstance(BydVoiceApplication.getContext());
            this.instrumentDevice = BYDAutoInstrumentDevice.getInstance(BydVoiceApplication.getContext());
            this.uiStyleConfig = UiStyleConfig.getInstance(BydVoiceApplication.getContext());
            this.acDevice = BYDAutoAcDevice.getInstance(BydVoiceApplication.getContext());
            this.autoStudy = BYDAutoStudy.getInstance(BydVoiceApplication.getContext());
            this.gearboxDevice = BYDAutoGearboxDevice.getInstance(BydVoiceApplication.getContext());
            this.audioDevice = BYDAutoAudioDevice.getInstance(BydVoiceApplication.getContext());
            this.vehicleHealthDevice = BYDAutoVehicleHealthDevice.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            RLog.i(TAG, "VehicleBasicHelper() Exception ", e);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:116)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:71)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0228 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0231 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x023b A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0246 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0250 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x025b A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0264 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x026f A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0278 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0283 A[Catch: Exception -> 0x0718, TRY_LEAVE, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x029f A[Catch: Exception -> 0x0718, TRY_ENTER, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02a9 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x02d1 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x02f8 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x031f A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0332 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x033e A[Catch: Exception -> 0x0718, TRY_LEAVE, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0384 A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x03e2 A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x040c A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x044a A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0476 A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x04a7 A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x04d6 A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x04dd A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:204:0x050b A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x053b A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0547 A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0574 A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:220:0x05a1 A[Catch: Exception -> 0x0700, TRY_LEAVE, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x05e5 A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x05f8 A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0624 A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:237:0x062b A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00fb A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:247:0x065b A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:259:0x068c A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:264:0x06b3 A[Catch: Exception -> 0x0700, TryCatch #0 {Exception -> 0x0700, blocks: (B:272:0x0702, B:269:0x06d9, B:156:0x0345, B:159:0x037a, B:160:0x037f, B:161:0x0384, B:166:0x03d8, B:167:0x03dd, B:168:0x03e2, B:170:0x0402, B:171:0x0407, B:172:0x040c, B:176:0x0435, B:178:0x043c, B:180:0x0445, B:179:0x0441, B:181:0x044a, B:183:0x046c, B:184:0x0471, B:185:0x0476, B:187:0x0497, B:189:0x049d, B:190:0x04a2, B:191:0x04a7, B:196:0x04cc, B:197:0x04d1, B:198:0x04d6, B:199:0x04dd, B:201:0x04ff, B:203:0x0506, B:202:0x0503, B:204:0x050b, B:209:0x0531, B:210:0x0536, B:211:0x053b, B:212:0x0547, B:214:0x056a, B:215:0x056f, B:216:0x0574, B:218:0x0597, B:219:0x059c, B:220:0x05a1, B:221:0x05a8, B:223:0x05ce, B:224:0x05d3, B:227:0x05d9, B:228:0x05e5, B:231:0x05f3, B:232:0x05f8, B:234:0x0615, B:235:0x061f, B:236:0x0624, B:237:0x062b, B:245:0x0651, B:246:0x0656, B:247:0x065b, B:257:0x0684, B:258:0x0688, B:259:0x068c, B:262:0x06ab, B:263:0x06af, B:264:0x06b3, B:267:0x06d2, B:268:0x06d6), top: B:280:0x0298, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0107 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0113 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x011e A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x012a A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0136 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0142 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x014d A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0159 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0165 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0171 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x017d A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0189 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0194 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01a0 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01ac A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01b8 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01c4 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01d0 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01db A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01e6 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01f2 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01fe A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x020a A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0214 A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x021e A[Catch: Exception -> 0x0718, TryCatch #3 {Exception -> 0x0718, blocks: (B:6:0x00b6, B:8:0x00bc, B:10:0x00c2, B:12:0x00c8, B:14:0x00ce, B:16:0x00d4, B:18:0x00da, B:20:0x00f1, B:21:0x00f6, B:135:0x029f, B:136:0x02a9, B:138:0x02c8, B:139:0x02cd, B:140:0x02d1, B:142:0x02f0, B:143:0x02f4, B:144:0x02f8, B:146:0x0317, B:147:0x031b, B:148:0x031f, B:150:0x032a, B:151:0x032e, B:152:0x0332, B:154:0x033e, B:23:0x00fb, B:26:0x0107, B:29:0x0113, B:32:0x011e, B:35:0x012a, B:38:0x0136, B:41:0x0142, B:44:0x014d, B:47:0x0159, B:50:0x0165, B:53:0x0171, B:56:0x017d, B:59:0x0189, B:62:0x0194, B:65:0x01a0, B:68:0x01ac, B:71:0x01b8, B:74:0x01c4, B:77:0x01d0, B:80:0x01db, B:83:0x01e6, B:86:0x01f2, B:89:0x01fe, B:92:0x020a, B:95:0x0214, B:98:0x021e, B:101:0x0228, B:104:0x0231, B:107:0x023b, B:110:0x0246, B:113:0x0250, B:116:0x025b, B:119:0x0264, B:122:0x026f, B:125:0x0278, B:128:0x0283), top: B:286:0x00b6 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void checkVehicleFunctionAvailability(cerence.ark.assistant.controller.domain.vehiclecontrol.param.CheckVehicleFunctionAvailabilityParam r34, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckVehicleFunctionAvailabilityResult> r35) {
        /*
            Method dump skipped, instruction units count: 2072
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleBasicHelper.checkVehicleFunctionAvailability(cerence.ark.assistant.controller.domain.vehiclecontrol.param.CheckVehicleFunctionAvailabilityParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    public boolean rotateTheScreenConfig() {
        String str = TAG;
        RLog.i(str, "rotateTheScreenConfig() called");
        int uiStyle = UiStyleConfig.getInstance(BydVoiceApplication.getContext()).getUiStyle();
        Log.i(str, "rotateTheScreenConfig: style = " + uiStyle);
        if (uiStyle == 1) {
            return false;
        }
        int i = SystemProperties.getInt("persist.vendor.byd.cfg.huc.config.area", 49);
        Log.i(str, "rotateTheScreenConfig: countryCode = " + i);
        if (i == 102) {
            int autoType = BYDAutoBodyworkDevice.getInstance(BydVoiceApplication.getContext()).getAutoType();
            Log.i(str, "rotateTheScreenConfig: 102 carType = " + autoType);
            if (autoType == 182 || autoType == 208) {
                return false;
            }
        }
        int autoType2 = BYDAutoBodyworkDevice.getInstance(BydVoiceApplication.getContext()).getAutoType();
        Log.i(str, "rotateTheScreenConfig: carType = " + autoType2);
        return autoType2 != 180;
    }

    private boolean cameraIsOk() {
        String str = TAG;
        Log.i(str, "cameraIsOk: ");
        try {
            CameraManager cameraManager = (CameraManager) BydVoiceApplication.getContext().getSystemService("camera");
            String[] cameraIdList = cameraManager.getCameraIdList();
            Log.i(str, "cameraIsOk:  cameraIds.length = " + cameraIdList.length);
            if (cameraIdList.length > 0) {
                for (String str2 : cameraIdList) {
                    Integer num = (Integer) cameraManager.getCameraCharacteristics(str2).get(CameraCharacteristics.LENS_FACING);
                    Log.i(TAG, "cameraIsOk:  cFacing = " + num);
                    if (num != null && num.intValue() == 0) {
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public void getVehicleInfo(IResponseSender<GetVehicleInfoResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "getVehicleInfo: ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getVehicleInfo】");
            GetVehicleInfoResult getVehicleInfoResult = new GetVehicleInfoResult();
            VehicleInfo vehicleInfo = new VehicleInfo();
            vehicleInfo.setVehicleCode("0x" + Integer.toHexString(SystemSettingManager.get().getVehicleType(BydVoiceApplication.getContext())).toUpperCase());
            getVehicleInfoResult.setVehicleInfo(vehicleInfo);
            if (SystemSettingManager.get().isRightDriver()) {
                vehicleInfo.setDriverSeatType(DriverSeatTypeEnum.RHD);
            } else {
                vehicleInfo.setDriverSeatType(DriverSeatTypeEnum.LHD);
            }
            vehicleInfo.setNumOfRows(2);
            RLog.i(str, "getVehicleInfo: OK  result = " + getVehicleInfoResult.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getVehicleInfo】,result【OK】");
            iResponseSender.send(new Response("OK", getVehicleInfoResult));
        } catch (Exception unused) {
            RLog.i(TAG, "getVehicleInfo: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getVehicleInfo】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void controlWingMirrorHeating(ControlWingMirrorHeatingParam controlWingMirrorHeatingParam, IResponseSender<BaseResult> iResponseSender) {
        int i;
        try {
            String str = TAG;
            RLog.i(str, "controlWingMirrorHeating: json = " + controlWingMirrorHeatingParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlWingMirrorHeating】");
            int acDefrostState = this.acDevice.getAcDefrostState(2);
            RLog.i(str, "controlWingMirrorHeating: state = " + acDefrostState);
            if ("ON".equals(controlWingMirrorHeatingParam.getAction())) {
                if (acDefrostState == 1) {
                    RLog.i(str, "controlWingMirrorHeating: ALREADY_EXPECTED");
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlWingMirrorHeating】,result【ALREADY_EXPECTED】");
                    iResponseSender.send(new Response("ALREADY_EXPECTED"));
                    return;
                }
                i = 1;
            } else {
                if (acDefrostState == 0) {
                    RLog.i(str, "controlWingMirrorHeating: ALREADY_EXPECTED");
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlWingMirrorHeating】,result【ALREADY_EXPECTED】");
                    iResponseSender.send(new Response("ALREADY_EXPECTED"));
                    return;
                }
                i = 0;
            }
            int acDefrostState2 = this.acDevice.setAcDefrostState(1, 2, i);
            RLog.i(str, "controlWingMirrorHeating: intValue = " + acDefrostState2);
            if (acDefrostState2 != 0) {
                RLog.i(str, "controlWingMirrorHeating: ERROR");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlWingMirrorHeating】,result【ERROR】");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            } else {
                RLog.i(str, "controlWingMirrorHeating: OK  intValue = " + acDefrostState2);
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlWingMirrorHeating】,result【OK】");
                iResponseSender.send(new Response("OK"));
            }
        } catch (Exception e) {
            RLog.i(TAG, "controlWingMirrorHeating: ERROR", e);
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlWingMirrorHeating】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void isRaining(IResponseSender<IsRainingResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "isRaining: ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【isRaining】");
            IsRainingResult isRainingResult = new IsRainingResult();
            isRainingResult.setIsRaining("UNKNOWN");
            RLog.i(str, "isRaining: UNKNOWN");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【isRaining】,result【OK】");
            iResponseSender.send(new Response("OK", isRainingResult));
        } catch (Exception unused) {
            RLog.i(TAG, "isRaining: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【isRaining】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void queryCurrentSpeed(IResponseSender<QueryCurrentSpeedResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "queryCurrentSpeed: ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【queryCurrentSpeed】");
            QueryCurrentSpeedResult queryCurrentSpeedResult = new QueryCurrentSpeedResult();
            int currentSpeed = (int) this.speedDevice.getCurrentSpeed();
            RLog.i(str, "queryCurrentSpeed: currentSpeed = " + currentSpeed);
            int speedUnit = this.instrumentDevice.getSpeedUnit();
            RLog.i(str, "queryCurrentSpeed: speedUnit=" + speedUnit);
            queryCurrentSpeedResult.setCurrentSpeed(currentSpeed);
            if (speedUnit == 1) {
                queryCurrentSpeedResult.setIsDangerous(currentSpeed > 65);
                queryCurrentSpeedResult.setUnit(SpeedUnitsEnum.KMH);
            } else {
                queryCurrentSpeedResult.setIsDangerous(currentSpeed > 40);
                queryCurrentSpeedResult.setUnit(SpeedUnitsEnum.MPH);
            }
            RLog.i(str, "queryCurrentSpeed: OK  result = " + queryCurrentSpeedResult.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【queryCurrentSpeed】,result【OK】");
            iResponseSender.send(new Response("OK", queryCurrentSpeedResult));
        } catch (Exception unused) {
            RLog.i(TAG, "queryCurrentSpeed: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【queryCurrentSpeed】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void getSunroofState(GetSunroofStateParam getSunroofStateParam, IResponseSender<GetSunroofStateResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "getSunroofState: ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getSunroofState】");
            GetSunroofStateResult getSunroofStateResult = new GetSunroofStateResult();
            RLog.i(str, "getSunroofState: sunroofOnLine = " + this.bodyworkDevice.getMoonRoofConfig());
            getSunroofStateResult.setIsTilted(false);
            int sunroofPosition = this.bodyworkDevice.getSunroofPosition();
            RLog.i(str, "getSunroofState: state = " + sunroofPosition);
            if (sunroofPosition <= 0 || sunroofPosition >= 7) {
                int windowState = this.bodyworkDevice.getWindowState(5);
                RLog.i(str, "getSunroofState: state2 = " + windowState);
                if (windowState == 0) {
                    getSunroofStateResult.setOpenPercentage(0);
                } else if (windowState == 1) {
                    getSunroofStateResult.setOpenPercentage(100);
                } else {
                    int windowOpenPercent = this.bodyworkDevice.getWindowOpenPercent(5);
                    RLog.i(str, "getSunroofState: state3 = " + windowOpenPercent);
                    if (windowOpenPercent >= 0 && windowOpenPercent <= 100) {
                        getSunroofStateResult.setOpenPercentage(windowOpenPercent);
                    } else {
                        RLog.i(str, "getSunroofState: ERROR");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getSunroofState】,result【ERROR】");
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                        return;
                    }
                }
            } else if (sunroofPosition == 1) {
                getSunroofStateResult.setOpenPercentage(100);
            } else if (sunroofPosition == 2) {
                getSunroofStateResult.setOpenPercentage(0);
            } else if (sunroofPosition == 3) {
                getSunroofStateResult.setOpenPercentage(50);
            } else if (sunroofPosition == 5) {
                getSunroofStateResult.setOpenPercentage(5);
            } else {
                getSunroofStateResult.setOpenPercentage(60);
            }
            RLog.i(str, "getSunroofState: OK  result = " + getSunroofStateResult.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getSunroofState】,result【OK】");
            iResponseSender.send(new Response("OK", getSunroofStateResult));
        } catch (Exception unused) {
            RLog.i(TAG, "getSunroofState: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getSunroofState】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void controlSunroof(cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlSunroofParam r18, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r19) {
        /*
            Method dump skipped, instruction units count: 419
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleBasicHelper.controlSunroof(cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlSunroofParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0140  */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v33, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v36 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getWindowState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetWindowStateParam r18, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetWindowStateResult> r19) {
        /*
            Method dump skipped, instruction units count: 592
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleBasicHelper.getWindowState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetWindowStateParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01e2 A[Catch: Exception -> 0x0206, TryCatch #0 {Exception -> 0x0206, blocks: (B:5:0x0093, B:7:0x0099, B:23:0x00bd, B:24:0x00e2, B:54:0x0141, B:107:0x01fc, B:56:0x0146, B:101:0x01e2, B:103:0x01e8, B:105:0x01f2, B:106:0x01f8, B:62:0x0156, B:63:0x0163, B:67:0x016e, B:68:0x0175, B:72:0x0180, B:73:0x0187, B:77:0x0192, B:78:0x0199, B:82:0x01a4, B:83:0x01aa, B:86:0x01b3, B:87:0x01b7, B:90:0x01c0, B:91:0x01c4, B:94:0x01ce, B:95:0x01d2, B:98:0x01dd, B:26:0x00e6, B:29:0x00f1, B:32:0x00fa, B:35:0x0104, B:38:0x010e, B:41:0x0118, B:44:0x0122, B:47:0x012c, B:50:0x0136, B:109:0x0200), top: B:115:0x0093 }] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01e8 A[Catch: Exception -> 0x0206, TryCatch #0 {Exception -> 0x0206, blocks: (B:5:0x0093, B:7:0x0099, B:23:0x00bd, B:24:0x00e2, B:54:0x0141, B:107:0x01fc, B:56:0x0146, B:101:0x01e2, B:103:0x01e8, B:105:0x01f2, B:106:0x01f8, B:62:0x0156, B:63:0x0163, B:67:0x016e, B:68:0x0175, B:72:0x0180, B:73:0x0187, B:77:0x0192, B:78:0x0199, B:82:0x01a4, B:83:0x01aa, B:86:0x01b3, B:87:0x01b7, B:90:0x01c0, B:91:0x01c4, B:94:0x01ce, B:95:0x01d2, B:98:0x01dd, B:26:0x00e6, B:29:0x00f1, B:32:0x00fa, B:35:0x0104, B:38:0x010e, B:41:0x0118, B:44:0x0122, B:47:0x012c, B:50:0x0136, B:109:0x0200), top: B:115:0x0093 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0140  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setWindowState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetWindowStateParam r20, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r21) {
        /*
            Method dump skipped, instruction units count: 604
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleBasicHelper.setWindowState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetWindowStateParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    private boolean windowOrChildLockIsOpen(String str) {
        String str2 = TAG;
        RLog.i(str2, "windowOrChildLockIsOpen() called with: position = [" + str + "]");
        if (!Arrays.asList(CAR_LOCK_POSITION).contains(str)) {
            return false;
        }
        boolean zWindowLockConfig = BydSdkHelper.getInstance().windowLockConfig();
        RLog.i(str2, "windowOrChildLockIsOpen() windowLockConfig = " + zWindowLockConfig);
        if (zWindowLockConfig) {
            boolean windowLockState = BydSdkHelper.getInstance().getWindowLockState();
            RLog.i(str2, "windowOrChildLockIsOpen() windowState = " + windowLockState);
            return windowLockState;
        }
        boolean zChildLockConfig = BydSdkHelper.getInstance().childLockConfig();
        RLog.i(str2, "windowOrChildLockIsOpen() childLockConfig = " + zChildLockConfig);
        if (!zChildLockConfig) {
            return false;
        }
        str.hashCode();
        switch (str) {
            case "SEAT_ROW_REAR":
            case "SEAT_ALL":
                return BydSdkHelper.getInstance().getChildLockState(1) || BydSdkHelper.getInstance().getChildLockState(2);
            case "SEAT_ROW_2_LEFT":
            case "SEAT_ALL_LEFT":
                return BydSdkHelper.getInstance().getChildLockState(1);
            case "SEAT_ROW_2_RIGHT":
            case "SEAT_ALL_RIGHT":
                return BydSdkHelper.getInstance().getChildLockState(2);
            default:
                return false;
        }
    }

    public void getBlindsState(IResponseSender<GetBlindsStateResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "getBlindsState: ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getBlindsState】");
            GetBlindsStateResult getBlindsStateResult = new GetBlindsStateResult();
            int sunroofWindowblindPosition = this.bodyworkDevice.getSunroofWindowblindPosition();
            RLog.i(str, "getBlindsState: blinds = " + sunroofWindowblindPosition);
            if (sunroofWindowblindPosition == 0) {
                RLog.i(str, "getBlindsState: ERROR");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getBlindsState】,result【ERROR】");
                getBlindsStateResult.setOpenPercentage(0);
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                return;
            }
            if (sunroofWindowblindPosition == 1) {
                getBlindsStateResult.setOpenPercentage(100);
            } else if (sunroofWindowblindPosition == 2) {
                getBlindsStateResult.setOpenPercentage(0);
            } else if (sunroofWindowblindPosition == 3) {
                getBlindsStateResult.setOpenPercentage(50);
            } else {
                getBlindsStateResult.setOpenPercentage(60);
            }
            RLog.i(str, "getBlindsState: OK");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getBlindsState】,result【OK】");
            iResponseSender.send(new Response("OK", getBlindsStateResult));
        } catch (Exception unused) {
            RLog.i(TAG, "getBlindsState: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getBlindsState】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setBlindsState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetBlindsStateParam r18, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r19) {
        /*
            Method dump skipped, instruction units count: 421
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleBasicHelper.setBlindsState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetBlindsStateParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    public void controlEngineSound(ControlEngineSoundParam controlEngineSoundParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "controlEngineSound: json = " + controlEngineSoundParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlEngineSound】");
            boolean zEquals = "ON".equals(controlEngineSoundParam.getAction());
            if (!zEquals && !engineAnalogSoundConfig()) {
                RLog.i(str, "controlEngineSound: UNSUPPORTED");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlEngineSound】,result【UNSUPPORTED】");
                iResponseSender.send(new Response("UNSUPPORTED"));
                return;
            }
            int vSPState = this.audioDevice.getVSPState();
            int i = 1;
            if ((vSPState == 1 && zEquals) || (vSPState == 0 && !zEquals)) {
                RLog.i(str, "controlEngineSound: ALREADY_EXPECTED");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlEngineSound】,result【ALREADY_EXPECTED】");
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                return;
            }
            if (!zEquals) {
                i = 0;
            }
            int vSPState2 = this.audioDevice.setVSPState(i);
            RLog.i(str, "controlEngineSound: setOk = " + vSPState2);
            if (vSPState2 == 0) {
                iResponseSender.send(new Response("OK"));
                RLog.i(str, "controlEngineSound: OK");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlEngineSound】,result【OK】");
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(str, "controlEngineSound: ERROR");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlEngineSound】,result【ERROR】");
            }
        } catch (Exception unused) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(TAG, "controlEngineSound: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlEngineSound】,result【ERROR】");
        }
    }

    public boolean engineAnalogSoundConfig() {
        String str = TAG;
        RLog.i(str, "engineAnalogSoundConfig() called");
        boolean zContains = contains(ENGINE_ANALOG_SOUND_SUPPORT, Integer.parseInt(CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext())));
        RLog.i(str, "engineAnalogSoundConfig: contains = " + zContains);
        return zContains;
    }

    public boolean checkFragrance() {
        int supportFragrance = BYDAutoStudy.getInstance(BydVoiceApplication.getContext()).getSupportFragrance();
        RLog.i(TAG, "checkVehicleFunctionAvailability: perfumeConfig = " + supportFragrance);
        return supportFragrance == 1;
    }

    public static boolean contains(int[] iArr, final int i) {
        String str = TAG;
        RLog.d(str, "contains() called with: arr = [" + Arrays.toString(iArr) + "], target = [" + i + "]");
        boolean zAnyMatch = Arrays.stream(iArr).anyMatch(new IntPredicate() { // from class: com.byd.vrassistant.conversation.VehicleBasicHelper$$ExternalSyntheticLambda0
            @Override // java.util.function.IntPredicate
            public final boolean test(int i2) {
                return VehicleBasicHelper.lambda$contains$0(i, i2);
            }
        });
        RLog.i(str, "contains: anyMatch = " + zAnyMatch);
        return zAnyMatch;
    }

    public void getTowingState(IResponseSender<GetTowingStateResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "getTowingState: json = ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getTowingState】");
            int ePBState = this.vehicleHealthDevice.getEPBState();
            Log.i(str, "getTowingState: epbState = " + ePBState);
            GetTowingStateResult getTowingStateResult = new GetTowingStateResult();
            if (ePBState == 1) {
                getTowingStateResult.setState(VCTowingStatusEnum.TOWING);
            } else {
                getTowingStateResult.setState(VCTowingStatusEnum.NOT_TOWING);
            }
            RLog.i(str, "getTowingState: OK result = " + getTowingStateResult.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getTowingState】,result【OK】");
            iResponseSender.send(new Response("OK", getTowingStateResult));
        } catch (Exception unused) {
            RLog.i(TAG, "getTowingState: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getTowingState】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void getTrunkState(IResponseSender<GetTrunkStateResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "getTrunkState: json = ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getTrunkState】");
            int backDoorCurState = this.bodyworkDevice.getBackDoorCurState();
            RLog.i(str, "getTrunkState: state = " + backDoorCurState);
            GetTrunkStateResult getTrunkStateResult = new GetTrunkStateResult();
            TrunkStateInfo trunkStateInfo = new TrunkStateInfo();
            getTrunkStateResult.setTrunkStates(trunkStateInfo);
            if (backDoorCurState == 1 || backDoorCurState == 3) {
                trunkStateInfo.setOpenPercentage(100);
            } else {
                trunkStateInfo.setOpenPercentage(0);
            }
            RLog.i(str, "getTrunkState: OK  result = " + getTrunkStateResult.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getTrunkState】,result【OK】");
            iResponseSender.send(new Response("OK", getTrunkStateResult));
        } catch (Exception unused) {
            RLog.i(TAG, "getTrunkState: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getTrunkState】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void setTrunkState(SetTrunkStateParam setTrunkStateParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "setTrunkState: json = " + setTrunkStateParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setTrunkState】");
            int iVoiceCtlBackDoor = this.settingDevice.voiceCtlBackDoor(setTrunkStateParam.getTrunkState().getOpenPercentage() == 0 ? 3 : 1);
            RLog.i(str, "setTrunkState: OK intValue = " + iVoiceCtlBackDoor);
            if (iVoiceCtlBackDoor != 0) {
                RLog.i(str, "setTrunkState: ERROR");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setTrunkState】,result【ERROR】");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            } else {
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setTrunkState】,result【OK】");
                iResponseSender.send(new Response("OK"));
            }
        } catch (Exception unused) {
            RLog.i(TAG, "setTrunkState: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setTrunkState】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void isParkingGearOn(IResponseSender<IsParkingGearOnResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "isParkingGearOn: json = ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【isParkingGearOn】");
            IsParkingGearOnResult isParkingGearOnResult = new IsParkingGearOnResult();
            isP();
            if (isP()) {
                isParkingGearOnResult.setIsParkinggearOn(YNUEnum.YES);
            } else {
                isParkingGearOnResult.setIsParkinggearOn(YNUEnum.NO);
            }
            RLog.i(str, "isParkingGearOn: OK  result = " + isParkingGearOnResult.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【isParkingGearOn】,result【OK】");
            iResponseSender.send(new Response("OK", isParkingGearOnResult));
        } catch (Exception unused) {
            RLog.i(TAG, "isParkingGearOn: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【isParkingGearOn】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    private boolean isP() {
        int gearboxAutoModeLevel;
        int i = SystemPropertiesUtils.getInt("persist.sys.energytype", 0);
        if (i == 0) {
            gearboxAutoModeLevel = this.gearboxDevice.getGearboxAutoModeType();
        } else if (i == 1) {
            gearboxAutoModeLevel = this.gearboxDevice.getGear();
        } else {
            gearboxAutoModeLevel = this.gearboxDevice.getGearboxAutoModeLevel();
        }
        return gearboxAutoModeLevel == 1;
    }

    public void getWindowLockState(GetWindowLockStateParam getWindowLockStateParam, IResponseSender<GetWindowLockStateResult> iResponseSender) {
        try {
            String str = TAG;
            TtsUtils.requestLog(str, VehicleControlRequestsEnum.GET_WINDOW_LOCK_STATE, getWindowLockStateParam);
            boolean windowLockState = BydSdkHelper.getInstance().getWindowLockState();
            Log.i(str, "getWindowLockState: windowLockState = " + windowLockState);
            GetWindowLockStateResult getWindowLockStateResult = new GetWindowLockStateResult();
            ArrayList arrayList = new ArrayList();
            getWindowLockStateResult.setWindowlockStates(arrayList);
            WindowLockStateInfo windowLockStateInfo = new WindowLockStateInfo();
            windowLockStateInfo.setVehicleArea(getWindowLockStateParam.getVehicleArea());
            windowLockStateInfo.setState(windowLockState ? "ON" : "OFF");
            arrayList.add(windowLockStateInfo);
            TtsUtils.tts(str, VehicleControlRequestsEnum.GET_WINDOW_LOCK_STATE, iResponseSender, "OK", getWindowLockStateResult);
        } catch (Exception unused) {
            TtsUtils.tts(TAG, VehicleControlRequestsEnum.GET_WINDOW_LOCK_STATE, iResponseSender, ResultCodeEnum.ERROR);
        }
    }

    public void controlWindowLock(ControlWindowLockParam controlWindowLockParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            TtsUtils.requestLog(str, VehicleControlRequestsEnum.CONTROL_WINDOW_LOCK, controlWindowLockParam);
            boolean zEquals = "ON".equals(controlWindowLockParam.getAction());
            RLog.i(str, "controlWindowLock: on = " + zEquals);
            if (BydSdkHelper.getInstance().getWindowLockState() == zEquals) {
                TtsUtils.tts(str, VehicleControlRequestsEnum.CONTROL_WINDOW_LOCK, iResponseSender, "ALREADY_EXPECTED");
            } else {
                boolean windowLockState = BydSdkHelper.getInstance().setWindowLockState(zEquals);
                RLog.i(str, "controlWindowLock: setOk = " + windowLockState);
                if (windowLockState) {
                    TtsUtils.tts(str, VehicleControlRequestsEnum.CONTROL_WINDOW_LOCK, iResponseSender, "OK");
                } else {
                    TtsUtils.tts(str, VehicleControlRequestsEnum.CONTROL_WINDOW_LOCK, iResponseSender, ResultCodeEnum.ERROR);
                }
            }
        } catch (Exception unused) {
            TtsUtils.tts(TAG, VehicleControlRequestsEnum.CONTROL_WINDOW_LOCK, iResponseSender, ResultCodeEnum.ERROR);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getChildLockState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetChildLockStateParam r17, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetChildLockStateResult> r18) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleBasicHelper.getChildLockState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetChildLockStateParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    public void controlChildLock(ControlChildLockParam controlChildLockParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            TtsUtils.requestLog(str, VehicleControlRequestsEnum.CONTROL_CHILD_LOCK, controlChildLockParam);
            boolean zEquals = "ON".equals(controlChildLockParam.getState().getState());
            int childLockAllState = BydSdkHelper.getInstance().getChildLockAllState();
            if ((childLockAllState == 1 && zEquals) || (childLockAllState == 2 && !zEquals)) {
                TtsUtils.tts(str, VehicleControlRequestsEnum.CONTROL_CHILD_LOCK, iResponseSender, "ALREADY_EXPECTED");
            } else {
                boolean childLockAllState2 = BydSdkHelper.getInstance().setChildLockAllState(zEquals);
                RLog.i(str, "controlChildLock: setOk = " + childLockAllState2);
                if (childLockAllState2) {
                    TtsUtils.tts(str, VehicleControlRequestsEnum.CONTROL_CHILD_LOCK, iResponseSender, "OK");
                } else {
                    TtsUtils.tts(str, VehicleControlRequestsEnum.CONTROL_CHILD_LOCK, iResponseSender, ResultCodeEnum.ERROR);
                }
            }
        } catch (Exception unused) {
            TtsUtils.tts(TAG, VehicleControlRequestsEnum.CONTROL_CHILD_LOCK, iResponseSender, ResultCodeEnum.ERROR);
        }
    }

    public void checkCapability(CheckCapabilityParam checkCapabilityParam, IResponseSender<BaseResult> iResponseSender) {
        String str = TAG;
        RLog.i(str, "checkCapability() called with: checkCapabilityParam = [" + checkCapabilityParam + "], iResponseSender = [" + iResponseSender + "]");
        try {
            TtsUtils.requestLog(str, VehicleControlRequestsEnum.CHECK_CAPABILITY, checkCapabilityParam);
            int value = checkCapabilityParam.getValue();
            String str2 = "OK";
            if (!checkCapabilityParam.getVehicleFunction().equals(VehicleFunctionEnum.BLIND) ? !(value == 0 || value == 5 || value == 50 || value == 100) : !(value == 0 || value == 50 || value == 100)) {
                str2 = ResultCodeEnum.ERROR;
            }
            TtsUtils.tts(str, VehicleControlRequestsEnum.CHECK_CAPABILITY, iResponseSender, str2);
        } catch (Exception e) {
            String str3 = TAG;
            RLog.e(str3, "checkCapability: ", e);
            TtsUtils.tts(str3, VehicleControlRequestsEnum.CHECK_CAPABILITY, iResponseSender, ResultCodeEnum.ERROR);
        }
    }
}
