package com.android.permissioncontroller.permission.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class LocationProviderInterceptDialog extends FragmentActivity {
    private static final String LOG_TAG = LocationProviderInterceptDialog.class.getSimpleName();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String packageName = getIntent().getStringExtra("android.intent.extra.PACKAGE_NAME");
        if (packageName == null) {
            Log.i(LOG_TAG, "Missing mandatory argument EXTRA_PACKAGE_NAME");
            finish();
        } else {
            new AlertDialog.Builder(this).setIcon(R.drawable.ic_dialog_alert_material).setTitle(android.R.string.dialog_alert_title).setMessage(getString(R.string.location_warning, new Object[]{Utils.getAppLabel(getPackageInfo(packageName).applicationInfo, this)})).setNegativeButton(R.string.ok, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.location_settings, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.-$$Lambda$LocationProviderInterceptDialog$fIRRGg6_mtFJhkZOICEA8iHg1PI
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreate$0$LocationProviderInterceptDialog(dialogInterface, i);
                }
            }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.permissioncontroller.permission.ui.-$$Lambda$LocationProviderInterceptDialog$LCmoTR4F4K4-z-Edam0kbL1cFRM
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    this.f$0.lambda$onCreate$1$LocationProviderInterceptDialog(dialogInterface);
                }
            }).show();
        }
    }

    public /* synthetic */ void lambda$onCreate$0$LocationProviderInterceptDialog(DialogInterface dialog, int which) {
        startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    public /* synthetic */ void lambda$onCreate$1$LocationProviderInterceptDialog(DialogInterface dialog) {
        finish();
    }

    private PackageInfo getPackageInfo(String packageName) {
        try {
            return getPackageManager().getPackageInfo(packageName, 4096);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i(LOG_TAG, "No package: " + packageName, e);
            finish();
            return null;
        }
    }
}
