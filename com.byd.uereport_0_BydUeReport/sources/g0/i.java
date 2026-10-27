package g0;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import f0.c;
import java.io.IOException;
import java.io.InputStream;
import l0.b;

/* JADX INFO: compiled from: TypefaceCompatApi29Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends j {
    @Override // g0.j
    public Typeface b(Context context, c.b bVar, Resources resources, int i8) {
        c.C0036c[] c0036cArrA = bVar.a();
        int length = c0036cArrA.length;
        FontFamily.Builder builder = null;
        int i9 = 0;
        while (true) {
            int i10 = 1;
            if (i9 >= length) {
                break;
            }
            c.C0036c c0036c = c0036cArrA[i9];
            try {
                Font.Builder weight = new Font.Builder(resources, c0036c.b()).setWeight(c0036c.e());
                if (!c0036c.f()) {
                    i10 = 0;
                }
                Font fontBuild = weight.setSlant(i10).setTtcIndex(c0036c.c()).setFontVariationSettings(c0036c.d()).build();
                if (builder == null) {
                    builder = new FontFamily.Builder(fontBuild);
                } else {
                    builder.addFont(fontBuild);
                }
            } catch (IOException unused) {
            }
            i9++;
        }
        if (builder == null) {
            return null;
        }
        return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i8 & 1) != 0 ? 700 : 400, (i8 & 2) != 0 ? 1 : 0)).build();
    }

    @Override // g0.j
    public Typeface c(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i8) {
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor;
        ContentResolver contentResolver = context.getContentResolver();
        int length = fVarArr.length;
        FontFamily.Builder builder = null;
        int i9 = 0;
        while (true) {
            int i10 = 1;
            if (i9 >= length) {
                if (builder == null) {
                    return null;
                }
                return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i8 & 1) != 0 ? 700 : 400, (i8 & 2) != 0 ? 1 : 0)).build();
            }
            b.f fVar = fVarArr[i9];
            try {
                parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(fVar.c(), "r", cancellationSignal);
            } catch (IOException unused) {
                continue;
            }
            if (parcelFileDescriptorOpenFileDescriptor == null) {
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                }
                i9++;
            } else {
                try {
                    Font.Builder weight = new Font.Builder(parcelFileDescriptorOpenFileDescriptor).setWeight(fVar.d());
                    if (!fVar.e()) {
                        i10 = 0;
                    }
                    Font fontBuild = weight.setSlant(i10).setTtcIndex(fVar.b()).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(fontBuild);
                    } else {
                        builder.addFont(fontBuild);
                    }
                } catch (Throwable th) {
                    try {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            parcelFileDescriptorOpenFileDescriptor.close();
            i9++;
        }
    }

    @Override // g0.j
    public Typeface d(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // g0.j
    public Typeface e(Context context, Resources resources, int i8, String str, int i9) {
        try {
            Font fontBuild = new Font.Builder(resources, i8).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(fontBuild).build()).setStyle(fontBuild.getStyle()).build();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // g0.j
    public b.f h(b.f[] fVarArr, int i8) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }
}
