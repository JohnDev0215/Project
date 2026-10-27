package com.amazon.avod.media.framework.error;

import com.amazon.avod.util.DLog;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.net.URL;
import java.util.Locale;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MediaException extends Exception {
    private static final long serialVersionUID = 1;
    private final MediaInternalErrorCode mErrorCode;
    private final URL mURL;

    public int getIntegerErrorCode() {
        return -1;
    }

    public MediaException(MediaInternalErrorCode mediaInternalErrorCode, String str, Throwable th) {
        this(mediaInternalErrorCode, str, th, null);
    }

    public MediaException(MediaInternalErrorCode mediaInternalErrorCode, String str, Throwable th, @Nullable URL url) {
        super(str, th);
        Preconditions.checkArgument(mediaInternalErrorCode != null);
        this.mErrorCode = mediaInternalErrorCode;
        this.mURL = url;
    }

    public MediaInternalErrorCode getErrorCode() {
        return this.mErrorCode;
    }

    @Override // java.lang.Throwable
    public String toString() {
        Locale locale = Locale.US;
        String str = String.format(locale, "%s of type: %s. Message: %s", getClass().getSimpleName(), getErrorCode(), getMessage());
        Throwable cause = getCause();
        if (cause == null) {
            return str;
        }
        String strSanitize_avod_message = DLog.sanitize_avod_message(cause.getMessage());
        if (strSanitize_avod_message == null) {
            strSanitize_avod_message = cause.getClass().getSimpleName();
        }
        return String.format(locale, "%s | Caused by: %s", str, strSanitize_avod_message);
    }

    @Nullable
    public URL getUrl() {
        return this.mURL;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaException)) {
            return false;
        }
        MediaException mediaException = (MediaException) obj;
        if (!this.mErrorCode.equals(mediaException.mErrorCode)) {
            return false;
        }
        URL url = this.mURL;
        return url != null ? url.toString().equals(mediaException.mURL.toString()) : mediaException.mURL == null;
    }

    public int hashCode() {
        return Objects.hashCode(this.mErrorCode, this.mURL);
    }
}
