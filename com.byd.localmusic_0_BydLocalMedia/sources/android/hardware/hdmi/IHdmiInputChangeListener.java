package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IHdmiInputChangeListener extends IInterface {

    public static class Default implements IHdmiInputChangeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.hdmi.IHdmiInputChangeListener
        public void onChanged(HdmiDeviceInfo hdmiDeviceInfo) throws RemoteException {
        }
    }

    void onChanged(HdmiDeviceInfo hdmiDeviceInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IHdmiInputChangeListener {
        private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiInputChangeListener";
        static final int TRANSACTION_onChanged = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHdmiInputChangeListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IHdmiInputChangeListener)) {
                return (IHdmiInputChangeListener) iInterfaceQueryLocalInterface;
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
                onChanged(parcel.readInt() != 0 ? HdmiDeviceInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IHdmiInputChangeListener {
            public static IHdmiInputChangeListener sDefaultImpl;
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

            @Override // android.hardware.hdmi.IHdmiInputChangeListener
            public void onChanged(HdmiDeviceInfo hdmiDeviceInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (hdmiDeviceInfo != null) {
                        parcelObtain.writeInt(1);
                        hdmiDeviceInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onChanged(hdmiDeviceInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IHdmiInputChangeListener iHdmiInputChangeListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iHdmiInputChangeListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iHdmiInputChangeListener;
            return true;
        }

        public static IHdmiInputChangeListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
