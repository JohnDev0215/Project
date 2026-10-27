package b.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: IAvmServiceListener.java */
/* JADX INFO: loaded from: classes.dex */
public interface d extends IInterface {

    /* JADX INFO: compiled from: IAvmServiceListener.java */
    public static abstract class a extends Binder implements d {
        public a() {
            attachInterface(this, "com.ts.avm.IAvmServiceListener");
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
                parcel2.writeString("com.ts.avm.IAvmServiceListener");
                return true;
            }
            parcel.enforceInterface("com.ts.avm.IAvmServiceListener");
            g(parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void g(int i, String str);
}
