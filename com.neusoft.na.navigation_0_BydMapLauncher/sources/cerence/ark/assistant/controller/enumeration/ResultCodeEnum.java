package cerence.ark.assistant.controller.enumeration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface ResultCodeEnum {
    public static final String ALREADY_EXPECTED = "ALREADY_EXPECTED";
    public static final String ALREADY_MAX = "ALREADY_MAX";
    public static final String ALREADY_MIN = "ALREADY_MIN";
    public static final String ERROR = "ERROR";
    public static final String OK = "OK";
    public static final String OUT_OF_RANGE = "OUT_OF_RANGE";
    public static final String TIMEOUT = "TIMEOUT";
    public static final String UNSUPPORTED = "UNSUPPORTED";
}
