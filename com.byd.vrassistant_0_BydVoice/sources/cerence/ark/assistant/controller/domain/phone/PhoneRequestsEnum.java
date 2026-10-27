package cerence.ark.assistant.controller.domain.phone;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface PhoneRequestsEnum {
    public static final String CALL_OPERATION = "callOperation";
    public static final String CHECK_PHONE_STATUS = "checkPhoneStatus";
    public static final String GET_CALL_LOG = "getCallLog";
    public static final String GET_CONTACTS = "getContacts";
    public static final String GET_PHONE_NUMBER = "getPhoneNumber";
    public static final String IS_CONTACT_LIST_EMPTY = "isContactListEmpty";
    public static final String MAKE_CALL = "makeCall";
}
