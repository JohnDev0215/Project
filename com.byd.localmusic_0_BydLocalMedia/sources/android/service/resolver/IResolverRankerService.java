package android.service.resolver;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.resolver.IResolverRankerResult;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IResolverRankerService extends IInterface {

    public static class Default implements IResolverRankerService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.resolver.IResolverRankerService
        public void predict(List<ResolverTarget> list, IResolverRankerResult iResolverRankerResult) throws RemoteException {
        }

        @Override // android.service.resolver.IResolverRankerService
        public void train(List<ResolverTarget> list, int i) throws RemoteException {
        }
    }

    void predict(List<ResolverTarget> list, IResolverRankerResult iResolverRankerResult) throws RemoteException;

    void train(List<ResolverTarget> list, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IResolverRankerService {
        private static final String DESCRIPTOR = "android.service.resolver.IResolverRankerService";
        static final int TRANSACTION_predict = 1;
        static final int TRANSACTION_train = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "predict";
            }
            if (i != 2) {
                return null;
            }
            return "train";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IResolverRankerService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IResolverRankerService)) {
                return (IResolverRankerService) iInterfaceQueryLocalInterface;
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
                predict(parcel.createTypedArrayList(ResolverTarget.CREATOR), IResolverRankerResult.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                train(parcel.createTypedArrayList(ResolverTarget.CREATOR), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IResolverRankerService {
            public static IResolverRankerService sDefaultImpl;
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

            @Override // android.service.resolver.IResolverRankerService
            public void predict(List<ResolverTarget> list, IResolverRankerResult iResolverRankerResult) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeStrongBinder(iResolverRankerResult != null ? iResolverRankerResult.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().predict(list, iResolverRankerResult);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.resolver.IResolverRankerService
            public void train(List<ResolverTarget> list, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().train(list, i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IResolverRankerService iResolverRankerService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iResolverRankerService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iResolverRankerService;
            return true;
        }

        public static IResolverRankerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
