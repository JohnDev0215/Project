package a.h.e;

import a.h.h.h;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class d extends e {
    @Override // a.h.e.e
    public Typeface a(Context context, a.h.d.b.b bVar, Resources resources, int i) {
        try {
            a.h.d.b.c[] cVarArr = bVar.f704a;
            int length = cVarArr.length;
            FontFamily.Builder builder = null;
            int i2 = 0;
            while (true) {
                int i3 = 1;
                if (i2 >= length) {
                    break;
                }
                a.h.d.b.c cVar = cVarArr[i2];
                try {
                    Font.Builder weight = new Font.Builder(resources, cVar.f710f).setWeight(cVar.f706b);
                    if (!cVar.f707c) {
                        i3 = 0;
                    }
                    Font fontBuild = weight.setSlant(i3).setTtcIndex(cVar.f709e).setFontVariationSettings(cVar.f708d).build();
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
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // a.h.e.e
    public Typeface b(Context context, CancellationSignal cancellationSignal, h[] hVarArr, int i) {
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            int length = hVarArr.length;
            FontFamily.Builder builder = null;
            int i2 = 0;
            while (true) {
                int i3 = 1;
                if (i2 >= length) {
                    if (builder == null) {
                        return null;
                    }
                    return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i & 1) != 0 ? 700 : 400, (i & 2) != 0 ? 1 : 0)).build();
                }
                h hVar = hVarArr[i2];
                try {
                    parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(hVar.f762a, "r", cancellationSignal);
                } catch (IOException unused) {
                }
                if (parcelFileDescriptorOpenFileDescriptor == null) {
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                    }
                    i2++;
                } else {
                    try {
                        Font.Builder weight = new Font.Builder(parcelFileDescriptorOpenFileDescriptor).setWeight(hVar.f764c);
                        if (!hVar.f765d) {
                            i3 = 0;
                        }
                        Font fontBuild = weight.setSlant(i3).setTtcIndex(hVar.f763b).build();
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
                i2++;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // a.h.e.e
    public Typeface c(Context context, Resources resources, int i, String str, int i2) {
        try {
            Font fontBuild = new Font.Builder(resources, i).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(fontBuild).build()).setStyle(fontBuild.getStyle()).build();
        } catch (Exception unused) {
            return null;
        }
    }
}
