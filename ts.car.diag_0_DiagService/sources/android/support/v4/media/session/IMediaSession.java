package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface IMediaSession extends IInterface {

    /* JADX INFO: compiled from: caradapter-bug */
    public static class Default implements IMediaSession {
        @Override // android.support.v4.media.session.IMediaSession
        public long A() {
            return 0L;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void B(int i, int i2, String str) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void C(Uri uri, Bundle bundle) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public int D() {
            return 0;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void E(long j) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void F(boolean z) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void G(String str, Bundle bundle) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public ParcelableVolumeInfo H() {
            return null;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void J() {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public Bundle K() {
            return null;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void L(Uri uri, Bundle bundle) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void M(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public boolean N() {
            return false;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void O(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public PendingIntent P() {
            return null;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public int Q() {
            return 0;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void R(long j) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void S(int i) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void U(int i) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public int V() {
            return 0;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void W(String str, Bundle bundle) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public boolean Y() {
            return false;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public String Z() {
            return null;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public MediaMetadataCompat a() {
            return null;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void a0() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public PlaybackStateCompat b() {
            return null;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void e(int i) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void g() {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void g0() {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void h0(float f) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public CharSequence i() {
            return null;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void i0(String str, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void j(String str, Bundle bundle) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public List<MediaSessionCompat.QueueItem> j0() {
            return null;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void k(int i, int i2, String str) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void l() {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void m(IMediaControllerCallback iMediaControllerCallback) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public boolean m0(KeyEvent keyEvent) {
            return false;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void n(RatingCompat ratingCompat, Bundle bundle) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void next() {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void o(String str, Bundle bundle) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void previous() {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public Bundle q() {
            return null;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void r(IMediaControllerCallback iMediaControllerCallback) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void s(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void stop() {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public String v() {
            return null;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public boolean w() {
            return false;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void x(boolean z) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void y(RatingCompat ratingCompat) {
        }

        @Override // android.support.v4.media.session.IMediaSession
        public void z(String str, Bundle bundle) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class Stub extends Binder implements IMediaSession {

        /* JADX INFO: compiled from: caradapter-bug */
        private static class a implements IMediaSession {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public static IMediaSession f40b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f41a;

            a(IBinder iBinder) {
                this.f41a = iBinder;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public long A() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(9, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().A();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void B(int i, int i2, String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (this.f41a.transact(12, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().B(i, i2, str);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void C(Uri uri, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f41a.transact(36, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().C(uri, bundle);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int D() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(37, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().D();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void E(long j) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeLong(j);
                    if (this.f41a.transact(17, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().E(j);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void F(boolean z) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.f41a.transact(40, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().F(z);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void G(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f41a.transact(15, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().G(str, bundle);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public ParcelableVolumeInfo H() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(10, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().H();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelableVolumeInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void J() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.f41a.transact(22, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().J();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public Bundle K() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(31, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().K();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void L(Uri uri, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f41a.transact(16, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().L(uri, bundle);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void M(MediaDescriptionCompat mediaDescriptionCompat) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (mediaDescriptionCompat != null) {
                        parcelObtain.writeInt(1);
                        mediaDescriptionCompat.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f41a.transact(43, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().M(mediaDescriptionCompat);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean N() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(5, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().N();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void O(MediaDescriptionCompat mediaDescriptionCompat) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (mediaDescriptionCompat != null) {
                        parcelObtain.writeInt(1);
                        mediaDescriptionCompat.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f41a.transact(41, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().O(mediaDescriptionCompat);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PendingIntent P() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(8, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().P();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int Q() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(47, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().Q();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void R(long j) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeLong(j);
                    if (this.f41a.transact(24, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().R(j);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void S(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeInt(i);
                    if (this.f41a.transact(48, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().S(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void U(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeInt(i);
                    if (this.f41a.transact(44, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().U(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int V() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(32, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().V();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void W(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f41a.transact(35, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().W(str, bundle);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean Y() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(45, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().Y();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public String Z() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(6, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().Z();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public MediaMetadataCompat a() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(27, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().a();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? MediaMetadataCompat.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void a0() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.f41a.transact(33, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().a0();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f41a;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PlaybackStateCompat b() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(28, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().b();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PlaybackStateCompat.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void e(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeInt(i);
                    if (this.f41a.transact(39, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().e(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void g() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.f41a.transact(23, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().g();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void g0() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.f41a.transact(13, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().g0();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void h0(float f) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeFloat(f);
                    if (this.f41a.transact(49, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().h0(f);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public CharSequence i() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(30, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().i();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void i0(String str, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (resultReceiverWrapper != null) {
                        parcelObtain.writeInt(1);
                        resultReceiverWrapper.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f41a.transact(1, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().i0(str, bundle, resultReceiverWrapper);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void j(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f41a.transact(26, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().j(str, bundle);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public List<MediaSessionCompat.QueueItem> j0() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(29, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().j0();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void k(int i, int i2, String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (this.f41a.transact(11, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().k(i, i2, str);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void l() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.f41a.transact(18, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().l();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void m(IMediaControllerCallback iMediaControllerCallback) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                    if (this.f41a.transact(3, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().m(iMediaControllerCallback);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean m0(KeyEvent keyEvent) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (keyEvent != null) {
                        parcelObtain.writeInt(1);
                        keyEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f41a.transact(2, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().m0(keyEvent);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void n(RatingCompat ratingCompat, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (ratingCompat != null) {
                        parcelObtain.writeInt(1);
                        ratingCompat.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f41a.transact(51, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().n(ratingCompat, bundle);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void next() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.f41a.transact(20, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().next();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void o(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f41a.transact(34, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().o(str, bundle);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void previous() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.f41a.transact(21, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().previous();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public Bundle q() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(50, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().q();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void r(IMediaControllerCallback iMediaControllerCallback) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                    if (this.f41a.transact(4, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().r(iMediaControllerCallback);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void s(MediaDescriptionCompat mediaDescriptionCompat, int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (mediaDescriptionCompat != null) {
                        parcelObtain.writeInt(1);
                        mediaDescriptionCompat.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.f41a.transact(42, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().s(mediaDescriptionCompat, i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void stop() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.f41a.transact(19, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().stop();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public String v() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(7, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().v();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean w() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.f41a.transact(38, parcelObtain, parcelObtain2, 0) && Stub.o0() != null) {
                        return Stub.o0().w();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void x(boolean z) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.f41a.transact(46, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().x(z);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void y(RatingCompat ratingCompat) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (ratingCompat != null) {
                        parcelObtain.writeInt(1);
                        ratingCompat.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f41a.transact(25, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().y(ratingCompat);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void z(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f41a.transact(14, parcelObtain, parcelObtain2, 0) || Stub.o0() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.o0().z(str, bundle);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "android.support.v4.media.session.IMediaSession");
        }

        public static IMediaSession n0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IMediaSession)) ? new a(iBinder) : (IMediaSession) iInterfaceQueryLocalInterface;
        }

        public static IMediaSession o0() {
            return a.f40b;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("android.support.v4.media.session.IMediaSession");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    i0(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? MediaSessionCompat.ResultReceiverWrapper.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean zM0 = m0(parcel.readInt() != 0 ? (KeyEvent) KeyEvent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zM0 ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    m(IMediaControllerCallback.Stub.n0(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    r(IMediaControllerCallback.Stub.n0(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean zN = N();
                    parcel2.writeNoException();
                    parcel2.writeInt(zN ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String strZ = Z();
                    parcel2.writeNoException();
                    parcel2.writeString(strZ);
                    return true;
                case 7:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String strV = v();
                    parcel2.writeNoException();
                    parcel2.writeString(strV);
                    return true;
                case 8:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    PendingIntent pendingIntentP = P();
                    parcel2.writeNoException();
                    if (pendingIntentP != null) {
                        parcel2.writeInt(1);
                        pendingIntentP.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    long jA = A();
                    parcel2.writeNoException();
                    parcel2.writeLong(jA);
                    return true;
                case 10:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    ParcelableVolumeInfo parcelableVolumeInfoH = H();
                    parcel2.writeNoException();
                    if (parcelableVolumeInfoH != null) {
                        parcel2.writeInt(1);
                        parcelableVolumeInfoH.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    k(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    B(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    g0();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    z(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    G(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    L(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    E(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    l();
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    stop();
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    next();
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    previous();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    J();
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    g();
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    R(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    y(parcel.readInt() != 0 ? RatingCompat.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    j(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    MediaMetadataCompat mediaMetadataCompatA = a();
                    parcel2.writeNoException();
                    if (mediaMetadataCompatA != null) {
                        parcel2.writeInt(1);
                        mediaMetadataCompatA.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 28:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    PlaybackStateCompat playbackStateCompatB = b();
                    parcel2.writeNoException();
                    if (playbackStateCompatB != null) {
                        parcel2.writeInt(1);
                        playbackStateCompatB.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 29:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    List<MediaSessionCompat.QueueItem> listJ0 = j0();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(listJ0);
                    return true;
                case 30:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    CharSequence charSequenceI = i();
                    parcel2.writeNoException();
                    if (charSequenceI != null) {
                        parcel2.writeInt(1);
                        TextUtils.writeToParcel(charSequenceI, parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 31:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Bundle bundleK = K();
                    parcel2.writeNoException();
                    if (bundleK != null) {
                        parcel2.writeInt(1);
                        bundleK.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 32:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int iV = V();
                    parcel2.writeNoException();
                    parcel2.writeInt(iV);
                    return true;
                case 33:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    a0();
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    o(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    W(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    C(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int iD = D();
                    parcel2.writeNoException();
                    parcel2.writeInt(iD);
                    return true;
                case 38:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean zW = w();
                    parcel2.writeNoException();
                    parcel2.writeInt(zW ? 1 : 0);
                    return true;
                case 39:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    e(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    F(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    O(parcel.readInt() != 0 ? MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    s(parcel.readInt() != 0 ? MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    M(parcel.readInt() != 0 ? MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    U(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean zY = Y();
                    parcel2.writeNoException();
                    parcel2.writeInt(zY ? 1 : 0);
                    return true;
                case 46:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    x(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int iQ = Q();
                    parcel2.writeNoException();
                    parcel2.writeInt(iQ);
                    return true;
                case 48:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    S(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    h0(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Bundle bundleQ = q();
                    parcel2.writeNoException();
                    if (bundleQ != null) {
                        parcel2.writeInt(1);
                        bundleQ.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 51:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    n(parcel.readInt() != 0 ? RatingCompat.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    long A();

    void B(int i, int i2, String str);

    void C(Uri uri, Bundle bundle);

    int D();

    void E(long j);

    void F(boolean z);

    void G(String str, Bundle bundle);

    ParcelableVolumeInfo H();

    void J();

    Bundle K();

    void L(Uri uri, Bundle bundle);

    void M(MediaDescriptionCompat mediaDescriptionCompat);

    boolean N();

    void O(MediaDescriptionCompat mediaDescriptionCompat);

    PendingIntent P();

    int Q();

    void R(long j);

    void S(int i);

    void U(int i);

    int V();

    void W(String str, Bundle bundle);

    boolean Y();

    String Z();

    MediaMetadataCompat a();

    void a0();

    PlaybackStateCompat b();

    void e(int i);

    void g();

    void g0();

    void h0(float f);

    CharSequence i();

    void i0(String str, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper);

    void j(String str, Bundle bundle);

    List<MediaSessionCompat.QueueItem> j0();

    void k(int i, int i2, String str);

    void l();

    void m(IMediaControllerCallback iMediaControllerCallback);

    boolean m0(KeyEvent keyEvent);

    void n(RatingCompat ratingCompat, Bundle bundle);

    void next();

    void o(String str, Bundle bundle);

    void previous();

    Bundle q();

    void r(IMediaControllerCallback iMediaControllerCallback);

    void s(MediaDescriptionCompat mediaDescriptionCompat, int i);

    void stop();

    String v();

    boolean w();

    void x(boolean z);

    void y(RatingCompat ratingCompat);

    void z(String str, Bundle bundle);
}
