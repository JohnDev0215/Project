package org.bouncycastle.operator;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface SecretKeySizeProvider {
    int getKeySize(ASN1ObjectIdentifier aSN1ObjectIdentifier);

    int getKeySize(AlgorithmIdentifier algorithmIdentifier);
}
