package defpackage;

import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.Parcel;
import android.util.Log;

/* JADX INFO: renamed from: Ki, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0188Ki extends AbstractC0259Oi {
    public final C0116Gi E;

    public C0188Ki(Context context, Looper looper, InterfaceC0110Gc interfaceC0110Gc, InterfaceC0128Hc interfaceC0128Hc, String str, C0381Ve c0381Ve) {
        super(context, looper, interfaceC0110Gc, interfaceC0128Hc, str, c0381Ve);
        this.E = new C0116Gi(context, this.D);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, defpackage.InterfaceC1882zc
    public final void disconnect() {
        synchronized (this.E) {
            if (a()) {
                try {
                    this.E.a();
                    this.E.c();
                } catch (Exception e) {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", e);
                }
                super.disconnect();
            } else {
                super.disconnect();
            }
        }
    }

    public final Location v() {
        C0116Gi c0116Gi = this.E;
        ((C0277Pi) c0116Gi.a).a();
        C0062Di c0062Di = (C0062Di) ((InterfaceC0080Ei) ((C0277Pi) c0116Gi.a).b());
        Parcel parcelC = c0062Di.c(7, c0062Di.f());
        Location location = (Location) AbstractC0383Vg.b(parcelC, Location.CREATOR);
        parcelC.recycle();
        return location;
    }
}
