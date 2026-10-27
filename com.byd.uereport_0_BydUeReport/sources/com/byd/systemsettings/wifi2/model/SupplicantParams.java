package com.byd.systemsettings.wifi2.model;

import android.net.wifi.SupplicantState;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class SupplicantParams implements Serializable {
    public static final int ERROR_AUTH_FAILURE_EAP_FAILURE = 3;
    public static final int ERROR_AUTH_FAILURE_NONE = 0;
    public static final int ERROR_AUTH_FAILURE_TIMEOUT = 1;
    public static final int ERROR_AUTH_FAILURE_WRONG_PSWD = 2;
    public int errorReason;
    public SupplicantState state;

    public SupplicantParams(SupplicantState supplicantState, int i8) {
        this.state = supplicantState;
        this.errorReason = i8;
    }

    public String toString() {
        return "SupplicantParems{state=" + this.state + ", errorReason=" + this.errorReason + '}';
    }
}
