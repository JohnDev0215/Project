package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import org.chromium.base.library_loader.Linker;

/* JADX INFO: renamed from: ut, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1624ut implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new Linker.LibInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new Linker.LibInfo[i];
    }
}
