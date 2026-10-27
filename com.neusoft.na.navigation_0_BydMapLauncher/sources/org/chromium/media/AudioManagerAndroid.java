package org.chromium.media;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.car.Car;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.provider.Settings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media")
class AudioManagerAndroid implements AudioManager.OnAudioFocusChangeListener {
    private static final boolean DEBUG = false;
    private static final int DEFAULT_FRAME_PER_BUFFER = 256;
    private static final int DEFAULT_SAMPLING_RATE = 44100;
    private static final int DEVICE_BLUETOOTH_HEADSET = 3;
    private static final int DEVICE_COUNT = 5;
    private static final int DEVICE_DEFAULT = -2;
    private static final int DEVICE_EARPIECE = 2;
    private static final int DEVICE_INVALID = -1;
    private static final int DEVICE_SPEAKERPHONE = 0;
    private static final int DEVICE_USB_AUDIO = 4;
    private static final int DEVICE_WIRED_HEADSET = 1;
    private static final int STATE_BLUETOOTH_SCO_INVALID = -1;
    private static final int STATE_BLUETOOTH_SCO_OFF = 0;
    private static final int STATE_BLUETOOTH_SCO_ON = 1;
    private static final int STATE_BLUETOOTH_SCO_TURNING_OFF = 3;
    private static final int STATE_BLUETOOTH_SCO_TURNING_ON = 2;
    private static final String TAG = "cr.media";
    private final AudioManager mAudioManager;
    private BroadcastReceiver mBluetoothHeadsetReceiver;
    private BroadcastReceiver mBluetoothScoReceiver;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private int mCurrentVolume;
    private final long mNativeAudioManagerAndroid;
    private boolean mSavedIsMicrophoneMute;
    private boolean mSavedIsSpeakerphoneOn;
    private BroadcastReceiver mUsbAudioReceiver;
    private final UsbManager mUsbManager;
    private BroadcastReceiver mWiredHeadsetReceiver;
    private static final String[] SUPPORTED_AEC_MODELS = {"GT-I9300", "GT-I9500", "GT-N7105", "Nexus 4", "Nexus 5", "Nexus 7", "SM-N9005", "SM-T310"};
    private static final String[] DEVICE_NAMES = {"Speakerphone", "Wired headset", "Headset earpiece", "Bluetooth headset", "USB audio"};
    private static final Integer[] VALID_DEVICES = {0, 1, 2, 3, 4};
    private boolean mHasModifyAudioSettingsPermission = false;
    private boolean mHasBluetoothPermission = false;
    private int mSavedAudioMode = -2;
    private int mBluetoothScoState = -1;
    private boolean mIsInitialized = false;
    private int mRequestedAudioDevice = -1;
    private final NonThreadSafe mNonThreadSafe = new NonThreadSafe();
    private final Object mLock = new Object();
    private boolean[] mAudioDevices = new boolean[5];
    private ContentObserver mSettingsObserver = null;
    private HandlerThread mSettingsObserverThread = null;

    private static class NonThreadSafe {
        private final Long mThreadId = 0L;

        public boolean calledOnValidThread() {
            return true;
        }
    }

    private void checkIfCalledOnValidThread() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSetMute(long j, boolean z);

    private void reportUpdate() {
    }

    private static class AudioDeviceName {
        private final int mId;
        private final String mName;

        private AudioDeviceName(int i, String str) {
            this.mId = i;
            this.mName = str;
        }

        private String id() {
            return String.valueOf(this.mId);
        }

        private String name() {
            return this.mName;
        }
    }

    private static AudioManagerAndroid createAudioManagerAndroid(Context context, long j) {
        return new AudioManagerAndroid(context, j);
    }

    private AudioManagerAndroid(Context context, long j) {
        this.mContext = context;
        this.mNativeAudioManagerAndroid = j;
        this.mAudioManager = (AudioManager) context.getSystemService(Car.AUDIO_SERVICE);
        this.mContentResolver = context.getContentResolver();
        this.mUsbManager = (UsbManager) context.getSystemService("usb");
    }

    private void init() {
        checkIfCalledOnValidThread();
        if (this.mIsInitialized) {
            return;
        }
        this.mHasModifyAudioSettingsPermission = hasPermission("android.permission.MODIFY_AUDIO_SETTINGS");
        this.mAudioDevices[2] = hasEarpiece();
        this.mAudioDevices[1] = hasWiredHeadset();
        this.mAudioDevices[4] = hasUsbAudio();
        this.mAudioDevices[0] = true;
        registerBluetoothIntentsIfNeeded();
        registerForWiredHeadsetIntentBroadcast();
        registerForUsbAudioIntentBroadcast();
        this.mIsInitialized = true;
    }

