package com.android.car.developeroptions.wifi;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.internal.PreferenceImageView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.wifi.NetworkRequestErrorDialogFragment;
import com.android.settingslib.Utils;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.WifiTracker;
import com.android.settingslib.wifi.WifiTrackerFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NetworkRequestDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener, WifiManager.NetworkRequestMatchCallback {
    static final String EXTRA_APP_NAME = "com.android.car.developeroptions.wifi.extra.APP_NAME";
    private List<AccessPoint> mAccessPointList;
    private AccessPointAdapter mDialogAdapter;
    private FilterWifiTracker mFilterWifiTracker;
    private boolean mIsSpecifiedSsid;
    private WifiManager.NetworkRequestUserSelectionCallback mUserSelectionCallback;
    private boolean mWaitingConnectCallback;
    private boolean mShowLimitedItem = true;
    private final Handler mHandler = new Handler() { // from class: com.android.car.developeroptions.wifi.NetworkRequestDialogFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            removeMessages(0);
            NetworkRequestDialogFragment.this.stopScanningAndPopErrorDialog(NetworkRequestErrorDialogFragment.ERROR_DIALOG_TYPE.TIME_OUT);
        }
    };

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1373;
    }

    public static NetworkRequestDialogFragment newInstance() {
        return new NetworkRequestDialogFragment();
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Context context = getContext();
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.network_request_dialog_title, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.network_request_title_text)).setText(getTitle());
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            this.mIsSpecifiedSsid = intent.getBooleanExtra("com.android.car.developeroptions.wifi.extra.REQUEST_IS_FOR_SINGLE_NETWORK", false);
        }
        ((ProgressBar) viewInflate.findViewById(R.id.network_request_title_progress)).setVisibility(0);
        this.mDialogAdapter = new AccessPointAdapter(this, context, R.layout.preference_access_point, getAccessPointList());
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCustomTitle(viewInflate);
        builder.setAdapter(this.mDialogAdapter, this);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$NetworkRequestDialogFragment$McL6XXv2WtYTcxhsTdJHygX9oTA
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreateDialog$0$NetworkRequestDialogFragment(dialogInterface, i);
            }
        });
        builder.setNeutralButton(R.string.network_connection_request_dialog_showall, null);
        if (this.mIsSpecifiedSsid) {
            builder.setPositiveButton(R.string.wifi_connect, (DialogInterface.OnClickListener) null);
        }
        final AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$NetworkRequestDialogFragment$7bZ9yhXgp_7p5hslLoocGIU2phA
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                this.f$0.lambda$onCreateDialog$1$NetworkRequestDialogFragment(alertDialogCreate, adapterView, view, i, j);
            }
        });
        setCancelable(false);
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$NetworkRequestDialogFragment$LYYMw0Eh4X4P1GYWQFuHPsia2oc
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                this.f$0.lambda$onCreateDialog$4$NetworkRequestDialogFragment(alertDialogCreate, dialogInterface);
            }
        });
        return alertDialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$0$NetworkRequestDialogFragment(DialogInterface dialogInterface, int i) {
        onCancel(dialogInterface);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$1$NetworkRequestDialogFragment(AlertDialog alertDialog, AdapterView adapterView, View view, int i, long j) {
        onClick(alertDialog, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$4$NetworkRequestDialogFragment(final AlertDialog alertDialog, DialogInterface dialogInterface) {
        final Button button = alertDialog.getButton(-3);
        button.setVisibility(8);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$NetworkRequestDialogFragment$lAOLzEDsH59y7ZvoEh3fIVumDAQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateDialog$2$NetworkRequestDialogFragment(button, view);
            }
        });
        if (this.mIsSpecifiedSsid) {
            Button button2 = alertDialog.getButton(-1);
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$NetworkRequestDialogFragment$fnVM7dcdchAYOzO_EJywuuA44Hc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCreateDialog$3$NetworkRequestDialogFragment(alertDialog, view);
                }
            });
            button2.setEnabled(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$2$NetworkRequestDialogFragment(Button button, View view) {
        this.mShowLimitedItem = false;
        renewAccessPointList(null);
        notifyAdapterRefresh();
        button.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$3$NetworkRequestDialogFragment(AlertDialog alertDialog, View view) {
        onClick(alertDialog, 0);
    }

    private String getTitle() {
        Intent intent = getActivity().getIntent();
        return getString(R.string.network_connection_request_dialog_title, intent != null ? intent.getStringExtra(EXTRA_APP_NAME) : "");
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
    public void onClick(DialogInterface dialogInterface, int i) {
        List<AccessPoint> accessPointList = getAccessPointList();
        if (accessPointList.size() == 0 || this.mUserSelectionCallback == null || i >= accessPointList.size()) {
            return;
        }
        AccessPoint accessPoint = accessPointList.get(i);
        WifiConfiguration config = accessPoint.getConfig();
        if (config == null) {
            config = WifiUtils.getWifiConfig(accessPoint, null, null);
        }
        if (config != null) {
            this.mUserSelectionCallback.select(config);
            this.mWaitingConnectCallback = true;
            updateConnectButton(false);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
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
        Button button;
        AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog == null || (button = alertDialog.getButton(-3)) == null) {
            return;
        }
        button.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConnectButton(boolean z) {
        AlertDialog alertDialog;
        Button button;
        if (!this.mIsSpecifiedSsid || (alertDialog = (AlertDialog) getDialog()) == null || (button = alertDialog.getButton(-1)) == null) {
            return;
        }
        button.setEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideProgressIcon() {
        View viewFindViewById;
        AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog == null || (viewFindViewById = alertDialog.findViewById(R.id.network_request_title_progress)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
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
            this.mFilterWifiTracker = new FilterWifiTracker(getContext(), getSettingsLifecycle());
        }
        this.mFilterWifiTracker.onResume();
    }

    protected void stopScanningAndPopErrorDialog(NetworkRequestErrorDialogFragment.ERROR_DIALOG_TYPE error_dialog_type) {
        Dialog dialog = getDialog();
        if (dialog != null && dialog.isShowing()) {
            dismiss();
        }
        NetworkRequestErrorDialogFragment networkRequestErrorDialogFragmentNewInstance = NetworkRequestErrorDialogFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable("DIALOG_ERROR_TYPE", error_dialog_type);
        networkRequestErrorDialogFragmentNewInstance.setArguments(bundle);
        networkRequestErrorDialogFragmentNewInstance.show(getActivity().getSupportFragmentManager(), NetworkRequestDialogFragment.class.getSimpleName());
    }

    private class AccessPointAdapter extends ArrayAdapter<AccessPoint> {
        private final LayoutInflater mInflater;
        private final int mResourceId;

        public AccessPointAdapter(NetworkRequestDialogFragment networkRequestDialogFragment, Context context, int i, List<AccessPoint> list) {
            super(context, i, list);
            this.mResourceId = i;
            this.mInflater = LayoutInflater.from(context);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.mInflater.inflate(this.mResourceId, viewGroup, false);
                view.findViewById(R.id.two_target_divider).setVisibility(8);
            }
            AccessPoint item = getItem(i);
            TextView textView = (TextView) view.findViewById(android.R.id.title);
            if (textView != null) {
                textView.setText(item.getSsidStr());
            }
            TextView textView2 = (TextView) view.findViewById(android.R.id.summary);
            if (textView2 != null) {
                String settingsSummary = item.getSettingsSummary();
                if (TextUtils.isEmpty(settingsSummary)) {
                    textView2.setVisibility(8);
                } else {
                    textView2.setVisibility(0);
                    textView2.setText(settingsSummary);
                }
            }
            PreferenceImageView preferenceImageView = (PreferenceImageView) view.findViewById(android.R.id.icon);
            int level = item.getLevel();
            if (preferenceImageView != null) {
                Drawable drawable = getContext().getDrawable(Utils.getWifiIconResource(level));
                drawable.setTintList(Utils.getColorAttr(getContext(), android.R.attr.colorControlNormal));
                preferenceImageView.setImageDrawable(drawable);
            }
            return view;
        }
    }

    public void onAbort() {
        stopScanningAndPopErrorDialog(NetworkRequestErrorDialogFragment.ERROR_DIALOG_TYPE.ABORT);
    }

    public void onUserSelectionCallbackRegistration(WifiManager.NetworkRequestUserSelectionCallback networkRequestUserSelectionCallback) {
        this.mUserSelectionCallback = networkRequestUserSelectionCallback;
    }

    public void onMatch(List<ScanResult> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mHandler.removeMessages(0);
        renewAccessPointList(list);
        notifyAdapterRefresh();
    }

    private void renewAccessPointList(List<ScanResult> list) {
        FilterWifiTracker filterWifiTracker = this.mFilterWifiTracker;
        if (filterWifiTracker == null) {
            return;
        }
        if (list != null) {
            filterWifiTracker.updateKeys(list);
        }
        List<AccessPoint> accessPointList = getAccessPointList();
        accessPointList.clear();
        accessPointList.addAll(this.mFilterWifiTracker.getAccessPoints());
    }

    void notifyAdapterRefresh() {
        if (getDialogAdapter() != null) {
            getDialogAdapter().notifyDataSetChanged();
        }
    }

    public void onUserSelectionConnectSuccess(WifiConfiguration wifiConfiguration) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Toast.makeText(activity, R.string.network_connection_connect_successful, 0).show();
            activity.finish();
        }
    }

    public void onUserSelectionConnectFailure(WifiConfiguration wifiConfiguration) {
        this.mWaitingConnectCallback = false;
        updateConnectButton(true);
    }

    private final class FilterWifiTracker {
        private final List<String> mAccessPointKeys;
        private final Context mContext;
        private WifiTracker.WifiListener mWifiListener;
        private final WifiTracker mWifiTracker;

        public FilterWifiTracker(Context context, Lifecycle lifecycle) {
            WifiTracker.WifiListener wifiListener = new WifiTracker.WifiListener() { // from class: com.android.car.developeroptions.wifi.NetworkRequestDialogFragment.FilterWifiTracker.1
                @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
                public void onWifiStateChanged(int i) {
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

        public void updateKeys(List<ScanResult> list) {
            Iterator<ScanResult> it = list.iterator();
            while (it.hasNext()) {
                String key = AccessPoint.getKey(this.mContext, it.next());
                if (!this.mAccessPointKeys.contains(key)) {
                    this.mAccessPointKeys.add(key);
                }
            }
        }

        public List<AccessPoint> getAccessPoints() {
            List<AccessPoint> accessPoints = this.mWifiTracker.getAccessPoints();
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (AccessPoint accessPoint : accessPoints) {
                if (this.mAccessPointKeys.contains(accessPoint.getKey())) {
                    arrayList.add(accessPoint);
                    i++;
                    if (NetworkRequestDialogFragment.this.mShowLimitedItem && i >= 5) {
                        break;
                    }
                }
            }
            if (NetworkRequestDialogFragment.this.mShowLimitedItem && i >= 5) {
                NetworkRequestDialogFragment.this.showAllButton();
            }
            if (i > 0) {
                NetworkRequestDialogFragment.this.hideProgressIcon();
            }
            if (i < 0) {
                NetworkRequestDialogFragment.this.updateConnectButton(false);
            } else if (!NetworkRequestDialogFragment.this.mWaitingConnectCallback) {
                NetworkRequestDialogFragment.this.updateConnectButton(true);
            }
            return arrayList;
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
