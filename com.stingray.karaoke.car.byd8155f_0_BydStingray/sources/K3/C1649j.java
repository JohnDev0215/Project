package k3;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: renamed from: k3.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1649j implements InterfaceC1651k {
    public IBinder c;

    @Override // k3.InterfaceC1651k
    public final void N0(int i7, Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaController");
            parcelObtain.writeInt(i7);
            n1.j(parcelObtain, bundle);
            this.c.transact(3009, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1651k
    public final void S0(int i7, Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaController");
            parcelObtain.writeInt(i7);
            n1.j(parcelObtain, bundle);
            this.c.transact(3008, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1651k
    public final void T0(int i7, Bundle bundle, boolean z6) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaController");
            parcelObtain.writeInt(i7);
            n1.j(parcelObtain, bundle);
            parcelObtain.writeInt(z6 ? 1 : 0);
            this.c.transact(3007, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1651k
    public final void a() {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaController");
            parcelObtain.writeInt(0);
            this.c.transact(3006, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.c;
    }

    @Override // k3.InterfaceC1651k
    public final void d1(int i7, Bundle bundle, Bundle bundle2) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaController");
            parcelObtain.writeInt(i7);
            n1.j(parcelObtain, bundle);
            n1.j(parcelObtain, bundle2);
            this.c.transact(3013, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1651k
    public final void f(int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaController");
            parcelObtain.writeInt(i7);
            this.c.transact(3011, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1651k
    public final void i1(int i7, Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaController");
            parcelObtain.writeInt(i7);
            n1.j(parcelObtain, bundle);
            this.c.transact(3002, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1651k
    public final void n(int i7, List list) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaController");
            parcelObtain.writeInt(i7);
            if (list == null) {
                parcelObtain.writeInt(-1);
            } else {
                int i10 = ((d5.d0) list).o;
                parcelObtain.writeInt(i10);
                for (int i11 = 0; i11 < i10; i11++) {
                    n1.j(parcelObtain, (Parcelable) ((d5.d0) list).get(i11));
                }
            }
            this.c.transact(3004, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1651k
    public final void r0(int i7, Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaController");
            parcelObtain.writeInt(i7);
            n1.j(parcelObtain, bundle);
            this.c.transact(3001, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // k3.InterfaceC1651k
    public final void t(int i7, Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.media3.session.IMediaController");
            parcelObtain.writeInt(i7);
            n1.j(parcelObtain, bundle);
            this.c.transact(3003, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }
}
