package com.android.car.developeroptions.inputmethod;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.textservice.SpellCheckerInfo;
import android.view.textservice.SpellCheckerSubtype;
import android.view.textservice.TextServicesManager;
import android.widget.Switch;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.widget.SwitchBar;

/* JADX INFO: loaded from: classes.dex */
public class SpellCheckersSettings extends SettingsPreferenceFragment implements SwitchBar.OnSwitchChangeListener, Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private SpellCheckerInfo mCurrentSci;
    private AlertDialog mDialog = null;
    private SpellCheckerInfo[] mEnabledScis;
    private Preference mSpellCheckerLanaguagePref;
    private SwitchBar mSwitchBar;
    private TextServicesManager mTsm;

    /* JADX INFO: Access modifiers changed from: private */
    public static int convertDialogItemIdToSubtypeIndex(int i) {
        return i - 1;
    }

    private static int convertSubtypeIndexToDialogItemId(int i) {
        return i + 1;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 59;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.spellchecker_prefs);
        Preference preferenceFindPreference = findPreference("spellchecker_language");
        this.mSpellCheckerLanaguagePref = preferenceFindPreference;
        preferenceFindPreference.setOnPreferenceClickListener(this);
        TextServicesManager textServicesManager = (TextServicesManager) getSystemService("textservices");
        this.mTsm = textServicesManager;
        this.mCurrentSci = textServicesManager.getCurrentSpellChecker();
        this.mEnabledScis = this.mTsm.getEnabledSpellCheckers();
        populatePreferenceScreen();
    }

    private void populatePreferenceScreen() {
        SpellCheckerPreference spellCheckerPreference = new SpellCheckerPreference(getPrefContext(), this.mEnabledScis);
        spellCheckerPreference.setTitle(R.string.default_spell_checker);
        SpellCheckerInfo[] spellCheckerInfoArr = this.mEnabledScis;
        if ((spellCheckerInfoArr == null ? 0 : spellCheckerInfoArr.length) > 0) {
            spellCheckerPreference.setSummary("%s");
        } else {
            spellCheckerPreference.setSummary(R.string.spell_checker_not_selected);
        }
        spellCheckerPreference.setKey("default_spellchecker");
        spellCheckerPreference.setOnPreferenceChangeListener(this);
        getPreferenceScreen().addPreference(spellCheckerPreference);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SwitchBar switchBar = ((SettingsActivity) getActivity()).getSwitchBar();
        this.mSwitchBar = switchBar;
        switchBar.setSwitchBarText(R.string.spell_checker_master_switch_title, R.string.spell_checker_master_switch_title);
        this.mSwitchBar.show();
        this.mSwitchBar.addOnSwitchChangeListener(this);
        updatePreferenceScreen();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mSwitchBar.removeOnSwitchChangeListener(this);
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r2, boolean z) {
        Settings.Secure.putInt(getContentResolver(), "spell_checker_enabled", z ? 1 : 0);
        updatePreferenceScreen();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreferenceScreen() {
        this.mCurrentSci = this.mTsm.getCurrentSpellChecker();
        boolean zIsSpellCheckerEnabled = this.mTsm.isSpellCheckerEnabled();
        this.mSwitchBar.setChecked(zIsSpellCheckerEnabled);
        boolean z = false;
        this.mSpellCheckerLanaguagePref.setSummary(getSpellCheckerSubtypeLabel(this.mCurrentSci, this.mCurrentSci != null ? this.mTsm.getCurrentSpellCheckerSubtype(false) : null));
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int preferenceCount = preferenceScreen.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceScreen.getPreference(i);
            preference.setEnabled(zIsSpellCheckerEnabled);
            if (preference instanceof SpellCheckerPreference) {
                ((SpellCheckerPreference) preference).setSelected(this.mCurrentSci);
            }
        }
        Preference preference2 = this.mSpellCheckerLanaguagePref;
        if (zIsSpellCheckerEnabled && this.mCurrentSci != null) {
            z = true;
        }
        preference2.setEnabled(z);
    }

    private CharSequence getSpellCheckerSubtypeLabel(SpellCheckerInfo spellCheckerInfo, SpellCheckerSubtype spellCheckerSubtype) {
        if (spellCheckerInfo == null) {
            return getString(R.string.spell_checker_not_selected);
        }
        if (spellCheckerSubtype == null) {
            return getString(R.string.use_system_language_to_select_input_method_subtypes);
        }
        return spellCheckerSubtype.getDisplayName(getActivity(), spellCheckerInfo.getPackageName(), spellCheckerInfo.getServiceInfo().applicationInfo);
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        if (preference != this.mSpellCheckerLanaguagePref) {
            return false;
        }
        showChooseLanguageDialog();
        return true;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        SpellCheckerInfo spellCheckerInfo = (SpellCheckerInfo) obj;
        if ((spellCheckerInfo.getServiceInfo().applicationInfo.flags & 1) != 0) {
            changeCurrentSpellChecker(spellCheckerInfo);
            return true;
        }
        showSecurityWarnDialog(spellCheckerInfo);
        return false;
    }

    private void showChooseLanguageDialog() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mDialog.dismiss();
        }
        final SpellCheckerInfo currentSpellChecker = this.mTsm.getCurrentSpellChecker();
        if (currentSpellChecker == null) {
            return;
        }
        SpellCheckerSubtype currentSpellCheckerSubtype = this.mTsm.getCurrentSpellCheckerSubtype(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.phone_language);
        int subtypeCount = currentSpellChecker.getSubtypeCount();
        CharSequence[] charSequenceArr = new CharSequence[subtypeCount + 1];
        charSequenceArr[0] = getSpellCheckerSubtypeLabel(currentSpellChecker, null);
        int i = 0;
        for (int i2 = 0; i2 < subtypeCount; i2++) {
            SpellCheckerSubtype subtypeAt = currentSpellChecker.getSubtypeAt(i2);
            int iConvertSubtypeIndexToDialogItemId = convertSubtypeIndexToDialogItemId(i2);
            charSequenceArr[iConvertSubtypeIndexToDialogItemId] = getSpellCheckerSubtypeLabel(currentSpellChecker, subtypeAt);
            if (subtypeAt.equals(currentSpellCheckerSubtype)) {
                i = iConvertSubtypeIndexToDialogItemId;
            }
        }
        builder.setSingleChoiceItems(charSequenceArr, i, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.inputmethod.SpellCheckersSettings.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                int iHashCode;
                if (i3 == 0) {
                    iHashCode = 0;
                } else {
                    iHashCode = currentSpellChecker.getSubtypeAt(SpellCheckersSettings.convertDialogItemIdToSubtypeIndex(i3)).hashCode();
                }
                Settings.Secure.putInt(SpellCheckersSettings.this.getContentResolver(), "selected_spell_checker_subtype", iHashCode);
                dialogInterface.dismiss();
                SpellCheckersSettings.this.updatePreferenceScreen();
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        this.mDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    private void showSecurityWarnDialog(final SpellCheckerInfo spellCheckerInfo) {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mDialog.dismiss();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(android.R.string.dialog_alert_title);
        builder.setMessage(getString(R.string.spellchecker_security_warning, spellCheckerInfo.loadLabel(getPackageManager())));
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.inputmethod.SpellCheckersSettings.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                SpellCheckersSettings.this.changeCurrentSpellChecker(spellCheckerInfo);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener(this) { // from class: com.android.car.developeroptions.inputmethod.SpellCheckersSettings.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        this.mDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeCurrentSpellChecker(SpellCheckerInfo spellCheckerInfo) {
        Settings.Secure.putString(getContentResolver(), "selected_spell_checker", spellCheckerInfo.getId());
        Settings.Secure.putInt(getContentResolver(), "selected_spell_checker_subtype", 0);
        updatePreferenceScreen();
    }
}
