package Q0;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

/* JADX INFO: loaded from: classes.dex */
public final class j extends CharacterStyle {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f6103a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f6104b;
    public final float c;
    public final float d;

    public j(float f10, float f11, float f12, int i7) {
        this.f6103a = i7;
        this.f6104b = f10;
        this.c = f11;
        this.d = f12;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        textPaint.setShadowLayer(this.d, this.f6104b, this.c, this.f6103a);
    }
}
