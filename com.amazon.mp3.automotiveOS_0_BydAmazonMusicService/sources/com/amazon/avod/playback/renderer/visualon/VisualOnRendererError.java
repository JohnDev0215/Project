package com.amazon.avod.playback.renderer.visualon;

import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.error.ErrorEnumUtils;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import com.visualon.OSMPUtils.voOSType;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum VisualOnRendererError implements ErrorEnumUtils.ErrorEnum, MediaInternalErrorCode {
    NO_ERROR(0, "VisualOn operation succeeded"),
    END_OF_SOURCE(1, "Reach the end of source"),
    RETRY(2, "The buffer was empty, try it again"),
    FORMAT_CHANGED(3, "Format was changed"),
    AUDIO_NOT_AVAILABLE(16, "Indicate audio is not available now"),
    VIDEO_NOT_AVAILABLE(17, "Indicate video is not available now"),
    NEED_FLUSH_BUFFER(18, "Indicate need flush buffer"),
    VIDEO_CODEC(voOSType.VOOSMP_ERR_Video, "Error from video codec"),
    AUDIO_CODEC(voOSType.VOOSMP_ERR_Audio, "Error from audio codec"),
    OUT_OF_MEMORY(voOSType.VOOSMP_ERR_OutMemory, "Error is out of memory"),
    POINTER_INVOID(voOSType.VOOSMP_ERR_Pointer, "The pointer is invoid"),
    PARAM_INVALID(voOSType.VOOSMP_ERR_ParamID, "The param id was not supported"),
    INVALID_STATUS(voOSType.VOOSMP_ERR_Status, "The status is wrong"),
    NOT_IMPLEMENTED(voOSType.VOOSMP_ERR_Implement, "The function was not implemented"),
    BUFFER_TOO_SMALL(voOSType.VOOSMP_ERR_SmallSize, "The input buffer was too small"),
    TIMEOUT(-2147483636, "It is out of time (late)"),
    WAITTIME(voOSType.VOOSMP_ERR_WaitTime, "It is waiting reference time (elear)"),
    UNKNOWN(voOSType.VOOSMP_ERR_Unknown, "Unknow error"),
    JNI(voOSType.VOOSMP_ERR_JNI, "JNI error"),
    SEEK_FAILED(-2147483632, "Seek failed");

    private static final Map<Integer, VisualOnRendererError> mErrorMap = ErrorEnumUtils.createErrorMap(values());
    private final String mDescription;
    private final int mErrorCode;

    VisualOnRendererError(int i, String str) {
        this.mErrorCode = i;
        this.mDescription = str;
    }

    public static Map<Integer, VisualOnRendererError> getErrorMap() {
        return mErrorMap;
    }

    @Override // com.amazon.avod.media.framework.error.ErrorEnumUtils.ErrorEnum
    public int getErrorCode() {
        return this.mErrorCode;
    }

    @Override // com.amazon.avod.media.framework.error.ErrorEnumUtils.ErrorEnum
    public String getDescription() {
        return this.mDescription;
    }

    @Override // com.amazon.avod.media.framework.error.MediaInternalErrorCode
    public MediaErrorCode getExternalCode() {
        return StandardErrorCode.PLAYBACK_RENDERER_ERROR;
    }

    @Override // com.amazon.avod.media.framework.error.MediaInternalErrorCode, com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return name();
    }
}
