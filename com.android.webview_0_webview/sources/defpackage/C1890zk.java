package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.text.internal.client.zza;
import com.google.android.gms.vision.text.internal.client.zzi;
import com.google.android.gms.vision.text.internal.client.zzp;

/* JADX INFO: renamed from: zk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1890zk implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        zzi[] zziVarArr = null;
        zza zzaVar = null;
        zza zzaVar2 = null;
        String strN = null;
        String strN2 = null;
        float fM = 0.0f;
        boolean zI = false;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    zziVarArr = (zzi[]) AbstractC1060kf.g(parcel, i, zzi.CREATOR);
                    break;
                case 3:
                    zzaVar = (zza) AbstractC1060kf.b(parcel, i, zza.CREATOR);
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    zzaVar2 = (zza) AbstractC1060kf.b(parcel, i, zza.CREATOR);
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    strN = AbstractC1060kf.n(parcel, i);
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    fM = AbstractC1060kf.m(parcel, i);
                    break;
                case 7:
                    strN2 = AbstractC1060kf.n(parcel, i);
                    break;
                case 8:
                    zI = AbstractC1060kf.i(parcel, i);
                    break;
                default:
                    AbstractC1060kf.f(parcel, i);
                    break;
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zzp(zziVarArr, zzaVar, zzaVar2, strN, fM, strN2, zI);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzp[i];
    }
}
