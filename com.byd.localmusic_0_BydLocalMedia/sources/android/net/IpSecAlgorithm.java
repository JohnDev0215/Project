package android.net;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.HexDump;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class IpSecAlgorithm implements Parcelable {
    public static final String AUTH_CRYPT_AES_GCM = "rfc4106(gcm(aes))";
    public static final String AUTH_HMAC_MD5 = "hmac(md5)";
    public static final String AUTH_HMAC_SHA1 = "hmac(sha1)";
    public static final String AUTH_HMAC_SHA256 = "hmac(sha256)";
    public static final String AUTH_HMAC_SHA384 = "hmac(sha384)";
    public static final String AUTH_HMAC_SHA512 = "hmac(sha512)";
    public static final Parcelable.Creator<IpSecAlgorithm> CREATOR = new Parcelable.Creator<IpSecAlgorithm>() { // from class: android.net.IpSecAlgorithm.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IpSecAlgorithm createFromParcel(Parcel parcel) {
            return new IpSecAlgorithm(parcel.readString(), parcel.createByteArray(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IpSecAlgorithm[] newArray(int i) {
            return new IpSecAlgorithm[i];
        }
    };
    public static final String CRYPT_AES_CBC = "cbc(aes)";
    public static final String CRYPT_NULL = "ecb(cipher_null)";
    private static final String TAG = "IpSecAlgorithm";
    private final byte[] mKey;
    private final String mName;
    private final int mTruncLenBits;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AlgorithmName {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IpSecAlgorithm(String str, byte[] bArr) {
        this(str, bArr, 0);
    }

    public IpSecAlgorithm(String str, byte[] bArr, int i) {
        this.mName = str;
        byte[] bArr2 = (byte[]) bArr.clone();
        this.mKey = bArr2;
        this.mTruncLenBits = i;
        checkValidOrThrow(this.mName, bArr2.length * 8, i);
    }

    public String getName() {
        return this.mName;
    }

    public byte[] getKey() {
        return (byte[]) this.mKey.clone();
    }

    public int getTruncationLengthBits() {
        return this.mTruncLenBits;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeByteArray(this.mKey);
        parcel.writeInt(this.mTruncLenBits);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008c A[PHI: r8
  0x008c: PHI (r8v14 boolean) = (r8v3 boolean), (r8v6 boolean), (r8v9 boolean), (r8v20 boolean), (r8v20 boolean), (r8v20 boolean) binds: [B:75:0x00c3, B:69:0x00b9, B:63:0x00af, B:40:0x0086, B:41:0x0088, B:42:0x008a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a5 A[PHI: r0
  0x00a5: PHI (r0v25 boolean) = (r0v22 boolean), (r0v27 boolean) binds: [B:56:0x00a3, B:49:0x0097] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void checkValidOrThrow(java.lang.String r8, int r9, int r10) {
        /*
            Method dump skipped, instruction units count: 308
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.IpSecAlgorithm.checkValidOrThrow(java.lang.String, int, int):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isAuthentication() {
        /*
            r7 = this;
            java.lang.String r0 = r7.getName()
            int r1 = r0.hashCode()
            r2 = 0
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            switch(r1) {
                case 559425185: goto L39;
                case 559457797: goto L2f;
                case 559510590: goto L25;
                case 759177996: goto L1b;
                case 2065384259: goto L11;
                default: goto L10;
            }
        L10:
            goto L43
        L11:
            java.lang.String r1 = "hmac(sha1)"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L43
            r0 = r6
            goto L44
        L1b:
            java.lang.String r1 = "hmac(md5)"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L43
            r0 = r2
            goto L44
        L25:
            java.lang.String r1 = "hmac(sha512)"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L43
            r0 = r3
            goto L44
        L2f:
            java.lang.String r1 = "hmac(sha384)"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L43
            r0 = r4
            goto L44
        L39:
            java.lang.String r1 = "hmac(sha256)"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L43
            r0 = r5
            goto L44
        L43:
            r0 = -1
        L44:
            if (r0 == 0) goto L4f
            if (r0 == r6) goto L4f
            if (r0 == r5) goto L4f
            if (r0 == r4) goto L4f
            if (r0 == r3) goto L4f
            return r2
        L4f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.IpSecAlgorithm.isAuthentication():boolean");
    }

    public boolean isEncryption() {
        return getName().equals(CRYPT_AES_CBC);
    }

    public boolean isAead() {
        return getName().equals(AUTH_CRYPT_AES_GCM);
    }

    private static boolean isUnsafeBuild() {
        return Build.IS_DEBUGGABLE && Build.IS_ENG;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{mName=");
        sb.append(this.mName);
        sb.append(", mKey=");
        sb.append(isUnsafeBuild() ? HexDump.toHexString(this.mKey) : "<hidden>");
        sb.append(", mTruncLenBits=");
        sb.append(this.mTruncLenBits);
        sb.append("}");
        return sb.toString();
    }

    public static boolean equals(IpSecAlgorithm ipSecAlgorithm, IpSecAlgorithm ipSecAlgorithm2) {
        return (ipSecAlgorithm == null || ipSecAlgorithm2 == null) ? ipSecAlgorithm == ipSecAlgorithm2 : ipSecAlgorithm.mName.equals(ipSecAlgorithm2.mName) && Arrays.equals(ipSecAlgorithm.mKey, ipSecAlgorithm2.mKey) && ipSecAlgorithm.mTruncLenBits == ipSecAlgorithm2.mTruncLenBits;
    }
}
