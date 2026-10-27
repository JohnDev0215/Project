package android.car.encryptionrunner;

import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class HandshakeMessage {
    private final int mHandshakeState;
    private final Key mKey;
    private final byte[] mNextMessage;
    private final byte[] mOobVerificationCode;
    private final String mVerificationCode;

    @Retention(RetentionPolicy.SOURCE)
    public @interface HandshakeState {
        public static final int FINISHED = 3;
        public static final int INVALID = 4;
        public static final int IN_PROGRESS = 1;
        public static final int OOB_VERIFICATION_NEEDED = 6;
        public static final int RESUMING_SESSION = 5;
        public static final int UNKNOWN = 0;
        public static final int VERIFICATION_NEEDED = 2;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private HandshakeMessage(int handshakeState, Key key, byte[] nextMessage, String verificationCode, byte[] oobVerificationCode) {
        this.mHandshakeState = handshakeState;
        this.mKey = key;
        this.mNextMessage = nextMessage;
        this.mVerificationCode = verificationCode;
        this.mOobVerificationCode = oobVerificationCode;
    }

    public byte[] getNextMessage() {
        byte[] bArr = this.mNextMessage;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public int getHandshakeState() {
        return this.mHandshakeState;
    }

    public Key getKey() {
        return this.mKey;
    }

    public String getVerificationCode() {
        return this.mVerificationCode;
    }

    public byte[] getOobVerificationCode() {
        return this.mOobVerificationCode;
    }

    static class Builder {
        int mHandshakeState;
        Key mKey;
        byte[] mNextMessage;
        byte[] mOobVerificationCode;
        String mVerificationCode;

        Builder() {
        }

        Builder setHandshakeState(int handshakeState) {
            this.mHandshakeState = handshakeState;
            return this;
        }

        Builder setKey(Key key) {
            this.mKey = key;
            return this;
        }

        Builder setNextMessage(byte[] nextMessage) {
            this.mNextMessage = nextMessage == null ? null : (byte[]) nextMessage.clone();
            return this;
        }

        Builder setVerificationCode(String verificationCode) {
            this.mVerificationCode = verificationCode;
            return this;
        }

        Builder setOobVerificationCode(byte[] oobVerificationCode) {
            this.mOobVerificationCode = oobVerificationCode;
            return this;
        }

        HandshakeMessage build() {
            byte[] bArr;
            int i = this.mHandshakeState;
            if (i == 0) {
                throw new IllegalStateException("must set handshake state before calling build");
            }
            if (i == 2 && TextUtils.isEmpty(this.mVerificationCode)) {
                throw new IllegalStateException("State is verification needed, but verification code null.");
            }
            if (this.mHandshakeState == 6 && ((bArr = this.mOobVerificationCode) == null || bArr.length == 0)) {
                throw new IllegalStateException("State is OOB verification needed, but OOB verification code null.");
            }
            return new HandshakeMessage(this.mHandshakeState, this.mKey, this.mNextMessage, this.mVerificationCode, this.mOobVerificationCode);
        }
    }
}
