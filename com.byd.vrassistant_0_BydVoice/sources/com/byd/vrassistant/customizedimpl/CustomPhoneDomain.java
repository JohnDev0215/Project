package com.byd.vrassistant.customizedimpl;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.api.dynamic.Contact;
import cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain;
import cerence.ark.assistant.controller.domain.phone.param.CallOperationParam;
import cerence.ark.assistant.controller.domain.phone.param.GetCallLogParam;
import cerence.ark.assistant.controller.domain.phone.param.GetContactsParam;
import cerence.ark.assistant.controller.domain.phone.param.GetPhoneNumberParam;
import cerence.ark.assistant.controller.domain.phone.result.CheckPhoneStatusResult;
import cerence.ark.assistant.controller.domain.phone.result.GetCallLogResult;
import cerence.ark.assistant.controller.domain.phone.result.GetContactsResult;
import cerence.ark.assistant.controller.domain.phone.result.GetPhoneNumberResult;
import cerence.ark.assistant.controller.domain.phone.result.IsContactListEmptyResult;
import cerence.ark.assistant.controller.enumeration.PhoneCallActionEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.struct.CallLogInfo;
import cerence.ark.assistant.controller.struct.ContactInfo;
import cerence.ark.assistant.controller.struct.PhoneNumberInfo;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.utils.RLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CustomPhoneDomain extends AbsPhoneDomain {
    private static final String TAG = "NEU_PHONEDOMAIN";
    private PhoneHelper mPhoneHelper;

    protected CustomPhoneDomain(Dhi dhi) {
        super(dhi);
        this.mPhoneHelper = null;
        RLog.i(TAG, "CustomPhoneDomain construct");
        this.mPhoneHelper = PhoneHelper.getInstance();
    }

    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    protected void checkPhoneStatus(IResponseSender<CheckPhoneStatusResult> iResponseSender) {
        try {
            RLog.i(TAG, "request checkPhoneStatus json = ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【checkPhoneStatus】");
            String strCheckPhoneStatus = this.mPhoneHelper.checkPhoneStatus();
            CheckPhoneStatusResult checkPhoneStatusResult = new CheckPhoneStatusResult();
            checkPhoneStatusResult.setPhoneStatus(strCheckPhoneStatus);
            iResponseSender.send(new Response("OK", checkPhoneStatusResult));
            RLog.i(TAG, "request checkPhoneStatus  OK ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【checkPhoneStatus】,result【OK】");
        } catch (Exception e) {
            RLog.e(TAG, "checkPhoneStatus ERROR", e);
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【checkPhoneStatus】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    protected void isContactListEmpty(IResponseSender<IsContactListEmptyResult> iResponseSender) {
        try {
            RLog.i(TAG, "request isContactListEmpty");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【isContactListEmpty】");
            List<Contact> contacts = this.mPhoneHelper.readContacts(null);
            IsContactListEmptyResult isContactListEmptyResult = new IsContactListEmptyResult();
            if (contacts == null) {
                RLog.i(TAG, "list<contact> is BULL");
                isContactListEmptyResult.setIsEmpty(true);
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, isContactListEmptyResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【isContactListEmpty】,result【ERROR】");
                return;
            }
            if (contacts.size() == 0) {
                RLog.i(TAG, "list<contact> is 0");
                isContactListEmptyResult.setIsEmpty(true);
                iResponseSender.send(new Response("OK", isContactListEmptyResult));
            } else {
                RLog.i(TAG, "list<contact> ok");
                isContactListEmptyResult.setIsEmpty(false);
                iResponseSender.send(new Response("OK", isContactListEmptyResult));
            }
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【isContactListEmpty】,result【OK】");
        } catch (Exception e) {
            RLog.e(TAG, "isContactListEmpty ERROR", e);
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【isContactListEmpty】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    protected void getContacts(GetContactsParam getContactsParam, IResponseSender<GetContactsResult> iResponseSender) {
        try {
            RLog.i(TAG, "request getContacts");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【getContacts】");
            List<Long> contactIds = getContactsParam.getContactIds();
            ArrayList arrayList = new ArrayList();
            if (contactIds.size() > 0) {
                for (int i = 0; i < contactIds.size(); i++) {
                    ContactInfo contactWithId = this.mPhoneHelper.readContactWithId(contactIds.get(i).longValue());
                    if (contactWithId != null) {
                        arrayList.add(contactWithId);
                    }
                }
            }
            GetContactsResult getContactsResult = new GetContactsResult();
            getContactsResult.setContactList(arrayList);
            iResponseSender.send(new Response("OK", getContactsResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【getContacts】,result【OK】");
        } catch (Exception e) {
            RLog.e(TAG, "getContacts ERROR", e);
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【getContacts】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    protected void getPhoneNumber(GetPhoneNumberParam getPhoneNumberParam, IResponseSender<GetPhoneNumberResult> iResponseSender) {
        try {
            RLog.i(TAG, "request getPhoneNumber");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【getPhoneNumber】");
            long contactId = getPhoneNumberParam.getContactId();
            RLog.i(TAG, "contactId :" + contactId);
            List<PhoneNumberInfo> contactPhone = this.mPhoneHelper.readContactPhone(contactId);
            RLog.i(TAG, "user[" + contactId + "] phoneNumber size=" + contactPhone.size());
            for (int size = contactPhone.size(); size > 0; size--) {
                int i = size - 1;
                RLog.i(TAG, "phones.get(i).getNumber() = " + contactPhone.get(i).getNumber());
                boolean zIsEmpty = contactPhone.get(i).getNumber().isEmpty();
                RLog.i(TAG, "empty = " + zIsEmpty);
                if (zIsEmpty) {
                    contactPhone.remove(i);
                }
            }
            GetPhoneNumberResult getPhoneNumberResult = new GetPhoneNumberResult();
            if (!contactPhone.isEmpty()) {
                RLog.i(TAG, "setNumberList not null ");
                getPhoneNumberResult.setNumberList(contactPhone);
            }
            iResponseSender.send(new Response("OK", getPhoneNumberResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【getPhoneNumber】,result【OK】");
        } catch (Exception e) {
            RLog.e(TAG, "getPhoneNumber ERROR", e);
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【getPhoneNumber】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    protected void callOperation(CallOperationParam callOperationParam, IResponseSender<BaseResult> iResponseSender) {
        Object obj;
        try {
            RLog.i(TAG, "request callOperation =>" + callOperationParam.getActionType());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【callOperation】");
            String message = "";
            if (callOperationParam.getActionType().equals(PhoneCallActionEnum.ANSWER_CALL)) {
                RLog.i(TAG, "PhoneCallAction is ANSWER CALL");
                try {
                    this.mPhoneHelper.acceptCall();
                    obj = "OK";
                } catch (Exception e) {
                    message = e.getMessage();
                    obj = ResultCodeEnum.ERROR;
                }
            } else if (callOperationParam.getActionType().equals(PhoneCallActionEnum.REJECT_INCOMING_CALL)) {
                RLog.i(TAG, "PhoneCallAction is REJECT_INCOMING_CALL");
                try {
                    this.mPhoneHelper.rejectCall();
                    obj = "OK";
                } catch (Exception e2) {
                    message = e2.getMessage();
                    obj = ResultCodeEnum.ERROR;
                }
            } else {
                obj = null;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("result_code", obj);
                jSONObject.put("error_msg", message);
            } catch (JSONException e3) {
                RLog.e(TAG, "callOperation create jsonObject fail", e3);
            }
            iResponseSender.send(new Response("OK", new BaseResult(jSONObject.toString())));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【callOperation】,result【OK】");
        } catch (Exception e4) {
            RLog.e(TAG, "callOperation ERROR", e4);
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【callOperation】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:27|3|4|(1:6)(8:25|8|9|13|23|14|18|29)|12|13|23|14|18|29) */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0054, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0055, code lost:
    
        com.byd.vrassistant.utils.RLog.e(com.byd.vrassistant.customizedimpl.CustomPhoneDomain.TAG, "callOperation create jsonObject fail", r9);
     */
    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void makeCall(cerence.ark.assistant.controller.domain.phone.param.MakeCallParam r9, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r10) {
        /*
            r8 = this;
            java.lang.String r0 = "PhoneNumber is null"
            java.lang.String r1 = "ERROR"
            java.lang.String r2 = "NEU_VRTEST"
            java.lang.String r3 = "NEU_PHONEDOMAIN"
            java.lang.String r4 = "request makeCall"
            com.byd.vrassistant.utils.RLog.i(r3, r4)     // Catch: java.lang.Exception -> L72
            java.lang.String r4 = "request domain【CustomPhoneDomain】,api【makeCall】"
            com.byd.vrassistant.utils.RLog.i(r2, r4)     // Catch: java.lang.Exception -> L72
            java.lang.String r4 = ""
            java.lang.String r5 = r9.getPhoneNumber()     // Catch: java.lang.Exception -> L72
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L72
            r6 = 0
            java.lang.String r7 = "OK"
            if (r5 == 0) goto L2c
            com.byd.vrassistant.utils.RLog.d(r3, r0)     // Catch: java.lang.Exception -> L72
            com.byd.vrassistant.conversation.PhoneHelper r9 = r8.mPhoneHelper     // Catch: java.lang.Exception -> L72
            r9.setCallingState(r6)     // Catch: java.lang.Exception -> L72
            goto L42
        L2c:
            com.byd.vrassistant.conversation.PhoneHelper r0 = r8.mPhoneHelper     // Catch: java.lang.Exception -> L38
            java.lang.String r9 = r9.getPhoneNumber()     // Catch: java.lang.Exception -> L38
            r0.makeCall(r9)     // Catch: java.lang.Exception -> L38
            r0 = r4
            r9 = r7
            goto L43
        L38:
            r9 = move-exception
            java.lang.String r0 = r9.getMessage()     // Catch: java.lang.Exception -> L72
            com.byd.vrassistant.conversation.PhoneHelper r9 = r8.mPhoneHelper     // Catch: java.lang.Exception -> L72
            r9.setCallingState(r6)     // Catch: java.lang.Exception -> L72
        L42:
            r9 = r1
        L43:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Exception -> L72
            r4.<init>()     // Catch: java.lang.Exception -> L72
            java.lang.String r5 = "result_code"
            r4.put(r5, r9)     // Catch: org.json.JSONException -> L54 java.lang.Exception -> L72
            java.lang.String r9 = "error_msg"
            r4.put(r9, r0)     // Catch: org.json.JSONException -> L54 java.lang.Exception -> L72
            goto L5a
        L54:
            r9 = move-exception
            java.lang.String r0 = "callOperation create jsonObject fail"
            com.byd.vrassistant.utils.RLog.e(r3, r0, r9)     // Catch: java.lang.Exception -> L72
        L5a:
            cerence.ark.assistant.api.BaseResult r9 = new cerence.ark.assistant.api.BaseResult     // Catch: java.lang.Exception -> L72
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Exception -> L72
            r9.<init>(r0)     // Catch: java.lang.Exception -> L72
            cerence.ark.assistant.api.Response r0 = new cerence.ark.assistant.api.Response     // Catch: java.lang.Exception -> L72
            r0.<init>(r7, r9)     // Catch: java.lang.Exception -> L72
            r10.send(r0)     // Catch: java.lang.Exception -> L72
            java.lang.String r9 = "request domain【CustomPhoneDomain】,api【makeCall】,result【OK】"
            com.byd.vrassistant.utils.RLog.i(r2, r9)     // Catch: java.lang.Exception -> L72
            goto L87
        L72:
            r9 = move-exception
            java.lang.String r0 = "makeCall ERROR"
            com.byd.vrassistant.utils.RLog.e(r3, r0, r9)
            java.lang.String r9 = "request domain【CustomPhoneDomain】,api【makeCall】,result【ERROR】"
            com.byd.vrassistant.utils.RLog.i(r2, r9)
            cerence.ark.assistant.api.Response r9 = new cerence.ark.assistant.api.Response
            r9.<init>(r1)
            r10.send(r9)
        L87:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.customizedimpl.CustomPhoneDomain.makeCall(cerence.ark.assistant.controller.domain.phone.param.MakeCallParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    protected void getCallLog(GetCallLogParam getCallLogParam, IResponseSender<GetCallLogResult> iResponseSender) {
        try {
            RLog.i(TAG, "request getCallLog");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【getCallLog】,result【ERROR】");
            List<CallLogInfo> callLog = this.mPhoneHelper.getCallLog(getCallLogParam.getCallLogType());
            RLog.i(TAG, "Calllogs size = " + callLog.size());
            GetCallLogResult getCallLogResult = new GetCallLogResult();
            getCallLogResult.setCallLogList(callLog);
            iResponseSender.send(new Response("OK", getCallLogResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【getCallLog】,result【OK】");
        } catch (Exception e) {
            RLog.e(TAG, "getCallLog ERROR", e);
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomPhoneDomain】,api【getCallLog】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
        RLog.i(TAG, "activate");
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
        RLog.i(TAG, "deactivate");
    }
}
