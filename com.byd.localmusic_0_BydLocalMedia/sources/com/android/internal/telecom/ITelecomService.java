package com.android.internal.telecom;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomAnalytics;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface ITelecomService extends IInterface {

    public static class Default implements ITelecomService {
        @Override // com.android.internal.telecom.ITelecomService
        public void acceptHandover(Uri uri, int i, PhoneAccountHandle phoneAccountHandle) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void acceptRingingCall(String str) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void acceptRingingCallWithVideoState(String str, int i) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void addNewIncomingCall(PhoneAccountHandle phoneAccountHandle, Bundle bundle) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void addNewIncomingConference(PhoneAccountHandle phoneAccountHandle, Bundle bundle) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void addNewUnknownCall(PhoneAccountHandle phoneAccountHandle, Bundle bundle) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void addOrRemoveTestCallCompanionApp(String str, boolean z) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void cancelMissedCallsNotification(String str) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void clearAccounts(String str) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public Intent createLaunchEmergencyDialerIntent(String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public Intent createManageBlockedNumbersIntent() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public TelecomAnalytics dumpCallAnalytics() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean enablePhoneAccount(PhoneAccountHandle phoneAccountHandle, boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean endCall(String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public Uri getAdnUriForPhoneAccount(PhoneAccountHandle phoneAccountHandle, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public List<PhoneAccountHandle> getAllPhoneAccountHandles() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public List<PhoneAccount> getAllPhoneAccounts() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public int getAllPhoneAccountsCount() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public List<PhoneAccountHandle> getCallCapablePhoneAccounts(boolean z, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public int getCallState() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public int getCurrentTtyMode(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public String getDefaultDialerPackage() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public String getDefaultDialerPackageForUser(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public PhoneAccountHandle getDefaultOutgoingPhoneAccount(String str, String str2, String str3) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public ComponentName getDefaultPhoneApp() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public String getLine1Number(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public PhoneAccount getPhoneAccount(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public List<PhoneAccountHandle> getPhoneAccountsForPackage(String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public List<PhoneAccountHandle> getPhoneAccountsSupportingScheme(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public List<PhoneAccountHandle> getSelfManagedPhoneAccounts(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public PhoneAccountHandle getSimCallManager(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public PhoneAccountHandle getSimCallManagerForUser(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public String getSystemDialerPackage() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public PhoneAccountHandle getUserSelectedOutgoingPhoneAccount(String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public String getVoiceMailNumber(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void handleCallIntent(Intent intent, String str) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean handlePinMmi(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean handlePinMmiForPhoneAccount(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isInCall(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isInEmergencyCall() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isInManagedCall(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isIncomingCallPermitted(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isOutgoingCallPermitted(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isRinging(String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isTtySupported(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isVoiceMailNumber(PhoneAccountHandle phoneAccountHandle, String str, String str2, String str3) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void placeCall(Uri uri, Bundle bundle, String str, String str2) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void registerPhoneAccount(PhoneAccount phoneAccount) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean setDefaultDialer(String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setSystemDialer(ComponentName componentName) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setTestDefaultCallRedirectionApp(String str) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setTestDefaultCallScreeningApp(String str) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setTestDefaultDialer(String str) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setTestEmergencyPhoneAccountPackageNameFilter(String str) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setTestPhoneAcctSuggestionComponent(String str) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setUserSelectedOutgoingPhoneAccount(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void showInCallScreen(boolean z, String str, String str2) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void silenceRinger(String str) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void startConference(List<Uri> list, Bundle bundle, String str) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void stopBlockSuppression() throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void unregisterPhoneAccount(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void waitOnHandlers() throws RemoteException {
        }
    }

    void acceptHandover(Uri uri, int i, PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    void acceptRingingCall(String str) throws RemoteException;

    void acceptRingingCallWithVideoState(String str, int i) throws RemoteException;

    void addNewIncomingCall(PhoneAccountHandle phoneAccountHandle, Bundle bundle) throws RemoteException;

    void addNewIncomingConference(PhoneAccountHandle phoneAccountHandle, Bundle bundle) throws RemoteException;

    void addNewUnknownCall(PhoneAccountHandle phoneAccountHandle, Bundle bundle) throws RemoteException;

    void addOrRemoveTestCallCompanionApp(String str, boolean z) throws RemoteException;

    void cancelMissedCallsNotification(String str) throws RemoteException;

    void clearAccounts(String str) throws RemoteException;

    Intent createLaunchEmergencyDialerIntent(String str) throws RemoteException;

    Intent createManageBlockedNumbersIntent() throws RemoteException;

    TelecomAnalytics dumpCallAnalytics() throws RemoteException;

    boolean enablePhoneAccount(PhoneAccountHandle phoneAccountHandle, boolean z) throws RemoteException;

    boolean endCall(String str) throws RemoteException;

    Uri getAdnUriForPhoneAccount(PhoneAccountHandle phoneAccountHandle, String str) throws RemoteException;

    List<PhoneAccountHandle> getAllPhoneAccountHandles() throws RemoteException;

    List<PhoneAccount> getAllPhoneAccounts() throws RemoteException;

    int getAllPhoneAccountsCount() throws RemoteException;

    List<PhoneAccountHandle> getCallCapablePhoneAccounts(boolean z, String str, String str2) throws RemoteException;

    int getCallState() throws RemoteException;

    int getCurrentTtyMode(String str, String str2) throws RemoteException;

    String getDefaultDialerPackage() throws RemoteException;

    String getDefaultDialerPackageForUser(int i) throws RemoteException;

    PhoneAccountHandle getDefaultOutgoingPhoneAccount(String str, String str2, String str3) throws RemoteException;

    ComponentName getDefaultPhoneApp() throws RemoteException;

    String getLine1Number(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException;

    PhoneAccount getPhoneAccount(PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    List<PhoneAccountHandle> getPhoneAccountsForPackage(String str) throws RemoteException;

    List<PhoneAccountHandle> getPhoneAccountsSupportingScheme(String str, String str2) throws RemoteException;

    List<PhoneAccountHandle> getSelfManagedPhoneAccounts(String str, String str2) throws RemoteException;

    PhoneAccountHandle getSimCallManager(int i) throws RemoteException;

    PhoneAccountHandle getSimCallManagerForUser(int i) throws RemoteException;

    String getSystemDialerPackage() throws RemoteException;

    PhoneAccountHandle getUserSelectedOutgoingPhoneAccount(String str) throws RemoteException;

    String getVoiceMailNumber(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException;

    void handleCallIntent(Intent intent, String str) throws RemoteException;

    boolean handlePinMmi(String str, String str2) throws RemoteException;

    boolean handlePinMmiForPhoneAccount(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException;

    boolean isInCall(String str, String str2) throws RemoteException;

    boolean isInEmergencyCall() throws RemoteException;

    boolean isInManagedCall(String str, String str2) throws RemoteException;

    boolean isIncomingCallPermitted(PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    boolean isOutgoingCallPermitted(PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    boolean isRinging(String str) throws RemoteException;

    boolean isTtySupported(String str, String str2) throws RemoteException;

    boolean isVoiceMailNumber(PhoneAccountHandle phoneAccountHandle, String str, String str2, String str3) throws RemoteException;

    void placeCall(Uri uri, Bundle bundle, String str, String str2) throws RemoteException;

    void registerPhoneAccount(PhoneAccount phoneAccount) throws RemoteException;

    boolean setDefaultDialer(String str) throws RemoteException;

    void setSystemDialer(ComponentName componentName) throws RemoteException;

    void setTestDefaultCallRedirectionApp(String str) throws RemoteException;

    void setTestDefaultCallScreeningApp(String str) throws RemoteException;

    void setTestDefaultDialer(String str) throws RemoteException;

    void setTestEmergencyPhoneAccountPackageNameFilter(String str) throws RemoteException;

    void setTestPhoneAcctSuggestionComponent(String str) throws RemoteException;

    void setUserSelectedOutgoingPhoneAccount(PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    void showInCallScreen(boolean z, String str, String str2) throws RemoteException;

    void silenceRinger(String str) throws RemoteException;

    void startConference(List<Uri> list, Bundle bundle, String str) throws RemoteException;

    void stopBlockSuppression() throws RemoteException;

    void unregisterPhoneAccount(PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    void waitOnHandlers() throws RemoteException;

    public static abstract class Stub extends Binder implements ITelecomService {
        private static final String DESCRIPTOR = "com.android.internal.telecom.ITelecomService";
        static final int TRANSACTION_acceptHandover = 53;
        static final int TRANSACTION_acceptRingingCall = 32;
        static final int TRANSACTION_acceptRingingCallWithVideoState = 33;
        static final int TRANSACTION_addNewIncomingCall = 40;
        static final int TRANSACTION_addNewIncomingConference = 41;
        static final int TRANSACTION_addNewUnknownCall = 42;
        static final int TRANSACTION_addOrRemoveTestCallCompanionApp = 60;
        static final int TRANSACTION_cancelMissedCallsNotification = 34;
        static final int TRANSACTION_clearAccounts = 17;
        static final int TRANSACTION_createLaunchEmergencyDialerIntent = 49;
        static final int TRANSACTION_createManageBlockedNumbersIntent = 48;
        static final int TRANSACTION_dumpCallAnalytics = 25;
        static final int TRANSACTION_enablePhoneAccount = 45;
        static final int TRANSACTION_endCall = 31;
        static final int TRANSACTION_getAdnUriForPhoneAccount = 37;
        static final int TRANSACTION_getAllPhoneAccountHandles = 12;
        static final int TRANSACTION_getAllPhoneAccounts = 11;
        static final int TRANSACTION_getAllPhoneAccountsCount = 10;
        static final int TRANSACTION_getCallCapablePhoneAccounts = 5;
        static final int TRANSACTION_getCallState = 30;
        static final int TRANSACTION_getCurrentTtyMode = 39;
        static final int TRANSACTION_getDefaultDialerPackage = 22;
        static final int TRANSACTION_getDefaultDialerPackageForUser = 23;
        static final int TRANSACTION_getDefaultOutgoingPhoneAccount = 2;
        static final int TRANSACTION_getDefaultPhoneApp = 21;
        static final int TRANSACTION_getLine1Number = 20;
        static final int TRANSACTION_getPhoneAccount = 9;
        static final int TRANSACTION_getPhoneAccountsForPackage = 8;
        static final int TRANSACTION_getPhoneAccountsSupportingScheme = 7;
        static final int TRANSACTION_getSelfManagedPhoneAccounts = 6;
        static final int TRANSACTION_getSimCallManager = 13;
        static final int TRANSACTION_getSimCallManagerForUser = 14;
        static final int TRANSACTION_getSystemDialerPackage = 24;
        static final int TRANSACTION_getUserSelectedOutgoingPhoneAccount = 3;
        static final int TRANSACTION_getVoiceMailNumber = 19;
        static final int TRANSACTION_handleCallIntent = 56;
        static final int TRANSACTION_handlePinMmi = 35;
        static final int TRANSACTION_handlePinMmiForPhoneAccount = 36;
        static final int TRANSACTION_isInCall = 27;
        static final int TRANSACTION_isInEmergencyCall = 55;
        static final int TRANSACTION_isInManagedCall = 28;
        static final int TRANSACTION_isIncomingCallPermitted = 50;
        static final int TRANSACTION_isOutgoingCallPermitted = 51;
        static final int TRANSACTION_isRinging = 29;
        static final int TRANSACTION_isTtySupported = 38;
        static final int TRANSACTION_isVoiceMailNumber = 18;
        static final int TRANSACTION_placeCall = 44;
        static final int TRANSACTION_registerPhoneAccount = 15;
        static final int TRANSACTION_setDefaultDialer = 46;
        static final int TRANSACTION_setSystemDialer = 61;
        static final int TRANSACTION_setTestDefaultCallRedirectionApp = 57;
        static final int TRANSACTION_setTestDefaultCallScreeningApp = 59;
        static final int TRANSACTION_setTestDefaultDialer = 62;
        static final int TRANSACTION_setTestEmergencyPhoneAccountPackageNameFilter = 54;
        static final int TRANSACTION_setTestPhoneAcctSuggestionComponent = 58;
        static final int TRANSACTION_setUserSelectedOutgoingPhoneAccount = 4;
        static final int TRANSACTION_showInCallScreen = 1;
        static final int TRANSACTION_silenceRinger = 26;
        static final int TRANSACTION_startConference = 43;
        static final int TRANSACTION_stopBlockSuppression = 47;
        static final int TRANSACTION_unregisterPhoneAccount = 16;
        static final int TRANSACTION_waitOnHandlers = 52;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "showInCallScreen";
                case 2:
                    return "getDefaultOutgoingPhoneAccount";
                case 3:
                    return "getUserSelectedOutgoingPhoneAccount";
                case 4:
                    return "setUserSelectedOutgoingPhoneAccount";
                case 5:
                    return "getCallCapablePhoneAccounts";
                case 6:
                    return "getSelfManagedPhoneAccounts";
                case 7:
                    return "getPhoneAccountsSupportingScheme";
                case 8:
                    return "getPhoneAccountsForPackage";
                case 9:
                    return "getPhoneAccount";
                case 10:
                    return "getAllPhoneAccountsCount";
                case 11:
                    return "getAllPhoneAccounts";
                case 12:
                    return "getAllPhoneAccountHandles";
                case 13:
                    return "getSimCallManager";
                case 14:
                    return "getSimCallManagerForUser";
                case 15:
                    return "registerPhoneAccount";
                case 16:
                    return "unregisterPhoneAccount";
                case 17:
                    return "clearAccounts";
                case 18:
                    return "isVoiceMailNumber";
                case 19:
                    return "getVoiceMailNumber";
                case 20:
                    return "getLine1Number";
                case 21:
                    return "getDefaultPhoneApp";
                case 22:
                    return "getDefaultDialerPackage";
                case 23:
                    return "getDefaultDialerPackageForUser";
                case 24:
                    return "getSystemDialerPackage";
                case 25:
                    return "dumpCallAnalytics";
                case 26:
                    return "silenceRinger";
                case 27:
                    return "isInCall";
                case 28:
                    return "isInManagedCall";
                case 29:
                    return "isRinging";
                case 30:
                    return "getCallState";
                case 31:
                    return "endCall";
                case 32:
                    return "acceptRingingCall";
                case 33:
                    return "acceptRingingCallWithVideoState";
                case 34:
                    return "cancelMissedCallsNotification";
                case 35:
                    return "handlePinMmi";
                case 36:
                    return "handlePinMmiForPhoneAccount";
                case 37:
                    return "getAdnUriForPhoneAccount";
                case 38:
                    return "isTtySupported";
                case 39:
                    return "getCurrentTtyMode";
                case 40:
                    return "addNewIncomingCall";
                case 41:
                    return "addNewIncomingConference";
                case 42:
                    return "addNewUnknownCall";
                case 43:
                    return "startConference";
                case 44:
                    return "placeCall";
                case 45:
                    return "enablePhoneAccount";
                case 46:
                    return "setDefaultDialer";
                case 47:
                    return "stopBlockSuppression";
                case 48:
                    return "createManageBlockedNumbersIntent";
                case 49:
                    return "createLaunchEmergencyDialerIntent";
                case 50:
                    return "isIncomingCallPermitted";
                case 51:
                    return "isOutgoingCallPermitted";
                case 52:
                    return "waitOnHandlers";
                case 53:
                    return "acceptHandover";
                case 54:
                    return "setTestEmergencyPhoneAccountPackageNameFilter";
                case 55:
                    return "isInEmergencyCall";
                case 56:
                    return "handleCallIntent";
                case 57:
                    return "setTestDefaultCallRedirectionApp";
                case 58:
                    return "setTestPhoneAcctSuggestionComponent";
                case 59:
                    return "setTestDefaultCallScreeningApp";
                case 60:
                    return "addOrRemoveTestCallCompanionApp";
                case 61:
                    return "setSystemDialer";
                case 62:
                    return "setTestDefaultDialer";
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

        public static ITelecomService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITelecomService)) {
                return (ITelecomService) iInterfaceQueryLocalInterface;
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
                    showInCallScreen(parcel.readInt() != 0, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    PhoneAccountHandle defaultOutgoingPhoneAccount = getDefaultOutgoingPhoneAccount(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (defaultOutgoingPhoneAccount != null) {
                        parcel2.writeInt(1);
                        defaultOutgoingPhoneAccount.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    PhoneAccountHandle userSelectedOutgoingPhoneAccount = getUserSelectedOutgoingPhoneAccount(parcel.readString());
                    parcel2.writeNoException();
                    if (userSelectedOutgoingPhoneAccount != null) {
                        parcel2.writeInt(1);
                        userSelectedOutgoingPhoneAccount.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUserSelectedOutgoingPhoneAccount(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<PhoneAccountHandle> callCapablePhoneAccounts = getCallCapablePhoneAccounts(parcel.readInt() != 0, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(callCapablePhoneAccounts);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<PhoneAccountHandle> selfManagedPhoneAccounts = getSelfManagedPhoneAccounts(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(selfManagedPhoneAccounts);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<PhoneAccountHandle> phoneAccountsSupportingScheme = getPhoneAccountsSupportingScheme(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(phoneAccountsSupportingScheme);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<PhoneAccountHandle> phoneAccountsForPackage = getPhoneAccountsForPackage(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(phoneAccountsForPackage);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    PhoneAccount phoneAccount = getPhoneAccount(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (phoneAccount != null) {
                        parcel2.writeInt(1);
                        phoneAccount.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int allPhoneAccountsCount = getAllPhoneAccountsCount();
                    parcel2.writeNoException();
                    parcel2.writeInt(allPhoneAccountsCount);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<PhoneAccount> allPhoneAccounts = getAllPhoneAccounts();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(allPhoneAccounts);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<PhoneAccountHandle> allPhoneAccountHandles = getAllPhoneAccountHandles();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(allPhoneAccountHandles);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    PhoneAccountHandle simCallManager = getSimCallManager(parcel.readInt());
                    parcel2.writeNoException();
                    if (simCallManager != null) {
                        parcel2.writeInt(1);
                        simCallManager.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    PhoneAccountHandle simCallManagerForUser = getSimCallManagerForUser(parcel.readInt());
                    parcel2.writeNoException();
                    if (simCallManagerForUser != null) {
                        parcel2.writeInt(1);
                        simCallManagerForUser.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerPhoneAccount(parcel.readInt() != 0 ? PhoneAccount.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterPhoneAccount(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearAccounts(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsVoiceMailNumber = isVoiceMailNumber(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsVoiceMailNumber ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    String voiceMailNumber = getVoiceMailNumber(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(voiceMailNumber);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    String line1Number = getLine1Number(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(line1Number);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    ComponentName defaultPhoneApp = getDefaultPhoneApp();
                    parcel2.writeNoException();
                    if (defaultPhoneApp != null) {
                        parcel2.writeInt(1);
                        defaultPhoneApp.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    String defaultDialerPackage = getDefaultDialerPackage();
                    parcel2.writeNoException();
                    parcel2.writeString(defaultDialerPackage);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    String defaultDialerPackageForUser = getDefaultDialerPackageForUser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(defaultDialerPackageForUser);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    String systemDialerPackage = getSystemDialerPackage();
                    parcel2.writeNoException();
                    parcel2.writeString(systemDialerPackage);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    TelecomAnalytics telecomAnalyticsDumpCallAnalytics = dumpCallAnalytics();
                    parcel2.writeNoException();
                    if (telecomAnalyticsDumpCallAnalytics != null) {
                        parcel2.writeInt(1);
                        telecomAnalyticsDumpCallAnalytics.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    silenceRinger(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsInCall = isInCall(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInCall ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsInManagedCall = isInManagedCall(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInManagedCall ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsRinging = isRinging(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsRinging ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    int callState = getCallState();
                    parcel2.writeNoException();
                    parcel2.writeInt(callState);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEndCall = endCall(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zEndCall ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    acceptRingingCall(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    acceptRingingCallWithVideoState(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelMissedCallsNotification(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHandlePinMmi = handlePinMmi(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHandlePinMmi ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHandlePinMmiForPhoneAccount = handlePinMmiForPhoneAccount(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHandlePinMmiForPhoneAccount ? 1 : 0);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    Uri adnUriForPhoneAccount = getAdnUriForPhoneAccount(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    if (adnUriForPhoneAccount != null) {
                        parcel2.writeInt(1);
                        adnUriForPhoneAccount.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsTtySupported = isTtySupported(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsTtySupported ? 1 : 0);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentTtyMode = getCurrentTtyMode(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(currentTtyMode);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    addNewIncomingCall(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    addNewIncomingConference(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    addNewUnknownCall(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    startConference(parcel.createTypedArrayList(Uri.CREATOR), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    placeCall(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnablePhoneAccount = enablePhoneAccount(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnablePhoneAccount ? 1 : 0);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean defaultDialer = setDefaultDialer(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(defaultDialer ? 1 : 0);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopBlockSuppression();
                    parcel2.writeNoException();
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    Intent intentCreateManageBlockedNumbersIntent = createManageBlockedNumbersIntent();
                    parcel2.writeNoException();
                    if (intentCreateManageBlockedNumbersIntent != null) {
                        parcel2.writeInt(1);
                        intentCreateManageBlockedNumbersIntent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    Intent intentCreateLaunchEmergencyDialerIntent = createLaunchEmergencyDialerIntent(parcel.readString());
                    parcel2.writeNoException();
                    if (intentCreateLaunchEmergencyDialerIntent != null) {
                        parcel2.writeInt(1);
                        intentCreateLaunchEmergencyDialerIntent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsIncomingCallPermitted = isIncomingCallPermitted(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsIncomingCallPermitted ? 1 : 0);
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsOutgoingCallPermitted = isOutgoingCallPermitted(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsOutgoingCallPermitted ? 1 : 0);
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    waitOnHandlers();
                    parcel2.writeNoException();
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    acceptHandover(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTestEmergencyPhoneAccountPackageNameFilter(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsInEmergencyCall = isInEmergencyCall();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInEmergencyCall ? 1 : 0);
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    handleCallIntent(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTestDefaultCallRedirectionApp(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTestPhoneAcctSuggestionComponent(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTestDefaultCallScreeningApp(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    addOrRemoveTestCallCompanionApp(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSystemDialer(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTestDefaultDialer(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITelecomService {
            public static ITelecomService sDefaultImpl;
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

            @Override // com.android.internal.telecom.ITelecomService
            public void showInCallScreen(boolean z, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showInCallScreen(z, str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public PhoneAccountHandle getDefaultOutgoingPhoneAccount(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultOutgoingPhoneAccount(str, str2, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public PhoneAccountHandle getUserSelectedOutgoingPhoneAccount(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserSelectedOutgoingPhoneAccount(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setUserSelectedOutgoingPhoneAccount(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUserSelectedOutgoingPhoneAccount(phoneAccountHandle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public List<PhoneAccountHandle> getCallCapablePhoneAccounts(boolean z, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallCapablePhoneAccounts(z, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(PhoneAccountHandle.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public List<PhoneAccountHandle> getSelfManagedPhoneAccounts(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSelfManagedPhoneAccounts(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(PhoneAccountHandle.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public List<PhoneAccountHandle> getPhoneAccountsSupportingScheme(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPhoneAccountsSupportingScheme(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(PhoneAccountHandle.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public List<PhoneAccountHandle> getPhoneAccountsForPackage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPhoneAccountsForPackage(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(PhoneAccountHandle.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public PhoneAccount getPhoneAccount(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPhoneAccount(phoneAccountHandle);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PhoneAccount.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public int getAllPhoneAccountsCount() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPhoneAccountsCount();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public List<PhoneAccount> getAllPhoneAccounts() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPhoneAccounts();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(PhoneAccount.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public List<PhoneAccountHandle> getAllPhoneAccountHandles() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPhoneAccountHandles();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(PhoneAccountHandle.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public PhoneAccountHandle getSimCallManager(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSimCallManager(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public PhoneAccountHandle getSimCallManagerForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSimCallManagerForUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void registerPhoneAccount(PhoneAccount phoneAccount) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccount != null) {
                        parcelObtain.writeInt(1);
                        phoneAccount.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerPhoneAccount(phoneAccount);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void unregisterPhoneAccount(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterPhoneAccount(phoneAccountHandle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void clearAccounts(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearAccounts(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isVoiceMailNumber(PhoneAccountHandle phoneAccountHandle, String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVoiceMailNumber(phoneAccountHandle, str, str2, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public String getVoiceMailNumber(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoiceMailNumber(phoneAccountHandle, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public String getLine1Number(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLine1Number(phoneAccountHandle, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public ComponentName getDefaultPhoneApp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultPhoneApp();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public String getDefaultDialerPackage() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultDialerPackage();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public String getDefaultDialerPackageForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultDialerPackageForUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public String getSystemDialerPackage() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemDialerPackage();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public TelecomAnalytics dumpCallAnalytics() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().dumpCallAnalytics();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? TelecomAnalytics.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void silenceRinger(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().silenceRinger(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isInCall(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInCall(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isInManagedCall(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInManagedCall(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isRinging(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRinging(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public int getCallState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean endCall(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().endCall(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void acceptRingingCall(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().acceptRingingCall(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void acceptRingingCallWithVideoState(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().acceptRingingCallWithVideoState(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void cancelMissedCallsNotification(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelMissedCallsNotification(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean handlePinMmi(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handlePinMmi(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean handlePinMmiForPhoneAccount(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handlePinMmiForPhoneAccount(phoneAccountHandle, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public Uri getAdnUriForPhoneAccount(PhoneAccountHandle phoneAccountHandle, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAdnUriForPhoneAccount(phoneAccountHandle, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isTtySupported(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTtySupported(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public int getCurrentTtyMode(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentTtyMode(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void addNewIncomingCall(PhoneAccountHandle phoneAccountHandle, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addNewIncomingCall(phoneAccountHandle, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void addNewIncomingConference(PhoneAccountHandle phoneAccountHandle, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addNewIncomingConference(phoneAccountHandle, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void addNewUnknownCall(PhoneAccountHandle phoneAccountHandle, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addNewUnknownCall(phoneAccountHandle, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void startConference(List<Uri> list, Bundle bundle, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startConference(list, bundle, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void placeCall(Uri uri, Bundle bundle, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().placeCall(uri, bundle, str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean enablePhoneAccount(PhoneAccountHandle phoneAccountHandle, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enablePhoneAccount(phoneAccountHandle, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean setDefaultDialer(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDefaultDialer(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void stopBlockSuppression() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopBlockSuppression();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public Intent createManageBlockedNumbersIntent() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createManageBlockedNumbersIntent();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public Intent createLaunchEmergencyDialerIntent(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createLaunchEmergencyDialerIntent(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isIncomingCallPermitted(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isIncomingCallPermitted(phoneAccountHandle);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isOutgoingCallPermitted(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isOutgoingCallPermitted(phoneAccountHandle);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void waitOnHandlers() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().waitOnHandlers();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void acceptHandover(Uri uri, int i, PhoneAccountHandle phoneAccountHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().acceptHandover(uri, i, phoneAccountHandle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setTestEmergencyPhoneAccountPackageNameFilter(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTestEmergencyPhoneAccountPackageNameFilter(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isInEmergencyCall() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(55, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInEmergencyCall();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void handleCallIntent(Intent intent, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(56, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handleCallIntent(intent, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setTestDefaultCallRedirectionApp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(57, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTestDefaultCallRedirectionApp(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setTestPhoneAcctSuggestionComponent(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(58, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTestPhoneAcctSuggestionComponent(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setTestDefaultCallScreeningApp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(59, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTestDefaultCallScreeningApp(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void addOrRemoveTestCallCompanionApp(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(60, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addOrRemoveTestCallCompanionApp(str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setSystemDialer(ComponentName componentName) throws RemoteException {
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
                    if (!this.mRemote.transact(61, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSystemDialer(componentName);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setTestDefaultDialer(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(62, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTestDefaultDialer(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITelecomService iTelecomService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTelecomService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTelecomService;
            return true;
        }

        public static ITelecomService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
