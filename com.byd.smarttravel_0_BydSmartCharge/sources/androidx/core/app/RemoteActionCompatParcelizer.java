package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import b.s.a;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f321a = (IconCompat) aVar.v(remoteActionCompat.f321a, 1);
        remoteActionCompat.f322b = aVar.l(remoteActionCompat.f322b, 2);
        remoteActionCompat.f323c = aVar.l(remoteActionCompat.f323c, 3);
        remoteActionCompat.f324d = (PendingIntent) aVar.r(remoteActionCompat.f324d, 4);
        remoteActionCompat.e = aVar.h(remoteActionCompat.e, 5);
        remoteActionCompat.f = aVar.h(remoteActionCompat.f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f321a, 1);
        aVar.D(remoteActionCompat.f322b, 2);
        aVar.D(remoteActionCompat.f323c, 3);
        aVar.H(remoteActionCompat.f324d, 4);
        aVar.z(remoteActionCompat.e, 5);
        aVar.z(remoteActionCompat.f, 6);
    }
}
