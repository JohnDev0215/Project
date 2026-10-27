package cerence.ark.assistant.core.phone.data;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneNumber {
    private final String mNumber;
    private final PhoneNumberType mNumberType;

    public enum PhoneNumberType {
        HOME,
        MOBILE,
        WORK,
        FAX_WORK,
        FAX_HOME,
        PAGER,
        CALLBACK,
        WORK_MOBILE,
        OTHER_FAX,
        WORK_PAGER,
        CAR,
        COMPANY_MAIN,
        MAIN,
        ISDN,
        RADIO,
        TELEX,
        TTY_TDD,
        ASSISTANT,
        MMS,
        OTHER
    }

    private PhoneNumber(Builder builder) {
        this.mNumber = builder.mNumber;
        this.mNumberType = builder.mNumberType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getNumber() {
        return this.mNumber;
    }

    public PhoneNumberType getNumberType() {
        return this.mNumberType;
    }

    public String toString() {
        return "PhoneNumber{mNumber='" + this.mNumber + "', mNumberType=" + this.mNumberType + '}';
    }

    public static class Builder {
        private String mNumber;
        private PhoneNumberType mNumberType;

        private Builder() {
        }

        public Builder setNumber(String str) {
            this.mNumber = str;
            return this;
        }

        public Builder setNumberType(PhoneNumberType phoneNumberType) {
            this.mNumberType = phoneNumberType;
            return this;
        }

        public PhoneNumber build() {
            return new PhoneNumber(this);
        }
    }
}
