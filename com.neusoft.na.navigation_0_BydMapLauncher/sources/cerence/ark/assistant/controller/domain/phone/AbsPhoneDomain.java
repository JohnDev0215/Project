package cerence.ark.assistant.controller.domain.phone;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.phone.param.CallOperationParam;
import cerence.ark.assistant.controller.domain.phone.param.GetCallLogParam;
import cerence.ark.assistant.controller.domain.phone.param.GetEmergencyNumberParam;
import cerence.ark.assistant.controller.domain.phone.param.GetPhoneNumberParam;
import cerence.ark.assistant.controller.domain.phone.param.GetServiceNumberParam;
import cerence.ark.assistant.controller.domain.phone.param.IsEmergencyNumberParam;
import cerence.ark.assistant.controller.domain.phone.param.MakeCallParam;
import cerence.ark.assistant.controller.domain.phone.result.GetAllContactsResult;
import cerence.ark.assistant.controller.domain.phone.result.GetCallLogResult;
import cerence.ark.assistant.controller.domain.phone.result.GetEmergencyNumberResult;
import cerence.ark.assistant.controller.domain.phone.result.GetPhoneNumberResult;
import cerence.ark.assistant.controller.domain.phone.result.GetServiceNumberResult;
import cerence.ark.assistant.controller.domain.phone.result.IsEmergencyNumberResult;
import cerence.ark.assistant.controller.domain.phone.result.IsPhoneConnectedResult;
import cerence.ark.assistant.controller.domain.phone.result.IsSynchronizingContactResult;
import cerence.ark.assistant.controller.domain.phone.result.MakeCallResult;
import cerence.ark.assistant.controller.domain.phone.result.NeedUserConfirmationResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsPhoneDomain extends Domain {
    private static final String TAG = "AbsPhoneDomain";

    protected boolean abortCallOperation() {
        return true;
    }

    protected boolean abortGetAllContacts() {
        return true;
    }

    protected boolean abortGetCallLog() {
        return true;
    }

    protected boolean abortGetEmergencyNumber() {
        return true;
    }

    protected boolean abortGetPhoneNumber() {
        return true;
    }

    protected boolean abortGetServiceNumber() {
        return true;
    }

    protected boolean abortIsEmergencyNumber() {
        return true;
    }

    protected boolean abortIsPhoneConnected() {
        return true;
    }

    protected boolean abortIsSynchronizingContact() {
        return true;
    }

    protected boolean abortMakeCall() {
        return true;
    }

    protected boolean abortNeedUserConfirmation() {
        return true;
    }

    protected abstract void callOperation(CallOperationParam callOperationParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void getAllContacts(IResponseSender<GetAllContactsResult> iResponseSender);

    protected abstract void getCallLog(GetCallLogParam getCallLogParam, IResponseSender<GetCallLogResult> iResponseSender);

    protected abstract void getEmergencyNumber(GetEmergencyNumberParam getEmergencyNumberParam, IResponseSender<GetEmergencyNumberResult> iResponseSender);

    protected abstract void getPhoneNumber(GetPhoneNumberParam getPhoneNumberParam, IResponseSender<GetPhoneNumberResult> iResponseSender);

    protected abstract void getServiceNumber(GetServiceNumberParam getServiceNumberParam, IResponseSender<GetServiceNumberResult> iResponseSender);

    protected abstract void isEmergencyNumber(IsEmergencyNumberParam isEmergencyNumberParam, IResponseSender<IsEmergencyNumberResult> iResponseSender);

    protected abstract void isPhoneConnected(IResponseSender<IsPhoneConnectedResult> iResponseSender);

    protected abstract void isSynchronizingContact(IResponseSender<IsSynchronizingContactResult> iResponseSender);

    protected abstract void makeCall(MakeCallParam makeCallParam, IResponseSender<MakeCallResult> iResponseSender);

    protected abstract void needUserConfirmation(IResponseSender<NeedUserConfirmationResult> iResponseSender);

    protected AbsPhoneDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(PhoneRequestsEnum.CALL_OPERATION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsPhoneDomain.this.callOperation(CallOperationParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPhoneDomain.TAG, "[Phone -> callOperation]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortCallOperation();
            }
        });
        map.put(PhoneRequestsEnum.GET_CALL_LOG, new RequestHandler<GetCallLogResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetCallLogResult> iResponseSender) {
                try {
                    AbsPhoneDomain.this.getCallLog(GetCallLogParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPhoneDomain.TAG, "[Phone -> getCallLog]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortGetCallLog();
            }
        });
        map.put(PhoneRequestsEnum.GET_ALL_CONTACTS, new RequestHandler<GetAllContactsResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetAllContactsResult> iResponseSender) {
                AbsPhoneDomain.this.getAllContacts(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortGetAllContacts();
            }
        });
        map.put(PhoneRequestsEnum.IS_PHONE_CONNECTED, new RequestHandler<IsPhoneConnectedResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsPhoneConnectedResult> iResponseSender) {
                AbsPhoneDomain.this.isPhoneConnected(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortIsPhoneConnected();
            }
        });
        map.put(PhoneRequestsEnum.IS_SYNCHRONIZING_CONTACT, new RequestHandler<IsSynchronizingContactResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsSynchronizingContactResult> iResponseSender) {
                AbsPhoneDomain.this.isSynchronizingContact(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortIsSynchronizingContact();
            }
        });
        map.put(PhoneRequestsEnum.MAKE_CALL, new RequestHandler<MakeCallResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<MakeCallResult> iResponseSender) {
                try {
                    AbsPhoneDomain.this.makeCall(MakeCallParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPhoneDomain.TAG, "[Phone -> makeCall]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortMakeCall();
            }
        });
        map.put(PhoneRequestsEnum.GET_PHONE_NUMBER, new RequestHandler<GetPhoneNumberResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetPhoneNumberResult> iResponseSender) {
                try {
                    AbsPhoneDomain.this.getPhoneNumber(GetPhoneNumberParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPhoneDomain.TAG, "[Phone -> getPhoneNumber]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortGetPhoneNumber();
            }
        });
        map.put(PhoneRequestsEnum.GET_SERVICE_NUMBER, new RequestHandler<GetServiceNumberResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetServiceNumberResult> iResponseSender) {
                try {
                    AbsPhoneDomain.this.getServiceNumber(GetServiceNumberParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPhoneDomain.TAG, "[Phone -> getServiceNumber]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortGetServiceNumber();
            }
        });
        map.put(PhoneRequestsEnum.GET_EMERGENCY_NUMBER, new RequestHandler<GetEmergencyNumberResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetEmergencyNumberResult> iResponseSender) {
                try {
                    AbsPhoneDomain.this.getEmergencyNumber(GetEmergencyNumberParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPhoneDomain.TAG, "[Phone -> getEmergencyNumber]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortGetEmergencyNumber();
            }
        });
        map.put(PhoneRequestsEnum.IS_EMERGENCY_NUMBER, new RequestHandler<IsEmergencyNumberResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.10
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsEmergencyNumberResult> iResponseSender) {
                try {
                    AbsPhoneDomain.this.isEmergencyNumber(IsEmergencyNumberParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPhoneDomain.TAG, "[Phone -> isEmergencyNumber]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortIsEmergencyNumber();
            }
        });
        map.put(PhoneRequestsEnum.NEED_USER_CONFIRMATION, new RequestHandler<NeedUserConfirmationResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.11
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<NeedUserConfirmationResult> iResponseSender) {
                AbsPhoneDomain.this.needUserConfirmation(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortNeedUserConfirmation();
            }
        });
    }
}
