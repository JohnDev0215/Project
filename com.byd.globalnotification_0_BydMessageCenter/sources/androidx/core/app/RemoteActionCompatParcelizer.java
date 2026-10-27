package androidx.core.app;

import a.t.a;
import a.t.b;
import a.t.c;
import android.app.PendingIntent;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        c cVarK = remoteActionCompat.f1522a;
        if (aVar.h(1)) {
            cVarK = aVar.k();
        }
        remoteActionCompat.f1522a = (IconCompat) cVarK;
        remoteActionCompat.f1523b = aVar.g(remoteActionCompat.f1523b, 2);
        remoteActionCompat.f1524c = aVar.g(remoteActionCompat.f1524c, 3);
        remoteActionCompat.f1525d = (PendingIntent) aVar.j(remoteActionCompat.f1525d, 4);
        remoteActionCompat.f1526e = aVar.f(remoteActionCompat.f1526e, 5);
        remoteActionCompat.f1527f = aVar.f(remoteActionCompat.f1527f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, a aVar) {
        if (aVar == null) {
            throw null;
        }
        IconCompat iconCompat = remoteActionCompat.f1522a;
        aVar.l(1);
        aVar.o(iconCompat);
        CharSequence charSequence = remoteActionCompat.f1523b;
        aVar.l(2);
        b bVar = (b) aVar;
        TextUtils.writeToParcel(charSequence, bVar.f1362e, 0);
        CharSequence charSequence2 = remoteActionCompat.f1524c;
        aVar.l(3);
        TextUtils.writeToParcel(charSequence2, bVar.f1362e, 0);
        aVar.n(remoteActionCompat.f1525d, 4);
        boolean z = remoteActionCompat.f1526e;
        aVar.l(5);
        bVar.f1362e.writeInt(z ? 1 : 0);
        boolean z2 = remoteActionCompat.f1527f;
        aVar.l(6);
        bVar.f1362e.writeInt(z2 ? 1 : 0);
    }
}
