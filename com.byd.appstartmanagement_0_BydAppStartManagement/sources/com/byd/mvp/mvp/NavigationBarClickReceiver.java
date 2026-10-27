package com.byd.mvp.mvp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import b.a.d.b.a;

/* JADX INFO: loaded from: classes2.dex */
public class NavigationBarClickReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f646a = "com.byd.action.navi.btn.click";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f647b = "carsettings";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public DialogActivity f648c;

    public NavigationBarClickReceiver(DialogActivity dialogActivity) {
        this.f648c = dialogActivity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null) {
            return;
        }
        if (f646a.equals(intent.getAction())) {
            if (!f647b.equals(intent.getStringExtra("btn_value")) || this.f648c == null) {
                return;
            }
            a.a("NavigationBarClickReceiver", "状态栏设置图标点击，关闭DialogActivity");
            this.f648c.finish();
        }
    }
}
