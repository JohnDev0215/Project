package com.android.bluetooth.hfp;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.IBluetoothHeadsetPhone;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.RemoteException;
import android.util.Log;
import com.android.vcard.VCardConfig;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class HeadsetSystemInterface {
    private static final boolean DBG = false;
    private static final String TAG = HeadsetSystemInterface.class.getSimpleName();
    private final AudioManager mAudioManager;
    private final HeadsetPhoneState mHeadsetPhoneState;
    private final HeadsetService mHeadsetService;
    private volatile IBluetoothHeadsetPhone mPhoneProxy;
    private final ServiceConnection mPhoneProxyConnection = new ServiceConnection() { // from class: com.android.bluetooth.hfp.HeadsetSystemInterface.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName className, IBinder service) {
            synchronized (HeadsetSystemInterface.this) {
                HeadsetSystemInterface.this.mPhoneProxy = IBluetoothHeadsetPhone.Stub.asInterface(service);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName className) {
            synchronized (HeadsetSystemInterface.this) {
                HeadsetSystemInterface.this.mPhoneProxy = null;
            }
        }
    };
    private PowerManager.WakeLock mVoiceRecognitionWakeLock;

    HeadsetSystemInterface(HeadsetService headsetService) {
        if (headsetService == null) {
            Log.wtf(TAG, "HeadsetService parameter is null");
        }
        this.mHeadsetService = headsetService;
        this.mAudioManager = (AudioManager) headsetService.getSystemService("audio");
        PowerManager powerManager = (PowerManager) this.mHeadsetService.getSystemService("power");
        PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, TAG + ":VoiceRecognition");
        this.mVoiceRecognitionWakeLock = wakeLockNewWakeLock;
        wakeLockNewWakeLock.setReferenceCounted(false);
        this.mHeadsetPhoneState = new HeadsetPhoneState(this.mHeadsetService);
    }

    public synchronized void init() {
        Intent intent = new Intent(IBluetoothHeadsetPhone.class.getName());
        intent.setComponent(resolveSystemService(this.mHeadsetService.getPackageManager(), 0, intent));
        if (intent.getComponent() == null || !this.mHeadsetService.bindService(intent, this.mPhoneProxyConnection, 0)) {
            Log.wtf(TAG, "Could not bind to IBluetoothHeadsetPhone Service, intent=" + intent);
        }
    }

    private ComponentName resolveSystemService(PackageManager pm, int flags, Intent intent) {
        if (intent.getComponent() != null) {
            return intent.getComponent();
        }
        List<ResolveInfo> results = pm.queryIntentServices(intent, flags);
        if (results == null) {
            return null;
        }
        ComponentName comp = null;
        for (int i = 0; i < results.size(); i++) {
            ResolveInfo ri = results.get(i);
            if ((ri.serviceInfo.applicationInfo.flags & 1) != 0) {
                ComponentName foundComp = new ComponentName(ri.serviceInfo.applicationInfo.packageName, ri.serviceInfo.name);
                if (comp != null) {
                    throw new IllegalStateException("Multiple system services handle " + this + ": " + comp + ", " + foundComp);
                }
                comp = foundComp;
            }
        }
        return comp;
    }

    public synchronized void stop() {
        if (this.mPhoneProxy != null) {
            this.mPhoneProxy = null;
            this.mHeadsetService.unbindService(this.mPhoneProxyConnection);
        }
        this.mHeadsetPhoneState.cleanup();
    }

    public AudioManager getAudioManager() {
        return this.mAudioManager;
    }

    public PowerManager.WakeLock getVoiceRecognitionWakeLock() {
        return this.mVoiceRecognitionWakeLock;
    }

    public HeadsetPhoneState getHeadsetPhoneState() {
        return this.mHeadsetPhoneState;
    }

    public void answerCall(BluetoothDevice device) {
        if (device == null) {
            Log.w(TAG, "answerCall device is null");
            return;
        }
        if (this.mPhoneProxy != null) {
            try {
                this.mHeadsetService.setActiveDevice(device);
                this.mPhoneProxy.answerCall();
                return;
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
                return;
            }
        }
        Log.e(TAG, "Handsfree phone proxy null for answering call");
    }

    public void hangupCall(BluetoothDevice device) {
        if (device == null) {
            Log.w(TAG, "hangupCall device is null");
            return;
        }
        if (this.mHeadsetService.isVirtualCallStarted()) {
            this.mHeadsetService.stopScoUsingVirtualVoiceCall();
        } else {
            if (this.mPhoneProxy != null) {
                try {
                    this.mPhoneProxy.hangupCall();
                    return;
                } catch (RemoteException e) {
                    Log.e(TAG, Log.getStackTraceString(new Throwable()));
                    return;
                }
            }
            Log.e(TAG, "Handsfree phone proxy null for hanging up call");
        }
    }

    public boolean sendDtmf(int dtmf, BluetoothDevice device) {
        if (device == null) {
            Log.w(TAG, "sendDtmf device is null");
            return false;
        }
        if (this.mPhoneProxy != null) {
            try {
                return this.mPhoneProxy.sendDtmf(dtmf);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        } else {
            Log.e(TAG, "Handsfree phone proxy null for sending DTMF");
        }
        return false;
    }

    public boolean processChld(int chld) {
        if (this.mPhoneProxy != null) {
            try {
                return this.mPhoneProxy.processChld(chld);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
                return false;
            }
        }
        Log.e(TAG, "Handsfree phone proxy null for sending DTMF");
        return false;
    }

    public String getNetworkOperator() {
        IBluetoothHeadsetPhone phoneProxy = this.mPhoneProxy;
        if (phoneProxy == null) {
            Log.e(TAG, "getNetworkOperator() failed: mPhoneProxy is null");
            return null;
        }
        try {
            return this.mPhoneProxy.getNetworkOperator();
        } catch (RemoteException exception) {
            Log.e(TAG, "getNetworkOperator() failed: " + exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    public String getSubscriberNumber() {
        IBluetoothHeadsetPhone phoneProxy = this.mPhoneProxy;
        if (phoneProxy == null) {
            Log.e(TAG, "getSubscriberNumber() failed: mPhoneProxy is null");
            return null;
        }
        try {
            return this.mPhoneProxy.getSubscriberNumber();
        } catch (RemoteException exception) {
            Log.e(TAG, "getSubscriberNumber() failed: " + exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    public boolean listCurrentCalls() {
        IBluetoothHeadsetPhone phoneProxy = this.mPhoneProxy;
        if (phoneProxy == null) {
            Log.e(TAG, "listCurrentCalls() failed: mPhoneProxy is null");
            return false;
        }
        try {
            return this.mPhoneProxy.listCurrentCalls();
        } catch (RemoteException exception) {
            Log.e(TAG, "listCurrentCalls() failed: " + exception.getMessage());
            exception.printStackTrace();
            return false;
        }
    }

    public void queryPhoneState() {
        IBluetoothHeadsetPhone phoneProxy = this.mPhoneProxy;
        if (phoneProxy != null) {
            try {
                this.mPhoneProxy.queryPhoneState();
                return;
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
                return;
            }
        }
        Log.e(TAG, "Handsfree phone proxy null for query phone state");
    }

    public boolean isInCall() {
        return this.mHeadsetPhoneState.getNumActiveCall() > 0 || this.mHeadsetPhoneState.getNumHeldCall() > 0 || !(this.mHeadsetPhoneState.getCallState() == 6 || this.mHeadsetPhoneState.getCallState() == 4);
    }

    public boolean isRinging() {
        return this.mHeadsetPhoneState.getCallState() == 4;
    }

    public boolean isCallIdle() {
        return (isInCall() || isRinging()) ? false : true;
    }

    public boolean activateVoiceRecognition() {
        Intent intent = new Intent("android.intent.action.VOICE_COMMAND");
        intent.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
        try {
            this.mHeadsetService.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "activateVoiceRecognition, failed due to activity not found for " + intent);
            return false;
        }
    }

    public boolean deactivateVoiceRecognition() {
        return true;
    }
}
