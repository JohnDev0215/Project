package b.c.a.f;

import android.view.View;
import com.byd.globalnotification.notification.NotificationActivity;

/* JADX INFO: loaded from: classes.dex */
public class s implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ NotificationActivity f1748a;

    public s(NotificationActivity notificationActivity) {
        this.f1748a = notificationActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b.c.a.h.b.a("finish by short click");
        this.f1748a.finish();
    }
}
