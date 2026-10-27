package android.security.keystore;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import libcore.util.EmptyArray;

/* JADX INFO: loaded from: classes3.dex */
public abstract class KeyProperties {
    public static final int AUTH_BIOMETRIC_STRONG = 2;
    public static final int AUTH_DEVICE_CREDENTIAL = 1;
    public static final String BLOCK_MODE_CBC = "CBC";
    public static final String BLOCK_MODE_CTR = "CTR";
    public static final String BLOCK_MODE_ECB = "ECB";
    public static final String BLOCK_MODE_GCM = "GCM";
    public static final String DIGEST_MD5 = "MD5";
    public static final String DIGEST_NONE = "NONE";
    public static final String DIGEST_SHA1 = "SHA-1";
    public static final String DIGEST_SHA224 = "SHA-224";
    public static final String DIGEST_SHA256 = "SHA-256";
    public static final String DIGEST_SHA384 = "SHA-384";
    public static final String DIGEST_SHA512 = "SHA-512";
    public static final String ENCRYPTION_PADDING_NONE = "NoPadding";
    public static final String ENCRYPTION_PADDING_PKCS7 = "PKCS7Padding";
    public static final String ENCRYPTION_PADDING_RSA_OAEP = "OAEPPadding";
    public static final String ENCRYPTION_PADDING_RSA_PKCS1 = "PKCS1Padding";

    @Deprecated
    public static final String KEY_ALGORITHM_3DES = "DESede";
    public static final String KEY_ALGORITHM_AES = "AES";
    public static final String KEY_ALGORITHM_EC = "EC";
    public static final String KEY_ALGORITHM_HMAC_SHA1 = "HmacSHA1";
    public static final String KEY_ALGORITHM_HMAC_SHA224 = "HmacSHA224";
    public static final String KEY_ALGORITHM_HMAC_SHA256 = "HmacSHA256";
    public static final String KEY_ALGORITHM_HMAC_SHA384 = "HmacSHA384";
    public static final String KEY_ALGORITHM_HMAC_SHA512 = "HmacSHA512";
    public static final String KEY_ALGORITHM_RSA = "RSA";
    public static final int ORIGIN_GENERATED = 1;
    public static final int ORIGIN_IMPORTED = 2;
    public static final int ORIGIN_SECURELY_IMPORTED = 8;
    public static final int ORIGIN_UNKNOWN = 4;
    public static final int PURPOSE_DECRYPT = 2;
    public static final int PURPOSE_ENCRYPT = 1;
    public static final int PURPOSE_SIGN = 4;
    public static final int PURPOSE_VERIFY = 8;
    public static final int PURPOSE_WRAP_KEY = 32;
    public static final String SIGNATURE_PADDING_RSA_PKCS1 = "PKCS1";
    public static final String SIGNATURE_PADDING_RSA_PSS = "PSS";

    @Retention(RetentionPolicy.SOURCE)
    public @interface AuthEnum {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BlockModeEnum {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DigestEnum {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EncryptionPaddingEnum {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KeyAlgorithmEnum {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OriginEnum {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PurposeEnum {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SignaturePaddingEnum {
    }

    private static int getSetBitCount(int i) {
        int i2 = 0;
        if (i == 0) {
            return 0;
        }
        while (i != 0) {
            if ((i & 1) != 0) {
                i2++;
            }
            i >>>= 1;
        }
        return i2;
    }

    private KeyProperties() {
    }

    public static abstract class Purpose {
        private Purpose() {
        }

        public static int toKeymaster(int i) {
            if (i == 1) {
                return 0;
            }
            if (i == 2) {
                return 1;
            }
            if (i == 4) {
                return 2;
            }
            if (i == 8) {
                return 3;
            }
            if (i == 32) {
                return 5;
            }
            throw new IllegalArgumentException("Unknown purpose: " + i);
        }

        public static int fromKeymaster(int i) {
            if (i == 0) {
                return 1;
            }
            if (i == 1) {
                return 2;
            }
            if (i == 2) {
                return 4;
            }
            if (i == 3) {
                return 8;
            }
            if (i == 5) {
                return 32;
            }
            throw new IllegalArgumentException("Unknown purpose: " + i);
        }

        public static int[] allToKeymaster(int i) {
            int[] setFlags = KeyProperties.getSetFlags(i);
            for (int i2 = 0; i2 < setFlags.length; i2++) {
                setFlags[i2] = toKeymaster(setFlags[i2]);
            }
            return setFlags;
        }

        public static int allFromKeymaster(Collection<Integer> collection) {
            Iterator<Integer> it = collection.iterator();
            int iFromKeymaster = 0;
            while (it.hasNext()) {
                iFromKeymaster |= fromKeymaster(it.next().intValue());
            }
            return iFromKeymaster;
        }
    }

