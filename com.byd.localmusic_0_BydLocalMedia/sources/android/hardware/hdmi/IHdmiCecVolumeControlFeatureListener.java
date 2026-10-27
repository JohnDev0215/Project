package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IHdmiCecVolumeControlFeatureListener extends IInterface {

    public static class Default implements IHdmiCecVolumeControlFeatureListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener
        public void onHdmiCecVolumeControlFeature(boolean z) throws RemoteException {
        }
    }

    void onHdmiCecVolumeControlFeature(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IHdmiCecVolumeControlFeatureListener {
        private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener";
        static final int TRANSACTION_onHdmiCecVolumeControlFeature = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onHdmiCecVolumeControlFeature";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHdmiCecVolumeControlFeatureListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IHdmiCecVolumeControlFeatureListener)) {
                return (IHdmiCecVolumeControlFeatureListener) iInterfaceQueryLocalInterface;
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
                onHdmiCecVolumeControlFeature(parcel.readInt() != 0);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IHdmiCecVolumeControlFeatureListener {
            public static IHdmiCecVolumeControlFeatureListener sDefaultImpl;
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

            @Override // android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener
            public void onHdmiCecVolumeControlFeature(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onHdmiCecVolumeControlFeature(z);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IHdmiCecVolumeControlFeatureListener iHdmiCecVolumeControlFeatureListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iHdmiCecVolumeControlFeatureListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iHdmiCecVolumeControlFeatureListener;
            return true;
        }

        public static IHdmiCecVolumeControlFeatureListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
