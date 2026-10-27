package com.byd.minikaraoke.api;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MicrophoneService {
    private static final String TAG = "MicrophoneService";
    private static MicrophoneService mInstance;
    private final MicrophoneServiceImpl mImpl;

    public interface BufferCallback {
        void onBufferAvailable(byte[] bArr);
    }

    public interface ErrorListener {
        void onError(String str);
    }

    public interface MicrophoneConnectionStateListener {
        void onMicrophoneConnectionStateChanged(boolean z);
    }

    private MicrophoneService(Context context) {
        this.mImpl = new MicrophoneServiceImpl(context);
    }

    public static MicrophoneService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MicrophoneService(context);
        }
        return mInstance;
    }

    public boolean connect() {
        Log.d(TAG, "connect");
        MicrophoneServiceImpl microphoneServiceImpl = this.mImpl;
        if (microphoneServiceImpl != null) {
            return microphoneServiceImpl.connect();
        }
        return false;
    }

    public boolean disconnect() {
        Log.d(TAG, "disconnect");
        MicrophoneServiceImpl microphoneServiceImpl = this.mImpl;
        if (microphoneServiceImpl != null) {
            return microphoneServiceImpl.disconnect();
        }
        return false;
    }

    public void registerMicrophoneConnectionStateListener(MicrophoneConnectionStateListener microphoneConnectionStateListener) {
        Log.d(TAG, "registerMicrophoneConnectionStateListener, listener: " + microphoneConnectionStateListener);
        MicrophoneServiceImpl microphoneServiceImpl = this.mImpl;
        if (microphoneServiceImpl != null) {
            microphoneServiceImpl.registerMicrophoneConnectionStateListener(microphoneConnectionStateListener);
        }
    }

    public void unregisterMicrophoneConnectionStateListener(MicrophoneConnectionStateListener microphoneConnectionStateListener) {
        Log.d(TAG, "unregisterMicrophoneConnectionStateListener, listener: " + microphoneConnectionStateListener);
        MicrophoneServiceImpl microphoneServiceImpl = this.mImpl;
        if (microphoneServiceImpl != null) {
            microphoneServiceImpl.unregisterMicrophoneConnectionStateListener(microphoneConnectionStateListener);
        }
    }

    public void setBufferCallback(BufferCallback bufferCallback) {
        Log.d(TAG, "setBufferCallback, callback: " + bufferCallback);
        MicrophoneServiceImpl microphoneServiceImpl = this.mImpl;
        if (microphoneServiceImpl != null) {
            microphoneServiceImpl.setBufferCallback(bufferCallback);
        }
    }

    public int sendBuffer(byte[] bArr) {
        Log.d(TAG, "sendBuffer, buffer length: " + bArr.length);
        MicrophoneServiceImpl microphoneServiceImpl = this.mImpl;
        if (microphoneServiceImpl != null) {
            return microphoneServiceImpl.sendBuffer(bArr);
        }
        return -1;
    }

    public void registerErrorListener(ErrorListener errorListener) {
        Log.d(TAG, "registerErrorListener, listener: " + errorListener);
        MicrophoneServiceImpl microphoneServiceImpl = this.mImpl;
        if (microphoneServiceImpl != null) {
            microphoneServiceImpl.registerErrorListener(errorListener);
        }
    }

    public void unregisterErrorListener(ErrorListener errorListener) {
        Log.d(TAG, "unregisterErrorListener, listener: " + errorListener);
        MicrophoneServiceImpl microphoneServiceImpl = this.mImpl;
        if (microphoneServiceImpl != null) {
            microphoneServiceImpl.unregisterErrorListener(errorListener);
        }
    }

    public boolean increaseMicVolume() {
        Log.d(TAG, "increaseMicVolume");
        MicrophoneServiceImpl microphoneServiceImpl = this.mImpl;
        if (microphoneServiceImpl != null) {
            return microphoneServiceImpl.increaseMicVolume();
        }
        return false;
    }

    public boolean decreaseMicVolume() {
        Log.d(TAG, "decreaseMicVolume");
        MicrophoneServiceImpl microphoneServiceImpl = this.mImpl;
        if (microphoneServiceImpl != null) {
            return microphoneServiceImpl.decreaseMicVolume();
        }
        return false;
    }

    public boolean setMicVolume(int i) {
        Log.d(TAG, "setMicVolume, volume: " + i);
        MicrophoneServiceImpl microphoneServiceImpl = this.mImpl;
        if (microphoneServiceImpl != null) {
            return microphoneServiceImpl.setMicVolume(i);
        }
        return false;
    }

    public int getMicVolume() {
        Log.d(TAG, "getMicVolume");
        MicrophoneServiceImpl microphoneServiceImpl = this.mImpl;
        if (microphoneServiceImpl != null) {
            return microphoneServiceImpl.getMicVolume();
        }
        return -1;
    }

    public boolean getMicConnectionState() {
        Log.d(TAG, "getMicConnectionState");
        MicrophoneServiceImpl microphoneServiceImpl = this.mImpl;
        if (microphoneServiceImpl != null) {
            return microphoneServiceImpl.getMicConnectionState();
        }
        return false;
    }
}
