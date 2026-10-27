package com.android.car.settings.quicksettings;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.car.settings.R;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.FragmentHost;
import com.android.car.settings.quicksettings.QuickSettingGridAdapter;

/* JADX INFO: loaded from: classes5.dex */
public class DayNightTile implements QuickSettingGridAdapter.Tile {
    private final Context mContext;
    private int mIconRes = R.drawable.ic_settings_night_display;
    private final View.OnLongClickListener mLaunchDisplaySettings;
    private QuickSettingGridAdapter.Tile.State mState;
    private final StateChangedListener mStateChangedListener;
    private final String mText;
    private final UiModeManager mUiModeManager;

    DayNightTile(final Context context, StateChangedListener stateChangedListener, FragmentHost fragmentHost) {
        this.mState = QuickSettingGridAdapter.Tile.State.ON;
        this.mStateChangedListener = stateChangedListener;
        this.mContext = context;
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        this.mUiModeManager = uiModeManager;
        if (uiModeManager.getNightMode() == 2) {
            this.mState = QuickSettingGridAdapter.Tile.State.ON;
        } else {
            this.mState = QuickSettingGridAdapter.Tile.State.OFF;
        }
        this.mText = this.mContext.getString(R.string.night_mode_tile_label);
        this.mLaunchDisplaySettings = new View.OnLongClickListener() { // from class: com.android.car.settings.quicksettings.-$$Lambda$DayNightTile$y30_2Yu1TZGc54faOB5KDXN4YZw
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return DayNightTile.lambda$new$0(context, view);
            }
        };
    }

    static /* synthetic */ boolean lambda$new$0(Context context, View v) {
        context.startActivity(new Intent(context, (Class<?>) CarSettingActivities.DisplaySettingsActivity.class));
        return true;
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public View.OnLongClickListener getOnLongClickListener() {
        return this.mLaunchDisplaySettings;
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public boolean isAvailable() {
        return true;
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public Drawable getIcon() {
        return this.mContext.getDrawable(this.mIconRes);
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public String getText() {
        return this.mText;
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public QuickSettingGridAdapter.Tile.State getState() {
        return this.mState;
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public void start() {
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public void stop() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (this.mUiModeManager.getNightMode() == 2) {
            this.mUiModeManager.setNightMode(1);
        } else {
            this.mUiModeManager.setNightMode(2);
        }
    }
}
