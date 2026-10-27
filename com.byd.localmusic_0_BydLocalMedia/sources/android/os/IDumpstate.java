package android.os;

import android.os.IDumpstateListener;
import java.io.FileDescriptor;

/* JADX INFO: loaded from: classes2.dex */
public interface IDumpstate extends IInterface {
    public static final int BUGREPORT_MODE_DEFAULT = 6;
    public static final int BUGREPORT_MODE_FULL = 0;
    public static final int BUGREPORT_MODE_INTERACTIVE = 1;
    public static final int BUGREPORT_MODE_REMOTE = 2;
    public static final int BUGREPORT_MODE_TELEPHONY = 4;
    public static final int BUGREPORT_MODE_WEAR = 3;
    public static final int BUGREPORT_MODE_WIFI = 5;

    public static class Default implements IDumpstate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IDumpstate
        public void cancelBugreport() throws RemoteException {
        }

        @Override // android.os.IDumpstate
        public void startBugreport(int i, String str, FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, int i2, IDumpstateListener iDumpstateListener, boolean z) throws RemoteException {
        }
    }

    void cancelBugreport() throws RemoteException;

    void startBugreport(int i, String str, FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, int i2, IDumpstateListener iDumpstateListener, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IDumpstate {
        private static final String DESCRIPTOR = "android.os.IDumpstate";
        static final int TRANSACTION_cancelBugreport = 2;
        static final int TRANSACTION_startBugreport = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "startBugreport";
            }
            if (i != 2) {
                return null;
            }
            return "cancelBugreport";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDumpstate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDumpstate)) {
                return (IDumpstate) iInterfaceQueryLocalInterface;
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
                startBugreport(parcel.readInt(), parcel.readString(), parcel.readRawFileDescriptor(), parcel.readRawFileDescriptor(), parcel.readInt(), IDumpstateListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            cancelBugreport();
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IDumpstate {
            public static IDumpstate sDefaultImpl;
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

            @Override // android.os.IDumpstate
            public void startBugreport(int i, String str, FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, int i2, IDumpstateListener iDumpstateListener, boolean z) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeRawFileDescriptor(fileDescriptor);
                    parcelObtain.writeRawFileDescriptor(fileDescriptor2);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iDumpstateListener != null ? iDumpstateListener.asBinder() : null);
                    parcelObtain.writeInt(z ? 1 : 0);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startBugreport(i, str, fileDescriptor, fileDescriptor2, i2, iDumpstateListener, z);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IDumpstate
            public void cancelBugreport() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelBugreport();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDumpstate iDumpstate) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDumpstate == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDumpstate;
            return true;
        }

        public static IDumpstate getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
