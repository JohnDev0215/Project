package com.amazon.music.wakeword;

import android.util.Log;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;

/* JADX INFO: loaded from: classes4.dex */
public class WakeWordErrorHandler {
    private static final String TAG = "WakeWordErrorHandler";

    public enum WakeWordErrorType {
        MICROPHONE_PERMISSIONS_REVOKED,
        MICROPHONE_PERMISSIONS_NOT_AVAILABLE,
        PRYON_LIBRARY_ERROR,
        PRYON_WAKE_WORD_DETECTOR_IS_NOT_INITIALIZED
    }

    public void handleError(WakeWordErrorType wakeWordErrorType, String str) {
        if (wakeWordErrorType == null) {
            Log.e(TAG, "wakeWordErrorType is null. Exiting handleError method.");
            return;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$wakeword$WakeWordErrorHandler$WakeWordErrorType[wakeWordErrorType.ordinal()];
        if (i == 1) {
            Log.w(TAG, "Micrpohone permission is not available.");
            sendFlexEvent("microphonePermissionNotAvailable");
            return;
        }
        if (i == 2) {
            Log.e(TAG, "Microphone permission is revoked. " + str);
            sendFlexEvent("microphonePermissionRevoked");
            return;
        }
        if (i == 3) {
            Log.e(TAG, "There was an error initializing Pryon.");
            sendFlexEvent("pryonLibraryError");
        } else {
            if (i != 4) {
                return;
            }
            Log.e(TAG, "PryonWakeWordDetector is null. " + str);
            sendFlexEvent("pryonNotInitialized");
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.wakeword.WakeWordErrorHandler$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$wakeword$WakeWordErrorHandler$WakeWordErrorType;

        static {
            int[] iArr = new int[WakeWordErrorType.values().length];
            $SwitchMap$com$amazon$music$wakeword$WakeWordErrorHandler$WakeWordErrorType = iArr;
            try {
                iArr[WakeWordErrorType.MICROPHONE_PERMISSIONS_NOT_AVAILABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$wakeword$WakeWordErrorHandler$WakeWordErrorType[WakeWordErrorType.MICROPHONE_PERMISSIONS_REVOKED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$wakeword$WakeWordErrorHandler$WakeWordErrorType[WakeWordErrorType.PRYON_LIBRARY_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$wakeword$WakeWordErrorHandler$WakeWordErrorType[WakeWordErrorType.PRYON_WAKE_WORD_DETECTOR_IS_NOT_INITIALIZED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void sendFlexEvent(String str) {
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("wakeWordError").withFlexStr1(str).build());
    }
}
