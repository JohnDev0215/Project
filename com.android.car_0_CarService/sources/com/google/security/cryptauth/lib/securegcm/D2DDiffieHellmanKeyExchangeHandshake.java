package com.google.security.cryptauth.lib.securegcm;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto;
import com.google.security.cryptauth.lib.securegcm.TransportCryptoOps;
import com.google.security.cryptauth.lib.securemessage.PublicKeyProtoUtil;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;

/* JADX INFO: loaded from: classes3.dex */
public class D2DDiffieHellmanKeyExchangeHandshake implements D2DHandshakeContext {
    private static final String INITIATOR_PURPOSE = "initiator";
    private static final String RESPONDER_PURPOSE = "responder";
    private State handshakeState;
    private boolean isInitiator;
    private int protocolVersionToUse;
    private KeyPair ourKeyPair = PublicKeyProtoUtil.generateEcP256KeyPair();
    private PublicKey theirPublicKey = null;
    private SecretKey initiatorEncodeKey = null;
    private SecretKey responderEncodeKey = null;

    private enum State {
        INITIATOR_START,
        INITIATOR_WAITING_FOR_RESPONDER_HELLO,
        RESPONDER_START,
        RESPONDER_AFTER_INITIATOR_HELLO,
        HANDSHAKE_FINISHED,
        HANDSHAKE_ALREADY_USED
    }

    private D2DDiffieHellmanKeyExchangeHandshake(State state) {
        this.handshakeState = state;
        this.isInitiator = state == State.INITIATOR_START;
        this.protocolVersionToUse = 1;
    }

    public static D2DDiffieHellmanKeyExchangeHandshake forInitiator() {
        return new D2DDiffieHellmanKeyExchangeHandshake(State.INITIATOR_START);
    }

