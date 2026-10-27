package com.android.car.settings.quicksettings;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.car.settings.R;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.FragmentHost;
import com.android.car.settings.quicksettings.QuickSettingGridAdapter;
import com.android.car.settings.wifi.CarWifiManager;
import com.android.car.settings.wifi.WifiUtil;
import com.android.settingslib.wifi.AccessPoint;

/* JADX INFO: loaded from: classes5.dex */
public class WifiTile implements QuickSettingGridAdapter.Tile, CarWifiManager.Listener {
    private final CarWifiManager mCarWifiManager;
    private final Context mContext;
    private final View.OnLongClickListener mLaunchWifiSettings;
    private final StateChangedListener mStateChangedListener;
    private String mText;
    private int mIconRes = R.drawable.ic_settings_wifi;
    private QuickSettingGridAdapter.Tile.State mState = QuickSettingGridAdapter.Tile.State.OFF;

    WifiTile(final Context context, StateChangedListener stateChangedListener, FragmentHost fragmentHost) {
        this.mContext = context;
        this.mLaunchWifiSettings = new View.OnLongClickListener() { // from class: com.android.car.settings.quicksettings.-$$Lambda$WifiTile$7k7sWZF9Zgt4Yd_Nr-RGWwTkn6M
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return WifiTile.lambda$new$0(context, view);
            }
        };
        this.mCarWifiManager = new CarWifiManager(context);
        this.mStateChangedListener = stateChangedListener;
        updateAccessPointSsid();
        onWifiStateChanged(this.mCarWifiManager.getWifiState());
    }

    static /* synthetic */ boolean lambda$new$0(Context context, View v) {
        context.startActivity(new Intent(context, (Class<?>) CarSettingActivities.WifiSettingsActivity.class));
        return true;
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public View.OnLongClickListener getOnLongClickListener() {
        return this.mLaunchWifiSettings;
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public boolean isAvailable() {
        return WifiUtil.isWifiAvailable(this.mContext);
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
        this.mCarWifiManager.addListener(this);
        this.mCarWifiManager.start();
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public void stop() {
        this.mCarWifiManager.removeListener(this);
        this.mCarWifiManager.stop();
        this.mCarWifiManager.destroy();
    }

    @Override // com.android.car.settings.wifi.CarWifiManager.Listener
    public void onAccessPointsChanged() {
        if (updateAccessPointSsid()) {
            this.mStateChangedListener.onStateChanged();
        }
    }

    @Override // com.android.car.settings.wifi.CarWifiManager.Listener
    public void onWifiStateChanged(int state) {
        this.mIconRes = WifiUtil.getIconRes(state);
        int stringId = WifiUtil.getStateDesc(state).intValue();
        if (stringId != 0) {
            this.mText = this.mContext.getString(stringId);
        } else if (!updateAccessPointSsid() && wifiEnabledNotConnected()) {
            this.mText = this.mContext.getString(R.string.wifi_settings);
        }
        this.mState = WifiUtil.isWifiOn(state) ? QuickSettingGridAdapter.Tile.State.ON : QuickSettingGridAdapter.Tile.State.OFF;
        this.mStateChangedListener.onStateChanged();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        this.mCarWifiManager.setWifiEnabled(!r0.isWifiEnabled());
    }

    private boolean wifiEnabledNotConnected() {
        return this.mCarWifiManager.isWifiEnabled() && this.mCarWifiManager.getConnectedAccessPoint() == null;
    }

    private boolean updateAccessPointSsid() {
        AccessPoint accessPoint = this.mCarWifiManager.getConnectedAccessPoint();
        if (accessPoint != null) {
            this.mText = accessPoint.getConfigName();
            return true;
        }
        return false;
    }
}
