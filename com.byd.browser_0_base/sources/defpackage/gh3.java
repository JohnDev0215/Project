package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.os.ParcelFileDescriptor;
import defpackage.rb2;
import java.io.IOException;

/* JADX INFO: compiled from: TypefaceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class gh3 {
    public static final hh3 a = new hh3();
    public static final ua1<String, Typeface> b = new ua1<>(16);

    /* JADX INFO: compiled from: TypefaceCompat.java */
    public static class a extends a8 {
        public final rb2.c w;

        public a(rb2.c cVar) {
            this.w = cVar;
        }

        @Override // defpackage.a8
        public final void Y(int i) {
            rb2.c cVar = this.w;
            if (cVar != null) {
                cVar.c(i);
            }
        }

        @Override // defpackage.a8
        public final void Z(Typeface typeface) {
            rb2.c cVar = this.w;
            if (cVar != null) {
                cVar.d(typeface);
            }
        }
    }

    public static Typeface a(Context context, xo0[] xo0VarArr, int i) {
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor;
        a.getClass();
        ContentResolver contentResolver = context.getContentResolver();
        try {
            FontFamily.Builder builder = null;
            for (xo0 xo0Var : xo0VarArr) {
                try {
                    parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(xo0Var.a, "r", null);
                } catch (IOException unused) {
                }
                if (parcelFileDescriptorOpenFileDescriptor == null) {
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                    }
                } else {
                    try {
                        Font fontBuild = new Font.Builder(parcelFileDescriptorOpenFileDescriptor).setWeight(xo0Var.c).setSlant(xo0Var.d ? 1 : 0).setTtcIndex(xo0Var.b).build();
                        if (builder == null) {
                            builder = new FontFamily.Builder(fontBuild);
                        } else {
                            builder.addFont(fontBuild);
                        }
                    } catch (Throwable th) {
                        try {
                            parcelFileDescriptorOpenFileDescriptor.close();
                        } catch (Throwable unused2) {
                        }
                        throw th;
                    }
                }
                parcelFileDescriptorOpenFileDescriptor.close();
            }
            if (builder == null) {
                return null;
            }
            FontFamily fontFamilyBuild = builder.build();
            return new Typeface.CustomFallbackBuilder(fontFamilyBuild).setStyle(hh3.a(fontFamilyBuild, i).getStyle()).build();
        } catch (Exception unused3) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Typeface b(android.content.Context r14, vo0.a r15, android.content.res.Resources r16, int r17, java.lang.String r18, int r19, int r20, rb2.c r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gh3.b(android.content.Context, vo0$a, android.content.res.Resources, int, java.lang.String, int, int, rb2$c, boolean):android.graphics.Typeface");
    }

    public static Typeface c(Resources resources, int i, String str, int i2, int i3) {
        Typeface typefaceBuild;
        a.getClass();
        try {
            Font fontBuild = new Font.Builder(resources, i).build();
            typefaceBuild = new Typeface.CustomFallbackBuilder(new FontFamily.Builder(fontBuild).build()).setStyle(fontBuild.getStyle()).build();
        } catch (Exception unused) {
            typefaceBuild = null;
        }
        if (typefaceBuild != null) {
            b.c(d(resources, i, str, i2, i3), typefaceBuild);
        }
        return typefaceBuild;
    }

    public static String d(Resources resources, int i, String str, int i2, int i3) {
        return resources.getResourcePackageName(i) + '-' + str + '-' + i2 + '-' + i + '-' + i3;
    }
}
