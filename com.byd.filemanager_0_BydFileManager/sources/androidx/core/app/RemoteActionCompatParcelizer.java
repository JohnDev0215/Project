package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(androidx.versionedparcelable.a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f942a = (IconCompat) aVar.v(remoteActionCompat.f942a, 1);
        remoteActionCompat.f943b = aVar.l(remoteActionCompat.f943b, 2);
        remoteActionCompat.f944c = aVar.l(remoteActionCompat.f944c, 3);
        remoteActionCompat.f945d = (PendingIntent) aVar.r(remoteActionCompat.f945d, 4);
        remoteActionCompat.f946e = aVar.h(remoteActionCompat.f946e, 5);
        remoteActionCompat.f = aVar.h(remoteActionCompat.f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, androidx.versionedparcelable.a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f942a, 1);
        aVar.D(remoteActionCompat.f943b, 2);
        aVar.D(remoteActionCompat.f944c, 3);
        aVar.H(remoteActionCompat.f945d, 4);
        aVar.z(remoteActionCompat.f946e, 5);
        aVar.z(remoteActionCompat.f, 6);
    }
}
