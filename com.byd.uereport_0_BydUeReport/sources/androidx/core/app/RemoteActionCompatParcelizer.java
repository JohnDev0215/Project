package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import m1.a;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f1240a = (IconCompat) aVar.v(remoteActionCompat.f1240a, 1);
        remoteActionCompat.f1241b = aVar.l(remoteActionCompat.f1241b, 2);
        remoteActionCompat.f1242c = aVar.l(remoteActionCompat.f1242c, 3);
        remoteActionCompat.f1243d = (PendingIntent) aVar.r(remoteActionCompat.f1243d, 4);
        remoteActionCompat.f1244e = aVar.h(remoteActionCompat.f1244e, 5);
        remoteActionCompat.f1245f = aVar.h(remoteActionCompat.f1245f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f1240a, 1);
        aVar.D(remoteActionCompat.f1241b, 2);
        aVar.D(remoteActionCompat.f1242c, 3);
        aVar.H(remoteActionCompat.f1243d, 4);
        aVar.z(remoteActionCompat.f1244e, 5);
        aVar.z(remoteActionCompat.f1245f, 6);
    }
}
