package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class Error implements JsonStream.Streamable {
    private final ErrorInternal impl;
    private final Logger logger;

    Error(@NonNull ErrorInternal errorInternal, @NonNull Logger logger) {
        this.impl = errorInternal;
        this.logger = logger;
    }

    private void logNull(String str) {
        this.logger.e("Invalid null value supplied to error." + str + ", ignoring");
    }

    public void setErrorClass(@NonNull String str) {
        if (str != null) {
            this.impl.setErrorClass(str);
        } else {
            logNull("errorClass");
        }
    }

    @NonNull
    public String getErrorClass() {
        return this.impl.getErrorClass();
    }

    public void setErrorMessage(@Nullable String str) {
        this.impl.setErrorMessage(str);
    }

    @Nullable
    public String getErrorMessage() {
        return this.impl.getErrorMessage();
    }

    public void setType(@NonNull ErrorType errorType) {
        if (errorType != null) {
            this.impl.setType(errorType);
        } else {
            logNull(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        }
    }

    @NonNull
    public ErrorType getType() {
        return this.impl.getType();
    }

    @NonNull
    public List<Stackframe> getStacktrace() {
        return this.impl.getStacktrace();
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        this.impl.toStream(jsonStream);
    }

    static List<Error> createError(@NonNull Throwable th, @NonNull Collection<String> collection, @NonNull Logger logger) {
        return ErrorInternal.INSTANCE.createError(th, collection, logger);
    }
}
