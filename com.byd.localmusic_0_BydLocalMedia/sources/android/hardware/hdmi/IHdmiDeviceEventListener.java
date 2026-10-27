package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IHdmiDeviceEventListener extends IInterface {

    public static class Default implements IHdmiDeviceEventListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.hdmi.IHdmiDeviceEventListener
        public void onStatusChanged(HdmiDeviceInfo hdmiDeviceInfo, int i) throws RemoteException {
        }
    }

    void onStatusChanged(HdmiDeviceInfo hdmiDeviceInfo, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IHdmiDeviceEventListener {
        private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiDeviceEventListener";
        static final int TRANSACTION_onStatusChanged = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onStatusChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHdmiDeviceEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IHdmiDeviceEventListener)) {
                return (IHdmiDeviceEventListener) iInterfaceQueryLocalInterface;
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
                onStatusChanged(parcel.readInt() != 0 ? HdmiDeviceInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IHdmiDeviceEventListener {
            public static IHdmiDeviceEventListener sDefaultImpl;
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

            @Override // android.hardware.hdmi.IHdmiDeviceEventListener
            public void onStatusChanged(HdmiDeviceInfo hdmiDeviceInfo, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (hdmiDeviceInfo != null) {
                        parcelObtain.writeInt(1);
                        hdmiDeviceInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStatusChanged(hdmiDeviceInfo, i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IHdmiDeviceEventListener iHdmiDeviceEventListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iHdmiDeviceEventListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iHdmiDeviceEventListener;
            return true;
        }

        public static IHdmiDeviceEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
