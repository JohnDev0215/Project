package org.chromium.base;

import android.os.Handler;
import android.os.Message;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
class SystemMessageHandler extends Handler {
    private static final int DELAYED_SCHEDULED_WORK = 2;
    private static final int SCHEDULED_WORK = 1;
    private static final String TAG = "cr.SysMessageHandler";
    private long mDelayedScheduledTimeTicks = 0;
    private long mMessagePumpDelegateNative;

    private native void nativeDoRunLoopOnce(long j, long j2);

    private SystemMessageHandler(long j) {
        this.mMessagePumpDelegateNative = j;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what == 2) {
            this.mDelayedScheduledTimeTicks = 0L;
        }
        nativeDoRunLoopOnce(this.mMessagePumpDelegateNative, this.mDelayedScheduledTimeTicks);
    }

    private void scheduleWork() {
        sendMessage(obtainAsyncMessage(1));
    }

    private void scheduleDelayedWork(long j, long j2) {
        if (this.mDelayedScheduledTimeTicks != 0) {
            removeMessages(2);
        }
        this.mDelayedScheduledTimeTicks = j;
        sendMessageDelayed(obtainAsyncMessage(2), j2);
    }

    private void removeAllPendingMessages() {
        removeMessages(1);
        removeMessages(2);
    }

    private Message obtainAsyncMessage(int i) {
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        MessageCompat.setAsynchronous(messageObtain, true);
        return messageObtain;
    }

    private static class MessageCompat {
        static final MessageWrapperImpl IMPL = new LollipopMr1MessageWrapperImpl();

        interface MessageWrapperImpl {
            void setAsynchronous(Message message, boolean z);
        }

        private MessageCompat() {
        }

        public static void setAsynchronous(Message message, boolean z) {
            IMPL.setAsynchronous(message, z);
        }

        static class LollipopMr1MessageWrapperImpl implements MessageWrapperImpl {
            LollipopMr1MessageWrapperImpl() {
            }

            @Override // org.chromium.base.SystemMessageHandler.MessageCompat.MessageWrapperImpl
            public void setAsynchronous(Message message, boolean z) {
                message.setAsynchronous(z);
            }
        }

        static class LegacyMessageWrapperImpl implements MessageWrapperImpl {
            private Method mMessageMethodSetAsynchronous;

            LegacyMessageWrapperImpl() {
                try {
                    this.mMessageMethodSetAsynchronous = Class.forName("android.os.Message").getMethod("setAsynchronous", Boolean.TYPE);
                } catch (ClassNotFoundException e) {
                    Log.e(SystemMessageHandler.TAG, "Failed to find android.os.Message class", e);
                } catch (NoSuchMethodException e2) {
                    Log.e(SystemMessageHandler.TAG, "Failed to load Message.setAsynchronous method", e2);
                } catch (RuntimeException e3) {
                    Log.e(SystemMessageHandler.TAG, "Exception while loading Message.setAsynchronous method", e3);
                }
            }

            @Override // org.chromium.base.SystemMessageHandler.MessageCompat.MessageWrapperImpl
            public void setAsynchronous(Message message, boolean z) {
                Method method = this.mMessageMethodSetAsynchronous;
                if (method == null) {
                    return;
                }
                try {
                    method.invoke(message, Boolean.valueOf(z));
                } catch (IllegalAccessException unused) {
                    Log.e(SystemMessageHandler.TAG, "Illegal access to async message creation, disabling.", new Object[0]);
                    this.mMessageMethodSetAsynchronous = null;
                } catch (IllegalArgumentException unused2) {
                    Log.e(SystemMessageHandler.TAG, "Illegal argument for async message creation, disabling.", new Object[0]);
                    this.mMessageMethodSetAsynchronous = null;
                } catch (RuntimeException unused3) {
                    Log.e(SystemMessageHandler.TAG, "Runtime exception during async message creation, disabling.", new Object[0]);
                    this.mMessageMethodSetAsynchronous = null;
                } catch (InvocationTargetException unused4) {
                    Log.e(SystemMessageHandler.TAG, "Invocation exception during async message creation, disabling.", new Object[0]);
                    this.mMessageMethodSetAsynchronous = null;
                }
            }
        }
    }

    private static SystemMessageHandler create(long j) {
        return new SystemMessageHandler(j);
    }
}
