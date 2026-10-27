package com.android.car.developeroptions;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActionBar;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.sysprop.VoldProperties;
import android.telephony.euicc.EuiccManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.enterprise.ActionDisabledByAdminDialogHelper;
import com.android.car.developeroptions.password.ChooseLockSettingsHelper;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.GlifLayout;

/* JADX INFO: loaded from: classes.dex */
public class MasterClear extends InstrumentedFragment implements ViewTreeObserver.OnGlobalLayoutListener {
    static final int CREDENTIAL_CONFIRM_REQUEST = 56;
    static final int KEYGUARD_REQUEST = 55;
    private View mContentView;
    CheckBox mEsimStorage;
    View mEsimStorageContainer;
    CheckBox mExternalStorage;
    private View mExternalStorageContainer;
    FooterButton mInitiateButton;
    protected final View.OnClickListener mInitiateListener = new View.OnClickListener() { // from class: com.android.car.developeroptions.MasterClear.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Context context = view.getContext();
            if (!Utils.isDemoUser(context)) {
                if (MasterClear.this.runKeyguardConfirmation(MasterClear.KEYGUARD_REQUEST)) {
                    return;
                }
                Intent accountConfirmationIntent = MasterClear.this.getAccountConfirmationIntent();
                if (accountConfirmationIntent != null) {
                    MasterClear.this.showAccountCredentialConfirmation(accountConfirmationIntent);
                    return;
                } else {
                    MasterClear.this.showFinalConfirmation();
                    return;
                }
            }
            ComponentName deviceOwnerComponent = Utils.getDeviceOwnerComponent(context);
            if (deviceOwnerComponent != null) {
                context.startActivity(new Intent().setPackage(deviceOwnerComponent.getPackageName()).setAction("android.intent.action.FACTORY_RESET"));
            }
        }
    };
    ScrollView mScrollView;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 66;
    }

    boolean isValidRequestCode(int i) {
        return i == KEYGUARD_REQUEST || i == CREDENTIAL_CONFIRM_REQUEST;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        this.mInitiateButton.setEnabled(hasReachedBottom(this.mScrollView));
    }

    private void setUpActionBarAndTitle() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Log.e("MasterClear", "No activity attached, skipping setUpActionBarAndTitle");
            return;
        }
        ActionBar actionBar = activity.getActionBar();
        if (actionBar == null) {
            Log.e("MasterClear", "No actionbar, skipping setUpActionBarAndTitle");
        } else {
            actionBar.hide();
            activity.getWindow().setStatusBarColor(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean runKeyguardConfirmation(int i) {
        return new ChooseLockSettingsHelper(getActivity(), this).launchConfirmationActivity(i, getActivity().getResources().getText(R.string.master_clear_short_title));
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        onActivityResultInternal(i, i2, intent);
    }

    void onActivityResultInternal(int i, int i2, Intent intent) {
        Intent accountConfirmationIntent;
        if (isValidRequestCode(i)) {
            if (i2 != -1) {
                establishInitialState();
            } else if (CREDENTIAL_CONFIRM_REQUEST != i && (accountConfirmationIntent = getAccountConfirmationIntent()) != null) {
                showAccountCredentialConfirmation(accountConfirmationIntent);
            } else {
                showFinalConfirmation();
            }
        }
    }

    void showFinalConfirmation() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("erase_sd", this.mExternalStorage.isChecked());
        bundle.putBoolean("erase_esim", this.mEsimStorageContainer.getVisibility() == 0 && this.mEsimStorage.isChecked());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
        subSettingLauncher.setDestination(MasterClearConfirm.class.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleRes(R.string.master_clear_confirm_title);
        subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
        subSettingLauncher.launch();
    }

    void showAccountCredentialConfirmation(Intent intent) {
        startActivityForResult(intent, CREDENTIAL_CONFIRM_REQUEST);
    }

    Intent getAccountConfirmationIntent() {
        ActivityInfo activityInfo;
        FragmentActivity activity = getActivity();
        String string = activity.getString(R.string.account_type);
        String string2 = activity.getString(R.string.account_confirmation_package);
        String string3 = activity.getString(R.string.account_confirmation_class);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
            Log.i("MasterClear", "Resources not set for account confirmation.");
            return null;
        }
        Account[] accountsByType = AccountManager.get(activity).getAccountsByType(string);
        if (accountsByType != null && accountsByType.length > 0) {
            Intent component = new Intent().setPackage(string2).setComponent(new ComponentName(string2, string3));
            ResolveInfo resolveInfoResolveActivity = activity.getPackageManager().resolveActivity(component, 0);
            if (resolveInfoResolveActivity != null && (activityInfo = resolveInfoResolveActivity.activityInfo) != null && string2.equals(activityInfo.packageName)) {
                return component;
            }
            Log.i("MasterClear", "Unable to resolve Activity: " + string2 + "/" + string3);
        } else {
            Log.d("MasterClear", "No " + string + " accounts installed!");
        }
        return null;
    }

    void establishInitialState() {
        setUpActionBarAndTitle();
        setUpInitiateButton();
        this.mExternalStorageContainer = this.mContentView.findViewById(R.id.erase_external_container);
        this.mExternalStorage = (CheckBox) this.mContentView.findViewById(R.id.erase_external);
        this.mEsimStorageContainer = this.mContentView.findViewById(R.id.erase_esim_container);
        this.mEsimStorage = (CheckBox) this.mContentView.findViewById(R.id.erase_esim);
        ScrollView scrollView = this.mScrollView;
        if (scrollView != null) {
            scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
        this.mScrollView = (ScrollView) this.mContentView.findViewById(R.id.master_clear_scrollview);
        boolean zIsExternalStorageEmulated = Environment.isExternalStorageEmulated();
        if (zIsExternalStorageEmulated || (!Environment.isExternalStorageRemovable() && isExtStorageEncrypted())) {
            this.mExternalStorageContainer.setVisibility(8);
            this.mContentView.findViewById(R.id.erase_external_option_text).setVisibility(8);
            this.mContentView.findViewById(R.id.also_erases_external).setVisibility(0);
            this.mExternalStorage.setChecked(!zIsExternalStorageEmulated);
        } else {
            this.mExternalStorageContainer.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.MasterClear.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MasterClear.this.mExternalStorage.toggle();
                }
            });
        }
        if (showWipeEuicc()) {
            if (showWipeEuiccCheckbox()) {
                this.mEsimStorageContainer.setVisibility(0);
                this.mEsimStorageContainer.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.MasterClear.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MasterClear.this.mEsimStorage.toggle();
                    }
                });
            } else {
                this.mContentView.findViewById(R.id.also_erases_esim).setVisibility(0);
                this.mContentView.findViewById(R.id.no_cancel_mobile_plan).setVisibility(0);
                this.mEsimStorage.setChecked(true);
            }
        }
        loadAccountList((UserManager) getActivity().getSystemService("user"));
        StringBuffer stringBuffer = new StringBuffer();
        View viewFindViewById = this.mContentView.findViewById(R.id.master_clear_container);
        getContentDescription(viewFindViewById, stringBuffer);
        viewFindViewById.setContentDescription(stringBuffer);
        this.mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.android.car.developeroptions.MasterClear.4
            @Override // android.view.View.OnScrollChangeListener
            public void onScrollChange(View view, int i, int i2, int i3, int i4) {
                if ((view instanceof ScrollView) && MasterClear.this.hasReachedBottom((ScrollView) view)) {
                    MasterClear.this.mInitiateButton.setEnabled(true);
                    MasterClear.this.mScrollView.setOnScrollChangeListener(null);
                }
            }
        });
        this.mScrollView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    boolean showWipeEuicc() {
        Context context = getContext();
        if (!isEuiccEnabled(context)) {
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        return (Settings.Global.getInt(contentResolver, "euicc_provisioned", 0) == 0 && Settings.Global.getInt(contentResolver, "development_settings_enabled", 0) == 0) ? false : true;
    }

    boolean showWipeEuiccCheckbox() {
        return SystemProperties.getBoolean("masterclear.allow_retain_esim_profiles_after_fdr", false);
    }

    protected boolean isEuiccEnabled(Context context) {
        return ((EuiccManager) context.getSystemService("euicc")).isEnabled();
    }

    boolean hasReachedBottom(ScrollView scrollView) {
        return scrollView.getChildCount() < 1 || scrollView.getChildAt(0).getBottom() - (scrollView.getHeight() + scrollView.getScrollY()) <= 0;
    }

    private void setUpInitiateButton() {
        if (this.mInitiateButton != null) {
            return;
        }
        FooterBarMixin footerBarMixin = (FooterBarMixin) ((GlifLayout) this.mContentView.findViewById(R.id.setup_wizard_layout)).getMixin(FooterBarMixin.class);
        FooterButton.Builder builder = new FooterButton.Builder(getActivity());
        builder.setText(R.string.master_clear_button_text);
        builder.setListener(this.mInitiateListener);
        builder.setButtonType(0);
        builder.setTheme(R.style.SudGlifButton_Primary);
        footerBarMixin.setPrimaryButton(builder.build());
        this.mInitiateButton = footerBarMixin.getPrimaryButton();
    }

    private void getContentDescription(View view, StringBuffer stringBuffer) {
        if (view.getVisibility() != 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                getContentDescription(viewGroup.getChildAt(i), stringBuffer);
            }
            return;
        }
        if (view instanceof TextView) {
            stringBuffer.append(((TextView) view).getText());
            stringBuffer.append(",");
        }
    }

    private boolean isExtStorageEncrypted() {
        return !"".equals((String) VoldProperties.decrypt().orElse(""));
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0140  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void loadAccountList(android.os.UserManager r24) {
        /*
            Method dump skipped, instruction units count: 432
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.MasterClear.loadAccountList(android.os.UserManager):void");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context context = getContext();
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(context, "no_factory_reset", UserHandle.myUserId());
        if ((!UserManager.get(context).isAdminUser() || RestrictedLockUtilsInternal.hasBaseUserRestriction(context, "no_factory_reset", UserHandle.myUserId())) && !Utils.isDemoUser(context)) {
            return layoutInflater.inflate(R.layout.master_clear_disallowed_screen, (ViewGroup) null);
        }
        if (enforcedAdminCheckIfRestrictionEnforced != null) {
            AlertDialog.Builder builderPrepareDialogBuilder = new ActionDisabledByAdminDialogHelper(getActivity()).prepareDialogBuilder("no_factory_reset", enforcedAdminCheckIfRestrictionEnforced);
            builderPrepareDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.car.developeroptions.-$$Lambda$MasterClear$VCJpgpF3FdQhPIRy6-0Ipzo-RiA
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    this.f$0.lambda$onCreateView$0$MasterClear(dialogInterface);
                }
            });
            builderPrepareDialogBuilder.show();
            return new View(getContext());
        }
        this.mContentView = layoutInflater.inflate(R.layout.master_clear, (ViewGroup) null);
        establishInitialState();
        return this.mContentView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateView$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateView$0$MasterClear(DialogInterface dialogInterface) {
        getActivity().finish();
    }
}