    private void close() {
        checkIfCalledOnValidThread();
        if (this.mIsInitialized) {
            stopObservingVolumeChanges();
            unregisterForWiredHeadsetIntentBroadcast();
            unregisterBluetoothIntentsIfNeeded();
            unregisterForUsbAudioIntentBroadcast();
            this.mIsInitialized = false;
        }
    }

    private void setCommunicationAudioModeOn(boolean z) {
        checkIfCalledOnValidThread();
        if (this.mIsInitialized) {
            if (!this.mHasModifyAudioSettingsPermission) {
                Log.w(TAG, "MODIFY_AUDIO_SETTINGS is missing => client will run with reduced functionality", new Object[0]);
                return;
            }
            if (z) {
                this.mAudioManager.requestAudioFocus(this, 0, 1);
                this.mSavedIsSpeakerphoneOn = this.mAudioManager.isSpeakerphoneOn();
                this.mSavedIsMicrophoneMute = this.mAudioManager.isMicrophoneMute();
                startObservingVolumeChanges();
            } else {
                this.mAudioManager.abandonAudioFocus(this);
                stopObservingVolumeChanges();
                stopBluetoothSco();
                synchronized (this.mLock) {
                    this.mRequestedAudioDevice = -1;
                }
                setMicrophoneMute(this.mSavedIsMicrophoneMute);
                setSpeakerphoneOn(this.mSavedIsSpeakerphoneOn);
            }
            setCommunicationAudioModeOnInternal(z);
        }
    }

    private void setCommunicationAudioModeOnInternal(boolean z) {
        if (z) {
            if (this.mSavedAudioMode != -2) {
                Log.w(TAG, "Audio mode has already been set", new Object[0]);
                return;
            }
            try {
                this.mSavedAudioMode = this.mAudioManager.getMode();
                try {
                    this.mAudioManager.setMode(3);
                    return;
                } catch (SecurityException e) {
                    logDeviceInfo();
                    throw e;
                }
            } catch (SecurityException e2) {
                logDeviceInfo();
                throw e2;
            }
        }
        int i = this.mSavedAudioMode;
        if (i == -2) {
            Log.w(TAG, "Audio mode has not yet been set", new Object[0]);
            return;
        }
        try {
            this.mAudioManager.setMode(i);
            this.mSavedAudioMode = -2;
        } catch (SecurityException e3) {
            logDeviceInfo();
            throw e3;
        }
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        if (i == 1) {
            setCommunicationAudioModeOnInternal(true);
        } else {
            setCommunicationAudioModeOnInternal(false);
        }
    }

    private boolean setDevice(String str) {
        boolean[] zArr;
        if (!this.mIsInitialized) {
            return false;
        }
        boolean zHasPermission = hasPermission("android.permission.RECORD_AUDIO");
        if (!this.mHasModifyAudioSettingsPermission || !zHasPermission) {
            Log.w(TAG, "Requires MODIFY_AUDIO_SETTINGS and RECORD_AUDIO. Selected device will not be available for recording", new Object[0]);
            return false;
        }
        int i = str.isEmpty() ? -2 : Integer.parseInt(str);
        if (i == -2) {
            synchronized (this.mLock) {
                zArr = (boolean[]) this.mAudioDevices.clone();
                this.mRequestedAudioDevice = -2;
            }
            setAudioDevice(selectDefaultDevice(zArr));
            return true;
        }
        if (!Arrays.asList(VALID_DEVICES).contains(Integer.valueOf(i)) || !this.mAudioDevices[i]) {
            return false;
        }
        synchronized (this.mLock) {
            this.mRequestedAudioDevice = i;
        }
        setAudioDevice(i);
        return true;
    }

