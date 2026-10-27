package android.support.v4.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import defpackage.AbstractBinderC1302p;
import defpackage.BinderC1466s;
import defpackage.InterfaceC1357q;
import defpackage.r;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator CREATOR = new r();
    public final Handler s = null;
    public InterfaceC1357q t;

    public ResultReceiver(Parcel parcel) {
        this.t = AbstractBinderC1302p.c(parcel.readStrongBinder());
    }

    public void a(int i, Bundle bundle) {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.t == null) {
                this.t = new BinderC1466s(this);
            }
            parcel.writeStrongBinder(this.t.asBinder());
        }
    }
}
