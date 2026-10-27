package com.vivid.carcontroller.byd.dx;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class DeviceBroadcastReceiver extends q {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f5115d = "DeviceBroadcastReceiver";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    i3.m f5116e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    l f5117f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    com.vivid.carcontroller.byd.dx.a f5118g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    a f5119h;

    public interface a {
        void a();
    }

    @Override // com.vivid.carcontroller.byd.dx.q, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra("pkgName");
        h3.i.b("DeviceBroadcastReceiver").b("onReceive broadcast " + stringExtra, new Object[0]);
        if (TextUtils.equals("byd.intent.action.MEDIA_MODE", action)) {
            h3.i.b("DeviceBroadcastReceiver").b("onReceive action 1", new Object[0]);
            a aVar = this.f5119h;
            if (aVar != null) {
                aVar.a();
                return;
            }
            return;
        }
        if (TextUtils.equals("byd.intent.action.KILL_SPOTIFY", action)) {
            h3.i.b("DeviceBroadcastReceiver").b("onReceive action 2", new Object[0]);
            this.f5118g.k();
            this.f5116e.H();
            this.f5117f.k(true);
            return;
        }
        if (TextUtils.equals("com.spotify.music_StopPlayButPlayMemoryEnable", action)) {
            h3.i.b("DeviceBroadcastReceiver").b("onReceive action 3", new Object[0]);
            this.f5118g.k();
            this.f5116e.H();
            this.f5117f.d(true);
        }
    }
}
