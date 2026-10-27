package b.b.g.r;

import android.content.Context;
import android.util.SparseArray;
import b.b.g.p.d;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: SettingItemsRecoveryUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t {

    /* JADX INFO: compiled from: SettingItemsRecoveryUtil.java */
    public static class a implements b.b.g.p.j.b {

        /* JADX INFO: renamed from: b.b.g.r.t$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: SettingItemsRecoveryUtil.java */
        public class RunnableC0075a implements Runnable {
            public RunnableC0075a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b.b.m.f.d.d.a(b.b.g.q.f.b.t.class);
                b.b.m.c.a.a("SettingItemsRecoveryUtil", "---resetSettingOptions---onCompleted---do dismiss.");
            }
        }

        @Override // b.b.g.p.j.b
        public void d() {
            b.b.m.c.a.a("SettingItemsRecoveryUtil", "---resetSettingOptions---onCompleted---postOnMain.");
            b.b.a.f.h.a().d(new RunnableC0075a());
        }
    }

    public static void a(Context context, SparseArray<Integer> sparseArray) {
        if (context == null || sparseArray == null || sparseArray.size() <= 0) {
            return;
        }
        b.b.m.c.a.a("SettingItemsRecoveryUtil", "---resetSettingOptions---");
        Context applicationContext = context.getApplicationContext();
        b.b.g.p.d dVarA = d.g.a();
        dVarA.W(applicationContext);
        dVarA.s0(applicationContext, sparseArray, new a());
        b.b.g.q.d.u.b.p(applicationContext, applicationContext.getString(R.string.msg_recovering_setting_items_recovery));
    }
}
