package android.media.midi;

import android.media.midi.IMidiDeviceServer;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IMidiDeviceOpenCallback extends IInterface {

    public static class Default implements IMidiDeviceOpenCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.midi.IMidiDeviceOpenCallback
        public void onDeviceOpened(IMidiDeviceServer iMidiDeviceServer, IBinder iBinder) throws RemoteException {
        }
    }

    void onDeviceOpened(IMidiDeviceServer iMidiDeviceServer, IBinder iBinder) throws RemoteException;

    public static abstract class Stub extends Binder implements IMidiDeviceOpenCallback {
        private static final String DESCRIPTOR = "android.media.midi.IMidiDeviceOpenCallback";
        static final int TRANSACTION_onDeviceOpened = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onDeviceOpened";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMidiDeviceOpenCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMidiDeviceOpenCallback)) {
                return (IMidiDeviceOpenCallback) iInterfaceQueryLocalInterface;
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
                onDeviceOpened(IMidiDeviceServer.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IMidiDeviceOpenCallback {
            public static IMidiDeviceOpenCallback sDefaultImpl;
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

            @Override // android.media.midi.IMidiDeviceOpenCallback
            public void onDeviceOpened(IMidiDeviceServer iMidiDeviceServer, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMidiDeviceServer != null ? iMidiDeviceServer.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDeviceOpened(iMidiDeviceServer, iBinder);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMidiDeviceOpenCallback iMidiDeviceOpenCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMidiDeviceOpenCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMidiDeviceOpenCallback;
            return true;
        }

        public static IMidiDeviceOpenCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
