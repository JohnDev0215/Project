package com.byd.data.collect;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: IDataCollect.java */
/* JADX INFO: loaded from: classes.dex */
public interface g extends IInterface {

    /* JADX INFO: compiled from: IDataCollect.java */
    public static abstract class a extends Binder implements g {
        public a() {
            attachInterface(this, "com.byd.data.collect.IDataCollect");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 == 1) {
                parcel.enforceInterface("com.byd.data.collect.IDataCollect");
                String strB = b();
                parcel2.writeNoException();
                parcel2.writeString(strB);
                return true;
            }
            if (i2 != 2) {
                if (i2 != 1598968902) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                parcel2.writeString("com.byd.data.collect.IDataCollect");
                return true;
            }
            parcel.enforceInterface("com.byd.data.collect.IDataCollect");
            o();
            parcel2.writeNoException();
            return true;
        }
    }

    String b();

    void o();
}
