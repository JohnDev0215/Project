package K6;

import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;

/* JADX INFO: loaded from: classes.dex */
public final class m extends CharacterStyle implements UpdateAppearance {
    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        A7.m.f(textPaint, "tp");
        textPaint.setUnderlineText(false);
        textPaint.setFakeBoldText(true);
    }
}
