package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import org.bouncycastle.asn1.x509.X509CertificateStructure;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.agreement.srp.SRP6Client;
import org.bouncycastle.crypto.agreement.srp.SRP6Util;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.io.SignerInputStream;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.BigIntegers;

/* JADX INFO: loaded from: classes4.dex */
class TlsSRPKeyExchange implements TlsKeyExchange {
    protected TlsClientContext context;
    protected byte[] identity;
    protected int keyExchange;
    protected byte[] password;
    protected TlsSigner tlsSigner;
    protected AsymmetricKeyParameter serverPublicKey = null;
    protected byte[] s = null;
    protected BigInteger B = null;
    protected SRP6Client srpClient = new SRP6Client();

    TlsSRPKeyExchange(TlsClientContext tlsClientContext, int i, byte[] bArr, byte[] bArr2) {
        TlsSigner tlsDSSSigner = null;
        switch (i) {
            case 21:
                break;
            case 22:
                tlsDSSSigner = new TlsDSSSigner();
                break;
            case 23:
                tlsDSSSigner = new TlsRSASigner();
                break;
            default:
                throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
        this.tlsSigner = tlsDSSSigner;
        this.context = tlsClientContext;
        this.keyExchange = i;
        this.identity = bArr;
        this.password = bArr2;
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) throws IOException {
        byte[] bArrAsUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.srpClient.generateClientCredentials(this.s, this.identity, this.password));
        TlsUtils.writeUint24(bArrAsUnsignedByteArray.length + 2, outputStream);
        TlsUtils.writeOpaque16(bArrAsUnsignedByteArray, outputStream);
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public byte[] generatePremasterSecret() throws IOException {
        try {
            return BigIntegers.asUnsignedByteArray(this.srpClient.calculateSecret(this.B));
        } catch (CryptoException unused) {
            throw new TlsFatalAlert((short) 47);
        }
    }

    protected Signer initSigner(TlsSigner tlsSigner, SecurityParameters securityParameters) {
        Signer signerCreateVerifyer = tlsSigner.createVerifyer(this.serverPublicKey);
        signerCreateVerifyer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        signerCreateVerifyer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return signerCreateVerifyer;
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processClientCredentials(TlsCredentials tlsCredentials) throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) throws IOException {
        if (this.tlsSigner == null) {
            throw new TlsFatalAlert((short) 10);
        }
        X509CertificateStructure x509CertificateStructure = certificate.certs[0];
        try {
            AsymmetricKeyParameter asymmetricKeyParameterCreateKey = PublicKeyFactory.createKey(x509CertificateStructure.getSubjectPublicKeyInfo());
            this.serverPublicKey = asymmetricKeyParameterCreateKey;
            if (!this.tlsSigner.isValidPublicKey(asymmetricKeyParameterCreateKey)) {
                throw new TlsFatalAlert((short) 46);
            }
            TlsUtils.validateKeyUsage(x509CertificateStructure, 128);
        } catch (RuntimeException unused) {
            throw new TlsFatalAlert((short) 43);
        }
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        Signer signerInitSigner;
        InputStream signerInputStream;
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        TlsSigner tlsSigner = this.tlsSigner;
        if (tlsSigner != null) {
            signerInitSigner = initSigner(tlsSigner, securityParameters);
            signerInputStream = new SignerInputStream(inputStream, signerInitSigner);
        } else {
            signerInitSigner = null;
            signerInputStream = inputStream;
        }
        byte[] opaque16 = TlsUtils.readOpaque16(signerInputStream);
        byte[] opaque162 = TlsUtils.readOpaque16(signerInputStream);
        byte[] opaque8 = TlsUtils.readOpaque8(signerInputStream);
        byte[] opaque163 = TlsUtils.readOpaque16(signerInputStream);
        if (signerInitSigner != null && !signerInitSigner.verifySignature(TlsUtils.readOpaque16(inputStream))) {
            throw new TlsFatalAlert((short) 42);
        }
        BigInteger bigInteger = new BigInteger(1, opaque16);
        BigInteger bigInteger2 = new BigInteger(1, opaque162);
        this.s = opaque8;
        try {
            this.B = SRP6Util.validatePublicValue(bigInteger, new BigInteger(1, opaque163));
            this.srpClient.init(bigInteger, bigInteger2, new SHA1Digest(), this.context.getSecureRandom());
        } catch (CryptoException unused) {
            throw new TlsFatalAlert((short) 47);
        }
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void skipClientCredentials() throws IOException {
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void skipServerCertificate() throws IOException {
        if (this.tlsSigner != null) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void skipServerKeyExchange() throws IOException {
        throw new TlsFatalAlert((short) 10);
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        throw new TlsFatalAlert((short) 10);
    }
}
