package com.android.inputmethod.latin.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.utils.ApplicationUtils;
import com.android.inputmethod.latin.utils.FeedbackUtils;
import com.android.inputmethodcommon.InputMethodSettingsFragment;
import com.byd.ime.TestLogger;

/* JADX INFO: loaded from: classes2.dex */
public final class SettingsFragment extends InputMethodSettingsFragment {
    private static final int MENU_ABOUT = 1;
    private static final int MENU_HELP_AND_FEEDBACK = 2;
    private static final int NO_MENU_GROUP = 0;

    @Override // com.android.inputmethodcommon.InputMethodSettingsFragment, android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        TestLogger.logE("onCreate_settingsFragment");
        setHasOptionsMenu(true);
        setInputMethodSettingsCategoryTitle(R.string.language_selection_title);
        setSubtypeEnablerTitle(R.string.select_language);
        addPreferencesFromResource(R.xml.prefs);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.setTitle(ApplicationUtils.getActivityTitleResId(getActivity(), SettingsActivity.class));
        preferenceScreen.removePreference(findPreference(Settings.SCREEN_ACCOUNTS));
        preferenceScreen.removePreference(findPreference("screen_gesture"));
        preferenceScreen.removePreference(findPreference("screen_appearance"));
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        if (FeedbackUtils.isHelpAndFeedbackFormSupported()) {
            menu.add(0, 2, 2, R.string.help_and_feedback);
        }
        int aboutKeyboardTitleResId = FeedbackUtils.getAboutKeyboardTitleResId();
        if (aboutKeyboardTitleResId != 0) {
            menu.add(0, 1, 1, aboutKeyboardTitleResId);
        }
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intent aboutKeyboardIntent;
        Activity activity = getActivity();
        if (!isUserSetupComplete(activity)) {
            return true;
        }
        int itemId = item.getItemId();
        if (itemId == 2) {
            FeedbackUtils.showHelpAndFeedbackForm(activity);
            return true;
        }
        if (itemId == 1 && (aboutKeyboardIntent = FeedbackUtils.getAboutKeyboardIntent(activity)) != null) {
            startActivity(aboutKeyboardIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static boolean isUserSetupComplete(final Activity activity) {
        return Build.VERSION.SDK_INT < 21 || Settings.Secure.getInt(activity.getContentResolver(), "user_setup_complete", 0) != 0;
    }
}
