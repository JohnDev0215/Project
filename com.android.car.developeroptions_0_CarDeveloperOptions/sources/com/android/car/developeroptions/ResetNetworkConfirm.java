package com.android.car.developeroptions;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkPolicyManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.os.UserHandle;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.enterprise.ActionDisabledByAdminDialogHelper;
import com.android.ims.ImsManager;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;

/* JADX INFO: loaded from: classes.dex */
public class ResetNetworkConfirm extends InstrumentedFragment {
    View mContentView;
    boolean mEraseEsim;
    EraseEsimAsyncTask mEraseEsimTask;
    private int mSubId = -1;
    private View.OnClickListener mFinalClickListener = new View.OnClickListener() { // from class: com.android.car.developeroptions.ResetNetworkConfirm.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BluetoothAdapter adapter;
            if (Utils.isMonkeyRunning()) {
                return;
            }
            FragmentActivity activity = ResetNetworkConfirm.this.getActivity();
            ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService("connectivity");
            if (connectivityManager != null) {
                connectivityManager.factoryReset();
            }
            WifiManager wifiManager = (WifiManager) activity.getSystemService("wifi");
            if (wifiManager != null) {
                wifiManager.factoryReset();
            }
            ResetNetworkConfirm.this.p2pFactoryReset(activity);
            TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService("phone");
            if (telephonyManager != null) {
                telephonyManager.factoryReset(ResetNetworkConfirm.this.mSubId);
            }
            NetworkPolicyManager networkPolicyManager = (NetworkPolicyManager) activity.getSystemService("netpolicy");
            if (networkPolicyManager != null) {
                networkPolicyManager.factoryReset(telephonyManager.getSubscriberId(ResetNetworkConfirm.this.mSubId));
            }
            BluetoothManager bluetoothManager = (BluetoothManager) activity.getSystemService("bluetooth");
            if (bluetoothManager != null && (adapter = bluetoothManager.getAdapter()) != null) {
                adapter.factoryReset();
            }
            ImsManager.getInstance(activity, SubscriptionManager.getPhoneId(ResetNetworkConfirm.this.mSubId)).factoryReset();
            ResetNetworkConfirm.this.restoreDefaultApn(activity);
            ResetNetworkConfirm.this.esimFactoryReset(activity, activity.getPackageName());
        }
    };

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 84;
    }

    private static class EraseEsimAsyncTask extends AsyncTask<Void, Void, Boolean> {
        private final Context mContext;
        private final String mPackageName;

        EraseEsimAsyncTask(Context context, String str) {
            this.mContext = context;
            this.mPackageName = str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(RecoverySystem.wipeEuiccData(this.mContext, this.mPackageName));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                Toast.makeText(this.mContext, R.string.reset_network_complete_toast, 0).show();
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            builder.setTitle(R.string.reset_esim_error_title);
            builder.setMessage(R.string.reset_esim_error_msg);
            builder.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
            builder.show();
        }
    }

    void esimFactoryReset(Context context, String str) {
        if (this.mEraseEsim) {
            EraseEsimAsyncTask eraseEsimAsyncTask = new EraseEsimAsyncTask(context, str);
            this.mEraseEsimTask = eraseEsimAsyncTask;
            eraseEsimAsyncTask.execute(new Void[0]);
            return;
        }
        Toast.makeText(context, R.string.reset_network_complete_toast, 0).show();
    }

    void p2pFactoryReset(Context context) {
        WifiP2pManager.Channel channelInitialize;
        WifiP2pManager wifiP2pManager = (WifiP2pManager) context.getSystemService("wifip2p");
        if (wifiP2pManager == null || (channelInitialize = wifiP2pManager.initialize(context.getApplicationContext(), context.getMainLooper(), null)) == null) {
            return;
        }
        wifiP2pManager.factoryReset(channelInitialize, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreDefaultApn(Context context) {
        Uri uriWithAppendedPath = Uri.parse("content://telephony/carriers/restore");
        if (SubscriptionManager.isUsableSubIdValue(this.mSubId)) {
            uriWithAppendedPath = Uri.withAppendedPath(uriWithAppendedPath, "subId/" + String.valueOf(this.mSubId));
        }
        context.getContentResolver().delete(uriWithAppendedPath, null, null);
    }

    private void establishFinalConfirmationState() {
        this.mContentView.findViewById(R.id.execute_reset_network).setOnClickListener(this.mFinalClickListener);
    }

    void setSubtitle() {
        if (this.mEraseEsim) {
            ((TextView) this.mContentView.findViewById(R.id.reset_network_confirm)).setText(R.string.reset_network_final_desc_esim);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getActivity(), "no_network_reset", UserHandle.myUserId());
        if (RestrictedLockUtilsInternal.hasBaseUserRestriction(getActivity(), "no_network_reset", UserHandle.myUserId())) {
            return layoutInflater.inflate(R.layout.network_reset_disallowed_screen, (ViewGroup) null);
        }
        if (enforcedAdminCheckIfRestrictionEnforced != null) {
            AlertDialog.Builder builderPrepareDialogBuilder = new ActionDisabledByAdminDialogHelper(getActivity()).prepareDialogBuilder("no_network_reset", enforcedAdminCheckIfRestrictionEnforced);
            builderPrepareDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.car.developeroptions.-$$Lambda$ResetNetworkConfirm$nm1_W59PDhfFJ2Z3uMBkkAV-NAc
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    this.f$0.lambda$onCreateView$0$ResetNetworkConfirm(dialogInterface);
                }
            });
            builderPrepareDialogBuilder.show();
            return new View(getContext());
        }
        this.mContentView = layoutInflater.inflate(R.layout.reset_network_confirm, (ViewGroup) null);
        establishFinalConfirmationState();
        setSubtitle();
        return this.mContentView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateView$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateView$0$ResetNetworkConfirm(DialogInterface dialogInterface) {
        getActivity().finish();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mSubId = arguments.getInt("subscription", -1);
            this.mEraseEsim = arguments.getBoolean("erase_esim");
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        EraseEsimAsyncTask eraseEsimAsyncTask = this.mEraseEsimTask;
        if (eraseEsimAsyncTask != null) {
            eraseEsimAsyncTask.cancel(true);
            this.mEraseEsimTask = null;
        }
        super.onDestroy();
    }
}
