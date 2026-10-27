package com.android.car.developeroptions.wifi;

import android.content.Context;
import android.content.res.Resources;
import android.net.IpConfiguration;
import android.net.LinkAddress;
import android.net.NetworkInfo;
import android.net.NetworkUtils;
import android.net.ProxyInfo;
import android.net.StaticIpConfiguration;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.WifiManager;
import android.os.UserManager;
import android.security.KeyStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.android.car.developeroptions.ProxySelector;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.car.developeroptions.wifi.details.WifiPrivacyPreferenceController;
import com.android.car.developeroptions.wifi.dpp.WifiDppUtils;
import com.android.settingslib.Utils;
import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.wifi.AccessPoint;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class WifiConfigController implements TextWatcher, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener, TextView.OnEditorActionListener, View.OnKeyListener {
    private final AccessPoint mAccessPoint;
    int mAccessPointSecurity;
    private final WifiConfigUiBase mConfigUi;
    private Context mContext;
    private ScrollView mDialogContainer;
    private TextView mDns1View;
    private TextView mDns2View;
    private String mDoNotProvideEapUserCertString;
    private String mDoNotValidateEapServerString;
    private TextView mEapAnonymousView;
    private Spinner mEapCaCertSpinner;
    private TextView mEapDomainView;
    private TextView mEapIdentityView;
    private Spinner mEapMethodSpinner;
    private Spinner mEapUserCertSpinner;
    private TextView mGatewayView;
    private Spinner mHiddenSettingsSpinner;
    private TextView mHiddenWarningView;
    private TextView mIpAddressView;
    private Spinner mIpSettingsSpinner;
    private String[] mLevels;
    private Spinner mMeteredSettingsSpinner;
    private int mMode;
    private String mMultipleCertSetString;
    private TextView mNetworkPrefixLengthView;
    private ImageButton mPasswordScanButton;
    private TextView mPasswordView;
    private ArrayAdapter<String> mPhase2Adapter;
    private ArrayAdapter<String> mPhase2FullAdapter;
    private ArrayAdapter<String> mPhase2PeapAdapter;
    private Spinner mPhase2Spinner;
    private Spinner mPrivacySettingsSpinner;
    private TextView mProxyExclusionListView;
    private TextView mProxyHostView;
    private TextView mProxyPacView;
    private TextView mProxyPortView;
    private Spinner mProxySettingsSpinner;
    private Integer[] mSecurityInPosition;
    private Spinner mSecuritySpinner;
    private CheckBox mSharedCheckBox;
    private ImageButton mSsidScanButton;
    private TextView mSsidView;
    private String mUnspecifiedCertString;
    private String mUseSystemCertsString;
    private final View mView;
    private final WifiManager mWifiManager;
    private IpConfiguration.IpAssignment mIpAssignment = IpConfiguration.IpAssignment.UNASSIGNED;
    private IpConfiguration.ProxySettings mProxySettings = IpConfiguration.ProxySettings.UNASSIGNED;
    private ProxyInfo mHttpProxy = null;
    private StaticIpConfiguration mStaticIpConfiguration = null;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public WifiConfigController(WifiConfigUiBase wifiConfigUiBase, View view, AccessPoint accessPoint, int i) {
        this.mConfigUi = wifiConfigUiBase;
        this.mView = view;
        this.mAccessPoint = accessPoint;
        Context context = wifiConfigUiBase.getContext();
        this.mContext = context;
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        initWifiConfigController(accessPoint, i);
    }

    public WifiConfigController(WifiConfigUiBase wifiConfigUiBase, View view, AccessPoint accessPoint, int i, WifiManager wifiManager) {
        this.mConfigUi = wifiConfigUiBase;
        this.mView = view;
        this.mAccessPoint = accessPoint;
        this.mContext = wifiConfigUiBase.getContext();
        this.mWifiManager = wifiManager;
        initWifiConfigController(accessPoint, i);
    }

    private void initWifiConfigController(AccessPoint accessPoint, int i) {
        boolean z;
        LinkAddress linkAddress;
        this.mAccessPointSecurity = accessPoint == null ? 0 : accessPoint.getSecurity();
        this.mMode = i;
        Resources resources = this.mContext.getResources();
        this.mLevels = resources.getStringArray(R.array.wifi_signal);
        if (Utils.isWifiOnly(this.mContext) || !this.mContext.getResources().getBoolean(android.R.bool.config_canRemoveFirstAccount)) {
            this.mPhase2PeapAdapter = new ArrayAdapter<>(this.mContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.wifi_peap_phase2_entries));
        } else {
            this.mPhase2PeapAdapter = new ArrayAdapter<>(this.mContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.wifi_peap_phase2_entries_with_sim_auth));
        }
        this.mPhase2PeapAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this.mContext, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.wifi_phase2_entries));
        this.mPhase2FullAdapter = arrayAdapter;
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.mUnspecifiedCertString = this.mContext.getString(R.string.wifi_unspecified);
        this.mMultipleCertSetString = this.mContext.getString(R.string.wifi_multiple_cert_added);
        this.mUseSystemCertsString = this.mContext.getString(R.string.wifi_use_system_certs);
        this.mDoNotProvideEapUserCertString = this.mContext.getString(R.string.wifi_do_not_provide_eap_user_cert);
        this.mDoNotValidateEapServerString = this.mContext.getString(R.string.wifi_do_not_validate_eap_server);
        this.mSsidScanButton = (ImageButton) this.mView.findViewById(R.id.ssid_scanner_button);
        this.mPasswordScanButton = (ImageButton) this.mView.findViewById(R.id.password_scanner_button);
        this.mDialogContainer = (ScrollView) this.mView.findViewById(R.id.dialog_scrollview);
        Spinner spinner = (Spinner) this.mView.findViewById(R.id.ip_settings);
        this.mIpSettingsSpinner = spinner;
        spinner.setOnItemSelectedListener(this);
        Spinner spinner2 = (Spinner) this.mView.findViewById(R.id.proxy_settings);
        this.mProxySettingsSpinner = spinner2;
        spinner2.setOnItemSelectedListener(this);
        this.mSharedCheckBox = (CheckBox) this.mView.findViewById(R.id.shared);
        this.mMeteredSettingsSpinner = (Spinner) this.mView.findViewById(R.id.metered_settings);
        this.mHiddenSettingsSpinner = (Spinner) this.mView.findViewById(R.id.hidden_settings);
        this.mPrivacySettingsSpinner = (Spinner) this.mView.findViewById(R.id.privacy_settings);
        if (this.mWifiManager.isConnectedMacRandomizationSupported()) {
            this.mView.findViewById(R.id.privacy_settings_fields).setVisibility(0);
        }
        this.mHiddenSettingsSpinner.setOnItemSelectedListener(this);
        TextView textView = (TextView) this.mView.findViewById(R.id.hidden_settings_warning);
        this.mHiddenWarningView = textView;
        textView.setVisibility(this.mHiddenSettingsSpinner.getSelectedItemPosition() == 0 ? 8 : 0);
        this.mSecurityInPosition = new Integer[7];
        AccessPoint accessPoint2 = this.mAccessPoint;
        if (accessPoint2 == null) {
            configureSecuritySpinner();
            this.mConfigUi.setSubmitButton(resources.getString(R.string.wifi_save));
            this.mPasswordScanButton.setVisibility(8);
        } else {
            this.mConfigUi.setTitle(accessPoint2.getTitle());
            ViewGroup viewGroup = (ViewGroup) this.mView.findViewById(R.id.info);
            if (this.mAccessPoint.isSaved()) {
                WifiConfiguration config = this.mAccessPoint.getConfig();
                this.mMeteredSettingsSpinner.setSelection(config.meteredOverride);
                this.mHiddenSettingsSpinner.setSelection(config.hiddenSSID ? 1 : 0);
                this.mPrivacySettingsSpinner.setSelection(WifiPrivacyPreferenceController.translateMacRandomizedValueToPrefValue(config.macRandomizationSetting));
                if (config.getIpConfiguration().getIpAssignment() == IpConfiguration.IpAssignment.STATIC) {
                    this.mIpSettingsSpinner.setSelection(1);
                    StaticIpConfiguration staticIpConfiguration = config.getIpConfiguration().getStaticIpConfiguration();
                    if (staticIpConfiguration != null && (linkAddress = staticIpConfiguration.ipAddress) != null) {
                        addRow(viewGroup, R.string.wifi_ip_address, linkAddress.getAddress().getHostAddress());
                    }
                    z = true;
                } else {
                    this.mIpSettingsSpinner.setSelection(0);
                    z = false;
                }
                this.mSharedCheckBox.setEnabled(config.shared);
                if (!config.shared) {
                    z = true;
                }
                IpConfiguration.ProxySettings proxySettings = config.getIpConfiguration().getProxySettings();
                if (proxySettings == IpConfiguration.ProxySettings.STATIC) {
                    this.mProxySettingsSpinner.setSelection(1);
                } else if (proxySettings == IpConfiguration.ProxySettings.PAC) {
                    this.mProxySettingsSpinner.setSelection(2);
                } else {
                    this.mProxySettingsSpinner.setSelection(0);
                    if (config != null && config.isPasspoint()) {
                        addRow(viewGroup, R.string.passpoint_label, String.format(this.mContext.getString(R.string.passpoint_content), config.providerFriendlyName));
                    }
                }
                z = true;
                if (config != null) {
                    addRow(viewGroup, R.string.passpoint_label, String.format(this.mContext.getString(R.string.passpoint_content), config.providerFriendlyName));
                }
            } else {
                z = false;
            }
            if ((!this.mAccessPoint.isSaved() && !this.mAccessPoint.isActive() && !this.mAccessPoint.isPasspointConfig()) || this.mMode != 0) {
                showSecurityFields();
                showIpConfigFields();
                showProxyFields();
                CheckBox checkBox = (CheckBox) this.mView.findViewById(R.id.wifi_advanced_togglebox);
                checkBox.setOnCheckedChangeListener(this);
                checkBox.setChecked(z);
                this.mView.findViewById(R.id.wifi_advanced_fields).setVisibility(z ? 0 : 8);
            }
            int i2 = this.mMode;
            if (i2 == 2) {
                this.mConfigUi.setSubmitButton(resources.getString(R.string.wifi_save));
            } else if (i2 == 1) {
                this.mConfigUi.setSubmitButton(resources.getString(R.string.wifi_connect));
            } else {
                NetworkInfo.DetailedState detailedState = this.mAccessPoint.getDetailedState();
                String signalString = getSignalString();
                if ((detailedState == null || detailedState == NetworkInfo.DetailedState.DISCONNECTED) && signalString != null) {
                    this.mConfigUi.setSubmitButton(resources.getString(R.string.wifi_connect));
                } else {
                    String string = null;
                    if (detailedState != null) {
                        boolean zIsEphemeral = this.mAccessPoint.isEphemeral();
                        WifiConfiguration config2 = this.mAccessPoint.getConfig();
                        if (config2 != null && config2.isPasspoint()) {
                            String str = config2.providerFriendlyName;
                        }
                        addRow(viewGroup, R.string.wifi_status, AccessPoint.getSummary(this.mConfigUi.getContext(), null, detailedState, zIsEphemeral, (config2 == null || !(config2.fromWifiNetworkSpecifier || config2.fromWifiNetworkSuggestion)) ? null : config2.creatorName));
                    }
                    if (signalString != null) {
                        addRow(viewGroup, R.string.wifi_signal, signalString);
                    }
                    android.net.wifi.WifiInfo info = this.mAccessPoint.getInfo();
                    if (info != null && info.getTxLinkSpeedMbps() != -1) {
                        addRow(viewGroup, R.string.tx_wifi_speed, String.format(resources.getString(R.string.tx_link_speed), Integer.valueOf(info.getTxLinkSpeedMbps())));
                    }
                    if (info != null && info.getRxLinkSpeedMbps() != -1) {
                        addRow(viewGroup, R.string.rx_wifi_speed, String.format(resources.getString(R.string.rx_link_speed), Integer.valueOf(info.getRxLinkSpeedMbps())));
                    }
                    if (info != null && info.getFrequency() != -1) {
                        int frequency = info.getFrequency();
                        if (frequency >= 2400 && frequency < 2500) {
                            string = resources.getString(R.string.wifi_band_24ghz);
                        } else if (frequency >= 4900 && frequency < 5900) {
                            string = resources.getString(R.string.wifi_band_5ghz);
                        } else {
                            Log.e("WifiConfigController", "Unexpected frequency " + frequency);
                        }
                        if (string != null) {
                            addRow(viewGroup, R.string.wifi_frequency, string);
                        }
                    }
                    addRow(viewGroup, R.string.wifi_security, this.mAccessPoint.getSecurityString(false));
                    this.mView.findViewById(R.id.ip_fields).setVisibility(8);
                }
                if (this.mAccessPoint.isSaved() || this.mAccessPoint.isActive() || this.mAccessPoint.isPasspointConfig()) {
                    this.mConfigUi.setForgetButton(resources.getString(R.string.wifi_forget));
                }
            }
            if (!WifiDppUtils.isSupportEnrolleeQrCodeScanner(this.mContext, this.mAccessPointSecurity)) {
                this.mPasswordScanButton.setVisibility(8);
            }
            this.mSsidScanButton.setVisibility(8);
        }
        if (!isSplitSystemUser()) {
            this.mSharedCheckBox.setVisibility(8);
        }
        this.mConfigUi.setCancelButton(resources.getString(R.string.wifi_cancel));
        if (this.mConfigUi.getSubmitButton() != null) {
            enableSubmitIfAppropriate();
        }
        this.mView.findViewById(R.id.l_wifidialog).requestFocus();
    }

    boolean isSplitSystemUser() {
        return UserManager.isSplitSystemUser();
    }

    private void addRow(ViewGroup viewGroup, int i, String str) {
        View viewInflate = this.mConfigUi.getLayoutInflater().inflate(R.layout.wifi_dialog_row, viewGroup, false);
        ((TextView) viewInflate.findViewById(R.id.name)).setText(i);
        ((TextView) viewInflate.findViewById(R.id.value)).setText(str);
        viewGroup.addView(viewInflate);
    }

    String getSignalString() {
        int level;
        if (!this.mAccessPoint.isReachable() || (level = this.mAccessPoint.getLevel()) <= -1) {
            return null;
        }
        String[] strArr = this.mLevels;
        if (level < strArr.length) {
            return strArr[level];
        }
        return null;
    }

    void hideForgetButton() {
        Button forgetButton = this.mConfigUi.getForgetButton();
        if (forgetButton == null) {
            return;
        }
        forgetButton.setVisibility(8);
    }

    void hideSubmitButton() {
        Button submitButton = this.mConfigUi.getSubmitButton();
        if (submitButton == null) {
            return;
        }
        submitButton.setVisibility(8);
    }

    void enableSubmitIfAppropriate() {
        Button submitButton = this.mConfigUi.getSubmitButton();
        if (submitButton == null) {
            return;
        }
        submitButton.setEnabled(isSubmittable());
    }

    boolean isValidPsk(String str) {
        if (str.length() == 64 && str.matches("[0-9A-Fa-f]{64}")) {
            return true;
        }
        return str.length() >= 8 && str.length() <= 63;
    }

    boolean isValidSaePassword(String str) {
        return str.length() >= 1 && str.length() <= 63;
    }

    boolean isSubmittable() {
        AccessPoint accessPoint;
        AccessPoint accessPoint2;
        TextView textView = this.mPasswordView;
        boolean z = true;
        if (textView == null || ((this.mAccessPointSecurity != 1 || textView.length() != 0) && ((this.mAccessPointSecurity != 2 || isValidPsk(this.mPasswordView.getText().toString())) && (this.mAccessPointSecurity != 5 || isValidSaePassword(this.mPasswordView.getText().toString()))))) {
            z = false;
        }
        TextView textView2 = this.mSsidView;
        boolean zIpAndProxyFieldsAreValid = ((textView2 == null || textView2.length() != 0) && (((accessPoint = this.mAccessPoint) != null && accessPoint.isSaved()) || !z) && ((accessPoint2 = this.mAccessPoint) == null || !accessPoint2.isSaved() || !z || this.mPasswordView.length() <= 0)) ? ipAndProxyFieldsAreValid() : false;
        int i = this.mAccessPointSecurity;
        if ((i == 3 || i == 6) && this.mEapCaCertSpinner != null && this.mView.findViewById(R.id.l_ca_cert).getVisibility() != 8) {
            String str = (String) this.mEapCaCertSpinner.getSelectedItem();
            if (str.equals(this.mUnspecifiedCertString)) {
                zIpAndProxyFieldsAreValid = false;
            }
            if (str.equals(this.mUseSystemCertsString) && this.mEapDomainView != null && this.mView.findViewById(R.id.l_domain).getVisibility() != 8 && TextUtils.isEmpty(this.mEapDomainView.getText().toString())) {
                zIpAndProxyFieldsAreValid = false;
            }
        }
        int i2 = this.mAccessPointSecurity;
        if ((i2 == 3 || i2 == 6) && this.mEapUserCertSpinner != null && this.mView.findViewById(R.id.l_user_cert).getVisibility() != 8 && this.mEapUserCertSpinner.getSelectedItem().equals(this.mUnspecifiedCertString)) {
            return false;
        }
        return zIpAndProxyFieldsAreValid;
    }

    void showWarningMessagesIfAppropriate() {
        this.mView.findViewById(R.id.no_ca_cert_warning).setVisibility(8);
        this.mView.findViewById(R.id.no_domain_warning).setVisibility(8);
        this.mView.findViewById(R.id.ssid_too_long_warning).setVisibility(8);
        TextView textView = this.mSsidView;
        if (textView != null && WifiUtils.isSSIDTooLong(textView.getText().toString())) {
            this.mView.findViewById(R.id.ssid_too_long_warning).setVisibility(0);
        }
        if (this.mEapCaCertSpinner == null || this.mView.findViewById(R.id.l_ca_cert).getVisibility() == 8) {
            return;
        }
        String str = (String) this.mEapCaCertSpinner.getSelectedItem();
        if (str.equals(this.mDoNotValidateEapServerString)) {
            this.mView.findViewById(R.id.no_ca_cert_warning).setVisibility(0);
        }
        if (!str.equals(this.mUseSystemCertsString) || this.mEapDomainView == null || this.mView.findViewById(R.id.l_domain).getVisibility() == 8 || !TextUtils.isEmpty(this.mEapDomainView.getText().toString())) {
            return;
        }
        this.mView.findViewById(R.id.no_domain_warning).setVisibility(0);
    }

    public WifiConfiguration getConfig() {
        if (this.mMode == 0) {
            return null;
        }
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        AccessPoint accessPoint = this.mAccessPoint;
        if (accessPoint == null) {
            wifiConfiguration.SSID = AccessPoint.convertToQuotedString(this.mSsidView.getText().toString());
            wifiConfiguration.hiddenSSID = this.mHiddenSettingsSpinner.getSelectedItemPosition() == 1;
        } else if (!accessPoint.isSaved()) {
            wifiConfiguration.SSID = AccessPoint.convertToQuotedString(this.mAccessPoint.getSsidStr());
        } else {
            wifiConfiguration.networkId = this.mAccessPoint.getConfig().networkId;
            wifiConfiguration.hiddenSSID = this.mAccessPoint.getConfig().hiddenSSID;
        }
        wifiConfiguration.shared = this.mSharedCheckBox.isChecked();
        switch (this.mAccessPointSecurity) {
            case 0:
                wifiConfiguration.allowedKeyManagement.set(0);
                break;
            case 1:
                wifiConfiguration.allowedKeyManagement.set(0);
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                wifiConfiguration.allowedAuthAlgorithms.set(1);
                if (this.mPasswordView.length() != 0) {
                    int length = this.mPasswordView.length();
                    String string = this.mPasswordView.getText().toString();
                    if ((length == 10 || length == 26 || length == 58) && string.matches("[0-9A-Fa-f]*")) {
                        wifiConfiguration.wepKeys[0] = string;
                    } else {
                        wifiConfiguration.wepKeys[0] = '\"' + string + '\"';
                    }
                }
                break;
            case 2:
                wifiConfiguration.allowedKeyManagement.set(1);
                if (this.mPasswordView.length() != 0) {
                    String string2 = this.mPasswordView.getText().toString();
                    if (string2.matches("[0-9A-Fa-f]{64}")) {
                        wifiConfiguration.preSharedKey = string2;
                    } else {
                        wifiConfiguration.preSharedKey = '\"' + string2 + '\"';
                    }
                }
                break;
            case 3:
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                wifiConfiguration.allowedKeyManagement.set(2);
                wifiConfiguration.allowedKeyManagement.set(3);
                if (this.mAccessPointSecurity == 6) {
                    wifiConfiguration.allowedKeyManagement.set(10);
                    wifiConfiguration.requirePmf = true;
                    wifiConfiguration.allowedPairwiseCiphers.set(3);
                    wifiConfiguration.allowedGroupCiphers.set(5);
                    wifiConfiguration.allowedGroupManagementCiphers.set(2);
                }
                wifiConfiguration.enterpriseConfig = new WifiEnterpriseConfig();
                int selectedItemPosition = this.mEapMethodSpinner.getSelectedItemPosition();
                int selectedItemPosition2 = this.mPhase2Spinner.getSelectedItemPosition();
                wifiConfiguration.enterpriseConfig.setEapMethod(selectedItemPosition);
                if (selectedItemPosition != 0) {
                    wifiConfiguration.enterpriseConfig.setPhase2Method(selectedItemPosition2);
                } else if (selectedItemPosition2 == 0) {
                    wifiConfiguration.enterpriseConfig.setPhase2Method(0);
                } else if (selectedItemPosition2 == 1) {
                    wifiConfiguration.enterpriseConfig.setPhase2Method(3);
                } else if (selectedItemPosition2 == 2) {
                    wifiConfiguration.enterpriseConfig.setPhase2Method(4);
                } else if (selectedItemPosition2 == 3) {
                    wifiConfiguration.enterpriseConfig.setPhase2Method(5);
                } else if (selectedItemPosition2 == 4) {
                    wifiConfiguration.enterpriseConfig.setPhase2Method(6);
                } else if (selectedItemPosition2 == 5) {
                    wifiConfiguration.enterpriseConfig.setPhase2Method(7);
                } else {
                    Log.e("WifiConfigController", "Unknown phase2 method" + selectedItemPosition2);
                }
                String str = (String) this.mEapCaCertSpinner.getSelectedItem();
                wifiConfiguration.enterpriseConfig.setCaCertificateAliases(null);
                wifiConfiguration.enterpriseConfig.setCaPath(null);
                wifiConfiguration.enterpriseConfig.setDomainSuffixMatch(this.mEapDomainView.getText().toString());
                if (!str.equals(this.mUnspecifiedCertString) && !str.equals(this.mDoNotValidateEapServerString)) {
                    if (str.equals(this.mUseSystemCertsString)) {
                        wifiConfiguration.enterpriseConfig.setCaPath("/system/etc/security/cacerts");
                    } else if (str.equals(this.mMultipleCertSetString)) {
                        AccessPoint accessPoint2 = this.mAccessPoint;
                        if (accessPoint2 != null) {
                            if (!accessPoint2.isSaved()) {
                                Log.e("WifiConfigController", "Multiple certs can only be set when editing saved network");
                            }
                            wifiConfiguration.enterpriseConfig.setCaCertificateAliases(this.mAccessPoint.getConfig().enterpriseConfig.getCaCertificateAliases());
                        }
                    } else {
                        wifiConfiguration.enterpriseConfig.setCaCertificateAliases(new String[]{str});
                    }
                }
                if (wifiConfiguration.enterpriseConfig.getCaCertificateAliases() != null && wifiConfiguration.enterpriseConfig.getCaPath() != null) {
                    Log.e("WifiConfigController", "ca_cert (" + wifiConfiguration.enterpriseConfig.getCaCertificateAliases() + ") and ca_path (" + wifiConfiguration.enterpriseConfig.getCaPath() + ") should not both be non-null");
                }
                String str2 = (String) this.mEapUserCertSpinner.getSelectedItem();
                if (str2.equals(this.mUnspecifiedCertString) || str2.equals(this.mDoNotProvideEapUserCertString)) {
                    str2 = "";
                }
                wifiConfiguration.enterpriseConfig.setClientCertificateAlias(str2);
                if (selectedItemPosition == 4 || selectedItemPosition == 5 || selectedItemPosition == 6) {
                    wifiConfiguration.enterpriseConfig.setIdentity("");
                    wifiConfiguration.enterpriseConfig.setAnonymousIdentity("");
                } else if (selectedItemPosition == 3) {
                    wifiConfiguration.enterpriseConfig.setIdentity(this.mEapIdentityView.getText().toString());
                    wifiConfiguration.enterpriseConfig.setAnonymousIdentity("");
                } else {
                    wifiConfiguration.enterpriseConfig.setIdentity(this.mEapIdentityView.getText().toString());
                    wifiConfiguration.enterpriseConfig.setAnonymousIdentity(this.mEapAnonymousView.getText().toString());
                }
                if (!this.mPasswordView.isShown() || this.mPasswordView.length() > 0) {
                    wifiConfiguration.enterpriseConfig.setPassword(this.mPasswordView.getText().toString());
                }
                break;
            case 4:
                wifiConfiguration.allowedKeyManagement.set(9);
                wifiConfiguration.requirePmf = true;
                break;
            case 5:
                wifiConfiguration.allowedKeyManagement.set(8);
                wifiConfiguration.requirePmf = true;
                if (this.mPasswordView.length() != 0) {
                    wifiConfiguration.preSharedKey = '\"' + this.mPasswordView.getText().toString() + '\"';
                }
                break;
            default:
                return null;
        }
        wifiConfiguration.setIpConfiguration(new IpConfiguration(this.mIpAssignment, this.mProxySettings, this.mStaticIpConfiguration, this.mHttpProxy));
        Spinner spinner = this.mMeteredSettingsSpinner;
        if (spinner != null) {
            wifiConfiguration.meteredOverride = spinner.getSelectedItemPosition();
        }
        Spinner spinner2 = this.mPrivacySettingsSpinner;
        if (spinner2 != null) {
            wifiConfiguration.macRandomizationSetting = WifiPrivacyPreferenceController.translatePrefValueToMacRandomizedValue(spinner2.getSelectedItemPosition());
        }
        return wifiConfiguration;
    }

    private boolean ipAndProxyFieldsAreValid() {
        IpConfiguration.IpAssignment ipAssignment;
        TextView textView;
        Uri uri;
        TextView textView2;
        int i;
        int iValidate;
        Spinner spinner = this.mIpSettingsSpinner;
        if (spinner != null && spinner.getSelectedItemPosition() == 1) {
            ipAssignment = IpConfiguration.IpAssignment.STATIC;
        } else {
            ipAssignment = IpConfiguration.IpAssignment.DHCP;
        }
        this.mIpAssignment = ipAssignment;
        if (ipAssignment == IpConfiguration.IpAssignment.STATIC) {
            StaticIpConfiguration staticIpConfiguration = new StaticIpConfiguration();
            this.mStaticIpConfiguration = staticIpConfiguration;
            if (validateIpConfigFields(staticIpConfiguration) != 0) {
                return false;
            }
        }
        int selectedItemPosition = this.mProxySettingsSpinner.getSelectedItemPosition();
        this.mProxySettings = IpConfiguration.ProxySettings.NONE;
        this.mHttpProxy = null;
        if (selectedItemPosition == 1 && (textView2 = this.mProxyHostView) != null) {
            this.mProxySettings = IpConfiguration.ProxySettings.STATIC;
            String string = textView2.getText().toString();
            String string2 = this.mProxyPortView.getText().toString();
            String string3 = this.mProxyExclusionListView.getText().toString();
            try {
                i = Integer.parseInt(string2);
                try {
                    iValidate = ProxySelector.validate(string, string2, string3);
                } catch (NumberFormatException unused) {
                    iValidate = R.string.proxy_error_invalid_port;
                }
            } catch (NumberFormatException unused2) {
                i = 0;
            }
            if (iValidate != 0) {
                return false;
            }
            this.mHttpProxy = new ProxyInfo(string, i, string3);
        } else if (selectedItemPosition == 2 && (textView = this.mProxyPacView) != null) {
            this.mProxySettings = IpConfiguration.ProxySettings.PAC;
            CharSequence text = textView.getText();
            if (TextUtils.isEmpty(text) || (uri = Uri.parse(text.toString())) == null) {
                return false;
            }
            this.mHttpProxy = new ProxyInfo(uri);
        }
        return true;
    }

    private Inet4Address getIPv4Address(String str) {
        try {
            return (Inet4Address) NetworkUtils.numericToInetAddress(str);
        } catch (ClassCastException | IllegalArgumentException unused) {
            return null;
        }
    }

    private int validateIpConfigFields(StaticIpConfiguration staticIpConfiguration) {
        Inet4Address iPv4Address;
        TextView textView = this.mIpAddressView;
        if (textView == null) {
            return 0;
        }
        String string = textView.getText().toString();
        if (TextUtils.isEmpty(string) || (iPv4Address = getIPv4Address(string)) == null || iPv4Address.equals(Inet4Address.ANY)) {
            return R.string.wifi_ip_settings_invalid_ip_address;
        }
        int i = -1;
        try {
            i = Integer.parseInt(this.mNetworkPrefixLengthView.getText().toString());
        } catch (NumberFormatException unused) {
            this.mNetworkPrefixLengthView.setText(this.mConfigUi.getContext().getString(R.string.wifi_network_prefix_length_hint));
        } catch (IllegalArgumentException unused2) {
            return R.string.wifi_ip_settings_invalid_ip_address;
        }
        if (i >= 0 && i <= 32) {
            staticIpConfiguration.ipAddress = new LinkAddress(iPv4Address, i);
            String string2 = this.mGatewayView.getText().toString();
            if (TextUtils.isEmpty(string2)) {
                try {
                    byte[] address = NetworkUtils.getNetworkPart(iPv4Address, i).getAddress();
                    address[address.length - 1] = 1;
                    this.mGatewayView.setText(InetAddress.getByAddress(address).getHostAddress());
                } catch (RuntimeException | UnknownHostException unused3) {
                }
            } else {
                Inet4Address iPv4Address2 = getIPv4Address(string2);
                if (iPv4Address2 == null || iPv4Address2.isMulticastAddress()) {
                    return R.string.wifi_ip_settings_invalid_gateway;
                }
                staticIpConfiguration.gateway = iPv4Address2;
            }
            String string3 = this.mDns1View.getText().toString();
            if (TextUtils.isEmpty(string3)) {
                this.mDns1View.setText(this.mConfigUi.getContext().getString(R.string.wifi_dns1_hint));
            } else {
                Inet4Address iPv4Address3 = getIPv4Address(string3);
                if (iPv4Address3 == null) {
                    return R.string.wifi_ip_settings_invalid_dns;
                }
                staticIpConfiguration.dnsServers.add(iPv4Address3);
            }
            if (this.mDns2View.length() > 0) {
                Inet4Address iPv4Address4 = getIPv4Address(this.mDns2View.getText().toString());
                if (iPv4Address4 == null) {
                    return R.string.wifi_ip_settings_invalid_dns;
                }
                staticIpConfiguration.dnsServers.add(iPv4Address4);
            }
            return 0;
        }
        return R.string.wifi_ip_settings_invalid_network_prefix_length;
    }

    private void showSecurityFields() {
        int i = this.mAccessPointSecurity;
        if (i == 0 || i == 4) {
            this.mView.findViewById(R.id.security_fields).setVisibility(8);
            return;
        }
        this.mView.findViewById(R.id.security_fields).setVisibility(0);
        if (this.mPasswordView == null) {
            TextView textView = (TextView) this.mView.findViewById(R.id.password);
            this.mPasswordView = textView;
            textView.addTextChangedListener(this);
            this.mPasswordView.setOnEditorActionListener(this);
            this.mPasswordView.setOnKeyListener(this);
            ((CheckBox) this.mView.findViewById(R.id.show_password)).setOnCheckedChangeListener(this);
            AccessPoint accessPoint = this.mAccessPoint;
            if (accessPoint != null && accessPoint.isSaved()) {
                this.mPasswordView.setHint(R.string.wifi_unchanged);
            }
        }
        int i2 = this.mAccessPointSecurity;
        if (i2 != 3 && i2 != 6) {
            this.mView.findViewById(R.id.eap).setVisibility(8);
            return;
        }
        this.mView.findViewById(R.id.eap).setVisibility(0);
        Spinner spinner = this.mEapMethodSpinner;
        if (spinner == null) {
            Spinner spinner2 = (Spinner) this.mView.findViewById(R.id.method);
            this.mEapMethodSpinner = spinner2;
            spinner2.setOnItemSelectedListener(this);
            if (Utils.isWifiOnly(this.mContext) || !this.mContext.getResources().getBoolean(android.R.bool.config_canRemoveFirstAccount)) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(this.mContext, android.R.layout.simple_spinner_item, this.mContext.getResources().getStringArray(R.array.eap_method_without_sim_auth));
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                this.mEapMethodSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            }
            Spinner spinner3 = (Spinner) this.mView.findViewById(R.id.phase2);
            this.mPhase2Spinner = spinner3;
            spinner3.setOnItemSelectedListener(this);
            Spinner spinner4 = (Spinner) this.mView.findViewById(R.id.ca_cert);
            this.mEapCaCertSpinner = spinner4;
            spinner4.setOnItemSelectedListener(this);
            TextView textView2 = (TextView) this.mView.findViewById(R.id.domain);
            this.mEapDomainView = textView2;
            textView2.addTextChangedListener(this);
            Spinner spinner5 = (Spinner) this.mView.findViewById(R.id.user_cert);
            this.mEapUserCertSpinner = spinner5;
            spinner5.setOnItemSelectedListener(this);
            this.mEapIdentityView = (TextView) this.mView.findViewById(R.id.identity);
            this.mEapAnonymousView = (TextView) this.mView.findViewById(R.id.anonymous);
            loadCertificates(this.mEapCaCertSpinner, "CACERT_", this.mDoNotValidateEapServerString, false, true);
            loadCertificates(this.mEapUserCertSpinner, "USRPKEY_", this.mDoNotProvideEapUserCertString, false, false);
            AccessPoint accessPoint2 = this.mAccessPoint;
            if (accessPoint2 != null && accessPoint2.isSaved()) {
                WifiEnterpriseConfig wifiEnterpriseConfig = this.mAccessPoint.getConfig().enterpriseConfig;
                int eapMethod = wifiEnterpriseConfig.getEapMethod();
                int phase2Method = wifiEnterpriseConfig.getPhase2Method();
                this.mEapMethodSpinner.setSelection(eapMethod);
                showEapFieldsByMethod(eapMethod);
                if (eapMethod != 0) {
                    this.mPhase2Spinner.setSelection(phase2Method);
                } else if (phase2Method == 0) {
                    this.mPhase2Spinner.setSelection(0);
                } else if (phase2Method == 3) {
                    this.mPhase2Spinner.setSelection(1);
                } else if (phase2Method == 4) {
                    this.mPhase2Spinner.setSelection(2);
                } else if (phase2Method == 5) {
                    this.mPhase2Spinner.setSelection(3);
                } else if (phase2Method == 6) {
                    this.mPhase2Spinner.setSelection(4);
                } else if (phase2Method == 7) {
                    this.mPhase2Spinner.setSelection(5);
                } else {
                    Log.e("WifiConfigController", "Invalid phase 2 method " + phase2Method);
                }
                if (!TextUtils.isEmpty(wifiEnterpriseConfig.getCaPath())) {
                    setSelection(this.mEapCaCertSpinner, this.mUseSystemCertsString);
                } else {
                    String[] caCertificateAliases = wifiEnterpriseConfig.getCaCertificateAliases();
                    if (caCertificateAliases == null) {
                        setSelection(this.mEapCaCertSpinner, this.mDoNotValidateEapServerString);
                    } else if (caCertificateAliases.length == 1) {
                        setSelection(this.mEapCaCertSpinner, caCertificateAliases[0]);
                    } else {
                        loadCertificates(this.mEapCaCertSpinner, "CACERT_", this.mDoNotValidateEapServerString, true, true);
                        setSelection(this.mEapCaCertSpinner, this.mMultipleCertSetString);
                    }
                }
                this.mEapDomainView.setText(wifiEnterpriseConfig.getDomainSuffixMatch());
                String clientCertificateAlias = wifiEnterpriseConfig.getClientCertificateAlias();
                if (TextUtils.isEmpty(clientCertificateAlias)) {
                    setSelection(this.mEapUserCertSpinner, this.mDoNotProvideEapUserCertString);
                } else {
                    setSelection(this.mEapUserCertSpinner, clientCertificateAlias);
                }
                this.mEapIdentityView.setText(wifiEnterpriseConfig.getIdentity());
                this.mEapAnonymousView.setText(wifiEnterpriseConfig.getAnonymousIdentity());
                return;
            }
            this.mPhase2Spinner = (Spinner) this.mView.findViewById(R.id.phase2);
            showEapFieldsByMethod(this.mEapMethodSpinner.getSelectedItemPosition());
            return;
        }
        showEapFieldsByMethod(spinner.getSelectedItemPosition());
    }

    private void showEapFieldsByMethod(int i) {
        this.mView.findViewById(R.id.l_method).setVisibility(0);
        this.mView.findViewById(R.id.l_identity).setVisibility(0);
        this.mView.findViewById(R.id.l_domain).setVisibility(0);
        this.mView.findViewById(R.id.l_ca_cert).setVisibility(0);
        this.mView.findViewById(R.id.password_layout).setVisibility(0);
        this.mView.findViewById(R.id.show_password_layout).setVisibility(0);
        this.mConfigUi.getContext();
        switch (i) {
            case 0:
                ArrayAdapter<String> arrayAdapter = this.mPhase2Adapter;
                ArrayAdapter<String> arrayAdapter2 = this.mPhase2PeapAdapter;
                if (arrayAdapter != arrayAdapter2) {
                    this.mPhase2Adapter = arrayAdapter2;
                    this.mPhase2Spinner.setAdapter((SpinnerAdapter) arrayAdapter2);
                }
                this.mView.findViewById(R.id.l_phase2).setVisibility(0);
                this.mView.findViewById(R.id.l_anonymous).setVisibility(0);
                showPeapFields();
                setUserCertInvisible();
                break;
            case 1:
                this.mView.findViewById(R.id.l_user_cert).setVisibility(0);
                setPhase2Invisible();
                setAnonymousIdentInvisible();
                setPasswordInvisible();
                break;
            case 2:
                ArrayAdapter<String> arrayAdapter3 = this.mPhase2Adapter;
                ArrayAdapter<String> arrayAdapter4 = this.mPhase2FullAdapter;
                if (arrayAdapter3 != arrayAdapter4) {
                    this.mPhase2Adapter = arrayAdapter4;
                    this.mPhase2Spinner.setAdapter((SpinnerAdapter) arrayAdapter4);
                }
                this.mView.findViewById(R.id.l_phase2).setVisibility(0);
                this.mView.findViewById(R.id.l_anonymous).setVisibility(0);
                setUserCertInvisible();
                break;
            case 3:
                setPhase2Invisible();
                setCaCertInvisible();
                setDomainInvisible();
                setAnonymousIdentInvisible();
                setUserCertInvisible();
                break;
            case 4:
            case 5:
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                setPhase2Invisible();
                setAnonymousIdentInvisible();
                setCaCertInvisible();
                setDomainInvisible();
                setUserCertInvisible();
                setPasswordInvisible();
                setIdentityInvisible();
                break;
        }
        if (this.mView.findViewById(R.id.l_ca_cert).getVisibility() != 8) {
            String str = (String) this.mEapCaCertSpinner.getSelectedItem();
            if (str.equals(this.mDoNotValidateEapServerString) || str.equals(this.mUnspecifiedCertString)) {
                setDomainInvisible();
            }
        }
    }

    private void showPeapFields() {
        int selectedItemPosition = this.mPhase2Spinner.getSelectedItemPosition();
        if (selectedItemPosition == 3 || selectedItemPosition == 4 || selectedItemPosition == 5) {
            this.mEapIdentityView.setText("");
            this.mView.findViewById(R.id.l_identity).setVisibility(8);
            setPasswordInvisible();
        } else {
            this.mView.findViewById(R.id.l_identity).setVisibility(0);
            this.mView.findViewById(R.id.l_anonymous).setVisibility(0);
            this.mView.findViewById(R.id.password_layout).setVisibility(0);
            this.mView.findViewById(R.id.show_password_layout).setVisibility(0);
        }
    }

    private void setIdentityInvisible() {
        this.mView.findViewById(R.id.l_identity).setVisibility(8);
        this.mPhase2Spinner.setSelection(0);
    }

    private void setPhase2Invisible() {
        this.mView.findViewById(R.id.l_phase2).setVisibility(8);
        this.mPhase2Spinner.setSelection(0);
    }

    private void setCaCertInvisible() {
        this.mView.findViewById(R.id.l_ca_cert).setVisibility(8);
        setSelection(this.mEapCaCertSpinner, this.mUnspecifiedCertString);
    }

    private void setDomainInvisible() {
        this.mView.findViewById(R.id.l_domain).setVisibility(8);
        this.mEapDomainView.setText("");
    }

    private void setUserCertInvisible() {
        this.mView.findViewById(R.id.l_user_cert).setVisibility(8);
        setSelection(this.mEapUserCertSpinner, this.mUnspecifiedCertString);
    }

    private void setAnonymousIdentInvisible() {
        this.mView.findViewById(R.id.l_anonymous).setVisibility(8);
        this.mEapAnonymousView.setText("");
    }

    private void setPasswordInvisible() {
        this.mPasswordView.setText("");
        this.mView.findViewById(R.id.password_layout).setVisibility(8);
        this.mView.findViewById(R.id.show_password_layout).setVisibility(8);
    }

    private void showIpConfigFields() {
        StaticIpConfiguration staticIpConfiguration;
        this.mView.findViewById(R.id.ip_fields).setVisibility(0);
        AccessPoint accessPoint = this.mAccessPoint;
        WifiConfiguration config = (accessPoint == null || !accessPoint.isSaved()) ? null : this.mAccessPoint.getConfig();
        if (this.mIpSettingsSpinner.getSelectedItemPosition() == 1) {
            this.mView.findViewById(R.id.staticip).setVisibility(0);
            if (this.mIpAddressView == null) {
                TextView textView = (TextView) this.mView.findViewById(R.id.ipaddress);
                this.mIpAddressView = textView;
                textView.addTextChangedListener(this);
                TextView textView2 = (TextView) this.mView.findViewById(R.id.gateway);
                this.mGatewayView = textView2;
                textView2.addTextChangedListener(this);
                TextView textView3 = (TextView) this.mView.findViewById(R.id.network_prefix_length);
                this.mNetworkPrefixLengthView = textView3;
                textView3.addTextChangedListener(this);
                TextView textView4 = (TextView) this.mView.findViewById(R.id.dns1);
                this.mDns1View = textView4;
                textView4.addTextChangedListener(this);
                TextView textView5 = (TextView) this.mView.findViewById(R.id.dns2);
                this.mDns2View = textView5;
                textView5.addTextChangedListener(this);
            }
            if (config == null || (staticIpConfiguration = config.getIpConfiguration().getStaticIpConfiguration()) == null) {
                return;
            }
            LinkAddress linkAddress = staticIpConfiguration.ipAddress;
            if (linkAddress != null) {
                this.mIpAddressView.setText(linkAddress.getAddress().getHostAddress());
                this.mNetworkPrefixLengthView.setText(Integer.toString(staticIpConfiguration.ipAddress.getPrefixLength()));
            }
            InetAddress inetAddress = staticIpConfiguration.gateway;
            if (inetAddress != null) {
                this.mGatewayView.setText(inetAddress.getHostAddress());
            }
            Iterator it = staticIpConfiguration.dnsServers.iterator();
            if (it.hasNext()) {
                this.mDns1View.setText(((InetAddress) it.next()).getHostAddress());
            }
            if (it.hasNext()) {
                this.mDns2View.setText(((InetAddress) it.next()).getHostAddress());
                return;
            }
            return;
        }
        this.mView.findViewById(R.id.staticip).setVisibility(8);
    }

    private void showProxyFields() {
        ProxyInfo httpProxy;
        ProxyInfo httpProxy2;
        this.mView.findViewById(R.id.proxy_settings_fields).setVisibility(0);
        AccessPoint accessPoint = this.mAccessPoint;
        WifiConfiguration config = (accessPoint == null || !accessPoint.isSaved()) ? null : this.mAccessPoint.getConfig();
        if (this.mProxySettingsSpinner.getSelectedItemPosition() == 1) {
            setVisibility(R.id.proxy_warning_limited_support, 0);
            setVisibility(R.id.proxy_fields, 0);
            setVisibility(R.id.proxy_pac_field, 8);
            if (this.mProxyHostView == null) {
                TextView textView = (TextView) this.mView.findViewById(R.id.proxy_hostname);
                this.mProxyHostView = textView;
                textView.addTextChangedListener(this);
                TextView textView2 = (TextView) this.mView.findViewById(R.id.proxy_port);
                this.mProxyPortView = textView2;
                textView2.addTextChangedListener(this);
                TextView textView3 = (TextView) this.mView.findViewById(R.id.proxy_exclusionlist);
                this.mProxyExclusionListView = textView3;
                textView3.addTextChangedListener(this);
            }
            if (config == null || (httpProxy2 = config.getHttpProxy()) == null) {
                return;
            }
            this.mProxyHostView.setText(httpProxy2.getHost());
            this.mProxyPortView.setText(Integer.toString(httpProxy2.getPort()));
            this.mProxyExclusionListView.setText(httpProxy2.getExclusionListAsString());
            return;
        }
        if (this.mProxySettingsSpinner.getSelectedItemPosition() == 2) {
            setVisibility(R.id.proxy_warning_limited_support, 8);
            setVisibility(R.id.proxy_fields, 8);
            setVisibility(R.id.proxy_pac_field, 0);
            if (this.mProxyPacView == null) {
                TextView textView4 = (TextView) this.mView.findViewById(R.id.proxy_pac);
                this.mProxyPacView = textView4;
                textView4.addTextChangedListener(this);
            }
            if (config == null || (httpProxy = config.getHttpProxy()) == null) {
                return;
            }
            this.mProxyPacView.setText(httpProxy.getPacFileUrl().toString());
            return;
        }
        setVisibility(R.id.proxy_warning_limited_support, 8);
        setVisibility(R.id.proxy_fields, 8);
        setVisibility(R.id.proxy_pac_field, 8);
    }

    private void setVisibility(int i, int i2) {
        View viewFindViewById = this.mView.findViewById(i);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(i2);
        }
    }

    KeyStore getKeyStore() {
        return KeyStore.getInstance();
    }

    private void loadCertificates(Spinner spinner, String str, String str2, boolean z, boolean z2) {
        Context context = this.mConfigUi.getContext();
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mUnspecifiedCertString);
        if (z) {
            arrayList.add(this.mMultipleCertSetString);
        }
        if (z2) {
            arrayList.add(this.mUseSystemCertsString);
        }
        try {
            arrayList.addAll(Arrays.asList(getKeyStore().list(str, 1010)));
        } catch (Exception unused) {
            Log.e("WifiConfigController", "can't get the certificate list from KeyStore");
        }
        arrayList.add(str2);
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, (String[]) arrayList.toArray(new String[arrayList.size()]));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
    }

    private void setSelection(Spinner spinner, String str) {
        if (str != null) {
            ArrayAdapter arrayAdapter = (ArrayAdapter) spinner.getAdapter();
            for (int count = arrayAdapter.getCount() - 1; count >= 0; count--) {
                if (str.equals(arrayAdapter.getItem(count))) {
                    spinner.setSelection(count);
                    return;
                }
            }
        }
    }

    public int getMode() {
        return this.mMode;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$WifiConfigController$dNE3ZNZjUEMIk2yJ0TSoopgERkI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$afterTextChanged$0$WifiConfigController();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$afterTextChanged$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$afterTextChanged$0$WifiConfigController() {
        showWarningMessagesIfAppropriate();
        enableSubmitIfAppropriate();
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (textView != this.mPasswordView || i != 6 || !isSubmittable()) {
            return false;
        }
        this.mConfigUi.dispatchSubmit();
        return true;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (view != this.mPasswordView || i != 66 || !isSubmittable()) {
            return false;
        }
        this.mConfigUi.dispatchSubmit();
        return true;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i;
        int i2;
        if (compoundButton.getId() == R.id.show_password) {
            int selectionEnd = this.mPasswordView.getSelectionEnd();
            this.mPasswordView.setInputType((z ? 144 : 128) | 1);
            if (selectionEnd >= 0) {
                ((EditText) this.mPasswordView).setSelection(selectionEnd);
                return;
            }
            return;
        }
        if (compoundButton.getId() == R.id.wifi_advanced_togglebox) {
            View viewFindViewById = this.mView.findViewById(R.id.wifi_advanced_toggle);
            if (z) {
                i = 0;
                i2 = R.string.wifi_advanced_toggle_description_expanded;
            } else {
                i = 8;
                i2 = R.string.wifi_advanced_toggle_description_collapsed;
            }
            this.mView.findViewById(R.id.wifi_advanced_fields).setVisibility(i);
            viewFindViewById.setContentDescription(this.mContext.getString(i2));
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView == this.mSecuritySpinner) {
            this.mAccessPointSecurity = this.mSecurityInPosition[i].intValue();
            showSecurityFields();
            if (WifiDppUtils.isSupportEnrolleeQrCodeScanner(this.mContext, this.mAccessPointSecurity)) {
                this.mSsidScanButton.setVisibility(0);
            } else {
                this.mSsidScanButton.setVisibility(8);
            }
        } else {
            Spinner spinner = this.mEapMethodSpinner;
            if (adapterView == spinner || adapterView == this.mEapCaCertSpinner) {
                showSecurityFields();
            } else if (adapterView == this.mPhase2Spinner && spinner.getSelectedItemPosition() == 0) {
                showPeapFields();
            } else if (adapterView == this.mProxySettingsSpinner) {
                showProxyFields();
            } else if (adapterView == this.mHiddenSettingsSpinner) {
                this.mHiddenWarningView.setVisibility(i == 0 ? 8 : 0);
                if (i == 1) {
                    this.mDialogContainer.post(new Runnable() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$WifiConfigController$zQolZ2Zo_A4OyO0KCaoLpPt7C1U
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onItemSelected$1$WifiConfigController();
                        }
                    });
                }
            } else {
                showIpConfigFields();
            }
        }
        showWarningMessagesIfAppropriate();
        enableSubmitIfAppropriate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onItemSelected$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onItemSelected$1$WifiConfigController() {
        this.mDialogContainer.fullScroll(130);
    }

    public void updatePassword() {
        ((TextView) this.mView.findViewById(R.id.password)).setInputType((((CheckBox) this.mView.findViewById(R.id.show_password)).isChecked() ? 144 : 128) | 1);
    }

    private void configureSecuritySpinner() {
        int i;
        this.mConfigUi.setTitle(R.string.wifi_add_network);
        TextView textView = (TextView) this.mView.findViewById(R.id.ssid);
        this.mSsidView = textView;
        textView.addTextChangedListener(this);
        Spinner spinner = (Spinner) this.mView.findViewById(R.id.security);
        this.mSecuritySpinner = spinner;
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.mContext, android.R.layout.simple_spinner_item, android.R.id.text1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.mSecuritySpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        arrayAdapter.add(this.mContext.getString(R.string.wifi_security_none));
        this.mSecurityInPosition[0] = 0;
        if (this.mWifiManager.isEnhancedOpenSupported()) {
            arrayAdapter.add(this.mContext.getString(R.string.wifi_security_owe));
            this.mSecurityInPosition[1] = 4;
            i = 2;
        } else {
            i = 1;
        }
        arrayAdapter.add(this.mContext.getString(R.string.wifi_security_wep));
        int i2 = i + 1;
        this.mSecurityInPosition[i] = 1;
        arrayAdapter.add(this.mContext.getString(R.string.wifi_security_wpa_wpa2));
        int i3 = i2 + 1;
        this.mSecurityInPosition[i2] = 2;
        if (this.mWifiManager.isWpa3SaeSupported()) {
            arrayAdapter.add(this.mContext.getString(R.string.wifi_security_sae));
            this.mSecurityInPosition[i3] = 5;
            i3++;
        }
        arrayAdapter.add(this.mContext.getString(R.string.wifi_security_eap));
        int i4 = i3 + 1;
        this.mSecurityInPosition[i3] = 3;
        if (this.mWifiManager.isWpa3SuiteBSupported()) {
            arrayAdapter.add(this.mContext.getString(R.string.wifi_security_eap_suiteb));
            this.mSecurityInPosition[i4] = 6;
        }
        arrayAdapter.notifyDataSetChanged();
        this.mView.findViewById(R.id.type).setVisibility(0);
        showIpConfigFields();
        showProxyFields();
        this.mView.findViewById(R.id.wifi_advanced_toggle).setVisibility(0);
        this.mView.findViewById(R.id.hidden_settings_field).setVisibility(0);
        ((CheckBox) this.mView.findViewById(R.id.wifi_advanced_togglebox)).setOnCheckedChangeListener(this);
    }
}
