package android.support.v4.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface IResultReceiver extends IInterface {

    /* JADX INFO: compiled from: caradapter-bug */
    public static class Default implements IResultReceiver {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.support.v4.os.IResultReceiver
        public void k0(int i, Bundle bundle) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class Stub extends Binder implements IResultReceiver {

        /* JADX INFO: compiled from: caradapter-bug */
        private static class a implements IResultReceiver {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public static IResultReceiver f66b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f67a;

            a(IBinder iBinder) {
                this.f67a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f67a;
            }

            @Override // android.support.v4.os.IResultReceiver
            public void k0(int i, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.os.IResultReceiver");
                    parcelObtain.writeInt(i);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f67a.transact(1, parcelObtain, null, 1) || Stub.o0() == null) {
                        return;
                    }
                    Stub.o0().k0(i, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "android.support.v4.os.IResultReceiver");
        }

        public static IResultReceiver n0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IResultReceiver)) ? new a(iBinder) : (IResultReceiver) iInterfaceQueryLocalInterface;
        }

        public static IResultReceiver o0() {
            return a.f66b;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("android.support.v4.os.IResultReceiver");
                k0(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("android.support.v4.os.IResultReceiver");
            return true;
        }
    }

    void k0(int i, Bundle bundle);
}
