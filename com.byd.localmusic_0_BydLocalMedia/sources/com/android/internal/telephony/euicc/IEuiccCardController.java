package com.android.internal.telephony.euicc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.telephony.euicc.IAuthenticateServerCallback;
import com.android.internal.telephony.euicc.ICancelSessionCallback;
import com.android.internal.telephony.euicc.IDeleteProfileCallback;
import com.android.internal.telephony.euicc.IDisableProfileCallback;
import com.android.internal.telephony.euicc.IGetAllProfilesCallback;
import com.android.internal.telephony.euicc.IGetDefaultSmdpAddressCallback;
import com.android.internal.telephony.euicc.IGetEuiccChallengeCallback;
import com.android.internal.telephony.euicc.IGetEuiccInfo1Callback;
import com.android.internal.telephony.euicc.IGetEuiccInfo2Callback;
import com.android.internal.telephony.euicc.IGetProfileCallback;
import com.android.internal.telephony.euicc.IGetRulesAuthTableCallback;
import com.android.internal.telephony.euicc.IGetSmdsAddressCallback;
import com.android.internal.telephony.euicc.IListNotificationsCallback;
import com.android.internal.telephony.euicc.ILoadBoundProfilePackageCallback;
import com.android.internal.telephony.euicc.IPrepareDownloadCallback;
import com.android.internal.telephony.euicc.IRemoveNotificationFromListCallback;
import com.android.internal.telephony.euicc.IResetMemoryCallback;
import com.android.internal.telephony.euicc.IRetrieveNotificationCallback;
import com.android.internal.telephony.euicc.IRetrieveNotificationListCallback;
import com.android.internal.telephony.euicc.ISetDefaultSmdpAddressCallback;
import com.android.internal.telephony.euicc.ISetNicknameCallback;
import com.android.internal.telephony.euicc.ISwitchToProfileCallback;

/* JADX INFO: loaded from: classes4.dex */
public interface IEuiccCardController extends IInterface {

