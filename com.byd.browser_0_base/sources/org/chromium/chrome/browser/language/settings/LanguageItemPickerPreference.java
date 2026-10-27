package org.chromium.chrome.browser.language.settings;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import defpackage.f51;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;

/* JADX INFO: loaded from: classes.dex */
public class LanguageItemPickerPreference extends ChromeBasePreference {
    public f51 Y;
    public boolean Z;

    public LanguageItemPickerPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void Z(String str) {
        this.Y = TextUtils.equals(str, null) ? f51.a() : c.b().c(str);
        a0();
    }

    public final void a0() {
        f51 f51Var = this.Y;
        if (f51Var == null) {
            return;
        }
        String str = f51Var.b;
        if (!this.Z) {
            R(str);
            return;
        }
        T(str);
        String str2 = this.Y.c;
        if (TextUtils.equals(str, str2)) {
            R("");
        } else {
            R(str2);
        }
    }
}
