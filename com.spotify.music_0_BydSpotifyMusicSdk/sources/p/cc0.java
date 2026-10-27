package p;

import android.content.Context;
import android.util.TypedValue;
import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public final class cc0 {
    public static final int f = (int) Math.round(5.1000000000000005d);
    public final boolean a;
    public final int b;
    public final int c;
    public final int d;
    public final float e;

    public cc0(Context context) {
        TypedValue typedValueN = he1.N(context, R.attr.elevationOverlayEnabled);
        boolean z = (typedValueN == null || typedValueN.type != 18 || typedValueN.data == 0) ? false : true;
        TypedValue typedValueN2 = he1.N(context, R.attr.elevationOverlayColor);
        int i = typedValueN2 != null ? typedValueN2.data : 0;
        TypedValue typedValueN3 = he1.N(context, R.attr.elevationOverlayAccentColor);
        int i2 = typedValueN3 != null ? typedValueN3.data : 0;
        TypedValue typedValueN4 = he1.N(context, R.attr.colorSurface);
        int i3 = typedValueN4 != null ? typedValueN4.data : 0;
        float f2 = context.getResources().getDisplayMetrics().density;
        this.a = z;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = f2;
    }
}
