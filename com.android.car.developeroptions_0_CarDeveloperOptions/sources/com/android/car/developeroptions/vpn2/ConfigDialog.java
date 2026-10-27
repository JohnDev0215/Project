package com.android.car.developeroptions.vpn2;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Proxy;
import android.net.ProxyInfo;
import android.os.Bundle;
import android.os.SystemProperties;
import android.security.KeyStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.internal.net.VpnProfile;
import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
class ConfigDialog extends AlertDialog implements TextWatcher, View.OnClickListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {
    private TextView mAlwaysOnInvalidReason;
    private CheckBox mAlwaysOnVpn;
    private TextView mDnsServers;
    private boolean mEditing;
    private boolean mExists;
    private Spinner mIpsecCaCert;
    private TextView mIpsecIdentifier;
    private TextView mIpsecSecret;
    private Spinner mIpsecServerCert;
    private Spinner mIpsecUserCert;
    private final KeyStore mKeyStore;
    private TextView mL2tpSecret;
    private final DialogInterface.OnClickListener mListener;
    private CheckBox mMppe;
    private TextView mName;
    private TextView mPassword;
    private final VpnProfile mProfile;
    private TextView mProxyHost;
    private TextView mProxyPort;
    private Spinner mProxySettings;
    private TextView mRoutes;
    private CheckBox mSaveLogin;
    private TextView mSearchDomains;
    private TextView mServer;
    private CheckBox mShowOptions;
    private Spinner mType;
    private TextView mUsername;
    private View mView;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    ConfigDialog(Context context, DialogInterface.OnClickListener onClickListener, VpnProfile vpnProfile, boolean z, boolean z2) {
        super(context);
        this.mKeyStore = KeyStore.getInstance();
        this.mListener = onClickListener;
        this.mProfile = vpnProfile;
        this.mEditing = z;
        this.mExists = z2;
    }

