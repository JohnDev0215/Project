package com.android.packageinstaller;

import android.app.AlertDialog;
import android.app.AppGlobals;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.admin.DevicePolicyManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.android.internal.app.AlertActivity;
import com.android.packageinstaller.PackageUtil;
import com.ts.installer.CarInstallerManager;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class PackageInstallerActivity extends AlertActivity {
    private static final String ALLOW_UNKNOWN_SOURCES_KEY = PackageInstallerActivity.class.getName() + "ALLOW_UNKNOWN_SOURCES_KEY";
    private static final int DLG_ANONYMOUS_SOURCE = 6;
    private static final int DLG_BASE = 0;
    private static final int DLG_EXTERNAL_SOURCE_BLOCKED = 8;
    private static final int DLG_INSTALL_APPS_RESTRICTED_FOR_USER = 9;
    private static final int DLG_INSTALL_ERROR = 4;
    private static final int DLG_NOT_SUPPORTED_ON_WEAR = 7;
    private static final int DLG_OUT_OF_SPACE = 3;
    private static final int DLG_PACKAGE_ERROR = 2;
    private static final int DLG_UNKNOWN_SOURCES_RESTRICTED_FOR_USER = 5;
    static final String EXTRA_CALLING_PACKAGE = "EXTRA_CALLING_PACKAGE";
    static final String EXTRA_ORIGINAL_SOURCE_INFO = "EXTRA_ORIGINAL_SOURCE_INFO";
    static final String PREFS_ALLOWED_SOURCES = "allowed_sources";
    private static final int REQUEST_TRUST_EXTERNAL_SOURCE = 1;
    static final String SCHEME_PACKAGE = "package";
    private static final String TAG = "PackageInstaller";
    private boolean mAllowUnknownSources;
    AppOpsManager mAppOpsManager;
    private PackageUtil.AppSnippet mAppSnippet;
    String mCallingPackage;
    PackageInstaller mInstaller;
    IPackageManager mIpm;
    private Button mOk;
    private String mOriginatingPackage;
    private Uri mOriginatingURI;
    private Uri mPackageURI;
    PackageInfo mPkgInfo;
    PackageManager mPm;
    private Uri mReferrerURI;
    ApplicationInfo mSourceInfo;
    UserManager mUserManager;
    private int mSessionId = -1;
    private int mOriginatingUid = -1;
    private boolean localLOGV = false;
    private ApplicationInfo mAppInfo = null;
    private boolean mEnableOk = false;

    private void startInstallConfirm() {
        View viewToEnable;
        ApplicationInfo applicationInfo = this.mAppInfo;
        if (applicationInfo != null) {
            if ((applicationInfo.flags & 1) != 0) {
                viewToEnable = requireViewById(R.id.install_confirm_question_update_system);
            } else {
                viewToEnable = requireViewById(R.id.install_confirm_question_update);
            }
        } else {
            viewToEnable = requireViewById(R.id.install_confirm_question);
        }
        viewToEnable.setVisibility(0);
        this.mEnableOk = true;
        this.mOk.setEnabled(true);
        this.mOk.setFilterTouchesWhenObscured(true);
    }

    private void showDialogInner(int id) {
        DialogFragment currentDialog = (DialogFragment) getFragmentManager().findFragmentByTag("dialog");
        if (currentDialog != null) {
            currentDialog.dismissAllowingStateLoss();
        }
        DialogFragment newDialog = createDialog(id);
        if (newDialog != null) {
            newDialog.showAllowingStateLoss(getFragmentManager(), "dialog");
        }
    }

    private DialogFragment createDialog(int id) {
        switch (id) {
            case 2:
                return SimpleErrorDialog.newInstance(R.string.Parse_error_dlg_text);
            case 3:
                return OutOfSpaceDialog.newInstance(this.mPm.getApplicationLabel(this.mPkgInfo.applicationInfo));
            case 4:
                return InstallErrorDialog.newInstance(this.mPm.getApplicationLabel(this.mPkgInfo.applicationInfo));
            case 5:
                return SimpleErrorDialog.newInstance(R.string.unknown_apps_user_restriction_dlg_text);
            case 6:
                return AnonymousSourceDialog.newInstance();
            case 7:
                return NotSupportedOnWearDialog.newInstance();
            case 8:
                return ExternalSourcesBlockedDialog.newInstance(this.mOriginatingPackage);
            case 9:
                return SimpleErrorDialog.newInstance(R.string.install_apps_user_restriction_dlg_text);
            default:
                return null;
        }
    }

    public void onActivityResult(int request, int result, Intent data) {
        if (request == 1 && result == -1) {
            this.mAllowUnknownSources = true;
            int appOpCode = AppOpsManager.permissionToOpCode("android.permission.REQUEST_INSTALL_PACKAGES");
            this.mAppOpsManager.noteOpNoThrow(appOpCode, this.mOriginatingUid, this.mOriginatingPackage);
            DialogFragment currentDialog = (DialogFragment) getFragmentManager().findFragmentByTag("dialog");
            if (currentDialog != null) {
                currentDialog.dismissAllowingStateLoss();
            }
            initiateInstall();
            return;
        }
        finish();
    }

    private String getPackageNameForUid(int sourceUid) {
        String[] packagesForUid = this.mPm.getPackagesForUid(sourceUid);
        if (packagesForUid == null) {
            return null;
        }
        if (packagesForUid.length > 1) {
            if (this.mCallingPackage != null) {
                for (String packageName : packagesForUid) {
                    if (packageName.equals(this.mCallingPackage)) {
                        return packageName;
                    }
                }
            }
            Log.i(TAG, "Multiple packages found for source uid " + sourceUid);
        }
        return packagesForUid[0];
    }

    private boolean isInstallRequestFromUnknownSource(Intent intent) {
        ApplicationInfo applicationInfo;
        return this.mCallingPackage == null || !intent.getBooleanExtra("android.intent.extra.NOT_UNKNOWN_SOURCE", false) || (applicationInfo = this.mSourceInfo) == null || (applicationInfo.privateFlags & 8) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initiateInstall() {
        String pkgName = this.mPkgInfo.packageName;
        String[] oldName = this.mPm.canonicalToCurrentPackageNames(new String[]{pkgName});
        if (oldName != null && oldName.length > 0 && oldName[0] != null) {
            pkgName = oldName[0];
            this.mPkgInfo.packageName = pkgName;
            this.mPkgInfo.applicationInfo.packageName = pkgName;
        }
        try {
            ApplicationInfo applicationInfo = this.mPm.getApplicationInfo(pkgName, 8192);
            this.mAppInfo = applicationInfo;
            if ((applicationInfo.flags & 8388608) == 0) {
                this.mAppInfo = null;
            }
        } catch (PackageManager.NameNotFoundException e) {
            this.mAppInfo = null;
        }
        startInstallConfirm();
    }

    void setPmResult(int pmResult) {
        Intent result = new Intent();
        result.putExtra("android.intent.extra.INSTALL_RESULT", pmResult);
        int i = 1;
        if (pmResult == 1) {
            i = -1;
        }
        setResult(i, result);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle icicle) {
        String packageNameForUid;
        Uri packageUri;
        getWindow().addSystemFlags(524288);
        super.onCreate((Bundle) null);
        if (icicle != null) {
            this.mAllowUnknownSources = icicle.getBoolean(ALLOW_UNKNOWN_SOURCES_KEY);
        }
        this.mPm = getPackageManager();
        this.mIpm = AppGlobals.getPackageManager();
        this.mAppOpsManager = (AppOpsManager) getSystemService("appops");
        this.mInstaller = this.mPm.getPackageInstaller();
        this.mUserManager = (UserManager) getSystemService("user");
        Intent intent = getIntent();
        this.mCallingPackage = intent.getStringExtra(EXTRA_CALLING_PACKAGE);
        this.mSourceInfo = (ApplicationInfo) intent.getParcelableExtra(EXTRA_ORIGINAL_SOURCE_INFO);
        int intExtra = intent.getIntExtra("android.intent.extra.ORIGINATING_UID", -1);
        this.mOriginatingUid = intExtra;
        if (intExtra == -1) {
            packageNameForUid = null;
        } else {
            packageNameForUid = getPackageNameForUid(intExtra);
        }
        this.mOriginatingPackage = packageNameForUid;
        if ("android.content.pm.action.CONFIRM_INSTALL".equals(intent.getAction())) {
            int sessionId = intent.getIntExtra("android.content.pm.extra.SESSION_ID", -1);
            PackageInstaller.SessionInfo info = this.mInstaller.getSessionInfo(sessionId);
            if (info == null || !info.sealed || info.resolvedBaseCodePath == null) {
                Log.w(TAG, "Session " + this.mSessionId + " in funky state; ignoring");
                finish();
                return;
            }
            this.mSessionId = sessionId;
            packageUri = Uri.fromFile(new File(info.resolvedBaseCodePath));
            this.mOriginatingURI = null;
            this.mReferrerURI = null;
        } else {
            this.mSessionId = -1;
            packageUri = intent.getData();
            this.mOriginatingURI = (Uri) intent.getParcelableExtra("android.intent.extra.ORIGINATING_URI");
            this.mReferrerURI = (Uri) intent.getParcelableExtra("android.intent.extra.REFERRER");
        }
        if (packageUri == null) {
            Log.w(TAG, "Unspecified source");
            setPmResult(-3);
            finish();
        } else {
            if (DeviceUtils.isWear(this)) {
                showDialogInner(7);
                return;
            }
            boolean wasSetUp = processPackageUri(packageUri);
            if (!wasSetUp) {
                return;
            }
            bindUi();
            checkIfAllowedAndInitiateInstall();
        }
    }

    protected void onResume() {
        super.onResume();
        Button button = this.mOk;
        if (button != null) {
            button.setEnabled(this.mEnableOk);
        }
    }

    protected void onPause() {
        super.onPause();
        Button button = this.mOk;
        if (button != null) {
            button.setEnabled(false);
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ALLOW_UNKNOWN_SOURCES_KEY, this.mAllowUnknownSources);
    }

    private void bindUi() {
        this.mAlert.setIcon(this.mAppSnippet.icon);
        this.mAlert.setTitle(this.mAppSnippet.label);
        this.mAlert.setView(R.layout.install_content_view);
        this.mAlert.setButton(-1, getString(R.string.install), new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$PackageInstallerActivity$Vln2nluA_QJFjJV_na_l6jbopHM
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$bindUi$0$PackageInstallerActivity(dialogInterface, i);
            }
        }, (Message) null);
        this.mAlert.setButton(-2, getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$PackageInstallerActivity$5ZUeBcoeWNB5sojcJYIj3vi7S2w
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$bindUi$1$PackageInstallerActivity(dialogInterface, i);
            }
        }, (Message) null);
        setupAlert();
        Button button = this.mAlert.getButton(-1);
        this.mOk = button;
        button.setEnabled(false);
        if (!this.mOk.isInTouchMode()) {
            this.mAlert.getButton(-2).requestFocus();
        }
    }

    public /* synthetic */ void lambda$bindUi$0$PackageInstallerActivity(DialogInterface ignored, int ignored2) {
        if (this.mOk.isEnabled()) {
            int i = this.mSessionId;
            if (i != -1) {
                this.mInstaller.setPermissionsResult(i, true);
                finish();
            } else {
                startInstall();
            }
        }
    }

    public /* synthetic */ void lambda$bindUi$1$PackageInstallerActivity(DialogInterface ignored, int ignored2) {
        setResult(0);
        int i = this.mSessionId;
        if (i != -1) {
            this.mInstaller.setPermissionsResult(i, false);
        }
        finish();
    }

    private void checkIfAllowedAndInitiateInstall() {
        int installAppsRestrictionSource = this.mUserManager.getUserRestrictionSource("no_install_apps", Process.myUserHandle());
        if ((installAppsRestrictionSource & 1) != 0) {
            showDialogInner(9);
            return;
        }
        if (installAppsRestrictionSource != 0) {
            startActivity(new Intent("android.settings.SHOW_ADMIN_SUPPORT_DETAILS"));
            finish();
            return;
        }
        if (this.mAllowUnknownSources || !isInstallRequestFromUnknownSource(getIntent()) || isInstallAppAllowed()) {
            initiateInstall();
            return;
        }
        int unknownSourcesRestrictionSource = this.mUserManager.getUserRestrictionSource("no_install_unknown_sources", Process.myUserHandle());
        int unknownSourcesGlobalRestrictionSource = this.mUserManager.getUserRestrictionSource("no_install_unknown_sources_globally", Process.myUserHandle());
        int systemRestriction = (unknownSourcesRestrictionSource | unknownSourcesGlobalRestrictionSource) & 1;
        if (systemRestriction != 0) {
            showDialogInner(5);
            return;
        }
        if (unknownSourcesRestrictionSource != 0) {
            startAdminSupportDetailsActivity("no_install_unknown_sources");
        } else if (unknownSourcesGlobalRestrictionSource != 0) {
            startAdminSupportDetailsActivity("no_install_unknown_sources_globally");
        } else {
            handleUnknownSources();
        }
    }

    private boolean isInstallAppAllowed() {
        int installPolicy = CarInstallerManager.getInstallerManager().canInstall(this.mOriginatingPackage);
        return 2 == installPolicy;
    }

    private void startAdminSupportDetailsActivity(String restriction) {
        DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(DevicePolicyManager.class);
        Intent showAdminSupportDetailsIntent = dpm.createAdminSupportIntent(restriction);
        if (showAdminSupportDetailsIntent != null) {
            startActivity(showAdminSupportDetailsIntent);
        }
        finish();
    }

    private void handleUnknownSources() {
        if (this.mOriginatingPackage == null) {
            Log.i(TAG, "No source found for package " + this.mPkgInfo.packageName);
            showDialogInner(6);
            return;
        }
        int appOpCode = AppOpsManager.permissionToOpCode("android.permission.REQUEST_INSTALL_PACKAGES");
        int appOpMode = this.mAppOpsManager.noteOpNoThrow(appOpCode, this.mOriginatingUid, this.mOriginatingPackage);
        if (appOpMode != 0) {
            if (appOpMode != 2) {
                if (appOpMode == 3) {
                    this.mAppOpsManager.setMode(appOpCode, this.mOriginatingUid, this.mOriginatingPackage, 2);
                } else {
                    Log.e(TAG, "Invalid app op mode " + appOpMode + " for OP_REQUEST_INSTALL_PACKAGES found for uid " + this.mOriginatingUid);
                    finish();
                    return;
                }
            }
            showDialogInner(8);
            return;
        }
        initiateInstall();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean processPackageUri(android.net.Uri r10) {
        /*
            r9 = this;
            r9.mPackageURI = r10
            java.lang.String r0 = r10.getScheme()
            int r1 = r0.hashCode()
            r2 = -807062458(0xffffffffcfe53446, float:-7.6908165E9)
            r3 = 0
            r4 = 1
            if (r1 == r2) goto L21
            r2 = 3143036(0x2ff57c, float:4.404332E-39)
            if (r1 == r2) goto L17
        L16:
            goto L2b
        L17:
            java.lang.String r1 = "file"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L16
            r1 = r4
            goto L2c
        L21:
            java.lang.String r1 = "package"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L16
            r1 = r3
            goto L2c
        L2b:
            r1 = -1
        L2c:
            r2 = -2
            r5 = 2
            java.lang.String r6 = "PackageInstaller"
            if (r1 == 0) goto L73
            if (r1 != r4) goto L5c
            java.io.File r1 = new java.io.File
            java.lang.String r7 = r10.getPath()
            r1.<init>(r7)
            r7 = 4096(0x1000, float:5.74E-42)
            android.content.pm.PackageInfo r7 = com.android.packageinstaller.PackageUtil.getPackageInfo(r9, r1, r7)
            r9.mPkgInfo = r7
            if (r7 != 0) goto L53
            java.lang.String r4 = "Parse error when parsing manifest. Discontinuing installation"
            android.util.Log.w(r6, r4)
            r9.showDialogInner(r5)
            r9.setPmResult(r2)
            return r3
        L53:
            android.content.pm.ApplicationInfo r2 = r7.applicationInfo
            com.android.packageinstaller.PackageUtil$AppSnippet r2 = com.android.packageinstaller.PackageUtil.getAppSnippet(r9, r2, r1)
            r9.mAppSnippet = r2
            goto Lc5
        L5c:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unexpected URI scheme "
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L73:
            android.content.pm.PackageManager r1 = r9.mPm     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L82
            java.lang.String r7 = r10.getSchemeSpecificPart()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L82
            r8 = 12288(0x3000, float:1.7219E-41)
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r7, r8)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L82
            r9.mPkgInfo = r1     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L82
            goto L83
        L82:
            r1 = move-exception
        L83:
            android.content.pm.PackageInfo r1 = r9.mPkgInfo
            if (r1 != 0) goto Lab
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "Requested package "
            r1.append(r4)
            java.lang.String r4 = r10.getScheme()
            r1.append(r4)
            java.lang.String r4 = " not available. Discontinuing installation"
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            android.util.Log.w(r6, r1)
            r9.showDialogInner(r5)
            r9.setPmResult(r2)
            return r3
        Lab:
            com.android.packageinstaller.PackageUtil$AppSnippet r2 = new com.android.packageinstaller.PackageUtil$AppSnippet
            android.content.pm.PackageManager r3 = r9.mPm
            android.content.pm.ApplicationInfo r1 = r1.applicationInfo
            java.lang.CharSequence r1 = r3.getApplicationLabel(r1)
            android.content.pm.PackageManager r3 = r9.mPm
            android.content.pm.PackageInfo r5 = r9.mPkgInfo
            android.content.pm.ApplicationInfo r5 = r5.applicationInfo
            android.graphics.drawable.Drawable r3 = r3.getApplicationIcon(r5)
            r2.<init>(r1, r3)
            r9.mAppSnippet = r2
        Lc5:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.packageinstaller.PackageInstallerActivity.processPackageUri(android.net.Uri):boolean");
    }

    public void onBackPressed() {
        int i = this.mSessionId;
        if (i != -1) {
            this.mInstaller.setPermissionsResult(i, false);
        }
        super.onBackPressed();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void startInstall() {
        Intent newIntent = new Intent();
        newIntent.putExtra(PackageUtil.INTENT_ATTR_APPLICATION_INFO, this.mPkgInfo.applicationInfo);
        newIntent.setData(this.mPackageURI);
        newIntent.setClass(this, InstallInstalling.class);
        String installerPackageName = getIntent().getStringExtra("android.intent.extra.INSTALLER_PACKAGE_NAME");
        Uri uri = this.mOriginatingURI;
        if (uri != null) {
            newIntent.putExtra("android.intent.extra.ORIGINATING_URI", uri);
        }
        Uri uri2 = this.mReferrerURI;
        if (uri2 != null) {
            newIntent.putExtra("android.intent.extra.REFERRER", uri2);
        }
        int i = this.mOriginatingUid;
        if (i != -1) {
            newIntent.putExtra("android.intent.extra.ORIGINATING_UID", i);
        }
        if (installerPackageName != null) {
            newIntent.putExtra("android.intent.extra.INSTALLER_PACKAGE_NAME", installerPackageName);
        } else {
            newIntent.putExtra("android.intent.extra.INSTALLER_PACKAGE_NAME", this.mOriginatingPackage);
        }
        if (getIntent().getBooleanExtra("android.intent.extra.RETURN_RESULT", false)) {
            newIntent.putExtra("android.intent.extra.RETURN_RESULT", true);
        }
        newIntent.addFlags(33554432);
        if (this.localLOGV) {
            Log.i(TAG, "downloaded app uri=" + this.mPackageURI);
        }
        startActivity(newIntent);
        finish();
    }

    public static class SimpleErrorDialog extends DialogFragment {
        private static final String MESSAGE_KEY = SimpleErrorDialog.class.getName() + "MESSAGE_KEY";

        static SimpleErrorDialog newInstance(int message) {
            SimpleErrorDialog dialog = new SimpleErrorDialog();
            Bundle args = new Bundle();
            args.putInt(MESSAGE_KEY, message);
            dialog.setArguments(args);
            return dialog;
        }

        @Override // android.app.DialogFragment
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity()).setMessage(getArguments().getInt(MESSAGE_KEY)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$PackageInstallerActivity$SimpleErrorDialog$CjGDh-fPYU-Pu9mKlkOS2BcORqU
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$0$PackageInstallerActivity$SimpleErrorDialog(dialogInterface, i);
                }
            }).create();
        }

        public /* synthetic */ void lambda$onCreateDialog$0$PackageInstallerActivity$SimpleErrorDialog(DialogInterface dialog, int which) {
            getActivity().finish();
        }
    }

    public static class AnonymousSourceDialog extends DialogFragment {
        static AnonymousSourceDialog newInstance() {
            return new AnonymousSourceDialog();
        }

        @Override // android.app.DialogFragment
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity()).setMessage(R.string.anonymous_source_warning).setPositiveButton(R.string.anonymous_source_continue, new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$PackageInstallerActivity$AnonymousSourceDialog$j7ZkjYfuLCxI-yvPw_BH4gKwnyQ
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$0$PackageInstallerActivity$AnonymousSourceDialog(dialogInterface, i);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$PackageInstallerActivity$AnonymousSourceDialog$8kZWiPYiC_m6DQoz2Y8AkkrIOm0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$1$PackageInstallerActivity$AnonymousSourceDialog(dialogInterface, i);
                }
            }).create();
        }

        public /* synthetic */ void lambda$onCreateDialog$0$PackageInstallerActivity$AnonymousSourceDialog(DialogInterface dialog, int which) {
            PackageInstallerActivity activity = (PackageInstallerActivity) getActivity();
            activity.mAllowUnknownSources = true;
            activity.initiateInstall();
        }

        public /* synthetic */ void lambda$onCreateDialog$1$PackageInstallerActivity$AnonymousSourceDialog(DialogInterface dialog, int which) {
            getActivity().finish();
        }

        @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            getActivity().finish();
        }
    }

    public static class NotSupportedOnWearDialog extends SimpleErrorDialog {
        static SimpleErrorDialog newInstance() {
            return SimpleErrorDialog.newInstance(R.string.wear_not_allowed_dlg_text);
        }

        @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            getActivity().setResult(-1);
            getActivity().finish();
        }
    }

    public static class OutOfSpaceDialog extends AppErrorDialog {
        static AppErrorDialog newInstance(CharSequence applicationLabel) {
            OutOfSpaceDialog dialog = new OutOfSpaceDialog();
            dialog.setArgument(applicationLabel);
            return dialog;
        }

        @Override // com.android.packageinstaller.PackageInstallerActivity.AppErrorDialog
        protected Dialog createDialog(CharSequence argument) {
            String dlgText = getString(R.string.out_of_space_dlg_text, new Object[]{argument});
            return new AlertDialog.Builder(getActivity()).setMessage(dlgText).setPositiveButton(R.string.manage_applications, new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$PackageInstallerActivity$OutOfSpaceDialog$1uGsoLrZEkf3PtFRxGm1HRlY75M
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$createDialog$0$PackageInstallerActivity$OutOfSpaceDialog(dialogInterface, i);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$PackageInstallerActivity$OutOfSpaceDialog$y1myQ4PNdkwtdc9tjmUKChqA78g
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$createDialog$1$PackageInstallerActivity$OutOfSpaceDialog(dialogInterface, i);
                }
            }).create();
        }

        public /* synthetic */ void lambda$createDialog$0$PackageInstallerActivity$OutOfSpaceDialog(DialogInterface dialog, int which) {
            Intent intent = new Intent("android.intent.action.MANAGE_PACKAGE_STORAGE");
            intent.setFlags(268435456);
            startActivity(intent);
            getActivity().finish();
        }

        public /* synthetic */ void lambda$createDialog$1$PackageInstallerActivity$OutOfSpaceDialog(DialogInterface dialog, int which) {
            getActivity().finish();
        }
    }

    public static class InstallErrorDialog extends AppErrorDialog {
        static AppErrorDialog newInstance(CharSequence applicationLabel) {
            InstallErrorDialog dialog = new InstallErrorDialog();
            dialog.setArgument(applicationLabel);
            return dialog;
        }

        @Override // com.android.packageinstaller.PackageInstallerActivity.AppErrorDialog
        protected Dialog createDialog(CharSequence argument) {
            return new AlertDialog.Builder(getActivity()).setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$PackageInstallerActivity$InstallErrorDialog$o_75ob8nQvqldslkDnXYGzX9T6E
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$createDialog$0$PackageInstallerActivity$InstallErrorDialog(dialogInterface, i);
                }
            }).setMessage(getString(R.string.install_failed_msg, new Object[]{argument})).create();
        }

        public /* synthetic */ void lambda$createDialog$0$PackageInstallerActivity$InstallErrorDialog(DialogInterface dialog, int which) {
            getActivity().finish();
        }
    }

    public static class ExternalSourcesBlockedDialog extends AppErrorDialog {
        static AppErrorDialog newInstance(String originationPkg) {
            ExternalSourcesBlockedDialog dialog = new ExternalSourcesBlockedDialog();
            dialog.setArgument(originationPkg);
            return dialog;
        }

        @Override // com.android.packageinstaller.PackageInstallerActivity.AppErrorDialog
        protected Dialog createDialog(final CharSequence argument) {
            try {
                PackageManager pm = getActivity().getPackageManager();
                ApplicationInfo sourceInfo = pm.getApplicationInfo(argument.toString(), 0);
                return new AlertDialog.Builder(getActivity()).setTitle(pm.getApplicationLabel(sourceInfo)).setIcon(pm.getApplicationIcon(sourceInfo)).setMessage(R.string.untrusted_external_source_warning).setPositiveButton(R.string.external_sources_settings, new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$PackageInstallerActivity$ExternalSourcesBlockedDialog$A5PlzBSROq1mgW2jWjKzk3yMh4U
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        this.f$0.lambda$createDialog$0$PackageInstallerActivity$ExternalSourcesBlockedDialog(argument, dialogInterface, i);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$PackageInstallerActivity$ExternalSourcesBlockedDialog$L2lERdinc3mjPuJONSQQSBxnEFQ
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        this.f$0.lambda$createDialog$1$PackageInstallerActivity$ExternalSourcesBlockedDialog(dialogInterface, i);
                    }
                }).create();
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(PackageInstallerActivity.TAG, "Did not find app info for " + ((Object) argument));
                getActivity().finish();
                return null;
            }
        }

        public /* synthetic */ void lambda$createDialog$0$PackageInstallerActivity$ExternalSourcesBlockedDialog(CharSequence argument, DialogInterface dialog, int which) {
            Intent settingsIntent = new Intent();
            settingsIntent.setAction("android.settings.MANAGE_UNKNOWN_APP_SOURCES");
            Uri packageUri = Uri.parse("package:" + ((Object) argument));
            settingsIntent.setData(packageUri);
            try {
                getActivity().startActivityForResult(settingsIntent, 1);
            } catch (ActivityNotFoundException e) {
                Log.e(PackageInstallerActivity.TAG, "Settings activity not found for action: android.settings.MANAGE_UNKNOWN_APP_SOURCES");
            }
        }

        public /* synthetic */ void lambda$createDialog$1$PackageInstallerActivity$ExternalSourcesBlockedDialog(DialogInterface dialog, int which) {
            getActivity().finish();
        }
    }

    public static abstract class AppErrorDialog extends DialogFragment {
        private static final String ARGUMENT_KEY = AppErrorDialog.class.getName() + "ARGUMENT_KEY";

        protected abstract Dialog createDialog(CharSequence charSequence);

        protected void setArgument(CharSequence argument) {
            Bundle args = new Bundle();
            args.putCharSequence(ARGUMENT_KEY, argument);
            setArguments(args);
        }

        @Override // android.app.DialogFragment
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return createDialog(getArguments().getString(ARGUMENT_KEY));
        }

        @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            getActivity().finish();
        }
    }
}
