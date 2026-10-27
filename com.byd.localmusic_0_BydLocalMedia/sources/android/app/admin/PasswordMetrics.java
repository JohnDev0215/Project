package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.android.internal.widget.LockscreenCredential;
import com.android.internal.widget.PasswordValidationError;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class PasswordMetrics implements Parcelable {
    private static final int CHAR_DIGIT = 2;
    private static final int CHAR_LOWER_CASE = 0;
    private static final int CHAR_SYMBOL = 3;
    private static final int CHAR_UPPER_CASE = 1;
    public static final Parcelable.Creator<PasswordMetrics> CREATOR = new Parcelable.Creator<PasswordMetrics>() { // from class: android.app.admin.PasswordMetrics.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PasswordMetrics createFromParcel(Parcel parcel) {
            return new PasswordMetrics(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PasswordMetrics[] newArray(int i) {
            return new PasswordMetrics[i];
        }
    };
    public static final int MAX_ALLOWED_SEQUENCE = 3;
    private static final String TAG = "PasswordMetrics";
    public int credType;
    public int length;
    public int letters;
    public int lowerCase;
    public int nonLetter;
    public int nonNumeric;
    public int numeric;
    public int seqLength;
    public int symbols;
    public int upperCase;

    @Retention(RetentionPolicy.SOURCE)
    private @interface CharacterCatagory {
    }

    private static int categoryChar(char c) {
        if ('a' <= c && c <= 'z') {
            return 0;
        }
        if ('A' > c || c > 'Z') {
            return ('0' > c || c > '9') ? 3 : 2;
        }
        return 1;
    }

    public static int complexityLevelToMinQuality(int i) {
        int i2 = 65536;
        if (i != 65536) {
            i2 = 196608;
            if (i != 196608 && i != 327680) {
                return 0;
            }
        }
        return i2;
    }

    private static int maxDiffCategory(int i) {
        if (i == 0 || i == 1) {
            return 1;
        }
        return i != 2 ? 0 : 10;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PasswordMetrics(int i) {
        this.length = 0;
        this.letters = 0;
        this.upperCase = 0;
        this.lowerCase = 0;
        this.numeric = 0;
        this.symbols = 0;
        this.nonLetter = 0;
        this.nonNumeric = 0;
        this.seqLength = Integer.MAX_VALUE;
        this.credType = i;
    }

    public PasswordMetrics(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.length = 0;
        this.letters = 0;
        this.upperCase = 0;
        this.lowerCase = 0;
        this.numeric = 0;
        this.symbols = 0;
        this.nonLetter = 0;
        this.nonNumeric = 0;
        this.seqLength = Integer.MAX_VALUE;
        this.credType = i;
        this.length = i2;
        this.letters = i3;
        this.upperCase = i4;
        this.lowerCase = i5;
        this.numeric = i6;
        this.symbols = i7;
        this.nonLetter = i8;
        this.nonNumeric = i9;
        this.seqLength = i10;
    }

    private PasswordMetrics(PasswordMetrics passwordMetrics) {
        this(passwordMetrics.credType, passwordMetrics.length, passwordMetrics.letters, passwordMetrics.upperCase, passwordMetrics.lowerCase, passwordMetrics.numeric, passwordMetrics.symbols, passwordMetrics.nonLetter, passwordMetrics.nonNumeric, passwordMetrics.seqLength);
    }

    public static int sanitizeComplexityLevel(int i) {
        if (i == 0 || i == 65536 || i == 196608 || i == 327680) {
            return i;
        }
        Log.w(TAG, "Invalid password complexity used: " + i);
        return 0;
    }

    private static boolean hasInvalidCharacters(byte[] bArr) {
        for (byte b : bArr) {
            char c = (char) b;
            if (c < ' ' || c > 127) {
                return true;
            }
        }
        return false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.credType);
        parcel.writeInt(this.length);
        parcel.writeInt(this.letters);
        parcel.writeInt(this.upperCase);
        parcel.writeInt(this.lowerCase);
        parcel.writeInt(this.numeric);
        parcel.writeInt(this.symbols);
        parcel.writeInt(this.nonLetter);
        parcel.writeInt(this.nonNumeric);
        parcel.writeInt(this.seqLength);
    }

    public static PasswordMetrics computeForCredential(LockscreenCredential lockscreenCredential) {
        if (lockscreenCredential.isPassword() || lockscreenCredential.isPin()) {
            return computeForPassword(lockscreenCredential.getCredential());
        }
        if (lockscreenCredential.isPattern()) {
            return new PasswordMetrics(1);
        }
        if (lockscreenCredential.isNone()) {
            return new PasswordMetrics(-1);
        }
        throw new IllegalArgumentException("Unknown credential type " + lockscreenCredential.getType());
    }

    public static PasswordMetrics computeForPassword(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (byte b : bArr) {
            int iCategoryChar = categoryChar((char) b);
            if (iCategoryChar == 0) {
                i++;
                i3++;
            } else if (iCategoryChar != 1) {
                if (iCategoryChar == 2) {
                    i4++;
                    i6++;
                } else if (iCategoryChar == 3) {
                    i5++;
                    i6++;
                }
            } else {
                i++;
                i2++;
            }
            i7++;
        }
        return new PasswordMetrics(4, length, i, i2, i3, i4, i5, i6, i7, maxLengthSequence(bArr));
    }

    public static int maxLengthSequence(byte[] bArr) {
        if (bArr.length == 0) {
            return 0;
        }
        char c = (char) bArr[0];
        int iCategoryChar = categoryChar(c);
        int iMax = 0;
        int i = 0;
        boolean z = false;
        int i2 = 0;
        int i3 = 1;
        while (i3 < bArr.length) {
            char c2 = (char) bArr[i3];
            int iCategoryChar2 = categoryChar(c2);
            int i4 = c2 - c;
            if (iCategoryChar2 != iCategoryChar || Math.abs(i4) > maxDiffCategory(iCategoryChar)) {
                iMax = Math.max(iMax, i3 - i);
                z = false;
                i = i3;
                iCategoryChar = iCategoryChar2;
            } else {
                if (z && i4 != i2) {
                    iMax = Math.max(iMax, i3 - i);
                    i = i3 - 1;
                }
                i2 = i4;
                z = true;
            }
            i3++;
            c = c2;
        }
        return Math.max(iMax, bArr.length - i);
    }

    public static PasswordMetrics merge(List<PasswordMetrics> list) {
        PasswordMetrics passwordMetrics = new PasswordMetrics(-1);
        Iterator<PasswordMetrics> it = list.iterator();
        while (it.hasNext()) {
            passwordMetrics.maxWith(it.next());
        }
        return passwordMetrics;
    }

    public void maxWith(PasswordMetrics passwordMetrics) {
        int iMax = Math.max(this.credType, passwordMetrics.credType);
        this.credType = iMax;
        if (iMax != 4) {
            return;
        }
        this.length = Math.max(this.length, passwordMetrics.length);
        this.letters = Math.max(this.letters, passwordMetrics.letters);
        this.upperCase = Math.max(this.upperCase, passwordMetrics.upperCase);
        this.lowerCase = Math.max(this.lowerCase, passwordMetrics.lowerCase);
        this.numeric = Math.max(this.numeric, passwordMetrics.numeric);
        this.symbols = Math.max(this.symbols, passwordMetrics.symbols);
        this.nonLetter = Math.max(this.nonLetter, passwordMetrics.nonLetter);
        this.nonNumeric = Math.max(this.nonNumeric, passwordMetrics.nonNumeric);
        this.seqLength = Math.min(this.seqLength, passwordMetrics.seqLength);
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'BUCKET_HIGH' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    private static abstract class ComplexityBucket {
        private static final /* synthetic */ ComplexityBucket[] $VALUES;
        public static final ComplexityBucket BUCKET_HIGH;
        public static final ComplexityBucket BUCKET_NONE;
        int mComplexityLevel;
        public static final ComplexityBucket BUCKET_MEDIUM = new ComplexityBucket("BUCKET_MEDIUM", 1, 196608) { // from class: android.app.admin.PasswordMetrics.ComplexityBucket.2
            @Override // android.app.admin.PasswordMetrics.ComplexityBucket
            boolean allowsCredType(int i) {
                return i == 4;
            }

            @Override // android.app.admin.PasswordMetrics.ComplexityBucket
            boolean allowsNumericPassword() {
                return false;
            }

            @Override // android.app.admin.PasswordMetrics.ComplexityBucket
            boolean canHaveSequence() {
                return false;
            }

            @Override // android.app.admin.PasswordMetrics.ComplexityBucket
            int getMinimumLength(boolean z) {
                return 4;
            }
        };
        public static final ComplexityBucket BUCKET_LOW = new ComplexityBucket("BUCKET_LOW", 2, 65536) { // from class: android.app.admin.PasswordMetrics.ComplexityBucket.3
            @Override // android.app.admin.PasswordMetrics.ComplexityBucket
            boolean allowsCredType(int i) {
                return i != -1;
            }

            @Override // android.app.admin.PasswordMetrics.ComplexityBucket
            boolean allowsNumericPassword() {
                return true;
            }

            @Override // android.app.admin.PasswordMetrics.ComplexityBucket
            boolean canHaveSequence() {
                return true;
            }

            @Override // android.app.admin.PasswordMetrics.ComplexityBucket
            int getMinimumLength(boolean z) {
                return 0;
            }
        };

        abstract boolean allowsCredType(int i);

        abstract boolean allowsNumericPassword();

        abstract boolean canHaveSequence();

        abstract int getMinimumLength(boolean z);

        public static ComplexityBucket valueOf(String str) {
            return (ComplexityBucket) Enum.valueOf(ComplexityBucket.class, str);
        }

        public static ComplexityBucket[] values() {
            return (ComplexityBucket[]) $VALUES.clone();
        }

        static {
            int i = 0;
            BUCKET_HIGH = new ComplexityBucket("BUCKET_HIGH", i, 327680) { // from class: android.app.admin.PasswordMetrics.ComplexityBucket.1
                @Override // android.app.admin.PasswordMetrics.ComplexityBucket
                boolean allowsCredType(int i2) {
                    return i2 == 4;
                }

                @Override // android.app.admin.PasswordMetrics.ComplexityBucket
                boolean allowsNumericPassword() {
                    return false;
                }

                @Override // android.app.admin.PasswordMetrics.ComplexityBucket
                boolean canHaveSequence() {
                    return false;
                }

                @Override // android.app.admin.PasswordMetrics.ComplexityBucket
                int getMinimumLength(boolean z) {
                    return z ? 6 : 8;
                }
            };
            ComplexityBucket complexityBucket = new ComplexityBucket("BUCKET_NONE", 3, i) { // from class: android.app.admin.PasswordMetrics.ComplexityBucket.4
                @Override // android.app.admin.PasswordMetrics.ComplexityBucket
                boolean allowsCredType(int i2) {
                    return true;
                }

                @Override // android.app.admin.PasswordMetrics.ComplexityBucket
                boolean allowsNumericPassword() {
                    return true;
                }

                @Override // android.app.admin.PasswordMetrics.ComplexityBucket
                boolean canHaveSequence() {
                    return true;
                }

                @Override // android.app.admin.PasswordMetrics.ComplexityBucket
                int getMinimumLength(boolean z) {
                    return 0;
                }
            };
            BUCKET_NONE = complexityBucket;
            $VALUES = new ComplexityBucket[]{BUCKET_HIGH, BUCKET_MEDIUM, BUCKET_LOW, complexityBucket};
        }

        private ComplexityBucket(String str, int i, int i2) {
            this.mComplexityLevel = i2;
        }

        static ComplexityBucket forComplexity(int i) {
            for (ComplexityBucket complexityBucket : values()) {
                if (complexityBucket.mComplexityLevel == i) {
                    return complexityBucket;
                }
            }
            throw new IllegalArgumentException("Invalid complexity level: " + i);
        }
    }

    private boolean satisfiesBucket(ComplexityBucket complexityBucket) {
        if (!complexityBucket.allowsCredType(this.credType)) {
            return false;
        }
        if (this.credType != 4) {
            return true;
        }
        if (complexityBucket.canHaveSequence() || this.seqLength <= 3) {
            return this.length >= complexityBucket.getMinimumLength(this.nonNumeric > 0);
        }
        return false;
    }

    public int determineComplexity() {
        for (ComplexityBucket complexityBucket : ComplexityBucket.values()) {
            if (satisfiesBucket(complexityBucket)) {
                return complexityBucket.mComplexityLevel;
            }
        }
        throw new IllegalStateException("Failed to figure out complexity for a given metrics");
    }

    public static List<PasswordValidationError> validatePassword(PasswordMetrics passwordMetrics, int i, boolean z, byte[] bArr) {
        if (hasInvalidCharacters(bArr)) {
            return Collections.singletonList(new PasswordValidationError(2, 0));
        }
        return validatePasswordMetrics(passwordMetrics, i, z, computeForPassword(bArr));
    }

    public static List<PasswordValidationError> validatePasswordMetrics(PasswordMetrics passwordMetrics, int i, boolean z, PasswordMetrics passwordMetrics2) {
        ComplexityBucket complexityBucketForComplexity = ComplexityBucket.forComplexity(i);
        int i2 = passwordMetrics2.credType;
        if (i2 < passwordMetrics.credType || !complexityBucketForComplexity.allowsCredType(i2)) {
            return Collections.singletonList(new PasswordValidationError(1, 0));
        }
        if (passwordMetrics2.credType != 4) {
            return Collections.emptyList();
        }
        if (z && passwordMetrics2.nonNumeric > 0) {
            return Collections.singletonList(new PasswordValidationError(2, 0));
        }
        ArrayList arrayList = new ArrayList();
        if (passwordMetrics2.length > 16) {
            arrayList.add(new PasswordValidationError(4, 16));
        }
        PasswordMetrics passwordMetricsApplyComplexity = applyComplexity(passwordMetrics, z, complexityBucketForComplexity);
        passwordMetricsApplyComplexity.length = Math.min(16, Math.max(passwordMetricsApplyComplexity.length, 4));
        passwordMetricsApplyComplexity.removeOverlapping();
        comparePasswordMetrics(passwordMetricsApplyComplexity, passwordMetrics2, arrayList);
        return arrayList;
    }

    private static void comparePasswordMetrics(PasswordMetrics passwordMetrics, PasswordMetrics passwordMetrics2, ArrayList<PasswordValidationError> arrayList) {
        if (passwordMetrics2.length < passwordMetrics.length) {
            arrayList.add(new PasswordValidationError(3, passwordMetrics.length));
        }
        if (passwordMetrics2.letters < passwordMetrics.letters) {
            arrayList.add(new PasswordValidationError(6, passwordMetrics.letters));
        }
        if (passwordMetrics2.upperCase < passwordMetrics.upperCase) {
            arrayList.add(new PasswordValidationError(7, passwordMetrics.upperCase));
        }
        if (passwordMetrics2.lowerCase < passwordMetrics.lowerCase) {
            arrayList.add(new PasswordValidationError(8, passwordMetrics.lowerCase));
        }
        if (passwordMetrics2.numeric < passwordMetrics.numeric) {
            arrayList.add(new PasswordValidationError(9, passwordMetrics.numeric));
        }
        if (passwordMetrics2.symbols < passwordMetrics.symbols) {
            arrayList.add(new PasswordValidationError(10, passwordMetrics.symbols));
        }
        if (passwordMetrics2.nonLetter < passwordMetrics.nonLetter) {
            arrayList.add(new PasswordValidationError(11, passwordMetrics.nonLetter));
        }
        if (passwordMetrics2.nonNumeric < passwordMetrics.nonNumeric) {
            arrayList.add(new PasswordValidationError(12, passwordMetrics.nonNumeric));
        }
        if (passwordMetrics2.seqLength > passwordMetrics.seqLength) {
            arrayList.add(new PasswordValidationError(5, 0));
        }
    }

    private void removeOverlapping() {
        int i = this.upperCase + this.lowerCase;
        int i2 = this.numeric + this.symbols;
        int iMax = Math.max(this.letters, i);
        int i3 = this.symbols + iMax;
        int iMax2 = Math.max(iMax + Math.max(this.nonLetter, i2), this.numeric + Math.max(this.nonNumeric, i3));
        if (i >= this.letters) {
            this.letters = 0;
        }
        if (i2 >= this.nonLetter) {
            this.nonLetter = 0;
        }
        if (i3 >= this.nonNumeric) {
            this.nonNumeric = 0;
        }
        if (iMax2 >= this.length) {
            this.length = 0;
        }
    }

    public static PasswordMetrics applyComplexity(PasswordMetrics passwordMetrics, boolean z, int i) {
        return applyComplexity(passwordMetrics, z, ComplexityBucket.forComplexity(i));
    }

    private static PasswordMetrics applyComplexity(PasswordMetrics passwordMetrics, boolean z, ComplexityBucket complexityBucket) {
        PasswordMetrics passwordMetrics2 = new PasswordMetrics(passwordMetrics);
        if (!complexityBucket.canHaveSequence()) {
            passwordMetrics2.seqLength = Math.min(passwordMetrics2.seqLength, 3);
        }
        passwordMetrics2.length = Math.max(passwordMetrics2.length, complexityBucket.getMinimumLength(!z));
        if (!z && !complexityBucket.allowsNumericPassword()) {
            passwordMetrics2.nonNumeric = Math.max(passwordMetrics2.nonNumeric, 1);
        }
        return passwordMetrics2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PasswordMetrics passwordMetrics = (PasswordMetrics) obj;
        return this.credType == passwordMetrics.credType && this.length == passwordMetrics.length && this.letters == passwordMetrics.letters && this.upperCase == passwordMetrics.upperCase && this.lowerCase == passwordMetrics.lowerCase && this.numeric == passwordMetrics.numeric && this.symbols == passwordMetrics.symbols && this.nonLetter == passwordMetrics.nonLetter && this.nonNumeric == passwordMetrics.nonNumeric && this.seqLength == passwordMetrics.seqLength;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.credType), Integer.valueOf(this.length), Integer.valueOf(this.letters), Integer.valueOf(this.upperCase), Integer.valueOf(this.lowerCase), Integer.valueOf(this.numeric), Integer.valueOf(this.symbols), Integer.valueOf(this.nonLetter), Integer.valueOf(this.nonNumeric), Integer.valueOf(this.seqLength));
    }
}
