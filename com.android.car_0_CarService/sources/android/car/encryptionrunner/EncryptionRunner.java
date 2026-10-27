package android.car.encryptionrunner;

/* JADX INFO: loaded from: classes.dex */
public interface EncryptionRunner {
    public static final String TAG = "EncryptionRunner";

    HandshakeMessage authenticateReconnection(byte[] bArr, byte[] bArr2) throws HandshakeException;

    HandshakeMessage continueHandshake(byte[] bArr) throws HandshakeException;

    HandshakeMessage initHandshake();

    HandshakeMessage initReconnectAuthentication(byte[] bArr) throws HandshakeException;

    void invalidPin();

    Key keyOf(byte[] bArr);

    HandshakeMessage respondToInitRequest(byte[] bArr) throws HandshakeException;

    void setIsReconnect(boolean z);

    HandshakeMessage verifyPin() throws HandshakeException;
}
