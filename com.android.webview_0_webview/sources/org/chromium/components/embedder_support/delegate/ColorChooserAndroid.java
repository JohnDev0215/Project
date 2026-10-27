package org.chromium.components.embedder_support.delegate;

import android.content.Context;
import defpackage.AbstractC0279Pk;
import defpackage.AlertDialogC1738wx;
import defpackage.C0683dt;
import defpackage.C1409qx;
import defpackage.Is;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ColorChooserAndroid {
    public final AlertDialogC1738wx a;
    public final long b;

    public ColorChooserAndroid(long j, Context context, int i, ColorSuggestion[] colorSuggestionArr) {
        C1409qx c1409qx = new C1409qx(this);
        this.b = j;
        this.a = new AlertDialogC1738wx(context, c1409qx, i, colorSuggestionArr);
    }

    public static void addToColorSuggestionArray(ColorSuggestion[] colorSuggestionArr, int i, int i2, String str) {
        colorSuggestionArr[i] = new ColorSuggestion(i2, str);
    }

    public static ColorChooserAndroid createColorChooserAndroid(long j, WindowAndroid windowAndroid, int i, ColorSuggestion[] colorSuggestionArr) {
        if (windowAndroid == null) {
            return null;
        }
        Context context = (Context) windowAndroid.S().get();
        if (Is.a(context) == null) {
            return null;
        }
        ColorChooserAndroid colorChooserAndroid = new ColorChooserAndroid(j, context, i, colorSuggestionArr);
        C0683dt c0683dtX = C0683dt.x();
        try {
            colorChooserAndroid.a.show();
            c0683dtX.close();
            return colorChooserAndroid;
        } catch (Throwable th) {
            try {
                c0683dtX.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }

    public static ColorSuggestion[] createColorSuggestionArray(int i) {
        return new ColorSuggestion[i];
    }

    public void closeColorChooser() {
        this.a.dismiss();
    }
}
