package com.byd.data.collect.server;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/* JADX INFO: compiled from: DataPickOperator.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements com.byd.data.collect.server.k.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile com.byd.data.collect.server.k.a f3231a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3232b = "DataPickOperator";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f3233c = "private2048.cert";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f3234d = 16;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f3235e = 9209;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Handler f3236f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ServerSocket f3237g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Context f3238h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private volatile boolean f3239i;
    private j j;

    private g(Context context) {
        this.f3238h = context;
        HandlerThread handlerThread = new HandlerThread("socket_service");
        handlerThread.start();
        this.f3236f = new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: com.byd.data.collect.server.d
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.f3228b.h(message);
            }
        });
        try {
            ServerSocket serverSocket = new ServerSocket(9209);
            this.f3237g = serverSocket;
            serverSocket.setReuseAddress(true);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private Socket f() {
        ServerSocket serverSocket = this.f3237g;
        Socket socketAccept = null;
        if (serverSocket == null) {
            return null;
        }
        try {
            socketAccept = serverSocket.accept();
            StringBuilder sb = new StringBuilder();
            sb.append("accept is success = ");
            sb.append(socketAccept != null);
            d.a.a.f.b.b.a.d("DataPickOperator", sb.toString());
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return socketAccept;
    }

    public static com.byd.data.collect.server.k.a g(Context context) {
        if (f3231a == null) {
            synchronized (g.class) {
                if (f3231a == null) {
                    f3231a = new g(context);
                }
            }
        }
        return f3231a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h(Message message) {
        Socket socketF = f();
        if (socketF != null) {
            d.a.a.f.b.b.a.d("DataPickOperator", "sock is not null ptr:" + socketF.getPort() + " is connect， bRun =" + this.f3239i);
            j jVar = this.j;
            if (jVar == null) {
                j jVar2 = new j(socketF, this);
                this.j = jVar2;
                jVar2.start();
            } else {
                jVar.t(socketF);
            }
        } else {
            d.a.a.f.b.b.a.d("DataPickOperator", "sock is null");
        }
        try {
            Thread.sleep(500L);
            return true;
        } catch (InterruptedException e2) {
            e2.printStackTrace();
            return true;
        }
    }

    @Override // com.byd.data.collect.server.k.a
    public Context a() {
        return this.f3238h;
    }

    @Override // com.byd.data.collect.server.k.a
    public void b() {
        j jVar = this.j;
        if (jVar != null) {
            jVar.b(false);
        }
        this.f3236f.sendEmptyMessage(16);
    }

    @Override // com.byd.data.collect.server.k.a
    public boolean c() {
        return this.f3239i;
    }

    @Override // com.byd.data.collect.server.k.a
    public void d() {
        if (this.f3239i) {
            this.f3239i = false;
            j jVar = this.j;
            if (jVar != null) {
                jVar.b(true);
            }
        }
    }

    @Override // com.byd.data.collect.server.k.a
    public void e() {
        if (this.f3239i) {
            return;
        }
        this.f3239i = true;
        this.f3236f.sendEmptyMessage(16);
    }
}
