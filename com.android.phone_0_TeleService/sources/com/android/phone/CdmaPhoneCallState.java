package com.android.phone;

/* JADX INFO: loaded from: classes.dex */
public class CdmaPhoneCallState {
    private PhoneCallState mCurrentCallState;
    private PhoneCallState mPreviousCallState;

    public enum PhoneCallState {
        IDLE,
        SINGLE_ACTIVE,
        THRWAY_ACTIVE,
        CONF_CALL
    }

    public void CdmaPhoneCallStateInit() {
        PhoneCallState phoneCallState = PhoneCallState.IDLE;
        this.mCurrentCallState = phoneCallState;
        this.mPreviousCallState = phoneCallState;
    }

    public PhoneCallState getCurrentCallState() {
        return this.mCurrentCallState;
    }

    public void setCurrentCallState(PhoneCallState phoneCallState) {
        this.mPreviousCallState = this.mCurrentCallState;
        this.mCurrentCallState = phoneCallState;
        if (phoneCallState == PhoneCallState.SINGLE_ACTIVE) {
            PhoneCallState phoneCallState2 = PhoneCallState.IDLE;
        }
    }
}
