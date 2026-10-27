package cerence.ark.assistant.controller.domain.dashcam;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.dashcam.param.CheckRecordingAvailabilityParam;
import cerence.ark.assistant.controller.domain.dashcam.param.ControlADASParam;
import cerence.ark.assistant.controller.domain.dashcam.param.ControlEmergencyRecordingParam;
import cerence.ark.assistant.controller.domain.dashcam.param.ControlPulloverRecordingParam;
import cerence.ark.assistant.controller.domain.dashcam.param.ManageRecordingsParam;
import cerence.ark.assistant.controller.domain.dashcam.result.CheckConnectionStatusResult;
import cerence.ark.assistant.controller.domain.dashcam.result.CheckRecordingAvailabilityResult;
import cerence.ark.assistant.controller.domain.dashcam.result.CheckStatusResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsDashCamDomain extends Domain {
    private static final String TAG = "AbsDashCamDomain";

    protected boolean abortCheckConnectionStatus() {
        return true;
    }

    protected boolean abortCheckRecordingAvailability() {
        return true;
    }

    protected boolean abortCheckStatus() {
        return true;
    }

    protected boolean abortControlADAS() {
        return true;
    }

    protected boolean abortControlEmergencyRecording() {
        return true;
    }

    protected boolean abortControlPulloverRecording() {
        return true;
    }

    protected boolean abortManageRecordings() {
        return true;
    }

    protected boolean abortTakePhoto() {
        return true;
    }

    protected abstract void checkConnectionStatus(IResponseSender<CheckConnectionStatusResult> iResponseSender);

    protected abstract void checkRecordingAvailability(CheckRecordingAvailabilityParam checkRecordingAvailabilityParam, IResponseSender<CheckRecordingAvailabilityResult> iResponseSender);

    protected abstract void checkStatus(IResponseSender<CheckStatusResult> iResponseSender);

    protected abstract void controlADAS(ControlADASParam controlADASParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlEmergencyRecording(ControlEmergencyRecordingParam controlEmergencyRecordingParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlPulloverRecording(ControlPulloverRecordingParam controlPulloverRecordingParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void manageRecordings(ManageRecordingsParam manageRecordingsParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void takePhoto(IResponseSender<BaseResult> iResponseSender);

    protected AbsDashCamDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(DashCamRequestsEnum.CHECK_STATUS, new RequestHandler<CheckStatusResult>() { // from class: cerence.ark.assistant.controller.domain.dashcam.AbsDashCamDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckStatusResult> iResponseSender) {
                AbsDashCamDomain.this.checkStatus(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsDashCamDomain.this.abortCheckStatus();
            }
        });
        map.put(DashCamRequestsEnum.CHECK_CONNECTION_STATUS, new RequestHandler<CheckConnectionStatusResult>() { // from class: cerence.ark.assistant.controller.domain.dashcam.AbsDashCamDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckConnectionStatusResult> iResponseSender) {
                AbsDashCamDomain.this.checkConnectionStatus(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsDashCamDomain.this.abortCheckConnectionStatus();
            }
        });
        map.put(DashCamRequestsEnum.TAKE_PHOTO, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.dashcam.AbsDashCamDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDashCamDomain.this.takePhoto(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsDashCamDomain.this.abortTakePhoto();
            }
        });
        map.put(DashCamRequestsEnum.CONTROL_EMERGENCY_RECORDING, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.dashcam.AbsDashCamDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDashCamDomain.this.controlEmergencyRecording(ControlEmergencyRecordingParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDashCamDomain.TAG, "[DashCam -> controlEmergencyRecording]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsDashCamDomain.this.abortControlEmergencyRecording();
            }
        });
        map.put(DashCamRequestsEnum.CHECK_RECORDING_AVAILABILITY, new RequestHandler<CheckRecordingAvailabilityResult>() { // from class: cerence.ark.assistant.controller.domain.dashcam.AbsDashCamDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckRecordingAvailabilityResult> iResponseSender) {
                try {
                    AbsDashCamDomain.this.checkRecordingAvailability(CheckRecordingAvailabilityParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDashCamDomain.TAG, "[DashCam -> checkRecordingAvailability]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsDashCamDomain.this.abortCheckRecordingAvailability();
            }
        });
        map.put(DashCamRequestsEnum.CONTROL_ADAS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.dashcam.AbsDashCamDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDashCamDomain.this.controlADAS(ControlADASParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDashCamDomain.TAG, "[DashCam -> controlADAS]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsDashCamDomain.this.abortControlADAS();
            }
        });
        map.put(DashCamRequestsEnum.CONTROL_PULLOVER_RECORDING, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.dashcam.AbsDashCamDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDashCamDomain.this.controlPulloverRecording(ControlPulloverRecordingParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDashCamDomain.TAG, "[DashCam -> controlPulloverRecording]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsDashCamDomain.this.abortControlPulloverRecording();
            }
        });
        map.put(DashCamRequestsEnum.MANAGE_RECORDINGS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.dashcam.AbsDashCamDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDashCamDomain.this.manageRecordings(ManageRecordingsParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDashCamDomain.TAG, "[DashCam -> manageRecordings]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsDashCamDomain.this.abortManageRecordings();
            }
        });
    }
}
