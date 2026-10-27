package v4;

import android.content.Context;
import com.byd.trafficmonitor.database.AppDatabase;
import com.byd.trafficmonitor.model.TrafficInfo;
import d1.i;
import java.util.List;

/* JADX INFO: compiled from: TrafficInfoDBHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f8569a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public AppDatabase f8570b;

    public a(Context context) {
        e(context);
    }

    public static a c(Context context) {
        if (f8569a == null) {
            synchronized (a.class) {
                if (f8569a == null) {
                    f8569a = new a(context.getApplicationContext());
                }
            }
        }
        return f8569a;
    }

    public void a(TrafficInfo trafficInfo) {
        this.f8570b.s().e(trafficInfo);
    }

    public void b() {
        this.f8570b.s().a();
    }

    public List<TrafficInfo> d() {
        return this.f8570b.s().d();
    }

    public final void e(Context context) {
        this.f8570b = (AppDatabase) i.a(context, AppDatabase.class, "database_traffic").a().b();
    }

    public void f(int i8, int i9) {
        this.f8570b.s().b(i8, i9);
    }

    public void g(TrafficInfo trafficInfo) {
        this.f8570b.s().c(trafficInfo);
    }
}
