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
        checkValidOrThrow(str, bArr2.length * 8, i);
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

    /* JADX WARN: Removed duplicated region for block: B:42:0x008a A[PHI: r8
  0x008a: PHI (r8v21 boolean) = (r8v2 boolean), (r8v2 boolean), (r8v2 boolean), (r8v8 boolean), (r8v15 boolean), (r8v23 boolean) binds: [B:86:0x00d9, B:87:0x00db, B:88:0x00dd, B:70:0x00bd, B:47:0x0093, B:41:0x0088] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00fe  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void checkValidOrThrow(java.lang.String r8, int r9, int r10) {
        /*
            Method dump skipped, instruction units count: 328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.IpSecAlgorithm.checkValidOrThrow(java.lang.String, int, int):void");
    }

    public boolean isAuthentication() {
        String name = getName();
        name.hashCode();
        switch (name) {
            case "hmac(sha256)":
            case "hmac(sha384)":
            case "hmac(sha512)":
            case "hmac(md5)":
            case "hmac(sha1)":
                return true;
            default:
                return false;
        }
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
        return "{mName=" + this.mName + ", mKey=" + (isUnsafeBuild() ? HexDump.toHexString(this.mKey) : "<hidden>") + ", mTruncLenBits=" + this.mTruncLenBits + "}";
    }

    public static boolean equals(IpSecAlgorithm ipSecAlgorithm, IpSecAlgorithm ipSecAlgorithm2) {
        return (ipSecAlgorithm == null || ipSecAlgorithm2 == null) ? ipSecAlgorithm == ipSecAlgorithm2 : ipSecAlgorithm.mName.equals(ipSecAlgorithm2.mName) && Arrays.equals(ipSecAlgorithm.mKey, ipSecAlgorithm2.mKey) && ipSecAlgorithm.mTruncLenBits == ipSecAlgorithm2.mTruncLenBits;
    }
}
