package android.view;

import android.os.IBinder;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.util.SparseIntArray;
import dalvik.system.CloseGuard;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public abstract class InputEventReceiver {
    private static final String TAG = "InputEventReceiver";
    private final CloseGuard mCloseGuard;
    private InputChannel mInputChannel;
    private MessageQueue mMessageQueue;
    private long mReceiverPtr;
    private final SparseIntArray mSeqMap;

    public interface Factory {
        InputEventReceiver createInputEventReceiver(InputChannel inputChannel, Looper looper);
    }

    private static native boolean nativeConsumeBatchedInputEvents(long j, long j2);

    private static native void nativeDispose(long j);

    private static native void nativeFinishInputEvent(long j, int i, boolean z);

    private static native long nativeInit(WeakReference<InputEventReceiver> weakReference, InputChannel inputChannel, MessageQueue messageQueue);

    public void onFocusEvent(boolean z, boolean z2) {
    }

    public InputEventReceiver(InputChannel inputChannel, Looper looper) {
        CloseGuard closeGuard = CloseGuard.get();
        this.mCloseGuard = closeGuard;
        this.mSeqMap = new SparseIntArray();
        if (inputChannel == null) {
            throw new IllegalArgumentException("inputChannel must not be null");
        }
        if (looper == null) {
            throw new IllegalArgumentException("looper must not be null");
        }
        this.mInputChannel = inputChannel;
        this.mMessageQueue = looper.getQueue();
        this.mReceiverPtr = nativeInit(new WeakReference(this), inputChannel, this.mMessageQueue);
        closeGuard.open("dispose");
    }

    protected void finalize() throws Throwable {
        try {
            dispose(true);
        } finally {
            super.finalize();
        }
    }

    public void dispose() {
        dispose(false);
    }

    private void dispose(boolean z) {
        CloseGuard closeGuard = this.mCloseGuard;
        if (closeGuard != null) {
            if (z) {
                closeGuard.warnIfOpen();
            }
            this.mCloseGuard.close();
        }
        long j = this.mReceiverPtr;
        if (j != 0) {
            nativeDispose(j);
            this.mReceiverPtr = 0L;
        }
        InputChannel inputChannel = this.mInputChannel;
        if (inputChannel != null) {
            inputChannel.dispose();
            this.mInputChannel = null;
        }
        this.mMessageQueue = null;
        Reference.reachabilityFence(this);
    }

    public void onInputEvent(InputEvent inputEvent) {
        finishInputEvent(inputEvent, false);
    }

    public void onBatchedInputEventPending(int i) {
        consumeBatchedInputEvents(-1L);
    }

    public final void finishInputEvent(InputEvent inputEvent, boolean z) {
        if (inputEvent == null) {
            throw new IllegalArgumentException("event must not be null");
        }
        if (this.mReceiverPtr == 0) {
            Log.w(TAG, "Attempted to finish an input event but the input event receiver has already been disposed.");
        } else {
            int iIndexOfKey = this.mSeqMap.indexOfKey(inputEvent.getSequenceNumber());
            if (iIndexOfKey < 0) {
                Log.w(TAG, "Attempted to finish an input event that is not in progress.");
            } else {
                int iValueAt = this.mSeqMap.valueAt(iIndexOfKey);
                this.mSeqMap.removeAt(iIndexOfKey);
                nativeFinishInputEvent(this.mReceiverPtr, iValueAt, z);
            }
        }
        inputEvent.recycleIfNeededAfterDispatch();
    }

    public final boolean consumeBatchedInputEvents(long j) {
        long j2 = this.mReceiverPtr;
        if (j2 == 0) {
            Log.w(TAG, "Attempted to consume batched input events but the input event receiver has already been disposed.");
            return false;
        }
        return nativeConsumeBatchedInputEvents(j2, j);
    }

    public IBinder getToken() {
        InputChannel inputChannel = this.mInputChannel;
        if (inputChannel == null) {
            return null;
        }
        return inputChannel.getToken();
    }

    private void dispatchInputEvent(int i, InputEvent inputEvent) {
        this.mSeqMap.put(inputEvent.getSequenceNumber(), i);
        onInputEvent(inputEvent);
    }
}
