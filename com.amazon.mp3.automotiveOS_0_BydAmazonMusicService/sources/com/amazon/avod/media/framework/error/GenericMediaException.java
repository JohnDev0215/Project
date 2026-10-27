package com.amazon.avod.media.framework.error;

import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class GenericMediaException extends MediaException {
    private static final long serialVersionUID = -5457240451699040043L;

    public GenericMediaException(Throwable th, @Nonnull final MediaErrorCode mediaErrorCode) {
        super(new MediaInternalErrorCode() { // from class: com.amazon.avod.media.framework.error.GenericMediaException.1
            @Override // com.amazon.avod.media.framework.error.MediaInternalErrorCode, com.amazon.avod.metrics.pmet.MetricParameter
            @Nonnull
            public String toReportableString() {
                return String.format(Locale.US, "GenericMediaException:%s", mediaErrorCode.getName());
            }

            @Override // com.amazon.avod.media.framework.error.MediaInternalErrorCode
            public MediaErrorCode getExternalCode() {
                return mediaErrorCode;
            }
        }, createMessageFromCause(th), th);
    }

    public static MediaException wrapIfNeeded(Throwable th, @Nonnull MediaErrorCode mediaErrorCode) {
        MediaException mediaException = (MediaException) CastUtils.castTo(th, MediaException.class);
        if (mediaException != null) {
            return mediaException;
        }
        DLog.devf("Wrapping %s in a GenericMediaException", th.getClass());
        return new GenericMediaException(th, mediaErrorCode);
    }

    private static String createMessageFromCause(Throwable th) {
        if (th == null) {
            return null;
        }
        String simpleName = th.getClass().getSimpleName();
        String string = th.getStackTrace()[0].toString();
        return String.format(Locale.US, "%s:%s at %s", simpleName, String.valueOf(th.getLocalizedMessage()), string);
    }
}
