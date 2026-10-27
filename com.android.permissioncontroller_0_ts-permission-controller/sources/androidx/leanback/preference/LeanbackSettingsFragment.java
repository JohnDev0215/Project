package androidx.leanback.preference;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public abstract class LeanbackSettingsFragment extends Fragment implements PreferenceFragment.OnPreferenceStartFragmentCallback, PreferenceFragment.OnPreferenceStartScreenCallback, PreferenceFragment.OnPreferenceDisplayDialogCallback {
    private static final String PREFERENCE_FRAGMENT_TAG = "androidx.leanback.preference.LeanbackSettingsFragment.PREFERENCE_FRAGMENT";
    private final RootViewOnKeyListener mRootViewOnKeyListener = new RootViewOnKeyListener();

    public abstract void onPreferenceStartInitialScreen();

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.leanback_settings_fragment, container, false);
        return v;
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            onPreferenceStartInitialScreen();
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        LeanbackSettingsRootView rootView = (LeanbackSettingsRootView) getView();
        if (rootView != null) {
            rootView.setOnBackKeyListener(this.mRootViewOnKeyListener);
        }
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        LeanbackSettingsRootView rootView = (LeanbackSettingsRootView) getView();
        if (rootView != null) {
            rootView.setOnBackKeyListener(null);
        }
    }

    @Override // androidx.preference.PreferenceFragment.OnPreferenceDisplayDialogCallback
    public boolean onPreferenceDisplayDialog(PreferenceFragment caller, Preference pref) {
        if (caller == null) {
            throw new IllegalArgumentException("Cannot display dialog for preference " + pref + ", Caller must not be null!");
        }
        if (pref instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) pref;
            Fragment f = LeanbackListPreferenceDialogFragment.newInstanceSingle(listPreference.getKey());
            f.setTargetFragment(caller, 0);
            startPreferenceFragment(f);
            return true;
        }
        if (!(pref instanceof MultiSelectListPreference)) {
            return false;
        }
        MultiSelectListPreference listPreference2 = (MultiSelectListPreference) pref;
        Fragment f2 = LeanbackListPreferenceDialogFragment.newInstanceMulti(listPreference2.getKey());
        f2.setTargetFragment(caller, 0);
        startPreferenceFragment(f2);
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
            if (Build.VERSION.SDK_INT < 23) {
                transaction.add(R.id.settings_preference_fragment_container, new DummyFragment());
            }
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
                return LeanbackSettingsFragment.this.getChildFragmentManager().popBackStackImmediate();
            }
            return false;
        }
    }

    public static class DummyFragment extends Fragment {
        @Override // android.app.Fragment
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = new Space(inflater.getContext());
            v.setVisibility(8);
            return v;
        }
    }
}
