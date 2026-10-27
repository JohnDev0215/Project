package android.hardware.display;

import android.content.pm.ParceledListSlice;
import android.graphics.Point;
import android.hardware.display.IDisplayManagerCallback;
import android.hardware.display.IVirtualDisplayCallback;
import android.media.projection.IMediaProjection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.DisplayInfo;
import android.view.Surface;

/* JADX INFO: loaded from: classes2.dex */
public interface IDisplayManager extends IInterface {

    public static class Default implements IDisplayManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.display.IDisplayManager
        public void connectWifiDisplay(String str) throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public int createVirtualDisplay(VirtualDisplayConfig virtualDisplayConfig, IVirtualDisplayCallback iVirtualDisplayCallback, IMediaProjection iMediaProjection, String str) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.display.IDisplayManager
        public void disconnectWifiDisplay() throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void forgetWifiDisplay(String str) throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public ParceledListSlice getAmbientBrightnessStats() throws RemoteException {
            return null;
        }

        @Override // android.hardware.display.IDisplayManager
        public BrightnessConfiguration getBrightnessConfigurationForUser(int i) throws RemoteException {
            return null;
        }

        @Override // android.hardware.display.IDisplayManager
        public ParceledListSlice getBrightnessEvents(String str) throws RemoteException {
            return null;
        }

        @Override // android.hardware.display.IDisplayManager
        public BrightnessConfiguration getDefaultBrightnessConfiguration() throws RemoteException {
            return null;
        }

        @Override // android.hardware.display.IDisplayManager
        public int[] getDisplayIds() throws RemoteException {
            return null;
        }

        @Override // android.hardware.display.IDisplayManager
        public DisplayInfo getDisplayInfo(int i) throws RemoteException {
            return null;
        }

        @Override // android.hardware.display.IDisplayManager
        public Curve getMinimumBrightnessCurve() throws RemoteException {
            return null;
        }

        @Override // android.hardware.display.IDisplayManager
        public int getPreferredWideGamutColorSpaceId() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.display.IDisplayManager
        public Point getStableDisplaySize() throws RemoteException {
            return null;
        }

        @Override // android.hardware.display.IDisplayManager
        public WifiDisplayStatus getWifiDisplayStatus() throws RemoteException {
            return null;
        }

