package com.android.packageinstaller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import com.android.internal.app.AlertActivity;
import com.android.packageinstaller.PackageUtil;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class InstallFailed extends AlertActivity {
    private static final String LOG_TAG = InstallFailed.class.getSimpleName();
    private CharSequence mLabel;

    private void setExplanationFromErrorCode(int statusCode) {
        View viewToEnable;
        Log.d(LOG_TAG, "Installation status code: " + statusCode);
        if (statusCode == 2) {
            viewToEnable = requireViewById(R.id.install_failed_blocked);
        } else if (statusCode == 7) {
            viewToEnable = requireViewById(R.id.install_failed_incompatible);
        } else if (statusCode == 4) {
            viewToEnable = requireViewById(R.id.install_failed_invalid_apk);
        } else if (statusCode == 5) {
            viewToEnable = requireViewById(R.id.install_failed_conflict);
        } else {
            viewToEnable = requireViewById(R.id.install_failed);
        }
        viewToEnable.setVisibility(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle savedInstanceState) {
        PackageUtil.AppSnippet as;
        super.onCreate(savedInstanceState);
        int statusCode = getIntent().getIntExtra("android.content.pm.extra.STATUS", 1);
        if (getIntent().getBooleanExtra("android.intent.extra.RETURN_RESULT", false)) {
            int legacyStatus = getIntent().getIntExtra("android.content.pm.extra.LEGACY_STATUS", -110);
            Intent result = new Intent();
            result.putExtra("android.intent.extra.INSTALL_RESULT", legacyStatus);
            setResult(1, result);
            finish();
            return;
        }
        Intent intent = getIntent();
        ApplicationInfo appInfo = (ApplicationInfo) intent.getParcelableExtra(PackageUtil.INTENT_ATTR_APPLICATION_INFO);
        Uri packageURI = intent.getData();
        PackageManager pm = getPackageManager();
        if ("package".equals(packageURI.getScheme())) {
            as = new PackageUtil.AppSnippet(pm.getApplicationLabel(appInfo), pm.getApplicationIcon(appInfo));
        } else {
            File sourceFile = new File(packageURI.getPath());
            as = PackageUtil.getAppSnippet(this, appInfo, sourceFile);
        }
        this.mLabel = as.label;
        this.mAlert.setIcon(as.icon);
        this.mAlert.setTitle(as.label);
        this.mAlert.setView(R.layout.install_content_view);
        this.mAlert.setButton(-1, getString(R.string.done), new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$InstallFailed$z0k0kenEFeFyyJkzlE55AnL0raU
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreate$0$InstallFailed(dialogInterface, i);
            }
        }, (Message) null);
        setupAlert();
        if (statusCode == 6) {
            new OutOfSpaceDialog().show(getFragmentManager(), "outofspace");
        }
        setExplanationFromErrorCode(statusCode);
    }

    public /* synthetic */ void lambda$onCreate$0$InstallFailed(DialogInterface ignored, int ignored2) {
        finish();
    }

    public static class OutOfSpaceDialog extends DialogFragment {
        private InstallFailed mActivity;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.app.DialogFragment, android.app.Fragment
        public void onAttach(Context context) {
            super.onAttach(context);
            this.mActivity = (InstallFailed) context;
        }

        @Override // android.app.DialogFragment
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(this.mActivity).setTitle(R.string.out_of_space_dlg_title).setMessage(getString(R.string.out_of_space_dlg_text, new Object[]{this.mActivity.mLabel})).setPositiveButton(R.string.manage_applications, new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$InstallFailed$OutOfSpaceDialog$hsig_VFpVwRJT9IHP-ssU4vs4R0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$0$InstallFailed$OutOfSpaceDialog(dialogInterface, i);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$InstallFailed$OutOfSpaceDialog$lKM9kdrRWfY6VG1ieZS-pYyhkf0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$1$InstallFailed$OutOfSpaceDialog(dialogInterface, i);
                }
            }).create();
        }

        public /* synthetic */ void lambda$onCreateDialog$0$InstallFailed$OutOfSpaceDialog(DialogInterface dialog, int which) {
            Intent intent = new Intent("android.intent.action.MANAGE_PACKAGE_STORAGE");
            startActivity(intent);
            this.mActivity.finish();
        }

        public /* synthetic */ void lambda$onCreateDialog$1$InstallFailed$OutOfSpaceDialog(DialogInterface dialog, int which) {
            this.mActivity.finish();
        }

        @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            super.onCancel(dialog);
            this.mActivity.finish();
        }
    }
}
