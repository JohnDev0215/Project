package cerence.ark.assistant.controller.domain.vehiclecontrol;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
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
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsVehicleControlDomain extends Domain {
    private static final String TAG = "AbsVehicleControlDomain";

    protected boolean abortCheckCPAAInForeground() {
        return true;
    }

    protected boolean abortCheckCPAAconnection() {
        return true;
    }

    protected boolean abortCheckCapability() {
        return true;
    }

    protected boolean abortCheckPerfumeEmpty() {
        return true;
    }

    protected boolean abortCheckScreenState() {
        return true;
    }

    protected boolean abortCheckVehicleFunctionAvailability() {
        return true;
    }

    protected boolean abortCloseApp() {
        return true;
    }

    protected boolean abortCloseFSDevice() {
        return true;
    }

    protected boolean abortControlAdas() {
        return true;
    }

    protected boolean abortControlBluetooth() {
        return true;
    }

    protected boolean abortControlCameraRecord() {
        return true;
    }

    protected boolean abortControlCameraSwitch() {
        return true;
    }

    protected boolean abortControlCameraTakePhoto() {
        return true;
    }

    protected boolean abortControlChildLock() {
        return true;
    }

    protected boolean abortControlDrivingMode() {
        return true;
    }

    protected boolean abortControlEngineSound() {
        return true;
    }

    protected boolean abortControlHotspot() {
        return true;
    }

    protected boolean abortControlNapMode() {
        return true;
    }

    protected boolean abortControlRoadMode() {
        return true;
    }

    protected boolean abortControlSunroof() {
        return true;
    }

    protected boolean abortControlWifi() {
        return true;
    }

    protected boolean abortControlWindowLock() {
        return true;
    }

    protected boolean abortControlWingMirrorHeating() {
        return true;
    }

    protected boolean abortDecreaseFragranceLevel() {
        return true;
    }

    protected boolean abortDecreaseInteriorLightBrightness() {
        return true;
    }

    protected boolean abortDecreaseScreenBrightness() {
        return true;
    }

    protected boolean abortGetAdasStatus() {
        return true;
    }

    protected boolean abortGetBlindsState() {
        return true;
    }

    protected boolean abortGetChildLockState() {
        return true;
    }

    protected boolean abortGetDeviceState() {
        return true;
    }

    protected boolean abortGetFSDeviceState() {
        return true;
    }

    protected boolean abortGetFragranceLevel() {
        return true;
    }

    protected boolean abortGetFragranceState() {
        return true;
    }

    protected boolean abortGetInteriorLightBrightness() {
        return true;
    }

    protected boolean abortGetInteriorLightState() {
        return true;
    }

    protected boolean abortGetInteriorLightZones() {
        return true;
    }

    protected boolean abortGetScreenBrightness() {
        return true;
    }

    protected boolean abortGetScreenDisplayMode() {
        return true;
    }

    protected boolean abortGetSocState() {
        return true;
    }

    protected boolean abortGetSunroofState() {
        return true;
    }

    protected boolean abortGetSupportedDrivingMode() {
        return true;
    }

    protected boolean abortGetSupportedRoadMode() {
        return true;
    }

    protected boolean abortGetTowingState() {
        return true;
    }

    protected boolean abortGetTrunkState() {
        return true;
    }

    protected boolean abortGetVehicleInfo() {
        return true;
    }

    protected boolean abortGetWindowLockState() {
        return true;
    }

    protected boolean abortGetWindowState() {
        return true;
    }

    protected boolean abortIncreaseFragranceLevel() {
        return true;
    }

    protected boolean abortIncreaseInteriorLightBrightness() {
        return true;
    }

    protected boolean abortIncreaseScreenBrightness() {
        return true;
    }

    protected boolean abortIsParkingGearOn() {
        return true;
    }

    protected boolean abortIsRaining() {
        return true;
    }

    protected boolean abortOpenApp() {
        return true;
    }

    protected boolean abortOpenFSDevice() {
        return true;
    }

    protected boolean abortQueryCurrentSpeed() {
        return true;
    }

    protected boolean abortQueryTirePressure() {
        return true;
    }

    protected boolean abortRotateScreen() {
        return true;
    }

    protected boolean abortSetBlindsState() {
        return true;
    }

    protected boolean abortSetFragranceLevel() {
        return true;
    }

    protected boolean abortSetFragranceState() {
        return true;
    }

    protected boolean abortSetInteriorLightBrightness() {
        return true;
    }

    protected boolean abortSetInteriorLightColor() {
        return true;
    }

    protected boolean abortSetInteriorLightState() {
        return true;
    }

    protected boolean abortSetScreenBrightness() {
        return true;
    }

    protected boolean abortSetScreenDisplayMode() {
        return true;
    }

    protected boolean abortSetScreenOnOff() {
        return true;
    }

    protected boolean abortSetScreenOrientation() {
        return true;
    }

    protected boolean abortSetSocState() {
        return true;
    }

    protected boolean abortSetTrunkState() {
        return true;
    }

    protected boolean abortSetWindowState() {
        return true;
    }

    protected abstract void checkCPAAInForeground(IResponseSender<CheckCPAAInForegroundResult> iResponseSender);

    protected abstract void checkCPAAconnection(CheckCPAAconnectionParam checkCPAAconnectionParam, IResponseSender<CheckCPAAconnectionResult> iResponseSender);

    protected abstract void checkCapability(CheckCapabilityParam checkCapabilityParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void checkPerfumeEmpty(IResponseSender<CheckPerfumeEmptyResult> iResponseSender);

    protected abstract void checkScreenState(IResponseSender<CheckScreenStateResult> iResponseSender);

    protected abstract void checkVehicleFunctionAvailability(CheckVehicleFunctionAvailabilityParam checkVehicleFunctionAvailabilityParam, IResponseSender<CheckVehicleFunctionAvailabilityResult> iResponseSender);

    protected abstract void closeApp(CloseAppParam closeAppParam, IResponseSender<CloseAppResult> iResponseSender);

    protected abstract void closeFSDevice(CloseFSDeviceParam closeFSDeviceParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlAdas(ControlAdasParam controlAdasParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlBluetooth(ControlBluetoothParam controlBluetoothParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlCameraRecord(ControlCameraRecordParam controlCameraRecordParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlCameraSwitch(ControlCameraSwitchParam controlCameraSwitchParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlCameraTakePhoto(ControlCameraTakePhotoParam controlCameraTakePhotoParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlChildLock(ControlChildLockParam controlChildLockParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlDrivingMode(ControlDrivingModeParam controlDrivingModeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlEngineSound(ControlEngineSoundParam controlEngineSoundParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlHotspot(ControlHotspotParam controlHotspotParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlNapMode(ControlNapModeParam controlNapModeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlRoadMode(ControlRoadModeParam controlRoadModeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlSunroof(ControlSunroofParam controlSunroofParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlWifi(ControlWifiParam controlWifiParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlWindowLock(ControlWindowLockParam controlWindowLockParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlWingMirrorHeating(ControlWingMirrorHeatingParam controlWingMirrorHeatingParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void decreaseFragranceLevel(IResponseSender<BaseResult> iResponseSender);

    protected abstract void decreaseInteriorLightBrightness(DecreaseInteriorLightBrightnessParam decreaseInteriorLightBrightnessParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void decreaseScreenBrightness(DecreaseScreenBrightnessParam decreaseScreenBrightnessParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void getAdasStatus(GetAdasStatusParam getAdasStatusParam, IResponseSender<GetAdasStatusResult> iResponseSender);

    protected abstract void getBlindsState(IResponseSender<GetBlindsStateResult> iResponseSender);

    protected abstract void getChildLockState(GetChildLockStateParam getChildLockStateParam, IResponseSender<GetChildLockStateResult> iResponseSender);

    protected abstract void getDeviceState(GetDeviceStateParam getDeviceStateParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void getFSDeviceState(GetFSDeviceStateParam getFSDeviceStateParam, IResponseSender<GetFSDeviceStateResult> iResponseSender);

    protected abstract void getFragranceLevel(IResponseSender<GetFragranceLevelResult> iResponseSender);

    protected abstract void getFragranceState(IResponseSender<GetFragranceStateResult> iResponseSender);

    protected abstract void getInteriorLightBrightness(GetInteriorLightBrightnessParam getInteriorLightBrightnessParam, IResponseSender<GetInteriorLightBrightnessResult> iResponseSender);

    protected abstract void getInteriorLightState(GetInteriorLightStateParam getInteriorLightStateParam, IResponseSender<GetInteriorLightStateResult> iResponseSender);

    protected abstract void getInteriorLightZones(GetInteriorLightZonesParam getInteriorLightZonesParam, IResponseSender<GetInteriorLightZonesResult> iResponseSender);

    protected abstract void getScreenBrightness(GetScreenBrightnessParam getScreenBrightnessParam, IResponseSender<GetScreenBrightnessResult> iResponseSender);

    protected abstract void getScreenDisplayMode(GetScreenDisplayModeParam getScreenDisplayModeParam, IResponseSender<GetScreenDisplayModeResult> iResponseSender);

    protected abstract void getSocState(IResponseSender<GetSocStateResult> iResponseSender);

    protected abstract void getSunroofState(GetSunroofStateParam getSunroofStateParam, IResponseSender<GetSunroofStateResult> iResponseSender);

    protected abstract void getSupportedDrivingMode(IResponseSender<GetSupportedDrivingModeResult> iResponseSender);

    protected abstract void getSupportedRoadMode(IResponseSender<GetSupportedRoadModeResult> iResponseSender);

    protected abstract void getTowingState(IResponseSender<GetTowingStateResult> iResponseSender);

    protected abstract void getTrunkState(IResponseSender<GetTrunkStateResult> iResponseSender);

    protected abstract void getVehicleInfo(IResponseSender<GetVehicleInfoResult> iResponseSender);

    protected abstract void getWindowLockState(GetWindowLockStateParam getWindowLockStateParam, IResponseSender<GetWindowLockStateResult> iResponseSender);

    protected abstract void getWindowState(GetWindowStateParam getWindowStateParam, IResponseSender<GetWindowStateResult> iResponseSender);

    protected abstract void increaseFragranceLevel(IResponseSender<BaseResult> iResponseSender);

    protected abstract void increaseInteriorLightBrightness(IncreaseInteriorLightBrightnessParam increaseInteriorLightBrightnessParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void increaseScreenBrightness(IncreaseScreenBrightnessParam increaseScreenBrightnessParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void isParkingGearOn(IResponseSender<IsParkingGearOnResult> iResponseSender);

    protected abstract void isRaining(IResponseSender<IsRainingResult> iResponseSender);

    protected abstract void openApp(OpenAppParam openAppParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void openFSDevice(OpenFSDeviceParam openFSDeviceParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void queryCurrentSpeed(IResponseSender<QueryCurrentSpeedResult> iResponseSender);

    protected abstract void queryTirePressure(IResponseSender<QueryTirePressureResult> iResponseSender);

    protected abstract void rotateScreen(IResponseSender<BaseResult> iResponseSender);

    protected abstract void setBlindsState(SetBlindsStateParam setBlindsStateParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setFragranceLevel(SetFragranceLevelParam setFragranceLevelParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setFragranceState(SetFragranceStateParam setFragranceStateParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setInteriorLightBrightness(SetInteriorLightBrightnessParam setInteriorLightBrightnessParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setInteriorLightColor(SetInteriorLightColorParam setInteriorLightColorParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setInteriorLightState(SetInteriorLightStateParam setInteriorLightStateParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setScreenBrightness(SetScreenBrightnessParam setScreenBrightnessParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setScreenDisplayMode(SetScreenDisplayModeParam setScreenDisplayModeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setScreenOnOff(SetScreenOnOffParam setScreenOnOffParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setScreenOrientation(SetScreenOrientationParam setScreenOrientationParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setSocState(SetSocStateParam setSocStateParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setTrunkState(SetTrunkStateParam setTrunkStateParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setWindowState(SetWindowStateParam setWindowStateParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsVehicleControlDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(VehicleControlRequestsEnum.CHECK_VEHICLE_FUNCTION_AVAILABILITY, new RequestHandler<CheckVehicleFunctionAvailabilityResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckVehicleFunctionAvailabilityResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.checkVehicleFunctionAvailability(CheckVehicleFunctionAvailabilityParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> checkVehicleFunctionAvailability]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortCheckVehicleFunctionAvailability();
            }
        });
        map.put(VehicleControlRequestsEnum.IS_RAINING, new RequestHandler<IsRainingResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsRainingResult> iResponseSender) {
                AbsVehicleControlDomain.this.isRaining(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortIsRaining();
            }
        });
        map.put(VehicleControlRequestsEnum.QUERY_CURRENT_SPEED, new RequestHandler<QueryCurrentSpeedResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryCurrentSpeedResult> iResponseSender) {
                AbsVehicleControlDomain.this.queryCurrentSpeed(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortQueryCurrentSpeed();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_WIFI, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlWifi(ControlWifiParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlWifi]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlWifi();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_BLUETOOTH, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlBluetooth(ControlBluetoothParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlBluetooth]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlBluetooth();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_HOTSPOT, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlHotspot(ControlHotspotParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlHotspot]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlHotspot();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_WING_MIRROR_HEATING, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlWingMirrorHeating(ControlWingMirrorHeatingParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlWingMirrorHeating]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlWingMirrorHeating();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_WINDOW_LOCK, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlWindowLock(ControlWindowLockParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlWindowLock]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlWindowLock();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_CHILD_LOCK_STATE, new RequestHandler<GetChildLockStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetChildLockStateResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.getChildLockState(GetChildLockStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> getChildLockState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetChildLockState();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_CHILD_LOCK, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.10
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlChildLock(ControlChildLockParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlChildLock]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlChildLock();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_WINDOW_LOCK_STATE, new RequestHandler<GetWindowLockStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.11
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetWindowLockStateResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.getWindowLockState(GetWindowLockStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> getWindowLockState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetWindowLockState();
            }
        });
        map.put(VehicleControlRequestsEnum.OPEN_APP, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.12
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.openApp(OpenAppParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> openApp]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortOpenApp();
            }
        });
        map.put(VehicleControlRequestsEnum.CLOSE_APP, new RequestHandler<CloseAppResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.13
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CloseAppResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.closeApp(CloseAppParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> closeApp]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortCloseApp();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_SUNROOF_STATE, new RequestHandler<GetSunroofStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.14
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetSunroofStateResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.getSunroofState(GetSunroofStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> getSunroofState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetSunroofState();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_SUNROOF, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.15
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlSunroof(ControlSunroofParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlSunroof]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlSunroof();
            }
        });
        map.put(VehicleControlRequestsEnum.CHECK_PERFUME_EMPTY, new RequestHandler<CheckPerfumeEmptyResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.16
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckPerfumeEmptyResult> iResponseSender) {
                AbsVehicleControlDomain.this.checkPerfumeEmpty(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortCheckPerfumeEmpty();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_TRUNK_STATE, new RequestHandler<GetTrunkStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.17
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetTrunkStateResult> iResponseSender) {
                AbsVehicleControlDomain.this.getTrunkState(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetTrunkState();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_TRUNK_STATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.18
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setTrunkState(SetTrunkStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setTrunkState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetTrunkState();
            }
        });
        map.put(VehicleControlRequestsEnum.IS_PARKING_GEAR_ON, new RequestHandler<IsParkingGearOnResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.19
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsParkingGearOnResult> iResponseSender) {
                AbsVehicleControlDomain.this.isParkingGearOn(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortIsParkingGearOn();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_WINDOW_STATE, new RequestHandler<GetWindowStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.20
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetWindowStateResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.getWindowState(GetWindowStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> getWindowState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetWindowState();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_WINDOW_STATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.21
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setWindowState(SetWindowStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setWindowState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetWindowState();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_BLINDS_STATE, new RequestHandler<GetBlindsStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.22
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetBlindsStateResult> iResponseSender) {
                AbsVehicleControlDomain.this.getBlindsState(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetBlindsState();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_BLINDS_STATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.23
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setBlindsState(SetBlindsStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setBlindsState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetBlindsState();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_INTERIOR_LIGHT_STATE, new RequestHandler<GetInteriorLightStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.24
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetInteriorLightStateResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.getInteriorLightState(GetInteriorLightStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> getInteriorLightState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetInteriorLightState();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_INTERIOR_LIGHT_STATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.25
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setInteriorLightState(SetInteriorLightStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setInteriorLightState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetInteriorLightState();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_INTERIOR_LIGHT_BRIGHTNESS, new RequestHandler<GetInteriorLightBrightnessResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.26
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetInteriorLightBrightnessResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.getInteriorLightBrightness(GetInteriorLightBrightnessParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> getInteriorLightBrightness]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetInteriorLightBrightness();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_INTERIOR_LIGHT_BRIGHTNESS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.27
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setInteriorLightBrightness(SetInteriorLightBrightnessParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setInteriorLightBrightness]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetInteriorLightBrightness();
            }
        });
        map.put(VehicleControlRequestsEnum.INCREASE_INTERIOR_LIGHT_BRIGHTNESS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.28
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.increaseInteriorLightBrightness(IncreaseInteriorLightBrightnessParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> increaseInteriorLightBrightness]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortIncreaseInteriorLightBrightness();
            }
        });
        map.put(VehicleControlRequestsEnum.DECREASE_INTERIOR_LIGHT_BRIGHTNESS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.29
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.decreaseInteriorLightBrightness(DecreaseInteriorLightBrightnessParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> decreaseInteriorLightBrightness]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortDecreaseInteriorLightBrightness();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_INTERIOR_LIGHT_COLOR, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.30
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setInteriorLightColor(SetInteriorLightColorParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setInteriorLightColor]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetInteriorLightColor();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_INTERIOR_LIGHT_ZONES, new RequestHandler<GetInteriorLightZonesResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.31
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetInteriorLightZonesResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.getInteriorLightZones(GetInteriorLightZonesParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> getInteriorLightZones]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetInteriorLightZones();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_SCREEN_BRIGHTNESS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.32
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setScreenBrightness(SetScreenBrightnessParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setScreenBrightness]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetScreenBrightness();
            }
        });
        map.put(VehicleControlRequestsEnum.INCREASE_SCREEN_BRIGHTNESS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.33
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.increaseScreenBrightness(IncreaseScreenBrightnessParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> increaseScreenBrightness]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortIncreaseScreenBrightness();
            }
        });
        map.put(VehicleControlRequestsEnum.DECREASE_SCREEN_BRIGHTNESS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.34
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.decreaseScreenBrightness(DecreaseScreenBrightnessParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> decreaseScreenBrightness]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortDecreaseScreenBrightness();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_SCREEN_BRIGHTNESS, new RequestHandler<GetScreenBrightnessResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.35
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetScreenBrightnessResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.getScreenBrightness(GetScreenBrightnessParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> getScreenBrightness]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetScreenBrightness();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_SCREEN_DISPLAY_MODE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.36
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setScreenDisplayMode(SetScreenDisplayModeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setScreenDisplayMode]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetScreenDisplayMode();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_SCREEN_DISPLAY_MODE, new RequestHandler<GetScreenDisplayModeResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.37
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetScreenDisplayModeResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.getScreenDisplayMode(GetScreenDisplayModeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> getScreenDisplayMode]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetScreenDisplayMode();
            }
        });
        map.put(VehicleControlRequestsEnum.ROTATE_SCREEN, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.38
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsVehicleControlDomain.this.rotateScreen(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortRotateScreen();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_SCREEN_ORIENTATION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.39
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setScreenOrientation(SetScreenOrientationParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setScreenOrientation]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetScreenOrientation();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_SCREEN_ON_OFF, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.40
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setScreenOnOff(SetScreenOnOffParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setScreenOnOff]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetScreenOnOff();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_VEHICLE_INFO, new RequestHandler<GetVehicleInfoResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.41
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetVehicleInfoResult> iResponseSender) {
                AbsVehicleControlDomain.this.getVehicleInfo(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetVehicleInfo();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_ENGINE_SOUND, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.42
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlEngineSound(ControlEngineSoundParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlEngineSound]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlEngineSound();
            }
        });
        map.put(VehicleControlRequestsEnum.QUERY_TIRE_PRESSURE, new RequestHandler<QueryTirePressureResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.43
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryTirePressureResult> iResponseSender) {
                AbsVehicleControlDomain.this.queryTirePressure(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortQueryTirePressure();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_FRAGRANCE_STATE, new RequestHandler<GetFragranceStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.44
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetFragranceStateResult> iResponseSender) {
                AbsVehicleControlDomain.this.getFragranceState(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetFragranceState();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_FRAGRANCE_STATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.45
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setFragranceState(SetFragranceStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setFragranceState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetFragranceState();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_FRAGRANCE_LEVEL, new RequestHandler<GetFragranceLevelResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.46
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetFragranceLevelResult> iResponseSender) {
                AbsVehicleControlDomain.this.getFragranceLevel(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetFragranceLevel();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_FRAGRANCE_LEVEL, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.47
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setFragranceLevel(SetFragranceLevelParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setFragranceLevel]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetFragranceLevel();
            }
        });
        map.put(VehicleControlRequestsEnum.INCREASE_FRAGRANCE_LEVEL, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.48
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsVehicleControlDomain.this.increaseFragranceLevel(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortIncreaseFragranceLevel();
            }
        });
        map.put(VehicleControlRequestsEnum.DECREASE_FRAGRANCE_LEVEL, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.49
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsVehicleControlDomain.this.decreaseFragranceLevel(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortDecreaseFragranceLevel();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_SOC_STATE, new RequestHandler<GetSocStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.50
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetSocStateResult> iResponseSender) {
                AbsVehicleControlDomain.this.getSocState(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetSocState();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_SOC_STATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.51
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setSocState(SetSocStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setSocState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetSocState();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_ADAS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.52
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlAdas(ControlAdasParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlAdas]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlAdas();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_CAMERA_TAKE_PHOTO, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.53
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlCameraTakePhoto(ControlCameraTakePhotoParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlCameraTakePhoto]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlCameraTakePhoto();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_CAMERA_RECORD, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.54
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlCameraRecord(ControlCameraRecordParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlCameraRecord]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlCameraRecord();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_CAMERA_SWITCH, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.55
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlCameraSwitch(ControlCameraSwitchParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlCameraSwitch]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlCameraSwitch();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_DRIVING_MODE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.56
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlDrivingMode(ControlDrivingModeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlDrivingMode]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlDrivingMode();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_ROAD_MODE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.57
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlRoadMode(ControlRoadModeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlRoadMode]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlRoadMode();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_SUPPORTED_DRIVING_MODE, new RequestHandler<GetSupportedDrivingModeResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.58
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetSupportedDrivingModeResult> iResponseSender) {
                AbsVehicleControlDomain.this.getSupportedDrivingMode(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetSupportedDrivingMode();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_SUPPORTED_ROAD_MODE, new RequestHandler<GetSupportedRoadModeResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.59
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetSupportedRoadModeResult> iResponseSender) {
                AbsVehicleControlDomain.this.getSupportedRoadMode(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetSupportedRoadMode();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_FSDEVICE_STATE, new RequestHandler<GetFSDeviceStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.60
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetFSDeviceStateResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.getFSDeviceState(GetFSDeviceStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> getFSDeviceState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetFSDeviceState();
            }
        });
        map.put(VehicleControlRequestsEnum.OPEN_FSDEVICE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.61
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.openFSDevice(OpenFSDeviceParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> openFSDevice]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortOpenFSDevice();
            }
        });
        map.put(VehicleControlRequestsEnum.CLOSE_FSDEVICE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.62
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.closeFSDevice(CloseFSDeviceParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> closeFSDevice]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortCloseFSDevice();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_ADAS_STATUS, new RequestHandler<GetAdasStatusResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.63
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetAdasStatusResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.getAdasStatus(GetAdasStatusParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> getAdasStatus]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetAdasStatus();
            }
        });
        map.put(VehicleControlRequestsEnum.CHECK_CPAACONNECTION, new RequestHandler<CheckCPAAconnectionResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.64
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckCPAAconnectionResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.checkCPAAconnection(CheckCPAAconnectionParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> checkCPAAconnection]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortCheckCPAAconnection();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_DEVICE_STATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.65
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.getDeviceState(GetDeviceStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> getDeviceState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetDeviceState();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_TOWING_STATE, new RequestHandler<GetTowingStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.66
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetTowingStateResult> iResponseSender) {
                AbsVehicleControlDomain.this.getTowingState(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetTowingState();
            }
        });
        map.put(VehicleControlRequestsEnum.CHECK_CAPABILITY, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.67
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.checkCapability(CheckCapabilityParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> checkCapability]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortCheckCapability();
            }
        });
        map.put(VehicleControlRequestsEnum.CHECK_SCREEN_STATE, new RequestHandler<CheckScreenStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.68
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckScreenStateResult> iResponseSender) {
                AbsVehicleControlDomain.this.checkScreenState(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortCheckScreenState();
            }
        });
        map.put(VehicleControlRequestsEnum.CHECK_CPAAIN_FOREGROUND, new RequestHandler<CheckCPAAInForegroundResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.69
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckCPAAInForegroundResult> iResponseSender) {
                AbsVehicleControlDomain.this.checkCPAAInForeground(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortCheckCPAAInForeground();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_NAP_MODE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.70
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlNapMode(ControlNapModeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlNapMode]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlNapMode();
            }
        });
    }
}
