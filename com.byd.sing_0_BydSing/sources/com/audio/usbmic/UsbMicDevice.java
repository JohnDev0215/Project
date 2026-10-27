package com.audio.usbmic;

import android.util.Log;
import com.micphone.service.IUsbDeviceEventListener;

/* JADX INFO: loaded from: classes.dex */
public class UsbMicDevice {
    private static final int INPUT_DONGLE_KEY = 161;
    private static final int INPUT_MIC_KEY = 160;
    public static final int MEDIA_IN_SOURCE = 1;
    public static final int MICPHONE_IN_SOURCE = 0;
    public static final int RECORD_OUT_SOURCE = 2;
    public static final int SHARED_OUT_SOURCE = 3;
    private static final String TAG = "UsbMicDevice";
    private static boolean isUpgrade = false;
    private static IUsbDeviceEventListener mListener;
    private static Object mLock = new Object();
    private static long mLoopback;

    static {
        System.loadLibrary("TsService");
    }

    public static void activeSource(int i, int i2) {
        long j = mLoopback;
        if (j != 0) {
            loopback_active_source(j, i, i2);
        }
    }

    public static int ble_upgrade(int i, byte[] bArr) {
        if (isUpgrade) {
            return -1;
        }
        isUpgrade = true;
        int iUsb_mic_ble_upgrade = usb_mic_ble_upgrade(i, bArr);
        isUpgrade = false;
        return iUsb_mic_ble_upgrade;
    }

    public static int cancelPair(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_cancel_pair(i);
    }

    public static int change2OtaMode() {
        return usb_mic_ota_mode();
    }

    public static int confirmPair(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_confirm_pair(i);
    }

    public static int erasePair() {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_erase_pair();
    }

    public static int getBLePairLength() {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_get_ble_pair_len();
    }

    public static int getInternalEffect() {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_get_internal_effect();
    }

    public static int getInternalEffectGroup() {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_get_internal_effect_group();
    }

    public static int getMicMax() {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_get_max();
    }

    public static int getReverb() {
        int iLoopback_se_get_reverb;
        synchronized (mLock) {
            long j = mLoopback;
            iLoopback_se_get_reverb = j != 0 ? loopback_se_get_reverb(j) : -1;
        }
        return iLoopback_se_get_reverb;
    }

    public static String getSoVersion() {
        return get_so_version();
    }

    public static int getVolume() {
        int iLoopback_se_get_volume;
        synchronized (mLock) {
            long j = mLoopback;
            iLoopback_se_get_volume = j != 0 ? loopback_se_get_volume(j) : -1;
        }
        return iLoopback_se_get_volume;
    }

    private static native String get_so_version();

    public static int isSupportOta() {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_is_support_upgrade();
    }

    public static int loadEffect(byte[] bArr) {
        int iLoopback_se_load_effect;
        synchronized (mLock) {
            long j = mLoopback;
            iLoopback_se_load_effect = j != 0 ? loopback_se_load_effect(j, bArr) : -1;
        }
        return iLoopback_se_load_effect;
    }

    public static int loopbackDeinit() {
        synchronized (mLock) {
            long j = mLoopback;
            if (j != 0) {
                loopback_stop(j);
                loopback_deinit(mLoopback);
                mLoopback = 0L;
            }
            Log.e("Driver", "loopback lp release");
        }
        return 0;
    }

