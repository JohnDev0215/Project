package cerence.ark.assistant.controller.enumeration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface FunctionAvailabilityEnum {
    public static final String AVAILABLE = "AVAILABLE";
    public static final String AVAILABLE_BY_MANUAL_ONLY = "AVAILABLE_BY_MANUAL_ONLY";
    public static final String UNAVAILABLE = "UNAVAILABLE";
}
