package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import java.util.List;
import p.he1;
import p.r21;
import p.s21;
import p.t21;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends Binder implements b {
    public a() {
        attachInterface(this, "android.support.v4.media.session.IMediaSession");
    }

    public static b g0(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
        if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof b)) {
            return (b) iInterfaceQueryLocalInterface;
        }
        t21 t21Var = new t21();
        t21Var.b = iBinder;
        return t21Var;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
        }
        if (i == 1598968902) {
            parcel2.writeString("android.support.v4.media.session.IMediaSession");
            return true;
        }
        s21 s21Var = null;
        s21 s21Var2 = null;
        switch (i) {
            case 1:
                D(parcel.readString(), (Bundle) he1.a(parcel, Bundle.CREATOR), (MediaSessionCompat$ResultReceiverWrapper) he1.a(parcel, MediaSessionCompat$ResultReceiverWrapper.CREATOR));
                parcel2.writeNoException();
                return true;
            case 2:
                boolean zF0 = f0((KeyEvent) he1.a(parcel, KeyEvent.CREATOR));
                parcel2.writeNoException();
                parcel2.writeInt(zF0 ? 1 : 0);
                return true;
            case 3:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof s21)) {
                        r21 r21Var = new r21();
                        r21Var.b = strongBinder;
                        s21Var = r21Var;
                    } else {
                        s21Var = (s21) iInterfaceQueryLocalInterface;
                    }
                }
                Z(s21Var);
                parcel2.writeNoException();
                return true;
            case 4:
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (iInterfaceQueryLocalInterface2 == null || !(iInterfaceQueryLocalInterface2 instanceof s21)) {
                        r21 r21Var2 = new r21();
                        r21Var2.b = strongBinder2;
                        s21Var2 = r21Var2;
                    } else {
                        s21Var2 = (s21) iInterfaceQueryLocalInterface2;
                    }
                }
                w(s21Var2);
                parcel2.writeNoException();
                return true;
            case 5:
                boolean zR = r();
                parcel2.writeNoException();
                parcel2.writeInt(zR ? 1 : 0);
                return true;
            case 6:
                String strA0 = a0();
                parcel2.writeNoException();
                parcel2.writeString(strA0);
                return true;
            case 7:
                String strB = b();
                parcel2.writeNoException();
                parcel2.writeString(strB);
                return true;
            case 8:
                PendingIntent pendingIntentU = u();
                parcel2.writeNoException();
                he1.Z(1, parcel2, pendingIntentU);
                return true;
            case 9:
                long flags = getFlags();
                parcel2.writeNoException();
                parcel2.writeLong(flags);
                return true;
            case 10:
                ParcelableVolumeInfo parcelableVolumeInfoR = R();
                parcel2.writeNoException();
                he1.Z(1, parcel2, parcelableVolumeInfoR);
                return true;
            case 11:
                int i3 = parcel.readInt();
                int i4 = parcel.readInt();
                parcel.readString();
                F(i3, i4);
                parcel2.writeNoException();
                return true;
            case 12:
                int i5 = parcel.readInt();
                int i6 = parcel.readInt();
                parcel.readString();
                P(i5, i6);
                parcel2.writeNoException();
                return true;
            case 13:
                B();
                parcel2.writeNoException();
                return true;
            case 14:
                J((Bundle) he1.a(parcel, Bundle.CREATOR), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 15:
                L((Bundle) he1.a(parcel, Bundle.CREATOR), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 16:
                U((Uri) he1.a(parcel, Uri.CREATOR), (Bundle) he1.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 17:
                O(parcel.readLong());
                parcel2.writeNoException();
                return true;
            case 18:
                h();
                parcel2.writeNoException();
                return true;
            case 19:
                j();
                parcel2.writeNoException();
                return true;
            case 20:
                next();
                parcel2.writeNoException();
                return true;
            case 21:
                previous();
                parcel2.writeNoException();
                return true;
            case 22:
                S();
                parcel2.writeNoException();
                return true;
            case 23:
                g();
                parcel2.writeNoException();
                return true;
            case 24:
                X(parcel.readLong());
                parcel2.writeNoException();
                return true;
            case 25:
                n((RatingCompat) he1.a(parcel, RatingCompat.CREATOR));
                parcel2.writeNoException();
                return true;
            case 26:
                o((Bundle) he1.a(parcel, Bundle.CREATOR), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 27:
                MediaMetadataCompat metadata = getMetadata();
                parcel2.writeNoException();
                he1.Z(1, parcel2, metadata);
                return true;
            case 28:
                PlaybackStateCompat playbackStateCompatA = a();
                parcel2.writeNoException();
                he1.Z(1, parcel2, playbackStateCompatA);
                return true;
            case 29:
                List listE = E();
                parcel2.writeNoException();
                if (listE == null) {
                    parcel2.writeInt(-1);
                } else {
                    int size = listE.size();
                    parcel2.writeInt(size);
                    for (int i7 = 0; i7 < size; i7++) {
                        he1.Z(1, parcel2, (Parcelable) listE.get(i7));
                    }
                }
                return true;
            case 30:
                CharSequence charSequenceI = I();
                parcel2.writeNoException();
                if (charSequenceI != null) {
                    parcel2.writeInt(1);
                    TextUtils.writeToParcel(charSequenceI, parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 31:
                Bundle bundleT = T();
                parcel2.writeNoException();
                he1.Z(1, parcel2, bundleT);
                return true;
            case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                y();
                parcel2.writeNoException();
                parcel2.writeInt(0);
                return true;
            case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                A();
                parcel2.writeNoException();
                return true;
            case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                C((Bundle) he1.a(parcel, Bundle.CREATOR), parcel.readString());
                parcel2.writeNoException();
                return true;
            case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                b0((Bundle) he1.a(parcel, Bundle.CREATOR), parcel.readString());
                parcel2.writeNoException();
                return true;
            case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                p((Uri) he1.a(parcel, Uri.CREATOR), (Bundle) he1.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                int iN = N();
                parcel2.writeNoException();
                parcel2.writeInt(iN);
                return true;
            case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                parcel2.writeNoException();
                parcel2.writeInt(0);
                return true;
            case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
                H(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 40:
                parcel.readInt();
                parcel2.writeNoException();
                return true;
            case 41:
                t((MediaDescriptionCompat) he1.a(parcel, MediaDescriptionCompat.CREATOR));
                parcel2.writeNoException();
                return true;
            case 42:
                k((MediaDescriptionCompat) he1.a(parcel, MediaDescriptionCompat.CREATOR), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 43:
                q((MediaDescriptionCompat) he1.a(parcel, MediaDescriptionCompat.CREATOR));
                parcel2.writeNoException();
                return true;
            case 44:
                x(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 45:
                z();
                parcel2.writeNoException();
                parcel2.writeInt(0);
                return true;
            case 46:
                m(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 47:
                int iV = v();
                parcel2.writeNoException();
                parcel2.writeInt(iV);
                return true;
            case 48:
                Y(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 49:
                d0(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 50:
                Bundle bundleK = K();
                parcel2.writeNoException();
                he1.Z(1, parcel2, bundleK);
                return true;
            case 51:
                i((RatingCompat) he1.a(parcel, RatingCompat.CREATOR), (Bundle) he1.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
