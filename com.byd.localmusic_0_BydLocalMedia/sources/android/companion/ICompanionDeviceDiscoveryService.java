package android.companion;

import android.companion.IFindDeviceCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.infra.AndroidFuture;

/* JADX INFO: loaded from: classes.dex */
public interface ICompanionDeviceDiscoveryService extends IInterface {

    public static class Default implements ICompanionDeviceDiscoveryService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.companion.ICompanionDeviceDiscoveryService
        public void startDiscovery(AssociationRequest associationRequest, String str, IFindDeviceCallback iFindDeviceCallback, AndroidFuture<Association> androidFuture) throws RemoteException {
        }
    }

    void startDiscovery(AssociationRequest associationRequest, String str, IFindDeviceCallback iFindDeviceCallback, AndroidFuture<Association> androidFuture) throws RemoteException;

    public static abstract class Stub extends Binder implements ICompanionDeviceDiscoveryService {
        private static final String DESCRIPTOR = "android.companion.ICompanionDeviceDiscoveryService";
        static final int TRANSACTION_startDiscovery = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "startDiscovery";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICompanionDeviceDiscoveryService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICompanionDeviceDiscoveryService)) {
                return (ICompanionDeviceDiscoveryService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            startDiscovery(parcel.readInt() != 0 ? AssociationRequest.CREATOR.createFromParcel(parcel) : null, parcel.readString(), IFindDeviceCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? AndroidFuture.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ICompanionDeviceDiscoveryService {
            public static ICompanionDeviceDiscoveryService sDefaultImpl;
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

            @Override // android.companion.ICompanionDeviceDiscoveryService
            public void startDiscovery(AssociationRequest associationRequest, String str, IFindDeviceCallback iFindDeviceCallback, AndroidFuture<Association> androidFuture) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (associationRequest != null) {
                        parcelObtain.writeInt(1);
                        associationRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iFindDeviceCallback != null ? iFindDeviceCallback.asBinder() : null);
                    if (androidFuture != null) {
                        parcelObtain.writeInt(1);
                        androidFuture.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startDiscovery(associationRequest, str, iFindDeviceCallback, androidFuture);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICompanionDeviceDiscoveryService iCompanionDeviceDiscoveryService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCompanionDeviceDiscoveryService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCompanionDeviceDiscoveryService;
            return true;
        }

        public static ICompanionDeviceDiscoveryService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
