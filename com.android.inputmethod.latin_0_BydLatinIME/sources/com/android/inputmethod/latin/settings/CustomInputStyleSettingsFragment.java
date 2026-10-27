package com.android.inputmethod.latin.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.RichInputMethodManager;
import com.android.inputmethod.latin.settings.CustomInputStylePreference;
import com.android.inputmethod.latin.utils.AdditionalSubtypeUtils;
import com.android.inputmethod.latin.utils.DialogUtils;
import com.android.inputmethod.latin.utils.IntentUtils;
import com.android.inputmethod.latin.utils.SubtypeLocaleUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class CustomInputStyleSettingsFragment extends PreferenceFragment implements CustomInputStylePreference.Listener {
    private static final boolean DEBUG_CUSTOM_INPUT_STYLES = true;
    private static final String KEY_IS_ADDING_NEW_SUBTYPE = "is_adding_new_subtype";
    private static final String KEY_IS_SUBTYPE_ENABLER_NOTIFICATION_DIALOG_OPEN = "is_subtype_enabler_notification_dialog_open";
    private static final String KEY_SUBTYPE_FOR_SUBTYPE_ENABLER = "subtype_for_subtype_enabler";
    private static final String TAG = "CustomInputStyleSettingsFragment";
    private boolean mIsAddingNewSubtype;
    private CustomInputStylePreference.KeyboardLayoutSetAdapter mKeyboardLayoutSetAdapter;
    private SharedPreferences mPrefs;
    private RichInputMethodManager mRichImm;
    private AlertDialog mSubtypeEnablerNotificationDialog;
    private CustomInputStylePreference.SubtypeLocaleAdapter mSubtypeLocaleAdapter;
    private String mSubtypePreferenceKeyForSubtypeEnabler;

    static void updateCustomInputStylesSummary(final Preference pref) {
        SubtypeLocaleUtils.init(pref.getContext());
        InputMethodSubtype[] inputMethodSubtypeArrCreateAdditionalSubtypesArray = AdditionalSubtypeUtils.createAdditionalSubtypesArray(Settings.readPrefAdditionalSubtypes(pref.getSharedPreferences(), pref.getContext().getResources()));
        ArrayList arrayList = new ArrayList();
        for (InputMethodSubtype inputMethodSubtype : inputMethodSubtypeArrCreateAdditionalSubtypesArray) {
            arrayList.add(SubtypeLocaleUtils.getSubtypeDisplayNameInSystemLocale(inputMethodSubtype));
        }
        pref.setSummary(TextUtils.join(", ", arrayList));
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPrefs = getPreferenceManager().getSharedPreferences();
        RichInputMethodManager.init(getActivity());
        this.mRichImm = RichInputMethodManager.getInstance();
        addPreferencesFromResource(R.xml.additional_subtype_settings);
        setHasOptionsMenu(true);
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        ViewCompat.setLayoutDirection(viewOnCreateView, 3);
        return viewOnCreateView;
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onActivityCreated(final Bundle savedInstanceState) {
        Activity activity = getActivity();
        this.mSubtypeLocaleAdapter = new CustomInputStylePreference.SubtypeLocaleAdapter(activity);
        this.mKeyboardLayoutSetAdapter = new CustomInputStylePreference.KeyboardLayoutSetAdapter(activity);
        String prefAdditionalSubtypes = Settings.readPrefAdditionalSubtypes(this.mPrefs, getResources());
        Log.i(TAG, "Load custom input styles: " + prefAdditionalSubtypes);
        setPrefSubtypes(prefAdditionalSubtypes, activity);
        boolean z = savedInstanceState != null && savedInstanceState.containsKey(KEY_IS_ADDING_NEW_SUBTYPE);
        this.mIsAddingNewSubtype = z;
        if (z) {
            getPreferenceScreen().addPreference(CustomInputStylePreference.newIncompleteSubtypePreference(activity, this));
        }
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null || !savedInstanceState.containsKey(KEY_IS_SUBTYPE_ENABLER_NOTIFICATION_DIALOG_OPEN)) {
            return;
        }
        this.mSubtypePreferenceKeyForSubtypeEnabler = savedInstanceState.getString(KEY_SUBTYPE_FOR_SUBTYPE_ENABLER);
        AlertDialog alertDialogCreateDialog = createDialog();
        this.mSubtypeEnablerNotificationDialog = alertDialogCreateDialog;
        alertDialogCreateDialog.show();
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mIsAddingNewSubtype) {
            outState.putBoolean(KEY_IS_ADDING_NEW_SUBTYPE, true);
        }
        AlertDialog alertDialog = this.mSubtypeEnablerNotificationDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        outState.putBoolean(KEY_IS_SUBTYPE_ENABLER_NOTIFICATION_DIALOG_OPEN, true);
        outState.putString(KEY_SUBTYPE_FOR_SUBTYPE_ENABLER, this.mSubtypePreferenceKeyForSubtypeEnabler);
    }

    @Override // com.android.inputmethod.latin.settings.CustomInputStylePreference.Listener
    public void onRemoveCustomInputStyle(final CustomInputStylePreference stylePref) {
        this.mIsAddingNewSubtype = false;
        getPreferenceScreen().removePreference(stylePref);
        this.mRichImm.setAdditionalInputMethodSubtypes(getSubtypes());
    }

    @Override // com.android.inputmethod.latin.settings.CustomInputStylePreference.Listener
    public void onSaveCustomInputStyle(final CustomInputStylePreference stylePref) {
        InputMethodSubtype subtype = stylePref.getSubtype();
        if (stylePref.hasBeenModified()) {
            if (findDuplicatedSubtype(subtype) == null) {
                this.mRichImm.setAdditionalInputMethodSubtypes(getSubtypes());
                return;
            }
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            preferenceScreen.removePreference(stylePref);
            stylePref.revert();
            preferenceScreen.addPreference(stylePref);
            showSubtypeAlreadyExistsToast(subtype);
        }
    }

    @Override // com.android.inputmethod.latin.settings.CustomInputStylePreference.Listener
    public void onAddCustomInputStyle(final CustomInputStylePreference stylePref) {
        this.mIsAddingNewSubtype = false;
        InputMethodSubtype subtype = stylePref.getSubtype();
        if (findDuplicatedSubtype(subtype) == null) {
            this.mRichImm.setAdditionalInputMethodSubtypes(getSubtypes());
            this.mSubtypePreferenceKeyForSubtypeEnabler = stylePref.getKey();
            AlertDialog alertDialogCreateDialog = createDialog();
            this.mSubtypeEnablerNotificationDialog = alertDialogCreateDialog;
            alertDialogCreateDialog.show();
            return;
        }
        getPreferenceScreen().removePreference(stylePref);
        showSubtypeAlreadyExistsToast(subtype);
    }

    @Override // com.android.inputmethod.latin.settings.CustomInputStylePreference.Listener
    public CustomInputStylePreference.SubtypeLocaleAdapter getSubtypeLocaleAdapter() {
        return this.mSubtypeLocaleAdapter;
    }

    @Override // com.android.inputmethod.latin.settings.CustomInputStylePreference.Listener
    public CustomInputStylePreference.KeyboardLayoutSetAdapter getKeyboardLayoutSetAdapter() {
        return this.mKeyboardLayoutSetAdapter;
    }

    private void showSubtypeAlreadyExistsToast(final InputMethodSubtype subtype) {
        Activity activity = getActivity();
        Toast.makeText(activity, activity.getResources().getString(R.string.custom_input_style_already_exists, SubtypeLocaleUtils.getSubtypeDisplayNameInSystemLocale(subtype)), 0).show();
    }

    private InputMethodSubtype findDuplicatedSubtype(final InputMethodSubtype subtype) {
        return this.mRichImm.findSubtypeByLocaleAndKeyboardLayoutSet(subtype.getLocale(), SubtypeLocaleUtils.getKeyboardLayoutSetName(subtype));
    }

    private AlertDialog createDialog() {
        final String inputMethodIdOfThisIme = this.mRichImm.getInputMethodIdOfThisIme();
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogUtils.getPlatformDialogThemeContext(getActivity()));
        builder.setTitle(R.string.custom_input_styles_title).setMessage(R.string.custom_input_style_note_message).setNegativeButton(R.string.not_now, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.enable, new DialogInterface.OnClickListener() { // from class: com.android.inputmethod.latin.settings.CustomInputStyleSettingsFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                CustomInputStyleSettingsFragment.this.startActivity(IntentUtils.getInputLanguageSelectionIntent(inputMethodIdOfThisIme, 337641472));
            }
        });
        return builder.create();
    }

    private void setPrefSubtypes(final String prefSubtypes, final Context context) {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.removeAll();
        for (InputMethodSubtype inputMethodSubtype : AdditionalSubtypeUtils.createAdditionalSubtypesArray(prefSubtypes)) {
            preferenceScreen.addPreference(new CustomInputStylePreference(context, inputMethodSubtype, this));
        }
    }

    private InputMethodSubtype[] getSubtypes() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        ArrayList arrayList = new ArrayList();
        int preferenceCount = preferenceScreen.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceScreen.getPreference(i);
            if (preference instanceof CustomInputStylePreference) {
                CustomInputStylePreference customInputStylePreference = (CustomInputStylePreference) preference;
                if (!customInputStylePreference.isIncomplete()) {
                    arrayList.add(customInputStylePreference.getSubtype());
                }
            }
        }
        return (InputMethodSubtype[]) arrayList.toArray(new InputMethodSubtype[arrayList.size()]);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        String prefAdditionalSubtypes = Settings.readPrefAdditionalSubtypes(this.mPrefs, getResources());
        InputMethodSubtype[] subtypes = getSubtypes();
        String strCreatePrefSubtypes = AdditionalSubtypeUtils.createPrefSubtypes(subtypes);
        Log.i(TAG, "Save custom input styles: " + strCreatePrefSubtypes);
        if (strCreatePrefSubtypes.equals(prefAdditionalSubtypes)) {
            return;
        }
        Settings.writePrefAdditionalSubtypes(this.mPrefs, strCreatePrefSubtypes);
        this.mRichImm.setAdditionalInputMethodSubtypes(subtypes);
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.add_style, menu);
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.action_add_style) {
            CustomInputStylePreference customInputStylePreferenceNewIncompleteSubtypePreference = CustomInputStylePreference.newIncompleteSubtypePreference(getActivity(), this);
            getPreferenceScreen().addPreference(customInputStylePreferenceNewIncompleteSubtypePreference);
            customInputStylePreferenceNewIncompleteSubtypePreference.show();
            this.mIsAddingNewSubtype = true;
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
