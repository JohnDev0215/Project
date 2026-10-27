package c.a.d;

import android.content.Context;
import android.util.Log;
import com.byd.smarttravel.PlanBean;
import java.util.TimeZone;

/* JADX INFO: compiled from: ServerUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class h {
    public static PlanBean a(Context context) {
        PlanBean planBean = new PlanBean();
        try {
            planBean.f1964c = i.b(context, "KEY_MODE");
            planBean.f1965d = i.b(context, "KEY_CHARGE_SWITCH");
            planBean.e = i.b(context, "KEY_USECAR_SWITCH");
            String id = TimeZone.getDefault().getID();
            planBean.p = id;
            i.i(context, "KEY_TIME_ZONE", id);
            planBean.f = i.d(context, "KEY_CHARGE_START_TIME");
            planBean.g = i.d(context, "KEY_CHARGE_END_TIME");
            planBean.h = i.b(context, "KEY_CHARGE_FULL");
            planBean.i = i.b(context, "KEY_CHARGE_NEXTDAY");
            planBean.j = i.f(context, "KEY_CHARGE_CYCLE");
            planBean.k = i.d(context, "KEY_USECAR_START_TIME");
            planBean.l = i.f(context, "KEY_USECAR_CYCLE");
            planBean.m = i.d(context, "KEY_PRICE_START_TIME");
            planBean.n = i.d(context, "KEY_PRICE_END_TIME");
            planBean.o = i.b(context, "KEY_PRICE_FULL");
            planBean.q = i.b(context, "gun_state_tips");
            planBean.r = i.b(context, "acc_off_tips");
        } catch (Exception e) {
            Log.e("smart_charge", e.getMessage());
        }
        return planBean;
    }

    public static void b(Context context, PlanBean planBean) throws Throwable {
        if (planBean == null) {
            return;
        }
        try {
            k.b("[应用服务]----" + planBean.toString());
            i.g(context, "KEY_MODE", planBean.f1964c);
            i.g(context, "KEY_CHARGE_SWITCH", planBean.f1965d);
            i.g(context, "KEY_USECAR_SWITCH", planBean.e);
            i.i(context, "KEY_TIME_ZONE", planBean.p);
            i.h(context, "KEY_CHARGE_START_TIME", planBean.f);
            i.h(context, "KEY_CHARGE_END_TIME", planBean.g);
            i.g(context, "KEY_CHARGE_FULL", planBean.h);
            i.g(context, "KEY_CHARGE_NEXTDAY", planBean.i);
            i.i(context, "KEY_CHARGE_CYCLE", planBean.j);
            i.h(context, "KEY_USECAR_START_TIME", planBean.k);
            i.i(context, "KEY_USECAR_CYCLE", planBean.l);
            i.h(context, "KEY_PRICE_START_TIME", planBean.m);
            i.h(context, "KEY_PRICE_END_TIME", planBean.n);
            i.g(context, "KEY_PRICE_FULL", planBean.o);
            i.g(context, "gun_state_tips", planBean.q);
            i.g(context, "acc_off_tips", planBean.r);
        } catch (Exception e) {
            Log.e("smart_charge", e.getMessage());
        }
    }
}
