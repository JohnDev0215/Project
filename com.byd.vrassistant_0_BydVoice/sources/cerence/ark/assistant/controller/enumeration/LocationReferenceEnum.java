package cerence.ark.assistant.controller.enumeration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface LocationReferenceEnum {
    public static final String CURRENT_LOCATION = "CURRENT_LOCATION";
    public static final String DESTINATION = "DESTINATION";
    public static final String HOME = "HOME";
    public static final String OFFICE = "OFFICE";
    public static final String PLACE = "PLACE";
    public static final String ROUTE = "ROUTE";
}
