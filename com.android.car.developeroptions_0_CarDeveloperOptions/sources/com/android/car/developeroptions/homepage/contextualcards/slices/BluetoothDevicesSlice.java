package com.android.car.developeroptions.homepage.contextualcards.slices;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.builders.SliceAction;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.bluetooth.BluetoothDeviceDetailsFragment;
import com.android.car.developeroptions.bluetooth.Utils;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.slices.CustomSliceRegistry;
import com.android.car.developeroptions.slices.CustomSliceable;
import com.android.car.developeroptions.slices.SliceBroadcastReceiver;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothDevicesSlice implements CustomSliceable {

    @VisibleForTesting
    static final String BLUETOOTH_DEVICE_HASH_CODE = "bluetooth_device_hash_code";
    private static final Comparator<CachedBluetoothDevice> COMPARATOR = Comparator.naturalOrder();

    @VisibleForTesting
    static final int DEFAULT_EXPANDED_ROW_COUNT = 3;
    private final Context mContext;

    public BluetoothDevicesSlice(Context context) {
        this.mContext = context;
    }

    public Uri getUri() {
        return CustomSliceRegistry.BLUETOOTH_DEVICES_SLICE_URI;
    }

    @Override // com.android.car.developeroptions.slices.CustomSliceable
    public void onNotifyChange(Intent intent) {
        int intExtra = intent.getIntExtra(BLUETOOTH_DEVICE_HASH_CODE, -1);
        for (CachedBluetoothDevice cachedBluetoothDevice : getConnectedBluetoothDevices()) {
            if (cachedBluetoothDevice.hashCode() == intExtra) {
                cachedBluetoothDevice.setActive();
                return;
            }
        }
    }

    @VisibleForTesting
    List<CachedBluetoothDevice> getConnectedBluetoothDevices() {
        ArrayList arrayList = new ArrayList();
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            Log.i("BluetoothDevicesSlice", "Cannot get Bluetooth devices, Bluetooth is disabled.");
            return arrayList;
        }
        LocalBluetoothManager localBtManager = Utils.getLocalBtManager(this.mContext);
        if (localBtManager == null) {
            Log.i("BluetoothDevicesSlice", "Cannot get Bluetooth devices, Bluetooth is unsupported.");
            return arrayList;
        }
        return (List) localBtManager.getCachedDeviceManager().getCachedDevicesCopy().stream().filter(new Predicate() { // from class: com.android.car.developeroptions.homepage.contextualcards.slices.-$$Lambda$BluetoothDevicesSlice$pLrbXO0WYRlPZVccG8ZMvKelUCE
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CachedBluetoothDevice) obj).getDevice().isConnected();
            }
        }).sorted(COMPARATOR).collect(Collectors.toList());
    }

    @VisibleForTesting
    PendingIntent getBluetoothDetailIntent(CachedBluetoothDevice cachedBluetoothDevice) {
        Bundle bundle = new Bundle();
        bundle.putString("device_address", cachedBluetoothDevice.getDevice().getAddress());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(BluetoothDeviceDetailsFragment.class.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleRes(R.string.device_details_title);
        subSettingLauncher.setSourceMetricsCategory(1009);
        return PendingIntent.getActivity(this.mContext, cachedBluetoothDevice.hashCode(), subSettingLauncher.toIntent(), 0);
    }

    @VisibleForTesting
    IconCompat getBluetoothDeviceIcon(CachedBluetoothDevice cachedBluetoothDevice) {
        Drawable drawable = (Drawable) BluetoothUtils.getBtRainbowDrawableWithDescription(this.mContext, cachedBluetoothDevice).first;
        if (drawable == null) {
            return IconCompat.createWithResource(this.mContext, android.R.drawable.ic_media_route_holo_dark);
        }
        return com.android.car.developeroptions.Utils.createIconWithDrawable(drawable);
    }

    @VisibleForTesting
    SliceAction buildMediaBluetoothAction(CachedBluetoothDevice cachedBluetoothDevice) {
        return SliceAction.create(PendingIntent.getBroadcast(this.mContext, cachedBluetoothDevice.hashCode(), new Intent(getUri().toString()).setClass(this.mContext, SliceBroadcastReceiver.class).putExtra(BLUETOOTH_DEVICE_HASH_CODE, cachedBluetoothDevice.hashCode()), 0), getBluetoothDeviceIcon(cachedBluetoothDevice), 0, cachedBluetoothDevice.getName());
    }

    @VisibleForTesting
    SliceAction buildBluetoothDetailDeepLinkAction(CachedBluetoothDevice cachedBluetoothDevice) {
        return SliceAction.createDeeplink(getBluetoothDetailIntent(cachedBluetoothDevice), IconCompat.createWithResource(this.mContext, R.drawable.ic_settings_accent), 0, cachedBluetoothDevice.getName());
    }
}
