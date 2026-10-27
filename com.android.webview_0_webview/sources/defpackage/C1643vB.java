package defpackage;

import J.N;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import org.chromium.content.browser.input.SuggestionInfo;
import org.chromium.content.browser.input.TextSuggestionHost;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: renamed from: vB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1643vB extends AbstractViewOnClickListenerC1423rB {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public SuggestionInfo[] f34J;
    public TextAppearanceSpan K;
    public TextAppearanceSpan L;

    public C1643vB(Context context, TextSuggestionHost textSuggestionHost, WindowAndroid windowAndroid, View view) {
        super(context, textSuggestionHost, windowAndroid, view);
        this.K = new TextAppearanceSpan(context, AbstractC0622cn.Z5);
        this.L = new TextAppearanceSpan(context, AbstractC0622cn.Z5);
    }

    @Override // defpackage.AbstractViewOnClickListenerC1423rB
    public void a(int i) {
        SuggestionInfo suggestionInfo = this.f34J[i];
        TextSuggestionHost textSuggestionHost = this.t;
        N.MIADbBhq(textSuggestionHost.s, textSuggestionHost, suggestionInfo.a, suggestionInfo.b);
    }

    @Override // defpackage.AbstractViewOnClickListenerC1423rB
    public Object b(int i) {
        return this.f34J[i];
    }

    @Override // defpackage.AbstractViewOnClickListenerC1423rB
    public SpannableString c(int i) {
        SuggestionInfo suggestionInfo = this.f34J[i];
        SpannableString spannableString = new SpannableString(suggestionInfo.c + suggestionInfo.d + suggestionInfo.e);
        spannableString.setSpan(this.K, 0, suggestionInfo.c.length(), 33);
        spannableString.setSpan(this.L, suggestionInfo.d.length() + suggestionInfo.c.length(), suggestionInfo.e.length() + suggestionInfo.d.length() + suggestionInfo.c.length(), 33);
        return spannableString;
    }

    @Override // defpackage.AbstractViewOnClickListenerC1423rB
    public int d() {
        return this.f34J.length;
    }

    public void f(double d, double d2, String str, SuggestionInfo[] suggestionInfoArr) {
        this.f34J = (SuggestionInfo[]) suggestionInfoArr.clone();
        this.C.setVisibility(8);
        super.e(d, d2, str);
    }
}
