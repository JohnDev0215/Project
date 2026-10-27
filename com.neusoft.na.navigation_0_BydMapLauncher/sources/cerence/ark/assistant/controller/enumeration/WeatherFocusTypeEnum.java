package cerence.ark.assistant.controller.enumeration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface WeatherFocusTypeEnum {
    public static final String CURRENT_CONDITIONS = "CURRENT_CONDITIONS";
    public static final String DAILY_FORECASTS = "DAILY_FORECASTS";
    public static final String HOURLY_FORECASTS = "HOURLY_FORECASTS";
    public static final String OUT_OF_RANGE = "OUT_OF_RANGE";
}
