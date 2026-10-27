package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.face.internal.client.FaceParcel;
import com.google.android.gms.vision.face.internal.client.LandmarkParcel;
import com.google.android.gms.vision.face.internal.client.zzb;

/* JADX INFO: renamed from: ek, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0730ek implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        LandmarkParcel[] landmarkParcelArr = null;
        zzb[] zzbVarArr = null;
        int iK = 0;
        int iK2 = 0;
        float fM = 0.0f;
        float fM2 = 0.0f;
        float fM3 = 0.0f;
        float fM4 = 0.0f;
        float fM5 = Float.MAX_VALUE;
        float fM6 = Float.MAX_VALUE;
        float fM7 = Float.MAX_VALUE;
        float fM8 = 0.0f;
        float fM9 = 0.0f;
        float fM10 = 0.0f;
        float fM11 = -1.0f;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iK = AbstractC1060kf.k(parcel, i);
                    break;
                case 2:
                    iK2 = AbstractC1060kf.k(parcel, i);
                    break;
                case 3:
                    fM = AbstractC1060kf.m(parcel, i);
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    fM2 = AbstractC1060kf.m(parcel, i);
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    fM3 = AbstractC1060kf.m(parcel, i);
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    fM4 = AbstractC1060kf.m(parcel, i);
                    break;
                case 7:
                    fM5 = AbstractC1060kf.m(parcel, i);
                    break;
                case 8:
                    fM6 = AbstractC1060kf.m(parcel, i);
                    break;
                case 9:
                    landmarkParcelArr = (LandmarkParcel[]) AbstractC1060kf.g(parcel, i, LandmarkParcel.CREATOR);
                    break;
                case 10:
                    fM8 = AbstractC1060kf.m(parcel, i);
                    break;
                case 11:
                    fM9 = AbstractC1060kf.m(parcel, i);
                    break;
                case 12:
                    fM10 = AbstractC1060kf.m(parcel, i);
                    break;
                case 13:
                    zzbVarArr = (zzb[]) AbstractC1060kf.g(parcel, i, zzb.CREATOR);
                    break;
                case 14:
                    fM7 = AbstractC1060kf.m(parcel, i);
                    break;
                case 15:
                    fM11 = AbstractC1060kf.m(parcel, i);
                    break;
                default:
                    AbstractC1060kf.f(parcel, i);
                    break;
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new FaceParcel(iK, iK2, fM, fM2, fM3, fM4, fM5, fM6, fM7, landmarkParcelArr, fM8, fM9, fM10, zzbVarArr, fM11);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new FaceParcel[i];
    }
}
