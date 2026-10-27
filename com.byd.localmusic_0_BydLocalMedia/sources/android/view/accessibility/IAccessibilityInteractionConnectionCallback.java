package android.view.accessibility;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IAccessibilityInteractionConnectionCallback extends IInterface {

    public static class Default implements IAccessibilityInteractionConnectionCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnectionCallback
        public void setFindAccessibilityNodeInfoResult(AccessibilityNodeInfo accessibilityNodeInfo, int i) throws RemoteException {
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnectionCallback
        public void setFindAccessibilityNodeInfosResult(List<AccessibilityNodeInfo> list, int i) throws RemoteException {
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnectionCallback
        public void setPerformAccessibilityActionResult(boolean z, int i) throws RemoteException {
        }
    }

    void setFindAccessibilityNodeInfoResult(AccessibilityNodeInfo accessibilityNodeInfo, int i) throws RemoteException;

    void setFindAccessibilityNodeInfosResult(List<AccessibilityNodeInfo> list, int i) throws RemoteException;

    void setPerformAccessibilityActionResult(boolean z, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IAccessibilityInteractionConnectionCallback {
        private static final String DESCRIPTOR = "android.view.accessibility.IAccessibilityInteractionConnectionCallback";
        static final int TRANSACTION_setFindAccessibilityNodeInfoResult = 1;
        static final int TRANSACTION_setFindAccessibilityNodeInfosResult = 2;
        static final int TRANSACTION_setPerformAccessibilityActionResult = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "setFindAccessibilityNodeInfoResult";
            }
            if (i == 2) {
                return "setFindAccessibilityNodeInfosResult";
            }
            if (i != 3) {
                return null;
            }
            return "setPerformAccessibilityActionResult";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAccessibilityInteractionConnectionCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAccessibilityInteractionConnectionCallback)) {
                return (IAccessibilityInteractionConnectionCallback) iInterfaceQueryLocalInterface;
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
                setFindAccessibilityNodeInfoResult(parcel.readInt() != 0 ? AccessibilityNodeInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                setFindAccessibilityNodeInfosResult(parcel.createTypedArrayList(AccessibilityNodeInfo.CREATOR), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                setPerformAccessibilityActionResult(parcel.readInt() != 0, parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IAccessibilityInteractionConnectionCallback {
            public static IAccessibilityInteractionConnectionCallback sDefaultImpl;
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

            @Override // android.view.accessibility.IAccessibilityInteractionConnectionCallback
            public void setFindAccessibilityNodeInfoResult(AccessibilityNodeInfo accessibilityNodeInfo, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (accessibilityNodeInfo != null) {
                        parcelObtain.writeInt(1);
                        accessibilityNodeInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setFindAccessibilityNodeInfoResult(accessibilityNodeInfo, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.accessibility.IAccessibilityInteractionConnectionCallback
            public void setFindAccessibilityNodeInfosResult(List<AccessibilityNodeInfo> list, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setFindAccessibilityNodeInfosResult(list, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.accessibility.IAccessibilityInteractionConnectionCallback
            public void setPerformAccessibilityActionResult(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setPerformAccessibilityActionResult(z, i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAccessibilityInteractionConnectionCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAccessibilityInteractionConnectionCallback;
            return true;
        }

        public static IAccessibilityInteractionConnectionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
