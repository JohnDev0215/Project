package V6;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.view.View;
import h6.n;

/* JADX INFO: loaded from: classes.dex */
public final class f extends URLSpan {
    public final T6.f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f9541m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.hints.i f9542n;

    public f(T6.f fVar, String str, io.sentry.hints.i iVar) {
        super(str);
        this.l = fVar;
        this.f9541m = str;
        this.f9542n = iVar;
    }

    @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
    public final void onClick(View view) {
        this.f9542n.getClass();
        String str = this.f9541m;
        Uri uriBuild = Uri.parse(str);
        if (TextUtils.isEmpty(uriBuild.getScheme())) {
            uriBuild = uriBuild.buildUpon().scheme("https").build();
        }
        Context context = view.getContext();
        Intent intent = new Intent("android.intent.action.VIEW", uriBuild);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            n.t("LinkResolverDef", "Actvity was not found for the link: '" + str + "'");
        }
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        this.l.getClass();
        textPaint.setUnderlineText(true);
        textPaint.setColor(textPaint.linkColor);
    }
}
