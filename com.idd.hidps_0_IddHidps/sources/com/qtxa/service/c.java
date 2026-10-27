package com.qtxa.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.qtxa.service.d;

/* JADX INFO: loaded from: classes.dex */
public interface c extends IInterface {

    public static abstract class a extends Binder implements c {
        public a() {
            attachInterface(this, "com.qtxa.service.IQtxaIdpsComm");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.qtxa.service.IQtxaIdpsComm");
                String strT = t(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(strT);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.qtxa.service.IQtxaIdpsComm");
                d(parcel.readString(), d.a.f(parcel.readStrongBinder()));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface("com.qtxa.service.IQtxaIdpsComm");
                v(parcel.readString(), d.a.f(parcel.readStrongBinder()));
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.qtxa.service.IQtxaIdpsComm");
            return true;
        }
    }

    void d(String str, d dVar);

    String t(String str);

    void v(String str, d dVar);
}
