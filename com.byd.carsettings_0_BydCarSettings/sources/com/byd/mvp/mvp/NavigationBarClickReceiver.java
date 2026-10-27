package com.byd.mvp.mvp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import b.b.m.c.a;

/* JADX INFO: loaded from: classes2.dex */
public class NavigationBarClickReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f3700a = "com.byd.action.navi.btn.click";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f3701b = "carsettings";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public DialogActivity f3702c;

    public NavigationBarClickReceiver(DialogActivity dialogActivity) {
        this.f3702c = dialogActivity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null) {
            return;
        }
        if (f3700a.equals(intent.getAction())) {
            if (!f3701b.equals(intent.getStringExtra("btn_value")) || this.f3702c == null) {
                return;
            }
            a.a("NavigationBarClickReceiver", "状态栏设置图标点击，关闭DialogActivity");
            this.f3702c.finish();
        }
    }
}
