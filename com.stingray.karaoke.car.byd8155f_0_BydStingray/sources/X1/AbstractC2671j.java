package x1;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: x1.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2671j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f23708a = new ThreadLocal();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final WeakHashMap f23709b = new WeakHashMap(0);
    public static final Object c = new Object();

    public static Typeface a(Context context, int i7) {
        if (context.isRestricted()) {
            return null;
        }
        return b(context, i7, new TypedValue(), 0, null, false, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Typeface b(android.content.Context r16, int r17, android.util.TypedValue r18, int r19, x1.AbstractC2663b r20, boolean r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: x1.AbstractC2671j.b(android.content.Context, int, android.util.TypedValue, int, x1.b, boolean, boolean):android.graphics.Typeface");
    }
}
