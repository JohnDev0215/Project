package cerence.ark.assistant.controller.enumeration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface PhoneStatusEnum {
    public static final String BLUETOOTH_OFF = "BLUETOOTH_OFF";
    public static final String CONTACT_LOADING_ERROR = "CONTACT_LOADING_ERROR";
    public static final String DISCONNECTED = "DISCONNECTED";
    public static final String LOADING_CONTACTS = "LOADING_CONTACTS";
    public static final String NO_PERMISSION_SYNC_CONTACTS = "NO_PERMISSION_SYNC_CONTACTS";
    public static final String NO_SIGNAL = "NO_SIGNAL";
    public static final String OK = "OK";
    public static final String PHONE_FUNCTION_OFF = "PHONE_FUNCTION_OFF";
    public static final String PHONE_LAUNCH_FAILED = "PHONE_LAUNCH_FAILED";
}
