package com.android.car.settings.quicksettings;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.car.settings.R;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.FragmentHost;
import com.android.car.settings.common.Logger;
import com.android.car.settings.quicksettings.QuickSettingGridAdapter;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothTile implements QuickSettingGridAdapter.Tile {
    private static final Logger LOG = new Logger((Class<?>) BluetoothTile.class);
    private BluetoothAdapter mBluetoothAdapter;
    private final Context mContext;
    private View.OnLongClickListener mLaunchBluetoothSettings;
    private final StateChangedListener mStateChangedListener;
    private String mText;
    private int mIconRes = R.drawable.ic_settings_bluetooth;
    private QuickSettingGridAdapter.Tile.State mState = QuickSettingGridAdapter.Tile.State.OFF;
    private final BroadcastReceiver mBtStateReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.quicksettings.BluetoothTile.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int state = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                if (state == 10 || state == 13) {
                    BluetoothTile.this.mIconRes = R.drawable.ic_settings_bluetooth_disabled;
                    BluetoothTile.this.mState = QuickSettingGridAdapter.Tile.State.OFF;
                } else {
                    BluetoothTile.this.mIconRes = R.drawable.ic_settings_bluetooth;
                    BluetoothTile bluetoothTile = BluetoothTile.this;
                    bluetoothTile.mText = bluetoothTile.mContext.getString(R.string.bluetooth_settings_title);
                    BluetoothTile.this.mState = QuickSettingGridAdapter.Tile.State.ON;
                }
            } else if (action.equals("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED")) {
                if (intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", Integer.MIN_VALUE) == 2) {
                    BluetoothTile.this.mIconRes = R.drawable.ic_settings_bluetooth_connected;
                } else {
                    BluetoothTile.this.mIconRes = R.drawable.ic_settings_bluetooth;
                }
            }
            BluetoothTile.this.mStateChangedListener.onStateChanged();
        }
    };

    BluetoothTile(final Context context, StateChangedListener stateChangedListener, FragmentHost fragmentHost) {
        this.mStateChangedListener = stateChangedListener;
        this.mContext = context;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothAdapter = defaultAdapter;
        if (defaultAdapter == null) {
            LOG.e("Bluetooth is not supported on this device");
        } else {
            this.mText = this.mContext.getString(R.string.bluetooth_settings_title);
            this.mLaunchBluetoothSettings = new View.OnLongClickListener() { // from class: com.android.car.settings.quicksettings.-$$Lambda$BluetoothTile$7-XxiypIdM9OXJ3wkbo3C0nIOEY
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return BluetoothTile.lambda$new$0(context, view);
                }
            };
        }
    }

    static /* synthetic */ boolean lambda$new$0(Context context, View v) {
        context.startActivity(new Intent(context, (Class<?>) CarSettingActivities.BluetoothSettingsActivity.class));
        return true;
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public View.OnLongClickListener getOnLongClickListener() {
        return this.mLaunchBluetoothSettings;
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public boolean isAvailable() {
        return this.mBluetoothAdapter != null;
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
        IntentFilter mBtStateChangeFilter = new IntentFilter();
        mBtStateChangeFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        mBtStateChangeFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        this.mContext.registerReceiver(this.mBtStateReceiver, mBtStateChangeFilter);
        updateBluetoothIconState();
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.Tile
    public void stop() {
        this.mContext.unregisterReceiver(this.mBtStateReceiver);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            return;
        }
        if (bluetoothAdapter.isEnabled()) {
            this.mBluetoothAdapter.disable();
        } else {
            this.mBluetoothAdapter.enable();
        }
    }

    private void updateBluetoothIconState() {
        if (this.mBluetoothAdapter.getConnectionState() == 2) {
            this.mIconRes = R.drawable.ic_settings_bluetooth_connected;
            this.mState = QuickSettingGridAdapter.Tile.State.ON;
        } else if (this.mBluetoothAdapter.isEnabled()) {
            this.mIconRes = R.drawable.ic_settings_bluetooth;
            this.mState = QuickSettingGridAdapter.Tile.State.ON;
        } else {
            this.mIconRes = R.drawable.ic_settings_bluetooth_disabled;
            this.mState = QuickSettingGridAdapter.Tile.State.OFF;
        }
        this.mStateChangedListener.onStateChanged();
    }
}
