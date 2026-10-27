package com.ts.lib.settings.vradapter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.lib.settings.vradapter.ILocalVrSettingsCallback;

/* JADX INFO: loaded from: classes3.dex */
public interface ILocalVrSettingsService extends IInterface {

    public static class Default implements ILocalVrSettingsService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
        public void doStartVr(ILocalVrSettingsCallback iLocalVrSettingsCallback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
        public void doStopVr(ILocalVrSettingsCallback iLocalVrSettingsCallback) throws RemoteException {
        }

        @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
        public int getVrState() throws RemoteException {
            return 0;
        }
    }

    void doStartVr(ILocalVrSettingsCallback iLocalVrSettingsCallback) throws RemoteException;

    void doStopVr(ILocalVrSettingsCallback iLocalVrSettingsCallback) throws RemoteException;

    int getVrState() throws RemoteException;

    public static abstract class Stub extends Binder implements ILocalVrSettingsService {
        private static final String DESCRIPTOR = "com.ts.lib.settings.vradapter.ILocalVrSettingsService";
        static final int TRANSACTION_doStartVr = 2;
        static final int TRANSACTION_doStopVr = 3;
        static final int TRANSACTION_getVrState = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocalVrSettingsService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILocalVrSettingsService)) {
                return (ILocalVrSettingsService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int vrState = getVrState();
                parcel2.writeNoException();
                parcel2.writeInt(vrState);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                doStartVr(ILocalVrSettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                doStopVr(ILocalVrSettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ILocalVrSettingsService {
            public static ILocalVrSettingsService sDefaultImpl;
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

            @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
            public int getVrState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVrState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
            public void doStartVr(ILocalVrSettingsCallback iLocalVrSettingsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iLocalVrSettingsCallback != null ? iLocalVrSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().doStartVr(iLocalVrSettingsCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
            public void doStopVr(ILocalVrSettingsCallback iLocalVrSettingsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iLocalVrSettingsCallback != null ? iLocalVrSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().doStopVr(iLocalVrSettingsCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocalVrSettingsService iLocalVrSettingsService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iLocalVrSettingsService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLocalVrSettingsService;
            return true;
        }

        public static ILocalVrSettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
