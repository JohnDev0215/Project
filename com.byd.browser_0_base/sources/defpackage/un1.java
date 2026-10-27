package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.TypedValue;
import android.view.View;
import org.chromium.base.Callback;
import org.chromium.ui.R$attr;
import org.chromium.ui.R$color;

/* JADX INFO: compiled from: NoUnderlineClickableSpan.java */
/* JADX INFO: loaded from: classes3.dex */
public final class un1 extends ClickableSpan {
    public final int e;
    public final Callback<View> f;

    public un1(Context context, Callback<View> callback) {
        Resources.Theme theme = context.getTheme();
        int i = R$attr.globalClickableSpanColor;
        int i2 = R$color.default_text_color_link_baseline;
        TypedValue typedValue = new TypedValue();
        this.e = theme.resolveAttribute(i, typedValue, true) ? typedValue.data : theme.getResources().getColor(i2, theme);
        this.f = callback;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        this.f.onResult(view);
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setUnderlineText(false);
        textPaint.setColor(this.e);
    }

    public un1(Context context, int i, lj1 lj1Var) {
        this.e = context.getColor(i);
        this.f = lj1Var;
    }
}
