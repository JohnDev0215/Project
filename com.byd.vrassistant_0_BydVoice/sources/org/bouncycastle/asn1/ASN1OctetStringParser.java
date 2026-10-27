package org.bouncycastle.asn1;

import java.io.InputStream;

/* JADX INFO: loaded from: classes4.dex */
public interface ASN1OctetStringParser extends DEREncodable, InMemoryRepresentable {
    InputStream getOctetStream();
}
