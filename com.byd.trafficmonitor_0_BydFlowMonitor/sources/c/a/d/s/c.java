package c.a.d.s;

import android.content.Context;
import com.bydux.api.R;

/* JADX INFO: compiled from: Common.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static int a(Context context) {
        return a.a.a.a(context).b();
    }

    public static void b(Context context) {
        if (a(context) == 1) {
            context.setTheme(R.style.byd_denza);
        } else {
            context.setTheme(R.style.byd_dynasty);
        }
    }
}
