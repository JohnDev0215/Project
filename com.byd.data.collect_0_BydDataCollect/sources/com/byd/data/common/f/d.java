package com.byd.data.common.f;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: ThreadFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final byte[] f3383a = new byte[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile d f3384b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f3385c = "ThreadFactory";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final ExecutorService f3387e = Executors.newCachedThreadPool();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final ExecutorService f3388f = Executors.newFixedThreadPool(1);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final ScheduledExecutorService f3389g = Executors.newScheduledThreadPool(1);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f3390h = 514;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Handler f3386d = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.byd.data.common.f.b
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return this.f3379b.b(message);
        }
    });

    /* JADX INFO: compiled from: ThreadFactory.java */
    public interface a extends Runnable {
    }

    private d() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(Message message) {
        if (!(message.obj instanceof Runnable) || message.what != 514) {
            return true;
        }
        Bundle data = message.getData();
        String string = data == null ? "ThreadFactory" : data.getString("ThreadFactory");
        d.a.a.f.b.b.a.d("ThreadFactory", string + " is begin execute on main thread");
        ((Runnable) message.obj).run();
        d.a.a.f.b.b.a.d("ThreadFactory", string + " is end on main thread");
        return true;
    }

    static /* synthetic */ void d(String str, String str2, a aVar) {
        d.a.a.f.b.b.a.e(str, str2, " is begin execute");
        aVar.run();
    }

    static /* synthetic */ void e(String str, String str2, a aVar) {
        d.a.a.f.b.b.a.e(str, str2, " is begin execute");
        aVar.run();
    }

    private void f(String str, String str2, a aVar, long j) {
        Message messageObtainMessage = this.f3386d.obtainMessage(514);
        messageObtainMessage.obj = aVar;
        Bundle data = messageObtainMessage.getData();
        if (data == null) {
            data = new Bundle();
        }
        data.putString("ThreadFactory", str + ", functionName =" + str2);
        messageObtainMessage.setData(data);
        this.f3386d.sendMessageDelayed(messageObtainMessage, j);
    }

    public static d getInstance() {
        if (f3384b == null) {
            synchronized (f3383a) {
                if (f3384b == null) {
                    f3384b = new d();
                }
            }
        }
        return f3384b;
    }

    public void a(int i2, final String str, final String str2, final a aVar) {
        if (i2 == 1) {
            this.f3387e.execute(new Runnable() { // from class: com.byd.data.common.f.a
                @Override // java.lang.Runnable
                public final void run() {
                    d.d(str, str2, aVar);
                }
            });
        } else if (i2 == 2) {
            this.f3388f.execute(new Runnable() { // from class: com.byd.data.common.f.c
                @Override // java.lang.Runnable
                public final void run() {
                    d.e(str, str2, aVar);
                }
            });
        } else {
            if (i2 != 8) {
                return;
            }
            f(str, str2, aVar, 0L);
        }
    }
}
