package android.car.encryptionrunner;

import android.util.Log;
import com.google.security.cryptauth.lib.securegcm.D2DConnectionContext;
import com.google.security.cryptauth.lib.securegcm.Ukey2Handshake;
import com.google.security.cryptauth.lib.securemessage.CryptoOps;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class Ukey2EncryptionRunner implements EncryptionRunner {
    private static final int AUTH_STRING_LENGTH = 6;
    private static final int RESUME_HMAC_LENGTH = 32;
    private Key mCurrentKey;
    private byte[] mCurrentUniqueSesion;
    private boolean mInitReconnectionVerification;
    private boolean mIsReconnect;
    private int mMode = 0;
    private byte[] mPrevUniqueSesion;
    private boolean mRunnerIsInvalid;
    private Ukey2Handshake mUkey2client;
    private static final Ukey2Handshake.HandshakeCipher CIPHER = Ukey2Handshake.HandshakeCipher.P256_SHA512;
    private static final byte[] RESUME = "RESUME".getBytes();
    private static final byte[] SERVER = "SERVER".getBytes();
    private static final byte[] CLIENT = "CLIENT".getBytes();

    private @interface Mode {
        public static final int CLIENT = 1;
        public static final int SERVER = 2;
        public static final int UNKNOWN = 0;
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public HandshakeMessage initHandshake() {
        checkRunnerIsNew();
        this.mMode = 1;
        try {
            this.mUkey2client = Ukey2Handshake.forInitiator(CIPHER);
            return HandshakeMessage.newBuilder().setHandshakeState(getHandshakeState()).setNextMessage(this.mUkey2client.getNextHandshakeMessage()).build();
        } catch (com.google.security.cryptauth.lib.securegcm.HandshakeException e) {
            Log.e(EncryptionRunner.TAG, "unexpected exception", e);
            throw new RuntimeException(e);
        }
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public void setIsReconnect(boolean isReconnect) {
        this.mIsReconnect = isReconnect;
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public HandshakeMessage respondToInitRequest(byte[] initializationRequest) throws HandshakeException {
        checkRunnerIsNew();
        this.mMode = 2;
        try {
            if (this.mUkey2client != null) {
                throw new IllegalStateException("Cannot reuse encryption runners, this one is already initialized");
            }
            Ukey2Handshake ukey2HandshakeForResponder = Ukey2Handshake.forResponder(CIPHER);
            this.mUkey2client = ukey2HandshakeForResponder;
            ukey2HandshakeForResponder.parseHandshakeMessage(initializationRequest);
            return HandshakeMessage.newBuilder().setHandshakeState(getHandshakeState()).setNextMessage(this.mUkey2client.getNextHandshakeMessage()).build();
        } catch (com.google.security.cryptauth.lib.securegcm.HandshakeException | Ukey2Handshake.AlertException e) {
            throw new HandshakeException(e);
        }
    }

    private void checkRunnerIsNew() {
        if (this.mUkey2client != null) {
            throw new IllegalStateException("This runner is already initialized.");
        }
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public HandshakeMessage continueHandshake(byte[] response) throws HandshakeException {
        checkInitialized();
        try {
            if (this.mUkey2client.getHandshakeState() != Ukey2Handshake.State.IN_PROGRESS) {
                throw new IllegalStateException("handshake is not in progress, state =" + this.mUkey2client.getHandshakeState());
            }
            this.mUkey2client.parseHandshakeMessage(response);
            byte[] nextMessage = null;
            if (this.mUkey2client.getHandshakeState() == Ukey2Handshake.State.IN_PROGRESS) {
                nextMessage = this.mUkey2client.getNextHandshakeMessage();
            }
            String verificationCode = null;
            if (this.mUkey2client.getHandshakeState() == Ukey2Handshake.State.VERIFICATION_NEEDED) {
                verificationCode = generateReadablePairingCode(this.mUkey2client.getVerificationString(6));
                if (this.mIsReconnect) {
                    HandshakeMessage handshakeMessage = verifyPin();
                    return HandshakeMessage.newBuilder().setHandshakeState(handshakeMessage.getHandshakeState()).setNextMessage(nextMessage).build();
                }
            }
            return HandshakeMessage.newBuilder().setHandshakeState(getHandshakeState()).setNextMessage(nextMessage).setVerificationCode(verificationCode).build();
        } catch (com.google.security.cryptauth.lib.securegcm.HandshakeException | Ukey2Handshake.AlertException e) {
            throw new HandshakeException(e);
        }
    }

    String generateReadablePairingCode(byte[] verificationCode) {
        StringBuilder outString = new StringBuilder();
        for (byte b : verificationCode) {
            int unsignedInt = Byte.toUnsignedInt(b);
            int digit = unsignedInt % 10;
            outString.append(digit);
        }
        return outString.toString();
    }

    private static class UKey2Key implements Key {
        private final D2DConnectionContext mConnectionContext;

        UKey2Key(D2DConnectionContext connectionContext) {
            this.mConnectionContext = connectionContext;
        }

        @Override // android.car.encryptionrunner.Key
        public byte[] asBytes() {
            return this.mConnectionContext.saveSession();
        }

        @Override // android.car.encryptionrunner.Key
        public byte[] encryptData(byte[] data) {
            return this.mConnectionContext.encodeMessageToPeer(data);
        }

        @Override // android.car.encryptionrunner.Key
        public byte[] decryptData(byte[] encryptedData) throws SignatureException {
            return this.mConnectionContext.decodeMessageFromPeer(encryptedData);
        }

        @Override // android.car.encryptionrunner.Key
        public byte[] getUniqueSession() throws NoSuchAlgorithmException {
            return this.mConnectionContext.getSessionUnique();
        }
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public HandshakeMessage verifyPin() throws HandshakeException {
        checkInitialized();
        this.mUkey2client.verifyHandshake();
        int state = getHandshakeState();
        try {
            this.mCurrentKey = new UKey2Key(this.mUkey2client.toConnectionContext());
            return HandshakeMessage.newBuilder().setHandshakeState(state).setKey(this.mCurrentKey).build();
        } catch (com.google.security.cryptauth.lib.securegcm.HandshakeException e) {
            throw new HandshakeException(e);
        }
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public HandshakeMessage authenticateReconnection(byte[] message, byte[] previousKey) throws HandshakeException {
        if (!this.mIsReconnect) {
            throw new HandshakeException("Reconnection authentication requires setIsReconnect(true)");
        }
        Key key = this.mCurrentKey;
        if (key == null) {
            throw new HandshakeException("Current key is null, make sure verifyPin() is called.");
        }
        if (message.length != 32) {
            this.mRunnerIsInvalid = true;
            throw new HandshakeException("Failing because (message.length =" + message.length + ") is not equal to 32");
        }
        try {
            this.mCurrentUniqueSesion = key.getUniqueSession();
            byte[] uniqueSession = keyOf(previousKey).getUniqueSession();
            this.mPrevUniqueSesion = uniqueSession;
            int i = this.mMode;
            if (i == 1) {
                if (!MessageDigest.isEqual(message, computeMAC(uniqueSession, this.mCurrentUniqueSesion, SERVER))) {
                    this.mRunnerIsInvalid = true;
                    throw new HandshakeException("Reconnection authentication failed.");
                }
                return HandshakeMessage.newBuilder().setHandshakeState(3).setKey(this.mCurrentKey).setNextMessage(this.mInitReconnectionVerification ? null : computeMAC(this.mPrevUniqueSesion, this.mCurrentUniqueSesion, CLIENT)).build();
            }
            if (i == 2) {
                if (!MessageDigest.isEqual(message, computeMAC(uniqueSession, this.mCurrentUniqueSesion, CLIENT))) {
                    this.mRunnerIsInvalid = true;
                    throw new HandshakeException("Reconnection authentication failed.");
                }
                return HandshakeMessage.newBuilder().setHandshakeState(3).setKey(this.mCurrentKey).setNextMessage(this.mInitReconnectionVerification ? null : computeMAC(this.mPrevUniqueSesion, this.mCurrentUniqueSesion, SERVER)).build();
            }
            throw new IllegalStateException("Encountered unexpected role during authenticateReconnection: " + this.mMode);
        } catch (NoSuchAlgorithmException e) {
            throw new HandshakeException(e);
        }
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public HandshakeMessage initReconnectAuthentication(byte[] previousKey) throws HandshakeException {
        if (!this.mIsReconnect) {
            throw new HandshakeException("Reconnection authentication requires setIsReconnect(true).");
        }
        Key key = this.mCurrentKey;
        if (key == null) {
            throw new HandshakeException("Current key is null, make sure verifyPin() is called.");
        }
        this.mInitReconnectionVerification = true;
        try {
            this.mCurrentUniqueSesion = key.getUniqueSession();
            this.mPrevUniqueSesion = keyOf(previousKey).getUniqueSession();
            int i = this.mMode;
            if (i == 1) {
                return HandshakeMessage.newBuilder().setHandshakeState(5).setNextMessage(computeMAC(this.mPrevUniqueSesion, this.mCurrentUniqueSesion, CLIENT)).build();
            }
            if (i == 2) {
                return HandshakeMessage.newBuilder().setHandshakeState(5).setNextMessage(computeMAC(this.mPrevUniqueSesion, this.mCurrentUniqueSesion, SERVER)).build();
            }
            throw new IllegalStateException("Encountered unexpected role during authenticateReconnection: " + this.mMode);
        } catch (NoSuchAlgorithmException e) {
            throw new HandshakeException(e);
        }
    }

    protected final Ukey2Handshake getUkey2Client() {
        return this.mUkey2client;
    }

    protected final boolean isReconnect() {
        return this.mIsReconnect;
    }

    /* JADX INFO: renamed from: android.car.encryptionrunner.Ukey2EncryptionRunner$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$State;

        static {
            int[] iArr = new int[Ukey2Handshake.State.values().length];
            $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$State = iArr;
            try {
                iArr[Ukey2Handshake.State.ALREADY_USED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$State[Ukey2Handshake.State.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$State[Ukey2Handshake.State.FINISHED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$State[Ukey2Handshake.State.IN_PROGRESS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$State[Ukey2Handshake.State.VERIFICATION_IN_PROGRESS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$State[Ukey2Handshake.State.VERIFICATION_NEEDED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    private int getHandshakeState() {
        checkInitialized();
        switch (AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$State[this.mUkey2client.getHandshakeState().ordinal()]) {
            case 1:
            case 2:
                throw new IllegalStateException("unexpected error state");
            case 3:
                if (this.mIsReconnect) {
                    return 5;
                }
                return 3;
            case 4:
                return 1;
            case 5:
            case 6:
                return 2;
            default:
                throw new IllegalStateException("unexpected handshake state");
        }
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public Key keyOf(byte[] serialized) {
        return new UKey2Key(D2DConnectionContext.fromSavedSession(serialized));
    }

    @Override // android.car.encryptionrunner.EncryptionRunner
    public void invalidPin() {
        this.mRunnerIsInvalid = true;
    }

    private UKey2Key checkIsUkey2Key(Key key) {
        if (!(key instanceof UKey2Key)) {
            throw new IllegalArgumentException("wrong key type");
        }
        return (UKey2Key) key;
    }

    protected void checkInitialized() {
        if (this.mUkey2client == null) {
            throw new IllegalStateException("runner not initialized");
        }
        if (this.mRunnerIsInvalid) {
            throw new IllegalStateException("runner has been invalidated");
        }
    }

    private byte[] computeMAC(byte[] previous, byte[] next, byte[] info) {
        try {
            SecretKeySpec inputKeyMaterial = new SecretKeySpec(concatByteArrays(previous, next), "");
            return CryptoOps.hkdf(inputKeyMaterial, RESUME, info);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            Log.e(EncryptionRunner.TAG, "Compute MAC failed");
            return null;
        }
    }

    private static byte[] concatByteArrays(byte[] a, byte[] b) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(a);
            outputStream.write(b);
            return outputStream.toByteArray();
        } catch (IOException e) {
            return new byte[0];
        }
    }
}
