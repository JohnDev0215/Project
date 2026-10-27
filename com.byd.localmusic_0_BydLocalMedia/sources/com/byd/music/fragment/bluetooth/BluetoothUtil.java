package com.byd.music.fragment.bluetooth;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.media.MediaMetrics;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import com.byd.music.utils.LogUtil;
import java.lang.reflect.Method;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothUtil {
    private static final int CMD_NEXT = 4;
    private static final int CMD_PAUSE = 2;
    private static final int CMD_PLAY = 1;
    private static final int CMD_PRV = 3;
    private static final String TAG = BluetoothUtil.class.getName();

    public static void connectA2dp(BluetoothA2dp bluetoothA2dp, BluetoothAdapter bluetoothAdapter) {
        BluetoothDevice connectedBtDevice = getConnectedBtDevice(bluetoothAdapter);
        setPriority(bluetoothA2dp, 2, bluetoothAdapter);
        try {
            BluetoothA2dp.class.getMethod(MediaMetrics.Value.CONNECT, BluetoothDevice.class).invoke(bluetoothA2dp, connectedBtDevice);
            bluetoothA2dp.isA2dpPlaying(connectedBtDevice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setPriority(BluetoothA2dp bluetoothA2dp, int i, BluetoothAdapter bluetoothAdapter) {
        BluetoothDevice connectedBtDevice = getConnectedBtDevice(bluetoothAdapter);
        if (bluetoothAdapter == null && connectedBtDevice == null) {
            return;
        }
        try {
            BluetoothA2dp.class.getMethod("setPriority", BluetoothDevice.class, Integer.TYPE).invoke(bluetoothA2dp, connectedBtDevice, Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setMusicMode(Object obj) {
        try {
            Object.class.getMethod("setBluetoothMusicMode", Class.forName("android.bluetooth.BluetoothAvrcpController"), String.class).invoke(obj, true);
        } catch (Exception e) {
            LogUtil.e(TAG, "Exception:" + e);
        }
    }

    public static BluetoothDevice getConnectedBtDevice(BluetoothAdapter bluetoothAdapter) {
        boolean zBooleanValue;
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if (bondedDevices != null && bondedDevices.size() > 0) {
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                try {
                    Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", (Class[]) null);
                    declaredMethod.setAccessible(true);
                    zBooleanValue = ((Boolean) declaredMethod.invoke(bluetoothDevice, (Object[]) null)).booleanValue();
                    LogUtil.e(TAG, "isConnected：" + zBooleanValue + ",bondedDevice.getName():" + bluetoothDevice.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (zBooleanValue) {
                    return bluetoothDevice;
                }
            }
        }
        return null;
    }

    public static boolean isBlueToothConnect(MediaBrowser mediaBrowser) {
        return mediaBrowser != null && mediaBrowser.isConnected();
    }

    public static void btMusicPlayContorlCmd(int i, MediaController mediaController) {
        if (mediaController == null || mediaController.getTransportControls() == null) {
            LogUtil.e(TAG, "btMusicPlayContorlCmd mediaSession is not ready");
            return;
        }
        MediaController.TransportControls transportControls = mediaController.getTransportControls();
        if (i == 1) {
            transportControls.play();
            LogUtil.e(TAG, "播放");
            return;
        }
        if (i == 2) {
            transportControls.pause();
            LogUtil.e(TAG, "暂停");
            return;
        }
        if (i == 3) {
            transportControls.skipToPrevious();
            LogUtil.e(TAG, "下一曲");
        } else {
            if (i == 4) {
                transportControls.skipToNext();
                LogUtil.e(TAG, "上一曲");
                return;
            }
            LogUtil.e(TAG, "进度：" + i);
            transportControls.seekTo((long) i);
        }
    }
}
