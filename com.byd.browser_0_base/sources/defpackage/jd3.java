package defpackage;

import android.view.View;
import defpackage.wm3;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: ToolbarUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class jd3 {
    public static void a(xe3 xe3Var, View view, View view2) {
        u73 u73Var = new u73(view2.getContext(), view2, R$string.iph_download_settings_text, R$string.iph_download_settings_accessibility_text, new nn3(view), qo.f().c());
        u73Var.e(true);
        u73Var.addOnDismissListener(new gd3(xe3Var, view, 0));
        wm3.b(view, new wm3.a(1));
        u73Var.f();
    }
}
