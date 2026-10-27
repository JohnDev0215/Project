package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManagerState;

/* JADX INFO: renamed from: l4, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1086l4 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new FragmentManagerState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new FragmentManagerState[i];
    }
}
