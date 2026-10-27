package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(androidx.versionedparcelable.a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f828a = (IconCompat) aVar.v(remoteActionCompat.f828a, 1);
        remoteActionCompat.f829b = aVar.l(remoteActionCompat.f829b, 2);
        remoteActionCompat.f830c = aVar.l(remoteActionCompat.f830c, 3);
        remoteActionCompat.f831d = (PendingIntent) aVar.r(remoteActionCompat.f831d, 4);
        remoteActionCompat.e = aVar.h(remoteActionCompat.e, 5);
        remoteActionCompat.f = aVar.h(remoteActionCompat.f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, androidx.versionedparcelable.a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f828a, 1);
        aVar.D(remoteActionCompat.f829b, 2);
        aVar.D(remoteActionCompat.f830c, 3);
        aVar.H(remoteActionCompat.f831d, 4);
        aVar.z(remoteActionCompat.e, 5);
        aVar.z(remoteActionCompat.f, 6);
    }
}