    public static void loopbackInit() {
        if (UsbAudioNativeLoopConfig.mPlaybackCardName.length() > 1) {
            loopback_playback_params(UsbAudioNativeLoopConfig.mPlaybackCardName, UsbAudioNativeLoopConfig.mPlaybackDevice, UsbAudioNativeLoopConfig.mPlaybackFreq, UsbAudioNativeLoopConfig.mPlaybackChannels, UsbAudioNativeLoopConfig.mPlaybackFormat, UsbAudioNativeLoopConfig.mPlaybackPeriods, UsbAudioNativeLoopConfig.mPlaybackCount, UsbAudioNativeLoopConfig.mPlaybackQueues);
        } else {
            loopback_playback_params2(UsbAudioNativeLoopConfig.mPlaybackCard, UsbAudioNativeLoopConfig.mPlaybackDevice, UsbAudioNativeLoopConfig.mPlaybackFreq, UsbAudioNativeLoopConfig.mPlaybackChannels, UsbAudioNativeLoopConfig.mPlaybackFormat, UsbAudioNativeLoopConfig.mPlaybackPeriods, UsbAudioNativeLoopConfig.mPlaybackCount, UsbAudioNativeLoopConfig.mPlaybackQueues);
        }
        loopback_capture_params2(UsbAudioNativeLoopConfig.mCaptureCard, UsbAudioNativeLoopConfig.mCaptureDevice, UsbAudioNativeLoopConfig.mCaptureFreq, UsbAudioNativeLoopConfig.mCaptureChannels, UsbAudioNativeLoopConfig.mCaptureFormat, UsbAudioNativeLoopConfig.mCapturePeriods, UsbAudioNativeLoopConfig.mCaptureCount, UsbAudioNativeLoopConfig.mCaptureQueues);
        long jLoopback_init = loopback_init();
        mLoopback = jLoopback_init;
        if (jLoopback_init != 0) {
            int maxMixerNum = UsbAudioNativeLoopConfig.getMaxMixerNum();
            for (int i = 0; i < maxMixerNum; i++) {
                String[] strArr = UsbAudioNativeLoopConfig.mMixerCtl;
                if (strArr[i] != null) {
                    loopback_set_mixer(0, i, strArr[i], UsbAudioNativeLoopConfig.mMixerVal[i]);
                }
                String[] strArr2 = UsbAudioNativeLoopConfig.mMixerCtl;
                int i2 = i + maxMixerNum;
                if (strArr2[i2] != null) {
                    loopback_set_mixer(1, i, strArr2[i2], UsbAudioNativeLoopConfig.mMixerVal[i2]);
                }
            }
            Log.e(TAG, "after mLoopback init");
            if (UsbAudioNativeLoopConfig.isSoftEffect()) {
                loopback_soft_effect_enable(mLoopback, 1);
            } else {
                loopback_soft_effect_enable(mLoopback, 0);
            }
            loopback_loop_mode(mLoopback, UsbAudioNativeLoopConfig.loopMode());
            if (UsbAudioNativeLoopConfig.loopMode() == 1) {
                loopback_start(mLoopback);
            }
        }
        Log.e("Driver", "loopback lp " + Long.toHexString(mLoopback));
    }

    public static int loopbackLatency(int i) {
        long j = mLoopback;
        if (j != 0) {
            return loopback_set_latency(j, i);
        }
        return -1;
    }

    public static int loopbackPause(int i) {
        if (mLoopback == 0 || UsbAudioNativeLoopConfig.loopMode() != 0) {
            return -1;
        }
        return loopback_pause(mLoopback, i);
    }

    public static int loopbackRead(byte[] bArr) {
        long j = mLoopback;
        if (j != 0) {
            return loopback_read(j, bArr, bArr.length);
        }
        return -1;
    }

    public static int loopbackStart() {
        if (mLoopback == 0 || UsbAudioNativeLoopConfig.loopMode() != 0) {
            return -1;
        }
        Log.e("MicphoneService", "loopbackStart runrun");
        return loopback_start(mLoopback);
    }

    public static int loopbackStop() {
        if (mLoopback == 0 || UsbAudioNativeLoopConfig.loopMode() != 0) {
            return -1;
        }
        return loopback_stop(mLoopback);
    }

    private static native void loopback_active_source(long j, int i, int i2);

    private static native void loopback_capture_params2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

    private static native int loopback_deinit(long j);

    private static native long loopback_init();

