package cerence.ark.assistant.controller.enumeration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface NaviRouteOptionEnum {
    public static final String AVOID_CONGESTION = "AVOID_CONGESTION";
    public static final String AVOID_HIGHWAY = "AVOID_HIGHWAY";
    public static final String FASTEST = "FASTEST";
    public static final String MOST_ECONOMIC = "MOST_ECONOMIC";
    public static final String PREFER_HIGHWAY = "PREFER_HIGHWAY";
    public static final String RECOMMENDED = "RECOMMENDED";
    public static final String SHORTEST = "SHORTEST";
    public static final String UNSPECIFIED = "UNSPECIFIED";
}
