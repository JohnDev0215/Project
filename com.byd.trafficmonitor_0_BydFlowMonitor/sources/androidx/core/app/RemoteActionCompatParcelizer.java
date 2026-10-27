package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import b.z.a;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f788a = (IconCompat) aVar.v(remoteActionCompat.f788a, 1);
        remoteActionCompat.f789b = aVar.l(remoteActionCompat.f789b, 2);
        remoteActionCompat.f790c = aVar.l(remoteActionCompat.f790c, 3);
        remoteActionCompat.f791d = (PendingIntent) aVar.r(remoteActionCompat.f791d, 4);
        remoteActionCompat.f792e = aVar.h(remoteActionCompat.f792e, 5);
        remoteActionCompat.f793f = aVar.h(remoteActionCompat.f793f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f788a, 1);
        aVar.D(remoteActionCompat.f789b, 2);
        aVar.D(remoteActionCompat.f790c, 3);
        aVar.H(remoteActionCompat.f791d, 4);
        aVar.z(remoteActionCompat.f792e, 5);
        aVar.z(remoteActionCompat.f793f, 6);
    }
}
