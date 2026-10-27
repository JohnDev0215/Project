package cerence.ark.assistant.controller.domain.vehiclecontrol;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.CheckVehicleFunctionAvailabilityParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.CheckVehicleStatusParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlHUDParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlHeadlightsParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlHighBeamsParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlLockParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlPanoramicViewParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlSunroofParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlTrunkParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetMileageInfoParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetWindowStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.QueryEnergyConsumptionParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.QueryRecentMaxSpeedParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetActiveSpeakersParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetAmbientLightColorParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetAmbientLightStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetClusterModeParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetDrivingModeParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetHUDModeParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetSunshadeStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetWindowStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckVehicleFunctionAvailabilityResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckVehicleStatusResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetAmbientLightStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetMileageInfoResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetSunroofStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetSunshadeStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetVehicleConnectionStatusResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetWindowStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.HasSunshadeSwitchResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.IsDarkOutsideResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.IsRainingResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.QueryCurrentSpeedResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.QueryEnergyConsumptionResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.QueryEnergyTypeResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.QueryEngineTemperatureResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.QueryNextMaintenanceResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.QueryRecentMaxSpeedResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.QueryRemainingMileageResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.QueryTirePressureResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsVehicleControlDomain extends Domain {
    private static final String TAG = "AbsVehicleControlDomain";

    protected boolean abortCheckVehicleFunctionAvailability() {
        return true;
    }

    protected boolean abortCheckVehicleStatus() {
        return true;
    }

    protected boolean abortControlHUD() {
        return true;
    }

    protected boolean abortControlHeadlights() {
        return true;
    }

    protected boolean abortControlHighBeams() {
        return true;
    }

    protected boolean abortControlLock() {
        return true;
    }

    protected boolean abortControlPanoramicView() {
        return true;
    }

    protected boolean abortControlSunroof() {
        return true;
    }

    protected boolean abortControlTrunk() {
        return true;
    }

    protected boolean abortFindVehicle() {
        return true;
    }

    protected boolean abortGetAmbientLightState() {
        return true;
    }

    protected boolean abortGetMileageInfo() {
        return true;
    }

    protected boolean abortGetSunroofState() {
        return true;
    }

    protected boolean abortGetSunshadeState() {
        return true;
    }

    protected boolean abortGetVehicleConnectionStatus() {
        return true;
    }

    protected boolean abortGetWindowState() {
        return true;
    }

    protected boolean abortHasSunshadeSwitch() {
        return true;
    }

    protected boolean abortIsDarkOutside() {
        return true;
    }

    protected boolean abortIsRaining() {
        return true;
    }

    protected boolean abortOpenGasCap() {
        return true;
    }

    protected boolean abortQueryCurrentSpeed() {
        return true;
    }

    protected boolean abortQueryEnergyConsumption() {
        return true;
    }

    protected boolean abortQueryEnergyType() {
        return true;
    }

    protected boolean abortQueryEngineTemperature() {
        return true;
    }

    protected boolean abortQueryNextMaintenance() {
        return true;
    }

    protected boolean abortQueryRecentMaxSpeed() {
        return true;
    }

    protected boolean abortQueryRemainingMileage() {
        return true;
    }

    protected boolean abortQueryTirePressure() {
        return true;
    }

    protected boolean abortSetActiveSpeakers() {
        return true;
    }

    protected boolean abortSetAmbientLightColor() {
        return true;
    }

    protected boolean abortSetAmbientLightState() {
        return true;
    }

    protected boolean abortSetClusterMode() {
        return true;
    }

    protected boolean abortSetDrivingMode() {
        return true;
    }

    protected boolean abortSetHUDMode() {
        return true;
    }

    protected boolean abortSetSunshadeState() {
        return true;
    }

    protected boolean abortSetWindowState() {
        return true;
    }

    protected abstract void checkVehicleFunctionAvailability(CheckVehicleFunctionAvailabilityParam checkVehicleFunctionAvailabilityParam, IResponseSender<CheckVehicleFunctionAvailabilityResult> iResponseSender);

    protected abstract void checkVehicleStatus(CheckVehicleStatusParam checkVehicleStatusParam, IResponseSender<CheckVehicleStatusResult> iResponseSender);

    protected abstract void controlHUD(ControlHUDParam controlHUDParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlHeadlights(ControlHeadlightsParam controlHeadlightsParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlHighBeams(ControlHighBeamsParam controlHighBeamsParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlLock(ControlLockParam controlLockParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlPanoramicView(ControlPanoramicViewParam controlPanoramicViewParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlSunroof(ControlSunroofParam controlSunroofParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlTrunk(ControlTrunkParam controlTrunkParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void findVehicle(IResponseSender<BaseResult> iResponseSender);

    protected abstract void getAmbientLightState(IResponseSender<GetAmbientLightStateResult> iResponseSender);

    protected abstract void getMileageInfo(GetMileageInfoParam getMileageInfoParam, IResponseSender<GetMileageInfoResult> iResponseSender);

    protected abstract void getSunroofState(IResponseSender<GetSunroofStateResult> iResponseSender);

    protected abstract void getSunshadeState(IResponseSender<GetSunshadeStateResult> iResponseSender);

    protected abstract void getVehicleConnectionStatus(IResponseSender<GetVehicleConnectionStatusResult> iResponseSender);

    protected abstract void getWindowState(GetWindowStateParam getWindowStateParam, IResponseSender<GetWindowStateResult> iResponseSender);

    protected abstract void hasSunshadeSwitch(IResponseSender<HasSunshadeSwitchResult> iResponseSender);

    protected abstract void isDarkOutside(IResponseSender<IsDarkOutsideResult> iResponseSender);

    protected abstract void isRaining(IResponseSender<IsRainingResult> iResponseSender);

    protected abstract void openGasCap(IResponseSender<BaseResult> iResponseSender);

    protected abstract void queryCurrentSpeed(IResponseSender<QueryCurrentSpeedResult> iResponseSender);

    protected abstract void queryEnergyConsumption(QueryEnergyConsumptionParam queryEnergyConsumptionParam, IResponseSender<QueryEnergyConsumptionResult> iResponseSender);

    protected abstract void queryEnergyType(IResponseSender<QueryEnergyTypeResult> iResponseSender);

    protected abstract void queryEngineTemperature(IResponseSender<QueryEngineTemperatureResult> iResponseSender);

    protected abstract void queryNextMaintenance(IResponseSender<QueryNextMaintenanceResult> iResponseSender);

    protected abstract void queryRecentMaxSpeed(QueryRecentMaxSpeedParam queryRecentMaxSpeedParam, IResponseSender<QueryRecentMaxSpeedResult> iResponseSender);

    protected abstract void queryRemainingMileage(IResponseSender<QueryRemainingMileageResult> iResponseSender);

    protected abstract void queryTirePressure(IResponseSender<QueryTirePressureResult> iResponseSender);

    protected abstract void setActiveSpeakers(SetActiveSpeakersParam setActiveSpeakersParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setAmbientLightColor(SetAmbientLightColorParam setAmbientLightColorParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setAmbientLightState(SetAmbientLightStateParam setAmbientLightStateParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setClusterMode(SetClusterModeParam setClusterModeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setDrivingMode(SetDrivingModeParam setDrivingModeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setHUDMode(SetHUDModeParam setHUDModeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setSunshadeState(SetSunshadeStateParam setSunshadeStateParam, IResponseSender<BaseResult> iResponseSender);

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
        map.put(VehicleControlRequestsEnum.QUERY_ENERGY_CONSUMPTION, new RequestHandler<QueryEnergyConsumptionResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryEnergyConsumptionResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.queryEnergyConsumption(QueryEnergyConsumptionParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> queryEnergyConsumption]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortQueryEnergyConsumption();
            }
        });
        map.put(VehicleControlRequestsEnum.QUERY_REMAINING_MILEAGE, new RequestHandler<QueryRemainingMileageResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryRemainingMileageResult> iResponseSender) {
                AbsVehicleControlDomain.this.queryRemainingMileage(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortQueryRemainingMileage();
            }
        });
        map.put(VehicleControlRequestsEnum.QUERY_ENERGY_TYPE, new RequestHandler<QueryEnergyTypeResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryEnergyTypeResult> iResponseSender) {
                AbsVehicleControlDomain.this.queryEnergyType(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortQueryEnergyType();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_MILEAGE_INFO, new RequestHandler<GetMileageInfoResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetMileageInfoResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.getMileageInfo(GetMileageInfoParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> getMileageInfo]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetMileageInfo();
            }
        });
        map.put(VehicleControlRequestsEnum.QUERY_NEXT_MAINTENANCE, new RequestHandler<QueryNextMaintenanceResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryNextMaintenanceResult> iResponseSender) {
                AbsVehicleControlDomain.this.queryNextMaintenance(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortQueryNextMaintenance();
            }
        });
        map.put(VehicleControlRequestsEnum.QUERY_RECENT_MAX_SPEED, new RequestHandler<QueryRecentMaxSpeedResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryRecentMaxSpeedResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.queryRecentMaxSpeed(QueryRecentMaxSpeedParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> queryRecentMaxSpeed]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortQueryRecentMaxSpeed();
            }
        });
        map.put(VehicleControlRequestsEnum.QUERY_CURRENT_SPEED, new RequestHandler<QueryCurrentSpeedResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryCurrentSpeedResult> iResponseSender) {
                AbsVehicleControlDomain.this.queryCurrentSpeed(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortQueryCurrentSpeed();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_SUNROOF_STATE, new RequestHandler<GetSunroofStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetSunroofStateResult> iResponseSender) {
                AbsVehicleControlDomain.this.getSunroofState(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetSunroofState();
            }
        });
        map.put(VehicleControlRequestsEnum.IS_RAINING, new RequestHandler<IsRainingResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.10
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsRainingResult> iResponseSender) {
                AbsVehicleControlDomain.this.isRaining(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortIsRaining();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_SUNROOF, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.11
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
        map.put(VehicleControlRequestsEnum.OPEN_GAS_CAP, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.12
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsVehicleControlDomain.this.openGasCap(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortOpenGasCap();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_TRUNK, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.13
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlTrunk(ControlTrunkParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlTrunk]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlTrunk();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_HEADLIGHTS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.14
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlHeadlights(ControlHeadlightsParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlHeadlights]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlHeadlights();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_HIGH_BEAMS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.15
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlHighBeams(ControlHighBeamsParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlHighBeams]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlHighBeams();
            }
        });
        map.put(VehicleControlRequestsEnum.IS_DARK_OUTSIDE, new RequestHandler<IsDarkOutsideResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.16
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsDarkOutsideResult> iResponseSender) {
                AbsVehicleControlDomain.this.isDarkOutside(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortIsDarkOutside();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_WINDOW_STATE, new RequestHandler<GetWindowStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.17
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
        map.put(VehicleControlRequestsEnum.SET_WINDOW_STATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.18
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
        map.put(VehicleControlRequestsEnum.GET_SUNSHADE_STATE, new RequestHandler<GetSunshadeStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.19
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetSunshadeStateResult> iResponseSender) {
                AbsVehicleControlDomain.this.getSunshadeState(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetSunshadeState();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_SUNSHADE_STATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.20
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setSunshadeState(SetSunshadeStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setSunshadeState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetSunshadeState();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_AMBIENT_LIGHT_STATE, new RequestHandler<GetAmbientLightStateResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.21
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetAmbientLightStateResult> iResponseSender) {
                AbsVehicleControlDomain.this.getAmbientLightState(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetAmbientLightState();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_AMBIENT_LIGHT_STATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.22
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setAmbientLightState(SetAmbientLightStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setAmbientLightState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetAmbientLightState();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_AMBIENT_LIGHT_COLOR, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.23
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setAmbientLightColor(SetAmbientLightColorParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setAmbientLightColor]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetAmbientLightColor();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_PANORAMIC_VIEW, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.24
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlPanoramicView(ControlPanoramicViewParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlPanoramicView]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlPanoramicView();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_HUD, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.25
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlHUD(ControlHUDParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlHUD]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlHUD();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_HUDMODE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.26
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setHUDMode(SetHUDModeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setHUDMode]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetHUDMode();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_DRIVING_MODE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.27
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setDrivingMode(SetDrivingModeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setDrivingMode]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetDrivingMode();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_ACTIVE_SPEAKERS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.28
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setActiveSpeakers(SetActiveSpeakersParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setActiveSpeakers]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetActiveSpeakers();
            }
        });
        map.put(VehicleControlRequestsEnum.CHECK_VEHICLE_STATUS, new RequestHandler<CheckVehicleStatusResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.29
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckVehicleStatusResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.checkVehicleStatus(CheckVehicleStatusParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> checkVehicleStatus]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortCheckVehicleStatus();
            }
        });
        map.put(VehicleControlRequestsEnum.QUERY_TIRE_PRESSURE, new RequestHandler<QueryTirePressureResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.30
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryTirePressureResult> iResponseSender) {
                AbsVehicleControlDomain.this.queryTirePressure(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortQueryTirePressure();
            }
        });
        map.put(VehicleControlRequestsEnum.QUERY_ENGINE_TEMPERATURE, new RequestHandler<QueryEngineTemperatureResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.31
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryEngineTemperatureResult> iResponseSender) {
                AbsVehicleControlDomain.this.queryEngineTemperature(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortQueryEngineTemperature();
            }
        });
        map.put(VehicleControlRequestsEnum.CONTROL_LOCK, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.32
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.controlLock(ControlLockParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> controlLock]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortControlLock();
            }
        });
        map.put(VehicleControlRequestsEnum.FIND_VEHICLE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.33
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsVehicleControlDomain.this.findVehicle(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortFindVehicle();
            }
        });
        map.put(VehicleControlRequestsEnum.GET_VEHICLE_CONNECTION_STATUS, new RequestHandler<GetVehicleConnectionStatusResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.34
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetVehicleConnectionStatusResult> iResponseSender) {
                AbsVehicleControlDomain.this.getVehicleConnectionStatus(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortGetVehicleConnectionStatus();
            }
        });
        map.put(VehicleControlRequestsEnum.SET_CLUSTER_MODE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.35
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsVehicleControlDomain.this.setClusterMode(SetClusterModeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsVehicleControlDomain.TAG, "[VehicleControl -> setClusterMode]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortSetClusterMode();
            }
        });
        map.put(VehicleControlRequestsEnum.HAS_SUNSHADE_SWITCH, new RequestHandler<HasSunshadeSwitchResult>() { // from class: cerence.ark.assistant.controller.domain.vehiclecontrol.AbsVehicleControlDomain.36
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<HasSunshadeSwitchResult> iResponseSender) {
                AbsVehicleControlDomain.this.hasSunshadeSwitch(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsVehicleControlDomain.this.abortHasSunshadeSwitch();
            }
        });
    }
}
