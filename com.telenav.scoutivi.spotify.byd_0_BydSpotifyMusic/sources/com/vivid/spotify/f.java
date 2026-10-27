package com.vivid.spotify;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.vivid.spotify.d;
import com.vivid.spotify.e;

/* JADX INFO: compiled from: ISpotifyMedia.java */
/* JADX INFO: loaded from: classes.dex */
public interface f extends IInterface {

    /* JADX INFO: compiled from: ISpotifyMedia.java */
    public static abstract class a extends Binder implements f {
        public a() {
            attachInterface(this, "com.vivid.spotify.ISpotifyMedia");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i8) {
            if (i7 == 1598968902) {
                parcel2.writeString("com.vivid.spotify.ISpotifyMedia");
                return true;
            }
            switch (i7) {
                case 1:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    String strJ = j();
                    parcel2.writeNoException();
                    parcel2.writeString(strJ);
                    return true;
                case 2:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    x(e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    s(e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    i(e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    E(e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    R(e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    h(e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    M(e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    G(e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    O(e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    l(parcel.readInt(), e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    z(parcel.readInt(), e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    int iN = n();
                    parcel2.writeNoException();
                    parcel2.writeInt(iN);
                    return true;
                case 14:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    w(d.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    C(d.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    B(e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    t(e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    F(e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    y(e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface("com.vivid.spotify.ISpotifyMedia");
                    r(parcel.readString(), parcel.readInt() != 0 ? SearchParam.CREATOR.createFromParcel(parcel) : null, e.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i7, parcel, parcel2, i8);
            }
        }
    }

    void B(e eVar);

    void C(d dVar);

    void E(e eVar);

    void F(e eVar);

    void G(e eVar);

    void M(e eVar);

    void O(e eVar);

    void R(e eVar);

    void h(e eVar);

    void i(e eVar);

    String j();

    void l(int i7, e eVar);

    int n();

    void r(String str, SearchParam searchParam, e eVar);

    void s(e eVar);

    void t(e eVar);

    void w(d dVar);

    void x(e eVar);

    void y(e eVar);

    void z(int i7, e eVar);
}