    public static abstract class KeyAlgorithm {
        private KeyAlgorithm() {
        }

        public static int toKeymasterAsymmetricKeyAlgorithm(String str) {
            if (KeyProperties.KEY_ALGORITHM_EC.equalsIgnoreCase(str)) {
                return 3;
            }
            if (KeyProperties.KEY_ALGORITHM_RSA.equalsIgnoreCase(str)) {
                return 1;
            }
            throw new IllegalArgumentException("Unsupported key algorithm: " + str);
        }

        public static String fromKeymasterAsymmetricKeyAlgorithm(int i) {
            if (i == 1) {
                return KeyProperties.KEY_ALGORITHM_RSA;
            }
            if (i == 3) {
                return KeyProperties.KEY_ALGORITHM_EC;
            }
            throw new IllegalArgumentException("Unsupported key algorithm: " + i);
        }

        public static int toKeymasterSecretKeyAlgorithm(String str) {
            if (KeyProperties.KEY_ALGORITHM_AES.equalsIgnoreCase(str)) {
                return 32;
            }
            if (KeyProperties.KEY_ALGORITHM_3DES.equalsIgnoreCase(str)) {
                return 33;
            }
            if (str.toUpperCase(Locale.US).startsWith("HMAC")) {
                return 128;
            }
            throw new IllegalArgumentException("Unsupported secret key algorithm: " + str);
        }

        public static String fromKeymasterSecretKeyAlgorithm(int i, int i2) {
            if (i == 32) {
                return KeyProperties.KEY_ALGORITHM_AES;
            }
            if (i == 33) {
                return KeyProperties.KEY_ALGORITHM_3DES;
            }
            if (i != 128) {
                throw new IllegalArgumentException("Unsupported key algorithm: " + i);
            }
            if (i2 == 2) {
                return KeyProperties.KEY_ALGORITHM_HMAC_SHA1;
            }
            if (i2 == 3) {
                return KeyProperties.KEY_ALGORITHM_HMAC_SHA224;
            }
            if (i2 == 4) {
                return KeyProperties.KEY_ALGORITHM_HMAC_SHA256;
            }
            if (i2 == 5) {
                return KeyProperties.KEY_ALGORITHM_HMAC_SHA384;
            }
            if (i2 == 6) {
                return KeyProperties.KEY_ALGORITHM_HMAC_SHA512;
            }
            throw new IllegalArgumentException("Unsupported HMAC digest: " + Digest.fromKeymaster(i2));
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public static int toKeymasterDigest(String str) {
            String upperCase = str.toUpperCase(Locale.US);
            byte b = -1;
            if (!upperCase.startsWith("HMAC")) {
                return -1;
            }
            String strSubstring = upperCase.substring(4);
            switch (strSubstring.hashCode()) {
                case -1850268184:
                    if (strSubstring.equals("SHA224")) {
                        b = 1;
                    }
                    break;
                case -1850268089:
                    if (strSubstring.equals("SHA256")) {
                        b = 2;
                    }
                    break;
                case -1850267037:
                    if (strSubstring.equals("SHA384")) {
                        b = 3;
                    }
                    break;
                case -1850265334:
                    if (strSubstring.equals("SHA512")) {
                        b = 4;
                    }
                    break;
                case 2543909:
                    if (strSubstring.equals("SHA1")) {
                        b = 0;
                    }
                    break;
            }
            if (b == 0) {
                return 2;
            }
            if (b == 1) {
                return 3;
            }
            if (b == 2) {
                return 4;
            }
            if (b == 3) {
                return 5;
            }
            if (b == 4) {
                return 6;
            }
            throw new IllegalArgumentException("Unsupported HMAC digest: " + strSubstring);
        }
    }

    public static abstract class BlockMode {
        private BlockMode() {
        }

        public static int toKeymaster(String str) {
            if (KeyProperties.BLOCK_MODE_ECB.equalsIgnoreCase(str)) {
                return 1;
            }
            if (KeyProperties.BLOCK_MODE_CBC.equalsIgnoreCase(str)) {
                return 2;
            }
            if (KeyProperties.BLOCK_MODE_CTR.equalsIgnoreCase(str)) {
                return 3;
            }
            if (KeyProperties.BLOCK_MODE_GCM.equalsIgnoreCase(str)) {
                return 32;
            }
            throw new IllegalArgumentException("Unsupported block mode: " + str);
        }

