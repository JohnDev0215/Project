package V1;

import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class i implements TransformationMethod {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TransformationMethod f9326a;

    public i(TransformationMethod transformationMethod) {
        this.f9326a = transformationMethod;
    }

    @Override // android.text.method.TransformationMethod
    public final CharSequence getTransformation(CharSequence charSequence, View view) {
        if (view.isInEditMode()) {
            return charSequence;
        }
        TransformationMethod transformationMethod = this.f9326a;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, view);
        }
        if (charSequence == null || T1.i.a().b() != 1) {
            return charSequence;
        }
        T1.i iVarA = T1.i.a();
        iVarA.getClass();
        return iVarA.f(0, charSequence.length(), 0, charSequence);
    }

    @Override // android.text.method.TransformationMethod
    public final void onFocusChanged(View view, CharSequence charSequence, boolean z6, int i7, Rect rect) {
        TransformationMethod transformationMethod = this.f9326a;
        if (transformationMethod != null) {
            transformationMethod.onFocusChanged(view, charSequence, z6, i7, rect);
        }
    }
}
