package cerence.ark.assistant.controller.enumeration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface PhoneCallActionEnum {
    public static final String ANSWER_CALL = "ANSWER_CALL";
    public static final String HANGUP_OUTGOING_CALL = "HANGUP_OUTGOING_CALL";
    public static final String IGNORE_INCOMING_CALL = "IGNORE_INCOMING_CALL";
    public static final String REJECT_INCOMING_CALL = "REJECT_INCOMING_CALL";
    public static final String REPLY_CALL_BY_TEXT = "REPLY_CALL_BY_TEXT";
}
