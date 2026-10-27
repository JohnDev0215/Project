package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;
import org.chromium.content.common.SurfaceWrapper;

/* JADX INFO: renamed from: yD, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1810yD implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new SurfaceWrapper((Surface) Surface.CREATOR.createFromParcel(parcel), parcel.readInt() == 1);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new SurfaceWrapper[i];
    }
}
