package android.media.audiopolicy;

import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.android.internal.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class AudioVolumeGroupChangeHandler {
    private static final int AUDIOVOLUMEGROUP_EVENT_NEW_LISTENER = 4;
    private static final int AUDIOVOLUMEGROUP_EVENT_VOLUME_CHANGED = 1000;
    private static final String TAG = "AudioVolumeGroupChangeHandler";
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private long mJniCallback;
    private final ArrayList<AudioManager.VolumeGroupCallback> mListeners = new ArrayList<>();

    private native void native_finalize();

    private native void native_setup(Object obj);

    public void init() {
        synchronized (this) {
            if (this.mHandler != null) {
                return;
            }
            HandlerThread handlerThread = new HandlerThread(TAG);
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            if (this.mHandlerThread.getLooper() == null) {
                this.mHandler = null;
            } else {
                this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: android.media.audiopolicy.AudioVolumeGroupChangeHandler.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        ArrayList arrayList;
                        synchronized (this) {
                            if (message.what != 4) {
                                arrayList = (ArrayList) AudioVolumeGroupChangeHandler.this.mListeners.clone();
                            } else {
                                arrayList = new ArrayList();
                                if (AudioVolumeGroupChangeHandler.this.mListeners.contains(message.obj)) {
                                    arrayList.add((AudioManager.VolumeGroupCallback) message.obj);
                                }
                            }
                        }
                        if (!arrayList.isEmpty() && message.what == 1000) {
                            for (int i = 0; i < arrayList.size(); i++) {
                                ((AudioManager.VolumeGroupCallback) arrayList.get(i)).onAudioVolumeGroupChanged(message.arg1, message.arg2);
                            }
                        }
                    }
                };
                native_setup(new WeakReference(this));
            }
        }
    }

    protected void finalize() {
        native_finalize();
        if (this.mHandlerThread.isAlive()) {
            this.mHandlerThread.quit();
        }
    }

    public void registerListener(AudioManager.VolumeGroupCallback volumeGroupCallback) {
        Preconditions.checkNotNull(volumeGroupCallback, "volume group callback shall not be null");
        synchronized (this) {
            this.mListeners.add(volumeGroupCallback);
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            this.mHandler.sendMessage(handler.obtainMessage(4, 0, 0, volumeGroupCallback));
        }
    }

    public void unregisterListener(AudioManager.VolumeGroupCallback volumeGroupCallback) {
        Preconditions.checkNotNull(volumeGroupCallback, "volume group callback shall not be null");
        synchronized (this) {
            this.mListeners.remove(volumeGroupCallback);
        }
    }

    Handler handler() {
        return this.mHandler;
    }

    private static void postEventFromNative(Object obj, int i, int i2, int i3, Object obj2) {
        Handler handler;
        AudioVolumeGroupChangeHandler audioVolumeGroupChangeHandler = (AudioVolumeGroupChangeHandler) ((WeakReference) obj).get();
        if (audioVolumeGroupChangeHandler == null || audioVolumeGroupChangeHandler == null || (handler = audioVolumeGroupChangeHandler.handler()) == null) {
            return;
        }
        handler.sendMessage(handler.obtainMessage(i, i2, i3, obj2));
    }
}
