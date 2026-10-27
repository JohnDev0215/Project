package cerence.ark.assistant.core.phone.data;

import cerence.ark.assistant.api.dynamic.Contact;

/* JADX INFO: loaded from: classes.dex */
public class CallLog {
    private final CallLogType mCallLogType;
    private final Contact mContact;
    private final PhoneNumber mPhoneNumber;
    private final long mTimestamp;

    public enum CallLogType {
        MISSED_CALL_LOG,
        OUTGOING_CALL_LOG,
        ANSWERED_CALL_LOG,
        INCOMING_CALL_LOG,
        ALL_CALL_LOG
    }

    private CallLog(Builder builder) {
        this.mContact = builder.mContact;
        this.mTimestamp = builder.mTimestamp;
        this.mPhoneNumber = builder.mPhoneNumber;
        this.mCallLogType = builder.mCallLogType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Contact getContact() {
        return this.mContact;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public PhoneNumber getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public CallLogType getCallLogType() {
        return this.mCallLogType;
    }

    public String toString() {
        return "CallLog{mContact=" + this.mContact + ", mTimestamp=" + this.mTimestamp + ", mPhoneNumber=" + this.mPhoneNumber + ", mCallLogType=" + this.mCallLogType + '}';
    }

    public static class Builder {
        private CallLogType mCallLogType;
        private Contact mContact;
        private PhoneNumber mPhoneNumber;
        private long mTimestamp;

        private Builder() {
        }

        public Builder setContact(Contact contact) {
            this.mContact = contact;
            return this;
        }

        public Builder setTimestamp(long j) {
            this.mTimestamp = j;
            return this;
        }

        public Builder setPhoneNumber(PhoneNumber phoneNumber) {
            this.mPhoneNumber = phoneNumber;
            return this;
        }

        public Builder setCallLogType(CallLogType callLogType) {
            this.mCallLogType = callLogType;
            return this;
        }

        public CallLog build() {
            return new CallLog(this);
        }
    }
}
