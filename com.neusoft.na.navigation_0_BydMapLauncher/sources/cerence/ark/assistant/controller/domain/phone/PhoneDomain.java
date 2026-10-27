package cerence.ark.assistant.controller.domain.phone;

import android.app.NotificationManager;
import android.car.Car;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.CancellationSignal;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.api.dynamic.Contact;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.phone.param.CallOperationParam;
import cerence.ark.assistant.controller.domain.phone.param.GetCallLogParam;
import cerence.ark.assistant.controller.domain.phone.param.GetPhoneNumberParam;
import cerence.ark.assistant.controller.domain.phone.param.MakeCallParam;
import cerence.ark.assistant.controller.domain.phone.result.GetAllContactsResult;
import cerence.ark.assistant.controller.domain.phone.result.GetCallLogResult;
import cerence.ark.assistant.controller.domain.phone.result.GetPhoneNumberResult;
import cerence.ark.assistant.controller.domain.phone.result.MakeCallResult;
import cerence.ark.assistant.controller.domain.phone.result.NeedUserConfirmationResult;
import cerence.ark.assistant.controller.enumeration.CallLogTypeEnum;
import cerence.ark.assistant.controller.enumeration.PhoneNumberTypeEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.struct.CallLogInfo;
import cerence.ark.assistant.controller.struct.ContactInfo;
import cerence.ark.assistant.controller.struct.PhoneNumberInfo;
import cerence.ark.assistant.core.phone.PhoneDatabase;
import cerence.ark.assistant.core.phone.data.CallLog;
import cerence.ark.assistant.core.phone.data.PhoneNumber;
import cerence.ark.assistant.library.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PhoneDomain extends AbsPhoneDomain {
    private static final String TAG = "PhoneDomain";
    private boolean isChangeStreamVolume;
    private AudioManager mAudioManager;
    private final Map<String, CallLog.CallLogType> mCallLogTypeMap;
    private CancellationSignal mCancellationSignal;
    private NotificationManager mNotificationManager;
    private final PhoneDatabase mPhoneDatabase;
    private PhoneStateListener mPhoneStateListener;
    private final Map<PhoneNumber.PhoneNumberType, String> mPhoneTypeMap;
    private int mRingerMode;
    private int mStreamVolume;

    protected PhoneDomain(Dhi dhi) {
        super(dhi);
        this.mPhoneDatabase = new PhoneDatabase();
        this.isChangeStreamVolume = false;
        HashMap map = new HashMap(16);
        this.mPhoneTypeMap = map;
        HashMap map2 = new HashMap(16);
        this.mCallLogTypeMap = map2;
        map.put(PhoneNumber.PhoneNumberType.HOME, PhoneNumberTypeEnum.HOME);
        map.put(PhoneNumber.PhoneNumberType.WORK, PhoneNumberTypeEnum.WORK);
        map.put(PhoneNumber.PhoneNumberType.MOBILE, PhoneNumberTypeEnum.MOBILE);
        map.put(PhoneNumber.PhoneNumberType.OTHER, PhoneNumberTypeEnum.OTHER);
        map2.put(CallLogTypeEnum.ALL_CALL_LOG, CallLog.CallLogType.ALL_CALL_LOG);
        map2.put(CallLogTypeEnum.MISSED_CALL_LOG, CallLog.CallLogType.MISSED_CALL_LOG);
        map2.put(CallLogTypeEnum.ANSWERED_CALL_LOG, CallLog.CallLogType.ANSWERED_CALL_LOG);
        map2.put(CallLogTypeEnum.INCOMING_CALL_LOG, CallLog.CallLogType.INCOMING_CALL_LOG);
        map2.put(CallLogTypeEnum.OUTGOING_CALL_LOG, CallLog.CallLogType.OUTGOING_CALL_LOG);
    }

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
        this.mNotificationManager = (NotificationManager) Utils.getContext().getSystemService("notification");
        this.mAudioManager = (AudioManager) Utils.getContext().getSystemService(Car.AUDIO_SERVICE);
        this.mPhoneStateListener = new PhoneStateListener() { // from class: cerence.ark.assistant.controller.domain.phone.PhoneDomain.1
            @Override // android.telephony.PhoneStateListener
            public void onCallStateChanged(int i, String str) {
                Logger.debug(PhoneDomain.TAG, String.format("onCallStateChanged: state = %s, incomingNumber = %s", Integer.valueOf(i), str));
                if (i == 0 && PhoneDomain.this.isChangeStreamVolume) {
                    PhoneDomain.this.isChangeStreamVolume = false;
                    if (PhoneDomain.this.mNotificationManager.isNotificationPolicyAccessGranted()) {
                        PhoneDomain.this.mAudioManager.setRingerMode(PhoneDomain.this.mRingerMode);
                    } else {
                        PhoneDomain.this.mAudioManager.setStreamVolume(2, PhoneDomain.this.mStreamVolume, 0);
                    }
                }
            }
        };
        ((TelephonyManager) Utils.getContext().getSystemService("phone")).listen(this.mPhoneStateListener, 32);
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
        if (this.mPhoneStateListener != null) {
            ((TelephonyManager) Utils.getContext().getSystemService("phone")).listen(this.mPhoneStateListener, 0);
            this.mPhoneStateListener = null;
        }
    }

    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    protected void callOperation(CallOperationParam callOperationParam, IResponseSender<BaseResult> iResponseSender) {
        Response response;
        response = new Response(ResultCodeEnum.OK);
        String actionType = callOperationParam.getActionType();
        actionType.hashCode();
        switch (actionType) {
            case "IGNORE_INCOMING_CALL":
                ignoreCall();
                break;
            case "REPLY_CALL_BY_TEXT":
                if (rejectCall()) {
                    response.setResultCode(ResultCodeEnum.OK);
                    break;
                } else {
                    response.setResultCode(ResultCodeEnum.ERROR);
                    break;
                }
                break;
            case "REJECT_INCOMING_CALL":
            case "HANGUP_OUTGOING_CALL":
                if (!rejectCall()) {
                    response.setResultCode(ResultCodeEnum.ERROR);
                    break;
                }
                break;
            case "ANSWER_CALL":
                if (!acceptCall()) {
                    response.setResultCode(ResultCodeEnum.ERROR);
                    break;
                }
                break;
            default:
                response.setResultCode(ResultCodeEnum.ERROR);
                break;
        }
        iResponseSender.send(response);
    }

    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    protected void getCallLog(GetCallLogParam getCallLogParam, IResponseSender<GetCallLogResult> iResponseSender) {
        GetCallLogResult getCallLogResult = new GetCallLogResult();
        List<CallLog> callLogsByType = this.mPhoneDatabase.getCallLogsByType(Utils.getContext(), this.mCallLogTypeMap.get(getCallLogParam.getCallLogType()), 30);
        ArrayList arrayList = new ArrayList();
        ContactInfo contactInfo = null;
        PhoneNumberInfo phoneNumberInfo = null;
        for (CallLog callLog : callLogsByType) {
            CallLogInfo callLogInfo = new CallLogInfo();
            Contact contact = callLog.getContact();
            PhoneNumber phoneNumber = callLog.getPhoneNumber();
            if (contact != null) {
                contactInfo = new ContactInfo();
                contactInfo.setContactId(contact.getId());
                contactInfo.setContactName(contact.getName());
            }
            if (phoneNumber != null) {
                phoneNumberInfo = new PhoneNumberInfo();
                phoneNumberInfo.setNumber(phoneNumber.getNumber());
                phoneNumberInfo.setNumberType(this.mPhoneTypeMap.get(phoneNumber.getNumberType()));
            }
            callLogInfo.setContact(contactInfo);
            callLogInfo.setPhoneNumber(phoneNumberInfo);
            callLogInfo.setTimestamp(callLog.getTimestamp());
            arrayList.add(callLogInfo);
        }
        getCallLogResult.setCallLogList(arrayList);
        iResponseSender.send(new Response(ResultCodeEnum.OK, getCallLogResult));
    }

    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    protected void getAllContacts(IResponseSender<GetAllContactsResult> iResponseSender) {
        GetAllContactsResult getAllContactsResult = new GetAllContactsResult();
        this.mCancellationSignal = new CancellationSignal();
        List<Contact> contacts = this.mPhoneDatabase.getContacts(Utils.getContext(), this.mCancellationSignal);
        ArrayList arrayList = new ArrayList();
        for (Contact contact : contacts) {
            ContactInfo contactInfo = new ContactInfo();
            contactInfo.setContactId(contact.getId());
            contactInfo.setContactName(contact.getName());
            arrayList.add(contactInfo);
        }
        getAllContactsResult.setContactList(arrayList);
        iResponseSender.send(new Response(ResultCodeEnum.OK, getAllContactsResult));
    }

    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    protected boolean abortGetAllContacts() {
        CancellationSignal cancellationSignal = this.mCancellationSignal;
        if (cancellationSignal == null) {
            return true;
        }
        cancellationSignal.cancel();
        return true;
    }

    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    protected void makeCall(MakeCallParam makeCallParam, IResponseSender<MakeCallResult> iResponseSender) {
        makeCallImpl(makeCallParam.getPhoneNumber());
        MakeCallResult makeCallResult = new MakeCallResult();
        makeCallResult.setNeedUserConfirmation(false);
        iResponseSender.send(new Response(ResultCodeEnum.OK, makeCallResult));
    }

    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    protected void getPhoneNumber(GetPhoneNumberParam getPhoneNumberParam, IResponseSender<GetPhoneNumberResult> iResponseSender) {
        GetPhoneNumberResult getPhoneNumberResult = new GetPhoneNumberResult();
        List<PhoneNumber> phoneNumberListById = this.mPhoneDatabase.getPhoneNumberListById(Utils.getContext(), getPhoneNumberParam.getContactId());
        ArrayList arrayList = new ArrayList();
        for (PhoneNumber phoneNumber : phoneNumberListById) {
            PhoneNumberInfo phoneNumberInfo = new PhoneNumberInfo();
            phoneNumberInfo.setNumber(phoneNumber.getNumber());
            phoneNumberInfo.setNumberType(this.mPhoneTypeMap.get(phoneNumber.getNumberType()));
            arrayList.add(phoneNumberInfo);
        }
        getPhoneNumberResult.setNumberList(arrayList);
        iResponseSender.send(new Response(ResultCodeEnum.OK, getPhoneNumberResult));
    }

    @Override // cerence.ark.assistant.controller.domain.phone.AbsPhoneDomain
    protected void needUserConfirmation(IResponseSender<NeedUserConfirmationResult> iResponseSender) {
        NeedUserConfirmationResult needUserConfirmationResult = new NeedUserConfirmationResult();
        needUserConfirmationResult.setNeedConfirmation(false);
        needUserConfirmationResult.setCallingDelayTime(0);
        iResponseSender.send(new Response(ResultCodeEnum.OK, needUserConfirmationResult));
    }

    private void makeCallImpl(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "makeCall: phone number is empty");
            return;
        }
        if (!Utils.checkSelfPermission("android.permission.CALL_PHONE")) {
            Logger.error(TAG, "makeCall: the android.permission.CALL_PHONE was denied.");
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.CALL");
            intent.addFlags(268435456);
            intent.setData(Uri.parse("tel:" + str));
            Utils.getContext().startActivity(intent);
        } catch (Exception e) {
            Logger.error(TAG, "makeCall: exception", e);
        }
    }

    private boolean acceptCall() {
        if (!Utils.checkSelfPermission("android.permission.ANSWER_PHONE_CALLS")) {
            Logger.error(TAG, "acceptCall: the android.permission.ANSWER_PHONE_CALLS was denied.");
            return false;
        }
        try {
            ((TelecomManager) Utils.getContext().getSystemService("telecom")).acceptRingingCall();
            return true;
        } catch (Exception e) {
            Logger.error(TAG, "acceptCall: exception", e);
            return false;
        }
    }

    private boolean rejectCall() {
        if (!Utils.checkSelfPermission("android.permission.ANSWER_PHONE_CALLS")) {
            Logger.error(TAG, "rejectCall: the android.permission.ANSWER_PHONE_CALLS was denied.");
            return false;
        }
        try {
            return ((TelecomManager) Utils.getContext().getSystemService("telecom")).endCall();
        } catch (Exception e) {
            Logger.error(TAG, "rejectCall: exception", e);
            return false;
        }
    }

    private void ignoreCall() {
        this.isChangeStreamVolume = true;
        int ringerMode = this.mAudioManager.getRingerMode();
        this.mRingerMode = ringerMode;
        if (ringerMode != 0) {
            if (this.mNotificationManager.isNotificationPolicyAccessGranted()) {
                this.mAudioManager.setRingerMode(0);
            } else {
                this.mStreamVolume = this.mAudioManager.getStreamVolume(2);
                this.mAudioManager.setStreamVolume(2, 1, 0);
            }
        }
    }
}
