package com.byd.data.common.b;

import android.util.Log;
import com.google.gson.n;
import h.j;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;
import org.json.JSONException;

/* JADX INFO: compiled from: BaseObserver.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<T> extends e.a.o.a<b<T>> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f3294c = "BaseObserver";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected com.byd.data.common.c.d.a f3295d;

    public c(com.byd.data.common.c.d.a aVar) {
        this.f3295d = aVar;
    }

    private void e(int i2, String str) {
        switch (i2) {
            case 1004:
                c(str);
                break;
            case 1005:
                c("连接超时");
                break;
            case 1006:
                c("连接错误");
                break;
            case 1007:
                c("网络超时");
                break;
            case 1008:
                c("数据解析失败");
                break;
        }
    }

    @Override // e.a.g
    public void a() {
        Log.i("BaseObserver", "onComplete is run");
    }

    @Override // e.a.o.a
    protected void b() {
        com.byd.data.common.c.d.a aVar = this.f3295d;
        if (aVar != null) {
            aVar.b("loading ....");
        }
    }

    public abstract void c(String str);

    @Override // e.a.g
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void h(b<T> bVar) {
        try {
            com.byd.data.common.c.d.a aVar = this.f3295d;
            if (aVar != null) {
                aVar.m();
            }
            int rebacekResult = bVar.getRebacekResult();
            Log.i("BaseObserver", "onNext is run " + bVar.getRebacekResult());
            if (rebacekResult == 0) {
                j(bVar);
                return;
            }
            com.byd.data.common.c.d.a aVar2 = this.f3295d;
            if (aVar2 != null) {
                aVar2.k(bVar);
            }
            e(1008, bVar.getReason());
        } catch (Exception e2) {
            e2.printStackTrace();
            c(e2.toString());
        }
    }

    @Override // e.a.g
    public void g(Throwable th) {
        com.byd.data.common.c.d.a aVar = this.f3295d;
        if (aVar != null) {
            aVar.m();
        }
        if (th instanceof j) {
            e(1007, "");
            return;
        }
        if ((th instanceof ConnectException) || (th instanceof UnknownHostException)) {
            e(1006, "");
            return;
        }
        if (th instanceof InterruptedIOException) {
            e(1005, "");
            return;
        }
        if ((th instanceof n) || (th instanceof JSONException) || (th instanceof ParseException)) {
            e(1008, "");
            th.printStackTrace();
        } else if (th != null) {
            c(th.toString());
        } else {
            c("未知错误");
        }
    }

    public abstract void j(b<T> bVar);

    public c() {
    }
}
