package com.amazon.mp3.playback.harley;

import com.amazon.digitalmusicplayback.EngineError;
import com.amazon.digitalmusicplayback.ErrorCode;
import com.amazon.music.downloads.ErrorReason;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HarleyToDownloadManagerErrorConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/playback/harley/HarleyToDownloadManagerErrorConverter;", "", "()V", "convert", "Lcom/amazon/music/downloads/ErrorReason;", "engineError", "Lcom/amazon/digitalmusicplayback/EngineError;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HarleyToDownloadManagerErrorConverter {
    public static final HarleyToDownloadManagerErrorConverter INSTANCE = new HarleyToDownloadManagerErrorConverter();

    /* JADX INFO: compiled from: HarleyToDownloadManagerErrorConverter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ErrorCode.values().length];
            iArr[ErrorCode.NOERROR.ordinal()] = 1;
            iArr[ErrorCode.STORAGEFULL.ordinal()] = 2;
            iArr[ErrorCode.FILEWRITE.ordinal()] = 3;
            iArr[ErrorCode.NOTAUTHENTICATED.ordinal()] = 4;
            iArr[ErrorCode.DEVICENOTELIGIBLE.ordinal()] = 5;
            iArr[ErrorCode.DEVICENOTAUTHORIZED.ordinal()] = 6;
            iArr[ErrorCode.CONTENTNOTELIGIBLE.ordinal()] = 7;
            iArr[ErrorCode.NETWORKREQUESTFAILED.ordinal()] = 8;
            iArr[ErrorCode.NETWORKUNAVAILABLE.ordinal()] = 9;
            iArr[ErrorCode.CONTENTNOTAVAILABLE.ordinal()] = 10;
            iArr[ErrorCode.FILEOPENFAILED.ordinal()] = 11;
            iArr[ErrorCode.CONTENTNOTVALID.ordinal()] = 12;
            iArr[ErrorCode.FILEEXCEPTION.ordinal()] = 13;
            iArr[ErrorCode.ENGINENOTINITIALIZED.ordinal()] = 14;
            iArr[ErrorCode.BADREQUEST.ordinal()] = 15;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private HarleyToDownloadManagerErrorConverter() {
    }

    public final ErrorReason convert(EngineError engineError) {
        Intrinsics.checkNotNullParameter(engineError, "engineError");
        ErrorCode code = engineError.getCode();
        switch (code == null ? -1 : WhenMappings.$EnumSwitchMapping$0[code.ordinal()]) {
            case 1:
                return ErrorReason.NO_ERROR;
            case 2:
                return ErrorReason.DISK_OUT_OF_SPACE;
            case 3:
                return ErrorReason.NO_DISK_ACCESS_PERMISSION;
            case 4:
                return ErrorReason.USER_NOT_AUTHENTICATED;
            case 5:
                return ErrorReason.DEVICE_NOT_ELIGIBLE;
            case 6:
                return ErrorReason.DEVICE_NOT_AUTHORIZED;
            case 7:
                return ErrorReason.CONTENT_NOT_ELIGIBLE;
            case 8:
            case 9:
                return ErrorReason.NETWORK;
            case 10:
                return ErrorReason.CONTENT_NOT_AVAILABLE;
            case 11:
                return ErrorReason.FILE_OPEN_FAILED;
            case 12:
                return ErrorReason.CONTENT_NOT_VALID;
            case 13:
                return ErrorReason.FILE_EXCEPTION;
            case 14:
                return ErrorReason.ENGINE_NOT_INITIALIZED;
            case 15:
                return ErrorReason.BAD_REQUEST;
            default:
                return ErrorReason.UNKNOWN;
        }
    }
}
