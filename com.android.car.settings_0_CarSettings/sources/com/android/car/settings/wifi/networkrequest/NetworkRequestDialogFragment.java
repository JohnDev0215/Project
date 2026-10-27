package com.android.car.settings.wifi.networkrequest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.car.settings.R;
import com.android.car.settings.wifi.WifiUtil;
import com.android.car.settings.wifi.networkrequest.NetworkRequestErrorDialogFragment;
import com.android.car.ui.AlertDialogBuilder;
import com.android.settingslib.Utils;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.ObservableDialogFragment;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.WifiTracker;
import com.android.settingslib.wifi.WifiTrackerFactory;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class NetworkRequestDialogFragment extends ObservableDialogFragment implements DialogInterface.OnClickListener, WifiManager.NetworkRequestMatchCallback {
    private static final int DELAY_TIME_STOP_SCAN_MS = 30000;
    static final String EXTRA_APP_NAME = "com.android.settings.wifi.extra.APP_NAME";
    static final String EXTRA_IS_SPECIFIED_SSID = "com.android.settings.wifi.extra.REQUEST_IS_FOR_SINGLE_NETWORK";
    private static final int MAX_NUMBER_LIST_ITEM = 5;
    private static final int MESSAGE_STOP_SCAN_WIFI_LIST = 0;
    private List<AccessPoint> mAccessPointList;
    private AccessPointAdapter mDialogAdapter;
    private FilterWifiTracker mFilterWifiTracker;
    private boolean mIsSpecifiedSsid;
    private WifiManager.NetworkRequestUserSelectionCallback mUserSelectionCallback;
    private boolean mWaitingConnectCallback;
    private boolean mShowLimitedItem = true;
    private final Handler mHandler = new Handler() { // from class: com.android.car.settings.wifi.networkrequest.NetworkRequestDialogFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                removeMessages(0);
                NetworkRequestDialogFragment.this.stopScanningAndPopErrorDialog(NetworkRequestErrorDialogFragment.ERROR_DIALOG_TYPE.TIME_OUT);
            }
        }
    };

    public static NetworkRequestDialogFragment newInstance() {
        NetworkRequestDialogFragment dialogFragment = new NetworkRequestDialogFragment();
        return dialogFragment;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getContext();
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            this.mIsSpecifiedSsid = intent.getBooleanExtra(EXTRA_IS_SPECIFIED_SSID, false);
        }
        this.mDialogAdapter = new AccessPointAdapter(context, R.layout.preference_access_point, getAccessPointList());
        AlertDialogBuilder builder = new AlertDialogBuilder(context).setTitle(getTitle()).setAdapter(this.mDialogAdapter, this).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.car.settings.wifi.networkrequest.-$$Lambda$NetworkRequestDialogFragment$VLS4lvgK3w_-R5UwcThYd5u_Nv4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreateDialog$0$NetworkRequestDialogFragment(dialogInterface, i);
            }
        }).setNeutralButton(R.string.network_connection_request_dialog_showall, (DialogInterface.OnClickListener) null);
        if (this.mIsSpecifiedSsid) {
            builder.setPositiveButton(R.string.wifi_setup_connect, (DialogInterface.OnClickListener) null);
        }
        final AlertDialog dialog = builder.create();
        dialog.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.android.car.settings.wifi.networkrequest.-$$Lambda$NetworkRequestDialogFragment$SCjGLvMl1YK3N2uhBHJLRNEtEzg
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                this.f$0.lambda$onCreateDialog$1$NetworkRequestDialogFragment(dialog, adapterView, view, i, j);
            }
        });
        setCancelable(false);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.car.settings.wifi.networkrequest.-$$Lambda$NetworkRequestDialogFragment$bKRkmNbm47Lq_qJ8PRSIk3HIBLQ
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                this.f$0.lambda$onCreateDialog$4$NetworkRequestDialogFragment(dialog, dialogInterface);
            }
        });
        return dialog;
    }

    public /* synthetic */ void lambda$onCreateDialog$0$NetworkRequestDialogFragment(DialogInterface dialog, int which) {
        onCancel(dialog);
    }

    public /* synthetic */ void lambda$onCreateDialog$1$NetworkRequestDialogFragment(AlertDialog dialog, AdapterView parent, View view, int position, long id) {
        onClick(dialog, position);
    }

    public /* synthetic */ void lambda$onCreateDialog$4$NetworkRequestDialogFragment(final AlertDialog dialog, DialogInterface dialogInterface) {
        final Button neutralBtn = dialog.getButton(-3);
        neutralBtn.setVisibility(8);
        neutralBtn.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.wifi.networkrequest.-$$Lambda$NetworkRequestDialogFragment$Zgav-PW7fUA9o7XjaVzHo2qW0pI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateDialog$2$NetworkRequestDialogFragment(neutralBtn, view);
            }
        });
        if (this.mIsSpecifiedSsid) {
            Button positiveBtn = dialog.getButton(-1);
            positiveBtn.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.wifi.networkrequest.-$$Lambda$NetworkRequestDialogFragment$Q38C5kU8pG1ruZUoQP6121GAL6w
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCreateDialog$3$NetworkRequestDialogFragment(dialog, view);
                }
            });
            positiveBtn.setEnabled(false);
        }
    }

    public /* synthetic */ void lambda$onCreateDialog$2$NetworkRequestDialogFragment(Button neutralBtn, View v) {
        this.mShowLimitedItem = false;
        renewAccessPointList(null);
        notifyAdapterRefresh();
        neutralBtn.setVisibility(8);
    }

    public /* synthetic */ void lambda$onCreateDialog$3$NetworkRequestDialogFragment(AlertDialog dialog, View v) {
        onClick(dialog, 0);
    }

    private String getTitle() {
        Intent intent = getActivity().getIntent();
        String appName = "";
        if (intent != null) {
            appName = intent.getStringExtra(EXTRA_APP_NAME);
        }
        return getString(R.string.network_connection_request_dialog_title, appName);
    }

    List<AccessPoint> getAccessPointList() {
        if (this.mAccessPointList == null) {
            this.mAccessPointList = new ArrayList();
        }
        return this.mAccessPointList;
    }

    private BaseAdapter getDialogAdapter() {
        return this.mDialogAdapter;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        List<AccessPoint> accessPointList = getAccessPointList();
        if (accessPointList.size() != 0 && this.mUserSelectionCallback != null && which < accessPointList.size()) {
            AccessPoint selectedAccessPoint = accessPointList.get(which);
            WifiConfiguration wifiConfig = selectedAccessPoint.getConfig();
            if (wifiConfig == null && selectedAccessPoint != null) {
                wifiConfig = WifiUtil.getWifiConfig(selectedAccessPoint, null);
            }
            if (wifiConfig != null) {
                this.mUserSelectionCallback.select(wifiConfig);
                this.mWaitingConnectCallback = true;
                updateConnectButton(false);
            }
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (getActivity() != null) {
            getActivity().finish();
        }
        WifiManager.NetworkRequestUserSelectionCallback networkRequestUserSelectionCallback = this.mUserSelectionCallback;
        if (networkRequestUserSelectionCallback != null) {
            networkRequestUserSelectionCallback.reject();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mHandler.removeMessages(0);
        WifiManager wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(WifiManager.class);
        if (wifiManager != null) {
            wifiManager.unregisterNetworkRequestMatchCallback(this);
        }
        FilterWifiTracker filterWifiTracker = this.mFilterWifiTracker;
        if (filterWifiTracker != null) {
            filterWifiTracker.onPause();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        FilterWifiTracker filterWifiTracker = this.mFilterWifiTracker;
        if (filterWifiTracker != null) {
            filterWifiTracker.onDestroy();
            this.mFilterWifiTracker = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAllButton() {
        Button neutralBtn;
        AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog != null && (neutralBtn = alertDialog.getButton(-3)) != null) {
            neutralBtn.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConnectButton(boolean enabled) {
        AlertDialog alertDialog;
        Button positiveBtn;
        if (this.mIsSpecifiedSsid && (alertDialog = (AlertDialog) getDialog()) != null && (positiveBtn = alertDialog.getButton(-1)) != null) {
            positiveBtn.setEnabled(enabled);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        WifiManager wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(WifiManager.class);
        if (wifiManager != null) {
            wifiManager.registerNetworkRequestMatchCallback(new HandlerExecutor(this.mHandler), this);
        }
        this.mHandler.sendEmptyMessageDelayed(0, 30000L);
        if (this.mFilterWifiTracker == null) {
            this.mFilterWifiTracker = new FilterWifiTracker(getActivity(), getSettingsLifecycle());
        }
        this.mFilterWifiTracker.onResume();
    }

    protected void stopScanningAndPopErrorDialog(NetworkRequestErrorDialogFragment.ERROR_DIALOG_TYPE type) {
        Dialog dialog = getDialog();
        if (dialog != null && dialog.isShowing()) {
            dismiss();
        }
        NetworkRequestErrorDialogFragment fragment = NetworkRequestErrorDialogFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable(NetworkRequestErrorDialogFragment.DIALOG_TYPE, type);
        fragment.setArguments(bundle);
        fragment.show(getActivity().getSupportFragmentManager(), NetworkRequestErrorDialogFragment.class.getSimpleName());
    }

    private class AccessPointAdapter extends ArrayAdapter<AccessPoint> {
        private final LayoutInflater mInflater;
        private final int mResourceId;

        AccessPointAdapter(Context context, int resourceId, List<AccessPoint> objects) {
            super(context, resourceId, objects);
            this.mResourceId = resourceId;
            this.mInflater = LayoutInflater.from(context);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null) {
                view = this.mInflater.inflate(this.mResourceId, parent, false);
                View divider = view.findViewById(com.android.settingslib.R.id.two_target_divider);
                divider.setVisibility(8);
            }
            AccessPoint accessPoint = getItem(position);
            TextView titleView = (TextView) view.findViewById(android.R.id.title);
            if (titleView != null) {
                titleView.setSingleLine(false);
                titleView.setText(accessPoint.getTitle());
            }
            TextView summary = (TextView) view.findViewById(android.R.id.summary);
            if (summary != null) {
                String summaryString = accessPoint.getSettingsSummary();
                if (TextUtils.isEmpty(summaryString)) {
                    summary.setVisibility(8);
                } else {
                    summary.setVisibility(0);
                    summary.setText(summaryString);
                }
            }
            ImageView imageView = (ImageView) view.findViewById(android.R.id.icon);
            int level = accessPoint.getLevel();
            if (imageView != null) {
                Drawable drawable = getContext().getDrawable(Utils.getWifiIconResource(level));
                drawable.setTintList(Utils.getColorAttr(getContext(), android.R.attr.colorControlNormal));
                imageView.setImageDrawable(drawable);
            }
            return view;
        }
    }

    public void onAbort() {
        stopScanningAndPopErrorDialog(NetworkRequestErrorDialogFragment.ERROR_DIALOG_TYPE.ABORT);
    }

    public void onUserSelectionCallbackRegistration(WifiManager.NetworkRequestUserSelectionCallback userSelectionCallback) {
        this.mUserSelectionCallback = userSelectionCallback;
    }

    public void onMatch(List<ScanResult> scanResults) {
        if (scanResults != null && scanResults.size() > 0) {
            this.mHandler.removeMessages(0);
            renewAccessPointList(scanResults);
            notifyAdapterRefresh();
        }
    }

    private void renewAccessPointList(List<ScanResult> scanResults) {
        FilterWifiTracker filterWifiTracker = this.mFilterWifiTracker;
        if (filterWifiTracker == null) {
            return;
        }
        if (scanResults != null) {
            filterWifiTracker.updateKeys(scanResults);
        }
        List<AccessPoint> list = getAccessPointList();
        list.clear();
        list.addAll(this.mFilterWifiTracker.getAccessPoints());
    }

    void notifyAdapterRefresh() {
        if (getDialogAdapter() != null) {
            getDialogAdapter().notifyDataSetChanged();
        }
    }

    public void onUserSelectionConnectSuccess(WifiConfiguration wificonfiguration) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    public void onUserSelectionConnectFailure(WifiConfiguration wificonfiguration) {
        this.mWaitingConnectCallback = false;
        updateConnectButton(true);
    }

    private final class FilterWifiTracker {
        private final List<String> mAccessPointKeys;
        private final Context mContext;
        private WifiTracker.WifiListener mWifiListener;
        private final WifiTracker mWifiTracker;

        FilterWifiTracker(Context context, Lifecycle lifecycle) {
            WifiTracker.WifiListener wifiListener = new WifiTracker.WifiListener() { // from class: com.android.car.settings.wifi.networkrequest.NetworkRequestDialogFragment.FilterWifiTracker.1
                @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
                public void onWifiStateChanged(int state) {
                    NetworkRequestDialogFragment.this.notifyAdapterRefresh();
                }

                @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
                public void onConnectedChanged() {
                    NetworkRequestDialogFragment.this.notifyAdapterRefresh();
                }

                @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
                public void onAccessPointsChanged() {
                    NetworkRequestDialogFragment.this.notifyAdapterRefresh();
                }
            };
            this.mWifiListener = wifiListener;
            this.mWifiTracker = WifiTrackerFactory.create(context, wifiListener, lifecycle, true, true);
            this.mAccessPointKeys = new ArrayList();
            this.mContext = context;
        }

        public void updateKeys(List<ScanResult> scanResults) {
            for (ScanResult scanResult : scanResults) {
                String key = AccessPoint.getKey(this.mContext, scanResult);
                if (!this.mAccessPointKeys.contains(key)) {
                    this.mAccessPointKeys.add(key);
                }
            }
        }

        public List<AccessPoint> getAccessPoints() {
            List<AccessPoint> allAccessPoints = this.mWifiTracker.getAccessPoints();
            List<AccessPoint> result = new ArrayList<>();
            int count = 0;
            for (AccessPoint accessPoint : allAccessPoints) {
                String key = accessPoint.getKey();
                if (this.mAccessPointKeys.contains(key)) {
                    result.add(accessPoint);
                    count++;
                    if (NetworkRequestDialogFragment.this.mShowLimitedItem && count >= 5) {
                        break;
                    }
                }
            }
            if (NetworkRequestDialogFragment.this.mShowLimitedItem && count >= 5) {
                NetworkRequestDialogFragment.this.showAllButton();
            }
            if (!NetworkRequestDialogFragment.this.mWaitingConnectCallback) {
                NetworkRequestDialogFragment.this.updateConnectButton(true);
            }
            return result;
        }

        public void onDestroy() {
            WifiTracker wifiTracker = this.mWifiTracker;
            if (wifiTracker != null) {
                wifiTracker.onDestroy();
            }
        }

        public void onResume() {
            WifiTracker wifiTracker = this.mWifiTracker;
            if (wifiTracker != null) {
                wifiTracker.onStart();
            }
        }

        public void onPause() {
            WifiTracker wifiTracker = this.mWifiTracker;
            if (wifiTracker != null) {
                wifiTracker.onStop();
            }
        }
    }
}