        @Override // android.hardware.display.IDisplayManager
        public boolean isMinimalPostProcessingRequested(int i) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IDisplayManager
        public boolean isUidPresentOnDisplay(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IDisplayManager
        public void pauseWifiDisplay() throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void registerCallback(IDisplayManagerCallback iDisplayManagerCallback) throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void releaseVirtualDisplay(IVirtualDisplayCallback iVirtualDisplayCallback) throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void renameWifiDisplay(String str, String str2) throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void requestColorMode(int i, int i2) throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void resizeVirtualDisplay(IVirtualDisplayCallback iVirtualDisplayCallback, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void resumeWifiDisplay() throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void setBrightnessConfigurationForUser(BrightnessConfiguration brightnessConfiguration, int i, String str) throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void setTemporaryAutoBrightnessAdjustment(float f) throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void setTemporaryBrightness(float f) throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void setVirtualDisplayState(IVirtualDisplayCallback iVirtualDisplayCallback, boolean z) throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void setVirtualDisplaySurface(IVirtualDisplayCallback iVirtualDisplayCallback, Surface surface) throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void startWifiDisplayScan() throws RemoteException {
        }

        @Override // android.hardware.display.IDisplayManager
        public void stopWifiDisplayScan() throws RemoteException {
        }
    }

    void connectWifiDisplay(String str) throws RemoteException;

    int createVirtualDisplay(VirtualDisplayConfig virtualDisplayConfig, IVirtualDisplayCallback iVirtualDisplayCallback, IMediaProjection iMediaProjection, String str) throws RemoteException;

    void disconnectWifiDisplay() throws RemoteException;

    void forgetWifiDisplay(String str) throws RemoteException;

    ParceledListSlice getAmbientBrightnessStats() throws RemoteException;

    BrightnessConfiguration getBrightnessConfigurationForUser(int i) throws RemoteException;

    ParceledListSlice getBrightnessEvents(String str) throws RemoteException;

    BrightnessConfiguration getDefaultBrightnessConfiguration() throws RemoteException;

    int[] getDisplayIds() throws RemoteException;

    DisplayInfo getDisplayInfo(int i) throws RemoteException;

    Curve getMinimumBrightnessCurve() throws RemoteException;

    int getPreferredWideGamutColorSpaceId() throws RemoteException;

    Point getStableDisplaySize() throws RemoteException;

    WifiDisplayStatus getWifiDisplayStatus() throws RemoteException;

    boolean isMinimalPostProcessingRequested(int i) throws RemoteException;

    boolean isUidPresentOnDisplay(int i, int i2) throws RemoteException;

    void pauseWifiDisplay() throws RemoteException;

    void registerCallback(IDisplayManagerCallback iDisplayManagerCallback) throws RemoteException;

    void releaseVirtualDisplay(IVirtualDisplayCallback iVirtualDisplayCallback) throws RemoteException;

    void renameWifiDisplay(String str, String str2) throws RemoteException;

    void requestColorMode(int i, int i2) throws RemoteException;

    void resizeVirtualDisplay(IVirtualDisplayCallback iVirtualDisplayCallback, int i, int i2, int i3) throws RemoteException;

    void resumeWifiDisplay() throws RemoteException;

    void setBrightnessConfigurationForUser(BrightnessConfiguration brightnessConfiguration, int i, String str) throws RemoteException;

    void setTemporaryAutoBrightnessAdjustment(float f) throws RemoteException;

    void setTemporaryBrightness(float f) throws RemoteException;

    void setVirtualDisplayState(IVirtualDisplayCallback iVirtualDisplayCallback, boolean z) throws RemoteException;

    void setVirtualDisplaySurface(IVirtualDisplayCallback iVirtualDisplayCallback, Surface surface) throws RemoteException;

    void startWifiDisplayScan() throws RemoteException;

    void stopWifiDisplayScan() throws RemoteException;

    public static abstract class Stub extends Binder implements IDisplayManager {
        private static final String DESCRIPTOR = "android.hardware.display.IDisplayManager";
        static final int TRANSACTION_connectWifiDisplay = 7;
        static final int TRANSACTION_createVirtualDisplay = 15;
        static final int TRANSACTION_disconnectWifiDisplay = 8;
        static final int TRANSACTION_forgetWifiDisplay = 10;
        static final int TRANSACTION_getAmbientBrightnessStats = 22;
        static final int TRANSACTION_getBrightnessConfigurationForUser = 24;
        static final int TRANSACTION_getBrightnessEvents = 21;
        static final int TRANSACTION_getDefaultBrightnessConfiguration = 25;
        static final int TRANSACTION_getDisplayIds = 2;
        static final int TRANSACTION_getDisplayInfo = 1;
        static final int TRANSACTION_getMinimumBrightnessCurve = 29;
        static final int TRANSACTION_getPreferredWideGamutColorSpaceId = 30;
        static final int TRANSACTION_getStableDisplaySize = 20;
        static final int TRANSACTION_getWifiDisplayStatus = 13;
        static final int TRANSACTION_isMinimalPostProcessingRequested = 26;
        static final int TRANSACTION_isUidPresentOnDisplay = 3;
        static final int TRANSACTION_pauseWifiDisplay = 11;
        static final int TRANSACTION_registerCallback = 4;
        static final int TRANSACTION_releaseVirtualDisplay = 18;
        static final int TRANSACTION_renameWifiDisplay = 9;
        static final int TRANSACTION_requestColorMode = 14;
        static final int TRANSACTION_resizeVirtualDisplay = 16;
        static final int TRANSACTION_resumeWifiDisplay = 12;
        static final int TRANSACTION_setBrightnessConfigurationForUser = 23;
        static final int TRANSACTION_setTemporaryAutoBrightnessAdjustment = 28;
        static final int TRANSACTION_setTemporaryBrightness = 27;
        static final int TRANSACTION_setVirtualDisplayState = 19;
        static final int TRANSACTION_setVirtualDisplaySurface = 17;
        static final int TRANSACTION_startWifiDisplayScan = 5;
        static final int TRANSACTION_stopWifiDisplayScan = 6;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getDisplayInfo";
                case 2:
                    return "getDisplayIds";
                case 3:
                    return "isUidPresentOnDisplay";
                case 4:
                    return "registerCallback";
                case 5:
                    return "startWifiDisplayScan";
                case 6:
                    return "stopWifiDisplayScan";
                case 7:
                    return "connectWifiDisplay";
                case 8:
                    return "disconnectWifiDisplay";
                case 9:
                    return "renameWifiDisplay";
                case 10:
                    return "forgetWifiDisplay";
                case 11:
                    return "pauseWifiDisplay";
                case 12:
                    return "resumeWifiDisplay";
                case 13:
                    return "getWifiDisplayStatus";
                case 14:
                    return "requestColorMode";
                case 15:
                    return "createVirtualDisplay";
                case 16:
                    return "resizeVirtualDisplay";
                case 17:
                    return "setVirtualDisplaySurface";
                case 18:
                    return "releaseVirtualDisplay";
                case 19:
                    return "setVirtualDisplayState";
                case 20:
                    return "getStableDisplaySize";
                case 21:
                    return "getBrightnessEvents";
                case 22:
                    return "getAmbientBrightnessStats";
                case 23:
                    return "setBrightnessConfigurationForUser";
                case 24:
                    return "getBrightnessConfigurationForUser";
                case 25:
                    return "getDefaultBrightnessConfiguration";
                case 26:
                    return "isMinimalPostProcessingRequested";
                case 27:
                    return "setTemporaryBrightness";
                case 28:
                    return "setTemporaryAutoBrightnessAdjustment";
                case 29:
                    return "getMinimumBrightnessCurve";
                case 30:
                    return "getPreferredWideGamutColorSpaceId";
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

        public static IDisplayManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDisplayManager)) {
                return (IDisplayManager) iInterfaceQueryLocalInterface;
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
                    DisplayInfo displayInfo = getDisplayInfo(parcel.readInt());
                    parcel2.writeNoException();
                    if (displayInfo != null) {
                        parcel2.writeInt(1);
                        displayInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] displayIds = getDisplayIds();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(displayIds);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsUidPresentOnDisplay = isUidPresentOnDisplay(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsUidPresentOnDisplay ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCallback(IDisplayManagerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    startWifiDisplayScan();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopWifiDisplayScan();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    connectWifiDisplay(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    disconnectWifiDisplay();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    renameWifiDisplay(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    forgetWifiDisplay(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    pauseWifiDisplay();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    resumeWifiDisplay();
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    WifiDisplayStatus wifiDisplayStatus = getWifiDisplayStatus();
                    parcel2.writeNoException();
                    if (wifiDisplayStatus != null) {
                        parcel2.writeInt(1);
                        wifiDisplayStatus.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestColorMode(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCreateVirtualDisplay = createVirtualDisplay(parcel.readInt() != 0 ? VirtualDisplayConfig.CREATOR.createFromParcel(parcel) : null, IVirtualDisplayCallback.Stub.asInterface(parcel.readStrongBinder()), IMediaProjection.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCreateVirtualDisplay);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    resizeVirtualDisplay(IVirtualDisplayCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVirtualDisplaySurface(IVirtualDisplayCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? Surface.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    releaseVirtualDisplay(IVirtualDisplayCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVirtualDisplayState(IVirtualDisplayCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    Point stableDisplaySize = getStableDisplaySize();
                    parcel2.writeNoException();
                    if (stableDisplaySize != null) {
                        parcel2.writeInt(1);
                        stableDisplaySize.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice brightnessEvents = getBrightnessEvents(parcel.readString());
                    parcel2.writeNoException();
                    if (brightnessEvents != null) {
                        parcel2.writeInt(1);
                        brightnessEvents.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice ambientBrightnessStats = getAmbientBrightnessStats();
                    parcel2.writeNoException();
                    if (ambientBrightnessStats != null) {
                        parcel2.writeInt(1);
                        ambientBrightnessStats.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBrightnessConfigurationForUser(parcel.readInt() != 0 ? BrightnessConfiguration.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    BrightnessConfiguration brightnessConfigurationForUser = getBrightnessConfigurationForUser(parcel.readInt());
                    parcel2.writeNoException();
                    if (brightnessConfigurationForUser != null) {
                        parcel2.writeInt(1);
                        brightnessConfigurationForUser.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    BrightnessConfiguration defaultBrightnessConfiguration = getDefaultBrightnessConfiguration();
                    parcel2.writeNoException();
                    if (defaultBrightnessConfiguration != null) {
                        parcel2.writeInt(1);
                        defaultBrightnessConfiguration.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsMinimalPostProcessingRequested = isMinimalPostProcessingRequested(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsMinimalPostProcessingRequested ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTemporaryBrightness(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTemporaryAutoBrightnessAdjustment(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    Curve minimumBrightnessCurve = getMinimumBrightnessCurve();
                    parcel2.writeNoException();
                    if (minimumBrightnessCurve != null) {
                        parcel2.writeInt(1);
                        minimumBrightnessCurve.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    int preferredWideGamutColorSpaceId = getPreferredWideGamutColorSpaceId();
                    parcel2.writeNoException();
                    parcel2.writeInt(preferredWideGamutColorSpaceId);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IDisplayManager {
            public static IDisplayManager sDefaultImpl;
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

            @Override // android.hardware.display.IDisplayManager
            public DisplayInfo getDisplayInfo(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisplayInfo(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? DisplayInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public int[] getDisplayIds() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisplayIds();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public boolean isUidPresentOnDisplay(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUidPresentOnDisplay(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void registerCallback(IDisplayManagerCallback iDisplayManagerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDisplayManagerCallback != null ? iDisplayManagerCallback.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallback(iDisplayManagerCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void startWifiDisplayScan() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startWifiDisplayScan();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void stopWifiDisplayScan() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopWifiDisplayScan();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void connectWifiDisplay(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().connectWifiDisplay(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void disconnectWifiDisplay() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disconnectWifiDisplay();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void renameWifiDisplay(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().renameWifiDisplay(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void forgetWifiDisplay(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().forgetWifiDisplay(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void pauseWifiDisplay() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pauseWifiDisplay();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void resumeWifiDisplay() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resumeWifiDisplay();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public WifiDisplayStatus getWifiDisplayStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWifiDisplayStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? WifiDisplayStatus.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void requestColorMode(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestColorMode(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public int createVirtualDisplay(VirtualDisplayConfig virtualDisplayConfig, IVirtualDisplayCallback iVirtualDisplayCallback, IMediaProjection iMediaProjection, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (virtualDisplayConfig != null) {
                        parcelObtain.writeInt(1);
                        virtualDisplayConfig.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iVirtualDisplayCallback != null ? iVirtualDisplayCallback.asBinder() : null);
                    parcelObtain.writeStrongBinder(iMediaProjection != null ? iMediaProjection.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createVirtualDisplay(virtualDisplayConfig, iVirtualDisplayCallback, iMediaProjection, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void resizeVirtualDisplay(IVirtualDisplayCallback iVirtualDisplayCallback, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iVirtualDisplayCallback != null ? iVirtualDisplayCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resizeVirtualDisplay(iVirtualDisplayCallback, i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void setVirtualDisplaySurface(IVirtualDisplayCallback iVirtualDisplayCallback, Surface surface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iVirtualDisplayCallback != null ? iVirtualDisplayCallback.asBinder() : null);
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVirtualDisplaySurface(iVirtualDisplayCallback, surface);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void releaseVirtualDisplay(IVirtualDisplayCallback iVirtualDisplayCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iVirtualDisplayCallback != null ? iVirtualDisplayCallback.asBinder() : null);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseVirtualDisplay(iVirtualDisplayCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void setVirtualDisplayState(IVirtualDisplayCallback iVirtualDisplayCallback, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iVirtualDisplayCallback != null ? iVirtualDisplayCallback.asBinder() : null);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVirtualDisplayState(iVirtualDisplayCallback, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public Point getStableDisplaySize() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStableDisplaySize();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Point.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public ParceledListSlice getBrightnessEvents(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBrightnessEvents(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public ParceledListSlice getAmbientBrightnessStats() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbientBrightnessStats();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void setBrightnessConfigurationForUser(BrightnessConfiguration brightnessConfiguration, int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (brightnessConfiguration != null) {
                        parcelObtain.writeInt(1);
                        brightnessConfiguration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBrightnessConfigurationForUser(brightnessConfiguration, i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public BrightnessConfiguration getBrightnessConfigurationForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBrightnessConfigurationForUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? BrightnessConfiguration.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public BrightnessConfiguration getDefaultBrightnessConfiguration() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultBrightnessConfiguration();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? BrightnessConfiguration.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public boolean isMinimalPostProcessingRequested(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMinimalPostProcessingRequested(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void setTemporaryBrightness(float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeFloat(f);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTemporaryBrightness(f);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public void setTemporaryAutoBrightnessAdjustment(float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeFloat(f);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTemporaryAutoBrightnessAdjustment(f);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public Curve getMinimumBrightnessCurve() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMinimumBrightnessCurve();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Curve.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.display.IDisplayManager
            public int getPreferredWideGamutColorSpaceId() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreferredWideGamutColorSpaceId();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDisplayManager iDisplayManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDisplayManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDisplayManager;
            return true;
        }

        public static IDisplayManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
