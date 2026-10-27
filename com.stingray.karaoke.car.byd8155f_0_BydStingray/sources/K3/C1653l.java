package k3;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.view.Surface;

/* JADX INFO: renamed from: k3.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1653l implements InterfaceC1655m {
    public IBinder c;

    @Override // k3.InterfaceC1655m
    public final void A0(InterfaceC1651k interfaceC1651k, int i7, int i10, IBinder iBinder) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            parcelObtain.writeStrongBinder(iBinder);
            this.c.transact(3032, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void B(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3035, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void B0(InterfaceC1651k interfaceC1651k, int i7, int i10, long j3) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            parcelObtain.writeLong(j3);
            this.c.transact(3039, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void C(InterfaceC1651k interfaceC1651k, int i7, int i10) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            this.c.transact(3003, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void C0(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            n1.c(parcelObtain, bundle);
            this.c.transact(3014, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void D(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            n1.c(parcelObtain, bundle);
            this.c.transact(3015, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void F(InterfaceC1651k interfaceC1651k, int i7, int i10, Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            n1.c(parcelObtain, bundle);
            this.c.transact(3055, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void F0(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle, Bundle bundle2) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            n1.c(parcelObtain, bundle);
            n1.c(parcelObtain, bundle2);
            this.c.transact(3016, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void H(InterfaceC1651k interfaceC1651k, int i7, int i10, int i11, IBinder iBinder) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            parcelObtain.writeInt(i11);
            parcelObtain.writeStrongBinder(iBinder);
            this.c.transact(3056, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void H0(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3047, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void I(InterfaceC1651k interfaceC1651k, int i7, float f10) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeFloat(f10);
            this.c.transact(3002, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void J(InterfaceC1651k interfaceC1651k, int i7, float f10) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeFloat(f10);
            this.c.transact(3028, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void K(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            n1.c(parcelObtain, bundle);
            this.c.transact(3027, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void K0(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3025, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void L0(InterfaceC1651k interfaceC1651k, int i7, IBinder iBinder) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeStrongBinder(iBinder);
            this.c.transact(3031, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void O(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            n1.c(parcelObtain, bundle);
            this.c.transact(3048, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void O0(InterfaceC1651k interfaceC1651k, int i7, long j3) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeLong(j3);
            this.c.transact(3038, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void P0(InterfaceC1651k interfaceC1651k, int i7, int i10, int i11, int i12) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            parcelObtain.writeInt(i11);
            parcelObtain.writeInt(i12);
            this.c.transact(3023, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void S(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle, long j3) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            n1.c(parcelObtain, bundle);
            parcelObtain.writeLong(j3);
            this.c.transact(3008, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void U(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3043, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void U0(InterfaceC1651k interfaceC1651k, int i7, int i10, int i11) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            parcelObtain.writeInt(i11);
            this.c.transact(3020, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void V(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            n1.c(parcelObtain, bundle);
            this.c.transact(3033, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void V0(InterfaceC1651k interfaceC1651k, int i7, IBinder iBinder, int i10, long j3) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeStrongBinder(iBinder);
            parcelObtain.writeInt(i10);
            parcelObtain.writeLong(j3);
            this.c.transact(3012, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void W0(InterfaceC1651k interfaceC1651k, int i7, int i10, int i11) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            parcelObtain.writeInt(i11);
            this.c.transact(3022, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void X0(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3021, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void Y(InterfaceC1651k interfaceC1651k, int i7, int i10) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            this.c.transact(3052, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void Z0(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3004, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void a0(InterfaceC1651k interfaceC1651k, int i7, boolean z6) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(z6 ? 1 : 0);
            this.c.transact(3006, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void a1(InterfaceC1651k interfaceC1651k, int i7, int i10, int i11) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            parcelObtain.writeInt(i11);
            this.c.transact(3051, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.c;
    }

    @Override // k3.InterfaceC1655m
    public final void b0(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3005, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void b1(InterfaceC1651k interfaceC1651k, int i7, boolean z6, int i10) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(z6 ? 1 : 0);
            parcelObtain.writeInt(i10);
            this.c.transact(3054, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void c1(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3041, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void d0(InterfaceC1651k interfaceC1651k, int i7, int i10) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            this.c.transact(3017, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void e0(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3026, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void e1(InterfaceC1651k interfaceC1651k, int i7, int i10, Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            n1.c(parcelObtain, bundle);
            this.c.transact(3030, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void f0(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3024, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void f1(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3034, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void g0(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle, boolean z6) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            n1.c(parcelObtain, bundle);
            parcelObtain.writeInt(1);
            this.c.transact(3009, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void g1(InterfaceC1651k interfaceC1651k, int i7, boolean z6) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(z6 ? 1 : 0);
            this.c.transact(3013, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void i0(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3042, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void j1(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3046, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void k0(InterfaceC1651k interfaceC1651k, int i7, int i10) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            this.c.transact(3019, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void l0(InterfaceC1651k interfaceC1651k, int i7, int i10) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            this.c.transact(3053, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void l1(InterfaceC1651k interfaceC1651k, int i7, int i10) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(i10);
            this.c.transact(3037, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void o(InterfaceC1651k interfaceC1651k, int i7, boolean z6) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeInt(z6 ? 1 : 0);
            this.c.transact(3018, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void p(InterfaceC1651k interfaceC1651k) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            this.c.transact(3045, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void s(InterfaceC1651k interfaceC1651k, int i7, Surface surface) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            n1.c(parcelObtain, surface);
            this.c.transact(3044, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void t0(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle, boolean z6) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            n1.c(parcelObtain, bundle);
            parcelObtain.writeInt(z6 ? 1 : 0);
            this.c.transact(3057, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void u0(InterfaceC1651k interfaceC1651k, int i7, IBinder iBinder, boolean z6) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            parcelObtain.writeStrongBinder(iBinder);
            parcelObtain.writeInt(1);
            this.c.transact(3011, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void v(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3040, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1655m
    public final void w(InterfaceC1651k interfaceC1651k, int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaSession");
            parcelObtain.writeStrongInterface(interfaceC1651k);
            parcelObtain.writeInt(i7);
            this.c.transact(3036, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }
}
