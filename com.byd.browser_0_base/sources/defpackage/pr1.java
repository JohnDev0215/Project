package defpackage;

import android.content.Context;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: OmniboxFeatures.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pr1 {
    public static final sh a = new sh("OmniboxModernizeVisualUpdate", "enable_modernize_visual_update_on_tablet", false);
    public static final sh b = new sh("OmniboxModernizeVisualUpdate", "modernize_visual_update_active_color_on_omnibox", false);
    public static final sh c = new sh("OmniboxModernizeVisualUpdate", "modernize_visual_update_small_bottom_margin", false);
    public static final yj1 d = new yj1("OmniboxConsumesImeInsets", false);
    public static final yj1 e = new yj1("OmniboxAdaptNarrowTabletWindows", false);

    public static boolean a() {
        return d.a();
    }

    public static boolean b() {
        return bq.H.a();
    }

    public static boolean c() {
        return bq.K.a();
    }

    public static boolean d() {
        return b.c();
    }

    public static boolean e(Context context) {
        return bq.I.a() && (!DeviceFormFactor.a(context) || a.c());
    }
}
