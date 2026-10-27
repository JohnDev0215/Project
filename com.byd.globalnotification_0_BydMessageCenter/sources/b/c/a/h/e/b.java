package b.c.a.h.e;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import com.byd.globalnotification.R;
import java.util.Timer;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f1792a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public NotificationManager f1794c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Notification.Builder f1795d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f1793b = "sd_card";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1796e = "title";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f1797f = "content";
    public String g = "sd_card";
    public int h = 0;

    public b(Context context) {
        this.f1792a = context;
    }

    public void a() {
        NotificationChannel notificationChannel = new NotificationChannel(this.g, this.f1792a.getString(R.string.app_name), 4);
        NotificationManager notificationManager = (NotificationManager) this.f1792a.getSystemService(NotificationManager.class);
        this.f1794c = notificationManager;
        notificationManager.createNotificationChannel(notificationChannel);
        if (this.f1794c != null) {
            new Timer().schedule(new a(this), 5000L);
        }
        Notification.Builder builder = new Notification.Builder(this.f1792a, this.g);
        this.f1795d = builder;
        builder.setContentTitle(this.f1796e).setContentTitle(this.f1797f).setSmallIcon(R.drawable.ic_launcher_foreground).build();
        this.f1794c.notify(this.h, this.f1795d.build());
    }
}
