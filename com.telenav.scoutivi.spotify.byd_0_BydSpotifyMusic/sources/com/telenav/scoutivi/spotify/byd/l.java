package com.telenav.scoutivi.spotify.byd;

import android.app.Application;
import android.app.Notification;
import android.content.Intent;
import com.vivid.carcontroller.byd.dx.DeviceBroadcastReceiver;
import com.vivid.carcontroller.byd.dx.RestoreStateService;
import com.vivid.spotify.MainActivity;

/* JADX INFO: compiled from: DXModule.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: compiled from: DXModule.java */
    class a implements RestoreStateService.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ i3.m f5028a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Application f5029b;

        a(i3.m mVar, Application application) {
            this.f5028a = mVar;
            this.f5029b = application;
        }

        @Override // com.vivid.carcontroller.byd.dx.RestoreStateService.a
        public boolean a() {
            return ((f4.o) this.f5028a).b0();
        }

        @Override // com.vivid.carcontroller.byd.dx.RestoreStateService.a
        public Notification b() {
            Notification.Builder contentTitle = new Notification.Builder(this.f5029b).setSmallIcon(R.drawable.ic_spotify_logo).setContentTitle("loading...");
            contentTitle.setChannelId("111");
            return contentTitle.build();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(Application application) {
        Intent intent = new Intent(application, (Class<?>) MainActivity.class);
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        application.startActivity(intent);
    }

    DeviceBroadcastReceiver.a c(final Application application) {
        return new DeviceBroadcastReceiver.a() { // from class: com.telenav.scoutivi.spotify.byd.k
            @Override // com.vivid.carcontroller.byd.dx.DeviceBroadcastReceiver.a
            public final void a() {
                l.b(application);
            }
        };
    }

    RestoreStateService.a d(Application application, i3.m mVar) {
        return new a(mVar, application);
    }

    i3.b e(Application application) {
        return new j(application);
    }

    y3.b f(Application application) {
        return new C0183r(application);
    }
}