    private AudioDeviceName[] getAudioInputDeviceNames() {
        boolean[] zArr;
        if (!this.mIsInitialized) {
            return null;
        }
        boolean zHasPermission = hasPermission("android.permission.RECORD_AUDIO");
        if (!this.mHasModifyAudioSettingsPermission || !zHasPermission) {
            Log.w(TAG, "Requires MODIFY_AUDIO_SETTINGS and RECORD_AUDIO. No audio device will be available for recording", new Object[0]);
            return null;
        }
        synchronized (this.mLock) {
            zArr = (boolean[]) this.mAudioDevices.clone();
        }
        ArrayList arrayList = new ArrayList();
        AudioDeviceName[] audioDeviceNameArr = new AudioDeviceName[getNumOfAudioDevices(zArr)];
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            if (zArr[i2]) {
                String[] strArr = DEVICE_NAMES;
                audioDeviceNameArr[i] = new AudioDeviceName(i2, strArr[i2]);
                arrayList.add(strArr[i2]);
                i++;
            }
        }
        return audioDeviceNameArr;
    }

    private int getNativeOutputSampleRate() {
        String property = this.mAudioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
        return property == null ? DEFAULT_SAMPLING_RATE : Integer.parseInt(property);
    }

    private static int getMinInputFrameSize(int i, int i2) {
        int i3;
        if (i2 == 1) {
            i3 = 16;
        } else {
            if (i2 != 2) {
                return -1;
            }
            i3 = 12;
        }
        return (AudioRecord.getMinBufferSize(i, i3, 2) / 2) / i2;
    }

    private static int getMinOutputFrameSize(int i, int i2) {
        int i3;
        if (i2 == 1) {
            i3 = 4;
        } else {
            if (i2 != 2) {
                return -1;
            }
            i3 = 12;
        }
        return (AudioTrack.getMinBufferSize(i, i3, 2) / 2) / i2;
    }

    private boolean isAudioLowLatencySupported() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    private int getAudioLowLatencyOutputFrameSize() {
        String property = this.mAudioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
        if (property == null) {
            return 256;
        }
        return Integer.parseInt(property);
    }

    private static boolean shouldUseAcousticEchoCanceler() {
        if (Arrays.asList(SUPPORTED_AEC_MODELS).contains(Build.MODEL)) {
            return AcousticEchoCanceler.isAvailable();
        }
        return false;
    }

    private void registerBluetoothIntentsIfNeeded() {
        boolean zHasPermission = hasPermission("android.permission.BLUETOOTH");
        this.mHasBluetoothPermission = zHasPermission;
        if (!zHasPermission) {
            Log.w(TAG, "Requires BLUETOOTH permission", new Object[0]);
            return;
        }
        this.mAudioDevices[3] = hasBluetoothHeadset();
        registerForBluetoothHeadsetIntentBroadcast();
        registerForBluetoothScoIntentBroadcast();
    }

    private void unregisterBluetoothIntentsIfNeeded() {
        if (this.mHasBluetoothPermission) {
            this.mAudioManager.stopBluetoothSco();
            unregisterForBluetoothHeadsetIntentBroadcast();
            unregisterForBluetoothScoIntentBroadcast();
        }
    }

    private void setSpeakerphoneOn(boolean z) {
        if (this.mAudioManager.isSpeakerphoneOn() == z) {
            return;
        }
        this.mAudioManager.setSpeakerphoneOn(z);
    }

    private void setMicrophoneMute(boolean z) {
        if (this.mAudioManager.isMicrophoneMute() == z) {
            return;
        }
        this.mAudioManager.setMicrophoneMute(z);
    }

    private boolean isMicrophoneMute() {
        return this.mAudioManager.isMicrophoneMute();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasEarpiece() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public boolean hasWiredHeadset() {
        return this.mAudioManager.isWiredHeadsetOn();
    }

    private boolean hasPermission(String str) {
        return this.mContext.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    private boolean hasBluetoothHeadset() {
        if (!this.mHasBluetoothPermission) {
            Log.w(TAG, "hasBluetoothHeadset() requires BLUETOOTH permission", new Object[0]);
            return false;
        }
        BluetoothAdapter adapter = ((BluetoothManager) this.mContext.getSystemService("bluetooth")).getAdapter();
        if (adapter == null) {
            return false;
        }
        return adapter.isEnabled() && adapter.getProfileConnectionState(1) == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasUsbAudio() {
        try {
            Iterator<UsbDevice> it = this.mUsbManager.getDeviceList().values().iterator();
            while (it.hasNext()) {
                if (hasUsbAudioCommInterface(it.next())) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    private void registerForWiredHeadsetIntentBroadcast() {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.HEADSET_PLUG");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: org.chromium.media.AudioManagerAndroid.1
            private static final int HAS_MIC = 1;
            private static final int HAS_NO_MIC = 0;
            private static final int STATE_PLUGGED = 1;
            private static final int STATE_UNPLUGGED = 0;

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int intExtra = intent.getIntExtra("state", 0);
                if (intExtra == 0) {
                    synchronized (AudioManagerAndroid.this.mLock) {
                        AudioManagerAndroid.this.mAudioDevices[1] = false;
                        if (AudioManagerAndroid.this.hasUsbAudio()) {
                            AudioManagerAndroid.this.mAudioDevices[4] = true;
                            AudioManagerAndroid.this.mAudioDevices[2] = false;
                        } else if (AudioManagerAndroid.this.hasEarpiece()) {
                            AudioManagerAndroid.this.mAudioDevices[2] = true;
                            AudioManagerAndroid.this.mAudioDevices[4] = false;
                        }
                    }
                } else if (intExtra != 1) {
                    AudioManagerAndroid.loge("Invalid state");
                } else {
                    synchronized (AudioManagerAndroid.this.mLock) {
                        AudioManagerAndroid.this.mAudioDevices[1] = true;
                        AudioManagerAndroid.this.mAudioDevices[2] = false;
                        AudioManagerAndroid.this.mAudioDevices[4] = false;
                    }
                }
                if (AudioManagerAndroid.this.deviceHasBeenRequested()) {
                    AudioManagerAndroid.this.updateDeviceActivation();
                }
            }
        };
        this.mWiredHeadsetReceiver = broadcastReceiver;
        this.mContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    private void unregisterForWiredHeadsetIntentBroadcast() {
        this.mContext.unregisterReceiver(this.mWiredHeadsetReceiver);
        this.mWiredHeadsetReceiver = null;
    }

    private void registerForBluetoothHeadsetIntentBroadcast() {
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: org.chromium.media.AudioManagerAndroid.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                if (intExtra == 0) {
                    synchronized (AudioManagerAndroid.this.mLock) {
                        AudioManagerAndroid.this.mAudioDevices[3] = false;
                    }
                } else if (intExtra != 1) {
                    if (intExtra != 2) {
                        if (intExtra != 3) {
                            AudioManagerAndroid.loge("Invalid state");
                        }
                    } else {
                        synchronized (AudioManagerAndroid.this.mLock) {
                            AudioManagerAndroid.this.mAudioDevices[3] = true;
                        }
                    }
                }
            }
        };
        this.mBluetoothHeadsetReceiver = broadcastReceiver;
        this.mContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    private void unregisterForBluetoothHeadsetIntentBroadcast() {
        this.mContext.unregisterReceiver(this.mBluetoothHeadsetReceiver);
        this.mBluetoothHeadsetReceiver = null;
    }

    private void registerForBluetoothScoIntentBroadcast() {
        IntentFilter intentFilter = new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: org.chromium.media.AudioManagerAndroid.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", 0);
                if (intExtra == 0) {
                    if (AudioManagerAndroid.this.mBluetoothScoState != 3 && AudioManagerAndroid.this.deviceHasBeenRequested()) {
                        AudioManagerAndroid.this.updateDeviceActivation();
                    }
                    AudioManagerAndroid.this.mBluetoothScoState = 0;
                    return;
                }
                if (intExtra == 1) {
                    AudioManagerAndroid.this.mBluetoothScoState = 1;
                } else if (intExtra != 2) {
                    AudioManagerAndroid.loge("Invalid state");
                }
            }
        };
        this.mBluetoothScoReceiver = broadcastReceiver;
        this.mContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    private void unregisterForBluetoothScoIntentBroadcast() {
        this.mContext.unregisterReceiver(this.mBluetoothScoReceiver);
        this.mBluetoothScoReceiver = null;
    }

    private void startBluetoothSco() {
        int i;
        if (!this.mHasBluetoothPermission || (i = this.mBluetoothScoState) == 1 || i == 2) {
            return;
        }
        if (this.mAudioManager.isBluetoothScoOn()) {
            this.mBluetoothScoState = 1;
        } else {
            this.mBluetoothScoState = 2;
            this.mAudioManager.startBluetoothSco();
        }
    }

    private void stopBluetoothSco() {
        if (this.mHasBluetoothPermission) {
            int i = this.mBluetoothScoState;
            if (i == 1 || i == 2) {
                if (!this.mAudioManager.isBluetoothScoOn()) {
                    loge("Unable to stop BT SCO since it is already disabled");
                    this.mBluetoothScoState = 0;
                } else {
                    this.mBluetoothScoState = 3;
                    this.mAudioManager.stopBluetoothSco();
                }
            }
        }
    }

    private void setAudioDevice(int i) {
        if (i == 3) {
            startBluetoothSco();
        } else {
            stopBluetoothSco();
        }
        if (i == 0) {
            setSpeakerphoneOn(true);
        } else if (i == 1 || i == 2) {
            setSpeakerphoneOn(false);
        } else if (i != 3) {
            if (i == 4) {
                setSpeakerphoneOn(false);
            } else {
                loge("Invalid audio device selection");
            }
        }
        reportUpdate();
    }

    private static int selectDefaultDevice(boolean[] zArr) {
        if (zArr[1]) {
            return 1;
        }
        if (zArr[4]) {
            return 4;
        }
        return zArr[3] ? 3 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean deviceHasBeenRequested() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mRequestedAudioDevice != -1;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDeviceActivation() {
        int i;
        boolean[] zArr;
        synchronized (this.mLock) {
            i = this.mRequestedAudioDevice;
            zArr = (boolean[]) this.mAudioDevices.clone();
        }
        if (i == -1) {
            loge("Unable to activate device since no device is selected");
        } else if (i == -2 || !zArr[i]) {
            setAudioDevice(selectDefaultDevice(zArr));
        } else {
            setAudioDevice(i);
        }
    }

    private static int getNumOfAudioDevices(boolean[] zArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            if (zArr[i2]) {
                i++;
            }
        }
        return i;
    }

    private void logDeviceInfo() {
        logd("Android SDK: " + Build.VERSION.SDK_INT + ", Release: " + Build.VERSION.RELEASE + ", Brand: " + Build.BRAND + ", Device: " + Build.DEVICE + ", Id: " + Build.ID + ", Hardware: " + Build.HARDWARE + ", Manufacturer: " + Build.MANUFACTURER + ", Model: " + Build.MODEL + ", Product: " + Build.PRODUCT);
    }

    private static void logd(String str) {
        Log.d(TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loge(String str) {
        Log.e(TAG, str, new Object[0]);
    }

    private void startObservingVolumeChanges() {
        if (this.mSettingsObserverThread != null) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread("SettingsObserver");
        this.mSettingsObserverThread = handlerThread;
        handlerThread.start();
        this.mSettingsObserver = new ContentObserver(new Handler(this.mSettingsObserverThread.getLooper())) { // from class: org.chromium.media.AudioManagerAndroid.4
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                int streamVolume = AudioManagerAndroid.this.mAudioManager.getStreamVolume(0);
                AudioManagerAndroid audioManagerAndroid = AudioManagerAndroid.this;
                audioManagerAndroid.nativeSetMute(audioManagerAndroid.mNativeAudioManagerAndroid, streamVolume == 0);
            }
        };
        this.mContentResolver.registerContentObserver(Settings.System.CONTENT_URI, true, this.mSettingsObserver);
    }

    private void stopObservingVolumeChanges() {
        if (this.mSettingsObserverThread == null) {
            return;
        }
        this.mContentResolver.unregisterContentObserver(this.mSettingsObserver);
        this.mSettingsObserver = null;
        this.mSettingsObserverThread.quit();
        try {
            this.mSettingsObserverThread.join();
        } catch (InterruptedException e) {
            Log.e(TAG, "Thread.join() exception: ", e);
        }
        this.mSettingsObserverThread = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasUsbAudioCommInterface(UsbDevice usbDevice) {
        for (int i = 0; i < usbDevice.getInterfaceCount(); i++) {
            UsbInterface usbInterface = usbDevice.getInterface(i);
            if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 2) {
                return true;
            }
        }
        return false;
    }

    private void registerForUsbAudioIntentBroadcast() {
        this.mUsbAudioReceiver = new BroadcastReceiver() { // from class: org.chromium.media.AudioManagerAndroid.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (AudioManagerAndroid.this.hasUsbAudioCommInterface((UsbDevice) intent.getParcelableExtra("device"))) {
                    if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction())) {
                        synchronized (AudioManagerAndroid.this.mLock) {
                            if (!AudioManagerAndroid.this.hasWiredHeadset()) {
                                AudioManagerAndroid.this.mAudioDevices[4] = true;
                                AudioManagerAndroid.this.mAudioDevices[2] = false;
                            }
                        }
                    } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction()) && !AudioManagerAndroid.this.hasUsbAudio()) {
                        synchronized (AudioManagerAndroid.this.mLock) {
                            if (!AudioManagerAndroid.this.hasWiredHeadset()) {
                                AudioManagerAndroid.this.mAudioDevices[4] = false;
                                if (AudioManagerAndroid.this.hasEarpiece()) {
                                    AudioManagerAndroid.this.mAudioDevices[2] = true;
                                }
                            }
                        }
                    }
                    if (AudioManagerAndroid.this.deviceHasBeenRequested()) {
                        AudioManagerAndroid.this.updateDeviceActivation();
                    }
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        this.mContext.registerReceiver(this.mUsbAudioReceiver, intentFilter);
    }

    private void unregisterForUsbAudioIntentBroadcast() {
        this.mContext.unregisterReceiver(this.mUsbAudioReceiver);
        this.mUsbAudioReceiver = null;
    }
}
