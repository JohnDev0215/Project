package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzz implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ApplicationMetadata applicationMetadata = null;
        com.google.android.gms.cast.zzar zzarVar = null;
        double d = 0.0d;
        double d2 = 0.0d;
        boolean z = false;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    d = SafeParcelReader.readDouble(parcel, header);
                    break;
                case 3:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 5:
                    applicationMetadata = (ApplicationMetadata) SafeParcelReader.createParcelable(parcel, header, ApplicationMetadata.CREATOR);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 7:
                    zzarVar = (com.google.android.gms.cast.zzar) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.cast.zzar.CREATOR);
                    break;
                case 8:
                    d2 = SafeParcelReader.readDouble(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzy(d, z, i, applicationMetadata, i2, zzarVar, d2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzy[i];
    }
}
