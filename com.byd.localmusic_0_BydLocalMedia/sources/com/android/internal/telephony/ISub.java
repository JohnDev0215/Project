package com.android.internal.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.telephony.SubscriptionInfo;
import com.android.internal.telephony.ISetOpportunisticDataCallback;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface ISub extends IInterface {

    public static class Default implements ISub {
        @Override // com.android.internal.telephony.ISub
        public int addSubInfo(String str, String str2, int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int addSubInfoRecord(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public void addSubscriptionsIntoGroup(int[] iArr, ParcelUuid parcelUuid, String str) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public boolean canDisablePhysicalSubscription() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISub
        public int clearSubInfo() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public ParcelUuid createSubscriptionGroup(int[] iArr, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public List<SubscriptionInfo> getAccessibleSubscriptionInfoList(String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public int getActiveDataSubscriptionId() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int[] getActiveSubIdList(boolean z) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public int getActiveSubInfoCount(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int getActiveSubInfoCountMax() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public SubscriptionInfo getActiveSubscriptionInfo(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public SubscriptionInfo getActiveSubscriptionInfoForIccId(String str, String str2, String str3) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public SubscriptionInfo getActiveSubscriptionInfoForSimSlotIndex(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public List<SubscriptionInfo> getActiveSubscriptionInfoList(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public int getAllSubInfoCount(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public List<SubscriptionInfo> getAllSubInfoList(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public List<SubscriptionInfo> getAvailableSubscriptionInfoList(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public int getDefaultDataSubId() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int getDefaultSmsSubId() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int getDefaultSubId() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int getDefaultVoiceSubId() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int getEnabledSubscriptionId(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public List<SubscriptionInfo> getOpportunisticSubscriptions(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public int getPhoneId(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int getPreferredDataSubscriptionId() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int getSimStateForSlotIndex(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int getSlotIndex(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int[] getSubId(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public String getSubscriptionProperty(int i, String str, String str2, String str3) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public List<SubscriptionInfo> getSubscriptionsInGroup(ParcelUuid parcelUuid, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISub
        public boolean isActiveSubId(int i, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISub
        public boolean isSubscriptionEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISub
        public int removeSubInfo(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public void removeSubscriptionsFromGroup(int[] iArr, ParcelUuid parcelUuid, String str) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISub
        public void requestEmbeddedSubscriptionInfoListRefresh(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISub
        public int setDataRoaming(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public void setDefaultDataSubId(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISub
        public void setDefaultSmsSubId(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISub
        public void setDefaultVoiceSubId(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISub
        public int setDisplayNameUsingSrc(String str, int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int setDisplayNumber(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int setIconTint(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int setOpportunistic(boolean z, int i, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public void setPreferredDataSubscriptionId(int i, boolean z, ISetOpportunisticDataCallback iSetOpportunisticDataCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISub
        public boolean setSubscriptionEnabled(boolean z, int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISub
        public int setSubscriptionProperty(int i, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISub
        public int setUiccApplicationsEnabled(boolean z, int i) throws RemoteException {
            return 0;
        }
    }

    int addSubInfo(String str, String str2, int i, int i2) throws RemoteException;

    int addSubInfoRecord(String str, int i) throws RemoteException;

    void addSubscriptionsIntoGroup(int[] iArr, ParcelUuid parcelUuid, String str) throws RemoteException;

    boolean canDisablePhysicalSubscription() throws RemoteException;

    int clearSubInfo() throws RemoteException;

    ParcelUuid createSubscriptionGroup(int[] iArr, String str) throws RemoteException;

    List<SubscriptionInfo> getAccessibleSubscriptionInfoList(String str) throws RemoteException;

    int getActiveDataSubscriptionId() throws RemoteException;

    int[] getActiveSubIdList(boolean z) throws RemoteException;

    int getActiveSubInfoCount(String str, String str2) throws RemoteException;

    int getActiveSubInfoCountMax() throws RemoteException;

    SubscriptionInfo getActiveSubscriptionInfo(int i, String str, String str2) throws RemoteException;

    SubscriptionInfo getActiveSubscriptionInfoForIccId(String str, String str2, String str3) throws RemoteException;

    SubscriptionInfo getActiveSubscriptionInfoForSimSlotIndex(int i, String str, String str2) throws RemoteException;

    List<SubscriptionInfo> getActiveSubscriptionInfoList(String str, String str2) throws RemoteException;

    int getAllSubInfoCount(String str, String str2) throws RemoteException;

    List<SubscriptionInfo> getAllSubInfoList(String str, String str2) throws RemoteException;

    List<SubscriptionInfo> getAvailableSubscriptionInfoList(String str, String str2) throws RemoteException;

    int getDefaultDataSubId() throws RemoteException;

    int getDefaultSmsSubId() throws RemoteException;

    int getDefaultSubId() throws RemoteException;

    int getDefaultVoiceSubId() throws RemoteException;

    int getEnabledSubscriptionId(int i) throws RemoteException;

    List<SubscriptionInfo> getOpportunisticSubscriptions(String str, String str2) throws RemoteException;

    int getPhoneId(int i) throws RemoteException;

    int getPreferredDataSubscriptionId() throws RemoteException;

    int getSimStateForSlotIndex(int i) throws RemoteException;

    int getSlotIndex(int i) throws RemoteException;

    int[] getSubId(int i) throws RemoteException;

    String getSubscriptionProperty(int i, String str, String str2, String str3) throws RemoteException;

    List<SubscriptionInfo> getSubscriptionsInGroup(ParcelUuid parcelUuid, String str, String str2) throws RemoteException;

    boolean isActiveSubId(int i, String str, String str2) throws RemoteException;

    boolean isSubscriptionEnabled(int i) throws RemoteException;

    int removeSubInfo(String str, int i) throws RemoteException;

    void removeSubscriptionsFromGroup(int[] iArr, ParcelUuid parcelUuid, String str) throws RemoteException;

    void requestEmbeddedSubscriptionInfoListRefresh(int i) throws RemoteException;

    int setDataRoaming(int i, int i2) throws RemoteException;

    void setDefaultDataSubId(int i) throws RemoteException;

    void setDefaultSmsSubId(int i) throws RemoteException;

    void setDefaultVoiceSubId(int i) throws RemoteException;

    int setDisplayNameUsingSrc(String str, int i, int i2) throws RemoteException;

    int setDisplayNumber(String str, int i) throws RemoteException;

    int setIconTint(int i, int i2) throws RemoteException;

    int setOpportunistic(boolean z, int i, String str) throws RemoteException;

    void setPreferredDataSubscriptionId(int i, boolean z, ISetOpportunisticDataCallback iSetOpportunisticDataCallback) throws RemoteException;

    boolean setSubscriptionEnabled(boolean z, int i) throws RemoteException;

    int setSubscriptionProperty(int i, String str, String str2) throws RemoteException;

    int setUiccApplicationsEnabled(boolean z, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ISub {
        private static final String DESCRIPTOR = "com.android.internal.telephony.ISub";
        static final int TRANSACTION_addSubInfo = 13;
        static final int TRANSACTION_addSubInfoRecord = 12;
        static final int TRANSACTION_addSubscriptionsIntoGroup = 25;
        static final int TRANSACTION_canDisablePhysicalSubscription = 47;
        static final int TRANSACTION_clearSubInfo = 30;
        static final int TRANSACTION_createSubscriptionGroup = 20;
        static final int TRANSACTION_getAccessibleSubscriptionInfoList = 10;
        static final int TRANSACTION_getActiveDataSubscriptionId = 46;
        static final int TRANSACTION_getActiveSubIdList = 38;
        static final int TRANSACTION_getActiveSubInfoCount = 7;
        static final int TRANSACTION_getActiveSubInfoCountMax = 8;
        static final int TRANSACTION_getActiveSubscriptionInfo = 3;
        static final int TRANSACTION_getActiveSubscriptionInfoForIccId = 4;
        static final int TRANSACTION_getActiveSubscriptionInfoForSimSlotIndex = 5;
        static final int TRANSACTION_getActiveSubscriptionInfoList = 6;
        static final int TRANSACTION_getAllSubInfoCount = 2;
        static final int TRANSACTION_getAllSubInfoList = 1;
        static final int TRANSACTION_getAvailableSubscriptionInfoList = 9;
        static final int TRANSACTION_getDefaultDataSubId = 32;
        static final int TRANSACTION_getDefaultSmsSubId = 36;
        static final int TRANSACTION_getDefaultSubId = 29;
        static final int TRANSACTION_getDefaultVoiceSubId = 34;
        static final int TRANSACTION_getEnabledSubscriptionId = 43;
        static final int TRANSACTION_getOpportunisticSubscriptions = 23;
        static final int TRANSACTION_getPhoneId = 31;
        static final int TRANSACTION_getPreferredDataSubscriptionId = 22;
        static final int TRANSACTION_getSimStateForSlotIndex = 44;
        static final int TRANSACTION_getSlotIndex = 27;
        static final int TRANSACTION_getSubId = 28;
        static final int TRANSACTION_getSubscriptionProperty = 40;
        static final int TRANSACTION_getSubscriptionsInGroup = 26;
        static final int TRANSACTION_isActiveSubId = 45;
        static final int TRANSACTION_isSubscriptionEnabled = 42;
        static final int TRANSACTION_removeSubInfo = 14;
        static final int TRANSACTION_removeSubscriptionsFromGroup = 24;
        static final int TRANSACTION_requestEmbeddedSubscriptionInfoListRefresh = 11;
        static final int TRANSACTION_setDataRoaming = 18;
        static final int TRANSACTION_setDefaultDataSubId = 33;
        static final int TRANSACTION_setDefaultSmsSubId = 37;
        static final int TRANSACTION_setDefaultVoiceSubId = 35;
        static final int TRANSACTION_setDisplayNameUsingSrc = 16;
        static final int TRANSACTION_setDisplayNumber = 17;
        static final int TRANSACTION_setIconTint = 15;
        static final int TRANSACTION_setOpportunistic = 19;
        static final int TRANSACTION_setPreferredDataSubscriptionId = 21;
        static final int TRANSACTION_setSubscriptionEnabled = 41;
        static final int TRANSACTION_setSubscriptionProperty = 39;
        static final int TRANSACTION_setUiccApplicationsEnabled = 48;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getAllSubInfoList";
                case 2:
                    return "getAllSubInfoCount";
                case 3:
                    return "getActiveSubscriptionInfo";
                case 4:
                    return "getActiveSubscriptionInfoForIccId";
                case 5:
                    return "getActiveSubscriptionInfoForSimSlotIndex";
                case 6:
                    return "getActiveSubscriptionInfoList";
                case 7:
                    return "getActiveSubInfoCount";
                case 8:
                    return "getActiveSubInfoCountMax";
                case 9:
                    return "getAvailableSubscriptionInfoList";
                case 10:
                    return "getAccessibleSubscriptionInfoList";
                case 11:
                    return "requestEmbeddedSubscriptionInfoListRefresh";
                case 12:
                    return "addSubInfoRecord";
                case 13:
                    return "addSubInfo";
                case 14:
                    return "removeSubInfo";
                case 15:
                    return "setIconTint";
                case 16:
                    return "setDisplayNameUsingSrc";
                case 17:
                    return "setDisplayNumber";
                case 18:
                    return "setDataRoaming";
                case 19:
                    return "setOpportunistic";
                case 20:
                    return "createSubscriptionGroup";
                case 21:
                    return "setPreferredDataSubscriptionId";
                case 22:
                    return "getPreferredDataSubscriptionId";
                case 23:
                    return "getOpportunisticSubscriptions";
                case 24:
                    return "removeSubscriptionsFromGroup";
                case 25:
                    return "addSubscriptionsIntoGroup";
                case 26:
                    return "getSubscriptionsInGroup";
                case 27:
                    return "getSlotIndex";
                case 28:
                    return "getSubId";
                case 29:
                    return "getDefaultSubId";
                case 30:
                    return "clearSubInfo";
                case 31:
                    return "getPhoneId";
                case 32:
                    return "getDefaultDataSubId";
                case 33:
                    return "setDefaultDataSubId";
                case 34:
                    return "getDefaultVoiceSubId";
                case 35:
                    return "setDefaultVoiceSubId";
                case 36:
                    return "getDefaultSmsSubId";
                case 37:
                    return "setDefaultSmsSubId";
                case 38:
                    return "getActiveSubIdList";
                case 39:
                    return "setSubscriptionProperty";
                case 40:
                    return "getSubscriptionProperty";
                case 41:
                    return "setSubscriptionEnabled";
                case 42:
                    return "isSubscriptionEnabled";
                case 43:
                    return "getEnabledSubscriptionId";
                case 44:
                    return "getSimStateForSlotIndex";
                case 45:
                    return "isActiveSubId";
                case 46:
                    return "getActiveDataSubscriptionId";
                case 47:
                    return "canDisablePhysicalSubscription";
                case 48:
                    return "setUiccApplicationsEnabled";
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

        public static ISub asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISub)) {
                return (ISub) iInterfaceQueryLocalInterface;
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
                    List<SubscriptionInfo> allSubInfoList = getAllSubInfoList(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(allSubInfoList);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int allSubInfoCount = getAllSubInfoCount(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(allSubInfoCount);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    SubscriptionInfo activeSubscriptionInfo = getActiveSubscriptionInfo(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (activeSubscriptionInfo != null) {
                        parcel2.writeInt(1);
                        activeSubscriptionInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    SubscriptionInfo activeSubscriptionInfoForIccId = getActiveSubscriptionInfoForIccId(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (activeSubscriptionInfoForIccId != null) {
                        parcel2.writeInt(1);
                        activeSubscriptionInfoForIccId.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = getActiveSubscriptionInfoForSimSlotIndex(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (activeSubscriptionInfoForSimSlotIndex != null) {
                        parcel2.writeInt(1);
                        activeSubscriptionInfoForSimSlotIndex.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<SubscriptionInfo> activeSubscriptionInfoList = getActiveSubscriptionInfoList(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(activeSubscriptionInfoList);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int activeSubInfoCount = getActiveSubInfoCount(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(activeSubInfoCount);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int activeSubInfoCountMax = getActiveSubInfoCountMax();
                    parcel2.writeNoException();
                    parcel2.writeInt(activeSubInfoCountMax);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<SubscriptionInfo> availableSubscriptionInfoList = getAvailableSubscriptionInfoList(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(availableSubscriptionInfoList);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<SubscriptionInfo> accessibleSubscriptionInfoList = getAccessibleSubscriptionInfoList(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(accessibleSubscriptionInfoList);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestEmbeddedSubscriptionInfoListRefresh(parcel.readInt());
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iAddSubInfoRecord = addSubInfoRecord(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iAddSubInfoRecord);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iAddSubInfo = addSubInfo(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iAddSubInfo);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRemoveSubInfo = removeSubInfo(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iRemoveSubInfo);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iconTint = setIconTint(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iconTint);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    int displayNameUsingSrc = setDisplayNameUsingSrc(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(displayNameUsingSrc);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int displayNumber = setDisplayNumber(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(displayNumber);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dataRoaming = setDataRoaming(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(dataRoaming);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int opportunistic = setOpportunistic(parcel.readInt() != 0, parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(opportunistic);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelUuid parcelUuidCreateSubscriptionGroup = createSubscriptionGroup(parcel.createIntArray(), parcel.readString());
                    parcel2.writeNoException();
                    if (parcelUuidCreateSubscriptionGroup != null) {
                        parcel2.writeInt(1);
                        parcelUuidCreateSubscriptionGroup.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPreferredDataSubscriptionId(parcel.readInt(), parcel.readInt() != 0, ISetOpportunisticDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int preferredDataSubscriptionId = getPreferredDataSubscriptionId();
                    parcel2.writeNoException();
                    parcel2.writeInt(preferredDataSubscriptionId);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<SubscriptionInfo> opportunisticSubscriptions = getOpportunisticSubscriptions(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(opportunisticSubscriptions);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeSubscriptionsFromGroup(parcel.createIntArray(), parcel.readInt() != 0 ? ParcelUuid.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    addSubscriptionsIntoGroup(parcel.createIntArray(), parcel.readInt() != 0 ? ParcelUuid.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<SubscriptionInfo> subscriptionsInGroup = getSubscriptionsInGroup(parcel.readInt() != 0 ? ParcelUuid.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(subscriptionsInGroup);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int slotIndex = getSlotIndex(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(slotIndex);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] subId = getSubId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeIntArray(subId);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    int defaultSubId = getDefaultSubId();
                    parcel2.writeNoException();
                    parcel2.writeInt(defaultSubId);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iClearSubInfo = clearSubInfo();
                    parcel2.writeNoException();
                    parcel2.writeInt(iClearSubInfo);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    int phoneId = getPhoneId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(phoneId);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    int defaultDataSubId = getDefaultDataSubId();
                    parcel2.writeNoException();
                    parcel2.writeInt(defaultDataSubId);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDefaultDataSubId(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    int defaultVoiceSubId = getDefaultVoiceSubId();
                    parcel2.writeNoException();
                    parcel2.writeInt(defaultVoiceSubId);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDefaultVoiceSubId(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    int defaultSmsSubId = getDefaultSmsSubId();
                    parcel2.writeNoException();
                    parcel2.writeInt(defaultSmsSubId);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDefaultSmsSubId(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] activeSubIdList = getActiveSubIdList(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeIntArray(activeSubIdList);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    int subscriptionProperty = setSubscriptionProperty(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(subscriptionProperty);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    String subscriptionProperty2 = getSubscriptionProperty(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(subscriptionProperty2);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean subscriptionEnabled = setSubscriptionEnabled(parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(subscriptionEnabled ? 1 : 0);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSubscriptionEnabled = isSubscriptionEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSubscriptionEnabled ? 1 : 0);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    int enabledSubscriptionId = getEnabledSubscriptionId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(enabledSubscriptionId);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    int simStateForSlotIndex = getSimStateForSlotIndex(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(simStateForSlotIndex);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsActiveSubId = isActiveSubId(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsActiveSubId ? 1 : 0);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    int activeDataSubscriptionId = getActiveDataSubscriptionId();
                    parcel2.writeNoException();
                    parcel2.writeInt(activeDataSubscriptionId);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanDisablePhysicalSubscription = canDisablePhysicalSubscription();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanDisablePhysicalSubscription ? 1 : 0);
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    int uiccApplicationsEnabled = setUiccApplicationsEnabled(parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(uiccApplicationsEnabled);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISub {
            public static ISub sDefaultImpl;
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

            @Override // com.android.internal.telephony.ISub
            public List<SubscriptionInfo> getAllSubInfoList(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllSubInfoList(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(SubscriptionInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int getAllSubInfoCount(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllSubInfoCount(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public SubscriptionInfo getActiveSubscriptionInfo(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveSubscriptionInfo(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SubscriptionInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public SubscriptionInfo getActiveSubscriptionInfoForIccId(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveSubscriptionInfoForIccId(str, str2, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SubscriptionInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public SubscriptionInfo getActiveSubscriptionInfoForSimSlotIndex(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveSubscriptionInfoForSimSlotIndex(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SubscriptionInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public List<SubscriptionInfo> getActiveSubscriptionInfoList(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveSubscriptionInfoList(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(SubscriptionInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int getActiveSubInfoCount(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveSubInfoCount(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int getActiveSubInfoCountMax() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveSubInfoCountMax();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public List<SubscriptionInfo> getAvailableSubscriptionInfoList(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableSubscriptionInfoList(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(SubscriptionInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public List<SubscriptionInfo> getAccessibleSubscriptionInfoList(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAccessibleSubscriptionInfoList(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(SubscriptionInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public void requestEmbeddedSubscriptionInfoListRefresh(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestEmbeddedSubscriptionInfoListRefresh(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int addSubInfoRecord(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addSubInfoRecord(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int addSubInfo(String str, String str2, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addSubInfo(str, str2, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int removeSubInfo(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeSubInfo(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int setIconTint(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setIconTint(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int setDisplayNameUsingSrc(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDisplayNameUsingSrc(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int setDisplayNumber(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDisplayNumber(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int setDataRoaming(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDataRoaming(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int setOpportunistic(boolean z, int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setOpportunistic(z, i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public ParcelUuid createSubscriptionGroup(int[] iArr, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createSubscriptionGroup(iArr, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelUuid.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public void setPreferredDataSubscriptionId(int i, boolean z, ISetOpportunisticDataCallback iSetOpportunisticDataCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iSetOpportunisticDataCallback != null ? iSetOpportunisticDataCallback.asBinder() : null);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPreferredDataSubscriptionId(i, z, iSetOpportunisticDataCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int getPreferredDataSubscriptionId() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreferredDataSubscriptionId();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public List<SubscriptionInfo> getOpportunisticSubscriptions(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOpportunisticSubscriptions(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(SubscriptionInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public void removeSubscriptionsFromGroup(int[] iArr, ParcelUuid parcelUuid, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeSubscriptionsFromGroup(iArr, parcelUuid, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public void addSubscriptionsIntoGroup(int[] iArr, ParcelUuid parcelUuid, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addSubscriptionsIntoGroup(iArr, parcelUuid, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public List<SubscriptionInfo> getSubscriptionsInGroup(ParcelUuid parcelUuid, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubscriptionsInGroup(parcelUuid, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(SubscriptionInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int getSlotIndex(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSlotIndex(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int[] getSubId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int getDefaultSubId() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultSubId();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int clearSubInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearSubInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int getPhoneId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPhoneId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int getDefaultDataSubId() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultDataSubId();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public void setDefaultDataSubId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDefaultDataSubId(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int getDefaultVoiceSubId() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultVoiceSubId();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public void setDefaultVoiceSubId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDefaultVoiceSubId(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int getDefaultSmsSubId() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultSmsSubId();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public void setDefaultSmsSubId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDefaultSmsSubId(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int[] getActiveSubIdList(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveSubIdList(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int setSubscriptionProperty(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSubscriptionProperty(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public String getSubscriptionProperty(int i, String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubscriptionProperty(i, str, str2, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public boolean setSubscriptionEnabled(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSubscriptionEnabled(z, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public boolean isSubscriptionEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSubscriptionEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int getEnabledSubscriptionId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnabledSubscriptionId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int getSimStateForSlotIndex(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSimStateForSlotIndex(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public boolean isActiveSubId(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isActiveSubId(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int getActiveDataSubscriptionId() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveDataSubscriptionId();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public boolean canDisablePhysicalSubscription() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canDisablePhysicalSubscription();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISub
            public int setUiccApplicationsEnabled(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setUiccApplicationsEnabled(z, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISub iSub) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSub == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSub;
            return true;
        }

        public static ISub getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