    public static D2DDiffieHellmanKeyExchangeHandshake forResponder() {
        return new D2DDiffieHellmanKeyExchangeHandshake(State.RESPONDER_START);
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DHandshakeContext
    public boolean isHandshakeComplete() {
        return this.handshakeState == State.HANDSHAKE_FINISHED || this.handshakeState == State.HANDSHAKE_ALREADY_USED;
    }

    /* JADX INFO: renamed from: com.google.security.cryptauth.lib.securegcm.D2DDiffieHellmanKeyExchangeHandshake$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$security$cryptauth$lib$securegcm$D2DDiffieHellmanKeyExchangeHandshake$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$google$security$cryptauth$lib$securegcm$D2DDiffieHellmanKeyExchangeHandshake$State = iArr;
            try {
                iArr[State.INITIATOR_START.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$D2DDiffieHellmanKeyExchangeHandshake$State[State.RESPONDER_AFTER_INITIATOR_HELLO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$D2DDiffieHellmanKeyExchangeHandshake$State[State.INITIATOR_WAITING_FOR_RESPONDER_HELLO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$D2DDiffieHellmanKeyExchangeHandshake$State[State.RESPONDER_START.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DHandshakeContext
    public byte[] getNextHandshakeMessage() throws HandshakeException {
        int i = AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$D2DDiffieHellmanKeyExchangeHandshake$State[this.handshakeState.ordinal()];
        if (i == 1) {
            this.handshakeState = State.INITIATOR_WAITING_FOR_RESPONDER_HELLO;
            return DeviceToDeviceMessagesProto.InitiatorHello.newBuilder().setPublicDhKey(PublicKeyProtoUtil.encodePublicKey(this.ourKeyPair.getPublic())).setProtocolVersion(this.protocolVersionToUse).build().toByteArray();
        }
        if (i == 2) {
            byte[] responderHello = makeResponderHelloWithPayload(new byte[0]);
            this.handshakeState = State.HANDSHAKE_FINISHED;
            return responderHello;
        }
        throw new HandshakeException("Cannot get next message in state: " + this.handshakeState);
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DHandshakeContext
    public boolean canSendPayloadInHandshakeMessage() {
        return this.handshakeState == State.RESPONDER_AFTER_INITIATOR_HELLO;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DHandshakeContext
    public byte[] getNextHandshakeMessage(byte[] payload) throws HandshakeException {
        if (this.handshakeState != State.RESPONDER_AFTER_INITIATOR_HELLO) {
            throw new HandshakeException("Cannot get next message with payload in state: " + this.handshakeState);
        }
        byte[] responderHello = makeResponderHelloWithPayload(payload);
        this.handshakeState = State.HANDSHAKE_FINISHED;
        return responderHello;
    }

    private byte[] makeResponderHelloWithPayload(byte[] payload) throws HandshakeException {
        if (payload == null) {
            throw new HandshakeException("Not expecting null payload");
        }
        try {
            SecretKey masterKey = EnrollmentCryptoOps.doKeyAgreement(this.ourKeyPair.getPrivate(), this.theirPublicKey);
            int i = this.protocolVersionToUse;
            if (i == 0) {
                this.initiatorEncodeKey = masterKey;
                this.responderEncodeKey = masterKey;
            } else if (i == 1) {
                this.initiatorEncodeKey = D2DCryptoOps.deriveNewKeyForPurpose(masterKey, INITIATOR_PURPOSE);
                this.responderEncodeKey = D2DCryptoOps.deriveNewKeyForPurpose(masterKey, RESPONDER_PURPOSE);
            } else {
                throw new IllegalStateException("Unexpected protocol version: " + this.protocolVersionToUse);
            }
            DeviceToDeviceMessagesProto.DeviceToDeviceMessage deviceToDeviceMessage = D2DConnectionContext.createDeviceToDeviceMessage(payload, 1);
            return D2DCryptoOps.signcryptMessageAndResponderHello(new TransportCryptoOps.Payload(TransportCryptoOps.PayloadType.DEVICE_TO_DEVICE_RESPONDER_HELLO_PAYLOAD, deviceToDeviceMessage.toByteArray()), this.responderEncodeKey, this.ourKeyPair.getPublic(), this.protocolVersionToUse);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new HandshakeException(e);
        }
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DHandshakeContext
    public byte[] parseHandshakeMessage(byte[] handshakeMessage) throws HandshakeException {
        if (handshakeMessage == null || handshakeMessage.length == 0) {
            throw new HandshakeException("Handshake message too short");
        }
        int i = AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$D2DDiffieHellmanKeyExchangeHandshake$State[this.handshakeState.ordinal()];
        if (i == 3) {
            byte[] payload = parseResponderHello(handshakeMessage);
            this.handshakeState = State.HANDSHAKE_FINISHED;
            return payload;
        }
        if (i == 4) {
            parseInitiatorHello(handshakeMessage);
            this.handshakeState = State.RESPONDER_AFTER_INITIATOR_HELLO;
            return new byte[0];
        }
        throw new HandshakeException("Cannot parse message in state: " + this.handshakeState);
    }

    private byte[] parseResponderHello(byte[] responderHello) throws HandshakeException {
        try {
            DeviceToDeviceMessagesProto.ResponderHello responderHelloProto = D2DCryptoOps.parseAndValidateResponderHello(responderHello);
            int protocolVersion = responderHelloProto.getProtocolVersion();
            if (protocolVersion == 0) {
                this.protocolVersionToUse = 0;
            }
            SecretKey masterKey = D2DCryptoOps.deriveSharedKeyFromGenericPublicKey(this.ourKeyPair.getPrivate(), responderHelloProto.getPublicDhKey());
            if (this.protocolVersionToUse == 0) {
                this.initiatorEncodeKey = masterKey;
                this.responderEncodeKey = masterKey;
            } else {
                this.initiatorEncodeKey = D2DCryptoOps.deriveNewKeyForPurpose(masterKey, INITIATOR_PURPOSE);
                this.responderEncodeKey = D2DCryptoOps.deriveNewKeyForPurpose(masterKey, RESPONDER_PURPOSE);
            }
            DeviceToDeviceMessagesProto.DeviceToDeviceMessage message = D2DCryptoOps.decryptResponderHelloMessage(this.responderEncodeKey, responderHello);
            if (message.getSequenceNumber() != 1) {
                throw new HandshakeException("Incorrect sequence number in responder hello");
            }
            return message.getMessage().toByteArray();
        } catch (InvalidProtocolBufferException | InvalidKeyException | NoSuchAlgorithmException | SignatureException e) {
            throw new HandshakeException(e);
        }
    }

    private void parseInitiatorHello(byte[] initiatorHello) throws HandshakeException {
        try {
            DeviceToDeviceMessagesProto.InitiatorHello initiatorHelloProto = DeviceToDeviceMessagesProto.InitiatorHello.parseFrom(initiatorHello);
            if (!initiatorHelloProto.hasPublicDhKey()) {
                throw new HandshakeException("Missing public key in initiator hello");
            }
            this.theirPublicKey = PublicKeyProtoUtil.parsePublicKey(initiatorHelloProto.getPublicDhKey());
            int protocolVersion = initiatorHelloProto.getProtocolVersion();
            if (protocolVersion == 0) {
                this.protocolVersionToUse = 0;
            }
        } catch (InvalidProtocolBufferException | InvalidKeySpecException e) {
            throw new HandshakeException(e);
        }
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DHandshakeContext
    public D2DConnectionContext toConnectionContext() throws HandshakeException {
        if (this.handshakeState == State.HANDSHAKE_ALREADY_USED) {
            throw new HandshakeException("Cannot reuse handshake context; is has already been used");
        }
        if (!isHandshakeComplete()) {
            throw new HandshakeException("Handshake is not complete; cannot create connection context");
        }
        this.handshakeState = State.HANDSHAKE_ALREADY_USED;
        if (this.protocolVersionToUse == 0) {
            return new D2DConnectionContextV0(this.initiatorEncodeKey, 1);
        }
        SecretKey secretKey = this.isInitiator ? this.initiatorEncodeKey : this.responderEncodeKey;
        SecretKey secretKey2 = this.isInitiator ? this.responderEncodeKey : this.initiatorEncodeKey;
        boolean z = this.isInitiator;
        return new D2DConnectionContextV1(secretKey, secretKey2, !z ? 1 : 0, z ? 1 : 0);
    }
}
