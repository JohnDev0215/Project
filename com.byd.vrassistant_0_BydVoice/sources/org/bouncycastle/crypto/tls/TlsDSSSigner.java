package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.signers.DSASigner;

/* JADX INFO: loaded from: classes4.dex */
class TlsDSSSigner extends TlsDSASigner {
    TlsDSSSigner() {
    }

    @Override // org.bouncycastle.crypto.tls.TlsDSASigner
    protected DSA createDSAImpl() {
        return new DSASigner();
    }

    @Override // org.bouncycastle.crypto.tls.TlsSigner
    public boolean isValidPublicKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        return asymmetricKeyParameter instanceof DSAPublicKeyParameters;
    }
}
