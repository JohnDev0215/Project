package org.apache.shiro.crypto.hash;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecException;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.UnknownAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class AbstractHash extends CodecSupport implements Hash, Serializable {
    private transient String base64Encoded;
    private byte[] bytes;
    private transient String hexEncoded;

    @Override // org.apache.shiro.crypto.hash.Hash
    public abstract String getAlgorithmName();

    public AbstractHash() {
        this.bytes = null;
        this.hexEncoded = null;
        this.base64Encoded = null;
    }

    public AbstractHash(Object obj) throws CodecException {
        this(obj, null, 1);
    }

    public AbstractHash(Object obj, Object obj2) throws CodecException {
        this(obj, obj2, 1);
    }

    public AbstractHash(Object obj, Object obj2, int i) throws CodecException {
        this.bytes = null;
        this.hexEncoded = null;
        this.base64Encoded = null;
        setBytes(hash(toBytes(obj), obj2 != null ? toBytes(obj2) : null, i));
    }

    @Override // org.apache.shiro.util.ByteSource
    public byte[] getBytes() {
        return this.bytes;
    }

    public void setBytes(byte[] bArr) {
        this.bytes = bArr;
        this.hexEncoded = null;
        this.base64Encoded = null;
    }

    protected MessageDigest getDigest(String str) throws UnknownAlgorithmException {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new UnknownAlgorithmException("No native '" + str + "' MessageDigest instance available on the current JVM.", e);
        }
    }

    protected byte[] hash(byte[] bArr) {
        return hash(bArr, null, 1);
    }

    protected byte[] hash(byte[] bArr, byte[] bArr2) {
        return hash(bArr, bArr2, 1);
    }

    protected byte[] hash(byte[] bArr, byte[] bArr2, int i) throws UnknownAlgorithmException {
        MessageDigest digest = getDigest(getAlgorithmName());
        if (bArr2 != null) {
            digest.reset();
            digest.update(bArr2);
        }
        byte[] bArrDigest = digest.digest(bArr);
        int i2 = i - 1;
        for (int i3 = 0; i3 < i2; i3++) {
            digest.reset();
            bArrDigest = digest.digest(bArrDigest);
        }
        return bArrDigest;
    }

    @Override // org.apache.shiro.util.ByteSource
    public String toHex() {
        if (this.hexEncoded == null) {
            this.hexEncoded = Hex.encodeToString(getBytes());
        }
        return this.hexEncoded;
    }

    @Override // org.apache.shiro.util.ByteSource
    public String toBase64() {
        if (this.base64Encoded == null) {
            this.base64Encoded = Base64.encodeToString(getBytes());
        }
        return this.base64Encoded;
    }

    public String toString() {
        return toHex();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Hash) {
            return MessageDigest.isEqual(getBytes(), ((Hash) obj).getBytes());
        }
        return false;
    }

    public int hashCode() {
        byte[] bArr = this.bytes;
        if (bArr == null || bArr.length == 0) {
            return 0;
        }
        return Arrays.hashCode(bArr);
    }

    private static void printMainUsage(Class<? extends AbstractHash> cls, String str) {
        System.out.println("Prints an " + str + " hash value.");
        System.out.println("Usage: java " + cls.getName() + " [-base64] [-salt <saltValue>] [-times <N>] <valueToHash>");
        System.out.println("Options:");
        System.out.println("\t-base64\t\tPrints the hash value as a base64 String instead of the default hex.");
        System.out.println("\t-salt\t\tSalts the hash with the specified <saltValue>");
        System.out.println("\t-times\t\tHashes the input <N> number of times");
    }

    private static boolean isReserved(String str) {
        return "-base64".equals(str) || "-times".equals(str) || "-salt".equals(str);
    }

    static int doMain(Class<? extends AbstractHash> cls, String[] strArr) {
        String simpleName = cls.getSimpleName();
        String upperCase = simpleName.substring(0, simpleName.indexOf("Hash")).toUpperCase();
        if (strArr == null || strArr.length < 1 || strArr.length > 7) {
            printMainUsage(cls, upperCase);
            return -1;
        }
        String str = null;
        String str2 = strArr[strArr.length - 1];
        int iIntValue = 1;
        boolean z = true;
        for (int i = 0; i < strArr.length; i++) {
            String str3 = strArr[i];
            if (str3.equals("-base64")) {
                z = false;
            } else if (str3.equals("-salt")) {
                int i2 = i + 1;
                if (i2 >= strArr.length - 1) {
                    System.out.println("Salt argument must be followed by a salt value.  The final argument is reserved for the value to hash.");
                    printMainUsage(cls, upperCase);
                    return -1;
                }
                str = strArr[i2];
            } else if (str3.equals("-times")) {
                int i3 = i + 1;
                if (i3 >= strArr.length - 1) {
                    System.out.println("Times argument must be followed by an integer value.  The final argument is reserved for the value to hash");
                    printMainUsage(cls, upperCase);
                    return -1;
                }
                try {
                    iIntValue = Integer.valueOf(strArr[i3]).intValue();
                } catch (NumberFormatException unused) {
                    System.out.println("Times argument must be followed by an integer value.");
                    printMainUsage(cls, upperCase);
                    return -1;
                }
            } else {
                continue;
            }
        }
        Md2Hash md2Hash = new Md2Hash(str2, str, iIntValue);
        String hex = z ? md2Hash.toHex() : md2Hash.toBase64();
        System.out.print(z ? "Hex: " : "Base64: ");
        System.out.println(hex);
        return 0;
    }
}
