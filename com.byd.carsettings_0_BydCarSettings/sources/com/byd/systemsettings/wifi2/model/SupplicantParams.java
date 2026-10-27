package com.byd.systemsettings.wifi2.model;

import android.net.wifi.SupplicantState;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class SupplicantParams implements Serializable {
    public static final int ERROR_AUTH_FAILURE_EAP_FAILURE = 3;
    public static final int ERROR_AUTH_FAILURE_NONE = 0;
    public static final int ERROR_AUTH_FAILURE_TIMEOUT = 1;
    public static final int ERROR_AUTH_FAILURE_WRONG_PSWD = 2;
    public int errorReason;
    public SupplicantState state;

    public SupplicantParams(SupplicantState supplicantState, int i) {
        this.state = supplicantState;
        this.errorReason = i;
    }

    public String toString() {
        return "SupplicantParems{state=" + this.state + ", errorReason=" + this.errorReason + '}';
    }
}
