package org.bouncycastle.asn1.pkcs;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.DERSequence;

/* JADX INFO: loaded from: classes4.dex */
public class PBES2Parameters extends ASN1Encodable implements PKCSObjectIdentifiers {
    private KeyDerivationFunc func;
    private EncryptionScheme scheme;

    public PBES2Parameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(((DEREncodable) objects.nextElement()).getDERObject());
        this.func = aSN1Sequence2.getObjectAt(0).equals(id_PBKDF2) ? new KeyDerivationFunc(id_PBKDF2, PBKDF2Params.getInstance(aSN1Sequence2.getObjectAt(1))) : new KeyDerivationFunc(aSN1Sequence2);
        this.scheme = (EncryptionScheme) EncryptionScheme.getInstance(objects.nextElement());
    }

    public static PBES2Parameters getInstance(Object obj) {
        if (obj == null || (obj instanceof PBES2Parameters)) {
            return (PBES2Parameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new PBES2Parameters((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public EncryptionScheme getEncryptionScheme() {
        return this.scheme;
    }

    public KeyDerivationFunc getKeyDerivationFunc() {
        return this.func;
    }

    @Override // org.bouncycastle.asn1.ASN1Encodable
    public DERObject toASN1Object() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.func);
        aSN1EncodableVector.add(this.scheme);
        return new DERSequence(aSN1EncodableVector);
    }
}
