package k1;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

/* JADX INFO: compiled from: RectEvaluator.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements TypeEvaluator<Rect> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Rect f5773a;

    @Override // android.animation.TypeEvaluator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Rect evaluate(float f8, Rect rect, Rect rect2) {
        int i8 = rect.left + ((int) ((rect2.left - r0) * f8));
        int i9 = rect.top + ((int) ((rect2.top - r1) * f8));
        int i10 = rect.right + ((int) ((rect2.right - r2) * f8));
        int i11 = rect.bottom + ((int) ((rect2.bottom - r6) * f8));
        Rect rect3 = this.f5773a;
        if (rect3 == null) {
            return new Rect(i8, i9, i10, i11);
        }
        rect3.set(i8, i9, i10, i11);
        return this.f5773a;
    }
}
