package v1;

import android.app.Notification;
import android.os.Parcel;
import f.C1241a;
import f.InterfaceC1243c;

/* JADX INFO: renamed from: v1.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2539f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22964a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Notification f22965b;

    public C2539f(String str, Notification notification) {
        this.f22964a = str;
        this.f22965b = notification;
    }

    public final void a(InterfaceC1243c interfaceC1243c) {
        String str = this.f22964a;
        C1241a c1241a = (C1241a) interfaceC1243c;
        c1241a.getClass();
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken(InterfaceC1243c.f14527a);
            parcelObtain.writeString(str);
            parcelObtain.writeInt(1001);
            parcelObtain.writeString(null);
            Notification notification = this.f22965b;
            if (notification != null) {
                parcelObtain.writeInt(1);
                notification.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            c1241a.c.transact(1, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder("NotifyTask[packageName:"), this.f22964a, ", id:1001, tag:null]");
    }
}
