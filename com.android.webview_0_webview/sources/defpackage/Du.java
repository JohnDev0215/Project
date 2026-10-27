package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import org.chromium.base.process_launcher.FileDescriptorInfo;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Du implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new FileDescriptorInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new FileDescriptorInfo[i];
    }
}
