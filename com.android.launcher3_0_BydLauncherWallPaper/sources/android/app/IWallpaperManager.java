package android.app;

import android.app.IWallpaperManagerCallback;
import android.content.ComponentName;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IWallpaperManager extends IInterface {

    public static class Default implements IWallpaperManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.IWallpaperManager
        public void clearWallpaper(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public int getHeightHint(int i) throws RemoteException {
            return 0;
        }

        @Override // android.app.IWallpaperManager
        public String getName() throws RemoteException {
            return null;
        }

        @Override // android.app.IWallpaperManager
        public ParcelFileDescriptor getWallpaper(String str, IWallpaperManagerCallback iWallpaperManagerCallback, int i, Bundle bundle, int i2) throws RemoteException {
            return null;
        }

        @Override // android.app.IWallpaperManager
        public WallpaperColors getWallpaperColors(int i, int i2, int i3) throws RemoteException {
            return null;
        }

        @Override // android.app.IWallpaperManager
        public int getWallpaperIdForUser(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.app.IWallpaperManager
        public WallpaperInfo getWallpaperInfo(int i) throws RemoteException {
            return null;
        }

        @Override // android.app.IWallpaperManager
        public ParcelFileDescriptor getWallpaperWithFeature(String str, String str2, IWallpaperManagerCallback iWallpaperManagerCallback, int i, Bundle bundle, int i2) throws RemoteException {
            return null;
        }

        @Override // android.app.IWallpaperManager
        public int getWallpapers(String str, String str2, int i, int i2, ParcelFileDescriptor[] parcelFileDescriptorArr, String[] strArr, int i3, int i4, boolean z) throws RemoteException {
            return 0;
        }

        @Override // android.app.IWallpaperManager
        public int getWidthHint(int i) throws RemoteException {
            return 0;
        }

        @Override // android.app.IWallpaperManager
        public boolean hasNamedWallpaper(String str) throws RemoteException {
            return false;
        }

        @Override // android.app.IWallpaperManager
        public boolean isSetWallpaperAllowed(String str) throws RemoteException {
            return false;
        }

        @Override // android.app.IWallpaperManager
        public boolean isWallpaperBackupEligible(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // android.app.IWallpaperManager
        public boolean isWallpaperSupported(String str) throws RemoteException {
            return false;
        }

        @Override // android.app.IWallpaperManager
        public void registerWallpaperColorsCallback(IWallpaperManagerCallback iWallpaperManagerCallback, int i, int i2) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public int removeAllWallpaperFiles(int i, int i2, int i3, int i4, boolean z) throws RemoteException {
            return 0;
        }

        @Override // android.app.IWallpaperManager
        public int removeWallpaperFiles(String[] strArr, int i, int i2, int i3, int i4, boolean z) throws RemoteException {
            return 0;
        }

        @Override // android.app.IWallpaperManager
        public void setDimensionHints(int i, int i2, String str, int i3) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public void setDisplayPadding(Rect rect, String str, int i) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public void setInAmbientMode(boolean z, long j) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public boolean setLockWallpaperCallback(IWallpaperManagerCallback iWallpaperManagerCallback) throws RemoteException {
            return false;
        }

        @Override // android.app.IWallpaperManager
        public ParcelFileDescriptor setWallpaper(String str, String str2, Rect rect, boolean z, Bundle bundle, int i, IWallpaperManagerCallback iWallpaperManagerCallback, int i2) throws RemoteException {
            return null;
        }

        @Override // android.app.IWallpaperManager
        public void setWallpaperComponent(ComponentName componentName) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public void setWallpaperComponentChecked(ComponentName componentName, String str, int i) throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public int setWallpapers(String str, String str2, int i, int i2, String[] strArr, ParcelFileDescriptor[] parcelFileDescriptorArr, int i3, int i4, boolean z) throws RemoteException {
            return 0;
        }

        @Override // android.app.IWallpaperManager
        public void settingsRestored() throws RemoteException {
        }

        @Override // android.app.IWallpaperManager
        public void unregisterWallpaperColorsCallback(IWallpaperManagerCallback iWallpaperManagerCallback, int i, int i2) throws RemoteException {
        }
    }

    void clearWallpaper(String str, int i, int i2) throws RemoteException;

    int getHeightHint(int i) throws RemoteException;

    String getName() throws RemoteException;

    ParcelFileDescriptor getWallpaper(String str, IWallpaperManagerCallback iWallpaperManagerCallback, int i, Bundle bundle, int i2) throws RemoteException;

    WallpaperColors getWallpaperColors(int i, int i2, int i3) throws RemoteException;

    int getWallpaperIdForUser(int i, int i2) throws RemoteException;

    WallpaperInfo getWallpaperInfo(int i) throws RemoteException;

    ParcelFileDescriptor getWallpaperWithFeature(String str, String str2, IWallpaperManagerCallback iWallpaperManagerCallback, int i, Bundle bundle, int i2) throws RemoteException;

    int getWallpapers(String str, String str2, int i, int i2, ParcelFileDescriptor[] parcelFileDescriptorArr, String[] strArr, int i3, int i4, boolean z) throws RemoteException;

    int getWidthHint(int i) throws RemoteException;

    boolean hasNamedWallpaper(String str) throws RemoteException;

    boolean isSetWallpaperAllowed(String str) throws RemoteException;

    boolean isWallpaperBackupEligible(int i, int i2) throws RemoteException;

    boolean isWallpaperSupported(String str) throws RemoteException;

    void registerWallpaperColorsCallback(IWallpaperManagerCallback iWallpaperManagerCallback, int i, int i2) throws RemoteException;

    int removeAllWallpaperFiles(int i, int i2, int i3, int i4, boolean z) throws RemoteException;

    int removeWallpaperFiles(String[] strArr, int i, int i2, int i3, int i4, boolean z) throws RemoteException;

    void setDimensionHints(int i, int i2, String str, int i3) throws RemoteException;

    void setDisplayPadding(Rect rect, String str, int i) throws RemoteException;

    void setInAmbientMode(boolean z, long j) throws RemoteException;

    boolean setLockWallpaperCallback(IWallpaperManagerCallback iWallpaperManagerCallback) throws RemoteException;

    ParcelFileDescriptor setWallpaper(String str, String str2, Rect rect, boolean z, Bundle bundle, int i, IWallpaperManagerCallback iWallpaperManagerCallback, int i2) throws RemoteException;

    void setWallpaperComponent(ComponentName componentName) throws RemoteException;

    void setWallpaperComponentChecked(ComponentName componentName, String str, int i) throws RemoteException;

    int setWallpapers(String str, String str2, int i, int i2, String[] strArr, ParcelFileDescriptor[] parcelFileDescriptorArr, int i3, int i4, boolean z) throws RemoteException;

    void settingsRestored() throws RemoteException;

    void unregisterWallpaperColorsCallback(IWallpaperManagerCallback iWallpaperManagerCallback, int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IWallpaperManager {
        private static final String DESCRIPTOR = "android.app.IWallpaperManager";
        static final int TRANSACTION_clearWallpaper = 8;
        static final int TRANSACTION_getHeightHint = 12;
        static final int TRANSACTION_getName = 14;
        static final int TRANSACTION_getWallpaper = 4;
        static final int TRANSACTION_getWallpaperColors = 20;
        static final int TRANSACTION_getWallpaperIdForUser = 6;
        static final int TRANSACTION_getWallpaperInfo = 7;
        static final int TRANSACTION_getWallpaperWithFeature = 5;
        static final int TRANSACTION_getWallpapers = 25;
        static final int TRANSACTION_getWidthHint = 11;
        static final int TRANSACTION_hasNamedWallpaper = 9;
        static final int TRANSACTION_isSetWallpaperAllowed = 17;
        static final int TRANSACTION_isWallpaperBackupEligible = 18;
        static final int TRANSACTION_isWallpaperSupported = 16;
        static final int TRANSACTION_registerWallpaperColorsCallback = 21;
        static final int TRANSACTION_removeAllWallpaperFiles = 26;
        static final int TRANSACTION_removeWallpaperFiles = 27;
        static final int TRANSACTION_setDimensionHints = 10;
        static final int TRANSACTION_setDisplayPadding = 13;
        static final int TRANSACTION_setInAmbientMode = 23;
        static final int TRANSACTION_setLockWallpaperCallback = 19;
        static final int TRANSACTION_setWallpaper = 1;
        static final int TRANSACTION_setWallpaperComponent = 3;
        static final int TRANSACTION_setWallpaperComponentChecked = 2;
        static final int TRANSACTION_setWallpapers = 24;
        static final int TRANSACTION_settingsRestored = 15;
        static final int TRANSACTION_unregisterWallpaperColorsCallback = 22;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "setWallpaper";
                case 2:
                    return "setWallpaperComponentChecked";
                case 3:
                    return "setWallpaperComponent";
                case 4:
                    return "getWallpaper";
                case 5:
                    return "getWallpaperWithFeature";
                case 6:
                    return "getWallpaperIdForUser";
                case 7:
                    return "getWallpaperInfo";
                case 8:
                    return "clearWallpaper";
                case 9:
                    return "hasNamedWallpaper";
                case 10:
                    return "setDimensionHints";
                case 11:
                    return "getWidthHint";
                case 12:
                    return "getHeightHint";
                case 13:
                    return "setDisplayPadding";
                case 14:
                    return "getName";
                case 15:
                    return "settingsRestored";
                case 16:
                    return "isWallpaperSupported";
                case 17:
                    return "isSetWallpaperAllowed";
                case 18:
                    return "isWallpaperBackupEligible";
                case 19:
                    return "setLockWallpaperCallback";
                case 20:
                    return "getWallpaperColors";
                case 21:
                    return "registerWallpaperColorsCallback";
                case 22:
                    return "unregisterWallpaperColorsCallback";
                case 23:
                    return "setInAmbientMode";
                case 24:
                    return "setWallpapers";
                case 25:
                    return "getWallpapers";
                case 26:
                    return "removeAllWallpaperFiles";
                case 27:
                    return "removeWallpaperFiles";
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

        public static IWallpaperManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWallpaperManager)) {
                return (IWallpaperManager) iInterfaceQueryLocalInterface;
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
                    String string = parcel.readString();
                    String string2 = parcel.readString();
                    Rect rectCreateFromParcel = parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null;
                    boolean z = parcel.readInt() != 0;
                    Bundle bundle = new Bundle();
                    ParcelFileDescriptor wallpaper = setWallpaper(string, string2, rectCreateFromParcel, z, bundle, parcel.readInt(), IWallpaperManagerCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (wallpaper != null) {
                        parcel2.writeInt(1);
                        wallpaper.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    parcel2.writeInt(1);
                    bundle.writeToParcel(parcel2, 1);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    setWallpaperComponentChecked(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setWallpaperComponent(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string3 = parcel.readString();
                    IWallpaperManagerCallback iWallpaperManagerCallbackAsInterface = IWallpaperManagerCallback.Stub.asInterface(parcel.readStrongBinder());
                    int i3 = parcel.readInt();
                    Bundle bundle2 = new Bundle();
                    ParcelFileDescriptor wallpaper2 = getWallpaper(string3, iWallpaperManagerCallbackAsInterface, i3, bundle2, parcel.readInt());
                    parcel2.writeNoException();
                    if (wallpaper2 != null) {
                        parcel2.writeInt(1);
                        wallpaper2.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    parcel2.writeInt(1);
                    bundle2.writeToParcel(parcel2, 1);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string4 = parcel.readString();
                    String string5 = parcel.readString();
                    IWallpaperManagerCallback iWallpaperManagerCallbackAsInterface2 = IWallpaperManagerCallback.Stub.asInterface(parcel.readStrongBinder());
                    int i4 = parcel.readInt();
                    Bundle bundle3 = new Bundle();
                    ParcelFileDescriptor wallpaperWithFeature = getWallpaperWithFeature(string4, string5, iWallpaperManagerCallbackAsInterface2, i4, bundle3, parcel.readInt());
                    parcel2.writeNoException();
                    if (wallpaperWithFeature != null) {
                        parcel2.writeInt(1);
                        wallpaperWithFeature.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    parcel2.writeInt(1);
                    bundle3.writeToParcel(parcel2, 1);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int wallpaperIdForUser = getWallpaperIdForUser(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(wallpaperIdForUser);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    WallpaperInfo wallpaperInfo = getWallpaperInfo(parcel.readInt());
                    parcel2.writeNoException();
                    if (wallpaperInfo != null) {
                        parcel2.writeInt(1);
                        wallpaperInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearWallpaper(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasNamedWallpaper = hasNamedWallpaper(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasNamedWallpaper ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDimensionHints(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int widthHint = getWidthHint(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(widthHint);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int heightHint = getHeightHint(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(heightHint);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDisplayPadding(parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    String name = getName();
                    parcel2.writeNoException();
                    parcel2.writeString(name);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    settingsRestored();
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsWallpaperSupported = isWallpaperSupported(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsWallpaperSupported ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSetWallpaperAllowed = isSetWallpaperAllowed(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSetWallpaperAllowed ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsWallpaperBackupEligible = isWallpaperBackupEligible(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsWallpaperBackupEligible ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean lockWallpaperCallback = setLockWallpaperCallback(IWallpaperManagerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(lockWallpaperCallback ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    WallpaperColors wallpaperColors = getWallpaperColors(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (wallpaperColors != null) {
                        parcel2.writeInt(1);
                        wallpaperColors.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerWallpaperColorsCallback(IWallpaperManagerCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterWallpaperColorsCallback(IWallpaperManagerCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInAmbientMode(parcel.readInt() != 0, parcel.readLong());
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string6 = parcel.readString();
                    String string7 = parcel.readString();
                    int i5 = parcel.readInt();
                    int i6 = parcel.readInt();
                    String[] strArrCreateStringArray = parcel.createStringArray();
                    ParcelFileDescriptor[] parcelFileDescriptorArr = (ParcelFileDescriptor[]) parcel.createTypedArray(ParcelFileDescriptor.CREATOR);
                    int wallpapers = setWallpapers(string6, string7, i5, i6, strArrCreateStringArray, parcelFileDescriptorArr, parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(wallpapers);
                    parcel2.writeTypedArray(parcelFileDescriptorArr, 1);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string8 = parcel.readString();
                    String string9 = parcel.readString();
                    int i7 = parcel.readInt();
                    int i8 = parcel.readInt();
                    ParcelFileDescriptor[] parcelFileDescriptorArr2 = (ParcelFileDescriptor[]) parcel.createTypedArray(ParcelFileDescriptor.CREATOR);
                    String[] strArrCreateStringArray2 = parcel.createStringArray();
                    int wallpapers2 = getWallpapers(string8, string9, i7, i8, parcelFileDescriptorArr2, strArrCreateStringArray2, parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(wallpapers2);
                    parcel2.writeTypedArray(parcelFileDescriptorArr2, 1);
                    parcel2.writeStringArray(strArrCreateStringArray2);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRemoveAllWallpaperFiles = removeAllWallpaperFiles(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(iRemoveAllWallpaperFiles);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRemoveWallpaperFiles = removeWallpaperFiles(parcel.createStringArray(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(iRemoveWallpaperFiles);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IWallpaperManager {
            public static IWallpaperManager sDefaultImpl;
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

            @Override // android.app.IWallpaperManager
            public ParcelFileDescriptor setWallpaper(String str, String str2, Rect rect, boolean z, Bundle bundle, int i, IWallpaperManagerCallback iWallpaperManagerCallback, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iWallpaperManagerCallback != null ? iWallpaperManagerCallback.asBinder() : null);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setWallpaper(str, str2, rect, z, bundle, i, iWallpaperManagerCallback, i2);
                    }
                    parcelObtain2.readException();
                    ParcelFileDescriptor parcelFileDescriptorCreateFromParcel = parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                    if (parcelObtain2.readInt() != 0) {
                        bundle.readFromParcel(parcelObtain2);
                    }
                    return parcelFileDescriptorCreateFromParcel;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void setWallpaperComponentChecked(ComponentName componentName, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWallpaperComponentChecked(componentName, str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void setWallpaperComponent(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWallpaperComponent(componentName);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public ParcelFileDescriptor getWallpaper(String str, IWallpaperManagerCallback iWallpaperManagerCallback, int i, Bundle bundle, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iWallpaperManagerCallback != null ? iWallpaperManagerCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWallpaper(str, iWallpaperManagerCallback, i, bundle, i2);
                    }
                    parcelObtain2.readException();
                    ParcelFileDescriptor parcelFileDescriptorCreateFromParcel = parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                    if (parcelObtain2.readInt() != 0) {
                        bundle.readFromParcel(parcelObtain2);
                    }
                    return parcelFileDescriptorCreateFromParcel;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public ParcelFileDescriptor getWallpaperWithFeature(String str, String str2, IWallpaperManagerCallback iWallpaperManagerCallback, int i, Bundle bundle, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iWallpaperManagerCallback != null ? iWallpaperManagerCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWallpaperWithFeature(str, str2, iWallpaperManagerCallback, i, bundle, i2);
                    }
                    parcelObtain2.readException();
                    ParcelFileDescriptor parcelFileDescriptorCreateFromParcel = parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                    if (parcelObtain2.readInt() != 0) {
                        bundle.readFromParcel(parcelObtain2);
                    }
                    return parcelFileDescriptorCreateFromParcel;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public int getWallpaperIdForUser(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWallpaperIdForUser(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public WallpaperInfo getWallpaperInfo(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWallpaperInfo(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? WallpaperInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void clearWallpaper(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearWallpaper(str, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public boolean hasNamedWallpaper(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasNamedWallpaper(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void setDimensionHints(int i, int i2, String str, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDimensionHints(i, i2, str, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public int getWidthHint(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWidthHint(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public int getHeightHint(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHeightHint(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void setDisplayPadding(Rect rect, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDisplayPadding(rect, str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public String getName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void settingsRestored() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().settingsRestored();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public boolean isWallpaperSupported(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWallpaperSupported(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public boolean isSetWallpaperAllowed(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSetWallpaperAllowed(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public boolean isWallpaperBackupEligible(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWallpaperBackupEligible(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public boolean setLockWallpaperCallback(IWallpaperManagerCallback iWallpaperManagerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWallpaperManagerCallback != null ? iWallpaperManagerCallback.asBinder() : null);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLockWallpaperCallback(iWallpaperManagerCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public WallpaperColors getWallpaperColors(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWallpaperColors(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? WallpaperColors.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void registerWallpaperColorsCallback(IWallpaperManagerCallback iWallpaperManagerCallback, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWallpaperManagerCallback != null ? iWallpaperManagerCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerWallpaperColorsCallback(iWallpaperManagerCallback, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void unregisterWallpaperColorsCallback(IWallpaperManagerCallback iWallpaperManagerCallback, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWallpaperManagerCallback != null ? iWallpaperManagerCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterWallpaperColorsCallback(iWallpaperManagerCallback, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public void setInAmbientMode(boolean z, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(23, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setInAmbientMode(z, j);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public int setWallpapers(String str, String str2, int i, int i2, String[] strArr, ParcelFileDescriptor[] parcelFileDescriptorArr, int i3, int i4, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeTypedArray(parcelFileDescriptorArr, 0);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setWallpapers(str, str2, i, i2, strArr, parcelFileDescriptorArr, i3, i4, z);
                    }
                    parcelObtain2.readException();
                    int i5 = parcelObtain2.readInt();
                    parcelObtain2.readTypedArray(parcelFileDescriptorArr, ParcelFileDescriptor.CREATOR);
                    return i5;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public int getWallpapers(String str, String str2, int i, int i2, ParcelFileDescriptor[] parcelFileDescriptorArr, String[] strArr, int i3, int i4, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeTypedArray(parcelFileDescriptorArr, 0);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWallpapers(str, str2, i, i2, parcelFileDescriptorArr, strArr, i3, i4, z);
                    }
                    parcelObtain2.readException();
                    int i5 = parcelObtain2.readInt();
                    parcelObtain2.readTypedArray(parcelFileDescriptorArr, ParcelFileDescriptor.CREATOR);
                    parcelObtain2.readStringArray(strArr);
                    return i5;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public int removeAllWallpaperFiles(int i, int i2, int i3, int i4, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeAllWallpaperFiles(i, i2, i3, i4, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWallpaperManager
            public int removeWallpaperFiles(String[] strArr, int i, int i2, int i3, int i4, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeWallpaperFiles(strArr, i, i2, i3, i4, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWallpaperManager iWallpaperManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWallpaperManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWallpaperManager;
            return true;
        }

        public static IWallpaperManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
