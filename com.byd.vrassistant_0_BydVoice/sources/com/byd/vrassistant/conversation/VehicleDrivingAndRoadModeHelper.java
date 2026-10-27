package com.byd.vrassistant.conversation;

import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.util.Log;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetSupportedDrivingModeResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetSupportedRoadModeResult;
import cerence.ark.assistant.controller.enumeration.DrivingModeEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.enumeration.RoadModeEnum;
import cerence.ark.assistant.controller.struct.DrivingModeInfo;
import cerence.ark.assistant.controller.struct.RoadModeInfo;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.utils.TagUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class VehicleDrivingAndRoadModeHelper {
    private static final String TAG = TagUtil.getTagName(VehicleDrivingAndRoadModeHelper.class);
    private BYDAutoEnergyDevice energyDevice;

    private static final class SingletonHolder {
        private static final VehicleDrivingAndRoadModeHelper INSTANCE = new VehicleDrivingAndRoadModeHelper();

        private SingletonHolder() {
        }
    }

    public static VehicleDrivingAndRoadModeHelper getInstance() {
        Log.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private VehicleDrivingAndRoadModeHelper() {
        try {
            Log.i(TAG, "VehicleDrivingAndRoadModeHelper()");
            this.energyDevice = BYDAutoEnergyDevice.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            Log.i(TAG, "VehicleDrivingAndRoadModeHelper() Exception ", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void controlDrivingMode(cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlDrivingModeParam r17, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r18) {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleDrivingAndRoadModeHelper.controlDrivingMode(cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlDrivingModeParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void controlRoadMode(cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlRoadModeParam r14, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r15) {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleDrivingAndRoadModeHelper.controlRoadMode(cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlRoadModeParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    public void getSupportedDrivingMode(IResponseSender<GetSupportedDrivingModeResult> iResponseSender) {
        try {
            String str = TAG;
            Log.d(str, "getSupportedDrivingMode json = ");
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getSupportedDrivingMode】");
            GetSupportedDrivingModeResult getSupportedDrivingModeResult = new GetSupportedDrivingModeResult();
            ArrayList arrayList = new ArrayList();
            getSupportedDrivingModeResult.setModeList(arrayList);
            DrivingModeInfo drivingModeInfo = new DrivingModeInfo();
            drivingModeInfo.setMode("NORMAL");
            drivingModeInfo.setLabel("NORMAL");
            arrayList.add(drivingModeInfo);
            DrivingModeInfo drivingModeInfo2 = new DrivingModeInfo();
            drivingModeInfo2.setMode(DrivingModeEnum.ECO);
            drivingModeInfo2.setLabel(DrivingModeEnum.ECO);
            arrayList.add(drivingModeInfo2);
            DrivingModeInfo drivingModeInfo3 = new DrivingModeInfo();
            drivingModeInfo3.setMode(DrivingModeEnum.SPORT);
            drivingModeInfo3.setLabel(DrivingModeEnum.SPORT);
            arrayList.add(drivingModeInfo3);
            Log.d(str, "getSupportedDrivingMode OK  result = " + getSupportedDrivingModeResult.toJsonString());
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getSupportedDrivingMode】,result【OK】");
            iResponseSender.send(new Response("OK", getSupportedDrivingModeResult));
        } catch (Exception e) {
            Log.i(TAG, "getSupportedDrivingMode: Exception ", e);
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getSupportedDrivingMode】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void getSupportedRoadMode(IResponseSender<GetSupportedRoadModeResult> iResponseSender) {
        try {
            String str = TAG;
            Log.d(str, "getSupportedRoadMode json = ");
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getSupportedRoadMode】");
            GetSupportedRoadModeResult getSupportedRoadModeResult = new GetSupportedRoadModeResult();
            ArrayList arrayList = new ArrayList();
            getSupportedRoadModeResult.setModeList(arrayList);
            RoadModeInfo roadModeInfo = new RoadModeInfo();
            roadModeInfo.setMode(RoadModeEnum.SNOW);
            roadModeInfo.setLabel(RoadModeEnum.SNOW);
            arrayList.add(roadModeInfo);
            Log.i(str, "getSupportedRoadMode: OK  result = " + getSupportedRoadModeResult.toJsonString());
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getSupportedRoadMode】,result【OK】");
            iResponseSender.send(new Response("OK", getSupportedRoadModeResult));
        } catch (Exception e) {
            Log.i(TAG, "getSupportedRoadMode: Exception ", e);
            Log.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getSupportedRoadMode】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }
}
