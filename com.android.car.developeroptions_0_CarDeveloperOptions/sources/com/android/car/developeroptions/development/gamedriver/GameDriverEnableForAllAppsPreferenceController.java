package com.android.car.developeroptions.development.gamedriver;

import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.development.gamedriver.GameDriverContentObserver;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.development.DevelopmentSettingsEnabler;

/* JADX INFO: loaded from: classes.dex */
public class GameDriverEnableForAllAppsPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener, GameDriverContentObserver.OnGameDriverContentChangedListener, LifecycleObserver, OnStart, OnStop {
    public static final int GAME_DRIVER_ALL_APPS = 1;
    public static final int GAME_DRIVER_DEFAULT = 0;
    public static final int GAME_DRIVER_OFF = 2;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    GameDriverContentObserver mGameDriverContentObserver;
    private SwitchPreference mPreference;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public GameDriverEnableForAllAppsPreferenceController(Context context, String str) {
        super(context, str);
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mGameDriverContentObserver = new GameDriverContentObserver(new Handler(Looper.getMainLooper()), this);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (!DevelopmentSettingsEnabler.isDevelopmentSettingsEnabled(this.mContext) || Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0) == 2) ? 2 : 0;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        SwitchPreference switchPreference = (SwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = switchPreference;
        switchPreference.setOnPreferenceChangeListener(this);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mGameDriverContentObserver.register(this.mContentResolver);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mGameDriverContentObserver.unregister(this.mContentResolver);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        SwitchPreference switchPreference = (SwitchPreference) preference;
        switchPreference.setVisible(isAvailable());
        switchPreference.setChecked(Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0) == 1);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        int i = Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0);
        if (zBooleanValue && i == 1) {
            return true;
        }
        if (!zBooleanValue && (i == 0 || i == 2)) {
            return true;
        }
        Settings.Global.putInt(this.mContentResolver, "game_driver_all_apps", zBooleanValue ? 1 : 0);
        return true;
    }

    @Override // com.android.car.developeroptions.development.gamedriver.GameDriverContentObserver.OnGameDriverContentChangedListener
    public void onGameDriverContentChanged() {
        updateState(this.mPreference);
    }
}
