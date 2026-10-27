package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothMetadataListener extends IInterface {

    public static class Default implements IBluetoothMetadataListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.bluetooth.IBluetoothMetadataListener
        public void onMetadataChanged(BluetoothDevice bluetoothDevice, int i, byte[] bArr) throws RemoteException {
        }
    }

    void onMetadataChanged(BluetoothDevice bluetoothDevice, int i, byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetoothMetadataListener {
        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothMetadataListener";
        static final int TRANSACTION_onMetadataChanged = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onMetadataChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBluetoothMetadataListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBluetoothMetadataListener)) {
                return (IBluetoothMetadataListener) iInterfaceQueryLocalInterface;
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
                onMetadataChanged(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.createByteArray());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IBluetoothMetadataListener {
            public static IBluetoothMetadataListener sDefaultImpl;
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

            @Override // android.bluetooth.IBluetoothMetadataListener
            public void onMetadataChanged(BluetoothDevice bluetoothDevice, int i, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMetadataChanged(bluetoothDevice, i, bArr);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothMetadataListener iBluetoothMetadataListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBluetoothMetadataListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBluetoothMetadataListener;
            return true;
        }

        public static IBluetoothMetadataListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
