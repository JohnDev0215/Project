package defpackage;

import J.N;
import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import org.chromium.content.browser.input.TextSuggestionHost;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: SpellCheckPopupWindow.java */
/* JADX INFO: loaded from: classes3.dex */
public final class rq2 extends jw2 {
    public String[] v;

    public rq2(Context context, TextSuggestionHost textSuggestionHost, WindowAndroid windowAndroid, View view) {
        super(context, textSuggestionHost, windowAndroid, view);
        this.v = new String[0];
    }

    @Override // defpackage.jw2
    public final void a(int i) {
        String str = this.v[i];
        TextSuggestionHost textSuggestionHost = this.f;
        N.M7RnYR2r(textSuggestionHost.e, textSuggestionHost, str);
    }

    @Override // defpackage.jw2
    public final Object b(int i) {
        return this.v[i];
    }

    @Override // defpackage.jw2
    public final SpannableString c(int i) {
        return new SpannableString(this.v[i]);
    }

    @Override // defpackage.jw2
    public final int d() {
        return this.v.length;
    }
}
