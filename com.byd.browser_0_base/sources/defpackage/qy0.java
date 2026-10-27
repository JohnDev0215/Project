package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import defpackage.k81;
import defpackage.om3;
import java.util.WeakHashMap;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: IncognitoReauthMenuDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class qy0 implements k81.a {
    public final Context e;
    public final Runnable f;
    public final SettingsLauncher g;
    public final zd h;

    public qy0(Context context, o90 o90Var, SettingsLauncher settingsLauncher) {
        this.e = context;
        this.f = o90Var;
        this.g = settingsLauncher;
        ya1 ya1Var = new ya1();
        ya1Var.t(b(1));
        ya1Var.t(b(2));
        int i = R$color.menu_item_bg_color_dark_baseline;
        zd zdVar = new zd(context, ya1Var, this);
        Object obj = bz.a;
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(context.getColor(i));
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.i.j(zdVar.g, colorStateListValueOf);
        this.h = zdVar;
    }

    public static xa1 b(int i) {
        if (i == 1) {
            return c(R$string.menu_close_all_incognito_tabs, R$drawable.btn_close, R$color.default_icon_color_secondary_light_tint_list, R$style.TextAppearance_TextLarge_Primary_Baseline_Light);
        }
        if (i != 2) {
            return null;
        }
        return c(R$string.menu_settings, R$drawable.settings_cog, R$color.default_icon_color_secondary_light_tint_list, R$style.TextAppearance_TextLarge_Primary_Baseline_Light);
    }

    public static xa1 c(int i, int i2, int i3, int i4) {
        PropertyModel.b bVar = new PropertyModel.b(o81.k);
        bVar.c(o81.a, i);
        bVar.c(o81.f, 0);
        bVar.c(o81.d, i2);
        bVar.b(o81.g, true);
        bVar.c(o81.h, i3);
        bVar.c(o81.i, i4);
        bVar.b(o81.j, true);
        return new xa1(1, bVar.a());
    }

    @Override // k81.a
    public final void a(PropertyModel propertyModel) {
        int iH = propertyModel.h(o81.a);
        if (iH == R$string.menu_close_all_incognito_tabs) {
            this.f.run();
        } else if (iH == R$string.menu_settings) {
            this.g.i(this.e);
        }
    }
}
