package cerence.ark.assistant.controller.domain.phone;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface PhoneRequestsEnum {
    public static final String CALL_OPERATION = "callOperation";
    public static final String GET_ALL_CONTACTS = "getAllContacts";
    public static final String GET_CALL_LOG = "getCallLog";
    public static final String GET_EMERGENCY_NUMBER = "getEmergencyNumber";
    public static final String GET_PHONE_NUMBER = "getPhoneNumber";
    public static final String GET_SERVICE_NUMBER = "getServiceNumber";
    public static final String IS_EMERGENCY_NUMBER = "isEmergencyNumber";
    public static final String IS_PHONE_CONNECTED = "isPhoneConnected";
    public static final String IS_SYNCHRONIZING_CONTACT = "isSynchronizingContact";
    public static final String MAKE_CALL = "makeCall";
    public static final String NEED_USER_CONFIRMATION = "needUserConfirmation";
}
