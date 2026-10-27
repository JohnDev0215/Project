package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import org.chromium.chrome.R$attr;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$style;

/* JADX INFO: compiled from: OmniboxResourceProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rr1 {
    public static int a(int i, Context context, boolean z) {
        if (z) {
            return 2;
        }
        if (i == yp.a(context, z)) {
            return 3;
        }
        return z7.h0(i) ? 1 : 0;
    }

    public static int b(Context context, int i) {
        Resources resources = context.getResources();
        return i == 0 ? resources.getColor(R$color.branded_url_text_on_light_bg) : i == 1 ? resources.getColor(R$color.branded_url_text_on_dark_bg) : i == 2 ? resources.getColor(R$color.url_bar_primary_text_incognito) : oj2.n(R$attr.colorOnSurface, context, "OmniboxResourceProvider");
    }

    public static int c(Context context, int i) {
        Resources resources = context.getResources();
        return i == 0 ? resources.getColor(R$color.branded_url_text_variant_on_light_bg) : i == 1 ? resources.getColor(R$color.branded_url_text_variant_on_dark_bg) : i == 2 ? resources.getColor(R$color.url_bar_secondary_text_incognito) : oj2.n(R$attr.colorOnSurfaceVariant, context, "OmniboxResourceProvider");
    }

    public static Drawable d(Context context, int i, int i2) {
        if (i == 2) {
            context = pn1.a(R$style.Theme_Chromium_TabbedMode, context, true);
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i2, typedValue, true);
        int i3 = typedValue.resourceId;
        Object obj = bz.a;
        return context.getDrawable(i3);
    }
}
