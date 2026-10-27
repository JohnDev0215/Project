package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.text.internal.client.zzi;

/* JADX INFO: renamed from: xk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1780xk implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        while (parcel.dataPosition() < iJ) {
            AbstractC1060kf.f(parcel, parcel.readInt());
        }
        AbstractC1060kf.e(parcel, iJ);
        return new zzi();
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzi[i];
    }
}
