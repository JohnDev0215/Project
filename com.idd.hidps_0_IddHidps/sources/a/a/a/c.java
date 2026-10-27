package a.a.a;

import a.a.a.b;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface c extends IInterface {

    public static abstract class a extends Binder implements c {
        public a() {
            attachInterface(this, "com.qtxa.cleanAidl.ISysCleanerInterface");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.qtxa.cleanAidl.ISysCleanerInterface");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.qtxa.cleanAidl.ISysCleanerInterface");
                    n(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.qtxa.cleanAidl.ISysCleanerInterface");
                    b(b.a.f(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.qtxa.cleanAidl.ISysCleanerInterface");
                    s(b.a.f(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.qtxa.cleanAidl.ISysCleanerInterface");
                    k();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.qtxa.cleanAidl.ISysCleanerInterface");
                    double dC = c(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeDouble(dC);
                    return true;
                case 6:
                    parcel.enforceInterface("com.qtxa.cleanAidl.ISysCleanerInterface");
                    double[] dArrO = o();
                    parcel2.writeNoException();
                    parcel2.writeDoubleArray(dArrO);
                    return true;
                case 7:
                    parcel.enforceInterface("com.qtxa.cleanAidl.ISysCleanerInterface");
                    double[] dArrA = a();
                    parcel2.writeNoException();
                    parcel2.writeDoubleArray(dArrA);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    double[] a();

    void b(b bVar);

    double c(boolean z);

    void k();

    void n(int i);

    double[] o();

    void s(b bVar);
}
