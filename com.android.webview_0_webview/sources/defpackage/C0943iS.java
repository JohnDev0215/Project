package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import org.chromium.weblayer_private.interfaces.NavigateParams;

/* JADX INFO: renamed from: iS, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0943iS implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new NavigateParams(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new NavigateParams[i];
    }
}
