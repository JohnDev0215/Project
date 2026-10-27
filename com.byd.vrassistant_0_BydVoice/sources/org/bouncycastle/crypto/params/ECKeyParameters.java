package org.bouncycastle.crypto.params;

/* JADX INFO: loaded from: classes4.dex */
public class ECKeyParameters extends AsymmetricKeyParameter {
    ECDomainParameters params;

    protected ECKeyParameters(boolean z, ECDomainParameters eCDomainParameters) {
        super(z);
        this.params = eCDomainParameters;
    }

    public ECDomainParameters getParameters() {
        return this.params;
    }
}
