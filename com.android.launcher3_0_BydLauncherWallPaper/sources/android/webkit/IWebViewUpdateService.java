package android.webkit;

import android.content.pm.PackageInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IWebViewUpdateService extends IInterface {

    public static class Default implements IWebViewUpdateService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.webkit.IWebViewUpdateService
        public String changeProviderAndSetting(String str) throws RemoteException {
            return null;
        }

        @Override // android.webkit.IWebViewUpdateService
        public void enableMultiProcess(boolean z) throws RemoteException {
        }

        @Override // android.webkit.IWebViewUpdateService
        public WebViewProviderInfo[] getAllWebViewPackages() throws RemoteException {
            return null;
        }

        @Override // android.webkit.IWebViewUpdateService
        public PackageInfo getCurrentWebViewPackage() throws RemoteException {
            return null;
        }

        @Override // android.webkit.IWebViewUpdateService
        public String getCurrentWebViewPackageName() throws RemoteException {
            return null;
        }

        @Override // android.webkit.IWebViewUpdateService
        public WebViewProviderInfo[] getValidWebViewPackages() throws RemoteException {
            return null;
        }

        @Override // android.webkit.IWebViewUpdateService
        public boolean isMultiProcessEnabled() throws RemoteException {
            return false;
        }

        @Override // android.webkit.IWebViewUpdateService
        public void notifyRelroCreationCompleted() throws RemoteException {
        }

        @Override // android.webkit.IWebViewUpdateService
        public WebViewProviderResponse waitForAndGetProvider() throws RemoteException {
            return null;
        }
    }

    String changeProviderAndSetting(String str) throws RemoteException;

    void enableMultiProcess(boolean z) throws RemoteException;

    WebViewProviderInfo[] getAllWebViewPackages() throws RemoteException;

    PackageInfo getCurrentWebViewPackage() throws RemoteException;

    String getCurrentWebViewPackageName() throws RemoteException;

    WebViewProviderInfo[] getValidWebViewPackages() throws RemoteException;

    boolean isMultiProcessEnabled() throws RemoteException;

    void notifyRelroCreationCompleted() throws RemoteException;

    WebViewProviderResponse waitForAndGetProvider() throws RemoteException;

    public static abstract class Stub extends Binder implements IWebViewUpdateService {
        private static final String DESCRIPTOR = "android.webkit.IWebViewUpdateService";
        static final int TRANSACTION_changeProviderAndSetting = 3;
        static final int TRANSACTION_enableMultiProcess = 9;
        static final int TRANSACTION_getAllWebViewPackages = 5;
        static final int TRANSACTION_getCurrentWebViewPackage = 7;
        static final int TRANSACTION_getCurrentWebViewPackageName = 6;
        static final int TRANSACTION_getValidWebViewPackages = 4;
        static final int TRANSACTION_isMultiProcessEnabled = 8;
        static final int TRANSACTION_notifyRelroCreationCompleted = 1;
        static final int TRANSACTION_waitForAndGetProvider = 2;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "notifyRelroCreationCompleted";
                case 2:
                    return "waitForAndGetProvider";
                case 3:
                    return "changeProviderAndSetting";
                case 4:
                    return "getValidWebViewPackages";
                case 5:
                    return "getAllWebViewPackages";
                case 6:
                    return "getCurrentWebViewPackageName";
                case 7:
                    return "getCurrentWebViewPackage";
                case 8:
                    return "isMultiProcessEnabled";
                case 9:
                    return "enableMultiProcess";
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

        public static IWebViewUpdateService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWebViewUpdateService)) {
                return (IWebViewUpdateService) iInterfaceQueryLocalInterface;
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
                    notifyRelroCreationCompleted();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    WebViewProviderResponse webViewProviderResponseWaitForAndGetProvider = waitForAndGetProvider();
                    parcel2.writeNoException();
                    if (webViewProviderResponseWaitForAndGetProvider != null) {
                        parcel2.writeInt(1);
                        webViewProviderResponseWaitForAndGetProvider.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strChangeProviderAndSetting = changeProviderAndSetting(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(strChangeProviderAndSetting);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    WebViewProviderInfo[] validWebViewPackages = getValidWebViewPackages();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(validWebViewPackages, 1);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    WebViewProviderInfo[] allWebViewPackages = getAllWebViewPackages();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(allWebViewPackages, 1);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currentWebViewPackageName = getCurrentWebViewPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(currentWebViewPackageName);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    PackageInfo currentWebViewPackage = getCurrentWebViewPackage();
                    parcel2.writeNoException();
                    if (currentWebViewPackage != null) {
                        parcel2.writeInt(1);
                        currentWebViewPackage.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsMultiProcessEnabled = isMultiProcessEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsMultiProcessEnabled ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableMultiProcess(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IWebViewUpdateService {
            public static IWebViewUpdateService sDefaultImpl;
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

            @Override // android.webkit.IWebViewUpdateService
            public void notifyRelroCreationCompleted() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRelroCreationCompleted();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.webkit.IWebViewUpdateService
            public WebViewProviderResponse waitForAndGetProvider() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().waitForAndGetProvider();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? WebViewProviderResponse.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.webkit.IWebViewUpdateService
            public String changeProviderAndSetting(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().changeProviderAndSetting(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.webkit.IWebViewUpdateService
            public WebViewProviderInfo[] getValidWebViewPackages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getValidWebViewPackages();
                    }
                    parcelObtain2.readException();
                    return (WebViewProviderInfo[]) parcelObtain2.createTypedArray(WebViewProviderInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.webkit.IWebViewUpdateService
            public WebViewProviderInfo[] getAllWebViewPackages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllWebViewPackages();
                    }
                    parcelObtain2.readException();
                    return (WebViewProviderInfo[]) parcelObtain2.createTypedArray(WebViewProviderInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.webkit.IWebViewUpdateService
            public String getCurrentWebViewPackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentWebViewPackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.webkit.IWebViewUpdateService
            public PackageInfo getCurrentWebViewPackage() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentWebViewPackage();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PackageInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.webkit.IWebViewUpdateService
            public boolean isMultiProcessEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMultiProcessEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.webkit.IWebViewUpdateService
            public void enableMultiProcess(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableMultiProcess(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWebViewUpdateService iWebViewUpdateService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWebViewUpdateService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWebViewUpdateService;
            return true;
        }

        public static IWebViewUpdateService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
