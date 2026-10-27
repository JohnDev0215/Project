package com.android.car.media.netObserver;

import android.car.utils.Logs;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.ObservableBoolean;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import spi.c;
import spi.u7;
import spi.z7;

/* JADX INFO: loaded from: classes2.dex */
public class NetStateChangeReceiver extends BroadcastReceiver {
    public static final ObservableBoolean c = new ObservableBoolean(z7.a());
    public static final ObservableBoolean d = new ObservableBoolean(false);
    public List<u7> a = new ArrayList();
    public List<Context> b = new ArrayList();

    public static class a {
        public static final NetStateChangeReceiver a = new NetStateChangeReceiver();

        private a() {
        }
    }

    public static void a(NetStateChangeReceiver netStateChangeReceiver, u7 u7Var, boolean z) {
        Objects.requireNonNull(netStateChangeReceiver);
        Logs.e("NetStateChangeReceiver", "dealObserver: isConnected=" + z);
        if (z) {
            u7Var.b();
        } else {
            u7Var.a();
        }
    }

    public static void b(NetStateChangeReceiver netStateChangeReceiver, u7 u7Var, boolean z) {
        Objects.requireNonNull(netStateChangeReceiver);
        Logs.e("NetStateChangeReceiver", "dealObserver: isConnected=" + z);
        u7Var.c(z);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList, java.util.List<spi.u7>] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.ArrayList, java.util.List<spi.u7>] */
    public static void c(u7 u7Var) {
        Logs.e("NetStateChangeReceiver", "registerObserver start: ");
        if (u7Var == null) {
            return;
        }
        NetStateChangeReceiver netStateChangeReceiver = a.a;
        if (netStateChangeReceiver.a.contains(u7Var)) {
            return;
        }
        netStateChangeReceiver.a.add(u7Var);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList, java.util.List<spi.u7>] */
    public static void d(u7 u7Var) {
        ?? r0;
        Logs.e("NetStateChangeReceiver", "unRegisterObserver: ");
        if (u7Var == null || (r0 = a.a.a) == 0) {
            return;
        }
        r0.remove(u7Var);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        StringBuilder sbK = c.k("onReceive: ");
        sbK.append(intent.getAction());
        Logs.e("NetStateChangeReceiver", sbK.toString());
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            synchronized (NetStateChangeReceiver.class) {
                c.set(z7.a());
            }
        }
    }
}
