package cerence.ark.assistant.controller.enumeration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface IntentionTypeEnum {
    public static final String CALLBACK = "CALLBACK";
    public static final String CALLING = "CALLING";
    public static final String REDIAL = "REDIAL";
    public static final String SHOW_CALL_LOG = "SHOW_CALL_LOG";
    public static final String SHOW_PHONE_BOOK = "SHOW_PHONE_BOOK";
}
