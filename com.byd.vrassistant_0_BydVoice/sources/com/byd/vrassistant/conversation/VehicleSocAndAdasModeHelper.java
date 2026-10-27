package com.byd.vrassistant.conversation;

import android.hardware.bydauto.adas.BYDAutoADASDevice;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.util.Log;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlAdasParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetSocStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetSocStateResult;
import cerence.ark.assistant.controller.enumeration.AdasTypeEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.utils.TagUtil;

/* JADX INFO: loaded from: classes.dex */
public class VehicleSocAndAdasModeHelper {
    private static final String TAG = TagUtil.getTagName(VehicleSocAndAdasModeHelper.class);
    private BYDAutoADASDevice adasDevice;
    private BYDAutoDiPilotDevice diPilotDevice;
    private BYDAutoSettingDevice settingDevice;

    private static final class SingletonHolder {
        private static final VehicleSocAndAdasModeHelper INSTANCE = new VehicleSocAndAdasModeHelper();

        private SingletonHolder() {
        }
    }

    public static VehicleSocAndAdasModeHelper getInstance() {
        Log.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private VehicleSocAndAdasModeHelper() {
        try {
            Log.i(TAG, "VehicleSocAndAdasModeHelper()");
            this.settingDevice = BYDAutoSettingDevice.getInstance(BydVoiceApplication.getContext());
            this.adasDevice = BYDAutoADASDevice.getInstance(BydVoiceApplication.getContext());
            this.diPilotDevice = BYDAutoDiPilotDevice.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            Log.i(TAG, "VehicleSocAndAdasModeHelper() Exception ", e);
        }
    }

    public void getSocState(IResponseSender<GetSocStateResult> iResponseSender) {
        try {
            String str = TAG;
            Log.i(str, "getSocState: json = ");
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getSocState】");
            int sOCTarget = this.settingDevice.getSOCTarget();
            Log.i(str, "getSocState:  socCurrent = " + sOCTarget);
            GetSocStateResult getSocStateResult = new GetSocStateResult();
            getSocStateResult.setCurrentSoc(sOCTarget);
            if (Constant.SET_SOC_DM_VERSION == 2) {
                getSocStateResult.setMaxSoc(70);
                getSocStateResult.setMinSoc(25);
            } else {
                getSocStateResult.setMaxSoc(70);
                getSocStateResult.setMinSoc(15);
            }
            Log.i(str, "getSocState: OK  result = " + getSocStateResult.toJsonString());
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getSocState】,result【OK】");
            iResponseSender.send(new Response("OK", getSocStateResult));
        } catch (Exception e) {
            Log.i(TAG, "getSocState: Exception ", e);
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getSocState】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void setSocState(SetSocStateParam setSocStateParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            Log.i(str, "setSocState: json = " + setSocStateParam.toJsonString());
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setSocState】");
            int socValue = setSocStateParam.getSocValue();
            Log.i(str, "setSocState: socValue = " + socValue);
            int sOCTarget = this.settingDevice.setSOCTarget(socValue);
            Log.i(str, "setSocState: intValue = " + sOCTarget);
            if (sOCTarget != 0) {
                Log.i(str, "setSocState: ERROR");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setSocState】,result【ERROR】");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            } else {
                Log.i(str, "setSocState: OK");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setSocState】,result【OK】");
                iResponseSender.send(new Response("OK"));
            }
        } catch (Exception e) {
            Log.i(TAG, "setSocState: Exception ", e);
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setSocState】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getAdasStatus(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetAdasStatusParam r13, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetAdasStatusResult> r14) {
        /*
            Method dump skipped, instruction units count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleSocAndAdasModeHelper.getAdasStatus(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetAdasStatusParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v16, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v7, types: [boolean] */
    public void controlAdas(ControlAdasParam controlAdasParam, IResponseSender<BaseResult> iResponseSender) {
        String str;
        String str2;
        String adasType;
        String action;
        String str3;
        Object obj;
        String str4;
        boolean z;
        int laneAssistType;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        String strEquals = "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【ERROR】";
        try {
            str2 = TAG;
            Log.i(str2, "controlAdas: json = " + controlAdasParam.toJsonString());
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】");
            adasType = controlAdasParam.getAdasType();
            action = controlAdasParam.getAction();
        } catch (Exception e) {
            e = e;
            str = ResultCodeEnum.ERROR;
        }
        if (adasType.equals(AdasTypeEnum.ICC)) {
            int aINavigationState = this.diPilotDevice.getAINavigationState();
            Log.i(str2, "controlAdas iccState = " + aINavigationState);
            if (action.equals("OFF")) {
                if (aINavigationState == 0) {
                    Log.i(str2, "controlAdas ALREADY_EXPECTED");
                    Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【ALREADY_EXPECTED】");
                    iResponseSender.send(new Response("ALREADY_EXPECTED"));
                    return;
                }
                int aINavigationState2 = this.diPilotDevice.setAINavigationState(1);
                Log.i(str2, "controlAdas intValue = " + aINavigationState2);
                if (aINavigationState2 != 0) {
                    Log.i(str2, "controlAdas ERROR");
                    Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【ERROR】");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    return;
                } else {
                    Log.i(str2, "controlAdas OK");
                    Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【OK】");
                    iResponseSender.send(new Response("OK"));
                    return;
                }
            }
            if (aINavigationState != 1 && aINavigationState != 2 && aINavigationState != 3) {
                int aINavigationState3 = this.diPilotDevice.setAINavigationState(2);
                Log.i(str2, "controlAdas intValue = " + aINavigationState3);
                if (aINavigationState3 != 0) {
                    Log.i(str2, "controlAdas ERROR");
                    Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【ERROR】");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    return;
                } else {
                    Log.i(str2, "controlAdas OK");
                    Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【OK】");
                    iResponseSender.send(new Response("OK"));
                    return;
                }
            }
            Log.i(str2, "controlAdas ALREADY_EXPECTED");
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【ALREADY_EXPECTED】");
            iResponseSender.send(new Response("ALREADY_EXPECTED"));
            return;
        }
        String str5 = adasType.equals(AdasTypeEnum.LDP_AND_LDW) ? AdasTypeEnum.LKA_AND_LDW : adasType;
        if (str5.equals(AdasTypeEnum.LKA) || str5.equals(AdasTypeEnum.LDP)) {
            String str6 = VehicleBasicHelper.getInstance().adasType;
            str3 = ResultCodeEnum.ERROR;
            obj = AdasTypeEnum.LDP;
            str4 = str6;
            z = true;
        } else {
            str3 = ResultCodeEnum.ERROR;
            obj = AdasTypeEnum.LDP;
            str4 = str5;
            z = false;
        }
        try {
            laneAssistType = this.diPilotDevice.getLaneAssistType();
            try {
                Log.i(str2, "controlAdas modeState: = " + laneAssistType);
                strEquals = action.equals("OFF");
                str = "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【UNSUPPORTED】";
                try {
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
                str = str3;
                strEquals = "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【ERROR】";
            }
        } catch (Exception e4) {
            e = e4;
            str = str3;
        }
        if (strEquals != 0) {
            Log.i(str2, "controlAdas: OFF");
            if (str4.equals("LDW") && laneAssistType != 1) {
                i3 = 3;
                if (laneAssistType == 3) {
                }
                Log.i(str2, "controlAdas ALREADY_EXPECTED");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【ALREADY_EXPECTED】");
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                return;
            }
            i3 = 3;
            if ((!z || laneAssistType == 2 || laneAssistType == i3) && (!str4.equals(AdasTypeEnum.LKA_AND_LDW) || laneAssistType != 0)) {
                if (str4.equals("LDW")) {
                    i4 = laneAssistType == 3 ? this.adasDevice.get(new int[]{3212}, Integer.TYPE).intValue == 1 ? 3 : 5 : laneAssistType == 1 ? 1 : 0;
                } else if (z) {
                    if (laneAssistType == 2) {
                        i6 = 3;
                        i5 = 1;
                    } else {
                        i5 = 0;
                        i6 = 3;
                    }
                    i4 = laneAssistType == i6 ? 2 : i5;
                } else {
                    if (!str4.equals(AdasTypeEnum.LKA_AND_LDW)) {
                        Log.i(str2, "controlAdas UNSUPPORTED");
                        Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【UNSUPPORTED】");
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        return;
                    }
                    i4 = 1;
                }
                int laneAssistType2 = this.diPilotDevice.setLaneAssistType(i4);
                Log.i(str2, "controlAdas intValue = " + laneAssistType2);
                if (laneAssistType2 != 0) {
                    Log.i(str2, "controlAdas ERROR");
                    strEquals = "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【ERROR】";
                    Log.i(Constant.TAG_FORTEST, strEquals);
                    iResponseSender.send(new Response(str3));
                    return;
                }
                Log.i(TAG, "controlAdas: Exception ", e);
                Log.i(Constant.TAG_FORTEST, strEquals);
                iResponseSender.send(new Response(str));
                return;
            }
            Log.i(str2, "controlAdas ALREADY_EXPECTED");
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【ALREADY_EXPECTED】");
            iResponseSender.send(new Response("ALREADY_EXPECTED"));
            return;
        }
        String str7 = str3;
        Log.i(str2, "action: ON");
        if (str4.equals("LDW")) {
            if (laneAssistType != 1) {
                i = 3;
                if (laneAssistType != 3) {
                }
            }
            Log.i(str2, "controlAdas ALREADY_EXPECTED");
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【ALREADY_EXPECTED】");
            iResponseSender.send(new Response("ALREADY_EXPECTED"));
            return;
        }
        i = 3;
        if (z) {
            i2 = 2;
            if (laneAssistType != 2 && laneAssistType != i) {
            }
            Log.i(str2, "controlAdas ALREADY_EXPECTED");
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【ALREADY_EXPECTED】");
            iResponseSender.send(new Response("ALREADY_EXPECTED"));
            return;
        }
        i2 = 2;
        if (!str4.equals(AdasTypeEnum.LKA_AND_LDW) || laneAssistType != i) {
            if (str4.equals("LDW")) {
                i = i2;
            } else if (!str4.equals(AdasTypeEnum.LKA)) {
                if (str4.equals(AdasTypeEnum.LKA_AND_LDW)) {
                    i = 4;
                } else {
                    if (!str4.equals(obj)) {
                        Log.i(str2, "controlAdas UNSUPPORTED");
                        Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【UNSUPPORTED】");
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        return;
                    }
                    i = 5;
                }
            }
            int laneAssistType3 = this.diPilotDevice.setLaneAssistType(i);
            Log.i(str2, "setSocState intValue = " + laneAssistType3);
            if (laneAssistType3 != 0) {
                Log.i(str2, "setSocState ERROR");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【ERROR】");
                iResponseSender.send(new Response(str7));
                return;
            }
        }
        Log.i(str2, "controlAdas ALREADY_EXPECTED");
        Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【ALREADY_EXPECTED】");
        iResponseSender.send(new Response("ALREADY_EXPECTED"));
        return;
        Log.i(str2, "setSocState OK");
        Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlAdas】,result【OK】");
        iResponseSender.send(new Response("OK"));
    }
}
