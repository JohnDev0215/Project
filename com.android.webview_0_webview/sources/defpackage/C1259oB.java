package defpackage;

import J.N;
import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import org.chromium.content.browser.input.TextSuggestionHost;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: renamed from: oB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1259oB extends AbstractViewOnClickListenerC1423rB {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public String[] f25J;

    public C1259oB(Context context, TextSuggestionHost textSuggestionHost, WindowAndroid windowAndroid, View view) {
        super(context, textSuggestionHost, windowAndroid, view);
        this.f25J = new String[0];
    }

    @Override // defpackage.AbstractViewOnClickListenerC1423rB
    public void a(int i) {
        TextSuggestionHost textSuggestionHost = this.t;
        N.M7RnYR2r(textSuggestionHost.s, textSuggestionHost, this.f25J[i]);
    }

    @Override // defpackage.AbstractViewOnClickListenerC1423rB
    public Object b(int i) {
        return this.f25J[i];
    }

    @Override // defpackage.AbstractViewOnClickListenerC1423rB
    public SpannableString c(int i) {
        return new SpannableString(this.f25J[i]);
    }

    @Override // defpackage.AbstractViewOnClickListenerC1423rB
    public int d() {
        return this.f25J.length;
    }

    public void f(double d, double d2, String str, String[] strArr) {
        this.f25J = (String[]) strArr.clone();
        this.C.setVisibility(0);
        super.e(d, d2, str);
    }
}