    private static native int loopback_loop_mode(long j, int i);

    private static native int loopback_pause(long j, int i);

    private static native void loopback_playback_params(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7);

    private static native void loopback_playback_params2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

    private static native int loopback_read(long j, byte[] bArr, int i);

    private static native int loopback_se_get_reverb(long j);

    private static native int loopback_se_get_volume(long j);

    private static native int loopback_se_load_effect(long j, byte[] bArr);

    private static native int loopback_se_set_reverb(long j, int i);

    private static native int loopback_se_set_volume(long j, int i);

    private static native int loopback_set_latency(long j, int i);

    private static native void loopback_set_mixer(int i, int i2, String str, String str2);

    private static native int loopback_soft_effect_enable(long j, int i);

    private static native int loopback_start(long j);

    private static native int loopback_stop(long j);

    public static void onDongleEvent(int i, int i2, int i3) {
        IUsbDeviceEventListener iUsbDeviceEventListener = mListener;
        if (iUsbDeviceEventListener != null) {
            if (i == 240 || i == 224) {
                iUsbDeviceEventListener.onOtaEvent(i, i2, i3);
            }
            if (i == 7 || i == 8 || i == 195 || i == 196 || i == 197 || i == 198) {
                mListener.onEndpointEvent(i, i2, i3);
            }
        }
    }

    public static int rebootDongle() {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_reboot_dongle();
    }

    public static int resetFactory() {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_reset_factory();
    }

    public static int selectInternalEffect(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_set_internal_effect(i);
    }

    public static int selectInternalEffectGroup(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_set_internal_effect_group(i);
    }

    public static int setReverb(int i) {
        int iLoopback_se_set_reverb;
        synchronized (mLock) {
            long j = mLoopback;
            iLoopback_se_set_reverb = j != 0 ? loopback_se_set_reverb(j, i) : -1;
        }
        return iLoopback_se_set_reverb;
    }

    public static int setVolume(int i) {
        int iLoopback_se_set_volume;
        synchronized (mLock) {
            long j = mLoopback;
            iLoopback_se_set_volume = j != 0 ? loopback_se_set_volume(j, i) : -1;
        }
        return iLoopback_se_set_volume;
    }

    public static int upgrade(byte[] bArr) {
        if (isUpgrade) {
            return -1;
        }
        activeSource(0, 0);
        isUpgrade = true;
        int iUsb_mic_upgrade = usb_mic_upgrade(bArr);
        isUpgrade = false;
        return iUsb_mic_upgrade;
    }

    public static String usbLibVersion() {
        return "v1.1.0";
    }

