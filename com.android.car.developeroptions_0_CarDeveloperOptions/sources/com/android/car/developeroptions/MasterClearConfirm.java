package com.android.car.developeroptions;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserHandle;
import android.service.oemlock.OemLockManager;
import android.service.persistentdata.PersistentDataBlockManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.enterprise.ActionDisabledByAdminDialogHelper;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.GlifLayout;

/* JADX INFO: loaded from: classes.dex */
public class MasterClearConfirm extends InstrumentedFragment {
    View mContentView;
    boolean mEraseEsims;
    private boolean mEraseSdCard;
    private View.OnClickListener mFinalClickListener = new View.OnClickListener() { // from class: com.android.car.developeroptions.MasterClearConfirm.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (Utils.isMonkeyRunning()) {
                return;
            }
            final PersistentDataBlockManager persistentDataBlockManager = (PersistentDataBlockManager) MasterClearConfirm.this.getActivity().getSystemService("persistent_data_block");
            OemLockManager oemLockManager = (OemLockManager) MasterClearConfirm.this.getActivity().getSystemService("oem_lock");
            if (persistentDataBlockManager == null || oemLockManager.isOemUnlockAllowed() || !Utils.isDeviceProvisioned(MasterClearConfirm.this.getActivity())) {
                MasterClearConfirm.this.doMasterClear();
            } else {
                new AsyncTask<Void, Void, Void>() { // from class: com.android.car.developeroptions.MasterClearConfirm.1.1
                    int mOldOrientation;
                    ProgressDialog mProgressDialog;

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public Void doInBackground(Void... voidArr) {
                        persistentDataBlockManager.wipe();
                        return null;
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(Void r2) {
                        this.mProgressDialog.hide();
                        if (MasterClearConfirm.this.getActivity() != null) {
                            MasterClearConfirm.this.getActivity().setRequestedOrientation(this.mOldOrientation);
                            MasterClearConfirm.this.doMasterClear();
                        }
                    }

                    @Override // android.os.AsyncTask
                    protected void onPreExecute() {
                        ProgressDialog progressDialog = getProgressDialog();
                        this.mProgressDialog = progressDialog;
                        progressDialog.show();
                        this.mOldOrientation = MasterClearConfirm.this.getActivity().getRequestedOrientation();
                        MasterClearConfirm.this.getActivity().setRequestedOrientation(14);
                    }
                }.execute(new Void[0]);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ProgressDialog getProgressDialog() {
            ProgressDialog progressDialog = new ProgressDialog(MasterClearConfirm.this.getActivity());
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setTitle(MasterClearConfirm.this.getActivity().getString(R.string.master_clear_progress_title));
            progressDialog.setMessage(MasterClearConfirm.this.getActivity().getString(R.string.master_clear_progress_text));
            return progressDialog;
        }
    };

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 67;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doMasterClear() {
        Intent intent = new Intent("android.intent.action.FACTORY_RESET");
        intent.setPackage("android");
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.REASON", "MasterClearConfirm");
        intent.putExtra("android.intent.extra.WIPE_EXTERNAL_STORAGE", this.mEraseSdCard);
        intent.putExtra("com.android.internal.intent.extra.WIPE_ESIMS", this.mEraseEsims);
        getActivity().sendBroadcast(intent);
    }

    private void establishFinalConfirmationState() {
        FooterBarMixin footerBarMixin = (FooterBarMixin) ((GlifLayout) this.mContentView.findViewById(R.id.setup_wizard_layout)).getMixin(FooterBarMixin.class);
        FooterButton.Builder builder = new FooterButton.Builder(getActivity());
        builder.setText(R.string.master_clear_button_text);
        builder.setListener(this.mFinalClickListener);
        builder.setButtonType(0);
        builder.setTheme(R.style.SudGlifButton_Primary);
        footerBarMixin.setPrimaryButton(builder.build());
    }

    private void setUpActionBarAndTitle() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Log.e("MasterClearConfirm", "No activity attached, skipping setUpActionBarAndTitle");
            return;
        }
        ActionBar actionBar = activity.getActionBar();
        if (actionBar == null) {
            Log.e("MasterClearConfirm", "No actionbar, skipping setUpActionBarAndTitle");
        } else {
            actionBar.hide();
            activity.getWindow().setStatusBarColor(0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getActivity(), "no_factory_reset", UserHandle.myUserId());
        if (RestrictedLockUtilsInternal.hasBaseUserRestriction(getActivity(), "no_factory_reset", UserHandle.myUserId())) {
            return layoutInflater.inflate(R.layout.master_clear_disallowed_screen, (ViewGroup) null);
        }
        if (enforcedAdminCheckIfRestrictionEnforced != null) {
            AlertDialog.Builder builderPrepareDialogBuilder = new ActionDisabledByAdminDialogHelper(getActivity()).prepareDialogBuilder("no_factory_reset", enforcedAdminCheckIfRestrictionEnforced);
            builderPrepareDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.car.developeroptions.-$$Lambda$MasterClearConfirm$DL6BmEiM82BDsPGCcvZPBR0e83A
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    this.f$0.lambda$onCreateView$0$MasterClearConfirm(dialogInterface);
                }
            });
            builderPrepareDialogBuilder.show();
            return new View(getActivity());
        }
        this.mContentView = layoutInflater.inflate(R.layout.master_clear_confirm, (ViewGroup) null);
        setUpActionBarAndTitle();
        establishFinalConfirmationState();
        setAccessibilityTitle();
        setSubtitle();
        return this.mContentView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateView$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateView$0$MasterClearConfirm(DialogInterface dialogInterface) {
        getActivity().finish();
    }

    private void setAccessibilityTitle() {
        CharSequence title = getActivity().getTitle();
        TextView textView = (TextView) this.mContentView.findViewById(R.id.master_clear_confirm);
        if (textView != null) {
            getActivity().setTitle(Utils.createAccessibleSequence(title, title + "," + textView.getText()));
        }
    }

    void setSubtitle() {
        if (this.mEraseEsims) {
            ((TextView) this.mContentView.findViewById(R.id.master_clear_confirm)).setText(R.string.master_clear_final_desc_esim);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mEraseSdCard = arguments != null && arguments.getBoolean("erase_sd");
        this.mEraseEsims = arguments != null && arguments.getBoolean("erase_esim");
    }
}
