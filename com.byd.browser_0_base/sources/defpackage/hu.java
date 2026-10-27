package defpackage;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.Arrays;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.omnibox.suggestions.base.c;
import org.chromium.chrome.browser.omnibox.suggestions.base.d;
import org.chromium.chrome.browser.omnibox.suggestions.base.e;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ClipboardSuggestionProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hu extends c {
    public hu(Context context, d dVar, mk0 mk0Var) {
        super(context, dVar, null, mk0Var);
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.base.c, defpackage.dw2
    public final void c(int i, AutocompleteMatch autocompleteMatch, PropertyModel propertyModel) {
        super.c(i, autocompleteMatch, propertyModel);
        propertyModel.l(fw2.a, !(autocompleteMatch.a == 19));
        propertyModel.p(fw2.b, new ew2(autocompleteMatch.f));
        j(autocompleteMatch, propertyModel, false);
    }

    @Override // defpackage.dw2
    public final boolean d(AutocompleteMatch autocompleteMatch, int i) {
        int i2 = autocompleteMatch.a;
        return i2 == 19 || i2 == 26 || i2 == 27;
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.base.c
    public final void f(AutocompleteMatch autocompleteMatch, int i) {
        if (!autocompleteMatch.j.k()) {
            super.f(autocompleteMatch, i);
            return;
        }
        gu guVar = new gu(this, i, 0, autocompleteMatch);
        long j = autocompleteMatch.w;
        if (j == 0) {
            guVar.run();
        } else {
            N.MrcKeMB9(j, guVar);
        }
    }

    public final void j(AutocompleteMatch autocompleteMatch, PropertyModel propertyModel, boolean z) {
        byte[] bArr;
        Bitmap bitmapDecodeByteArray;
        propertyModel.p(fw2.c, new ew2(z ? autocompleteMatch.d : ""));
        boolean z2 = autocompleteMatch.a == 19;
        int i = z2 ? R$drawable.ic_globe_24dp : R$drawable.ic_suggestion_magnifier;
        Context context = this.a;
        e.a aVarC = e.a.c(context, i);
        aVarC.b = true;
        c.g(propertyModel, aVarC.a());
        if (z) {
            if (autocompleteMatch.a == 27 && (bArr = autocompleteMatch.t) != null && bArr.length > 0 && (bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length)) != null) {
                if (bitmapDecodeByteArray.getWidth() > 0 && bitmapDecodeByteArray.getHeight() > 0) {
                    int width = bitmapDecodeByteArray.getWidth();
                    int i2 = this.e;
                    if (width > i2 || bitmapDecodeByteArray.getHeight() > i2) {
                        float fMax = i2 / Math.max(bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight());
                        bitmapDecodeByteArray = Bitmap.createScaledBitmap(bitmapDecodeByteArray, Math.round(bitmapDecodeByteArray.getWidth() * fMax), Math.round(fMax * bitmapDecodeByteArray.getHeight()), true);
                    }
                }
                e.a aVarB = e.a.b(context, bitmapDecodeByteArray);
                aVarB.c = true;
                aVarB.d = true;
                c.g(propertyModel, aVarB.a());
            } else if (z2) {
                this.d.b(autocompleteMatch.j, false, new i40(this, propertyModel));
            }
        }
        int i3 = z ? R$drawable.ic_visibility_off_black : R$drawable.ic_visibility_black;
        String string = context.getResources().getString(z ? R$string.accessibility_omnibox_conceal_clipboard_contents : R$string.accessibility_omnibox_reveal_clipboard_contents);
        String string2 = context.getResources().getString(z ? R$string.accessibility_omnibox_conceal_button_announcement : R$string.accessibility_omnibox_reveal_button_announcement);
        int i4 = 2;
        Runnable w70Var = z ? new w70(this, i4, autocompleteMatch, propertyModel) : new tf3(this, i4, autocompleteMatch, propertyModel);
        e.a aVarC2 = e.a.c(context, i3);
        aVarC2.d = true;
        aVarC2.b = true;
        propertyModel.p(org.chromium.chrome.browser.omnibox.suggestions.base.d.b, Arrays.asList(new d.a(aVarC2.a(), string, string2, w70Var)));
    }

    @Override // defpackage.ee0
    public final int m() {
        return 5;
    }

    @Override // defpackage.ee0
    public final PropertyModel n() {
        return new PropertyModel(fw2.f);
    }
}
