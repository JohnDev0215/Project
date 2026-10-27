package b.d.a.a.v;

import a.b.k.l;
import android.R;
import android.content.Context;
import android.content.res.TypedArray;

/* JADX INFO: loaded from: classes.dex */
public final class n<S> extends a.l.d.c {
    public static boolean P(Context context) {
        return Q(context, R.attr.windowFullscreen);
    }

    public static boolean Q(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(l.i.j0(context, b.d.a.a.b.materialCalendarStyle, f.class.getCanonicalName()), new int[]{i});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }
}
