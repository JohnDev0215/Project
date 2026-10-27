package org.bouncycastle.asn1;

import java.util.Date;

/* JADX INFO: loaded from: classes4.dex */
public class ASN1GeneralizedTime extends DERGeneralizedTime {
    public ASN1GeneralizedTime(String str) {
        super(str);
    }

    public ASN1GeneralizedTime(Date date) {
        super(date);
    }

    ASN1GeneralizedTime(byte[] bArr) {
        super(bArr);
    }
}
