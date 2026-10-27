package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import defpackage.kl3;
import defpackage.ml3;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(kl3 kl3Var) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        ml3 ml3VarN = remoteActionCompat.a;
        if (kl3Var.h(1)) {
            ml3VarN = kl3Var.n();
        }
        remoteActionCompat.a = (IconCompat) ml3VarN;
        CharSequence charSequenceG = remoteActionCompat.b;
        if (kl3Var.h(2)) {
            charSequenceG = kl3Var.g();
        }
        remoteActionCompat.b = charSequenceG;
        CharSequence charSequenceG2 = remoteActionCompat.c;
        if (kl3Var.h(3)) {
            charSequenceG2 = kl3Var.g();
        }
        remoteActionCompat.c = charSequenceG2;
        remoteActionCompat.d = (PendingIntent) kl3Var.l(remoteActionCompat.d, 4);
        boolean zE = remoteActionCompat.e;
        if (kl3Var.h(5)) {
            zE = kl3Var.e();
        }
        remoteActionCompat.e = zE;
        boolean zE2 = remoteActionCompat.f;
        if (kl3Var.h(6)) {
            zE2 = kl3Var.e();
        }
        remoteActionCompat.f = zE2;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, kl3 kl3Var) {
        kl3Var.getClass();
        IconCompat iconCompat = remoteActionCompat.a;
        kl3Var.o(1);
        kl3Var.w(iconCompat);
        CharSequence charSequence = remoteActionCompat.b;
        kl3Var.o(2);
        kl3Var.r(charSequence);
        CharSequence charSequence2 = remoteActionCompat.c;
        kl3Var.o(3);
        kl3Var.r(charSequence2);
        PendingIntent pendingIntent = remoteActionCompat.d;
        kl3Var.o(4);
        kl3Var.u(pendingIntent);
        boolean z = remoteActionCompat.e;
        kl3Var.o(5);
        kl3Var.p(z);
        boolean z2 = remoteActionCompat.f;
        kl3Var.o(6);
        kl3Var.p(z2);
    }
}
