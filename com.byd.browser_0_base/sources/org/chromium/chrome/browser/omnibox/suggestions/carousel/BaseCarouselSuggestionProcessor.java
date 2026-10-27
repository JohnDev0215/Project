package org.chromium.chrome.browser.omnibox.suggestions.carousel;

import android.content.Context;
import defpackage.dd;
import defpackage.dw2;
import org.chromium.chrome.R$dimen;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseCarouselSuggestionProcessor implements dw2 {
    public final Context a;
    public final int b;

    public BaseCarouselSuggestionProcessor(Context context) {
        this.a = context;
        this.b = context.getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_header_height);
    }

    @Override // defpackage.dw2, defpackage.ee0
    public final boolean b() {
        return false;
    }

    @Override // defpackage.dw2
    public void c(int i, AutocompleteMatch autocompleteMatch, PropertyModel propertyModel) {
        propertyModel.l(dd.d, DeviceFormFactor.a(this.a));
    }

    public abstract int e();

    @Override // defpackage.ee0
    public final int o() {
        return e() + this.b;
    }
}
