package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.a;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f1949a = (IconCompat) aVar.v(remoteActionCompat.f1949a, 1);
        remoteActionCompat.f1950b = aVar.l(remoteActionCompat.f1950b, 2);
        remoteActionCompat.f1951c = aVar.l(remoteActionCompat.f1951c, 3);
        remoteActionCompat.f1952d = (PendingIntent) aVar.r(remoteActionCompat.f1952d, 4);
        remoteActionCompat.f1953e = aVar.h(remoteActionCompat.f1953e, 5);
        remoteActionCompat.f1954f = aVar.h(remoteActionCompat.f1954f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f1949a, 1);
        aVar.D(remoteActionCompat.f1950b, 2);
        aVar.D(remoteActionCompat.f1951c, 3);
        aVar.H(remoteActionCompat.f1952d, 4);
        aVar.z(remoteActionCompat.f1953e, 5);
        aVar.z(remoteActionCompat.f1954f, 6);
    }
}
