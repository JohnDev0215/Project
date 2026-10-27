package defpackage;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;

/* JADX INFO: compiled from: SkinCompatThemeUtils.java */
/* JADX INFO: loaded from: classes3.dex */
public final class uo2 {
    public static final int[] a;
    public static final int[] b;
    public static final int[] c;
    public static final int[] d;
    public static final int[] e;
    public static final int[] f;
    public static final int[] g;
    public static final int[] h;
    public static final int[] i;
    public static final int[] j;
    public static final int[] k;
    public static final int[] l;

    static {
        new ThreadLocal();
        a = new int[]{R.attr.state_enabled};
        b = new int[]{R.attr.state_window_focused};
        c = new int[]{R.attr.state_focused};
        d = new int[]{R.attr.state_activated};
        e = new int[]{R.attr.state_accelerated};
        f = new int[]{R.attr.state_hovered};
        g = new int[]{R.attr.state_drag_can_accept};
        h = new int[]{R.attr.state_drag_hovered};
        i = new int[]{R.attr.state_pressed};
        j = new int[]{R.attr.state_checked};
        k = new int[]{R.attr.state_selected};
        l = new int[0];
    }

    public static int a(Context context, int[] iArr) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }
}
