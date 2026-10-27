package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import b.q.a;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f443a = (IconCompat) aVar.v(remoteActionCompat.f443a, 1);
        remoteActionCompat.f444b = aVar.l(remoteActionCompat.f444b, 2);
        remoteActionCompat.f445c = aVar.l(remoteActionCompat.f445c, 3);
        remoteActionCompat.f446d = (PendingIntent) aVar.r(remoteActionCompat.f446d, 4);
        remoteActionCompat.f447e = aVar.h(remoteActionCompat.f447e, 5);
        remoteActionCompat.f448f = aVar.h(remoteActionCompat.f448f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f443a, 1);
        aVar.D(remoteActionCompat.f444b, 2);
        aVar.D(remoteActionCompat.f445c, 3);
        aVar.H(remoteActionCompat.f446d, 4);
        aVar.z(remoteActionCompat.f447e, 5);
        aVar.z(remoteActionCompat.f448f, 6);
    }
}
