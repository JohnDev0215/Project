package cerence.ark.assistant.controller.domain.hvac;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.hvac.param.CheckHvacFunctionAvailabilityParam;
import cerence.ark.assistant.controller.domain.hvac.param.GetACTemperatureParam;
import cerence.ark.assistant.controller.domain.hvac.param.GetDefrosterStateParam;
import cerence.ark.assistant.controller.domain.hvac.param.GetFanDirectionParam;
import cerence.ark.assistant.controller.domain.hvac.param.GetHavcLevelParam;
import cerence.ark.assistant.controller.domain.hvac.param.GetHvacStateParam;
import cerence.ark.assistant.controller.domain.hvac.param.GetMaxHavcLevelParam;
import cerence.ark.assistant.controller.domain.hvac.param.HvacInterfaceWaitTimerParam;
import cerence.ark.assistant.controller.domain.hvac.param.SetACTemperatureParam;
import cerence.ark.assistant.controller.domain.hvac.param.SetDefrosterStateParam;
import cerence.ark.assistant.controller.domain.hvac.param.SetFanDirectionParam;
import cerence.ark.assistant.controller.domain.hvac.param.SetHvacLevelParam;
import cerence.ark.assistant.controller.domain.hvac.param.SetHvacStateParam;
import cerence.ark.assistant.controller.domain.hvac.result.CheckHvacFunctionAvailabilityResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetACTemperatureResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetDefrosterStateResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetFanDirectionResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetHavcLevelResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetHvacMinTempUnitResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetHvacStateResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetHvacTypeResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetMaxHavcLevelResult;
import cerence.ark.assistant.controller.domain.hvac.result.HvacCheckisHeatPumpResult;
import cerence.ark.assistant.controller.domain.hvac.result.IsPowerOnResult;
import cerence.ark.assistant.controller.domain.hvac.result.SetACTemperatureResult;
import cerence.ark.assistant.controller.domain.hvac.result.SetHvacLevelResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsHVACDomain extends Domain {
    private static final String TAG = "AbsHVACDomain";

    protected boolean abortCheckHvacFunctionAvailability() {
        return true;
    }

    protected boolean abortGetACTemperature() {
        return true;
    }

    protected boolean abortGetDefrosterState() {
        return true;
    }

    protected boolean abortGetFanDirection() {
        return true;
    }

    protected boolean abortGetHavcLevel() {
        return true;
    }

    protected boolean abortGetHvacMinTempUnit() {
        return true;
    }

    protected boolean abortGetHvacState() {
        return true;
    }

    protected boolean abortGetHvacType() {
        return true;
    }

    protected boolean abortGetMaxHavcLevel() {
        return true;
    }

    protected boolean abortHvacCheckisHeatPump() {
        return true;
    }

    protected boolean abortHvacInterfaceWaitTimer() {
        return true;
    }

    protected boolean abortIsPowerOn() {
        return true;
    }

    protected boolean abortSetACTemperature() {
        return true;
    }

    protected boolean abortSetDefrosterState() {
        return true;
    }

    protected boolean abortSetFanDirection() {
        return true;
    }

    protected boolean abortSetHvacLevel() {
        return true;
    }

    protected boolean abortSetHvacState() {
        return true;
    }

    protected abstract void checkHvacFunctionAvailability(CheckHvacFunctionAvailabilityParam checkHvacFunctionAvailabilityParam, IResponseSender<CheckHvacFunctionAvailabilityResult> iResponseSender);

    protected abstract void getACTemperature(GetACTemperatureParam getACTemperatureParam, IResponseSender<GetACTemperatureResult> iResponseSender);

    protected abstract void getDefrosterState(GetDefrosterStateParam getDefrosterStateParam, IResponseSender<GetDefrosterStateResult> iResponseSender);

    protected abstract void getFanDirection(GetFanDirectionParam getFanDirectionParam, IResponseSender<GetFanDirectionResult> iResponseSender);

    protected abstract void getHavcLevel(GetHavcLevelParam getHavcLevelParam, IResponseSender<GetHavcLevelResult> iResponseSender);

    protected abstract void getHvacMinTempUnit(IResponseSender<GetHvacMinTempUnitResult> iResponseSender);

    protected abstract void getHvacState(GetHvacStateParam getHvacStateParam, IResponseSender<GetHvacStateResult> iResponseSender);

    protected abstract void getHvacType(IResponseSender<GetHvacTypeResult> iResponseSender);

    protected abstract void getMaxHavcLevel(GetMaxHavcLevelParam getMaxHavcLevelParam, IResponseSender<GetMaxHavcLevelResult> iResponseSender);

    protected abstract void hvacCheckisHeatPump(IResponseSender<HvacCheckisHeatPumpResult> iResponseSender);

    protected abstract void hvacInterfaceWaitTimer(HvacInterfaceWaitTimerParam hvacInterfaceWaitTimerParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void isPowerOn(IResponseSender<IsPowerOnResult> iResponseSender);

    protected abstract void setACTemperature(SetACTemperatureParam setACTemperatureParam, IResponseSender<SetACTemperatureResult> iResponseSender);

    protected abstract void setDefrosterState(SetDefrosterStateParam setDefrosterStateParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setFanDirection(SetFanDirectionParam setFanDirectionParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setHvacLevel(SetHvacLevelParam setHvacLevelParam, IResponseSender<SetHvacLevelResult> iResponseSender);

    protected abstract void setHvacState(SetHvacStateParam setHvacStateParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsHVACDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(HVACRequestsEnum.IS_POWER_ON, new RequestHandler<IsPowerOnResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsPowerOnResult> iResponseSender) {
                AbsHVACDomain.this.isPowerOn(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortIsPowerOn();
            }
        });
        map.put(HVACRequestsEnum.GET_HVAC_STATE, new RequestHandler<GetHvacStateResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetHvacStateResult> iResponseSender) {
                try {
                    AbsHVACDomain.this.getHvacState(GetHvacStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHVACDomain.TAG, "[HVAC -> getHvacState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortGetHvacState();
            }
        });
        map.put(HVACRequestsEnum.GET_ACTEMPERATURE, new RequestHandler<GetACTemperatureResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetACTemperatureResult> iResponseSender) {
                try {
                    AbsHVACDomain.this.getACTemperature(GetACTemperatureParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHVACDomain.TAG, "[HVAC -> getACTemperature]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortGetACTemperature();
            }
        });
        map.put(HVACRequestsEnum.GET_HAVC_LEVEL, new RequestHandler<GetHavcLevelResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetHavcLevelResult> iResponseSender) {
                try {
                    AbsHVACDomain.this.getHavcLevel(GetHavcLevelParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHVACDomain.TAG, "[HVAC -> getHavcLevel]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortGetHavcLevel();
            }
        });
        map.put(HVACRequestsEnum.GET_MAX_HAVC_LEVEL, new RequestHandler<GetMaxHavcLevelResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetMaxHavcLevelResult> iResponseSender) {
                try {
                    AbsHVACDomain.this.getMaxHavcLevel(GetMaxHavcLevelParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHVACDomain.TAG, "[HVAC -> getMaxHavcLevel]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortGetMaxHavcLevel();
            }
        });
        map.put(HVACRequestsEnum.GET_FAN_DIRECTION, new RequestHandler<GetFanDirectionResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetFanDirectionResult> iResponseSender) {
                try {
                    AbsHVACDomain.this.getFanDirection(GetFanDirectionParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHVACDomain.TAG, "[HVAC -> getFanDirection]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortGetFanDirection();
            }
        });
        map.put(HVACRequestsEnum.GET_DEFROSTER_STATE, new RequestHandler<GetDefrosterStateResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetDefrosterStateResult> iResponseSender) {
                try {
                    AbsHVACDomain.this.getDefrosterState(GetDefrosterStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHVACDomain.TAG, "[HVAC -> getDefrosterState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortGetDefrosterState();
            }
        });
        map.put(HVACRequestsEnum.CHECK_HVAC_FUNCTION_AVAILABILITY, new RequestHandler<CheckHvacFunctionAvailabilityResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckHvacFunctionAvailabilityResult> iResponseSender) {
                try {
                    AbsHVACDomain.this.checkHvacFunctionAvailability(CheckHvacFunctionAvailabilityParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHVACDomain.TAG, "[HVAC -> checkHvacFunctionAvailability]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortCheckHvacFunctionAvailability();
            }
        });
        map.put(HVACRequestsEnum.SET_FAN_DIRECTION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsHVACDomain.this.setFanDirection(SetFanDirectionParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHVACDomain.TAG, "[HVAC -> setFanDirection]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortSetFanDirection();
            }
        });
        map.put(HVACRequestsEnum.SET_HVAC_STATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.10
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsHVACDomain.this.setHvacState(SetHvacStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHVACDomain.TAG, "[HVAC -> setHvacState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortSetHvacState();
            }
        });
        map.put(HVACRequestsEnum.SET_HVAC_LEVEL, new RequestHandler<SetHvacLevelResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.11
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<SetHvacLevelResult> iResponseSender) {
                try {
                    AbsHVACDomain.this.setHvacLevel(SetHvacLevelParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHVACDomain.TAG, "[HVAC -> setHvacLevel]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortSetHvacLevel();
            }
        });
        map.put(HVACRequestsEnum.SET_DEFROSTER_STATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.12
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsHVACDomain.this.setDefrosterState(SetDefrosterStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHVACDomain.TAG, "[HVAC -> setDefrosterState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortSetDefrosterState();
            }
        });
        map.put(HVACRequestsEnum.SET_ACTEMPERATURE, new RequestHandler<SetACTemperatureResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.13
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<SetACTemperatureResult> iResponseSender) {
                try {
                    AbsHVACDomain.this.setACTemperature(SetACTemperatureParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHVACDomain.TAG, "[HVAC -> setACTemperature]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortSetACTemperature();
            }
        });
        map.put(HVACRequestsEnum.HVAC_INTERFACE_WAIT_TIMER, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.14
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsHVACDomain.this.hvacInterfaceWaitTimer(HvacInterfaceWaitTimerParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsHVACDomain.TAG, "[HVAC -> hvacInterfaceWaitTimer]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortHvacInterfaceWaitTimer();
            }
        });
        map.put(HVACRequestsEnum.HVAC_CHECKIS_HEAT_PUMP, new RequestHandler<HvacCheckisHeatPumpResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.15
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<HvacCheckisHeatPumpResult> iResponseSender) {
                AbsHVACDomain.this.hvacCheckisHeatPump(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortHvacCheckisHeatPump();
            }
        });
        map.put(HVACRequestsEnum.GET_HVAC_TYPE, new RequestHandler<GetHvacTypeResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.16
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetHvacTypeResult> iResponseSender) {
                AbsHVACDomain.this.getHvacType(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortGetHvacType();
            }
        });
        map.put(HVACRequestsEnum.GET_HVAC_MIN_TEMP_UNIT, new RequestHandler<GetHvacMinTempUnitResult>() { // from class: cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain.17
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetHvacMinTempUnitResult> iResponseSender) {
                AbsHVACDomain.this.getHvacMinTempUnit(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsHVACDomain.this.abortGetHvacMinTempUnit();
            }
        });
    }
}
