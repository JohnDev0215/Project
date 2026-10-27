package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzcl implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int header;
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        long j = 0;
        int i = 0;
        int i2 = 0;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        ArrayList<String> arrayListCreateStringList = null;
        while (true) {
            long j2 = j;
            while (parcel.dataPosition() < iValidateObjectHeader) {
                header = SafeParcelReader.readHeader(parcel);
                switch (SafeParcelReader.getFieldId(header)) {
                    case 2:
                        break;
                    case 3:
                        i = SafeParcelReader.readInt(parcel, header);
                        break;
                    case 4:
                        strCreateString2 = SafeParcelReader.createString(parcel, header);
                        break;
                    case 5:
                        strCreateString3 = SafeParcelReader.createString(parcel, header);
                        break;
                    case 6:
                        strCreateString4 = SafeParcelReader.createString(parcel, header);
                        break;
                    case 7:
                        strCreateString5 = SafeParcelReader.createString(parcel, header);
                        break;
                    case 8:
                        i2 = SafeParcelReader.readInt(parcel, header);
                        break;
                    case 9:
                        arrayListCreateStringList = SafeParcelReader.createStringList(parcel, header);
                        break;
                    case 10:
                        strCreateString = SafeParcelReader.createString(parcel, header);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, header);
                        break;
                }
            }
            SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
            return new MediaTrack(j2, i, strCreateString2, strCreateString3, strCreateString4, strCreateString5, i2, arrayListCreateStringList, CastUtils.jsonStringToJsonObject(strCreateString));
            j = SafeParcelReader.readLong(parcel, header);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new MediaTrack[i];
    }
}
