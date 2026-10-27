package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzf implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        ArrayList<String> arrayListCreateStringList = null;
        LaunchOptions launchOptions = null;
        CastMediaOptions castMediaOptions = null;
        ArrayList<String> arrayListCreateStringList2 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        double d = 0.0d;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    arrayListCreateStringList = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 4:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 5:
                    launchOptions = (LaunchOptions) SafeParcelReader.createParcelable(parcel, header, LaunchOptions.CREATOR);
                    break;
                case 6:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 7:
                    castMediaOptions = (CastMediaOptions) SafeParcelReader.createParcelable(parcel, header, CastMediaOptions.CREATOR);
                    break;
                case 8:
                    z3 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 9:
                    d = SafeParcelReader.readDouble(parcel, header);
                    break;
                case 10:
                    z4 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 11:
                    z5 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 12:
                    z6 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 13:
                    arrayListCreateStringList2 = SafeParcelReader.createStringList(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new CastOptions(strCreateString, arrayListCreateStringList, z, launchOptions, z2, castMediaOptions, z3, d, z4, z5, z6, arrayListCreateStringList2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new CastOptions[i];
    }
}
