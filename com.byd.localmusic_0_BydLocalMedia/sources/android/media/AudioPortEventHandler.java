package android.media;

import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
class AudioPortEventHandler {
    private static final int AUDIOPORT_EVENT_NEW_LISTENER = 4;
    private static final int AUDIOPORT_EVENT_PATCH_LIST_UPDATED = 2;
    private static final int AUDIOPORT_EVENT_PORT_LIST_UPDATED = 1;
    private static final int AUDIOPORT_EVENT_SERVICE_DIED = 3;
    private static final long RESCHEDULE_MESSAGE_DELAY_MS = 100;
    private static final String TAG = "AudioPortEventHandler";
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private long mJniCallback;
    private final Object mLock = new Object();
    private final ArrayList<AudioManager.OnAudioPortUpdateListener> mListeners = new ArrayList<>();

    private native void native_finalize();

    private native void native_setup(Object obj);

    AudioPortEventHandler() {
    }

    void init() {
        synchronized (this.mLock) {
            if (this.mHandler != null) {
                return;
            }
            HandlerThread handlerThread = new HandlerThread(TAG);
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            if (this.mHandlerThread.getLooper() != null) {
                this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: android.media.AudioPortEventHandler.1
                    /* JADX WARN: Removed duplicated region for block: B:39:0x009d A[LOOP:2: B:37:0x0097->B:39:0x009d, LOOP_END] */
                    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
                    @Override // android.os.Handler
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void handleMessage(android.os.Message r10) {
                        /*
                            Method dump skipped, instruction units count: 204
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: android.media.AudioPortEventHandler.AnonymousClass1.handleMessage(android.os.Message):void");
                    }
                };
                native_setup(new WeakReference(this));
            } else {
                this.mHandler = null;
            }
        }
    }

    protected void finalize() {
        native_finalize();
        if (this.mHandlerThread.isAlive()) {
            this.mHandlerThread.quit();
        }
    }

    void registerListener(AudioManager.OnAudioPortUpdateListener onAudioPortUpdateListener) {
        synchronized (this.mLock) {
            this.mListeners.add(onAudioPortUpdateListener);
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            this.mHandler.sendMessage(handler.obtainMessage(4, 0, 0, onAudioPortUpdateListener));
        }
    }

    void unregisterListener(AudioManager.OnAudioPortUpdateListener onAudioPortUpdateListener) {
        synchronized (this.mLock) {
            this.mListeners.remove(onAudioPortUpdateListener);
        }
    }

    Handler handler() {
        return this.mHandler;
    }

    private static void postEventFromNative(Object obj, int i, int i2, int i3, Object obj2) {
        Handler handler;
        AudioPortEventHandler audioPortEventHandler = (AudioPortEventHandler) ((WeakReference) obj).get();
        if (audioPortEventHandler == null || audioPortEventHandler == null || (handler = audioPortEventHandler.handler()) == null) {
            return;
        }
        Message messageObtainMessage = handler.obtainMessage(i, i2, i3, obj2);
        if (i != 4) {
            handler.removeMessages(i);
        }
        handler.sendMessage(messageObtainMessage);
    }
}
