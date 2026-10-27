package androidx.core.app;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import x3.AbstractC2674b;
import x3.C2675c;
import x3.InterfaceC2676d;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(AbstractC2674b abstractC2674b) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        InterfaceC2676d interfaceC2676dH = remoteActionCompat.f11737a;
        if (abstractC2674b.e(1)) {
            interfaceC2676dH = abstractC2674b.h();
        }
        remoteActionCompat.f11737a = (IconCompat) interfaceC2676dH;
        CharSequence charSequence = remoteActionCompat.f11738b;
        if (abstractC2674b.e(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((C2675c) abstractC2674b).f23772e);
        }
        remoteActionCompat.f11738b = charSequence;
        CharSequence charSequence2 = remoteActionCompat.c;
        if (abstractC2674b.e(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((C2675c) abstractC2674b).f23772e);
        }
        remoteActionCompat.c = charSequence2;
        remoteActionCompat.d = (PendingIntent) abstractC2674b.g(remoteActionCompat.d, 4);
        boolean z6 = remoteActionCompat.f11739e;
        if (abstractC2674b.e(5)) {
            z6 = ((C2675c) abstractC2674b).f23772e.readInt() != 0;
        }
        remoteActionCompat.f11739e = z6;
        boolean z10 = remoteActionCompat.f11740f;
        if (abstractC2674b.e(6)) {
            z10 = ((C2675c) abstractC2674b).f23772e.readInt() != 0;
        }
        remoteActionCompat.f11740f = z10;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, AbstractC2674b abstractC2674b) {
        abstractC2674b.getClass();
        IconCompat iconCompat = remoteActionCompat.f11737a;
        abstractC2674b.i(1);
        abstractC2674b.l(iconCompat);
        CharSequence charSequence = remoteActionCompat.f11738b;
        abstractC2674b.i(2);
        Parcel parcel = ((C2675c) abstractC2674b).f23772e;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.c;
        abstractC2674b.i(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        abstractC2674b.k(remoteActionCompat.d, 4);
        boolean z6 = remoteActionCompat.f11739e;
        abstractC2674b.i(5);
        parcel.writeInt(z6 ? 1 : 0);
        boolean z10 = remoteActionCompat.f11740f;
        abstractC2674b.i(6);
        parcel.writeInt(z10 ? 1 : 0);
    }
}
