package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.DERInteger;

/* JADX INFO: loaded from: classes4.dex */
public class SubsequentMessage extends DERInteger {
    public static final SubsequentMessage encrCert = new SubsequentMessage(0);
    public static final SubsequentMessage challengeResp = new SubsequentMessage(1);

    private SubsequentMessage(int i) {
        super(i);
    }

    public static SubsequentMessage valueOf(int i) {
        if (i == 0) {
            return encrCert;
        }
        if (i == 1) {
            return challengeResp;
        }
        throw new IllegalArgumentException("unknown value: " + i);
    }
}
