package com.android.internal.telephony;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Messenger;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.WorkSource;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telephony.CallForwardingInfo;
import android.telephony.CarrierRestrictionRules;
import android.telephony.CellIdentity;
import android.telephony.CellInfo;
import android.telephony.ClientRequestStats;
import android.telephony.ICellInfoCallback;
import android.telephony.IccOpenLogicalChannelResponse;
import android.telephony.NeighboringCellInfo;
import android.telephony.NetworkScanRequest;
import android.telephony.PhoneNumberRange;
import android.telephony.RadioAccessFamily;
import android.telephony.RadioAccessSpecifier;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyHistogram;
import android.telephony.UiccCardInfo;
import android.telephony.UiccSlotInfo;
import android.telephony.VisualVoicemailSmsFilterSettings;
import android.telephony.emergency.EmergencyNumber;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IImsConfig;
import android.telephony.ims.aidl.IImsConfigCallback;
import android.telephony.ims.aidl.IImsMmTelFeature;
import android.telephony.ims.aidl.IImsRcsFeature;
import android.telephony.ims.aidl.IImsRegistration;
import android.telephony.ims.aidl.IImsRegistrationCallback;
import com.android.ims.internal.IImsServiceFeatureCallback;
import com.android.internal.telephony.IBooleanConsumer;
import com.android.internal.telephony.IIntegerConsumer;
import com.android.internal.telephony.INumberVerificationCallback;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public interface ITelephony extends IInterface {

    public static class Default implements ITelephony {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void cacheMmTelCapabilityProvisioning(int i, int i2, int i3, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void call(String str, String str2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean canChangeDtmfToneLength(int i, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean canConnectTo5GInDsdsMode() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void carrierActionReportDefaultNetworkStatus(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void carrierActionResetAll(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void carrierActionSetMeteredApnsEnabled(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void carrierActionSetRadioEnabled(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int changeIccLockPassword(int i, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int checkCarrierPrivilegesForPackage(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int checkCarrierPrivilegesForPackageAnyPhone(String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void dial(String str) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean disableDataConnectivity() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void disableIms(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void disableLocationUpdates() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void disableLocationUpdatesForSubscriber(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void disableVisualVoicemailSmsFilter(String str, int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean doesSwitchMultiSimConfigTriggerReboot(int i, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean enableDataConnectivity() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void enableIms(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void enableLocationUpdates() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void enableLocationUpdatesForSubscriber(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean enableModemForSlot(int i, boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void enableVideoCalling(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void enableVisualVoicemailSmsFilter(String str, int i, VisualVoicemailSmsFilterSettings visualVoicemailSmsFilterSettings) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void enqueueSmsPickResult(String str, String str2, IIntegerConsumer iIntegerConsumer) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void factoryReset(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getActivePhoneType() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getActivePhoneTypeForSlot(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public VisualVoicemailSmsFilterSettings getActiveVisualVoicemailSmsFilterSettings(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getAidForAppType(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<CellInfo> getAllCellInfo(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public CarrierRestrictionRules getAllowedCarriers() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public long getAllowedNetworkTypes(int i) throws RemoteException {
            return 0L;
        }

        @Override // com.android.internal.telephony.ITelephony
        public long getAllowedNetworkTypesForReason(int i, int i2) throws RemoteException {
            return 0L;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getBoundImsServicePackage(int i, boolean z, int i2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCalculatedPreferredNetworkType(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public CallForwardingInfo getCallForwarding(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCallState() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCallStateForSlot(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCallWaitingStatus(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCardIdForDefaultEuicc(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCarrierIdFromMccMnc(int i, String str, boolean z) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCarrierIdListVersion(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<String> getCarrierPackageNamesForIntentAndPhone(Intent intent, int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCarrierPrivilegeStatus(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCarrierPrivilegeStatusForUid(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCdmaEriIconIndex(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCdmaEriIconIndexForSubscriber(int i, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCdmaEriIconMode(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCdmaEriIconModeForSubscriber(int i, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCdmaEriText(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCdmaEriTextForSubscriber(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCdmaMdn(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCdmaMin(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCdmaPrlVersion(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCdmaRoamingMode(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public CellIdentity getCellLocation(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public CellNetworkScanResult getCellNetworkScanResults(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<String> getCertsFromCarrierPrivilegeAccessRules(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<ClientRequestStats> getClientRequestStats(String str, String str2, int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCurrentPackageName() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataActivationState(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataActivity() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataActivityForSubId(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean getDataEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataNetworkType(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataNetworkTypeForSubscriber(int i, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataState() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataStateForSubId(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getDeviceId(String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getDeviceIdWithFeature(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getDeviceSoftwareVersionForSlot(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public long getEffectiveAllowedNetworkTypes(int i) throws RemoteException {
            return 0L;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean getEmergencyCallbackMode(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getEmergencyNumberDbVersion(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public Map getEmergencyNumberList(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<String> getEmergencyNumberListTestMode() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getEsn(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String[] getForbiddenPlmns(int i, int i2, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getImeiForSlot(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public IImsConfig getImsConfig(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void getImsMmTelFeatureState(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void getImsMmTelRegistrationState(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void getImsMmTelRegistrationTransportType(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getImsProvisioningInt(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean getImsProvisioningStatusForCapability(int i, int i2, int i3) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getImsProvisioningString(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getImsRegTechnologyForMmTel(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public IImsRegistration getImsRegistration(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getLine1AlphaTagForDisplay(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getLine1NumberForDisplay(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getLteOnCdmaMode(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getLteOnCdmaModeForSubscriber(int i, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getManualNetworkSelectionPlmn(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getManufacturerCodeForSlot(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getMeidForSlot(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String[] getMergedImsisFromGroup(int i, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String[] getMergedSubscriberIds(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public IImsMmTelFeature getMmTelFeatureAndListen(int i, IImsServiceFeatureCallback iImsServiceFeatureCallback) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getMmsUAProfUrl(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getMmsUserAgent(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<NeighboringCellInfo> getNeighboringCellInfo(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getNetworkCountryIsoForPhone(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getNetworkSelectionMode(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getNetworkTypeForSubscriber(int i, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getNumberOfModemsWithSimultaneousDataConnections(int i, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<String> getPackagesWithCarrierPrivileges(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<String> getPackagesWithCarrierPrivilegesForAllPhones() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String[] getPcscfAddress(String str, String str2, String str3) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public PhoneAccountHandle getPhoneAccountHandleForSubscriptionId(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getPreferredNetworkType(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getRadioAccessFamily(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getRadioHalVersion() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getRadioPowerState(int i, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public IImsRcsFeature getRcsFeatureAndListen(int i, IImsServiceFeatureCallback iImsServiceFeatureCallback) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean getRcsProvisioningStatusForCapability(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public ServiceState getServiceStateForSubscriber(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public SignalStrength getSignalStrength(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getSimLocaleForSubscriber(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int[] getSlotsMapping() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getSubIdForPhoneAccount(PhoneAccount phoneAccount) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getSubIdForPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getSubscriptionCarrierId(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getSubscriptionCarrierName(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getSubscriptionSpecificCarrierId(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getSubscriptionSpecificCarrierName(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<TelephonyHistogram> getTelephonyHistograms() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getTypeAllocationCodeForSlot(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<UiccCardInfo> getUiccCardsInfo(String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public UiccSlotInfo[] getUiccSlotsInfo() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getVisualVoicemailPackageName(String str, String str2, int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public Bundle getVisualVoicemailSettings(String str, int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public VisualVoicemailSmsFilterSettings getVisualVoicemailSmsFilterSettings(String str, int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getVoWiFiModeSetting(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getVoWiFiRoamingModeSetting(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getVoiceActivationState(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getVoiceMessageCountForSubscriber(int i, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getVoiceNetworkTypeForSubscriber(int i, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public Uri getVoicemailRingtoneUri(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean handlePinMmi(String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean handlePinMmiForSubscriber(int i, String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void handleUssdRequest(int i, String str, ResultReceiver resultReceiver) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean hasIccCard() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean hasIccCardUsingSlotIndex(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean iccCloseLogicalChannel(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean iccCloseLogicalChannelBySlot(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public byte[] iccExchangeSimIO(int i, int i2, int i3, int i4, int i5, int i6, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public IccOpenLogicalChannelResponse iccOpenLogicalChannel(int i, String str, String str2, int i2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public IccOpenLogicalChannelResponse iccOpenLogicalChannelBySlot(int i, String str, String str2, int i2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String iccTransmitApduBasicChannel(int i, String str, int i2, int i3, int i4, int i5, int i6, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String iccTransmitApduBasicChannelBySlot(int i, String str, int i2, int i3, int i4, int i5, int i6, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String iccTransmitApduLogicalChannel(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String iccTransmitApduLogicalChannelBySlot(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int invokeOemRilRequestRaw(byte[] bArr, byte[] bArr2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isAdvancedCallingSettingEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isApnMetered(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isApplicationOnUicc(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isAvailable(int i, int i2, int i3) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isCapable(int i, int i2, int i3) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isConcurrentVoiceAndDataAllowed(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isDataAllowedInVoiceCall(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isDataConnectivityPossible(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isDataEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isDataEnabledForApn(int i, int i2, String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isDataRoamingEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isEmergencyNumber(String str, boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isHearingAidCompatibilitySupported() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isIccLockEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isImsRegistered(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isInEmergencySmsMode() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isManualNetworkSelectionAllowed(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isMmTelCapabilityProvisionedInCache(int i, int i2, int i3) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void isMmTelCapabilitySupported(int i, IIntegerConsumer iIntegerConsumer, int i2, int i3) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isModemEnabledForSlot(int i, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int isMultiSimSupported(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isMvnoMatched(int i, int i2, String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRadioOn(String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRadioOnForSubscriber(int i, String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRadioOnForSubscriberWithFeature(int i, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRadioOnWithFeature(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRttSupported(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isTetheringApnRequiredForSubscriber(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isTtyModeSupported() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isTtyOverVolteEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isUserDataEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isVideoCallingEnabled(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isVideoTelephonyAvailable(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isVoWiFiRoamingSettingEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isVoWiFiSettingEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isVoicemailVibrationEnabled(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isVtSettingEnabled(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isWifiCallingAvailable(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isWorldPhone(int i, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean needMobileRadioShutdown() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean needsOtaServiceProvisioning() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void notifyOtaEmergencyNumberDbInstalled() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void notifyRcsAutoConfigurationReceived(int i, byte[] bArr, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public String nvReadItem(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean nvWriteCdmaPrl(byte[] bArr) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean nvWriteItem(int i, String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean rebootModem(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void refreshUiccProfile(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void registerImsProvisioningChangedCallback(int i, IImsConfigCallback iImsConfigCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void registerImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void registerMmTelCapabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void requestCellInfoUpdate(int i, ICellInfoCallback iCellInfoCallback, String str, String str2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void requestCellInfoUpdateWithWorkSource(int i, ICellInfoCallback iCellInfoCallback, String str, String str2, WorkSource workSource) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void requestModemActivityInfo(ResultReceiver resultReceiver) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int requestNetworkScan(int i, NetworkScanRequest networkScanRequest, Messenger messenger, IBinder iBinder, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void requestNumberVerification(PhoneNumberRange phoneNumberRange, long j, INumberVerificationCallback iNumberVerificationCallback, String str) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void requestUserActivityNotification() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void resetIms(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean resetModemConfig(int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void resetOtaEmergencyNumberDbFilePath() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void sendDialerSpecialCode(String str, String str2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public String sendEnvelopeWithStatus(int i, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void sendVisualVoicemailSmsForSubscriber(String str, String str2, int i, String str3, int i2, String str4, PendingIntent pendingIntent) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setAdvancedCallingSettingEnabled(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int setAllowedCarriers(CarrierRestrictionRules carrierRestrictionRules) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setAllowedNetworkTypes(int i, long j) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setAllowedNetworkTypesForReason(int i, int i2, long j) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setAlwaysAllowMmsData(int i, boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setAlwaysReportSignalStrength(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setBoundImsServiceOverride(int i, boolean z, int[] iArr, String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setCallForwarding(int i, CallForwardingInfo callForwardingInfo) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setCallWaitingStatus(int i, boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setCarrierTestOverride(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setCdmaRoamingMode(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setCdmaSubscriptionMode(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setCellInfoListRate(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setCepEnabled(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setDataActivationState(int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setDataAllowedDuringVoiceCall(int i, boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setDataRoamingEnabled(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int setForbiddenPlmns(int i, int i2, List<String> list, String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int setIccLockEnabled(int i, boolean z, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int setImsProvisioningInt(int i, int i2, int i3) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setImsProvisioningStatusForCapability(int i, int i2, int i3, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int setImsProvisioningString(int i, int i2, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setImsRegistrationState(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setLine1NumberForDisplayForSubscriber(int i, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setMultiSimCarrierRestriction(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setNetworkSelectionModeAutomatic(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setNetworkSelectionModeManual(int i, OperatorInfo operatorInfo, boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setOperatorBrandOverride(int i, String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setPolicyDataEnabled(boolean z, int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setPreferredNetworkType(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setRadio(boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setRadioCapability(RadioAccessFamily[] radioAccessFamilyArr) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setRadioForSubscriber(int i, boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setRadioPower(boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setRcsProvisioningStatusForCapability(int i, int i2, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setRoamingOverride(int i, List<String> list, List<String> list2, List<String> list3, List<String> list4) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setRttCapabilitySetting(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setSimPowerStateForSlot(int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setSystemSelectionChannels(List<RadioAccessSpecifier> list, int i, IBooleanConsumer iBooleanConsumer) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setUserDataEnabled(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoWiFiModeSetting(int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoWiFiNonPersistent(int i, boolean z, int i2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoWiFiRoamingModeSetting(int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoWiFiRoamingSettingEnabled(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoWiFiSettingEnabled(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoiceActivationState(int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setVoiceMailNumber(int i, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoicemailRingtoneUri(String str, PhoneAccountHandle phoneAccountHandle, Uri uri) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoicemailVibrationEnabled(String str, PhoneAccountHandle phoneAccountHandle, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVtSettingEnabled(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void shutdownMobileRadios() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void startEmergencyCallbackMode() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void stopNetworkScan(int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean supplyPinForSubscriber(int i, String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int[] supplyPinReportResultForSubscriber(int i, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean supplyPukForSubscriber(int i, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int[] supplyPukReportResultForSubscriber(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void switchMultiSimConfig(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean switchSlots(int[] iArr) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void toggleRadioOnOff() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void toggleRadioOnOffForSubscriber(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void unregisterImsFeatureCallback(int i, int i2, IImsServiceFeatureCallback iImsServiceFeatureCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void unregisterImsProvisioningChangedCallback(int i, IImsConfigCallback iImsConfigCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void unregisterImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void unregisterMmTelCapabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void updateEmergencyNumberListTestMode(int i, EmergencyNumber emergencyNumber) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void updateOtaEmergencyNumberDbFilePath(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void updateServiceLocation() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void updateServiceLocationForSubscriber(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void userActivity() throws RemoteException {
        }
    }

    void cacheMmTelCapabilityProvisioning(int i, int i2, int i3, boolean z) throws RemoteException;

    void call(String str, String str2) throws RemoteException;

    boolean canChangeDtmfToneLength(int i, String str, String str2) throws RemoteException;

    boolean canConnectTo5GInDsdsMode() throws RemoteException;

    void carrierActionReportDefaultNetworkStatus(int i, boolean z) throws RemoteException;

    void carrierActionResetAll(int i) throws RemoteException;

    void carrierActionSetMeteredApnsEnabled(int i, boolean z) throws RemoteException;

    void carrierActionSetRadioEnabled(int i, boolean z) throws RemoteException;

    int changeIccLockPassword(int i, String str, String str2) throws RemoteException;

    int checkCarrierPrivilegesForPackage(int i, String str) throws RemoteException;

    int checkCarrierPrivilegesForPackageAnyPhone(String str) throws RemoteException;

    void dial(String str) throws RemoteException;

    boolean disableDataConnectivity() throws RemoteException;

    void disableIms(int i) throws RemoteException;

    void disableLocationUpdates() throws RemoteException;

    void disableLocationUpdatesForSubscriber(int i) throws RemoteException;

    void disableVisualVoicemailSmsFilter(String str, int i) throws RemoteException;

    boolean doesSwitchMultiSimConfigTriggerReboot(int i, String str, String str2) throws RemoteException;

    boolean enableDataConnectivity() throws RemoteException;

    void enableIms(int i) throws RemoteException;

    void enableLocationUpdates() throws RemoteException;

    void enableLocationUpdatesForSubscriber(int i) throws RemoteException;

    boolean enableModemForSlot(int i, boolean z) throws RemoteException;

    void enableVideoCalling(boolean z) throws RemoteException;

    void enableVisualVoicemailSmsFilter(String str, int i, VisualVoicemailSmsFilterSettings visualVoicemailSmsFilterSettings) throws RemoteException;

    void enqueueSmsPickResult(String str, String str2, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    void factoryReset(int i) throws RemoteException;

    int getActivePhoneType() throws RemoteException;

    int getActivePhoneTypeForSlot(int i) throws RemoteException;

    VisualVoicemailSmsFilterSettings getActiveVisualVoicemailSmsFilterSettings(int i) throws RemoteException;

    String getAidForAppType(int i, int i2) throws RemoteException;

    List<CellInfo> getAllCellInfo(String str, String str2) throws RemoteException;

    CarrierRestrictionRules getAllowedCarriers() throws RemoteException;

    long getAllowedNetworkTypes(int i) throws RemoteException;

    long getAllowedNetworkTypesForReason(int i, int i2) throws RemoteException;

    String getBoundImsServicePackage(int i, boolean z, int i2) throws RemoteException;

    int getCalculatedPreferredNetworkType(String str, String str2) throws RemoteException;

    CallForwardingInfo getCallForwarding(int i, int i2) throws RemoteException;

    int getCallState() throws RemoteException;

    int getCallStateForSlot(int i) throws RemoteException;

    int getCallWaitingStatus(int i) throws RemoteException;

    int getCardIdForDefaultEuicc(int i, String str) throws RemoteException;

    int getCarrierIdFromMccMnc(int i, String str, boolean z) throws RemoteException;

    int getCarrierIdListVersion(int i) throws RemoteException;

    List<String> getCarrierPackageNamesForIntentAndPhone(Intent intent, int i) throws RemoteException;

    int getCarrierPrivilegeStatus(int i) throws RemoteException;

    int getCarrierPrivilegeStatusForUid(int i, int i2) throws RemoteException;

    int getCdmaEriIconIndex(String str, String str2) throws RemoteException;

    int getCdmaEriIconIndexForSubscriber(int i, String str, String str2) throws RemoteException;

    int getCdmaEriIconMode(String str, String str2) throws RemoteException;

    int getCdmaEriIconModeForSubscriber(int i, String str, String str2) throws RemoteException;

    String getCdmaEriText(String str, String str2) throws RemoteException;

    String getCdmaEriTextForSubscriber(int i, String str, String str2) throws RemoteException;

    String getCdmaMdn(int i) throws RemoteException;

    String getCdmaMin(int i) throws RemoteException;

    String getCdmaPrlVersion(int i) throws RemoteException;

    int getCdmaRoamingMode(int i) throws RemoteException;

    CellIdentity getCellLocation(String str, String str2) throws RemoteException;

    CellNetworkScanResult getCellNetworkScanResults(int i, String str, String str2) throws RemoteException;

    List<String> getCertsFromCarrierPrivilegeAccessRules(int i) throws RemoteException;

    List<ClientRequestStats> getClientRequestStats(String str, String str2, int i) throws RemoteException;

    String getCurrentPackageName() throws RemoteException;

    int getDataActivationState(int i, String str) throws RemoteException;

    int getDataActivity() throws RemoteException;

    int getDataActivityForSubId(int i) throws RemoteException;

    boolean getDataEnabled(int i) throws RemoteException;

    int getDataNetworkType(String str, String str2) throws RemoteException;

    int getDataNetworkTypeForSubscriber(int i, String str, String str2) throws RemoteException;

    int getDataState() throws RemoteException;

    int getDataStateForSubId(int i) throws RemoteException;

    String getDeviceId(String str) throws RemoteException;

    String getDeviceIdWithFeature(String str, String str2) throws RemoteException;

    String getDeviceSoftwareVersionForSlot(int i, String str, String str2) throws RemoteException;

    long getEffectiveAllowedNetworkTypes(int i) throws RemoteException;

    boolean getEmergencyCallbackMode(int i) throws RemoteException;

    int getEmergencyNumberDbVersion(int i) throws RemoteException;

    Map getEmergencyNumberList(String str, String str2) throws RemoteException;

    List<String> getEmergencyNumberListTestMode() throws RemoteException;

    String getEsn(int i) throws RemoteException;

    String[] getForbiddenPlmns(int i, int i2, String str, String str2) throws RemoteException;

    String getImeiForSlot(int i, String str, String str2) throws RemoteException;

    IImsConfig getImsConfig(int i, int i2) throws RemoteException;

    void getImsMmTelFeatureState(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    void getImsMmTelRegistrationState(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    void getImsMmTelRegistrationTransportType(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    int getImsProvisioningInt(int i, int i2) throws RemoteException;

    boolean getImsProvisioningStatusForCapability(int i, int i2, int i3) throws RemoteException;

    String getImsProvisioningString(int i, int i2) throws RemoteException;

    int getImsRegTechnologyForMmTel(int i) throws RemoteException;

    IImsRegistration getImsRegistration(int i, int i2) throws RemoteException;

    String getLine1AlphaTagForDisplay(int i, String str, String str2) throws RemoteException;

    String getLine1NumberForDisplay(int i, String str, String str2) throws RemoteException;

    int getLteOnCdmaMode(String str, String str2) throws RemoteException;

    int getLteOnCdmaModeForSubscriber(int i, String str, String str2) throws RemoteException;

    String getManualNetworkSelectionPlmn(int i) throws RemoteException;

    String getManufacturerCodeForSlot(int i) throws RemoteException;

    String getMeidForSlot(int i, String str, String str2) throws RemoteException;

    String[] getMergedImsisFromGroup(int i, String str) throws RemoteException;

    String[] getMergedSubscriberIds(int i, String str, String str2) throws RemoteException;

    IImsMmTelFeature getMmTelFeatureAndListen(int i, IImsServiceFeatureCallback iImsServiceFeatureCallback) throws RemoteException;

    String getMmsUAProfUrl(int i) throws RemoteException;

    String getMmsUserAgent(int i) throws RemoteException;

    List<NeighboringCellInfo> getNeighboringCellInfo(String str, String str2) throws RemoteException;

    String getNetworkCountryIsoForPhone(int i) throws RemoteException;

    int getNetworkSelectionMode(int i) throws RemoteException;

    int getNetworkTypeForSubscriber(int i, String str, String str2) throws RemoteException;

    int getNumberOfModemsWithSimultaneousDataConnections(int i, String str, String str2) throws RemoteException;

    List<String> getPackagesWithCarrierPrivileges(int i) throws RemoteException;

    List<String> getPackagesWithCarrierPrivilegesForAllPhones() throws RemoteException;

    String[] getPcscfAddress(String str, String str2, String str3) throws RemoteException;

    PhoneAccountHandle getPhoneAccountHandleForSubscriptionId(int i) throws RemoteException;

    int getPreferredNetworkType(int i) throws RemoteException;

    int getRadioAccessFamily(int i, String str) throws RemoteException;

    int getRadioHalVersion() throws RemoteException;

    int getRadioPowerState(int i, String str, String str2) throws RemoteException;

    IImsRcsFeature getRcsFeatureAndListen(int i, IImsServiceFeatureCallback iImsServiceFeatureCallback) throws RemoteException;

    boolean getRcsProvisioningStatusForCapability(int i, int i2) throws RemoteException;

    ServiceState getServiceStateForSubscriber(int i, String str, String str2) throws RemoteException;

    SignalStrength getSignalStrength(int i) throws RemoteException;

    String getSimLocaleForSubscriber(int i) throws RemoteException;

    int[] getSlotsMapping() throws RemoteException;

    int getSubIdForPhoneAccount(PhoneAccount phoneAccount) throws RemoteException;

    int getSubIdForPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException;

    int getSubscriptionCarrierId(int i) throws RemoteException;

    String getSubscriptionCarrierName(int i) throws RemoteException;

    int getSubscriptionSpecificCarrierId(int i) throws RemoteException;

    String getSubscriptionSpecificCarrierName(int i) throws RemoteException;

    List<TelephonyHistogram> getTelephonyHistograms() throws RemoteException;

    String getTypeAllocationCodeForSlot(int i) throws RemoteException;

    List<UiccCardInfo> getUiccCardsInfo(String str) throws RemoteException;

    UiccSlotInfo[] getUiccSlotsInfo() throws RemoteException;

    String getVisualVoicemailPackageName(String str, String str2, int i) throws RemoteException;

    Bundle getVisualVoicemailSettings(String str, int i) throws RemoteException;

    VisualVoicemailSmsFilterSettings getVisualVoicemailSmsFilterSettings(String str, int i) throws RemoteException;

    int getVoWiFiModeSetting(int i) throws RemoteException;

    int getVoWiFiRoamingModeSetting(int i) throws RemoteException;

    int getVoiceActivationState(int i, String str) throws RemoteException;

    int getVoiceMessageCountForSubscriber(int i, String str, String str2) throws RemoteException;

    int getVoiceNetworkTypeForSubscriber(int i, String str, String str2) throws RemoteException;

    Uri getVoicemailRingtoneUri(PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    boolean handlePinMmi(String str) throws RemoteException;

    boolean handlePinMmiForSubscriber(int i, String str) throws RemoteException;

    void handleUssdRequest(int i, String str, ResultReceiver resultReceiver) throws RemoteException;

    boolean hasIccCard() throws RemoteException;

    boolean hasIccCardUsingSlotIndex(int i) throws RemoteException;

    boolean iccCloseLogicalChannel(int i, int i2) throws RemoteException;

    boolean iccCloseLogicalChannelBySlot(int i, int i2) throws RemoteException;

    byte[] iccExchangeSimIO(int i, int i2, int i3, int i4, int i5, int i6, String str) throws RemoteException;

    IccOpenLogicalChannelResponse iccOpenLogicalChannel(int i, String str, String str2, int i2) throws RemoteException;

    IccOpenLogicalChannelResponse iccOpenLogicalChannelBySlot(int i, String str, String str2, int i2) throws RemoteException;

    String iccTransmitApduBasicChannel(int i, String str, int i2, int i3, int i4, int i5, int i6, String str2) throws RemoteException;

    String iccTransmitApduBasicChannelBySlot(int i, String str, int i2, int i3, int i4, int i5, int i6, String str2) throws RemoteException;

    String iccTransmitApduLogicalChannel(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str) throws RemoteException;

    String iccTransmitApduLogicalChannelBySlot(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str) throws RemoteException;

    int invokeOemRilRequestRaw(byte[] bArr, byte[] bArr2) throws RemoteException;

    boolean isAdvancedCallingSettingEnabled(int i) throws RemoteException;

    boolean isApnMetered(int i, int i2) throws RemoteException;

    boolean isApplicationOnUicc(int i, int i2) throws RemoteException;

    boolean isAvailable(int i, int i2, int i3) throws RemoteException;

    boolean isCapable(int i, int i2, int i3) throws RemoteException;

    boolean isConcurrentVoiceAndDataAllowed(int i) throws RemoteException;

    boolean isDataAllowedInVoiceCall(int i) throws RemoteException;

    boolean isDataConnectivityPossible(int i) throws RemoteException;

    boolean isDataEnabled(int i) throws RemoteException;

    boolean isDataEnabledForApn(int i, int i2, String str) throws RemoteException;

    boolean isDataRoamingEnabled(int i) throws RemoteException;

    boolean isEmergencyNumber(String str, boolean z) throws RemoteException;

    boolean isHearingAidCompatibilitySupported() throws RemoteException;

    boolean isIccLockEnabled(int i) throws RemoteException;

    boolean isImsRegistered(int i) throws RemoteException;

    boolean isInEmergencySmsMode() throws RemoteException;

    boolean isManualNetworkSelectionAllowed(int i) throws RemoteException;

    boolean isMmTelCapabilityProvisionedInCache(int i, int i2, int i3) throws RemoteException;

    void isMmTelCapabilitySupported(int i, IIntegerConsumer iIntegerConsumer, int i2, int i3) throws RemoteException;

    boolean isModemEnabledForSlot(int i, String str, String str2) throws RemoteException;

    int isMultiSimSupported(String str, String str2) throws RemoteException;

    boolean isMvnoMatched(int i, int i2, String str) throws RemoteException;

    boolean isRadioOn(String str) throws RemoteException;

    boolean isRadioOnForSubscriber(int i, String str) throws RemoteException;

    boolean isRadioOnForSubscriberWithFeature(int i, String str, String str2) throws RemoteException;

    boolean isRadioOnWithFeature(String str, String str2) throws RemoteException;

    boolean isRttSupported(int i) throws RemoteException;

    boolean isTetheringApnRequiredForSubscriber(int i) throws RemoteException;

    boolean isTtyModeSupported() throws RemoteException;

    boolean isTtyOverVolteEnabled(int i) throws RemoteException;

    boolean isUserDataEnabled(int i) throws RemoteException;

    boolean isVideoCallingEnabled(String str, String str2) throws RemoteException;

    boolean isVideoTelephonyAvailable(int i) throws RemoteException;

    boolean isVoWiFiRoamingSettingEnabled(int i) throws RemoteException;

    boolean isVoWiFiSettingEnabled(int i) throws RemoteException;

    boolean isVoicemailVibrationEnabled(PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    boolean isVtSettingEnabled(int i) throws RemoteException;

    boolean isWifiCallingAvailable(int i) throws RemoteException;

    boolean isWorldPhone(int i, String str, String str2) throws RemoteException;

    boolean needMobileRadioShutdown() throws RemoteException;

    boolean needsOtaServiceProvisioning() throws RemoteException;

    void notifyOtaEmergencyNumberDbInstalled() throws RemoteException;

    void notifyRcsAutoConfigurationReceived(int i, byte[] bArr, boolean z) throws RemoteException;

    String nvReadItem(int i) throws RemoteException;

    boolean nvWriteCdmaPrl(byte[] bArr) throws RemoteException;

    boolean nvWriteItem(int i, String str) throws RemoteException;

    boolean rebootModem(int i) throws RemoteException;

    void refreshUiccProfile(int i) throws RemoteException;

    void registerImsProvisioningChangedCallback(int i, IImsConfigCallback iImsConfigCallback) throws RemoteException;

    void registerImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException;

    void registerMmTelCapabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    void requestCellInfoUpdate(int i, ICellInfoCallback iCellInfoCallback, String str, String str2) throws RemoteException;

    void requestCellInfoUpdateWithWorkSource(int i, ICellInfoCallback iCellInfoCallback, String str, String str2, WorkSource workSource) throws RemoteException;

    void requestModemActivityInfo(ResultReceiver resultReceiver) throws RemoteException;

    int requestNetworkScan(int i, NetworkScanRequest networkScanRequest, Messenger messenger, IBinder iBinder, String str, String str2) throws RemoteException;

    void requestNumberVerification(PhoneNumberRange phoneNumberRange, long j, INumberVerificationCallback iNumberVerificationCallback, String str) throws RemoteException;

    void requestUserActivityNotification() throws RemoteException;

    void resetIms(int i) throws RemoteException;

    boolean resetModemConfig(int i) throws RemoteException;

    void resetOtaEmergencyNumberDbFilePath() throws RemoteException;

    void sendDialerSpecialCode(String str, String str2) throws RemoteException;

    String sendEnvelopeWithStatus(int i, String str) throws RemoteException;

    void sendVisualVoicemailSmsForSubscriber(String str, String str2, int i, String str3, int i2, String str4, PendingIntent pendingIntent) throws RemoteException;

    void setAdvancedCallingSettingEnabled(int i, boolean z) throws RemoteException;

    int setAllowedCarriers(CarrierRestrictionRules carrierRestrictionRules) throws RemoteException;

    boolean setAllowedNetworkTypes(int i, long j) throws RemoteException;

    boolean setAllowedNetworkTypesForReason(int i, int i2, long j) throws RemoteException;

    boolean setAlwaysAllowMmsData(int i, boolean z) throws RemoteException;

    void setAlwaysReportSignalStrength(int i, boolean z) throws RemoteException;

    boolean setBoundImsServiceOverride(int i, boolean z, int[] iArr, String str) throws RemoteException;

    boolean setCallForwarding(int i, CallForwardingInfo callForwardingInfo) throws RemoteException;

    boolean setCallWaitingStatus(int i, boolean z) throws RemoteException;

    void setCarrierTestOverride(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) throws RemoteException;

    boolean setCdmaRoamingMode(int i, int i2) throws RemoteException;

    boolean setCdmaSubscriptionMode(int i, int i2) throws RemoteException;

    void setCellInfoListRate(int i) throws RemoteException;

    void setCepEnabled(boolean z) throws RemoteException;

    void setDataActivationState(int i, int i2) throws RemoteException;

    boolean setDataAllowedDuringVoiceCall(int i, boolean z) throws RemoteException;

    void setDataRoamingEnabled(int i, boolean z) throws RemoteException;

    int setForbiddenPlmns(int i, int i2, List<String> list, String str, String str2) throws RemoteException;

    int setIccLockEnabled(int i, boolean z, String str) throws RemoteException;

    int setImsProvisioningInt(int i, int i2, int i3) throws RemoteException;

    void setImsProvisioningStatusForCapability(int i, int i2, int i3, boolean z) throws RemoteException;

    int setImsProvisioningString(int i, int i2, String str) throws RemoteException;

    void setImsRegistrationState(boolean z) throws RemoteException;

    boolean setLine1NumberForDisplayForSubscriber(int i, String str, String str2) throws RemoteException;

    void setMultiSimCarrierRestriction(boolean z) throws RemoteException;

    void setNetworkSelectionModeAutomatic(int i) throws RemoteException;

    boolean setNetworkSelectionModeManual(int i, OperatorInfo operatorInfo, boolean z) throws RemoteException;

    boolean setOperatorBrandOverride(int i, String str) throws RemoteException;

    void setPolicyDataEnabled(boolean z, int i) throws RemoteException;

    boolean setPreferredNetworkType(int i, int i2) throws RemoteException;

    boolean setRadio(boolean z) throws RemoteException;

    void setRadioCapability(RadioAccessFamily[] radioAccessFamilyArr) throws RemoteException;

    boolean setRadioForSubscriber(int i, boolean z) throws RemoteException;

    boolean setRadioPower(boolean z) throws RemoteException;

    void setRcsProvisioningStatusForCapability(int i, int i2, boolean z) throws RemoteException;

    boolean setRoamingOverride(int i, List<String> list, List<String> list2, List<String> list3, List<String> list4) throws RemoteException;

    void setRttCapabilitySetting(int i, boolean z) throws RemoteException;

    void setSimPowerStateForSlot(int i, int i2) throws RemoteException;

    void setSystemSelectionChannels(List<RadioAccessSpecifier> list, int i, IBooleanConsumer iBooleanConsumer) throws RemoteException;

    void setUserDataEnabled(int i, boolean z) throws RemoteException;

    void setVoWiFiModeSetting(int i, int i2) throws RemoteException;

    void setVoWiFiNonPersistent(int i, boolean z, int i2) throws RemoteException;

    void setVoWiFiRoamingModeSetting(int i, int i2) throws RemoteException;

    void setVoWiFiRoamingSettingEnabled(int i, boolean z) throws RemoteException;

    void setVoWiFiSettingEnabled(int i, boolean z) throws RemoteException;

    void setVoiceActivationState(int i, int i2) throws RemoteException;

    boolean setVoiceMailNumber(int i, String str, String str2) throws RemoteException;

    void setVoicemailRingtoneUri(String str, PhoneAccountHandle phoneAccountHandle, Uri uri) throws RemoteException;

    void setVoicemailVibrationEnabled(String str, PhoneAccountHandle phoneAccountHandle, boolean z) throws RemoteException;

    void setVtSettingEnabled(int i, boolean z) throws RemoteException;

    void shutdownMobileRadios() throws RemoteException;

    void startEmergencyCallbackMode() throws RemoteException;

    void stopNetworkScan(int i, int i2) throws RemoteException;

    boolean supplyPinForSubscriber(int i, String str) throws RemoteException;

    int[] supplyPinReportResultForSubscriber(int i, String str) throws RemoteException;

    boolean supplyPukForSubscriber(int i, String str, String str2) throws RemoteException;

    int[] supplyPukReportResultForSubscriber(int i, String str, String str2) throws RemoteException;

    void switchMultiSimConfig(int i) throws RemoteException;

    boolean switchSlots(int[] iArr) throws RemoteException;

    void toggleRadioOnOff() throws RemoteException;

    void toggleRadioOnOffForSubscriber(int i) throws RemoteException;

    void unregisterImsFeatureCallback(int i, int i2, IImsServiceFeatureCallback iImsServiceFeatureCallback) throws RemoteException;

    void unregisterImsProvisioningChangedCallback(int i, IImsConfigCallback iImsConfigCallback) throws RemoteException;

    void unregisterImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException;

    void unregisterMmTelCapabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    void updateEmergencyNumberListTestMode(int i, EmergencyNumber emergencyNumber) throws RemoteException;

    void updateOtaEmergencyNumberDbFilePath(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void updateServiceLocation() throws RemoteException;

    void updateServiceLocationForSubscriber(int i) throws RemoteException;

    void userActivity() throws RemoteException;

    public static abstract class Stub extends Binder implements ITelephony {
        private static final String DESCRIPTOR = "com.android.internal.telephony.ITelephony";
        static final int TRANSACTION_cacheMmTelCapabilityProvisioning = 248;
        static final int TRANSACTION_call = 2;
        static final int TRANSACTION_canChangeDtmfToneLength = 143;
        static final int TRANSACTION_canConnectTo5GInDsdsMode = 288;
        static final int TRANSACTION_carrierActionReportDefaultNetworkStatus = 185;
        static final int TRANSACTION_carrierActionResetAll = 186;
        static final int TRANSACTION_carrierActionSetMeteredApnsEnabled = 183;
        static final int TRANSACTION_carrierActionSetRadioEnabled = 184;
        static final int TRANSACTION_changeIccLockPassword = 284;
        static final int TRANSACTION_checkCarrierPrivilegesForPackage = 126;
        static final int TRANSACTION_checkCarrierPrivilegesForPackageAnyPhone = 127;
        static final int TRANSACTION_dial = 1;
        static final int TRANSACTION_disableDataConnectivity = 26;
        static final int TRANSACTION_disableIms = 92;
        static final int TRANSACTION_disableLocationUpdates = 23;
        static final int TRANSACTION_disableLocationUpdatesForSubscriber = 24;
        static final int TRANSACTION_disableVisualVoicemailSmsFilter = 56;
        static final int TRANSACTION_doesSwitchMultiSimConfigTriggerReboot = 264;
        static final int TRANSACTION_enableDataConnectivity = 25;
        static final int TRANSACTION_enableIms = 91;
        static final int TRANSACTION_enableLocationUpdates = 21;
        static final int TRANSACTION_enableLocationUpdatesForSubscriber = 22;
        static final int TRANSACTION_enableModemForSlot = 260;
        static final int TRANSACTION_enableVideoCalling = 141;
        static final int TRANSACTION_enableVisualVoicemailSmsFilter = 55;
        static final int TRANSACTION_enqueueSmsPickResult = 274;
        static final int TRANSACTION_factoryReset = 162;
        static final int TRANSACTION_getActivePhoneType = 37;
        static final int TRANSACTION_getActivePhoneTypeForSlot = 38;
        static final int TRANSACTION_getActiveVisualVoicemailSmsFilterSettings = 58;
        static final int TRANSACTION_getAidForAppType = 172;
        static final int TRANSACTION_getAllCellInfo = 69;
        static final int TRANSACTION_getAllowedCarriers = 177;
        static final int TRANSACTION_getAllowedNetworkTypes = 107;
        static final int TRANSACTION_getAllowedNetworkTypesForReason = 109;
        static final int TRANSACTION_getBoundImsServicePackage = 100;
        static final int TRANSACTION_getCalculatedPreferredNetworkType = 88;
        static final int TRANSACTION_getCallForwarding = 187;
        static final int TRANSACTION_getCallState = 31;
        static final int TRANSACTION_getCallStateForSlot = 32;
        static final int TRANSACTION_getCallWaitingStatus = 189;
        static final int TRANSACTION_getCardIdForDefaultEuicc = 198;
        static final int TRANSACTION_getCarrierIdFromMccMnc = 182;
        static final int TRANSACTION_getCarrierIdListVersion = 208;
        static final int TRANSACTION_getCarrierPackageNamesForIntentAndPhone = 128;
        static final int TRANSACTION_getCarrierPrivilegeStatus = 124;
        static final int TRANSACTION_getCarrierPrivilegeStatusForUid = 125;
        static final int TRANSACTION_getCdmaEriIconIndex = 39;
        static final int TRANSACTION_getCdmaEriIconIndexForSubscriber = 40;
        static final int TRANSACTION_getCdmaEriIconMode = 41;
        static final int TRANSACTION_getCdmaEriIconModeForSubscriber = 42;
        static final int TRANSACTION_getCdmaEriText = 43;
        static final int TRANSACTION_getCdmaEriTextForSubscriber = 44;
        static final int TRANSACTION_getCdmaMdn = 121;
        static final int TRANSACTION_getCdmaMin = 122;
        static final int TRANSACTION_getCdmaPrlVersion = 174;
        static final int TRANSACTION_getCdmaRoamingMode = 204;
        static final int TRANSACTION_getCellLocation = 28;
        static final int TRANSACTION_getCellNetworkScanResults = 103;
        static final int TRANSACTION_getCertsFromCarrierPrivilegeAccessRules = 240;
        static final int TRANSACTION_getClientRequestStats = 192;
        static final int TRANSACTION_getCurrentPackageName = 267;
        static final int TRANSACTION_getDataActivationState = 50;
        static final int TRANSACTION_getDataActivity = 33;
        static final int TRANSACTION_getDataActivityForSubId = 34;
        static final int TRANSACTION_getDataEnabled = 114;
        static final int TRANSACTION_getDataNetworkType = 62;
        static final int TRANSACTION_getDataNetworkTypeForSubscriber = 63;
        static final int TRANSACTION_getDataState = 35;
        static final int TRANSACTION_getDataStateForSubId = 36;
        static final int TRANSACTION_getDeviceId = 152;
        static final int TRANSACTION_getDeviceIdWithFeature = 153;
        static final int TRANSACTION_getDeviceSoftwareVersionForSlot = 158;
        static final int TRANSACTION_getEffectiveAllowedNetworkTypes = 110;
        static final int TRANSACTION_getEmergencyCallbackMode = 196;
        static final int TRANSACTION_getEmergencyNumberDbVersion = 256;
        static final int TRANSACTION_getEmergencyNumberList = 238;
        static final int TRANSACTION_getEmergencyNumberListTestMode = 255;
        static final int TRANSACTION_getEsn = 173;
        static final int TRANSACTION_getForbiddenPlmns = 194;
        static final int TRANSACTION_getImeiForSlot = 154;
        static final int TRANSACTION_getImsConfig = 98;
        static final int TRANSACTION_getImsMmTelFeatureState = 101;
        static final int TRANSACTION_getImsMmTelRegistrationState = 216;
        static final int TRANSACTION_getImsMmTelRegistrationTransportType = 217;
        static final int TRANSACTION_getImsProvisioningInt = 249;
        static final int TRANSACTION_getImsProvisioningStatusForCapability = 244;
        static final int TRANSACTION_getImsProvisioningString = 250;
        static final int TRANSACTION_getImsRegTechnologyForMmTel = 151;
        static final int TRANSACTION_getImsRegistration = 97;
        static final int TRANSACTION_getLine1AlphaTagForDisplay = 131;
        static final int TRANSACTION_getLine1NumberForDisplay = 130;
        static final int TRANSACTION_getLteOnCdmaMode = 67;
        static final int TRANSACTION_getLteOnCdmaModeForSubscriber = 68;
        static final int TRANSACTION_getManualNetworkSelectionPlmn = 287;
        static final int TRANSACTION_getManufacturerCodeForSlot = 157;
        static final int TRANSACTION_getMeidForSlot = 156;
        static final int TRANSACTION_getMergedImsisFromGroup = 133;
        static final int TRANSACTION_getMergedSubscriberIds = 132;
        static final int TRANSACTION_getMmTelFeatureAndListen = 94;
        static final int TRANSACTION_getMmsUAProfUrl = 276;
        static final int TRANSACTION_getMmsUserAgent = 275;
        static final int TRANSACTION_getNeighboringCellInfo = 30;
        static final int TRANSACTION_getNetworkCountryIsoForPhone = 29;
        static final int TRANSACTION_getNetworkSelectionMode = 211;
        static final int TRANSACTION_getNetworkTypeForSubscriber = 61;
        static final int TRANSACTION_getNumberOfModemsWithSimultaneousDataConnections = 210;
        static final int TRANSACTION_getPackagesWithCarrierPrivileges = 170;
        static final int TRANSACTION_getPackagesWithCarrierPrivilegesForAllPhones = 171;
        static final int TRANSACTION_getPcscfAddress = 119;
        static final int TRANSACTION_getPhoneAccountHandleForSubscriptionId = 161;
        static final int TRANSACTION_getPreferredNetworkType = 89;
        static final int TRANSACTION_getRadioAccessFamily = 140;
        static final int TRANSACTION_getRadioHalVersion = 266;
        static final int TRANSACTION_getRadioPowerState = 213;
        static final int TRANSACTION_getRcsFeatureAndListen = 95;
        static final int TRANSACTION_getRcsProvisioningStatusForCapability = 245;
        static final int TRANSACTION_getServiceStateForSubscriber = 165;
        static final int TRANSACTION_getSignalStrength = 197;
        static final int TRANSACTION_getSimLocaleForSubscriber = 163;
        static final int TRANSACTION_getSlotsMapping = 265;
        static final int TRANSACTION_getSubIdForPhoneAccount = 159;
        static final int TRANSACTION_getSubIdForPhoneAccountHandle = 160;
        static final int TRANSACTION_getSubscriptionCarrierId = 178;
        static final int TRANSACTION_getSubscriptionCarrierName = 179;
        static final int TRANSACTION_getSubscriptionSpecificCarrierId = 180;
        static final int TRANSACTION_getSubscriptionSpecificCarrierName = 181;
        static final int TRANSACTION_getTelephonyHistograms = 175;
        static final int TRANSACTION_getTypeAllocationCodeForSlot = 155;
        static final int TRANSACTION_getUiccCardsInfo = 199;
        static final int TRANSACTION_getUiccSlotsInfo = 200;
        static final int TRANSACTION_getVisualVoicemailPackageName = 54;
        static final int TRANSACTION_getVisualVoicemailSettings = 53;
        static final int TRANSACTION_getVisualVoicemailSmsFilterSettings = 57;
        static final int TRANSACTION_getVoWiFiModeSetting = 232;
        static final int TRANSACTION_getVoWiFiRoamingModeSetting = 234;
        static final int TRANSACTION_getVoiceActivationState = 49;
        static final int TRANSACTION_getVoiceMessageCountForSubscriber = 51;
        static final int TRANSACTION_getVoiceNetworkTypeForSubscriber = 64;
        static final int TRANSACTION_getVoicemailRingtoneUri = 166;
        static final int TRANSACTION_handlePinMmi = 11;
        static final int TRANSACTION_handlePinMmiForSubscriber = 13;
        static final int TRANSACTION_handleUssdRequest = 12;
        static final int TRANSACTION_hasIccCard = 65;
        static final int TRANSACTION_hasIccCardUsingSlotIndex = 66;
        static final int TRANSACTION_iccCloseLogicalChannel = 76;
        static final int TRANSACTION_iccCloseLogicalChannelBySlot = 75;
        static final int TRANSACTION_iccExchangeSimIO = 81;
        static final int TRANSACTION_iccOpenLogicalChannel = 74;
        static final int TRANSACTION_iccOpenLogicalChannelBySlot = 73;
        static final int TRANSACTION_iccTransmitApduBasicChannel = 80;
        static final int TRANSACTION_iccTransmitApduBasicChannelBySlot = 79;
        static final int TRANSACTION_iccTransmitApduLogicalChannel = 78;
        static final int TRANSACTION_iccTransmitApduLogicalChannelBySlot = 77;
        static final int TRANSACTION_invokeOemRilRequestRaw = 136;
        static final int TRANSACTION_isAdvancedCallingSettingEnabled = 223;
        static final int TRANSACTION_isApnMetered = 271;
        static final int TRANSACTION_isApplicationOnUicc = 268;
        static final int TRANSACTION_isAvailable = 221;
        static final int TRANSACTION_isCapable = 220;
        static final int TRANSACTION_isConcurrentVoiceAndDataAllowed = 52;
        static final int TRANSACTION_isDataAllowedInVoiceCall = 278;
        static final int TRANSACTION_isDataConnectivityPossible = 27;
        static final int TRANSACTION_isDataEnabled = 116;
        static final int TRANSACTION_isDataEnabledForApn = 270;
        static final int TRANSACTION_isDataRoamingEnabled = 202;
        static final int TRANSACTION_isEmergencyNumber = 239;
        static final int TRANSACTION_isHearingAidCompatibilitySupported = 147;
        static final int TRANSACTION_isIccLockEnabled = 282;
        static final int TRANSACTION_isImsRegistered = 148;
        static final int TRANSACTION_isInEmergencySmsMode = 212;
        static final int TRANSACTION_isManualNetworkSelectionAllowed = 117;
        static final int TRANSACTION_isMmTelCapabilityProvisionedInCache = 247;
        static final int TRANSACTION_isMmTelCapabilitySupported = 222;
        static final int TRANSACTION_isModemEnabledForSlot = 269;
        static final int TRANSACTION_isMultiSimSupported = 262;
        static final int TRANSACTION_isMvnoMatched = 273;
        static final int TRANSACTION_isRadioOn = 3;
        static final int TRANSACTION_isRadioOnForSubscriber = 5;
        static final int TRANSACTION_isRadioOnForSubscriberWithFeature = 6;
        static final int TRANSACTION_isRadioOnWithFeature = 4;
        static final int TRANSACTION_isRttSupported = 146;
        static final int TRANSACTION_isTetheringApnRequiredForSubscriber = 90;
        static final int TRANSACTION_isTtyModeSupported = 145;
        static final int TRANSACTION_isTtyOverVolteEnabled = 237;
        static final int TRANSACTION_isUserDataEnabled = 115;
        static final int TRANSACTION_isVideoCallingEnabled = 142;
        static final int TRANSACTION_isVideoTelephonyAvailable = 150;
        static final int TRANSACTION_isVoWiFiRoamingSettingEnabled = 229;
        static final int TRANSACTION_isVoWiFiSettingEnabled = 227;
        static final int TRANSACTION_isVoicemailVibrationEnabled = 168;
        static final int TRANSACTION_isVtSettingEnabled = 225;
        static final int TRANSACTION_isWifiCallingAvailable = 149;
        static final int TRANSACTION_isWorldPhone = 144;
        static final int TRANSACTION_needMobileRadioShutdown = 137;
        static final int TRANSACTION_needsOtaServiceProvisioning = 45;
        static final int TRANSACTION_notifyOtaEmergencyNumberDbInstalled = 257;
        static final int TRANSACTION_notifyRcsAutoConfigurationReceived = 281;
        static final int TRANSACTION_nvReadItem = 83;
        static final int TRANSACTION_nvWriteCdmaPrl = 85;
        static final int TRANSACTION_nvWriteItem = 84;
        static final int TRANSACTION_rebootModem = 87;
        static final int TRANSACTION_refreshUiccProfile = 209;
        static final int TRANSACTION_registerImsProvisioningChangedCallback = 241;
        static final int TRANSACTION_registerImsRegistrationCallback = 214;
        static final int TRANSACTION_registerMmTelCapabilityCallback = 218;
        static final int TRANSACTION_requestCellInfoUpdate = 70;
        static final int TRANSACTION_requestCellInfoUpdateWithWorkSource = 71;
        static final int TRANSACTION_requestModemActivityInfo = 164;
        static final int TRANSACTION_requestNetworkScan = 104;
        static final int TRANSACTION_requestNumberVerification = 123;
        static final int TRANSACTION_requestUserActivityNotification = 285;
        static final int TRANSACTION_resetIms = 93;
        static final int TRANSACTION_resetModemConfig = 86;
        static final int TRANSACTION_resetOtaEmergencyNumberDbFilePath = 259;
        static final int TRANSACTION_sendDialerSpecialCode = 60;
        static final int TRANSACTION_sendEnvelopeWithStatus = 82;
        static final int TRANSACTION_sendVisualVoicemailSmsForSubscriber = 59;
        static final int TRANSACTION_setAdvancedCallingSettingEnabled = 224;
        static final int TRANSACTION_setAllowedCarriers = 176;
        static final int TRANSACTION_setAllowedNetworkTypes = 108;
        static final int TRANSACTION_setAllowedNetworkTypesForReason = 111;
        static final int TRANSACTION_setAlwaysAllowMmsData = 279;
        static final int TRANSACTION_setAlwaysReportSignalStrength = 118;
        static final int TRANSACTION_setBoundImsServiceOverride = 99;
        static final int TRANSACTION_setCallForwarding = 188;
        static final int TRANSACTION_setCallWaitingStatus = 190;
        static final int TRANSACTION_setCarrierTestOverride = 207;
        static final int TRANSACTION_setCdmaRoamingMode = 205;
        static final int TRANSACTION_setCdmaSubscriptionMode = 206;
        static final int TRANSACTION_setCellInfoListRate = 72;
        static final int TRANSACTION_setCepEnabled = 280;
        static final int TRANSACTION_setDataActivationState = 48;
        static final int TRANSACTION_setDataAllowedDuringVoiceCall = 277;
        static final int TRANSACTION_setDataRoamingEnabled = 203;
        static final int TRANSACTION_setForbiddenPlmns = 195;
        static final int TRANSACTION_setIccLockEnabled = 283;
        static final int TRANSACTION_setImsProvisioningInt = 251;
        static final int TRANSACTION_setImsProvisioningStatusForCapability = 243;
        static final int TRANSACTION_setImsProvisioningString = 252;
        static final int TRANSACTION_setImsRegistrationState = 120;
        static final int TRANSACTION_setLine1NumberForDisplayForSubscriber = 129;
        static final int TRANSACTION_setMultiSimCarrierRestriction = 261;
        static final int TRANSACTION_setNetworkSelectionModeAutomatic = 102;
        static final int TRANSACTION_setNetworkSelectionModeManual = 106;
        static final int TRANSACTION_setOperatorBrandOverride = 134;
        static final int TRANSACTION_setPolicyDataEnabled = 191;
        static final int TRANSACTION_setPreferredNetworkType = 112;
        static final int TRANSACTION_setRadio = 16;
        static final int TRANSACTION_setRadioCapability = 139;
        static final int TRANSACTION_setRadioForSubscriber = 17;
        static final int TRANSACTION_setRadioPower = 18;
        static final int TRANSACTION_setRcsProvisioningStatusForCapability = 246;
        static final int TRANSACTION_setRoamingOverride = 135;
        static final int TRANSACTION_setRttCapabilitySetting = 236;
        static final int TRANSACTION_setSimPowerStateForSlot = 193;
        static final int TRANSACTION_setSystemSelectionChannels = 272;
        static final int TRANSACTION_setUserDataEnabled = 113;
        static final int TRANSACTION_setVoWiFiModeSetting = 233;
        static final int TRANSACTION_setVoWiFiNonPersistent = 231;
        static final int TRANSACTION_setVoWiFiRoamingModeSetting = 235;
        static final int TRANSACTION_setVoWiFiRoamingSettingEnabled = 230;
        static final int TRANSACTION_setVoWiFiSettingEnabled = 228;
        static final int TRANSACTION_setVoiceActivationState = 47;
        static final int TRANSACTION_setVoiceMailNumber = 46;
        static final int TRANSACTION_setVoicemailRingtoneUri = 167;
        static final int TRANSACTION_setVoicemailVibrationEnabled = 169;
        static final int TRANSACTION_setVtSettingEnabled = 226;
        static final int TRANSACTION_shutdownMobileRadios = 138;
        static final int TRANSACTION_startEmergencyCallbackMode = 253;
        static final int TRANSACTION_stopNetworkScan = 105;
        static final int TRANSACTION_supplyPinForSubscriber = 7;
        static final int TRANSACTION_supplyPinReportResultForSubscriber = 9;
        static final int TRANSACTION_supplyPukForSubscriber = 8;
        static final int TRANSACTION_supplyPukReportResultForSubscriber = 10;
        static final int TRANSACTION_switchMultiSimConfig = 263;
        static final int TRANSACTION_switchSlots = 201;
        static final int TRANSACTION_toggleRadioOnOff = 14;
        static final int TRANSACTION_toggleRadioOnOffForSubscriber = 15;
        static final int TRANSACTION_unregisterImsFeatureCallback = 96;
        static final int TRANSACTION_unregisterImsProvisioningChangedCallback = 242;
        static final int TRANSACTION_unregisterImsRegistrationCallback = 215;
        static final int TRANSACTION_unregisterMmTelCapabilityCallback = 219;
        static final int TRANSACTION_updateEmergencyNumberListTestMode = 254;
        static final int TRANSACTION_updateOtaEmergencyNumberDbFilePath = 258;
        static final int TRANSACTION_updateServiceLocation = 19;
        static final int TRANSACTION_updateServiceLocationForSubscriber = 20;
        static final int TRANSACTION_userActivity = 286;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "dial";
                case 2:
                    return "call";
                case 3:
                    return "isRadioOn";
                case 4:
                    return "isRadioOnWithFeature";
                case 5:
                    return "isRadioOnForSubscriber";
                case 6:
                    return "isRadioOnForSubscriberWithFeature";
                case 7:
                    return "supplyPinForSubscriber";
                case 8:
                    return "supplyPukForSubscriber";
                case 9:
                    return "supplyPinReportResultForSubscriber";
                case 10:
                    return "supplyPukReportResultForSubscriber";
                case 11:
                    return "handlePinMmi";
                case 12:
                    return "handleUssdRequest";
                case 13:
                    return "handlePinMmiForSubscriber";
                case 14:
                    return "toggleRadioOnOff";
                case 15:
                    return "toggleRadioOnOffForSubscriber";
                case 16:
                    return "setRadio";
                case 17:
                    return "setRadioForSubscriber";
                case 18:
                    return "setRadioPower";
                case 19:
                    return "updateServiceLocation";
                case 20:
                    return "updateServiceLocationForSubscriber";
                case 21:
                    return "enableLocationUpdates";
                case 22:
                    return "enableLocationUpdatesForSubscriber";
                case 23:
                    return "disableLocationUpdates";
                case 24:
                    return "disableLocationUpdatesForSubscriber";
                case 25:
                    return "enableDataConnectivity";
                case 26:
                    return "disableDataConnectivity";
                case 27:
                    return "isDataConnectivityPossible";
                case 28:
                    return "getCellLocation";
                case 29:
                    return "getNetworkCountryIsoForPhone";
                case 30:
                    return "getNeighboringCellInfo";
                case 31:
                    return "getCallState";
                case 32:
                    return "getCallStateForSlot";
                case 33:
                    return "getDataActivity";
                case 34:
                    return "getDataActivityForSubId";
                case 35:
                    return "getDataState";
                case 36:
                    return "getDataStateForSubId";
                case 37:
                    return "getActivePhoneType";
                case 38:
                    return "getActivePhoneTypeForSlot";
                case 39:
                    return "getCdmaEriIconIndex";
                case 40:
                    return "getCdmaEriIconIndexForSubscriber";
                case 41:
                    return "getCdmaEriIconMode";
                case 42:
                    return "getCdmaEriIconModeForSubscriber";
                case 43:
                    return "getCdmaEriText";
                case 44:
                    return "getCdmaEriTextForSubscriber";
                case 45:
                    return "needsOtaServiceProvisioning";
                case 46:
                    return "setVoiceMailNumber";
                case 47:
                    return "setVoiceActivationState";
                case 48:
                    return "setDataActivationState";
                case 49:
                    return "getVoiceActivationState";
                case 50:
                    return "getDataActivationState";
                case 51:
                    return "getVoiceMessageCountForSubscriber";
                case 52:
                    return "isConcurrentVoiceAndDataAllowed";
                case 53:
                    return "getVisualVoicemailSettings";
                case 54:
                    return "getVisualVoicemailPackageName";
                case 55:
                    return "enableVisualVoicemailSmsFilter";
                case 56:
                    return "disableVisualVoicemailSmsFilter";
                case 57:
                    return "getVisualVoicemailSmsFilterSettings";
                case 58:
                    return "getActiveVisualVoicemailSmsFilterSettings";
                case 59:
                    return "sendVisualVoicemailSmsForSubscriber";
                case 60:
                    return "sendDialerSpecialCode";
                case 61:
                    return "getNetworkTypeForSubscriber";
                case 62:
                    return "getDataNetworkType";
                case 63:
                    return "getDataNetworkTypeForSubscriber";
                case 64:
                    return "getVoiceNetworkTypeForSubscriber";
                case 65:
                    return "hasIccCard";
                case 66:
                    return "hasIccCardUsingSlotIndex";
                case 67:
                    return "getLteOnCdmaMode";
                case 68:
                    return "getLteOnCdmaModeForSubscriber";
                case 69:
                    return "getAllCellInfo";
                case 70:
                    return "requestCellInfoUpdate";
                case 71:
                    return "requestCellInfoUpdateWithWorkSource";
                case 72:
                    return "setCellInfoListRate";
                case 73:
                    return "iccOpenLogicalChannelBySlot";
                case 74:
                    return "iccOpenLogicalChannel";
                case 75:
                    return "iccCloseLogicalChannelBySlot";
                case 76:
                    return "iccCloseLogicalChannel";
                case 77:
                    return "iccTransmitApduLogicalChannelBySlot";
                case 78:
                    return "iccTransmitApduLogicalChannel";
                case 79:
                    return "iccTransmitApduBasicChannelBySlot";
                case 80:
                    return "iccTransmitApduBasicChannel";
                case 81:
                    return "iccExchangeSimIO";
                case 82:
                    return "sendEnvelopeWithStatus";
                case 83:
                    return "nvReadItem";
                case 84:
                    return "nvWriteItem";
                case 85:
                    return "nvWriteCdmaPrl";
                case 86:
                    return "resetModemConfig";
                case 87:
                    return "rebootModem";
                case 88:
                    return "getCalculatedPreferredNetworkType";
                case 89:
                    return "getPreferredNetworkType";
                case 90:
                    return "isTetheringApnRequiredForSubscriber";
                case 91:
                    return "enableIms";
                case 92:
                    return "disableIms";
                case 93:
                    return "resetIms";
                case 94:
                    return "getMmTelFeatureAndListen";
                case 95:
                    return "getRcsFeatureAndListen";
                case 96:
                    return "unregisterImsFeatureCallback";
                case 97:
                    return "getImsRegistration";
                case 98:
                    return "getImsConfig";
                case 99:
                    return "setBoundImsServiceOverride";
                case 100:
                    return "getBoundImsServicePackage";
                case 101:
                    return "getImsMmTelFeatureState";
                case 102:
                    return "setNetworkSelectionModeAutomatic";
                case 103:
                    return "getCellNetworkScanResults";
                case 104:
                    return "requestNetworkScan";
                case 105:
                    return "stopNetworkScan";
                case 106:
                    return "setNetworkSelectionModeManual";
                case 107:
                    return "getAllowedNetworkTypes";
                case 108:
                    return "setAllowedNetworkTypes";
                case 109:
                    return "getAllowedNetworkTypesForReason";
                case 110:
                    return "getEffectiveAllowedNetworkTypes";
                case 111:
                    return "setAllowedNetworkTypesForReason";
                case 112:
                    return "setPreferredNetworkType";
                case 113:
                    return "setUserDataEnabled";
                case 114:
                    return "getDataEnabled";
                case 115:
                    return "isUserDataEnabled";
                case 116:
                    return "isDataEnabled";
                case 117:
                    return "isManualNetworkSelectionAllowed";
                case 118:
                    return "setAlwaysReportSignalStrength";
                case 119:
                    return "getPcscfAddress";
                case 120:
                    return "setImsRegistrationState";
                case 121:
                    return "getCdmaMdn";
                case 122:
                    return "getCdmaMin";
                case 123:
                    return "requestNumberVerification";
                case 124:
                    return "getCarrierPrivilegeStatus";
                case 125:
                    return "getCarrierPrivilegeStatusForUid";
                case 126:
                    return "checkCarrierPrivilegesForPackage";
                case 127:
                    return "checkCarrierPrivilegesForPackageAnyPhone";
                case 128:
                    return "getCarrierPackageNamesForIntentAndPhone";
                case 129:
                    return "setLine1NumberForDisplayForSubscriber";
                case 130:
                    return "getLine1NumberForDisplay";
                case 131:
                    return "getLine1AlphaTagForDisplay";
                case 132:
                    return "getMergedSubscriberIds";
                case 133:
                    return "getMergedImsisFromGroup";
                case 134:
                    return "setOperatorBrandOverride";
                case 135:
                    return "setRoamingOverride";
                case 136:
                    return "invokeOemRilRequestRaw";
                case 137:
                    return "needMobileRadioShutdown";
                case 138:
                    return "shutdownMobileRadios";
                case 139:
                    return "setRadioCapability";
                case 140:
                    return "getRadioAccessFamily";
                case 141:
                    return "enableVideoCalling";
                case 142:
                    return "isVideoCallingEnabled";
                case 143:
                    return "canChangeDtmfToneLength";
                case 144:
                    return "isWorldPhone";
                case 145:
                    return "isTtyModeSupported";
                case 146:
                    return "isRttSupported";
                case 147:
                    return "isHearingAidCompatibilitySupported";
                case 148:
                    return "isImsRegistered";
                case 149:
                    return "isWifiCallingAvailable";
                case 150:
                    return "isVideoTelephonyAvailable";
                case 151:
                    return "getImsRegTechnologyForMmTel";
                case 152:
                    return "getDeviceId";
                case 153:
                    return "getDeviceIdWithFeature";
                case 154:
                    return "getImeiForSlot";
                case 155:
                    return "getTypeAllocationCodeForSlot";
                case 156:
                    return "getMeidForSlot";
                case 157:
                    return "getManufacturerCodeForSlot";
                case 158:
                    return "getDeviceSoftwareVersionForSlot";
                case 159:
                    return "getSubIdForPhoneAccount";
                case 160:
                    return "getSubIdForPhoneAccountHandle";
                case 161:
                    return "getPhoneAccountHandleForSubscriptionId";
                case 162:
                    return "factoryReset";
                case 163:
                    return "getSimLocaleForSubscriber";
                case 164:
                    return "requestModemActivityInfo";
                case 165:
                    return "getServiceStateForSubscriber";
                case 166:
                    return "getVoicemailRingtoneUri";
                case 167:
                    return "setVoicemailRingtoneUri";
                case 168:
                    return "isVoicemailVibrationEnabled";
                case 169:
                    return "setVoicemailVibrationEnabled";
                case 170:
                    return "getPackagesWithCarrierPrivileges";
                case 171:
                    return "getPackagesWithCarrierPrivilegesForAllPhones";
                case 172:
                    return "getAidForAppType";
                case 173:
                    return "getEsn";
                case 174:
                    return "getCdmaPrlVersion";
                case 175:
                    return "getTelephonyHistograms";
                case 176:
                    return "setAllowedCarriers";
                case 177:
                    return "getAllowedCarriers";
                case 178:
                    return "getSubscriptionCarrierId";
                case 179:
                    return "getSubscriptionCarrierName";
                case 180:
                    return "getSubscriptionSpecificCarrierId";
                case 181:
                    return "getSubscriptionSpecificCarrierName";
                case 182:
                    return "getCarrierIdFromMccMnc";
                case 183:
                    return "carrierActionSetMeteredApnsEnabled";
                case 184:
                    return "carrierActionSetRadioEnabled";
                case 185:
                    return "carrierActionReportDefaultNetworkStatus";
                case 186:
                    return "carrierActionResetAll";
                case 187:
                    return "getCallForwarding";
                case 188:
                    return "setCallForwarding";
                case 189:
                    return "getCallWaitingStatus";
                case 190:
                    return "setCallWaitingStatus";
                case 191:
                    return "setPolicyDataEnabled";
                case 192:
                    return "getClientRequestStats";
                case 193:
                    return "setSimPowerStateForSlot";
                case 194:
                    return "getForbiddenPlmns";
                case 195:
                    return "setForbiddenPlmns";
                case 196:
                    return "getEmergencyCallbackMode";
                case 197:
                    return "getSignalStrength";
                case 198:
                    return "getCardIdForDefaultEuicc";
                case 199:
                    return "getUiccCardsInfo";
                case 200:
                    return "getUiccSlotsInfo";
                case 201:
                    return "switchSlots";
                case 202:
                    return "isDataRoamingEnabled";
                case 203:
                    return "setDataRoamingEnabled";
                case 204:
                    return "getCdmaRoamingMode";
                case 205:
                    return "setCdmaRoamingMode";
                case 206:
                    return "setCdmaSubscriptionMode";
                case 207:
                    return "setCarrierTestOverride";
                case 208:
                    return "getCarrierIdListVersion";
                case 209:
                    return "refreshUiccProfile";
                case 210:
                    return "getNumberOfModemsWithSimultaneousDataConnections";
                case 211:
                    return "getNetworkSelectionMode";
                case 212:
                    return "isInEmergencySmsMode";
                case 213:
                    return "getRadioPowerState";
                case 214:
                    return "registerImsRegistrationCallback";
                case 215:
                    return "unregisterImsRegistrationCallback";
                case 216:
                    return "getImsMmTelRegistrationState";
                case 217:
                    return "getImsMmTelRegistrationTransportType";
                case 218:
                    return "registerMmTelCapabilityCallback";
                case 219:
                    return "unregisterMmTelCapabilityCallback";
                case 220:
                    return "isCapable";
                case 221:
                    return "isAvailable";
                case 222:
                    return "isMmTelCapabilitySupported";
                case 223:
                    return "isAdvancedCallingSettingEnabled";
                case 224:
                    return "setAdvancedCallingSettingEnabled";
                case 225:
                    return "isVtSettingEnabled";
                case 226:
                    return "setVtSettingEnabled";
                case 227:
                    return "isVoWiFiSettingEnabled";
                case 228:
                    return "setVoWiFiSettingEnabled";
                case 229:
                    return "isVoWiFiRoamingSettingEnabled";
                case 230:
                    return "setVoWiFiRoamingSettingEnabled";
                case 231:
                    return "setVoWiFiNonPersistent";
                case 232:
                    return "getVoWiFiModeSetting";
                case 233:
                    return "setVoWiFiModeSetting";
                case 234:
                    return "getVoWiFiRoamingModeSetting";
                case 235:
                    return "setVoWiFiRoamingModeSetting";
                case 236:
                    return "setRttCapabilitySetting";
                case 237:
                    return "isTtyOverVolteEnabled";
                case 238:
                    return "getEmergencyNumberList";
                case 239:
                    return "isEmergencyNumber";
                case 240:
                    return "getCertsFromCarrierPrivilegeAccessRules";
                case 241:
                    return "registerImsProvisioningChangedCallback";
                case 242:
                    return "unregisterImsProvisioningChangedCallback";
                case 243:
                    return "setImsProvisioningStatusForCapability";
                case 244:
                    return "getImsProvisioningStatusForCapability";
                case 245:
                    return "getRcsProvisioningStatusForCapability";
                case 246:
                    return "setRcsProvisioningStatusForCapability";
                case 247:
                    return "isMmTelCapabilityProvisionedInCache";
                case 248:
                    return "cacheMmTelCapabilityProvisioning";
                case 249:
                    return "getImsProvisioningInt";
                case 250:
                    return "getImsProvisioningString";
                case 251:
                    return "setImsProvisioningInt";
                case 252:
                    return "setImsProvisioningString";
                case 253:
                    return "startEmergencyCallbackMode";
                case 254:
                    return "updateEmergencyNumberListTestMode";
                case 255:
                    return "getEmergencyNumberListTestMode";
                case 256:
                    return "getEmergencyNumberDbVersion";
                case 257:
                    return "notifyOtaEmergencyNumberDbInstalled";
                case 258:
                    return "updateOtaEmergencyNumberDbFilePath";
                case 259:
                    return "resetOtaEmergencyNumberDbFilePath";
                case 260:
                    return "enableModemForSlot";
                case 261:
                    return "setMultiSimCarrierRestriction";
                case 262:
                    return "isMultiSimSupported";
                case 263:
                    return "switchMultiSimConfig";
                case 264:
                    return "doesSwitchMultiSimConfigTriggerReboot";
                case 265:
                    return "getSlotsMapping";
                case 266:
                    return "getRadioHalVersion";
                case 267:
                    return "getCurrentPackageName";
                case 268:
                    return "isApplicationOnUicc";
                case 269:
                    return "isModemEnabledForSlot";
                case 270:
                    return "isDataEnabledForApn";
                case 271:
                    return "isApnMetered";
                case 272:
                    return "setSystemSelectionChannels";
                case 273:
                    return "isMvnoMatched";
                case 274:
                    return "enqueueSmsPickResult";
                case 275:
                    return "getMmsUserAgent";
                case 276:
                    return "getMmsUAProfUrl";
                case 277:
                    return "setDataAllowedDuringVoiceCall";
                case 278:
                    return "isDataAllowedInVoiceCall";
                case 279:
                    return "setAlwaysAllowMmsData";
                case 280:
                    return "setCepEnabled";
                case 281:
                    return "notifyRcsAutoConfigurationReceived";
                case 282:
                    return "isIccLockEnabled";
                case 283:
                    return "setIccLockEnabled";
                case 284:
                    return "changeIccLockPassword";
                case 285:
                    return "requestUserActivityNotification";
                case 286:
                    return "userActivity";
                case 287:
                    return "getManualNetworkSelectionPlmn";
                case 288:
                    return "canConnectTo5GInDsdsMode";
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

        public static ITelephony asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITelephony)) {
                return (ITelephony) iInterfaceQueryLocalInterface;
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
                    dial(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    call(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsRadioOn = isRadioOn(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsRadioOn ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsRadioOnWithFeature = isRadioOnWithFeature(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsRadioOnWithFeature ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsRadioOnForSubscriber = isRadioOnForSubscriber(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsRadioOnForSubscriber ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsRadioOnForSubscriberWithFeature = isRadioOnForSubscriberWithFeature(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsRadioOnForSubscriberWithFeature ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSupplyPinForSubscriber = supplyPinForSubscriber(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zSupplyPinForSubscriber ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSupplyPukForSubscriber = supplyPukForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zSupplyPukForSubscriber ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] iArrSupplyPinReportResultForSubscriber = supplyPinReportResultForSubscriber(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeIntArray(iArrSupplyPinReportResultForSubscriber);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] iArrSupplyPukReportResultForSubscriber = supplyPukReportResultForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeIntArray(iArrSupplyPukReportResultForSubscriber);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHandlePinMmi = handlePinMmi(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHandlePinMmi ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    handleUssdRequest(parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHandlePinMmiForSubscriber = handlePinMmiForSubscriber(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHandlePinMmiForSubscriber ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    toggleRadioOnOff();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    toggleRadioOnOffForSubscriber(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean radio = setRadio(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(radio ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean radioForSubscriber = setRadioForSubscriber(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(radioForSubscriber ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean radioPower = setRadioPower(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(radioPower ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateServiceLocation();
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateServiceLocationForSubscriber(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableLocationUpdates();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableLocationUpdatesForSubscriber(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableLocationUpdates();
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableLocationUpdatesForSubscriber(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnableDataConnectivity = enableDataConnectivity();
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnableDataConnectivity ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisableDataConnectivity = disableDataConnectivity();
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisableDataConnectivity ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDataConnectivityPossible = isDataConnectivityPossible(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDataConnectivityPossible ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    CellIdentity cellLocation = getCellLocation(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (cellLocation != null) {
                        parcel2.writeInt(1);
                        cellLocation.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    String networkCountryIsoForPhone = getNetworkCountryIsoForPhone(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(networkCountryIsoForPhone);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<NeighboringCellInfo> neighboringCellInfo = getNeighboringCellInfo(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(neighboringCellInfo);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    int callState = getCallState();
                    parcel2.writeNoException();
                    parcel2.writeInt(callState);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    int callStateForSlot = getCallStateForSlot(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(callStateForSlot);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dataActivity = getDataActivity();
                    parcel2.writeNoException();
                    parcel2.writeInt(dataActivity);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dataActivityForSubId = getDataActivityForSubId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(dataActivityForSubId);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dataState = getDataState();
                    parcel2.writeNoException();
                    parcel2.writeInt(dataState);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dataStateForSubId = getDataStateForSubId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(dataStateForSubId);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    int activePhoneType = getActivePhoneType();
                    parcel2.writeNoException();
                    parcel2.writeInt(activePhoneType);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    int activePhoneTypeForSlot = getActivePhoneTypeForSlot(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(activePhoneTypeForSlot);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    int cdmaEriIconIndex = getCdmaEriIconIndex(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(cdmaEriIconIndex);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    int cdmaEriIconIndexForSubscriber = getCdmaEriIconIndexForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(cdmaEriIconIndexForSubscriber);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    int cdmaEriIconMode = getCdmaEriIconMode(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(cdmaEriIconMode);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    int cdmaEriIconModeForSubscriber = getCdmaEriIconModeForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(cdmaEriIconModeForSubscriber);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    String cdmaEriText = getCdmaEriText(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(cdmaEriText);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    String cdmaEriTextForSubscriber = getCdmaEriTextForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(cdmaEriTextForSubscriber);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zNeedsOtaServiceProvisioning = needsOtaServiceProvisioning();
                    parcel2.writeNoException();
                    parcel2.writeInt(zNeedsOtaServiceProvisioning ? 1 : 0);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean voiceMailNumber = setVoiceMailNumber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(voiceMailNumber ? 1 : 0);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVoiceActivationState(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDataActivationState(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    int voiceActivationState = getVoiceActivationState(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(voiceActivationState);
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dataActivationState = getDataActivationState(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(dataActivationState);
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    int voiceMessageCountForSubscriber = getVoiceMessageCountForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(voiceMessageCountForSubscriber);
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsConcurrentVoiceAndDataAllowed = isConcurrentVoiceAndDataAllowed(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsConcurrentVoiceAndDataAllowed ? 1 : 0);
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle visualVoicemailSettings = getVisualVoicemailSettings(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (visualVoicemailSettings != null) {
                        parcel2.writeInt(1);
                        visualVoicemailSettings.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    String visualVoicemailPackageName = getVisualVoicemailPackageName(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(visualVoicemailPackageName);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableVisualVoicemailSmsFilter(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? VisualVoicemailSmsFilterSettings.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableVisualVoicemailSmsFilter(parcel.readString(), parcel.readInt());
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    VisualVoicemailSmsFilterSettings visualVoicemailSmsFilterSettings = getVisualVoicemailSmsFilterSettings(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (visualVoicemailSmsFilterSettings != null) {
                        parcel2.writeInt(1);
                        visualVoicemailSmsFilterSettings.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    VisualVoicemailSmsFilterSettings activeVisualVoicemailSmsFilterSettings = getActiveVisualVoicemailSmsFilterSettings(parcel.readInt());
                    parcel2.writeNoException();
                    if (activeVisualVoicemailSmsFilterSettings != null) {
                        parcel2.writeInt(1);
                        activeVisualVoicemailSmsFilterSettings.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 59:
                    return onTransact$sendVisualVoicemailSmsForSubscriber$(parcel, parcel2);
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendDialerSpecialCode(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    int networkTypeForSubscriber = getNetworkTypeForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(networkTypeForSubscriber);
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dataNetworkType = getDataNetworkType(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(dataNetworkType);
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dataNetworkTypeForSubscriber = getDataNetworkTypeForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(dataNetworkTypeForSubscriber);
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    int voiceNetworkTypeForSubscriber = getVoiceNetworkTypeForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(voiceNetworkTypeForSubscriber);
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasIccCard = hasIccCard();
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasIccCard ? 1 : 0);
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasIccCardUsingSlotIndex = hasIccCardUsingSlotIndex(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasIccCardUsingSlotIndex ? 1 : 0);
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    int lteOnCdmaMode = getLteOnCdmaMode(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(lteOnCdmaMode);
                    return true;
                case 68:
                    parcel.enforceInterface(DESCRIPTOR);
                    int lteOnCdmaModeForSubscriber = getLteOnCdmaModeForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(lteOnCdmaModeForSubscriber);
                    return true;
                case 69:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<CellInfo> allCellInfo = getAllCellInfo(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(allCellInfo);
                    return true;
                case 70:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestCellInfoUpdate(parcel.readInt(), ICellInfoCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 71:
                    return onTransact$requestCellInfoUpdateWithWorkSource$(parcel, parcel2);
                case 72:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCellInfoListRate(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 73:
                    parcel.enforceInterface(DESCRIPTOR);
                    IccOpenLogicalChannelResponse iccOpenLogicalChannelResponseIccOpenLogicalChannelBySlot = iccOpenLogicalChannelBySlot(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (iccOpenLogicalChannelResponseIccOpenLogicalChannelBySlot != null) {
                        parcel2.writeInt(1);
                        iccOpenLogicalChannelResponseIccOpenLogicalChannelBySlot.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 74:
                    parcel.enforceInterface(DESCRIPTOR);
                    IccOpenLogicalChannelResponse iccOpenLogicalChannelResponseIccOpenLogicalChannel = iccOpenLogicalChannel(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (iccOpenLogicalChannelResponseIccOpenLogicalChannel != null) {
                        parcel2.writeInt(1);
                        iccOpenLogicalChannelResponseIccOpenLogicalChannel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 75:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIccCloseLogicalChannelBySlot = iccCloseLogicalChannelBySlot(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIccCloseLogicalChannelBySlot ? 1 : 0);
                    return true;
                case 76:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIccCloseLogicalChannel = iccCloseLogicalChannel(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIccCloseLogicalChannel ? 1 : 0);
                    return true;
                case 77:
                    return onTransact$iccTransmitApduLogicalChannelBySlot$(parcel, parcel2);
                case 78:
                    return onTransact$iccTransmitApduLogicalChannel$(parcel, parcel2);
                case 79:
                    return onTransact$iccTransmitApduBasicChannelBySlot$(parcel, parcel2);
                case 80:
                    return onTransact$iccTransmitApduBasicChannel$(parcel, parcel2);
                case 81:
                    return onTransact$iccExchangeSimIO$(parcel, parcel2);
                case 82:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strSendEnvelopeWithStatus = sendEnvelopeWithStatus(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(strSendEnvelopeWithStatus);
                    return true;
                case 83:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strNvReadItem = nvReadItem(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(strNvReadItem);
                    return true;
                case 84:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zNvWriteItem = nvWriteItem(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zNvWriteItem ? 1 : 0);
                    return true;
                case 85:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zNvWriteCdmaPrl = nvWriteCdmaPrl(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zNvWriteCdmaPrl ? 1 : 0);
                    return true;
                case 86:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zResetModemConfig = resetModemConfig(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zResetModemConfig ? 1 : 0);
                    return true;
                case 87:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRebootModem = rebootModem(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRebootModem ? 1 : 0);
                    return true;
                case 88:
                    parcel.enforceInterface(DESCRIPTOR);
                    int calculatedPreferredNetworkType = getCalculatedPreferredNetworkType(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(calculatedPreferredNetworkType);
                    return true;
                case 89:
                    parcel.enforceInterface(DESCRIPTOR);
                    int preferredNetworkType = getPreferredNetworkType(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(preferredNetworkType);
                    return true;
                case 90:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsTetheringApnRequiredForSubscriber = isTetheringApnRequiredForSubscriber(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsTetheringApnRequiredForSubscriber ? 1 : 0);
                    return true;
                case 91:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableIms(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 92:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableIms(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 93:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetIms(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 94:
                    parcel.enforceInterface(DESCRIPTOR);
                    IImsMmTelFeature mmTelFeatureAndListen = getMmTelFeatureAndListen(parcel.readInt(), IImsServiceFeatureCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(mmTelFeatureAndListen != null ? mmTelFeatureAndListen.asBinder() : null);
                    return true;
                case 95:
                    parcel.enforceInterface(DESCRIPTOR);
                    IImsRcsFeature rcsFeatureAndListen = getRcsFeatureAndListen(parcel.readInt(), IImsServiceFeatureCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(rcsFeatureAndListen != null ? rcsFeatureAndListen.asBinder() : null);
                    return true;
                case 96:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterImsFeatureCallback(parcel.readInt(), parcel.readInt(), IImsServiceFeatureCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 97:
                    parcel.enforceInterface(DESCRIPTOR);
                    IImsRegistration imsRegistration = getImsRegistration(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(imsRegistration != null ? imsRegistration.asBinder() : null);
                    return true;
                case 98:
                    parcel.enforceInterface(DESCRIPTOR);
                    IImsConfig imsConfig = getImsConfig(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(imsConfig != null ? imsConfig.asBinder() : null);
                    return true;
                case 99:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean boundImsServiceOverride = setBoundImsServiceOverride(parcel.readInt(), parcel.readInt() != 0, parcel.createIntArray(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(boundImsServiceOverride ? 1 : 0);
                    return true;
                case 100:
                    parcel.enforceInterface(DESCRIPTOR);
                    String boundImsServicePackage = getBoundImsServicePackage(parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(boundImsServicePackage);
                    return true;
                case 101:
                    parcel.enforceInterface(DESCRIPTOR);
                    getImsMmTelFeatureState(parcel.readInt(), IIntegerConsumer.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 102:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNetworkSelectionModeAutomatic(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 103:
                    parcel.enforceInterface(DESCRIPTOR);
                    CellNetworkScanResult cellNetworkScanResults = getCellNetworkScanResults(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (cellNetworkScanResults != null) {
                        parcel2.writeInt(1);
                        cellNetworkScanResults.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 104:
                    return onTransact$requestNetworkScan$(parcel, parcel2);
                case 105:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopNetworkScan(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 106:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean networkSelectionModeManual = setNetworkSelectionModeManual(parcel.readInt(), parcel.readInt() != 0 ? OperatorInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(networkSelectionModeManual ? 1 : 0);
                    return true;
                case 107:
                    parcel.enforceInterface(DESCRIPTOR);
                    long allowedNetworkTypes = getAllowedNetworkTypes(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(allowedNetworkTypes);
                    return true;
                case 108:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean allowedNetworkTypes2 = setAllowedNetworkTypes(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(allowedNetworkTypes2 ? 1 : 0);
                    return true;
                case 109:
                    parcel.enforceInterface(DESCRIPTOR);
                    long allowedNetworkTypesForReason = getAllowedNetworkTypesForReason(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(allowedNetworkTypesForReason);
                    return true;
                case 110:
                    parcel.enforceInterface(DESCRIPTOR);
                    long effectiveAllowedNetworkTypes = getEffectiveAllowedNetworkTypes(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(effectiveAllowedNetworkTypes);
                    return true;
                case 111:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean allowedNetworkTypesForReason2 = setAllowedNetworkTypesForReason(parcel.readInt(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(allowedNetworkTypesForReason2 ? 1 : 0);
                    return true;
                case 112:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean preferredNetworkType2 = setPreferredNetworkType(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(preferredNetworkType2 ? 1 : 0);
                    return true;
                case 113:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUserDataEnabled(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 114:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean dataEnabled = getDataEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(dataEnabled ? 1 : 0);
                    return true;
                case 115:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsUserDataEnabled = isUserDataEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsUserDataEnabled ? 1 : 0);
                    return true;
                case 116:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDataEnabled = isDataEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDataEnabled ? 1 : 0);
                    return true;
                case 117:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsManualNetworkSelectionAllowed = isManualNetworkSelectionAllowed(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsManualNetworkSelectionAllowed ? 1 : 0);
                    return true;
                case 118:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAlwaysReportSignalStrength(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 119:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] pcscfAddress = getPcscfAddress(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(pcscfAddress);
                    return true;
                case 120:
                    parcel.enforceInterface(DESCRIPTOR);
                    setImsRegistrationState(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 121:
                    parcel.enforceInterface(DESCRIPTOR);
                    String cdmaMdn = getCdmaMdn(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(cdmaMdn);
                    return true;
                case 122:
                    parcel.enforceInterface(DESCRIPTOR);
                    String cdmaMin = getCdmaMin(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(cdmaMin);
                    return true;
                case 123:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestNumberVerification(parcel.readInt() != 0 ? PhoneNumberRange.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), INumberVerificationCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 124:
                    parcel.enforceInterface(DESCRIPTOR);
                    int carrierPrivilegeStatus = getCarrierPrivilegeStatus(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(carrierPrivilegeStatus);
                    return true;
                case 125:
                    parcel.enforceInterface(DESCRIPTOR);
                    int carrierPrivilegeStatusForUid = getCarrierPrivilegeStatusForUid(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(carrierPrivilegeStatusForUid);
                    return true;
                case 126:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckCarrierPrivilegesForPackage = checkCarrierPrivilegesForPackage(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckCarrierPrivilegesForPackage);
                    return true;
                case 127:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckCarrierPrivilegesForPackageAnyPhone = checkCarrierPrivilegesForPackageAnyPhone(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckCarrierPrivilegesForPackageAnyPhone);
                    return true;
                case 128:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> carrierPackageNamesForIntentAndPhone = getCarrierPackageNamesForIntentAndPhone(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringList(carrierPackageNamesForIntentAndPhone);
                    return true;
                case 129:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean line1NumberForDisplayForSubscriber = setLine1NumberForDisplayForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(line1NumberForDisplayForSubscriber ? 1 : 0);
                    return true;
                case 130:
                    parcel.enforceInterface(DESCRIPTOR);
                    String line1NumberForDisplay = getLine1NumberForDisplay(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(line1NumberForDisplay);
                    return true;
                case 131:
                    parcel.enforceInterface(DESCRIPTOR);
                    String line1AlphaTagForDisplay = getLine1AlphaTagForDisplay(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(line1AlphaTagForDisplay);
                    return true;
                case 132:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] mergedSubscriberIds = getMergedSubscriberIds(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(mergedSubscriberIds);
                    return true;
                case 133:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] mergedImsisFromGroup = getMergedImsisFromGroup(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(mergedImsisFromGroup);
                    return true;
                case 134:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean operatorBrandOverride = setOperatorBrandOverride(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(operatorBrandOverride ? 1 : 0);
                    return true;
                case 135:
                    return onTransact$setRoamingOverride$(parcel, parcel2);
                case 136:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] bArrCreateByteArray = parcel.createByteArray();
                    int i3 = parcel.readInt();
                    byte[] bArr = i3 >= 0 ? new byte[i3] : null;
                    int iInvokeOemRilRequestRaw = invokeOemRilRequestRaw(bArrCreateByteArray, bArr);
                    parcel2.writeNoException();
                    parcel2.writeInt(iInvokeOemRilRequestRaw);
                    parcel2.writeByteArray(bArr);
                    return true;
                case 137:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zNeedMobileRadioShutdown = needMobileRadioShutdown();
                    parcel2.writeNoException();
                    parcel2.writeInt(zNeedMobileRadioShutdown ? 1 : 0);
                    return true;
                case 138:
                    parcel.enforceInterface(DESCRIPTOR);
                    shutdownMobileRadios();
                    parcel2.writeNoException();
                    return true;
                case 139:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRadioCapability((RadioAccessFamily[]) parcel.createTypedArray(RadioAccessFamily.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 140:
                    parcel.enforceInterface(DESCRIPTOR);
                    int radioAccessFamily = getRadioAccessFamily(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(radioAccessFamily);
                    return true;
                case 141:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableVideoCalling(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 142:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsVideoCallingEnabled = isVideoCallingEnabled(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsVideoCallingEnabled ? 1 : 0);
                    return true;
                case 143:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanChangeDtmfToneLength = canChangeDtmfToneLength(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanChangeDtmfToneLength ? 1 : 0);
                    return true;
                case 144:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsWorldPhone = isWorldPhone(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsWorldPhone ? 1 : 0);
                    return true;
                case 145:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsTtyModeSupported = isTtyModeSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsTtyModeSupported ? 1 : 0);
                    return true;
                case 146:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsRttSupported = isRttSupported(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsRttSupported ? 1 : 0);
                    return true;
                case 147:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsHearingAidCompatibilitySupported = isHearingAidCompatibilitySupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsHearingAidCompatibilitySupported ? 1 : 0);
                    return true;
                case 148:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsImsRegistered = isImsRegistered(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsImsRegistered ? 1 : 0);
                    return true;
                case 149:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsWifiCallingAvailable = isWifiCallingAvailable(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsWifiCallingAvailable ? 1 : 0);
                    return true;
                case 150:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsVideoTelephonyAvailable = isVideoTelephonyAvailable(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsVideoTelephonyAvailable ? 1 : 0);
                    return true;
                case 151:
                    parcel.enforceInterface(DESCRIPTOR);
                    int imsRegTechnologyForMmTel = getImsRegTechnologyForMmTel(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(imsRegTechnologyForMmTel);
                    return true;
                case 152:
                    parcel.enforceInterface(DESCRIPTOR);
                    String deviceId = getDeviceId(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(deviceId);
                    return true;
                case 153:
                    parcel.enforceInterface(DESCRIPTOR);
                    String deviceIdWithFeature = getDeviceIdWithFeature(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(deviceIdWithFeature);
                    return true;
                case 154:
                    parcel.enforceInterface(DESCRIPTOR);
                    String imeiForSlot = getImeiForSlot(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(imeiForSlot);
                    return true;
                case 155:
                    parcel.enforceInterface(DESCRIPTOR);
                    String typeAllocationCodeForSlot = getTypeAllocationCodeForSlot(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(typeAllocationCodeForSlot);
                    return true;
                case 156:
                    parcel.enforceInterface(DESCRIPTOR);
                    String meidForSlot = getMeidForSlot(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(meidForSlot);
                    return true;
                case 157:
                    parcel.enforceInterface(DESCRIPTOR);
                    String manufacturerCodeForSlot = getManufacturerCodeForSlot(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(manufacturerCodeForSlot);
                    return true;
                case 158:
                    parcel.enforceInterface(DESCRIPTOR);
                    String deviceSoftwareVersionForSlot = getDeviceSoftwareVersionForSlot(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(deviceSoftwareVersionForSlot);
                    return true;
                case 159:
                    parcel.enforceInterface(DESCRIPTOR);
                    int subIdForPhoneAccount = getSubIdForPhoneAccount(parcel.readInt() != 0 ? PhoneAccount.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(subIdForPhoneAccount);
                    return true;
                case 160:
                    parcel.enforceInterface(DESCRIPTOR);
                    int subIdForPhoneAccountHandle = getSubIdForPhoneAccountHandle(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(subIdForPhoneAccountHandle);
                    return true;
                case 161:
                    parcel.enforceInterface(DESCRIPTOR);
                    PhoneAccountHandle phoneAccountHandleForSubscriptionId = getPhoneAccountHandleForSubscriptionId(parcel.readInt());
                    parcel2.writeNoException();
                    if (phoneAccountHandleForSubscriptionId != null) {
                        parcel2.writeInt(1);
                        phoneAccountHandleForSubscriptionId.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 162:
                    parcel.enforceInterface(DESCRIPTOR);
                    factoryReset(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 163:
                    parcel.enforceInterface(DESCRIPTOR);
                    String simLocaleForSubscriber = getSimLocaleForSubscriber(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(simLocaleForSubscriber);
                    return true;
                case 164:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestModemActivityInfo(parcel.readInt() != 0 ? ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 165:
                    parcel.enforceInterface(DESCRIPTOR);
                    ServiceState serviceStateForSubscriber = getServiceStateForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (serviceStateForSubscriber != null) {
                        parcel2.writeInt(1);
                        serviceStateForSubscriber.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 166:
                    parcel.enforceInterface(DESCRIPTOR);
                    Uri voicemailRingtoneUri = getVoicemailRingtoneUri(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (voicemailRingtoneUri != null) {
                        parcel2.writeInt(1);
                        voicemailRingtoneUri.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 167:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVoicemailRingtoneUri(parcel.readString(), parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 168:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsVoicemailVibrationEnabled = isVoicemailVibrationEnabled(parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsVoicemailVibrationEnabled ? 1 : 0);
                    return true;
                case 169:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVoicemailVibrationEnabled(parcel.readString(), parcel.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 170:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> packagesWithCarrierPrivileges = getPackagesWithCarrierPrivileges(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringList(packagesWithCarrierPrivileges);
                    return true;
                case 171:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> packagesWithCarrierPrivilegesForAllPhones = getPackagesWithCarrierPrivilegesForAllPhones();
                    parcel2.writeNoException();
                    parcel2.writeStringList(packagesWithCarrierPrivilegesForAllPhones);
                    return true;
                case 172:
                    parcel.enforceInterface(DESCRIPTOR);
                    String aidForAppType = getAidForAppType(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(aidForAppType);
                    return true;
                case 173:
                    parcel.enforceInterface(DESCRIPTOR);
                    String esn = getEsn(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(esn);
                    return true;
                case 174:
                    parcel.enforceInterface(DESCRIPTOR);
                    String cdmaPrlVersion = getCdmaPrlVersion(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(cdmaPrlVersion);
                    return true;
                case 175:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<TelephonyHistogram> telephonyHistograms = getTelephonyHistograms();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(telephonyHistograms);
                    return true;
                case 176:
                    parcel.enforceInterface(DESCRIPTOR);
                    int allowedCarriers = setAllowedCarriers(parcel.readInt() != 0 ? CarrierRestrictionRules.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(allowedCarriers);
                    return true;
                case 177:
                    parcel.enforceInterface(DESCRIPTOR);
                    CarrierRestrictionRules allowedCarriers2 = getAllowedCarriers();
                    parcel2.writeNoException();
                    if (allowedCarriers2 != null) {
                        parcel2.writeInt(1);
                        allowedCarriers2.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 178:
                    parcel.enforceInterface(DESCRIPTOR);
                    int subscriptionCarrierId = getSubscriptionCarrierId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(subscriptionCarrierId);
                    return true;
                case 179:
                    parcel.enforceInterface(DESCRIPTOR);
                    String subscriptionCarrierName = getSubscriptionCarrierName(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(subscriptionCarrierName);
                    return true;
                case 180:
                    parcel.enforceInterface(DESCRIPTOR);
                    int subscriptionSpecificCarrierId = getSubscriptionSpecificCarrierId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(subscriptionSpecificCarrierId);
                    return true;
                case 181:
                    parcel.enforceInterface(DESCRIPTOR);
                    String subscriptionSpecificCarrierName = getSubscriptionSpecificCarrierName(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(subscriptionSpecificCarrierName);
                    return true;
                case 182:
                    parcel.enforceInterface(DESCRIPTOR);
                    int carrierIdFromMccMnc = getCarrierIdFromMccMnc(parcel.readInt(), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(carrierIdFromMccMnc);
                    return true;
                case 183:
                    parcel.enforceInterface(DESCRIPTOR);
                    carrierActionSetMeteredApnsEnabled(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 184:
                    parcel.enforceInterface(DESCRIPTOR);
                    carrierActionSetRadioEnabled(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 185:
                    parcel.enforceInterface(DESCRIPTOR);
                    carrierActionReportDefaultNetworkStatus(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 186:
                    parcel.enforceInterface(DESCRIPTOR);
                    carrierActionResetAll(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 187:
                    parcel.enforceInterface(DESCRIPTOR);
                    CallForwardingInfo callForwarding = getCallForwarding(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (callForwarding != null) {
                        parcel2.writeInt(1);
                        callForwarding.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 188:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean callForwarding2 = setCallForwarding(parcel.readInt(), parcel.readInt() != 0 ? CallForwardingInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(callForwarding2 ? 1 : 0);
                    return true;
                case 189:
                    parcel.enforceInterface(DESCRIPTOR);
                    int callWaitingStatus = getCallWaitingStatus(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(callWaitingStatus);
                    return true;
                case 190:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean callWaitingStatus2 = setCallWaitingStatus(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(callWaitingStatus2 ? 1 : 0);
                    return true;
                case 191:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPolicyDataEnabled(parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 192:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ClientRequestStats> clientRequestStats = getClientRequestStats(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(clientRequestStats);
                    return true;
                case 193:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSimPowerStateForSlot(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 194:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] forbiddenPlmns = getForbiddenPlmns(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(forbiddenPlmns);
                    return true;
                case 195:
                    return onTransact$setForbiddenPlmns$(parcel, parcel2);
                case 196:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean emergencyCallbackMode = getEmergencyCallbackMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(emergencyCallbackMode ? 1 : 0);
                    return true;
                case 197:
                    parcel.enforceInterface(DESCRIPTOR);
                    SignalStrength signalStrength = getSignalStrength(parcel.readInt());
                    parcel2.writeNoException();
                    if (signalStrength != null) {
                        parcel2.writeInt(1);
                        signalStrength.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 198:
                    parcel.enforceInterface(DESCRIPTOR);
                    int cardIdForDefaultEuicc = getCardIdForDefaultEuicc(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(cardIdForDefaultEuicc);
                    return true;
                case 199:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<UiccCardInfo> uiccCardsInfo = getUiccCardsInfo(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(uiccCardsInfo);
                    return true;
                case 200:
                    parcel.enforceInterface(DESCRIPTOR);
                    UiccSlotInfo[] uiccSlotsInfo = getUiccSlotsInfo();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(uiccSlotsInfo, 1);
                    return true;
                case 201:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSwitchSlots = switchSlots(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zSwitchSlots ? 1 : 0);
                    return true;
                case 202:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDataRoamingEnabled = isDataRoamingEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDataRoamingEnabled ? 1 : 0);
                    return true;
                case 203:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDataRoamingEnabled(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 204:
                    parcel.enforceInterface(DESCRIPTOR);
                    int cdmaRoamingMode = getCdmaRoamingMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(cdmaRoamingMode);
                    return true;
                case 205:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean cdmaRoamingMode2 = setCdmaRoamingMode(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(cdmaRoamingMode2 ? 1 : 0);
                    return true;
                case 206:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean cdmaSubscriptionMode = setCdmaSubscriptionMode(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(cdmaSubscriptionMode ? 1 : 0);
                    return true;
                case 207:
                    return onTransact$setCarrierTestOverride$(parcel, parcel2);
                case 208:
                    parcel.enforceInterface(DESCRIPTOR);
                    int carrierIdListVersion = getCarrierIdListVersion(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(carrierIdListVersion);
                    return true;
                case 209:
                    parcel.enforceInterface(DESCRIPTOR);
                    refreshUiccProfile(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 210:
                    parcel.enforceInterface(DESCRIPTOR);
                    int numberOfModemsWithSimultaneousDataConnections = getNumberOfModemsWithSimultaneousDataConnections(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(numberOfModemsWithSimultaneousDataConnections);
                    return true;
                case 211:
                    parcel.enforceInterface(DESCRIPTOR);
                    int networkSelectionMode = getNetworkSelectionMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(networkSelectionMode);
                    return true;
                case 212:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsInEmergencySmsMode = isInEmergencySmsMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInEmergencySmsMode ? 1 : 0);
                    return true;
                case 213:
                    parcel.enforceInterface(DESCRIPTOR);
                    int radioPowerState = getRadioPowerState(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(radioPowerState);
                    return true;
                case 214:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerImsRegistrationCallback(parcel.readInt(), IImsRegistrationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 215:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterImsRegistrationCallback(parcel.readInt(), IImsRegistrationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 216:
                    parcel.enforceInterface(DESCRIPTOR);
                    getImsMmTelRegistrationState(parcel.readInt(), IIntegerConsumer.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 217:
                    parcel.enforceInterface(DESCRIPTOR);
                    getImsMmTelRegistrationTransportType(parcel.readInt(), IIntegerConsumer.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 218:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerMmTelCapabilityCallback(parcel.readInt(), IImsCapabilityCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 219:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterMmTelCapabilityCallback(parcel.readInt(), IImsCapabilityCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 220:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsCapable = isCapable(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsCapable ? 1 : 0);
                    return true;
                case 221:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAvailable = isAvailable(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAvailable ? 1 : 0);
                    return true;
                case 222:
                    parcel.enforceInterface(DESCRIPTOR);
                    isMmTelCapabilitySupported(parcel.readInt(), IIntegerConsumer.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 223:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAdvancedCallingSettingEnabled = isAdvancedCallingSettingEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAdvancedCallingSettingEnabled ? 1 : 0);
                    return true;
                case 224:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAdvancedCallingSettingEnabled(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 225:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsVtSettingEnabled = isVtSettingEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsVtSettingEnabled ? 1 : 0);
                    return true;
                case 226:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVtSettingEnabled(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 227:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsVoWiFiSettingEnabled = isVoWiFiSettingEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsVoWiFiSettingEnabled ? 1 : 0);
                    return true;
                case 228:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVoWiFiSettingEnabled(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 229:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsVoWiFiRoamingSettingEnabled = isVoWiFiRoamingSettingEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsVoWiFiRoamingSettingEnabled ? 1 : 0);
                    return true;
                case 230:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVoWiFiRoamingSettingEnabled(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 231:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVoWiFiNonPersistent(parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 232:
                    parcel.enforceInterface(DESCRIPTOR);
                    int voWiFiModeSetting = getVoWiFiModeSetting(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(voWiFiModeSetting);
                    return true;
                case 233:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVoWiFiModeSetting(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 234:
                    parcel.enforceInterface(DESCRIPTOR);
                    int voWiFiRoamingModeSetting = getVoWiFiRoamingModeSetting(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(voWiFiRoamingModeSetting);
                    return true;
                case 235:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVoWiFiRoamingModeSetting(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 236:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRttCapabilitySetting(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 237:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsTtyOverVolteEnabled = isTtyOverVolteEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsTtyOverVolteEnabled ? 1 : 0);
                    return true;
                case 238:
                    parcel.enforceInterface(DESCRIPTOR);
                    Map emergencyNumberList = getEmergencyNumberList(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeMap(emergencyNumberList);
                    return true;
                case 239:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsEmergencyNumber = isEmergencyNumber(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsEmergencyNumber ? 1 : 0);
                    return true;
                case 240:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> certsFromCarrierPrivilegeAccessRules = getCertsFromCarrierPrivilegeAccessRules(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringList(certsFromCarrierPrivilegeAccessRules);
                    return true;
                case 241:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerImsProvisioningChangedCallback(parcel.readInt(), IImsConfigCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 242:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterImsProvisioningChangedCallback(parcel.readInt(), IImsConfigCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 243:
                    return onTransact$setImsProvisioningStatusForCapability$(parcel, parcel2);
                case 244:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean imsProvisioningStatusForCapability = getImsProvisioningStatusForCapability(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(imsProvisioningStatusForCapability ? 1 : 0);
                    return true;
                case 245:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean rcsProvisioningStatusForCapability = getRcsProvisioningStatusForCapability(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(rcsProvisioningStatusForCapability ? 1 : 0);
                    return true;
                case 246:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRcsProvisioningStatusForCapability(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 247:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsMmTelCapabilityProvisionedInCache = isMmTelCapabilityProvisionedInCache(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsMmTelCapabilityProvisionedInCache ? 1 : 0);
                    return true;
                case 248:
                    return onTransact$cacheMmTelCapabilityProvisioning$(parcel, parcel2);
                case 249:
                    parcel.enforceInterface(DESCRIPTOR);
                    int imsProvisioningInt = getImsProvisioningInt(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(imsProvisioningInt);
                    return true;
                case 250:
                    parcel.enforceInterface(DESCRIPTOR);
                    String imsProvisioningString = getImsProvisioningString(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(imsProvisioningString);
                    return true;
                case 251:
                    parcel.enforceInterface(DESCRIPTOR);
                    int imsProvisioningInt2 = setImsProvisioningInt(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(imsProvisioningInt2);
                    return true;
                case 252:
                    parcel.enforceInterface(DESCRIPTOR);
                    int imsProvisioningString2 = setImsProvisioningString(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(imsProvisioningString2);
                    return true;
                case 253:
                    parcel.enforceInterface(DESCRIPTOR);
                    startEmergencyCallbackMode();
                    parcel2.writeNoException();
                    return true;
                case 254:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateEmergencyNumberListTestMode(parcel.readInt(), parcel.readInt() != 0 ? EmergencyNumber.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 255:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> emergencyNumberListTestMode = getEmergencyNumberListTestMode();
                    parcel2.writeNoException();
                    parcel2.writeStringList(emergencyNumberListTestMode);
                    return true;
                case 256:
                    parcel.enforceInterface(DESCRIPTOR);
                    int emergencyNumberDbVersion = getEmergencyNumberDbVersion(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(emergencyNumberDbVersion);
                    return true;
                case 257:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyOtaEmergencyNumberDbInstalled();
                    parcel2.writeNoException();
                    return true;
                case 258:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateOtaEmergencyNumberDbFilePath(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 259:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetOtaEmergencyNumberDbFilePath();
                    parcel2.writeNoException();
                    return true;
                case 260:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnableModemForSlot = enableModemForSlot(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnableModemForSlot ? 1 : 0);
                    return true;
                case 261:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMultiSimCarrierRestriction(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 262:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iIsMultiSimSupported = isMultiSimSupported(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iIsMultiSimSupported);
                    return true;
                case 263:
                    parcel.enforceInterface(DESCRIPTOR);
                    switchMultiSimConfig(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 264:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDoesSwitchMultiSimConfigTriggerReboot = doesSwitchMultiSimConfigTriggerReboot(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDoesSwitchMultiSimConfigTriggerReboot ? 1 : 0);
                    return true;
                case 265:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] slotsMapping = getSlotsMapping();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(slotsMapping);
                    return true;
                case 266:
                    parcel.enforceInterface(DESCRIPTOR);
                    int radioHalVersion = getRadioHalVersion();
                    parcel2.writeNoException();
                    parcel2.writeInt(radioHalVersion);
                    return true;
                case 267:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currentPackageName = getCurrentPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(currentPackageName);
                    return true;
                case 268:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsApplicationOnUicc = isApplicationOnUicc(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsApplicationOnUicc ? 1 : 0);
                    return true;
                case 269:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsModemEnabledForSlot = isModemEnabledForSlot(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsModemEnabledForSlot ? 1 : 0);
                    return true;
                case 270:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDataEnabledForApn = isDataEnabledForApn(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDataEnabledForApn ? 1 : 0);
                    return true;
                case 271:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsApnMetered = isApnMetered(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsApnMetered ? 1 : 0);
                    return true;
                case 272:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSystemSelectionChannels(parcel.createTypedArrayList(RadioAccessSpecifier.CREATOR), parcel.readInt(), IBooleanConsumer.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 273:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsMvnoMatched = isMvnoMatched(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsMvnoMatched ? 1 : 0);
                    return true;
                case 274:
                    parcel.enforceInterface(DESCRIPTOR);
                    enqueueSmsPickResult(parcel.readString(), parcel.readString(), IIntegerConsumer.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 275:
                    parcel.enforceInterface(DESCRIPTOR);
                    String mmsUserAgent = getMmsUserAgent(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(mmsUserAgent);
                    return true;
                case 276:
                    parcel.enforceInterface(DESCRIPTOR);
                    String mmsUAProfUrl = getMmsUAProfUrl(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(mmsUAProfUrl);
                    return true;
                case 277:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean dataAllowedDuringVoiceCall = setDataAllowedDuringVoiceCall(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(dataAllowedDuringVoiceCall ? 1 : 0);
                    return true;
                case 278:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDataAllowedInVoiceCall = isDataAllowedInVoiceCall(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDataAllowedInVoiceCall ? 1 : 0);
                    return true;
                case 279:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean alwaysAllowMmsData = setAlwaysAllowMmsData(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(alwaysAllowMmsData ? 1 : 0);
                    return true;
                case 280:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCepEnabled(parcel.readInt() != 0);
                    return true;
                case 281:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyRcsAutoConfigurationReceived(parcel.readInt(), parcel.createByteArray(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 282:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsIccLockEnabled = isIccLockEnabled(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsIccLockEnabled ? 1 : 0);
                    return true;
                case 283:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iccLockEnabled = setIccLockEnabled(parcel.readInt(), parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iccLockEnabled);
                    return true;
                case 284:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iChangeIccLockPassword = changeIccLockPassword(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iChangeIccLockPassword);
                    return true;
                case 285:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestUserActivityNotification();
                    return true;
                case 286:
                    parcel.enforceInterface(DESCRIPTOR);
                    userActivity();
                    return true;
                case 287:
                    parcel.enforceInterface(DESCRIPTOR);
                    String manualNetworkSelectionPlmn = getManualNetworkSelectionPlmn(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(manualNetworkSelectionPlmn);
                    return true;
                case 288:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanConnectTo5GInDsdsMode = canConnectTo5GInDsdsMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanConnectTo5GInDsdsMode ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITelephony {
            public static ITelephony sDefaultImpl;
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

            @Override // com.android.internal.telephony.ITelephony
            public void dial(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dial(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void call(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().call(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRadioOn(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRadioOn(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRadioOnWithFeature(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRadioOnWithFeature(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRadioOnForSubscriber(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRadioOnForSubscriber(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRadioOnForSubscriberWithFeature(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRadioOnForSubscriberWithFeature(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean supplyPinForSubscriber(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supplyPinForSubscriber(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean supplyPukForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supplyPukForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int[] supplyPinReportResultForSubscriber(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supplyPinReportResultForSubscriber(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int[] supplyPukReportResultForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supplyPukReportResultForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean handlePinMmi(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handlePinMmi(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void handleUssdRequest(int i, String str, ResultReceiver resultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (resultReceiver != null) {
                        parcelObtain.writeInt(1);
                        resultReceiver.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handleUssdRequest(i, str, resultReceiver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean handlePinMmiForSubscriber(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handlePinMmiForSubscriber(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void toggleRadioOnOff() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().toggleRadioOnOff();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void toggleRadioOnOffForSubscriber(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().toggleRadioOnOffForSubscriber(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setRadio(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRadio(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setRadioForSubscriber(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRadioForSubscriber(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setRadioPower(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRadioPower(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void updateServiceLocation() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateServiceLocation();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void updateServiceLocationForSubscriber(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateServiceLocationForSubscriber(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void enableLocationUpdates() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableLocationUpdates();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void enableLocationUpdatesForSubscriber(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableLocationUpdatesForSubscriber(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void disableLocationUpdates() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableLocationUpdates();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void disableLocationUpdatesForSubscriber(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableLocationUpdatesForSubscriber(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean enableDataConnectivity() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableDataConnectivity();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean disableDataConnectivity() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disableDataConnectivity();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isDataConnectivityPossible(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDataConnectivityPossible(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public CellIdentity getCellLocation(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCellLocation(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CellIdentity.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getNetworkCountryIsoForPhone(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNetworkCountryIsoForPhone(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<NeighboringCellInfo> getNeighboringCellInfo(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNeighboringCellInfo(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(NeighboringCellInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCallState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCallStateForSlot(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallStateForSlot(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataActivity() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataActivity();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataActivityForSubId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataActivityForSubId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataStateForSubId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataStateForSubId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getActivePhoneType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActivePhoneType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getActivePhoneTypeForSlot(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActivePhoneTypeForSlot(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCdmaEriIconIndex(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaEriIconIndex(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCdmaEriIconIndexForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaEriIconIndexForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCdmaEriIconMode(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaEriIconMode(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCdmaEriIconModeForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaEriIconModeForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCdmaEriText(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaEriText(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCdmaEriTextForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaEriTextForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean needsOtaServiceProvisioning() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().needsOtaServiceProvisioning();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setVoiceMailNumber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setVoiceMailNumber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setVoiceActivationState(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVoiceActivationState(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setDataActivationState(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDataActivationState(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getVoiceActivationState(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoiceActivationState(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataActivationState(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataActivationState(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getVoiceMessageCountForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoiceMessageCountForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isConcurrentVoiceAndDataAllowed(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isConcurrentVoiceAndDataAllowed(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public Bundle getVisualVoicemailSettings(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVisualVoicemailSettings(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getVisualVoicemailPackageName(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVisualVoicemailPackageName(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void enableVisualVoicemailSmsFilter(String str, int i, VisualVoicemailSmsFilterSettings visualVoicemailSmsFilterSettings) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (visualVoicemailSmsFilterSettings != null) {
                        parcelObtain.writeInt(1);
                        visualVoicemailSmsFilterSettings.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(55, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableVisualVoicemailSmsFilter(str, i, visualVoicemailSmsFilterSettings);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void disableVisualVoicemailSmsFilter(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(56, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().disableVisualVoicemailSmsFilter(str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public VisualVoicemailSmsFilterSettings getVisualVoicemailSmsFilterSettings(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(57, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVisualVoicemailSmsFilterSettings(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? VisualVoicemailSmsFilterSettings.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public VisualVoicemailSmsFilterSettings getActiveVisualVoicemailSmsFilterSettings(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(58, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveVisualVoicemailSmsFilterSettings(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? VisualVoicemailSmsFilterSettings.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void sendVisualVoicemailSmsForSubscriber(String str, String str2, int i, String str3, int i2, String str4, PendingIntent pendingIntent) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str4);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    try {
                        if (!this.mRemote.transact(59, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().sendVisualVoicemailSmsForSubscriber(str, str2, i, str3, i2, str4, pendingIntent);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        } else {
                            parcelObtain2.readException();
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void sendDialerSpecialCode(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(60, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendDialerSpecialCode(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getNetworkTypeForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(61, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNetworkTypeForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataNetworkType(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(62, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataNetworkType(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataNetworkTypeForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(63, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataNetworkTypeForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getVoiceNetworkTypeForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(64, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoiceNetworkTypeForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean hasIccCard() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(65, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasIccCard();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean hasIccCardUsingSlotIndex(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(66, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasIccCardUsingSlotIndex(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getLteOnCdmaMode(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(67, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLteOnCdmaMode(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getLteOnCdmaModeForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(68, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLteOnCdmaModeForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<CellInfo> getAllCellInfo(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(69, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllCellInfo(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(CellInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void requestCellInfoUpdate(int i, ICellInfoCallback iCellInfoCallback, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iCellInfoCallback != null ? iCellInfoCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(70, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestCellInfoUpdate(i, iCellInfoCallback, str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void requestCellInfoUpdateWithWorkSource(int i, ICellInfoCallback iCellInfoCallback, String str, String str2, WorkSource workSource) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iCellInfoCallback != null ? iCellInfoCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (workSource != null) {
                        parcelObtain.writeInt(1);
                        workSource.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(71, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestCellInfoUpdateWithWorkSource(i, iCellInfoCallback, str, str2, workSource);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setCellInfoListRate(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(72, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCellInfoListRate(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public IccOpenLogicalChannelResponse iccOpenLogicalChannelBySlot(int i, String str, String str2, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(73, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().iccOpenLogicalChannelBySlot(i, str, str2, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IccOpenLogicalChannelResponse.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public IccOpenLogicalChannelResponse iccOpenLogicalChannel(int i, String str, String str2, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(74, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().iccOpenLogicalChannel(i, str, str2, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IccOpenLogicalChannelResponse.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean iccCloseLogicalChannelBySlot(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(75, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().iccCloseLogicalChannelBySlot(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean iccCloseLogicalChannel(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(76, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().iccCloseLogicalChannel(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String iccTransmitApduLogicalChannelBySlot(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    parcelObtain.writeInt(i6);
                    parcelObtain.writeInt(i7);
                    parcelObtain.writeString(str);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(77, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        String strIccTransmitApduLogicalChannelBySlot = Stub.getDefaultImpl().iccTransmitApduLogicalChannelBySlot(i, i2, i3, i4, i5, i6, i7, str);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return strIccTransmitApduLogicalChannelBySlot;
                    }
                    parcelObtain2.readException();
                    String string = parcelObtain2.readString();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return string;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String iccTransmitApduLogicalChannel(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    parcelObtain.writeInt(i6);
                    parcelObtain.writeInt(i7);
                    parcelObtain.writeString(str);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(78, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        String strIccTransmitApduLogicalChannel = Stub.getDefaultImpl().iccTransmitApduLogicalChannel(i, i2, i3, i4, i5, i6, i7, str);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return strIccTransmitApduLogicalChannel;
                    }
                    parcelObtain2.readException();
                    String string = parcelObtain2.readString();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return string;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String iccTransmitApduBasicChannelBySlot(int i, String str, int i2, int i3, int i4, int i5, int i6, String str2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    parcelObtain.writeInt(i6);
                    parcelObtain.writeString(str2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(79, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        String strIccTransmitApduBasicChannelBySlot = Stub.getDefaultImpl().iccTransmitApduBasicChannelBySlot(i, str, i2, i3, i4, i5, i6, str2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return strIccTransmitApduBasicChannelBySlot;
                    }
                    parcelObtain2.readException();
                    String string = parcelObtain2.readString();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return string;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String iccTransmitApduBasicChannel(int i, String str, int i2, int i3, int i4, int i5, int i6, String str2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    parcelObtain.writeInt(i6);
                    parcelObtain.writeString(str2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(80, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        String strIccTransmitApduBasicChannel = Stub.getDefaultImpl().iccTransmitApduBasicChannel(i, str, i2, i3, i4, i5, i6, str2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return strIccTransmitApduBasicChannel;
                    }
                    parcelObtain2.readException();
                    String string = parcelObtain2.readString();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return string;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public byte[] iccExchangeSimIO(int i, int i2, int i3, int i4, int i5, int i6, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    parcelObtain.writeInt(i6);
                    parcelObtain.writeString(str);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(81, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        byte[] bArrIccExchangeSimIO = Stub.getDefaultImpl().iccExchangeSimIO(i, i2, i3, i4, i5, i6, str);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return bArrIccExchangeSimIO;
                    }
                    parcelObtain2.readException();
                    byte[] bArrCreateByteArray = parcelObtain2.createByteArray();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return bArrCreateByteArray;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String sendEnvelopeWithStatus(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(82, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendEnvelopeWithStatus(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String nvReadItem(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(83, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().nvReadItem(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean nvWriteItem(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(84, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().nvWriteItem(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean nvWriteCdmaPrl(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(85, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().nvWriteCdmaPrl(bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean resetModemConfig(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(86, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resetModemConfig(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean rebootModem(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(87, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().rebootModem(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCalculatedPreferredNetworkType(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(88, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCalculatedPreferredNetworkType(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getPreferredNetworkType(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(89, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreferredNetworkType(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isTetheringApnRequiredForSubscriber(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(90, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTetheringApnRequiredForSubscriber(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void enableIms(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(91, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableIms(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void disableIms(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(92, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableIms(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void resetIms(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(93, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetIms(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public IImsMmTelFeature getMmTelFeatureAndListen(int i, IImsServiceFeatureCallback iImsServiceFeatureCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iImsServiceFeatureCallback != null ? iImsServiceFeatureCallback.asBinder() : null);
                    if (!this.mRemote.transact(94, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMmTelFeatureAndListen(i, iImsServiceFeatureCallback);
                    }
                    parcelObtain2.readException();
                    return IImsMmTelFeature.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public IImsRcsFeature getRcsFeatureAndListen(int i, IImsServiceFeatureCallback iImsServiceFeatureCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iImsServiceFeatureCallback != null ? iImsServiceFeatureCallback.asBinder() : null);
                    if (!this.mRemote.transact(95, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRcsFeatureAndListen(i, iImsServiceFeatureCallback);
                    }
                    parcelObtain2.readException();
                    return IImsRcsFeature.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void unregisterImsFeatureCallback(int i, int i2, IImsServiceFeatureCallback iImsServiceFeatureCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iImsServiceFeatureCallback != null ? iImsServiceFeatureCallback.asBinder() : null);
                    if (!this.mRemote.transact(96, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterImsFeatureCallback(i, i2, iImsServiceFeatureCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public IImsRegistration getImsRegistration(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(97, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsRegistration(i, i2);
                    }
                    parcelObtain2.readException();
                    return IImsRegistration.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public IImsConfig getImsConfig(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(98, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsConfig(i, i2);
                    }
                    parcelObtain2.readException();
                    return IImsConfig.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setBoundImsServiceOverride(int i, boolean z, int[] iArr, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(99, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setBoundImsServiceOverride(i, z, iArr, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getBoundImsServicePackage(int i, boolean z, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(100, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBoundImsServicePackage(i, z, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void getImsMmTelFeatureState(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iIntegerConsumer != null ? iIntegerConsumer.asBinder() : null);
                    if (!this.mRemote.transact(101, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getImsMmTelFeatureState(i, iIntegerConsumer);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setNetworkSelectionModeAutomatic(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(102, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNetworkSelectionModeAutomatic(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public CellNetworkScanResult getCellNetworkScanResults(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(103, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCellNetworkScanResults(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CellNetworkScanResult.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int requestNetworkScan(int i, NetworkScanRequest networkScanRequest, Messenger messenger, IBinder iBinder, String str, String str2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (networkScanRequest != null) {
                        parcelObtain.writeInt(1);
                        networkScanRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (messenger != null) {
                        parcelObtain.writeInt(1);
                        messenger.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(104, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        int iRequestNetworkScan = Stub.getDefaultImpl().requestNetworkScan(i, networkScanRequest, messenger, iBinder, str, str2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return iRequestNetworkScan;
                    }
                    parcelObtain2.readException();
                    int i2 = parcelObtain2.readInt();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return i2;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void stopNetworkScan(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(105, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopNetworkScan(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setNetworkSelectionModeManual(int i, OperatorInfo operatorInfo, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (operatorInfo != null) {
                        parcelObtain.writeInt(1);
                        operatorInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(106, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNetworkSelectionModeManual(i, operatorInfo, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public long getAllowedNetworkTypes(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(107, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowedNetworkTypes(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setAllowedNetworkTypes(int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(108, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAllowedNetworkTypes(i, j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public long getAllowedNetworkTypesForReason(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(109, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowedNetworkTypesForReason(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public long getEffectiveAllowedNetworkTypes(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(110, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEffectiveAllowedNetworkTypes(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setAllowedNetworkTypesForReason(int i, int i2, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(111, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAllowedNetworkTypesForReason(i, i2, j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setPreferredNetworkType(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(112, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPreferredNetworkType(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setUserDataEnabled(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(113, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUserDataEnabled(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean getDataEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(114, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isUserDataEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(115, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserDataEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isDataEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(116, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDataEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isManualNetworkSelectionAllowed(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(117, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isManualNetworkSelectionAllowed(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setAlwaysReportSignalStrength(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(118, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAlwaysReportSignalStrength(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String[] getPcscfAddress(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(119, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPcscfAddress(str, str2, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setImsRegistrationState(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(120, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setImsRegistrationState(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCdmaMdn(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(121, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaMdn(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCdmaMin(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(122, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaMin(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void requestNumberVerification(PhoneNumberRange phoneNumberRange, long j, INumberVerificationCallback iNumberVerificationCallback, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneNumberRange != null) {
                        parcelObtain.writeInt(1);
                        phoneNumberRange.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeStrongBinder(iNumberVerificationCallback != null ? iNumberVerificationCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(123, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestNumberVerification(phoneNumberRange, j, iNumberVerificationCallback, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCarrierPrivilegeStatus(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(124, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarrierPrivilegeStatus(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCarrierPrivilegeStatusForUid(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(125, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarrierPrivilegeStatusForUid(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int checkCarrierPrivilegesForPackage(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(126, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkCarrierPrivilegesForPackage(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int checkCarrierPrivilegesForPackageAnyPhone(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(127, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkCarrierPrivilegesForPackageAnyPhone(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<String> getCarrierPackageNamesForIntentAndPhone(Intent intent, int i) throws RemoteException {
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
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(128, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarrierPackageNamesForIntentAndPhone(intent, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setLine1NumberForDisplayForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(129, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLine1NumberForDisplayForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getLine1NumberForDisplay(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(130, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLine1NumberForDisplay(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getLine1AlphaTagForDisplay(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(131, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLine1AlphaTagForDisplay(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String[] getMergedSubscriberIds(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(132, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMergedSubscriberIds(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String[] getMergedImsisFromGroup(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(133, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMergedImsisFromGroup(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setOperatorBrandOverride(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(134, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setOperatorBrandOverride(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setRoamingOverride(int i, List<String> list, List<String> list2, List<String> list3, List<String> list4) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeStringList(list2);
                    parcelObtain.writeStringList(list3);
                    parcelObtain.writeStringList(list4);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(135, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean roamingOverride = Stub.getDefaultImpl().setRoamingOverride(i, list, list2, list3, list4);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return roamingOverride;
                    }
                    parcelObtain2.readException();
                    boolean z = parcelObtain2.readInt() != 0;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int invokeOemRilRequestRaw(byte[] bArr, byte[] bArr2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (bArr2 == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(bArr2.length);
                    }
                    if (!this.mRemote.transact(136, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().invokeOemRilRequestRaw(bArr, bArr2);
                    }
                    parcelObtain2.readException();
                    int i = parcelObtain2.readInt();
                    parcelObtain2.readByteArray(bArr2);
                    return i;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean needMobileRadioShutdown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(137, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().needMobileRadioShutdown();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void shutdownMobileRadios() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(138, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().shutdownMobileRadios();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setRadioCapability(RadioAccessFamily[] radioAccessFamilyArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(radioAccessFamilyArr, 0);
                    if (!this.mRemote.transact(139, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRadioCapability(radioAccessFamilyArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getRadioAccessFamily(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(140, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioAccessFamily(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void enableVideoCalling(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(141, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableVideoCalling(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isVideoCallingEnabled(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(142, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVideoCallingEnabled(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean canChangeDtmfToneLength(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(143, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canChangeDtmfToneLength(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isWorldPhone(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(144, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWorldPhone(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isTtyModeSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(145, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTtyModeSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRttSupported(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(146, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRttSupported(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isHearingAidCompatibilitySupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(147, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHearingAidCompatibilitySupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isImsRegistered(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(148, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isImsRegistered(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isWifiCallingAvailable(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(149, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWifiCallingAvailable(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isVideoTelephonyAvailable(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(150, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVideoTelephonyAvailable(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getImsRegTechnologyForMmTel(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(151, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsRegTechnologyForMmTel(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getDeviceId(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(152, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceId(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getDeviceIdWithFeature(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(153, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceIdWithFeature(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getImeiForSlot(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(154, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImeiForSlot(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getTypeAllocationCodeForSlot(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(155, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTypeAllocationCodeForSlot(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getMeidForSlot(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(156, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMeidForSlot(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getManufacturerCodeForSlot(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(157, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getManufacturerCodeForSlot(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getDeviceSoftwareVersionForSlot(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(158, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceSoftwareVersionForSlot(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getSubIdForPhoneAccount(PhoneAccount phoneAccount) throws RemoteException {
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
                    if (!this.mRemote.transact(159, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubIdForPhoneAccount(phoneAccount);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getSubIdForPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException {
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
                    if (!this.mRemote.transact(160, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubIdForPhoneAccountHandle(phoneAccountHandle, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public PhoneAccountHandle getPhoneAccountHandleForSubscriptionId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(161, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPhoneAccountHandleForSubscriptionId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PhoneAccountHandle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void factoryReset(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(162, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().factoryReset(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getSimLocaleForSubscriber(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(163, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSimLocaleForSubscriber(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void requestModemActivityInfo(ResultReceiver resultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (resultReceiver != null) {
                        parcelObtain.writeInt(1);
                        resultReceiver.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(164, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestModemActivityInfo(resultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public ServiceState getServiceStateForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(165, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getServiceStateForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ServiceState.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public Uri getVoicemailRingtoneUri(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
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
                    if (!this.mRemote.transact(166, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoicemailRingtoneUri(phoneAccountHandle);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setVoicemailRingtoneUri(String str, PhoneAccountHandle phoneAccountHandle, Uri uri) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(167, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVoicemailRingtoneUri(str, phoneAccountHandle, uri);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isVoicemailVibrationEnabled(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
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
                    if (!this.mRemote.transact(168, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVoicemailVibrationEnabled(phoneAccountHandle);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setVoicemailVibrationEnabled(String str, PhoneAccountHandle phoneAccountHandle, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    int i = 1;
                    if (phoneAccountHandle != null) {
                        parcelObtain.writeInt(1);
                        phoneAccountHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(169, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVoicemailVibrationEnabled(str, phoneAccountHandle, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<String> getPackagesWithCarrierPrivileges(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(170, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackagesWithCarrierPrivileges(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<String> getPackagesWithCarrierPrivilegesForAllPhones() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(171, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackagesWithCarrierPrivilegesForAllPhones();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getAidForAppType(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(172, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAidForAppType(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getEsn(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(173, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEsn(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCdmaPrlVersion(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(174, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaPrlVersion(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<TelephonyHistogram> getTelephonyHistograms() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(175, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTelephonyHistograms();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(TelephonyHistogram.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int setAllowedCarriers(CarrierRestrictionRules carrierRestrictionRules) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (carrierRestrictionRules != null) {
                        parcelObtain.writeInt(1);
                        carrierRestrictionRules.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(176, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAllowedCarriers(carrierRestrictionRules);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public CarrierRestrictionRules getAllowedCarriers() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(177, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowedCarriers();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CarrierRestrictionRules.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getSubscriptionCarrierId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(178, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubscriptionCarrierId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getSubscriptionCarrierName(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(179, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubscriptionCarrierName(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getSubscriptionSpecificCarrierId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(180, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubscriptionSpecificCarrierId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getSubscriptionSpecificCarrierName(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(181, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubscriptionSpecificCarrierName(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCarrierIdFromMccMnc(int i, String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(182, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarrierIdFromMccMnc(i, str, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void carrierActionSetMeteredApnsEnabled(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(183, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().carrierActionSetMeteredApnsEnabled(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void carrierActionSetRadioEnabled(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(184, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().carrierActionSetRadioEnabled(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void carrierActionReportDefaultNetworkStatus(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(185, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().carrierActionReportDefaultNetworkStatus(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void carrierActionResetAll(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(186, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().carrierActionResetAll(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public CallForwardingInfo getCallForwarding(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(187, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallForwarding(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CallForwardingInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setCallForwarding(int i, CallForwardingInfo callForwardingInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (callForwardingInfo != null) {
                        parcelObtain.writeInt(1);
                        callForwardingInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(188, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCallForwarding(i, callForwardingInfo);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCallWaitingStatus(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(189, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallWaitingStatus(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setCallWaitingStatus(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(190, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCallWaitingStatus(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setPolicyDataEnabled(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(191, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPolicyDataEnabled(z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<ClientRequestStats> getClientRequestStats(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(192, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getClientRequestStats(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ClientRequestStats.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setSimPowerStateForSlot(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(193, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSimPowerStateForSlot(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String[] getForbiddenPlmns(int i, int i2, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(194, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getForbiddenPlmns(i, i2, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int setForbiddenPlmns(int i, int i2, List<String> list, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(195, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setForbiddenPlmns(i, i2, list, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean getEmergencyCallbackMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(196, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEmergencyCallbackMode(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public SignalStrength getSignalStrength(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(197, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSignalStrength(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SignalStrength.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCardIdForDefaultEuicc(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(198, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCardIdForDefaultEuicc(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<UiccCardInfo> getUiccCardsInfo(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(199, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUiccCardsInfo(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(UiccCardInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public UiccSlotInfo[] getUiccSlotsInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(200, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUiccSlotsInfo();
                    }
                    parcelObtain2.readException();
                    return (UiccSlotInfo[]) parcelObtain2.createTypedArray(UiccSlotInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean switchSlots(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(201, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().switchSlots(iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isDataRoamingEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(202, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDataRoamingEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setDataRoamingEnabled(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(203, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDataRoamingEnabled(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCdmaRoamingMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(204, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaRoamingMode(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setCdmaRoamingMode(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(205, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCdmaRoamingMode(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setCdmaSubscriptionMode(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(206, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCdmaSubscriptionMode(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setCarrierTestOverride(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    parcelObtain.writeString(str5);
                    parcelObtain.writeString(str6);
                    parcelObtain.writeString(str7);
                    parcelObtain.writeString(str8);
                    parcelObtain.writeString(str9);
                    try {
                        if (!this.mRemote.transact(207, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().setCarrierTestOverride(i, str, str2, str3, str4, str5, str6, str7, str8, str9);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        } else {
                            parcelObtain2.readException();
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCarrierIdListVersion(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(208, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarrierIdListVersion(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void refreshUiccProfile(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(209, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().refreshUiccProfile(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getNumberOfModemsWithSimultaneousDataConnections(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(210, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNumberOfModemsWithSimultaneousDataConnections(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getNetworkSelectionMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(211, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNetworkSelectionMode(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isInEmergencySmsMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(212, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInEmergencySmsMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getRadioPowerState(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(213, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioPowerState(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void registerImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iImsRegistrationCallback != null ? iImsRegistrationCallback.asBinder() : null);
                    if (!this.mRemote.transact(214, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerImsRegistrationCallback(i, iImsRegistrationCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void unregisterImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iImsRegistrationCallback != null ? iImsRegistrationCallback.asBinder() : null);
                    if (!this.mRemote.transact(215, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterImsRegistrationCallback(i, iImsRegistrationCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void getImsMmTelRegistrationState(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iIntegerConsumer != null ? iIntegerConsumer.asBinder() : null);
                    if (!this.mRemote.transact(216, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getImsMmTelRegistrationState(i, iIntegerConsumer);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void getImsMmTelRegistrationTransportType(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iIntegerConsumer != null ? iIntegerConsumer.asBinder() : null);
                    if (!this.mRemote.transact(217, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getImsMmTelRegistrationTransportType(i, iIntegerConsumer);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void registerMmTelCapabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iImsCapabilityCallback != null ? iImsCapabilityCallback.asBinder() : null);
                    if (!this.mRemote.transact(218, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerMmTelCapabilityCallback(i, iImsCapabilityCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void unregisterMmTelCapabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iImsCapabilityCallback != null ? iImsCapabilityCallback.asBinder() : null);
                    if (!this.mRemote.transact(219, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterMmTelCapabilityCallback(i, iImsCapabilityCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isCapable(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(220, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCapable(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isAvailable(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(221, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAvailable(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void isMmTelCapabilitySupported(int i, IIntegerConsumer iIntegerConsumer, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iIntegerConsumer != null ? iIntegerConsumer.asBinder() : null);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(222, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().isMmTelCapabilitySupported(i, iIntegerConsumer, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isAdvancedCallingSettingEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(223, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAdvancedCallingSettingEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setAdvancedCallingSettingEnabled(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(224, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAdvancedCallingSettingEnabled(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isVtSettingEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(225, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVtSettingEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setVtSettingEnabled(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(226, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVtSettingEnabled(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isVoWiFiSettingEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(227, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVoWiFiSettingEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setVoWiFiSettingEnabled(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(228, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVoWiFiSettingEnabled(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isVoWiFiRoamingSettingEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(229, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVoWiFiRoamingSettingEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setVoWiFiRoamingSettingEnabled(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(230, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVoWiFiRoamingSettingEnabled(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setVoWiFiNonPersistent(int i, boolean z, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(231, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVoWiFiNonPersistent(i, z, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getVoWiFiModeSetting(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(232, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoWiFiModeSetting(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setVoWiFiModeSetting(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(233, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVoWiFiModeSetting(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getVoWiFiRoamingModeSetting(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(234, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoWiFiRoamingModeSetting(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setVoWiFiRoamingModeSetting(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(235, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVoWiFiRoamingModeSetting(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setRttCapabilitySetting(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(236, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRttCapabilitySetting(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isTtyOverVolteEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(237, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTtyOverVolteEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public Map getEmergencyNumberList(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(238, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEmergencyNumberList(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isEmergencyNumber(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(239, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isEmergencyNumber(str, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<String> getCertsFromCarrierPrivilegeAccessRules(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(240, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCertsFromCarrierPrivilegeAccessRules(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void registerImsProvisioningChangedCallback(int i, IImsConfigCallback iImsConfigCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iImsConfigCallback != null ? iImsConfigCallback.asBinder() : null);
                    if (!this.mRemote.transact(241, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerImsProvisioningChangedCallback(i, iImsConfigCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void unregisterImsProvisioningChangedCallback(int i, IImsConfigCallback iImsConfigCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iImsConfigCallback != null ? iImsConfigCallback.asBinder() : null);
                    if (!this.mRemote.transact(242, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterImsProvisioningChangedCallback(i, iImsConfigCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setImsProvisioningStatusForCapability(int i, int i2, int i3, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(243, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setImsProvisioningStatusForCapability(i, i2, i3, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean getImsProvisioningStatusForCapability(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(244, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsProvisioningStatusForCapability(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean getRcsProvisioningStatusForCapability(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(245, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRcsProvisioningStatusForCapability(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setRcsProvisioningStatusForCapability(int i, int i2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(246, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRcsProvisioningStatusForCapability(i, i2, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isMmTelCapabilityProvisionedInCache(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(247, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMmTelCapabilityProvisionedInCache(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void cacheMmTelCapabilityProvisioning(int i, int i2, int i3, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(248, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cacheMmTelCapabilityProvisioning(i, i2, i3, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getImsProvisioningInt(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(249, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsProvisioningInt(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getImsProvisioningString(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(250, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsProvisioningString(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int setImsProvisioningInt(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(251, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setImsProvisioningInt(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int setImsProvisioningString(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(252, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setImsProvisioningString(i, i2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void startEmergencyCallbackMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(253, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startEmergencyCallbackMode();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void updateEmergencyNumberListTestMode(int i, EmergencyNumber emergencyNumber) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (emergencyNumber != null) {
                        parcelObtain.writeInt(1);
                        emergencyNumber.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(254, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateEmergencyNumberListTestMode(i, emergencyNumber);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<String> getEmergencyNumberListTestMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(255, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEmergencyNumberListTestMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getEmergencyNumberDbVersion(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(256, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEmergencyNumberDbVersion(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void notifyOtaEmergencyNumberDbInstalled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(257, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyOtaEmergencyNumberDbInstalled();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void updateOtaEmergencyNumberDbFilePath(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(258, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateOtaEmergencyNumberDbFilePath(parcelFileDescriptor);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void resetOtaEmergencyNumberDbFilePath() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(259, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetOtaEmergencyNumberDbFilePath();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean enableModemForSlot(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(260, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableModemForSlot(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setMultiSimCarrierRestriction(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(261, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMultiSimCarrierRestriction(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int isMultiSimSupported(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(262, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMultiSimSupported(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void switchMultiSimConfig(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(263, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().switchMultiSimConfig(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean doesSwitchMultiSimConfigTriggerReboot(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(264, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().doesSwitchMultiSimConfigTriggerReboot(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int[] getSlotsMapping() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(265, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSlotsMapping();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getRadioHalVersion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(266, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioHalVersion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCurrentPackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(267, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentPackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isApplicationOnUicc(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(268, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isApplicationOnUicc(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isModemEnabledForSlot(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(269, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isModemEnabledForSlot(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isDataEnabledForApn(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(270, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDataEnabledForApn(i, i2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isApnMetered(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(271, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isApnMetered(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setSystemSelectionChannels(List<RadioAccessSpecifier> list, int i, IBooleanConsumer iBooleanConsumer) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBooleanConsumer != null ? iBooleanConsumer.asBinder() : null);
                    if (this.mRemote.transact(272, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setSystemSelectionChannels(list, i, iBooleanConsumer);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isMvnoMatched(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(273, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMvnoMatched(i, i2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void enqueueSmsPickResult(String str, String str2, IIntegerConsumer iIntegerConsumer) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iIntegerConsumer != null ? iIntegerConsumer.asBinder() : null);
                    if (this.mRemote.transact(274, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().enqueueSmsPickResult(str, str2, iIntegerConsumer);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getMmsUserAgent(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(275, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMmsUserAgent(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getMmsUAProfUrl(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(276, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMmsUAProfUrl(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setDataAllowedDuringVoiceCall(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(277, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDataAllowedDuringVoiceCall(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isDataAllowedInVoiceCall(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(278, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDataAllowedInVoiceCall(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setAlwaysAllowMmsData(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(279, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAlwaysAllowMmsData(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setCepEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(280, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setCepEnabled(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void notifyRcsAutoConfigurationReceived(int i, byte[] bArr, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(281, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRcsAutoConfigurationReceived(i, bArr, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isIccLockEnabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(282, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isIccLockEnabled(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int setIccLockEnabled(int i, boolean z, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(283, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setIccLockEnabled(i, z, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int changeIccLockPassword(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(284, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().changeIccLockPassword(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void requestUserActivityNotification() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(285, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestUserActivityNotification();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void userActivity() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(286, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().userActivity();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getManualNetworkSelectionPlmn(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(287, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getManualNetworkSelectionPlmn(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean canConnectTo5GInDsdsMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(288, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canConnectTo5GInDsdsMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        private boolean onTransact$sendVisualVoicemailSmsForSubscriber$(Parcel parcel, Parcel parcel2) throws RemoteException {
            parcel.enforceInterface(DESCRIPTOR);
            sendVisualVoicemailSmsForSubscriber(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        private boolean onTransact$requestCellInfoUpdateWithWorkSource$(Parcel parcel, Parcel parcel2) throws RemoteException {
            parcel.enforceInterface(DESCRIPTOR);
            requestCellInfoUpdateWithWorkSource(parcel.readInt(), ICellInfoCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? WorkSource.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        private boolean onTransact$iccTransmitApduLogicalChannelBySlot$(Parcel parcel, Parcel parcel2) throws RemoteException {
            parcel.enforceInterface(DESCRIPTOR);
            String strIccTransmitApduLogicalChannelBySlot = iccTransmitApduLogicalChannelBySlot(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeString(strIccTransmitApduLogicalChannelBySlot);
            return true;
        }

        private boolean onTransact$iccTransmitApduLogicalChannel$(Parcel parcel, Parcel parcel2) throws RemoteException {
            parcel.enforceInterface(DESCRIPTOR);
            String strIccTransmitApduLogicalChannel = iccTransmitApduLogicalChannel(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeString(strIccTransmitApduLogicalChannel);
            return true;
        }

        private boolean onTransact$iccTransmitApduBasicChannelBySlot$(Parcel parcel, Parcel parcel2) throws RemoteException {
            parcel.enforceInterface(DESCRIPTOR);
            String strIccTransmitApduBasicChannelBySlot = iccTransmitApduBasicChannelBySlot(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeString(strIccTransmitApduBasicChannelBySlot);
            return true;
        }

        private boolean onTransact$iccTransmitApduBasicChannel$(Parcel parcel, Parcel parcel2) throws RemoteException {
            parcel.enforceInterface(DESCRIPTOR);
            String strIccTransmitApduBasicChannel = iccTransmitApduBasicChannel(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeString(strIccTransmitApduBasicChannel);
            return true;
        }

        private boolean onTransact$iccExchangeSimIO$(Parcel parcel, Parcel parcel2) throws RemoteException {
            parcel.enforceInterface(DESCRIPTOR);
            byte[] bArrIccExchangeSimIO = iccExchangeSimIO(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeByteArray(bArrIccExchangeSimIO);
            return true;
        }

        private boolean onTransact$requestNetworkScan$(Parcel parcel, Parcel parcel2) throws RemoteException {
            parcel.enforceInterface(DESCRIPTOR);
            int iRequestNetworkScan = requestNetworkScan(parcel.readInt(), parcel.readInt() != 0 ? NetworkScanRequest.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Messenger.CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder(), parcel.readString(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(iRequestNetworkScan);
            return true;
        }

        private boolean onTransact$setRoamingOverride$(Parcel parcel, Parcel parcel2) throws RemoteException {
            parcel.enforceInterface(DESCRIPTOR);
            boolean roamingOverride = setRoamingOverride(parcel.readInt(), parcel.createStringArrayList(), parcel.createStringArrayList(), parcel.createStringArrayList(), parcel.createStringArrayList());
            parcel2.writeNoException();
            parcel2.writeInt(roamingOverride ? 1 : 0);
            return true;
        }

        private boolean onTransact$setForbiddenPlmns$(Parcel parcel, Parcel parcel2) throws RemoteException {
            parcel.enforceInterface(DESCRIPTOR);
            int forbiddenPlmns = setForbiddenPlmns(parcel.readInt(), parcel.readInt(), parcel.createStringArrayList(), parcel.readString(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(forbiddenPlmns);
            return true;
        }

        private boolean onTransact$setCarrierTestOverride$(Parcel parcel, Parcel parcel2) throws RemoteException {
            parcel.enforceInterface(DESCRIPTOR);
            setCarrierTestOverride(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        private boolean onTransact$setImsProvisioningStatusForCapability$(Parcel parcel, Parcel parcel2) throws RemoteException {
            parcel.enforceInterface(DESCRIPTOR);
            setImsProvisioningStatusForCapability(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
            parcel2.writeNoException();
            return true;
        }

        private boolean onTransact$cacheMmTelCapabilityProvisioning$(Parcel parcel, Parcel parcel2) throws RemoteException {
            parcel.enforceInterface(DESCRIPTOR);
            cacheMmTelCapabilityProvisioning(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
            parcel2.writeNoException();
            return true;
        }

        public static boolean setDefaultImpl(ITelephony iTelephony) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTelephony == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTelephony;
            return true;
        }

        public static ITelephony getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
