package com.google.android.exoplayer2.drm;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes6.dex */
public final class DrmInitData implements Comparator<SchemeData>, Parcelable {
    public static final Parcelable.Creator<DrmInitData> CREATOR = new Parcelable.Creator<DrmInitData>() { // from class: com.google.android.exoplayer2.drm.DrmInitData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DrmInitData createFromParcel(Parcel parcel) {
            return new DrmInitData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DrmInitData[] newArray(int i) {
            return new DrmInitData[i];
        }
    };
    private int hashCode;
    public final int schemeDataCount;
    private final SchemeData[] schemeDatas;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DrmInitData(List<SchemeData> list) {
        this(false, (SchemeData[]) list.toArray(new SchemeData[list.size()]));
    }

    public DrmInitData(SchemeData... schemeDataArr) {
        this(true, schemeDataArr);
    }

    private DrmInitData(boolean z, SchemeData... schemeDataArr) {
        schemeDataArr = z ? (SchemeData[]) schemeDataArr.clone() : schemeDataArr;
        Arrays.sort(schemeDataArr, this);
        for (int i = 1; i < schemeDataArr.length; i++) {
            if (schemeDataArr[i - 1].uuid.equals(schemeDataArr[i].uuid)) {
                throw new IllegalArgumentException("Duplicate data for uuid: " + schemeDataArr[i].uuid);
            }
        }
        this.schemeDatas = schemeDataArr;
        this.schemeDataCount = schemeDataArr.length;
    }

    DrmInitData(Parcel parcel) {
        SchemeData[] schemeDataArr = (SchemeData[]) parcel.createTypedArray(SchemeData.CREATOR);
        this.schemeDatas = schemeDataArr;
        this.schemeDataCount = schemeDataArr.length;
    }

    public SchemeData get(int i) {
        return this.schemeDatas[i];
    }

    public DrmInitData copyWithSchemeType(String str) {
        boolean z;
        SchemeData[] schemeDataArr = this.schemeDatas;
        int length = schemeDataArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (!Util.areEqual(schemeDataArr[i].type, str)) {
                z = true;
                break;
            }
            i++;
        }
        if (!z) {
            return this;
        }
        int length2 = this.schemeDatas.length;
        SchemeData[] schemeDataArr2 = new SchemeData[length2];
        for (int i2 = 0; i2 < length2; i2++) {
            schemeDataArr2[i2] = this.schemeDatas[i2].copyWithSchemeType(str);
        }
        return new DrmInitData(schemeDataArr2);
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = Arrays.hashCode(this.schemeDatas);
        }
        return this.hashCode;
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DrmInitData.class != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.schemeDatas, ((DrmInitData) obj).schemeDatas);
    }

    @Override // java.util.Comparator
    public int compare(SchemeData schemeData, SchemeData schemeData2) {
        UUID uuid = C.UUID_NIL;
        if (uuid.equals(schemeData.uuid)) {
            return uuid.equals(schemeData2.uuid) ? 0 : 1;
        }
        return schemeData.uuid.compareTo(schemeData2.uuid);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.schemeDatas, 0);
    }

    public static final class SchemeData implements Parcelable {
        public static final Parcelable.Creator<SchemeData> CREATOR = new Parcelable.Creator<SchemeData>() { // from class: com.google.android.exoplayer2.drm.DrmInitData.SchemeData.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SchemeData createFromParcel(Parcel parcel) {
                return new SchemeData(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SchemeData[] newArray(int i) {
                return new SchemeData[i];
            }
        };
        public final byte[] data;
        private int hashCode;
        public final String mimeType;
        public final boolean requiresSecureDecryption;
        public final String type;
        private final UUID uuid;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public SchemeData(UUID uuid, String str, String str2, byte[] bArr) {
            this(uuid, str, str2, bArr, false);
        }

        public SchemeData(UUID uuid, String str, String str2, byte[] bArr, boolean z) {
            this.uuid = (UUID) Assertions.checkNotNull(uuid);
            this.type = str;
            this.mimeType = (String) Assertions.checkNotNull(str2);
            this.data = (byte[]) Assertions.checkNotNull(bArr);
            this.requiresSecureDecryption = z;
        }

        SchemeData(Parcel parcel) {
            this.uuid = new UUID(parcel.readLong(), parcel.readLong());
            this.type = parcel.readString();
            this.mimeType = parcel.readString();
            this.data = parcel.createByteArray();
            this.requiresSecureDecryption = parcel.readByte() != 0;
        }

        public SchemeData copyWithSchemeType(String str) {
            return Util.areEqual(this.type, str) ? this : new SchemeData(this.uuid, str, this.mimeType, this.data, this.requiresSecureDecryption);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof SchemeData)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            SchemeData schemeData = (SchemeData) obj;
            return this.mimeType.equals(schemeData.mimeType) && Util.areEqual(this.uuid, schemeData.uuid) && Util.areEqual(this.type, schemeData.type) && Arrays.equals(this.data, schemeData.data);
        }

        public int hashCode() {
            if (this.hashCode == 0) {
                int iHashCode = this.uuid.hashCode() * 31;
                String str = this.type;
                this.hashCode = ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.mimeType.hashCode()) * 31) + Arrays.hashCode(this.data);
            }
            return this.hashCode;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.uuid.getMostSignificantBits());
            parcel.writeLong(this.uuid.getLeastSignificantBits());
            parcel.writeString(this.type);
            parcel.writeString(this.mimeType);
            parcel.writeByteArray(this.data);
            parcel.writeByte(this.requiresSecureDecryption ? (byte) 1 : (byte) 0);
        }
    }
}
