package com.android.car.developeroptions.notification;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.RestrictedRadioButton;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.SetupRedactionInterstitial;
import com.android.car.developeroptions.SetupWizardUtils;
import com.android.car.developeroptions.Utils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.GlifLayout;

/* JADX INFO: loaded from: classes.dex */
public class RedactionInterstitial extends SettingsActivity {
    @Override // com.android.car.developeroptions.SettingsActivity, android.app.Activity
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", RedactionInterstitialFragment.class.getName());
        return intent;
    }

    @Override // com.android.car.developeroptions.SettingsActivity, android.app.Activity, android.view.ContextThemeWrapper
    protected void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, SetupWizardUtils.getTheme(getIntent()), z);
    }

    @Override // com.android.car.developeroptions.SettingsActivity
    protected boolean isValidFragment(String str) {
        return RedactionInterstitialFragment.class.getName().equals(str);
    }

    @Override // com.android.car.developeroptions.SettingsActivity, com.android.car.developeroptions.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((LinearLayout) findViewById(R.id.content_parent)).setFitsSystemWindows(false);
    }

    public static Intent createStartIntent(Context context, int i) {
        return new Intent(context, (Class<?>) RedactionInterstitial.class).putExtra(":settings:show_fragment_title_resid", UserManager.get(context).isManagedProfile(i) ? R.string.lock_screen_notifications_interstitial_title_profile : R.string.lock_screen_notifications_interstitial_title).putExtra("android.intent.extra.USER_ID", i);
    }

    public static class RedactionInterstitialFragment extends SettingsPreferenceFragment implements RadioGroup.OnCheckedChangeListener {
        private RadioGroup mRadioGroup;
        private RestrictedRadioButton mRedactSensitiveButton;
        private RestrictedRadioButton mShowAllButton;
        private int mUserId;

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 74;
        }

        @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.redaction_interstitial, viewGroup, false);
        }

        @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            this.mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
            this.mShowAllButton = (RestrictedRadioButton) view.findViewById(R.id.show_all);
            this.mRedactSensitiveButton = (RestrictedRadioButton) view.findViewById(R.id.redact_sensitive);
            this.mRadioGroup.setOnCheckedChangeListener(this);
            this.mUserId = Utils.getUserIdFromBundle(getContext(), getActivity().getIntent().getExtras());
            if (UserManager.get(getContext()).isManagedProfile(this.mUserId)) {
                ((TextView) view.findViewById(R.id.message)).setText(R.string.lock_screen_notifications_interstitial_message_profile);
                this.mShowAllButton.setText(R.string.lock_screen_notifications_summary_show_profile);
                this.mRedactSensitiveButton.setText(R.string.lock_screen_notifications_summary_hide_profile);
                ((RadioButton) view.findViewById(R.id.hide_all)).setVisibility(8);
            }
            FooterBarMixin footerBarMixin = (FooterBarMixin) ((GlifLayout) view.findViewById(R.id.setup_wizard_layout)).getMixin(FooterBarMixin.class);
            FooterButton.Builder builder = new FooterButton.Builder(getContext());
            builder.setText(R.string.app_notifications_dialog_done);
            builder.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$RedactionInterstitial$RedactionInterstitialFragment$IR6YF-9Oml4evRQzLiw0x0hRxzA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.onDoneButtonClicked(view2);
                }
            });
            builder.setButtonType(5);
            builder.setTheme(R.style.SudGlifButton_Primary);
            footerBarMixin.setPrimaryButton(builder.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDoneButtonClicked(View view) {
            SetupRedactionInterstitial.setEnabled(getContext(), false);
            RedactionInterstitial redactionInterstitial = (RedactionInterstitial) getActivity();
            if (redactionInterstitial != null) {
                redactionInterstitial.setResult(-1, null);
                finish();
            }
        }

        @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
        public void onResume() {
            super.onResume();
            checkNotificationFeaturesAndSetDisabled(this.mShowAllButton, 12);
            checkNotificationFeaturesAndSetDisabled(this.mRedactSensitiveButton, 4);
            loadFromSettings();
        }

        private void checkNotificationFeaturesAndSetDisabled(RestrictedRadioButton restrictedRadioButton, int i) {
            restrictedRadioButton.setDisabledByAdmin(RestrictedLockUtilsInternal.checkIfKeyguardFeaturesDisabled(getActivity(), i, this.mUserId));
        }

        private void loadFromSettings() {
            boolean z = UserManager.get(getContext()).isManagedProfile(this.mUserId) || Settings.Secure.getIntForUser(getContentResolver(), "lock_screen_show_notifications", 0, this.mUserId) != 0;
            boolean z2 = Settings.Secure.getIntForUser(getContentResolver(), "lock_screen_allow_private_notifications", 1, this.mUserId) != 0;
            int i = R.id.hide_all;
            if (z) {
                if (z2 && !this.mShowAllButton.isDisabledByAdmin()) {
                    i = R.id.show_all;
                } else if (!this.mRedactSensitiveButton.isDisabledByAdmin()) {
                    i = R.id.redact_sensitive;
                }
            }
            this.mRadioGroup.check(i);
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int i2 = i == R.id.show_all ? 1 : 0;
            int i3 = i == R.id.hide_all ? 0 : 1;
            Settings.Secure.putIntForUser(getContentResolver(), "lock_screen_allow_private_notifications", i2, this.mUserId);
            Settings.Secure.putIntForUser(getContentResolver(), "lock_screen_show_notifications", i3, this.mUserId);
        }
    }
}
