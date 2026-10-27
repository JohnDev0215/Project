package X0;

import K7.K;
import T.C0735d;
import T.C0738e0;
import T.D;
import T.S;
import V0.i;
import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import m0.f;
import n0.AbstractC1864N;

/* JADX INFO: loaded from: classes.dex */
public final class b extends CharacterStyle implements UpdateAppearance {
    public final AbstractC1864N l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f9968m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0738e0 f9969n = C0735d.O(new f(9205357640488583168L), S.f8224q);
    public final D o = C0735d.G(new K(this, 22));

    public b(AbstractC1864N abstractC1864N, float f10) {
        this.l = abstractC1864N;
        this.f9968m = f10;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        i.b(textPaint, this.f9968m);
        textPaint.setShader((Shader) this.o.getValue());
    }
}
