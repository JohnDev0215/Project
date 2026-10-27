package cerence.ark.assistant.controller.domain.system;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.system.param.CheckShortcutAvailableParam;
import cerence.ark.assistant.controller.domain.system.param.GetEnabledAudioZonesParam;
import cerence.ark.assistant.controller.domain.system.param.GetExternalVoiceAssistantStatusParam;
import cerence.ark.assistant.controller.domain.system.param.HideScreenParam;
import cerence.ark.assistant.controller.domain.system.param.ShowScreenParam;
import cerence.ark.assistant.controller.domain.system.result.CheckShortcutAvailableResult;
import cerence.ark.assistant.controller.domain.system.result.GetEnabledAudioZonesResult;
import cerence.ark.assistant.controller.domain.system.result.GetExternalVoiceAssistantStatusResult;
import cerence.ark.assistant.controller.domain.system.result.GetNetworkStatusResult;
import cerence.ark.assistant.controller.domain.system.result.QueryHUForbidStatusResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsSystemDomain extends Domain {
    private static final String TAG = "AbsSystemDomain";

    protected boolean abortCancel() {
        return true;
    }

    protected boolean abortCheckShortcutAvailable() {
        return true;
    }

    protected boolean abortGetEnabledAudioZones() {
        return true;
    }

    protected boolean abortGetExternalVoiceAssistantStatus() {
        return true;
    }

    protected boolean abortGetNetworkStatus() {
        return true;
    }

    protected boolean abortHideScreen() {
        return true;
    }

    protected boolean abortQueryHUForbidStatus() {
        return true;
    }

    protected boolean abortReleaseAudioFocus() {
        return true;
    }

    protected boolean abortRequestAudioFocus() {
        return true;
    }

    protected boolean abortShowScreen() {
        return true;
    }

    protected abstract void cancel(IResponseSender<BaseResult> iResponseSender);

    protected abstract void checkShortcutAvailable(CheckShortcutAvailableParam checkShortcutAvailableParam, IResponseSender<CheckShortcutAvailableResult> iResponseSender);

    protected abstract void getEnabledAudioZones(GetEnabledAudioZonesParam getEnabledAudioZonesParam, IResponseSender<GetEnabledAudioZonesResult> iResponseSender);

    protected abstract void getExternalVoiceAssistantStatus(GetExternalVoiceAssistantStatusParam getExternalVoiceAssistantStatusParam, IResponseSender<GetExternalVoiceAssistantStatusResult> iResponseSender);

    protected abstract void getNetworkStatus(IResponseSender<GetNetworkStatusResult> iResponseSender);

    protected abstract void hideScreen(HideScreenParam hideScreenParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void queryHUForbidStatus(IResponseSender<QueryHUForbidStatusResult> iResponseSender);

    protected abstract void releaseAudioFocus(IResponseSender<BaseResult> iResponseSender);

    protected abstract void requestAudioFocus(IResponseSender<BaseResult> iResponseSender);

    protected abstract void showScreen(ShowScreenParam showScreenParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsSystemDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(SystemRequestsEnum.GET_NETWORK_STATUS, new RequestHandler<GetNetworkStatusResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetNetworkStatusResult> iResponseSender) {
                AbsSystemDomain.this.getNetworkStatus(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortGetNetworkStatus();
            }
        });
        map.put(SystemRequestsEnum.REQUEST_AUDIO_FOCUS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsSystemDomain.this.requestAudioFocus(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortRequestAudioFocus();
            }
        });
        map.put(SystemRequestsEnum.RELEASE_AUDIO_FOCUS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsSystemDomain.this.releaseAudioFocus(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortReleaseAudioFocus();
            }
        });
        map.put(SystemRequestsEnum.CHECK_SHORTCUT_AVAILABLE, new RequestHandler<CheckShortcutAvailableResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckShortcutAvailableResult> iResponseSender) {
                try {
                    AbsSystemDomain.this.checkShortcutAvailable(CheckShortcutAvailableParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsSystemDomain.TAG, "[System -> checkShortcutAvailable]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortCheckShortcutAvailable();
            }
        });
        map.put(SystemRequestsEnum.CANCEL, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsSystemDomain.this.cancel(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortCancel();
            }
        });
        map.put(SystemRequestsEnum.SHOW_SCREEN, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsSystemDomain.this.showScreen(ShowScreenParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsSystemDomain.TAG, "[System -> showScreen]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortShowScreen();
            }
        });
        map.put(SystemRequestsEnum.HIDE_SCREEN, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsSystemDomain.this.hideScreen(HideScreenParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsSystemDomain.TAG, "[System -> hideScreen]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortHideScreen();
            }
        });
        map.put(SystemRequestsEnum.QUERY_HUFORBID_STATUS, new RequestHandler<QueryHUForbidStatusResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryHUForbidStatusResult> iResponseSender) {
                AbsSystemDomain.this.queryHUForbidStatus(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortQueryHUForbidStatus();
            }
        });
        map.put(SystemRequestsEnum.GET_ENABLED_AUDIO_ZONES, new RequestHandler<GetEnabledAudioZonesResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetEnabledAudioZonesResult> iResponseSender) {
                try {
                    AbsSystemDomain.this.getEnabledAudioZones(GetEnabledAudioZonesParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsSystemDomain.TAG, "[System -> getEnabledAudioZones]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortGetEnabledAudioZones();
            }
        });
        map.put(SystemRequestsEnum.GET_EXTERNAL_VOICE_ASSISTANT_STATUS, new RequestHandler<GetExternalVoiceAssistantStatusResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.10
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetExternalVoiceAssistantStatusResult> iResponseSender) {
                try {
                    AbsSystemDomain.this.getExternalVoiceAssistantStatus(GetExternalVoiceAssistantStatusParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsSystemDomain.TAG, "[System -> getExternalVoiceAssistantStatus]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortGetExternalVoiceAssistantStatus();
            }
        });
    }
}
