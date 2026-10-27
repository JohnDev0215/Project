package com.android.car.settings.bluetooth;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.UserManager;
import android.text.TextUtils;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import com.android.car.ui.AlertDialogBuilder;
import com.android.settingslib.bluetooth.BluetoothDiscoverableTimeoutReceiver;
import com.android.settingslib.bluetooth.LocalBluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothRequestPermissionActivity extends Activity {
    static final int DEFAULT_DISCOVERABLE_TIMEOUT = 120;
    private static final int DISCOVERABLE_TIMEOUT_ONE_HOUR = 3600;
    private static final int DISCOVERABLE_TIMEOUT_TWO_MINUTES = 120;
    static final String EXTRA_BYPASS_CONFIRM_DIALOG = "bypassConfirmDialog";
    private static final Logger LOG = new Logger((Class<?>) BluetoothRequestPermissionActivity.class);
    static final int MAX_DISCOVERABLE_TIMEOUT = 3600;
    static final int REQUEST_DISABLE = 2;
    static final int REQUEST_ENABLE = 1;
    static final int REQUEST_ENABLE_DISCOVERABLE = 3;
    static final int REQUEST_UNKNOWN = 0;
    private CharSequence mAppLabel;
    private AlertDialog mDialog;
    private LocalBluetoothAdapter mLocalBluetoothAdapter;
    private LocalBluetoothManager mLocalBluetoothManager;
    private StateChangeReceiver mReceiver;
    private int mRequest;
    private boolean mBypassConfirmDialog = false;
    private int mTimeout = 120;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addSystemFlags(524288);
        int intent = parseIntent();
        this.mRequest = intent;
        if (intent == 0) {
            finishWithResult(0);
        }
        LocalBluetoothManager localBluetoothManager = LocalBluetoothManager.getInstance(getApplicationContext(), null);
        this.mLocalBluetoothManager = localBluetoothManager;
        if (localBluetoothManager == null) {
            LOG.e("Bluetooth is not supported on this device");
            finishWithResult(0);
        }
        LocalBluetoothAdapter bluetoothAdapter = this.mLocalBluetoothManager.getBluetoothAdapter();
        this.mLocalBluetoothAdapter = bluetoothAdapter;
        int btState = bluetoothAdapter.getState();
        int i = this.mRequest;
        if (i == 1) {
            switch (btState) {
                case 10:
                case 13:
                    AlertDialog alertDialogCreateRequestEnableBluetoothDialog = createRequestEnableBluetoothDialog();
                    this.mDialog = alertDialogCreateRequestEnableBluetoothDialog;
                    alertDialogCreateRequestEnableBluetoothDialog.show();
                    break;
                case 11:
                case 12:
                    proceedAndFinish();
                    break;
                default:
                    LOG.e("Unknown adapter state: " + btState);
                    finishWithResult(0);
                    break;
            }
        }
        if (i == 2) {
            switch (btState) {
                case 10:
                case 13:
                    proceedAndFinish();
                    break;
                case 11:
                case 12:
                    AlertDialog alertDialogCreateRequestDisableBluetoothDialog = createRequestDisableBluetoothDialog();
                    this.mDialog = alertDialogCreateRequestDisableBluetoothDialog;
                    alertDialogCreateRequestDisableBluetoothDialog.show();
                    break;
                default:
                    LOG.e("Unknown adapter state: " + btState);
                    finishWithResult(0);
                    break;
            }
        }
        if (i == 3) {
            switch (btState) {
                case 10:
                case 11:
                case 13:
                    if (isSetupWizardDialogBypass()) {
                        enableBluetoothWithWaitingDialog(null);
                    } else {
                        AlertDialog alertDialogCreateRequestEnableBluetoothDialogWithTimeout = createRequestEnableBluetoothDialogWithTimeout(this.mTimeout);
                        this.mDialog = alertDialogCreateRequestEnableBluetoothDialogWithTimeout;
                        alertDialogCreateRequestEnableBluetoothDialogWithTimeout.show();
                    }
                    break;
                case 12:
                    if (isSetupWizardDialogBypass()) {
                        proceedAndFinish();
                    } else {
                        AlertDialog alertDialogCreateDiscoverableConfirmDialog = createDiscoverableConfirmDialog(this.mTimeout);
                        this.mDialog = alertDialogCreateDiscoverableConfirmDialog;
                        alertDialogCreateDiscoverableConfirmDialog.show();
                    }
                    break;
                default:
                    LOG.e("Unknown adapter state: " + btState);
                    finishWithResult(0);
                    break;
            }
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        StateChangeReceiver stateChangeReceiver = this.mReceiver;
        if (stateChangeReceiver != null) {
            unregisterReceiver(stateChangeReceiver);
        }
    }

    private boolean isSetupWizardDialogBypass() {
        String callerName = getCallingPackage();
        return this.mBypassConfirmDialog && callerName != null && callerName.equals(getSetupWizardPackageName());
    }

    private String getSetupWizardPackageName() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.SETUP_WIZARD");
        List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 1835520);
        if (matches.size() == 1) {
            return matches.get(0).getComponentInfo().packageName;
        }
        LOG.e("There should probably be exactly one setup wizard; found " + matches.size() + ": matches=" + matches);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void proceedAndFinish() {
        if (this.mRequest == 3) {
            finishWithResult(setDiscoverable(this.mTimeout));
        } else {
            finishWithResult(-1);
        }
    }

    private int setDiscoverable(int timeoutSeconds) {
        if (!this.mLocalBluetoothAdapter.setScanMode(23, timeoutSeconds)) {
            return 0;
        }
        long endTime = System.currentTimeMillis() + (((long) timeoutSeconds) * 1000);
        BluetoothUtils.persistDiscoverableEndTimestamp(this, endTime);
        if (timeoutSeconds > 0) {
            BluetoothDiscoverableTimeoutReceiver.setDiscoverableAlarm(this, endTime);
        }
        if (timeoutSeconds >= 1) {
            return timeoutSeconds;
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishWithResult(int result) {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        setResult(result);
        finish();
    }

    private int parseIntent() {
        int request;
        Intent intent = getIntent();
        if (intent == null) {
            return 0;
        }
        String action = intent.getAction();
        byte b = -1;
        int iHashCode = action.hashCode();
        if (iHashCode != -485784851) {
            if (iHashCode != 412748461) {
                if (iHashCode == 1106473630 && action.equals("android.bluetooth.adapter.action.REQUEST_DISABLE")) {
                    b = 1;
                }
            } else if (action.equals("android.bluetooth.adapter.action.REQUEST_DISCOVERABLE")) {
                b = 2;
            }
        } else if (action.equals("android.bluetooth.adapter.action.REQUEST_ENABLE")) {
            b = 0;
        }
        if (b == 0) {
            request = 1;
        } else if (b == 1) {
            request = 2;
        } else if (b == 2) {
            request = 3;
            this.mTimeout = intent.getIntExtra("android.bluetooth.adapter.extra.DISCOVERABLE_DURATION", 120);
            this.mBypassConfirmDialog = intent.getBooleanExtra(EXTRA_BYPASS_CONFIRM_DIALOG, false);
            int i = this.mTimeout;
            if (i < 1 || i > 3600) {
                this.mTimeout = 120;
            }
        } else {
            LOG.e("Error: this activity may be started only with intent android.bluetooth.adapter.action.REQUEST_ENABLE");
            return 0;
        }
        String packageName = getCallingPackage();
        if (TextUtils.isEmpty(packageName)) {
            packageName = intent.getStringExtra("android.intent.extra.PACKAGE_NAME");
        }
        if (!this.mBypassConfirmDialog && !TextUtils.isEmpty(packageName)) {
            try {
                ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(packageName, 0);
                this.mAppLabel = applicationInfo.loadLabel(getPackageManager());
            } catch (PackageManager.NameNotFoundException e) {
                LOG.e("Couldn't find app with package name " + packageName);
                return 0;
            }
        }
        return request;
    }

    private AlertDialog createWaitingDialog() {
        int message = this.mRequest == 2 ? R.string.bluetooth_turning_off : R.string.bluetooth_turning_on;
        return new AlertDialogBuilder(this).setMessage(message).setCancelable(false).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRequestPermissionActivity$Vy4wTHsrt2MCjj-JmIWsx3oII8I
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$createWaitingDialog$0$BluetoothRequestPermissionActivity(dialogInterface);
            }
        }).create();
    }

    public /* synthetic */ void lambda$createWaitingDialog$0$BluetoothRequestPermissionActivity(DialogInterface dialog) {
        finishWithResult(0);
    }

    private AlertDialog createDiscoverableConfirmDialog(int timeoutSeconds) {
        String message;
        if (this.mAppLabel != null) {
            message = getString(R.string.bluetooth_ask_discovery, new Object[]{this.mAppLabel, Integer.valueOf(timeoutSeconds)});
        } else {
            message = getString(R.string.bluetooth_ask_discovery_no_name, new Object[]{Integer.valueOf(timeoutSeconds)});
        }
        return new AlertDialogBuilder(this).setMessage(message).setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRequestPermissionActivity$vjnXOXn7mG23o2nyWw5T9s-E95Y
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createDiscoverableConfirmDialog$1$BluetoothRequestPermissionActivity(dialogInterface, i);
            }
        }).setNegativeButton(R.string.deny, new DialogInterface.OnClickListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRequestPermissionActivity$cXWsSRkyj1XcG8rEmEst3lcfSNw
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createDiscoverableConfirmDialog$2$BluetoothRequestPermissionActivity(dialogInterface, i);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRequestPermissionActivity$YlIlrG55M7msjJmpnp1tcDmP-Zg
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$createDiscoverableConfirmDialog$3$BluetoothRequestPermissionActivity(dialogInterface);
            }
        }).create();
    }

    public /* synthetic */ void lambda$createDiscoverableConfirmDialog$1$BluetoothRequestPermissionActivity(DialogInterface dialog, int which) {
        proceedAndFinish();
    }

    public /* synthetic */ void lambda$createDiscoverableConfirmDialog$2$BluetoothRequestPermissionActivity(DialogInterface dialog, int which) {
        finishWithResult(0);
    }

    public /* synthetic */ void lambda$createDiscoverableConfirmDialog$3$BluetoothRequestPermissionActivity(DialogInterface dialog) {
        finishWithResult(0);
    }

    private AlertDialog createRequestEnableBluetoothDialog() {
        String message;
        if (this.mAppLabel != null) {
            message = getString(R.string.bluetooth_ask_enablement, new Object[]{this.mAppLabel});
        } else {
            message = getString(R.string.bluetooth_ask_enablement_no_name);
        }
        return new AlertDialogBuilder(this).setMessage(message).setPositiveButton(R.string.allow, new $$Lambda$BluetoothRequestPermissionActivity$0OTMqZUDjn7CmA8K9WyaJwpSp0M(this)).setNegativeButton(R.string.deny, new DialogInterface.OnClickListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRequestPermissionActivity$ELq1YTWLB2I7uG192VmXG8loLhw
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createRequestEnableBluetoothDialog$4$BluetoothRequestPermissionActivity(dialogInterface, i);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRequestPermissionActivity$3toYSNOki4OcmoREmMgoemha-Io
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$createRequestEnableBluetoothDialog$5$BluetoothRequestPermissionActivity(dialogInterface);
            }
        }).create();
    }

    public /* synthetic */ void lambda$createRequestEnableBluetoothDialog$4$BluetoothRequestPermissionActivity(DialogInterface dialog, int which) {
        finishWithResult(0);
    }

    public /* synthetic */ void lambda$createRequestEnableBluetoothDialog$5$BluetoothRequestPermissionActivity(DialogInterface dialog) {
        finishWithResult(0);
    }

    private AlertDialog createRequestEnableBluetoothDialogWithTimeout(int timeoutSeconds) {
        String message;
        if (this.mAppLabel != null) {
            message = getString(R.string.bluetooth_ask_enablement_and_discovery, new Object[]{this.mAppLabel, Integer.valueOf(timeoutSeconds)});
        } else {
            message = getString(R.string.bluetooth_ask_enablement_and_discovery_no_name, new Object[]{Integer.valueOf(timeoutSeconds)});
        }
        return new AlertDialogBuilder(this).setMessage(message).setPositiveButton(R.string.allow, new $$Lambda$BluetoothRequestPermissionActivity$0OTMqZUDjn7CmA8K9WyaJwpSp0M(this)).setNegativeButton(R.string.deny, new DialogInterface.OnClickListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRequestPermissionActivity$gT1Qe-koblpXCaaW30mvgvfTFPE
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createRequestEnableBluetoothDialogWithTimeout$6$BluetoothRequestPermissionActivity(dialogInterface, i);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRequestPermissionActivity$XGi5iB2A5Eg7OfQDM_mkjZZ_ebk
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$createRequestEnableBluetoothDialogWithTimeout$7$BluetoothRequestPermissionActivity(dialogInterface);
            }
        }).create();
    }

    public /* synthetic */ void lambda$createRequestEnableBluetoothDialogWithTimeout$6$BluetoothRequestPermissionActivity(DialogInterface dialog, int which) {
        finishWithResult(0);
    }

    public /* synthetic */ void lambda$createRequestEnableBluetoothDialogWithTimeout$7$BluetoothRequestPermissionActivity(DialogInterface dialog) {
        finishWithResult(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConfirmEnableBluetooth(DialogInterface dialog, int which) {
        UserManager userManager = (UserManager) getSystemService(UserManager.class);
        if (userManager.hasUserRestriction("no_bluetooth")) {
            DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(DevicePolicyManager.class);
            Intent intent = dpm.createAdminSupportIntent("no_bluetooth");
            if (intent != null) {
                startActivity(intent);
                return;
            }
            return;
        }
        if (this.mRequest == 1) {
            enableBluetoothWithWaitingDialog(createWaitingDialog());
        } else {
            enableBluetoothWithWaitingDialog(createDiscoverableConfirmDialog(this.mTimeout));
        }
    }

    private void enableBluetoothWithWaitingDialog(AlertDialog dialogToShowOnWait) {
        this.mLocalBluetoothAdapter.enable();
        if (this.mLocalBluetoothAdapter.getState() == 12) {
            proceedAndFinish();
            return;
        }
        StateChangeReceiver stateChangeReceiver = new StateChangeReceiver(12);
        this.mReceiver = stateChangeReceiver;
        registerReceiver(stateChangeReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        if (dialogToShowOnWait != null) {
            this.mDialog = dialogToShowOnWait;
            dialogToShowOnWait.show();
        }
    }

    private AlertDialog createRequestDisableBluetoothDialog() {
        String message;
        if (this.mAppLabel != null) {
            message = getString(R.string.bluetooth_ask_disablement, new Object[]{this.mAppLabel});
        } else {
            message = getString(R.string.bluetooth_ask_disablement_no_name);
        }
        return new AlertDialogBuilder(this).setMessage(message).setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRequestPermissionActivity$Jkwh_6M77Xwr_BNUw3YqeE_qIJA
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.onConfirmDisableBluetooth(dialogInterface, i);
            }
        }).setNegativeButton(R.string.deny, new DialogInterface.OnClickListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRequestPermissionActivity$mfwv4C0KyvVRwZVzUGLk7SLl8pc
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createRequestDisableBluetoothDialog$8$BluetoothRequestPermissionActivity(dialogInterface, i);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRequestPermissionActivity$Lp-xz1vw0mEfky7aAUDz3MszJXA
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$createRequestDisableBluetoothDialog$9$BluetoothRequestPermissionActivity(dialogInterface);
            }
        }).create();
    }

    public /* synthetic */ void lambda$createRequestDisableBluetoothDialog$8$BluetoothRequestPermissionActivity(DialogInterface dialog, int which) {
        finishWithResult(0);
    }

    public /* synthetic */ void lambda$createRequestDisableBluetoothDialog$9$BluetoothRequestPermissionActivity(DialogInterface dialog) {
        finishWithResult(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConfirmDisableBluetooth(DialogInterface dialog, int which) {
        this.mLocalBluetoothAdapter.disable();
        if (this.mLocalBluetoothAdapter.getState() == 10) {
            proceedAndFinish();
            return;
        }
        StateChangeReceiver stateChangeReceiver = new StateChangeReceiver(10);
        this.mReceiver = stateChangeReceiver;
        registerReceiver(stateChangeReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        AlertDialog alertDialogCreateWaitingDialog = createWaitingDialog();
        this.mDialog = alertDialogCreateWaitingDialog;
        alertDialogCreateWaitingDialog.show();
    }

    int getRequestType() {
        return this.mRequest;
    }

    int getTimeout() {
        return this.mTimeout;
    }

    AlertDialog getCurrentDialog() {
        return this.mDialog;
    }

    StateChangeReceiver getCurrentReceiver() {
        return this.mReceiver;
    }

    final class StateChangeReceiver extends BroadcastReceiver {
        private static final long TOGGLE_TIMEOUT_MILLIS = 10000;
        private final int mDesiredState;

        StateChangeReceiver(int desiredState) {
            this.mDesiredState = desiredState;
            BluetoothRequestPermissionActivity.this.getWindow().getDecorView().postDelayed(new Runnable() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRequestPermissionActivity$StateChangeReceiver$-GyNjFeOBhGOdBI6M_c-Uac9Mgg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$new$0$BluetoothRequestPermissionActivity$StateChangeReceiver();
                }
            }, TOGGLE_TIMEOUT_MILLIS);
        }

        public /* synthetic */ void lambda$new$0$BluetoothRequestPermissionActivity$StateChangeReceiver() {
            if (!BluetoothRequestPermissionActivity.this.isFinishing() && !BluetoothRequestPermissionActivity.this.isDestroyed()) {
                BluetoothRequestPermissionActivity.this.finishWithResult(0);
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            int currentState = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            if (this.mDesiredState == currentState) {
                BluetoothRequestPermissionActivity.this.proceedAndFinish();
            }
        }
    }
}
