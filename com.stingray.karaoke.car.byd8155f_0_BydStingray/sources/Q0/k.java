package Q0;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

/* JADX INFO: loaded from: classes.dex */
public final class k extends CharacterStyle {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f6105a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f6106b;

    public k(boolean z6, boolean z10) {
        this.f6105a = z6;
        this.f6106b = z10;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(this.f6105a);
        textPaint.setStrikeThruText(this.f6106b);
    }
}
