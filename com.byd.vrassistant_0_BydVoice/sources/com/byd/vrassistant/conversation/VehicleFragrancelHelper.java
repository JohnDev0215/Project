package com.byd.vrassistant.conversation;

import android.hardware.bydauto.fragrance.BYDAutoFragranceDevice;
import android.util.Log;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetFragranceStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckPerfumeEmptyResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetFragranceLevelResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetFragranceStateResult;
import cerence.ark.assistant.controller.enumeration.FragranceLevelEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.enumeration.YNUEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.utils.TagUtil;

/* JADX INFO: loaded from: classes.dex */
public class VehicleFragrancelHelper {
    private static final String TAG = TagUtil.getTagName(VehicleFragrancelHelper.class);
    private BYDAutoFragranceDevice fragranceDevice;

    private boolean checkFragrance() {
        return true;
    }

    private static final class SingletonHolder {
        private static final VehicleFragrancelHelper INSTANCE = new VehicleFragrancelHelper();

        private SingletonHolder() {
        }
    }

    public static VehicleFragrancelHelper getInstance() {
        Log.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private VehicleFragrancelHelper() {
        try {
            Log.i(TAG, "VehicleFragrancelHelper()");
            this.fragranceDevice = BYDAutoFragranceDevice.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            Log.i(TAG, "VehicleFragrancelHelper() Exception ", e);
        }
    }

    public void checkPerfumeEmpty(IResponseSender<CheckPerfumeEmptyResult> iResponseSender) {
        try {
            String str = TAG;
            Log.i(str, "checkPerfumeEmpty: json = ");
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【checkPerfumeEmpty】");
            CheckPerfumeEmptyResult checkPerfumeEmptyResult = new CheckPerfumeEmptyResult();
            if (checkFragrance()) {
                checkPerfumeEmptyResult.setIsPerfumeEmpty(YNUEnum.YES);
            } else {
                checkPerfumeEmptyResult.setIsPerfumeEmpty(YNUEnum.NO);
            }
            Log.i(str, "checkPerfumeEmpty: OK  result = " + checkPerfumeEmptyResult.toJsonString());
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【checkPerfumeEmpty】,result【OK】");
            iResponseSender.send(new Response("OK", checkPerfumeEmptyResult));
        } catch (Exception e) {
            Log.i(TAG, "checkPerfumeEmpty: Exception = ", e);
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【checkPerfumeEmpty】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void getFragranceState(IResponseSender<GetFragranceStateResult> iResponseSender) {
        try {
            String str = TAG;
            Log.i(str, "getFragranceState: json = ");
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getFragranceState】");
            int fragranceConcentration = this.fragranceDevice.getFragranceConcentration(1);
            Log.i(str, "getFragranceState: perfumeState = " + fragranceConcentration);
            GetFragranceStateResult getFragranceStateResult = new GetFragranceStateResult();
            if (fragranceConcentration == 1 || fragranceConcentration == 2 || fragranceConcentration == 3) {
                getFragranceStateResult.setState("ON");
            } else if (fragranceConcentration == 4) {
                getFragranceStateResult.setState("OFF");
            } else {
                iResponseSender.send(new Response("UNSUPPORTED"));
                Log.i(str, "getFragranceState: UNSUPPORTED");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getFragranceState】,result【UNSUPPORTED】");
                return;
            }
            Log.i(str, "getFragranceState: OK  result = " + getFragranceStateResult.toJsonString());
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getFragranceState】,result【OK】");
            iResponseSender.send(new Response("OK", getFragranceStateResult));
        } catch (Exception e) {
            Log.i(TAG, "getFragranceState: Exception = ", e);
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getFragranceState】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void setFragranceState(SetFragranceStateParam setFragranceStateParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            Log.i(str, "setFragranceState: json = " + setFragranceStateParam.toJsonString());
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setFragranceState】");
            int fragranceConcentration = this.fragranceDevice.setFragranceConcentration(setFragranceStateParam.getState().equals("ON") ? 2 : 4);
            Log.i(str, "setFragranceState: intValue = " + fragranceConcentration);
            if (fragranceConcentration != 0) {
                Log.i(str, "setFragranceState: ERROR");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setFragranceState】,result【ERROR】");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            } else {
                Log.i(str, "setFragranceState: OK");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setFragranceState】,result【OK】");
                iResponseSender.send(new Response("OK"));
            }
        } catch (Exception e) {
            Log.i(TAG, "setFragranceState: Exception = ", e);
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setFragranceState】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void getFragranceLevel(IResponseSender<GetFragranceLevelResult> iResponseSender) {
        try {
            String str = TAG;
            Log.i(str, "getFragranceLevel: json = ");
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getFragranceLevel】");
            int fragranceConcentration = this.fragranceDevice.getFragranceConcentration(1);
            Log.i(str, "getFragranceLevel: perfumeLevel = " + fragranceConcentration);
            GetFragranceLevelResult getFragranceLevelResult = new GetFragranceLevelResult();
            if (fragranceConcentration == 1) {
                getFragranceLevelResult.setFragranceLevel(FragranceLevelEnum.FRAGRANCE_LIGHT);
            } else if (fragranceConcentration == 2) {
                getFragranceLevelResult.setFragranceLevel(FragranceLevelEnum.FRAGRANCE_MEDIUM);
            } else if (fragranceConcentration == 3) {
                getFragranceLevelResult.setFragranceLevel(FragranceLevelEnum.FRAGRANCE_STRONG);
            } else {
                getFragranceLevelResult.setFragranceLevel(FragranceLevelEnum.FRAGRANCE_UNDEFINED);
            }
            Log.i(str, "getFragranceLevel: OK  result = " + getFragranceLevelResult.toJsonString());
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getFragranceLevel】,result【OK】");
            iResponseSender.send(new Response("OK", getFragranceLevelResult));
        } catch (Exception e) {
            Log.i(TAG, "getFragranceLevel: Exception = ", e);
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getFragranceLevel】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setFragranceLevel(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetFragranceLevelParam r10, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r11) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleFragrancelHelper.setFragranceLevel(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetFragranceLevelParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    public void increaseFragranceLevel(IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            Log.i(str, "increaseFragranceLevel: json = ");
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【increaseFragranceLevel】");
            int i = 1;
            int fragranceConcentration = this.fragranceDevice.getFragranceConcentration(1);
            Log.i(str, "getFragranceLevel: perfumeLevel = " + fragranceConcentration);
            if (fragranceConcentration == 3) {
                Log.i(str, "increaseFragranceLevel: ALREADY_MAX");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【increaseFragranceLevel】,result【ALREADY_MAX】");
                iResponseSender.send(new Response(ResultCodeEnum.ALREADY_MAX));
                return;
            }
            if (fragranceConcentration == 1 || fragranceConcentration == 2) {
                i = 1 + fragranceConcentration;
            } else if (fragranceConcentration != 4) {
                Log.i(str, "increaseFragranceLevel: UNSUPPORTED");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【increaseFragranceLevel】,result【UNSUPPORTED】");
                iResponseSender.send(new Response("UNSUPPORTED"));
                return;
            }
            int fragranceConcentration2 = this.fragranceDevice.setFragranceConcentration(i);
            Log.i(str, "increaseFragranceLevel: intValue = " + fragranceConcentration2);
            if (fragranceConcentration2 != 0) {
                Log.i(str, "increaseFragranceLevel: ERROR");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【increaseFragranceLevel】,result【ERROR】");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            } else {
                Log.i(str, "increaseFragranceLevel: OK");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【increaseFragranceLevel】,result【OK】");
                iResponseSender.send(new Response("OK"));
            }
        } catch (Exception e) {
            Log.i(TAG, "increaseFragranceLevel: Exception ", e);
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【increaseFragranceLevel】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void decreaseFragranceLevel(IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            Log.i(str, "decreaseFragranceLevel: json = ");
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【decreaseFragranceLevel】");
            int fragranceConcentration = this.fragranceDevice.getFragranceConcentration(1);
            Log.i(str, "getFragranceLevel: perfumeLevel = " + fragranceConcentration);
            if (fragranceConcentration == 1) {
                Log.i(str, "decreaseFragranceLevel: ALREADY_MIN");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【decreaseFragranceLevel】,result【ALREADY_MIN】");
                iResponseSender.send(new Response(ResultCodeEnum.ALREADY_MIN));
                return;
            }
            if (fragranceConcentration != 2 && fragranceConcentration != 3) {
                if (fragranceConcentration == 4) {
                    Log.i(str, "decreaseFragranceLevel: ALREADY_EXPECTED");
                    Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【decreaseFragranceLevel】,result【ALREADY_EXPECTED】");
                    iResponseSender.send(new Response("ALREADY_EXPECTED"));
                    return;
                } else {
                    Log.i(str, "decreaseFragranceLevel: UNSUPPORTED");
                    Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【decreaseFragranceLevel】,result【UNSUPPORTED】");
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    return;
                }
            }
            int fragranceConcentration2 = this.fragranceDevice.setFragranceConcentration(fragranceConcentration - 1);
            Log.i(str, "decreaseFragranceLevel: intValue = " + fragranceConcentration2);
            if (fragranceConcentration2 != 0) {
                Log.i(str, "decreaseFragranceLevel: ERROR");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【decreaseFragranceLevel】,result【ERROR】");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            } else {
                Log.i(str, "decreaseFragranceLevel: OK");
                Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【decreaseFragranceLevel】,result【OK】");
                iResponseSender.send(new Response("OK"));
            }
        } catch (Exception e) {
            Log.i(TAG, "decreaseFragranceLevel: Exception ", e);
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【decreaseFragranceLevel】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }
}
