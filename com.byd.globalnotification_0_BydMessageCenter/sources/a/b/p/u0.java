package a.b.p;

import android.content.Context;
import android.content.ContextWrapper;

/* JADX INFO: loaded from: classes.dex */
public class u0 extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f365a = new Object();

    public static Context a(Context context) {
        if (!(context instanceof u0) && !(context.getResources() instanceof w0)) {
            boolean z = context.getResources() instanceof a1;
        }
        return context;
    }
}
