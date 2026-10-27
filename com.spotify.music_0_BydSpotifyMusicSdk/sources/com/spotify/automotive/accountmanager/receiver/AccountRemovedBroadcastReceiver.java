package com.spotify.automotive.accountmanager.receiver;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import com.spotify.automotive.base.session.worker.LogoutWorker;
import com.spotify.base.java.logging.Logger;
import p.ef2;
import p.lq1;
import p.m11;
import p.v03;
import p.wx2;
import p.yn;

/* JADX INFO: loaded from: classes.dex */
public class AccountRemovedBroadcastReceiver extends BroadcastReceiver {
    public ef2 a;
    public yn b;

    public final void a(Context context, Intent intent) {
        if (context == null) {
            throw new NullPointerException("context");
        }
        ComponentCallbacks2 componentCallbacks2 = (Application) context.getApplicationContext();
        if (!(componentCallbacks2 instanceof m11)) {
            throw new RuntimeException(String.format("%s does not implement %s", componentCallbacks2.getClass().getCanonicalName(), m11.class.getCanonicalName()));
        }
        v03.u(this, (m11) componentCallbacks2);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        a(context, intent);
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        if (!"android.accounts.action.ACCOUNT_REMOVED".equals(action)) {
            "android.accounts.LOGIN_ACCOUNTS_CHANGED".equals(action);
            return;
        }
        if ("com.spotify".equals(intent.getStringExtra("accountType"))) {
            Logger.e("Received ACTION_ACCOUNT_REMOVED intent for the Spotify account.", new Object[0]);
            if (this.a.b().length == 0) {
                this.b.getClass();
                v03.j(context, "context");
                wx2.E(context).i(new lq1(LogoutWorker.class).a());
            }
        }
    }
}
