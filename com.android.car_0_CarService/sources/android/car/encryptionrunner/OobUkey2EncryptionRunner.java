package android.car.encryptionrunner;

import com.google.security.cryptauth.lib.securegcm.Ukey2Handshake;

/* JADX INFO: loaded from: classes.dex */
public final class OobUkey2EncryptionRunner extends Ukey2EncryptionRunner {
    private static final int VERIFICATION_STRING_LENGTH = 32;

    @Override // android.car.encryptionrunner.Ukey2EncryptionRunner, android.car.encryptionrunner.EncryptionRunner
    public HandshakeMessage continueHandshake(byte[] response) throws HandshakeException {
        checkInitialized();
        Ukey2Handshake uKey2Client = getUkey2Client();
        try {
            if (uKey2Client.getHandshakeState() != Ukey2Handshake.State.IN_PROGRESS) {
                throw new IllegalStateException("handshake is not in progress, state =" + uKey2Client.getHandshakeState());
            }
            uKey2Client.parseHandshakeMessage(response);
            byte[] nextMessage = null;
            if (uKey2Client.getHandshakeState() == Ukey2Handshake.State.IN_PROGRESS) {
                nextMessage = uKey2Client.getNextHandshakeMessage();
            }
            byte[] verificationCode = null;
            if (uKey2Client.getHandshakeState() == Ukey2Handshake.State.VERIFICATION_NEEDED) {
                verificationCode = uKey2Client.getVerificationString(32);
                if (isReconnect()) {
                    HandshakeMessage handshakeMessage = verifyPin();
                    return HandshakeMessage.newBuilder().setHandshakeState(handshakeMessage.getHandshakeState()).setNextMessage(nextMessage).build();
                }
            }
            return HandshakeMessage.newBuilder().setHandshakeState(6).setNextMessage(nextMessage).setOobVerificationCode(verificationCode).build();
        } catch (com.google.security.cryptauth.lib.securegcm.HandshakeException | Ukey2Handshake.AlertException e) {
            throw new HandshakeException(e);
        }
    }
}
