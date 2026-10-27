package Y3;

import a4.AbstractC0925f;
import a4.AbstractC0927h;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.animation.BaseInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;

/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LinearInterpolator f10690a = new LinearInterpolator();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final io.sentry.internal.debugmeta.c f10691b = io.sentry.internal.debugmeta.c.Q("t", "s", "e", "o", "i", "h", "to", "ti");
    public static final io.sentry.internal.debugmeta.c c = io.sentry.internal.debugmeta.c.Q("x", "y");

    public static BaseInterpolator a(PointF pointF, PointF pointF2) {
        pointF.x = AbstractC0925f.b(pointF.x, -1.0f, 1.0f);
        pointF.y = AbstractC0925f.b(pointF.y, -100.0f, 100.0f);
        pointF2.x = AbstractC0925f.b(pointF2.x, -1.0f, 1.0f);
        float fB = AbstractC0925f.b(pointF2.y, -100.0f, 100.0f);
        pointF2.y = fB;
        Matrix matrix = AbstractC0927h.f11132a;
        try {
            return new PathInterpolator(pointF.x, pointF.y, pointF2.x, fB);
        } catch (IllegalArgumentException e10) {
            return "The Path cannot loop back on itself.".equals(e10.getMessage()) ? new PathInterpolator(Math.min(pointF.x, 1.0f), pointF.y, Math.max(pointF2.x, 0.0f), pointF2.y) : new LinearInterpolator();
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x002d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:101:0x020b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static b4.C0995a b(Z3.a r24, O3.a r25, float r26, Y3.D r27, boolean r28, boolean r29) {
        /*
            Method dump skipped, instruction units count: 730
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Y3.o.b(Z3.a, O3.a, float, Y3.D, boolean, boolean):b4.a");
    }
}
