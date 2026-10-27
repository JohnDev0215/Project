package com.byd.taskmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f2918a;
    private Map<String, Long> e = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ExecutorService f2919b = Executors.newCachedThreadPool();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Map<String, Future> f2920c = new HashMap();
    private HandlerC0082a d = new HandlerC0082a(this);

    /* JADX INFO: renamed from: com.byd.taskmanager.a$a, reason: collision with other inner class name */
    private static class HandlerC0082a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<a> f2921a;

        public HandlerC0082a(a aVar) {
            super(Looper.getMainLooper());
            this.f2921a = new WeakReference<>(aVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            com.byd.taskmanager.b.a aVar = (com.byd.taskmanager.b.a) message.obj;
            Log.e("m_tag", "TimeOutHandler cancel : " + aVar.getTaskName());
            this.f2921a.get().a(aVar);
        }
    }

    private a() {
    }

    private boolean c(String str, Future<?> future) {
        try {
            if (this.e.containsKey(str) && this.e.get(str) != null) {
                if (System.currentTimeMillis() - this.e.get(str).longValue() < 2000) {
                    return true;
                }
                future.cancel(true);
                this.f2920c.remove(str);
                this.e.remove(str);
                return false;
            }
            this.e.put(str, Long.valueOf(System.currentTimeMillis()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static a e() {
        synchronized (a.class) {
            if (f2918a == null) {
                f2918a = new a();
            }
        }
        return f2918a;
    }

    public void a(com.byd.taskmanager.b.a aVar) {
        String taskName = aVar.getTaskName();
        if (this.f2920c.containsKey(taskName)) {
            Future future = this.f2920c.get(taskName);
            if (!future.isCancelled() && !future.isDone()) {
                Log.e("m_tag", "cancel:" + taskName);
                future.cancel(true);
                aVar.cancelTask();
            }
            this.f2920c.remove(taskName);
        }
        this.e.remove(taskName);
    }

    public void b() {
        this.d.removeCallbacksAndMessages(null);
        this.f2919b.shutdownNow();
        this.f2920c.clear();
        this.e.clear();
    }

    public void d(com.byd.taskmanager.b.a<?> aVar) {
        Future future;
        String taskName = aVar.getTaskName();
        if (this.f2920c.containsKey(taskName) && (future = this.f2920c.get(taskName)) != null && !future.isDone() && !future.isCancelled()) {
            Log.e("m_tag", "task exist:" + taskName);
            if (c(taskName, future)) {
                return;
            }
        }
        this.f2920c.put(taskName, this.f2919b.submit(aVar));
        if (aVar.getTimeOut() > 0) {
            Message message = new Message();
            message.what = aVar.hashCode();
            message.obj = aVar;
            this.d.sendMessageDelayed(message, aVar.getTimeOut());
        }
    }

    public int f(String str) {
        if (!this.f2920c.containsKey(str)) {
            return -1;
        }
        Future future = this.f2920c.get(str);
        if (future.isDone()) {
            return 1;
        }
        return future.isCancelled() ? 2 : 0;
    }

    public void g() {
        b();
        this.f2920c = null;
        this.f2919b = null;
        f2918a = null;
    }

    public void h(String str) {
        this.f2920c.remove(str);
        this.e.remove(str);
    }
}
