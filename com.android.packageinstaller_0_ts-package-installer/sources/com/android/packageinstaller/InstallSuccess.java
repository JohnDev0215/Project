package com.android.packageinstaller;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.android.internal.app.AlertActivity;
import com.android.packageinstaller.PackageUtil;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class InstallSuccess extends AlertActivity {
    private static final String LOG_TAG = InstallSuccess.class.getSimpleName();

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle savedInstanceState) {
        PackageUtil.AppSnippet as;
        List<ResolveInfo> list;
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("android.intent.extra.RETURN_RESULT", false)) {
            Intent result = new Intent();
            result.putExtra("android.intent.extra.INSTALL_RESULT", 1);
            setResult(-1, result);
            finish();
            return;
        }
        Intent intent = getIntent();
        final ApplicationInfo appInfo = (ApplicationInfo) intent.getParcelableExtra(PackageUtil.INTENT_ATTR_APPLICATION_INFO);
        Uri packageURI = intent.getData();
        PackageManager pm = getPackageManager();
        if ("package".equals(packageURI.getScheme())) {
            as = new PackageUtil.AppSnippet(pm.getApplicationLabel(appInfo), pm.getApplicationIcon(appInfo));
        } else {
            File sourceFile = new File(packageURI.getPath());
            as = PackageUtil.getAppSnippet(this, appInfo, sourceFile);
        }
        this.mAlert.setIcon(as.icon);
        this.mAlert.setTitle(as.label);
        this.mAlert.setView(R.layout.install_content_view);
        this.mAlert.setButton(-1, getString(R.string.launch), (DialogInterface.OnClickListener) null, (Message) null);
        this.mAlert.setButton(-2, getString(R.string.done), new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$InstallSuccess$wg_zwfwMas82DCBZSGSiaINwWxk
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreate$0$InstallSuccess(appInfo, dialogInterface, i);
            }
        }, (Message) null);
        setupAlert();
        requireViewById(R.id.install_success).setVisibility(0);
        final Intent launchIntent = getPackageManager().getLaunchIntentForPackage(appInfo.packageName);
        boolean enabled = false;
        if (launchIntent != null && (list = getPackageManager().queryIntentActivities(launchIntent, 0)) != null && list.size() > 0) {
            enabled = true;
        }
        Button launchButton = this.mAlert.getButton(-1);
        if (enabled) {
            launchButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$InstallSuccess$2vWvr9bvgN8Tfp2L3TxaAphjvy8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCreate$1$InstallSuccess(launchIntent, view);
                }
            });
        } else {
            launchButton.setEnabled(false);
        }
    }

    public /* synthetic */ void lambda$onCreate$0$InstallSuccess(ApplicationInfo appInfo, DialogInterface ignored, int ignored2) {
        if (appInfo.packageName != null) {
            Log.i(LOG_TAG, "Finished installing " + appInfo.packageName);
        }
        finish();
    }

    public /* synthetic */ void lambda$onCreate$1$InstallSuccess(Intent launchIntent, View view) {
        try {
            startActivity(launchIntent);
        } catch (ActivityNotFoundException | SecurityException e) {
            Log.e(LOG_TAG, "Could not start activity", e);
        }
        finish();
    }
}
