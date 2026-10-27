package defpackage;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.GetServiceRequest;

/* JADX INFO: renamed from: vf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1665vf implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        String strN = null;
        IBinder iBinderO = null;
        Scope[] scopeArr = null;
        Bundle bundleP = null;
        Account account = null;
        Feature[] featureArr = null;
        Feature[] featureArr2 = null;
        int iK = 0;
        int iK2 = 0;
        int iK3 = 0;
        boolean zI = false;
        int iK4 = 0;
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
                    iK3 = AbstractC1060kf.k(parcel, i);
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    strN = AbstractC1060kf.n(parcel, i);
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    iBinderO = AbstractC1060kf.o(parcel, i);
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    scopeArr = (Scope[]) AbstractC1060kf.g(parcel, i, Scope.CREATOR);
                    break;
                case 7:
                    bundleP = AbstractC1060kf.p(parcel, i);
                    break;
                case 8:
                    account = (Account) AbstractC1060kf.b(parcel, i, Account.CREATOR);
                    break;
                case 9:
                default:
                    AbstractC1060kf.f(parcel, i);
                    break;
                case 10:
                    featureArr = (Feature[]) AbstractC1060kf.g(parcel, i, Feature.CREATOR);
                    break;
                case 11:
                    featureArr2 = (Feature[]) AbstractC1060kf.g(parcel, i, Feature.CREATOR);
                    break;
                case 12:
                    zI = AbstractC1060kf.i(parcel, i);
                    break;
                case 13:
                    iK4 = AbstractC1060kf.k(parcel, i);
                    break;
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new GetServiceRequest(iK, iK2, iK3, strN, iBinderO, scopeArr, bundleP, account, featureArr, featureArr2, zI, iK4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new GetServiceRequest[i];
    }
}
