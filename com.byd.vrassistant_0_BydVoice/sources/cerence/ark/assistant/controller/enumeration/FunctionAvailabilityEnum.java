package cerence.ark.assistant.controller.enumeration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface FunctionAvailabilityEnum {
    public static final String ALREADY_EXPECTED = "ALREADY_EXPECTED";
    public static final String AVAILABLE = "AVAILABLE";
    public static final String AVAILABLE_BY_DRIVER_ONLY = "AVAILABLE_BY_DRIVER_ONLY";
    public static final String AVAILABLE_BY_MANUAL_ONLY = "AVAILABLE_BY_MANUAL_ONLY";
    public static final String AVAILABLE_BY_PASSENGER_ONLY = "AVAILABLE_BY_PASSENGER_ONLY";
    public static final String UNAVAILABLE = "UNAVAILABLE";
    public static final String UNAVAILABLE_BY_STATE = "UNAVAILABLE_BY_STATE";
}
