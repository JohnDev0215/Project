package android.printservice.recommendation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IRecommendationServiceCallbacks extends IInterface {

    public static class Default implements IRecommendationServiceCallbacks {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.printservice.recommendation.IRecommendationServiceCallbacks
        public void onRecommendationsUpdated(List<RecommendationInfo> list) throws RemoteException {
        }
    }

    void onRecommendationsUpdated(List<RecommendationInfo> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IRecommendationServiceCallbacks {
        private static final String DESCRIPTOR = "android.printservice.recommendation.IRecommendationServiceCallbacks";
        static final int TRANSACTION_onRecommendationsUpdated = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onRecommendationsUpdated";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRecommendationServiceCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRecommendationServiceCallbacks)) {
                return (IRecommendationServiceCallbacks) iInterfaceQueryLocalInterface;
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
                onRecommendationsUpdated(parcel.createTypedArrayList(RecommendationInfo.CREATOR));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IRecommendationServiceCallbacks {
            public static IRecommendationServiceCallbacks sDefaultImpl;
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

            @Override // android.printservice.recommendation.IRecommendationServiceCallbacks
            public void onRecommendationsUpdated(List<RecommendationInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRecommendationsUpdated(list);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRecommendationServiceCallbacks iRecommendationServiceCallbacks) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRecommendationServiceCallbacks == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRecommendationServiceCallbacks;
            return true;
        }

        public static IRecommendationServiceCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
