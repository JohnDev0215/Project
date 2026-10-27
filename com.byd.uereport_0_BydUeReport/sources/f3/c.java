package f3;

import android.content.Context;
import com.byd.carsettings.db.AppDatabase;
import com.byd.carsettings.db.model.EnergyBean;
import d1.i;
import java.util.List;

/* JADX INFO: compiled from: EnergyConDBUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile c f4942a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static AppDatabase f4943b;

    /* JADX INFO: compiled from: EnergyConDBUtils.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Context f4944d;

        public a(Context context) {
            this.f4944d = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppDatabase unused = c.f4943b = (AppDatabase) i.a(this.f4944d, AppDatabase.class, "database_byd").a().b();
        }
    }

    /* JADX INFO: compiled from: EnergyConDBUtils.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            List<EnergyBean> listL;
            List<EnergyBean> listL2;
            int iK = c.i().k(1);
            g5.a.c("[曲线数据]----queryDataSizeByField----" + iK);
            if (iK > 300 && (listL2 = c.i().l(1, iK - 300, false)) != null && listL2.size() > 0) {
                g5.a.c("[曲线数据]----" + listL2.get(listL2.size() - 1).toString());
                c.i().h(1, listL2.get(listL2.size() - 1).o());
            }
            int iK2 = c.i().k(2);
            g5.a.c("[曲线数据]----queryDataSizeByField----" + iK2);
            if (iK2 <= 300 || (listL = c.i().l(2, iK - 300, false)) == null || listL.size() <= 0) {
                return;
            }
            g5.a.c("[曲线数据]----" + listL.get(listL.size() - 1).toString());
            c.i().h(2, listL.get(listL.size() - 1).o());
        }
    }

    /* JADX INFO: renamed from: f3.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: EnergyConDBUtils.java */
    public class RunnableC0041c implements Runnable {
        public RunnableC0041c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.f4943b.s().a();
        }
    }

    /* JADX INFO: compiled from: EnergyConDBUtils.java */
    public class d implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ EnergyBean f4948d;

        public d(EnergyBean energyBean) {
            this.f4948d = energyBean;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.f4943b.s().c(this.f4948d);
        }
    }

    public static void e(EnergyBean energyBean) {
        if (f4943b == null) {
            return;
        }
        new Thread(new d(energyBean)).start();
    }

    public static c i() {
        if (f4942a == null) {
            synchronized (c.class) {
                if (f4942a == null) {
                    f4942a = new c();
                }
            }
        }
        return f4942a;
    }

    public void d(EnergyBean energyBean) {
        g5.a.c("[曲线数据]----add----" + energyBean.toString());
        e(energyBean);
    }

    public void f() {
        if (f4943b == null) {
            return;
        }
        new Thread(new RunnableC0041c()).start();
    }

    public void g() {
        new Thread(new b()).start();
    }

    public final void h(int i8, long j8) {
        AppDatabase appDatabase = f4943b;
        if (appDatabase == null) {
            return;
        }
        appDatabase.s().d(i8, j8);
    }

    public void j(Context context) {
        new Thread(new a(context)).start();
    }

    public int k(int i8) {
        AppDatabase appDatabase = f4943b;
        if (appDatabase == null) {
            return 0;
        }
        return appDatabase.s().f(i8);
    }

    public List<EnergyBean> l(int i8, int i9, boolean z7) {
        AppDatabase appDatabase = f4943b;
        if (appDatabase == null) {
            return null;
        }
        return z7 ? appDatabase.s().b(i8, i9) : appDatabase.s().e(i8, i9);
    }
}
