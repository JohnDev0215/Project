package com.byd.vrassistant.conversation;

import android.app.UiStyleConfig;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.pm2p5.BYDAutoPM2p5Device;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.util.Log;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.hvac.HVACRequestsEnum;
import cerence.ark.assistant.controller.domain.hvac.result.GetHvacStateResult;
import cerence.ark.assistant.controller.domain.hvac.result.HvacCheckisHeatPumpResult;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import cerence.ark.assistant.controller.struct.HVACStateInfo;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;
import com.byd.vrassistant.utils.TtsUtils;
import com.byd.vrassistant.utils.VehicleAndHvacUtil;
import java.util.List;
import java.util.function.Predicate;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class HvacGetStateHelper {
    private static final String TAG = TagUtil.getTagName(HvacGetStateHelper.class);
    private BYDAutoAcDevice acDevice;
    private BYDAutoStudy autoStudy;
    private BYDAutoPM2p5Device pm2p5Device;
    private BYDAutoSettingDevice settingDevice;
    private UiStyleConfig uiStyleConfig;

    private String changeHeatingState(double d) {
        return (d == 33.0d || d == 91.0d) ? "ON" : (d > 91.0d || d < 17.0d) ? "UNSUPPORTED" : "OFF";
    }

    private static final class SingletonHolder {
        private static final HvacGetStateHelper INSTANCE = new HvacGetStateHelper();

        private SingletonHolder() {
        }
    }

    public static HvacGetStateHelper getInstance() {
        RLog.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private HvacGetStateHelper() {
        try {
            RLog.i(TAG, "HvacGetStateHelper()");
            this.acDevice = BYDAutoAcDevice.getInstance(BydVoiceApplication.getContext());
            this.settingDevice = BYDAutoSettingDevice.getInstance(BydVoiceApplication.getContext());
            this.uiStyleConfig = UiStyleConfig.getInstance(BydVoiceApplication.getContext());
            this.autoStudy = BYDAutoStudy.getInstance(BydVoiceApplication.getContext());
            this.pm2p5Device = BYDAutoPM2p5Device.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            RLog.i(TAG, "HvacGetStateHelper Exception ", e);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:131:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x06f5  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0205 A[Catch: Exception -> 0x0a71, TryCatch #7 {Exception -> 0x0a71, blocks: (B:3:0x003a, B:4:0x0083, B:70:0x01a3, B:71:0x01ad, B:73:0x01bb, B:81:0x01fb, B:83:0x0205, B:85:0x020c, B:84:0x0209, B:76:0x01d7, B:78:0x01f6, B:86:0x0230, B:88:0x0256, B:90:0x025d, B:89:0x025a, B:91:0x0281, B:93:0x0297, B:95:0x029e, B:94:0x029b, B:96:0x02c2, B:98:0x02ea, B:100:0x02f1, B:99:0x02ee, B:101:0x0315, B:102:0x0356, B:132:0x03be, B:153:0x0498, B:134:0x03c3, B:136:0x03d3, B:138:0x03da, B:140:0x03e8, B:142:0x03ef, B:141:0x03ec, B:137:0x03d7, B:143:0x0417, B:145:0x042b, B:147:0x0432, B:146:0x042f, B:148:0x045a, B:150:0x046e, B:152:0x0475, B:151:0x0472, B:104:0x035b, B:107:0x0364, B:110:0x036f, B:113:0x037a, B:116:0x0385, B:119:0x0390, B:122:0x039b, B:125:0x03a6, B:128:0x03b1, B:154:0x04ae, B:155:0x04b7, B:6:0x0088, B:9:0x0093, B:12:0x009f, B:15:0x00ab, B:18:0x00b6, B:21:0x00c2, B:24:0x00ce, B:27:0x00da, B:30:0x00e6, B:33:0x00f2, B:36:0x00fe, B:39:0x010a, B:42:0x0114, B:45:0x011f, B:48:0x0129, B:51:0x0134, B:54:0x013f, B:57:0x014a, B:60:0x0155, B:63:0x0160), top: B:371:0x003a }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0209 A[Catch: Exception -> 0x0a71, TryCatch #7 {Exception -> 0x0a71, blocks: (B:3:0x003a, B:4:0x0083, B:70:0x01a3, B:71:0x01ad, B:73:0x01bb, B:81:0x01fb, B:83:0x0205, B:85:0x020c, B:84:0x0209, B:76:0x01d7, B:78:0x01f6, B:86:0x0230, B:88:0x0256, B:90:0x025d, B:89:0x025a, B:91:0x0281, B:93:0x0297, B:95:0x029e, B:94:0x029b, B:96:0x02c2, B:98:0x02ea, B:100:0x02f1, B:99:0x02ee, B:101:0x0315, B:102:0x0356, B:132:0x03be, B:153:0x0498, B:134:0x03c3, B:136:0x03d3, B:138:0x03da, B:140:0x03e8, B:142:0x03ef, B:141:0x03ec, B:137:0x03d7, B:143:0x0417, B:145:0x042b, B:147:0x0432, B:146:0x042f, B:148:0x045a, B:150:0x046e, B:152:0x0475, B:151:0x0472, B:104:0x035b, B:107:0x0364, B:110:0x036f, B:113:0x037a, B:116:0x0385, B:119:0x0390, B:122:0x039b, B:125:0x03a6, B:128:0x03b1, B:154:0x04ae, B:155:0x04b7, B:6:0x0088, B:9:0x0093, B:12:0x009f, B:15:0x00ab, B:18:0x00b6, B:21:0x00c2, B:24:0x00ce, B:27:0x00da, B:30:0x00e6, B:33:0x00f2, B:36:0x00fe, B:39:0x010a, B:42:0x0114, B:45:0x011f, B:48:0x0129, B:51:0x0134, B:54:0x013f, B:57:0x014a, B:60:0x0155, B:63:0x0160), top: B:371:0x003a }] */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.byd.vrassistant.conversation.HvacGetStateHelper] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v32 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v2, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r11v26 */
    /* JADX WARN: Type inference failed for: r11v29 */
    /* JADX WARN: Type inference failed for: r11v31 */
    /* JADX WARN: Type inference failed for: r11v32 */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getHvacState(cerence.ark.assistant.controller.domain.hvac.param.GetHvacStateParam r46, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.hvac.result.GetHvacStateResult> r47) {
        /*
            Method dump skipped, instruction units count: 3010
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacGetStateHelper.getHvacState(cerence.ark.assistant.controller.domain.hvac.param.GetHvacStateParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    public void hvacCheckisHeatPump(IResponseSender<HvacCheckisHeatPumpResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "hvacCheckisHeatPump: json = ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【hvacCheckisHeatPump】");
            int acWarmTypeOnlineState = this.acDevice.getAcWarmTypeOnlineState();
            RLog.i(str, "hvacCheckisHeatPump: warmType = " + acWarmTypeOnlineState);
            HvacCheckisHeatPumpResult hvacCheckisHeatPumpResult = new HvacCheckisHeatPumpResult();
            hvacCheckisHeatPumpResult.setIsPump(acWarmTypeOnlineState == 6);
            RLog.i(str, "hvacCheckisHeatPump: OK  result = " + hvacCheckisHeatPumpResult.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【hvacCheckisHeatPump】,result【OK】");
            iResponseSender.send(new Response("OK", hvacCheckisHeatPumpResult));
        } catch (Exception unused) {
            RLog.i(TAG, "hvacCheckisHeatPump: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【hvacCheckisHeatPump】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void getAcCompressorModeNot(IResponseSender<GetHvacStateResult> iResponseSender, String str, GetHvacStateResult getHvacStateResult, List<HVACStateInfo> list) throws JSONException {
        String strChangeRearAcArea;
        byte b;
        double temperature = BydSdkHelper.getInstance().getTemperature(BydVoiceApplication.getContext(), 1);
        double temperature2 = BydSdkHelper.getInstance().getTemperature(BydVoiceApplication.getContext(), 2);
        double temperature3 = BydSdkHelper.getInstance().getTemperature(BydVoiceApplication.getContext(), 3);
        String str2 = TAG;
        RLog.i(str2, "getHvacState: acMainTemperature = " + temperature);
        RLog.i(str2, "getHvacState: acDeputyTemperature = " + temperature2);
        RLog.i(str2, "getHvacState: acRearTemperature = " + temperature3);
        HVACStateInfo hVACStateInfo = new HVACStateInfo();
        if (str == null || "".equals(str) || VehicleAreaEnum.SEAT_UNKNOWN.equals(str)) {
            strChangeRearAcArea = HvacHelper.getInstance().changeRearAcArea(VehicleAndHvacUtil.getArea(str));
        } else {
            strChangeRearAcArea = VehicleAndHvacUtil.getArea(str);
        }
        strChangeRearAcArea.hashCode();
        byte b2 = -1;
        switch (strChangeRearAcArea.hashCode()) {
            case -1779453437:
                if (strChangeRearAcArea.equals(VehicleAreaEnum.SEAT_ROW_REAR)) {
                    b = 0;
                    b2 = b;
                }
                break;
            case -1673964625:
                if (strChangeRearAcArea.equals(VehicleAreaEnum.SEAT_ROW_1_RIGHT)) {
                    b2 = 1;
                }
                break;
            case -1578202124:
                if (strChangeRearAcArea.equals(VehicleAreaEnum.SEAT_ROW_1_LEFT)) {
                    b2 = 2;
                }
                break;
            case -1549572973:
                if (strChangeRearAcArea.equals(VehicleAreaEnum.SEAT_ROW_2_LEFT)) {
                    b2 = 3;
                }
                break;
            case -786460944:
                if (strChangeRearAcArea.equals(VehicleAreaEnum.SEAT_ROW_2_RIGHT)) {
                    b = 4;
                    b2 = b;
                }
                break;
            case -772471804:
                if (strChangeRearAcArea.equals(VehicleAreaEnum.SEAT_ALL_RIGHT)) {
                    b = 5;
                    b2 = b;
                }
                break;
            case -25101057:
                if (strChangeRearAcArea.equals(VehicleAreaEnum.SEAT_ALL_LEFT)) {
                    b = 6;
                    b2 = b;
                }
                break;
            case 660836746:
                if (strChangeRearAcArea.equals(VehicleAreaEnum.SEAT_ROW_FRONT)) {
                    b = 7;
                    b2 = b;
                }
                break;
            case 1673947207:
                if (strChangeRearAcArea.equals(VehicleAreaEnum.SEAT_ALL)) {
                    b = 8;
                    b2 = b;
                }
                break;
        }
        switch (b2) {
            case 0:
            case 3:
            case 4:
                Log.i(str2, "getHvacState: SEAT_ROW_REAR");
                HVACStateInfo hVACStateInfo2 = new HVACStateInfo();
                hVACStateInfo2.setVehicleArea(VehicleAreaEnum.SEAT_ROW_REAR);
                hVACStateInfo2.setState(changeHeatingState(temperature3));
                list.add(hVACStateInfo2);
                break;
            case 1:
                Log.i(str2, "getHvacState: SEAT_ROW_1_RIGHT");
                hVACStateInfo.setVehicleArea(VehicleAreaEnum.SEAT_ROW_1_RIGHT);
                if (VehicleAndHvacUtil.isLeftRudder()) {
                    hVACStateInfo.setState(changeHeatingState(temperature2));
                } else {
                    hVACStateInfo.setState(changeHeatingState(temperature));
                }
                list.add(hVACStateInfo);
                break;
            case 2:
                Log.i(str2, "getHvacState: SEAT_ROW_1_LEFT");
                hVACStateInfo.setVehicleArea(VehicleAreaEnum.SEAT_ROW_1_LEFT);
                if (VehicleAndHvacUtil.isLeftRudder()) {
                    hVACStateInfo.setState(changeHeatingState(temperature));
                } else {
                    hVACStateInfo.setState(changeHeatingState(temperature2));
                }
                list.add(hVACStateInfo);
                break;
            case 5:
                Log.i(str2, "getHvacState: SEAT_ALL_RIGHT");
                HVACStateInfo hVACStateInfo3 = new HVACStateInfo();
                hVACStateInfo3.setVehicleArea(VehicleAreaEnum.SEAT_ROW_1_RIGHT);
                if (VehicleAndHvacUtil.isLeftRudder()) {
                    hVACStateInfo3.setState(changeHeatingState(temperature2));
                } else {
                    hVACStateInfo3.setState(changeHeatingState(temperature));
                }
                list.add(hVACStateInfo3);
                HVACStateInfo hVACStateInfo4 = new HVACStateInfo();
                hVACStateInfo4.setVehicleArea(VehicleAreaEnum.SEAT_ROW_REAR);
                hVACStateInfo4.setState(changeHeatingState(temperature3));
                list.add(hVACStateInfo4);
                break;
            case 6:
                Log.i(str2, "getHvacState: SEAT_ALL_LEFT");
                HVACStateInfo hVACStateInfo5 = new HVACStateInfo();
                hVACStateInfo5.setVehicleArea(VehicleAreaEnum.SEAT_ROW_1_LEFT);
                if (VehicleAndHvacUtil.isLeftRudder()) {
                    hVACStateInfo5.setState(changeHeatingState(temperature));
                } else {
                    hVACStateInfo5.setState(changeHeatingState(temperature2));
                }
                list.add(hVACStateInfo5);
                HVACStateInfo hVACStateInfo6 = new HVACStateInfo();
                hVACStateInfo6.setVehicleArea(VehicleAreaEnum.SEAT_ROW_REAR);
                hVACStateInfo6.setState(changeHeatingState(temperature3));
                list.add(hVACStateInfo6);
                break;
            case 7:
                Log.i(str2, "getHvacState: SEAT_ROW_FRONT");
                HVACStateInfo hVACStateInfo7 = new HVACStateInfo();
                hVACStateInfo7.setVehicleArea(VehicleAreaEnum.SEAT_ROW_1_LEFT);
                HVACStateInfo hVACStateInfo8 = new HVACStateInfo();
                hVACStateInfo8.setVehicleArea(VehicleAreaEnum.SEAT_ROW_1_RIGHT);
                if (VehicleAndHvacUtil.isLeftRudder()) {
                    hVACStateInfo7.setState(changeHeatingState(temperature));
                    hVACStateInfo8.setState(changeHeatingState(temperature2));
                } else {
                    hVACStateInfo7.setState(changeHeatingState(temperature2));
                    hVACStateInfo8.setState(changeHeatingState(temperature));
                }
                list.add(hVACStateInfo7);
                list.add(hVACStateInfo8);
                break;
            case 8:
                Log.i(str2, "getHvacState: SEAT_ALL");
                HVACStateInfo hVACStateInfo9 = new HVACStateInfo();
                hVACStateInfo9.setVehicleArea(VehicleAreaEnum.SEAT_ROW_1_LEFT);
                HVACStateInfo hVACStateInfo10 = new HVACStateInfo();
                hVACStateInfo10.setVehicleArea(VehicleAreaEnum.SEAT_ROW_1_RIGHT);
                if (VehicleAndHvacUtil.isLeftRudder()) {
                    hVACStateInfo9.setState(changeHeatingState(temperature));
                    hVACStateInfo10.setState(changeHeatingState(temperature2));
                } else {
                    hVACStateInfo9.setState(changeHeatingState(temperature2));
                    hVACStateInfo10.setState(changeHeatingState(temperature));
                }
                list.add(hVACStateInfo9);
                list.add(hVACStateInfo10);
                HVACStateInfo hVACStateInfo11 = new HVACStateInfo();
                hVACStateInfo11.setVehicleArea(VehicleAreaEnum.SEAT_ROW_REAR);
                hVACStateInfo11.setState(changeHeatingState(temperature3));
                list.add(hVACStateInfo11);
                break;
            default:
                Log.i(str2, "getHvacState: UNSUPPORTED");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【getHvacState】,result【UNSUPPORTED】");
                iResponseSender.send(new Response("UNSUPPORTED"));
                return;
        }
        list.removeIf(new Predicate() { // from class: com.byd.vrassistant.conversation.HvacGetStateHelper$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return "UNSUPPORTED".equals(((HVACStateInfo) obj).getState());
            }
        });
        Log.i(str2, "getHvacState: OK  getHvacStateResult = " + getHvacStateResult.toJsonString());
        Log.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【getHvacState】,result【OK】");
        iResponseSender.send(new Response("OK", getHvacStateResult));
    }

    private void getAcCompressorMode(IResponseSender<GetHvacStateResult> iResponseSender, String str, GetHvacStateResult getHvacStateResult, List<HVACStateInfo> list) throws JSONException {
        String strChangeRearAcArea;
        int acWarmTypeOnlineState = this.acDevice.getAcWarmTypeOnlineState();
        String str2 = TAG;
        RLog.i(str2, "getAcCompressorMode: warmType = " + acWarmTypeOnlineState);
        if (str == null || "".equals(str) || VehicleAreaEnum.SEAT_UNKNOWN.equals(str)) {
            strChangeRearAcArea = HvacHelper.getInstance().changeRearAcArea(VehicleAndHvacUtil.getArea(str));
        } else {
            strChangeRearAcArea = VehicleAndHvacUtil.getArea(str);
        }
        if (acWarmTypeOnlineState == 6) {
            HVACStateInfo hVACStateInfo = new HVACStateInfo();
            hVACStateInfo.setVehicleArea(strChangeRearAcArea);
            list.add(hVACStateInfo);
            int heatPumpCompressorWorkMode = this.acDevice.getHeatPumpCompressorWorkMode();
            RLog.i(str2, "getAcCompressorMode: acCompressorMode = " + heatPumpCompressorWorkMode);
            if (heatPumpCompressorWorkMode == 2 || heatPumpCompressorWorkMode == 3) {
                hVACStateInfo.setState("ON");
            } else {
                hVACStateInfo.setState("OFF");
            }
            RLog.i(str2, "getHvacState: OK  getHvacStateResult = " + getHvacStateResult.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【getHvacState】,result【OK】");
            iResponseSender.send(new Response("OK", getHvacStateResult));
            return;
        }
        HVACStateInfo hVACStateInfo2 = new HVACStateInfo();
        hVACStateInfo2.setVehicleArea(strChangeRearAcArea);
        list.add(hVACStateInfo2);
        int acCompressorMode = this.acDevice.getAcCompressorMode();
        RLog.i(str2, "getAcCompressorMode: acCompressorMode = " + acCompressorMode);
        if (acCompressorMode == 1) {
            hVACStateInfo2.setState("ON");
        } else {
            hVACStateInfo2.setState("OFF");
        }
        RLog.i(str2, "getHvacState: OK  getHvacStateResult = " + getHvacStateResult.toJsonString());
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【getHvacState】,result【OK】");
        iResponseSender.send(new Response("OK", getHvacStateResult));
    }

    private void getAcVentilationState(IResponseSender<GetHvacStateResult> iResponseSender, String str, GetHvacStateResult getHvacStateResult, List<HVACStateInfo> list) throws JSONException {
        HVACStateInfo hVACStateInfo = new HVACStateInfo();
        hVACStateInfo.setVehicleArea(str);
        list.add(hVACStateInfo);
        int acVentilationState = this.acDevice.getAcVentilationState();
        String str2 = TAG;
        RLog.i(str2, "getHvacState: acVentilationState = " + acVentilationState);
        if (acVentilationState == 1) {
            hVACStateInfo.setState("ON");
        } else {
            hVACStateInfo.setState("OFF");
        }
        RLog.i(str2, "getHvacState: OK  getHvacStateResult = " + getHvacStateResult.toJsonString());
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【getHvacState】,result【OK】");
        iResponseSender.send(new Response("OK", getHvacStateResult));
    }

    private void getRearAcStartState(IResponseSender<GetHvacStateResult> iResponseSender, String str, GetHvacStateResult getHvacStateResult, List<HVACStateInfo> list) throws JSONException {
        HVACStateInfo hVACStateInfo = new HVACStateInfo();
        hVACStateInfo.setVehicleArea(str);
        list.add(hVACStateInfo);
        int rearAcStartState = this.acDevice.getRearAcStartState();
        String str2 = TAG;
        RLog.i(str2, "getRearAcStartState: acOnlineState = " + rearAcStartState);
        if (rearAcStartState == 1) {
            hVACStateInfo.setState("ON");
        } else {
            hVACStateInfo.setState("OFF");
        }
        RLog.i(str2, "getHvacState: OK  getHvacStateResult = " + getHvacStateResult.toJsonString());
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【getHvacState】,result【OK】");
        iResponseSender.send(new Response("OK", getHvacStateResult));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:118:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x029a A[Catch: Exception -> 0x02f3, TryCatch #2 {Exception -> 0x02f3, blocks: (B:109:0x0257, B:128:0x02de, B:123:0x0274, B:125:0x029a, B:127:0x02c3, B:126:0x02af), top: B:212:0x0257 }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02af A[Catch: Exception -> 0x02f3, TryCatch #2 {Exception -> 0x02f3, blocks: (B:109:0x0257, B:128:0x02de, B:123:0x0274, B:125:0x029a, B:127:0x02c3, B:126:0x02af), top: B:212:0x0257 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0137  */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v2, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getSeatHeatState(cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.hvac.result.GetHvacStateResult> r20, java.lang.String r21, cerence.ark.assistant.controller.domain.hvac.result.GetHvacStateResult r22, java.util.List<cerence.ark.assistant.controller.struct.HVACStateInfo> r23) {
        /*
            Method dump skipped, instruction units count: 1328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacGetStateHelper.getSeatHeatState(cerence.ark.assistant.api.IResponseSender, java.lang.String, cerence.ark.assistant.controller.domain.hvac.result.GetHvacStateResult, java.util.List):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:118:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x029a A[Catch: Exception -> 0x02f3, TryCatch #2 {Exception -> 0x02f3, blocks: (B:109:0x0257, B:128:0x02de, B:123:0x0274, B:125:0x029a, B:127:0x02c3, B:126:0x02af), top: B:212:0x0257 }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02af A[Catch: Exception -> 0x02f3, TryCatch #2 {Exception -> 0x02f3, blocks: (B:109:0x0257, B:128:0x02de, B:123:0x0274, B:125:0x029a, B:127:0x02c3, B:126:0x02af), top: B:212:0x0257 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0137  */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v2, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getSeatVentilatingState(cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.hvac.result.GetHvacStateResult> r20, java.lang.String r21, cerence.ark.assistant.controller.domain.hvac.result.GetHvacStateResult r22, java.util.List<cerence.ark.assistant.controller.struct.HVACStateInfo> r23) {
        /*
            Method dump skipped, instruction units count: 1328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacGetStateHelper.getSeatVentilatingState(cerence.ark.assistant.api.IResponseSender, java.lang.String, cerence.ark.assistant.controller.domain.hvac.result.GetHvacStateResult, java.util.List):void");
    }

    private String changeSeatHeat(int i) {
        if (i == 2 || i == 3) {
            RLog.i(TAG, "changeSeatHeat: SwitchEnum.ON");
            return "ON";
        }
        if (i == 1) {
            RLog.i(TAG, "changeSeatHeat: SwitchEnum.OFF");
            return "OFF";
        }
        RLog.i(TAG, "changeSeatHeat: SwitchEnum.UNSUPPORTED");
        return "UNSUPPORTED";
    }

    private String changeSeatVentilating(int i) {
        String str = TAG;
        RLog.i(str, "changeSeatVentilating: acSeatHeatState = " + i);
        if (i == 2 || i == 3) {
            RLog.i(str, "changeSeatVentilating: SwitchEnum.ON");
            return "ON";
        }
        if (i == 1) {
            RLog.i(str, "changeSeatHeat: SwitchEnum.OFF");
            return "OFF";
        }
        RLog.i(str, "changeSeatHeat: SwitchEnum.UNSUPPORTED");
        return "UNSUPPORTED";
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x014b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getAcPowerState(cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.hvac.result.GetHvacStateResult> r19, cerence.ark.assistant.controller.domain.hvac.result.GetHvacStateResult r20, java.lang.String r21, java.util.List<cerence.ark.assistant.controller.struct.HVACStateInfo> r22) {
        /*
            Method dump skipped, instruction units count: 536
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacGetStateHelper.getAcPowerState(cerence.ark.assistant.api.IResponseSender, cerence.ark.assistant.controller.domain.hvac.result.GetHvacStateResult, java.lang.String, java.util.List):void");
    }

    private void getAcTemperatureControlMode(IResponseSender<GetHvacStateResult> iResponseSender, GetHvacStateResult getHvacStateResult, String str, List<HVACStateInfo> list) throws JSONException {
        HVACStateInfo hVACStateInfo = new HVACStateInfo();
        hVACStateInfo.setVehicleArea(str);
        list.add(hVACStateInfo);
        int acTemperatureControlMode = this.acDevice.getAcTemperatureControlMode();
        String str2 = TAG;
        RLog.i(str2, "getHvacState: acTemperatureControlMode = " + acTemperatureControlMode);
        if (acTemperatureControlMode == 0) {
            hVACStateInfo.setState("ON");
        } else {
            hVACStateInfo.setState("OFF");
        }
        RLog.i(str2, "getHvacState: OK  getHvacStateResult = " + getHvacStateResult.toJsonString());
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【getHvacState】,result【OK,】");
        iResponseSender.send(new Response("OK", getHvacStateResult));
    }

    private boolean isSingleTemperatureControl() {
        int acTemperatureControlMode = this.acDevice.getAcTemperatureControlMode();
        RLog.i(TAG, "isSync: acSync = " + acTemperatureControlMode);
        return (acTemperatureControlMode == 0 || acTemperatureControlMode == 1) ? false : true;
    }

    private void getAcHeatingKey(IResponseSender<GetHvacStateResult> iResponseSender, String str, GetHvacStateResult getHvacStateResult, List<HVACStateInfo> list) {
        boolean acHeatingKey = BydSdkHelper.getInstance().getAcHeatingKey(BydVoiceApplication.getContext());
        String str2 = TAG;
        Log.i(str2, "getAcHeatingKey: acHeatingKey = " + acHeatingKey);
        HVACStateInfo hVACStateInfo = new HVACStateInfo();
        if (acHeatingKey) {
            hVACStateInfo.setState("ON");
        } else {
            hVACStateInfo.setState("OFF");
        }
        hVACStateInfo.setVehicleArea(str);
        list.add(hVACStateInfo);
        TtsUtils.tts(str2, HVACRequestsEnum.GET_HVAC_STATE, iResponseSender, "OK", getHvacStateResult);
    }
}
