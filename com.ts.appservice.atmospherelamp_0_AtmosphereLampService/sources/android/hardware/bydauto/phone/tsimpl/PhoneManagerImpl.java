package android.hardware.bydauto.phone.tsimpl;

import android.bluetooth.BluetoothHeadsetClientCall;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.phone.AbsBYDAutoPhoneListener;
import android.media.AudioManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PhoneManagerImpl {
    public static final int PHONE_COMMAND_BUSY = -2147482647;
    public static final int PHONE_COMMAND_FAILED = -2147482648;
    public static final int PHONE_COMMAND_INVALID = -2147482645;
    public static final int PHONE_COMMAND_SUCCESS = 0;
    public static final int PHONE_COMMAND_TIMEOUT = -2147482646;
    public static final int PHONE_EVENT_ANSWER = 1;
    public static final int PHONE_EVENT_HANGUP = 3;
    public static final int PHONE_EVENT_REJECT = 2;
    public static final int PHONE_INFO_CALLING = 2;
    public static final int PHONE_INFO_DAIL = 1;
    public static final int PHONE_INFO_HANGUP = 4;
    public static final int PHONE_INFO_ONLINE = 3;
    public static final int PHONE_MUTE_OFF = 1;
    public static final int PHONE_MUTE_ON = 0;
    public static final int PHONE_NAME_LEN_MAX = 160;
    public static final int PHONE_NAME_LEN_MIN = 1;
    public static final int PHONE_NUMBER_LEN_MAX = 20;
    public static final int PHONE_NUMBER_LEN_MIN = 1;
    public static final int PHONE_RESULT_NAME = 0;
    public static final int PHONE_RESULT_NUMBER = 1;
    protected static final String TAG = "PhoneManagerImpl";
    private static PhoneManagerImpl sInstance;
    private AudioManager mAudioManager;
    private BluetoothHeadsetClientCall mBTCall;
    private ContentValues mContentValues;
    private Context mContext;
    private IntentFilter mIntentFilter;
    private final List<AbsBYDAutoPhoneListener> mListenerList = Collections.synchronizedList(new ArrayList());
    private final BroadcastReceiver mReceiver = new AnonymousClass1();

    PhoneManagerImpl(Context context) {
        this.mContext = context;
        initPhoneManagerImpl();
    }

    private void initPhoneManagerImpl() {
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        this.mBTCall = (BluetoothHeadsetClientCall) this.mContext.getSystemService("bluetooth");
        this.mContentValues = new ContentValues();
        IntentFilter intentFilter = new IntentFilter();
        this.mIntentFilter = intentFilter;
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED");
        this.mIntentFilter.addAction("android.media.STREAM_MUTE_CHANGED_ACTION");
        this.mContext.registerReceiver(this.mReceiver, this.mIntentFilter);
    }

    /* JADX INFO: renamed from: android.hardware.bydauto.phone.tsimpl.PhoneManagerImpl$1, reason: invalid class name */
    class AnonymousClass1 extends BroadcastReceiver {
        AnonymousClass1() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AudioManager audioManager;
            BluetoothHeadsetClientCall btCall;
            if (intent != null) {
                String action = intent.getAction();
                Log.d(PhoneManagerImpl.TAG, "BTService receiver action == " + action);
                if ("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED".equals(action) && (btCall = (BluetoothHeadsetClientCall) intent.getExtra("android.bluetooth.headsetclient.extra.CALL", null)) != null) {
                    final int callState = btCall.getState();
                    new Thread(new Runnable() { // from class: android.hardware.bydauto.phone.tsimpl.-$$Lambda$PhoneManagerImpl$1$MEq63QupI_i4g4G5zHKq_ru6w7Q
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onReceive$0$PhoneManagerImpl$1(callState);
                        }
                    }, "callbackPhoneEventChanged").start();
                }
                if ("android.media.STREAM_MUTE_CHANGED_ACTION".equals(action) && (audioManager = (AudioManager) intent.getExtra("android.media.EXTRA_STREAM_VOLUME_MUTED", null)) != null) {
                    final int muteState = audioManager.getRingerMode();
                    new Thread(new Runnable() { // from class: android.hardware.bydauto.phone.tsimpl.-$$Lambda$PhoneManagerImpl$1$CnGmoiZhsDpK8V9N5B6VphhxDqs
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onReceive$1$PhoneManagerImpl$1(muteState);
                        }
                    }, "callbackPhoneMuteChanged").start();
                }
            }
        }

        public /* synthetic */ void lambda$onReceive$0$PhoneManagerImpl$1(int callState) {
            synchronized (PhoneManagerImpl.this.mListenerList) {
                for (AbsBYDAutoPhoneListener absBYDAutoPhoneListener : PhoneManagerImpl.this.mListenerList) {
                    absBYDAutoPhoneListener.onPhoneEventChanged(callState);
                }
            }
        }

        public /* synthetic */ void lambda$onReceive$1$PhoneManagerImpl$1(int muteState) {
            synchronized (PhoneManagerImpl.this.mListenerList) {
                for (AbsBYDAutoPhoneListener absBYDAutoPhoneListener : PhoneManagerImpl.this.mListenerList) {
                    absBYDAutoPhoneListener.onPhoneMuteChanged(muteState);
                }
            }
        }
    }

    public static PhoneManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (PhoneManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new PhoneManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public int getDevicetype() {
        Log.d(TAG, "getDevicetype is empty implementation. ");
        return 0;
    }

    public String getSetPermission() {
        Log.d(TAG, "getSetPermission is empty implementation. ");
        return null;
    }

    public String getGetPermission() {
        Log.d(TAG, "getGetPermission is empty implementation. ");
        return null;
    }

    public int setPhoneInfoState(int value) {
        Log.d(TAG, "setPhoneInfoState is run. ");
        if (value == 1) {
            this.mBTCall.setState(2);
            Log.d(TAG, "setPhoneInfoState : set dail. ");
            return 0;
        }
        if (value == 2) {
            this.mBTCall.setState(4);
            Log.d(TAG, "setPhoneInfoState : set calling. ");
            return 0;
        }
        if (value == 3) {
            this.mBTCall.setState(0);
            Log.d(TAG, "setPhoneInfoState : set online. ");
            return 0;
        }
        if (value != 4) {
            Log.e(TAG, "setPhoneInfoState run failed, value is invalid. ");
            return -2147482645;
        }
        this.mBTCall.setState(7);
        Log.d(TAG, "setPhoneInfoState : set hangup. ");
        return 0;
    }

    public int getPhoneEvent() {
        Log.d(TAG, "getPhoneEvent is run. ");
        int event = this.mBTCall.getState();
        if (event == 0) {
            Log.d(TAG, "getPhoneEvent : answer. ");
            return 1;
        }
        if (event == 1) {
            Log.d(TAG, "getPhoneEvent : hangup. ");
            return 3;
        }
        if (event != 7) {
            Log.e(TAG, "getPhoneEvent run failed. ");
            return -2147482648;
        }
        Log.d(TAG, "getPhoneEvent : reject. ");
        return 2;
    }

    public int setPhoneMute(int value) {
        Log.d(TAG, "setPhoneMute is run. ");
        if (value == 0) {
            this.mAudioManager.setRingerMode(0);
            Log.d(TAG, "setPhoneMute : set mute on. ");
            return 0;
        }
        if (value != 1) {
            Log.e(TAG, "setPhoneMute run failed, value is invalid. ");
            return -2147482645;
        }
        this.mAudioManager.setRingerMode(2);
        Log.d(TAG, "setPhoneMute : set mute off. ");
        return 0;
    }

    public int getPhoneMute() {
        Log.d(TAG, "getPhoneMute is run. ");
        int mode = this.mAudioManager.getRingerMode();
        if (mode == 0) {
            Log.d(TAG, "getPhoneMute : mute is on. ");
            return 0;
        }
        if (mode != 2) {
            Log.e(TAG, "getPhoneMute run failed. ");
            return -2147482648;
        }
        Log.d(TAG, "getPhoneMute : mute is off. ");
        return 1;
    }

    public int[] setPhoneNameNumber(String name, String number) {
        Log.d(TAG, "setPhoneNameNumber is run. ");
        int[] result = new int[0];
        if (name.length() < 1 || name.length() > 160) {
            Log.e(TAG, "setPhoneNameNumber run failed, name is invalid. ");
            result[0] = -2147482645;
        } else {
            this.mContentValues.put("data2", name);
            Log.d(TAG, "setPhoneNameNumber : set name. ");
            result[0] = 0;
        }
        if (number.length() < 1 || number.length() > 20) {
            Log.e(TAG, "setPhoneNameNumber run failed, number is invalid. ");
            result[1] = -2147482645;
        } else {
            this.mContentValues.put("data1", number);
            Log.d(TAG, "setPhoneNameNumber : set number. ");
            result[1] = 0;
        }
        return result;
    }

    public void setAllStatus() {
        Log.d(TAG, "setAllStatus is empty implementation. ");
    }

    public void getAllStatus() {
        Log.d(TAG, "getAllStatus is empty implementation. ");
    }

    public boolean postEvent(int deviceType, int eventType, int value, Object data) {
        Log.d(TAG, "postEvent is empty implementation. ");
        return false;
    }

    public int getType() {
        Log.d(TAG, "getType is empty implementation. ");
        return 0;
    }

    public int[] getFeatureList() {
        Log.d(TAG, "getFeatureList is empty implementation. ");
        return null;
    }

    public void registerListener(AbsBYDAutoPhoneListener l) {
        Log.d(TAG, "registerListener one para is run. ");
        if (l != null && !this.mListenerList.contains(l)) {
            this.mListenerList.add(l);
        }
    }

    public void registerListener(AbsBYDAutoPhoneListener l, int[] featureIDs) {
        Log.d(TAG, "registerListener two para is run. ");
        registerListener(l);
    }

    public void unregisterListener(AbsBYDAutoPhoneListener l) {
        Log.d(TAG, "unregisterListener is run. ");
        if (l != null && this.mListenerList.contains(l)) {
            this.mListenerList.remove(l);
        }
        if (this.mListenerList == null) {
            this.mContext.unregisterReceiver(this.mReceiver);
        }
    }
}
