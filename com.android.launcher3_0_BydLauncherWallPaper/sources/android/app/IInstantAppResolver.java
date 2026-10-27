package android.app;

import android.content.pm.InstantAppRequestInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IInstantAppResolver extends IInterface {

    public static class Default implements IInstantAppResolver {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.IInstantAppResolver
        public void getInstantAppIntentFilterList(InstantAppRequestInfo instantAppRequestInfo, IRemoteCallback iRemoteCallback) throws RemoteException {
        }

        @Override // android.app.IInstantAppResolver
        public void getInstantAppResolveInfoList(InstantAppRequestInfo instantAppRequestInfo, int i, IRemoteCallback iRemoteCallback) throws RemoteException {
        }
    }

    void getInstantAppIntentFilterList(InstantAppRequestInfo instantAppRequestInfo, IRemoteCallback iRemoteCallback) throws RemoteException;

    void getInstantAppResolveInfoList(InstantAppRequestInfo instantAppRequestInfo, int i, IRemoteCallback iRemoteCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IInstantAppResolver {
        private static final String DESCRIPTOR = "android.app.IInstantAppResolver";
        static final int TRANSACTION_getInstantAppIntentFilterList = 2;
        static final int TRANSACTION_getInstantAppResolveInfoList = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getInstantAppResolveInfoList";
            }
            if (i != 2) {
                return null;
            }
            return "getInstantAppIntentFilterList";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInstantAppResolver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInstantAppResolver)) {
                return (IInstantAppResolver) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                getInstantAppResolveInfoList(parcel.readInt() != 0 ? InstantAppRequestInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                getInstantAppIntentFilterList(parcel.readInt() != 0 ? InstantAppRequestInfo.CREATOR.createFromParcel(parcel) : null, IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IInstantAppResolver {
            public static IInstantAppResolver sDefaultImpl;
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

            @Override // android.app.IInstantAppResolver
            public void getInstantAppResolveInfoList(InstantAppRequestInfo instantAppRequestInfo, int i, IRemoteCallback iRemoteCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (instantAppRequestInfo != null) {
                        parcelObtain.writeInt(1);
                        instantAppRequestInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iRemoteCallback != null ? iRemoteCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getInstantAppResolveInfoList(instantAppRequestInfo, i, iRemoteCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IInstantAppResolver
            public void getInstantAppIntentFilterList(InstantAppRequestInfo instantAppRequestInfo, IRemoteCallback iRemoteCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (instantAppRequestInfo != null) {
                        parcelObtain.writeInt(1);
                        instantAppRequestInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iRemoteCallback != null ? iRemoteCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getInstantAppIntentFilterList(instantAppRequestInfo, iRemoteCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInstantAppResolver iInstantAppResolver) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInstantAppResolver == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInstantAppResolver;
            return true;
        }

        public static IInstantAppResolver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
