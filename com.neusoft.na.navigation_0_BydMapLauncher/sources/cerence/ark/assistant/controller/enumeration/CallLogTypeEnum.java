package cerence.ark.assistant.controller.enumeration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface CallLogTypeEnum {
    public static final String ALL_CALL_LOG = "ALL_CALL_LOG";
    public static final String ANSWERED_CALL_LOG = "ANSWERED_CALL_LOG";
    public static final String INCOMING_CALL_LOG = "INCOMING_CALL_LOG";
    public static final String MISSED_CALL_LOG = "MISSED_CALL_LOG";
    public static final String OUTGOING_CALL_LOG = "OUTGOING_CALL_LOG";
}
