package android.nfc;

import android.app.PendingIntent;
import android.content.IntentFilter;
import android.nfc.IAppCallback;
import android.nfc.INfcAdapterExtras;
import android.nfc.INfcCardEmulation;
import android.nfc.INfcDta;
import android.nfc.INfcFCardEmulation;
import android.nfc.INfcTag;
import android.nfc.INfcUnlockHandler;
import android.nfc.ITagRemovedCallback;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface INfcAdapter extends IInterface {

    public static class Default implements INfcAdapter {
        @Override // android.nfc.INfcAdapter
        public void addNfcUnlockHandler(INfcUnlockHandler iNfcUnlockHandler, int[] iArr) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.nfc.INfcAdapter
        public boolean deviceSupportsNfcSecure() throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcAdapter
        public boolean disable(boolean z) throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcAdapter
        public boolean disableNdefPush() throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcAdapter
        public void dispatch(Tag tag) throws RemoteException {
        }

        @Override // android.nfc.INfcAdapter
        public boolean enable() throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcAdapter
        public boolean enableNdefPush() throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcAdapter
        public INfcAdapterExtras getNfcAdapterExtrasInterface(String str) throws RemoteException {
            return null;
        }

        @Override // android.nfc.INfcAdapter
        public INfcCardEmulation getNfcCardEmulationInterface() throws RemoteException {
            return null;
        }

        @Override // android.nfc.INfcAdapter
        public INfcDta getNfcDtaInterface(String str) throws RemoteException {
            return null;
        }

        @Override // android.nfc.INfcAdapter
        public INfcFCardEmulation getNfcFCardEmulationInterface() throws RemoteException {
            return null;
        }

        @Override // android.nfc.INfcAdapter
        public INfcTag getNfcTagInterface() throws RemoteException {
            return null;
        }

        @Override // android.nfc.INfcAdapter
        public int getState() throws RemoteException {
            return 0;
        }

        @Override // android.nfc.INfcAdapter
        public boolean ignore(int i, int i2, ITagRemovedCallback iTagRemovedCallback) throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcAdapter
        public void invokeBeam() throws RemoteException {
        }

        @Override // android.nfc.INfcAdapter
        public void invokeBeamInternal(BeamShareData beamShareData) throws RemoteException {
        }

        @Override // android.nfc.INfcAdapter
        public boolean isNdefPushEnabled() throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcAdapter
        public boolean isNfcSecureEnabled() throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcAdapter
        public void pausePolling(int i) throws RemoteException {
        }

        @Override // android.nfc.INfcAdapter
        public void removeNfcUnlockHandler(INfcUnlockHandler iNfcUnlockHandler) throws RemoteException {
        }

        @Override // android.nfc.INfcAdapter
        public void resumePolling() throws RemoteException {
        }

        @Override // android.nfc.INfcAdapter
        public void setAppCallback(IAppCallback iAppCallback) throws RemoteException {
        }

        @Override // android.nfc.INfcAdapter
        public void setForegroundDispatch(PendingIntent pendingIntent, IntentFilter[] intentFilterArr, TechListParcel techListParcel) throws RemoteException {
        }

        @Override // android.nfc.INfcAdapter
        public boolean setNfcSecure(boolean z) throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcAdapter
        public void setP2pModes(int i, int i2) throws RemoteException {
        }

        @Override // android.nfc.INfcAdapter
        public void setReaderMode(IBinder iBinder, IAppCallback iAppCallback, int i, Bundle bundle) throws RemoteException {
        }

        @Override // android.nfc.INfcAdapter
        public void verifyNfcPermission() throws RemoteException {
        }
    }

    void addNfcUnlockHandler(INfcUnlockHandler iNfcUnlockHandler, int[] iArr) throws RemoteException;

    boolean deviceSupportsNfcSecure() throws RemoteException;

    boolean disable(boolean z) throws RemoteException;

    boolean disableNdefPush() throws RemoteException;

    void dispatch(Tag tag) throws RemoteException;

    boolean enable() throws RemoteException;

    boolean enableNdefPush() throws RemoteException;

    INfcAdapterExtras getNfcAdapterExtrasInterface(String str) throws RemoteException;

    INfcCardEmulation getNfcCardEmulationInterface() throws RemoteException;

    INfcDta getNfcDtaInterface(String str) throws RemoteException;

    INfcFCardEmulation getNfcFCardEmulationInterface() throws RemoteException;

    INfcTag getNfcTagInterface() throws RemoteException;

    int getState() throws RemoteException;

    boolean ignore(int i, int i2, ITagRemovedCallback iTagRemovedCallback) throws RemoteException;

    void invokeBeam() throws RemoteException;

    void invokeBeamInternal(BeamShareData beamShareData) throws RemoteException;

    boolean isNdefPushEnabled() throws RemoteException;

    boolean isNfcSecureEnabled() throws RemoteException;

    void pausePolling(int i) throws RemoteException;

    void removeNfcUnlockHandler(INfcUnlockHandler iNfcUnlockHandler) throws RemoteException;

    void resumePolling() throws RemoteException;

    void setAppCallback(IAppCallback iAppCallback) throws RemoteException;

    void setForegroundDispatch(PendingIntent pendingIntent, IntentFilter[] intentFilterArr, TechListParcel techListParcel) throws RemoteException;

    boolean setNfcSecure(boolean z) throws RemoteException;

    void setP2pModes(int i, int i2) throws RemoteException;

    void setReaderMode(IBinder iBinder, IAppCallback iAppCallback, int i, Bundle bundle) throws RemoteException;

    void verifyNfcPermission() throws RemoteException;

    public static abstract class Stub extends Binder implements INfcAdapter {
        private static final String DESCRIPTOR = "android.nfc.INfcAdapter";
        static final int TRANSACTION_addNfcUnlockHandler = 22;
        static final int TRANSACTION_deviceSupportsNfcSecure = 26;
        static final int TRANSACTION_disable = 7;
        static final int TRANSACTION_disableNdefPush = 10;
        static final int TRANSACTION_dispatch = 19;
        static final int TRANSACTION_enable = 8;
        static final int TRANSACTION_enableNdefPush = 9;
        static final int TRANSACTION_getNfcAdapterExtrasInterface = 4;
        static final int TRANSACTION_getNfcCardEmulationInterface = 2;
        static final int TRANSACTION_getNfcDtaInterface = 5;
        static final int TRANSACTION_getNfcFCardEmulationInterface = 3;
        static final int TRANSACTION_getNfcTagInterface = 1;
        static final int TRANSACTION_getState = 6;
        static final int TRANSACTION_ignore = 18;
        static final int TRANSACTION_invokeBeam = 16;
        static final int TRANSACTION_invokeBeamInternal = 17;
        static final int TRANSACTION_isNdefPushEnabled = 11;
        static final int TRANSACTION_isNfcSecureEnabled = 25;
        static final int TRANSACTION_pausePolling = 12;
        static final int TRANSACTION_removeNfcUnlockHandler = 23;
        static final int TRANSACTION_resumePolling = 13;
        static final int TRANSACTION_setAppCallback = 15;
        static final int TRANSACTION_setForegroundDispatch = 14;
        static final int TRANSACTION_setNfcSecure = 27;
        static final int TRANSACTION_setP2pModes = 21;
        static final int TRANSACTION_setReaderMode = 20;
        static final int TRANSACTION_verifyNfcPermission = 24;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getNfcTagInterface";
                case 2:
                    return "getNfcCardEmulationInterface";
                case 3:
                    return "getNfcFCardEmulationInterface";
                case 4:
                    return "getNfcAdapterExtrasInterface";
                case 5:
                    return "getNfcDtaInterface";
                case 6:
                    return "getState";
                case 7:
                    return "disable";
                case 8:
                    return "enable";
                case 9:
                    return "enableNdefPush";
                case 10:
                    return "disableNdefPush";
                case 11:
                    return "isNdefPushEnabled";
                case 12:
                    return "pausePolling";
                case 13:
                    return "resumePolling";
                case 14:
                    return "setForegroundDispatch";
                case 15:
                    return "setAppCallback";
                case 16:
                    return "invokeBeam";
                case 17:
                    return "invokeBeamInternal";
                case 18:
                    return "ignore";
                case 19:
                    return "dispatch";
                case 20:
                    return "setReaderMode";
                case 21:
                    return "setP2pModes";
                case 22:
                    return "addNfcUnlockHandler";
                case 23:
                    return "removeNfcUnlockHandler";
                case 24:
                    return "verifyNfcPermission";
                case 25:
                    return "isNfcSecureEnabled";
                case 26:
                    return "deviceSupportsNfcSecure";
                case 27:
                    return "setNfcSecure";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfcAdapter asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INfcAdapter)) {
                return (INfcAdapter) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    INfcTag nfcTagInterface = getNfcTagInterface();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(nfcTagInterface != null ? nfcTagInterface.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    INfcCardEmulation nfcCardEmulationInterface = getNfcCardEmulationInterface();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(nfcCardEmulationInterface != null ? nfcCardEmulationInterface.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    INfcFCardEmulation nfcFCardEmulationInterface = getNfcFCardEmulationInterface();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(nfcFCardEmulationInterface != null ? nfcFCardEmulationInterface.asBinder() : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    INfcAdapterExtras nfcAdapterExtrasInterface = getNfcAdapterExtrasInterface(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(nfcAdapterExtrasInterface != null ? nfcAdapterExtrasInterface.asBinder() : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    INfcDta nfcDtaInterface = getNfcDtaInterface(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(nfcDtaInterface != null ? nfcDtaInterface.asBinder() : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int state = getState();
                    parcel2.writeNoException();
                    parcel2.writeInt(state);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisable = disable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisable ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnable = enable();
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnable ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnableNdefPush = enableNdefPush();
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnableNdefPush ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisableNdefPush = disableNdefPush();
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisableNdefPush ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsNdefPushEnabled = isNdefPushEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsNdefPushEnabled ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    pausePolling(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    resumePolling();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setForegroundDispatch(parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, (IntentFilter[]) parcel.createTypedArray(IntentFilter.CREATOR), parcel.readInt() != 0 ? TechListParcel.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAppCallback(IAppCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    invokeBeam();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    invokeBeamInternal(parcel.readInt() != 0 ? BeamShareData.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIgnore = ignore(parcel.readInt(), parcel.readInt(), ITagRemovedCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zIgnore ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    dispatch(parcel.readInt() != 0 ? Tag.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    setReaderMode(parcel.readStrongBinder(), IAppCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    setP2pModes(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    addNfcUnlockHandler(INfcUnlockHandler.Stub.asInterface(parcel.readStrongBinder()), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeNfcUnlockHandler(INfcUnlockHandler.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    verifyNfcPermission();
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsNfcSecureEnabled = isNfcSecureEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsNfcSecureEnabled ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeviceSupportsNfcSecure = deviceSupportsNfcSecure();
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeviceSupportsNfcSecure ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean nfcSecure = setNfcSecure(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(nfcSecure ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements INfcAdapter {
            public static INfcAdapter sDefaultImpl;
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

            @Override // android.nfc.INfcAdapter
            public INfcTag getNfcTagInterface() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNfcTagInterface();
                    }
                    parcelObtain2.readException();
                    return INfcTag.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public INfcCardEmulation getNfcCardEmulationInterface() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNfcCardEmulationInterface();
                    }
                    parcelObtain2.readException();
                    return INfcCardEmulation.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public INfcFCardEmulation getNfcFCardEmulationInterface() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNfcFCardEmulationInterface();
                    }
                    parcelObtain2.readException();
                    return INfcFCardEmulation.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public INfcAdapterExtras getNfcAdapterExtrasInterface(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNfcAdapterExtrasInterface(str);
                    }
                    parcelObtain2.readException();
                    return INfcAdapterExtras.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public INfcDta getNfcDtaInterface(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNfcDtaInterface(str);
                    }
                    parcelObtain2.readException();
                    return INfcDta.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public int getState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public boolean disable(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disable(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public boolean enable() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enable();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public boolean enableNdefPush() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableNdefPush();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public boolean disableNdefPush() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disableNdefPush();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public boolean isNdefPushEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNdefPushEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public void pausePolling(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pausePolling(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public void resumePolling() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resumePolling();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public void setForegroundDispatch(PendingIntent pendingIntent, IntentFilter[] intentFilterArr, TechListParcel techListParcel) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedArray(intentFilterArr, 0);
                    if (techListParcel != null) {
                        parcelObtain.writeInt(1);
                        techListParcel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setForegroundDispatch(pendingIntent, intentFilterArr, techListParcel);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public void setAppCallback(IAppCallback iAppCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAppCallback != null ? iAppCallback.asBinder() : null);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAppCallback(iAppCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public void invokeBeam() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().invokeBeam();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public void invokeBeamInternal(BeamShareData beamShareData) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (beamShareData != null) {
                        parcelObtain.writeInt(1);
                        beamShareData.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(17, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().invokeBeamInternal(beamShareData);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public boolean ignore(int i, int i2, ITagRemovedCallback iTagRemovedCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iTagRemovedCallback != null ? iTagRemovedCallback.asBinder() : null);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().ignore(i, i2, iTagRemovedCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public void dispatch(Tag tag) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (tag != null) {
                        parcelObtain.writeInt(1);
                        tag.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dispatch(tag);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public void setReaderMode(IBinder iBinder, IAppCallback iAppCallback, int i, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeStrongBinder(iAppCallback != null ? iAppCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setReaderMode(iBinder, iAppCallback, i, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public void setP2pModes(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setP2pModes(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public void addNfcUnlockHandler(INfcUnlockHandler iNfcUnlockHandler, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNfcUnlockHandler != null ? iNfcUnlockHandler.asBinder() : null);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addNfcUnlockHandler(iNfcUnlockHandler, iArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public void removeNfcUnlockHandler(INfcUnlockHandler iNfcUnlockHandler) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNfcUnlockHandler != null ? iNfcUnlockHandler.asBinder() : null);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeNfcUnlockHandler(iNfcUnlockHandler);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public void verifyNfcPermission() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().verifyNfcPermission();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public boolean isNfcSecureEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNfcSecureEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public boolean deviceSupportsNfcSecure() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deviceSupportsNfcSecure();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.nfc.INfcAdapter
            public boolean setNfcSecure(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNfcSecure(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INfcAdapter iNfcAdapter) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iNfcAdapter == null) {
                return false;
            }
            Proxy.sDefaultImpl = iNfcAdapter;
            return true;
        }

        public static INfcAdapter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
