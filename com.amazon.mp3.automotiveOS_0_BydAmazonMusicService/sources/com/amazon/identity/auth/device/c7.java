package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.AccountChangeEvent;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class c7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f262a = "com.amazon.identity.auth.device.c7";
    public static List<MAPAccountManager.MAPAccountChangeObserver> b;
    public static volatile String c;
    public static AtomicBoolean d = new AtomicBoolean(false);

    /* JADX INFO: compiled from: DCP */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AccountChangeEvent f263a;

        public a(AccountChangeEvent accountChangeEvent) {
            this.f263a = accountChangeEvent;
        }

        @Override // java.lang.Runnable
        public void run() {
            yd.a("NotifyMAPAccountChangeObservers", new String[0]);
            Iterator<MAPAccountManager.MAPAccountChangeObserver> it = c7.b.iterator();
            while (it.hasNext()) {
                it.next().onAccountChange(this.f263a);
            }
        }
    }

    public static void a(Context context) {
        if (d.getAndSet(true)) {
            return;
        }
        f8 f8Var = new f8(context, "account_change_observer", 0);
        if (!Boolean.valueOf(f8Var.f313a.getBoolean("initialized", false)).booleanValue()) {
            f8Var.a("last_seen_account", new MAPAccountManager(context).getAccount());
            f8Var.a("initialized", Boolean.TRUE);
        }
        c = f8Var.f313a.getString("last_seen_account", null);
    }

    public static synchronized void a(Context context, String str) {
        a(context);
        if (!TextUtils.equals(c, str)) {
            AccountChangeEvent accountChangeEvent = new AccountChangeEvent(c, str);
            Log.i(ga.a(f262a), "Notifying observers for the account change for app: " + context.getPackageName());
            c = str;
            new f8(context, "account_change_observer", 0).a("last_seen_account", str);
            if (b != null) {
                va.f637a.execute(new a(accountChangeEvent));
            }
        }
    }
}
