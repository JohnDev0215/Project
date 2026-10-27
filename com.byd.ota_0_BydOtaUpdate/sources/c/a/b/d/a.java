package c.a.b.d;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import java.util.Calendar;

/* JADX INFO: compiled from: AlarmUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2330a = "a";

    public static void a(Context context, PendingIntent pendingIntent) {
        e.c(f2330a, "cancelAlarmTask");
        ((AlarmManager) context.getSystemService("alarm")).cancel(pendingIntent);
    }

    public static void b(Context context, Calendar calendar, PendingIntent pendingIntent) {
        e.c(f2330a, "setAlarmTask->calendar = " + n.d("yyyy-MM-dd HH:mm:ss", calendar.getTimeInMillis()));
        ((AlarmManager) context.getSystemService("alarm")).setExact(0, calendar.getTimeInMillis(), pendingIntent);
    }
}
