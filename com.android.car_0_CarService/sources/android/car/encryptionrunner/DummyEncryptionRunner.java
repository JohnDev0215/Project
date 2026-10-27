package android.car.encryptionrunner;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class DummyEncryptionRunner implements EncryptionRunner {
    public static final String CLIENT_RESPONSE = "clientResponse";
    private static final byte[] DUMMY_MESSAGE = "Dummy Message".getBytes();
    public static final String INIT = "init";
    public static final String INIT_RESPONSE = "initResponse";
    private static final String KEY = "key";
    public static final String VERIFICATION_CODE = "1234";
    private Key mCurrentDummyKey;
    private boolean mInitReconnectVerification;
    private boolean mIsReconnect;
    private int mMode;
    private int mState;

    @Retention(RetentionPolicy.SOURCE)
    private @interface Mode {
        public static final int CLIENT = 1;
        public static final int SERVER = 2;
        public static final int UNKNOWN = 0;
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public HandshakeMessage initHandshake() {
        checkRunnerIsNew();
        this.mMode = 1;
        this.mState = 1;
        return HandshakeMessage.newBuilder().setHandshakeState(this.mState).setNextMessage(INIT.getBytes()).build();
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public HandshakeMessage respondToInitRequest(byte[] initializationRequest) throws HandshakeException {
        checkRunnerIsNew();
        this.mMode = 2;
        if (!new String(initializationRequest).equals(INIT)) {
            throw new HandshakeException("Unexpected initialization request");
        }
        this.mState = 1;
        return HandshakeMessage.newBuilder().setHandshakeState(1).setNextMessage(INIT_RESPONSE.getBytes()).build();
    }

    private void checkRunnerIsNew() {
        if (this.mState != 0) {
            throw new IllegalStateException("runner already initialized.");
        }
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public HandshakeMessage continueHandshake(byte[] response) throws HandshakeException {
        if (this.mState != 1) {
            throw new HandshakeException("not waiting for response but got one");
        }
        int i = this.mMode;
        if (i == 1) {
            if (!INIT_RESPONSE.equals(new String(response))) {
                throw new HandshakeException("unexpected response: " + new String(response));
            }
            this.mState = 2;
            if (this.mIsReconnect) {
                verifyPin();
                this.mState = 5;
            }
            return HandshakeMessage.newBuilder().setHandshakeState(this.mState).setNextMessage(CLIENT_RESPONSE.getBytes()).setVerificationCode(VERIFICATION_CODE).build();
        }
        if (i == 2) {
            if (!CLIENT_RESPONSE.equals(new String(response))) {
                throw new HandshakeException("unexpected response: " + new String(response));
            }
            this.mState = 2;
            if (this.mIsReconnect) {
                verifyPin();
                this.mState = 5;
            }
            return HandshakeMessage.newBuilder().setVerificationCode(VERIFICATION_CODE).setHandshakeState(this.mState).build();
        }
        throw new IllegalStateException("unexpected role: " + this.mMode);
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public HandshakeMessage authenticateReconnection(byte[] bArr, byte[] bArr2) throws HandshakeException {
        this.mCurrentDummyKey = new DummyKey();
        return HandshakeMessage.newBuilder().setHandshakeState(3).setKey(this.mCurrentDummyKey).setNextMessage(this.mInitReconnectVerification ? null : DUMMY_MESSAGE).build();
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public HandshakeMessage initReconnectAuthentication(byte[] previousKey) throws HandshakeException {
        this.mInitReconnectVerification = true;
        this.mState = 5;
        return HandshakeMessage.newBuilder().setHandshakeState(this.mState).setNextMessage(DUMMY_MESSAGE).build();
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public Key keyOf(byte[] serialized) {
        return new DummyKey();
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public HandshakeMessage verifyPin() throws HandshakeException {
        if (this.mState != 2) {
            throw new IllegalStateException("asking to verify pin, state = " + this.mState);
        }
        this.mState = 3;
        return HandshakeMessage.newBuilder().setKey(new DummyKey()).setHandshakeState(this.mState).build();
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public void invalidPin() {
        this.mState = 4;
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public void setIsReconnect(boolean isReconnect) {
        this.mIsReconnect = isReconnect;
    }

    private class DummyKey implements Key {
        private DummyKey() {
        }

        @Override // android.car.encryptionrunner.Key
        public byte[] asBytes() {
            return DummyEncryptionRunner.KEY.getBytes();
        }

        @Override // android.car.encryptionrunner.Key
        public byte[] encryptData(byte[] data) {
            return data;
        }

        @Override // android.car.encryptionrunner.Key
        public byte[] decryptData(byte[] encryptedData) {
            return encryptedData;
        }

        @Override // android.car.encryptionrunner.Key
        public byte[] getUniqueSession() {
            return DummyEncryptionRunner.KEY.getBytes();
        }
    }
}
