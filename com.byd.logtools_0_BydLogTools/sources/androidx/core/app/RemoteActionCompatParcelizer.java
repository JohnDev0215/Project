package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(androidx.versionedparcelable.a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f1250a = (IconCompat) aVar.v(remoteActionCompat.f1250a, 1);
        remoteActionCompat.f1251b = aVar.l(remoteActionCompat.f1251b, 2);
        remoteActionCompat.f1252c = aVar.l(remoteActionCompat.f1252c, 3);
        remoteActionCompat.f1253d = (PendingIntent) aVar.r(remoteActionCompat.f1253d, 4);
        remoteActionCompat.f1254e = aVar.h(remoteActionCompat.f1254e, 5);
        remoteActionCompat.f1255f = aVar.h(remoteActionCompat.f1255f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, androidx.versionedparcelable.a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f1250a, 1);
        aVar.D(remoteActionCompat.f1251b, 2);
        aVar.D(remoteActionCompat.f1252c, 3);
        aVar.H(remoteActionCompat.f1253d, 4);
        aVar.z(remoteActionCompat.f1254e, 5);
        aVar.z(remoteActionCompat.f1255f, 6);
    }
}
