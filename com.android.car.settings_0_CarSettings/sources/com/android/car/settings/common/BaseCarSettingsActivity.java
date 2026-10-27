package com.android.car.settings.common;

import android.car.drivingstate.CarUxRestrictions;
import android.car.drivingstate.CarUxRestrictionsManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.android.car.apps.common.util.Themes;
import com.android.car.settings.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseCarSettingsActivity extends FragmentActivity implements FragmentHost, CarUxRestrictionsManager.OnUxRestrictionsChangedListener, UxRestrictionsProvider, FragmentManager.OnBackStackChangedListener, PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    private static final Logger LOG = new Logger((Class<?>) BaseCarSettingsActivity.class);
    private CarUxRestrictions mCarUxRestrictions = new CarUxRestrictions.Builder(true, 0, 0).build();
    private ViewGroup mFragmentContainer;
    private View mRestrictedMessage;
    private CarUxRestrictionsHelper mUxRestrictionsHelper;

    protected abstract Fragment getInitialFragment();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_setting_activity);
        this.mFragmentContainer = (ViewGroup) findViewById(R.id.fragment_container);
        if (this.mUxRestrictionsHelper == null) {
            this.mUxRestrictionsHelper = new CarUxRestrictionsHelper(this, this);
        }
        this.mUxRestrictionsHelper.start();
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        this.mRestrictedMessage = findViewById(R.id.restricted_message);
        launchIfDifferent(getInitialFragment());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mUxRestrictionsHelper.stop();
        this.mUxRestrictionsHelper = null;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        hideKeyboard();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        }
    }

    @Override // com.android.car.settings.common.FragmentHost
    public void launchFragment(Fragment fragment) {
        if (fragment instanceof DialogFragment) {
            throw new IllegalArgumentException("cannot launch dialogs with launchFragment() - use showDialog() instead");
        }
        getSupportFragmentManager().beginTransaction().setCustomAnimations(Themes.getAttrResourceId(this, android.R.attr.fragmentOpenEnterAnimation), Themes.getAttrResourceId(this, android.R.attr.fragmentOpenExitAnimation), Themes.getAttrResourceId(this, android.R.attr.fragmentCloseEnterAnimation), Themes.getAttrResourceId(this, android.R.attr.fragmentCloseExitAnimation)).replace(R.id.fragment_container, fragment, Integer.toString(getSupportFragmentManager().getBackStackEntryCount())).addToBackStack(null).commit();
    }

    @Override // com.android.car.settings.common.FragmentHost
    public void goBack() {
        onBackPressed();
    }

    @Override // com.android.car.settings.common.FragmentHost
    public void showBlockingMessage() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.car.drivingstate.CarUxRestrictionsManager.OnUxRestrictionsChangedListener
    public void onUxRestrictionsChanged(CarUxRestrictions restrictionInfo) {
        this.mCarUxRestrictions = restrictionInfo;
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment instanceof CarUxRestrictionsManager.OnUxRestrictionsChangedListener) {
            ((CarUxRestrictionsManager.OnUxRestrictionsChangedListener) currentFragment).onUxRestrictionsChanged(restrictionInfo);
        }
        updateBlockingView(currentFragment);
    }

    @Override // com.android.car.settings.common.UxRestrictionsProvider
    public CarUxRestrictions getCarUxRestrictions() {
        return this.mCarUxRestrictions;
    }

    @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
    public void onBackStackChanged() {
        onUxRestrictionsChanged(getCarUxRestrictions());
    }

    @Override // androidx.preference.PreferenceFragmentCompat.OnPreferenceStartFragmentCallback
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        if (pref.getFragment() != null) {
            Fragment fragment = Fragment.instantiate(this, pref.getFragment(), pref.getExtras());
            launchFragment(fragment);
            return true;
        }
        return false;
    }

    protected void launchIfDifferent(Fragment newFragment) {
        Fragment currentFragment = getCurrentFragment();
        if (newFragment != null && differentFragment(newFragment, currentFragment)) {
            LOG.d("launchIfDifferent: " + newFragment + " replacing " + currentFragment);
            launchFragment(newFragment);
        }
    }

    protected Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }

    private boolean differentFragment(Fragment newFragment, Fragment currentFragment) {
        return currentFragment == null || !currentFragment.getClass().equals(newFragment.getClass());
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService("input_method");
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    private void updateBlockingView(Fragment currentFragment) {
        if ((currentFragment instanceof BaseFragment) && !((BaseFragment) currentFragment).canBeShown(this.mCarUxRestrictions)) {
            this.mRestrictedMessage.setVisibility(0);
            this.mFragmentContainer.setDescendantFocusability(393216);
            this.mFragmentContainer.clearFocus();
            hideKeyboard();
            return;
        }
        this.mRestrictedMessage.setVisibility(8);
        this.mFragmentContainer.setDescendantFocusability(131072);
    }
}
