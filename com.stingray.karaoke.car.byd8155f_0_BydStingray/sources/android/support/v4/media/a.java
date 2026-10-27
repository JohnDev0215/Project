package android.support.v4.media;

import A7.m;
import P8.n;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import androidx.versionedparcelable.ParcelImpl;
import com.google.android.material.datepicker.d;
import com.google.android.material.datepicker.g;
import e2.C1203k;
import e2.C1204l;
import e2.K;
import e2.Z;
import g.BinderC1272c;
import g.C1270a;
import g.C1273d;
import g.InterfaceC1271b;
import i2.e;
import i2.f;
import java.util.Calendar;
import k.C1581a;
import k.C1588h;
import l3.C1740E;
import l3.C1741F;
import l3.C1753i;
import l3.M;
import l3.N;
import l3.O;
import l3.V;
import l3.W;
import l3.X;
import l3.Y;
import n3.C1946k;
import s3.C2400t;
import s3.a0;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Parcelable.Creator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f11248a;

    public /* synthetic */ a(int i7) {
        this.f11248a = i7;
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        Bundle bundle;
        InterfaceC1271b interfaceC1271b = null;
        switch (this.f11248a) {
            case 0:
                Object objCreateFromParcel = MediaDescription.CREATOR.createFromParcel(parcel);
                if (objCreateFromParcel == null) {
                    return null;
                }
                MediaDescription mediaDescription = (MediaDescription) objCreateFromParcel;
                String strG = b.g(mediaDescription);
                CharSequence charSequenceI = b.i(mediaDescription);
                CharSequence charSequenceH = b.h(mediaDescription);
                CharSequence charSequenceC = b.c(mediaDescription);
                Bitmap bitmapE = b.e(mediaDescription);
                Uri uriF = b.f(mediaDescription);
                Bundle bundleD = b.d(mediaDescription);
                if (bundleD != null) {
                    bundleD = n.W(bundleD);
                }
                Uri uri = bundleD != null ? (Uri) bundleD.getParcelable("android.support.v4.media.description.MEDIA_URI") : null;
                if (uri == null) {
                    bundle = bundleD;
                } else if (bundleD.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") && bundleD.size() == 2) {
                    bundle = null;
                } else {
                    bundleD.remove("android.support.v4.media.description.MEDIA_URI");
                    bundleD.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
                    bundle = bundleD;
                }
                MediaDescriptionCompat mediaDescriptionCompat = new MediaDescriptionCompat(strG, charSequenceI, charSequenceH, charSequenceC, bitmapE, uriF, bundle, uri != null ? uri : c.a(mediaDescription));
                mediaDescriptionCompat.f11246t = mediaDescription;
                return mediaDescriptionCompat;
            case 1:
                return new MediaMetadataCompat(parcel);
            case 2:
                return new RatingCompat(parcel.readFloat(), parcel.readInt());
            case 3:
                int i7 = parcel.readInt();
                int i10 = parcel.readInt();
                Calendar calendarB = g.b(null);
                calendarB.set(1, i7);
                calendarB.set(2, i10);
                return new d(calendarB);
            case 4:
                return new C1204l(parcel);
            case 5:
                return new C1203k(parcel);
            case 6:
                return new K(parcel);
            case 7:
                return new Z(parcel);
            case 8:
                C1273d c1273d = new C1273d();
                IBinder strongBinder = parcel.readStrongBinder();
                int i11 = BinderC1272c.d;
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface(InterfaceC1271b.f14658b);
                    if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC1271b)) {
                        C1270a c1270a = new C1270a();
                        c1270a.c = strongBinder;
                        interfaceC1271b = c1270a;
                    } else {
                        interfaceC1271b = (InterfaceC1271b) iInterfaceQueryLocalInterface;
                    }
                }
                c1273d.l = interfaceC1271b;
                return c1273d;
            case 9:
                return new i2.a(parcel);
            case 10:
                return new e(parcel);
            case 11:
                return new f(parcel);
            case 12:
                m.f(parcel, "parcel");
                return new C1581a(parcel.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
            case 13:
                m.f(parcel, "inParcel");
                Parcelable parcelable = parcel.readParcelable(IntentSender.class.getClassLoader());
                m.c(parcelable);
                return new C1588h((IntentSender) parcelable, (Intent) parcel.readParcelable(Intent.class.getClassLoader()), parcel.readInt(), parcel.readInt());
            case 14:
                return new C1753i(parcel);
            case 15:
                C1740E c1740eA = C1740E.a(MediaDescription.CREATOR.createFromParcel(parcel));
                c1740eA.getClass();
                return c1740eA;
            case 16:
                return new C1741F(parcel);
            case 17:
                return new M(parcel);
            case 18:
                N n10 = new N();
                n10.l = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
                return n10;
            case 19:
                Parcelable parcelable2 = parcel.readParcelable(null);
                parcelable2.getClass();
                return new O(parcelable2, null);
            case 20:
                V v10 = new V();
                v10.l = parcel.readInt();
                v10.f18547n = parcel.readInt();
                v10.o = parcel.readInt();
                v10.f18548p = parcel.readInt();
                v10.f18546m = parcel.readInt();
                return v10;
            case 21:
                return new X(parcel);
            case 22:
                return new W(parcel);
            case 23:
                return new Y(parcel.readFloat(), parcel.readInt());
            case 24:
                m.f(parcel, "inParcel");
                return new C1946k(parcel);
            case 25:
                C2400t c2400t = new C2400t();
                c2400t.l = parcel.readInt();
                c2400t.f22349m = parcel.readInt();
                c2400t.f22350n = parcel.readInt() == 1;
                return c2400t;
            case 26:
                s3.Z z6 = new s3.Z();
                z6.l = parcel.readInt();
                z6.f22242m = parcel.readInt();
                z6.o = parcel.readInt() == 1;
                int i12 = parcel.readInt();
                if (i12 > 0) {
                    int[] iArr = new int[i12];
                    z6.f22243n = iArr;
                    parcel.readIntArray(iArr);
                }
                return z6;
            case 27:
                a0 a0Var = new a0();
                a0Var.l = parcel.readInt();
                a0Var.f22246m = parcel.readInt();
                int i13 = parcel.readInt();
                a0Var.f22247n = i13;
                if (i13 > 0) {
                    int[] iArr2 = new int[i13];
                    a0Var.o = iArr2;
                    parcel.readIntArray(iArr2);
                }
                int i14 = parcel.readInt();
                a0Var.f22248p = i14;
                if (i14 > 0) {
                    int[] iArr3 = new int[i14];
                    a0Var.f22249q = iArr3;
                    parcel.readIntArray(iArr3);
                }
                a0Var.f22251s = parcel.readInt() == 1;
                a0Var.f22252t = parcel.readInt() == 1;
                a0Var.f22253u = parcel.readInt() == 1;
                a0Var.f22250r = parcel.readArrayList(s3.Z.class.getClassLoader());
                return a0Var;
            default:
                return new ParcelImpl(parcel);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        switch (this.f11248a) {
            case 0:
                return new MediaDescriptionCompat[i7];
            case 1:
                return new MediaMetadataCompat[i7];
            case 2:
                return new RatingCompat[i7];
            case 3:
                return new d[i7];
            case 4:
                return new C1204l[i7];
            case 5:
                return new C1203k[i7];
            case 6:
                return new K[i7];
            case 7:
                return new Z[i7];
            case 8:
                return new C1273d[i7];
            case 9:
                return new i2.a[i7];
            case 10:
                return new e[i7];
            case 11:
                return new f[i7];
            case 12:
                return new C1581a[i7];
            case 13:
                return new C1588h[i7];
            case 14:
                return new C1753i[i7];
            case 15:
                return new C1740E[i7];
            case 16:
                return new C1741F[i7];
            case 17:
                return new M[i7];
            case 18:
                return new N[i7];
            case 19:
                return new O[i7];
            case 20:
                return new V[i7];
            case 21:
                return new X[i7];
            case 22:
                return new W[i7];
            case 23:
                return new Y[i7];
            case 24:
                return new C1946k[i7];
            case 25:
                return new C2400t[i7];
            case 26:
                return new s3.Z[i7];
            case 27:
                return new a0[i7];
            default:
                return new ParcelImpl[i7];
        }
    }
}
