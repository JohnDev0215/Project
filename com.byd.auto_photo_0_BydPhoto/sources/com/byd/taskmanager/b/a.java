package com.byd.taskmanager.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a<T> implements Runnable, Handler.Callback {
    private static final int MSG_CANCEL = 4;
    private static final int MSG_COMPLETE = 3;
    private static final int MSG_START = 1;
    private b<T> callback;
    private Handler mHandler;
    protected String name;
    protected boolean submitInUIThread;
    protected int timeOut;

    /* JADX INFO: renamed from: com.byd.taskmanager.b.a$a, reason: collision with other inner class name */
    private static class HandlerC0083a<T> extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<a<T>> f2922a;

        public HandlerC0083a(a<T> aVar, Handler.Callback callback) {
            super(Looper.getMainLooper(), callback);
            this.f2922a = new WeakReference<>(aVar);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            if (this.f2922a.get() == null) {
                Log.d("m_tag", "WeakReference task is null");
            } else {
                super.dispatchMessage(message);
            }
        }
    }

    public a() {
    }

    public a(int i) {
        this(null, i, false);
    }

    public a(b<T> bVar) {
        this(bVar, 0, bVar != null && (bVar instanceof c));
    }

    public a(b<T> bVar, int i) {
        this(bVar, i, bVar != null && (bVar instanceof c));
    }

    public a(b<T> bVar, int i, boolean z) {
        this.callback = bVar;
        this.timeOut = i;
        this.submitInUIThread = z;
    }

    public a(b<T> bVar, boolean z) {
        this(bVar, 0, z);
    }

    public a(boolean z) {
        this.submitInUIThread = z;
    }

    private void checkHandler() {
        if (this.mHandler == null) {
            this.mHandler = new HandlerC0083a(this, this);
        }
    }

    public void cancelTask() {
        b<T> bVar = this.callback;
        if (bVar != null) {
            if (!this.submitInUIThread) {
                bVar.onCanceled(this.name);
            } else {
                checkHandler();
                this.mHandler.sendEmptyMessage(4);
            }
        }
    }

    protected abstract T doTask();

    public boolean equals(Object obj) {
        String str;
        return (!(obj instanceof a) || (str = this.name) == null) ? super.equals(obj) : str.equals(((a) obj).name);
    }

    public b<T> getCallback() {
        return this.callback;
    }

    public String getTaskName() {
        if (this.name == null) {
            this.name = getClass().getName() + String.valueOf(System.nanoTime());
        }
        return this.name;
    }

    public int getTimeOut() {
        return this.timeOut;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        b<T> bVar = this.callback;
        if ((bVar instanceof c) && !((c) bVar).isUIAvailable()) {
            Log.d("m_tag", "BaseTask handleMessage callback un registUI " + this.callback.getClass().getName());
            return true;
        }
        int i = message.what;
        if (i == 1) {
            b<T> bVar2 = this.callback;
            if (bVar2 == null) {
                return true;
            }
            bVar2.onStart();
            return true;
        }
        if (i == 3) {
            b<T> bVar3 = this.callback;
            if (bVar3 == null) {
                return true;
            }
            bVar3.onComplete(this.name, (T) message.obj);
            return true;
        }
        if (i != 4) {
            return false;
        }
        b<T> bVar4 = this.callback;
        if (bVar4 == null) {
            return true;
        }
        bVar4.onCanceled(this.name);
        return true;
    }

    public void release() {
        this.name = null;
        this.callback = null;
    }

    @Override // java.lang.Runnable
    public final void run() {
        b<T> bVar = this.callback;
        if (bVar != null) {
            if (this.submitInUIThread) {
                checkHandler();
                this.mHandler.sendEmptyMessage(1);
            } else {
                bVar.onStart();
            }
        }
        T tDoTask = null;
        try {
            tDoTask = doTask();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("m_tag", "interrupt : " + this.name);
        }
        b<T> bVar2 = this.callback;
        if (bVar2 != null) {
            if (this.submitInUIThread) {
                checkHandler();
                this.mHandler.obtainMessage(3, tDoTask).sendToTarget();
            } else {
                bVar2.onComplete(this.name, tDoTask);
            }
        }
        com.byd.taskmanager.a.e().h(this.name);
    }

    public a<T> setCallback(b<T> bVar) {
        this.callback = bVar;
        if (bVar != null && (bVar instanceof c) && !this.submitInUIThread) {
            this.submitInUIThread = true;
        }
        return this;
    }

    public void setTaskName(String str) {
        this.name = str;
    }

    public a<T> setTimeOut(int i) {
        this.timeOut = i;
        return this;
    }

    public a<T> submitInUIThread() {
        this.submitInUIThread = true;
        return this;
    }
}