    public static class Default implements IEuiccCardController {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void authenticateServer(String str, String str2, String str3, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, IAuthenticateServerCallback iAuthenticateServerCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void cancelSession(String str, String str2, byte[] bArr, int i, ICancelSessionCallback iCancelSessionCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void deleteProfile(String str, String str2, String str3, IDeleteProfileCallback iDeleteProfileCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void disableProfile(String str, String str2, String str3, boolean z, IDisableProfileCallback iDisableProfileCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getAllProfiles(String str, String str2, IGetAllProfilesCallback iGetAllProfilesCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getDefaultSmdpAddress(String str, String str2, IGetDefaultSmdpAddressCallback iGetDefaultSmdpAddressCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getEuiccChallenge(String str, String str2, IGetEuiccChallengeCallback iGetEuiccChallengeCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getEuiccInfo1(String str, String str2, IGetEuiccInfo1Callback iGetEuiccInfo1Callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getEuiccInfo2(String str, String str2, IGetEuiccInfo2Callback iGetEuiccInfo2Callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getProfile(String str, String str2, String str3, IGetProfileCallback iGetProfileCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getRulesAuthTable(String str, String str2, IGetRulesAuthTableCallback iGetRulesAuthTableCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getSmdsAddress(String str, String str2, IGetSmdsAddressCallback iGetSmdsAddressCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void listNotifications(String str, String str2, int i, IListNotificationsCallback iListNotificationsCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void loadBoundProfilePackage(String str, String str2, byte[] bArr, ILoadBoundProfilePackageCallback iLoadBoundProfilePackageCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void prepareDownload(String str, String str2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, IPrepareDownloadCallback iPrepareDownloadCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void removeNotificationFromList(String str, String str2, int i, IRemoveNotificationFromListCallback iRemoveNotificationFromListCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void resetMemory(String str, String str2, int i, IResetMemoryCallback iResetMemoryCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void retrieveNotification(String str, String str2, int i, IRetrieveNotificationCallback iRetrieveNotificationCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void retrieveNotificationList(String str, String str2, int i, IRetrieveNotificationListCallback iRetrieveNotificationListCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void setDefaultSmdpAddress(String str, String str2, String str3, ISetDefaultSmdpAddressCallback iSetDefaultSmdpAddressCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void setNickname(String str, String str2, String str3, String str4, ISetNicknameCallback iSetNicknameCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void switchToProfile(String str, String str2, String str3, boolean z, ISwitchToProfileCallback iSwitchToProfileCallback) throws RemoteException {
        }
    }

    void authenticateServer(String str, String str2, String str3, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, IAuthenticateServerCallback iAuthenticateServerCallback) throws RemoteException;

    void cancelSession(String str, String str2, byte[] bArr, int i, ICancelSessionCallback iCancelSessionCallback) throws RemoteException;

    void deleteProfile(String str, String str2, String str3, IDeleteProfileCallback iDeleteProfileCallback) throws RemoteException;

    void disableProfile(String str, String str2, String str3, boolean z, IDisableProfileCallback iDisableProfileCallback) throws RemoteException;

    void getAllProfiles(String str, String str2, IGetAllProfilesCallback iGetAllProfilesCallback) throws RemoteException;

    void getDefaultSmdpAddress(String str, String str2, IGetDefaultSmdpAddressCallback iGetDefaultSmdpAddressCallback) throws RemoteException;

    void getEuiccChallenge(String str, String str2, IGetEuiccChallengeCallback iGetEuiccChallengeCallback) throws RemoteException;

    void getEuiccInfo1(String str, String str2, IGetEuiccInfo1Callback iGetEuiccInfo1Callback) throws RemoteException;

    void getEuiccInfo2(String str, String str2, IGetEuiccInfo2Callback iGetEuiccInfo2Callback) throws RemoteException;

    void getProfile(String str, String str2, String str3, IGetProfileCallback iGetProfileCallback) throws RemoteException;

    void getRulesAuthTable(String str, String str2, IGetRulesAuthTableCallback iGetRulesAuthTableCallback) throws RemoteException;

    void getSmdsAddress(String str, String str2, IGetSmdsAddressCallback iGetSmdsAddressCallback) throws RemoteException;

    void listNotifications(String str, String str2, int i, IListNotificationsCallback iListNotificationsCallback) throws RemoteException;

    void loadBoundProfilePackage(String str, String str2, byte[] bArr, ILoadBoundProfilePackageCallback iLoadBoundProfilePackageCallback) throws RemoteException;

    void prepareDownload(String str, String str2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, IPrepareDownloadCallback iPrepareDownloadCallback) throws RemoteException;

    void removeNotificationFromList(String str, String str2, int i, IRemoveNotificationFromListCallback iRemoveNotificationFromListCallback) throws RemoteException;

    void resetMemory(String str, String str2, int i, IResetMemoryCallback iResetMemoryCallback) throws RemoteException;

    void retrieveNotification(String str, String str2, int i, IRetrieveNotificationCallback iRetrieveNotificationCallback) throws RemoteException;

    void retrieveNotificationList(String str, String str2, int i, IRetrieveNotificationListCallback iRetrieveNotificationListCallback) throws RemoteException;

    void setDefaultSmdpAddress(String str, String str2, String str3, ISetDefaultSmdpAddressCallback iSetDefaultSmdpAddressCallback) throws RemoteException;

    void setNickname(String str, String str2, String str3, String str4, ISetNicknameCallback iSetNicknameCallback) throws RemoteException;

    void switchToProfile(String str, String str2, String str3, boolean z, ISwitchToProfileCallback iSwitchToProfileCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IEuiccCardController {
        private static final String DESCRIPTOR = "com.android.internal.telephony.euicc.IEuiccCardController";
        static final int TRANSACTION_authenticateServer = 15;
        static final int TRANSACTION_cancelSession = 18;
        static final int TRANSACTION_deleteProfile = 6;
        static final int TRANSACTION_disableProfile = 3;
        static final int TRANSACTION_getAllProfiles = 1;
        static final int TRANSACTION_getDefaultSmdpAddress = 8;
        static final int TRANSACTION_getEuiccChallenge = 12;
        static final int TRANSACTION_getEuiccInfo1 = 13;
        static final int TRANSACTION_getEuiccInfo2 = 14;
        static final int TRANSACTION_getProfile = 2;
        static final int TRANSACTION_getRulesAuthTable = 11;
        static final int TRANSACTION_getSmdsAddress = 9;
        static final int TRANSACTION_listNotifications = 19;
        static final int TRANSACTION_loadBoundProfilePackage = 17;
        static final int TRANSACTION_prepareDownload = 16;
        static final int TRANSACTION_removeNotificationFromList = 22;
        static final int TRANSACTION_resetMemory = 7;
        static final int TRANSACTION_retrieveNotification = 21;
        static final int TRANSACTION_retrieveNotificationList = 20;
        static final int TRANSACTION_setDefaultSmdpAddress = 10;
        static final int TRANSACTION_setNickname = 5;
        static final int TRANSACTION_switchToProfile = 4;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getAllProfiles";
                case 2:
                    return "getProfile";
                case 3:
                    return "disableProfile";
                case 4:
                    return "switchToProfile";
                case 5:
                    return "setNickname";
                case 6:
                    return "deleteProfile";
                case 7:
                    return "resetMemory";
                case 8:
                    return "getDefaultSmdpAddress";
                case 9:
                    return "getSmdsAddress";
                case 10:
                    return "setDefaultSmdpAddress";
                case 11:
                    return "getRulesAuthTable";
                case 12:
                    return "getEuiccChallenge";
                case 13:
                    return "getEuiccInfo1";
                case 14:
                    return "getEuiccInfo2";
                case 15:
                    return "authenticateServer";
                case 16:
                    return "prepareDownload";
                case 17:
                    return "loadBoundProfilePackage";
                case 18:
                    return "cancelSession";
                case 19:
                    return "listNotifications";
                case 20:
                    return "retrieveNotificationList";
                case 21:
                    return "retrieveNotification";
                case 22:
                    return "removeNotificationFromList";
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

        public static IEuiccCardController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IEuiccCardController)) {
                return (IEuiccCardController) iInterfaceQueryLocalInterface;
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
                    getAllProfiles(parcel.readString(), parcel.readString(), IGetAllProfilesCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    getProfile(parcel.readString(), parcel.readString(), parcel.readString(), IGetProfileCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableProfile(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, IDisableProfileCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    switchToProfile(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, ISwitchToProfileCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNickname(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), ISetNicknameCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteProfile(parcel.readString(), parcel.readString(), parcel.readString(), IDeleteProfileCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetMemory(parcel.readString(), parcel.readString(), parcel.readInt(), IResetMemoryCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    getDefaultSmdpAddress(parcel.readString(), parcel.readString(), IGetDefaultSmdpAddressCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    getSmdsAddress(parcel.readString(), parcel.readString(), IGetSmdsAddressCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDefaultSmdpAddress(parcel.readString(), parcel.readString(), parcel.readString(), ISetDefaultSmdpAddressCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    getRulesAuthTable(parcel.readString(), parcel.readString(), IGetRulesAuthTableCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    getEuiccChallenge(parcel.readString(), parcel.readString(), IGetEuiccChallengeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    getEuiccInfo1(parcel.readString(), parcel.readString(), IGetEuiccInfo1Callback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    getEuiccInfo2(parcel.readString(), parcel.readString(), IGetEuiccInfo2Callback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    authenticateServer(parcel.readString(), parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), IAuthenticateServerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    prepareDownload(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), IPrepareDownloadCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    loadBoundProfilePackage(parcel.readString(), parcel.readString(), parcel.createByteArray(), ILoadBoundProfilePackageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelSession(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readInt(), ICancelSessionCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    listNotifications(parcel.readString(), parcel.readString(), parcel.readInt(), IListNotificationsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    retrieveNotificationList(parcel.readString(), parcel.readString(), parcel.readInt(), IRetrieveNotificationListCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    retrieveNotification(parcel.readString(), parcel.readString(), parcel.readInt(), IRetrieveNotificationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeNotificationFromList(parcel.readString(), parcel.readString(), parcel.readInt(), IRemoveNotificationFromListCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IEuiccCardController {
            public static IEuiccCardController sDefaultImpl;
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

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getAllProfiles(String str, String str2, IGetAllProfilesCallback iGetAllProfilesCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iGetAllProfilesCallback != null ? iGetAllProfilesCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getAllProfiles(str, str2, iGetAllProfilesCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getProfile(String str, String str2, String str3, IGetProfileCallback iGetProfileCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeStrongBinder(iGetProfileCallback != null ? iGetProfileCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getProfile(str, str2, str3, iGetProfileCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void disableProfile(String str, String str2, String str3, boolean z, IDisableProfileCallback iDisableProfileCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iDisableProfileCallback != null ? iDisableProfileCallback.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().disableProfile(str, str2, str3, z, iDisableProfileCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void switchToProfile(String str, String str2, String str3, boolean z, ISwitchToProfileCallback iSwitchToProfileCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iSwitchToProfileCallback != null ? iSwitchToProfileCallback.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().switchToProfile(str, str2, str3, z, iSwitchToProfileCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void setNickname(String str, String str2, String str3, String str4, ISetNicknameCallback iSetNicknameCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    parcelObtain.writeStrongBinder(iSetNicknameCallback != null ? iSetNicknameCallback.asBinder() : null);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setNickname(str, str2, str3, str4, iSetNicknameCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void deleteProfile(String str, String str2, String str3, IDeleteProfileCallback iDeleteProfileCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeStrongBinder(iDeleteProfileCallback != null ? iDeleteProfileCallback.asBinder() : null);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().deleteProfile(str, str2, str3, iDeleteProfileCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void resetMemory(String str, String str2, int i, IResetMemoryCallback iResetMemoryCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResetMemoryCallback != null ? iResetMemoryCallback.asBinder() : null);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().resetMemory(str, str2, i, iResetMemoryCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getDefaultSmdpAddress(String str, String str2, IGetDefaultSmdpAddressCallback iGetDefaultSmdpAddressCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iGetDefaultSmdpAddressCallback != null ? iGetDefaultSmdpAddressCallback.asBinder() : null);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getDefaultSmdpAddress(str, str2, iGetDefaultSmdpAddressCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getSmdsAddress(String str, String str2, IGetSmdsAddressCallback iGetSmdsAddressCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iGetSmdsAddressCallback != null ? iGetSmdsAddressCallback.asBinder() : null);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getSmdsAddress(str, str2, iGetSmdsAddressCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void setDefaultSmdpAddress(String str, String str2, String str3, ISetDefaultSmdpAddressCallback iSetDefaultSmdpAddressCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeStrongBinder(iSetDefaultSmdpAddressCallback != null ? iSetDefaultSmdpAddressCallback.asBinder() : null);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setDefaultSmdpAddress(str, str2, str3, iSetDefaultSmdpAddressCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getRulesAuthTable(String str, String str2, IGetRulesAuthTableCallback iGetRulesAuthTableCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iGetRulesAuthTableCallback != null ? iGetRulesAuthTableCallback.asBinder() : null);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getRulesAuthTable(str, str2, iGetRulesAuthTableCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getEuiccChallenge(String str, String str2, IGetEuiccChallengeCallback iGetEuiccChallengeCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iGetEuiccChallengeCallback != null ? iGetEuiccChallengeCallback.asBinder() : null);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getEuiccChallenge(str, str2, iGetEuiccChallengeCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getEuiccInfo1(String str, String str2, IGetEuiccInfo1Callback iGetEuiccInfo1Callback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iGetEuiccInfo1Callback != null ? iGetEuiccInfo1Callback.asBinder() : null);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getEuiccInfo1(str, str2, iGetEuiccInfo1Callback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getEuiccInfo2(String str, String str2, IGetEuiccInfo2Callback iGetEuiccInfo2Callback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iGetEuiccInfo2Callback != null ? iGetEuiccInfo2Callback.asBinder() : null);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getEuiccInfo2(str, str2, iGetEuiccInfo2Callback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void authenticateServer(String str, String str2, String str3, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, IAuthenticateServerCallback iAuthenticateServerCallback) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeByteArray(bArr2);
                    parcelObtain.writeByteArray(bArr3);
                    parcelObtain.writeByteArray(bArr4);
                    parcelObtain.writeStrongBinder(iAuthenticateServerCallback != null ? iAuthenticateServerCallback.asBinder() : null);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().authenticateServer(str, str2, str3, bArr, bArr2, bArr3, bArr4, iAuthenticateServerCallback);
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void prepareDownload(String str, String str2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, IPrepareDownloadCallback iPrepareDownloadCallback) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeByteArray(bArr2);
                    parcelObtain.writeByteArray(bArr3);
                    parcelObtain.writeByteArray(bArr4);
                    parcelObtain.writeStrongBinder(iPrepareDownloadCallback != null ? iPrepareDownloadCallback.asBinder() : null);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().prepareDownload(str, str2, bArr, bArr2, bArr3, bArr4, iPrepareDownloadCallback);
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void loadBoundProfilePackage(String str, String str2, byte[] bArr, ILoadBoundProfilePackageCallback iLoadBoundProfilePackageCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeStrongBinder(iLoadBoundProfilePackageCallback != null ? iLoadBoundProfilePackageCallback.asBinder() : null);
                    if (this.mRemote.transact(17, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().loadBoundProfilePackage(str, str2, bArr, iLoadBoundProfilePackageCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void cancelSession(String str, String str2, byte[] bArr, int i, ICancelSessionCallback iCancelSessionCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iCancelSessionCallback != null ? iCancelSessionCallback.asBinder() : null);
                    if (this.mRemote.transact(18, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelSession(str, str2, bArr, i, iCancelSessionCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void listNotifications(String str, String str2, int i, IListNotificationsCallback iListNotificationsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iListNotificationsCallback != null ? iListNotificationsCallback.asBinder() : null);
                    if (this.mRemote.transact(19, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().listNotifications(str, str2, i, iListNotificationsCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void retrieveNotificationList(String str, String str2, int i, IRetrieveNotificationListCallback iRetrieveNotificationListCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iRetrieveNotificationListCallback != null ? iRetrieveNotificationListCallback.asBinder() : null);
                    if (this.mRemote.transact(20, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().retrieveNotificationList(str, str2, i, iRetrieveNotificationListCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void retrieveNotification(String str, String str2, int i, IRetrieveNotificationCallback iRetrieveNotificationCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iRetrieveNotificationCallback != null ? iRetrieveNotificationCallback.asBinder() : null);
                    if (this.mRemote.transact(21, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().retrieveNotification(str, str2, i, iRetrieveNotificationCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void removeNotificationFromList(String str, String str2, int i, IRemoveNotificationFromListCallback iRemoveNotificationFromListCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iRemoveNotificationFromListCallback != null ? iRemoveNotificationFromListCallback.asBinder() : null);
                    if (this.mRemote.transact(22, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeNotificationFromList(str, str2, i, iRemoveNotificationFromListCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IEuiccCardController iEuiccCardController) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iEuiccCardController == null) {
                return false;
            }
            Proxy.sDefaultImpl = iEuiccCardController;
            return true;
        }

        public static IEuiccCardController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
