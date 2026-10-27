package com.android.car.settings.system;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkPolicyManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.provider.Telephony;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import com.android.car.settings.R;
import com.android.car.settings.common.ErrorDialog;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.ui.toolbar.MenuItem;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ResetNetworkConfirmFragment extends SettingsFragment {
    static final String RESTORE_CARRIERS_URI = "content://telephony/carriers/restore";
    private MenuItem mResetButton;

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.reset_network_confirm_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mResetButton);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mResetButton = new MenuItem.Builder(getContext()).setTitle(R.string.reset_network_confirm_button_text).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.system.-$$Lambda$ResetNetworkConfirmFragment$39AAWe_-Mx-VqWa2rwzPHYd3jWE
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$ResetNetworkConfirmFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$0$ResetNetworkConfirmFragment(MenuItem i) {
        resetNetwork();
    }

    private void resetNetwork() {
        BluetoothAdapter btAdapter;
        if (ActivityManager.isUserAMonkey()) {
            return;
        }
        Context context = requireActivity().getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            connectivityManager.factoryReset();
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager != null) {
            wifiManager.factoryReset();
        }
        BluetoothManager btManager = (BluetoothManager) context.getSystemService("bluetooth");
        if (btManager != null && (btAdapter = btManager.getAdapter()) != null) {
            btAdapter.factoryReset();
        }
        int networkSubscriptionId = getNetworkSubscriptionId();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            telephonyManager.factoryReset(networkSubscriptionId);
        }
        NetworkPolicyManager policyManager = (NetworkPolicyManager) context.getSystemService("netpolicy");
        if (policyManager != null) {
            String subscriberId = telephonyManager.getSubscriberId(networkSubscriptionId);
            policyManager.factoryReset(subscriberId);
        }
        restoreDefaultApn(context, networkSubscriptionId);
        cleanUpSmsRawTable(context);
        if (shouldResetEsim()) {
            new EraseEsimAsyncTask(getContext(), context.getPackageName(), this).execute(new Void[0]);
        } else {
            showCompletionToast(getContext());
        }
    }

    private boolean shouldResetEsim() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        return sharedPreferences.getBoolean(requireContext().getString(R.string.pk_reset_esim), false);
    }

    private int getNetworkSubscriptionId() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String stringId = sharedPreferences.getString(requireContext().getString(R.string.pk_reset_network_subscription), null);
        if (TextUtils.isEmpty(stringId)) {
            return -1;
        }
        return Integer.parseInt(stringId);
    }

    private void restoreDefaultApn(Context context, int subscriptionId) {
        Uri uri = Uri.parse(RESTORE_CARRIERS_URI);
        if (SubscriptionManager.isUsableSubIdValue(subscriptionId)) {
            uri = Uri.withAppendedPath(uri, "subId/" + subscriptionId);
        }
        ContentResolver resolver = context.getContentResolver();
        resolver.delete(uri, null, null);
    }

    private void cleanUpSmsRawTable(Context context) {
        ContentResolver resolver = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(Telephony.Sms.CONTENT_URI, "raw/permanentDelete");
        resolver.delete(uri, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showCompletionToast(Context context) {
    }

    private static class EraseEsimAsyncTask extends AsyncTask<Void, Void, Boolean> {
        private final Context mContext;
        private final Fragment mFragment;
        private final String mPackageName;

        EraseEsimAsyncTask(Context context, String packageName, Fragment parent) {
            this.mContext = context;
            this.mPackageName = packageName;
            this.mFragment = parent;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voids) {
            return Boolean.valueOf(RecoverySystem.wipeEuiccData(this.mContext, this.mPackageName));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean succeeded) {
            if (succeeded.booleanValue()) {
                ResetNetworkConfirmFragment.showCompletionToast(this.mContext);
            } else {
                ErrorDialog.show(this.mFragment, R.string.reset_esim_error_title);
            }
        }
    }
}
