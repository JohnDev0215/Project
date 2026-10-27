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
    protected static final String TAG = "PhoneManagerImpl";
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
        public /* synthetic */ void lambda$onReceive$0(int i) {
            synchronized (PhoneManagerImpl.this.mListenerList) {
                Iterator it = PhoneManagerImpl.this.mListenerList.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoPhoneListener) it.next()).onPhoneEventChanged(i);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceive$1(int i) {
            synchronized (PhoneManagerImpl.this.mListenerList) {
                Iterator it = PhoneManagerImpl.this.mListenerList.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoPhoneListener) it.next()).onPhoneMuteChanged(i);
                }
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AudioManager audioManager;
            BluetoothHeadsetClientCall bluetoothHeadsetClientCall;
            if (intent != null) {
                String action = intent.getAction();
                if ("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED".equals(action) && (bluetoothHeadsetClientCall = (BluetoothHeadsetClientCall) intent.getExtra("android.bluetooth.headsetclient.extra.CALL", null)) != null) {
                    final int state = bluetoothHeadsetClientCall.getState();
                    new Thread(new Runnable() { // from class: android.hardware.bydauto.phone.tsimpl.a
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.e.lambda$onReceive$0(state);
                        }
                    }, "callbackPhoneEventChanged").start();
                }
                if (!"android.media.STREAM_MUTE_CHANGED_ACTION".equals(action) || (audioManager = (AudioManager) intent.getExtra("android.media.EXTRA_STREAM_VOLUME_MUTED", null)) == null) {
                    return;
                }
                final int ringerMode = audioManager.getRingerMode();
                new Thread(new Runnable() { // from class: android.hardware.bydauto.phone.tsimpl.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.e.lambda$onReceive$1(ringerMode);
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

    public int getDevicetype() {
        return 0;
    }

    public int[] getFeatureList() {
        return null;
    }

    public String getGetPermission() {
        return null;
    }

    public int getPhoneEvent() {
        int state = this.mBTCall.getState();
        if (state == 0) {
            return 1;
        }
        if (state == 1) {
            return 3;
        }
        if (state == 7) {
            return 2;
        }
        Log.e(TAG, "getPhoneEvent run failed. ");
        return -2147482648;
    }

    public int getPhoneMute() {
        int ringerMode = this.mAudioManager.getRingerMode();
        if (ringerMode == 0) {
            return 0;
        }
        if (ringerMode == 2) {
            return 1;
        }
        Log.e(TAG, "getPhoneMute run failed. ");
        return -2147482648;
    }

    public String getSetPermission() {
        return null;
    }

    public int getType() {
        return 0;
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoPhoneListener absBYDAutoPhoneListener) {
        if (absBYDAutoPhoneListener == null || this.mListenerList.contains(absBYDAutoPhoneListener)) {
            return;
        }
        this.mListenerList.add(absBYDAutoPhoneListener);
    }

    public int setPhoneInfoState(int i) {
        if (i == 1) {
            this.mBTCall.setState(2);
            return 0;
        }
        if (i == 2) {
            this.mBTCall.setState(4);
            return 0;
        }
        if (i == 3) {
            this.mBTCall.setState(0);
            return 0;
        }
        if (i != 4) {
            Log.e(TAG, "setPhoneInfoState run failed, value is invalid. ");
            return -2147482645;
        }
        this.mBTCall.setState(7);
        return 0;
    }

    public int setPhoneMute(int i) {
        if (i == 0) {
            this.mAudioManager.setRingerMode(0);
            return 0;
        }
        if (i != 1) {
            Log.e(TAG, "setPhoneMute run failed, value is invalid. ");
            return -2147482645;
        }
        this.mAudioManager.setRingerMode(2);
        return 0;
    }

    public int[] setPhoneNameNumber(String str, String str2) {
        int[] iArr = new int[0];
        if (str.length() < 1 || str.length() > 160) {
            Log.e(TAG, "setPhoneNameNumber run failed, name is invalid. ");
            iArr[0] = -2147482645;
        } else {
            this.mContentValues.put("data2", str);
            iArr[0] = 0;
        }
        if (str2.length() < 1 || str2.length() > 20) {
            Log.e(TAG, "setPhoneNameNumber run failed, number is invalid. ");
            iArr[1] = -2147482645;
        } else {
            this.mContentValues.put("data1", str2);
            iArr[1] = 0;
        }
        return iArr;
    }

    public void unregisterListener(AbsBYDAutoPhoneListener absBYDAutoPhoneListener) {
        if (absBYDAutoPhoneListener != null && this.mListenerList.contains(absBYDAutoPhoneListener)) {
            this.mListenerList.remove(absBYDAutoPhoneListener);
        }
        if (this.mListenerList == null) {
            this.mContext.unregisterReceiver(this.mReceiver);
        }
    }

    public void registerListener(AbsBYDAutoPhoneListener absBYDAutoPhoneListener, int[] iArr) {
        registerListener(absBYDAutoPhoneListener);
    }

    public void getAllStatus() {
    }

    public void setAllStatus() {
    }
}
