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
import java.util.Iterator;
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
    public static final String TAG = "PhoneManagerImpl";
    private static PhoneManagerImpl sInstance;
    private AudioManager mAudioManager;
    private BluetoothHeadsetClientCall mBTCall;
    private ContentValues mContentValues;
    private Context mContext;
    private IntentFilter mIntentFilter;
    private final List<AbsBYDAutoPhoneListener> mListenerList = Collections.synchronizedList(new ArrayList());
    private final BroadcastReceiver mReceiver = new AnonymousClass1();

    /* JADX INFO: renamed from: android.hardware.bydauto.phone.tsimpl.PhoneManagerImpl$1, reason: invalid class name */
    public class AnonymousClass1 extends BroadcastReceiver {
        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onReceive$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void a(int i2) {
            synchronized (PhoneManagerImpl.this.mListenerList) {
                Iterator it = PhoneManagerImpl.this.mListenerList.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoPhoneListener) it.next()).onPhoneEventChanged(i2);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onReceive$1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(int i2) {
            synchronized (PhoneManagerImpl.this.mListenerList) {
                Iterator it = PhoneManagerImpl.this.mListenerList.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoPhoneListener) it.next()).onPhoneMuteChanged(i2);
                }
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AudioManager audioManager;
            BluetoothHeadsetClientCall bluetoothHeadsetClientCall;
            if (intent != null) {
                String action = intent.getAction();
                Log.d(PhoneManagerImpl.TAG, "BTService receiver action == " + action);
                if ("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED".equals(action) && (bluetoothHeadsetClientCall = (BluetoothHeadsetClientCall) intent.getExtra("android.bluetooth.headsetclient.extra.CALL", null)) != null) {
                    final int state = bluetoothHeadsetClientCall.getState();
                    new Thread(new Runnable() { // from class: a.b.e.b.a.a
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f9c.a(state);
                        }
                    }, "callbackPhoneEventChanged").start();
                }
                if (!"android.media.STREAM_MUTE_CHANGED_ACTION".equals(action) || (audioManager = (AudioManager) intent.getExtra("android.media.EXTRA_STREAM_VOLUME_MUTED", null)) == null) {
                    return;
                }
                final int ringerMode = audioManager.getRingerMode();
                new Thread(new Runnable() { // from class: a.b.e.b.a.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f11c.b(ringerMode);
                    }
                }, "callbackPhoneMuteChanged").start();
            }
        }
    }

    public PhoneManagerImpl(Context context) {
        this.mContext = context;
        initPhoneManagerImpl();
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

    public void getAllStatus() {
        Log.d(TAG, "getAllStatus is empty implementation. ");
    }

    public int getDevicetype() {
        Log.d(TAG, "getDevicetype is empty implementation. ");
        return 0;
    }

    public int[] getFeatureList() {
        Log.d(TAG, "getFeatureList is empty implementation. ");
        return null;
    }

    public String getGetPermission() {
        Log.d(TAG, "getGetPermission is empty implementation. ");
        return null;
    }

    public int getPhoneEvent() {
        Log.d(TAG, "getPhoneEvent is run. ");
        int state = this.mBTCall.getState();
        if (state == 0) {
            Log.d(TAG, "getPhoneEvent : answer. ");
            return 1;
        }
        if (state == 1) {
            Log.d(TAG, "getPhoneEvent : hangup. ");
            return 3;
        }
        if (state != 7) {
            Log.e(TAG, "getPhoneEvent run failed. ");
            return -2147482648;
        }
        Log.d(TAG, "getPhoneEvent : reject. ");
        return 2;
    }

    public int getPhoneMute() {
        Log.d(TAG, "getPhoneMute is run. ");
        int ringerMode = this.mAudioManager.getRingerMode();
        if (ringerMode == 0) {
            Log.d(TAG, "getPhoneMute : mute is on. ");
            return 0;
        }
        if (ringerMode != 2) {
            Log.e(TAG, "getPhoneMute run failed. ");
            return -2147482648;
        }
        Log.d(TAG, "getPhoneMute : mute is off. ");
        return 1;
    }

    public String getSetPermission() {
        Log.d(TAG, "getSetPermission is empty implementation. ");
        return null;
    }

    public int getType() {
        Log.d(TAG, "getType is empty implementation. ");
        return 0;
    }

    public boolean postEvent(int i2, int i3, int i4, Object obj) {
        Log.d(TAG, "postEvent is empty implementation. ");
        return false;
    }

    public void registerListener(AbsBYDAutoPhoneListener absBYDAutoPhoneListener) {
        Log.d(TAG, "registerListener one para is run. ");
        if (absBYDAutoPhoneListener == null || this.mListenerList.contains(absBYDAutoPhoneListener)) {
            return;
        }
        this.mListenerList.add(absBYDAutoPhoneListener);
    }

    public void setAllStatus() {
        Log.d(TAG, "setAllStatus is empty implementation. ");
    }

    public int setPhoneInfoState(int i2) {
        Log.d(TAG, "setPhoneInfoState is run. ");
        if (i2 == 1) {
            this.mBTCall.setState(2);
            Log.d(TAG, "setPhoneInfoState : set dail. ");
            return 0;
        }
        if (i2 == 2) {
            this.mBTCall.setState(4);
            Log.d(TAG, "setPhoneInfoState : set calling. ");
            return 0;
        }
        if (i2 == 3) {
            this.mBTCall.setState(0);
            Log.d(TAG, "setPhoneInfoState : set online. ");
            return 0;
        }
        if (i2 != 4) {
            Log.e(TAG, "setPhoneInfoState run failed, value is invalid. ");
            return -2147482645;
        }
        this.mBTCall.setState(7);
        Log.d(TAG, "setPhoneInfoState : set hangup. ");
        return 0;
    }

    public int setPhoneMute(int i2) {
        Log.d(TAG, "setPhoneMute is run. ");
        if (i2 == 0) {
            this.mAudioManager.setRingerMode(0);
            Log.d(TAG, "setPhoneMute : set mute on. ");
            return 0;
        }
        if (i2 != 1) {
            Log.e(TAG, "setPhoneMute run failed, value is invalid. ");
            return -2147482645;
        }
        this.mAudioManager.setRingerMode(2);
        Log.d(TAG, "setPhoneMute : set mute off. ");
        return 0;
    }

    public int[] setPhoneNameNumber(String str, String str2) {
        Log.d(TAG, "setPhoneNameNumber is run. ");
        int[] iArr = new int[0];
        if (str.length() < 1 || str.length() > 160) {
            Log.e(TAG, "setPhoneNameNumber run failed, name is invalid. ");
            iArr[0] = -2147482645;
        } else {
            this.mContentValues.put("data2", str);
            Log.d(TAG, "setPhoneNameNumber : set name. ");
            iArr[0] = 0;
        }
        if (str2.length() < 1 || str2.length() > 20) {
            Log.e(TAG, "setPhoneNameNumber run failed, number is invalid. ");
            iArr[1] = -2147482645;
        } else {
            this.mContentValues.put("data1", str2);
            Log.d(TAG, "setPhoneNameNumber : set number. ");
            iArr[1] = 0;
        }
        return iArr;
    }

    public void unregisterListener(AbsBYDAutoPhoneListener absBYDAutoPhoneListener) {
        Log.d(TAG, "unregisterListener is run. ");
        if (absBYDAutoPhoneListener != null && this.mListenerList.contains(absBYDAutoPhoneListener)) {
            this.mListenerList.remove(absBYDAutoPhoneListener);
        }
        if (this.mListenerList == null) {
            this.mContext.unregisterReceiver(this.mReceiver);
        }
    }

    public void registerListener(AbsBYDAutoPhoneListener absBYDAutoPhoneListener, int[] iArr) {
        Log.d(TAG, "registerListener two para is run. ");
        registerListener(absBYDAutoPhoneListener);
    }
}
