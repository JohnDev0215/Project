package androidx.core.app;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import p.at2;
import p.ys2;
import p.zs2;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(ys2 ys2Var) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        at2 at2VarH = remoteActionCompat.a;
        if (ys2Var.e(1)) {
            at2VarH = ys2Var.h();
        }
        remoteActionCompat.a = (IconCompat) at2VarH;
        CharSequence charSequence = remoteActionCompat.b;
        if (ys2Var.e(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((zs2) ys2Var).e);
        }
        remoteActionCompat.b = charSequence;
        CharSequence charSequence2 = remoteActionCompat.c;
        if (ys2Var.e(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((zs2) ys2Var).e);
        }
        remoteActionCompat.c = charSequence2;
        remoteActionCompat.d = (PendingIntent) ys2Var.g(remoteActionCompat.d, 4);
        boolean z = remoteActionCompat.e;
        if (ys2Var.e(5)) {
            z = ((zs2) ys2Var).e.readInt() != 0;
        }
        remoteActionCompat.e = z;
        boolean z2 = remoteActionCompat.f;
        if (ys2Var.e(6)) {
            z2 = ((zs2) ys2Var).e.readInt() != 0;
        }
        remoteActionCompat.f = z2;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, ys2 ys2Var) {
        ys2Var.getClass();
        IconCompat iconCompat = remoteActionCompat.a;
        ys2Var.i(1);
        ys2Var.l(iconCompat);
        CharSequence charSequence = remoteActionCompat.b;
        ys2Var.i(2);
        Parcel parcel = ((zs2) ys2Var).e;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.c;
        ys2Var.i(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        ys2Var.k(remoteActionCompat.d, 4);
        boolean z = remoteActionCompat.e;
        ys2Var.i(5);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = remoteActionCompat.f;
        ys2Var.i(6);
        parcel.writeInt(z2 ? 1 : 0);
    }
}
