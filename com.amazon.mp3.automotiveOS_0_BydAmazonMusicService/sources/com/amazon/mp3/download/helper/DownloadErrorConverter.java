package com.amazon.mp3.download.helper;

import android.content.Context;
import androidx.compose.runtime.ComposerKt;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.music.downloads.ErrorReason;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadErrorConverter {

    /* JADX INFO: renamed from: com.amazon.mp3.download.helper.DownloadErrorConverter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$ErrorReason;

        static {
            int[] iArr = new int[ErrorReason.values().length];
            $SwitchMap$com$amazon$music$downloads$ErrorReason = iArr;
            try {
                iArr[ErrorReason.NETWORK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.DISK_OUT_OF_SPACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.NO_DISK_ACCESS_PERMISSION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.DEVICE_NOT_AUTHORIZED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.DEVICE_NOT_ELIGIBLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.CONTENT_NOT_ELIGIBLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.USER_NOT_AUTHENTICATED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.INVALID_DIRECTORY_OR_DB.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.FILE_WRITE_FAILED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.FILE_OPEN_FAILED.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.CONTENT_NOT_AVAILABLE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.CONTENT_NOT_FOUND.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.CONTENT_NOT_VALID.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.FILE_EXCEPTION.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.ENGINE_NOT_INITIALIZED.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.BAD_REQUEST.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    public static int toExceptionCode(ErrorReason reason) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$music$downloads$ErrorReason[reason.ordinal()]) {
            case 1:
                return ComposerKt.providerMapsKey;
            case 2:
                return 205;
            case 3:
                return ComposerKt.referenceKey;
            case 4:
                return ComposerKt.reuseKey;
            case 5:
                return ContentCatalogStatus.SONIC_RUSH_OR_NOT_BOUNDARY;
            case 6:
                return 214;
            case 7:
                return 218;
            case 8:
                return 228;
            case 9:
                return 229;
            case 10:
                return 230;
            case 11:
                return 231;
            case 12:
                return 232;
            case 13:
                return 233;
            case 14:
                return 240;
            case 15:
                return 241;
            case 16:
                return 242;
            default:
                return 200;
        }
    }

    public static String toMessage(Context context, ErrorReason reason) {
        if (reason == null) {
            reason = ErrorReason.UNKNOWN;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$ErrorReason[reason.ordinal()];
        if (i == 1) {
            return context.getString(R.string.dmusic_download_error_network, Integer.valueOf(toExceptionCode(reason)));
        }
        if (i == 2) {
            return context.getString(R.string.dmusic_download_error_storage, Integer.valueOf(toExceptionCode(reason)));
        }
        if (i != 3) {
            return i != 5 ? context.getString(R.string.dmusic_download_error_unknown, Integer.valueOf(toExceptionCode(reason))) : context.getString(R.string.dmusic_download_error_authorization, Integer.valueOf(toExceptionCode(reason)));
        }
        return context.getString(R.string.dmusic_alert_storage_permission_text);
    }
}
