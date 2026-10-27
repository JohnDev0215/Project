package com.byd.vrassistant.customizedimpl;

import android.util.Log;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.CheckCPAAconnectionParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.CheckCapabilityParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.CheckVehicleFunctionAvailabilityParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.CloseAppParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.CloseFSDeviceParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlAdasParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlBluetoothParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlCameraRecordParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlCameraSwitchParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlCameraTakePhotoParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlChildLockParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlDrivingModeParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlEngineSoundParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlHotspotParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlNapModeParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlRoadModeParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlSunroofParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlWifiParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlWindowLockParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlWingMirrorHeatingParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.DecreaseInteriorLightBrightnessParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.DecreaseScreenBrightnessParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetAdasStatusParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetChildLockStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetDeviceStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetFSDeviceStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetInteriorLightBrightnessParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetInteriorLightStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetInteriorLightZonesParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetScreenBrightnessParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetScreenDisplayModeParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetSunroofStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetWindowLockStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetWindowStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.IncreaseInteriorLightBrightnessParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.IncreaseScreenBrightnessParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.OpenAppParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.OpenFSDeviceParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetBlindsStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetFragranceLevelParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetFragranceStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetInteriorLightBrightnessParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetInteriorLightColorParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetInteriorLightStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetScreenBrightnessParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetScreenDisplayModeParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetScreenOnOffParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetScreenOrientationParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetSocStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetTrunkStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetWindowStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckCPAAInForegroundResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckCPAAconnectionResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckPerfumeEmptyResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckScreenStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckVehicleFunctionAvailabilityResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CloseAppResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetAdasStatusResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetBlindsStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetChildLockStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetFSDeviceStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetFragranceLevelResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetFragranceStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetInteriorLightBrightnessResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetInteriorLightStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetInteriorLightZonesResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetScreenBrightnessResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetScreenDisplayModeResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetSocStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetSunroofStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetSupportedDrivingModeResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetSupportedRoadModeResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetTowingStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetTrunkStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetVehicleInfoResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetWindowLockStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetWindowStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.IsParkingGearOnResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.IsRainingResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.QueryCurrentSpeedResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.QueryTirePressureResult;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import com.byd.vrassistant.conversation.AppHelper;
import com.byd.vrassistant.conversation.VehicleBasicHelper;
import com.byd.vrassistant.conversation.VehicleDrivingAndRoadModeHelper;
import com.byd.vrassistant.conversation.VehicleFragrancelHelper;
import com.byd.vrassistant.conversation.VehicleHelper;
import com.byd.vrassistant.conversation.VehicleLampHelper;
import com.byd.vrassistant.conversation.VehicleModeHelper;
import com.byd.vrassistant.conversation.VehicleScreenHelper;
import com.byd.vrassistant.conversation.VehicleSettingsHelper;
import com.byd.vrassistant.conversation.VehicleSocAndAdasModeHelper;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;

/* JADX INFO: loaded from: classes.dex */
class CustomVehicleControlDomain extends AbsVehicleControlDomain {
    private static final String TAG = TagUtil.getTagName(CustomVehicleControlDomain.class);

