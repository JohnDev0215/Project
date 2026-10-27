package android.media;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.VolumeShaper;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.util.AndroidRuntimeException;
import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
public class SoundPool extends PlayerBase {
    private static final boolean DEBUG;
    private static final int SAMPLE_LOADED = 1;
    private static final String TAG = "SoundPool";
    private final AudioAttributes mAttributes;
    private final AtomicReference<EventHandler> mEventHandler;
    private boolean mHasAppOpsPlayAudio;
    private long mNativeContext;

    public interface OnLoadCompleteListener {
        void onLoadComplete(SoundPool soundPool, int i, int i2);
    }

    private final native int _load(FileDescriptor fileDescriptor, long j, long j2, int i);

    private final native void _mute(boolean z);

    private final native int _play(int i, float f, float f2, int i2, int i3, float f3);

    private final native void _setVolume(int i, float f, float f2);

    private final native void native_release();

    private final native int native_setup(Object obj, int i, Object obj2, String str);

    public final native void autoPause();

    public final native void autoResume();

    public final native void pause(int i);

    @Override // android.media.PlayerBase
    int playerApplyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation) {
        return -1;
    }

    @Override // android.media.PlayerBase
    VolumeShaper.State playerGetVolumeShaperState(int i) {
        return null;
    }

    @Override // android.media.PlayerBase
    void playerPause() {
    }

    @Override // android.media.PlayerBase
    int playerSetAuxEffectSendLevel(boolean z, float f) {
        return 0;
    }

    @Override // android.media.PlayerBase
    void playerStart() {
    }

    @Override // android.media.PlayerBase
    void playerStop() {
    }

    public final native void resume(int i);

    public final native void setLoop(int i, int i2);

    public final native void setPriority(int i, int i2);

    public final native void setRate(int i, float f);

    public final native void stop(int i);

    public final native boolean unload(int i);

    static {
        System.loadLibrary("soundpool");
        DEBUG = Log.isLoggable(TAG, 3);
    }

    public SoundPool(int i, int i2, int i3) {
        this(i, new AudioAttributes.Builder().setInternalLegacyStreamType(i2).build());
        PlayerBase.deprecateStreamTypeForPlayback(i2, TAG, "SoundPool()");
    }

    private SoundPool(int i, AudioAttributes audioAttributes) {
        super(audioAttributes, 3);
        this.mEventHandler = new AtomicReference<>(null);
        if (native_setup(new WeakReference(this), i, audioAttributes, getCurrentOpPackageName()) != 0) {
            throw new RuntimeException("Native setup failed");
        }
        this.mAttributes = audioAttributes;
        baseRegisterPlayer();
    }

    public final void release() {
        baseRelease();
        native_release();
    }

    protected void finalize() {
        release();
    }

    public int load(String str, int i) {
        int i_load = 0;
        try {
            File file = new File(str);
            ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(file, 268435456);
            if (parcelFileDescriptorOpen == null) {
                return 0;
            }
            i_load = _load(parcelFileDescriptorOpen.getFileDescriptor(), 0L, file.length(), i);
            parcelFileDescriptorOpen.close();
            return i_load;
        } catch (IOException unused) {
            Log.e(TAG, "error loading " + str);
            return i_load;
        }
    }

    public int load(Context context, int i, int i2) {
        AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = context.getResources().openRawResourceFd(i);
        if (assetFileDescriptorOpenRawResourceFd == null) {
            return 0;
        }
        int i_load = _load(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength(), i2);
        try {
            assetFileDescriptorOpenRawResourceFd.close();
            return i_load;
        } catch (IOException unused) {
            return i_load;
        }
    }

    public int load(AssetFileDescriptor assetFileDescriptor, int i) {
        if (assetFileDescriptor == null) {
            return 0;
        }
        long length = assetFileDescriptor.getLength();
        if (length < 0) {
            throw new AndroidRuntimeException("no length for fd");
        }
        return _load(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), length, i);
    }

    public int load(FileDescriptor fileDescriptor, long j, long j2, int i) {
        return _load(fileDescriptor, j, j2, i);
    }

    public final int play(int i, float f, float f2, int i2, int i3, float f3) {
        baseStart();
        return _play(i, f, f2, i2, i3, f3);
    }

    public final void setVolume(int i, float f, float f2) {
        _setVolume(i, f, f2);
    }

    @Override // android.media.PlayerBase
    void playerSetVolume(boolean z, float f, float f2) {
        _mute(z);
    }

    public void setVolume(int i, float f) {
        setVolume(i, f, f);
    }

    public void setOnLoadCompleteListener(OnLoadCompleteListener onLoadCompleteListener) {
        if (onLoadCompleteListener == null) {
            this.mEventHandler.set(null);
            return;
        }
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null) {
            this.mEventHandler.set(new EventHandler(looperMyLooper, onLoadCompleteListener));
            return;
        }
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper != null) {
            this.mEventHandler.set(new EventHandler(mainLooper, onLoadCompleteListener));
        } else {
            this.mEventHandler.set(null);
        }
    }

    private static void postEventFromNative(Object obj, int i, int i2, int i3, Object obj2) {
        EventHandler eventHandler;
        SoundPool soundPool = (SoundPool) ((WeakReference) obj).get();
        if (soundPool == null || (eventHandler = soundPool.mEventHandler.get()) == null) {
            return;
        }
        eventHandler.sendMessage(eventHandler.obtainMessage(i, i2, i3, obj2));
    }

    private final class EventHandler extends Handler {
        private final OnLoadCompleteListener mOnLoadCompleteListener;

        EventHandler(Looper looper, OnLoadCompleteListener onLoadCompleteListener) {
            super(looper);
            this.mOnLoadCompleteListener = onLoadCompleteListener;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                if (SoundPool.DEBUG) {
                    Log.d(SoundPool.TAG, "Sample " + message.arg1 + " loaded");
                }
                this.mOnLoadCompleteListener.onLoadComplete(SoundPool.this, message.arg1, message.arg2);
                return;
            }
            Log.e(SoundPool.TAG, "Unknown message type " + message.what);
        }
    }

    public static class Builder {
        private AudioAttributes mAudioAttributes;
        private int mMaxStreams = 1;

        public Builder setMaxStreams(int i) throws IllegalArgumentException {
            if (i <= 0) {
                throw new IllegalArgumentException("Strictly positive value required for the maximum number of streams");
            }
            this.mMaxStreams = i;
            return this;
        }

        public Builder setAudioAttributes(AudioAttributes audioAttributes) throws IllegalArgumentException {
            if (audioAttributes == null) {
                throw new IllegalArgumentException("Invalid null AudioAttributes");
            }
            this.mAudioAttributes = audioAttributes;
            return this;
        }

        public SoundPool build() {
            if (this.mAudioAttributes == null) {
                this.mAudioAttributes = new AudioAttributes.Builder().setUsage(1).build();
            }
            return new SoundPool(this.mMaxStreams, this.mAudioAttributes);
        }
    }
}
