package com.amazon.avod.playback.renderer.tate;

import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import com.amazon.avod.playback.PlaybackException;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class OMXILPlaybackException extends PlaybackException {
    public static final String ANDROID_UNKNOWN_ERROR = "0x80000000";
    public static final String OMX_MEDIA_SERVER_DIED = "0xBAD00003";
    public static final String OMX_TIMEOUT = "0x80001011";
    private static final long serialVersionUID = 1;
    private int mNativeErrorCode;

    private static String mapErrorCodeToDescription(int i) {
        if (OMXILPlaybackError.matchesErrorCode(i)) {
            return OMXILPlaybackError.fromErrorCode(i).toString();
        }
        return LicenseError.matchesErrorCode(i) ? LicenseError.fromErrorCode(i).toString() : String.format(Locale.US, "unrecognized error 0x%x", Integer.valueOf(i));
    }

    public OMXILPlaybackException(String str, int i) {
        super(PlaybackException.PlaybackError.NATIVE_PLAYBACK_ERROR, str + ": " + mapErrorCodeToDescription(i));
        this.mNativeErrorCode = i;
    }

    @Override // com.amazon.avod.media.framework.error.MediaException
    public MediaInternalErrorCode getErrorCode() {
        if (LicenseError.matchesErrorCode(this.mNativeErrorCode)) {
            return LicenseError.fromErrorCode(this.mNativeErrorCode);
        }
        return super.getErrorCode();
    }

    public int getNativeErrorCode() {
        return this.mNativeErrorCode;
    }

    @Override // com.amazon.avod.media.framework.error.MediaException
    public int getIntegerErrorCode() {
        return this.mNativeErrorCode;
    }
}
