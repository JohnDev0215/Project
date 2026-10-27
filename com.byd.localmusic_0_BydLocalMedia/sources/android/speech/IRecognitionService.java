package android.speech;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.speech.IRecognitionListener;

/* JADX INFO: loaded from: classes3.dex */
public interface IRecognitionService extends IInterface {

    public static class Default implements IRecognitionService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.speech.IRecognitionService
        public void cancel(IRecognitionListener iRecognitionListener, String str, String str2) throws RemoteException {
        }

        @Override // android.speech.IRecognitionService
        public void startListening(Intent intent, IRecognitionListener iRecognitionListener, String str, String str2) throws RemoteException {
        }

        @Override // android.speech.IRecognitionService
        public void stopListening(IRecognitionListener iRecognitionListener, String str, String str2) throws RemoteException {
        }
    }

    void cancel(IRecognitionListener iRecognitionListener, String str, String str2) throws RemoteException;

    void startListening(Intent intent, IRecognitionListener iRecognitionListener, String str, String str2) throws RemoteException;

    void stopListening(IRecognitionListener iRecognitionListener, String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IRecognitionService {
        private static final String DESCRIPTOR = "android.speech.IRecognitionService";
        static final int TRANSACTION_cancel = 3;
        static final int TRANSACTION_startListening = 1;
        static final int TRANSACTION_stopListening = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "startListening";
            }
            if (i == 2) {
                return "stopListening";
            }
            if (i != 3) {
                return null;
            }
            return "cancel";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRecognitionService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRecognitionService)) {
                return (IRecognitionService) iInterfaceQueryLocalInterface;
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
                startListening(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, IRecognitionListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                stopListening(IRecognitionListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                cancel(IRecognitionListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IRecognitionService {
            public static IRecognitionService sDefaultImpl;
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

            @Override // android.speech.IRecognitionService
            public void startListening(Intent intent, IRecognitionListener iRecognitionListener, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iRecognitionListener != null ? iRecognitionListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startListening(intent, iRecognitionListener, str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.speech.IRecognitionService
            public void stopListening(IRecognitionListener iRecognitionListener, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRecognitionListener != null ? iRecognitionListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stopListening(iRecognitionListener, str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.speech.IRecognitionService
            public void cancel(IRecognitionListener iRecognitionListener, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRecognitionListener != null ? iRecognitionListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancel(iRecognitionListener, str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRecognitionService iRecognitionService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRecognitionService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRecognitionService;
            return true;
        }

        public static IRecognitionService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
