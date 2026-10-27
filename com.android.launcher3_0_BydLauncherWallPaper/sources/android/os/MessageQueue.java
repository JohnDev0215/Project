package android.os;

import android.util.Log;
import android.util.Printer;
import android.util.SparseArray;
import android.util.proto.ProtoOutputStream;
import java.io.FileDescriptor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class MessageQueue {
    private static final boolean DEBUG = false;
    private static final String TAG = "MessageQueue";
    private boolean mBlocked;
    private SparseArray<FileDescriptorRecord> mFileDescriptorRecords;
    Message mMessages;
    private int mNextBarrierToken;
    private IdleHandler[] mPendingIdleHandlers;
    private final boolean mQuitAllowed;
    private boolean mQuitting;
    private final ArrayList<IdleHandler> mIdleHandlers = new ArrayList<>();
    private long mPtr = nativeInit();

    public interface IdleHandler {
        boolean queueIdle();
    }

    public interface OnFileDescriptorEventListener {
        public static final int EVENT_ERROR = 4;
        public static final int EVENT_INPUT = 1;
        public static final int EVENT_OUTPUT = 2;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Events {
        }

        int onFileDescriptorEvents(FileDescriptor fileDescriptor, int i);
    }

    private static native void nativeDestroy(long j);

    private static native long nativeInit();

    private static native boolean nativeIsPolling(long j);

    private native void nativePollOnce(long j, int i);

    private static native void nativeSetFileDescriptorEvents(long j, int i, int i2);

    private static native void nativeWake(long j);

    MessageQueue(boolean z) {
        this.mQuitAllowed = z;
    }

    protected void finalize() throws Throwable {
        try {
            dispose();
        } finally {
            super.finalize();
        }
    }

    private void dispose() {
        long j = this.mPtr;
        if (j != 0) {
            nativeDestroy(j);
            this.mPtr = 0L;
        }
    }

    public boolean isIdle() {
        boolean z;
        synchronized (this) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            Message message = this.mMessages;
            z = message == null || jUptimeMillis < message.when;
        }
        return z;
    }

    public void addIdleHandler(IdleHandler idleHandler) {
        Objects.requireNonNull(idleHandler, "Can't add a null IdleHandler");
        synchronized (this) {
            this.mIdleHandlers.add(idleHandler);
        }
    }

    public void removeIdleHandler(IdleHandler idleHandler) {
        synchronized (this) {
            this.mIdleHandlers.remove(idleHandler);
        }
    }

    public boolean isPolling() {
        boolean zIsPollingLocked;
        synchronized (this) {
            zIsPollingLocked = isPollingLocked();
        }
        return zIsPollingLocked;
    }

    private boolean isPollingLocked() {
        return !this.mQuitting && nativeIsPolling(this.mPtr);
    }

    public void addOnFileDescriptorEventListener(FileDescriptor fileDescriptor, int i, OnFileDescriptorEventListener onFileDescriptorEventListener) {
        if (fileDescriptor == null) {
            throw new IllegalArgumentException("fd must not be null");
        }
        if (onFileDescriptorEventListener == null) {
            throw new IllegalArgumentException("listener must not be null");
        }
        synchronized (this) {
            updateOnFileDescriptorEventListenerLocked(fileDescriptor, i, onFileDescriptorEventListener);
        }
    }

    public void removeOnFileDescriptorEventListener(FileDescriptor fileDescriptor) {
        if (fileDescriptor == null) {
            throw new IllegalArgumentException("fd must not be null");
        }
        synchronized (this) {
            updateOnFileDescriptorEventListenerLocked(fileDescriptor, 0, null);
        }
    }

    private void updateOnFileDescriptorEventListenerLocked(FileDescriptor fileDescriptor, int i, OnFileDescriptorEventListener onFileDescriptorEventListener) {
        int iIndexOfKey;
        int int$ = fileDescriptor.getInt$();
        SparseArray<FileDescriptorRecord> sparseArray = this.mFileDescriptorRecords;
        FileDescriptorRecord fileDescriptorRecordValueAt = null;
        if (sparseArray != null) {
            iIndexOfKey = sparseArray.indexOfKey(int$);
            if (iIndexOfKey >= 0 && (fileDescriptorRecordValueAt = this.mFileDescriptorRecords.valueAt(iIndexOfKey)) != null && fileDescriptorRecordValueAt.mEvents == i) {
                return;
            }
        } else {
            iIndexOfKey = -1;
        }
        if (i == 0) {
            if (fileDescriptorRecordValueAt != null) {
                fileDescriptorRecordValueAt.mEvents = 0;
                this.mFileDescriptorRecords.removeAt(iIndexOfKey);
                nativeSetFileDescriptorEvents(this.mPtr, int$, 0);
                return;
            }
            return;
        }
        int i2 = i | 4;
        if (fileDescriptorRecordValueAt == null) {
            if (this.mFileDescriptorRecords == null) {
                this.mFileDescriptorRecords = new SparseArray<>();
            }
            this.mFileDescriptorRecords.put(int$, new FileDescriptorRecord(fileDescriptor, i2, onFileDescriptorEventListener));
        } else {
            fileDescriptorRecordValueAt.mListener = onFileDescriptorEventListener;
            fileDescriptorRecordValueAt.mEvents = i2;
            fileDescriptorRecordValueAt.mSeq++;
        }
        nativeSetFileDescriptorEvents(this.mPtr, int$, i2);
    }

    private int dispatchEvents(int i, int i2) {
        synchronized (this) {
            FileDescriptorRecord fileDescriptorRecord = this.mFileDescriptorRecords.get(i);
            if (fileDescriptorRecord == null) {
                return 0;
            }
            int i3 = fileDescriptorRecord.mEvents;
            int i4 = i2 & i3;
            if (i4 == 0) {
                return i3;
            }
            OnFileDescriptorEventListener onFileDescriptorEventListener = fileDescriptorRecord.mListener;
            int i5 = fileDescriptorRecord.mSeq;
            int iOnFileDescriptorEvents = onFileDescriptorEventListener.onFileDescriptorEvents(fileDescriptorRecord.mDescriptor, i4);
            if (iOnFileDescriptorEvents != 0) {
                iOnFileDescriptorEvents |= 4;
            }
            if (iOnFileDescriptorEvents != i3) {
                synchronized (this) {
                    int iIndexOfKey = this.mFileDescriptorRecords.indexOfKey(i);
                    if (iIndexOfKey >= 0 && this.mFileDescriptorRecords.valueAt(iIndexOfKey) == fileDescriptorRecord && fileDescriptorRecord.mSeq == i5) {
                        fileDescriptorRecord.mEvents = iOnFileDescriptorEvents;
                        if (iOnFileDescriptorEvents == 0) {
                            this.mFileDescriptorRecords.removeAt(iIndexOfKey);
                        }
                    }
                }
            }
            return iOnFileDescriptorEvents;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x009a, code lost:
    
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x009b, code lost:
    
        if (r5 >= r6) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x009d, code lost:
    
        r7 = r14.mPendingIdleHandlers;
        r8 = r7[r5];
        r7[r5] = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00a3, code lost:
    
        r7 = r8.queueIdle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00a8, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a9, code lost:
    
        android.util.Log.wtf(android.os.MessageQueue.TAG, "IdleHandler threw exception", r7);
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00c1, code lost:
    
        r5 = 0;
        r6 = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    android.os.Message next() {
        /*
            r14 = this;
            long r0 = r14.mPtr
            r2 = 0
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto La
            return r3
        La:
            r2 = -1
            r4 = 0
            r6 = r2
            r5 = r4
        Le:
            if (r5 == 0) goto L13
            android.os.Binder.flushPendingCommands()
        L13:
            r14.nativePollOnce(r0, r5)
            monitor-enter(r14)
            long r7 = android.os.SystemClock.uptimeMillis()     // Catch: java.lang.Throwable -> Lc5
            android.os.Message r5 = r14.mMessages     // Catch: java.lang.Throwable -> Lc5
            if (r5 == 0) goto L34
            android.os.Handler r9 = r5.target     // Catch: java.lang.Throwable -> Lc5
            if (r9 != 0) goto L34
        L23:
            android.os.Message r9 = r5.next     // Catch: java.lang.Throwable -> Lc5
            if (r9 == 0) goto L30
            boolean r10 = r9.isAsynchronous()     // Catch: java.lang.Throwable -> Lc5
            if (r10 == 0) goto L2e
            goto L30
        L2e:
            r5 = r9
            goto L23
        L30:
            r13 = r9
            r9 = r5
            r5 = r13
            goto L35
        L34:
            r9 = r3
        L35:
            if (r5 == 0) goto L5d
            long r10 = r5.when     // Catch: java.lang.Throwable -> Lc5
            int r10 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r10 >= 0) goto L49
            long r9 = r5.when     // Catch: java.lang.Throwable -> Lc5
            long r9 = r9 - r7
            r11 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r9 = java.lang.Math.min(r9, r11)     // Catch: java.lang.Throwable -> Lc5
            int r5 = (int) r9     // Catch: java.lang.Throwable -> Lc5
            goto L5e
        L49:
            r14.mBlocked = r4     // Catch: java.lang.Throwable -> Lc5
            if (r9 == 0) goto L52
            android.os.Message r0 = r5.next     // Catch: java.lang.Throwable -> Lc5
            r9.next = r0     // Catch: java.lang.Throwable -> Lc5
            goto L56
        L52:
            android.os.Message r0 = r5.next     // Catch: java.lang.Throwable -> Lc5
            r14.mMessages = r0     // Catch: java.lang.Throwable -> Lc5
        L56:
            r5.next = r3     // Catch: java.lang.Throwable -> Lc5
            r5.markInUse()     // Catch: java.lang.Throwable -> Lc5
            monitor-exit(r14)     // Catch: java.lang.Throwable -> Lc5
            return r5
        L5d:
            r5 = r2
        L5e:
            boolean r9 = r14.mQuitting     // Catch: java.lang.Throwable -> Lc5
            if (r9 == 0) goto L67
            r14.dispose()     // Catch: java.lang.Throwable -> Lc5
            monitor-exit(r14)     // Catch: java.lang.Throwable -> Lc5
            return r3
        L67:
            if (r6 >= 0) goto L79
            android.os.Message r9 = r14.mMessages     // Catch: java.lang.Throwable -> Lc5
            if (r9 == 0) goto L73
            long r9 = r9.when     // Catch: java.lang.Throwable -> Lc5
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 >= 0) goto L79
        L73:
            java.util.ArrayList<android.os.MessageQueue$IdleHandler> r6 = r14.mIdleHandlers     // Catch: java.lang.Throwable -> Lc5
            int r6 = r6.size()     // Catch: java.lang.Throwable -> Lc5
        L79:
            if (r6 > 0) goto L80
            r7 = 1
            r14.mBlocked = r7     // Catch: java.lang.Throwable -> Lc5
            monitor-exit(r14)     // Catch: java.lang.Throwable -> Lc5
            goto Le
        L80:
            android.os.MessageQueue$IdleHandler[] r5 = r14.mPendingIdleHandlers     // Catch: java.lang.Throwable -> Lc5
            if (r5 != 0) goto L8d
            r5 = 4
            int r5 = java.lang.Math.max(r6, r5)     // Catch: java.lang.Throwable -> Lc5
            android.os.MessageQueue$IdleHandler[] r5 = new android.os.MessageQueue.IdleHandler[r5]     // Catch: java.lang.Throwable -> Lc5
            r14.mPendingIdleHandlers = r5     // Catch: java.lang.Throwable -> Lc5
        L8d:
            java.util.ArrayList<android.os.MessageQueue$IdleHandler> r5 = r14.mIdleHandlers     // Catch: java.lang.Throwable -> Lc5
            android.os.MessageQueue$IdleHandler[] r7 = r14.mPendingIdleHandlers     // Catch: java.lang.Throwable -> Lc5
            java.lang.Object[] r5 = r5.toArray(r7)     // Catch: java.lang.Throwable -> Lc5
            android.os.MessageQueue$IdleHandler[] r5 = (android.os.MessageQueue.IdleHandler[]) r5     // Catch: java.lang.Throwable -> Lc5
            r14.mPendingIdleHandlers = r5     // Catch: java.lang.Throwable -> Lc5
            monitor-exit(r14)     // Catch: java.lang.Throwable -> Lc5
            r5 = r4
        L9b:
            if (r5 >= r6) goto Lc1
            android.os.MessageQueue$IdleHandler[] r7 = r14.mPendingIdleHandlers
            r8 = r7[r5]
            r7[r5] = r3
            boolean r7 = r8.queueIdle()     // Catch: java.lang.Throwable -> La8
            goto Lb1
        La8:
            r7 = move-exception
            java.lang.String r9 = "MessageQueue"
            java.lang.String r10 = "IdleHandler threw exception"
            android.util.Log.wtf(r9, r10, r7)
            r7 = r4
        Lb1:
            if (r7 != 0) goto Lbe
            monitor-enter(r14)
            java.util.ArrayList<android.os.MessageQueue$IdleHandler> r7 = r14.mIdleHandlers     // Catch: java.lang.Throwable -> Lbb
            r7.remove(r8)     // Catch: java.lang.Throwable -> Lbb
            monitor-exit(r14)     // Catch: java.lang.Throwable -> Lbb
            goto Lbe
        Lbb:
            r0 = move-exception
            monitor-exit(r14)     // Catch: java.lang.Throwable -> Lbb
            throw r0
        Lbe:
            int r5 = r5 + 1
            goto L9b
        Lc1:
            r5 = r4
            r6 = r5
            goto Le
        Lc5:
            r0 = move-exception
            monitor-exit(r14)     // Catch: java.lang.Throwable -> Lc5
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.os.MessageQueue.next():android.os.Message");
    }

    void quit(boolean z) {
        if (!this.mQuitAllowed) {
            throw new IllegalStateException("Main thread not allowed to quit.");
        }
        synchronized (this) {
            if (this.mQuitting) {
                return;
            }
            this.mQuitting = true;
            if (z) {
                removeAllFutureMessagesLocked();
            } else {
                removeAllMessagesLocked();
            }
            nativeWake(this.mPtr);
        }
    }

    public int postSyncBarrier() {
        return postSyncBarrier(SystemClock.uptimeMillis());
    }

    private int postSyncBarrier(long j) {
        int i;
        synchronized (this) {
            i = this.mNextBarrierToken;
            this.mNextBarrierToken = i + 1;
            Message messageObtain = Message.obtain();
            messageObtain.markInUse();
            messageObtain.when = j;
            messageObtain.arg1 = i;
            Message message = null;
            Message message2 = this.mMessages;
            if (j != 0) {
                while (message2 != null && message2.when <= j) {
                    Message message3 = message2;
                    message2 = message2.next;
                    message = message3;
                }
            }
            if (message != null) {
                messageObtain.next = message2;
                message.next = messageObtain;
            } else {
                messageObtain.next = message2;
                this.mMessages = messageObtain;
            }
        }
        return i;
    }

    public void removeSyncBarrier(int i) {
        Message message;
        synchronized (this) {
            Message message2 = null;
            Message message3 = this.mMessages;
            while (true) {
                Message message4 = message3;
                message = message2;
                message2 = message4;
                if (message2 == null || (message2.target == null && message2.arg1 == i)) {
                    break;
                } else {
                    message3 = message2.next;
                }
            }
            if (message2 == null) {
                throw new IllegalStateException("The specified message queue synchronization  barrier token has not been posted or has already been removed.");
            }
            boolean z = false;
            if (message != null) {
                message.next = message2.next;
            } else {
                Message message5 = message2.next;
                this.mMessages = message5;
                if (message5 == null || message5.target != null) {
                    z = true;
                }
            }
            message2.recycleUnchecked();
            if (z && !this.mQuitting) {
                nativeWake(this.mPtr);
            }
        }
    }

    boolean enqueueMessage(Message message, long j) {
        boolean z;
        Message message2;
        if (message.target == null) {
            throw new IllegalArgumentException("Message must have a target.");
        }
        synchronized (this) {
            if (message.isInUse()) {
                throw new IllegalStateException(message + " This message is already in use.");
            }
            if (this.mQuitting) {
                IllegalStateException illegalStateException = new IllegalStateException(message.target + " sending message to a Handler on a dead thread");
                Log.w(TAG, illegalStateException.getMessage(), illegalStateException);
                message.recycle();
                return false;
            }
            message.markInUse();
            message.when = j;
            Message message3 = this.mMessages;
            if (message3 == null || j == 0 || j < message3.when) {
                message.next = message3;
                this.mMessages = message;
                z = this.mBlocked;
            } else {
                z = this.mBlocked && message3.target == null && message.isAsynchronous();
                while (true) {
                    message2 = message3.next;
                    if (message2 == null || j < message2.when) {
                        break;
                    }
                    if (z && message2.isAsynchronous()) {
                        z = false;
                    }
                    message3 = message2;
                }
                message.next = message2;
                message3.next = message;
            }
            if (z) {
                nativeWake(this.mPtr);
            }
            return true;
        }
    }

    boolean hasMessages(Handler handler, int i, Object obj) {
        if (handler == null) {
            return false;
        }
        synchronized (this) {
            for (Message message = this.mMessages; message != null; message = message.next) {
                if (message.target == handler && message.what == i && (obj == null || message.obj == obj)) {
                    return true;
                }
            }
            return false;
        }
    }

    boolean hasEqualMessages(Handler handler, int i, Object obj) {
        if (handler == null) {
            return false;
        }
        synchronized (this) {
            for (Message message = this.mMessages; message != null; message = message.next) {
                if (message.target == handler && message.what == i && (obj == null || obj.equals(message.obj))) {
                    return true;
                }
            }
            return false;
        }
    }

    boolean hasMessages(Handler handler, Runnable runnable, Object obj) {
        if (handler == null) {
            return false;
        }
        synchronized (this) {
            for (Message message = this.mMessages; message != null; message = message.next) {
                if (message.target == handler && message.callback == runnable && (obj == null || message.obj == obj)) {
                    return true;
                }
            }
            return false;
        }
    }

    boolean hasMessages(Handler handler) {
        if (handler == null) {
            return false;
        }
        synchronized (this) {
            for (Message message = this.mMessages; message != null; message = message.next) {
                if (message.target == handler) {
                    return true;
                }
            }
            return false;
        }
    }

    void removeMessages(Handler handler, int i, Object obj) {
        if (handler == null) {
            return;
        }
        synchronized (this) {
            Message message = this.mMessages;
            while (message != null && message.target == handler && message.what == i && (obj == null || message.obj == obj)) {
                Message message2 = message.next;
                this.mMessages = message2;
                message.recycleUnchecked();
                message = message2;
            }
            while (message != null) {
                Message message3 = message.next;
                if (message3 != null && message3.target == handler && message3.what == i && (obj == null || message3.obj == obj)) {
                    Message message4 = message3.next;
                    message3.recycleUnchecked();
                    message.next = message4;
                } else {
                    message = message3;
                }
            }
        }
    }

    void removeEqualMessages(Handler handler, int i, Object obj) {
        if (handler == null) {
            return;
        }
        synchronized (this) {
            Message message = this.mMessages;
            while (message != null && message.target == handler && message.what == i && (obj == null || obj.equals(message.obj))) {
                Message message2 = message.next;
                this.mMessages = message2;
                message.recycleUnchecked();
                message = message2;
            }
            while (message != null) {
                Message message3 = message.next;
                if (message3 != null && message3.target == handler && message3.what == i && (obj == null || obj.equals(message3.obj))) {
                    Message message4 = message3.next;
                    message3.recycleUnchecked();
                    message.next = message4;
                } else {
                    message = message3;
                }
            }
        }
    }

    void removeMessages(Handler handler, Runnable runnable, Object obj) {
        if (handler == null || runnable == null) {
            return;
        }
        synchronized (this) {
            Message message = this.mMessages;
            while (message != null && message.target == handler && message.callback == runnable && (obj == null || message.obj == obj)) {
                Message message2 = message.next;
                this.mMessages = message2;
                message.recycleUnchecked();
                message = message2;
            }
            while (message != null) {
                Message message3 = message.next;
                if (message3 != null && message3.target == handler && message3.callback == runnable && (obj == null || message3.obj == obj)) {
                    Message message4 = message3.next;
                    message3.recycleUnchecked();
                    message.next = message4;
                } else {
                    message = message3;
                }
            }
        }
    }

    void removeEqualMessages(Handler handler, Runnable runnable, Object obj) {
        if (handler == null || runnable == null) {
            return;
        }
        synchronized (this) {
            Message message = this.mMessages;
            while (message != null && message.target == handler && message.callback == runnable && (obj == null || obj.equals(message.obj))) {
                Message message2 = message.next;
                this.mMessages = message2;
                message.recycleUnchecked();
                message = message2;
            }
            while (message != null) {
                Message message3 = message.next;
                if (message3 != null && message3.target == handler && message3.callback == runnable && (obj == null || obj.equals(message3.obj))) {
                    Message message4 = message3.next;
                    message3.recycleUnchecked();
                    message.next = message4;
                } else {
                    message = message3;
                }
            }
        }
    }

    void removeCallbacksAndMessages(Handler handler, Object obj) {
        if (handler == null) {
            return;
        }
        synchronized (this) {
            Message message = this.mMessages;
            while (message != null && message.target == handler && (obj == null || message.obj == obj)) {
                Message message2 = message.next;
                this.mMessages = message2;
                message.recycleUnchecked();
                message = message2;
            }
            while (message != null) {
                Message message3 = message.next;
                if (message3 != null && message3.target == handler && (obj == null || message3.obj == obj)) {
                    Message message4 = message3.next;
                    message3.recycleUnchecked();
                    message.next = message4;
                } else {
                    message = message3;
                }
            }
        }
    }

    void removeCallbacksAndEqualMessages(Handler handler, Object obj) {
        if (handler == null) {
            return;
        }
        synchronized (this) {
            Message message = this.mMessages;
            while (message != null && message.target == handler && (obj == null || obj.equals(message.obj))) {
                Message message2 = message.next;
                this.mMessages = message2;
                message.recycleUnchecked();
                message = message2;
            }
            while (message != null) {
                Message message3 = message.next;
                if (message3 != null && message3.target == handler && (obj == null || obj.equals(message3.obj))) {
                    Message message4 = message3.next;
                    message3.recycleUnchecked();
                    message.next = message4;
                } else {
                    message = message3;
                }
            }
        }
    }

    private void removeAllMessagesLocked() {
        Message message = this.mMessages;
        while (message != null) {
            Message message2 = message.next;
            message.recycleUnchecked();
            message = message2;
        }
        this.mMessages = null;
    }

    private void removeAllFutureMessagesLocked() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        Message message = this.mMessages;
        if (message == null) {
            return;
        }
        if (message.when > jUptimeMillis) {
            removeAllMessagesLocked();
            return;
        }
        while (true) {
            Message message2 = message.next;
            if (message2 == null) {
                return;
            }
            if (message2.when > jUptimeMillis) {
                message.next = null;
                while (true) {
                    Message message3 = message2.next;
                    message2.recycleUnchecked();
                    if (message3 == null) {
                        return;
                    } else {
                        message2 = message3;
                    }
                }
            } else {
                message = message2;
            }
        }
    }

    void dump(Printer printer, String str, Handler handler) {
        synchronized (this) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            int i = 0;
            for (Message message = this.mMessages; message != null; message = message.next) {
                if (handler == null || handler == message.target) {
                    printer.println(str + "Message " + i + ": " + message.toString(jUptimeMillis));
                }
                i++;
            }
            printer.println(str + "(Total messages: " + i + ", polling=" + isPollingLocked() + ", quitting=" + this.mQuitting + ")");
        }
    }

    void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        synchronized (this) {
            for (Message message = this.mMessages; message != null; message = message.next) {
                message.dumpDebug(protoOutputStream, 2246267895809L);
            }
            protoOutputStream.write(1133871366146L, isPollingLocked());
            protoOutputStream.write(1133871366147L, this.mQuitting);
        }
        protoOutputStream.end(jStart);
    }

    private static final class FileDescriptorRecord {
        public final FileDescriptor mDescriptor;
        public int mEvents;
        public OnFileDescriptorEventListener mListener;
        public int mSeq;

        public FileDescriptorRecord(FileDescriptor fileDescriptor, int i, OnFileDescriptorEventListener onFileDescriptorEventListener) {
            this.mDescriptor = fileDescriptor;
            this.mEvents = i;
            this.mListener = onFileDescriptorEventListener;
        }
    }
}
