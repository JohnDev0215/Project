package T4;

import P8.n;
import android.content.Context;
import android.util.TypedValue;
import com.stingray.drivemodedetector.R;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f8645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8646b;
    public final int c;
    public final float d;

    public a(Context context) {
        TypedValue typedValueS = n.S(context, R.attr.elevationOverlayEnabled);
        this.f8645a = (typedValueS == null || typedValueS.type != 18 || typedValueS.data == 0) ? false : true;
        TypedValue typedValueS2 = n.S(context, R.attr.elevationOverlayColor);
        this.f8646b = typedValueS2 != null ? typedValueS2.data : 0;
        TypedValue typedValueS3 = n.S(context, R.attr.colorSurface);
        this.c = typedValueS3 != null ? typedValueS3.data : 0;
        this.d = context.getResources().getDisplayMetrics().density;
    }
}
