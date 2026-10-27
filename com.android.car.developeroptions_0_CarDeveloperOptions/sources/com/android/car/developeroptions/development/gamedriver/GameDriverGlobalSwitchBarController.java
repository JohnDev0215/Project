package com.android.car.developeroptions.development.gamedriver;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.android.car.developeroptions.development.gamedriver.GameDriverContentObserver;
import com.android.car.developeroptions.widget.SwitchWidgetController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.development.DevelopmentSettingsEnabler;

/* JADX INFO: loaded from: classes.dex */
public class GameDriverGlobalSwitchBarController implements SwitchWidgetController.OnSwitchChangeListener, GameDriverContentObserver.OnGameDriverContentChangedListener, LifecycleObserver, OnStart, OnStop {
    private final ContentResolver mContentResolver;
    GameDriverContentObserver mGameDriverContentObserver = new GameDriverContentObserver(new Handler(Looper.getMainLooper()), this);
    SwitchWidgetController mSwitchWidgetController;

    GameDriverGlobalSwitchBarController(Context context, SwitchWidgetController switchWidgetController) {
        this.mContentResolver = context.getContentResolver();
        this.mSwitchWidgetController = switchWidgetController;
        switchWidgetController.setEnabled(DevelopmentSettingsEnabler.isDevelopmentSettingsEnabled(context));
        this.mSwitchWidgetController.setChecked(Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0) != 2);
        this.mSwitchWidgetController.setListener(this);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mSwitchWidgetController.startListening();
        this.mGameDriverContentObserver.register(this.mContentResolver);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mSwitchWidgetController.stopListening();
        this.mGameDriverContentObserver.unregister(this.mContentResolver);
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController.OnSwitchChangeListener
    public boolean onSwitchToggled(boolean z) {
        int i = Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0);
        if (z && (i == 0 || i == 1)) {
            return true;
        }
        if (!z && i == 2) {
            return true;
        }
        Settings.Global.putInt(this.mContentResolver, "game_driver_all_apps", z ? 0 : 2);
        return true;
    }

    @Override // com.android.car.developeroptions.development.gamedriver.GameDriverContentObserver.OnGameDriverContentChangedListener
    public void onGameDriverContentChanged() {
        this.mSwitchWidgetController.setChecked(Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0) != 2);
    }
}
