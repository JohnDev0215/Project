package l3;

import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.view.KeyEvent;
import java.util.List;

/* JADX INFO: renamed from: l3.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1749e implements InterfaceC1750f {
    public IBinder c;

    @Override // l3.InterfaceC1750f
    public final void D0(long j3) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void E0(String str, Bundle bundle) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void G(Uri uri, Bundle bundle) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void G0(int i7, int i10) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final V I0() {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void J0() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.c.transact(22, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final void M0(Uri uri, Bundle bundle) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final int N() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.c.transact(47, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
            return parcelObtain2.readInt();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final int Q() {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void Q0(int i7) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void R(String str, Bundle bundle) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final boolean T() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.c.transact(45, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
            return parcelObtain2.readInt() != 0;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final void W(C1740E c1740e, int i7) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void Y0(String str, Bundle bundle, N n10) {
        throw null;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.c;
    }

    @Override // l3.InterfaceC1750f
    public final void b() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.c.transact(18, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final void c() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.c.transact(33, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final void c0(InterfaceC1748d interfaceC1748d) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcelObtain.writeStrongBinder((BinderC1769z) interfaceC1748d);
            if (!this.c.transact(4, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final X e() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.c.transact(28, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
            return parcelObtain2.readInt() != 0 ? X.CREATOR.createFromParcel(parcelObtain2) : null;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final void g() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.c.transact(13, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final String getPackageName() {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void h(int i7) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final List h0() {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void h1(InterfaceC1748d interfaceC1748d) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcelObtain.writeStrongBinder((BinderC1769z) interfaceC1748d);
            if (!this.c.transact(3, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final int i() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.c.transact(37, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
            return parcelObtain2.readInt();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final long j() {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void j0(int i7, int i10) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void k(long j3) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void l(float f10) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final Bundle m() {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final boolean m1(KeyEvent keyEvent) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void n0(C1740E c1740e) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void next() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.c.transact(20, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final void o0() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.c.transact(23, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final void previous() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.c.transact(21, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final CharSequence q0() {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void r(String str, Bundle bundle) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void stop() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.c.transact(19, parcelObtain, parcelObtain2, 0)) {
                int i7 = J.d;
            }
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1750f
    public final C1741F v0() {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void w0(String str, Bundle bundle) {
        throw null;
    }

    @Override // l3.InterfaceC1750f
    public final void z0(String str, Bundle bundle) {
        throw null;
    }
}
