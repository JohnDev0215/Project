package com.android.server.telecom;

/* JADX INFO: loaded from: classes.dex */
public class HandoverState {
    public static String stateToString(int i) {
        switch (i) {
            case 1:
                return "NONE";
            case HeadsetMediaButton.LONG_PRESS /* 2 */:
                return "HANDOVER_TO_STARTED";
            case 3:
                return "HANDOVER_FROM_STARTED";
            case 4:
                return "HANDOVER_ACCEPTED";
            case 5:
                return "HANDOVER_COMPLETE";
            case 6:
                return "HANDOVER_FAILED";
            default:
                return "";
        }
    }
}
