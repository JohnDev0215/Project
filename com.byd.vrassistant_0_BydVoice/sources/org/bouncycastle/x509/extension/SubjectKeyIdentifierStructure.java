package org.bouncycastle.x509.extension;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

/* JADX INFO: loaded from: classes4.dex */
public class SubjectKeyIdentifierStructure extends SubjectKeyIdentifier {
    public SubjectKeyIdentifierStructure(PublicKey publicKey) throws InvalidKeyException {
        super(fromPublicKey(publicKey));
    }

    public SubjectKeyIdentifierStructure(byte[] bArr) throws IOException {
        super((ASN1OctetString) X509ExtensionUtil.fromExtensionValue(bArr));
    }

    private static ASN1OctetString fromPublicKey(PublicKey publicKey) throws InvalidKeyException {
        try {
            ASN1OctetString aSN1OctetString = (ASN1OctetString) new SubjectKeyIdentifier(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded())).toASN1Object();
            return aSN1OctetString;
        } catch (Exception e) {
            throw new InvalidKeyException("Exception extracting key details: " + e.toString());
        }
    }
}
