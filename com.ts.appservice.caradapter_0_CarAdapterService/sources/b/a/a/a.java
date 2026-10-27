package b.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: IAvmCallListener.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: b.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IAvmCallListener.java */
    public static abstract class AbstractBinderC0001a extends Binder implements a {
        public AbstractBinderC0001a() {
            attachInterface(this, "com.ts.avm.IAvmCallListener");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.ts.avm.IAvmCallListener");
                return true;
            }
            parcel.enforceInterface("com.ts.avm.IAvmCallListener");
            m(parcel.readString(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }

    void m(String str, int i, int i2);
}
