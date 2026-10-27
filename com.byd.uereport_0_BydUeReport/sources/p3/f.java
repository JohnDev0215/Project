package p3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: ThreadFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f7037a = new byte[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile f f7038b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ExecutorService f7040d = Executors.newCachedThreadPool();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ExecutorService f7041e = Executors.newFixedThreadPool(5);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ScheduledExecutorService f7042f = Executors.newScheduledThreadPool(d.f7036a.intValue());

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f7043g = 514;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f7039c = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: p3.a
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return this.f7029d.b(message);
        }
    });

    /* JADX INFO: compiled from: ThreadFactory.java */
    public interface a extends Runnable {
    }

    public static /* synthetic */ void d(String str, String str2, a aVar) {
        BydLogHelper.logD(str, str2, " is begin execute");
        aVar.run();
    }

    public static /* synthetic */ void e(String str, String str2, a aVar) {
        BydLogHelper.logD(str, str2, " is begin execute");
        aVar.run();
    }

    public static f getInstance() {
        if (f7038b == null) {
            synchronized (f7037a) {
                if (f7038b == null) {
                    f7038b = new f();
                }
            }
        }
        return f7038b;
    }

    public void a(int i8, final String str, final String str2, final a aVar) {
        if (i8 == 1) {
            this.f7040d.execute(new Runnable() { // from class: p3.c
                @Override // java.lang.Runnable
                public final void run() {
                    f.d(str, str2, aVar);
                }
            });
        } else if (i8 == 2) {
            this.f7041e.execute(new Runnable() { // from class: p3.b
                @Override // java.lang.Runnable
                public final void run() {
                    f.e(str, str2, aVar);
                }
            });
        } else {
            if (i8 != 8) {
                return;
            }
            f(str, str2, aVar, 0L);
        }
    }

    public final boolean b(Message message) {
        if (!(message.obj instanceof Runnable) || message.what != 514) {
            return true;
        }
        Bundle data = message.getData();
        String string = data == null ? "ThreadFactory" : data.getString("ThreadFactory");
        BydLogHelper.logD("ThreadFactory", string, " is begin execute on main thread");
        ((Runnable) message.obj).run();
        BydLogHelper.logD("ThreadFactory", string, " is end on main thread");
        return true;
    }

    public final void f(String str, String str2, a aVar, long j8) {
        Message messageObtainMessage = this.f7039c.obtainMessage(514);
        messageObtainMessage.obj = aVar;
        Bundle data = messageObtainMessage.getData();
        if (data == null) {
            data = new Bundle();
        }
        data.putString("ThreadFactory", str + ", functionName =" + str2);
        messageObtainMessage.setData(data);
        this.f7039c.sendMessageDelayed(messageObtainMessage, j8);
    }
}
