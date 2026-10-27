package org.chromium.chrome.browser.about_settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.preference.Preference;
import defpackage.xz;
import org.chromium.base.LocaleUtils;
import org.chromium.chrome.R$styleable;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;

/* JADX INFO: loaded from: classes.dex */
public class HyperlinkPreference extends Preference {
    public final int S;

    public HyperlinkPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HyperlinkPreference, 0, 0);
        this.S = typedArrayObtainStyledAttributes.getResourceId(R$styleable.HyperlinkPreference_url, 0);
        typedArrayObtainStyledAttributes.recycle();
        P();
    }

    @Override // androidx.preference.Preference
    public final void z() {
        Context context = this.e;
        CustomTabActivity.i3(xz.a(context), context.getString(this.S).replace("$LOCALE", LocaleUtils.getDefaultLocaleString().replace('-', '_')));
    }
}
