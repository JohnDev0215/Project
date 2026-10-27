package com.android.car.developeroptions.biometrics.fingerprint;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.fingerprint.Fingerprint;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.SubSettings;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.biometrics.fingerprint.FingerprintAuthenticateSidecar;
import com.android.car.developeroptions.biometrics.fingerprint.FingerprintRemoveSidecar;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.password.ChooseLockGeneric;
import com.android.car.developeroptions.password.ChooseLockSettingsHelper;
import com.android.car.developeroptions.utils.AnnotationSpan;
import com.android.car.developeroptions.widget.ImeAwareEditText;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.TwoTargetPreference;
import com.android.settingslib.widget.FooterPreference;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FingerprintSettings extends SubSettings {
    @Override // com.android.car.developeroptions.SettingsActivity, android.app.Activity
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", FingerprintSettingsFragment.class.getName());
        return intent;
    }

    @Override // com.android.car.developeroptions.SubSettings, com.android.car.developeroptions.SettingsActivity
    protected boolean isValidFragment(String str) {
        return FingerprintSettingsFragment.class.getName().equals(str);
    }

    @Override // com.android.car.developeroptions.SettingsActivity, com.android.car.developeroptions.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getText(R.string.security_settings_fingerprint_preference_title));
    }

    public static class FingerprintSettingsFragment extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, FingerprintPreference.OnDeleteClickListener {
        private FingerprintAuthenticateSidecar mAuthenticateSidecar;
        private FingerprintManager mFingerprintManager;
        private HashMap<Integer, String> mFingerprintsRenaming;
        private CharSequence mFooterTitle;
        private Drawable mHighlightDrawable;
        private boolean mInFingerprintLockout;
        private boolean mLaunchedConfirm;
        private FingerprintRemoveSidecar mRemovalSidecar;
        private byte[] mToken;
        private int mUserId;
        FingerprintAuthenticateSidecar.Listener mAuthenticateListener = new FingerprintAuthenticateSidecar.Listener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintSettings.FingerprintSettingsFragment.1
            @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintAuthenticateSidecar.Listener
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
                FingerprintSettingsFragment.this.mHandler.obtainMessage(1001, authenticationResult.getFingerprint().getBiometricId(), 0).sendToTarget();
            }

            @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintAuthenticateSidecar.Listener
            public void onAuthenticationFailed() {
                FingerprintSettingsFragment.this.mHandler.obtainMessage(1002).sendToTarget();
            }

            @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintAuthenticateSidecar.Listener
            public void onAuthenticationError(int i, CharSequence charSequence) {
                FingerprintSettingsFragment.this.mHandler.obtainMessage(1003, i, 0, charSequence).sendToTarget();
            }

            @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintAuthenticateSidecar.Listener
            public void onAuthenticationHelp(int i, CharSequence charSequence) {
                FingerprintSettingsFragment.this.mHandler.obtainMessage(1004, i, 0, charSequence).sendToTarget();
            }
        };
        FingerprintRemoveSidecar.Listener mRemovalListener = new FingerprintRemoveSidecar.Listener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintSettings.FingerprintSettingsFragment.2
            @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintRemoveSidecar.Listener
            public void onRemovalSucceeded(Fingerprint fingerprint) {
                FingerprintSettingsFragment.this.mHandler.obtainMessage(1000, fingerprint.getBiometricId(), 0).sendToTarget();
                updateDialog();
            }

            @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintRemoveSidecar.Listener
            public void onRemovalError(Fingerprint fingerprint, int i, CharSequence charSequence) {
                FragmentActivity activity = FingerprintSettingsFragment.this.getActivity();
                if (activity != null) {
                    Toast.makeText(activity, charSequence, 0);
                }
                updateDialog();
            }

            private void updateDialog() {
                RenameDialog renameDialog = (RenameDialog) FingerprintSettingsFragment.this.getFragmentManager().findFragmentByTag(RenameDialog.class.getName());
                if (renameDialog != null) {
                    renameDialog.enableDelete();
                }
            }
        };
        private final Handler mHandler = new Handler() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintSettings.FingerprintSettingsFragment.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1000) {
                    FingerprintSettingsFragment.this.removeFingerprintPreference(message.arg1);
                    FingerprintSettingsFragment.this.updateAddPreference();
                    FingerprintSettingsFragment.this.retryFingerprint();
                } else if (i == 1001) {
                    FingerprintSettingsFragment.this.highlightFingerprintItem(message.arg1);
                    FingerprintSettingsFragment.this.retryFingerprint();
                } else {
                    if (i != 1003) {
                        return;
                    }
                    FingerprintSettingsFragment.this.handleError(message.arg1, (CharSequence) message.obj);
                }
            }
        };
        private final Runnable mFingerprintLockoutReset = new Runnable() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintSettings.FingerprintSettingsFragment.5
            @Override // java.lang.Runnable
            public void run() {
                FingerprintSettingsFragment.this.mInFingerprintLockout = false;
                FingerprintSettingsFragment.this.retryFingerprint();
            }
        };

        @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
        public int getHelpResource() {
            return R.string.help_url_fingerprint;
        }

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 49;
        }

        protected void handleError(int i, CharSequence charSequence) {
            FragmentActivity activity;
            if (i != 5) {
                if (i == 7) {
                    this.mInFingerprintLockout = true;
                    if (!this.mHandler.hasCallbacks(this.mFingerprintLockoutReset)) {
                        this.mHandler.postDelayed(this.mFingerprintLockoutReset, 30000L);
                    }
                } else if (i == 9) {
                    this.mInFingerprintLockout = true;
                }
                if (this.mInFingerprintLockout && (activity = getActivity()) != null) {
                    Toast.makeText(activity, charSequence, 0).show();
                }
                retryFingerprint();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void retryFingerprint() {
            if (this.mRemovalSidecar.inProgress() || this.mFingerprintManager.getEnrolledFingerprints(this.mUserId).size() == 0 || this.mLaunchedConfirm || this.mInFingerprintLockout) {
                return;
            }
            this.mAuthenticateSidecar.startAuthentication(this.mUserId);
            this.mAuthenticateSidecar.setListener(this.mAuthenticateListener);
        }

        @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            final FragmentActivity activity = getActivity();
            this.mFingerprintManager = Utils.getFingerprintManagerOrNull(activity);
            FingerprintAuthenticateSidecar fingerprintAuthenticateSidecar = (FingerprintAuthenticateSidecar) getFragmentManager().findFragmentByTag("authenticate_sidecar");
            this.mAuthenticateSidecar = fingerprintAuthenticateSidecar;
            if (fingerprintAuthenticateSidecar == null) {
                this.mAuthenticateSidecar = new FingerprintAuthenticateSidecar();
                FragmentTransaction fragmentTransactionBeginTransaction = getFragmentManager().beginTransaction();
                fragmentTransactionBeginTransaction.add(this.mAuthenticateSidecar, "authenticate_sidecar");
                fragmentTransactionBeginTransaction.commit();
            }
            this.mAuthenticateSidecar.setFingerprintManager(this.mFingerprintManager);
            FingerprintRemoveSidecar fingerprintRemoveSidecar = (FingerprintRemoveSidecar) getFragmentManager().findFragmentByTag("removal_sidecar");
            this.mRemovalSidecar = fingerprintRemoveSidecar;
            if (fingerprintRemoveSidecar == null) {
                this.mRemovalSidecar = new FingerprintRemoveSidecar();
                FragmentTransaction fragmentTransactionBeginTransaction2 = getFragmentManager().beginTransaction();
                fragmentTransactionBeginTransaction2.add(this.mRemovalSidecar, "removal_sidecar");
                fragmentTransactionBeginTransaction2.commit();
            }
            this.mRemovalSidecar.setFingerprintManager(this.mFingerprintManager);
            this.mRemovalSidecar.setListener(this.mRemovalListener);
            RenameDialog renameDialog = (RenameDialog) getFragmentManager().findFragmentByTag(RenameDialog.class.getName());
            if (renameDialog != null) {
                renameDialog.setDeleteInProgress(this.mRemovalSidecar.inProgress());
            }
            this.mFingerprintsRenaming = new HashMap<>();
            if (bundle != null) {
                this.mFingerprintsRenaming = (HashMap) bundle.getSerializable("mFingerprintsRenaming");
                this.mToken = bundle.getByteArray("hw_auth_token");
                this.mLaunchedConfirm = bundle.getBoolean("launched_confirm", false);
            }
            this.mUserId = getActivity().getIntent().getIntExtra("android.intent.extra.USER_ID", UserHandle.myUserId());
            if (this.mToken == null && !this.mLaunchedConfirm) {
                this.mLaunchedConfirm = true;
                launchChooseOrConfirmLock();
            }
            final RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfKeyguardFeaturesDisabled = RestrictedLockUtilsInternal.checkIfKeyguardFeaturesDisabled(activity, 32, this.mUserId);
            AnnotationSpan.LinkInfo linkInfo = new AnnotationSpan.LinkInfo("admin_details", new View.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.-$$Lambda$FingerprintSettings$FingerprintSettingsFragment$a5SrmcLmXwhlduBuskLy9BpgqI8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RestrictedLockUtils.sendShowAdminSupportDetailsIntent(activity, enforcedAdminCheckIfKeyguardFeaturesDisabled);
                }
            });
            this.mFooterTitle = AnnotationSpan.linkify(getText(enforcedAdminCheckIfKeyguardFeaturesDisabled != null ? R.string.security_settings_fingerprint_enroll_disclaimer_lockscreen_disabled : R.string.security_settings_fingerprint_enroll_disclaimer), new AnnotationSpan.LinkInfo(activity, "url", HelpUtils.getHelpIntent(activity, getString(getHelpResource()), activity.getClass().getName())), linkInfo);
        }

        protected void removeFingerprintPreference(int i) {
            String strGenKey = genKey(i);
            Preference preferenceFindPreference = findPreference(strGenKey);
            if (preferenceFindPreference != null) {
                if (getPreferenceScreen().removePreference(preferenceFindPreference)) {
                    return;
                }
                Log.w("FingerprintSettings", "Failed to remove preference with key " + strGenKey);
                return;
            }
            Log.w("FingerprintSettings", "Can't find preference to remove: " + strGenKey);
        }

        private PreferenceScreen createPreferenceHierarchy() {
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            if (preferenceScreen != null) {
                preferenceScreen.removeAll();
            }
            addPreferencesFromResource(R.xml.security_settings_fingerprint);
            PreferenceScreen preferenceScreen2 = getPreferenceScreen();
            addFingerprintItemPreferences(preferenceScreen2);
            setPreferenceScreen(preferenceScreen2);
            return preferenceScreen2;
        }

        private void addFingerprintItemPreferences(PreferenceGroup preferenceGroup) {
            preferenceGroup.removeAll();
            List enrolledFingerprints = this.mFingerprintManager.getEnrolledFingerprints(this.mUserId);
            int size = enrolledFingerprints.size();
            for (int i = 0; i < size; i++) {
                Fingerprint fingerprint = (Fingerprint) enrolledFingerprints.get(i);
                FingerprintPreference fingerprintPreference = new FingerprintPreference(preferenceGroup.getContext(), this);
                fingerprintPreference.setKey(genKey(fingerprint.getBiometricId()));
                fingerprintPreference.setTitle(fingerprint.getName());
                fingerprintPreference.setFingerprint(fingerprint);
                fingerprintPreference.setPersistent(false);
                fingerprintPreference.setIcon(R.drawable.ic_fingerprint_24dp);
                if (this.mRemovalSidecar.isRemovingFingerprint(fingerprint.getBiometricId())) {
                    fingerprintPreference.setEnabled(false);
                }
                if (this.mFingerprintsRenaming.containsKey(Integer.valueOf(fingerprint.getBiometricId()))) {
                    fingerprintPreference.setTitle(this.mFingerprintsRenaming.get(Integer.valueOf(fingerprint.getBiometricId())));
                }
                preferenceGroup.addPreference(fingerprintPreference);
                fingerprintPreference.setOnPreferenceChangeListener(this);
            }
            Preference preference = new Preference(preferenceGroup.getContext());
            preference.setKey("key_fingerprint_add");
            preference.setTitle(R.string.fingerprint_add_title);
            preference.setIcon(R.drawable.ic_menu_add);
            preferenceGroup.addPreference(preference);
            preference.setOnPreferenceChangeListener(this);
            updateAddPreference();
            createFooterPreference(preferenceGroup);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateAddPreference() {
            if (getActivity() == null) {
                return;
            }
            int integer = getContext().getResources().getInteger(android.R.integer.config_defaultNightDisplayCustomStartTime);
            boolean z = this.mFingerprintManager.getEnrolledFingerprints(this.mUserId).size() >= integer;
            boolean zInProgress = this.mRemovalSidecar.inProgress();
            String string = z ? getContext().getString(R.string.fingerprint_add_max, Integer.valueOf(integer)) : "";
            Preference preferenceFindPreference = findPreference("key_fingerprint_add");
            preferenceFindPreference.setSummary(string);
            preferenceFindPreference.setEnabled((z || zInProgress) ? false : true);
        }

        private void createFooterPreference(PreferenceGroup preferenceGroup) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                return;
            }
            FooterPreference.Builder builder = new FooterPreference.Builder(activity);
            builder.setTitle(this.mFooterTitle);
            preferenceGroup.addPreference(builder.build());
        }

        private static String genKey(int i) {
            return "key_fingerprint_item_" + i;
        }

        @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
        public void onResume() {
            super.onResume();
            this.mInFingerprintLockout = false;
            updatePreferences();
            FingerprintRemoveSidecar fingerprintRemoveSidecar = this.mRemovalSidecar;
            if (fingerprintRemoveSidecar != null) {
                fingerprintRemoveSidecar.setListener(this.mRemovalListener);
            }
        }

        private void updatePreferences() {
            createPreferenceHierarchy();
            retryFingerprint();
        }

        @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
        public void onPause() {
            super.onPause();
            FingerprintRemoveSidecar fingerprintRemoveSidecar = this.mRemovalSidecar;
            if (fingerprintRemoveSidecar != null) {
                fingerprintRemoveSidecar.setListener(null);
            }
            FingerprintAuthenticateSidecar fingerprintAuthenticateSidecar = this.mAuthenticateSidecar;
            if (fingerprintAuthenticateSidecar != null) {
                fingerprintAuthenticateSidecar.setListener(null);
                this.mAuthenticateSidecar.stopAuthentication();
            }
        }

        @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onSaveInstanceState(Bundle bundle) {
            bundle.putByteArray("hw_auth_token", this.mToken);
            bundle.putBoolean("launched_confirm", this.mLaunchedConfirm);
            bundle.putSerializable("mFingerprintsRenaming", this.mFingerprintsRenaming);
        }

        @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
        public boolean onPreferenceTreeClick(Preference preference) {
            if ("key_fingerprint_add".equals(preference.getKey())) {
                Intent intent = new Intent();
                intent.setClassName("com.android.car.developeroptions", FingerprintEnrollEnrolling.class.getName());
                intent.putExtra("android.intent.extra.USER_ID", this.mUserId);
                intent.putExtra("hw_auth_token", this.mToken);
                startActivityForResult(intent, 10);
            } else if (preference instanceof FingerprintPreference) {
                showRenameDialog(((FingerprintPreference) preference).getFingerprint());
            }
            return super.onPreferenceTreeClick(preference);
        }

        @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintSettings.FingerprintPreference.OnDeleteClickListener
        public void onDeleteClick(FingerprintPreference fingerprintPreference) {
            boolean z = this.mFingerprintManager.getEnrolledFingerprints(this.mUserId).size() > 1;
            Parcelable fingerprint = fingerprintPreference.getFingerprint();
            if (z) {
                if (this.mRemovalSidecar.inProgress()) {
                    Log.d("FingerprintSettings", "Fingerprint delete in progress, skipping");
                    return;
                } else {
                    DeleteFingerprintDialog.newInstance(fingerprint, this).show(getFragmentManager(), DeleteFingerprintDialog.class.getName());
                    return;
                }
            }
            ConfirmLastDeleteDialog confirmLastDeleteDialog = new ConfirmLastDeleteDialog();
            boolean zIsManagedProfile = UserManager.get(getContext()).isManagedProfile(this.mUserId);
            Bundle bundle = new Bundle();
            bundle.putParcelable("fingerprint", fingerprint);
            bundle.putBoolean("isProfileChallengeUser", zIsManagedProfile);
            confirmLastDeleteDialog.setArguments(bundle);
            confirmLastDeleteDialog.setTargetFragment(this, 0);
            confirmLastDeleteDialog.show(getFragmentManager(), ConfirmLastDeleteDialog.class.getName());
        }

        private void showRenameDialog(Fingerprint fingerprint) {
            RenameDialog renameDialog = new RenameDialog();
            Bundle bundle = new Bundle();
            if (this.mFingerprintsRenaming.containsKey(Integer.valueOf(fingerprint.getBiometricId()))) {
                bundle.putParcelable("fingerprint", new Fingerprint(this.mFingerprintsRenaming.get(Integer.valueOf(fingerprint.getBiometricId())), fingerprint.getGroupId(), fingerprint.getBiometricId(), fingerprint.getDeviceId()));
            } else {
                bundle.putParcelable("fingerprint", fingerprint);
            }
            renameDialog.setDeleteInProgress(this.mRemovalSidecar.inProgress());
            renameDialog.setArguments(bundle);
            renameDialog.setTargetFragment(this, 0);
            renameDialog.show(getFragmentManager(), RenameDialog.class.getName());
        }

        @Override // androidx.preference.Preference.OnPreferenceChangeListener
        public boolean onPreferenceChange(Preference preference, Object obj) {
            String key = preference.getKey();
            if ("fingerprint_enable_keyguard_toggle".equals(key)) {
                return true;
            }
            Log.v("FingerprintSettings", "Unknown key:" + key);
            return true;
        }

        @Override // androidx.fragment.app.Fragment
        public void onActivityResult(int i, int i2, Intent intent) {
            super.onActivityResult(i, i2, intent);
            if (i == 102 || i == 101) {
                this.mLaunchedConfirm = false;
                if ((i2 == 1 || i2 == -1) && intent != null) {
                    this.mToken = intent.getByteArrayExtra("hw_auth_token");
                }
            } else if (i == 10 && i2 == 3) {
                FragmentActivity activity = getActivity();
                activity.setResult(3);
                activity.finish();
            }
            if (this.mToken == null) {
                getActivity().finish();
            }
        }

        @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
        public void onDestroy() {
            int iPostEnroll;
            super.onDestroy();
            if (!getActivity().isFinishing() || (iPostEnroll = this.mFingerprintManager.postEnroll()) >= 0) {
                return;
            }
            Log.w("FingerprintSettings", "postEnroll failed: result = " + iPostEnroll);
        }

        private Drawable getHighlightDrawable() {
            FragmentActivity activity;
            if (this.mHighlightDrawable == null && (activity = getActivity()) != null) {
                this.mHighlightDrawable = activity.getDrawable(R.drawable.preference_highlight);
            }
            return this.mHighlightDrawable;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void highlightFingerprintItem(int i) {
            final View view;
            FingerprintPreference fingerprintPreference = (FingerprintPreference) findPreference(genKey(i));
            Drawable highlightDrawable = getHighlightDrawable();
            if (highlightDrawable == null || fingerprintPreference == null || (view = fingerprintPreference.getView()) == null) {
                return;
            }
            highlightDrawable.setHotspot(view.getWidth() / 2, view.getHeight() / 2);
            view.setBackground(highlightDrawable);
            view.setPressed(true);
            view.setPressed(false);
            this.mHandler.postDelayed(new Runnable(this) { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintSettings.FingerprintSettingsFragment.4
                @Override // java.lang.Runnable
                public void run() {
                    view.setBackground(null);
                }
            }, 500L);
        }

        private void launchChooseOrConfirmLock() {
            Intent intent = new Intent();
            long jPreEnroll = this.mFingerprintManager.preEnroll();
            if (new ChooseLockSettingsHelper(getActivity(), this).launchConfirmationActivity(101, getString(R.string.security_settings_fingerprint_preference_title), (CharSequence) null, (CharSequence) null, jPreEnroll, this.mUserId)) {
                return;
            }
            intent.setClassName("com.android.car.developeroptions", ChooseLockGeneric.class.getName());
            intent.putExtra("minimum_quality", 65536);
            intent.putExtra("hide_disabled_prefs", true);
            intent.putExtra("has_challenge", true);
            intent.putExtra("android.intent.extra.USER_ID", this.mUserId);
            intent.putExtra("challenge", jPreEnroll);
            intent.putExtra("android.intent.extra.USER_ID", this.mUserId);
            startActivityForResult(intent, 102);
        }

        void deleteFingerPrint(Fingerprint fingerprint) {
            this.mRemovalSidecar.startRemove(fingerprint, this.mUserId);
            findPreference(genKey(fingerprint.getBiometricId())).setEnabled(false);
            updateAddPreference();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void renameFingerPrint(int i, String str) {
            this.mFingerprintManager.rename(i, this.mUserId, str);
            if (!TextUtils.isEmpty(str)) {
                this.mFingerprintsRenaming.put(Integer.valueOf(i), str);
            }
            updatePreferences();
        }

        public static class DeleteFingerprintDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
            private AlertDialog mAlertDialog;
            private Fingerprint mFp;

            @Override // com.android.settingslib.core.instrumentation.Instrumentable
            public int getMetricsCategory() {
                return 570;
            }

            public static DeleteFingerprintDialog newInstance(Fingerprint fingerprint, FingerprintSettingsFragment fingerprintSettingsFragment) {
                DeleteFingerprintDialog deleteFingerprintDialog = new DeleteFingerprintDialog();
                Bundle bundle = new Bundle();
                bundle.putParcelable("fingerprint", fingerprint);
                deleteFingerprintDialog.setArguments(bundle);
                deleteFingerprintDialog.setTargetFragment(fingerprintSettingsFragment, 0);
                return deleteFingerprintDialog;
            }

            @Override // androidx.fragment.app.DialogFragment
            public Dialog onCreateDialog(Bundle bundle) {
                Fingerprint parcelable = getArguments().getParcelable("fingerprint");
                this.mFp = parcelable;
                String string = getString(R.string.fingerprint_delete_title, parcelable.getName());
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(string);
                builder.setMessage(R.string.fingerprint_delete_message);
                builder.setPositiveButton(R.string.security_settings_fingerprint_enroll_dialog_delete, this);
                builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
                AlertDialog alertDialogCreate = builder.create();
                this.mAlertDialog = alertDialogCreate;
                return alertDialogCreate;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    int biometricId = this.mFp.getBiometricId();
                    Log.v("FingerprintSettings", "Removing fpId=" + biometricId);
                    this.mMetricsFeatureProvider.action(getContext(), 253, biometricId);
                    ((FingerprintSettingsFragment) getTargetFragment()).deleteFingerPrint(this.mFp);
                }
            }
        }

        public static class RenameDialog extends InstrumentedDialogFragment {
            private AlertDialog mAlertDialog;
            private boolean mDeleteInProgress;
            private ImeAwareEditText mDialogTextField;
            private Fingerprint mFp;

            @Override // com.android.settingslib.core.instrumentation.Instrumentable
            public int getMetricsCategory() {
                return 570;
            }

            public void setDeleteInProgress(boolean z) {
                this.mDeleteInProgress = z;
            }

            @Override // androidx.fragment.app.DialogFragment
            public Dialog onCreateDialog(Bundle bundle) {
                final String string;
                final int i;
                this.mFp = getArguments().getParcelable("fingerprint");
                final int i2 = -1;
                if (bundle != null) {
                    string = bundle.getString("fingerName");
                    int i3 = bundle.getInt("startSelection", -1);
                    i = bundle.getInt("endSelection", -1);
                    i2 = i3;
                } else {
                    string = null;
                    i = -1;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(R.layout.fingerprint_rename_dialog);
                builder.setPositiveButton(R.string.security_settings_fingerprint_enroll_dialog_ok, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintSettings.FingerprintSettingsFragment.RenameDialog.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i4) {
                        String string2 = RenameDialog.this.mDialogTextField.getText().toString();
                        CharSequence name = RenameDialog.this.mFp.getName();
                        if (!TextUtils.equals(string2, name)) {
                            Log.d("FingerprintSettings", "rename " + ((Object) name) + " to " + string2);
                            ((InstrumentedDialogFragment) RenameDialog.this).mMetricsFeatureProvider.action(RenameDialog.this.getContext(), 254, RenameDialog.this.mFp.getBiometricId());
                            ((FingerprintSettingsFragment) RenameDialog.this.getTargetFragment()).renameFingerPrint(RenameDialog.this.mFp.getBiometricId(), string2);
                        }
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialogCreate = builder.create();
                this.mAlertDialog = alertDialogCreate;
                alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintSettings.FingerprintSettingsFragment.RenameDialog.2
                    @Override // android.content.DialogInterface.OnShowListener
                    public void onShow(DialogInterface dialogInterface) {
                        RenameDialog renameDialog = RenameDialog.this;
                        renameDialog.mDialogTextField = (ImeAwareEditText) renameDialog.mAlertDialog.findViewById(R.id.fingerprint_rename_field);
                        CharSequence name = string;
                        if (name == null) {
                            name = RenameDialog.this.mFp.getName();
                        }
                        RenameDialog.this.mDialogTextField.setText(name);
                        if (i2 == -1 || i == -1) {
                            RenameDialog.this.mDialogTextField.selectAll();
                        } else {
                            RenameDialog.this.mDialogTextField.setSelection(i2, i);
                        }
                        if (RenameDialog.this.mDeleteInProgress) {
                            RenameDialog.this.mAlertDialog.getButton(-2).setEnabled(false);
                        }
                        RenameDialog.this.mDialogTextField.requestFocus();
                        RenameDialog.this.mDialogTextField.scheduleShowSoftInput();
                    }
                });
                return this.mAlertDialog;
            }

            public void enableDelete() {
                this.mDeleteInProgress = false;
                AlertDialog alertDialog = this.mAlertDialog;
                if (alertDialog != null) {
                    alertDialog.getButton(-2).setEnabled(true);
                }
            }

            @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
            public void onSaveInstanceState(Bundle bundle) {
                super.onSaveInstanceState(bundle);
                ImeAwareEditText imeAwareEditText = this.mDialogTextField;
                if (imeAwareEditText != null) {
                    bundle.putString("fingerName", imeAwareEditText.getText().toString());
                    bundle.putInt("startSelection", this.mDialogTextField.getSelectionStart());
                    bundle.putInt("endSelection", this.mDialogTextField.getSelectionEnd());
                }
            }
        }

        public static class ConfirmLastDeleteDialog extends InstrumentedDialogFragment {
            private Fingerprint mFp;

            @Override // com.android.settingslib.core.instrumentation.Instrumentable
            public int getMetricsCategory() {
                return 571;
            }

            @Override // androidx.fragment.app.DialogFragment
            public Dialog onCreateDialog(Bundle bundle) {
                this.mFp = getArguments().getParcelable("fingerprint");
                boolean z = getArguments().getBoolean("isProfileChallengeUser");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.fingerprint_last_delete_title);
                builder.setMessage(z ? R.string.fingerprint_last_delete_message_profile_challenge : R.string.fingerprint_last_delete_message);
                builder.setPositiveButton(R.string.fingerprint_last_delete_confirm, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintSettings.FingerprintSettingsFragment.ConfirmLastDeleteDialog.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((FingerprintSettingsFragment) ConfirmLastDeleteDialog.this.getTargetFragment()).deleteFingerPrint(ConfirmLastDeleteDialog.this.mFp);
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(this) { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintSettings.FingerprintSettingsFragment.ConfirmLastDeleteDialog.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                return builder.create();
            }
        }
    }

    public static class FingerprintPreference extends TwoTargetPreference {
        private View mDeleteView;
        private Fingerprint mFingerprint;
        private final OnDeleteClickListener mOnDeleteClickListener;
        private View mView;

        public interface OnDeleteClickListener {
            void onDeleteClick(FingerprintPreference fingerprintPreference);
        }

        @Override // com.android.settingslib.TwoTargetPreference
        protected int getSecondTargetResId() {
            return R.layout.preference_widget_delete;
        }

        public FingerprintPreference(Context context, OnDeleteClickListener onDeleteClickListener) {
            super(context);
            this.mOnDeleteClickListener = onDeleteClickListener;
        }

        public View getView() {
            return this.mView;
        }

        public void setFingerprint(Fingerprint fingerprint) {
            this.mFingerprint = fingerprint;
        }

        public Fingerprint getFingerprint() {
            return this.mFingerprint;
        }

        @Override // com.android.settingslib.TwoTargetPreference, androidx.preference.Preference
        public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
            super.onBindViewHolder(preferenceViewHolder);
            View view = preferenceViewHolder.itemView;
            this.mView = view;
            View viewFindViewById = view.findViewById(R.id.delete_button);
            this.mDeleteView = viewFindViewById;
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintSettings.FingerprintPreference.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (FingerprintPreference.this.mOnDeleteClickListener != null) {
                        FingerprintPreference.this.mOnDeleteClickListener.onDeleteClick(FingerprintPreference.this);
                    }
                }
            });
        }
    }
}
