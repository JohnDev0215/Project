package com.qtxa.qtcarsecsvc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.qtxa.qtcarsecsvc.ISystemCallBack;
import com.qtxa.qtcarsecsvc.OnEventListener;

/* JADX INFO: loaded from: classes.dex */
public interface ISecurityService extends IInterface {

    public static abstract class a extends Binder implements ISecurityService {
        public a() {
            attachInterface(this, "com.qtxa.qtcarsecsvc.ISecurityService");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.qtxa.qtcarsecsvc.ISecurityService");
                addEventListener(OnEventListener.a.f(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.qtxa.qtcarsecsvc.ISecurityService");
                removeEventListener(OnEventListener.a.f(parcel.readStrongBinder()));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface("com.qtxa.qtcarsecsvc.ISecurityService");
                String strCall = call(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(strCall);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface("com.qtxa.qtcarsecsvc.ISecurityService");
                addSystemCallBack(ISystemCallBack.a.f(parcel.readStrongBinder()));
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.qtxa.qtcarsecsvc.ISecurityService");
            return true;
        }
    }

    void addEventListener(OnEventListener onEventListener);

    void addSystemCallBack(ISystemCallBack iSystemCallBack);

    String call(String str);

    void removeEventListener(OnEventListener onEventListener);
}
