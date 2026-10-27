package cerence.ark.assistant.controller.domain.cc;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.cc.param.CheckFunctionAvailabilityParam;
import cerence.ark.assistant.controller.domain.cc.param.CheckVolumeControlAvailabilityParam;
import cerence.ark.assistant.controller.domain.cc.param.CloseAppParam;
import cerence.ark.assistant.controller.domain.cc.param.ControlBluetoothParam;
import cerence.ark.assistant.controller.domain.cc.param.ControlVolumeParam;
import cerence.ark.assistant.controller.domain.cc.param.ControlWifiParam;
import cerence.ark.assistant.controller.domain.cc.param.GetVolumeParam;
import cerence.ark.assistant.controller.domain.cc.param.IsForegroundAppParam;
import cerence.ark.assistant.controller.domain.cc.param.OpenAppParam;
import cerence.ark.assistant.controller.domain.cc.param.SetScreenBrightnessParam;
import cerence.ark.assistant.controller.domain.cc.param.SetScreenStateParam;
import cerence.ark.assistant.controller.domain.cc.param.SetVolumeParam;
import cerence.ark.assistant.controller.domain.cc.result.CheckFunctionAvailabilityResult;
import cerence.ark.assistant.controller.domain.cc.result.CheckVolumeControlAvailabilityResult;
import cerence.ark.assistant.controller.domain.cc.result.GetActiveStreamTypeResult;
import cerence.ark.assistant.controller.domain.cc.result.GetScreenBrightnessResult;
import cerence.ark.assistant.controller.domain.cc.result.GetScreenStateResult;
import cerence.ark.assistant.controller.domain.cc.result.GetVolumeResult;
import cerence.ark.assistant.controller.domain.cc.result.IsForegroundAppResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsCCDomain extends Domain {
    private static final String TAG = "AbsCCDomain";

    protected boolean abortCancel() {
        return true;
    }

    protected boolean abortCheckFunctionAvailability() {
        return true;
    }

    protected boolean abortCheckVolumeControlAvailability() {
        return true;
    }

    protected boolean abortCloseApp() {
        return true;
    }

    protected boolean abortControlBluetooth() {
        return true;
    }

    protected boolean abortControlVolume() {
        return true;
    }

    protected boolean abortControlWifi() {
        return true;
    }

    protected boolean abortGetActiveStreamType() {
        return true;
    }

    protected boolean abortGetScreenBrightness() {
        return true;
    }

    protected boolean abortGetScreenState() {
        return true;
    }

    protected boolean abortGetVolume() {
        return true;
    }

    protected boolean abortIsForegroundApp() {
        return true;
    }

    protected boolean abortOpenApp() {
        return true;
    }

    protected boolean abortSetScreenBrightness() {
        return true;
    }

    protected boolean abortSetScreenState() {
        return true;
    }

    protected boolean abortSetVolume() {
        return true;
    }

    protected abstract void cancel(IResponseSender<BaseResult> iResponseSender);

    protected abstract void checkFunctionAvailability(CheckFunctionAvailabilityParam checkFunctionAvailabilityParam, IResponseSender<CheckFunctionAvailabilityResult> iResponseSender);

    protected abstract void checkVolumeControlAvailability(CheckVolumeControlAvailabilityParam checkVolumeControlAvailabilityParam, IResponseSender<CheckVolumeControlAvailabilityResult> iResponseSender);

    protected abstract void closeApp(CloseAppParam closeAppParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlBluetooth(ControlBluetoothParam controlBluetoothParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlVolume(ControlVolumeParam controlVolumeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlWifi(ControlWifiParam controlWifiParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void getActiveStreamType(IResponseSender<GetActiveStreamTypeResult> iResponseSender);

    protected abstract void getScreenBrightness(IResponseSender<GetScreenBrightnessResult> iResponseSender);

    protected abstract void getScreenState(IResponseSender<GetScreenStateResult> iResponseSender);

    protected abstract void getVolume(GetVolumeParam getVolumeParam, IResponseSender<GetVolumeResult> iResponseSender);

    protected abstract void isForegroundApp(IsForegroundAppParam isForegroundAppParam, IResponseSender<IsForegroundAppResult> iResponseSender);

    protected abstract void openApp(OpenAppParam openAppParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setScreenBrightness(SetScreenBrightnessParam setScreenBrightnessParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setScreenState(SetScreenStateParam setScreenStateParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setVolume(SetVolumeParam setVolumeParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsCCDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(CCRequestsEnum.SET_VOLUME, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsCCDomain.this.setVolume(SetVolumeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsCCDomain.TAG, "[CC -> setVolume]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortSetVolume();
            }
        });
        map.put(CCRequestsEnum.GET_VOLUME, new RequestHandler<GetVolumeResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetVolumeResult> iResponseSender) {
                try {
                    AbsCCDomain.this.getVolume(GetVolumeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsCCDomain.TAG, "[CC -> getVolume]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortGetVolume();
            }
        });
        map.put(CCRequestsEnum.CONTROL_VOLUME, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsCCDomain.this.controlVolume(ControlVolumeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsCCDomain.TAG, "[CC -> controlVolume]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortControlVolume();
            }
        });
        map.put(CCRequestsEnum.SET_SCREEN_BRIGHTNESS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsCCDomain.this.setScreenBrightness(SetScreenBrightnessParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsCCDomain.TAG, "[CC -> setScreenBrightness]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortSetScreenBrightness();
            }
        });
        map.put(CCRequestsEnum.GET_SCREEN_BRIGHTNESS, new RequestHandler<GetScreenBrightnessResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetScreenBrightnessResult> iResponseSender) {
                AbsCCDomain.this.getScreenBrightness(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortGetScreenBrightness();
            }
        });
        map.put(CCRequestsEnum.CONTROL_WIFI, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsCCDomain.this.controlWifi(ControlWifiParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsCCDomain.TAG, "[CC -> controlWifi]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortControlWifi();
            }
        });
        map.put(CCRequestsEnum.CONTROL_BLUETOOTH, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsCCDomain.this.controlBluetooth(ControlBluetoothParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsCCDomain.TAG, "[CC -> controlBluetooth]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortControlBluetooth();
            }
        });
        map.put(CCRequestsEnum.OPEN_APP, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsCCDomain.this.openApp(OpenAppParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsCCDomain.TAG, "[CC -> openApp]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortOpenApp();
            }
        });
        map.put(CCRequestsEnum.CLOSE_APP, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsCCDomain.this.closeApp(CloseAppParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsCCDomain.TAG, "[CC -> closeApp]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortCloseApp();
            }
        });
        map.put(CCRequestsEnum.CANCEL, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.10
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsCCDomain.this.cancel(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortCancel();
            }
        });
        map.put(CCRequestsEnum.CHECK_FUNCTION_AVAILABILITY, new RequestHandler<CheckFunctionAvailabilityResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.11
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckFunctionAvailabilityResult> iResponseSender) {
                try {
                    AbsCCDomain.this.checkFunctionAvailability(CheckFunctionAvailabilityParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsCCDomain.TAG, "[CC -> checkFunctionAvailability]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortCheckFunctionAvailability();
            }
        });
        map.put(CCRequestsEnum.SET_SCREEN_STATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.12
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsCCDomain.this.setScreenState(SetScreenStateParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsCCDomain.TAG, "[CC -> setScreenState]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortSetScreenState();
            }
        });
        map.put(CCRequestsEnum.GET_SCREEN_STATE, new RequestHandler<GetScreenStateResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.13
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetScreenStateResult> iResponseSender) {
                AbsCCDomain.this.getScreenState(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortGetScreenState();
            }
        });
        map.put(CCRequestsEnum.GET_ACTIVE_STREAM_TYPE, new RequestHandler<GetActiveStreamTypeResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.14
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetActiveStreamTypeResult> iResponseSender) {
                AbsCCDomain.this.getActiveStreamType(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortGetActiveStreamType();
            }
        });
        map.put(CCRequestsEnum.CHECK_VOLUME_CONTROL_AVAILABILITY, new RequestHandler<CheckVolumeControlAvailabilityResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.15
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckVolumeControlAvailabilityResult> iResponseSender) {
                try {
                    AbsCCDomain.this.checkVolumeControlAvailability(CheckVolumeControlAvailabilityParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsCCDomain.TAG, "[CC -> checkVolumeControlAvailability]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortCheckVolumeControlAvailability();
            }
        });
        map.put(CCRequestsEnum.IS_FOREGROUND_APP, new RequestHandler<IsForegroundAppResult>() { // from class: cerence.ark.assistant.controller.domain.cc.AbsCCDomain.16
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsForegroundAppResult> iResponseSender) {
                try {
                    AbsCCDomain.this.isForegroundApp(IsForegroundAppParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsCCDomain.TAG, "[CC -> isForegroundApp]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsCCDomain.this.abortIsForegroundApp();
            }
        });
    }
}
