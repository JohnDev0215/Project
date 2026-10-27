package r4;

import android.content.Context;
import android.util.Log;
import com.byd.uereport.R;
import g5.l;

/* JADX INFO: compiled from: Common.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f7799a = false;

    public static int a(int i8) {
        if (!f7799a) {
            return i8;
        }
        switch (i8) {
            case R.layout.dialog_carhealthy_epb /* 2131492949 */:
                break;
            case R.layout.dialog_discharge_status /* 2131492965 */:
                break;
            case R.layout.layout_dialog_discharge_layout /* 2131493007 */:
                break;
            case R.layout.layout_dialog_time_set /* 2131493011 */:
                break;
        }
        return i8;
    }

    public static int b(Context context) {
        return a.a.a(context).b();
    }

    public static boolean c(Context context) {
        try {
            f7799a = "1".equals(l.a("persist.vendor.byd.cfg.huc.steering.wheel.position", "0"));
        } catch (Exception e8) {
            Log.e("isRightRudder", "isRightRudder,error:" + e8.getMessage());
        }
        return f7799a;
    }

    public static void d(Context context) {
        if (b(context) == 1) {
            context.setTheme(R.style.byd_denza);
        } else {
            context.setTheme(R.style.byd_dynasty);
        }
    }
}
