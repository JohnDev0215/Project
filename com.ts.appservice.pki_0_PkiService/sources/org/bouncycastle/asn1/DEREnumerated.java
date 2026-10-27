package org.bouncycastle.asn1;

import java.math.BigInteger;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class DEREnumerated extends ASN1Enumerated {
    public DEREnumerated(int i) {
        super(i);
    }

    public DEREnumerated(BigInteger bigInteger) {
        super(bigInteger);
    }

    DEREnumerated(byte[] bArr) {
        super(bArr);
    }
}
