package com.neusoft.geely.btphone.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp;
import com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp;
import com.ecarx.xui.adaptapi.bt1.gattserver.IGattServer;
import com.ecarx.xui.adaptapi.bt1.hfp.IHfp;
import com.ecarx.xui.adaptapi.bt1.hid.IHid;
import com.ecarx.xui.adaptapi.bt1.map.IMap;
import com.ecarx.xui.adaptapi.bt1.pbap.IPbap;
import com.ecarx.xui.adaptapi.bt1.settings.IBtSettings;
import com.ecarx.xui.adaptapi.bt1.spp.ISpp;

/* JADX INFO: loaded from: classes2.dex */
public interface IBtPhoneManager extends IInterface {
    IA2dp getA2dp() throws RemoteException;

    IAvrcp getAvrcp() throws RemoteException;

    IBtSettings getBtSettings() throws RemoteException;

    IGattServer getGattServer() throws RemoteException;

    IHfp getHfp() throws RemoteException;

    IHid getHid() throws RemoteException;

    IMap getMap() throws RemoteException;

    IPbap getPbap() throws RemoteException;

    ISpp getSpp() throws RemoteException;

    public static abstract class Stub extends Binder implements IBtPhoneManager {
        private static final String DESCRIPTOR = "com.neusoft.geely.btphone.service.IBtPhoneManager";
        static final int TRANSACTION_getA2dp = 2;
        static final int TRANSACTION_getAvrcp = 3;
        static final int TRANSACTION_getBtSettings = 8;
        static final int TRANSACTION_getGattServer = 5;
        static final int TRANSACTION_getHfp = 1;
        static final int TRANSACTION_getHid = 6;
        static final int TRANSACTION_getMap = 7;
        static final int TRANSACTION_getPbap = 4;
        static final int TRANSACTION_getSpp = 9;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBtPhoneManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBtPhoneManager)) {
                return (IBtPhoneManager) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    IHfp hfp = getHfp();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(hfp != null ? hfp.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    IA2dp a2dp = getA2dp();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(a2dp != null ? a2dp.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    IAvrcp avrcp = getAvrcp();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(avrcp != null ? avrcp.asBinder() : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    IPbap pbap = getPbap();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(pbap != null ? pbap.asBinder() : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    IGattServer gattServer = getGattServer();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(gattServer != null ? gattServer.asBinder() : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    IHid hid = getHid();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(hid != null ? hid.asBinder() : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    IMap map = getMap();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(map != null ? map.asBinder() : null);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    IBtSettings btSettings = getBtSettings();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(btSettings != null ? btSettings.asBinder() : null);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    ISpp spp = getSpp();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(spp != null ? spp.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBtPhoneManager {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.neusoft.geely.btphone.service.IBtPhoneManager
            public IHfp getHfp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IHfp.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.geely.btphone.service.IBtPhoneManager
            public IA2dp getA2dp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IA2dp.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.geely.btphone.service.IBtPhoneManager
            public IAvrcp getAvrcp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IAvrcp.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.geely.btphone.service.IBtPhoneManager
            public IPbap getPbap() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IPbap.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.geely.btphone.service.IBtPhoneManager
            public IGattServer getGattServer() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IGattServer.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.geely.btphone.service.IBtPhoneManager
            public IHid getHid() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IHid.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.geely.btphone.service.IBtPhoneManager
            public IMap getMap() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IMap.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.geely.btphone.service.IBtPhoneManager
            public IBtSettings getBtSettings() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IBtSettings.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.geely.btphone.service.IBtPhoneManager
            public ISpp getSpp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return ISpp.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
