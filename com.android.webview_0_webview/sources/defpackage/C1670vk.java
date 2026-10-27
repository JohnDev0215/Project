package defpackage;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.text.internal.client.RecognitionOptions;

/* JADX INFO: renamed from: vk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1670vk implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iJ = AbstractC1060kf.j(parcel);
        Rect rect = null;
        while (parcel.dataPosition() < iJ) {
            int i = parcel.readInt();
            if ((65535 & i) != 2) {
                AbstractC1060kf.f(parcel, i);
            } else {
                rect = (Rect) AbstractC1060kf.b(parcel, i, Rect.CREATOR);
            }
        }
        AbstractC1060kf.e(parcel, iJ);
        return new RecognitionOptions(rect);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new RecognitionOptions[i];
    }
}
