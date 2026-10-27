package android.car.userlib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public interface HalCallback<R> {
    public static final int STATUS_CONCURRENT_OPERATION = 5;
    public static final int STATUS_HAL_NOT_SUPPORTED = 6;
    public static final int STATUS_HAL_RESPONSE_TIMEOUT = 3;
    public static final int STATUS_HAL_SET_TIMEOUT = 2;
    public static final int STATUS_INVALID = -1;
    public static final int STATUS_OK = 1;
    public static final int STATUS_WRONG_HAL_RESPONSE = 4;

    @Retention(RetentionPolicy.SOURCE)
    public @interface HalCallbackStatus {
    }

    void onResponse(int i, R r);
}
