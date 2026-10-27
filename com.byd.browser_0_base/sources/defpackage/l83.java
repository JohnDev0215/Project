package defpackage;

import J.N;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import org.chromium.content.R$style;
import org.chromium.content.browser.input.SuggestionInfo;
import org.chromium.content.browser.input.TextSuggestionHost;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: TextSuggestionsPopupWindow.java */
/* JADX INFO: loaded from: classes3.dex */
public final class l83 extends jw2 {
    public SuggestionInfo[] v;
    public final TextAppearanceSpan w;
    public final TextAppearanceSpan x;

    public l83(Context context, TextSuggestionHost textSuggestionHost, WindowAndroid windowAndroid, View view) {
        super(context, textSuggestionHost, windowAndroid, view);
        int i = R$style.TextAppearance_SuggestionPrefixOrSuffix;
        this.w = new TextAppearanceSpan(context, i);
        this.x = new TextAppearanceSpan(context, i);
    }

    @Override // defpackage.jw2
    public final void a(int i) {
        SuggestionInfo suggestionInfo = this.v[i];
        int i2 = suggestionInfo.a;
        TextSuggestionHost textSuggestionHost = this.f;
        N.MIADbBhq(textSuggestionHost.e, textSuggestionHost, i2, suggestionInfo.b);
    }

    @Override // defpackage.jw2
    public final Object b(int i) {
        return this.v[i];
    }

    @Override // defpackage.jw2
    public final SpannableString c(int i) {
        SuggestionInfo suggestionInfo = this.v[i];
        StringBuilder sb = new StringBuilder();
        sb.append(suggestionInfo.c);
        String str = suggestionInfo.d;
        sb.append(str);
        String str2 = suggestionInfo.e;
        sb.append(str2);
        SpannableString spannableString = new SpannableString(sb.toString());
        TextAppearanceSpan textAppearanceSpan = this.w;
        String str3 = suggestionInfo.c;
        spannableString.setSpan(textAppearanceSpan, 0, str3.length(), 33);
        spannableString.setSpan(this.x, str.length() + str3.length(), str2.length() + str.length() + str3.length(), 33);
        return spannableString;
    }

    @Override // defpackage.jw2
    public final int d() {
        return this.v.length;
    }
}
