package com.byd.minikaraoke.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.byd.minikaraoke.IBufferCallback;
import com.byd.minikaraoke.IConnectionStateListener;
import com.byd.minikaraoke.IErrorListener;
import com.byd.minikaraoke.IMicrophoneService;
import com.byd.minikaraoke.api.MicrophoneService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class MicrophoneServiceImpl {
    private static final String ACTION_MICROPHONE_SERVICE = "byd.intent.action.MICROPHONE_SERVICE";
    private static final String PACKAGE_NAME = "com.byd.minikaraoke";
    private static final String TAG = "MicrophoneServiceImpl";
    private MicrophoneService.BufferCallback mBufferCallback;
    private final Context mContext;
    private IMicrophoneService mMicrophoneService;
    private final List<MicrophoneService.MicrophoneConnectionStateListener> mStateListeners = new ArrayList();
    private final List<MicrophoneService.ErrorListener> mErrorListeners = new ArrayList();
    private boolean mConnectionState = false;
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() { // from class: com.byd.minikaraoke.api.MicrophoneServiceImpl.1
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Log.d(MicrophoneServiceImpl.TAG, "binderDied");
            MicrophoneServiceImpl.this.mMicrophoneService.asBinder().unlinkToDeath(MicrophoneServiceImpl.this.mDeathRecipient, 0);
            MicrophoneServiceImpl.this.mMicrophoneService = null;
            MicrophoneServiceImpl.this.connect();
        }
    };
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.byd.minikaraoke.api.MicrophoneServiceImpl.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(MicrophoneServiceImpl.TAG, "onServiceDisconnected");
            MicrophoneServiceImpl.this.mMicrophoneService = IMicrophoneService.Stub.asInterface(iBinder);
            try {
                MicrophoneServiceImpl.this.mMicrophoneService.setBufferCallback(MicrophoneServiceImpl.this.mIBufferCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                MicrophoneServiceImpl.this.mMicrophoneService.registerMicrophoneConnectionStateListener(MicrophoneServiceImpl.this.mStateListener);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            try {
                MicrophoneServiceImpl.this.mMicrophoneService.registerErrorListener(MicrophoneServiceImpl.this.mErrorListener);
            } catch (RemoteException e3) {
                e3.printStackTrace();
            }
            try {
                iBinder.linkToDeath(MicrophoneServiceImpl.this.mDeathRecipient, 0);
            } catch (RemoteException e4) {
                e4.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(MicrophoneServiceImpl.TAG, "onServiceDisconnected");
            MicrophoneServiceImpl.this.mMicrophoneService = null;
            MicrophoneServiceImpl.this.mConnectionState = false;
            Iterator it = MicrophoneServiceImpl.this.mStateListeners.iterator();
            while (it.hasNext()) {
                ((MicrophoneService.MicrophoneConnectionStateListener) it.next()).onMicrophoneConnectionStateChanged(false);
            }
        }
    };
    private final IConnectionStateListener mStateListener = new IConnectionStateListener.Stub() { // from class: com.byd.minikaraoke.api.MicrophoneServiceImpl.3
        @Override // com.byd.minikaraoke.IConnectionStateListener
        public void onMicrophoneConnectionStateChanged(boolean z) throws RemoteException {
            Log.d(MicrophoneServiceImpl.TAG, "onMicrophoneConnectionStateChanged, mConnectionState: " + MicrophoneServiceImpl.this.mConnectionState + ", isConnected: " + z);
            if (MicrophoneServiceImpl.this.mConnectionState != z) {
                MicrophoneServiceImpl.this.mConnectionState = z;
                Iterator it = MicrophoneServiceImpl.this.mStateListeners.iterator();
                while (it.hasNext()) {
                    ((MicrophoneService.MicrophoneConnectionStateListener) it.next()).onMicrophoneConnectionStateChanged(z);
                }
            }
        }
    };
    private final IBufferCallback mIBufferCallback = new IBufferCallback.Stub() { // from class: com.byd.minikaraoke.api.MicrophoneServiceImpl.4
        @Override // com.byd.minikaraoke.IBufferCallback
        public void onBufferAvailable(byte[] bArr) throws RemoteException {
            Log.d(MicrophoneServiceImpl.TAG, "onBufferAvailable, length: " + bArr.length);
            if (MicrophoneServiceImpl.this.mBufferCallback != null) {
                MicrophoneServiceImpl.this.mBufferCallback.onBufferAvailable(bArr);
            }
        }
    };
    private final IErrorListener mErrorListener = new IErrorListener.Stub() { // from class: com.byd.minikaraoke.api.MicrophoneServiceImpl.5
        @Override // com.byd.minikaraoke.IErrorListener
        public void onError(String str) throws RemoteException {
            Log.e(MicrophoneServiceImpl.TAG, "onError: " + str);
            Iterator it = MicrophoneServiceImpl.this.mErrorListeners.iterator();
            while (it.hasNext()) {
                ((MicrophoneService.ErrorListener) it.next()).onError(str);
            }
        }
    };

    public MicrophoneServiceImpl(Context context) {
        this.mContext = context;
    }

    boolean connect() {
        Log.d(TAG, "connect");
        Intent intent = new Intent(ACTION_MICROPHONE_SERVICE);
        intent.setPackage("com.byd.minikaraoke");
        return this.mContext.bindService(intent, this.mServiceConnection, 1);
    }

    boolean disconnect() {
        Log.d(TAG, "disconnect");
        IMicrophoneService iMicrophoneService = this.mMicrophoneService;
        if (iMicrophoneService == null) {
            return false;
        }
        try {
            iMicrophoneService.unregisterMicrophoneConnectionStateListener(this.mStateListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            this.mMicrophoneService.unregisterErrorListener(this.mErrorListener);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        try {
            this.mMicrophoneService.setBufferCallback(null);
        } catch (RemoteException e3) {
            e3.printStackTrace();
        }
        this.mMicrophoneService.asBinder().unlinkToDeath(this.mDeathRecipient, 0);
        this.mContext.unbindService(this.mServiceConnection);
        return true;
    }

    void registerMicrophoneConnectionStateListener(MicrophoneService.MicrophoneConnectionStateListener microphoneConnectionStateListener) {
        Log.d(TAG, "registerMicrophoneConnectionStateListener, listener: " + microphoneConnectionStateListener);
        if (microphoneConnectionStateListener == null || this.mStateListeners.contains(microphoneConnectionStateListener)) {
            return;
        }
        this.mStateListeners.add(microphoneConnectionStateListener);
        microphoneConnectionStateListener.onMicrophoneConnectionStateChanged(this.mConnectionState);
    }

    void unregisterMicrophoneConnectionStateListener(MicrophoneService.MicrophoneConnectionStateListener microphoneConnectionStateListener) {
        Log.d(TAG, "unregisterMicrophoneConnectionStateListener, listener: " + microphoneConnectionStateListener);
        this.mStateListeners.remove(microphoneConnectionStateListener);
    }

    void setBufferCallback(MicrophoneService.BufferCallback bufferCallback) {
        Log.d(TAG, "setBufferCallback, callback: " + bufferCallback);
        if (bufferCallback == null) {
            return;
        }
        this.mBufferCallback = bufferCallback;
    }

    int sendBuffer(byte[] bArr) {
        Log.d(TAG, "sendBuffer, length: " + bArr.length);
        IMicrophoneService iMicrophoneService = this.mMicrophoneService;
        if (iMicrophoneService == null) {
            return -1;
        }
        try {
            return iMicrophoneService.sendBuffer(bArr);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    void registerErrorListener(MicrophoneService.ErrorListener errorListener) {
        Log.d(TAG, "registerErrorListener, listener: " + errorListener);
        if (errorListener == null || this.mErrorListeners.contains(errorListener)) {
            return;
        }
        this.mErrorListeners.add(errorListener);
    }

    void unregisterErrorListener(MicrophoneService.ErrorListener errorListener) {
        Log.d(TAG, "unregisterErrorListener, listener: " + errorListener);
        if (errorListener == null) {
            return;
        }
        this.mErrorListeners.remove(errorListener);
    }

    boolean increaseMicVolume() {
        Log.d(TAG, "increaseMicVolume");
        IMicrophoneService iMicrophoneService = this.mMicrophoneService;
        if (iMicrophoneService == null) {
            return false;
        }
        try {
            return iMicrophoneService.increaseMicVolume();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean decreaseMicVolume() {
        Log.d(TAG, "decreaseMicVolume");
        IMicrophoneService iMicrophoneService = this.mMicrophoneService;
        if (iMicrophoneService == null) {
            return false;
        }
        try {
            return iMicrophoneService.decreaseMicVolume();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean setMicVolume(int i) {
        Log.d(TAG, "setMicVolume, volume: " + i);
        IMicrophoneService iMicrophoneService = this.mMicrophoneService;
        if (iMicrophoneService == null) {
            return false;
        }
        try {
            return iMicrophoneService.setMicVolume(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getMicVolume() {
        Log.d(TAG, "getMicVolume");
        IMicrophoneService iMicrophoneService = this.mMicrophoneService;
        if (iMicrophoneService == null) {
            return -1;
        }
        try {
            return iMicrophoneService.getMicVolume();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean getMicConnectionState() {
        Log.d(TAG, "getMicConnectionState");
        IMicrophoneService iMicrophoneService = this.mMicrophoneService;
        if (iMicrophoneService == null) {
            return false;
        }
        try {
            return iMicrophoneService.getMicConnectionState();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
}
