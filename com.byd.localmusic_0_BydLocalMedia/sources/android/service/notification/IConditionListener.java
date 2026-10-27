package android.service.notification;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IConditionListener extends IInterface {

    public static class Default implements IConditionListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.notification.IConditionListener
        public void onConditionsReceived(Condition[] conditionArr) throws RemoteException {
        }
    }

    void onConditionsReceived(Condition[] conditionArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IConditionListener {
        private static final String DESCRIPTOR = "android.service.notification.IConditionListener";
        static final int TRANSACTION_onConditionsReceived = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onConditionsReceived";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IConditionListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IConditionListener)) {
                return (IConditionListener) iInterfaceQueryLocalInterface;
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
                onConditionsReceived((Condition[]) parcel.createTypedArray(Condition.CREATOR));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IConditionListener {
            public static IConditionListener sDefaultImpl;
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

            @Override // android.service.notification.IConditionListener
            public void onConditionsReceived(Condition[] conditionArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(conditionArr, 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConditionsReceived(conditionArr);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IConditionListener iConditionListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iConditionListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iConditionListener;
            return true;
        }

        public static IConditionListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
