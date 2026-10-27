package com.amazon.mp3.service.metrics.mts;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.auto.carmode.detection.CarBluetoothDetector;
import com.amazon.mp3.bluetooth.BluetoothServiceListener;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.auto.AutoMusicMediaBrowserService;
import com.amazon.music.metrics.mts.MTSEventDataProvider;
import com.amazon.music.metrics.mts.android.AndroidMTSEventDataProvider;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class MusicEventDataProvider extends AndroidMTSEventDataProvider {
    private static final String TAG = "MusicEventDataProvider";
    private final Context mContext;

    public MusicEventDataProvider(Context context, String platformName) {
        super(context, platformName);
        this.mContext = context.getApplicationContext();
    }

    @Override // com.amazon.music.metrics.mts.MTSEventDataProvider
    public String getMusicTerritory() {
        return AccountDetailUtil.getMusicTerritoryOfResidence();
    }

    @Override // com.amazon.music.metrics.mts.MTSEventDataProvider
    public String getSubDeviceId() {
        if (CastingUtil.isCasting()) {
            return CastingUtil.getCastingDeviceTypeId();
        }
        if (isBluetoothDeviceConnectedWithPermissions()) {
            return getBluetoothDeviceNameWithPermissions();
        }
        return MTSEventDataProvider.NO_SUB_DEVICE;
    }

    @Override // com.amazon.music.metrics.mts.MTSEventDataProvider
    public String getSubDeviceStreamMedium() {
        return CastingUtil.isCasting() ? "WIFI" : isBluetoothDeviceConnectedWithPermissions() ? "BLUETOOTH" : AutoMusicMediaBrowserService.INSTANCE.getMetadataLoaded() ? "USB" : MTSEventDataProvider.NO_SUB_DEVICE;
    }

    private boolean isBluetoothDeviceConnectedWithPermissions() {
        if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.BLUETOOTH_CONNECT") == 0 && Build.VERSION.SDK_INT >= 31) {
            return isBluetoothDeviceConnected();
        }
        if (Build.VERSION.SDK_INT < 31) {
            return isBluetoothDeviceConnected();
        }
        return false;
    }

    private boolean isBluetoothDeviceConnected() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || !defaultAdapter.isEnabled()) {
            return false;
        }
        return defaultAdapter.getProfileConnectionState(2) == 2 || defaultAdapter.getProfileConnectionState(1) == 2 || defaultAdapter.getProfileConnectionState(10) == 2 || defaultAdapter.getProfileConnectionState(7) == 2 || CarBluetoothDetector.INSTANCE.isCarBluetoothConnected();
    }

    private String getBluetoothDeviceNameWithPermissions() {
        if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.BLUETOOTH_CONNECT") == 0 && Build.VERSION.SDK_INT >= 31) {
            return getBluetoothDeviceName();
        }
        if (Build.VERSION.SDK_INT < 31) {
            return getBluetoothDeviceName();
        }
        return MTSEventDataProvider.NO_SUB_DEVICE;
    }

    private String getBluetoothDeviceName() {
        String str = MTSEventDataProvider.NO_SUB_DEVICE;
        if (BluetoothAdapter.getDefaultAdapter() == null) {
            return str;
        }
        List<BluetoothDevice> connectedDevices = BluetoothServiceListener.getConnectedDevices();
        if (connectedDevices != null) {
            return connectedDevices.size() > 0 ? BluetoothServiceListener.getDeviceNameList(connectedDevices) : str;
        }
        Log.error(TAG, "ServiceListener returned null, error obtaining available set of bluetooth devices");
        return str;
    }
}
