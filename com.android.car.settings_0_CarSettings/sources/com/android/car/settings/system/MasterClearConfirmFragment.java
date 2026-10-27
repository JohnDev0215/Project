package com.android.car.settings.system;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.service.oemlock.OemLockManager;
import android.service.persistentdata.PersistentDataBlockManager;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import androidx.preference.PreferenceManager;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.ui.toolbar.MenuItem;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MasterClearConfirmFragment extends SettingsFragment {
    private MenuItem mClearConfirmButton;
    private MenuItem.OnClickListener mFinalClickListener = new MenuItem.OnClickListener() { // from class: com.android.car.settings.system.-$$Lambda$MasterClearConfirmFragment$U90TmmsArqOXB9c4kOmt0TKEAwQ
        @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
        public final void onClick(MenuItem menuItem) {
            this.f$0.lambda$new$0$MasterClearConfirmFragment(menuItem);
        }
    };

    /* JADX WARN: Type inference failed for: r2v3, types: [com.android.car.settings.system.MasterClearConfirmFragment$1] */
    public /* synthetic */ void lambda$new$0$MasterClearConfirmFragment(MenuItem i) {
        if (ActivityManager.isUserAMonkey()) {
            return;
        }
        final PersistentDataBlockManager pdbManager = (PersistentDataBlockManager) requireContext().getSystemService("persistent_data_block");
        OemLockManager oemLockManager = (OemLockManager) requireContext().getSystemService("oem_lock");
        if (pdbManager != null && !oemLockManager.isOemUnlockAllowed() && isDeviceProvisioned()) {
            new AsyncTask<Void, Void, Void>() { // from class: com.android.car.settings.system.MasterClearConfirmFragment.1
                private ProgressDialog mProgressDialog;

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(Void... params) {
                    pdbManager.wipe();
                    return null;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(Void aVoid) {
                    this.mProgressDialog.hide();
                    if (MasterClearConfirmFragment.this.getActivity() != null) {
                        MasterClearConfirmFragment.this.resetEverything();
                    }
                }

                @Override // android.os.AsyncTask
                protected void onPreExecute() {
                    ProgressDialog progressDialog = MasterClearConfirmFragment.this.getProgressDialog();
                    this.mProgressDialog = progressDialog;
                    progressDialog.show();
                }
            }.execute(new Void[0]);
        } else {
            resetEverything();
        }
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.master_clear_confirm_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mClearConfirmButton);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mClearConfirmButton = new MenuItem.Builder(getContext()).setTitle(R.string.master_clear_confirm_button_text).setOnClickListener(this.mFinalClickListener).build();
    }

    private boolean isDeviceProvisioned() {
        return Settings.Global.getInt(requireContext().getContentResolver(), "device_provisioned", 0) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ProgressDialog getProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(requireContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(requireContext().getString(R.string.master_clear_progress_title));
        progressDialog.setMessage(requireContext().getString(R.string.master_clear_progress_text));
        return progressDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetEverything() {
        Intent intent = new Intent("android.intent.action.FACTORY_RESET");
        intent.setPackage(SystemMediaRouteProvider.PACKAGE_NAME);
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.REASON", "MasterClearConfirm");
        intent.putExtra("com.android.internal.intent.extra.WIPE_ESIMS", shouldResetEsim());
        requireActivity().sendBroadcast(intent);
    }

    private boolean shouldResetEsim() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        return sharedPreferences.getBoolean(requireContext().getString(R.string.pk_master_clear_reset_esim), false);
    }
}
