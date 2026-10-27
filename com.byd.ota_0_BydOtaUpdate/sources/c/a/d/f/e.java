package c.a.d.f;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import c.a.b.d.n;
import com.byd.ota.view.OtaUiService;
import java.util.Calendar;

/* JADX INFO: compiled from: AppointmentBroadcastImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2428a = "e";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2429b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.a.a f2430c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c.a.g.c f2431d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c.a.d.a f2432e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c.a.a.b f2433f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.a.g.d f2434g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c.a.b.c.a f2435h = c.a.b.c.a.k();

    public e(Context context, c.a.a.a aVar, c.a.g.c cVar, c.a.d.a aVar2, c.a.a.b bVar, c.a.g.d dVar) {
        this.f2429b = context;
        this.f2430c = aVar;
        this.f2431d = cVar;
        this.f2432e = aVar2;
        this.f2433f = bVar;
        this.f2434g = dVar;
    }

    public void a() {
        String str = f2428a;
        c.a.b.d.e.c(str, "dealAppointmentBroadcast");
        if (!this.f2435h.w() || this.f2435h.b() <= 0) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        long jB = this.f2435h.b();
        c.a.b.d.e.c(str, "curTime = " + n.d("yyyy-MM-dd HH:mm:ss", timeInMillis) + ", appointmentTime = " + n.d("yyyy-MM-dd HH:mm:ss", jB));
        if (timeInMillis < jB) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(this.f2435h.b());
            PendingIntent pendingIntent = OtaUiService.I3;
            if (pendingIntent != null) {
                c.a.b.d.a.a(this.f2429b, pendingIntent);
                OtaUiService.I3 = null;
            }
            PendingIntent broadcast = PendingIntent.getBroadcast(this.f2429b, 0, new Intent("vendor.intent.action.OTA_APPOINTMENT_BROADCAST"), 0);
            OtaUiService.I3 = broadcast;
            c.a.b.d.a.b(this.f2429b, calendar2, broadcast);
            return;
        }
        if (timeInMillis - jB < 120000) {
            this.f2430c.e(3);
            return;
        }
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTimeInMillis(jB);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(calendar.get(1), calendar.get(2), calendar.get(5), calendar3.get(11), calendar3.get(12), 0);
        if (calendar4.getTimeInMillis() < timeInMillis) {
            calendar4.add(5, 1);
        }
        this.f2432e.i(calendar4.getTimeInMillis());
        this.f2431d.c().i(0, n.d("yyyy-MM-dd HH:mm", calendar4.getTimeInMillis()));
    }
}
