package b.c.a.f;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import com.byd.globalnotification.notification.NotificationActivity;

/* JADX INFO: loaded from: classes.dex */
public class r implements a.n.q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ NotificationActivity f1747a;

    public r(NotificationActivity notificationActivity) {
        this.f1747a = notificationActivity;
    }

    @Override // a.n.q
    public void a(Object obj) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?activity=notification_app_list"));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        if (this.f1747a.getPackageManager().queryIntentServices(intent, 0).isEmpty()) {
            return;
        }
        this.f1747a.startService(intent);
    }
}
