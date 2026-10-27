package androidx.core.app;

import android.app.PendingIntent;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcel;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(VersionedParcel versionedParcel) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f424a = (IconCompat) versionedParcel.v(remoteActionCompat.f424a, 1);
        remoteActionCompat.f425b = versionedParcel.l(remoteActionCompat.f425b, 2);
        remoteActionCompat.f426c = versionedParcel.l(remoteActionCompat.f426c, 3);
        remoteActionCompat.d = (PendingIntent) versionedParcel.r(remoteActionCompat.d, 4);
        remoteActionCompat.e = versionedParcel.h(remoteActionCompat.e, 5);
        remoteActionCompat.f = versionedParcel.h(remoteActionCompat.f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, VersionedParcel versionedParcel) {
        versionedParcel.x(false, false);
        versionedParcel.M(remoteActionCompat.f424a, 1);
        versionedParcel.D(remoteActionCompat.f425b, 2);
        versionedParcel.D(remoteActionCompat.f426c, 3);
        versionedParcel.H(remoteActionCompat.d, 4);
        versionedParcel.z(remoteActionCompat.e, 5);
        versionedParcel.z(remoteActionCompat.f, 6);
    }
}
