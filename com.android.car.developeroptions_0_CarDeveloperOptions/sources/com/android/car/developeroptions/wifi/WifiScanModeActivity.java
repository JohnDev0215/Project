package com.android.car.developeroptions.wifi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class WifiScanModeActivity extends FragmentActivity {
    private String mApp;
    private DialogFragment mDialog;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (bundle == null) {
            if (intent != null && intent.getAction().equals("android.net.wifi.action.REQUEST_SCAN_ALWAYS_AVAILABLE")) {
                this.mApp = getCallingPackage();
                try {
                    PackageManager packageManager = getPackageManager();
                    this.mApp = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mApp, 0));
                } catch (PackageManager.NameNotFoundException unused) {
                }
            } else {
                finish();
                return;
            }
        } else {
            this.mApp = bundle.getString("app");
        }
        createDialog();
    }

    private void createDialog() {
        if (this.mDialog == null) {
            AlertDialogFragment alertDialogFragmentNewInstance = AlertDialogFragment.newInstance(this.mApp);
            this.mDialog = alertDialogFragmentNewInstance;
            alertDialogFragmentNewInstance.show(getSupportFragmentManager(), "dialog");
        }
    }

    private void dismissDialog() {
        DialogFragment dialogFragment = this.mDialog;
        if (dialogFragment != null) {
            dialogFragment.dismiss();
            this.mDialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doPositiveClick() {
        ((WifiManager) getApplicationContext().getSystemService(WifiManager.class)).setScanAlwaysAvailable(true);
        setResult(-1);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doNegativeClick() {
        setResult(0);
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("app", this.mApp);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        dismissDialog();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        createDialog();
    }

    public static class AlertDialogFragment extends InstrumentedDialogFragment {
        private final String mApp;

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 543;
        }

        static AlertDialogFragment newInstance(String str) {
            return new AlertDialogFragment(str);
        }

        public AlertDialogFragment(String str) {
            this.mApp = str;
        }

        public AlertDialogFragment() {
            this.mApp = null;
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(getString(R.string.wifi_scan_always_turnon_message, this.mApp));
            builder.setPositiveButton(R.string.wifi_scan_always_confirm_allow, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.wifi.WifiScanModeActivity.AlertDialogFragment.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((WifiScanModeActivity) AlertDialogFragment.this.getActivity()).doPositiveClick();
                }
            });
            builder.setNegativeButton(R.string.wifi_scan_always_confirm_deny, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.wifi.WifiScanModeActivity.AlertDialogFragment.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((WifiScanModeActivity) AlertDialogFragment.this.getActivity()).doNegativeClick();
                }
            });
            return builder.create();
        }

        @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            ((WifiScanModeActivity) getActivity()).doNegativeClick();
        }
    }
}
