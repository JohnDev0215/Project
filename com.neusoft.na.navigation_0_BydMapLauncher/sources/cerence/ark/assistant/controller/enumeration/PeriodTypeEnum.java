package cerence.ark.assistant.controller.enumeration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface PeriodTypeEnum {
    public static final String ALL = "ALL";
    public static final String CURRENT_TRIP = "CURRENT_TRIP";
    public static final String THIS_MONTH = "THIS_MONTH";
    public static final String THIS_WEEK = "THIS_WEEK";
    public static final String THIS_YEAR = "THIS_YEAR";
}
