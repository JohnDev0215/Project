package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(androidx.versionedparcelable.a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f820a = (IconCompat) aVar.v(remoteActionCompat.f820a, 1);
        remoteActionCompat.f821b = aVar.l(remoteActionCompat.f821b, 2);
        remoteActionCompat.f822c = aVar.l(remoteActionCompat.f822c, 3);
        remoteActionCompat.f823d = (PendingIntent) aVar.r(remoteActionCompat.f823d, 4);
        remoteActionCompat.e = aVar.h(remoteActionCompat.e, 5);
        remoteActionCompat.f = aVar.h(remoteActionCompat.f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, androidx.versionedparcelable.a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f820a, 1);
        aVar.D(remoteActionCompat.f821b, 2);
        aVar.D(remoteActionCompat.f822c, 3);
        aVar.H(remoteActionCompat.f823d, 4);
        aVar.z(remoteActionCompat.e, 5);
        aVar.z(remoteActionCompat.f, 6);
    }
}