    @Override // androidx.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        ProxyInfo proxyInfo;
        View viewInflate = getLayoutInflater().inflate(R.layout.vpn_dialog, (ViewGroup) null);
        this.mView = viewInflate;
        setView(viewInflate);
        Context context = getContext();
        this.mName = (TextView) this.mView.findViewById(R.id.name);
        this.mType = (Spinner) this.mView.findViewById(R.id.type);
        this.mServer = (TextView) this.mView.findViewById(R.id.server);
        this.mUsername = (TextView) this.mView.findViewById(R.id.username);
        this.mPassword = (TextView) this.mView.findViewById(R.id.password);
        this.mSearchDomains = (TextView) this.mView.findViewById(R.id.search_domains);
        this.mDnsServers = (TextView) this.mView.findViewById(R.id.dns_servers);
        this.mRoutes = (TextView) this.mView.findViewById(R.id.routes);
        this.mProxySettings = (Spinner) this.mView.findViewById(R.id.vpn_proxy_settings);
        this.mProxyHost = (TextView) this.mView.findViewById(R.id.vpn_proxy_host);
        this.mProxyPort = (TextView) this.mView.findViewById(R.id.vpn_proxy_port);
        this.mMppe = (CheckBox) this.mView.findViewById(R.id.mppe);
        this.mL2tpSecret = (TextView) this.mView.findViewById(R.id.l2tp_secret);
        this.mIpsecIdentifier = (TextView) this.mView.findViewById(R.id.ipsec_identifier);
        this.mIpsecSecret = (TextView) this.mView.findViewById(R.id.ipsec_secret);
        this.mIpsecUserCert = (Spinner) this.mView.findViewById(R.id.ipsec_user_cert);
        this.mIpsecCaCert = (Spinner) this.mView.findViewById(R.id.ipsec_ca_cert);
        this.mIpsecServerCert = (Spinner) this.mView.findViewById(R.id.ipsec_server_cert);
        this.mSaveLogin = (CheckBox) this.mView.findViewById(R.id.save_login);
        this.mShowOptions = (CheckBox) this.mView.findViewById(R.id.show_options);
        this.mAlwaysOnVpn = (CheckBox) this.mView.findViewById(R.id.always_on_vpn);
        this.mAlwaysOnInvalidReason = (TextView) this.mView.findViewById(R.id.always_on_invalid_reason);
        this.mName.setText(this.mProfile.name);
        this.mType.setSelection(this.mProfile.type);
        this.mServer.setText(this.mProfile.server);
        VpnProfile vpnProfile = this.mProfile;
        if (vpnProfile.saveLogin) {
            this.mUsername.setText(vpnProfile.username);
            this.mPassword.setText(this.mProfile.password);
        }
        this.mSearchDomains.setText(this.mProfile.searchDomains);
        this.mDnsServers.setText(this.mProfile.dnsServers);
        this.mRoutes.setText(this.mProfile.routes);
        ProxyInfo proxyInfo2 = this.mProfile.proxy;
        if (proxyInfo2 != null) {
            this.mProxyHost.setText(proxyInfo2.getHost());
            int port = this.mProfile.proxy.getPort();
            this.mProxyPort.setText(port == 0 ? "" : Integer.toString(port));
        }
        this.mMppe.setChecked(this.mProfile.mppe);
        this.mL2tpSecret.setText(this.mProfile.l2tpSecret);
        this.mL2tpSecret.setTextAppearance(android.R.style.TextAppearance.DeviceDefault.Medium);
        this.mIpsecIdentifier.setText(this.mProfile.ipsecIdentifier);
        this.mIpsecSecret.setText(this.mProfile.ipsecSecret);
        loadCertificates(this.mIpsecUserCert, "USRPKEY_", 0, this.mProfile.ipsecUserCert);
        loadCertificates(this.mIpsecCaCert, "CACERT_", R.string.vpn_no_ca_cert, this.mProfile.ipsecCaCert);
        loadCertificates(this.mIpsecServerCert, "USRCERT_", R.string.vpn_no_server_cert, this.mProfile.ipsecServerCert);
        this.mSaveLogin.setChecked(this.mProfile.saveLogin);
        this.mAlwaysOnVpn.setChecked(this.mProfile.key.equals(VpnUtils.getLockdownVpn()));
        this.mPassword.setTextAppearance(android.R.style.TextAppearance.DeviceDefault.Medium);
        if (SystemProperties.getBoolean("persist.radio.imsregrequired", false)) {
            this.mAlwaysOnVpn.setVisibility(8);
        }
        this.mName.addTextChangedListener(this);
        this.mType.setOnItemSelectedListener(this);
        this.mServer.addTextChangedListener(this);
        this.mUsername.addTextChangedListener(this);
        this.mPassword.addTextChangedListener(this);
        this.mDnsServers.addTextChangedListener(this);
        this.mRoutes.addTextChangedListener(this);
        this.mProxySettings.setOnItemSelectedListener(this);
        this.mProxyHost.addTextChangedListener(this);
        this.mProxyPort.addTextChangedListener(this);
        this.mIpsecSecret.addTextChangedListener(this);
        this.mIpsecUserCert.setOnItemSelectedListener(this);
        this.mShowOptions.setOnClickListener(this);
        this.mAlwaysOnVpn.setOnCheckedChangeListener(this);
        boolean z = this.mEditing || !validate(true);
        this.mEditing = z;
        if (z) {
            setTitle(R.string.vpn_edit);
            this.mView.findViewById(R.id.editor).setVisibility(0);
            changeType(this.mProfile.type);
            this.mSaveLogin.setVisibility(8);
            if (!this.mProfile.searchDomains.isEmpty() || !this.mProfile.dnsServers.isEmpty() || !this.mProfile.routes.isEmpty() || ((proxyInfo = this.mProfile.proxy) != null && (!proxyInfo.getHost().isEmpty() || this.mProfile.proxy.getPort() != 0))) {
                showAdvancedOptions();
            }
            if (this.mExists) {
                setButton(-3, context.getString(R.string.vpn_forget), this.mListener);
            }
            setButton(-1, context.getString(R.string.vpn_save), this.mListener);
        } else {
            setTitle(context.getString(R.string.vpn_connect_to, this.mProfile.name));
            setButton(-1, context.getString(R.string.vpn_connect), this.mListener);
        }
        setButton(-2, context.getString(R.string.vpn_cancel), this.mListener);
        super.onCreate(bundle);
        updateUiControls();
        getWindow().setSoftInputMode(20);
    }

    @Override // android.app.Dialog
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (this.mShowOptions.isChecked()) {
            showAdvancedOptions();
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        updateUiControls();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mShowOptions) {
            showAdvancedOptions();
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView == this.mType) {
            changeType(i);
        } else if (adapterView == this.mProxySettings) {
            updateProxyFieldsVisibility(i);
        }
        updateUiControls();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton == this.mAlwaysOnVpn) {
            updateUiControls();
        }
    }

    public boolean isVpnAlwaysOn() {
        return this.mAlwaysOnVpn.isChecked();
    }

    private void updateUiControls() {
        VpnProfile profile = getProfile();
        if (profile.isValidLockdownProfile()) {
            this.mAlwaysOnVpn.setEnabled(true);
            this.mAlwaysOnInvalidReason.setVisibility(8);
        } else {
            this.mAlwaysOnVpn.setChecked(false);
            this.mAlwaysOnVpn.setEnabled(false);
            if (!profile.isTypeValidForLockdown()) {
                this.mAlwaysOnInvalidReason.setText(R.string.vpn_always_on_invalid_reason_type);
            } else if (!profile.isServerAddressNumeric()) {
                this.mAlwaysOnInvalidReason.setText(R.string.vpn_always_on_invalid_reason_server);
            } else if (!profile.hasDns()) {
                this.mAlwaysOnInvalidReason.setText(R.string.vpn_always_on_invalid_reason_no_dns);
            } else if (!profile.areDnsAddressesNumeric()) {
                this.mAlwaysOnInvalidReason.setText(R.string.vpn_always_on_invalid_reason_dns);
            } else {
                this.mAlwaysOnInvalidReason.setText(R.string.vpn_always_on_invalid_reason_other);
            }
            this.mAlwaysOnInvalidReason.setVisibility(0);
        }
        ProxyInfo proxyInfo = this.mProfile.proxy;
        if (proxyInfo != null && (!proxyInfo.getHost().isEmpty() || this.mProfile.proxy.getPort() != 0)) {
            this.mProxySettings.setSelection(1);
            updateProxyFieldsVisibility(1);
        }
        if (this.mAlwaysOnVpn.isChecked()) {
            this.mSaveLogin.setChecked(true);
            this.mSaveLogin.setEnabled(false);
        } else {
            this.mSaveLogin.setChecked(this.mProfile.saveLogin);
            this.mSaveLogin.setEnabled(true);
        }
        getButton(-1).setEnabled(validate(this.mEditing));
    }

    private void updateProxyFieldsVisibility(int i) {
        this.mView.findViewById(R.id.vpn_proxy_fields).setVisibility(i == 1 ? 0 : 8);
    }

    private void showAdvancedOptions() {
        this.mView.findViewById(R.id.options).setVisibility(0);
        this.mShowOptions.setVisibility(8);
    }

    private void changeType(int i) {
        this.mMppe.setVisibility(8);
        this.mView.findViewById(R.id.l2tp).setVisibility(8);
        this.mView.findViewById(R.id.ipsec_psk).setVisibility(8);
        this.mView.findViewById(R.id.ipsec_user).setVisibility(8);
        this.mView.findViewById(R.id.ipsec_peer).setVisibility(8);
        if (i == 0) {
            this.mMppe.setVisibility(0);
            return;
        }
        if (i == 1) {
            this.mView.findViewById(R.id.l2tp).setVisibility(0);
        } else {
            if (i == 2) {
                this.mView.findViewById(R.id.l2tp).setVisibility(0);
            } else if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        return;
                    }
                }
                this.mView.findViewById(R.id.ipsec_peer).setVisibility(0);
                return;
            }
            this.mView.findViewById(R.id.ipsec_user).setVisibility(0);
            this.mView.findViewById(R.id.ipsec_peer).setVisibility(0);
            return;
        }
        this.mView.findViewById(R.id.ipsec_psk).setVisibility(0);
    }

    private boolean validate(boolean z) {
        if (this.mAlwaysOnVpn.isChecked() && !getProfile().isValidLockdownProfile()) {
            return false;
        }
        if (!z) {
            return (this.mUsername.getText().length() == 0 || this.mPassword.getText().length() == 0) ? false : true;
        }
        if (this.mName.getText().length() == 0 || this.mServer.getText().length() == 0 || !validateAddresses(this.mDnsServers.getText().toString(), false) || !validateAddresses(this.mRoutes.getText().toString(), true) || !validateProxy()) {
            return false;
        }
        int selectedItemPosition = this.mType.getSelectedItemPosition();
        if (selectedItemPosition != 0) {
            if (selectedItemPosition != 1) {
                if (selectedItemPosition != 2) {
                    if (selectedItemPosition != 3) {
                        if (selectedItemPosition != 4) {
                            if (selectedItemPosition != 5) {
                                return false;
                            }
                        }
                    }
                }
                return this.mIpsecUserCert.getSelectedItemPosition() != 0;
            }
            return this.mIpsecSecret.getText().length() != 0;
        }
        return true;
    }

    private boolean validateAddresses(String str, boolean z) {
        int i;
        try {
            for (String str2 : str.split(" ")) {
                if (!str2.isEmpty()) {
                    if (z) {
                        String[] strArrSplit = str2.split("/", 2);
                        String str3 = strArrSplit[0];
                        i = Integer.parseInt(strArrSplit[1]);
                        str2 = str3;
                    } else {
                        i = 32;
                    }
                    byte[] address = InetAddress.parseNumericAddress(str2).getAddress();
                    int i2 = ((address[1] & 255) << 16) | ((address[2] & 255) << 8) | (address[3] & 255) | ((address[0] & 255) << 24);
                    if (address.length != 4 || i < 0 || i > 32 || (i < 32 && (i2 << i) != 0)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private void loadCertificates(Spinner spinner, String str, int i, String str2) {
        String[] strArr;
        Context context = getContext();
        String string = i == 0 ? "" : context.getString(i);
        String[] list = this.mKeyStore.list(str);
        if (list == null || list.length == 0) {
            strArr = new String[]{string};
        } else {
            strArr = new String[list.length + 1];
            strArr[0] = string;
            System.arraycopy(list, 0, strArr, 1, list.length);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, strArr);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        for (int i2 = 1; i2 < strArr.length; i2++) {
            if (strArr[i2].equals(str2)) {
                spinner.setSelection(i2);
                return;
            }
        }
    }

    boolean isEditing() {
        return this.mEditing;
    }

    boolean hasProxy() {
        return this.mProxySettings.getSelectedItemPosition() == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    com.android.internal.net.VpnProfile getProfile() {
        /*
            Method dump skipped, instruction units count: 345
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.vpn2.ConfigDialog.getProfile():com.android.internal.net.VpnProfile");
    }

    private boolean validateProxy() {
        return !hasProxy() || Proxy.validate(this.mProxyHost.getText().toString().trim(), this.mProxyPort.getText().toString().trim(), "") == 0;
    }
}
