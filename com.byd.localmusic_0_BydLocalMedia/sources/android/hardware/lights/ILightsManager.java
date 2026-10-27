package android.hardware.lights;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface ILightsManager extends IInterface {

    public static class Default implements ILightsManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.lights.ILightsManager
        public void closeSession(IBinder iBinder) throws RemoteException {
        }

        @Override // android.hardware.lights.ILightsManager
        public LightState getLightState(int i) throws RemoteException {
            return null;
        }

        @Override // android.hardware.lights.ILightsManager
        public List<Light> getLights() throws RemoteException {
            return null;
        }

        @Override // android.hardware.lights.ILightsManager
        public void openSession(IBinder iBinder) throws RemoteException {
        }

        @Override // android.hardware.lights.ILightsManager
        public void setLightStates(IBinder iBinder, int[] iArr, LightState[] lightStateArr) throws RemoteException {
        }
    }

    void closeSession(IBinder iBinder) throws RemoteException;

    LightState getLightState(int i) throws RemoteException;

    List<Light> getLights() throws RemoteException;

    void openSession(IBinder iBinder) throws RemoteException;

    void setLightStates(IBinder iBinder, int[] iArr, LightState[] lightStateArr) throws RemoteException;

    public static abstract class Stub extends Binder implements ILightsManager {
        private static final String DESCRIPTOR = "android.hardware.lights.ILightsManager";
        static final int TRANSACTION_closeSession = 4;
        static final int TRANSACTION_getLightState = 2;
        static final int TRANSACTION_getLights = 1;
        static final int TRANSACTION_openSession = 3;
        static final int TRANSACTION_setLightStates = 5;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getLights";
            }
            if (i == 2) {
                return "getLightState";
            }
            if (i == 3) {
                return "openSession";
            }
            if (i == 4) {
                return "closeSession";
            }
            if (i != 5) {
                return null;
            }
            return "setLightStates";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILightsManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILightsManager)) {
                return (ILightsManager) iInterfaceQueryLocalInterface;
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
                List<Light> lights = getLights();
                parcel2.writeNoException();
                parcel2.writeTypedList(lights);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                LightState lightState = getLightState(parcel.readInt());
                parcel2.writeNoException();
                if (lightState != null) {
                    parcel2.writeInt(1);
                    lightState.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                openSession(parcel.readStrongBinder());
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                closeSession(parcel.readStrongBinder());
                parcel2.writeNoException();
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            setLightStates(parcel.readStrongBinder(), parcel.createIntArray(), (LightState[]) parcel.createTypedArray(LightState.CREATOR));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ILightsManager {
            public static ILightsManager sDefaultImpl;
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

            @Override // android.hardware.lights.ILightsManager
            public List<Light> getLights() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLights();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(Light.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.lights.ILightsManager
            public LightState getLightState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLightState(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? LightState.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.lights.ILightsManager
            public void openSession(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openSession(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.lights.ILightsManager
            public void closeSession(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeSession(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.lights.ILightsManager
            public void setLightStates(IBinder iBinder, int[] iArr, LightState[] lightStateArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeTypedArray(lightStateArr, 0);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLightStates(iBinder, iArr, lightStateArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILightsManager iLightsManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iLightsManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLightsManager;
            return true;
        }

        public static ILightsManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
