package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.b;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(29)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class i extends j {
    @Override // androidx.core.graphics.j
    @Nullable
    public Typeface b(Context context, FontResourcesParserCompat.b bVar, Resources resources, int i) {
        FontResourcesParserCompat.c[] cVarArrA = bVar.a();
        int length = cVarArrA.length;
        FontFamily.Builder builder = null;
        int i2 = 0;
        while (true) {
            int i3 = 1;
            if (i2 >= length) {
                break;
            }
            FontResourcesParserCompat.c cVar = cVarArrA[i2];
            try {
                Font.Builder weight = new Font.Builder(resources, cVar.b()).setWeight(cVar.e());
                if (!cVar.f()) {
                    i3 = 0;
                }
                Font fontBuild = weight.setSlant(i3).setTtcIndex(cVar.c()).setFontVariationSettings(cVar.d()).build();
                if (builder == null) {
                    builder = new FontFamily.Builder(fontBuild);
                } else {
                    builder.addFont(fontBuild);
                }
            } catch (IOException unused) {
            }
            i2++;
        }
        if (builder == null) {
            return null;
        }
        return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i & 1) != 0 ? 700 : 400, (i & 2) != 0 ? 1 : 0)).build();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001c A[Catch: IOException -> 0x0060, PHI: r3
  0x001c: PHI (r3v5 android.graphics.fonts.FontFamily$Builder) = (r3v3 android.graphics.fonts.FontFamily$Builder), (r3v1 android.graphics.fonts.FontFamily$Builder) binds: [B:19:0x0051, B:8:0x001a] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #2 {IOException -> 0x0060, blocks: (B:6:0x000e, B:9:0x001c, B:27:0x005f, B:26:0x005c, B:11:0x0020, B:15:0x0035, B:17:0x0047, B:18:0x004e, B:23:0x0057), top: B:45:0x000e, inners: #0, #1 }] */
    @Override // androidx.core.graphics.j
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Typeface c(android.content.Context r10, @androidx.annotation.Nullable android.os.CancellationSignal r11, @androidx.annotation.NonNull androidx.core.provider.b.f[] r12, int r13) {
        /*
            r9 = this;
            android.content.ContentResolver r9 = r10.getContentResolver()
            int r10 = r12.length
            r0 = 0
            r1 = 0
            r3 = r0
            r2 = r1
        L9:
            r4 = 1
            if (r2 >= r10) goto L63
            r5 = r12[r2]
            android.net.Uri r6 = r5.c()     // Catch: java.io.IOException -> L60
            java.lang.String r7 = "r"
            android.os.ParcelFileDescriptor r6 = r9.openFileDescriptor(r6, r7, r11)     // Catch: java.io.IOException -> L60
            if (r6 != 0) goto L20
            if (r6 == 0) goto L60
        L1c:
            r6.close()     // Catch: java.io.IOException -> L60
            goto L60
        L20:
            android.graphics.fonts.Font$Builder r7 = new android.graphics.fonts.Font$Builder     // Catch: java.lang.Throwable -> L54
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L54
            int r8 = r5.d()     // Catch: java.lang.Throwable -> L54
            android.graphics.fonts.Font$Builder r7 = r7.setWeight(r8)     // Catch: java.lang.Throwable -> L54
            boolean r8 = r5.e()     // Catch: java.lang.Throwable -> L54
            if (r8 == 0) goto L34
            goto L35
        L34:
            r4 = r1
        L35:
            android.graphics.fonts.Font$Builder r4 = r7.setSlant(r4)     // Catch: java.lang.Throwable -> L54
            int r5 = r5.b()     // Catch: java.lang.Throwable -> L54
            android.graphics.fonts.Font$Builder r4 = r4.setTtcIndex(r5)     // Catch: java.lang.Throwable -> L54
            android.graphics.fonts.Font r4 = r4.build()     // Catch: java.lang.Throwable -> L54
            if (r3 != 0) goto L4e
            android.graphics.fonts.FontFamily$Builder r5 = new android.graphics.fonts.FontFamily$Builder     // Catch: java.lang.Throwable -> L54
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L54
            r3 = r5
            goto L51
        L4e:
            r3.addFont(r4)     // Catch: java.lang.Throwable -> L54
        L51:
            if (r6 == 0) goto L60
            goto L1c
        L54:
            r4 = move-exception
            if (r6 == 0) goto L5f
            r6.close()     // Catch: java.lang.Throwable -> L5b
            goto L5f
        L5b:
            r5 = move-exception
            r4.addSuppressed(r5)     // Catch: java.io.IOException -> L60
        L5f:
            throw r4     // Catch: java.io.IOException -> L60
        L60:
            int r2 = r2 + 1
            goto L9
        L63:
            if (r3 != 0) goto L66
            return r0
        L66:
            android.graphics.fonts.FontStyle r9 = new android.graphics.fonts.FontStyle
            r10 = r13 & 1
            if (r10 == 0) goto L6f
            r10 = 700(0x2bc, float:9.81E-43)
            goto L71
        L6f:
            r10 = 400(0x190, float:5.6E-43)
        L71:
            r11 = r13 & 2
            if (r11 == 0) goto L76
            r1 = r4
        L76:
            r9.<init>(r10, r1)
            android.graphics.Typeface$CustomFallbackBuilder r10 = new android.graphics.Typeface$CustomFallbackBuilder
            android.graphics.fonts.FontFamily r11 = r3.build()
            r10.<init>(r11)
            android.graphics.Typeface$CustomFallbackBuilder r9 = r10.setStyle(r9)
            android.graphics.Typeface r9 = r9.build()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.i.c(android.content.Context, android.os.CancellationSignal, androidx.core.provider.b$f[], int):android.graphics.Typeface");
    }

    @Override // androidx.core.graphics.j
    protected Typeface d(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // androidx.core.graphics.j
    @Nullable
    public Typeface e(Context context, Resources resources, int i, String str, int i2) {
        try {
            Font fontBuild = new Font.Builder(resources, i).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(fontBuild).build()).setStyle(fontBuild.getStyle()).build();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // androidx.core.graphics.j
    protected b.f h(b.f[] fVarArr, int i) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }
}