        public static String fromKeymaster(int i) {
            if (i == 1) {
                return KeyProperties.BLOCK_MODE_ECB;
            }
            if (i == 2) {
                return KeyProperties.BLOCK_MODE_CBC;
            }
            if (i == 3) {
                return KeyProperties.BLOCK_MODE_CTR;
            }
            if (i == 32) {
                return KeyProperties.BLOCK_MODE_GCM;
            }
            throw new IllegalArgumentException("Unsupported block mode: " + i);
        }

        public static String[] allFromKeymaster(Collection<Integer> collection) {
            if (collection == null || collection.isEmpty()) {
                return EmptyArray.STRING;
            }
            String[] strArr = new String[collection.size()];
            int i = 0;
            Iterator<Integer> it = collection.iterator();
            while (it.hasNext()) {
                strArr[i] = fromKeymaster(it.next().intValue());
                i++;
            }
            return strArr;
        }

        public static int[] allToKeymaster(String[] strArr) {
            if (strArr == null || strArr.length == 0) {
                return EmptyArray.INT;
            }
            int[] iArr = new int[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                iArr[i] = toKeymaster(strArr[i]);
            }
            return iArr;
        }
    }

    public static abstract class EncryptionPadding {
        private EncryptionPadding() {
        }

        public static int toKeymaster(String str) {
            if (KeyProperties.ENCRYPTION_PADDING_NONE.equalsIgnoreCase(str)) {
                return 1;
            }
            if (KeyProperties.ENCRYPTION_PADDING_PKCS7.equalsIgnoreCase(str)) {
                return 64;
            }
            if (KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1.equalsIgnoreCase(str)) {
                return 4;
            }
            if (KeyProperties.ENCRYPTION_PADDING_RSA_OAEP.equalsIgnoreCase(str)) {
                return 2;
            }
            throw new IllegalArgumentException("Unsupported encryption padding scheme: " + str);
        }

        public static String fromKeymaster(int i) {
            if (i == 1) {
                return KeyProperties.ENCRYPTION_PADDING_NONE;
            }
            if (i == 2) {
                return KeyProperties.ENCRYPTION_PADDING_RSA_OAEP;
            }
            if (i == 4) {
                return KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1;
            }
            if (i == 64) {
                return KeyProperties.ENCRYPTION_PADDING_PKCS7;
            }
            throw new IllegalArgumentException("Unsupported encryption padding: " + i);
        }

        public static int[] allToKeymaster(String[] strArr) {
            if (strArr == null || strArr.length == 0) {
                return EmptyArray.INT;
            }
            int[] iArr = new int[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                iArr[i] = toKeymaster(strArr[i]);
            }
            return iArr;
        }
    }

