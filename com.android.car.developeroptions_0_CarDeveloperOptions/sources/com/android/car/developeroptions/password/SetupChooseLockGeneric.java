package com.android.car.developeroptions.password;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.os.UserHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SetupEncryptionInterstitial;
import com.android.car.developeroptions.SetupWizardUtils;
import com.android.car.developeroptions.biometrics.fingerprint.SetupFingerprintEnrollFindSensor;
import com.android.car.developeroptions.password.ChooseLockGeneric;
import com.android.car.developeroptions.utils.SettingsDividerItemDecoration;
import com.android.internal.widget.LockPatternUtils;
import com.google.android.setupdesign.GlifPreferenceLayout;

/* JADX INFO: loaded from: classes.dex */
public class SetupChooseLockGeneric extends ChooseLockGeneric {
    @Override // com.android.car.developeroptions.password.ChooseLockGeneric, com.android.car.developeroptions.SettingsActivity
    protected boolean isValidFragment(String str) {
        return SetupChooseLockGenericFragment.class.getName().equals(str);
    }

    @Override // com.android.car.developeroptions.password.ChooseLockGeneric
    Class<? extends PreferenceFragmentCompat> getFragmentClass() {
        return SetupChooseLockGenericFragment.class;
    }

    @Override // com.android.car.developeroptions.SettingsActivity, android.app.Activity, android.view.ContextThemeWrapper
    protected void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, SetupWizardUtils.getTheme(getIntent()), z);
    }

    @Override // com.android.car.developeroptions.SettingsActivity, com.android.car.developeroptions.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().hasExtra("requested_min_complexity")) {
            IBinder activityToken = getActivityToken();
            if (!PasswordUtils.isCallingAppPermitted(this, activityToken, "android.permission.REQUEST_PASSWORD_COMPLEXITY")) {
                PasswordUtils.crashCallingApplication(activityToken, "Must have permission android.permission.REQUEST_PASSWORD_COMPLEXITY to use extra android.app.extra.PASSWORD_COMPLEXITY");
                finish();
                return;
            }
        }
        ((LinearLayout) findViewById(R.id.content_parent)).setFitsSystemWindows(false);
    }

    public static class SetupChooseLockGenericFragment extends ChooseLockGeneric.ChooseLockGenericFragment {
        @Override // com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment
        protected boolean canRunBeforeDeviceProvisioned() {
            return true;
        }

        @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            GlifPreferenceLayout glifPreferenceLayout = (GlifPreferenceLayout) view;
            glifPreferenceLayout.setDividerItemDecoration(new SettingsDividerItemDecoration(getContext()));
            glifPreferenceLayout.setDividerInset(getContext().getResources().getDimensionPixelSize(R.dimen.sud_items_glif_text_divider_inset));
            glifPreferenceLayout.setIcon(getContext().getDrawable(R.drawable.ic_lock));
            int i = this.mForFingerprint ? R.string.lock_settings_picker_title : R.string.setup_lock_settings_picker_title;
            if (getActivity() != null) {
                getActivity().setTitle(i);
            }
            glifPreferenceLayout.setHeaderText(i);
            setDivider(null);
        }

        @Override // com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment
        protected void addHeaderView() {
            if (this.mForFingerprint || this.mForFace) {
                setHeaderView(R.layout.setup_choose_lock_generic_biometrics_header);
            } else {
                setHeaderView(R.layout.setup_choose_lock_generic_header);
            }
        }

        @Override // com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment, androidx.fragment.app.Fragment
        public void onActivityResult(int i, int i2, Intent intent) {
            if (intent == null) {
                intent = new Intent();
            }
            intent.putExtra(":settings:password_quality", new LockPatternUtils(getActivity()).getKeyguardStoredPasswordQuality(UserHandle.myUserId()));
            super.onActivityResult(i, i2, intent);
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return ((GlifPreferenceLayout) viewGroup).onCreateRecyclerView(layoutInflater, viewGroup, bundle);
        }

        @Override // com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment
        protected Class<? extends ChooseLockGeneric.InternalActivity> getInternalActivityClass() {
            return InternalActivity.class;
        }

        @Override // com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment
        protected void disableUnusablePreferences(int i, boolean z) {
            super.disableUnusablePreferencesImpl(Math.max(i, 65536), true);
        }

        @Override // com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment
        protected void addPreferences() {
            if (this.mForFingerprint) {
                super.addPreferences();
            } else {
                addPreferencesFromResource(R.xml.setup_security_settings_picker);
            }
        }

        @Override // com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
        public boolean onPreferenceTreeClick(Preference preference) {
            if ("unlock_set_do_later".equals(preference.getKey())) {
                SetupSkipDialog.newInstance(getActivity().getIntent().getBooleanExtra(":settings:frp_supported", false)).show(getFragmentManager());
                return true;
            }
            return super.onPreferenceTreeClick(preference);
        }

        @Override // com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment
        protected Intent getLockPasswordIntent(int i) {
            Context context = getContext();
            Intent lockPasswordIntent = super.getLockPasswordIntent(i);
            SetupChooseLockPassword.modifyIntentForSetup(context, lockPasswordIntent);
            SetupWizardUtils.copySetupExtras(getActivity().getIntent(), lockPasswordIntent);
            return lockPasswordIntent;
        }

        @Override // com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment
        protected Intent getLockPatternIntent() {
            Context context = getContext();
            Intent lockPatternIntent = super.getLockPatternIntent();
            SetupChooseLockPattern.modifyIntentForSetup(context, lockPatternIntent);
            SetupWizardUtils.copySetupExtras(getActivity().getIntent(), lockPatternIntent);
            return lockPatternIntent;
        }

        @Override // com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment
        protected Intent getEncryptionInterstitialIntent(Context context, int i, boolean z, Intent intent) {
            Intent intentCreateStartIntent = SetupEncryptionInterstitial.createStartIntent(context, i, z, intent);
            SetupWizardUtils.copySetupExtras(getActivity().getIntent(), intentCreateStartIntent);
            return intentCreateStartIntent;
        }

        @Override // com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment
        protected Intent getFindSensorIntent(Context context) {
            Intent intent = new Intent(context, (Class<?>) SetupFingerprintEnrollFindSensor.class);
            SetupWizardUtils.copySetupExtras(getActivity().getIntent(), intent);
            return intent;
        }
    }

    public static class InternalActivity extends ChooseLockGeneric.InternalActivity {

        public static class InternalSetupChooseLockGenericFragment extends ChooseLockGeneric.ChooseLockGenericFragment {
            @Override // com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment
            protected boolean canRunBeforeDeviceProvisioned() {
                return true;
            }
        }

        @Override // com.android.car.developeroptions.password.ChooseLockGeneric, com.android.car.developeroptions.SettingsActivity
        protected boolean isValidFragment(String str) {
            return InternalSetupChooseLockGenericFragment.class.getName().equals(str);
        }

        @Override // com.android.car.developeroptions.password.ChooseLockGeneric
        Class<? extends Fragment> getFragmentClass() {
            return InternalSetupChooseLockGenericFragment.class;
        }
    }
}
