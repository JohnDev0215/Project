package com.android.server.telecom.callfiltering;

import android.text.TextUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class CallFilteringResult {
    public boolean contactExists;
    public int mCallBlockReason;
    public CharSequence mCallScreeningAppName;
    public String mCallScreeningComponentName;
    public boolean shouldAddToCallLog;
    public boolean shouldAllowCall;
    public boolean shouldReject;
    public boolean shouldScreenViaAudio;
    public boolean shouldShowNotification;
    public boolean shouldSilence;

    private boolean isBlockedByProvider(int i) {
        return i == 3 || i == 4 || i == 5 || i == 6 || i == 7;
    }

    public static class Builder {
        private boolean mShouldAddToCallLog;
        private boolean mShouldAllowCall;
        private boolean mShouldReject;
        private boolean mShouldShowNotification;
        private boolean mShouldSilence = false;
        private boolean mShouldScreenViaAudio = false;
        private boolean mContactExists = false;
        private int mCallBlockReason = 0;
        private CharSequence mCallScreeningAppName = null;
        private String mCallScreeningComponentName = null;

        public Builder setShouldAllowCall(boolean z) {
            this.mShouldAllowCall = z;
            return this;
        }

        public Builder setShouldReject(boolean z) {
            this.mShouldReject = z;
            return this;
        }

        public Builder setShouldAddToCallLog(boolean z) {
            this.mShouldAddToCallLog = z;
            return this;
        }

        public Builder setShouldShowNotification(boolean z) {
            this.mShouldShowNotification = z;
            return this;
        }

        public Builder setShouldSilence(boolean z) {
            this.mShouldSilence = z;
            return this;
        }

        public Builder setCallBlockReason(int i) {
            this.mCallBlockReason = i;
            return this;
        }

        public Builder setShouldScreenViaAudio(boolean z) {
            this.mShouldScreenViaAudio = z;
            return this;
        }

        public Builder setCallScreeningAppName(CharSequence charSequence) {
            this.mCallScreeningAppName = charSequence;
            return this;
        }

        public Builder setCallScreeningComponentName(String str) {
            this.mCallScreeningComponentName = str;
            return this;
        }

        public Builder setContactExists(boolean z) {
            this.mContactExists = z;
            return this;
        }

        public CallFilteringResult build() {
            return new CallFilteringResult(this.mShouldAllowCall, this.mShouldReject, this.mShouldSilence, this.mShouldAddToCallLog, this.mShouldShowNotification, this.mCallBlockReason, this.mCallScreeningAppName, this.mCallScreeningComponentName, this.mShouldScreenViaAudio, this.mContactExists);
        }
    }

    private CallFilteringResult(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, CharSequence charSequence, String str, boolean z6, boolean z7) {
        this.shouldScreenViaAudio = false;
        this.shouldAllowCall = z;
        this.shouldReject = z2;
        this.shouldSilence = z3;
        this.shouldAddToCallLog = z4;
        this.shouldShowNotification = z5;
        this.shouldScreenViaAudio = z6;
        this.mCallBlockReason = i;
        this.mCallScreeningAppName = charSequence;
        this.mCallScreeningComponentName = str;
        this.contactExists = z7;
    }

    public CallFilteringResult combine(CallFilteringResult callFilteringResult) {
        if (callFilteringResult == null) {
            return this;
        }
        if (isBlockedByProvider(this.mCallBlockReason)) {
            return getCombinedCallFilteringResult(callFilteringResult, this.mCallBlockReason, null, null);
        }
        if (isBlockedByProvider(callFilteringResult.mCallBlockReason)) {
            return getCombinedCallFilteringResult(callFilteringResult, callFilteringResult.mCallBlockReason, null, null);
        }
        int i = this.mCallBlockReason;
        if (i == 2 || callFilteringResult.mCallBlockReason == 2) {
            return getCombinedCallFilteringResult(callFilteringResult, 2, null, null);
        }
        boolean z = true;
        if (this.shouldReject && i == 1) {
            return getCombinedCallFilteringResult(callFilteringResult, 1, this.mCallScreeningAppName, this.mCallScreeningComponentName);
        }
        if (callFilteringResult.shouldReject && callFilteringResult.mCallBlockReason == 1) {
            return getCombinedCallFilteringResult(callFilteringResult, 1, callFilteringResult.mCallScreeningAppName, callFilteringResult.mCallScreeningComponentName);
        }
        if (this.shouldScreenViaAudio) {
            return getCombinedCallFilteringResult(callFilteringResult, 0, this.mCallScreeningAppName, this.mCallScreeningComponentName);
        }
        if (callFilteringResult.shouldScreenViaAudio) {
            return getCombinedCallFilteringResult(callFilteringResult, 0, callFilteringResult.mCallScreeningAppName, callFilteringResult.mCallScreeningComponentName);
        }
        Builder builder = new Builder();
        builder.setShouldAllowCall(this.shouldAllowCall && callFilteringResult.shouldAllowCall);
        builder.setShouldReject(this.shouldReject || callFilteringResult.shouldReject);
        builder.setShouldSilence(this.shouldSilence || callFilteringResult.shouldSilence);
        builder.setShouldAddToCallLog(this.shouldAddToCallLog && callFilteringResult.shouldAddToCallLog);
        builder.setShouldShowNotification(this.shouldShowNotification && callFilteringResult.shouldShowNotification);
        builder.setShouldScreenViaAudio(this.shouldScreenViaAudio || callFilteringResult.shouldScreenViaAudio);
        if (!this.contactExists && !callFilteringResult.contactExists) {
            z = false;
        }
        builder.setContactExists(z);
        return builder.build();
    }

    private CallFilteringResult getCombinedCallFilteringResult(CallFilteringResult callFilteringResult, int i, CharSequence charSequence, String str) {
        Builder builder = new Builder();
        boolean z = true;
        builder.setShouldAllowCall(this.shouldAllowCall && callFilteringResult.shouldAllowCall);
        builder.setShouldReject(this.shouldReject || callFilteringResult.shouldReject);
        builder.setShouldSilence(this.shouldSilence || callFilteringResult.shouldSilence);
        builder.setShouldAddToCallLog(this.shouldAddToCallLog && callFilteringResult.shouldAddToCallLog);
        builder.setShouldShowNotification(this.shouldShowNotification && callFilteringResult.shouldShowNotification);
        builder.setShouldScreenViaAudio(this.shouldScreenViaAudio || callFilteringResult.shouldScreenViaAudio);
        builder.setCallBlockReason(i);
        builder.setCallScreeningAppName(charSequence);
        builder.setCallScreeningComponentName(str);
        if (!this.contactExists && !callFilteringResult.contactExists) {
            z = false;
        }
        builder.setContactExists(z);
        return builder.build();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CallFilteringResult.class != obj.getClass()) {
            return false;
        }
        CallFilteringResult callFilteringResult = (CallFilteringResult) obj;
        return this.shouldAllowCall == callFilteringResult.shouldAllowCall && this.shouldReject == callFilteringResult.shouldReject && this.shouldSilence == callFilteringResult.shouldSilence && this.shouldAddToCallLog == callFilteringResult.shouldAddToCallLog && this.shouldShowNotification == callFilteringResult.shouldShowNotification && this.mCallBlockReason == callFilteringResult.mCallBlockReason && this.contactExists == callFilteringResult.contactExists && Objects.equals(this.mCallScreeningAppName, callFilteringResult.mCallScreeningAppName) && Objects.equals(this.mCallScreeningComponentName, callFilteringResult.mCallScreeningComponentName);
    }

    public int hashCode() {
        return ((((((((this.shouldAllowCall ? 1 : 0) * 31) + (this.shouldReject ? 1 : 0)) * 31) + (this.shouldSilence ? 1 : 0)) * 31) + (this.shouldAddToCallLog ? 1 : 0)) * 31) + (this.shouldShowNotification ? 1 : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (this.shouldAllowCall) {
            sb.append("Allow");
        } else if (this.shouldReject) {
            sb.append("Reject");
        } else if (this.shouldSilence) {
            sb.append("Silence");
        } else {
            sb.append("Ignore");
        }
        if (this.shouldScreenViaAudio) {
            sb.append(", audio processing");
        }
        if (this.shouldAddToCallLog) {
            sb.append(", logged");
        }
        if (this.shouldShowNotification) {
            sb.append(", notified");
        }
        if (this.contactExists) {
            sb.append(", contact exists");
        }
        if (this.mCallBlockReason != 0) {
            sb.append(", mCallBlockReason = ");
            sb.append(this.mCallBlockReason);
        }
        if (!TextUtils.isEmpty(this.mCallScreeningAppName)) {
            sb.append(", mCallScreeningAppName = ");
            sb.append(this.mCallScreeningAppName);
        }
        if (!TextUtils.isEmpty(this.mCallScreeningComponentName)) {
            sb.append(", mCallScreeningComponentName = ");
            sb.append(this.mCallScreeningComponentName);
        }
        sb.append("]");
        return sb.toString();
    }
}
