package cerence.ark.assistant.controller.domain.system;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.system.param.CheckPermissionParam;
import cerence.ark.assistant.controller.domain.system.param.GetMediaStateBeforeConversationParam;
import cerence.ark.assistant.controller.domain.system.result.CheckPermissionResult;
import cerence.ark.assistant.controller.domain.system.result.GetActiveMediaTypeBeforeConversationResult;
import cerence.ark.assistant.controller.domain.system.result.GetForegroundMediaTypeResult;
import cerence.ark.assistant.controller.domain.system.result.GetMediaStateBeforeConversationResult;
import cerence.ark.assistant.controller.domain.system.result.GetNetworkStatusResult;
import cerence.ark.assistant.controller.domain.system.result.IsBluetoothHeadsetConnectedResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsSystemDomain extends Domain {
    private static final String TAG = "AbsSystemDomain";

    protected boolean abortCheckPermission() {
        return true;
    }

    protected boolean abortGetActiveMediaTypeBeforeConversation() {
        return true;
    }

    protected boolean abortGetForegroundMediaType() {
        return true;
    }

    protected boolean abortGetMediaStateBeforeConversation() {
        return true;
    }

    protected boolean abortGetNetworkStatus() {
        return true;
    }

    protected boolean abortIsBluetoothHeadsetConnected() {
        return true;
    }

    protected boolean abortReleaseAudioFocus() {
        return true;
    }

    protected boolean abortRequestAudioFocus() {
        return true;
    }

    protected abstract void checkPermission(CheckPermissionParam checkPermissionParam, IResponseSender<CheckPermissionResult> iResponseSender);

    protected abstract void getActiveMediaTypeBeforeConversation(IResponseSender<GetActiveMediaTypeBeforeConversationResult> iResponseSender);

    protected abstract void getForegroundMediaType(IResponseSender<GetForegroundMediaTypeResult> iResponseSender);

    protected abstract void getMediaStateBeforeConversation(GetMediaStateBeforeConversationParam getMediaStateBeforeConversationParam, IResponseSender<GetMediaStateBeforeConversationResult> iResponseSender);

    protected abstract void getNetworkStatus(IResponseSender<GetNetworkStatusResult> iResponseSender);

    protected abstract void isBluetoothHeadsetConnected(IResponseSender<IsBluetoothHeadsetConnectedResult> iResponseSender);

    protected abstract void releaseAudioFocus(IResponseSender<BaseResult> iResponseSender);

    protected abstract void requestAudioFocus(IResponseSender<BaseResult> iResponseSender);

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
        map.put(SystemRequestsEnum.CHECK_PERMISSION, new RequestHandler<CheckPermissionResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckPermissionResult> iResponseSender) {
                try {
                    AbsSystemDomain.this.checkPermission(CheckPermissionParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsSystemDomain.TAG, "[System -> checkPermission]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortCheckPermission();
            }
        });
        map.put(SystemRequestsEnum.REQUEST_AUDIO_FOCUS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsSystemDomain.this.requestAudioFocus(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortRequestAudioFocus();
            }
        });
        map.put(SystemRequestsEnum.RELEASE_AUDIO_FOCUS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsSystemDomain.this.releaseAudioFocus(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortReleaseAudioFocus();
            }
        });
        map.put(SystemRequestsEnum.GET_MEDIA_STATE_BEFORE_CONVERSATION, new RequestHandler<GetMediaStateBeforeConversationResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetMediaStateBeforeConversationResult> iResponseSender) {
                try {
                    AbsSystemDomain.this.getMediaStateBeforeConversation(GetMediaStateBeforeConversationParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsSystemDomain.TAG, "[System -> getMediaStateBeforeConversation]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortGetMediaStateBeforeConversation();
            }
        });
        map.put(SystemRequestsEnum.GET_ACTIVE_MEDIA_TYPE_BEFORE_CONVERSATION, new RequestHandler<GetActiveMediaTypeBeforeConversationResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetActiveMediaTypeBeforeConversationResult> iResponseSender) {
                AbsSystemDomain.this.getActiveMediaTypeBeforeConversation(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortGetActiveMediaTypeBeforeConversation();
            }
        });
        map.put(SystemRequestsEnum.GET_FOREGROUND_MEDIA_TYPE, new RequestHandler<GetForegroundMediaTypeResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetForegroundMediaTypeResult> iResponseSender) {
                AbsSystemDomain.this.getForegroundMediaType(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortGetForegroundMediaType();
            }
        });
        map.put(SystemRequestsEnum.IS_BLUETOOTH_HEADSET_CONNECTED, new RequestHandler<IsBluetoothHeadsetConnectedResult>() { // from class: cerence.ark.assistant.controller.domain.system.AbsSystemDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsBluetoothHeadsetConnectedResult> iResponseSender) {
                AbsSystemDomain.this.isBluetoothHeadsetConnected(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsSystemDomain.this.abortIsBluetoothHeadsetConnected();
            }
        });
    }
}
