package com.android.phone.settings;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.AsyncResult;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.SystemProperties;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthLte;
import android.telephony.DataSpecificRegistrationInfo;
import android.telephony.NetworkRegistrationInfo;
import android.telephony.PhoneStateListener;
import android.telephony.PhysicalChannelConfig;
import android.telephony.PreciseCallState;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.ims.ImsConfig;
import com.android.ims.ImsException;
import com.android.ims.ImsManager;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.phone.R;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class RadioInfo extends AppCompatActivity {
    private static String[] sPhoneIndexLabels;
    private TextView mCallState;
    private Button mCarrierProvisioningButton;
    private Switch mCbrsDataSwitch;
    private TextView mCellInfo;
    private int mCellInfoRefreshRateIndex;
    private Spinner mCellInfoRefreshRateSpinner;
    private TextView mCfi;
    private ConnectivityManager mConnectivityManager;
    private TextView mDBm;
    private TextView mDataNetwork;
    private TextView mDcnrRestricted;
    private TextView mDds;
    private TextView mDeviceId;
    private TextView mDnsCheckState;
    private Button mDnsCheckToggleButton;
    private TextView mDownlinkKbps;
    private Switch mDsdsSwitch;
    private Switch mEabProvisionedSwitch;
    private TextView mEndcAvailable;
    private TextView mGprsState;
    private TextView mGsmState;
    private TextView mHttpClientTest;
    private String mHttpClientTestResult;
    private Switch mImsVolteProvisionedSwitch;
    private Switch mImsVtProvisionedSwitch;
    private Switch mImsWfcProvisionedSwitch;
    private TextView mLine1Number;
    private TextView mLocation;
    private TextView mMwi;
    private TextView mNrAvailable;
    private TextView mNrFrequency;
    private TextView mNrState;
    private Button mOemInfoButton;
    private TextView mOperatorName;
    private TextView mPhyChanConfig;
    private String mPingHostnameResultV4;
    private String mPingHostnameResultV6;
    private TextView mPingHostnameV4;
    private TextView mPingHostnameV6;
    private Button mPingTestButton;
    private Spinner mPreferredNetworkType;
    private int mPreferredNetworkTypeResult;
    private ThreadPoolExecutor mQueuedWork;
    private Switch mRadioPowerOnSwitch;
    private TextView mReceived;
    private Button mRefreshSmscButton;
    private TextView mRoamingState;
    private Spinner mSelectPhoneIndex;
    private int mSelectedPhoneIndex;
    private TextView mSent;
    private EditText mSmsc;
    private TextView mSubscriberId;
    private TextView mSubscriptionId;
    private TelephonyManager mTelephonyManager;
    private Button mTriggerCarrierProvisioningButton;
    private Button mUpdateSmscButton;
    private TextView mUplinkKbps;
    private TextView mVoiceNetwork;
    private static final boolean IS_USER_BUILD = "user".equals(Build.TYPE);
    private static final String[] PREFERRED_NETWORK_LABELS = {"GSM/WCDMA preferred", "GSM only", "WCDMA only", "GSM/WCDMA auto (PRL)", "CDMA/EvDo auto (PRL)", "CDMA only", "EvDo only", "CDMA/EvDo/GSM/WCDMA (PRL)", "CDMA + LTE/EvDo (PRL)", "GSM/WCDMA/LTE (PRL)", "LTE/CDMA/EvDo/GSM/WCDMA (PRL)", "LTE only", "LTE/WCDMA", "TDSCDMA only", "TDSCDMA/WCDMA", "LTE/TDSCDMA", "TDSCDMA/GSM", "LTE/TDSCDMA/GSM", "TDSCDMA/GSM/WCDMA", "LTE/TDSCDMA/WCDMA", "LTE/TDSCDMA/GSM/WCDMA", "TDSCDMA/CDMA/EvDo/GSM/WCDMA ", "LTE/TDSCDMA/CDMA/EvDo/GSM/WCDMA", "NR only", "NR/LTE", "NR/LTE/CDMA/EvDo", "NR/LTE/GSM/WCDMA", "NR/LTE/CDMA/EvDo/GSM/WCDMA", "NR/LTE/WCDMA", "NR/LTE/TDSCDMA", "NR/LTE/TDSCDMA/GSM", "NR/LTE/TDSCDMA/WCDMA", "NR/LTE/TDSCDMA/GSM/WCDMA", "NR/LTE/TDSCDMA/CDMA/EvDo/GSM/WCDMA", "Unknown"};
    private static final String[] CELL_INFO_REFRESH_RATE_LABELS = {"Disabled", "Immediate", "Min 5s", "Min 10s", "Min 60s"};
    private static final int[] CELL_INFO_REFRESH_RATES = {Integer.MAX_VALUE, 0, 5000, 10000, 60000};
    private ImsManager mImsManager = null;
    private Phone mPhone = null;
    private boolean mMwiValue = false;
    private boolean mCfiValue = false;
    private List<CellInfo> mCellInfoResult = null;
    private CellLocation mCellLocationResult = null;
    private final NetworkRequest mDefaultNetworkRequest = new NetworkRequest.Builder().addTransportType(0).addCapability(12).build();
    private final ConnectivityManager.NetworkCallback mNetworkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.android.phone.settings.RadioInfo.1
        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            RadioInfo.this.updateBandwidths(networkCapabilities.getLinkDownstreamBandwidthKbps(), networkCapabilities.getLinkUpstreamBandwidthKbps());
        }
    };
    private PhoneStateListener mPhoneStateListener = new RadioInfoPhoneStateListener();
    private Handler mHandler = new Handler() { // from class: com.android.phone.settings.RadioInfo.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Object obj;
            int i = message.what;
            if (i == 1000) {
                AsyncResult asyncResult = (AsyncResult) message.obj;
                if (asyncResult.exception != null || (obj = asyncResult.result) == null) {
                    RadioInfo.this.updatePreferredNetworkType(RadioInfo.PREFERRED_NETWORK_LABELS.length - 1);
                } else {
                    RadioInfo.this.updatePreferredNetworkType(((int[]) obj)[0]);
                    return;
                }
            }
            if (i == 1001) {
                if (((AsyncResult) message.obj).exception != null) {
                    RadioInfo.log("Set preferred network type failed.");
                    return;
                }
                return;
            }
            switch (i) {
                case 1005:
                    AsyncResult asyncResult2 = (AsyncResult) message.obj;
                    if (asyncResult2.exception != null) {
                        RadioInfo.this.mSmsc.setText("refresh error");
                    } else {
                        RadioInfo.this.mSmsc.setText((String) asyncResult2.result);
                    }
                    break;
                case 1006:
                    RadioInfo.this.mUpdateSmscButton.setEnabled(true);
                    if (((AsyncResult) message.obj).exception != null) {
                        RadioInfo.this.mSmsc.setText("update error");
                    }
                    break;
                case 1007:
                    AsyncResult asyncResult3 = (AsyncResult) message.obj;
                    if (asyncResult3.exception != null) {
                        RadioInfo.this.mPhyChanConfig.setText("update error");
                    }
                    RadioInfo.this.updatePhysicalChannelConfiguration((List) asyncResult3.result);
                    break;
                default:
                    super.handleMessage(message);
                    break;
            }
        }
    };
    private MenuItem.OnMenuItemClickListener mViewADNCallback = new MenuItem.OnMenuItemClickListener() { // from class: com.android.phone.settings.RadioInfo.9
        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClassName("com.android.phone", "com.android.phone.SimContacts");
            RadioInfo.this.startActivity(intent);
            return true;
        }
    };
    private MenuItem.OnMenuItemClickListener mViewFDNCallback = new MenuItem.OnMenuItemClickListener() { // from class: com.android.phone.settings.RadioInfo.10
        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClassName("com.android.phone", "com.android.phone.settings.fdn.FdnList");
            RadioInfo.this.startActivity(intent);
            return true;
        }
    };
    private MenuItem.OnMenuItemClickListener mViewSDNCallback = new MenuItem.OnMenuItemClickListener() { // from class: com.android.phone.settings.RadioInfo.11
        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("content://icc/sdn"));
            intent.setClassName("com.android.phone", "com.android.phone.ADNList");
            RadioInfo.this.startActivity(intent);
            return true;
        }
    };
    private MenuItem.OnMenuItemClickListener mGetImsStatus = new MenuItem.OnMenuItemClickListener() { // from class: com.android.phone.settings.RadioInfo.12
        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            String string;
            boolean zIsImsRegistered = RadioInfo.this.mPhone.isImsRegistered();
            boolean zIsVolteEnabled = RadioInfo.this.mPhone.isVolteEnabled();
            boolean zIsWifiCallingEnabled = RadioInfo.this.mPhone.isWifiCallingEnabled();
            boolean zIsVideoEnabled = RadioInfo.this.mPhone.isVideoEnabled();
            boolean zIsUtEnabled = RadioInfo.this.mPhone.isUtEnabled();
            if (zIsImsRegistered) {
                string = RadioInfo.this.getString(R.string.radio_info_ims_reg_status_registered);
            } else {
                string = RadioInfo.this.getString(R.string.radio_info_ims_reg_status_not_registered);
            }
            String string2 = RadioInfo.this.getString(R.string.radio_info_ims_feature_status_available);
            String string3 = RadioInfo.this.getString(R.string.radio_info_ims_feature_status_unavailable);
            RadioInfo radioInfo = RadioInfo.this;
            Object[] objArr = new Object[5];
            objArr[0] = string;
            objArr[1] = zIsVolteEnabled ? string2 : string3;
            objArr[2] = zIsWifiCallingEnabled ? string2 : string3;
            objArr[3] = zIsVideoEnabled ? string2 : string3;
            if (!zIsUtEnabled) {
                string2 = string3;
            }
            objArr[4] = string2;
            String string4 = radioInfo.getString(R.string.radio_info_ims_reg_status, objArr);
            AlertDialog.Builder builder = new AlertDialog.Builder(RadioInfo.this);
            builder.setMessage(string4);
            builder.setTitle(RadioInfo.this.getString(R.string.radio_info_ims_reg_status_title));
            builder.create().show();
            return true;
        }
    };
    private MenuItem.OnMenuItemClickListener mSelectBandCallback = new MenuItem.OnMenuItemClickListener() { // from class: com.android.phone.settings.RadioInfo.13
        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            Intent intent = new Intent();
            intent.setClass(RadioInfo.this, BandMode.class);
            RadioInfo.this.startActivity(intent);
            return true;
        }
    };
    private MenuItem.OnMenuItemClickListener mToggleData = new MenuItem.OnMenuItemClickListener() { // from class: com.android.phone.settings.RadioInfo.14
        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            int dataState = RadioInfo.this.mTelephonyManager.getDataState();
            if (dataState == 0) {
                RadioInfo.this.mTelephonyManager.setDataEnabled(true);
            } else if (dataState == 2) {
                RadioInfo.this.mTelephonyManager.setDataEnabled(false);
            }
            return true;
        }
    };
    CompoundButton.OnCheckedChangeListener mRadioPowerOnChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.android.phone.settings.RadioInfo.16
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Phone[] phones = PhoneFactory.getPhones();
            if (phones == null) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("toggle radio power: phone*");
            sb.append(phones.length);
            sb.append(" ");
            sb.append(RadioInfo.this.isRadioOn() ? "on" : "off");
            RadioInfo.log(sb.toString());
            for (int i = 0; i < phones.length; i++) {
                if (phones[i] != null) {
                    phones[i].setRadioPower(z);
                }
            }
        }
    };
    CompoundButton.OnCheckedChangeListener mImsVolteCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.android.phone.settings.RadioInfo.17
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            RadioInfo.this.setImsVolteProvisionedState(z);
        }
    };
    CompoundButton.OnCheckedChangeListener mImsVtCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.android.phone.settings.RadioInfo.18
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            RadioInfo.this.setImsVtProvisionedState(z);
        }
    };
    CompoundButton.OnCheckedChangeListener mImsWfcCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.android.phone.settings.RadioInfo.19
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            RadioInfo.this.setImsWfcProvisionedState(z);
        }
    };
    CompoundButton.OnCheckedChangeListener mEabCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.android.phone.settings.RadioInfo.20
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            RadioInfo.this.setEabProvisionedState(z);
        }
    };
    View.OnClickListener mDnsCheckButtonHandler = new View.OnClickListener() { // from class: com.android.phone.settings.RadioInfo.21
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RadioInfo.this.mPhone.disableDnsCheck(!RadioInfo.this.mPhone.isDnsCheckDisabled());
            RadioInfo.this.updateDnsCheckState();
        }
    };
    View.OnClickListener mOemInfoButtonHandler = new View.OnClickListener() { // from class: com.android.phone.settings.RadioInfo.22
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                RadioInfo.this.startActivity(new Intent("com.android.phone.settings.OEM_RADIO_INFO"));
            } catch (ActivityNotFoundException e) {
                RadioInfo.log("OEM-specific Info/Settings Activity Not Found : " + e);
            }
        }
    };
    View.OnClickListener mPingButtonHandler = new View.OnClickListener() { // from class: com.android.phone.settings.RadioInfo.23
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RadioInfo.this.updatePingState();
        }
    };
    View.OnClickListener mUpdateSmscButtonHandler = new View.OnClickListener() { // from class: com.android.phone.settings.RadioInfo.24
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RadioInfo.this.mUpdateSmscButton.setEnabled(false);
            RadioInfo.this.mQueuedWork.execute(new Runnable() { // from class: com.android.phone.settings.RadioInfo.24.1
                @Override // java.lang.Runnable
                public void run() {
                    RadioInfo.this.mPhone.setSmscAddress(RadioInfo.this.mSmsc.getText().toString(), RadioInfo.this.mHandler.obtainMessage(1006));
                }
            });
        }
    };
    View.OnClickListener mRefreshSmscButtonHandler = new View.OnClickListener() { // from class: com.android.phone.settings.RadioInfo.25
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RadioInfo.this.refreshSmsc();
        }
    };
    View.OnClickListener mCarrierProvisioningButtonHandler = new View.OnClickListener() { // from class: com.android.phone.settings.RadioInfo.26
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent("com.android.settings.CARRIER_PROVISIONING");
            intent.setComponent(ComponentName.unflattenFromString("com.android.omadm.service/.DMIntentReceiver"));
            RadioInfo.this.sendBroadcast(intent);
        }
    };
    View.OnClickListener mTriggerCarrierProvisioningButtonHandler = new View.OnClickListener() { // from class: com.android.phone.settings.RadioInfo.27
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent("com.android.settings.TRIGGER_CARRIER_PROVISIONING");
            intent.setComponent(ComponentName.unflattenFromString("com.android.omadm.service/.DMIntentReceiver"));
            RadioInfo.this.sendBroadcast(intent);
        }
    };
    AdapterView.OnItemSelectedListener mPreferredNetworkHandler = new AdapterView.OnItemSelectedListener() { // from class: com.android.phone.settings.RadioInfo.28
        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView adapterView) {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            if (RadioInfo.this.mPreferredNetworkTypeResult == i || i < 0 || i > RadioInfo.PREFERRED_NETWORK_LABELS.length - 2) {
                return;
            }
            RadioInfo.this.mPreferredNetworkTypeResult = i;
            int subId = RadioInfo.this.mPhone.getSubId();
            if (SubscriptionManager.isUsableSubIdValue(subId)) {
                Settings.Global.putInt(RadioInfo.this.mPhone.getContext().getContentResolver(), "preferred_network_mode" + subId, RadioInfo.this.mPreferredNetworkTypeResult);
            }
            RadioInfo.log("Calling setPreferredNetworkType(" + RadioInfo.this.mPreferredNetworkTypeResult + ")");
            RadioInfo.this.mPhone.setPreferredNetworkType(RadioInfo.this.mPreferredNetworkTypeResult, RadioInfo.this.mHandler.obtainMessage(1001));
        }
    };
    AdapterView.OnItemSelectedListener mSelectPhoneIndexHandler = new AdapterView.OnItemSelectedListener() { // from class: com.android.phone.settings.RadioInfo.29
        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView adapterView) {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            Phone[] phones;
            if (i < 0 || i > RadioInfo.sPhoneIndexLabels.length - 1 || (phones = PhoneFactory.getPhones()) == null || phones.length <= i) {
                return;
            }
            int i2 = -1;
            int[] subId = SubscriptionManager.getSubId(i);
            if (subId != null && subId.length > 0) {
                i2 = subId[0];
            }
            RadioInfo.this.mSelectedPhoneIndex = i;
            RadioInfo.this.updatePhoneIndex(i, i2);
        }
    };
    AdapterView.OnItemSelectedListener mCellInfoRefreshRateHandler = new AdapterView.OnItemSelectedListener() { // from class: com.android.phone.settings.RadioInfo.30
        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView adapterView) {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            RadioInfo.this.mCellInfoRefreshRateIndex = i;
            RadioInfo.this.mTelephonyManager.setCellInfoListRate(RadioInfo.CELL_INFO_REFRESH_RATES[i]);
            RadioInfo.this.updateAllCellInfo();
        }
    };
    CompoundButton.OnCheckedChangeListener mCbrsDataSwitchChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.android.phone.settings.RadioInfo.32
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            RadioInfo.this.updateCbrsDataState(z);
        }
    };
    DialogInterface.OnClickListener mOnDsdsDialogConfirmedListener = new DialogInterface.OnClickListener() { // from class: com.android.phone.settings.RadioInfo.33
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == -1) {
                RadioInfo.this.mDsdsSwitch.toggle();
                RadioInfo.this.performDsdsSwitch();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str) {
        Log.d("RadioInfo", str);
    }

    private class RadioInfoPhoneStateListener extends PhoneStateListener {
        private RadioInfoPhoneStateListener() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onDataConnectionStateChanged(int i) {
            RadioInfo.this.updateDataState();
            RadioInfo.this.updateNetworkType();
        }

        @Override // android.telephony.PhoneStateListener
        public void onDataActivity(int i) {
            RadioInfo.this.updateDataStats2();
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            RadioInfo.this.updateNetworkType();
            RadioInfo.this.updatePhoneState(i);
        }

        public void onPreciseCallStateChanged(PreciseCallState preciseCallState) {
            RadioInfo.this.updateNetworkType();
        }

        @Override // android.telephony.PhoneStateListener
        public void onCellLocationChanged(CellLocation cellLocation) {
            RadioInfo.this.updateLocation(cellLocation);
        }

        @Override // android.telephony.PhoneStateListener
        public void onMessageWaitingIndicatorChanged(boolean z) {
            RadioInfo.this.mMwiValue = z;
            RadioInfo.this.updateMessageWaiting();
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallForwardingIndicatorChanged(boolean z) {
            RadioInfo.this.mCfiValue = z;
            RadioInfo.this.updateCallRedirect();
        }

        @Override // android.telephony.PhoneStateListener
        public void onCellInfoChanged(List<CellInfo> list) {
            RadioInfo.log("onCellInfoChanged: arrayCi=" + list);
            RadioInfo.this.mCellInfoResult = list;
            RadioInfo radioInfo = RadioInfo.this;
            radioInfo.updateCellInfo(radioInfo.mCellInfoResult);
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            RadioInfo.log("onSignalStrengthChanged: SignalStrength=" + signalStrength);
            RadioInfo.this.updateSignalStrength(signalStrength);
        }

        @Override // android.telephony.PhoneStateListener
        public void onServiceStateChanged(ServiceState serviceState) {
            RadioInfo.log("onServiceStateChanged: ServiceState=" + serviceState);
            RadioInfo.this.updateServiceState(serviceState);
            RadioInfo.this.updateRadioPowerState();
            RadioInfo.this.updateNetworkType();
            RadioInfo.this.updateImsProvisionedState();
            RadioInfo.this.updateNrStats(serviceState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePhysicalChannelConfiguration(List<PhysicalChannelConfig> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (list != null) {
            String str = "";
            for (PhysicalChannelConfig physicalChannelConfig : list) {
                sb.append(str);
                sb.append(physicalChannelConfig);
                str = ",";
            }
        }
        sb.append("}");
        this.mPhyChanConfig.setText(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreferredNetworkType(int i) {
        if (i >= PREFERRED_NETWORK_LABELS.length || i < 0) {
            log("EVENT_QUERY_PREFERRED_TYPE_DONE: unknown type=" + i);
            i = PREFERRED_NETWORK_LABELS.length - 1;
        }
        this.mPreferredNetworkTypeResult = i;
        this.mPreferredNetworkType.setSelection(i, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePhoneIndex(int i, int i2) {
        unregisterPhoneStateListener();
        this.mTelephonyManager.setCellInfoListRate(Integer.MAX_VALUE);
        this.mTelephonyManager = this.mTelephonyManager.createForSubscriptionId(i2);
        this.mImsManager = ImsManager.getInstance(getApplicationContext(), i);
        this.mPhone = PhoneFactory.getPhone(i);
        updateAllFields();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!Process.myUserHandle().isSystem()) {
            Log.e("RadioInfo", "Not run from system user, don't do anything.");
            finish();
            return;
        }
        setContentView(R.layout.radio_info);
        log("Started onCreate");
        this.mQueuedWork = new ThreadPoolExecutor(1, 1, 300000L, TimeUnit.MICROSECONDS, new LinkedBlockingDeque());
        this.mTelephonyManager = (TelephonyManager) getSystemService("phone");
        this.mConnectivityManager = (ConnectivityManager) getSystemService("connectivity");
        this.mPhone = PhoneFactory.getDefaultPhone();
        this.mImsManager = ImsManager.getInstance(getApplicationContext(), SubscriptionManager.getDefaultVoicePhoneId());
        sPhoneIndexLabels = getPhoneIndexLabels(this.mTelephonyManager);
        this.mDeviceId = (TextView) findViewById(R.id.imei);
        this.mLine1Number = (TextView) findViewById(R.id.number);
        this.mSubscriptionId = (TextView) findViewById(R.id.subid);
        this.mDds = (TextView) findViewById(R.id.dds);
        this.mSubscriberId = (TextView) findViewById(R.id.imsi);
        this.mCallState = (TextView) findViewById(R.id.call);
        this.mOperatorName = (TextView) findViewById(R.id.operator);
        this.mRoamingState = (TextView) findViewById(R.id.roaming);
        this.mGsmState = (TextView) findViewById(R.id.gsm);
        this.mGprsState = (TextView) findViewById(R.id.gprs);
        this.mVoiceNetwork = (TextView) findViewById(R.id.voice_network);
        this.mDataNetwork = (TextView) findViewById(R.id.data_network);
        this.mDBm = (TextView) findViewById(R.id.dbm);
        this.mMwi = (TextView) findViewById(R.id.mwi);
        this.mCfi = (TextView) findViewById(R.id.cfi);
        this.mLocation = (TextView) findViewById(R.id.location);
        TextView textView = (TextView) findViewById(R.id.cellinfo);
        this.mCellInfo = textView;
        textView.setTypeface(Typeface.MONOSPACE);
        this.mSent = (TextView) findViewById(R.id.sent);
        this.mReceived = (TextView) findViewById(R.id.received);
        this.mSmsc = (EditText) findViewById(R.id.smsc);
        this.mDnsCheckState = (TextView) findViewById(R.id.dnsCheckState);
        this.mPingHostnameV4 = (TextView) findViewById(R.id.pingHostnameV4);
        this.mPingHostnameV6 = (TextView) findViewById(R.id.pingHostnameV6);
        this.mHttpClientTest = (TextView) findViewById(R.id.httpClientTest);
        this.mEndcAvailable = (TextView) findViewById(R.id.endc_available);
        this.mDcnrRestricted = (TextView) findViewById(R.id.dcnr_restricted);
        this.mNrAvailable = (TextView) findViewById(R.id.nr_available);
        this.mNrState = (TextView) findViewById(R.id.nr_state);
        this.mNrFrequency = (TextView) findViewById(R.id.nr_frequency);
        this.mPhyChanConfig = (TextView) findViewById(R.id.phy_chan_config);
        if ((this.mTelephonyManager.getSupportedRadioAccessFamily() & 524288) == 0) {
            ((TextView) findViewById(R.id.endc_available_label)).setVisibility(8);
            this.mEndcAvailable.setVisibility(8);
            ((TextView) findViewById(R.id.dcnr_restricted_label)).setVisibility(8);
            this.mDcnrRestricted.setVisibility(8);
            ((TextView) findViewById(R.id.nr_available_label)).setVisibility(8);
            this.mNrAvailable.setVisibility(8);
            ((TextView) findViewById(R.id.nr_state_label)).setVisibility(8);
            this.mNrState.setVisibility(8);
            ((TextView) findViewById(R.id.nr_frequency_label)).setVisibility(8);
            this.mNrFrequency.setVisibility(8);
        }
        this.mPreferredNetworkType = (Spinner) findViewById(R.id.preferredNetworkType);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, PREFERRED_NETWORK_LABELS);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.mPreferredNetworkType.setAdapter((SpinnerAdapter) arrayAdapter);
        this.mSelectPhoneIndex = (Spinner) findViewById(R.id.phoneIndex);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sPhoneIndexLabels);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.mSelectPhoneIndex.setAdapter((SpinnerAdapter) arrayAdapter2);
        this.mCellInfoRefreshRateSpinner = (Spinner) findViewById(R.id.cell_info_rate_select);
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, CELL_INFO_REFRESH_RATE_LABELS);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.mCellInfoRefreshRateSpinner.setAdapter((SpinnerAdapter) arrayAdapter3);
        this.mImsVolteProvisionedSwitch = (Switch) findViewById(R.id.volte_provisioned_switch);
        this.mImsVtProvisionedSwitch = (Switch) findViewById(R.id.vt_provisioned_switch);
        this.mImsWfcProvisionedSwitch = (Switch) findViewById(R.id.wfc_provisioned_switch);
        this.mEabProvisionedSwitch = (Switch) findViewById(R.id.eab_provisioned_switch);
        if (!ImsManager.isImsSupportedOnDevice(this.mPhone.getContext())) {
            this.mImsVolteProvisionedSwitch.setVisibility(8);
            this.mImsVtProvisionedSwitch.setVisibility(8);
            this.mImsWfcProvisionedSwitch.setVisibility(8);
            this.mEabProvisionedSwitch.setVisibility(8);
        }
        Switch r0 = (Switch) findViewById(R.id.cbrs_data_switch);
        this.mCbrsDataSwitch = r0;
        r0.setVisibility(isCbrsSupported() ? 0 : 8);
        this.mDsdsSwitch = (Switch) findViewById(R.id.dsds_switch);
        if (isDsdsSupported() && !dsdsModeOnly()) {
            this.mDsdsSwitch.setVisibility(0);
            this.mDsdsSwitch.setOnClickListener(new View.OnClickListener() { // from class: com.android.phone.settings.-$$Lambda$RadioInfo$Ee2XEUVYb_ZlfdivOpQJiJgSAIs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCreate$0$RadioInfo(view);
                }
            });
            this.mDsdsSwitch.setChecked(isDsdsEnabled());
        } else {
            this.mDsdsSwitch.setVisibility(8);
        }
        this.mRadioPowerOnSwitch = (Switch) findViewById(R.id.radio_power);
        this.mDownlinkKbps = (TextView) findViewById(R.id.dl_kbps);
        this.mUplinkKbps = (TextView) findViewById(R.id.ul_kbps);
        updateBandwidths(0, 0);
        Button button = (Button) findViewById(R.id.ping_test);
        this.mPingTestButton = button;
        button.setOnClickListener(this.mPingButtonHandler);
        Button button2 = (Button) findViewById(R.id.update_smsc);
        this.mUpdateSmscButton = button2;
        button2.setOnClickListener(this.mUpdateSmscButtonHandler);
        Button button3 = (Button) findViewById(R.id.refresh_smsc);
        this.mRefreshSmscButton = button3;
        button3.setOnClickListener(this.mRefreshSmscButtonHandler);
        Button button4 = (Button) findViewById(R.id.dns_check_toggle);
        this.mDnsCheckToggleButton = button4;
        button4.setOnClickListener(this.mDnsCheckButtonHandler);
        Button button5 = (Button) findViewById(R.id.carrier_provisioning);
        this.mCarrierProvisioningButton = button5;
        button5.setOnClickListener(this.mCarrierProvisioningButtonHandler);
        Button button6 = (Button) findViewById(R.id.trigger_carrier_provisioning);
        this.mTriggerCarrierProvisioningButton = button6;
        button6.setOnClickListener(this.mTriggerCarrierProvisioningButtonHandler);
        Button button7 = (Button) findViewById(R.id.oem_info);
        this.mOemInfoButton = button7;
        button7.setOnClickListener(this.mOemInfoButtonHandler);
        if (getPackageManager().queryIntentActivities(new Intent("com.android.phone.settings.OEM_RADIO_INFO"), 0).size() == 0) {
            this.mOemInfoButton.setEnabled(false);
        }
        this.mCellInfoRefreshRateIndex = 0;
        this.mPreferredNetworkTypeResult = PREFERRED_NETWORK_LABELS.length - 1;
        this.mSelectedPhoneIndex = 0;
        this.mPhone.getPreferredNetworkType(this.mHandler.obtainMessage(1000));
        restoreFromBundle(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$0$RadioInfo(View view) {
        if (this.mTelephonyManager.doesSwitchMultiSimConfigTriggerReboot()) {
            this.mDsdsSwitch.toggle();
            showDsdsChangeDialog();
        } else {
            performDsdsSwitch();
        }
    }

    @Override // android.app.Activity
    public Intent getParentActivityIntent() {
        Intent parentActivityIntent = super.getParentActivityIntent();
        return parentActivityIntent == null ? new Intent().setClassName("com.android.settings", "com.android.settings.Settings$TestingSettingsActivity") : parentActivityIntent;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        log("Started onResume");
        updateAllFields();
    }

    private void updateAllFields() {
        updateMessageWaiting();
        updateCallRedirect();
        updateDataState();
        updateDataStats2();
        updateRadioPowerState();
        updateImsProvisionedState();
        updateProperties();
        updateDnsCheckState();
        updateNetworkType();
        updateNrStats(null);
        updateLocation(this.mCellLocationResult);
        updateCellInfo(this.mCellInfoResult);
        updateSubscriptionIds();
        this.mPingHostnameV4.setText(this.mPingHostnameResultV4);
        this.mPingHostnameV6.setText(this.mPingHostnameResultV6);
        this.mHttpClientTest.setText(this.mHttpClientTestResult);
        this.mCellInfoRefreshRateSpinner.setOnItemSelectedListener(this.mCellInfoRefreshRateHandler);
        this.mCellInfoRefreshRateSpinner.setSelection(this.mCellInfoRefreshRateIndex);
        this.mPreferredNetworkType.setSelection(this.mPreferredNetworkTypeResult, true);
        this.mPreferredNetworkType.setOnItemSelectedListener(this.mPreferredNetworkHandler);
        this.mSelectPhoneIndex.setSelection(this.mSelectedPhoneIndex, true);
        this.mSelectPhoneIndex.setOnItemSelectedListener(this.mSelectPhoneIndexHandler);
        this.mRadioPowerOnSwitch.setOnCheckedChangeListener(this.mRadioPowerOnChangeListener);
        this.mImsVolteProvisionedSwitch.setOnCheckedChangeListener(this.mImsVolteCheckedChangeListener);
        this.mImsVtProvisionedSwitch.setOnCheckedChangeListener(this.mImsVtCheckedChangeListener);
        this.mImsWfcProvisionedSwitch.setOnCheckedChangeListener(this.mImsWfcCheckedChangeListener);
        this.mEabProvisionedSwitch.setOnCheckedChangeListener(this.mEabCheckedChangeListener);
        if (isCbrsSupported()) {
            this.mCbrsDataSwitch.setChecked(getCbrsDataState());
            this.mCbrsDataSwitch.setOnCheckedChangeListener(this.mCbrsDataSwitchChangeListener);
        }
        unregisterPhoneStateListener();
        registerPhoneStateListener();
        this.mPhone.registerForPhysicalChannelConfig(this.mHandler, 1007, (Object) null);
        this.mConnectivityManager.registerNetworkCallback(this.mDefaultNetworkRequest, this.mNetworkCallback, this.mHandler);
        this.mSmsc.clearFocus();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        log("onPause: unregister phone & data intents");
        this.mTelephonyManager.listen(this.mPhoneStateListener, 0);
        this.mTelephonyManager.setCellInfoListRate(Integer.MAX_VALUE);
        this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
    }

    private void restoreFromBundle(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.mPingHostnameResultV4 = bundle.getString("mPingHostnameResultV4", "");
        this.mPingHostnameResultV6 = bundle.getString("mPingHostnameResultV6", "");
        this.mHttpClientTestResult = bundle.getString("mHttpClientTestResult", "");
        this.mPingHostnameV4.setText(this.mPingHostnameResultV4);
        this.mPingHostnameV6.setText(this.mPingHostnameResultV6);
        this.mHttpClientTest.setText(this.mHttpClientTestResult);
        this.mPreferredNetworkTypeResult = bundle.getInt("mPreferredNetworkTypeResult", PREFERRED_NETWORK_LABELS.length - 1);
        this.mSelectedPhoneIndex = bundle.getInt("mSelectedPhoneIndex", 0);
        this.mCellInfoRefreshRateIndex = bundle.getInt("mCellInfoRefreshRateIndex", 0);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putString("mPingHostnameResultV4", this.mPingHostnameResultV4);
        bundle.putString("mPingHostnameResultV6", this.mPingHostnameResultV6);
        bundle.putString("mHttpClientTestResult", this.mHttpClientTestResult);
        bundle.putInt("mPreferredNetworkTypeResult", this.mPreferredNetworkTypeResult);
        bundle.putInt("mSelectedPhoneIndex", this.mSelectedPhoneIndex);
        bundle.putInt("mCellInfoRefreshRateIndex", this.mCellInfoRefreshRateIndex);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, R.string.radio_info_band_mode_label).setOnMenuItemClickListener(this.mSelectBandCallback).setAlphabeticShortcut('b');
        menu.add(1, 1, 0, R.string.radioInfo_menu_viewADN).setOnMenuItemClickListener(this.mViewADNCallback);
        menu.add(1, 2, 0, R.string.radioInfo_menu_viewFDN).setOnMenuItemClickListener(this.mViewFDNCallback);
        menu.add(1, 3, 0, R.string.radioInfo_menu_viewSDN).setOnMenuItemClickListener(this.mViewSDNCallback);
        if (ImsManager.isImsSupportedOnDevice(this.mPhone.getContext())) {
            menu.add(1, 4, 0, R.string.radioInfo_menu_getIMS).setOnMenuItemClickListener(this.mGetImsStatus);
        }
        menu.add(1, 5, 0, R.string.radio_info_data_connection_disable).setOnMenuItemClickListener(this.mToggleData);
        return true;
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z;
        MenuItem menuItemFindItem = menu.findItem(5);
        int dataState = this.mTelephonyManager.getDataState();
        if (dataState == 0) {
            menuItemFindItem.setTitle(R.string.radio_info_data_connection_enable);
        } else if (dataState == 2 || dataState == 3) {
            menuItemFindItem.setTitle(R.string.radio_info_data_connection_disable);
        } else {
            z = false;
            menuItemFindItem.setVisible(z);
            return true;
        }
        z = true;
        menuItemFindItem.setVisible(z);
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mQueuedWork.shutdown();
    }

    private static String[] getPhoneIndexLabels(TelephonyManager telephonyManager) {
        int phoneCount = telephonyManager.getPhoneCount();
        String[] strArr = new String[phoneCount];
        for (int i = 0; i < phoneCount; i++) {
            strArr[i] = "Phone " + i;
        }
        return strArr;
    }

    private void unregisterPhoneStateListener() {
        this.mTelephonyManager.listen(this.mPhoneStateListener, 0);
        this.mPhone.unregisterForPhysicalChannelConfig(this.mHandler);
        this.mOperatorName.setText("");
        this.mGprsState.setText("");
        this.mDataNetwork.setText("");
        this.mVoiceNetwork.setText("");
        this.mSent.setText("");
        this.mReceived.setText("");
        this.mCallState.setText("");
        this.mLocation.setText("");
        this.mMwiValue = false;
        this.mMwi.setText("");
        this.mCfiValue = false;
        this.mCfi.setText("");
        this.mCellInfo.setText("");
        this.mDBm.setText("");
        this.mGsmState.setText("");
        this.mRoamingState.setText("");
        this.mPhyChanConfig.setText("");
    }

    private void registerPhoneStateListener() {
        RadioInfoPhoneStateListener radioInfoPhoneStateListener = new RadioInfoPhoneStateListener();
        this.mPhoneStateListener = radioInfoPhoneStateListener;
        this.mTelephonyManager.listen(radioInfoPhoneStateListener, 1533);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDnsCheckState() {
        this.mDnsCheckState.setText(this.mPhone.isDnsCheckDisabled() ? "0.0.0.0 allowed" : "0.0.0.0 not allowed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBandwidths(int i, int i2) {
        if (i < 0 || i == Integer.MAX_VALUE) {
            i = -1;
        }
        if (i2 < 0 || i2 == Integer.MAX_VALUE) {
            i2 = -1;
        }
        this.mDownlinkKbps.setText(String.format("%-5d", Integer.valueOf(i)));
        this.mUplinkKbps.setText(String.format("%-5d", Integer.valueOf(i2)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSignalStrength(SignalStrength signalStrength) {
        Resources resources = getResources();
        int dbm = signalStrength.getDbm();
        int asuLevel = signalStrength.getAsuLevel();
        if (-1 == asuLevel) {
            asuLevel = 0;
        }
        this.mDBm.setText(String.valueOf(dbm) + " " + resources.getString(R.string.radioInfo_display_dbm) + "   " + String.valueOf(asuLevel) + " " + resources.getString(R.string.radioInfo_display_asu));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLocation(CellLocation cellLocation) {
        Resources resources = getResources();
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            int lac = gsmCellLocation.getLac();
            int cid = gsmCellLocation.getCid();
            TextView textView = this.mLocation;
            StringBuilder sb = new StringBuilder();
            sb.append(resources.getString(R.string.radioInfo_lac));
            sb.append(" = ");
            sb.append(lac == -1 ? "unknown" : Integer.toHexString(lac));
            sb.append("   ");
            sb.append(resources.getString(R.string.radioInfo_cid));
            sb.append(" = ");
            sb.append(cid != -1 ? Integer.toHexString(cid) : "unknown");
            textView.setText(sb.toString());
            return;
        }
        if (cellLocation instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            int baseStationId = cdmaCellLocation.getBaseStationId();
            int systemId = cdmaCellLocation.getSystemId();
            int networkId = cdmaCellLocation.getNetworkId();
            int baseStationLatitude = cdmaCellLocation.getBaseStationLatitude();
            int baseStationLongitude = cdmaCellLocation.getBaseStationLongitude();
            TextView textView2 = this.mLocation;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("BID = ");
            sb2.append(baseStationId == -1 ? "unknown" : Integer.toHexString(baseStationId));
            sb2.append("   SID = ");
            sb2.append(systemId == -1 ? "unknown" : Integer.toHexString(systemId));
            sb2.append("   NID = ");
            sb2.append(networkId == -1 ? "unknown" : Integer.toHexString(networkId));
            sb2.append("\nLAT = ");
            sb2.append(baseStationLatitude == -1 ? "unknown" : Integer.toHexString(baseStationLatitude));
            sb2.append("   LONG = ");
            sb2.append(baseStationLongitude != -1 ? Integer.toHexString(baseStationLongitude) : "unknown");
            textView2.setText(sb2.toString());
            return;
        }
        this.mLocation.setText("unknown");
    }

    private String getCellInfoDisplayString(int i) {
        return i != Integer.MAX_VALUE ? Integer.toString(i) : "";
    }

    private String getConnectionStatusString(CellInfo cellInfo) {
        String str = "";
        String str2 = cellInfo.isRegistered() ? "R" : "";
        int cellConnectionStatus = cellInfo.getCellConnectionStatus();
        String str3 = cellConnectionStatus != 0 ? cellConnectionStatus != 1 ? cellConnectionStatus != 2 ? "" : "S" : "P" : "N";
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            str = "+";
        }
        return str2 + str + str3;
    }

    private String buildCdmaInfoString(CellInfoCdma cellInfoCdma) {
        CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
        CellSignalStrengthCdma cellSignalStrength = cellInfoCdma.getCellSignalStrength();
        return String.format("%-3.3s %-5.5s %-5.5s %-5.5s %-6.6s %-6.6s %-6.6s %-6.6s %-5.5s", getConnectionStatusString(cellInfoCdma), getCellInfoDisplayString(cellIdentity.getSystemId()), getCellInfoDisplayString(cellIdentity.getNetworkId()), getCellInfoDisplayString(cellIdentity.getBasestationId()), getCellInfoDisplayString(cellSignalStrength.getCdmaDbm()), getCellInfoDisplayString(cellSignalStrength.getCdmaEcio()), getCellInfoDisplayString(cellSignalStrength.getEvdoDbm()), getCellInfoDisplayString(cellSignalStrength.getEvdoEcio()), getCellInfoDisplayString(cellSignalStrength.getEvdoSnr()));
    }

    private String buildGsmInfoString(CellInfoGsm cellInfoGsm) {
        CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
        return String.format("%-3.3s %-3.3s %-3.3s %-5.5s %-5.5s %-6.6s %-4.4s %-4.4s\n", getConnectionStatusString(cellInfoGsm), getCellInfoDisplayString(cellIdentity.getMcc()), getCellInfoDisplayString(cellIdentity.getMnc()), getCellInfoDisplayString(cellIdentity.getLac()), getCellInfoDisplayString(cellIdentity.getCid()), getCellInfoDisplayString(cellIdentity.getArfcn()), getCellInfoDisplayString(cellIdentity.getBsic()), getCellInfoDisplayString(cellInfoGsm.getCellSignalStrength().getDbm()));
    }

    private String buildLteInfoString(CellInfoLte cellInfoLte) {
        CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
        CellSignalStrengthLte cellSignalStrength = cellInfoLte.getCellSignalStrength();
        return String.format("%-3.3s %-3.3s %-3.3s %-5.5s %-5.5s %-3.3s %-6.6s %-2.2s %-4.4s %-4.4s %-2.2s\n", getConnectionStatusString(cellInfoLte), getCellInfoDisplayString(cellIdentity.getMcc()), getCellInfoDisplayString(cellIdentity.getMnc()), getCellInfoDisplayString(cellIdentity.getTac()), getCellInfoDisplayString(cellIdentity.getCi()), getCellInfoDisplayString(cellIdentity.getPci()), getCellInfoDisplayString(cellIdentity.getEarfcn()), getCellInfoDisplayString(cellIdentity.getBandwidth()), getCellInfoDisplayString(cellSignalStrength.getDbm()), getCellInfoDisplayString(cellSignalStrength.getRsrq()), getCellInfoDisplayString(cellSignalStrength.getTimingAdvance()));
    }

    private String buildWcdmaInfoString(CellInfoWcdma cellInfoWcdma) {
        CellIdentityWcdma cellIdentity = cellInfoWcdma.getCellIdentity();
        return String.format("%-3.3s %-3.3s %-3.3s %-5.5s %-5.5s %-6.6s %-3.3s %-4.4s\n", getConnectionStatusString(cellInfoWcdma), getCellInfoDisplayString(cellIdentity.getMcc()), getCellInfoDisplayString(cellIdentity.getMnc()), getCellInfoDisplayString(cellIdentity.getLac()), getCellInfoDisplayString(cellIdentity.getCid()), getCellInfoDisplayString(cellIdentity.getUarfcn()), getCellInfoDisplayString(cellIdentity.getPsc()), getCellInfoDisplayString(cellInfoWcdma.getCellSignalStrength().getDbm()));
    }

    private String buildCellInfoString(List<CellInfo> list) {
        String str = new String();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        if (list != null) {
            for (CellInfo cellInfo : list) {
                if (cellInfo instanceof CellInfoLte) {
                    sb3.append(buildLteInfoString((CellInfoLte) cellInfo));
                } else if (cellInfo instanceof CellInfoWcdma) {
                    sb4.append(buildWcdmaInfoString((CellInfoWcdma) cellInfo));
                } else if (cellInfo instanceof CellInfoGsm) {
                    sb2.append(buildGsmInfoString((CellInfoGsm) cellInfo));
                } else if (cellInfo instanceof CellInfoCdma) {
                    sb.append(buildCdmaInfoString((CellInfoCdma) cellInfo));
                }
            }
            if (sb3.length() != 0) {
                str = (str + String.format("LTE\n%-3.3s %-3.3s %-3.3s %-5.5s %-5.5s %-3.3s %-6.6s %-2.2s %-4.4s %-4.4s %-2.2s\n", "SRV", "MCC", "MNC", "TAC", "CID", "PCI", "EARFCN", "BW", "RSRP", "RSRQ", "TA")) + sb3.toString();
            }
            if (sb4.length() != 0) {
                str = (str + String.format("WCDMA\n%-3.3s %-3.3s %-3.3s %-5.5s %-5.5s %-6.6s %-3.3s %-4.4s\n", "SRV", "MCC", "MNC", "LAC", "CID", "UARFCN", "PSC", "RSCP")) + sb4.toString();
            }
            if (sb2.length() != 0) {
                str = (str + String.format("GSM\n%-3.3s %-3.3s %-3.3s %-5.5s %-5.5s %-6.6s %-4.4s %-4.4s\n", "SRV", "MCC", "MNC", "LAC", "CID", "ARFCN", "BSIC", "RSSI")) + sb2.toString();
            }
            if (sb.length() != 0) {
                str = (str + String.format("CDMA/EVDO\n%-3.3s %-5.5s %-5.5s %-5.5s %-6.6s %-6.6s %-6.6s %-6.6s %-5.5s\n", "SRV", "SID", "NID", "BSID", "C-RSSI", "C-ECIO", "E-RSSI", "E-ECIO", "E-SNR")) + sb.toString();
            }
        } else {
            str = "unknown";
        }
        return str.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCellInfo(List<CellInfo> list) {
        this.mCellInfo.setText(buildCellInfoString(list));
    }

    private void updateSubscriptionIds() {
        this.mSubscriptionId.setText(Integer.toString(this.mPhone.getSubId()));
        this.mDds.setText(Integer.toString(SubscriptionManager.getDefaultDataSubscriptionId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMessageWaiting() {
        this.mMwi.setText(String.valueOf(this.mMwiValue));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCallRedirect() {
        this.mCfi.setText(String.valueOf(this.mCfiValue));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateServiceState(ServiceState serviceState) {
        int state = serviceState.getState();
        Resources resources = getResources();
        String string = resources.getString(R.string.radioInfo_unknown);
        if (state == 0) {
            string = resources.getString(R.string.radioInfo_service_in);
        } else if (state == 1 || state == 2) {
            string = resources.getString(R.string.radioInfo_service_emergency);
        } else if (state == 3) {
            string = resources.getString(R.string.radioInfo_service_off);
        }
        this.mGsmState.setText(string);
        if (serviceState.getRoaming()) {
            this.mRoamingState.setText(R.string.radioInfo_roaming_in);
        } else {
            this.mRoamingState.setText(R.string.radioInfo_roaming_not);
        }
        this.mOperatorName.setText(serviceState.getOperatorAlphaLong());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePhoneState(int i) {
        Resources resources = getResources();
        String string = resources.getString(R.string.radioInfo_unknown);
        if (i == 0) {
            string = resources.getString(R.string.radioInfo_phone_idle);
        } else if (i == 1) {
            string = resources.getString(R.string.radioInfo_phone_ringing);
        } else if (i == 2) {
            string = resources.getString(R.string.radioInfo_phone_offhook);
        }
        this.mCallState.setText(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDataState() {
        int dataState = this.mTelephonyManager.getDataState();
        Resources resources = getResources();
        String string = resources.getString(R.string.radioInfo_unknown);
        if (dataState == 0) {
            string = resources.getString(R.string.radioInfo_data_disconnected);
        } else if (dataState == 1) {
            string = resources.getString(R.string.radioInfo_data_connecting);
        } else if (dataState == 2) {
            string = resources.getString(R.string.radioInfo_data_connected);
        } else if (dataState == 3) {
            string = resources.getString(R.string.radioInfo_data_suspended);
        }
        this.mGprsState.setText(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNetworkType() {
        Phone phone = this.mPhone;
        if (phone != null) {
            phone.getServiceState();
            this.mDataNetwork.setText(ServiceState.rilRadioTechnologyToString(this.mPhone.getServiceState().getRilDataRadioTechnology()));
            this.mVoiceNetwork.setText(ServiceState.rilRadioTechnologyToString(this.mPhone.getServiceState().getRilVoiceRadioTechnology()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNrStats(ServiceState serviceState) {
        DataSpecificRegistrationInfo dataSpecificInfo;
        Phone phone;
        if ((this.mTelephonyManager.getSupportedRadioAccessFamily() & 524288) == 0) {
            return;
        }
        if (serviceState == null && (phone = this.mPhone) != null) {
            serviceState = phone.getServiceState();
        }
        if (serviceState != null) {
            NetworkRegistrationInfo networkRegistrationInfo = serviceState.getNetworkRegistrationInfo(2, 1);
            if (networkRegistrationInfo != null && (dataSpecificInfo = networkRegistrationInfo.getDataSpecificInfo()) != null) {
                this.mEndcAvailable.setText(dataSpecificInfo.isEnDcAvailable ? "True" : "False");
                this.mDcnrRestricted.setText(dataSpecificInfo.isDcNrRestricted ? "True" : "False");
                this.mNrAvailable.setText(dataSpecificInfo.isNrAvailable ? "True" : "False");
            }
            this.mNrState.setText(NetworkRegistrationInfo.nrStateToString(serviceState.getNrState()));
            this.mNrFrequency.setText(ServiceState.frequencyRangeToString(serviceState.getNrFrequencyRange()));
        }
    }

    private void updateProperties() {
        Resources resources = getResources();
        String deviceId = this.mPhone.getDeviceId();
        if (deviceId == null) {
            deviceId = resources.getString(R.string.radioInfo_unknown);
        }
        this.mDeviceId.setText(deviceId);
        String subscriberId = this.mPhone.getSubscriberId();
        if (subscriberId == null) {
            subscriberId = resources.getString(R.string.radioInfo_unknown);
        }
        this.mSubscriberId.setText(subscriberId);
        String line1Number = this.mPhone.getLine1Number();
        if (line1Number == null) {
            line1Number = resources.getString(R.string.radioInfo_unknown);
        }
        this.mLine1Number.setText(line1Number);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDataStats2() {
        Resources resources = getResources();
        long mobileTxPackets = TrafficStats.getMobileTxPackets();
        long mobileRxPackets = TrafficStats.getMobileRxPackets();
        long mobileTxBytes = TrafficStats.getMobileTxBytes();
        long mobileRxBytes = TrafficStats.getMobileRxBytes();
        String string = resources.getString(R.string.radioInfo_display_packets);
        String string2 = resources.getString(R.string.radioInfo_display_bytes);
        this.mSent.setText(mobileTxPackets + " " + string + ", " + mobileTxBytes + " " + string2);
        this.mReceived.setText(mobileRxPackets + " " + string + ", " + mobileRxBytes + " " + string2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:14:0x004e
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public void pingHostname() {
        /*
            r7 = this;
            java.lang.String r0 = "Pass"
            java.lang.String r1 = "Fail(%d)"
            java.lang.String r2 = "Fail: IOException"
            r3 = 0
            r4 = 1
            java.lang.Runtime r5 = java.lang.Runtime.getRuntime()     // Catch: java.io.IOException -> L2a java.lang.InterruptedException -> L51
            java.lang.String r6 = "ping -c 1 www.google.com"
            java.lang.Process r5 = r5.exec(r6)     // Catch: java.io.IOException -> L2a java.lang.InterruptedException -> L51
            int r5 = r5.waitFor()     // Catch: java.io.IOException -> L2a java.lang.InterruptedException -> L51
            if (r5 != 0) goto L1b
            r7.mPingHostnameResultV4 = r0     // Catch: java.io.IOException -> L2a java.lang.InterruptedException -> L51
            goto L2c
        L1b:
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: java.io.IOException -> L2a java.lang.InterruptedException -> L51
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.io.IOException -> L2a java.lang.InterruptedException -> L51
            r6[r3] = r5     // Catch: java.io.IOException -> L2a java.lang.InterruptedException -> L51
            java.lang.String r5 = java.lang.String.format(r1, r6)     // Catch: java.io.IOException -> L2a java.lang.InterruptedException -> L51
            r7.mPingHostnameResultV4 = r5     // Catch: java.io.IOException -> L2a java.lang.InterruptedException -> L51
            goto L2c
        L2a:
            r7.mPingHostnameResultV4 = r2     // Catch: java.lang.InterruptedException -> L51
        L2c:
            java.lang.Runtime r5 = java.lang.Runtime.getRuntime()     // Catch: java.io.IOException -> L4e java.lang.InterruptedException -> L51
            java.lang.String r6 = "ping6 -c 1 www.google.com"
            java.lang.Process r5 = r5.exec(r6)     // Catch: java.io.IOException -> L4e java.lang.InterruptedException -> L51
            int r5 = r5.waitFor()     // Catch: java.io.IOException -> L4e java.lang.InterruptedException -> L51
            if (r5 != 0) goto L3f
            r7.mPingHostnameResultV6 = r0     // Catch: java.io.IOException -> L4e java.lang.InterruptedException -> L51
            goto L57
        L3f:
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch: java.io.IOException -> L4e java.lang.InterruptedException -> L51
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)     // Catch: java.io.IOException -> L4e java.lang.InterruptedException -> L51
            r0[r3] = r4     // Catch: java.io.IOException -> L4e java.lang.InterruptedException -> L51
            java.lang.String r0 = java.lang.String.format(r1, r0)     // Catch: java.io.IOException -> L4e java.lang.InterruptedException -> L51
            r7.mPingHostnameResultV6 = r0     // Catch: java.io.IOException -> L4e java.lang.InterruptedException -> L51
            goto L57
        L4e:
            r7.mPingHostnameResultV6 = r2     // Catch: java.lang.InterruptedException -> L51
            goto L57
        L51:
            java.lang.String r0 = "Fail: InterruptedException"
            r7.mPingHostnameResultV6 = r0
            r7.mPingHostnameResultV4 = r0
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.settings.RadioInfo.pingHostname():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void httpClientTest() throws Throwable {
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL("https://www.google.com").openConnection();
                try {
                    if (httpURLConnection2.getResponseCode() == 200) {
                        this.mHttpClientTestResult = "Pass";
                    } else {
                        this.mHttpClientTestResult = "Fail: Code: " + httpURLConnection2.getResponseMessage();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                } catch (IOException unused) {
                    httpURLConnection = httpURLConnection2;
                    this.mHttpClientTestResult = "Fail: IOException";
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Throwable th) {
                    th = th;
                    httpURLConnection = httpURLConnection2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshSmsc() {
        this.mQueuedWork.execute(new Runnable() { // from class: com.android.phone.settings.RadioInfo.3
            @Override // java.lang.Runnable
            public void run() {
                RadioInfo.this.mPhone.getSmscAddress(RadioInfo.this.mHandler.obtainMessage(1005));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAllCellInfo() {
        this.mCellInfo.setText("");
        this.mLocation.setText("");
        final Runnable runnable = new Runnable() { // from class: com.android.phone.settings.RadioInfo.4
            @Override // java.lang.Runnable
            public void run() {
                RadioInfo radioInfo = RadioInfo.this;
                radioInfo.updateLocation(radioInfo.mCellLocationResult);
                RadioInfo radioInfo2 = RadioInfo.this;
                radioInfo2.updateCellInfo(radioInfo2.mCellInfoResult);
            }
        };
        this.mQueuedWork.execute(new Runnable() { // from class: com.android.phone.settings.RadioInfo.5
            @Override // java.lang.Runnable
            public void run() {
                RadioInfo radioInfo = RadioInfo.this;
                radioInfo.mCellInfoResult = radioInfo.mTelephonyManager.getAllCellInfo();
                RadioInfo radioInfo2 = RadioInfo.this;
                radioInfo2.mCellLocationResult = radioInfo2.mTelephonyManager.getCellLocation();
                RadioInfo.this.mHandler.post(runnable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePingState() {
        this.mPingHostnameResultV4 = getResources().getString(R.string.radioInfo_unknown);
        this.mPingHostnameResultV6 = getResources().getString(R.string.radioInfo_unknown);
        this.mHttpClientTestResult = getResources().getString(R.string.radioInfo_unknown);
        this.mPingHostnameV4.setText(this.mPingHostnameResultV4);
        this.mPingHostnameV6.setText(this.mPingHostnameResultV6);
        this.mHttpClientTest.setText(this.mHttpClientTestResult);
        final Runnable runnable = new Runnable() { // from class: com.android.phone.settings.RadioInfo.6
            @Override // java.lang.Runnable
            public void run() {
                RadioInfo.this.mPingHostnameV4.setText(RadioInfo.this.mPingHostnameResultV4);
                RadioInfo.this.mPingHostnameV6.setText(RadioInfo.this.mPingHostnameResultV6);
                RadioInfo.this.mHttpClientTest.setText(RadioInfo.this.mHttpClientTestResult);
            }
        };
        new Thread() { // from class: com.android.phone.settings.RadioInfo.7
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                RadioInfo.this.pingHostname();
                RadioInfo.this.mHandler.post(runnable);
            }
        }.start();
        new Thread() { // from class: com.android.phone.settings.RadioInfo.8
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws Throwable {
                RadioInfo.this.httpClientTest();
                RadioInfo.this.mHandler.post(runnable);
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isRadioOn() {
        return this.mPhone.getServiceState().getState() != 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRadioPowerState() {
        this.mRadioPowerOnSwitch.setOnCheckedChangeListener(null);
        this.mRadioPowerOnSwitch.setChecked(isRadioOn());
        this.mRadioPowerOnSwitch.setOnCheckedChangeListener(this.mRadioPowerOnChangeListener);
    }

    void setImsVolteProvisionedState(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("setImsVolteProvisioned state: ");
        sb.append(z ? "on" : "off");
        Log.d("RadioInfo", sb.toString());
        setImsConfigProvisionedState(10, z);
    }

    void setImsVtProvisionedState(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("setImsVtProvisioned() state: ");
        sb.append(z ? "on" : "off");
        Log.d("RadioInfo", sb.toString());
        setImsConfigProvisionedState(11, z);
    }

    void setImsWfcProvisionedState(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("setImsWfcProvisioned() state: ");
        sb.append(z ? "on" : "off");
        Log.d("RadioInfo", sb.toString());
        setImsConfigProvisionedState(28, z);
    }

    void setEabProvisionedState(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("setEabProvisioned() state: ");
        sb.append(z ? "on" : "off");
        Log.d("RadioInfo", sb.toString());
        setImsConfigProvisionedState(25, z);
    }

    void setImsConfigProvisionedState(final int i, final boolean z) {
        if (this.mPhone == null || this.mImsManager == null) {
            return;
        }
        this.mQueuedWork.execute(new Runnable() { // from class: com.android.phone.settings.RadioInfo.15
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RadioInfo.this.mImsManager.getConfigInterface().setProvisionedValue(i, z ? 1 : 0);
                } catch (ImsException e) {
                    Log.e("RadioInfo", "setImsConfigProvisioned() exception:", e);
                }
            }
        });
    }

    private boolean isImsVolteProvisioned() {
        Phone phone = this.mPhone;
        return phone != null && this.mImsManager != null && ImsManager.isVolteEnabledByPlatform(phone.getContext()) && ImsManager.isVolteProvisionedOnDevice(this.mPhone.getContext());
    }

    private boolean isImsVtProvisioned() {
        Phone phone = this.mPhone;
        return phone != null && this.mImsManager != null && ImsManager.isVtEnabledByPlatform(phone.getContext()) && ImsManager.isVtProvisionedOnDevice(this.mPhone.getContext());
    }

    private boolean isImsWfcProvisioned() {
        Phone phone = this.mPhone;
        return phone != null && this.mImsManager != null && ImsManager.isWfcEnabledByPlatform(phone.getContext()) && ImsManager.isWfcProvisionedOnDevice(this.mPhone.getContext());
    }

    private boolean isEabProvisioned() {
        return isFeatureProvisioned(25, false);
    }

    private boolean isFeatureProvisioned(int i, boolean z) {
        ImsManager imsManager = this.mImsManager;
        if (imsManager != null) {
            try {
                ImsConfig configInterface = imsManager.getConfigInterface();
                if (configInterface != null) {
                    z = true;
                    if (configInterface.getProvisionedValue(i) != 1) {
                        z = false;
                    }
                }
            } catch (ImsException e) {
                Log.e("RadioInfo", "isFeatureProvisioned() exception:", e);
            }
        }
        log("isFeatureProvisioned() featureId=" + i + " provisioned=" + z);
        return z;
    }

    private static boolean isEabEnabledByPlatform(Context context) {
        CarrierConfigManager carrierConfigManager;
        return (context == null || (carrierConfigManager = (CarrierConfigManager) context.getSystemService("carrier_config")) == null || !carrierConfigManager.getConfig().getBoolean("use_rcs_presence_bool")) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateImsProvisionedState() {
        if (ImsManager.isImsSupportedOnDevice(this.mPhone.getContext())) {
            log("updateImsProvisionedState isImsVolteProvisioned()=" + isImsVolteProvisioned());
            this.mImsVolteProvisionedSwitch.setOnCheckedChangeListener(null);
            this.mImsVolteProvisionedSwitch.setChecked(isImsVolteProvisioned());
            this.mImsVolteProvisionedSwitch.setOnCheckedChangeListener(this.mImsVolteCheckedChangeListener);
            this.mImsVolteProvisionedSwitch.setEnabled(!IS_USER_BUILD && ImsManager.isVolteEnabledByPlatform(this.mPhone.getContext()));
            this.mImsVtProvisionedSwitch.setOnCheckedChangeListener(null);
            this.mImsVtProvisionedSwitch.setChecked(isImsVtProvisioned());
            this.mImsVtProvisionedSwitch.setOnCheckedChangeListener(this.mImsVtCheckedChangeListener);
            this.mImsVtProvisionedSwitch.setEnabled(!IS_USER_BUILD && ImsManager.isVtEnabledByPlatform(this.mPhone.getContext()));
            this.mImsWfcProvisionedSwitch.setOnCheckedChangeListener(null);
            this.mImsWfcProvisionedSwitch.setChecked(isImsWfcProvisioned());
            this.mImsWfcProvisionedSwitch.setOnCheckedChangeListener(this.mImsWfcCheckedChangeListener);
            this.mImsWfcProvisionedSwitch.setEnabled(!IS_USER_BUILD && ImsManager.isWfcEnabledByPlatform(this.mPhone.getContext()));
            this.mEabProvisionedSwitch.setOnCheckedChangeListener(null);
            this.mEabProvisionedSwitch.setChecked(isEabProvisioned());
            this.mEabProvisionedSwitch.setOnCheckedChangeListener(this.mEabCheckedChangeListener);
            this.mEabProvisionedSwitch.setEnabled(!IS_USER_BUILD && isEabEnabledByPlatform(this.mPhone.getContext()));
        }
    }

    boolean isCbrsSupported() {
        return getResources().getBoolean(android.R.bool.config_awareSettingAvailable);
    }

    void updateCbrsDataState(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("setCbrsDataSwitchState() state:");
        sb.append(z ? "on" : "off");
        Log.d("RadioInfo", sb.toString());
        if (this.mTelephonyManager != null) {
            this.mQueuedWork.execute(new AnonymousClass31(z));
        }
    }

    /* JADX INFO: renamed from: com.android.phone.settings.RadioInfo$31, reason: invalid class name */
    class AnonymousClass31 implements Runnable {
        final /* synthetic */ boolean val$state;

        AnonymousClass31(boolean z) {
            this.val$state = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            RadioInfo.this.mTelephonyManager.setOpportunisticNetworkState(this.val$state);
            RadioInfo.this.mHandler.post(new Runnable() { // from class: com.android.phone.settings.-$$Lambda$RadioInfo$31$4PqE1dTfUTmGl-uOnhiu2afeRZs
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$run$0$RadioInfo$31();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$run$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$run$0$RadioInfo$31() {
            RadioInfo.this.mCbrsDataSwitch.setChecked(RadioInfo.this.getCbrsDataState());
        }
    }

    boolean getCbrsDataState() {
        TelephonyManager telephonyManager = this.mTelephonyManager;
        boolean zIsOpportunisticNetworkEnabled = telephonyManager != null ? telephonyManager.isOpportunisticNetworkEnabled() : false;
        StringBuilder sb = new StringBuilder();
        sb.append("getCbrsDataState() state:");
        sb.append(zIsOpportunisticNetworkEnabled ? "on" : "off");
        Log.d("RadioInfo", sb.toString());
        return zIsOpportunisticNetworkEnabled;
    }

    private void showDsdsChangeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dsds_dialog_title);
        builder.setMessage(R.string.dsds_dialog_message);
        builder.setPositiveButton(R.string.dsds_dialog_confirm, this.mOnDsdsDialogConfirmedListener);
        builder.setNegativeButton(R.string.dsds_dialog_cancel, this.mOnDsdsDialogConfirmedListener);
        builder.create().show();
    }

    private static boolean isDsdsSupported() {
        return TelephonyManager.getDefault().isMultiSimSupported() == 0;
    }

    private static boolean isDsdsEnabled() {
        return TelephonyManager.getDefault().getPhoneCount() > 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performDsdsSwitch() {
        this.mTelephonyManager.switchMultiSimConfig(this.mDsdsSwitch.isChecked() ? 2 : 1);
    }

    private boolean dsdsModeOnly() {
        String str = SystemProperties.get("ro.boot.hardware.dsds");
        return !TextUtils.isEmpty(str) && Integer.parseInt(str) == 1;
    }
}
