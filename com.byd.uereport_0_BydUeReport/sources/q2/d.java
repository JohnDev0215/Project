package q2;

import android.content.Context;
import com.bydux.api.common.views.window.ToastUtils;

/* JADX INFO: compiled from: ToastUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f7375a = "d";

    public static void a() {
        ToastUtils.cancel();
    }

    public static void b(Context context, int i8) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            c(applicationContext, applicationContext.getResources().getString(i8));
            return;
        }
        x3.a.c(f7375a, "[ToastUtils]----[Error]----context==null:" + i8);
    }

    public static void c(Context context, String str) {
        a();
        if (context == null) {
            return;
        }
        ToastUtils.show(context.getApplicationContext(), str);
    }
}
