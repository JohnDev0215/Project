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
import cerence.ark.assistant.controller.domain.phone.param.GetContactsParam;
import cerence.ark.assistant.controller.domain.phone.param.GetPhoneNumberParam;
import cerence.ark.assistant.controller.domain.phone.param.MakeCallParam;
import cerence.ark.assistant.controller.domain.phone.result.CheckPhoneStatusResult;
import cerence.ark.assistant.controller.domain.phone.result.GetCallLogResult;
import cerence.ark.assistant.controller.domain.phone.result.GetContactsResult;
import cerence.ark.assistant.controller.domain.phone.result.GetPhoneNumberResult;
import cerence.ark.assistant.controller.domain.phone.result.IsContactListEmptyResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsPhoneDomain extends Domain {
    private static final String TAG = "AbsPhoneDomain";

    protected boolean abortCallOperation() {
        return true;
    }

    protected boolean abortCheckPhoneStatus() {
        return true;
    }

    protected boolean abortGetCallLog() {
        return true;
    }

    protected boolean abortGetContacts() {
        return true;
    }

    protected boolean abortGetPhoneNumber() {
        return true;
    }

    protected boolean abortIsContactListEmpty() {
        return true;
    }

    protected boolean abortMakeCall() {
        return true;
    }

    protected abstract void callOperation(CallOperationParam callOperationParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void checkPhoneStatus(IResponseSender<CheckPhoneStatusResult> iResponseSender);

    protected abstract void getCallLog(GetCallLogParam getCallLogParam, IResponseSender<GetCallLogResult> iResponseSender);

    protected abstract void getContacts(GetContactsParam getContactsParam, IResponseSender<GetContactsResult> iResponseSender);

    protected abstract void getPhoneNumber(GetPhoneNumberParam getPhoneNumberParam, IResponseSender<GetPhoneNumberResult> iResponseSender);

    protected abstract void isContactListEmpty(IResponseSender<IsContactListEmptyResult> iResponseSender);

    protected abstract void makeCall(MakeCallParam makeCallParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsPhoneDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(PhoneRequestsEnum.CHECK_PHONE_STATUS, new RequestHandler<CheckPhoneStatusResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckPhoneStatusResult> iResponseSender) {
                AbsPhoneDomain.this.checkPhoneStatus(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortCheckPhoneStatus();
            }
        });
        map.put(PhoneRequestsEnum.IS_CONTACT_LIST_EMPTY, new RequestHandler<IsContactListEmptyResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsContactListEmptyResult> iResponseSender) {
                AbsPhoneDomain.this.isContactListEmpty(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortIsContactListEmpty();
            }
        });
        map.put(PhoneRequestsEnum.GET_CONTACTS, new RequestHandler<GetContactsResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetContactsResult> iResponseSender) {
                try {
                    AbsPhoneDomain.this.getContacts(GetContactsParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsPhoneDomain.TAG, "[Phone -> getContacts]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsPhoneDomain.this.abortGetContacts();
            }
        });
        map.put(PhoneRequestsEnum.GET_PHONE_NUMBER, new RequestHandler<GetPhoneNumberResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.4
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
        map.put(PhoneRequestsEnum.CALL_OPERATION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.5
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
        map.put(PhoneRequestsEnum.MAKE_CALL, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
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
        map.put(PhoneRequestsEnum.GET_CALL_LOG, new RequestHandler<GetCallLogResult>() { // from class: cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain.7
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
    }
}
