package android.service.voice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.app.IVoiceActionCheckCallback;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IVoiceInteractionService extends IInterface {

    public static class Default implements IVoiceInteractionService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.voice.IVoiceInteractionService
        public void getActiveServiceSupportedActions(List<String> list, IVoiceActionCheckCallback iVoiceActionCheckCallback) throws RemoteException {
        }

        @Override // android.service.voice.IVoiceInteractionService
        public void launchVoiceAssistFromKeyguard() throws RemoteException {
        }

        @Override // android.service.voice.IVoiceInteractionService
        public void ready() throws RemoteException {
        }

        @Override // android.service.voice.IVoiceInteractionService
        public void shutdown() throws RemoteException {
        }

        @Override // android.service.voice.IVoiceInteractionService
        public void soundModelsChanged() throws RemoteException {
        }
    }

    void getActiveServiceSupportedActions(List<String> list, IVoiceActionCheckCallback iVoiceActionCheckCallback) throws RemoteException;

    void launchVoiceAssistFromKeyguard() throws RemoteException;

    void ready() throws RemoteException;

    void shutdown() throws RemoteException;

    void soundModelsChanged() throws RemoteException;

    public static abstract class Stub extends Binder implements IVoiceInteractionService {
        private static final String DESCRIPTOR = "android.service.voice.IVoiceInteractionService";
        static final int TRANSACTION_getActiveServiceSupportedActions = 5;
        static final int TRANSACTION_launchVoiceAssistFromKeyguard = 4;
        static final int TRANSACTION_ready = 1;
        static final int TRANSACTION_shutdown = 3;
        static final int TRANSACTION_soundModelsChanged = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "ready";
            }
            if (i == 2) {
                return "soundModelsChanged";
            }
            if (i == 3) {
                return "shutdown";
            }
            if (i == 4) {
                return "launchVoiceAssistFromKeyguard";
            }
            if (i != 5) {
                return null;
            }
            return "getActiveServiceSupportedActions";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVoiceInteractionService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IVoiceInteractionService)) {
                return (IVoiceInteractionService) iInterfaceQueryLocalInterface;
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
                ready();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                soundModelsChanged();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                shutdown();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                launchVoiceAssistFromKeyguard();
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                getActiveServiceSupportedActions(parcel.createStringArrayList(), IVoiceActionCheckCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IVoiceInteractionService {
            public static IVoiceInteractionService sDefaultImpl;
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

            @Override // android.service.voice.IVoiceInteractionService
            public void ready() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().ready();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.voice.IVoiceInteractionService
            public void soundModelsChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().soundModelsChanged();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.voice.IVoiceInteractionService
            public void shutdown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().shutdown();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.voice.IVoiceInteractionService
            public void launchVoiceAssistFromKeyguard() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().launchVoiceAssistFromKeyguard();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.voice.IVoiceInteractionService
            public void getActiveServiceSupportedActions(List<String> list, IVoiceActionCheckCallback iVoiceActionCheckCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeStrongBinder(iVoiceActionCheckCallback != null ? iVoiceActionCheckCallback.asBinder() : null);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getActiveServiceSupportedActions(list, iVoiceActionCheckCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVoiceInteractionService iVoiceInteractionService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iVoiceInteractionService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iVoiceInteractionService;
            return true;
        }

        public static IVoiceInteractionService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
