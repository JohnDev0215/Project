package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(androidx.versionedparcelable.a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f1168a = (IconCompat) aVar.v(remoteActionCompat.f1168a, 1);
        remoteActionCompat.f1169b = aVar.l(remoteActionCompat.f1169b, 2);
        remoteActionCompat.f1170c = aVar.l(remoteActionCompat.f1170c, 3);
        remoteActionCompat.f1171d = (PendingIntent) aVar.r(remoteActionCompat.f1171d, 4);
        remoteActionCompat.f1172e = aVar.h(remoteActionCompat.f1172e, 5);
        remoteActionCompat.f = aVar.h(remoteActionCompat.f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, androidx.versionedparcelable.a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f1168a, 1);
        aVar.D(remoteActionCompat.f1169b, 2);
        aVar.D(remoteActionCompat.f1170c, 3);
        aVar.H(remoteActionCompat.f1171d, 4);
        aVar.z(remoteActionCompat.f1172e, 5);
        aVar.z(remoteActionCompat.f, 6);
    }
}
