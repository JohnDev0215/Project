package com.amazon.avod.playbackresourcev2;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.media.error.Prsv2ErrorCode;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class Prsv2ErrorLookup {
    private static final Map<Prsv2ErrorCode, ContentException.ContentError> PRSV2_ERROR_CODE = buildPrsErrorCodes();

    private static Map<Prsv2ErrorCode, ContentException.ContentError> buildPrsErrorCodes() {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        Prsv2ErrorCode prsv2ErrorCode = Prsv2ErrorCode.Denied;
        ContentException.ContentError contentError = ContentException.ContentError.SERVICE_ERROR;
        builder.put(prsv2ErrorCode, contentError).put(Prsv2ErrorCode.ConcurrencyLimitBreached, ContentException.ContentError.NO_AVAILABLE_ONLINE_STREAMS).put(Prsv2ErrorCode.Fault, contentError).put(Prsv2ErrorCode.Timeout, contentError).put(Prsv2ErrorCode.Invalid, contentError).put(Prsv2ErrorCode.PlaybackEnvelopeExpired, ContentException.ContentError.EXPIRED_PLAYBACK_ENVELOPE).put(Prsv2ErrorCode.PlaybackEnvelopeInvalid, ContentException.ContentError.INVALID_PLAYBACK_ENVELOPE);
        return builder.build();
    }

    public static ContentException.ContentError fromPrsv2ErrorCode(@Nonnull Prsv2ErrorCode prsv2ErrorCode) {
        Preconditions.checkNotNull(prsv2ErrorCode, "errorCode");
        return PRSV2_ERROR_CODE.get(prsv2ErrorCode);
    }
}
