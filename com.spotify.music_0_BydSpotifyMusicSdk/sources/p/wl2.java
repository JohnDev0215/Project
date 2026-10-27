package p;

import android.content.Context;
import android.content.ContextWrapper;

/* JADX INFO: loaded from: classes.dex */
public abstract class wl2 extends ContextWrapper {
    public static final Object a = new Object();

    public static void a(Context context) {
        if (context.getResources() instanceof yl2) {
            return;
        }
        context.getResources();
        int i = ws2.a;
    }
}
