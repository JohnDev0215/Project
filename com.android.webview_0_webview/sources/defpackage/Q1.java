package defpackage;

import android.app.Notification;
import android.os.Parcel;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Q1 implements U1 {
    public final String a;
    public final int b;
    public final String c;
    public final Notification d;

    public Q1(String str, int i, String str2, Notification notification) {
        this.a = str;
        this.b = i;
        this.c = str2;
        this.d = notification;
    }

    public void a(InterfaceC0579c interfaceC0579c) {
        String str = this.a;
        int i = this.b;
        String str2 = this.c;
        Notification notification = this.d;
        C0467a c0467a = (C0467a) interfaceC0579c;
        Objects.requireNonNull(c0467a);
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
            parcelObtain.writeString(str);
            parcelObtain.writeInt(i);
            parcelObtain.writeString(str2);
            if (notification != null) {
                parcelObtain.writeInt(1);
                notification.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            c0467a.a.transact(1, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    public String toString() {
        return "NotifyTask[packageName:" + this.a + ", id:" + this.b + ", tag:" + this.c + "]";
    }
}
