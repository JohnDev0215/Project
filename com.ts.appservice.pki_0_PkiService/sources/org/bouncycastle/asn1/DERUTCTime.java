package org.bouncycastle.asn1;

import java.util.Date;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class DERUTCTime extends ASN1UTCTime {
    public DERUTCTime(String str) {
        super(str);
    }

    public DERUTCTime(Date date) {
        super(date);
    }

    DERUTCTime(byte[] bArr) {
        super(bArr);
    }
}
