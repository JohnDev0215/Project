package android.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IGnssStatusListener extends IInterface {

    public static class Default implements IGnssStatusListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.location.IGnssStatusListener
        public void onFirstFix(int i) throws RemoteException {
        }

        @Override // android.location.IGnssStatusListener
        public void onGnssStarted() throws RemoteException {
        }

        @Override // android.location.IGnssStatusListener
        public void onGnssStopped() throws RemoteException {
        }

        @Override // android.location.IGnssStatusListener
        public void onNmeaReceived(long j, String str) throws RemoteException {
        }

        @Override // android.location.IGnssStatusListener
        public void onSvStatusChanged(int i, int[] iArr, float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5) throws RemoteException {
        }
    }

    void onFirstFix(int i) throws RemoteException;

    void onGnssStarted() throws RemoteException;

    void onGnssStopped() throws RemoteException;

    void onNmeaReceived(long j, String str) throws RemoteException;

    void onSvStatusChanged(int i, int[] iArr, float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5) throws RemoteException;

    public static abstract class Stub extends Binder implements IGnssStatusListener {
        private static final String DESCRIPTOR = "android.location.IGnssStatusListener";
        static final int TRANSACTION_onFirstFix = 3;
        static final int TRANSACTION_onGnssStarted = 1;
        static final int TRANSACTION_onGnssStopped = 2;
        static final int TRANSACTION_onNmeaReceived = 5;
        static final int TRANSACTION_onSvStatusChanged = 4;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onGnssStarted";
            }
            if (i == 2) {
                return "onGnssStopped";
            }
            if (i == 3) {
                return "onFirstFix";
            }
            if (i == 4) {
                return "onSvStatusChanged";
            }
            if (i != 5) {
                return null;
            }
            return "onNmeaReceived";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGnssStatusListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGnssStatusListener)) {
                return (IGnssStatusListener) iInterfaceQueryLocalInterface;
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
                onGnssStarted();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onGnssStopped();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onFirstFix(parcel.readInt());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onSvStatusChanged(parcel.readInt(), parcel.createIntArray(), parcel.createFloatArray(), parcel.createFloatArray(), parcel.createFloatArray(), parcel.createFloatArray(), parcel.createFloatArray());
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                onNmeaReceived(parcel.readLong(), parcel.readString());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IGnssStatusListener {
            public static IGnssStatusListener sDefaultImpl;
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

            @Override // android.location.IGnssStatusListener
            public void onGnssStarted() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGnssStarted();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.IGnssStatusListener
            public void onGnssStopped() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGnssStopped();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.IGnssStatusListener
            public void onFirstFix(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFirstFix(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.IGnssStatusListener
            public void onSvStatusChanged(int i, int[] iArr, float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeFloatArray(fArr);
                    parcelObtain.writeFloatArray(fArr2);
                    parcelObtain.writeFloatArray(fArr3);
                    parcelObtain.writeFloatArray(fArr4);
                    parcelObtain.writeFloatArray(fArr5);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onSvStatusChanged(i, iArr, fArr, fArr2, fArr3, fArr4, fArr5);
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.location.IGnssStatusListener
            public void onNmeaReceived(long j, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNmeaReceived(j, str);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGnssStatusListener iGnssStatusListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGnssStatusListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGnssStatusListener;
            return true;
        }

        public static IGnssStatusListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
