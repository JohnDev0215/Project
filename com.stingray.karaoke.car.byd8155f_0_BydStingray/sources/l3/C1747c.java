package l3;

import android.os.IBinder;
import android.os.Parcel;
import java.util.List;

/* JADX INFO: renamed from: l3.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1747c implements InterfaceC1748d {
    public IBinder c;

    @Override // l3.InterfaceC1748d
    public final void E(X x8) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            if (x8 != null) {
                parcelObtain.writeInt(1);
                x8.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            if (!this.c.transact(3, parcelObtain, null, 1)) {
                int i7 = BinderC1769z.d;
            }
            parcelObtain.recycle();
        } catch (Throwable th) {
            parcelObtain.recycle();
            throw th;
        }
    }

    @Override // l3.InterfaceC1748d
    public final void R0(C1741F c1741f) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            if (c1741f != null) {
                parcelObtain.writeInt(1);
                parcelObtain.writeBundle(c1741f.l);
            } else {
                parcelObtain.writeInt(0);
            }
            if (!this.c.transact(4, parcelObtain, null, 1)) {
                int i7 = BinderC1769z.d;
            }
            parcelObtain.recycle();
        } catch (Throwable th) {
            parcelObtain.recycle();
            throw th;
        }
    }

    @Override // l3.InterfaceC1748d
    public final void X(int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            parcelObtain.writeInt(i7);
            if (!this.c.transact(12, parcelObtain, null, 1)) {
                int i10 = BinderC1769z.d;
            }
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.c;
    }

    @Override // l3.InterfaceC1748d
    public final void d(int i7) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            parcelObtain.writeInt(i7);
            if (!this.c.transact(9, parcelObtain, null, 1)) {
                int i10 = BinderC1769z.d;
            }
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1748d
    public final void m0(List list) {
        throw null;
    }

    @Override // l3.InterfaceC1748d
    public final void q(CharSequence charSequence) {
        throw null;
    }

    @Override // l3.InterfaceC1748d
    public final void u() {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            if (!this.c.transact(2, parcelObtain, null, 1)) {
                int i7 = BinderC1769z.d;
            }
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // l3.InterfaceC1748d
    public final void y(V v10) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            parcelObtain.writeInt(1);
            v10.writeToParcel(parcelObtain, 0);
            if (!this.c.transact(8, parcelObtain, null, 1)) {
                int i7 = BinderC1769z.d;
            }
        } finally {
            parcelObtain.recycle();
        }
    }
}
