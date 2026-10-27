package org.chromium.components.embedder_support.delegate;

import J.N;
import android.content.Context;
import defpackage.av;
import defpackage.bs1;
import defpackage.ku2;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class ColorChooserAndroid {
    public final av a;
    public final long b;

    public class a implements bs1 {
        public a() {
        }

        @Override // defpackage.bs1
        public final void a(int i) {
            ColorChooserAndroid colorChooserAndroid = ColorChooserAndroid.this;
            colorChooserAndroid.a.dismiss();
            N.M2zph6xH(colorChooserAndroid.b, colorChooserAndroid, i);
        }
    }

    public ColorChooserAndroid(long j, Context context, int i, ColorSuggestion[] colorSuggestionArr) {
        a aVar = new a();
        this.b = j;
        this.a = new av(context, aVar, i, colorSuggestionArr);
    }

    @CalledByNative
    public static void addToColorSuggestionArray(ColorSuggestion[] colorSuggestionArr, int i, int i2, String str) {
        colorSuggestionArr[i] = new ColorSuggestion(i2, str);
    }

    @CalledByNative
    public static ColorChooserAndroid createColorChooserAndroid(long j, WindowAndroid windowAndroid, int i, ColorSuggestion[] colorSuggestionArr) {
        if (windowAndroid == null) {
            return null;
        }
        Context context = windowAndroid.i.get();
        if (xz.a(context) == null) {
            return null;
        }
        ColorChooserAndroid colorChooserAndroid = new ColorChooserAndroid(j, context, i, colorSuggestionArr);
        ku2 ku2VarT = ku2.t();
        try {
            colorChooserAndroid.a.show();
            ku2VarT.close();
            return colorChooserAndroid;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @CalledByNative
    public static ColorSuggestion[] createColorSuggestionArray(int i) {
        return new ColorSuggestion[i];
    }

    @CalledByNative
    public void closeColorChooser() {
        this.a.dismiss();
    }
}
