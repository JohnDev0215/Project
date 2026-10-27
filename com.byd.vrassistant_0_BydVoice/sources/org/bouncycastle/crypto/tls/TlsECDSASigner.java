package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

/* JADX INFO: loaded from: classes4.dex */
class TlsECDSASigner extends TlsDSASigner {
    TlsECDSASigner() {
    }

    @Override // org.bouncycastle.crypto.tls.TlsDSASigner
    protected DSA createDSAImpl() {
        return new ECDSASigner();
    }

    @Override // org.bouncycastle.crypto.tls.TlsSigner
    public boolean isValidPublicKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        return asymmetricKeyParameter instanceof ECPublicKeyParameters;
    }
}
