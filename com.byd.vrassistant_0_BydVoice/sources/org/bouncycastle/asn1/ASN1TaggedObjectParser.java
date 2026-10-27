package org.bouncycastle.asn1;

import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public interface ASN1TaggedObjectParser extends DEREncodable, InMemoryRepresentable {
    DEREncodable getObjectParser(int i, boolean z) throws IOException;

    int getTagNo();
}