    static abstract class SignaturePadding {
        private SignaturePadding() {
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        static int toKeymaster(java.lang.String r4) {
            /*
                java.util.Locale r0 = java.util.Locale.US
                java.lang.String r0 = r4.toUpperCase(r0)
                int r1 = r0.hashCode()
                r2 = 79536(0x136b0, float:1.11454E-40)
                r3 = 1
                if (r1 == r2) goto L20
                r2 = 76183014(0x48a75e6, float:3.2551917E-36)
                if (r1 == r2) goto L16
                goto L2a
            L16:
                java.lang.String r1 = "PKCS1"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L2a
                r0 = 0
                goto L2b
            L20:
                java.lang.String r1 = "PSS"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L2a
                r0 = r3
                goto L2b
            L2a:
                r0 = -1
            L2b:
                if (r0 == 0) goto L48
                if (r0 != r3) goto L31
                r4 = 3
                return r4
            L31:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Unsupported signature padding scheme: "
                r1.append(r2)
                r1.append(r4)
                java.lang.String r4 = r1.toString()
                r0.<init>(r4)
                throw r0
            L48:
                r4 = 5
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: android.security.keystore.KeyProperties.SignaturePadding.toKeymaster(java.lang.String):int");
        }

        static String fromKeymaster(int i) {
            if (i == 3) {
                return KeyProperties.SIGNATURE_PADDING_RSA_PSS;
            }
            if (i == 5) {
                return KeyProperties.SIGNATURE_PADDING_RSA_PKCS1;
            }
            throw new IllegalArgumentException("Unsupported signature padding: " + i);
        }

        static int[] allToKeymaster(String[] strArr) {
            if (strArr == null || strArr.length == 0) {
                return EmptyArray.INT;
            }
            int[] iArr = new int[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                iArr[i] = toKeymaster(strArr[i]);
            }
            return iArr;
        }
    }

    public static abstract class Digest {
        private Digest() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:26:0x005b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int toKeymaster(java.lang.String r9) {
            /*
                java.util.Locale r0 = java.util.Locale.US
                java.lang.String r0 = r9.toUpperCase(r0)
                int r1 = r0.hashCode()
                r2 = 1
                r3 = 0
                r4 = 6
                r5 = 5
                r6 = 4
                r7 = 3
                r8 = 2
                switch(r1) {
                    case -1523887821: goto L51;
                    case -1523887726: goto L47;
                    case -1523886674: goto L3d;
                    case -1523884971: goto L33;
                    case 76158: goto L29;
                    case 2402104: goto L1f;
                    case 78861104: goto L15;
                    default: goto L14;
                }
            L14:
                goto L5b
            L15:
                java.lang.String r1 = "SHA-1"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L5b
                r0 = r3
                goto L5c
            L1f:
                java.lang.String r1 = "NONE"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L5b
                r0 = r5
                goto L5c
            L29:
                java.lang.String r1 = "MD5"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L5b
                r0 = r4
                goto L5c
            L33:
                java.lang.String r1 = "SHA-512"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L5b
                r0 = r6
                goto L5c
            L3d:
                java.lang.String r1 = "SHA-384"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L5b
                r0 = r7
                goto L5c
            L47:
                java.lang.String r1 = "SHA-256"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L5b
                r0 = r8
                goto L5c
            L51:
                java.lang.String r1 = "SHA-224"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L5b
                r0 = r2
                goto L5c
            L5b:
                r0 = -1
            L5c:
                switch(r0) {
                    case 0: goto L7c;
                    case 1: goto L7b;
                    case 2: goto L7a;
                    case 3: goto L79;
                    case 4: goto L78;
                    case 5: goto L77;
                    case 6: goto L76;
                    default: goto L5f;
                }
            L5f:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Unsupported digest algorithm: "
                r1.append(r2)
                r1.append(r9)
                java.lang.String r9 = r1.toString()
                r0.<init>(r9)
                throw r0
            L76:
                return r2
            L77:
                return r3
            L78:
                return r4
            L79:
                return r5
            L7a:
                return r6
            L7b:
                return r7
            L7c:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: android.security.keystore.KeyProperties.Digest.toKeymaster(java.lang.String):int");
        }

        public static String fromKeymaster(int i) {
            switch (i) {
                case 0:
                    return KeyProperties.DIGEST_NONE;
                case 1:
                    return KeyProperties.DIGEST_MD5;
                case 2:
                    return KeyProperties.DIGEST_SHA1;
                case 3:
                    return KeyProperties.DIGEST_SHA224;
                case 4:
                    return "SHA-256";
                case 5:
                    return KeyProperties.DIGEST_SHA384;
                case 6:
                    return KeyProperties.DIGEST_SHA512;
                default:
                    throw new IllegalArgumentException("Unsupported digest algorithm: " + i);
            }
        }

        public static String fromKeymasterToSignatureAlgorithmDigest(int i) {
            switch (i) {
                case 0:
                    return KeyProperties.DIGEST_NONE;
                case 1:
                    return KeyProperties.DIGEST_MD5;
                case 2:
                    return "SHA1";
                case 3:
                    return "SHA224";
                case 4:
                    return "SHA256";
                case 5:
                    return "SHA384";
                case 6:
                    return "SHA512";
                default:
                    throw new IllegalArgumentException("Unsupported digest algorithm: " + i);
            }
        }

        public static String[] allFromKeymaster(Collection<Integer> collection) {
            if (collection.isEmpty()) {
                return EmptyArray.STRING;
            }
            String[] strArr = new String[collection.size()];
            int i = 0;
            Iterator<Integer> it = collection.iterator();
            while (it.hasNext()) {
                strArr[i] = fromKeymaster(it.next().intValue());
                i++;
            }
            return strArr;
        }

        public static int[] allToKeymaster(String[] strArr) {
            if (strArr == null || strArr.length == 0) {
                return EmptyArray.INT;
            }
            int[] iArr = new int[strArr.length];
            int i = 0;
            for (String str : strArr) {
                iArr[i] = toKeymaster(str);
                i++;
            }
            return iArr;
        }
    }

    public static abstract class Origin {
        private Origin() {
        }

        public static int fromKeymaster(int i) {
            if (i == 0) {
                return 1;
            }
            if (i == 2) {
                return 2;
            }
            if (i == 3) {
                return 4;
            }
            if (i == 4) {
                return 8;
            }
            throw new IllegalArgumentException("Unknown origin: " + i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int[] getSetFlags(int i) {
        if (i == 0) {
            return EmptyArray.INT;
        }
        int[] iArr = new int[getSetBitCount(i)];
        int i2 = 0;
        int i3 = 1;
        while (i != 0) {
            if ((i & 1) != 0) {
                iArr[i2] = i3;
                i2++;
            }
            i >>>= 1;
            i3 <<= 1;
        }
        return iArr;
    }
}
