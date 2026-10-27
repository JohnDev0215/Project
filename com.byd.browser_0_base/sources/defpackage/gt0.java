package defpackage;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.Map;

/* JADX INFO: compiled from: GoogleFontService.java */
/* JADX INFO: loaded from: classes.dex */
public final class gt0 {
    public final Activity a;

    /* JADX INFO: compiled from: GoogleFontService.java */
    public static class a {
    }

    public gt0(Activity activity) {
        this.a = activity;
    }

    public static void a(gt0 gt0Var, Map map, int i, a aVar, HandlerThread handlerThread) {
        gt0Var.getClass();
        if (map.size() != i) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new z8(aVar, 7, map));
        handlerThread.quitSafely();
    }
}
