package cerence.ark.assistant.controller.enumeration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface MaintenanceStateEnum {
    public static final String ALREADY_EXCEED = "ALREADY_EXCEED";
    public static final String MILEAGE_NEAR = "MILEAGE_NEAR";
    public static final String SUFFICIENT = "SUFFICIENT";
    public static final String TIME_NEAR = "TIME_NEAR";
}
