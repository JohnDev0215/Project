package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes4.dex */
public class DERObjectIdentifier extends ASN1Object {
    String identifier;

    public DERObjectIdentifier(String str) {
        if (!isValidIdentifier(str)) {
            throw new IllegalArgumentException("string " + str + " not an OID");
        }
        this.identifier = str;
    }

    DERObjectIdentifier(byte[] bArr) {
        BigInteger bigIntegerOr;
        long j;
        StringBuffer stringBuffer = new StringBuffer();
        int i = 1;
        boolean z = true;
        int i2 = 0;
        long j2 = 0;
        BigInteger bigInteger = null;
        while (i2 != bArr.length) {
            int i3 = bArr[i2] & 255;
            if (j2 < 36028797018963968L) {
                j2 = (j2 * 128) + ((long) (i3 & 127));
                if ((i3 & 128) == 0) {
                    if (z) {
                        int i4 = ((int) j2) / 40;
                        if (i4 != 0) {
                            if (i4 != i) {
                                stringBuffer.append('2');
                                j = 80;
                            } else {
                                stringBuffer.append('1');
                                j = 40;
                            }
                            j2 -= j;
                        } else {
                            stringBuffer.append('0');
                        }
                        z = false;
                    }
                    stringBuffer.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH);
                    stringBuffer.append(j2);
                    bigIntegerOr = bigInteger;
                    j2 = 0;
                } else {
                    bigIntegerOr = bigInteger;
                }
            } else {
                bigIntegerOr = (bigInteger == null ? BigInteger.valueOf(j2) : bigInteger).shiftLeft(7).or(BigInteger.valueOf(i3 & 127));
                if ((i3 & 128) == 0) {
                    stringBuffer.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH);
                    stringBuffer.append(bigIntegerOr);
                    bigIntegerOr = null;
                    j2 = 0;
                }
            }
            i2++;
            bigInteger = bigIntegerOr;
            i = 1;
        }
        this.identifier = stringBuffer.toString();
    }

    public static DERObjectIdentifier getInstance(Object obj) {
        if (obj == null || (obj instanceof DERObjectIdentifier)) {
            return (DERObjectIdentifier) obj;
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static DERObjectIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        DERObject object = aSN1TaggedObject.getObject();
        return (z || (object instanceof DERObjectIdentifier)) ? getInstance(object) : new ASN1ObjectIdentifier(ASN1OctetString.getInstance(aSN1TaggedObject.getObject()).getOctets());
    }

    private static boolean isValidIdentifier(String str) {
        char cCharAt;
        if (str.length() < 3 || str.charAt(1) != '.' || (cCharAt = str.charAt(0)) < '0' || cCharAt > '2') {
            return false;
        }
        boolean z = false;
        for (int length = str.length() - 1; length >= 2; length--) {
            char cCharAt2 = str.charAt(length);
            if ('0' <= cCharAt2 && cCharAt2 <= '9') {
                z = true;
            } else {
                if (cCharAt2 != '.' || !z) {
                    return false;
                }
                z = false;
            }
        }
        return z;
    }

    private void writeField(OutputStream outputStream, long j) throws IOException {
        byte[] bArr = new byte[9];
        int i = 8;
        bArr[8] = (byte) (((int) j) & 127);
        while (j >= 128) {
            j >>= 7;
            i--;
            bArr[i] = (byte) ((((int) j) & 127) | 128);
        }
        outputStream.write(bArr, i, 9 - i);
    }

    private void writeField(OutputStream outputStream, BigInteger bigInteger) throws IOException {
        int iBitLength = (bigInteger.bitLength() + 6) / 7;
        if (iBitLength == 0) {
            outputStream.write(0);
            return;
        }
        byte[] bArr = new byte[iBitLength];
        int i = iBitLength - 1;
        for (int i2 = i; i2 >= 0; i2--) {
            bArr[i2] = (byte) ((bigInteger.intValue() & 127) | 128);
            bigInteger = bigInteger.shiftRight(7);
        }
        bArr[i] = (byte) (bArr[i] & 127);
        outputStream.write(bArr);
    }

    @Override // org.bouncycastle.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof DERObjectIdentifier) {
            return this.identifier.equals(((DERObjectIdentifier) dERObject).identifier);
        }
        return false;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.DERObject
    void encode(DEROutputStream dEROutputStream) throws IOException {
        String strNextToken;
        OIDTokenizer oIDTokenizer = new OIDTokenizer(this.identifier);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DEROutputStream dEROutputStream2 = new DEROutputStream(byteArrayOutputStream);
        long j = (Integer.parseInt(oIDTokenizer.nextToken()) * 40) + Integer.parseInt(oIDTokenizer.nextToken());
        while (true) {
            writeField(byteArrayOutputStream, j);
            while (oIDTokenizer.hasMoreTokens()) {
                strNextToken = oIDTokenizer.nextToken();
                if (strNextToken.length() < 18) {
                    break;
                } else {
                    writeField(byteArrayOutputStream, new BigInteger(strNextToken));
                }
            }
            dEROutputStream2.close();
            dEROutputStream.writeEncoded(6, byteArrayOutputStream.toByteArray());
            return;
            j = Long.parseLong(strNextToken);
        }
    }

    public String getId() {
        return this.identifier;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.DERObject, org.bouncycastle.asn1.ASN1Encodable
    public int hashCode() {
        return this.identifier.hashCode();
    }

    public String toString() {
        return getId();
    }
}