    public static int usbMicAuthcheck(String str, int i, int i2) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_auth_check(str, i, i2);
    }

    public static void usbMicClose() {
        mListener = null;
        usb_mic_close();
    }

    public static String usbMicGetDeviceId() {
        if (isUpgrade) {
            return null;
        }
        return usb_mic_get_device_id();
    }

    public static String usbMicGetDongleProductName() {
        if (isUpgrade) {
            return null;
        }
        return usb_mic_get_dongle_product();
    }

    public static String usbMicGetDongleVersion() {
        if (isUpgrade) {
            return null;
        }
        return usb_mic_get_dongle_version();
    }

    public static int usbMicGetFreq(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_get_freq(i);
    }

    public static String usbMicGetMicHardwareVersion(int i) {
        if (isUpgrade) {
            return null;
        }
        return usb_mic_get_mic_hardware_version(i);
    }

    public static int usbMicGetMicID(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_get_mic_id(i);
    }

    public static String usbMicGetMicName(int i) {
        if (isUpgrade) {
            return null;
        }
        return usb_mic_get_mic_name(i);
    }

    public static int usbMicGetMicPairType(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_get_mic_pair_type(i);
    }

    public static int usbMicGetMicPower(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_get_mic_power(i);
    }

    public static String usbMicGetMicVersion(int i) {
        if (isUpgrade) {
            return null;
        }
        return usb_mic_get_mic_version(i);
    }

    public static int usbMicGetMicVolume(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_get_mic_volume(i);
    }

    public static int usbMicGetReverb() {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_get_reverb();
    }

    public static int usbMicGetRssi(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_get_rssi(i);
    }

    public static int usbMicIsConnected(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_is_connected(i);
    }

    public static int usbMicLoadAudioEffect(byte[] bArr) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_load_audio_effect(bArr);
    }

    public static int usbMicLoadAudioEffectTipSound(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_load_audio_effect_tip_sound(i);
    }

    public static int usbMicLoadMicEffect(int i, byte[] bArr) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_load_mic_effect(i, bArr);
    }

    public static int usbMicOpen(String str, int i, IUsbDeviceEventListener iUsbDeviceEventListener) {
        int iUsb_mic_open = usb_mic_open(str, i);
        mListener = iUsbDeviceEventListener;
        return iUsb_mic_open;
    }

    public static int usbMicOpenByDisk(String str, int i, IUsbDeviceEventListener iUsbDeviceEventListener) {
        int iUsb_mic_open_disk = usb_mic_open_disk(str, i);
        mListener = iUsbDeviceEventListener;
        return iUsb_mic_open_disk;
    }

    public static int usbMicSaveEffect() {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_save_effect();
    }

    public static int usbMicSetMicVolume(int i, int i2) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_set_mic_volume(i, i2);
    }

    public static int usbMicSetReverb(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_set_reverb(i);
    }

    public static int usbMicSwitchEffect(int i) {
        if (isUpgrade) {
            return -1;
        }
        return usb_mic_switch_effect(i);
    }

    private static native int usb_mic_auth_check(String str, int i, int i2);

    private static native int usb_mic_ble_upgrade(int i, byte[] bArr);

    private static native int usb_mic_cancel_pair(int i);

    private static native void usb_mic_close();

    private static native int usb_mic_confirm_pair(int i);

    private static native int usb_mic_erase_pair();

    private static native int usb_mic_get_ble_pair_len();

    private static native String usb_mic_get_device_id();

    private static native String usb_mic_get_dongle_product();

    private static native String usb_mic_get_dongle_version();

    private static native int usb_mic_get_freq(int i);

    private static native int usb_mic_get_internal_effect();

    private static native int usb_mic_get_internal_effect_group();

    private static native int usb_mic_get_max();

    private static native String usb_mic_get_mic_hardware_version(int i);

    private static native int usb_mic_get_mic_id(int i);

    private static native String usb_mic_get_mic_name(int i);

    private static native int usb_mic_get_mic_pair_type(int i);

    private static native int usb_mic_get_mic_power(int i);

    private static native String usb_mic_get_mic_version(int i);

    private static native int usb_mic_get_mic_volume(int i);

    private static native int usb_mic_get_reverb();

    private static native int usb_mic_get_rssi(int i);

    private static native int usb_mic_is_connected(int i);

    private static native int usb_mic_is_support_upgrade();

    private static native int usb_mic_load_audio_effect(byte[] bArr);

    private static native int usb_mic_load_audio_effect_tip_sound(int i);

    private static native int usb_mic_load_mic_effect(int i, byte[] bArr);

    private static native int usb_mic_open(String str, int i);

    private static native int usb_mic_open_disk(String str, int i);

    private static native int usb_mic_ota_mode();

    private static native int usb_mic_reboot_dongle();

    private static native int usb_mic_reset_factory();

    private static native int usb_mic_save_effect();

    private static native int usb_mic_set_internal_effect(int i);

    private static native int usb_mic_set_internal_effect_group(int i);

    private static native int usb_mic_set_mic_volume(int i, int i2);

    private static native int usb_mic_set_reverb(int i);

    private static native int usb_mic_switch_effect(int i);

    private static native int usb_mic_upgrade(byte[] bArr);
}
