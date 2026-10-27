package android.support.v4.media.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface IMediaControllerCallback extends IInterface {

    /* JADX INFO: compiled from: caradapter-bug */
    public static class Default implements IMediaControllerCallback {
        @Override // android.support.v4.media.session.IMediaControllerCallback
        public void I(int i) {
        }

        @Override // android.support.v4.media.session.IMediaControllerCallback
        public void T(boolean z) {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.support.v4.media.session.IMediaControllerCallback
        public void b0(int i) {
        }

        @Override // android.support.v4.media.session.IMediaControllerCallback
        public void c0() {
        }

        @Override // android.support.v4.media.session.IMediaControllerCallback
        public void d(List<MediaSessionCompat.QueueItem> list) {
        }

        @Override // android.support.v4.media.session.IMediaControllerCallback
        public void d0(Bundle bundle) {
        }

        @Override // android.support.v4.media.session.IMediaControllerCallback
        public void e0(PlaybackStateCompat playbackStateCompat) {
        }

        @Override // android.support.v4.media.session.IMediaControllerCallback
        public void f(boolean z) {
        }

        @Override // android.support.v4.media.session.IMediaControllerCallback
        public void f0(String str, Bundle bundle) {
        }

        @Override // android.support.v4.media.session.IMediaControllerCallback
        public void h(CharSequence charSequence) {
        }

        @Override // android.support.v4.media.session.IMediaControllerCallback
        public void l0(ParcelableVolumeInfo parcelableVolumeInfo) {
        }

        @Override // android.support.v4.media.session.IMediaControllerCallback
        public void p() {
        }

        @Override // android.support.v4.media.session.IMediaControllerCallback
        public void t(MediaMetadataCompat mediaMetadataCompat) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class Stub extends Binder implements IMediaControllerCallback {

        /* JADX INFO: compiled from: caradapter-bug */
        private static class a implements IMediaControllerCallback {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public static IMediaControllerCallback f38b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f39a;

            a(IBinder iBinder) {
                this.f39a = iBinder;
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void I(int i) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    parcelObtain.writeInt(i);
                    if (this.f39a.transact(9, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().I(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void T(boolean z) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.f39a.transact(11, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().T(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f39a;
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void b0(int i) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    parcelObtain.writeInt(i);
                    if (this.f39a.transact(12, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().b0(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void c0() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (this.f39a.transact(13, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().c0();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void d(List<MediaSessionCompat.QueueItem> list) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    parcelObtain.writeTypedList(list);
                    if (this.f39a.transact(5, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().d(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void d0(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f39a.transact(7, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().d0(bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void e0(PlaybackStateCompat playbackStateCompat) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (playbackStateCompat != null) {
                        parcelObtain.writeInt(1);
                        playbackStateCompat.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f39a.transact(3, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().e0(playbackStateCompat);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void f(boolean z) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.f39a.transact(10, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().f(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void f0(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f39a.transact(1, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().f0(str, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void h(CharSequence charSequence) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f39a.transact(6, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().h(charSequence);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void l0(ParcelableVolumeInfo parcelableVolumeInfo) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (parcelableVolumeInfo != null) {
                        parcelObtain.writeInt(1);
                        parcelableVolumeInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f39a.transact(8, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().l0(parcelableVolumeInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void p() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (this.f39a.transact(2, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().p();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void t(MediaMetadataCompat mediaMetadataCompat) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (mediaMetadataCompat != null) {
                        parcelObtain.writeInt(1);
                        mediaMetadataCompat.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f39a.transact(4, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().t(mediaMetadataCompat);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
        }

        public static IMediaControllerCallback n0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IMediaControllerCallback)) ? new a(iBinder) : (IMediaControllerCallback) iInterfaceQueryLocalInterface;
        }

        public static IMediaControllerCallback o0() {
            return a.f38b;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("android.support.v4.media.session.IMediaControllerCallback");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    f0(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    p();
                    return true;
                case 3:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    e0(parcel.readInt() != 0 ? PlaybackStateCompat.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    t(parcel.readInt() != 0 ? MediaMetadataCompat.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    d(parcel.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR));
                    return true;
                case 6:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    h(parcel.readInt() != 0 ? (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 7:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    d0(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    l0(parcel.readInt() != 0 ? ParcelableVolumeInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 9:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    I(parcel.readInt());
                    return true;
                case 10:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    f(parcel.readInt() != 0);
                    return true;
                case 11:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    T(parcel.readInt() != 0);
                    return true;
                case 12:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    b0(parcel.readInt());
                    return true;
                case 13:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    c0();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void I(int i);

    void T(boolean z);

    void b0(int i);

    void c0();

    void d(List<MediaSessionCompat.QueueItem> list);

    void d0(Bundle bundle);

    void e0(PlaybackStateCompat playbackStateCompat);

    void f(boolean z);

    void f0(String str, Bundle bundle);

    void h(CharSequence charSequence);

    void l0(ParcelableVolumeInfo parcelableVolumeInfo);

    void p();

    void t(MediaMetadataCompat mediaMetadataCompat);
}
