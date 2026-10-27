package com.byd.vrassistant.conversation;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlNapModeParam;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import com.byd.profile.ModeCallback;
import com.byd.profile.ModeManager;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class VehicleModeHelper {
    private static final String TAG = "NEU_VehicleModeHelper";

    private VehicleModeHelper() {
    }

    private static final class SingletonHolder {
        private static final VehicleModeHelper INSTANCE = new VehicleModeHelper();

        private SingletonHolder() {
        }
    }

    public static VehicleModeHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void controlNapMode(ControlNapModeParam controlNapModeParam, final IResponseSender<BaseResult> iResponseSender) {
        try {
            RLog.i(TAG, "controlNapMode: json = " + controlNapModeParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlNapMode】");
            boolean zEquals = "ON".equals(controlNapModeParam.getAction());
            RLog.i(TAG, "controlNapMode: action = " + zEquals);
            if (zEquals) {
                RLog.i(TAG, "controlNapMode: 开启小憩模式");
                ModeManager.getInstance(BydVoiceApplication.getContext()).startRestMode(new ModeCallback() { // from class: com.byd.vrassistant.conversation.VehicleModeHelper.1
                    public void onResult(int i, int i2, int i3) {
                        RLog.i(VehicleModeHelper.TAG, "onResult: mode = " + i + ", result = " + i2 + ", value = " + i3);
                        switch (i2) {
                            case 1:
                            case 9:
                                iResponseSender.send(new Response("OK"));
                                break;
                            case 2:
                                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                                break;
                            case 3:
                                iResponseSender.send(new Response("UNSUPPORTED"));
                                break;
                            case 4:
                            case 7:
                                iResponseSender.send(new Response(ResultCodeEnum.NOT_P_MODE));
                                break;
                            case 5:
                                iResponseSender.send(new Response(ResultCodeEnum.DOOR_OPEN));
                                break;
                            case 6:
                                iResponseSender.send(new Response(ResultCodeEnum.REAR_SEAT_NOT_EMPTY));
                                break;
                            case 8:
                                iResponseSender.send(new Response(ResultCodeEnum.CHARGING_ON));
                                break;
                            default:
                                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                                break;
                        }
                    }
                });
            } else {
                RLog.i(TAG, "controlNapMode: 关闭小憩模式");
                ModeManager.getInstance(BydVoiceApplication.getContext()).stopRestMode(new ModeCallback() { // from class: com.byd.vrassistant.conversation.VehicleModeHelper.2
                    public void onResult(int i, int i2, int i3) {
                        RLog.i(VehicleModeHelper.TAG, "onResult() called with: mode = [" + i + "], result = [" + i2 + "], value = [" + i3 + "]");
                        if (i2 == 1) {
                            iResponseSender.send(new Response("OK"));
                            return;
                        }
                        if (i2 == 2) {
                            iResponseSender.send(new Response("UNSUPPORTED"));
                        } else if (i2 == 3 || i2 == 4) {
                            iResponseSender.send(new Response("ALREADY_EXPECTED"));
                        } else {
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                        }
                    }
                });
            }
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        } catch (Exception e) {
            RLog.e(TAG, "controlNapMode: error = " + e.getMessage());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }
}
