package android.content.integrity;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class AtomicFormula extends IntegrityFormula {
    public static final int APP_CERTIFICATE = 1;
    public static final int EQ = 0;
    public static final int GT = 1;
    public static final int GTE = 2;
    public static final int INSTALLER_CERTIFICATE = 3;
    public static final int INSTALLER_NAME = 2;
    public static final int PACKAGE_NAME = 0;
    public static final int PRE_INSTALLED = 5;
    public static final int STAMP_CERTIFICATE_HASH = 7;
    public static final int STAMP_TRUSTED = 6;
    public static final int VERSION_CODE = 4;
    private final int mKey;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Key {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Operator {
    }

    private static boolean isValidKey(int i) {
        return i == 0 || i == 1 || i == 4 || i == 2 || i == 3 || i == 5 || i == 6 || i == 7;
    }

    public AtomicFormula(int i) {
        Preconditions.checkArgument(isValidKey(i), String.format("Unknown key: %d", Integer.valueOf(i)));
        this.mKey = i;
    }

    public static final class LongAtomicFormula extends AtomicFormula implements Parcelable {
        public static final Parcelable.Creator<LongAtomicFormula> CREATOR = new Parcelable.Creator<LongAtomicFormula>() { // from class: android.content.integrity.AtomicFormula.LongAtomicFormula.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LongAtomicFormula createFromParcel(Parcel parcel) {
                return new LongAtomicFormula(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LongAtomicFormula[] newArray(int i) {
                return new LongAtomicFormula[i];
            }
        };
        private final Integer mOperator;
        private final Long mValue;

        private static boolean isValidOperator(int i) {
            return i == 0 || i == 1 || i == 2;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.content.integrity.IntegrityFormula
        public int getTag() {
            return 2;
        }

        @Override // android.content.integrity.IntegrityFormula
        public boolean isAppCertificateFormula() {
            return false;
        }

        @Override // android.content.integrity.IntegrityFormula
        public boolean isInstallerFormula() {
            return false;
        }

        public LongAtomicFormula(int i) {
            super(i);
            Preconditions.checkArgument(i == 4, String.format("Key %s cannot be used with LongAtomicFormula", keyToString(i)));
            this.mValue = null;
            this.mOperator = null;
        }

        public LongAtomicFormula(int i, int i2, long j) {
            super(i);
            Preconditions.checkArgument(i == 4, String.format("Key %s cannot be used with LongAtomicFormula", keyToString(i)));
            Preconditions.checkArgument(isValidOperator(i2), String.format("Unknown operator: %d", Integer.valueOf(i2)));
            this.mOperator = Integer.valueOf(i2);
            this.mValue = Long.valueOf(j);
        }

        LongAtomicFormula(Parcel parcel) {
            super(parcel.readInt());
            this.mValue = Long.valueOf(parcel.readLong());
            this.mOperator = Integer.valueOf(parcel.readInt());
        }

        @Override // android.content.integrity.IntegrityFormula
        public boolean matches(AppInstallMetadata appInstallMetadata) {
            if (this.mValue == null || this.mOperator == null) {
                return false;
            }
            long longMetadataValue = getLongMetadataValue(appInstallMetadata, getKey());
            int iIntValue = this.mOperator.intValue();
            if (iIntValue == 0) {
                return longMetadataValue == this.mValue.longValue();
            }
            if (iIntValue == 1) {
                return longMetadataValue > this.mValue.longValue();
            }
            if (iIntValue == 2) {
                return longMetadataValue >= this.mValue.longValue();
            }
            throw new IllegalArgumentException(String.format("Unexpected operator %d", this.mOperator));
        }

        public String toString() {
            return (this.mValue == null || this.mOperator == null) ? String.format("(%s)", keyToString(getKey())) : String.format("(%s %s %s)", keyToString(getKey()), operatorToString(this.mOperator.intValue()), this.mValue);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            LongAtomicFormula longAtomicFormula = (LongAtomicFormula) obj;
            return getKey() == longAtomicFormula.getKey() && this.mValue == longAtomicFormula.mValue && this.mOperator == longAtomicFormula.mOperator;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(getKey()), this.mOperator, this.mValue);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (this.mValue == null || this.mOperator == null) {
                throw new IllegalStateException("Cannot write an empty LongAtomicFormula.");
            }
            parcel.writeInt(getKey());
            parcel.writeLong(this.mValue.longValue());
            parcel.writeInt(this.mOperator.intValue());
        }

        public Long getValue() {
            return this.mValue;
        }

        public Integer getOperator() {
            return this.mOperator;
        }

        private static long getLongMetadataValue(AppInstallMetadata appInstallMetadata, int i) {
            if (i == 4) {
                return appInstallMetadata.getVersionCode();
            }
            throw new IllegalStateException("Unexpected key in IntAtomicFormula" + i);
        }
    }

    public static final class StringAtomicFormula extends AtomicFormula implements Parcelable {
        public static final Parcelable.Creator<StringAtomicFormula> CREATOR = new Parcelable.Creator<StringAtomicFormula>() { // from class: android.content.integrity.AtomicFormula.StringAtomicFormula.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public StringAtomicFormula createFromParcel(Parcel parcel) {
                return new StringAtomicFormula(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public StringAtomicFormula[] newArray(int i) {
                return new StringAtomicFormula[i];
            }
        };
        private final Boolean mIsHashedValue;
        private final String mValue;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.content.integrity.IntegrityFormula
        public int getTag() {
            return 1;
        }

        public StringAtomicFormula(int i) {
            super(i);
            Preconditions.checkArgument(i == 0 || i == 1 || i == 3 || i == 2 || i == 7, String.format("Key %s cannot be used with StringAtomicFormula", keyToString(i)));
            this.mValue = null;
            this.mIsHashedValue = null;
        }

        public StringAtomicFormula(int i, String str, boolean z) {
            super(i);
            Preconditions.checkArgument(i == 0 || i == 1 || i == 3 || i == 2 || i == 7, String.format("Key %s cannot be used with StringAtomicFormula", keyToString(i)));
            this.mValue = str;
            this.mIsHashedValue = Boolean.valueOf(z);
        }

        public StringAtomicFormula(int i, String str) {
            super(i);
            Preconditions.checkArgument(i == 0 || i == 1 || i == 3 || i == 2 || i == 7, String.format("Key %s cannot be used with StringAtomicFormula", keyToString(i)));
            String strHashValue = hashValue(i, str);
            this.mValue = strHashValue;
            this.mIsHashedValue = Boolean.valueOf(i == 1 || i == 3 || i == 7 || !strHashValue.equals(str));
        }

        StringAtomicFormula(Parcel parcel) {
            super(parcel.readInt());
            this.mValue = parcel.readStringNoHelper();
            this.mIsHashedValue = Boolean.valueOf(parcel.readByte() != 0);
        }

        @Override // android.content.integrity.IntegrityFormula
        public boolean matches(AppInstallMetadata appInstallMetadata) {
            if (this.mValue == null || this.mIsHashedValue == null) {
                return false;
            }
            return getMetadataValue(appInstallMetadata, getKey()).contains(this.mValue);
        }

        @Override // android.content.integrity.IntegrityFormula
        public boolean isAppCertificateFormula() {
            return getKey() == 1;
        }

        @Override // android.content.integrity.IntegrityFormula
        public boolean isInstallerFormula() {
            return getKey() == 2 || getKey() == 3;
        }

        public String toString() {
            return (this.mValue == null || this.mIsHashedValue == null) ? String.format("(%s)", keyToString(getKey())) : String.format("(%s %s %s)", keyToString(getKey()), operatorToString(0), this.mValue);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            StringAtomicFormula stringAtomicFormula = (StringAtomicFormula) obj;
            return getKey() == stringAtomicFormula.getKey() && Objects.equals(this.mValue, stringAtomicFormula.mValue);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(getKey()), this.mValue);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (this.mValue == null || this.mIsHashedValue == null) {
                throw new IllegalStateException("Cannot write an empty StringAtomicFormula.");
            }
            parcel.writeInt(getKey());
            parcel.writeStringNoHelper(this.mValue);
            parcel.writeByte(this.mIsHashedValue.booleanValue() ? (byte) 1 : (byte) 0);
        }

        public String getValue() {
            return this.mValue;
        }

        public Boolean getIsHashedValue() {
            return this.mIsHashedValue;
        }

        private static List<String> getMetadataValue(AppInstallMetadata appInstallMetadata, int i) {
            if (i == 0) {
                return Collections.singletonList(appInstallMetadata.getPackageName());
            }
            if (i == 1) {
                return appInstallMetadata.getAppCertificates();
            }
            if (i == 2) {
                return Collections.singletonList(appInstallMetadata.getInstallerName());
            }
            if (i == 3) {
                return appInstallMetadata.getInstallerCertificates();
            }
            if (i == 7) {
                return Collections.singletonList(appInstallMetadata.getStampCertificateHash());
            }
            throw new IllegalStateException("Unexpected key in StringAtomicFormula: " + i);
        }

        private static String hashValue(int i, String str) {
            return (str.length() <= 32 || !(i == 0 || i == 2)) ? str : hash(str);
        }

        private static String hash(String str) {
            try {
                return IntegrityUtils.getHexDigest(MessageDigest.getInstance("SHA-256").digest(str.getBytes(StandardCharsets.UTF_8)));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("SHA-256 algorithm not found", e);
            }
        }
    }

    public static final class BooleanAtomicFormula extends AtomicFormula implements Parcelable {
        public static final Parcelable.Creator<BooleanAtomicFormula> CREATOR = new Parcelable.Creator<BooleanAtomicFormula>() { // from class: android.content.integrity.AtomicFormula.BooleanAtomicFormula.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BooleanAtomicFormula createFromParcel(Parcel parcel) {
                return new BooleanAtomicFormula(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BooleanAtomicFormula[] newArray(int i) {
                return new BooleanAtomicFormula[i];
            }
        };
        private final Boolean mValue;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.content.integrity.IntegrityFormula
        public int getTag() {
            return 3;
        }

        @Override // android.content.integrity.IntegrityFormula
        public boolean isAppCertificateFormula() {
            return false;
        }

        @Override // android.content.integrity.IntegrityFormula
        public boolean isInstallerFormula() {
            return false;
        }

        public BooleanAtomicFormula(int i) {
            super(i);
            Preconditions.checkArgument(i == 5 || i == 6, String.format("Key %s cannot be used with BooleanAtomicFormula", keyToString(i)));
            this.mValue = null;
        }

        public BooleanAtomicFormula(int i, boolean z) {
            super(i);
            Preconditions.checkArgument(i == 5 || i == 6, String.format("Key %s cannot be used with BooleanAtomicFormula", keyToString(i)));
            this.mValue = Boolean.valueOf(z);
        }

        BooleanAtomicFormula(Parcel parcel) {
            super(parcel.readInt());
            this.mValue = Boolean.valueOf(parcel.readByte() != 0);
        }

        @Override // android.content.integrity.IntegrityFormula
        public boolean matches(AppInstallMetadata appInstallMetadata) {
            return this.mValue != null && getBooleanMetadataValue(appInstallMetadata, getKey()) == this.mValue.booleanValue();
        }

        public String toString() {
            return this.mValue == null ? String.format("(%s)", keyToString(getKey())) : String.format("(%s %s %s)", keyToString(getKey()), operatorToString(0), this.mValue);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            BooleanAtomicFormula booleanAtomicFormula = (BooleanAtomicFormula) obj;
            return getKey() == booleanAtomicFormula.getKey() && this.mValue == booleanAtomicFormula.mValue;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(getKey()), this.mValue);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (this.mValue == null) {
                throw new IllegalStateException("Cannot write an empty BooleanAtomicFormula.");
            }
            parcel.writeInt(getKey());
            parcel.writeByte(this.mValue.booleanValue() ? (byte) 1 : (byte) 0);
        }

        public Boolean getValue() {
            return this.mValue;
        }

        private static boolean getBooleanMetadataValue(AppInstallMetadata appInstallMetadata, int i) {
            if (i == 5) {
                return appInstallMetadata.isPreInstalled();
            }
            if (i == 6) {
                return appInstallMetadata.isStampTrusted();
            }
            throw new IllegalStateException("Unexpected key in BooleanAtomicFormula: " + i);
        }
    }

    public int getKey() {
        return this.mKey;
    }

    static String keyToString(int i) {
        switch (i) {
            case 0:
                return "PACKAGE_NAME";
            case 1:
                return "APP_CERTIFICATE";
            case 2:
                return "INSTALLER_NAME";
            case 3:
                return "INSTALLER_CERTIFICATE";
            case 4:
                return "VERSION_CODE";
            case 5:
                return "PRE_INSTALLED";
            case 6:
                return "STAMP_TRUSTED";
            case 7:
                return "STAMP_CERTIFICATE_HASH";
            default:
                throw new IllegalArgumentException("Unknown key " + i);
        }
    }

    static String operatorToString(int i) {
        if (i == 0) {
            return "EQ";
        }
        if (i == 1) {
            return "GT";
        }
        if (i == 2) {
            return "GTE";
        }
        throw new IllegalArgumentException("Unknown operator " + i);
    }
}
