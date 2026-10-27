package android.service.euicc;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.euicc.IDeleteSubscriptionCallback;
import android.service.euicc.IDownloadSubscriptionCallback;
import android.service.euicc.IEraseSubscriptionsCallback;
import android.service.euicc.IEuiccServiceDumpResultCallback;
import android.service.euicc.IGetDefaultDownloadableSubscriptionListCallback;
import android.service.euicc.IGetDownloadableSubscriptionMetadataCallback;
import android.service.euicc.IGetEidCallback;
import android.service.euicc.IGetEuiccInfoCallback;
import android.service.euicc.IGetEuiccProfileInfoListCallback;
import android.service.euicc.IGetOtaStatusCallback;
import android.service.euicc.IOtaStatusChangedCallback;
import android.service.euicc.IRetainSubscriptionsForFactoryResetCallback;
import android.service.euicc.ISwitchToSubscriptionCallback;
import android.service.euicc.IUpdateSubscriptionNicknameCallback;
import android.telephony.euicc.DownloadableSubscription;

/* JADX INFO: loaded from: classes3.dex */
public interface IEuiccService extends IInterface {

    public static class Default implements IEuiccService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.euicc.IEuiccService
        public void deleteSubscription(int i, String str, IDeleteSubscriptionCallback iDeleteSubscriptionCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void downloadSubscription(int i, DownloadableSubscription downloadableSubscription, boolean z, boolean z2, Bundle bundle, IDownloadSubscriptionCallback iDownloadSubscriptionCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void dump(IEuiccServiceDumpResultCallback iEuiccServiceDumpResultCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void eraseSubscriptions(int i, IEraseSubscriptionsCallback iEraseSubscriptionsCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void eraseSubscriptionsWithOptions(int i, int i2, IEraseSubscriptionsCallback iEraseSubscriptionsCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void getDefaultDownloadableSubscriptionList(int i, boolean z, IGetDefaultDownloadableSubscriptionListCallback iGetDefaultDownloadableSubscriptionListCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void getDownloadableSubscriptionMetadata(int i, DownloadableSubscription downloadableSubscription, boolean z, IGetDownloadableSubscriptionMetadataCallback iGetDownloadableSubscriptionMetadataCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void getEid(int i, IGetEidCallback iGetEidCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void getEuiccInfo(int i, IGetEuiccInfoCallback iGetEuiccInfoCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void getEuiccProfileInfoList(int i, IGetEuiccProfileInfoListCallback iGetEuiccProfileInfoListCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void getOtaStatus(int i, IGetOtaStatusCallback iGetOtaStatusCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void retainSubscriptionsForFactoryReset(int i, IRetainSubscriptionsForFactoryResetCallback iRetainSubscriptionsForFactoryResetCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void startOtaIfNecessary(int i, IOtaStatusChangedCallback iOtaStatusChangedCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void switchToSubscription(int i, String str, boolean z, ISwitchToSubscriptionCallback iSwitchToSubscriptionCallback) throws RemoteException {
        }

        @Override // android.service.euicc.IEuiccService
        public void updateSubscriptionNickname(int i, String str, String str2, IUpdateSubscriptionNicknameCallback iUpdateSubscriptionNicknameCallback) throws RemoteException {
        }
    }

    void deleteSubscription(int i, String str, IDeleteSubscriptionCallback iDeleteSubscriptionCallback) throws RemoteException;

    void downloadSubscription(int i, DownloadableSubscription downloadableSubscription, boolean z, boolean z2, Bundle bundle, IDownloadSubscriptionCallback iDownloadSubscriptionCallback) throws RemoteException;

    void dump(IEuiccServiceDumpResultCallback iEuiccServiceDumpResultCallback) throws RemoteException;

    void eraseSubscriptions(int i, IEraseSubscriptionsCallback iEraseSubscriptionsCallback) throws RemoteException;

    void eraseSubscriptionsWithOptions(int i, int i2, IEraseSubscriptionsCallback iEraseSubscriptionsCallback) throws RemoteException;

    void getDefaultDownloadableSubscriptionList(int i, boolean z, IGetDefaultDownloadableSubscriptionListCallback iGetDefaultDownloadableSubscriptionListCallback) throws RemoteException;

    void getDownloadableSubscriptionMetadata(int i, DownloadableSubscription downloadableSubscription, boolean z, IGetDownloadableSubscriptionMetadataCallback iGetDownloadableSubscriptionMetadataCallback) throws RemoteException;

    void getEid(int i, IGetEidCallback iGetEidCallback) throws RemoteException;

    void getEuiccInfo(int i, IGetEuiccInfoCallback iGetEuiccInfoCallback) throws RemoteException;

    void getEuiccProfileInfoList(int i, IGetEuiccProfileInfoListCallback iGetEuiccProfileInfoListCallback) throws RemoteException;

    void getOtaStatus(int i, IGetOtaStatusCallback iGetOtaStatusCallback) throws RemoteException;

    void retainSubscriptionsForFactoryReset(int i, IRetainSubscriptionsForFactoryResetCallback iRetainSubscriptionsForFactoryResetCallback) throws RemoteException;

    void startOtaIfNecessary(int i, IOtaStatusChangedCallback iOtaStatusChangedCallback) throws RemoteException;

    void switchToSubscription(int i, String str, boolean z, ISwitchToSubscriptionCallback iSwitchToSubscriptionCallback) throws RemoteException;

    void updateSubscriptionNickname(int i, String str, String str2, IUpdateSubscriptionNicknameCallback iUpdateSubscriptionNicknameCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IEuiccService {
        private static final String DESCRIPTOR = "android.service.euicc.IEuiccService";
        static final int TRANSACTION_deleteSubscription = 9;
        static final int TRANSACTION_downloadSubscription = 1;
        static final int TRANSACTION_dump = 15;
        static final int TRANSACTION_eraseSubscriptions = 12;
        static final int TRANSACTION_eraseSubscriptionsWithOptions = 13;
        static final int TRANSACTION_getDefaultDownloadableSubscriptionList = 7;
        static final int TRANSACTION_getDownloadableSubscriptionMetadata = 2;
        static final int TRANSACTION_getEid = 3;
        static final int TRANSACTION_getEuiccInfo = 8;
        static final int TRANSACTION_getEuiccProfileInfoList = 6;
        static final int TRANSACTION_getOtaStatus = 4;
        static final int TRANSACTION_retainSubscriptionsForFactoryReset = 14;
        static final int TRANSACTION_startOtaIfNecessary = 5;
        static final int TRANSACTION_switchToSubscription = 10;
        static final int TRANSACTION_updateSubscriptionNickname = 11;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "downloadSubscription";
                case 2:
                    return "getDownloadableSubscriptionMetadata";
                case 3:
                    return "getEid";
                case 4:
                    return "getOtaStatus";
                case 5:
                    return "startOtaIfNecessary";
                case 6:
                    return "getEuiccProfileInfoList";
                case 7:
                    return "getDefaultDownloadableSubscriptionList";
                case 8:
                    return "getEuiccInfo";
                case 9:
                    return "deleteSubscription";
                case 10:
                    return "switchToSubscription";
                case 11:
                    return "updateSubscriptionNickname";
                case 12:
                    return "eraseSubscriptions";
                case 13:
                    return "eraseSubscriptionsWithOptions";
                case 14:
                    return "retainSubscriptionsForFactoryReset";
                case 15:
                    return "dump";
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

        public static IEuiccService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IEuiccService)) {
                return (IEuiccService) iInterfaceQueryLocalInterface;
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
                    downloadSubscription(parcel.readInt(), parcel.readInt() != 0 ? DownloadableSubscription.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, IDownloadSubscriptionCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    getDownloadableSubscriptionMetadata(parcel.readInt(), parcel.readInt() != 0 ? DownloadableSubscription.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, IGetDownloadableSubscriptionMetadataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    getEid(parcel.readInt(), IGetEidCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    getOtaStatus(parcel.readInt(), IGetOtaStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    startOtaIfNecessary(parcel.readInt(), IOtaStatusChangedCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    getEuiccProfileInfoList(parcel.readInt(), IGetEuiccProfileInfoListCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    getDefaultDownloadableSubscriptionList(parcel.readInt(), parcel.readInt() != 0, IGetDefaultDownloadableSubscriptionListCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    getEuiccInfo(parcel.readInt(), IGetEuiccInfoCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteSubscription(parcel.readInt(), parcel.readString(), IDeleteSubscriptionCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    switchToSubscription(parcel.readInt(), parcel.readString(), parcel.readInt() != 0, ISwitchToSubscriptionCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateSubscriptionNickname(parcel.readInt(), parcel.readString(), parcel.readString(), IUpdateSubscriptionNicknameCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    eraseSubscriptions(parcel.readInt(), IEraseSubscriptionsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    eraseSubscriptionsWithOptions(parcel.readInt(), parcel.readInt(), IEraseSubscriptionsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    retainSubscriptionsForFactoryReset(parcel.readInt(), IRetainSubscriptionsForFactoryResetCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    dump(IEuiccServiceDumpResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IEuiccService {
            public static IEuiccService sDefaultImpl;
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

            @Override // android.service.euicc.IEuiccService
            public void downloadSubscription(int i, DownloadableSubscription downloadableSubscription, boolean z, boolean z2, Bundle bundle, IDownloadSubscriptionCallback iDownloadSubscriptionCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (downloadableSubscription != null) {
                        parcelObtain.writeInt(1);
                        downloadableSubscription.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iDownloadSubscriptionCallback != null ? iDownloadSubscriptionCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().downloadSubscription(i, downloadableSubscription, z, z2, bundle, iDownloadSubscriptionCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void getDownloadableSubscriptionMetadata(int i, DownloadableSubscription downloadableSubscription, boolean z, IGetDownloadableSubscriptionMetadataCallback iGetDownloadableSubscriptionMetadataCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (downloadableSubscription != null) {
                        parcelObtain.writeInt(1);
                        downloadableSubscription.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iGetDownloadableSubscriptionMetadataCallback != null ? iGetDownloadableSubscriptionMetadataCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getDownloadableSubscriptionMetadata(i, downloadableSubscription, z, iGetDownloadableSubscriptionMetadataCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void getEid(int i, IGetEidCallback iGetEidCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iGetEidCallback != null ? iGetEidCallback.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getEid(i, iGetEidCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void getOtaStatus(int i, IGetOtaStatusCallback iGetOtaStatusCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iGetOtaStatusCallback != null ? iGetOtaStatusCallback.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getOtaStatus(i, iGetOtaStatusCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void startOtaIfNecessary(int i, IOtaStatusChangedCallback iOtaStatusChangedCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iOtaStatusChangedCallback != null ? iOtaStatusChangedCallback.asBinder() : null);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startOtaIfNecessary(i, iOtaStatusChangedCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void getEuiccProfileInfoList(int i, IGetEuiccProfileInfoListCallback iGetEuiccProfileInfoListCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iGetEuiccProfileInfoListCallback != null ? iGetEuiccProfileInfoListCallback.asBinder() : null);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getEuiccProfileInfoList(i, iGetEuiccProfileInfoListCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void getDefaultDownloadableSubscriptionList(int i, boolean z, IGetDefaultDownloadableSubscriptionListCallback iGetDefaultDownloadableSubscriptionListCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iGetDefaultDownloadableSubscriptionListCallback != null ? iGetDefaultDownloadableSubscriptionListCallback.asBinder() : null);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getDefaultDownloadableSubscriptionList(i, z, iGetDefaultDownloadableSubscriptionListCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void getEuiccInfo(int i, IGetEuiccInfoCallback iGetEuiccInfoCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iGetEuiccInfoCallback != null ? iGetEuiccInfoCallback.asBinder() : null);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getEuiccInfo(i, iGetEuiccInfoCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void deleteSubscription(int i, String str, IDeleteSubscriptionCallback iDeleteSubscriptionCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iDeleteSubscriptionCallback != null ? iDeleteSubscriptionCallback.asBinder() : null);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().deleteSubscription(i, str, iDeleteSubscriptionCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void switchToSubscription(int i, String str, boolean z, ISwitchToSubscriptionCallback iSwitchToSubscriptionCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iSwitchToSubscriptionCallback != null ? iSwitchToSubscriptionCallback.asBinder() : null);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().switchToSubscription(i, str, z, iSwitchToSubscriptionCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void updateSubscriptionNickname(int i, String str, String str2, IUpdateSubscriptionNicknameCallback iUpdateSubscriptionNicknameCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iUpdateSubscriptionNicknameCallback != null ? iUpdateSubscriptionNicknameCallback.asBinder() : null);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().updateSubscriptionNickname(i, str, str2, iUpdateSubscriptionNicknameCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void eraseSubscriptions(int i, IEraseSubscriptionsCallback iEraseSubscriptionsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iEraseSubscriptionsCallback != null ? iEraseSubscriptionsCallback.asBinder() : null);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().eraseSubscriptions(i, iEraseSubscriptionsCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void eraseSubscriptionsWithOptions(int i, int i2, IEraseSubscriptionsCallback iEraseSubscriptionsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iEraseSubscriptionsCallback != null ? iEraseSubscriptionsCallback.asBinder() : null);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().eraseSubscriptionsWithOptions(i, i2, iEraseSubscriptionsCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void retainSubscriptionsForFactoryReset(int i, IRetainSubscriptionsForFactoryResetCallback iRetainSubscriptionsForFactoryResetCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iRetainSubscriptionsForFactoryResetCallback != null ? iRetainSubscriptionsForFactoryResetCallback.asBinder() : null);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().retainSubscriptionsForFactoryReset(i, iRetainSubscriptionsForFactoryResetCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.euicc.IEuiccService
            public void dump(IEuiccServiceDumpResultCallback iEuiccServiceDumpResultCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iEuiccServiceDumpResultCallback != null ? iEuiccServiceDumpResultCallback.asBinder() : null);
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dump(iEuiccServiceDumpResultCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IEuiccService iEuiccService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iEuiccService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iEuiccService;
            return true;
        }

        public static IEuiccService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
