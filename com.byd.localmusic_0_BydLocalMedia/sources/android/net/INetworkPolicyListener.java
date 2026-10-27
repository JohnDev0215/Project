package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.SubscriptionPlan;

/* JADX INFO: loaded from: classes2.dex */
public interface INetworkPolicyListener extends IInterface {

    public static class Default implements INetworkPolicyListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.INetworkPolicyListener
        public void onMeteredIfacesChanged(String[] strArr) throws RemoteException {
        }

        @Override // android.net.INetworkPolicyListener
        public void onRestrictBackgroundChanged(boolean z) throws RemoteException {
        }

        @Override // android.net.INetworkPolicyListener
        public void onSubscriptionOverride(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.net.INetworkPolicyListener
        public void onSubscriptionPlansChanged(int i, SubscriptionPlan[] subscriptionPlanArr) throws RemoteException {
        }

        @Override // android.net.INetworkPolicyListener
        public void onUidPoliciesChanged(int i, int i2) throws RemoteException {
        }

        @Override // android.net.INetworkPolicyListener
        public void onUidRulesChanged(int i, int i2) throws RemoteException {
        }
    }

    void onMeteredIfacesChanged(String[] strArr) throws RemoteException;

    void onRestrictBackgroundChanged(boolean z) throws RemoteException;

    void onSubscriptionOverride(int i, int i2, int i3) throws RemoteException;

    void onSubscriptionPlansChanged(int i, SubscriptionPlan[] subscriptionPlanArr) throws RemoteException;

    void onUidPoliciesChanged(int i, int i2) throws RemoteException;

    void onUidRulesChanged(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements INetworkPolicyListener {
        private static final String DESCRIPTOR = "android.net.INetworkPolicyListener";
        static final int TRANSACTION_onMeteredIfacesChanged = 2;
        static final int TRANSACTION_onRestrictBackgroundChanged = 3;
        static final int TRANSACTION_onSubscriptionOverride = 5;
        static final int TRANSACTION_onSubscriptionPlansChanged = 6;
        static final int TRANSACTION_onUidPoliciesChanged = 4;
        static final int TRANSACTION_onUidRulesChanged = 1;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onUidRulesChanged";
                case 2:
                    return "onMeteredIfacesChanged";
                case 3:
                    return "onRestrictBackgroundChanged";
                case 4:
                    return "onUidPoliciesChanged";
                case 5:
                    return "onSubscriptionOverride";
                case 6:
                    return "onSubscriptionPlansChanged";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INetworkPolicyListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INetworkPolicyListener)) {
                return (INetworkPolicyListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
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
                    onUidRulesChanged(parcel.readInt(), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMeteredIfacesChanged(parcel.createStringArray());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRestrictBackgroundChanged(parcel.readInt() != 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onUidPoliciesChanged(parcel.readInt(), parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSubscriptionOverride(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSubscriptionPlansChanged(parcel.readInt(), (SubscriptionPlan[]) parcel.createTypedArray(SubscriptionPlan.CREATOR));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements INetworkPolicyListener {
            public static INetworkPolicyListener sDefaultImpl;
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

            @Override // android.net.INetworkPolicyListener
            public void onUidRulesChanged(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUidRulesChanged(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkPolicyListener
            public void onMeteredIfacesChanged(String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMeteredIfacesChanged(strArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkPolicyListener
            public void onRestrictBackgroundChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRestrictBackgroundChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkPolicyListener
            public void onUidPoliciesChanged(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUidPoliciesChanged(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkPolicyListener
            public void onSubscriptionOverride(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSubscriptionOverride(i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.INetworkPolicyListener
            public void onSubscriptionPlansChanged(int i, SubscriptionPlan[] subscriptionPlanArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedArray(subscriptionPlanArr, 0);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSubscriptionPlansChanged(i, subscriptionPlanArr);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INetworkPolicyListener iNetworkPolicyListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iNetworkPolicyListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iNetworkPolicyListener;
            return true;
        }

        public static INetworkPolicyListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
