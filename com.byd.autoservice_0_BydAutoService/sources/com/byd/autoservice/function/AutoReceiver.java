package com.byd.autoservice.function;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import e1.c;
import f1.b;
import i1.a;

/* JADX INFO: loaded from: classes.dex */
public class AutoReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1832a = "com.byd.action.panel.state.change";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f1833b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f1834c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f1835d = null;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        BydLogHelper.logD("AutoReceiver", "onReceive action =" + intent.getAction());
        if (f1832a.equals(intent.getAction())) {
            f1833b = intent.getBooleanExtra("expanded", false);
            BydLogHelper.logD("AutoReceiver", "onReceive expanded =" + f1833b);
            return;
        }
        if ("android.broadcast.voice.request.start".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("who");
            BydLogHelper.logD("AutoReceiver", "onReceive name =" + stringExtra);
            f1834c = true;
            if (TextUtils.isEmpty(stringExtra) || !stringExtra.equals("bydvoice")) {
                return;
            }
            b.S0().e();
            return;
        }
        if ("android.broadcast.voice.request.release".equals(intent.getAction())) {
            f1834c = false;
            return;
        }
        if ("com.byd.action.quit.power".equals(intent.getAction()) || "com.byd.system.ecall.ACTION_SHOW".equals(intent.getAction())) {
            if (this.f1835d == null) {
                this.f1835d = new a(context);
            }
            if (this.f1835d.b()) {
                c.b().c();
                this.f1835d.h(false);
                this.f1835d.i(false);
            }
        }
    }
}
