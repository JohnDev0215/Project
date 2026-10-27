package android.printservice.recommendation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.printservice.recommendation.IRecommendationServiceCallbacks;

/* JADX INFO: loaded from: classes2.dex */
public interface IRecommendationService extends IInterface {

    public static class Default implements IRecommendationService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.printservice.recommendation.IRecommendationService
        public void registerCallbacks(IRecommendationServiceCallbacks iRecommendationServiceCallbacks) throws RemoteException {
        }
    }

    void registerCallbacks(IRecommendationServiceCallbacks iRecommendationServiceCallbacks) throws RemoteException;

    public static abstract class Stub extends Binder implements IRecommendationService {
        private static final String DESCRIPTOR = "android.printservice.recommendation.IRecommendationService";
        static final int TRANSACTION_registerCallbacks = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "registerCallbacks";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRecommendationService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRecommendationService)) {
                return (IRecommendationService) iInterfaceQueryLocalInterface;
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
                registerCallbacks(IRecommendationServiceCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IRecommendationService {
            public static IRecommendationService sDefaultImpl;
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

            @Override // android.printservice.recommendation.IRecommendationService
            public void registerCallbacks(IRecommendationServiceCallbacks iRecommendationServiceCallbacks) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRecommendationServiceCallbacks != null ? iRecommendationServiceCallbacks.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerCallbacks(iRecommendationServiceCallbacks);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRecommendationService iRecommendationService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRecommendationService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRecommendationService;
            return true;
        }

        public static IRecommendationService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
