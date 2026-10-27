package org.bouncycastle.asn1;

import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public interface ASN1SequenceParser extends DEREncodable, InMemoryRepresentable {
    DEREncodable readObject() throws IOException;
}