    protected CustomVehicleControlDomain(Dhi dhi) {
        super(dhi);
        RLog.i(TAG, "CustomVehicleControlDomain: ");
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void checkVehicleFunctionAvailability(CheckVehicleFunctionAvailabilityParam checkVehicleFunctionAvailabilityParam, IResponseSender<CheckVehicleFunctionAvailabilityResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().checkVehicleFunctionAvailability(checkVehicleFunctionAvailabilityParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "checkVehicleFunctionAvailability: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void isRaining(IResponseSender<IsRainingResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().isRaining(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "isRaining: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void queryCurrentSpeed(IResponseSender<QueryCurrentSpeedResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().queryCurrentSpeed(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "queryCurrentSpeed: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlWifi(ControlWifiParam controlWifiParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleSettingsHelper.getInstance().controlWifi(controlWifiParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlWifi: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlBluetooth(ControlBluetoothParam controlBluetoothParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleSettingsHelper.getInstance().controlBluetooth(controlBluetoothParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlBluetooth: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlHotspot(ControlHotspotParam controlHotspotParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleSettingsHelper.getInstance().controlHotspot(controlHotspotParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlHotspot: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlWingMirrorHeating(ControlWingMirrorHeatingParam controlWingMirrorHeatingParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().controlWingMirrorHeating(controlWingMirrorHeatingParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlWingMirrorHeating: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlWindowLock(ControlWindowLockParam controlWindowLockParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().controlWindowLock(controlWindowLockParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlWindowLock: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getChildLockState(GetChildLockStateParam getChildLockStateParam, IResponseSender<GetChildLockStateResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().getChildLockState(getChildLockStateParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getChildLockState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlChildLock(ControlChildLockParam controlChildLockParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().controlChildLock(controlChildLockParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlChildLock: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getWindowLockState(GetWindowLockStateParam getWindowLockStateParam, IResponseSender<GetWindowLockStateResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().getWindowLockState(getWindowLockStateParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getWindowLockState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void openApp(OpenAppParam openAppParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleSettingsHelper.getInstance().openApp(openAppParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "openApp: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void closeApp(CloseAppParam closeAppParam, IResponseSender<CloseAppResult> iResponseSender) {
        try {
            VehicleSettingsHelper.getInstance().closeApp(closeAppParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "closeApp: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getSunroofState(GetSunroofStateParam getSunroofStateParam, IResponseSender<GetSunroofStateResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().getSunroofState(getSunroofStateParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getSunroofState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlSunroof(ControlSunroofParam controlSunroofParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().controlSunroof(controlSunroofParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlSunroof: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getTrunkState(IResponseSender<GetTrunkStateResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().getTrunkState(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getTrunkState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void setTrunkState(SetTrunkStateParam setTrunkStateParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().setTrunkState(setTrunkStateParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "setTrunkState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void isParkingGearOn(IResponseSender<IsParkingGearOnResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().isParkingGearOn(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "isParkingGearOn: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getWindowState(GetWindowStateParam getWindowStateParam, IResponseSender<GetWindowStateResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().getWindowState(getWindowStateParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getWindowState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void setWindowState(SetWindowStateParam setWindowStateParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().setWindowState(setWindowStateParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "setWindowState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getBlindsState(IResponseSender<GetBlindsStateResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().getBlindsState(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getBlindsState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void setBlindsState(SetBlindsStateParam setBlindsStateParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().setBlindsState(setBlindsStateParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "setBlindsState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getInteriorLightState(GetInteriorLightStateParam getInteriorLightStateParam, IResponseSender<GetInteriorLightStateResult> iResponseSender) {
        try {
            VehicleLampHelper.getInstance().getInteriorLightState(getInteriorLightStateParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getInteriorLightState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void setInteriorLightState(SetInteriorLightStateParam setInteriorLightStateParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleLampHelper.getInstance().setInteriorLightState(setInteriorLightStateParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "setInteriorLightState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getInteriorLightBrightness(GetInteriorLightBrightnessParam getInteriorLightBrightnessParam, IResponseSender<GetInteriorLightBrightnessResult> iResponseSender) {
        try {
            VehicleLampHelper.getInstance().getInteriorLightBrightness(getInteriorLightBrightnessParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getInteriorLightBrightness: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void setInteriorLightBrightness(SetInteriorLightBrightnessParam setInteriorLightBrightnessParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleLampHelper.getInstance().setInteriorLightBrightness(setInteriorLightBrightnessParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "setInteriorLightBrightness: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void increaseInteriorLightBrightness(IncreaseInteriorLightBrightnessParam increaseInteriorLightBrightnessParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleLampHelper.getInstance().increaseInteriorLightBrightness(increaseInteriorLightBrightnessParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "increaseInteriorLightBrightness: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void decreaseInteriorLightBrightness(DecreaseInteriorLightBrightnessParam decreaseInteriorLightBrightnessParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleLampHelper.getInstance().decreaseInteriorLightBrightness(decreaseInteriorLightBrightnessParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "decreaseInteriorLightBrightness: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void setInteriorLightColor(SetInteriorLightColorParam setInteriorLightColorParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleLampHelper.getInstance().setInteriorLightColor(setInteriorLightColorParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "setInteriorLightColor: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getInteriorLightZones(GetInteriorLightZonesParam getInteriorLightZonesParam, IResponseSender<GetInteriorLightZonesResult> iResponseSender) {
        try {
            VehicleLampHelper.getInstance().getInteriorLightZones(getInteriorLightZonesParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getInteriorLightZones: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void setScreenBrightness(SetScreenBrightnessParam setScreenBrightnessParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleScreenHelper.getInstance().setScreenBrightness(setScreenBrightnessParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "setScreenBrightness: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void increaseScreenBrightness(IncreaseScreenBrightnessParam increaseScreenBrightnessParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleScreenHelper.getInstance().increaseScreenBrightness(increaseScreenBrightnessParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "increaseScreenBrightness: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void decreaseScreenBrightness(DecreaseScreenBrightnessParam decreaseScreenBrightnessParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleScreenHelper.getInstance().decreaseScreenBrightness(decreaseScreenBrightnessParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "decreaseScreenBrightness: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getScreenBrightness(GetScreenBrightnessParam getScreenBrightnessParam, IResponseSender<GetScreenBrightnessResult> iResponseSender) {
        try {
            VehicleScreenHelper.getInstance().getScreenBrightness(getScreenBrightnessParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getScreenBrightness: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void setScreenDisplayMode(SetScreenDisplayModeParam setScreenDisplayModeParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleScreenHelper.getInstance().setScreenDisplayMode(setScreenDisplayModeParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "setScreenDisplayMode: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getScreenDisplayMode(GetScreenDisplayModeParam getScreenDisplayModeParam, IResponseSender<GetScreenDisplayModeResult> iResponseSender) {
        try {
            VehicleScreenHelper.getInstance().getScreenDisplayMode(getScreenDisplayModeParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getScreenDisplayMode: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void rotateScreen(IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleScreenHelper.getInstance().rotateScreen(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "rotateScreen: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void setScreenOrientation(SetScreenOrientationParam setScreenOrientationParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleScreenHelper.getInstance().setScreenOrientation(setScreenOrientationParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "setScreenOrientation: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void setScreenOnOff(SetScreenOnOffParam setScreenOnOffParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleScreenHelper.getInstance().setScreenOnOff(setScreenOnOffParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "setScreenOnOff: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getVehicleInfo(IResponseSender<GetVehicleInfoResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().getVehicleInfo(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getVehicleInfo: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlEngineSound(ControlEngineSoundParam controlEngineSoundParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().controlEngineSound(controlEngineSoundParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlEngineSound: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void queryTirePressure(IResponseSender<QueryTirePressureResult> iResponseSender) {
        try {
            iResponseSender.send(new Response("UNSUPPORTED"));
        } catch (Error | Exception e) {
            Log.e(TAG, "queryTirePressure: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void checkPerfumeEmpty(IResponseSender<CheckPerfumeEmptyResult> iResponseSender) {
        try {
            VehicleFragrancelHelper.getInstance().checkPerfumeEmpty(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "checkPerfumeEmpty: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getFragranceState(IResponseSender<GetFragranceStateResult> iResponseSender) {
        try {
            VehicleFragrancelHelper.getInstance().getFragranceState(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getFragranceState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void setFragranceState(SetFragranceStateParam setFragranceStateParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleFragrancelHelper.getInstance().setFragranceState(setFragranceStateParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "setFragranceState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getFragranceLevel(IResponseSender<GetFragranceLevelResult> iResponseSender) {
        try {
            VehicleFragrancelHelper.getInstance().getFragranceLevel(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getFragranceLevel: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void setFragranceLevel(SetFragranceLevelParam setFragranceLevelParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleFragrancelHelper.getInstance().setFragranceLevel(setFragranceLevelParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "setFragranceLevel: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void increaseFragranceLevel(IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleFragrancelHelper.getInstance().increaseFragranceLevel(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "increaseFragranceLevel: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void decreaseFragranceLevel(IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleFragrancelHelper.getInstance().decreaseFragranceLevel(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "decreaseFragranceLevel: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getSocState(IResponseSender<GetSocStateResult> iResponseSender) {
        try {
            VehicleSocAndAdasModeHelper.getInstance().getSocState(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getSocState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void setSocState(SetSocStateParam setSocStateParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleSocAndAdasModeHelper.getInstance().setSocState(setSocStateParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "setSocState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getAdasStatus(GetAdasStatusParam getAdasStatusParam, IResponseSender<GetAdasStatusResult> iResponseSender) {
        try {
            VehicleSocAndAdasModeHelper.getInstance().getAdasStatus(getAdasStatusParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getAdasStatus: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlAdas(ControlAdasParam controlAdasParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleSocAndAdasModeHelper.getInstance().controlAdas(controlAdasParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlAdas: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlCameraTakePhoto(ControlCameraTakePhotoParam controlCameraTakePhotoParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleSettingsHelper.getInstance().controlCameraTakePhoto(controlCameraTakePhotoParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlCameraTakePhoto: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlCameraRecord(ControlCameraRecordParam controlCameraRecordParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleSettingsHelper.getInstance().controlCameraRecord(controlCameraRecordParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlCameraRecord: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlCameraSwitch(ControlCameraSwitchParam controlCameraSwitchParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleSettingsHelper.getInstance().controlCameraSwitch(controlCameraSwitchParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlCameraSwitch: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlDrivingMode(ControlDrivingModeParam controlDrivingModeParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleDrivingAndRoadModeHelper.getInstance().controlDrivingMode(controlDrivingModeParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlDrivingMode: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlRoadMode(ControlRoadModeParam controlRoadModeParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleDrivingAndRoadModeHelper.getInstance().controlRoadMode(controlRoadModeParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlRoadMode: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getSupportedDrivingMode(IResponseSender<GetSupportedDrivingModeResult> iResponseSender) {
        try {
            VehicleDrivingAndRoadModeHelper.getInstance().getSupportedDrivingMode(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getSupportedDrivingMode: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getSupportedRoadMode(IResponseSender<GetSupportedRoadModeResult> iResponseSender) {
        try {
            VehicleDrivingAndRoadModeHelper.getInstance().getSupportedRoadMode(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getSupportedRoadMode: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getFSDeviceState(GetFSDeviceStateParam getFSDeviceStateParam, IResponseSender<GetFSDeviceStateResult> iResponseSender) {
        try {
            VehicleSettingsHelper.getInstance().getFSDeviceState(getFSDeviceStateParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getFSDeviceState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void openFSDevice(OpenFSDeviceParam openFSDeviceParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleSettingsHelper.getInstance().openFSDevice(openFSDeviceParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "openFSDevice: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void closeFSDevice(CloseFSDeviceParam closeFSDeviceParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleSettingsHelper.getInstance().closeFSDevice(closeFSDeviceParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "closeFSDevice: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void checkCPAAconnection(CheckCPAAconnectionParam checkCPAAconnectionParam, IResponseSender<CheckCPAAconnectionResult> iResponseSender) {
        try {
            VehicleSettingsHelper.getInstance().checkCPAAConnection(checkCPAAconnectionParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "checkCPAAconnection: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getDeviceState(GetDeviceStateParam getDeviceStateParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleSettingsHelper.getInstance().getDeviceState(getDeviceStateParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getDeviceState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void getTowingState(IResponseSender<GetTowingStateResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().getTowingState(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "getTowingState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void checkCapability(CheckCapabilityParam checkCapabilityParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleBasicHelper.getInstance().checkCapability(checkCapabilityParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "checkCapability: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void checkScreenState(IResponseSender<CheckScreenStateResult> iResponseSender) {
        try {
            VehicleScreenHelper.getInstance().checkScreenState(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "checkScreenState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void checkCPAAInForeground(IResponseSender<CheckCPAAInForegroundResult> iResponseSender) {
        try {
            VehicleScreenHelper.getInstance().checkCPAAInForeground(iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "checkCPAAInForeground: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain
    protected void controlNapMode(ControlNapModeParam controlNapModeParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            VehicleModeHelper.getInstance().controlNapMode(controlNapModeParam, iResponseSender);
        } catch (Error | Exception e) {
            Log.e(TAG, "controlNapMode: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
        try {
            RLog.i(TAG, "activate()");
            AppHelper.getInstance().loadAppLabel();
            VehicleHelper.getInstance().saveSettingValue();
        } catch (Exception e) {
            RLog.i(TAG, "activate() Exception", e);
        }
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
        RLog.i(TAG, "deactivate()");
    }
}
