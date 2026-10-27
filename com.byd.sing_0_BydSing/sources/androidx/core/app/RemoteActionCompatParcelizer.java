package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(androidx.versionedparcelable.a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f1349a = (IconCompat) aVar.v(remoteActionCompat.f1349a, 1);
        remoteActionCompat.f1350b = aVar.l(remoteActionCompat.f1350b, 2);
        remoteActionCompat.f1351c = aVar.l(remoteActionCompat.f1351c, 3);
        remoteActionCompat.f1352d = (PendingIntent) aVar.r(remoteActionCompat.f1352d, 4);
        remoteActionCompat.e = aVar.h(remoteActionCompat.e, 5);
        remoteActionCompat.f = aVar.h(remoteActionCompat.f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, androidx.versionedparcelable.a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f1349a, 1);
        aVar.D(remoteActionCompat.f1350b, 2);
        aVar.D(remoteActionCompat.f1351c, 3);
        aVar.H(remoteActionCompat.f1352d, 4);
        aVar.z(remoteActionCompat.e, 5);
        aVar.z(remoteActionCompat.f, 6);
    }
}
