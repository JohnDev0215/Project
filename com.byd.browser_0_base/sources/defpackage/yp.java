package defpackage;

import android.content.Context;
import com.google.android.material.elevation.ElevationOverlayProvider;
import org.chromium.components.browser_ui.styles.R$color;

/* JADX INFO: compiled from: ChromeColors.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yp {
    public static final String a = oj2.m();

    /* JADX WARN: Removed duplicated region for block: B:16:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(android.content.Context r2, boolean r3) {
        /*
            if (r3 == 0) goto L9
            int r3 = org.chromium.components.browser_ui.styles.R$color.toolbar_background_primary_dark
            int r2 = r2.getColor(r3)
            return r2
        L9:
            java.lang.String r3 = defpackage.yp.a
            int r0 = r3.hashCode()
            r1 = 1307376552(0x4decfba8, float:4.9698944E8)
            if (r0 == r1) goto L24
            r1 = 1418153572(0x54874e64, float:4.649086E12)
            if (r0 == r1) goto L1a
            goto L2e
        L1a:
            java.lang.String r0 = "8155f_dynasty_def"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L2e
            r3 = 1
            goto L2f
        L24:
            java.lang.String r0 = "8155f_denza_def"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L2e
            r3 = 0
            goto L2f
        L2e:
            r3 = -1
        L2f:
            if (r3 == 0) goto L38
            int r3 = org.chromium.components.browser_ui.styles.R$color.color_surface_8155f_dynasty_def
            int r2 = r2.getColor(r3)
            return r2
        L38:
            int r3 = org.chromium.components.browser_ui.styles.R$color.color_surface_8155f_denza_def
            int r2 = r2.getColor(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yp.a(android.content.Context, boolean):int");
    }

    public static int b(Context context, boolean z) {
        return z ? context.getColor(R$color.default_bg_color_dark) : ii2.a(context);
    }

    public static int c(Context context, int i) {
        float dimension = context.getResources().getDimension(i);
        ElevationOverlayProvider elevationOverlayProvider = new ElevationOverlayProvider(context);
        return elevationOverlayProvider.b(dimension, elevationOverlayProvider.d);
    }
}
