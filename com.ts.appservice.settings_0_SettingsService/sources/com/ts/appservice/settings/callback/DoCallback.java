package com.ts.appservice.settings.callback;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IInterface;
import android.os.Message;
import com.android.common.speech.LoggingEvents;
import com.ts.appservice.settings.callback.SettingsRemoteCallbackList;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public abstract class DoCallback<E extends IInterface> {
    protected Handler mHandler;
    HandlerThread mHandlerThread;
    protected SettingsRemoteCallbackList<E> mRemoteCallbacks = new SettingsRemoteCallbackList<>();
    private String mTag;

    protected abstract void dequeueMessage(Message message);

    protected abstract String getLogTag();

    protected DoCallback(Context context) {
        this.mTag = LoggingEvents.EXTRA_CALLING_APP_NAME;
        this.mTag = getLogTag();
        if (this.mHandlerThread == null) {
            this.mHandlerThread = new HandlerThread(this.mTag);
        }
        if (!this.mHandlerThread.isAlive()) {
            this.mHandlerThread.start();
        }
        this.mHandler = initCallbackHandler();
        LogUtil.info(this.mTag, LoggingEvents.EXTRA_CALLING_APP_NAME + this.mTag + "() init");
    }

    public void register(final E callback) {
        this.mHandler.post(new Runnable() { // from class: com.ts.appservice.settings.callback.DoCallback.1
            @Override // java.lang.Runnable
            public void run() {
                DoCallback.this.mRemoteCallbacks.register(callback);
            }
        });
    }

    public void unregister(final E callback) {
        this.mHandler.post(new Runnable() { // from class: com.ts.appservice.settings.callback.DoCallback.2
            @Override // java.lang.Runnable
            public void run() {
                DoCallback.this.mRemoteCallbacks.unregister(callback);
            }
        });
    }

    public void kill() {
        this.mHandler.post(new Runnable() { // from class: com.ts.appservice.settings.callback.DoCallback.3
            @Override // java.lang.Runnable
            public void run() {
                DoCallback.this.mRemoteCallbacks.kill();
                DoCallback.this.mRemoteCallbacks = null;
                DoCallback.this.mRemoteCallbacks = new SettingsRemoteCallbackList<>();
            }
        });
    }

    protected void enqueueNullMessage(int id) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendEmptyMessage(id);
        }
    }

    protected void enqueueMessage(Message msg) {
        this.mHandler.sendMessage(msg);
    }

    protected Message getMessage(int what) {
        return this.mHandler.obtainMessage(what);
    }

    private Handler initCallbackHandler() {
        Handler handler = this.mHandler;
        if (handler == null) {
            if (this.mHandlerThread == null) {
                LogUtil.error(this.mTag, "mHandlerThread try reconstruction");
                HandlerThread handlerThread = new HandlerThread(this.mTag);
                this.mHandlerThread = handlerThread;
                handlerThread.start();
            }
            if (this.mHandlerThread == null) {
                LogUtil.error(this.mTag, "mHandlerThread is null !!");
                return null;
            }
            return new Handler(this.mHandlerThread.getLooper()) { // from class: com.ts.appservice.settings.callback.DoCallback.4
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    DoCallback.this.dequeueMessage(msg);
                }
            };
        }
        return handler;
    }

    protected void checkCallbacksValid(int index) {
        SettingsRemoteCallbackList<E> settingsRemoteCallbackList = this.mRemoteCallbacks;
        if (settingsRemoteCallbackList == null) {
            return;
        }
        E cb = settingsRemoteCallbackList.getBroadcastItem(index);
        if (cb == null) {
            LogUtil.error(this.mTag, "check Callbacks is null !!");
        }
    }

    protected boolean isCallbackValid() {
        if (this.mRemoteCallbacks == null) {
            LogUtil.error(this.mTag, "Remote Callbacks is null !!");
            return false;
        }
        return true;
    }

    protected void setRemoteCallbacksListener(SettingsRemoteCallbackList.OnCallbackListListener listener) {
        SettingsRemoteCallbackList<E> settingsRemoteCallbackList = this.mRemoteCallbacks;
        if (settingsRemoteCallbackList != null) {
            settingsRemoteCallbackList.setOnCallbackListListener(listener);
        }
    }
}
