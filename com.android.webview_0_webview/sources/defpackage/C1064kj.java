package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.signin.internal.zzg;
import java.util.ArrayList;

/* JADX INFO: renamed from: kj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1064kj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        ArrayList<String> arrayList = null;
        String strN = null;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            int i2 = 65535 & i;
            if (i2 == 1) {
                int iA = AbstractC1060kf.a(parcel, i);
                int iDataPosition = parcel.dataPosition();
                if (iA == 0) {
                    arrayList = null;
                } else {
                    ArrayList<String> arrayListCreateStringArrayList = parcel.createStringArrayList();
                    parcel.setDataPosition(iDataPosition + iA);
                    arrayList = arrayListCreateStringArrayList;
                }
            } else if (i2 != 2) {
                AbstractC1060kf.f(parcel, i);
            } else {
                strN = AbstractC1060kf.n(parcel, i);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zzg(arrayList, strN);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzg[i];
    }
}
