package com.android.car.settings.wifi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.Logger;

/* JADX INFO: loaded from: classes5.dex */
public class WifiRequestToggleActivity extends FragmentActivity {
    private static final Logger LOG = new Logger((Class<?>) WifiRequestToggleActivity.class);
    private static final int STATE_DISABLE = 3;
    private static final int STATE_DISABLING = 4;
    private static final int STATE_ENABLE = 1;
    private static final int STATE_ENABLING = 2;
    private static final int STATE_UNKNOWN = -1;
    private static final long TOGGLE_TIMEOUT_MILLIS = 10000;
    private CharSequence mAppLabel;
    private CarWifiManager mCarWifiManager;
    private ConfirmationDialogFragment mDialog;
    private final StateChangeReceiver mReceiver = new StateChangeReceiver();
    private final Runnable mTimeoutCommand = new Runnable() { // from class: com.android.car.settings.wifi.-$$Lambda$WifiRequestToggleActivity$K_M8vMiLd58CLlY71wpITUWbeKE
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$WifiRequestToggleActivity();
        }
    };
    private int mAction = -1;
    private int mState = -1;
    final ConfirmationDialogFragment.ConfirmListener mConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.wifi.-$$Lambda$WifiRequestToggleActivity$LQQ3zZkU1sY5L5K6eQXwcAsKJDQ
        @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
        public final void onConfirm(Bundle bundle) {
            this.f$0.lambda$new$1$WifiRequestToggleActivity(bundle);
        }
    };
    final ConfirmationDialogFragment.RejectListener mRejectListener = new ConfirmationDialogFragment.RejectListener() { // from class: com.android.car.settings.wifi.-$$Lambda$WifiRequestToggleActivity$JWG21Zd37A17jQz6JUUo07nifBU
        @Override // com.android.car.settings.common.ConfirmationDialogFragment.RejectListener
        public final void onReject(Bundle bundle) {
            this.f$0.lambda$new$2$WifiRequestToggleActivity(bundle);
        }
    };
    final ConfirmationDialogFragment.DismissListener mDismissListener = new ConfirmationDialogFragment.DismissListener() { // from class: com.android.car.settings.wifi.-$$Lambda$WifiRequestToggleActivity$IHgfD6o0argBpl3idzICZGO92aE
        @Override // com.android.car.settings.common.ConfirmationDialogFragment.DismissListener
        public final void onDismiss(Bundle bundle) {
            this.f$0.lambda$new$3$WifiRequestToggleActivity(bundle);
        }
    };

    public /* synthetic */ void lambda$new$0$WifiRequestToggleActivity() {
        if (!isFinishing() && !isDestroyed()) {
            finish();
        }
    }

    public /* synthetic */ void lambda$new$1$WifiRequestToggleActivity(Bundle arguments) {
        int i = this.mState;
        if (i == 1) {
            this.mCarWifiManager.setWifiEnabled(true);
            this.mState = 2;
            updateUi();
            scheduleToggleTimeout();
        } else if (i == 3) {
            this.mCarWifiManager.setWifiEnabled(false);
            this.mState = 4;
            updateUi();
            scheduleToggleTimeout();
        }
        setResult(-1);
    }

    public /* synthetic */ void lambda$new$2$WifiRequestToggleActivity(Bundle arguments) {
        finish();
    }

    public /* synthetic */ void lambda$new$3$WifiRequestToggleActivity(Bundle arguments) {
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCarWifiManager = new CarWifiManager(getApplicationContext());
        setResult(0);
        String packageName = getIntent().getStringExtra("android.intent.extra.PACKAGE_NAME");
        if (!TextUtils.isEmpty(packageName)) {
            try {
                ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(packageName, 0);
                this.mAppLabel = applicationInfo.loadSafeLabel(getPackageManager());
                int iExtractActionState = extractActionState();
                this.mAction = iExtractActionState;
                if (iExtractActionState == -1) {
                    finish();
                    return;
                }
                return;
            } catch (PackageManager.NameNotFoundException e) {
                LOG.e("Couldn't find app with package name " + packageName);
                finish();
                return;
            }
        }
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mReceiver.register();
        int wifiState = this.mCarWifiManager.getWifiState();
        int i = this.mAction;
        if (i != 1) {
            if (i == 3) {
                if (wifiState == 0) {
                    this.mState = 4;
                    scheduleToggleTimeout();
                } else {
                    if (wifiState == 1) {
                        setResult(-1);
                        finish();
                        return;
                    }
                    this.mState = i;
                }
            }
        } else if (wifiState == 2) {
            this.mState = 2;
            scheduleToggleTimeout();
        } else {
            if (wifiState == 3) {
                setResult(-1);
                finish();
                return;
            }
            this.mState = i;
        }
        updateUi();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mReceiver.unregister();
        unscheduleToggleTimeout();
        ConfirmationDialogFragment confirmationDialogFragment = this.mDialog;
        if (confirmationDialogFragment != null) {
            confirmationDialogFragment.dismiss();
        }
    }

    private void updateUi() {
        ConfirmationDialogFragment confirmationDialogFragment = this.mDialog;
        if (confirmationDialogFragment != null) {
            confirmationDialogFragment.dismiss();
        }
        ConfirmationDialogFragment.Builder builder = new ConfirmationDialogFragment.Builder(this).setDismissListener(this.mDismissListener);
        int i = this.mState;
        if (i == 1) {
            builder.setPositiveButton(R.string.allow, this.mConfirmListener);
            builder.setNegativeButton(R.string.deny, this.mRejectListener);
            builder.setMessage(getString(R.string.wifi_ask_enable, new Object[]{this.mAppLabel}));
        } else if (i == 2) {
            builder.setMessage(getString(R.string.wifi_starting, new Object[]{this.mAppLabel}));
        } else if (i == 3) {
            builder.setPositiveButton(R.string.allow, this.mConfirmListener);
            builder.setNegativeButton(R.string.deny, this.mRejectListener);
            builder.setMessage(getString(R.string.wifi_ask_disable, new Object[]{this.mAppLabel}));
        } else if (i == 4) {
            builder.setMessage(getString(R.string.wifi_stopping, new Object[]{this.mAppLabel}));
        }
        ConfirmationDialogFragment confirmationDialogFragmentBuild = builder.build();
        this.mDialog = confirmationDialogFragmentBuild;
        confirmationDialogFragmentBuild.show(getSupportFragmentManager(), ConfirmationDialogFragment.TAG);
    }

    private void scheduleToggleTimeout() {
        getWindow().getDecorView().postDelayed(this.mTimeoutCommand, TOGGLE_TIMEOUT_MILLIS);
    }

    private void unscheduleToggleTimeout() {
        getWindow().getDecorView().removeCallbacks(this.mTimeoutCommand);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int extractActionState() {
        /*
            r5 = this;
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r0 = r0.getAction()
            int r1 = r0.hashCode()
            r2 = -2035256254(0xffffffff86b07442, float:-6.637467E-35)
            r3 = -1
            r4 = 1
            if (r1 == r2) goto L23
            r2 = 317500393(0x12ecabe9, float:1.4936073E-27)
            if (r1 == r2) goto L19
        L18:
            goto L2d
        L19:
            java.lang.String r1 = "android.net.wifi.action.REQUEST_DISABLE"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L18
            r1 = r4
            goto L2e
        L23:
            java.lang.String r1 = "android.net.wifi.action.REQUEST_ENABLE"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L18
            r1 = 0
            goto L2e
        L2d:
            r1 = r3
        L2e:
            if (r1 == 0) goto L35
            if (r1 == r4) goto L33
            return r3
        L33:
            r1 = 3
            return r1
        L35:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.settings.wifi.WifiRequestToggleActivity.extractActionState():int");
    }

    private final class StateChangeReceiver extends BroadcastReceiver {
        private final IntentFilter mFilter;

        private StateChangeReceiver() {
            this.mFilter = new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED");
        }

        public void register() {
            WifiRequestToggleActivity.this.registerReceiver(this, this.mFilter);
        }

        public void unregister() {
            WifiRequestToggleActivity.this.unregisterReceiver(this);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Activity activity = WifiRequestToggleActivity.this;
            if (!activity.isFinishing() && !activity.isDestroyed()) {
                int currentState = WifiRequestToggleActivity.this.mCarWifiManager.getWifiState();
                if (currentState == 1 || currentState == 3) {
                    if (WifiRequestToggleActivity.this.mState == 2 || WifiRequestToggleActivity.this.mState == 4) {
                        activity.setResult(-1);
                        WifiRequestToggleActivity.this.finish();
                    }
                }
            }
        }
    }
}
