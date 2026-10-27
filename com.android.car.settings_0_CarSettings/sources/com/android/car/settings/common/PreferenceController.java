package com.android.car.settings.common;

import android.car.drivingstate.CarUxRestrictions;
import android.car.drivingstate.CarUxRestrictionsManager;
import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.ui.preference.UxRestrictablePreference;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes5.dex */
public abstract class PreferenceController<V extends Preference> implements DefaultLifecycleObserver, CarUxRestrictionsManager.OnUxRestrictionsChangedListener {
    public static final int AVAILABLE = 0;
    public static final int AVAILABLE_FOR_VIEWING = 4;
    public static final int CONDITIONALLY_UNAVAILABLE = 1;
    public static final int DISABLED_FOR_USER = 3;
    public static final int UNSUPPORTED_ON_DEVICE = 2;
    private final boolean mAlwaysIgnoreUxRestrictions;
    private final Context mContext;
    private final FragmentController mFragmentController;
    private boolean mIsCreated;
    private V mPreference;
    private final String mPreferenceKey;
    private final Set<String> mPreferencesIgnoringUxRestrictions;
    private final String mRestrictedWhileDrivingMessage;
    private CarUxRestrictions mUxRestrictions;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AvailabilityStatus {
    }

    protected abstract Class<V> getPreferenceType();

    public PreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        this.mContext = context;
        this.mPreferenceKey = preferenceKey;
        this.mFragmentController = fragmentController;
        this.mUxRestrictions = uxRestrictions;
        this.mPreferencesIgnoringUxRestrictions = new HashSet(Arrays.asList(this.mContext.getResources().getStringArray(R.array.config_ignore_ux_restrictions)));
        this.mAlwaysIgnoreUxRestrictions = this.mContext.getResources().getBoolean(R.bool.config_always_ignore_ux_restrictions);
        this.mRestrictedWhileDrivingMessage = this.mContext.getResources().getString(R.string.car_ui_restricted_while_driving);
    }

    protected final Context getContext() {
        return this.mContext;
    }

    protected final String getPreferenceKey() {
        return this.mPreferenceKey;
    }

    protected final FragmentController getFragmentController() {
        return this.mFragmentController;
    }

    protected final CarUxRestrictions getUxRestrictions() {
        return this.mUxRestrictions;
    }

    protected final V getPreference() {
        return this.mPreference;
    }

    final void setPreference(Preference preference) {
        PreferenceUtil.requirePreferenceType(preference, getPreferenceType());
        V vCast = getPreferenceType().cast(preference);
        this.mPreference = vCast;
        vCast.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.settings.common.-$$Lambda$PreferenceController$CMjkrEXKKbb2EC5X47CfF01qdfE
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference2, Object obj) {
                return this.f$0.lambda$setPreference$0$PreferenceController(preference2, obj);
            }
        });
        this.mPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.common.-$$Lambda$PreferenceController$xrXKtrsZEWHONZRbhBdLicUKJ2o
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return this.f$0.lambda$setPreference$1$PreferenceController(preference2);
            }
        });
        checkInitialized();
    }

    public /* synthetic */ boolean lambda$setPreference$0$PreferenceController(Preference changedPref, Object newValue) {
        return handlePreferenceChanged(getPreferenceType().cast(changedPref), newValue);
    }

    public /* synthetic */ boolean lambda$setPreference$1$PreferenceController(Preference clickedPref) {
        return handlePreferenceClicked(getPreferenceType().cast(clickedPref));
    }

    @Override // android.car.drivingstate.CarUxRestrictionsManager.OnUxRestrictionsChangedListener
    public final void onUxRestrictionsChanged(CarUxRestrictions uxRestrictions) {
        this.mUxRestrictions = uxRestrictions;
        refreshUi();
    }

    public final void refreshUi() {
        if (!this.mIsCreated) {
            return;
        }
        if (isAvailable()) {
            this.mPreference.setVisible(true);
            this.mPreference.setEnabled(getAvailabilityStatus() != 4);
            updateState(this.mPreference);
            onApplyUxRestrictions(this.mUxRestrictions);
            return;
        }
        this.mPreference.setVisible(false);
    }

    private boolean isAvailable() {
        int availabilityStatus = getAvailabilityStatus();
        return availabilityStatus == 0 || availabilityStatus == 4;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public final void onCreate(LifecycleOwner owner) {
        if (getAvailabilityStatus() == 2) {
            this.mPreference.setVisible(false);
            return;
        }
        onCreateInternal();
        this.mIsCreated = true;
        refreshUi();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public final void onStart(LifecycleOwner owner) {
        if (getAvailabilityStatus() == 2) {
            return;
        }
        onStartInternal();
        refreshUi();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public final void onResume(LifecycleOwner owner) {
        if (getAvailabilityStatus() == 2) {
            return;
        }
        onResumeInternal();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public final void onPause(LifecycleOwner owner) {
        if (getAvailabilityStatus() == 2) {
            return;
        }
        onPauseInternal();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public final void onStop(LifecycleOwner owner) {
        if (getAvailabilityStatus() == 2) {
            return;
        }
        onStopInternal();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public final void onDestroy(LifecycleOwner owner) {
        if (getAvailabilityStatus() == 2) {
            return;
        }
        this.mIsCreated = false;
        onDestroyInternal();
    }

    protected void checkInitialized() {
    }

    protected int getAvailabilityStatus() {
        return 0;
    }

    protected void onCreateInternal() {
    }

    protected void onStartInternal() {
    }

    protected void onResumeInternal() {
    }

    protected void onPauseInternal() {
    }

    protected void onStopInternal() {
    }

    protected void onDestroyInternal() {
    }

    protected void updateState(V preference) {
    }

    protected void onApplyUxRestrictions(CarUxRestrictions uxRestrictions) {
        boolean restrict = false;
        if (!isUxRestrictionsIgnored(this.mAlwaysIgnoreUxRestrictions, this.mPreferencesIgnoringUxRestrictions) && CarUxRestrictionsHelper.isNoSetup(uxRestrictions) && getAvailabilityStatus() != 4) {
            restrict = true;
        }
        restrictPreference(this.mPreference, restrict);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void restrictPreference(Preference preference, boolean restrict) {
        if (preference instanceof UxRestrictablePreference) {
            UxRestrictablePreference restrictablePreference = (UxRestrictablePreference) preference;
            restrictablePreference.setUxRestricted(restrict);
            restrictablePreference.setOnClickWhileRestrictedListener(new Consumer() { // from class: com.android.car.settings.common.-$$Lambda$PreferenceController$vSdyGkIxQ_WQsqQRl72iRxSEAjo
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$restrictPreference$2$PreferenceController((Preference) obj);
                }
            });
        }
        if (preference instanceof PreferenceGroup) {
            PreferenceGroup preferenceGroup = (PreferenceGroup) preference;
            for (int i = 0; i < preferenceGroup.getPreferenceCount(); i++) {
                restrictPreference(preferenceGroup.getPreference(i), restrict);
            }
        }
    }

    public /* synthetic */ void lambda$restrictPreference$2$PreferenceController(Preference p) {
        Toast.makeText(this.mContext, this.mRestrictedWhileDrivingMessage, 1).show();
    }

    protected boolean handlePreferenceChanged(V preference, Object newValue) {
        return true;
    }

    protected boolean handlePreferenceClicked(V preference) {
        return false;
    }

    protected boolean isUxRestrictionsIgnored(boolean allIgnores, Set prefsThatIgnore) {
        return allIgnores || prefsThatIgnore.contains(this.mPreferenceKey);
    }
}
