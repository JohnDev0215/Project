package androidx.leanback.preference;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

/* JADX INFO: loaded from: classes3.dex */
public abstract class LeanbackSettingsFragmentCompat extends Fragment implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback, PreferenceFragmentCompat.OnPreferenceStartScreenCallback, PreferenceFragmentCompat.OnPreferenceDisplayDialogCallback {
    private static final String PREFERENCE_FRAGMENT_TAG = "androidx.leanback.preference.LeanbackSettingsFragment.PREFERENCE_FRAGMENT";
    private final RootViewOnKeyListener mRootViewOnKeyListener = new RootViewOnKeyListener();

    public abstract void onPreferenceStartInitialScreen();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.leanback_settings_fragment, container, false);
        return v;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            onPreferenceStartInitialScreen();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LeanbackSettingsRootView rootView = (LeanbackSettingsRootView) getView();
        if (rootView != null) {
            rootView.setOnBackKeyListener(this.mRootViewOnKeyListener);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LeanbackSettingsRootView rootView = (LeanbackSettingsRootView) getView();
        if (rootView != null) {
            rootView.setOnBackKeyListener(null);
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat.OnPreferenceDisplayDialogCallback
    public boolean onPreferenceDisplayDialog(PreferenceFragmentCompat caller, Preference pref) {
        if (caller == null) {
            throw new IllegalArgumentException("Cannot display dialog for preference " + pref + ", Caller must not be null!");
        }
        if (pref instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) pref;
            Fragment f = LeanbackListPreferenceDialogFragmentCompat.newInstanceSingle(listPreference.getKey());
            f.setTargetFragment(caller, 0);
            startPreferenceFragment(f);
            return true;
        }
        if (pref instanceof MultiSelectListPreference) {
            MultiSelectListPreference listPreference2 = (MultiSelectListPreference) pref;
            Fragment f2 = LeanbackListPreferenceDialogFragmentCompat.newInstanceMulti(listPreference2.getKey());
            f2.setTargetFragment(caller, 0);
            startPreferenceFragment(f2);
            return true;
        }
        if (!(pref instanceof EditTextPreference)) {
            return false;
        }
        Fragment f3 = LeanbackEditTextPreferenceDialogFragmentCompat.newInstance(pref.getKey());
        f3.setTargetFragment(caller, 0);
        startPreferenceFragment(f3);
        return true;
    }

    public void startPreferenceFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        Fragment prevFragment = getChildFragmentManager().findFragmentByTag(PREFERENCE_FRAGMENT_TAG);
        if (prevFragment != null) {
            transaction.addToBackStack(null).replace(R.id.settings_preference_fragment_container, fragment, PREFERENCE_FRAGMENT_TAG);
        } else {
            transaction.add(R.id.settings_preference_fragment_container, fragment, PREFERENCE_FRAGMENT_TAG);
        }
        transaction.commit();
    }

    public void startImmersiveFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        Fragment preferenceFragment = getChildFragmentManager().findFragmentByTag(PREFERENCE_FRAGMENT_TAG);
        if (preferenceFragment != null && !preferenceFragment.isHidden()) {
            transaction.remove(preferenceFragment);
        }
        transaction.add(R.id.settings_dialog_container, fragment).addToBackStack(null).commit();
    }

    private class RootViewOnKeyListener implements View.OnKeyListener {
        RootViewOnKeyListener() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == 4) {
                return LeanbackSettingsFragmentCompat.this.getChildFragmentManager().popBackStackImmediate();
            }
            return false;
        }
    }
}
