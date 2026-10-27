package com.android.server.telecom;

/* JADX INFO: loaded from: classes.dex */
public final class CallState {
    public static String toString(int i) {
        switch (i) {
            case 0:
                return "NEW";
            case 1:
                return "CONNECTING";
            case HeadsetMediaButton.LONG_PRESS /* 2 */:
                return "SELECT_PHONE_ACCOUNT";
            case 3:
                return "DIALING";
            case 4:
                return "RINGING";
            case 5:
                return "ACTIVE";
            case 6:
                return "ON_HOLD";
            case 7:
                return "DISCONNECTED";
            case 8:
                return "ABORTED";
            case PhoneAccountRegistrar.EXPECTED_STATE_VERSION /* 9 */:
                return "DISCONNECTING";
            case 10:
                return "PULLING";
            case 11:
                return "ANSWERED";
            case 12:
                return "AUDIO_PROCESSING";
            case 13:
                return "SIMULATED_RINGING";
            default:
                return "UNKNOWN";
        }
    }
}
