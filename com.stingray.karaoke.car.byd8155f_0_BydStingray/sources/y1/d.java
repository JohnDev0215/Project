package y1;

import C1.k;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.os.Trace;
import h6.e;
import h6.n;
import java.util.concurrent.ConcurrentHashMap;
import s3.b0;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m6.b f24116a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b0 f24117b;

    static {
        e.f("TypefaceCompat static init");
        m6.b bVar = new m6.b();
        new ConcurrentHashMap();
        f24116a = bVar;
        f24117b = new b0(16);
        Trace.endSection();
    }

    public static Typeface a(Context context, k[] kVarArr, int i7) {
        e.f("TypefaceCompat.createFromFontInfo");
        try {
            f24116a.getClass();
            Typeface typefaceBuild = null;
            try {
                FontFamily fontFamilyL = m6.b.L(kVarArr, context.getContentResolver());
                if (fontFamilyL != null) {
                    typefaceBuild = new Typeface.CustomFallbackBuilder(fontFamilyL).setStyle(m6.b.r(fontFamilyL, i7).getStyle()).build();
                }
            } catch (Exception e10) {
                n.u("TypefaceCompatApi29Impl", "Font load failed", e10);
            }
            return typefaceBuild;
        } finally {
            Trace.endSection();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Typeface b(android.content.Context r16, x1.InterfaceC2665d r17, android.content.res.Resources r18, int r19, java.lang.String r20, int r21, int r22, x1.AbstractC2663b r23, boolean r24) {
        /*
            Method dump skipped, instruction units count: 630
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: y1.d.b(android.content.Context, x1.d, android.content.res.Resources, int, java.lang.String, int, int, x1.b, boolean):android.graphics.Typeface");
    }

    public static Typeface c(Resources resources, int i7, String str, int i10, int i11) {
        Typeface typefaceBuild;
        f24116a.getClass();
        try {
            Font fontBuild = new Font.Builder(resources, i7).build();
            typefaceBuild = new Typeface.CustomFallbackBuilder(new FontFamily.Builder(fontBuild).build()).setStyle(fontBuild.getStyle()).build();
        } catch (Exception e10) {
            n.u("TypefaceCompatApi29Impl", "Font load failed", e10);
            typefaceBuild = null;
        }
        if (typefaceBuild != null) {
            f24117b.k(d(resources, i7, str, i10, i11), typefaceBuild);
        }
        return typefaceBuild;
    }

    public static String d(Resources resources, int i7, String str, int i10, int i11) {
        return resources.getResourcePackageName(i7) + '-' + str + '-' + i10 + '-' + i7 + '-' + i11;
    }
}
