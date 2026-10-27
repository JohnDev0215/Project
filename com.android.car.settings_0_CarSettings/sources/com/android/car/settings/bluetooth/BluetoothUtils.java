package com.android.car.settings.bluetooth;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import com.android.car.ui.AlertDialogBuilder;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.LocalBluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;

/* JADX INFO: loaded from: classes5.dex */
final class BluetoothUtils {
    private static final int GRACE_PERIOD_TO_SHOW_DIALOGS_IN_FOREGROUND = 60000;
    private static final String KEY_DISCOVERABLE_END_TIMESTAMP = "discoverable_end_timestamp";
    private static final String KEY_LAST_SELECTED_DEVICE = "last_selected_device";
    private static final String KEY_LAST_SELECTED_DEVICE_TIME = "last_selected_device_time";
    private static final String SHARED_PREFERENCES_NAME = "bluetooth_settings";
    private static final Logger LOG = new Logger((Class<?>) BluetoothUtils.class);
    private static final LocalBluetoothManager.BluetoothManagerCallback mOnInitCallback = new LocalBluetoothManager.BluetoothManagerCallback() { // from class: com.android.car.settings.bluetooth.BluetoothUtils.1
        @Override // com.android.settingslib.bluetooth.LocalBluetoothManager.BluetoothManagerCallback
        public void onBluetoothManagerInitialized(Context appContext, LocalBluetoothManager bluetoothManager) {
            com.android.settingslib.bluetooth.BluetoothUtils.setErrorListener(new BluetoothUtils.ErrorListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$PUi_wcV4kKIcKkCEisEIFPHnqLI
                @Override // com.android.settingslib.bluetooth.BluetoothUtils.ErrorListener
                public final void onShowError(Context context, String str, int i) {
                    BluetoothUtils.showError(context, str, i);
                }
            });
        }
    };

    private BluetoothUtils() {
    }

    static void showError(Context context, String name, int messageResId) {
        showError(context, name, messageResId, getLocalBtManager(context));
    }

    private static void showError(Context context, String name, int messageResId, LocalBluetoothManager manager) {
        String message = context.getString(messageResId, name);
        Context activity = manager.getForegroundActivity();
        if (manager.isForegroundActivity()) {
            new AlertDialogBuilder(activity).setTitle(R.string.bluetooth_error_title).setMessage(message).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null).create().show();
        }
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
    }

    static long getDiscoverableEndTimestamp(Context context) {
        return getSharedPreferences(context).getLong(KEY_DISCOVERABLE_END_TIMESTAMP, 0L);
    }

    static boolean shouldShowDialogInForeground(Context context, String deviceAddress, String deviceName) {
        LocalBluetoothManager manager = getLocalBtManager(context);
        if (manager == null) {
            LOG.v("manager == null - do not show dialog.");
            return false;
        }
        if (manager.isForegroundActivity()) {
            return true;
        }
        if ((context.getResources().getConfiguration().uiMode & 5) == 5) {
            LOG.v("in appliance mode - do not show dialog.");
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        long lastDiscoverableEndTime = sharedPreferences.getLong(KEY_DISCOVERABLE_END_TIMESTAMP, 0L);
        if (lastDiscoverableEndTime + 60000 > currentTimeMillis) {
            return true;
        }
        LocalBluetoothAdapter adapter = manager.getBluetoothAdapter();
        if (adapter != null && (adapter.isDiscovering() || adapter.getDiscoveryEndMillis() + 60000 > currentTimeMillis)) {
            return true;
        }
        if (deviceAddress != null) {
            String lastSelectedDevice = sharedPreferences.getString(KEY_LAST_SELECTED_DEVICE, null);
            if (deviceAddress.equals(lastSelectedDevice)) {
                long lastDeviceSelectedTime = sharedPreferences.getLong(KEY_LAST_SELECTED_DEVICE_TIME, 0L);
                if (60000 + lastDeviceSelectedTime > currentTimeMillis) {
                    return true;
                }
            }
        }
        if (!TextUtils.isEmpty(deviceName)) {
            String packagedKeyboardName = context.getString(android.R.string.config_defaultCredentialManagerAutofillService);
            if (deviceName.equals(packagedKeyboardName)) {
                LOG.v("showing dialog for packaged keyboard");
                return true;
            }
        }
        LOG.v("Found no reason to show the dialog - do not show dialog.");
        return false;
    }

    static void persistSelectedDeviceInPicker(Context context, String deviceAddress) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_LAST_SELECTED_DEVICE, deviceAddress);
        editor.putLong(KEY_LAST_SELECTED_DEVICE_TIME, System.currentTimeMillis());
        editor.apply();
    }

    static void persistDiscoverableEndTimestamp(Context context, long endTimestamp) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putLong(KEY_DISCOVERABLE_END_TIMESTAMP, endTimestamp);
        editor.apply();
    }

    public static LocalBluetoothManager getLocalBtManager(Context context) {
        return LocalBluetoothManager.getInstance(context, mOnInitCallback);
    }
}
