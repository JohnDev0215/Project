package defpackage;

import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;
import androidx.emoji2.text.d;

/* JADX INFO: compiled from: EmojiTransformationMethod.java */
/* JADX INFO: loaded from: classes.dex */
public final class sg0 implements TransformationMethod {
    public final TransformationMethod e;

    public sg0(TransformationMethod transformationMethod) {
        this.e = transformationMethod;
    }

    @Override // android.text.method.TransformationMethod
    public final CharSequence getTransformation(CharSequence charSequence, View view) {
        if (view.isInEditMode()) {
            return charSequence;
        }
        TransformationMethod transformationMethod = this.e;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, view);
        }
        if (charSequence == null || d.a().b() != 1) {
            return charSequence;
        }
        d dVarA = d.a();
        dVarA.getClass();
        return dVarA.f(0, charSequence.length(), charSequence);
    }

    @Override // android.text.method.TransformationMethod
    public final void onFocusChanged(View view, CharSequence charSequence, boolean z, int i, Rect rect) {
        TransformationMethod transformationMethod = this.e;
        if (transformationMethod != null) {
            transformationMethod.onFocusChanged(view, charSequence, z, i, rect);
        }
    }
}
