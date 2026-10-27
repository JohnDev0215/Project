package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(androidx.versionedparcelable.a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f1177a = (IconCompat) aVar.v(remoteActionCompat.f1177a, 1);
        remoteActionCompat.f1178b = aVar.l(remoteActionCompat.f1178b, 2);
        remoteActionCompat.f1179c = aVar.l(remoteActionCompat.f1179c, 3);
        remoteActionCompat.f1180d = (PendingIntent) aVar.r(remoteActionCompat.f1180d, 4);
        remoteActionCompat.f1181e = aVar.h(remoteActionCompat.f1181e, 5);
        remoteActionCompat.f1182f = aVar.h(remoteActionCompat.f1182f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, androidx.versionedparcelable.a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f1177a, 1);
        aVar.D(remoteActionCompat.f1178b, 2);
        aVar.D(remoteActionCompat.f1179c, 3);
        aVar.H(remoteActionCompat.f1180d, 4);
        aVar.z(remoteActionCompat.f1181e, 5);
        aVar.z(remoteActionCompat.f1182f, 6);
    }
}
