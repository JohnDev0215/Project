package com.qtxa.qtxadefend;

import android.os.RemoteCallbackList;
import com.qtxa.qtcarsecsvc.OnEventListener;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final u f148a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private RemoteCallbackList<OnEventListener> f149b = new RemoteCallbackList<>();

    v(u uVar) {
        this.f148a = uVar;
    }

    private int e(b0 b0Var) {
        return -1;
    }

    private synchronized int f(b0 b0Var) {
        int iA = e.c().a(b0Var.f112a, (String[]) b0Var.f.toArray(new String[0]), 2, b0Var.f113b);
        if (iA != -1) {
            if (iA == 0) {
                return b0Var.e;
            }
            if (iA == 1) {
                return b0Var.d;
            }
            if (iA != 2) {
                return -1;
            }
        }
        return b0Var.c;
    }

    private boolean h(String str) {
        try {
            return new JSONObject(str).getString("result").equalsIgnoreCase("noresult");
        } catch (JSONException e) {
            e.printStackTrace();
            return true;
        }
    }

    synchronized void a(OnEventListener onEventListener) {
        if (onEventListener != null) {
            this.f149b.register(onEventListener);
        }
    }

    void b(String str) {
        if (i(b0.g(str)) == 2) {
            this.f148a.h().c(str);
        }
    }

    public synchronized String c(String str) {
        String str2;
        int iBeginBroadcast = this.f149b.beginBroadcast();
        String strOnEvent = "";
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                strOnEvent = ((OnEventListener) this.f149b.getBroadcastItem(i)).onEvent(str);
                if (!h(strOnEvent)) {
                    break;
                }
            } catch (Exception e) {
                w.f("fireEvent : " + e.toString());
                str2 = "";
            }
        }
        str2 = strOnEvent;
        this.f149b.finishBroadcast();
        return str2;
    }

    public synchronized int d() {
        return this.f149b.getRegisteredCallbackCount();
    }

    synchronized boolean g() {
        return this.f149b.getRegisteredCallbackCount() > 0;
    }

    int i(b0 b0Var) {
        int iF;
        w.d("[System_Action] " + p.f137b[b0Var.c + 1] + " : " + b0Var);
        if (d() == 0) {
            iF = e(b0Var);
            w.d("[default Action] " + p.f137b[iF + 1] + " : " + b0Var);
        } else {
            iF = f(b0Var);
            w.d("[user Action] " + p.f137b[iF + 1] + " : " + b0Var);
        }
        w.d("[System_Action] " + iF);
        return iF;
    }

    synchronized void j(OnEventListener onEventListener) {
        if (onEventListener != null) {
            this.f149b.unregister(onEventListener);
        }
    }

    void k() {
        this.f149b.kill();
    }
}
