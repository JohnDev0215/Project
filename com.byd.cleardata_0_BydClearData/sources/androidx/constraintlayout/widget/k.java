package androidx.constraintlayout.widget;

import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes.dex */
public abstract class k extends c {
    private boolean j;
    private boolean k;

    @Override // androidx.constraintlayout.widget.c
    protected void g(AttributeSet attributeSet) {
        super.g(attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, j.a1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == j.c1) {
                    this.j = true;
                } else if (index == j.h1) {
                    this.k = true;
                }
            }
        }
    }

    public void n(a.e.b.k.k kVar, int i, int i2) {
    }

    @Override // androidx.constraintlayout.widget.c, android.view.View
    public void onAttachedToWindow() {
        ViewParent parent;
        super.onAttachedToWindow();
        if ((this.j || this.k) && (parent = getParent()) != null && (parent instanceof ConstraintLayout)) {
            ConstraintLayout constraintLayout = (ConstraintLayout) parent;
            int visibility = getVisibility();
            float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
            for (int i = 0; i < this.f1103c; i++) {
                View viewG = constraintLayout.g(this.f1102b[i]);
                if (viewG != null) {
                    if (this.j) {
                        viewG.setVisibility(visibility);
                    }
                    if (this.k && elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                        viewG.setTranslationZ(viewG.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        c();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        c();
    }
}
