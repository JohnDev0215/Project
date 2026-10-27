package defpackage;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class R6 implements TypeEvaluator {
    @Override // android.animation.TypeEvaluator
    public Object evaluate(float f, Object obj, Object obj2) {
        Rect rect = (Rect) obj;
        Rect rect2 = (Rect) obj2;
        return new Rect(rect.left + ((int) ((rect2.left - r0) * f)), rect.top + ((int) ((rect2.top - r1) * f)), rect.right + ((int) ((rect2.right - r2) * f)), rect.bottom + ((int) ((rect2.bottom - r6) * f)));
    }
}
