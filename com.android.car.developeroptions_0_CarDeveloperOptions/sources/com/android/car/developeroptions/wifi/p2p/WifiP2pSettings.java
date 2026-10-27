package com.android.car.developeroptions.wifi.p2p;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pGroupList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.SystemProperties;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiP2pSettings extends DashboardFragment implements WifiP2pManager.PersistentGroupInfoListener, WifiP2pManager.PeerListListener {
    private DialogInterface.OnClickListener mCancelConnectListener;
    private WifiP2pManager.Channel mChannel;
    private int mConnectedDevices;
    private DialogInterface.OnClickListener mDeleteGroupListener;
    private EditText mDeviceNameText;
    private DialogInterface.OnClickListener mDisconnectListener;
    private P2pPeerCategoryPreferenceController mPeerCategoryController;
    private P2pPersistentCategoryPreferenceController mPersistentCategoryController;
    private DialogInterface.OnClickListener mRenameListener;
    private String mSavedDeviceName;
    private WifiP2pPersistentGroup mSelectedGroup;
    private String mSelectedGroupName;
    private WifiP2pPeer mSelectedWifiPeer;
    private WifiP2pDevice mThisDevice;
    private P2pThisDevicePreferenceController mThisDevicePreferenceController;
    private boolean mWifiP2pEnabled;
    private WifiP2pManager mWifiP2pManager;
    private boolean mWifiP2pSearching;
    private final IntentFilter mIntentFilter = new IntentFilter();
    private boolean mLastGroupFormed = false;
    private boolean mIsIgnoreInitConnectionInfoCallback = false;
    private WifiP2pDeviceList mPeers = new WifiP2pDeviceList();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.wifi.p2p.WifiP2pSettings.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.wifi.p2p.STATE_CHANGED".equals(action)) {
                WifiP2pSettings.this.mWifiP2pEnabled = intent.getIntExtra("wifi_p2p_state", 1) == 2;
                WifiP2pSettings.this.handleP2pStateChanged();
                return;
            }
            if ("android.net.wifi.p2p.PEERS_CHANGED".equals(action)) {
                WifiP2pSettings.this.mPeers = (WifiP2pDeviceList) intent.getParcelableExtra("wifiP2pDeviceList");
                WifiP2pSettings.this.handlePeersChanged();
                return;
            }
            if ("android.net.wifi.p2p.CONNECTION_STATE_CHANGE".equals(action)) {
                if (WifiP2pSettings.this.mWifiP2pManager == null) {
                    return;
                }
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                WifiP2pInfo wifiP2pInfo = (WifiP2pInfo) intent.getParcelableExtra("wifiP2pInfo");
                if (!networkInfo.isConnected() && !WifiP2pSettings.this.mLastGroupFormed) {
                    WifiP2pSettings.this.startSearch();
                }
                WifiP2pSettings.this.mLastGroupFormed = wifiP2pInfo.groupFormed;
                WifiP2pSettings.this.mIsIgnoreInitConnectionInfoCallback = true;
                return;
            }
            if ("android.net.wifi.p2p.THIS_DEVICE_CHANGED".equals(action)) {
                WifiP2pSettings.this.mThisDevice = (WifiP2pDevice) intent.getParcelableExtra("wifiP2pDevice");
                WifiP2pSettings.this.mThisDevicePreferenceController.updateDeviceName(WifiP2pSettings.this.mThisDevice);
            } else {
                if ("android.net.wifi.p2p.DISCOVERY_STATE_CHANGE".equals(action)) {
                    if (intent.getIntExtra("discoveryState", 1) == 2) {
                        WifiP2pSettings.this.updateSearchMenu(true);
                        return;
                    } else {
                        WifiP2pSettings.this.updateSearchMenu(false);
                        return;
                    }
                }
                if (!"android.net.wifi.p2p.action.WIFI_P2P_PERSISTENT_GROUPS_CHANGED".equals(action) || WifiP2pSettings.this.mWifiP2pManager == null) {
                    return;
                }
                WifiP2pSettings.this.mWifiP2pManager.requestPersistentGroupInfo(WifiP2pSettings.this.mChannel, WifiP2pSettings.this);
            }
        }
    };

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        if (i == 1) {
            return 575;
        }
        if (i == 2) {
            return 576;
        }
        if (i != 3) {
            return i != 4 ? 0 : 578;
        }
        return 577;
    }

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_wifi_p2p;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "WifiP2pSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 109;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.wifi_p2p_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mPersistentCategoryController = new P2pPersistentCategoryPreferenceController(context);
        this.mPeerCategoryController = new P2pPeerCategoryPreferenceController(context);
        this.mThisDevicePreferenceController = new P2pThisDevicePreferenceController(context);
        arrayList.add(this.mPersistentCategoryController);
        arrayList.add(this.mPeerCategoryController);
        arrayList.add(this.mThisDevicePreferenceController);
        return arrayList;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        FragmentActivity activity = getActivity();
        WifiP2pManager wifiP2pManager = (WifiP2pManager) getSystemService("wifip2p");
        this.mWifiP2pManager = wifiP2pManager;
        if (wifiP2pManager != null) {
            WifiP2pManager.Channel channelInitialize = wifiP2pManager.initialize(activity.getApplicationContext(), getActivity().getMainLooper(), null);
            this.mChannel = channelInitialize;
            if (channelInitialize == null) {
                Log.e("WifiP2pSettings", "Failed to set up connection with wifi p2p service");
                this.mWifiP2pManager = null;
            }
        } else {
            Log.e("WifiP2pSettings", "mWifiP2pManager is null !");
        }
        if (bundle != null && bundle.containsKey("PEER_STATE")) {
            this.mSelectedWifiPeer = new WifiP2pPeer(getPrefContext(), (WifiP2pDevice) bundle.getParcelable("PEER_STATE"));
        }
        if (bundle != null && bundle.containsKey("DEV_NAME")) {
            this.mSavedDeviceName = bundle.getString("DEV_NAME");
        }
        if (bundle != null && bundle.containsKey("GROUP_NAME")) {
            this.mSelectedGroupName = bundle.getString("GROUP_NAME");
        }
        this.mRenameListener = new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.wifi.p2p.WifiP2pSettings.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i != -1 || WifiP2pSettings.this.mWifiP2pManager == null) {
                    return;
                }
                String string = WifiP2pSettings.this.mDeviceNameText.getText().toString();
                if (string != null) {
                    for (int i2 = 0; i2 < string.length(); i2++) {
                        char cCharAt = string.charAt(i2);
                        if (!Character.isDigit(cCharAt) && !Character.isLetter(cCharAt) && cCharAt != '-' && cCharAt != '_' && cCharAt != ' ') {
                            Toast.makeText(WifiP2pSettings.this.getActivity(), R.string.wifi_p2p_failed_rename_message, 1).show();
                            return;
                        }
                    }
                }
                WifiP2pSettings.this.mWifiP2pManager.setDeviceName(WifiP2pSettings.this.mChannel, WifiP2pSettings.this.mDeviceNameText.getText().toString(), new WifiP2pManager.ActionListener() { // from class: com.android.car.developeroptions.wifi.p2p.WifiP2pSettings.2.1
                    @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                    public void onSuccess() {
                    }

                    @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                    public void onFailure(int i3) {
                        Toast.makeText(WifiP2pSettings.this.getActivity(), R.string.wifi_p2p_failed_rename_message, 1).show();
                    }
                });
            }
        };
        this.mDisconnectListener = new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.wifi.p2p.WifiP2pSettings.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i != -1 || WifiP2pSettings.this.mWifiP2pManager == null) {
                    return;
                }
                WifiP2pSettings.this.mWifiP2pManager.removeGroup(WifiP2pSettings.this.mChannel, new WifiP2pManager.ActionListener(this) { // from class: com.android.car.developeroptions.wifi.p2p.WifiP2pSettings.3.1
                    @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                    public void onFailure(int i2) {
                    }

                    @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                    public void onSuccess() {
                    }
                });
            }
        };
        this.mCancelConnectListener = new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.wifi.p2p.WifiP2pSettings.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i != -1 || WifiP2pSettings.this.mWifiP2pManager == null) {
                    return;
                }
                WifiP2pSettings.this.mWifiP2pManager.cancelConnect(WifiP2pSettings.this.mChannel, new WifiP2pManager.ActionListener(this) { // from class: com.android.car.developeroptions.wifi.p2p.WifiP2pSettings.4.1
                    @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                    public void onFailure(int i2) {
                    }

                    @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                    public void onSuccess() {
                    }
                });
            }
        };
        this.mDeleteGroupListener = new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.wifi.p2p.WifiP2pSettings.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i != -1) {
                    if (i == -2) {
                        WifiP2pSettings.this.mSelectedGroup = null;
                    }
                } else {
                    if (WifiP2pSettings.this.mWifiP2pManager == null || WifiP2pSettings.this.mSelectedGroup == null) {
                        return;
                    }
                    WifiP2pSettings.this.mWifiP2pManager.deletePersistentGroup(WifiP2pSettings.this.mChannel, WifiP2pSettings.this.mSelectedGroup.getNetworkId(), new WifiP2pManager.ActionListener(this) { // from class: com.android.car.developeroptions.wifi.p2p.WifiP2pSettings.5.1
                        @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                        public void onFailure(int i2) {
                        }

                        @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                        public void onSuccess() {
                        }
                    });
                    WifiP2pSettings.this.mSelectedGroup = null;
                }
            }
        };
        super.onActivityCreated(bundle);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mIntentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
        this.mIntentFilter.addAction("android.net.wifi.p2p.PEERS_CHANGED");
        this.mIntentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
        this.mIntentFilter.addAction("android.net.wifi.p2p.THIS_DEVICE_CHANGED");
        this.mIntentFilter.addAction("android.net.wifi.p2p.DISCOVERY_STATE_CHANGE");
        this.mIntentFilter.addAction("android.net.wifi.p2p.action.WIFI_P2P_PERSISTENT_GROUPS_CHANGED");
        getPreferenceScreen();
        getActivity().registerReceiver(this.mReceiver, this.mIntentFilter);
        WifiP2pManager wifiP2pManager = this.mWifiP2pManager;
        if (wifiP2pManager != null) {
            wifiP2pManager.requestPeers(this.mChannel, this);
            this.mWifiP2pManager.requestDeviceInfo(this.mChannel, new WifiP2pManager.DeviceInfoListener() { // from class: com.android.car.developeroptions.wifi.p2p.-$$Lambda$WifiP2pSettings$1fDoOJgmpkNIEvRyB1qqLCGOJ6A
                @Override // android.net.wifi.p2p.WifiP2pManager.DeviceInfoListener
                public final void onDeviceInfoAvailable(WifiP2pDevice wifiP2pDevice) {
                    this.f$0.lambda$onResume$0$WifiP2pSettings(wifiP2pDevice);
                }
            });
            this.mIsIgnoreInitConnectionInfoCallback = false;
            this.mWifiP2pManager.requestNetworkInfo(this.mChannel, new WifiP2pManager.NetworkInfoListener() { // from class: com.android.car.developeroptions.wifi.p2p.-$$Lambda$WifiP2pSettings$D15WslvI4OanEKtSih8WbkcoJ7g
                @Override // android.net.wifi.p2p.WifiP2pManager.NetworkInfoListener
                public final void onNetworkInfoAvailable(NetworkInfo networkInfo) {
                    this.f$0.lambda$onResume$2$WifiP2pSettings(networkInfo);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onResume$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onResume$0$WifiP2pSettings(WifiP2pDevice wifiP2pDevice) {
        this.mThisDevice = wifiP2pDevice;
        this.mThisDevicePreferenceController.updateDeviceName(wifiP2pDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onResume$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onResume$2$WifiP2pSettings(final NetworkInfo networkInfo) {
        this.mWifiP2pManager.requestConnectionInfo(this.mChannel, new WifiP2pManager.ConnectionInfoListener() { // from class: com.android.car.developeroptions.wifi.p2p.-$$Lambda$WifiP2pSettings$3ejZgEL-TNxj5QuEkZUMWlVSNQE
            @Override // android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener
            public final void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
                this.f$0.lambda$onResume$1$WifiP2pSettings(networkInfo, wifiP2pInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onResume$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onResume$1$WifiP2pSettings(NetworkInfo networkInfo, WifiP2pInfo wifiP2pInfo) {
        if (this.mIsIgnoreInitConnectionInfoCallback) {
            return;
        }
        if (!networkInfo.isConnected() && !this.mLastGroupFormed) {
            startSearch();
        }
        this.mLastGroupFormed = wifiP2pInfo.groupFormed;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        WifiP2pManager wifiP2pManager = this.mWifiP2pManager;
        if (wifiP2pManager != null) {
            wifiP2pManager.stopPeerDiscovery(this.mChannel, null);
        }
        getActivity().unregisterReceiver(this.mReceiver);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.add(0, 1, 0, this.mWifiP2pSearching ? R.string.wifi_p2p_menu_searching : R.string.wifi_p2p_menu_search).setEnabled(this.mWifiP2pEnabled);
        menu.add(0, 2, 0, R.string.wifi_p2p_menu_rename).setEnabled(this.mWifiP2pEnabled);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItemFindItem = menu.findItem(1);
        MenuItem menuItemFindItem2 = menu.findItem(2);
        if (this.mWifiP2pEnabled) {
            menuItemFindItem.setEnabled(true);
            menuItemFindItem2.setEnabled(true);
        } else {
            menuItemFindItem.setEnabled(false);
            menuItemFindItem2.setEnabled(false);
        }
        if (this.mWifiP2pSearching) {
            menuItemFindItem.setTitle(R.string.wifi_p2p_menu_searching);
        } else {
            menuItemFindItem.setTitle(R.string.wifi_p2p_menu_search);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            startSearch();
            return true;
        }
        if (itemId == 2) {
            showDialog(3);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference instanceof WifiP2pPeer) {
            WifiP2pPeer wifiP2pPeer = (WifiP2pPeer) preference;
            this.mSelectedWifiPeer = wifiP2pPeer;
            int i = wifiP2pPeer.device.status;
            if (i == 0) {
                showDialog(1);
            } else if (i == 1) {
                showDialog(2);
            } else {
                WifiP2pConfig wifiP2pConfig = new WifiP2pConfig();
                wifiP2pConfig.deviceAddress = this.mSelectedWifiPeer.device.deviceAddress;
                int i2 = SystemProperties.getInt("wifidirect.wps", -1);
                if (i2 != -1) {
                    wifiP2pConfig.wps.setup = i2;
                } else if (this.mSelectedWifiPeer.device.wpsPbcSupported()) {
                    wifiP2pConfig.wps.setup = 0;
                } else if (this.mSelectedWifiPeer.device.wpsKeypadSupported()) {
                    wifiP2pConfig.wps.setup = 2;
                } else {
                    wifiP2pConfig.wps.setup = 1;
                }
                this.mWifiP2pManager.connect(this.mChannel, wifiP2pConfig, new WifiP2pManager.ActionListener() { // from class: com.android.car.developeroptions.wifi.p2p.WifiP2pSettings.6
                    @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                    public void onSuccess() {
                    }

                    @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                    public void onFailure(int i3) {
                        Log.e("WifiP2pSettings", " connect fail " + i3);
                        Toast.makeText(WifiP2pSettings.this.getActivity(), R.string.wifi_p2p_failed_connect_message, 0).show();
                    }
                });
            }
        } else if (preference instanceof WifiP2pPersistentGroup) {
            this.mSelectedGroup = (WifiP2pPersistentGroup) preference;
            showDialog(4);
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public Dialog onCreateDialog(int i) {
        String str;
        String str2;
        String string;
        if (i == 1) {
            if (TextUtils.isEmpty(this.mSelectedWifiPeer.device.deviceName)) {
                str2 = this.mSelectedWifiPeer.device.deviceAddress;
            } else {
                str2 = this.mSelectedWifiPeer.device.deviceName;
            }
            if (this.mConnectedDevices > 1) {
                string = getActivity().getString(R.string.wifi_p2p_disconnect_multiple_message, new Object[]{str2, Integer.valueOf(this.mConnectedDevices - 1)});
            } else {
                string = getActivity().getString(R.string.wifi_p2p_disconnect_message, new Object[]{str2});
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.wifi_p2p_disconnect_title);
            builder.setMessage(string);
            builder.setPositiveButton(getActivity().getString(R.string.dlg_ok), this.mDisconnectListener);
            builder.setNegativeButton(getActivity().getString(R.string.dlg_cancel), (DialogInterface.OnClickListener) null);
            return builder.create();
        }
        if (i == 2) {
            if (TextUtils.isEmpty(this.mSelectedWifiPeer.device.deviceName)) {
                str = this.mSelectedWifiPeer.device.deviceAddress;
            } else {
                str = this.mSelectedWifiPeer.device.deviceName;
            }
            AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
            builder2.setTitle(R.string.wifi_p2p_cancel_connect_title);
            builder2.setMessage(getActivity().getString(R.string.wifi_p2p_cancel_connect_message, new Object[]{str}));
            builder2.setPositiveButton(getActivity().getString(R.string.dlg_ok), this.mCancelConnectListener);
            builder2.setNegativeButton(getActivity().getString(R.string.dlg_cancel), (DialogInterface.OnClickListener) null);
            return builder2.create();
        }
        if (i != 3) {
            if (i != 4) {
                return null;
            }
            AlertDialog.Builder builder3 = new AlertDialog.Builder(getActivity());
            builder3.setMessage(getActivity().getString(R.string.wifi_p2p_delete_group_message));
            builder3.setPositiveButton(getActivity().getString(R.string.dlg_ok), this.mDeleteGroupListener);
            builder3.setNegativeButton(getActivity().getString(R.string.dlg_cancel), this.mDeleteGroupListener);
            return builder3.create();
        }
        EditText editText = new EditText(getActivity());
        this.mDeviceNameText = editText;
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)});
        String str3 = this.mSavedDeviceName;
        if (str3 != null) {
            this.mDeviceNameText.setText(str3);
            this.mDeviceNameText.setSelection(this.mSavedDeviceName.length());
        } else {
            WifiP2pDevice wifiP2pDevice = this.mThisDevice;
            if (wifiP2pDevice != null && !TextUtils.isEmpty(wifiP2pDevice.deviceName)) {
                this.mDeviceNameText.setText(this.mThisDevice.deviceName);
                this.mDeviceNameText.setSelection(0, this.mThisDevice.deviceName.length());
            }
        }
        this.mSavedDeviceName = null;
        AlertDialog.Builder builder4 = new AlertDialog.Builder(getActivity());
        builder4.setTitle(R.string.wifi_p2p_menu_rename);
        builder4.setView(this.mDeviceNameText);
        builder4.setPositiveButton(getActivity().getString(R.string.dlg_ok), this.mRenameListener);
        builder4.setNegativeButton(getActivity().getString(R.string.dlg_cancel), (DialogInterface.OnClickListener) null);
        return builder4.create();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        WifiP2pPeer wifiP2pPeer = this.mSelectedWifiPeer;
        if (wifiP2pPeer != null) {
            bundle.putParcelable("PEER_STATE", wifiP2pPeer.device);
        }
        EditText editText = this.mDeviceNameText;
        if (editText != null) {
            bundle.putString("DEV_NAME", editText.getText().toString());
        }
        WifiP2pPersistentGroup wifiP2pPersistentGroup = this.mSelectedGroup;
        if (wifiP2pPersistentGroup != null) {
            bundle.putString("GROUP_NAME", wifiP2pPersistentGroup.getGroupName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePeersChanged() {
        this.mPeerCategoryController.removeAllChildren();
        this.mConnectedDevices = 0;
        for (WifiP2pDevice wifiP2pDevice : this.mPeers.getDeviceList()) {
            this.mPeerCategoryController.addChild(new WifiP2pPeer(getPrefContext(), wifiP2pDevice));
            if (wifiP2pDevice.status == 0) {
                this.mConnectedDevices++;
            }
        }
    }

    public void onPersistentGroupInfoAvailable(WifiP2pGroupList wifiP2pGroupList) {
        this.mPersistentCategoryController.removeAllChildren();
        Iterator it = wifiP2pGroupList.getGroupList().iterator();
        while (it.hasNext()) {
            WifiP2pPersistentGroup wifiP2pPersistentGroup = new WifiP2pPersistentGroup(getPrefContext(), (WifiP2pGroup) it.next());
            this.mPersistentCategoryController.addChild(wifiP2pPersistentGroup);
            if (wifiP2pPersistentGroup.getGroupName().equals(this.mSelectedGroupName)) {
                this.mSelectedGroup = wifiP2pPersistentGroup;
                this.mSelectedGroupName = null;
            }
        }
        if (this.mSelectedGroupName != null) {
            Log.w("WifiP2pSettings", " Selected group " + this.mSelectedGroupName + " disappered on next query ");
        }
    }

    @Override // android.net.wifi.p2p.WifiP2pManager.PeerListListener
    public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
        this.mPeers = wifiP2pDeviceList;
        handlePeersChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleP2pStateChanged() {
        updateSearchMenu(false);
        this.mThisDevicePreferenceController.setEnabled(this.mWifiP2pEnabled);
        this.mPersistentCategoryController.setEnabled(this.mWifiP2pEnabled);
        this.mPeerCategoryController.setEnabled(this.mWifiP2pEnabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSearchMenu(boolean z) {
        this.mWifiP2pSearching = z;
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSearch() {
        WifiP2pManager wifiP2pManager = this.mWifiP2pManager;
        if (wifiP2pManager == null || this.mWifiP2pSearching) {
            return;
        }
        wifiP2pManager.discoverPeers(this.mChannel, new WifiP2pManager.ActionListener(this) { // from class: com.android.car.developeroptions.wifi.p2p.WifiP2pSettings.7
            @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
            public void onFailure(int i) {
            }

            @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
            public void onSuccess() {
            }
        });
    }
}
