package com.android.car.settings.quicksettings;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.telephony.TelephonyManager;
import android.view.View;
import com.android.car.settings.R;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.FragmentHost;
import com.android.car.settings.quicksettings.QuickSettingGridAdapter;
import com.android.settingslib.net.DataUsageController;

/* JADX INFO: loaded from: classes5.dex */
public class CelluarTile implements QuickSettingGridAdapter.Tile, DataUsageController.Callback {
    private final boolean mAvailable;
    private final String mCarrierName;
    private final Context mContext;
    private final DataUsageController mDataUsageController;
    private final View.OnLongClickListener mLaunchDisplaySettings;
    private QuickSettingGridAdapter.Tile.State mState;
    private final StateChangedListener mStateChangedListener;

    CelluarTile(final Context context, StateChangedListener stateChangedListener, FragmentHost fragmentHost) {
        this.mState = QuickSettingGridAdapter.Tile.State.ON;
        this.mStateChangedListener = stateChangedListener;
        this.mContext = context;
        TelephonyManager manager = (TelephonyManager) context.getSystemService("phone");
        DataUsageController dataUsageController = new DataUsageController(this.mContext);
        this.mDataUsageController = dataUsageController;
        dataUsageController.setCallback(this);
        boolean zIsMobileDataSupported = this.mDataUsageController.isMobileDataSupported();
        this.mAvailable = zIsMobileDataSupported;
        this.mState = (zIsMobileDataSupported && this.mDataUsageController.isMobileDataEnabled()) ? QuickSettingGridAdapter.Tile.State.ON : QuickSettingGridAdapter.Tile.State.OFF;
        this.mCarrierName = this.mAvailable ? manager.getNetworkOperatorName() : null;
        this.mLaunchDisplaySettings = new View.OnLongClickListener() { // from class: com.android.car.settings.quicksettings.-$$Lambda$CelluarTile$s2ngR2zR3yN6sxliL_H8Nx0IYAA
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return CelluarTile.lambda$new$0(context, view);
            }
        };
    }

    static /* synthetic */ boolean lambda$new$0(Context context, View v) {
        context.startActivity(new Intent(context, (Class<?>) CarSettingActivities.MobileNetworkActivity.class));
        return true;
    }

    @Override // com.android.settingslib.net.DataUsageController.Callback
    public void onMobileDataEnabled(boolean enabled) {
        this.mState = enabled ? QuickSettingGridAdapter.Tile.State.ON : QuickSettingGridAdapter.Tile.State.OFF;
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public View.OnLongClickListener getOnLongClickListener() {
        return this.mLaunchDisplaySettings;
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public boolean isAvailable() {
        return this.mAvailable;
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public Drawable getIcon() {
        return this.mContext.getDrawable(R.drawable.ic_cellular_data);
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public String getText() {
        return this.mCarrierName;
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
        this.mDataUsageController.setMobileDataEnabled(!r0.isMobileDataEnabled());
    }
}
