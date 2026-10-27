package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IShortcutChangeCallback extends IInterface {

    public static class Default implements IShortcutChangeCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.pm.IShortcutChangeCallback
        public void onShortcutsAddedOrUpdated(String str, List<ShortcutInfo> list, UserHandle userHandle) throws RemoteException {
        }

        @Override // android.content.pm.IShortcutChangeCallback
        public void onShortcutsRemoved(String str, List<ShortcutInfo> list, UserHandle userHandle) throws RemoteException {
        }
    }

    void onShortcutsAddedOrUpdated(String str, List<ShortcutInfo> list, UserHandle userHandle) throws RemoteException;

    void onShortcutsRemoved(String str, List<ShortcutInfo> list, UserHandle userHandle) throws RemoteException;

    public static abstract class Stub extends Binder implements IShortcutChangeCallback {
        private static final String DESCRIPTOR = "android.content.pm.IShortcutChangeCallback";
        static final int TRANSACTION_onShortcutsAddedOrUpdated = 1;
        static final int TRANSACTION_onShortcutsRemoved = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onShortcutsAddedOrUpdated";
            }
            if (i != 2) {
                return null;
            }
            return "onShortcutsRemoved";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IShortcutChangeCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IShortcutChangeCallback)) {
                return (IShortcutChangeCallback) iInterfaceQueryLocalInterface;
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
                onShortcutsAddedOrUpdated(parcel.readString(), parcel.createTypedArrayList(ShortcutInfo.CREATOR), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onShortcutsRemoved(parcel.readString(), parcel.createTypedArrayList(ShortcutInfo.CREATOR), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IShortcutChangeCallback {
            public static IShortcutChangeCallback sDefaultImpl;
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

            @Override // android.content.pm.IShortcutChangeCallback
            public void onShortcutsAddedOrUpdated(String str, List<ShortcutInfo> list, UserHandle userHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedList(list);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onShortcutsAddedOrUpdated(str, list, userHandle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IShortcutChangeCallback
            public void onShortcutsRemoved(String str, List<ShortcutInfo> list, UserHandle userHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedList(list);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onShortcutsRemoved(str, list, userHandle);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IShortcutChangeCallback iShortcutChangeCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iShortcutChangeCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iShortcutChangeCallback;
            return true;
        }

        public static IShortcutChangeCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
