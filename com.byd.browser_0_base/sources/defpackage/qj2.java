package defpackage;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.preference.d;
import org.chromium.components.browser_ui.settings.R$color;

/* JADX INFO: compiled from: SettingsUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qj2 {
    public static void a(d dVar, int i) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            dVar.H0(i);
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    public static Drawable b(Context context, int i) {
        return c(context, i, R$color.default_icon_color_tint_list);
    }

    public static Drawable c(Context context, int i, int i2) {
        Drawable drawableA = AppCompatResources.a(context, i);
        drawableA.setColorFilter(bz.b(context, i2).getDefaultColor(), PorterDuff.Mode.SRC_IN);
        return drawableA;
    }
}
