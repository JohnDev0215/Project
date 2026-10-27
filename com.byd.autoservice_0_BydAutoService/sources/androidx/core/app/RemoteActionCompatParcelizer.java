package androidx.core.app;

import a1.a;
import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f1151a = (IconCompat) aVar.v(remoteActionCompat.f1151a, 1);
        remoteActionCompat.f1152b = aVar.l(remoteActionCompat.f1152b, 2);
        remoteActionCompat.f1153c = aVar.l(remoteActionCompat.f1153c, 3);
        remoteActionCompat.f1154d = (PendingIntent) aVar.r(remoteActionCompat.f1154d, 4);
        remoteActionCompat.f1155e = aVar.h(remoteActionCompat.f1155e, 5);
        remoteActionCompat.f1156f = aVar.h(remoteActionCompat.f1156f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f1151a, 1);
        aVar.D(remoteActionCompat.f1152b, 2);
        aVar.D(remoteActionCompat.f1153c, 3);
        aVar.H(remoteActionCompat.f1154d, 4);
        aVar.z(remoteActionCompat.f1155e, 5);
        aVar.z(remoteActionCompat.f1156f, 6);
    }
}
