package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import defpackage.hf;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(hf hfVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.a = (IconCompat) hfVar.v(remoteActionCompat.a, 1);
        remoteActionCompat.b = hfVar.l(remoteActionCompat.b, 2);
        remoteActionCompat.c = hfVar.l(remoteActionCompat.c, 3);
        remoteActionCompat.d = (PendingIntent) hfVar.r(remoteActionCompat.d, 4);
        remoteActionCompat.e = hfVar.h(remoteActionCompat.e, 5);
        remoteActionCompat.f = hfVar.h(remoteActionCompat.f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, hf hfVar) {
        hfVar.x(false, false);
        hfVar.M(remoteActionCompat.a, 1);
        hfVar.D(remoteActionCompat.b, 2);
        hfVar.D(remoteActionCompat.c, 3);
        hfVar.H(remoteActionCompat.d, 4);
        hfVar.z(remoteActionCompat.e, 5);
        hfVar.z(remoteActionCompat.f, 6);
    }
}
