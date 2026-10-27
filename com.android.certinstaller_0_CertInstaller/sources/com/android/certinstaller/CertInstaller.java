package com.android.certinstaller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.security.KeyChain;
import android.text.TextUtils;
import android.util.Log;
import android.util.Slog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class CertInstaller extends Activity {
    private CredentialHelper mCredentials;
    private MyAction mNextAction;
    private int mState;
    private final ViewHelper mView = new ViewHelper();

    private interface MyAction extends Serializable {
        void run(CertInstaller certInstaller);
    }

    private CredentialHelper createCredentialHelper(Intent intent) {
        try {
            return new CredentialHelper(intent);
        } catch (Throwable th) {
            Log.w("CertInstaller", "createCredentialHelper", th);
            toastErrorAndFinish(R.string.invalid_cert);
            return new CredentialHelper();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addSystemFlags(524288);
        this.mCredentials = createCredentialHelper(getIntent());
        int i = bundle == null ? 1 : 2;
        this.mState = i;
        if (i == 1) {
            if (!this.mCredentials.containsAnyRawData()) {
                toastErrorAndFinish(R.string.no_cert_to_saved);
                finish();
                return;
            }
            if (installingCaCertificate()) {
                extractPkcs12OrInstall();
                return;
            }
            if (this.mCredentials.hasUserCertificate() && !this.mCredentials.hasPrivateKey()) {
                toastErrorAndFinish(R.string.action_missing_private_key);
                return;
            } else if (this.mCredentials.hasPrivateKey() && !this.mCredentials.hasUserCertificate()) {
                toastErrorAndFinish(R.string.action_missing_user_cert);
                return;
            } else {
                extractPkcs12OrInstall();
                return;
            }
        }
        this.mCredentials.onRestoreStates(bundle);
        this.mNextAction = (MyAction) bundle.getSerializable("na");
    }

    private boolean installingCaCertificate() {
        return (!this.mCredentials.hasCaCerts() || this.mCredentials.hasPrivateKey() || this.mCredentials.hasUserCertificate()) ? false : true;
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.mState == 1) {
            this.mState = 2;
            return;
        }
        MyAction myAction = this.mNextAction;
        if (myAction != null) {
            myAction.run(this);
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mState = 3;
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mCredentials.onSaveStates(bundle);
        MyAction myAction = this.mNextAction;
        if (myAction != null) {
            bundle.putSerializable("na", myAction);
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 1:
                return createNameCertificateDialog();
            case 2:
                return createPkcs12PasswordDialog();
            case 3:
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage(getString(R.string.extracting_pkcs12));
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                return progressDialog;
            case 4:
                return createRedirectCaCertificateDialog();
            case 5:
                return createSelectCertificateUsageDialog();
            case 6:
                return createInvalidCertificateDialog();
            default:
                return null;
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i != 1) {
            Log.w("CertInstaller", "unknown request code: " + i);
            finish();
            return;
        }
        if (i2 != -1) {
            Log.d("CertInstaller", "credential not saved, err: " + i2);
            toastErrorAndFinish(R.string.cert_not_saved);
            return;
        }
        Log.d("CertInstaller", "credential is added: " + this.mCredentials.getName());
        if (this.mCredentials.getCertUsageSelected().equals("wifi")) {
            Toast.makeText(this, R.string.wifi_cert_is_added, 1).show();
        } else {
            Toast.makeText(this, R.string.user_cert_is_added, 1).show();
        }
        setResult(-1);
        finish();
    }

    private void extractPkcs12OrInstall() {
        if (this.mCredentials.hasPkcs12KeyStore()) {
            if (this.mCredentials.hasPassword()) {
                showDialog(2);
                return;
            } else {
                new Pkcs12ExtractAction("").run(this);
                return;
            }
        }
        if (this.mCredentials.calledBySettings()) {
            new InstallOthersAction().run(this);
        } else {
            createRedirectOrSelectUsageDialog();
        }
    }

    private class InstallVpnAndAppsTrustAnchorsTask extends AsyncTask<Void, Void, Boolean> {
        private InstallVpnAndAppsTrustAnchorsTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voidArr) {
            try {
                KeyChain.KeyChainConnection keyChainConnectionBind = KeyChain.bind(CertInstaller.this);
                try {
                    Boolean boolValueOf = Boolean.valueOf(CertInstaller.this.mCredentials.installVpnAndAppsTrustAnchors(CertInstaller.this, keyChainConnectionBind.getService()));
                    if (keyChainConnectionBind != null) {
                        keyChainConnectionBind.close();
                    }
                    return boolValueOf;
                } finally {
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return Boolean.FALSE;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                Toast.makeText(CertInstaller.this.getApplicationContext(), R.string.ca_cert_is_added, 1).show();
                CertInstaller.this.setResult(-1);
            }
            CertInstaller.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installOthers() {
        boolean z = this.mCredentials.hasPrivateKey() && this.mCredentials.hasUserCertificate();
        boolean zHasCaCerts = this.mCredentials.hasCaCerts();
        Log.d("CertInstaller", String.format("Attempting credentials installation, has ca cert? %b, has user cert? %b", Boolean.valueOf(zHasCaCerts), Boolean.valueOf(z)));
        if (!z && !zHasCaCerts) {
            finish();
        } else if (validCertificateSelected()) {
            installCertificateOrShowNameDialog();
        } else {
            showDialog(6);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean validCertificateSelected() {
        /*
            r6 = this;
            com.android.certinstaller.CredentialHelper r0 = r6.mCredentials
            java.lang.String r0 = r0.getCertUsageSelected()
            int r1 = r0.hashCode()
            r2 = 3166(0xc5e, float:4.437E-42)
            r3 = 2
            r4 = 0
            r5 = 1
            if (r1 == r2) goto L30
            r2 = 3599307(0x36ebcb, float:5.043703E-39)
            if (r1 == r2) goto L26
            r2 = 3649301(0x37af15, float:5.11376E-39)
            if (r1 == r2) goto L1c
            goto L3a
        L1c:
            java.lang.String r1 = "wifi"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L3a
            r0 = r3
            goto L3b
        L26:
            java.lang.String r1 = "user"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L3a
            r0 = r5
            goto L3b
        L30:
            java.lang.String r1 = "ca"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L3a
            r0 = r4
            goto L3b
        L3a:
            r0 = -1
        L3b:
            if (r0 == 0) goto L55
            if (r0 == r5) goto L43
            if (r0 == r3) goto L42
            return r4
        L42:
            return r5
        L43:
            com.android.certinstaller.CredentialHelper r0 = r6.mCredentials
            boolean r0 = r0.hasUserCertificate()
            if (r0 == 0) goto L54
            com.android.certinstaller.CredentialHelper r6 = r6.mCredentials
            boolean r6 = r6.hasOnlyVpnAndAppsTrustAnchors()
            if (r6 != 0) goto L54
            r4 = r5
        L54:
            return r4
        L55:
            com.android.certinstaller.CredentialHelper r6 = r6.mCredentials
            boolean r6 = r6.hasOnlyVpnAndAppsTrustAnchors()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.certinstaller.CertInstaller.validCertificateSelected():boolean");
    }

    private void installCertificateOrShowNameDialog() {
        if (!this.mCredentials.hasAnyForSystemInstall()) {
            toastErrorAndFinish(R.string.no_cert_to_saved);
        } else if (this.mCredentials.hasOnlyVpnAndAppsTrustAnchors()) {
            new InstallVpnAndAppsTrustAnchorsTask().execute(new Void[0]);
        } else {
            showDialog(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void extractPkcs12InBackground(final String str) {
        showDialog(3);
        new AsyncTask<Void, Void, Boolean>() { // from class: com.android.certinstaller.CertInstaller.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... voidArr) {
                return Boolean.valueOf(CertInstaller.this.mCredentials.extractPkcs12(str));
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean bool) {
                OnExtractionDoneAction onExtractionDoneAction = new OnExtractionDoneAction(bool.booleanValue());
                if (CertInstaller.this.mState == 3) {
                    CertInstaller.this.mNextAction = onExtractionDoneAction;
                } else {
                    onExtractionDoneAction.run(CertInstaller.this);
                }
            }
        }.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onExtractionDone(boolean z) {
        this.mNextAction = null;
        removeDialog(3);
        if (z) {
            removeDialog(2);
            if (this.mCredentials.calledBySettings()) {
                if (validCertificateSelected()) {
                    installCertificateOrShowNameDialog();
                    return;
                } else {
                    showDialog(6);
                    return;
                }
            }
            createRedirectOrSelectUsageDialog();
            return;
        }
        showDialog(2);
        this.mView.setText(R.id.credential_password, "");
        this.mView.showError(R.string.password_error);
    }

    private void createRedirectOrSelectUsageDialog() {
        if (this.mCredentials.hasOnlyVpnAndAppsTrustAnchors()) {
            showDialog(4);
        } else {
            showDialog(5);
        }
    }

    public CharSequence getCallingAppLabel() {
        String referrer = this.mCredentials.getReferrer();
        if (referrer == null) {
            Log.e("CertInstaller", "Cannot get calling calling AppPackage");
            return null;
        }
        PackageManager packageManager = getPackageManager();
        try {
            return packageManager.getApplicationInfo(referrer, 512).loadLabel(packageManager);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("CertInstaller", "Unable to find info for package: " + referrer);
            return null;
        }
    }

    private Dialog createRedirectCaCertificateDialog() {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this).setTitle(R.string.redirect_ca_certificate_title).setMessage(getString(R.string.redirect_ca_certificate_with_app_info_message, new Object[]{getCallingAppLabel()})).setPositiveButton(R.string.redirect_ca_certificate_close_button, new DialogInterface.OnClickListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$mI7HN3r5wYBDBPbEwdEozhuxHBE
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createRedirectCaCertificateDialog$0$CertInstaller(dialogInterface, i);
            }
        }).create();
        alertDialogCreate.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$NfTGX2Gk1DmBF2_WlP5lrifSWG4
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$createRedirectCaCertificateDialog$1$CertInstaller(dialogInterface);
            }
        });
        return alertDialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createRedirectCaCertificateDialog$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createRedirectCaCertificateDialog$0$CertInstaller(DialogInterface dialogInterface, int i) {
        toastErrorAndFinish(R.string.cert_not_saved);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createRedirectCaCertificateDialog$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createRedirectCaCertificateDialog$1$CertInstaller(DialogInterface dialogInterface) {
        toastErrorAndFinish(R.string.cert_not_saved);
    }

    private Dialog createSelectCertificateUsageDialog() {
        ViewGroup viewGroup = (ViewGroup) View.inflate(this, R.layout.select_certificate_usage_dialog, null);
        this.mView.setView(viewGroup);
        ((RadioGroup) viewGroup.findViewById(R.id.certificate_usage)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$xE-b2aU7MsY3_QStHKqbyqj4rPg
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$createSelectCertificateUsageDialog$2$CertInstaller(radioGroup, i);
            }
        });
        getApplicationContext();
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this).setView(viewGroup).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$ONjdX7WNZkOHCOmtssKIyYTRc00
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createSelectCertificateUsageDialog$3$CertInstaller(dialogInterface, i);
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$ocXgb68jW8nm_tXx1UGUkyuU9J8
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createSelectCertificateUsageDialog$4$CertInstaller(dialogInterface, i);
            }
        }).create();
        alertDialogCreate.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$SQdGcB3uGG9oG_2cNV4zk72kXYQ
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$createSelectCertificateUsageDialog$5$CertInstaller(dialogInterface);
            }
        });
        return alertDialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createSelectCertificateUsageDialog$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createSelectCertificateUsageDialog$2$CertInstaller(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.user_certificate /* 2131034118 */:
                this.mCredentials.setCertUsageSelectedAndUid("user");
                return;
            case R.id.wifi_certificate /* 2131034119 */:
                this.mCredentials.setCertUsageSelectedAndUid("wifi");
                break;
        }
        Slog.i("CertInstaller", "Unknown selection for scope");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createSelectCertificateUsageDialog$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createSelectCertificateUsageDialog$3$CertInstaller(DialogInterface dialogInterface, int i) {
        showDialog(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createSelectCertificateUsageDialog$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createSelectCertificateUsageDialog$4$CertInstaller(DialogInterface dialogInterface, int i) {
        toastErrorAndFinish(R.string.cert_not_saved);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createSelectCertificateUsageDialog$5, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createSelectCertificateUsageDialog$5$CertInstaller(DialogInterface dialogInterface) {
        toastErrorAndFinish(R.string.cert_not_saved);
    }

    private Dialog createInvalidCertificateDialog() {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this).setTitle(R.string.invalid_certificate_title).setMessage(getString(R.string.invalid_certificate_message, new Object[]{getCertificateUsageName()})).setPositiveButton(R.string.invalid_certificate_close_button, new DialogInterface.OnClickListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$cM7iN-X00_xgVbSYkKePd5uX6Qw
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createInvalidCertificateDialog$6$CertInstaller(dialogInterface, i);
            }
        }).create();
        alertDialogCreate.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$ziqKgA8xkLWxq5-Y-zdiktj7mQA
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$createInvalidCertificateDialog$7$CertInstaller(dialogInterface);
            }
        });
        return alertDialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createInvalidCertificateDialog$6, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createInvalidCertificateDialog$6$CertInstaller(DialogInterface dialogInterface, int i) {
        toastErrorAndFinish(R.string.cert_not_saved);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createInvalidCertificateDialog$7, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createInvalidCertificateDialog$7$CertInstaller(DialogInterface dialogInterface) {
        finish();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.lang.String getCertificateUsageName() {
        /*
            r5 = this;
            com.android.certinstaller.CredentialHelper r0 = r5.mCredentials
            java.lang.String r0 = r0.getCertUsageSelected()
            int r1 = r0.hashCode()
            r2 = 3166(0xc5e, float:4.437E-42)
            r3 = 2
            r4 = 1
            if (r1 == r2) goto L2f
            r2 = 3599307(0x36ebcb, float:5.043703E-39)
            if (r1 == r2) goto L25
            r2 = 3649301(0x37af15, float:5.11376E-39)
            if (r1 == r2) goto L1b
            goto L39
        L1b:
            java.lang.String r1 = "wifi"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L39
            r0 = r3
            goto L3a
        L25:
            java.lang.String r1 = "user"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L39
            r0 = r4
            goto L3a
        L2f:
            java.lang.String r1 = "ca"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L39
            r0 = 0
            goto L3a
        L39:
            r0 = -1
        L3a:
            if (r0 == 0) goto L58
            if (r0 == r4) goto L50
            if (r0 == r3) goto L48
            r0 = 2131165194(0x7f07000a, float:1.7944598E38)
            java.lang.String r5 = r5.getString(r0)
            return r5
        L48:
            r0 = 2131165234(0x7f070032, float:1.794468E38)
            java.lang.String r5 = r5.getString(r0)
            return r5
        L50:
            r0 = 2131165230(0x7f07002e, float:1.7944671E38)
            java.lang.String r5 = r5.getString(r0)
            return r5
        L58:
            r0 = 2131165188(0x7f070004, float:1.7944586E38)
            java.lang.String r5 = r5.getString(r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.certinstaller.CertInstaller.getCertificateUsageName():java.lang.String");
    }

    private Dialog createPkcs12PasswordDialog() {
        String string;
        View viewInflate = View.inflate(this, R.layout.password_dialog, null);
        this.mView.setView(viewInflate);
        if (this.mView.getHasEmptyError()) {
            this.mView.showError(R.string.password_empty_error);
            this.mView.setHasEmptyError(false);
        }
        String name = this.mCredentials.getName();
        if (TextUtils.isEmpty(name)) {
            string = getString(R.string.pkcs12_password_dialog_title);
        } else {
            string = getString(R.string.pkcs12_file_password_dialog_title, new Object[]{name});
        }
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this).setView(viewInflate).setTitle(string).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$CPLyJgZRwAGRPdrYoppOEUCpn2k
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createPkcs12PasswordDialog$8$CertInstaller(dialogInterface, i);
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$bws0iP8m4YQVU7BhwZQ5UTmVt1I
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createPkcs12PasswordDialog$9$CertInstaller(dialogInterface, i);
            }
        }).create();
        alertDialogCreate.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$2_QYbLTUGbvfmQP6kp_umJCjiXs
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$createPkcs12PasswordDialog$10$CertInstaller(dialogInterface);
            }
        });
        return alertDialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createPkcs12PasswordDialog$8, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createPkcs12PasswordDialog$8$CertInstaller(DialogInterface dialogInterface, int i) {
        Pkcs12ExtractAction pkcs12ExtractAction = new Pkcs12ExtractAction(this.mView.getText(R.id.credential_password));
        this.mNextAction = pkcs12ExtractAction;
        pkcs12ExtractAction.run(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createPkcs12PasswordDialog$9, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createPkcs12PasswordDialog$9$CertInstaller(DialogInterface dialogInterface, int i) {
        toastErrorAndFinish(R.string.cert_not_saved);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createPkcs12PasswordDialog$10, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createPkcs12PasswordDialog$10$CertInstaller(DialogInterface dialogInterface) {
        toastErrorAndFinish(R.string.cert_not_saved);
    }

    private Dialog createNameCertificateDialog() {
        ViewGroup viewGroup = (ViewGroup) View.inflate(this, R.layout.name_certificate_dialog, null);
        this.mView.setView(viewGroup);
        if (this.mView.getHasEmptyError()) {
            this.mView.showError(R.string.name_empty_error);
            this.mView.setHasEmptyError(false);
        }
        EditText editText = (EditText) viewGroup.findViewById(R.id.certificate_name);
        editText.setText(getDefaultName());
        editText.selectAll();
        final Context applicationContext = getApplicationContext();
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this).setView(viewGroup).setTitle(R.string.name_credential_dialog_title).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$TnrDgl-IUM_880fMZaJaG1VNFTQ
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createNameCertificateDialog$11$CertInstaller(applicationContext, dialogInterface, i);
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$uj6mys2PMENSwO80hNqcVFoqZCw
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createNameCertificateDialog$12$CertInstaller(dialogInterface, i);
            }
        }).create();
        alertDialogCreate.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.certinstaller.-$$Lambda$CertInstaller$e-9wL0GLC09_tgO2FBRh7TZFyCc
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$createNameCertificateDialog$13$CertInstaller(dialogInterface);
            }
        });
        return alertDialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createNameCertificateDialog$11, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createNameCertificateDialog$11$CertInstaller(Context context, DialogInterface dialogInterface, int i) {
        String text = this.mView.getText(R.id.certificate_name);
        if (TextUtils.isEmpty(text)) {
            this.mView.setHasEmptyError(true);
            removeDialog(1);
            showDialog(1);
        } else {
            removeDialog(1);
            this.mCredentials.setName(text);
            installCertificateToKeystore(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createNameCertificateDialog$12, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createNameCertificateDialog$12$CertInstaller(DialogInterface dialogInterface, int i) {
        toastErrorAndFinish(R.string.cert_not_saved);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createNameCertificateDialog$13, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createNameCertificateDialog$13$CertInstaller(DialogInterface dialogInterface) {
        toastErrorAndFinish(R.string.cert_not_saved);
    }

    private void installCertificateToKeystore(Context context) {
        try {
            startActivityForResult(this.mCredentials.createSystemInstallIntent(context), 1);
        } catch (ActivityNotFoundException e) {
            Log.w("CertInstaller", "installCertificateToKeystore(): ", e);
            toastErrorAndFinish(R.string.cert_not_saved);
        }
    }

    private String getDefaultName() {
        String name = this.mCredentials.getName();
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        int iLastIndexOf = name.lastIndexOf(".");
        return iLastIndexOf > 0 ? name.substring(0, iLastIndexOf) : name;
    }

    private void toastErrorAndFinish(int i) {
        Toast.makeText(this, i, 0).show();
        finish();
    }

    private static class Pkcs12ExtractAction implements MyAction {
        private transient boolean hasRun;
        private final String mPassword;

        Pkcs12ExtractAction(String str) {
            this.mPassword = str;
        }

        @Override // com.android.certinstaller.CertInstaller.MyAction
        public void run(CertInstaller certInstaller) {
            if (this.hasRun) {
                return;
            }
            this.hasRun = true;
            certInstaller.extractPkcs12InBackground(this.mPassword);
        }
    }

    private static class InstallOthersAction implements MyAction {
        private InstallOthersAction() {
        }

        @Override // com.android.certinstaller.CertInstaller.MyAction
        public void run(CertInstaller certInstaller) {
            certInstaller.mNextAction = null;
            certInstaller.installOthers();
        }
    }

    private static class OnExtractionDoneAction implements MyAction {
        private final boolean mSuccess;

        OnExtractionDoneAction(boolean z) {
            this.mSuccess = z;
        }

        @Override // com.android.certinstaller.CertInstaller.MyAction
        public void run(CertInstaller certInstaller) {
            certInstaller.onExtractionDone(this.mSuccess);
        }
    }
}
