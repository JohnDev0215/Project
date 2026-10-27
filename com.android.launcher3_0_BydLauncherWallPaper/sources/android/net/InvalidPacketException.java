package android.net;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class InvalidPacketException extends Exception {
    public static final int ERROR_INVALID_IP_ADDRESS = -21;
    public static final int ERROR_INVALID_LENGTH = -23;
    public static final int ERROR_INVALID_PORT = -22;
    private final int mError;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {
    }

    public InvalidPacketException(int i) {
        this.mError = i;
    }

    public int getError() {
        return this.mError;
    }
}
