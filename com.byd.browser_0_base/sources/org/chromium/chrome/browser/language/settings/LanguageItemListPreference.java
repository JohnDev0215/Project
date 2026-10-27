package org.chromium.chrome.browser.language.settings;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import defpackage.f51;
import java.util.ArrayList;
import org.chromium.chrome.browser.language.settings.LanguageItemListFragment;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;

/* JADX INFO: loaded from: classes.dex */
public class LanguageItemListPreference extends ChromeBasePreference implements PrefChangeRegistrar.a {
    public LanguageItemListFragment.b Y;

    public LanguageItemListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void Z() {
        String strJoin;
        if (this.Y == null) {
            strJoin = null;
        } else {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (f51 f51Var : this.Y.b()) {
                i++;
                if (i > 3) {
                    break;
                } else {
                    arrayList.add(f51Var.b);
                }
            }
            strJoin = TextUtils.join(", ", arrayList);
        }
        if (strJoin == null) {
            return;
        }
        R(strJoin);
    }

    @Override // org.chromium.chrome.browser.preferences.PrefChangeRegistrar.a
    public final void f() {
        Z();
    }
}
